package org.cmms.modules.system.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.entity.DpJdgl;
import org.cmms.modules.system.entity.DpJdrwgl;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.IDpJdrwglService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 节点任务管理
 * @Author: jeecg-boot
 * @Date:   2021-01-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="节点任务管理")
@RestController
@RequestMapping("/system/dpJdrwgl")
public class DpJdrwglController extends JeecgController<DpJdrwgl, IDpJdrwglService> {
	@Autowired
	private IDpJdrwglService dpJdrwglService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dpJdrwgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "节点任务管理-分页列表查询")
	@ApiOperation(value="节点任务管理-分页列表查询", notes="节点任务管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DpJdrwgl dpJdrwgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<IPage<DpJdrwgl>> result = new Result<IPage<DpJdrwgl>>();
		Page<DpJdrwgl> page = new Page<DpJdrwgl>(pageNo, pageSize);
		String jdid = req.getParameter("jdid");
		String rwmc = req.getParameter("rwmc");
		IPage<DpJdrwgl> pageList = dpJdrwglService.getJdrwByJdId(page,jdid,rwmc);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;



	}


	 /**
	  * 分页列表查询
	  *
	  * @param dpJdrwgl
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "节点任务管理-分页列表查询")
	 @ApiOperation(value="节点任务管理-分页列表查询", notes="节点任务管理-分页列表查询")
	 @GetMapping(value = "/listAll")
	 public Result<?> queryPageListAll(DpJdrwgl dpJdrwgl,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 Result<IPage<DpJdrwgl>> result = new Result<IPage<DpJdrwgl>>();
		 Page<DpJdrwgl> page = new Page<DpJdrwgl>(pageNo, pageSize);
		 QueryWrapper<DpJdrwgl> queryWrapper =new QueryWrapper<>();
		 queryWrapper.eq("sfqy",1);
		 queryWrapper.orderByAsc("to_number(jdid)");
		 IPage<DpJdrwgl> pageList = dpJdrwglService.page(page,queryWrapper);
		 result.setSuccess(true);
		 result.setResult(pageList);
		 return result;



	 }

	/**
	 * 添加
	 *
	 * @param dpJdrwgl
	 * @return
	 */
	@AutoLog(value = "节点任务管理-添加")
	@ApiOperation(value="节点任务管理-添加", notes="节点任务管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DpJdrwgl dpJdrwgl) {
		dpJdrwglService.save(dpJdrwgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dpJdrwgl
	 * @return
	 */
	@AutoLog(value = "节点任务管理-编辑")
	@ApiOperation(value="节点任务管理-编辑", notes="节点任务管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DpJdrwgl dpJdrwgl) {
		dpJdrwglService.updateById(dpJdrwgl);
		return Result.ok("编辑成功!");
	}




	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "节点任务管理-通过id删除")
	@ApiOperation(value="节点任务管理-通过id删除", notes="节点任务管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dpJdrwglService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "节点任务管理-批量删除")
	@ApiOperation(value="节点任务管理-批量删除", notes="节点任务管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dpJdrwglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "节点任务管理-通过id查询")
	@ApiOperation(value="节点任务管理-通过id查询", notes="节点任务管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DpJdrwgl dpJdrwgl = dpJdrwglService.getById(id);
		return Result.ok(dpJdrwgl);
	}



	 /**
	  * 冻结&解冻用户
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/sfqyBatch", method = RequestMethod.PUT)
	 public Result<DpJdrwgl> sfqyBatch(@RequestBody JSONObject jsonObject) {
		 Result<DpJdrwgl> result = new Result<DpJdrwgl>();
		 try {
			 String id = jsonObject.getString("id");
			 String sfqy = jsonObject.getString("sfqy");
			 if(oConvertUtils.isNotEmpty(id)) {
				 dpJdrwglService.update(new DpJdrwgl().setSfqy(sfqy),
						 new UpdateWrapper<DpJdrwgl>().lambda().eq(DpJdrwgl::getId,id));
			 }
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败"+e.getMessage());
		 }
		 result.success("操作成功!");
		 return result;
	 }



	 /**
	  * 提取绩效数据
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/extract", method = RequestMethod.PUT)
	 public Result<DpJdrwgl> extract(@RequestBody JSONObject jsonObject) {
		 Result<DpJdrwgl> result = new Result<DpJdrwgl>();
		 String tjrq = jsonObject.getString("tjrq").replace("-","");
		 String rwid = jsonObject.getString("rwid");
		 String spname = jsonObject.getString("spname");
		 try {
			 dpJdrwglService.extract(spname,tjrq,tjrq,rwid);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败"+e.getMessage());
		 }
		 result.success("操作成功!");
		 return result;
	 }

	 /**
	  * 提取绩效数据
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/extractBatch", method = RequestMethod.PUT)
	 public Result<DpJdrwgl> extractBatch(@RequestBody JSONObject jsonObject) {
		 Result<DpJdrwgl> result = new Result<DpJdrwgl>();
		 String ksrq = jsonObject.getString("ksrq");
		 String jsrq = jsonObject.getString("jsrq");
		 String rwid = jsonObject.getString("rwid");
		 String spname = jsonObject.getString("spname");
		 try {
			 dpJdrwglService.extract(spname,ksrq,jsrq,rwid);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败"+e.getMessage());
		 }
		 result.success("操作成功!");
		 return result;
	 }



	 @RequestMapping(value = "/updateBatchzt", method = RequestMethod.PUT)
	 public Result<DpJdrwgl> updateBatchzt(@RequestBody JSONObject jsonObject) {
		 Result<DpJdrwgl> result = new Result<DpJdrwgl>();
		 String ksrq = jsonObject.getString("ksrq");
		 String jsrq = jsonObject.getString("jsrq");
		 String rwid = jsonObject.getString("rwid");
		 try {
			 dpJdrwglService.updateBatchzt(ksrq,jsrq,rwid);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败"+e.getMessage());
		 }
		 result.success("操作成功!");
		 return result;
	 }


	 /**
	  * 提取绩效数据
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/updatezt", method = RequestMethod.PUT)
	 public Result<DpJdrwgl> updatezt(@RequestBody JSONObject jsonObject) {
		 Result<DpJdrwgl> result = new Result<DpJdrwgl>();
		 String tjrq = jsonObject.getString("tjrq").replace("-","");
		 String rwid = jsonObject.getString("rwid");
		 try {
			 dpJdrwglService.updatezt(tjrq,rwid);
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败"+e.getMessage());
		 }
		 result.success("操作成功!");
		 return result;
	 }



	 /**
   * 导出excel
   *
   * @param request
   * @param dpJdrwgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DpJdrwgl dpJdrwgl) {
      return super.exportXls(request, dpJdrwgl, DpJdrwgl.class, "节点任务管理");
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
      return super.importExcel(request, response, DpJdrwgl.class);
  }

}
