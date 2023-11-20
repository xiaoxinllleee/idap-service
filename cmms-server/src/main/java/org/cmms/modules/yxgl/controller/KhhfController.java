package org.cmms.modules.yxgl.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.yxgl.entity.Khhffjxx;
import org.cmms.modules.yxgl.entity.Khhf;
import org.cmms.modules.yxgl.vo.KhhfPage;
import org.cmms.modules.yxgl.service.IKhhfService;
import org.cmms.modules.yxgl.service.IKhhffjxxService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 客户回访
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
@RestController
@RequestMapping("/yxgl/khhf")
@Slf4j
public class KhhfController {
	@Autowired
	private IKhhfService khhfService;
	@Autowired
	private IKhhffjxxService khhffjxxService;

	/**
	  * 分页列表查询
	 * @param khhf
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<Khhf>> queryPageList(Khhf khhf,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Khhf>> result = new Result<IPage<Khhf>>();
		QueryWrapper<Khhf> queryWrapper = QueryGenerator.initQueryWrapper(khhf, req.getParameterMap());
		Page<Khhf> page = new Page<Khhf>(pageNo, pageSize);
		IPage<Khhf> pageList = khhfService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	  *   添加
	 * @param khhfPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Khhf> add(@RequestBody KhhfPage khhfPage) {
		Result<Khhf> result = new Result<Khhf>();
		try {
			Khhf khhf = new Khhf();
			BeanUtils.copyProperties(khhfPage, khhf);

			khhfService.saveMain(khhf, khhfPage.getKhhffjxxList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	  *  编辑
	 * @param khhfPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Khhf> edit(@RequestBody KhhfPage khhfPage) {
		Result<Khhf> result = new Result<Khhf>();
		Khhf khhf = new Khhf();
		BeanUtils.copyProperties(khhfPage, khhf);
		Khhf khhfEntity = khhfService.getById(khhf.getId());
		List<Khhffjxx> khhffjxxList = khhfPage.getKhhffjxxList();
		if(khhfEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = khhfService.updateById(khhf);
			khhfService.updateMain(khhf, khhfPage.getKhhffjxxList());
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
			khhfService.delMain(id);
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
	public Result<Khhf> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Khhf> result = new Result<Khhf>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.khhfService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<Khhf> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Khhf> result = new Result<Khhf>();
		Khhf khhf = khhfService.getById(id);
		if(khhf==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khhf);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryKhhffjxxByMainId")
	public Result<Map<String,List<Khhffjxx>>> queryKhhffjxxListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<Map<String,List<Khhffjxx>>> result = new Result<Map<String,List<Khhffjxx>>>();
		List<Khhffjxx> khhffjxxList = khhffjxxService.selectByMainId(id);
		Map<String,List<Khhffjxx>> map = new HashMap<String,List<Khhffjxx>>();
		List<Khhffjxx> fjxxImage = new ArrayList<Khhffjxx>();
		List<Khhffjxx> fjxxVideo = new ArrayList<Khhffjxx>();
		List<Khhffjxx> fjxxAudio = new ArrayList<Khhffjxx>();
		for (Khhffjxx khhffjxx : khhffjxxList) {
			String fjlx = khhffjxx.getFjlx();
			if ("1".equalsIgnoreCase(fjlx)) {
				fjxxImage.add(khhffjxx);
			} else if ("2".equalsIgnoreCase(fjlx)) {
				fjxxVideo.add(khhffjxx);
			} else if ("3".equalsIgnoreCase(fjlx)) {
				fjxxAudio.add(khhffjxx);
			}
		}
		map.put("fjxxImage", fjxxImage);
		map.put("fjxxVideo", fjxxVideo);
		map.put("fjxxAudio", fjxxAudio);
		result.setResult(map);
		result.setSuccess(true);
		return result;
	}

	 /**
	  * 冻结&解冻用户
	  * @param jsonObject
	  * @return
	  */
	 @RequestMapping(value = "/submitBatch", method = RequestMethod.PUT)
	 public Result<Khhf> frozenBatch(@RequestBody JSONObject jsonObject) {
		 Result<Khhf> result = new Result<Khhf>();
		 try {
			 String ids = jsonObject.getString("ids");
			 String status = jsonObject.getString("status");
			 String[] arr = ids.split(",");
			 for (String id : arr) {
				 if(oConvertUtils.isNotEmpty(id)) {
					 this.khhfService.update(new Khhf().setTjzt(status),
							 new UpdateWrapper<Khhf>().lambda().eq(Khhf::getId,id));
				 }
			 }
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.error500("操作失败"+e.getMessage());
		 }
		 result.success("操作成功!");
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
      QueryWrapper<Khhf> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Khhf khhf = JSON.parseObject(deString, Khhf.class);
              queryWrapper = QueryGenerator.initQueryWrapper(khhf, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<KhhfPage> pageList = new ArrayList<KhhfPage>();
      List<Khhf> khhfList = khhfService.list(queryWrapper);
      for (Khhf khhf : khhfList) {
          KhhfPage vo = new KhhfPage();
          BeanUtils.copyProperties(khhf, vo);
          List<Khhffjxx> khhffjxxList = khhffjxxService.selectByMainId(khhf.getId());
          vo.setKhhffjxxList(khhffjxxList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户回访列表");
      mv.addObject(NormalExcelConstants.CLASS, KhhfPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户回访列表数据", "导出人:Jeecg", "导出信息"));
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
              List<KhhfPage> list = ExcelImportUtil.importExcel(file.getInputStream(), KhhfPage.class, params);
              for (KhhfPage page : list) {
                  Khhf po = new Khhf();
                  BeanUtils.copyProperties(page, po);
                  khhfService.saveMain(po, page.getKhhffjxxList());
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
//5，8，6，3，9，2，1，7
	 public static int [] maopao (int [] array){
  	 int temp;
		 for (int j = 0; j < array.length; j++) {
			 for (int i = 0; i < array.length-j-1; i++) {
				 if(array[i]>array[i+1]){
					 temp=array[i];
					 array[i]=array[i+1];
					 array[i+1]=temp;
				 }
			 }
		 }

		 return array;
	 }

	 public static void main(String[] args) {
		 int [] a = new int[]{5,8,6,13,3,18,9,22,2,1,7};
		 int[] maopao = maopao(a);
		 for (int i = 0; i < maopao(a).length; i++) {
			 System.out.println(maopao[i]);
		 }
	 }
}
