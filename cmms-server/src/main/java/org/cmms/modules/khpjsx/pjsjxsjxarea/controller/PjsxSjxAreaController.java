package org.cmms.modules.khpjsx.pjsjxsjxarea.controller;

import java.io.File;
import java.sql.Timestamp;
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
import org.cmms.modules.khpjsx.pjsjxsjxarea.entity.PjsxSjxArea;
import org.cmms.modules.khpjsx.pjsjxsjxarea.service.IPjsxSjxAreaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 区域数据项
 * @Author: jeecg-boot
 * @Date:   2020-01-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="区域数据项")
@RestController
@RequestMapping("/pjsjxsjxarea/pjsxSjxArea")
public class PjsxSjxAreaController extends JeecgController<PjsxSjxArea, IPjsxSjxAreaService> {
	@Autowired
	private IPjsxSjxAreaService pjsxSjxAreaService;
	@Autowired
    private ISysDictService iSysDictService;
	@Value(value = "${common.path.upload}")
    private String uploadPath;
	private boolean status;
	
	/**
	 * 分页列表查询
	 * @param pjsxSjxArea
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "区域数据项-分页列表查询")
	@ApiOperation(value="区域数据项-分页列表查询", notes="区域数据项-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PjsxSjxArea pjsxSjxArea,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PjsxSjxArea> queryWrapper = QueryGenerator.initQueryWrapper(pjsxSjxArea, req.getParameterMap());
		Page<PjsxSjxArea> page = new Page<PjsxSjxArea>(pageNo, pageSize);
		IPage<PjsxSjxArea> pageList = pjsxSjxAreaService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 * @param pjsxSjxArea
	 * @return
	 */
	@AutoLog(value = "区域数据项-添加")
	@ApiOperation(value="区域数据项-添加", notes="区域数据项-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PjsxSjxArea pjsxSjxArea) {
	    try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            pjsxSjxArea.setCreateBy(loginUser.getUsername());
            pjsxSjxArea.setCreateTime(new Timestamp(System.currentTimeMillis()));
            pjsxSjxArea.setQydm(
                    iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001")
            );
            pjsxSjxAreaService.save(pjsxSjxArea);
        } catch (Exception e){
            log.error("添加失败",e.getMessage());
            return Result.error("添加失败!");
        }
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 * @param pjsxSjxArea
	 * @return
	 */
	@AutoLog(value = "区域数据项-编辑")
	@ApiOperation(value="区域数据项-编辑", notes="区域数据项-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PjsxSjxArea pjsxSjxArea) {
	    try {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            pjsxSjxArea.setUpdateBy(sysUser.getUsername());
            pjsxSjxArea.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            pjsxSjxArea.setQydm(
                    iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001")
            );
            pjsxSjxAreaService.updateById(pjsxSjxArea);
        } catch (Exception e) {
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
	@AutoLog(value = "区域数据项-通过id删除")
	@ApiOperation(value="区域数据项-通过id删除", notes="区域数据项-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pjsxSjxAreaService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "区域数据项-批量删除")
	@ApiOperation(value="区域数据项-批量删除", notes="区域数据项-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjsxSjxAreaService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "区域数据项-通过id查询")
	@ApiOperation(value="区域数据项-通过id查询", notes="区域数据项-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PjsxSjxArea pjsxSjxArea = pjsxSjxAreaService.getById(id);
		return Result.ok(pjsxSjxArea);
	}

  /**
   * 导出excel
   * @param request
   * @param pjsxSjxArea
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PjsxSjxArea pjsxSjxArea) {
      return super.exportXls(request, pjsxSjxArea, PjsxSjxArea.class, "区域数据项");
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
      modelAndView.addObject(NormalExcelConstants.FILE_NAME, "评级授信区域数据项导入模板");
      modelAndView.addObject(NormalExcelConstants.CLASS, PjsxSjxArea.class);
      ExportParams exportParams = new ExportParams("评级授信区域数据项导入模板", "模板信息");
      modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
      modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<PjsxSjxArea>());
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
          try {
              List<PjsxSjxArea> pjsxSjxAreaList = ExcelImportUtil.importExcel(file, PjsxSjxArea.class, importParams);
              List<String> list = new ArrayList<String>();
              for (PjsxSjxArea sjxAreaEntity : pjsxSjxAreaList) {
                  status = sjxAreaEntity.getSjxid().contains(".");
                  if (status) { sjxAreaEntity.setSjxid(sjxAreaEntity.getSjxid().split("\\.")[0]); }
                  sjxAreaEntity.setQydm(iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001"));
                  Map<String, String> param = new HashMap<>();
                  param.put("qydm", sjxAreaEntity.getQydm());
                  param.put("sjxid", sjxAreaEntity.getSjxid());
                  PjsxSjxArea pjsxSjxArea = pjsxSjxAreaService.querySjxid(param);
                  if (pjsxSjxArea != null) {
                      list.add(pjsxSjxArea.getQydm()+"-"+pjsxSjxArea.getSjxid());
                  }
              }
              if (!list.isEmpty()) {
                  for (String data : list) {
                      String[] strings = data.split("-");
                      String qydm = strings[0];
                      String sjxid = strings[1];
                      QueryWrapper<PjsxSjxArea> updateWrapper = new QueryWrapper<>();
                      updateWrapper.eq("qydm", qydm).eq("sjxid", sjxid);
                      pjsxSjxAreaService.remove(updateWrapper);
                  }
              }
              pjsxSjxAreaService.saveBatch(pjsxSjxAreaList);
              return Result.ok("文件导入成功！共[ "+pjsxSjxAreaList.size()+" ]条数据！");
          } catch (Exception e) {
              log.error(e.getMessage(), e);
              return Result.error("文件导入失败！"+e.getMessage());
          }
      }
      return Result.ok("文件导入失败！");
  }

}
