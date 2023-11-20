package org.cmms.modules.xdgl.dkzlgl.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.xdgl.dkzlgl.entity.XdglDkzl;
import org.cmms.modules.xdgl.dkzlgl.service.IXdglDkzlService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 信贷贷款资料管理
 * @Author: jeecg-boot
 * @Date:   2020-07-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="信贷贷款资料管理")
@RestController
@RequestMapping("/xdgl/dkzlgl/xdglDkzl")
public class XdglDkzlController extends JeecgController<XdglDkzl, IXdglDkzlService> {
	@Autowired
	private IXdglDkzlService xdglDkzlService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param xdglDkzl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "信贷贷款资料管理-分页列表查询")
	@ApiOperation(value="信贷贷款资料管理-分页列表查询", notes="信贷贷款资料管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(XdglDkzl xdglDkzl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<XdglDkzl> queryWrapper = QueryGenerator.initQueryWrapper(xdglDkzl, req.getParameterMap());
		Page<XdglDkzl> page = new Page<XdglDkzl>(pageNo, pageSize);
		IPage<XdglDkzl> pageList = xdglDkzlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param xdglDkzl
	 * @return
	 */
	@AutoLog(value = "信贷贷款资料管理-添加")
	@ApiOperation(value="信贷贷款资料管理-添加", notes="信贷贷款资料管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody List<XdglDkzl> xdglDkzl) {
	//	xdglDkzlService.save(xdglDkzl);

		for(XdglDkzl entity:xdglDkzl) {
			//外键设置
			if(!entity.getFwlj().equals("")&&entity.getFwlj()!=null){
				String fwlj = entity.getFwlj();
				String fjlj = uploadpath + File.separator+fwlj;
				String wjmc=fwlj.split("_")[0];
				File file = new File(fjlj);
				entity.setZlmc(wjmc);
				entity.setZllj(fjlj);
				entity.setZldx(new BigDecimal(file.length()).divide(new BigDecimal(1000)));
			}
			xdglDkzlService.save(entity);
		}

		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param xdglDkzl
	 * @return
	 */
	@AutoLog(value = "信贷贷款资料管理-编辑")
	@ApiOperation(value="信贷贷款资料管理-编辑", notes="信贷贷款资料管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody XdglDkzl xdglDkzl) {
		xdglDkzlService.updateById(xdglDkzl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷贷款资料管理-通过id删除")
	@ApiOperation(value="信贷贷款资料管理-通过id删除", notes="信贷贷款资料管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xdglDkzlService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "信贷贷款资料管理-批量删除")
	@ApiOperation(value="信贷贷款资料管理-批量删除", notes="信贷贷款资料管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xdglDkzlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "信贷贷款资料管理-通过id查询")
	@ApiOperation(value="信贷贷款资料管理-通过id查询", notes="信贷贷款资料管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		XdglDkzl xdglDkzl = xdglDkzlService.getById(id);
		return Result.ok(xdglDkzl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xdglDkzl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, XdglDkzl xdglDkzl) {
      return super.exportXls(request, xdglDkzl, XdglDkzl.class, "信贷贷款资料管理");
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
      return super.importExcel(request, response, XdglDkzl.class);
  }

}
