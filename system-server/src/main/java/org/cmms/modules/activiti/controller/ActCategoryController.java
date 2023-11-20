package org.cmms.modules.activiti.controller;

import org.cmms.common.constant.CommonConstant;
import org.cmms.common.exception.XbootException;
//import org.cmms.common.redis.RedisTemplateHelper;
import org.cmms.common.utils.CommonUtil;
import org.cmms.common.utils.ResultUtil;
import org.cmms.common.vo.Result;
import org.cmms.modules.activiti.entity.ActCategory;
import org.cmms.modules.activiti.entity.ActProcess;
import org.cmms.modules.activiti.service.ActCategoryService;
import org.cmms.modules.activiti.service.ActProcessService;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author Exrick
 */
@Slf4j
@RestController
@Api(tags = "流程分类管理接口")
@RequestMapping("/actCategory")
public class ActCategoryController {

    @Autowired
    private ActCategoryService actCategoryService;

    @Autowired
    private ActProcessService actProcessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

  /*  @Autowired
    private RedisTemplateHelper redisTemplateHelper;*/

    @RequestMapping(value = "/getByParentId/{parentId}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    public Result<List<ActCategory>> getByParentId(@PathVariable String parentId){

        List<ActCategory> list = actCategoryService.findByParentIdOrderBySortOrder(parentId);
        setInfo(list);
        return new ResultUtil<List<ActCategory>>().setData(list);
    }

    @ApiOperation(value = "添加")
    @PostMapping(value = "/add")
    public Result<Object> add(@RequestBody ActCategory actCategory){
        actCategoryService.save(actCategory);
        // 如果不是添加的一级 判断设置上级为父节点标识
        if(!CommonConstant.PARENT_ID.equals(actCategory.getParentId())){
            ActCategory parent = actCategoryService.get(actCategory.getParentId());
            if(parent.getIsParent()==null||!parent.getIsParent()){
                parent.setIsParent(true);
                actCategoryService.update(parent);
            }
        }
        // 更新缓存
      //  Set<String> keys = redisTemplateHelper.keys("actCategory::*");
        //redisTemplate.delete(keys);
        return ResultUtil.success("添加成功");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑")
    public Result<Object> edit(ActCategory actCategory){

        ActCategory old = actCategoryService.get(actCategory.getId());
        actCategoryService.update(actCategory);
        // 若修改了分类名称
        if(!old.getTitle().equals(actCategory.getTitle())){
            actProcessService.updateCategoryTitle(actCategory.getId(), actCategory.getTitle());
        }
        // 手动删除所有分类缓存
       // Set<String> keys = redisTemplateHelper.keys("actCategory:" + "*");
       // redisTemplate.delete(keys);
        return ResultUtil.success("编辑成功");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            deleteRecursion(id, ids);
        }
        // 手动删除所有缓存
        //Set<String> keys = redisTemplateHelper.keys("actCategory:" + "*");
        //redisTemplate.delete(keys);
        return ResultUtil.success("批量通过id删除数据成功");
    }

    public void deleteRecursion(String id, String[] ids){

        List<ActProcess> list = actProcessService.findByCategoryId(id);
        if(list!=null&&list.size()>0){
            throw new XbootException("删除失败，包含正被流程使用关联的分类");
        }
        // 获得其父节点
        ActCategory cat = actCategoryService.get(id);
        ActCategory parent = null;
        if(cat!=null&& StrUtil.isNotBlank(cat.getParentId())){
            parent = actCategoryService.get(cat.getParentId());
        }
        actCategoryService.delete(id);
        // 判断父节点是否还有子节点
        if(parent!=null){
            List<ActCategory> childrenDeps = actCategoryService.findByParentIdOrderBySortOrder(parent.getId());
            if(childrenDeps==null||childrenDeps.size()==0){
                parent.setIsParent(false);
                actCategoryService.update(parent);
            }
        }
        // 递归删除
        List<ActCategory> categories = actCategoryService.findByParentIdOrderBySortOrder(id);
        for(ActCategory c : categories){
            if(!CommonUtil.judgeIds(c.getId(), ids)){
                deleteRecursion(c.getId(), ids);
            }
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "名称模糊搜索")
    public Result<List<ActCategory>> searchByTitle(@RequestParam String title) {

        List<ActCategory> list = actCategoryService.findByTitleLikeOrderBySortOrder("%"+title+"%");
        setInfo(list);
        return new ResultUtil<List<ActCategory>>().setData(list);
    }

    public List<ActCategory> setInfo(List<ActCategory> list){

        // lambda表达式
        list.forEach(item -> {
            if(!CommonConstant.PARENT_ID.equals(item.getParentId())){
                ActCategory parent = actCategoryService.get(item.getParentId());
                item.setParentTitle(parent.getTitle());
            }else{
                item.setParentTitle("一级节点");
            }
        });
        return list;
    }
}
