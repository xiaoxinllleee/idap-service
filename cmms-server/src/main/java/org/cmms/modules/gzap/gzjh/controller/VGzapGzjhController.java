package org.cmms.modules.gzap.gzjh.controller;

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
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.gzap.gzjh.entity.GzapJhxfKhjl;
import org.cmms.modules.gzap.gzjh.entity.VGzapGzjh;
import org.cmms.modules.gzap.gzjh.service.IGzapJhxfKhjlService;
import org.cmms.modules.gzap.gzjh.service.IVGzapGzjhService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
 * @Description: 工作安排/工作计划
 * @Author: cmms
 * @Date:   2019-09-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="工作安排/工作计划")
@RestController
@RequestMapping("/gzjh/vGzapGzjh")
public class VGzapGzjhController {
	@Autowired
	private IVGzapGzjhService vGzapGzjhService;
	@Autowired
    private IGzapJhxfKhjlService gzapJhxfKhjlService;
	
	/**
     * 分页列表查询
	 * @param vGzapGzjh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 */
	@AutoLog(value = "工作安排/工作计划-分页列表查询")
	@ApiOperation(value="工作安排/工作计划-分页列表查询", notes="工作安排/工作计划-分页列表查询")
	@GetMapping(value = "/list")
    @PermissionData(pageComponent="gzap/gzjh/Gzap_gzjhList")
	public Result<IPage<VGzapGzjh>> queryPageList(VGzapGzjh vGzapGzjh,
                                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                  HttpServletRequest req) {
		Result<IPage<VGzapGzjh>> result = new Result<IPage<VGzapGzjh>>();
		QueryWrapper<VGzapGzjh> queryWrapper = QueryGenerator.initQueryWrapper(vGzapGzjh, req.getParameterMap());
		Page<VGzapGzjh> page = new Page<VGzapGzjh>(pageNo, pageSize);
		IPage<VGzapGzjh> pageList = vGzapGzjhService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
     * 编辑
	 * @param vGzapGzjh
	 */
	@AutoLog(value = "工作安排/工作计划-编辑")
	@ApiOperation(value="工作安排/工作计划-编辑", notes="工作安排/工作计划-编辑")
	@PutMapping(value = "/edit")
	public Result<VGzapGzjh> edit(@RequestBody VGzapGzjh vGzapGzjh) {
		Result<VGzapGzjh> result = new Result<VGzapGzjh>();
		VGzapGzjh vGzapGzjhEntity = vGzapGzjhService.getById(vGzapGzjh.getId());
		if(vGzapGzjhEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = vGzapGzjhService.updateById(vGzapGzjh);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		return result;
	}
	
	/**
     * 通过id查询
	 * @param id
	 */
	@AutoLog(value = "工作安排/工作计划-通过id查询")
	@ApiOperation(value="工作安排/工作计划-通过id查询", notes="工作安排/工作计划-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<VGzapGzjh> queryById(@RequestParam(name="id",required=true) String id) {
		Result<VGzapGzjh> result = new Result<VGzapGzjh>();
		VGzapGzjh vGzapGzjh = vGzapGzjhService.getById(id);
		if(vGzapGzjh==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(vGzapGzjh);
			result.setSuccess(true);
		}
		return result;
	}

    /**
     * 导出excel
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<VGzapGzjh> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              VGzapGzjh vGzapGzjh = JSON.parseObject(deString, VGzapGzjh.class);
              queryWrapper = QueryGenerator.initQueryWrapper(vGzapGzjh, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<VGzapGzjh> pageList = vGzapGzjhService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "工作安排/工作计划列表");
      mv.addObject(NormalExcelConstants.CLASS, VGzapGzjh.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("工作安排/工作计划列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

    /**
     * 通过excel导入数据
     * @param request
     * @param response
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<VGzapGzjh> listVGzapGzjhs = ExcelImportUtil.importExcel(file.getInputStream(), VGzapGzjh.class, params);
              vGzapGzjhService.saveBatch(listVGzapGzjhs);
              return Result.ok("文件导入成功！数据行数:" + listVGzapGzjhs.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

     /**
      * 填写计划完成情况
      * @param jsonObject
      */
    @PutMapping(value = "/editMyPlan")
    public Result<GzapJhxfKhjl> editMyPlan(@RequestBody JSONObject jsonObject) {
        Result<GzapJhxfKhjl> result = new Result<GzapJhxfKhjl>();
        String id = jsonObject.getString("id");
        GzapJhxfKhjl jhxfKhjlEntity = gzapJhxfKhjlService.getById(id);
        try {
            GzapJhxfKhjl jhxfKhjl = new GzapJhxfKhjl();
            jhxfKhjl.setId(id);
            jhxfKhjl.setWczt("1");          // 完成状态：已完成
            jhxfKhjl.setSjwcrq(new Date()); // 实际完成日期：当前日期
            jhxfKhjl.setSjwcqk(jsonObject.getString("sjwcqk"));
            gzapJhxfKhjlService.updateById(jhxfKhjl);
            result.success("填写成功！");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("填写失败");
        }
        return result;
    }
}
