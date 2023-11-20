package org.cmms.modules.khxxgl.khywxx.qhywxx.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.api.vo.ResultConstant;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.modules.khxxgl.khywxx.qhywxx.entity.KhxxglCksjmxQh;
import org.cmms.modules.khxxgl.khywxx.qhywxx.entity.KhxxglDksjmxQh;
import org.cmms.modules.khxxgl.khywxx.qhywxx.entity.KhxxglDksjmxQhVO;
import org.cmms.modules.khxxgl.khywxx.qhywxx.service.IKhxxglDksjmxQhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
* @Description: 客户信息管理贷款数据明细全行
* @Author: jeecg-boot
* @Date:   2022-01-05
* @Version: V1.0
*/
@Slf4j
@Api(tags="客户信息管理贷款数据明细全行")
@RestController
@RequestMapping("/khywxx/khxxglDksjmxQh")
public class KhxxglDksjmxQhController extends JeecgController<KhxxglDksjmxQh, IKhxxglDksjmxQhService> {
   @Autowired
   private IKhxxglDksjmxQhService khxxglDksjmxQhService;

   /**
    * 分页列表查询
    *
    * @param khxxglDksjmxQh
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "客户信息管理贷款数据明细全行-分页列表查询")
   @ApiOperation(value="客户信息管理贷款数据明细全行-分页列表查询", notes="客户信息管理贷款数据明细全行-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(KhxxglDksjmxQh khxxglDksjmxQh,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<KhxxglDksjmxQh> queryWrapper = QueryGenerator.initQueryWrapper(khxxglDksjmxQh, req.getParameterMap());
       Page<KhxxglDksjmxQh> page = new Page<KhxxglDksjmxQh>(pageNo, pageSize);
       IPage<KhxxglDksjmxQh> pageList = khxxglDksjmxQhService.page(page, queryWrapper);
       return Result.ok(pageList);
   }

   /**
    * 添加
    *
    * @param khxxglDksjmxQh
    * @return
    */
   @AutoLog(value = "客户信息管理贷款数据明细全行-添加")
   @ApiOperation(value="客户信息管理贷款数据明细全行-添加", notes="客户信息管理贷款数据明细全行-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody KhxxglDksjmxQh khxxglDksjmxQh) {
       khxxglDksjmxQhService.save(khxxglDksjmxQh);
       return Result.ok("添加成功！");
   }

   /**
    * 编辑
    *
    * @param khxxglDksjmxQh
    * @return
    */
   @AutoLog(value = "客户信息管理贷款数据明细全行-编辑")
   @ApiOperation(value="客户信息管理贷款数据明细全行-编辑", notes="客户信息管理贷款数据明细全行-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody KhxxglDksjmxQh khxxglDksjmxQh) {
       khxxglDksjmxQhService.updateById(khxxglDksjmxQh);
       return Result.ok("编辑成功!");
   }

   /**
    * 通过id删除
    *
    * @param id
    * @return
    */
   @AutoLog(value = "客户信息管理贷款数据明细全行-通过id删除")
   @ApiOperation(value="客户信息管理贷款数据明细全行-通过id删除", notes="客户信息管理贷款数据明细全行-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       khxxglDksjmxQhService.removeById(id);
       return Result.ok("删除成功!");
   }

   /**
    * 批量删除
    *
    * @param ids
    * @return
    */
   @AutoLog(value = "客户信息管理贷款数据明细全行-批量删除")
   @ApiOperation(value="客户信息管理贷款数据明细全行-批量删除", notes="客户信息管理贷款数据明细全行-批量删除")
   @DeleteMapping(value = "/deleteBatch")
   public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       this.khxxglDksjmxQhService.removeByIds(Arrays.asList(ids.split(",")));
       return Result.ok("批量删除成功！");
   }

   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   @AutoLog(value = "客户信息管理贷款数据明细全行-通过id查询")
   @ApiOperation(value="客户信息管理贷款数据明细全行-通过id查询", notes="客户信息管理贷款数据明细全行-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
       KhxxglDksjmxQh khxxglDksjmxQh = khxxglDksjmxQhService.getById(id);
       return Result.ok(khxxglDksjmxQh);
   }

 /**
  * 导出excel
  *
  * @param request
  * @param khxxglDksjmxQh
  */
 @RequestMapping(value = "/exportXls")
 public ModelAndView exportXls(HttpServletRequest request, KhxxglDksjmxQh khxxglDksjmxQh) {
     return super.exportXls(request, khxxglDksjmxQh, KhxxglDksjmxQh.class, "客户信息管理贷款数据明细全行");
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
     return super.importExcel(request, response, KhxxglDksjmxQh.class);
 }

    @RequestMapping("/getMaxDateByZjhm")
    public Result<?> getMaxDateByZjhm(String zjhm){
        if (StringUtils.isBlank(zjhm))
            return Result.error("证件号码不能为空！");
        LambdaQueryWrapper<KhxxglDksjmxQh> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(KhxxglDksjmxQh::getZjhm,zjhm);
        lambdaQueryWrapper.orderByDesc(KhxxglDksjmxQh::getTjyf);
;		 List<KhxxglDksjmxQh> list = service.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)){
            KhxxglDksjmxQh khxxglDksjmxQh = list.get(0);
            KhxxglDksjmxQhVO result = new KhxxglDksjmxQhVO();
            BeanUtils.copyProperties(khxxglDksjmxQh,result);

            if (result.getDkye() == null)
                result.setDkye(new BigDecimal("0"));
            if (result.getSydkye() == null)
                result.setSydkye(new BigDecimal("0"));
            if (result.getNcdkye() == null)
                result.setNcdkye(new BigDecimal("0"));
            result.setYejsy(result.getDkye().subtract(result.getSydkye()));
            result.setYejsybz(result.getDkye().compareTo(result.getSydkye()));
            result.setYejnc(result.getDkye().subtract(result.getNcdkye()));
            result.setYejncbz(result.getDkye().compareTo(result.getNcdkye()));

            return Result.ok(result);
        }
        return Result.ok();
 }

    /**
     * 根据证件号码获取近一年得存款数据 暂时是取当前时间处理
     *
     * */

    @RequestMapping("/getDkqsByZjhm")
    public Result<?> getDkqsByZjhm(String zjhm){
        LambdaQueryWrapper<KhxxglDksjmxQh> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(KhxxglDksjmxQh::getZjhm,zjhm);
        lambdaQueryWrapper.orderByAsc(KhxxglDksjmxQh::getTjyf);
        List<KhxxglDksjmxQh> list = service.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)){
            //只取1年
            if (list.size() > 12){
                return Result.ok(list.subList(0,12));
            }else {
                return Result.ok(list);
            }
        }
        return Result.ok();
    }
}
