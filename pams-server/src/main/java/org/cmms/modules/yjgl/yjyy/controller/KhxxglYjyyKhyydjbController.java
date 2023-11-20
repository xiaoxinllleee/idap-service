package org.cmms.modules.yjgl.yjyy.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.performance.depositcustomer.ckzhghlsb.entity.Ckzhghlsb;
import org.cmms.modules.performance.depositcustomer.ckzhghlsb.service.ICkzhghlsbService;
import org.cmms.modules.performance.depositcustomer.ckzhtzxx.entity.Ckzhtzxx;
import org.cmms.modules.performance.depositcustomer.ckzhtzxx.service.ICkzhtzxxService;
import org.cmms.modules.yjgl.yjyy.entity.KhxxglYjyyKhyydjb;
import org.cmms.modules.yjgl.yjyy.entity.KhxxglYjyyKhyydjbVo;
import org.cmms.modules.yjgl.yjyy.service.IKhxxglYjyyKhyydjbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户预约登记簿
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户预约登记簿")
@RestController
@RequestMapping("/yjyy/khxxglYjyyKhyydjb")
public class KhxxglYjyyKhyydjbController extends JeecgController<KhxxglYjyyKhyydjb, IKhxxglYjyyKhyydjbService> {
	@Autowired
	private IKhxxglYjyyKhyydjbService khxxglYjyyKhyydjbService;
	@Autowired
	private ICkzhtzxxService ckzhtzxxService;
	@Autowired
	private ICkzhghlsbService ckzhghlsbService;
	 @Autowired
	 IDictValueQuery iDictValueQuery;
	/**
	 * 分页列表查询
	 *
	 * @param khxxglYjyyKhyydjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户预约登记簿-分页列表查询")
	@ApiOperation(value="客户预约登记簿-分页列表查询", notes="客户预约登记簿-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhxxglYjyyKhyydjb khxxglYjyyKhyydjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhxxglYjyyKhyydjb> queryWrapper = QueryGenerator.initQueryWrapper(khxxglYjyyKhyydjb, req.getParameterMap());
		Page<KhxxglYjyyKhyydjb> page = new Page<KhxxglYjyyKhyydjb>(pageNo, pageSize);
		IPage<KhxxglYjyyKhyydjb> pageList = khxxglYjyyKhyydjbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param khxxglYjyyKhyydjb
	 * @return
	 */
	@AutoLog(value = "客户预约登记簿-添加")
	@ApiOperation(value="客户预约登记簿-添加", notes="客户预约登记簿-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhxxglYjyyKhyydjb khxxglYjyyKhyydjb) {
		QueryWrapper<KhxxglYjyyKhyydjb> check = new QueryWrapper<>();
		check.eq("jgdm", khxxglYjyyKhyydjb.getJgdm());
		check.eq("khmc", khxxglYjyyKhyydjb.getKhmc());
		check.eq("yyrq", khxxglYjyyKhyydjb.getYyrq());
		check.eq("yylx", khxxglYjyyKhyydjb.getYylx());
		if (StringUtils.isNotEmpty(khxxglYjyyKhyydjb.getZjhm())) {
			check.like("zjhm", khxxglYjyyKhyydjb.getZjhm());
		}
		List<KhxxglYjyyKhyydjb> checkList =  khxxglYjyyKhyydjbService.list(check);
		if (!checkList.isEmpty()) {
			return Result.error("已经存在此客户当天的预约记录！");
		}
//		String yybh =
		//生成预约编号
		String prefix = "";
		if("1".equals(khxxglYjyyKhyydjb.getYylx())) {
			prefix = "CK";
		} else if ("2".equals(khxxglYjyyKhyydjb.getYylx())) {
			prefix = "DK";
		} else if ("3".equals(khxxglYjyyKhyydjb.getYylx())) {
			prefix = "SJYH";
		}
		String yybh = prefix + khxxglYjyyKhyydjb.getYxrgh() + DateUtil.format(khxxglYjyyKhyydjb.getYyrq(), "yyyyMMdd") + iDictValueQuery.getSeqNextval("SEQ_PUBLIC_ID.nextval");
		khxxglYjyyKhyydjb.setYybh(yybh);
		khxxglYjyyKhyydjbService.save(khxxglYjyyKhyydjb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param khxxglYjyyKhyydjb
	 * @return
	 */
	@AutoLog(value = "客户预约登记簿-编辑")
	@ApiOperation(value="客户预约登记簿-编辑", notes="客户预约登记簿-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhxxglYjyyKhyydjb khxxglYjyyKhyydjb) {
		QueryWrapper<KhxxglYjyyKhyydjb> check = new QueryWrapper<>();
		check.ne("yybh", khxxglYjyyKhyydjb.getYybh());
		check.eq("jgdm", khxxglYjyyKhyydjb.getJgdm());
		check.eq("khmc", khxxglYjyyKhyydjb.getKhmc());
		check.eq("yyrq", khxxglYjyyKhyydjb.getYyrq());
		check.eq("yylx", khxxglYjyyKhyydjb.getYylx());
		if (StringUtils.isNotEmpty(khxxglYjyyKhyydjb.getZjhm())) {
			check.like("zjhm", khxxglYjyyKhyydjb.getZjhm());
		}
		List<KhxxglYjyyKhyydjb> checkList =  khxxglYjyyKhyydjbService.list(check);
		if (!checkList.isEmpty()) {
			return Result.error("已经存在此客户当天的预约记录！");
		}
		khxxglYjyyKhyydjbService.updateById(khxxglYjyyKhyydjb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户预约登记簿-通过id删除")
	@ApiOperation(value="客户预约登记簿-通过id删除", notes="客户预约登记簿-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khxxglYjyyKhyydjbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户预约登记簿-批量删除")
	@ApiOperation(value="客户预约登记簿-批量删除", notes="客户预约登记簿-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khxxglYjyyKhyydjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户预约登记簿-通过id查询")
	@ApiOperation(value="客户预约登记簿-通过id查询", notes="客户预约登记簿-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhxxglYjyyKhyydjb khxxglYjyyKhyydjb = khxxglYjyyKhyydjbService.getById(id);
		return Result.ok(khxxglYjyyKhyydjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khxxglYjyyKhyydjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhxxglYjyyKhyydjb khxxglYjyyKhyydjb) {
      return super.exportXls(request, khxxglYjyyKhyydjb, KhxxglYjyyKhyydjb.class, "客户预约登记簿");
  }

	 /**
	  * 通过excel导入数据
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 String filePaths = jsonObject.getString("filePaths");
		 if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 JSONObject obj = new JSONObject();
		 for (String filePath : filePathList) {
			 String baseFilePath = uploadpath + File.separator + filePath;
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			 /*if (khxxglJzyxDklshVerify != null) {
				 params.setVerifyHanlder(khxxglJzyxDklshVerify);
			 }*/
			 FileOutputStream fos = null;
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(file);
				 boolean checkResult = ExcelImportCheckUtil.check(fis, KhxxglYjyyKhyydjbVo.class, params);
				 ExcelImportResult<KhxxglYjyyKhyydjbVo> importResult = ExcelImportUtil.importExcelVerify(file, KhxxglYjyyKhyydjbVo.class, params);
				 List<KhxxglYjyyKhyydjbVo> list = importResult.getList();
				 List<KhxxglYjyyKhyydjb> list1 =new ArrayList<>();
				 for(KhxxglYjyyKhyydjbVo khxxglYjyyKhyydjbVo:list){
					 KhxxglYjyyKhyydjb khxxglYjyyKhyydjb=new KhxxglYjyyKhyydjb();
					 BeanUtils.copyProperties(khxxglYjyyKhyydjbVo, khxxglYjyyKhyydjb);
					 QueryWrapper<KhxxglYjyyKhyydjb> queryWrapper=new QueryWrapper();
					 queryWrapper.eq("zjhm",khxxglYjyyKhyydjb.getZjhm());
					 queryWrapper.eq("jgdm",khxxglYjyyKhyydjb.getJgdm());
					 queryWrapper.eq("yybh",khxxglYjyyKhyydjb.getYybh());
					 queryWrapper.eq("yylx",khxxglYjyyKhyydjb.getYylx());
					 queryWrapper.eq("yyrq",khxxglYjyyKhyydjb.getYyrq());
					 khxxglYjyyKhyydjbService.remove(queryWrapper);
					 list1.add(khxxglYjyyKhyydjb);
				 }
				 khxxglYjyyKhyydjbService.saveBatch(list1);
				 obj.put("filePath", filePath);
				 fos = new FileOutputStream(baseFilePath);
				 importResult.getWorkbook().write(fos);
				 fos.flush();
				 fos.close();
				 return Result.ok("文件导入完成！成功导入数据行数:" + list1.size(), obj);
			 } catch (Exception e) {
				 log.error(e.getMessage(),e);
				 return Result.error("文件导入失败:"+e.getMessage());
			 } finally {
				 IoUtil.close(fis);
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

	 /**
	  * 导出模板
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "客户预约登记簿");
		 modelAndView.addObject(NormalExcelConstants.CLASS, KhxxglYjyyKhyydjbVo.class);
		 ExportParams exportParams = new ExportParams("客户预约登记簿", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

	 @PostMapping(value ="/approve")
	 public Result<?> approve(@RequestBody JSONObject jsonObject) {
		 KhxxglYjyyKhyydjb khyydjb = JSON.parseObject(JSON.toJSONString(jsonObject), KhxxglYjyyKhyydjb.class);
		 String spjg = jsonObject.getString("spjg");
	 	 String spsm = jsonObject.getString("spsm");


		 KhxxglYjyyKhyydjb khyydjbUpdate = new KhxxglYjyyKhyydjb();
		 khyydjbUpdate.setBz(khyydjb.getBz() + " " + StringUtils.nvl(spsm));
	 	 //审批通过
		 if("1".equals(spjg)) {
		 	 khyydjbUpdate.setSbzt("1");
			 QueryWrapper<Ckzhtzxx> ckzhtzxxQueryWrapper = new QueryWrapper<Ckzhtzxx>();
			 ckzhtzxxQueryWrapper.eq("ckzh", khyydjb.getCkzh());
			 Ckzhtzxx ckzhtzxx = ckzhtzxxService.getOne(ckzhtzxxQueryWrapper);

			 //将数据写入正式表
			 Ckzhghlsb ckzhghlsb = new Ckzhghlsb();
			 ckzhghlsb.setJgdm(ckzhtzxx.getJgdm());
			 ckzhghlsb.setKhbh(ckzhtzxx.getKhbh());
			 ckzhghlsb.setCkzh(ckzhtzxx.getCkzh());
			 ckzhghlsb.setZhlx(ckzhtzxx.getZhlx());
			 ckzhghlsb.setGhlx(1);
			 ckzhghlsb.setGhr(khyydjb.getYxrgh());
			 ckzhghlsb.setGhbl(khyydjb.getYxbl());
			 ckzhghlsb.setKsrq(khyydjb.getYyrq());
			 ckzhghlsb.setRlje(ckzhtzxx.getCkye());
			 ckzhghlsb.setSjly(1);
			 ckzhghlsb.setBz("活期存款预约");
			 ckzhghlsbService.save(ckzhghlsb);

			 //更新yxlx
			 Ckzhtzxx update = new Ckzhtzxx();
			 update.setYxlx(1);
			 ckzhtzxxService.update(update, ckzhtzxxQueryWrapper);

			 //更新原拓展人的移交时间
			 if (StringUtils.isNotEmpty(khyydjb.getCkghlsbid())) {
				 UpdateWrapper<Ckzhghlsb> updateWrapper = new UpdateWrapper<>();
				 updateWrapper.eq("id", khyydjb.getCkghlsbid());
				 Ckzhghlsb ckzhghlsbUpdate = new Ckzhghlsb();
				 ckzhghlsbUpdate.setJsrq(DateUtil.addDays(khyydjb.getYyrq(), -1));
				 ckzhghlsbUpdate.setLrbz(2);
				 ckzhghlsbUpdate.setBz("移交给预约人：" + khyydjb.getYxrgh());
				 ckzhghlsbService.update(ckzhghlsbUpdate, updateWrapper);
			 }
		 } else {
			 khyydjbUpdate.setSbzt("4");
		 }
		 QueryWrapper<KhxxglYjyyKhyydjb> khyydjbQueryWrapper = new QueryWrapper<>();
		 khyydjbQueryWrapper.eq("yybh", khyydjb.getYybh());
		 khxxglYjyyKhyydjbService.update(khyydjbUpdate, khyydjbQueryWrapper);
		 return Result.ok("操作成功!");
	 }
 }
