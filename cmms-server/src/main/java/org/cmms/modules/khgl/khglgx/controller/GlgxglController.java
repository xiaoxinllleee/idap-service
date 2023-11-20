package org.cmms.modules.khgl.khglgx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.khglgx.entity.CamsZcsxNhcjxx;
import org.cmms.modules.khgl.khglgx.entity.Glgxgl;
import org.cmms.modules.khgl.khglgx.entity.Khgl_khglgx;
import org.cmms.modules.khgl.khglgx.entity.Khzyrzgl;
import org.cmms.modules.khgl.khglgx.service.ICamsZcsxNhcjxxService1;
import org.cmms.modules.khgl.khglgx.service.IGlgxglService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.khglgx.service.IVglgxmxService;
import org.cmms.modules.yxdygl.pqzrrgl.service.IYxdyglPqzrrglService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 关联关系管理
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="关联关系管理")
@RestController
@RequestMapping("/khgl/khglgx/glgxgl")
public class GlgxglController extends JeecgController<Glgxgl, IGlgxglService> {
	@Autowired
	private IGlgxglService glgxglService;

	@Autowired
	private IYxdyglPqzrrglService yxdyglPqzrrglService;

	@Autowired
	private IVglgxmxService iVglgxmxService;

	 @Autowired
	 private ICamsZcsxNhcjxxService1 iCamsZcsxNhcjxxService1;

	/**
	 * 分页列表查询
	 *
	 * @param glgxgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "关联关系管理-分页列表查询")
	@ApiOperation(value="关联关系管理-分页列表查询", notes="关联关系管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Glgxgl glgxgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Glgxgl> queryWrapper = QueryGenerator.initQueryWrapper(glgxgl, req.getParameterMap());
		queryWrapper.orderByDesc("CZSJ");
		Page<Glgxgl> page = new Page<Glgxgl>(pageNo, pageSize);
		IPage<Glgxgl> pageList = glgxglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param glgxgl
	 * @return
	 */
	@AutoLog(value = "关联关系管理-添加")
	@ApiOperation(value="关联关系管理-添加", notes="关联关系管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Glgxgl glgxgl) {
		glgxglService.save(glgxgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param glgxgl
	 * @return
	 */
	@AutoLog(value = "关联关系管理-编辑")
	@ApiOperation(value="关联关系管理-编辑", notes="关联关系管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Glgxgl glgxgl) {
		glgxglService.updateById(glgxgl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "关联关系管理-通过id删除")
	@ApiOperation(value="关联关系管理-通过id删除", notes="关联关系管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		glgxglService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "关联关系管理-批量删除")
	@ApiOperation(value="关联关系管理-批量删除", notes="关联关系管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.glgxglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "关联关系管理-通过id查询")
	@ApiOperation(value="关联关系管理-通过id查询", notes="关联关系管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Glgxgl glgxgl = glgxglService.getById(id);
		return Result.ok(glgxgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param glgxgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Glgxgl glgxgl) {
      return super.exportXls(request, glgxgl, Glgxgl.class, "关联关系管理");
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
      return super.importExcel(request, response, Glgxgl.class);
  }



	 @AutoLog(value = "关联关系管理-编辑")
	 @ApiOperation(value="关联关系管理-编辑", notes="关联关系管理-编辑")
	 @PutMapping(value = "/queryKhjlPqKh")
	 public Result<?> queryKhjlPqKh(@RequestBody Glgxgl glgxgl) {
		 Result<JSONObject> result = new Result<JSONObject>();
		 try {
			JSONObject obj = new JSONObject();
			int pqs=yxdyglPqzrrglService.queryCountBykhjl(glgxgl.getYkhjl());
			int khs=iVglgxmxService.queryCountBykhjl(glgxgl.getYkhjl());
			obj.put("pqs",pqs);
			obj.put("khs",khs);
			result.setResult(obj);
			result.success("查询成功");
		}catch (Exception e){
			 result.error500("查询失败");
		}
		 return result;
	 }



	 @AutoLog(value = "关联关系管理-一键转移")
	 @ApiOperation(value="关联关系管理-一键转移", notes="关联关系管理-一键转移")
	 @PutMapping(value = "/yjzyAction")
	 public Result<?> yjzyAction(@RequestBody Glgxgl glgxgl) {
		 try {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

			 glgxglService.updateGlgx(glgxgl.getYkhjl()  ,glgxgl.getZyhkhjl(),loginUser.getRealname());
			 return Result.ok("转移成功");
		 }catch (Exception e){
			 return Result.error("转移失败");
		 }
	 }

	 @AutoLog(value = "关联关系管理-转移客户")
	 @ApiOperation(value="关联关系管理-转移客户", notes="关联关系管理-转移客户")
	 @PutMapping(value = "/zykhAction")
	 public Result<?> zykhAction(@RequestBody JSONObject para) {
		 try {
			 List<Map<String,String>> rows = (List<Map<String,String>> )para.get("rows");
			 for (int i = 0; i < rows.size(); i++) {
				 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				 glgxglService.updateKhGlgx(rows.get(i).get("zjhm") ,para.get("transferTo").toString(),loginUser.getRealname(),String.valueOf(rows.get(i).get("khxz")));
			 }
			 return Result.ok("转移成功");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 return Result.error("转移失败");
		 }
	 }
 }
