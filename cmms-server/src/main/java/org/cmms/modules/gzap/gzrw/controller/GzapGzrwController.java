package org.cmms.modules.gzap.gzrw.controller;

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
import org.cmms.modules.gzap.gzrw.entity.GzapGzrw;
import org.cmms.modules.gzap.gzrw.entity.RwxfRwgl;
import org.cmms.modules.gzap.gzrw.service.IGzapGzrwService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.gzap.gzrw.service.IRwxfRwglService;
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
 * @Description: 工作安排/工作任务
 * @Author: cmms
 * @Date:   2019-09-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="工作安排/工作任务")
@RestController
@RequestMapping("/gzrw/gzapGzrw")
public class GzapGzrwController {
	@Autowired
	private IGzapGzrwService gzapGzrwService;
	@Autowired
    private IRwxfRwglService rwxfRwglService;
	
	/**
     * 分页列表查询
	 * @param gzapGzrw
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "工作安排/工作任务-分页列表查询")
	@ApiOperation(value="工作安排/工作任务-分页列表查询", notes="工作安排/工作任务-分页列表查询")
	@GetMapping(value = "/list")
    @PermissionData(pageComponent="gzap/gzrw/GzapGzrwList")
    public Result<IPage<GzapGzrw>> queryPageList(GzapGzrw gzapGzrw,
                                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                 HttpServletRequest req) {
		Result<IPage<GzapGzrw>> result = new Result<IPage<GzapGzrw>>();
		QueryWrapper<GzapGzrw> queryWrapper = QueryGenerator.initQueryWrapper(gzapGzrw, req.getParameterMap());
		Page<GzapGzrw> page = new Page<GzapGzrw>(pageNo, pageSize);
		IPage<GzapGzrw> pageList = gzapGzrwService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
     * 编辑
	 * @param gzapGzrw
	 * @return
	 */
	@AutoLog(value = "工作安排/工作任务-编辑")
	@ApiOperation(value="工作安排/工作任务-编辑", notes="工作安排/工作任务-编辑")
	@PutMapping(value = "/edit")
	public Result<GzapGzrw> edit(@RequestBody GzapGzrw gzapGzrw) {
		Result<GzapGzrw> result = new Result<GzapGzrw>();
		GzapGzrw gzapGzrwEntity = gzapGzrwService.getById(gzapGzrw.getId());
		if(gzapGzrwEntity==null) {
			result.error500("未找到对应实体");
		} else {
			boolean ok = gzapGzrwService.updateById(gzapGzrw);
			if(ok) {
				result.success("修改成功!");
			}
		}
		return result;
	}
	
	/**
     * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工作安排/工作任务-通过id查询")
	@ApiOperation(value="工作安排/工作任务-通过id查询", notes="工作安排/工作任务-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<GzapGzrw> queryById(@RequestParam(name="id",required=true) String id) {
		Result<GzapGzrw> result = new Result<GzapGzrw>();
		GzapGzrw gzapGzrw = gzapGzrwService.getById(id);
		if(gzapGzrw==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(gzapGzrw);
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
      QueryWrapper<GzapGzrw> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              GzapGzrw gzapGzrw = JSON.parseObject(deString, GzapGzrw.class);
              queryWrapper = QueryGenerator.initQueryWrapper(gzapGzrw, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<GzapGzrw> pageList = gzapGzrwService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "工作安排/工作任务列表");
      mv.addObject(NormalExcelConstants.CLASS, GzapGzrw.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("工作安排/工作任务列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

    /**
     * 通过excel导入数据
     * @param request
     * @param response
     * @return
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
              List<GzapGzrw> listGzapGzrws = ExcelImportUtil.importExcel(file.getInputStream(), GzapGzrw.class, params);
              gzapGzrwService.saveBatch(listGzapGzrws);
              return Result.ok("文件导入成功！数据行数:" + listGzapGzrws.size());
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
      * 填写任务完成情况
      * @param jsonObject
      * @return
      */
     @PutMapping(value = "/editMission")
     public Result<RwxfRwgl> editMission(@RequestBody JSONObject jsonObject) {
         Result<RwxfRwgl> result = new Result<RwxfRwgl>();
         String id = jsonObject.getString("id");
         RwxfRwgl rwxfRwglEntity = rwxfRwglService.getById(id);

         RwxfRwgl rwxfRwgl = new RwxfRwgl();
         try {
             rwxfRwgl.setId(id);
             long rwz = jsonObject.getLong("rwz");
             long wcz = jsonObject.getLong("wcz");
             if (rwz > wcz) {
                 rwxfRwgl.setSfdb("2"); // 未达标
             } else {
                 rwxfRwgl.setSfdb("1"); // 已达标
             }
             rwxfRwgl.setWcz(wcz);
             rwxfRwgl.setWcsj(new Date());
             rwxfRwgl.setWcqk(jsonObject.getString("wcqk"));
             rwxfRwgl.setWczt("1");
             rwxfRwglService.updateById(rwxfRwgl);
             result.success("填写成功！");
         } catch (Exception e) {
             log.error(e.getMessage(),e);
             result.error500("填写失败");
         }
         return result;
     }
 }
