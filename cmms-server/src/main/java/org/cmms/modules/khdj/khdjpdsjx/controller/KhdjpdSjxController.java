package org.cmms.modules.khdj.khdjpdsjx.controller;

import java.io.File;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khdj.khdjpdsjx.entity.KhdjpdSjx;
import org.cmms.modules.khdj.khdjpdsjx.service.IKhdjpdSjxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户等级评定数据项
 * @Author: cmms
 * @Date:   2019-10-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户等级评定数据项")
@RestController
@RequestMapping("/khdjpdsjx/khdjpdSjx")
public class KhdjpdSjxController {
	@Autowired
	private IKhdjpdSjxService khdjpdSjxService;
	@Autowired
	private ISysDictService iSysDictService;
	@Value(value = "${common.path.upload}")
    private String uploadPath;
	private boolean status;

	/**
     * 分页列表查询
	 * @param khdjpdSjx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户等级评定数据项-分页列表查询")
	@ApiOperation(value="客户等级评定数据项-分页列表查询", notes="客户等级评定数据项-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<KhdjpdSjx>> queryPageList(KhdjpdSjx khdjpdSjx,
                                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                  HttpServletRequest req) {
		Result<IPage<KhdjpdSjx>> result = new Result<IPage<KhdjpdSjx>>();
		QueryWrapper<KhdjpdSjx> queryWrapper = QueryGenerator.initQueryWrapper(khdjpdSjx, req.getParameterMap());
		String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
		queryWrapper.eq("qydm",qydm);
		queryWrapper.orderByAsc("zxsx");
		Page<KhdjpdSjx> page = new Page<KhdjpdSjx>(pageNo, pageSize);
		IPage<KhdjpdSjx> pageList = khdjpdSjxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
     * 添加
	 * @param khdjpdSjx
	 * @return
	 */
	@AutoLog(value = "客户等级评定数据项-添加")
	@ApiOperation(value="客户等级评定数据项-添加", notes="客户等级评定数据项-添加")
	@PostMapping(value = "/add")
	public Result<KhdjpdSjx> add(@RequestBody KhdjpdSjx khdjpdSjx) {
		Result<KhdjpdSjx> result = new Result<KhdjpdSjx>();
		try {
			String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
			khdjpdSjx.setQydm(qydm==null?"":qydm);
			khdjpdSjxService.save(khdjpdSjx);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
     * 编辑
	 * @param khdjpdSjx
	 * @return
	 */
	@AutoLog(value = "客户等级评定数据项-编辑")
	@ApiOperation(value="客户等级评定数据项-编辑", notes="客户等级评定数据项-编辑")
	@PutMapping(value = "/edit")
	public Result<KhdjpdSjx> edit(@RequestBody KhdjpdSjx khdjpdSjx) {
		Result<KhdjpdSjx> result = new Result<KhdjpdSjx>();
		try {
			KhdjpdSjx khdjpdSjxEntity = khdjpdSjxService.getById(khdjpdSjx.getId());
			if (khdjpdSjxEntity==null) {
				result.error500("未找到对应实体");
			}
			String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
			khdjpdSjx.setQydm(qydm==null?"":qydm);
			khdjpdSjxService.updateById(khdjpdSjx);
			result.success("修改成功!");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
     * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户等级评定数据项-通过id删除")
	@ApiOperation(value="客户等级评定数据项-通过id删除", notes="客户等级评定数据项-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			khdjpdSjxService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}

	/**
     * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户等级评定数据项-批量删除")
	@ApiOperation(value="客户等级评定数据项-批量删除", notes="客户等级评定数据项-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<KhdjpdSjx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<KhdjpdSjx> result = new Result<KhdjpdSjx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.khdjpdSjxService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
     * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户等级评定数据项-通过id查询")
	@ApiOperation(value="客户等级评定数据项-通过id查询", notes="客户等级评定数据项-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<KhdjpdSjx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<KhdjpdSjx> result = new Result<KhdjpdSjx>();
		KhdjpdSjx khdjpdSjx = khdjpdSjxService.getById(id);
		if(khdjpdSjx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khdjpdSjx);
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
      QueryWrapper<KhdjpdSjx> queryWrapper = null;
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              KhdjpdSjx khdjpdSjx = JSON.parseObject(deString, KhdjpdSjx.class);
              queryWrapper = QueryGenerator.initQueryWrapper(khdjpdSjx, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<KhdjpdSjx> pageList = khdjpdSjxService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户等级评定数据项列表");
      mv.addObject(NormalExcelConstants.CLASS, KhdjpdSjx.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户等级评定数据项", "导出人:"+sysUser.getRealname(), "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

     /**
      * 导出Excel模板
      * @param request
      * @param response
      * @return
      */
     @RequestMapping(value = "/exportTemplateXls")
     public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
         // AutoPoi 导出Excel
         ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
         // 导出文件名称
         modelAndView.addObject(NormalExcelConstants.FILE_NAME, "客户等级评定数据项导入模板");
         modelAndView.addObject(NormalExcelConstants.CLASS, KhdjpdSjx.class);
         ExportParams exportParams = new ExportParams("客户等级评定数据项导入模板", "模板信息");
         modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
         modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<KhdjpdSjx>());
         return modelAndView;
     }

  /**
   * 通过excel导入数据
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject object) {
      String filePaths = object.getString("filePaths");
      if (StringUtils.isEmpty(filePaths)) {
          return Result.error("请先上传文件！");
      }
      String[] filePathList = filePaths.split(",");
      for (String filePath : filePathList) {
          filePath = uploadPath + File.separator + filePath;
          File file = new File(filePath);
          ImportParams importParams = new ImportParams();
          importParams.setTitleRows(1);
          importParams.setHeadRows(1);
          importParams.setNeedSave(true);
          try {
            List<KhdjpdSjx> khdjpdSjxList = ExcelImportUtil.importExcel(file, KhdjpdSjx.class, importParams);
            List<String> list = new ArrayList<String>();
            for (KhdjpdSjx khdjpdSjx : khdjpdSjxList) {
                status = khdjpdSjx.getSjxid().contains(".");
                if (status) { khdjpdSjx.setSjxid(khdjpdSjx.getSjxid().split("\\.")[0]); };
                Map<String, String> param = new HashMap<>();
                param.put("sjxid", khdjpdSjx.getSjxid());
                KhdjpdSjx pdsjxList = khdjpdSjxService.queryBySjxid(param);
                if (pdsjxList != null) {
                    list.add(khdjpdSjx.getSjxid());
                }
            }
            if (!list.isEmpty()){
                for (String id : list) {
                    String sjxid = id;
                    QueryWrapper<KhdjpdSjx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("sjxid", sjxid);
                    khdjpdSjxService.remove(queryWrapper);
                }
            }
            khdjpdSjxService.saveBatch(khdjpdSjxList);
            return Result.ok("文件导入成功！共[ "+khdjpdSjxList.size()+" ]条数据！");
          } catch (Exception e) {
              log.error(e.getMessage(), e);
              return Result.error("文件导入失败！"+e.getMessage());
          }
      }
      return Result.ok("文件导入成功！");
  }

}
