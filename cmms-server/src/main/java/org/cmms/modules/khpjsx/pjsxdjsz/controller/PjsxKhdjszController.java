package org.cmms.modules.khpjsx.pjsxdjsz.controller;

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
import org.cmms.modules.khpjsx.pjsxdjsz.entity.PjsxKhdjsz;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khpjsx.pjsxdjsz.service.IPjsxKhdjszService;
import org.cmms.modules.system.entity.SysDict;
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
 * @Description: 评级授信等级设置
 * @Author: Penghr
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评级授信等级设置")
@RestController
@RequestMapping("/pjsxdjsz/pjsxKhdjsz")
public class PjsxKhdjszController extends JeecgController<PjsxKhdjsz, IPjsxKhdjszService> {

    @Autowired
	private IPjsxKhdjszService iPjsxKhdjszService;
    @Autowired
    private ISysDictService iSysDictService;
    @Value(value = "${common.path.upload}")
    private String uploadPath;
    private boolean status;

	/**
	 * 分页列表查询
	 * @param pjsxKhdjsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评级授信等级设置-分页列表查询")
	@ApiOperation(value="评级授信等级设置-分页列表查询", notes="评级授信等级设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PjsxKhdjsz pjsxKhdjsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PjsxKhdjsz> queryWrapper = QueryGenerator.initQueryWrapper(pjsxKhdjsz, req.getParameterMap());
		Page<PjsxKhdjsz> page = new Page<PjsxKhdjsz>(pageNo, pageSize);
		IPage<PjsxKhdjsz> pageList = iPjsxKhdjszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 * @param pjsxKhdjsz
	 * @return
	 */
	@AutoLog(value = "评级授信等级设置-添加")
	@ApiOperation(value="评级授信等级设置-添加", notes="评级授信等级设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PjsxKhdjsz pjsxKhdjsz) {
	    try {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            pjsxKhdjsz.setCreateBy(sysUser.getUsername());
            pjsxKhdjsz.setCreateTime(new Timestamp(System.currentTimeMillis()));
            pjsxKhdjsz.setQydm(
                    iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001")
            );
            QueryWrapper<PjsxKhdjsz> queryWrapper=new QueryWrapper();
            queryWrapper.eq("djbh",pjsxKhdjsz.getDjbh());
            List<PjsxKhdjsz> list = iPjsxKhdjszService.list(queryWrapper);
            if(list.size()==0){
                iPjsxKhdjszService.save(pjsxKhdjsz);
            }else{
                return Result.error("添加失败，等级编号重复!");
            }
        } catch (Exception e){
            log.error("添加失败",e.getMessage());
            return Result.error("添加失败!");
        }
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 * @param pjsxKhdjsz
	 * @return
	 */
	@AutoLog(value = "评级授信等级设置-编辑")
	@ApiOperation(value="评级授信等级设置-编辑", notes="评级授信等级设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PjsxKhdjsz pjsxKhdjsz) {
        try {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            pjsxKhdjsz.setUpdateBy(sysUser.getUsername());
            pjsxKhdjsz.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            pjsxKhdjsz.setQydm(
                    iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001")
            );
            iPjsxKhdjszService.updateById(pjsxKhdjsz);
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
	@AutoLog(value = "评级授信等级设置-通过id删除")
	@ApiOperation(value="评级授信等级设置-通过id删除", notes="评级授信等级设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		iPjsxKhdjszService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评级授信等级设置-批量删除")
	@ApiOperation(value="评级授信等级设置-批量删除", notes="评级授信等级设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.iPjsxKhdjszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评级授信等级设置-通过id查询")
	@ApiOperation(value="评级授信等级设置-通过id查询", notes="评级授信等级设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PjsxKhdjsz pjsxKhdjsz = iPjsxKhdjszService.getById(id);
		return Result.ok(pjsxKhdjsz);
	}

  /**
   * 导出excel
   * @param request
   * @param pjsxKhdjsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PjsxKhdjsz pjsxKhdjsz) {
      return super.exportXls(request, pjsxKhdjsz, PjsxKhdjsz.class, "评级授信等级设置");
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
      modelAndView.addObject(NormalExcelConstants.FILE_NAME, "评级授信客户等级导入模板");
      modelAndView.addObject(NormalExcelConstants.CLASS, PjsxKhdjsz.class);
      ExportParams exportParams = new ExportParams("评级授信客户等级导入模板", "模板信息");
      modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
      modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<PjsxKhdjsz>());
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
          filePath = uploadPath+File.separator+filePath;
          File file = new File(filePath);
          ImportParams importParams = new ImportParams();
          importParams.setTitleRows(1);
          importParams.setHeadRows(1);
          importParams.setNeedSave(true);
          try {
              List<PjsxKhdjsz> pjsxKhdjszList = ExcelImportUtil.importExcel(file,PjsxKhdjsz.class,importParams);
              List<String> list = new ArrayList<String>();
              for (PjsxKhdjsz djszEntity : pjsxKhdjszList) {
                  boolean status = djszEntity.getDjbh().contains(".");
                  if (status) { djszEntity.setDjbh(djszEntity.getDjbh().split("\\.")[0]); }
                  djszEntity.setQydm(iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001"));
                  Map<String, String> param = new HashMap<>();
                  param.put("qydm", djszEntity.getQydm());
                  param.put("djbh", djszEntity.getDjbh());
                  PjsxKhdjsz pjsxKhdjsz = iPjsxKhdjszService.queryDjbh(param);
                  if (pjsxKhdjsz != null) {
                      list.add(pjsxKhdjsz.getQydm()+"-"+pjsxKhdjsz.getDjbh());
                  }
              }
              if (!list.isEmpty()) {
                  for (String data : list) {
                      String[] strings = data.split("-");
                      String qydm = strings[0];
                      String djbh = strings[1];
                      QueryWrapper<PjsxKhdjsz> updateWrapper = new QueryWrapper<>();
                      updateWrapper.eq("qydm",qydm).eq("djbh", djbh);
                      iPjsxKhdjszService.remove(updateWrapper);
                  }
              }
              iPjsxKhdjszService.saveBatch(pjsxKhdjszList);
              return Result.ok("文件导入成功！共[ "+pjsxKhdjszList.size()+" ]条数据！");
          } catch (Exception e) {
              log.error(e.getMessage(), e);
              return Result.error("文件导入失败！"+e.getMessage());
          }
      }
      return Result.ok("文件导入失败！");
  }

}
