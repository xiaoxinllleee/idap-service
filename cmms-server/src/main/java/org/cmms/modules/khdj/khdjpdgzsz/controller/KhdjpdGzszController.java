package org.cmms.modules.khdj.khdjpdgzsz.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khdj.khdjpdgzsz.entity.KhdjpdGzszGzxx;
import org.cmms.modules.khdj.khdjpdgzsz.entity.KhdjpdGzsz;
import org.cmms.modules.khdj.khdjpdgzsz.service.IKhdjpdGzszGzxxService;
import org.cmms.modules.khdj.khdjpdgzsz.service.IKhdjpdGzszService;
import org.cmms.modules.khdj.khdjpdgzsz.vo.KhdjpdGzszPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Description: 客户等级规则设置
 * @Author: cmms
 * @Date:   2019-09-21
 * @Version: V1.0
 */
@RestController
@RequestMapping("/khdj/khdjpdgzsz")
@Slf4j
public class KhdjpdGzszController {
	@Autowired
	private IKhdjpdGzszService iKhdjpdGzszService;
	@Autowired
	private IKhdjpdGzszGzxxService iKhdjpdGzszGzxxService;

	/**
	  * 分页列表查询
	 * @param khdjpdGzsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<KhdjpdGzsz>> queryPageList(KhdjpdGzsz khdjpdGzsz,
                                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                   HttpServletRequest req) {
		Result<IPage<KhdjpdGzsz>> result = new Result<IPage<KhdjpdGzsz>>();
		QueryWrapper<KhdjpdGzsz> queryWrapper = QueryGenerator.initQueryWrapper(khdjpdGzsz, req.getParameterMap());
		Page<KhdjpdGzsz> page = new Page<KhdjpdGzsz>(pageNo, pageSize);
		IPage<KhdjpdGzsz> pageList = iKhdjpdGzszService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param khdjpdGzszPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<KhdjpdGzsz> add(@RequestBody KhdjpdGzszPage khdjpdGzszPage) {
		Result<KhdjpdGzsz> result = new Result<KhdjpdGzsz>();
		try {
			KhdjpdGzsz kHDJ_KHDJPDGZSZ_real = new KhdjpdGzsz();
			BeanUtils.copyProperties(khdjpdGzszPage, kHDJ_KHDJPDGZSZ_real);

			iKhdjpdGzszService.saveMain(kHDJ_KHDJPDGZSZ_real, khdjpdGzszPage.getGzap_jhxf_khjl_gzxx_realList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}




	/**
	  *  编辑
	 * @param kHDJ_KHDJPDGZSZ_realPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<KhdjpdGzsz> edit(@RequestBody KhdjpdGzszPage kHDJ_KHDJPDGZSZ_realPage) {
		Result<KhdjpdGzsz> result = new Result<KhdjpdGzsz>();
		KhdjpdGzsz kHDJ_KHDJPDGZSZ_real = new KhdjpdGzsz();
		BeanUtils.copyProperties(kHDJ_KHDJPDGZSZ_realPage, kHDJ_KHDJPDGZSZ_real);
		KhdjpdGzsz kHDJ_KHDJPDGZSZ_realEntity = iKhdjpdGzszService.getById(kHDJ_KHDJPDGZSZ_real.getId());
		if(kHDJ_KHDJPDGZSZ_realEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = iKhdjpdGzszService.updateById(kHDJ_KHDJPDGZSZ_real);
			iKhdjpdGzszService.updateMain(kHDJ_KHDJPDGZSZ_real, kHDJ_KHDJPDGZSZ_realPage.getGzap_jhxf_khjl_gzxx_realList());
			result.success("修改成功!");
		}

		return result;
	}

	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			iKhdjpdGzszService.delMain(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}

	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<KhdjpdGzsz> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<KhdjpdGzsz> result = new Result<KhdjpdGzsz>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.iKhdjpdGzszService.delBatchMain(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<KhdjpdGzsz> queryById(@RequestParam(name="id",required=true) String id) {
		Result<KhdjpdGzsz> result = new Result<KhdjpdGzsz>();
		KhdjpdGzsz kHDJ_KHDJPDGZSZ_real = iKhdjpdGzszService.getById(id);
		if(kHDJ_KHDJPDGZSZ_real==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(kHDJ_KHDJPDGZSZ_real);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryGzap_jhxf_khjl_gzxx_realByMainId")
	public Result<List<KhdjpdGzszGzxx>> queryGzap_jhxf_khjl_gzxx_realListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<KhdjpdGzszGzxx>> result = new Result<List<KhdjpdGzszGzxx>>();
		List<KhdjpdGzszGzxx> gzap_jhxf_khjl_gzxx_realList = iKhdjpdGzszGzxxService.selectByMainId(id);
		result.setResult(gzap_jhxf_khjl_gzxx_realList);
		result.setSuccess(true);
		return result;
	}

	 /**
	  * 通过id查询
	  * @param mainId
	  * @param key
	  * @return
	  */
	 @GetMapping(value = "/queryByMainIdAndKey")
	 public Result<List<KhdjpdGzszGzxx>> queryByMainIdAndKey(@RequestParam(name="mainId",required=true) String mainId,
														 @RequestParam(name="key",required=true) String key) {
		 Result<List<KhdjpdGzszGzxx>> result = new Result<List<KhdjpdGzszGzxx>>();
		 List<KhdjpdGzszGzxx> gzxxList = iKhdjpdGzszGzxxService.selectByMainIdAndKey(mainId, key);
		 result.setResult(gzxxList);
		 result.setSuccess(true);
		 return result;
	 }

	 /**
	  * 通过id查询
	  * @param mainId
	  * @return
	  */
	 @GetMapping(value = "/getAllKey")
	 public Result<List<KhdjpdGzszGzxx>> getAllKey(@RequestParam(name="mainId",required=true) String mainId) {
		 Result<List<KhdjpdGzszGzxx>> result = new Result<List<KhdjpdGzszGzxx>>();
		 List<KhdjpdGzszGzxx> gzxxList = iKhdjpdGzszGzxxService.selectByMainId(mainId);
		 result.setResult(gzxxList);
		 result.setSuccess(true);
		 return result;
	 }
  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<KhdjpdGzsz> queryWrapper = null;
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              KhdjpdGzsz kHDJ_KHDJPDGZSZ_real = JSON.parseObject(deString, KhdjpdGzsz.class);
              queryWrapper = QueryGenerator.initQueryWrapper(kHDJ_KHDJPDGZSZ_real, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<KhdjpdGzszPage> pageList = new ArrayList<KhdjpdGzszPage>();
      List<KhdjpdGzsz> kHDJ_KHDJPDGZSZ_realList = iKhdjpdGzszService.list(queryWrapper);
      for (KhdjpdGzsz kHDJ_KHDJPDGZSZ_real : kHDJ_KHDJPDGZSZ_realList) {
          KhdjpdGzszPage vo = new KhdjpdGzszPage();
          BeanUtils.copyProperties(kHDJ_KHDJPDGZSZ_real, vo);
          List<KhdjpdGzszGzxx> gzap_jhxf_khjl_gzxx_realList = iKhdjpdGzszGzxxService.selectByMainId(kHDJ_KHDJPDGZSZ_real.getId());
          vo.setGzap_jhxf_khjl_gzxx_realList(gzap_jhxf_khjl_gzxx_realList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户等级规则设置列表");
      mv.addObject(NormalExcelConstants.CLASS, KhdjpdGzszPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户等级规则数据列表", "导出人:"+sysUser.getRealname(), "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<KhdjpdGzszPage> list = ExcelImportUtil.importExcel(file.getInputStream(), KhdjpdGzszPage.class, params);
              for (KhdjpdGzszPage page : list) {
                  KhdjpdGzsz po = new KhdjpdGzsz();
                  BeanUtils.copyProperties(page, po);
                  iKhdjpdGzszService.saveMain(po, page.getGzap_jhxf_khjl_gzxx_realList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
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

}
