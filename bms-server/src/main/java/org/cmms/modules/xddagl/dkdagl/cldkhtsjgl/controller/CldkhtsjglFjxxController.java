package org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.Cldkhtsjgl;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.CldkhtsjglFjxx;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglFjxxService;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 存量贷款合同数据管理附件信息
 * @Author: jeecg-boot
 * @Date:   2022-01-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存量贷款合同数据管理附件信息")
@RestController
@RequestMapping("/cldkhtsjglfjxx/cldkhtsjglFjxx")
public class CldkhtsjglFjxxController extends JeecgController<CldkhtsjglFjxx, ICldkhtsjglFjxxService> {
	@Autowired
	private ICldkhtsjglFjxxService cldkhtsjglFjxxService;
	@Autowired
	private ICldkhtsjglService cldkhtsjglService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cldkhtsjglFjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存量贷款合同数据管理附件信息-分页列表查询")
	@ApiOperation(value="存量贷款合同数据管理附件信息-分页列表查询", notes="存量贷款合同数据管理附件信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CldkhtsjglFjxx cldkhtsjglFjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CldkhtsjglFjxx> queryWrapper = QueryGenerator.initQueryWrapper(cldkhtsjglFjxx, req.getParameterMap());
		Page<CldkhtsjglFjxx> page = new Page<CldkhtsjglFjxx>(pageNo, pageSize);
		IPage<CldkhtsjglFjxx> pageList = cldkhtsjglFjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param cldkhtsjglFjxx
	 * @return
	 */
	@AutoLog(value = "存量贷款合同数据管理附件信息-添加")
	@ApiOperation(value="存量贷款合同数据管理附件信息-添加", notes="存量贷款合同数据管理附件信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CldkhtsjglFjxx cldkhtsjglFjxx) {
		cldkhtsjglFjxxService.save(cldkhtsjglFjxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param
	 * @return
	 */
//	@AutoLog(value = "存量贷款合同数据管理附件信息-编辑")
//	@ApiOperation(value="存量贷款合同数据管理附件信息-编辑", notes="存量贷款合同数据管理附件信息-编辑")
//	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
//	public Result<?> edit(@RequestBody CldkhtsjglFjxxVO cldkhtsjglFjxx) {
//
//		cldkhtsjglFjxxService.updateById();
//		return Result.ok("编辑成功!");
//	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "存量贷款合同数据管理附件信息-通过id删除")
	@ApiOperation(value="存量贷款合同数据管理附件信息-通过id删除", notes="存量贷款合同数据管理附件信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="wjid",required=true) String wjid) {
		QueryWrapper<CldkhtsjglFjxx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("wjid",wjid);
		cldkhtsjglFjxxService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	 /**
	  * 删除附件
	  */
	 @DeleteMapping(value = "/deletefjxx")
	 public Result<?> deleteFj(@RequestBody Cldkhtsjgl cldkhtsjgl){
		 CldkhtsjglFjxx fjxx = new CldkhtsjglFjxx();
		 BeanUtils.copyProperties(cldkhtsjgl,fjxx);
		 QueryWrapper<CldkhtsjglFjxx> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("hth",cldkhtsjgl.getHth());
		 queryWrapper.eq("wjid",fjxx.getWjid());
		 cldkhtsjglFjxxService.remove(queryWrapper);
		 return Result.ok("删除成功!");
	 }
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存量贷款合同数据管理附件信息-批量删除")
	@ApiOperation(value="存量贷款合同数据管理附件信息-批量删除", notes="存量贷款合同数据管理附件信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cldkhtsjglFjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存量贷款合同数据管理附件信息-通过id查询")
	@ApiOperation(value="存量贷款合同数据管理附件信息-通过id查询", notes="存量贷款合同数据管理附件信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CldkhtsjglFjxx cldkhtsjglFjxx = cldkhtsjglFjxxService.getById(id);
		return Result.ok(cldkhtsjglFjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cldkhtsjglFjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CldkhtsjglFjxx cldkhtsjglFjxx) {
      return super.exportXls(request, cldkhtsjglFjxx, CldkhtsjglFjxx.class, "存量贷款合同数据管理附件信息");
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
      return super.importExcel(request, response, CldkhtsjglFjxx.class);
  }

}
