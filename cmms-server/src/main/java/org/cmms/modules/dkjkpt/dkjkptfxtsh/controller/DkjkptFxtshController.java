package org.cmms.modules.dkjkpt.dkjkptfxtsh.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dkjkptdjkjk.entity.DkjkptDjkJk;
import org.cmms.modules.dkjkpt.dkjkptdjkjk.service.IDkjkptDjkJkService;
import org.cmms.modules.dkjkpt.dkjkptfmk.entity.DkjkptFmk;
import org.cmms.modules.dkjkpt.dkjkptfmk.service.IDkjkptFmkService;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.entity.*;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.service.IDkjkptFxtshService;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjbl.entity.DkjkptByxjbl;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyxjbl.service.IDkjkptByxjblService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.WordUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 风险提示函
 * @Author: jeecg-boot
 * @Date:   2023-09-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="风险提示函")
@RestController
@RequestMapping("/dkjkptfxtsh/dkjkptFxtsh")
public class DkjkptFxtshController extends JeecgController<DkjkptFxtsh, IDkjkptFxtshService> {
	@Autowired
	private IDkjkptFxtshService dkjkptFxtshService;
	 @Value(value = "${common.path.export}")
	 private String exportpath;
	 @Autowired
	 private ISysDictService sysDictService;
	 @Autowired
	 private IDkjkptByxjblService dkjkptByxjblService;
	 @Autowired
	 private IDkjkptDjkJkService dkjkptDjkJkService;
	 @Autowired
	 private IDkjkptFmkService dkjkptFmkService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkptFxtsh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "风险提示函-分页列表查询")
	@ApiOperation(value="风险提示函-分页列表查询", notes="风险提示函-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjkptFxtsh dkjkptFxtsh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjkptFxtsh> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptFxtsh, req.getParameterMap());
		Page<DkjkptFxtsh> page = new Page<DkjkptFxtsh>(pageNo, pageSize);
		IPage<DkjkptFxtsh> pageList = dkjkptFxtshService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkptFxtsh
	 * @return
	 */
	@AutoLog(value = "风险提示函-添加")
	@ApiOperation(value="风险提示函-添加", notes="风险提示函-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkjkptFxtsh dkjkptFxtsh) {
		dkjkptFxtshService.save(dkjkptFxtsh);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkptFxtsh
	 * @return
	 */
	@AutoLog(value = "风险提示函-编辑")
	@ApiOperation(value="风险提示函-编辑", notes="风险提示函-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkjkptFxtsh dkjkptFxtsh) {
		dkjkptFxtshService.updateById(dkjkptFxtsh);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "风险提示函-通过id删除")
	@ApiOperation(value="风险提示函-通过id删除", notes="风险提示函-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkjkptFxtshService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "风险提示函-批量删除")
	@ApiOperation(value="风险提示函-批量删除", notes="风险提示函-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkptFxtshService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "风险提示函-通过id查询")
	@ApiOperation(value="风险提示函-通过id查询", notes="风险提示函-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkjkptFxtsh dkjkptFxtsh = dkjkptFxtshService.getById(id);
		return Result.ok(dkjkptFxtsh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptFxtsh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DkjkptFxtsh dkjkptFxtsh) {
      return super.exportXls(request, dkjkptFxtsh, DkjkptFxtsh.class, "风险提示函");
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
      return super.importExcel(request, response, DkjkptFxtsh.class);
  }
	 /**
	  * 提取
	  * @return
	  */
	 @AutoLog(value = "提取")
	 @ApiOperation(value="提取", notes="提取")
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject) {
		 String tjyf = jsonObject.getString("tjyf");
		 Result result = new Result<>();
		 tjyf = tjyf.replaceAll("-", "");
		 try {
			 dkjkptFxtshService.init(tjyf);
			 result.setSuccess(true);
			 result.setMessage("统计成功");
			 return result;
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.setSuccess(false);
			 result.setMessage("统计失败");
			 return result;
		 }
	 }


	 /**
	  * 导出风险提示函
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportExportFxedXls")
	 public void exportExportFxedXls(HttpServletRequest request,
									 HttpServletResponse response,
									 DkjkptFxtsh dkjkptFxtsh) {
		 InputStream inputStream = null;
		 OutputStream outputStream = null;
		 try {
			 if (dkjkptFxtsh != null && StringUtils.isNotBlank(dkjkptFxtsh.getJgdm())) {
				 //dkjkptByxjblService.extract(cn.hutool.core.date.DateUtil.formatDate(dkjkptFxtsh.getTjyf()).replaceAll("-",""));
				 QueryWrapper<DkjkptFxtsh> queryWrapper = new QueryWrapper<>();
				 queryWrapper.eq("tjyf", dkjkptFxtsh.getTjyf());
				 queryWrapper.eq("jgdm", dkjkptFxtsh.getJgdm());
				 DkjkptFxtsh dkjkptFxtsh1 = dkjkptFxtshService.getOne(queryWrapper, false);
				 if (dkjkptFxtsh1 != null && StringUtils.isNotBlank(dkjkptFxtsh1.getJgdm())) {
					 Map<String, Object> data = new HashMap<>();
					 //所属支行
					 data.put("jgdm", StringUtils.isNotBlank(dkjkptFxtsh.getJgdm()) ? sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", dkjkptFxtsh.getJgdm()) : null);
					 data.put("yyyy", cn.hutool.core.date.DateUtil.format(dkjkptFxtsh.getTjyf(),"YYYY"));
					 data.put("mm", cn.hutool.core.date.DateUtil.format(dkjkptFxtsh.getTjyf(),"MM"));
					 data.put("gxck",dkjkptFxtsh.getGxck() == null ? 0 :dkjkptFxtsh.getGxck());
					 data.put("gxdk", dkjkptFxtsh.getGxdk()== null ? 0 :dkjkptFxtsh.getGxdk());
					 data.put("bldk", dkjkptFxtsh.getBldk()== null ? 0 :dkjkptFxtsh.getBldk());
					 data.put("xzbldkbs",dkjkptFxtsh.getXzbldkbs()== null ? 0 : dkjkptFxtsh.getXzbldkbs());
					 data.put("xzbldkye",dkjkptFxtsh.getXzbldkye()== null ? 0 : dkjkptFxtsh.getXzbldkye());
					 data.put("yqdkye", dkjkptFxtsh.getYqdkye() == null ? 0 : dkjkptFxtsh.getYqdkye());
					 data.put("xzyqdkbs",dkjkptFxtsh.getXzyqdkbs() == null ? 0 : dkjkptFxtsh.getXzyqdkbs());
					 data.put("xzyqdkye", dkjkptFxtsh.getXzyqdkye()== null ? 0 : dkjkptFxtsh.getXzyqdkye());
					 data.put("dedk", dkjkptFxtsh.getDedk()== null ? 0 :dkjkptFxtsh.getDedk());
					 data.put("jkyqbs", dkjkptFxtsh.getJkyqbs() == null ? 0 :dkjkptFxtsh.getJkyqbs());
					 data.put("jktzje",dkjkptFxtsh.getJktzje()== null ? 0 : dkjkptFxtsh.getJktzje());
					 data.put("fmkyqbs", dkjkptFxtsh.getFmkyqbs()== null ? 0 :dkjkptFxtsh.getFmkyqbs());
					 data.put("fmktzje", dkjkptFxtsh.getFmktzje()== null ? 0 :dkjkptFxtsh.getFmktzje());
					 //获取新增不良贷款明细
					 QueryWrapper<DkjkptByxjbl> dkjkptByxjblQueryWrapper = new QueryWrapper<>();
					 dkjkptByxjblQueryWrapper.eq("tjyf",dkjkptFxtsh.getTjyf());
					 dkjkptByxjblQueryWrapper.eq("jgdm",dkjkptFxtsh.getJgdm());
					 List<XzbldkmxVo> xzbldkmxVo = new ArrayList<>();
					 List<DkjkptByxjbl> xzbldkmx = dkjkptByxjblService.list(dkjkptByxjblQueryWrapper);
					 Double xzblhj = 0.00;
					 if(CollUtil.isNotEmpty(xzbldkmx)){
						 int i = 0;
						 for (DkjkptByxjbl dkjkptByxjbl : xzbldkmx) {
							 XzbldkmxVo xzbldkmxVo2 = new XzbldkmxVo();
							 xzbldkmxVo2.setXh(++i);
							 BeanUtil.copyProperties(dkjkptByxjbl,xzbldkmxVo2);
							 xzblhj +=dkjkptByxjbl.getDkye().doubleValue();
							 xzbldkmxVo.add(xzbldkmxVo2);

						 }

					 }
					 data.put("xzblmx",xzbldkmxVo);
					 data.put("xzblhj", xzblhj);


					 String yymm = cn.hutool.core.date.DateUtil.format(dkjkptFxtsh.getTjyf(), "YYMM");
					 String table = "zmcbsbormbase"+yymm;

					 //逾期贷款
					 ArrayList<YqdkmxVo> yqdkmxVo = new ArrayList<>();
					 List<YqdkmxVo> yqdk = service.yqdk(table, dkjkptFxtsh.getJgdm(), DateUtil.format(dkjkptFxtsh.getTjyf(), "yyyyMMdd"));
					 Double yqdkhj = 0.00;
					 if (CollUtil.isNotEmpty(yqdk)){
						 int i = 0;
						 for (YqdkmxVo vo : yqdk) {
							 vo.setXh(++i);
							 YqdkmxVo yqdkmxVo1 = new YqdkmxVo();
							 BeanUtil.copyProperties(vo,yqdkmxVo1);
							 yqdkhj += vo.getDkye().doubleValue();
							 yqdkmxVo.add(yqdkmxVo1);
						 }

					 }
					 data.put("yqdkmx",yqdkmxVo);
					 data.put("yqdkhj", yqdkhj);


					 //大额贷款
					 List<DedkmxVo> dedkmxVo = new ArrayList<>();

					 List<DedkmxVo> dedk = service.dedk(table, dkjkptFxtsh.getJgdm(), DateUtil.format(dkjkptFxtsh.getTjyf(), "yyyyMMdd"));
					 if (CollUtil.isNotEmpty(dedk)){
					 	int i = 0;
						 for (DedkmxVo vo : dedk) {
							 DedkmxVo dedkmxVo1 = new DedkmxVo();
							 vo.setXh(++i);
							 BeanUtil.copyProperties(vo,dedkmxVo1);
							 dedkmxVo.add(dedkmxVo1);
						 }
					 }
					 data.put("dedkmx",dedkmxVo);

					 //金卡
					 List<DkjkptDjkJkVo> djkJks = new ArrayList<DkjkptDjkJkVo>();
					 QueryWrapper<DkjkptDjkJk> dkjkptDjkJkQueryWrapper = new QueryWrapper<>();
					 dkjkptDjkJkQueryWrapper.eq("tjyf",dkjkptFxtsh.getTjyf());
					 dkjkptDjkJkQueryWrapper.eq("jgdm",dkjkptFxtsh.getJgdm());
					 dkjkptDjkJkQueryWrapper.ge("yqqs", 1);
					 dkjkptDjkJkQueryWrapper.orderByAsc("xh");
					 List<DkjkptDjkJk> dkjkptDjkJks = dkjkptDjkJkService.list(dkjkptDjkJkQueryWrapper);
					 Double djked = 0.00;
					 Double djkbj = 0.00;
					 Double djklx = 0.00;
					 Double djkwyj = 0.00;
					 Double djkfx = 0.00;
					 if (CollUtil.isNotEmpty(dkjkptDjkJks)){
						 int i = 0;
						 for (DkjkptDjkJk dkjkptDjkJk : dkjkptDjkJks) {
							 DkjkptDjkJkVo djkJkVo = new DkjkptDjkJkVo();
							 BeanUtil.copyProperties(dkjkptDjkJk,djkJkVo);
							 djkJkVo.setXh(String.valueOf(++i));
							 djked +=dkjkptDjkJk.getXyed().doubleValue();
							 djkbj +=dkjkptDjkJk.getTzbj().doubleValue();
							 djklx +=dkjkptDjkJk.getLx().doubleValue();
							 djkwyj +=dkjkptDjkJk.getWyj().doubleValue();
							 djkfx +=dkjkptDjkJk.getFx().doubleValue();
							 djkJks.add(djkJkVo);
						 }
					 }
					 data.put("djkjk",djkJks);
					 data.put("djked", djked);//信用额度合计
					 data.put("djkbj", djkbj);//透支本金合计
					 data.put("djklx", djklx);//利息合计
					 data.put("djkwyj", djkwyj);//违约金合计
					 data.put("djkfx", djkfx);//罚息合计




					 //福民卡
					 List<DkjkptDjkFmkVo> dkjkptFmks = new ArrayList<DkjkptDjkFmkVo>();
					 QueryWrapper<DkjkptFmk> fmkQueryWrapper = new QueryWrapper<>();
					 fmkQueryWrapper.eq("tjyf",dkjkptFxtsh.getTjyf());
					 fmkQueryWrapper.eq("jgdm",dkjkptFxtsh.getJgdm());
					 fmkQueryWrapper.ge("yqqs", 1);
					 fmkQueryWrapper.orderByAsc("xh");
					 List<DkjkptFmk> fmk = dkjkptFmkService.list(fmkQueryWrapper);
					 Double fmed = 0.00;
					 Double fmbj = 0.00;
					 Double fmwyj = 0.00;
					 Double fmfx = 0.00;
					 if (CollUtil.isNotEmpty(fmk)){
						 int i = 0;
						 for (DkjkptFmk dkjkptFmk : fmk) {
							 DkjkptDjkFmkVo djkFmkVo = new DkjkptDjkFmkVo();
							 BeanUtil.copyProperties(dkjkptFmk,djkFmkVo);
							 djkFmkVo.setXh(String.valueOf(++i));
							 fmed +=dkjkptFmk.getXyed().doubleValue();
							 fmbj +=dkjkptFmk.getTzbj().doubleValue();
							 fmwyj +=dkjkptFmk.getWyj().doubleValue();
							 fmfx +=dkjkptFmk.getFx().doubleValue();
							 dkjkptFmks.add(djkFmkVo);
						 }
					 }
					 data.put("fmk",dkjkptFmks);
					 data.put("fmed", fmed);//信用额度合计
					 data.put("fmbj", fmbj);//透支本金合计
					 data.put("fmwyj",fmwyj);//违约金合计
					 data.put("fmfx", fmfx);//罚息合计




					 Date dateTime = cn.hutool.core.date.DateUtil.endOfMonth(dkjkptFxtsh.getTjyf());
					 data.put("date", DateUtil.getChineseDateString(dateTime));



					 String fileName = "风险提示函" + DateUtil.format(new Date(), "yyyyMMddHHmmssSSS") + ".docx";
					 String exportFilePath = exportpath + File.separator + fileName;
					 WordUtils.generateWord(data, exportFilePath, "风险提示函2.ftl");
					 FileInputStream fileInputStream = new FileInputStream(exportFilePath);
					 byte[] bys = new byte[fileInputStream.available()];
					 fileInputStream.read(bys);
					 fileInputStream.close();
					 response.reset();
					 response.setContentType("application/force-download");// 设置强制下载不打开
					 response.setHeader("Content-type", "application-download");
					 response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
					 outputStream = response.getOutputStream();
					 outputStream.write(bys);
					 outputStream.flush();
					 outputStream.close();
				 }
			 }
		 } catch (Exception e) {
//            log.info("文件下载失败" + e.getMessage());
			 e.printStackTrace();
		 } finally {
			 if (inputStream != null) {
				 try {
					 inputStream.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
			 if (outputStream != null) {
				 try {
					 outputStream.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
	 }







}
