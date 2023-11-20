package org.cmms.modules.pad.shxxgl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.pad.shxxgl.entity.CamsJbxxShzllb;
import org.cmms.modules.pad.shxxgl.service.ICamsJbxxShzllbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* @Description: 1
* @Author: jeecg-boot
* @Date:   2020-08-02
* @Version: V1.0
*/
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/khgl/shzlgl")
public class KhglShzllbController extends JeecgController<CamsJbxxShzllb, ICamsJbxxShzllbService> {
    @Autowired
    private ICamsJbxxShzllbService camsJbxxShzllbService;

    @Value(value = "${common.path.upload}")
    private String uploadpath;

    /**
     * 分页列表查询
     *
     * @param camsJbxxShzllb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1-分页列表查询")
    @ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CamsJbxxShzllb camsJbxxShzllb,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<CamsJbxxShzllb> queryWrapper = QueryGenerator.initQueryWrapper(camsJbxxShzllb, req.getParameterMap());
       Page<CamsJbxxShzllb> page = new Page<CamsJbxxShzllb>(pageNo, pageSize);
       IPage<CamsJbxxShzllb> pageList = camsJbxxShzllbService.page(page, queryWrapper);
       return Result.ok(pageList);
    }

    /**
     * 获取附件信息
     * @param shid
     * @return
     */
    @GetMapping(value = "/queryFjxxByShid")
    public Result<?> queryFjxxByShid(@Param("shid") String shid) {
        try {
            if (!StringUtils.isEmpty(shid)) {
                List<CamsJbxxShzllb> list = camsJbxxShzllbService.getByShid(shid);
                if (list != null && list.size() > 0) {
                    return Result.ok(list);
                }
            } else {
                return Result.error("请求参数错误！");
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有附件数据");
    }

    /**
     * 保存商户附件信息
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/saveShfjImage",method = RequestMethod.POST)
    public Result<?> saveShfjImage(@RequestBody  List<CamsJbxxShzllb>  jsonObject) {
        try {
            if (jsonObject!=null  && jsonObject.size()>0){
               LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
               for (int i = 0; i < jsonObject.size(); i++) {
                   if (StringUtils.isEmpty(jsonObject.get(i).getId())) {
                       CamsJbxxShzllb fjgl = new CamsJbxxShzllb();
                       fjgl.setQydm(jsonObject.get(i).getQydm());
                       fjgl.setShid(jsonObject.get(i).getShid());
                       fjgl.setShmc(jsonObject.get(i).getShmc());
                       fjgl.setZllx(jsonObject.get(i).getZllx());
                       fjgl.setZldx(jsonObject.get(i).getZldx());
                       fjgl.setFwlj(jsonObject.get(i).getFwlj());
                       fjgl.setZlmc(jsonObject.get(i).getZlmc());
                       fjgl.setZllj(uploadpath + "/" + jsonObject.get(i).getFwlj());
                       fjgl.setScsj(new Date());
                       fjgl.setScr(sysUser.getUsername());
                       fjgl.setLrsj(new Date());
                       fjgl.setLrr(sysUser.getUsername());
                       camsJbxxShzllbService.save(fjgl);
                   }
               }
           }
        }catch (Exception e){
            return  Result.error(e.toString());
        }
        return Result.ok("添加成功");
    }

    /**
     * 删除商户附件信息
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/deleteShfjImage",method = RequestMethod.POST)
    public Result<?> deleteShfjImage(@RequestBody  List<CamsJbxxShzllb> jsonObject) {
        try {
            if (jsonObject!=null  && jsonObject.size()>0){
                for (int i = 0; i < jsonObject.size(); i++) {
                    if (!StringUtils.isEmpty(jsonObject.get(i).getId())){
                        UpdateWrapper<CamsJbxxShzllb> camsJbxxShzllbUpdateWrapper=new UpdateWrapper<>();
                        camsJbxxShzllbUpdateWrapper.eq("id",jsonObject.get(i).getId());
                        camsJbxxShzllbService.remove(camsJbxxShzllbUpdateWrapper);
                    }
                }
            }

        }catch (Exception e){
            return  Result.error(e.toString());
        }
        return Result.ok("添加成功");
    }

    @RequestMapping(value = "/queryByShidAndZllx",method = RequestMethod.GET)
    public Result<?> queryByHhbmAndZllx(@RequestParam(name = "shid") String shid,
                                        @RequestParam(name = "zllx") String zllx) {
        try {
            QueryWrapper<CamsJbxxShzllb> fjxxQueryWrapper=new QueryWrapper<>();
            fjxxQueryWrapper.eq("shid", shid);
            fjxxQueryWrapper.eq("zllx", zllx);
            List<CamsJbxxShzllb> list = camsJbxxShzllbService.list(fjxxQueryWrapper);
            if (list!=null && list.size()>0){
                list.get(0).setZllj("");
                return Result.ok(list.get(0));
            }
        }catch (Exception e){
            log.error("查询商户附件信息失败", e);
            return  Result.error("查询商户附件信息失败");
        }
        return Result.ok("查询成功");
    }


   /**
    * 添加
    *
    * @param camsJbxxShzllb
    * @return
    */
   @AutoLog(value = "1-添加")
   @ApiOperation(value="1-添加", notes="1-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody CamsJbxxShzllb camsJbxxShzllb) {
       camsJbxxShzllbService.save(camsJbxxShzllb);
       return Result.ok("添加成功！");
   }


   /**
    * 编辑
    *
    * @param camsJbxxShzllb
    * @return
    */
   @AutoLog(value = "1-编辑")
   @ApiOperation(value="1-编辑", notes="1-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody CamsJbxxShzllb camsJbxxShzllb) {
       camsJbxxShzllbService.updateById(camsJbxxShzllb);
       return Result.ok("编辑成功!");
   }

   /**
    * 通过id删除
    *
    * @param id
    * @return
    */
   @AutoLog(value = "1-通过id删除")
   @ApiOperation(value="1-通过id删除", notes="1-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       camsJbxxShzllbService.removeById(id);
       return Result.ok("删除成功!");
   }

   /**
    * 批量删除
    *
    * @param ids
    * @return
    */
   @AutoLog(value = "1-批量删除")
   @ApiOperation(value="1-批量删除", notes="1-批量删除")
   @DeleteMapping(value = "/deleteBatch")
   public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       camsJbxxShzllbService.removeByIds(Arrays.asList(ids.split(",")));
       return Result.ok("批量删除成功！");
   }

   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   @AutoLog(value = "1-通过id查询")
   @ApiOperation(value="1-通过id查询", notes="1-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
       CamsJbxxShzllb camsJbxxShzllb = camsJbxxShzllbService.getById(id);
       return Result.ok(camsJbxxShzllb);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param camsJbxxShzllb
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, CamsJbxxShzllb camsJbxxShzllb) {
     return super.exportXls(request, camsJbxxShzllb, CamsJbxxShzllb.class, "1");
 }

 /**
  * 通过excel导入数据
  *
  * @param request
  * @param response
  * @return
  */
 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
     return super.importExcel(request, response, CamsJbxxShzllb.class);
 }

}
