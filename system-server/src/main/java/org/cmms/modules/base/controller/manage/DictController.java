package org.cmms.modules.base.controller.manage;

import org.cmms.common.utils.ResultUtil;
import org.cmms.common.vo.Result;
import org.cmms.modules.base.entity.Dict;
import org.cmms.modules.base.service.DictDataService;
import org.cmms.modules.base.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Exrick
 */
@Slf4j
@RestController
@Api(tags = "字典管理接口")
@RequestMapping("/dict")
@Transactional
public class DictController {

    @Autowired
    private DictService dictService;

    @Autowired
    private DictDataService dictDataService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部数据")
    public Result<List<Dict>> getAll(){

        List<Dict> list = dictService.findAllOrderBySortOrder();
        return new ResultUtil<List<Dict>>().setData(list);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    public Result<Object> add(Dict dict){

        if(dictService.findByType(dict.getType())!=null){
            return ResultUtil.error("字典类型Type已存在");
        }
        dictService.save(dict);
        return ResultUtil.success("添加成功");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑")
    public Result<Object> edit(Dict dict){

        Dict old = dictService.get(dict.getId());
        // 若type修改判断唯一
        if(!old.getType().equals(dict.getType())&&dictService.findByType(dict.getType())!=null){
            return ResultUtil.error("字典类型Type已存在");
        }
        dictService.update(dict);
        return ResultUtil.success("编辑成功");
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "通过id删除")
    public Result<Object> delAllByIds(@RequestParam String[] ids){

        for (String id : ids){
            Dict dict = dictService.get(id);
            dictService.delete(id);
            dictDataService.deleteByDictId(id);
            // 删除缓存
            redisTemplate.delete("dictData::"+dict.getType());
        }
        return ResultUtil.success("删除成功");
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "搜索字典")
    public Result<List<Dict>> searchPermissionList(@RequestParam String key){

        List<Dict> list = dictService.findByTitleOrTypeLike(key);
        return new ResultUtil<List<Dict>>().setData(list);
    }
}
