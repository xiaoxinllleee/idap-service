package org.cmms.modules.khpjsx.pjsxzbk.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khpjsx.pjsxzbk.entity.PjsxZbkxx;
import org.cmms.modules.khpjsx.pjsxzbk.service.IPjsxZbkxxService;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 评级授信指标库
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评级授信指标库")
@RestController
@RequestMapping("/pjsxzbkxx/pjszZbkxx")
public class PjsxZbkxxController extends JeecgController<PjsxZbkxx, IPjsxZbkxxService> {
	@Autowired
	private IPjsxZbkxxService pjszZbkxxService;
	@Value(value = "${common.path.upload}")
    private String uploadPath;
	private boolean status;
	
	/**
	 * 分页列表查询
	 * @param pjsxZbkxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评级授信指标库-分页列表查询")
	@ApiOperation(value="评级授信指标库-分页列表查询", notes="评级授信指标库-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PjsxZbkxx pjsxZbkxx,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<PjsxZbkxx> queryWrapper = QueryGenerator.initQueryWrapper(pjsxZbkxx, req.getParameterMap());
		Page<PjsxZbkxx> page = new Page<PjsxZbkxx>(pageNo, pageSize);
		IPage<PjsxZbkxx> pageList = pjszZbkxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 * @param pjsxZbkxx
	 * @return
	 */
	@AutoLog(value = "评级授信指标库-添加")
	@ApiOperation(value="评级授信指标库-添加", notes="评级授信指标库-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PjsxZbkxx pjsxZbkxx) {
	    try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            pjsxZbkxx.setCreateBy(loginUser.getUsername());
            pjsxZbkxx.setCreateTime(new Timestamp(System.currentTimeMillis()));
            pjszZbkxxService.save(pjsxZbkxx);
        } catch (Exception e) {
	        log.error("添加失败",e.getMessage());
	        return Result.error("添加失败!");
        }
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param pjsxZbkxx
	 * @return
	 */
	@AutoLog(value = "评级授信指标库-编辑")
	@ApiOperation(value="评级授信指标库-编辑", notes="评级授信指标库-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PjsxZbkxx pjsxZbkxx) {
	    try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            pjsxZbkxx.setUpdateBy(loginUser.getUsername());
            pjsxZbkxx.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            pjszZbkxxService.updateById(pjsxZbkxx);
        } catch (Exception e){
	        log.error("编辑失败",e.getMessage());
	        return Result.error("编辑失败!");
        }
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评级授信指标库-通过id删除")
	@ApiOperation(value="评级授信指标库-通过id删除", notes="评级授信指标库-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pjszZbkxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评级授信指标库-批量删除")
	@ApiOperation(value="评级授信指标库-批量删除", notes="评级授信指标库-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjszZbkxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评级授信指标库-通过id查询")
	@ApiOperation(value="评级授信指标库-通过id查询", notes="评级授信指标库-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PjsxZbkxx pjsxZbkxx = pjszZbkxxService.getById(id);
		return Result.ok(pjsxZbkxx);
	}

     /**
      * 导出excel
      * @param request
      * @param pjsxZbkxx
      */
     @RequestMapping(value = "/exportXls")
     public ModelAndView exportXls(HttpServletRequest request, PjsxZbkxx pjsxZbkxx) {
         return super.exportXls(request, pjsxZbkxx, PjsxZbkxx.class, "评级授信指标库");
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
         modelAndView.addObject(NormalExcelConstants.FILE_NAME, "评级授信指标库导入模板");
         modelAndView.addObject(NormalExcelConstants.CLASS, PjsxZbkxx.class);
         ExportParams exportParams = new ExportParams("评级授信指标库导入模板", "模板信息");
         modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
         modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<PjsxZbkxx>());
         return modelAndView;
     }

     /**
      * 通过excel导入数据
      * @param request
      * @param response
      * @return
      */
     @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
     public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jsonObject) {
         String filePaths = jsonObject.getString("filePaths");
         if (StringUtils.isEmpty(filePaths)) {
             return Result.error("请先上传文件！");
         }
         String[] filePathList = filePaths.split(",");
         for (String filePath : filePathList) {
             filePath = uploadPath+ File.separator+filePath;
             File file = new File(filePath);
             ImportParams importParams = new ImportParams();
             importParams.setTitleRows(1);
             importParams.setHeadRows(1);
             importParams.setNeedSave(true);
             try{
                 List<PjsxZbkxx> pjsxZbkxxList = ExcelImportUtil.importExcel(file, PjsxZbkxx.class, importParams);
                 List<String> list = new ArrayList<String>();
                 for (PjsxZbkxx zbkEntity : pjsxZbkxxList) {
                     status = zbkEntity.getZbid().contains(".");
                     if (status) { zbkEntity.setZbid(zbkEntity.getZbid().split("\\.")[0]); }
                     Map<String, String> param = new HashMap<>();
                     param.put("zbid", zbkEntity.getZbid());
                     PjsxZbkxx pjsxZbkxx = pjszZbkxxService.queryZbid(param);
                     if (pjsxZbkxx != null) {
                         list.add(pjsxZbkxx.getZbid());
                     }
                 }
                 if (!list.isEmpty()) {
                     for (String id : list) {
                         String zbid = id;
                         QueryWrapper<PjsxZbkxx> updateWrapper = new QueryWrapper<>();
                         updateWrapper.eq("zbid", zbid);
                         pjszZbkxxService.remove(updateWrapper);
                     }
                 }
                 pjszZbkxxService.saveBatch(pjsxZbkxxList);
                 return Result.ok("文件导入成功！共[ "+pjsxZbkxxList.size()+" ]条数据！");
             } catch (Exception e) {
                 log.error(e.getMessage(), e);
                 return Result.error("文件导入失败！"+e.getMessage());
             }
         }
         return Result.ok("文件导入失败！");
     }
}
