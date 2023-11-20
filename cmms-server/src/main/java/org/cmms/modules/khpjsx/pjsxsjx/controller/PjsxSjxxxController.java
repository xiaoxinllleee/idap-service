package org.cmms.modules.khpjsx.pjsxsjx.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khpjsx.pjsxsjx.entity.PjsxSjxxx;
import org.cmms.modules.khpjsx.pjsxsjx.service.IPjsxSjxxxService;
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
 * @Description: 评级授信数据项
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评级授信数据项")
@RestController
@RequestMapping("/pjsxsjx/pjsxSjxxx")
public class PjsxSjxxxController extends JeecgController<PjsxSjxxx, IPjsxSjxxxService> {
	@Autowired
	private IPjsxSjxxxService pjsxSjxxxService;
	@Autowired
    private ISysDictService iSysDictService;
	@Value(value = "${common.path.upload}")
    private String uploadPath;
	private boolean status;
	
	/**
	 * 分页列表查询
	 * @param pjsxSjxxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评级授信数据项-分页列表查询")
	@ApiOperation(value="评级授信数据项-分页列表查询", notes="评级授信数据项-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PjsxSjxxx pjsxSjxxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PjsxSjxxx> queryWrapper = QueryGenerator.initQueryWrapper(pjsxSjxxx, req.getParameterMap());
		Page<PjsxSjxxx> page = new Page<PjsxSjxxx>(pageNo, pageSize);
		IPage<PjsxSjxxx> pageList = pjsxSjxxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 * @param pjsxSjxxx
	 * @return
	 */
	@AutoLog(value = "评级授信数据项-添加")
	@ApiOperation(value="评级授信数据项-添加", notes="评级授信数据项-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PjsxSjxxx pjsxSjxxx) {
	    try {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            pjsxSjxxx.setCreateBy(sysUser.getUsername());
            pjsxSjxxx.setCreateTime(new Timestamp(System.currentTimeMillis()));
            pjsxSjxxxService.save(pjsxSjxxx);
        } catch (Exception e){
            log.error("添加失败",e.getMessage());
            return Result.error("添加失败!");
        }
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param pjsxSjxxx
	 * @return
	 */
	@AutoLog(value = "评级授信数据项-编辑")
	@ApiOperation(value="评级授信数据项-编辑", notes="评级授信数据项-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PjsxSjxxx pjsxSjxxx) {
	    try {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            pjsxSjxxx.setUpdateBy(sysUser.getUsername());
            pjsxSjxxx.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            pjsxSjxxxService.updateById(pjsxSjxxx);
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
	@AutoLog(value = "评级授信数据项-通过id删除")
	@ApiOperation(value="评级授信数据项-通过id删除", notes="评级授信数据项-通过id删除")
    @RequestMapping(value = "/delete", method = RequestMethod.PUT)
	public Result<?> delete(@RequestBody JSONObject kh) {
        UpdateWrapper<PjsxSjxxx> delete=new UpdateWrapper<>();
        delete.eq("khlx",kh.getString("khlx")).eq("sjxid",kh.getString("id"));
        pjsxSjxxxService.remove(delete);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评级授信数据项-批量删除")
	@ApiOperation(value="评级授信数据项-批量删除", notes="评级授信数据项-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjsxSjxxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评级授信数据项-通过id查询")
	@ApiOperation(value="评级授信数据项-通过id查询", notes="评级授信数据项-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PjsxSjxxx pjsxSjxxx = pjsxSjxxxService.getById(id);
		return Result.ok(pjsxSjxxx);
	}

  /**
   * 导出excel
   * @param request
   * @param pjsxSjxxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PjsxSjxxx pjsxSjxxx) {
      return super.exportXls(request, pjsxSjxxx, PjsxSjxxx.class, "评级授信数据项");
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
      modelAndView.addObject(NormalExcelConstants.FILE_NAME, "评级授信数据项导入模板");
      modelAndView.addObject(NormalExcelConstants.CLASS, PjsxSjxxx.class);
      ExportParams exportParams = new ExportParams("评级授信数据项导入模板", "模板信息");
      modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
      modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<PjsxSjxxx>());
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
              List<PjsxSjxxx> pjsxSjxxxList = ExcelImportUtil.importExcel(file, PjsxSjxxx.class, importParams);
              List<String> list = new ArrayList<String>();
              for (PjsxSjxxx sjxEntity : pjsxSjxxxList) {
                  status = sjxEntity.getSjxid().contains(".");
                  if (status) { sjxEntity.setSjxid(sjxEntity.getSjxid().split("\\.")[0]); }
                  Map<String, String> param = new HashMap<>();
                  param.put("sjxid", sjxEntity.getSjxid());
                  PjsxSjxxx pjsxSjxxx = pjsxSjxxxService.querySjxid(param);
                  if (pjsxSjxxx != null) {
                      list.add(pjsxSjxxx.getSjxid());
                  }
              }
              if (!list.isEmpty()) {
                  for (String id : list) {
                      String sjxid = id;
                      QueryWrapper<PjsxSjxxx> updateWrapper = new QueryWrapper<>();
                      updateWrapper.eq("sjxid", sjxid);
                      pjsxSjxxxService.remove(updateWrapper);
                  }
              }
              pjsxSjxxxService.saveBatch(pjsxSjxxxList);
              return Result.ok("文件导入成功！共[ "+pjsxSjxxxList.size()+" ]条数据！");
          } catch (Exception e) {
              log.error(e.getMessage(), e);
              return Result.error("文件导入失败！"+e.getMessage());
          }
      }
      return Result.ok("文件导入失败！");
  }

}
