package org.cmms.modules.khxxgl.khywxx.zhywxx.controller;

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
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglDksjmxZh;
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglDksjmxZhVO;
import org.cmms.modules.khxxgl.khywxx.zhywxx.service.IKhxxglDksjmxZhService;
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
 * @Description: 客户信息管理贷款数据明细支行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户信息管理贷款数据明细支行")
@RestController
@RequestMapping("/khywxx/khxxglDksjmxZh")
public class KhxxglDksjmxZhController extends JeecgController<KhxxglDksjmxZh, IKhxxglDksjmxZhService> {
	@Autowired
	private IKhxxglDksjmxZhService khxxglDksjmxZhService;
	
	/**
	 * 分页列表查询
	 *
	 * @param khxxglDksjmxZh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户信息管理贷款数据明细支行-分页列表查询")
	@ApiOperation(value="客户信息管理贷款数据明细支行-分页列表查询", notes="客户信息管理贷款数据明细支行-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhxxglDksjmxZh khxxglDksjmxZh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhxxglDksjmxZh> queryWrapper = QueryGenerator.initQueryWrapper(khxxglDksjmxZh, req.getParameterMap());
		Page<KhxxglDksjmxZh> page = new Page<KhxxglDksjmxZh>(pageNo, pageSize);
		IPage<KhxxglDksjmxZh> pageList = khxxglDksjmxZhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param khxxglDksjmxZh
	 * @return
	 */
	@AutoLog(value = "客户信息管理贷款数据明细支行-添加")
	@ApiOperation(value="客户信息管理贷款数据明细支行-添加", notes="客户信息管理贷款数据明细支行-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhxxglDksjmxZh khxxglDksjmxZh) {
		khxxglDksjmxZhService.save(khxxglDksjmxZh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khxxglDksjmxZh
	 * @return
	 */
	@AutoLog(value = "客户信息管理贷款数据明细支行-编辑")
	@ApiOperation(value="客户信息管理贷款数据明细支行-编辑", notes="客户信息管理贷款数据明细支行-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhxxglDksjmxZh khxxglDksjmxZh) {
		khxxglDksjmxZhService.updateById(khxxglDksjmxZh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息管理贷款数据明细支行-通过id删除")
	@ApiOperation(value="客户信息管理贷款数据明细支行-通过id删除", notes="客户信息管理贷款数据明细支行-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khxxglDksjmxZhService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户信息管理贷款数据明细支行-批量删除")
	@ApiOperation(value="客户信息管理贷款数据明细支行-批量删除", notes="客户信息管理贷款数据明细支行-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khxxglDksjmxZhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息管理贷款数据明细支行-通过id查询")
	@ApiOperation(value="客户信息管理贷款数据明细支行-通过id查询", notes="客户信息管理贷款数据明细支行-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhxxglDksjmxZh khxxglDksjmxZh = khxxglDksjmxZhService.getById(id);
		return Result.ok(khxxglDksjmxZh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khxxglDksjmxZh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhxxglDksjmxZh khxxglDksjmxZh) {
      return super.exportXls(request, khxxglDksjmxZh, KhxxglDksjmxZh.class, "客户信息管理贷款数据明细支行");
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
      return super.importExcel(request, response, KhxxglDksjmxZh.class);
  }

	 @RequestMapping("/getMaxDateByZjhm")
	 public Result<?> getMaxDateByZjhm(String zjhm,String jgdm){
		 if (StringUtils.isBlank(zjhm))
			 return Result.error("证件号码不能为空！");
		 LambdaQueryWrapper<KhxxglDksjmxZh> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(KhxxglDksjmxZh::getZjhm,zjhm);
		 lambdaQueryWrapper.eq(KhxxglDksjmxZh::getJgdm,jgdm);
;		 List<KhxxglDksjmxZh> list = service.list(lambdaQueryWrapper);
		 if (CollUtil.isNotEmpty(list)){
			 KhxxglDksjmxZh khxxglDksjmxZh = list.get(0);
			 KhxxglDksjmxZhVO result = new KhxxglDksjmxZhVO();
			 BeanUtils.copyProperties(khxxglDksjmxZh,result);

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
	 public Result<?> getDkqsByZjhm(String zjhm,String jgdm){
		 LambdaQueryWrapper<KhxxglDksjmxZh> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(KhxxglDksjmxZh::getZjhm,zjhm);
		 lambdaQueryWrapper.eq(KhxxglDksjmxZh::getJgdm,jgdm);
		 lambdaQueryWrapper.orderByDesc(KhxxglDksjmxZh::getTjyf);
		 List<KhxxglDksjmxZh> list = service.list(lambdaQueryWrapper);
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
