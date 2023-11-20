package org.cmms.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.modules.system.entity.SysSubSystem;
import org.cmms.modules.system.service.ISysSubSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
* @Description: 子系统信息表
* @Author: jeecg-boot
* @Date:   2020-12-30
* @Version: V1.0
*/
@Slf4j
@Api(tags="子系统信息表")
@RestController
@RequestMapping("/subsystem")
public class SysSubSystemController extends JeecgController<SysSubSystem, ISysSubSystemService> {
   @Autowired
   private ISysSubSystemService sysSubSystemService;

   /**
    * 分页列表查询
    *
    * @param subSystem
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "子系统信息表-分页列表查询")
   @ApiOperation(value="子系统信息表-分页列表查询", notes="子系统信息表-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(SysSubSystem subSystem,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<SysSubSystem> queryWrapper = QueryGenerator.initQueryWrapper(subSystem, req.getParameterMap());
       Page<SysSubSystem> page = new Page<SysSubSystem>(pageNo, pageSize);
       IPage<SysSubSystem> pageList = sysSubSystemService.page(page, queryWrapper);
       return Result.ok(pageList);
   }

   /**
    * 添加
    *
    * @param subSystem
    * @return
    */
   @AutoLog(value = "子系统信息表-添加")
   @ApiOperation(value="子系统信息表-添加", notes="子系统信息表-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody SysSubSystem subSystem) {
       sysSubSystemService.save(subSystem);
       return Result.ok("添加成功！");
   }

   /**
    * 编辑
    *
    * @param subSystem
    * @return
    */
   @AutoLog(value = "子系统信息表-编辑")
   @ApiOperation(value="子系统信息表-编辑", notes="子系统信息表-编辑")
   @PostMapping(value = "/edit")
   public Result<?> edit(@RequestBody SysSubSystem subSystem) {
       sysSubSystemService.updateById(subSystem);
       return Result.ok("编辑成功!");
   }

   /**
    * 通过id删除
    *
    * @param id
    * @return
    */
   @AutoLog(value = "子系统信息表-通过id删除")
   @ApiOperation(value="子系统信息表-通过id删除", notes="子系统信息表-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       sysSubSystemService.removeById(id);
       return Result.ok("删除成功!");
   }

   /**
    * 批量删除
    *
    * @param ids
    * @return
    */
   @AutoLog(value = "子系统信息表-批量删除")
   @ApiOperation(value="子系统信息表-批量删除", notes="子系统信息表-批量删除")
   @DeleteMapping(value = "/deleteBatch")
   public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       this.sysSubSystemService.removeByIds(Arrays.asList(ids.split(",")));
       return Result.ok("批量删除成功！");
   }

   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   @AutoLog(value = "子系统信息表-通过id查询")
   @ApiOperation(value="子系统信息表-通过id查询", notes="子系统信息表-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
       SysSubSystem subSystem = sysSubSystemService.getById(id);
       return Result.ok(subSystem);
   }

    /**
     * 查询所有记录
     *
     * @return
     */
    @AutoLog(value = "子系统信息表-查询所有记录")
    @ApiOperation(value="子系统信息表-查询所有记录", notes="子系统信息表-查询所有记录")
    @GetMapping(value = "/all")
    public Result<?> queryAll() {
        LambdaQueryWrapper<SysSubSystem> query = new LambdaQueryWrapper<SysSubSystem>();
        query.eq(SysSubSystem::getSfqy, "1");
        query.orderByAsc(SysSubSystem::getSx);
        List<SysSubSystem> subSystemList = sysSubSystemService.list(query);
        return Result.ok(subSystemList);
    }

 /**
  * 导出excel
  *
  * @param request
  * @param subSystem
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, SysSubSystem subSystem) {
     return super.exportXls(request, subSystem, SysSubSystem.class, "子系统信息表");
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
     return super.importExcel(request, response, SysSubSystem.class);
 }

}
