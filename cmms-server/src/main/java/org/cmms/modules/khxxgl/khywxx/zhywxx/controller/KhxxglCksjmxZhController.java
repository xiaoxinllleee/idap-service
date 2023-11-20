package org.cmms.modules.khxxgl.khywxx.zhywxx.controller;

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
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglCksjmxZh;
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglCksjmxZhVO;
import org.cmms.modules.khxxgl.khywxx.zhywxx.service.IKhxxglCksjmxZhService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

 /**
 * @Description: 客户信息管理存款数据明细支行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户信息管理存款数据明细支行")
@RestController
@RequestMapping("/khywxx/khxxglCksjmxZh")
public class KhxxglCksjmxZhController extends JeecgController<KhxxglCksjmxZh, IKhxxglCksjmxZhService> {
	@Autowired
	private IKhxxglCksjmxZhService khxxglCksjmxZhService;
	/**
	 * 分页列表查询
	 *
	 * @param khxxglCksjmxZh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户信息管理存款数据明细支行-分页列表查询")
	@ApiOperation(value="客户信息管理存款数据明细支行-分页列表查询", notes="客户信息管理存款数据明细支行-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhxxglCksjmxZh khxxglCksjmxZh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhxxglCksjmxZh> queryWrapper = QueryGenerator.initQueryWrapper(khxxglCksjmxZh, req.getParameterMap());
		Page<KhxxglCksjmxZh> page = new Page<KhxxglCksjmxZh>(pageNo, pageSize);
		IPage<KhxxglCksjmxZh> pageList = khxxglCksjmxZhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	/**
	 * 添加
	 *
	 * @param khxxglCksjmxZh
	 * @return
	 */
	@AutoLog(value = "客户信息管理存款数据明细支行-添加")
	@ApiOperation(value="客户信息管理存款数据明细支行-添加", notes="客户信息管理存款数据明细支行-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhxxglCksjmxZh khxxglCksjmxZh) {
		khxxglCksjmxZhService.save(khxxglCksjmxZh);
		return Result.ok("添加成功！");
	}
	/**
	 * 编辑
	 *
	 * @param khxxglCksjmxZh
	 * @return
	 */
	@AutoLog(value = "客户信息管理存款数据明细支行-编辑")
	@ApiOperation(value="客户信息管理存款数据明细支行-编辑", notes="客户信息管理存款数据明细支行-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhxxglCksjmxZh khxxglCksjmxZh) {
		khxxglCksjmxZhService.updateById(khxxglCksjmxZh);
		return Result.ok("编辑成功!");
	}
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息管理存款数据明细支行-通过id删除")
	@ApiOperation(value="客户信息管理存款数据明细支行-通过id删除", notes="客户信息管理存款数据明细支行-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khxxglCksjmxZhService.removeById(id);
		return Result.ok("删除成功!");
	}
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户信息管理存款数据明细支行-批量删除")
	@ApiOperation(value="客户信息管理存款数据明细支行-批量删除", notes="客户信息管理存款数据明细支行-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khxxglCksjmxZhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息管理存款数据明细支行-通过id查询")
	@ApiOperation(value="客户信息管理存款数据明细支行-通过id查询", notes="客户信息管理存款数据明细支行-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhxxglCksjmxZh khxxglCksjmxZh = khxxglCksjmxZhService.getById(id);
		return Result.ok(khxxglCksjmxZh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khxxglCksjmxZh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhxxglCksjmxZh khxxglCksjmxZh) {
      return super.exportXls(request, khxxglCksjmxZh, KhxxglCksjmxZh.class, "客户信息管理存款数据明细支行");
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
      return super.importExcel(request, response, KhxxglCksjmxZh.class);
  }


  //获取日期最大得一条数据
  @RequestMapping("/getMaxDateByZjhm")
  public Result<?> getMaxDateByZjhm(String zjhm,String jgdm){
	  if (StringUtils.isBlank(zjhm))
		  return Result.error("证件号码不能为空！");
	  LambdaQueryWrapper<KhxxglCksjmxZh> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	  lambdaQueryWrapper.eq(KhxxglCksjmxZh::getZjhm,zjhm);
	  lambdaQueryWrapper.eq(KhxxglCksjmxZh::getJgdm,jgdm);
	  lambdaQueryWrapper.orderByDesc(KhxxglCksjmxZh::getTjyf);
	  List<KhxxglCksjmxZh> list = service.list(lambdaQueryWrapper);
	  if (CollUtil.isNotEmpty(list)) {
		  KhxxglCksjmxZh khxxglCksjmxZh = list.get(0);
		  KhxxglCksjmxZhVO result = new KhxxglCksjmxZhVO();
		  BeanUtils.copyProperties(khxxglCksjmxZh,result);
		  //计算比较数据
		  //存款余额
		  if (result.getYe() == null)
			  result.setYe(new BigDecimal("0"));
		  if (result.getSyye() == null)
			  result.setSyye(new BigDecimal("0"));
		  if (result.getNcye() == null)
			  result.setNcye(new BigDecimal("0"));
		  result.setYejsy(result.getYe().subtract(result.getSyye()));
		  result.setYejsybz(result.getYe().compareTo(result.getSyye()));
		  result.setYejnc(result.getYe().subtract(result.getNcye()));
		  result.setYejncbz(result.getYe().compareTo(result.getNcye()));
		  //活期余额
		  if (result.getHqye() == null)
			  result.setHqye(new BigDecimal("0"));
		  if (result.getSyhqye() == null)
			  result.setSyhqye(new BigDecimal("0"));
		  if (result.getNchqye() == null)
			  result.setNchqye(new BigDecimal("0"));
		  result.setHqjsy(result.getHqye().subtract(result.getSyhqye()));
		  result.setHqjsybz(result.getHqye().compareTo(result.getSyhqye()));
		  result.setHqjnc(result.getHqye().subtract(result.getNchqye()));
		  result.setHqjncbz(result.getHqye().compareTo(result.getNchqye()));
		  //定期余额
		  if (result.getDqye() == null)
			  result.setDqye(new BigDecimal("0"));
		  if (result.getSydqye() == null)
			  result.setSydqye(new BigDecimal("0"));
		  if (result.getNcdqye() == null)
			  result.setNcdqye(new BigDecimal("0"));
		  result.setDqjsy(result.getDqye().subtract(result.getSydqye()));
		  result.setDqjsybz(result.getDqye().compareTo(result.getSydqye()));
		  result.setDqjnc(result.getDqye().subtract(result.getNcdqye()));
		  result.setDqjncbz(result.getDqye().compareTo(result.getNcdqye()));
		  return Result.ok(result);
	  }
	  return Result.ok();
  }

  /**
   * 根据证件号码获取近一年得存款数据 暂时是取当前时间处理
   *
   * */

  @RequestMapping("/getCkqsByZjhm")
  public Result<?> getCkqsByZjhm(String zjhm,String jgdm){
	  LambdaQueryWrapper<KhxxglCksjmxZh> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	  lambdaQueryWrapper.eq(KhxxglCksjmxZh::getZjhm,zjhm);
	  lambdaQueryWrapper.eq(KhxxglCksjmxZh::getJgdm,jgdm);
	  lambdaQueryWrapper.orderByDesc(KhxxglCksjmxZh::getTjyf);
	  List<KhxxglCksjmxZh> list = service.list(lambdaQueryWrapper);
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
