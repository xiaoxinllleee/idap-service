package org.cmms.modules.khxxgl.dfpkh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khxxgl.dfpkh.entity.DfpWgkh;
import org.cmms.modules.khxxgl.dfpkh.entity.Khwgfpls;
import org.cmms.modules.khxxgl.dfpkh.service.IDfpWgkhService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.dfpkh.service.IKhwgfplsService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
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
 * @Description: 待分配网格客户
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="待分配网格客户")
@RestController
@RequestMapping("/dfpkh/dfpWgkh")
public class DfpWgkhController extends JeecgController<DfpWgkh, IDfpWgkhService> {
	 @Autowired
	 private IDfpWgkhService dfpWgkhService;
	 @Autowired
	 private IKhjbzlService khjbzlService;

	 @Autowired
	 private IKhwgfplsService khwgfplsService;
	/**
	 * 分页列表查询
	 *
	 * @param dfpWgkh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "待分配网格客户-分页列表查询")
	@ApiOperation(value="待分配网格客户-分页列表查询", notes="待分配网格客户-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DfpWgkh dfpWgkh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DfpWgkh> queryWrapper = QueryGenerator.initQueryWrapper(dfpWgkh, req.getParameterMap());
		Page<DfpWgkh> page = new Page<DfpWgkh>(pageNo, pageSize);
		IPage<DfpWgkh> pageList = dfpWgkhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/extract")
	 public Result<?> extract() {
		 Result result = new Result<>();
		 try {
			 dfpWgkhService.extract();
			 result.setSuccess(true);
			 return result;
		 } catch (Exception e) {
			 System.out.println(e);
			 log.error("提取失败", e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
	/**
	 * 添加
	 *
	 * @param dfpWgkh
	 * @return
	 */
	@AutoLog(value = "待分配网格客户-添加")
	@ApiOperation(value="待分配网格客户-添加", notes="待分配网格客户-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DfpWgkh dfpWgkh) {
		dfpWgkhService.save(dfpWgkh);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param dfpWgkh
	 * @return
	 */
	@AutoLog(value = "待分配网格客户-编辑")
	@ApiOperation(value="待分配网格客户-编辑", notes="待分配网格客户-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DfpWgkh dfpWgkh) {
		dfpWgkhService.updateById(dfpWgkh);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "待分配网格客户-通过id删除")
	@ApiOperation(value="待分配网格客户-通过id删除", notes="待分配网格客户-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dfpWgkhService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "待分配网格客户-批量删除")
	@ApiOperation(value="待分配网格客户-批量删除", notes="待分配网格客户-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dfpWgkhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "待分配网格客户-通过id查询")
	@ApiOperation(value="待分配网格客户-通过id查询", notes="待分配网格客户-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DfpWgkh dfpWgkh = dfpWgkhService.getById(id);
		return Result.ok(dfpWgkh);
	}

	 @AutoLog(value = "客户网格分配")
	 @ApiOperation(value="客户网格分配", notes="客户网格分配")
	 @PutMapping(value = "/wgfpAction")
	 public Result<?> wgfpAction(@RequestBody JSONObject para) {
		 try {
			 List<Map<String,String>> rows = (List<Map<String,String>> )para.get("rows");
			 String gswgid =para.get("gswg").toString();
			 System.out.println("gswgid="+gswgid);
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 for (int i = 0; i < rows.size(); i++) {
				 System.out.println(rows.get(i).get("zjhm"));
				 QueryWrapper queryWrapper =new QueryWrapper<>();
				 queryWrapper.eq("zjhm",rows.get(i).get("zjhm"));
				 Khjbzl khjbzl =khjbzlService.getOne(queryWrapper);
				 if(khjbzl!=null){
					 khjbzl.setWgbh(gswgid);
					 boolean flag=khjbzlService.update(khjbzl,queryWrapper);
					 if(flag){
						 dfpWgkhService.remove(queryWrapper);
						 Khwgfpls khwgfpls= new Khwgfpls();
						 khwgfpls.setJgdm(khjbzl.getJgdm());
						 khwgfpls.setKhmc(khjbzl.getKhmc());
						 khwgfpls.setZjhm(khjbzl.getZjhm());
						 khwgfpls.setFpwg(gswgid);
						 khwgfpls.setCreateBy(loginUser.getUsername());
						 khwgfpls.setCreateTime(new Date());
						 khwgfplsService.save(khwgfpls);
					 }
				 }
			 }
			 //dfpWgkhService.extract();
			 return Result.ok("分配成功");
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 return Result.error("分配失败");
		 }
	 }
  /**
   * 导出excel
   *
   * @param request
   * @param dfpWgkh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DfpWgkh dfpWgkh) {
      return super.exportXls(request, dfpWgkh, DfpWgkh.class, "待分配网格客户");
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
      return super.importExcel(request, response, DfpWgkh.class);
  }

}
