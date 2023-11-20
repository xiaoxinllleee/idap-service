package org.cmms.modules.khgl.jzyx.dklshcmmon.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khgl.jzyx.dklshcmmon.entity.Dklsh;
import org.cmms.modules.khgl.jzyx.dklshcmmon.service.IDklshService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款流失户
 * @Author: jeecg-boot
 * @Date:   2023-07-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款流失户")
@RestController
@RequestMapping("/dklshcmmon/dklsh")
public class DklshController extends JeecgController<Dklsh, IDklshService> {
	@Autowired
	private IDklshService dklshService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dklsh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款流失户-分页列表查询")
	@ApiOperation(value="贷款流失户-分页列表查询", notes="贷款流失户-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dklsh dklsh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dklsh> queryWrapper = QueryGenerator.initQueryWrapper(dklsh, req.getParameterMap());
		Page<Dklsh> page = new Page<Dklsh>(pageNo, pageSize);
		IPage<Dklsh> pageList = dklshService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param dklsh
	 * @return
	 */
	@AutoLog(value = "贷款流失户-添加")
	@ApiOperation(value="贷款流失户-添加", notes="贷款流失户-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dklsh dklsh) {
		dklshService.save(dklsh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dklsh
	 * @return
	 */
	@AutoLog(value = "贷款流失户-编辑")
	@ApiOperation(value="贷款流失户-编辑", notes="贷款流失户-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dklsh dklsh) {
		dklshService.updateById(dklsh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款流失户-通过id删除")
	@ApiOperation(value="贷款流失户-通过id删除", notes="贷款流失户-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dklshService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款流失户-批量删除")
	@ApiOperation(value="贷款流失户-批量删除", notes="贷款流失户-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dklshService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款流失户-通过id查询")
	@ApiOperation(value="贷款流失户-通过id查询", notes="贷款流失户-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dklsh dklsh = dklshService.getById(id);
		return Result.ok(dklsh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dklsh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dklsh dklsh) {
      return super.exportXls(request, dklsh, Dklsh.class, "贷款流失户");
  }
	 /**
	  * @return
	  */
	 @RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	 public Result<?> extract() {
		 try {
			 dklshService.extract();
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");
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
      return super.importExcel(request, response, Dklsh.class);
  }

}
