package org.cmms.modules.xddagl.dqdagl.dqdazlgl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.shiro.SecurityUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.entity.Dkdahtsjgl;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.service.IDkdahtsjglService;
import org.cmms.modules.xddagl.dkdagl.xddaglxlh.entity.XddaglXlh;
import org.cmms.modules.xddagl.dkdagl.xddaglxlh.service.IXddaglXlhService;
import org.cmms.modules.xddagl.dkdaglfjxx.entity.DkdaglFjxx;
import org.cmms.modules.xddagl.dkdaglfjxx.service.IDkdaglFjxxService;
import org.cmms.modules.xddagl.dqdagl.dqdazlgl.entity.Dqdazlgl;
import org.cmms.modules.xddagl.dqdagl.dqdazlgl.entity.DqdazlglFjxx;
import org.cmms.modules.xddagl.dqdagl.dqdazlgl.entity.DqdazlglVo;
import org.cmms.modules.xddagl.dqdagl.dqdazlgl.service.IDqdazlglFjxxService;
import org.cmms.modules.xddagl.dqdagl.dqdazlgl.service.IDqdazlglService;
import org.cmms.modules.xddagl.xtgl.xddaglcsgl.entity.Xddaglcsgl;
import org.cmms.modules.xddagl.xtgl.xddaglcsgl.service.IXddaglcsglService;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.entity.Dksjjktz;
import org.cmms.modules.xddaglxt.dksjgl.dksjjktz.service.IDksjjktzService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.controller.XfdkhtsjglController.haoAddOne;

/**
 * @Description: 贷前档案资料管理
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷前档案资料管理")
@RestController
@RequestMapping("/dqdazlgl/dqdazlgl")
public class  DqdazlglController extends JeecgController<Dqdazlgl, IDqdazlglService> {
	@Autowired
	private IDqdazlglService dqdazlglService;
	 @Autowired
	 private IDksjjktzService dksjjktzService;
	 @Autowired
	 private IDkdahtsjglService dkdahtsjglService;
	 @Autowired
	 private IXddaglXlhService xddaglXlhService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	@Autowired
	 private IXddaglcsglService iXddaglcsglService;
	 @Autowired
	 private IDqdazlglFjxxService dqdazlglFjxxService;

	 @Autowired
	 IDictValueQuery iDictValueQuery;

	private static final String STR_FORMAT = "000000";
	/**
	 * 分页列表查询
	 *
	 * @param dqdazlgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理-分页列表查询")
	@ApiOperation(value="贷前档案资料管理-分页列表查询", notes="贷前档案资料管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dqdazlgl dqdazlgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dqdazlgl> queryWrapper = QueryGenerator.initQueryWrapper(dqdazlgl, req.getParameterMap());
		Page<Dqdazlgl> page = new Page<Dqdazlgl>(pageNo, pageSize);
		IPage<Dqdazlgl> pageList = dqdazlglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	/**
	 * 添加
	 *
	 * @param dqdazlgl
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理-添加")
	@ApiOperation(value="贷前档案资料管理-添加", notes="贷前档案资料管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dqdazlgl dqdazlgl) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		dqdazlgl.setId(UUID.randomUUID().toString().replace("-",""));
		dqdazlgl.setLrbz(1);
		dqdazlgl.setQrzt("2");
		dqdazlgl.setPpzt("2");
		dqdazlgl.setLrr(sysUser.getUsername());
		dqdazlgl.setLrsj(new Date());
		dqdazlglService.save(dqdazlgl);
		return Result.ok("注册成功！");
	}

	/**
	 * 编辑
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理-编辑")
	@ApiOperation(value="贷前档案资料管理-编辑", notes="贷前档案资料管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DqdazlglVo dqdazlglVo) {
       	long sjc=System.currentTimeMillis();
		Dqdazlgl dqdazlgl=new Dqdazlgl();
		BeanUtils.copyProperties(dqdazlglVo,dqdazlgl);
		JSONArray fjxxs= dqdazlglVo.getImgdate();
		DqdazlglFjxx fjgl = new DqdazlglFjxx();
		ArrayList<String> list4=new ArrayList();
		ArrayList<String> list5=new ArrayList();
		ArrayList<String> list7=new ArrayList();

		//把之前已经存在的附件查询出来
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("hth",dqdazlglVo.getId());
		//queryWrapper.ne("fjlx","8");
		List<DqdazlglFjxx> list = dqdazlglFjxxService.list(queryWrapper);
		for(DqdazlglFjxx dqdazlglFjxx: list){
			if(dqdazlglFjxx.getFjlx().equals("6")){
				list4.add(dqdazlglFjxx.getWjlj());
			}else if(dqdazlglFjxx.getFjlx().equals("5")){
				list5.add(dqdazlglFjxx.getWjlj());
			}else if(dqdazlglFjxx.getFjlx().equals("7")){
				list7.add(dqdazlglFjxx.getWjlj());
			}
		}

		//按类型把本次上传的附件追加在之前存在的文件上
		for (int i = 0; i < fjxxs.size(); i++) {
			String id = UUIDGenerator.generate();
			String fjname= (String) fjxxs.getJSONObject(i).get("name");
			if(fjname!=null&&!fjname.equals("")){
				String fjlx=fjname.split("_")[0];
				String wllj = uploadpath+ File.separator +fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
				if(fjlx.equals("6")){//贷前资料
					list4.add(wllj);
				}else if(fjlx.equals("5")){//其他证明资料
					list5.add(wllj);
				}else if(fjlx.equals("7")){//贷款合同资料
					list7.add(wllj);
				}else{
					continue;
				}
			}
		}


		//对合并后的附件进行更新或者保存
		String savePath=uploadpath+"/" +"dqdazl"+"/"+dqdazlglVo.getKhxm()+"_"+dqdazlglVo.getZjhm()+"/";
		if(list4!=null&&list4.size()>0){
			String fwljPath="/" +"dqdazl"+"/"+dqdazlglVo.getKhxm()+"_"+dqdazlglVo.getZjhm()+"/"+"贷前资料汇总"+"_"+sjc+".pdf";
			String hbhfjlj4=hbfj(list4,savePath,"贷前资料汇总"+"_"+sjc);
			Boolean aBoolean4 = zlxxXg(hbhfjlj4, "6", dqdazlglVo.getId(),fwljPath);

		}
		if(list5!=null&&list5.size()>0){
			String fwljPath="/" +"dqdazl"+"/"+dqdazlglVo.getKhxm()+"_"+dqdazlglVo.getZjhm()+"/"+"其他证明资料汇总"+"_"+sjc+".pdf";
			String hbhfjlj5=hbfj(list5,savePath,"其他证明资料汇总"+"_"+sjc);
			Boolean aBoolean5= zlxxXg(hbhfjlj5, "5", dqdazlglVo.getId(),fwljPath);
		}
		if(list7!=null&&list7.size()>0){
			String fwljPath="/" +"dqdazl"+"/"+dqdazlglVo.getKhxm()+"_"+dqdazlglVo.getZjhm()+"/"+"贷款合同资料汇总"+"_"+sjc+".pdf";
			String hbhfjlj7=hbfj(list7,savePath,"贷款合同资料汇总"+"_"+sjc);
			Boolean aBoolean7 = zlxxXg(hbhfjlj7, "7", dqdazlglVo.getId(),fwljPath);
		}

		//汇总合并
		//查询出除汇总附件外的所有类型附件
		QueryWrapper queryWrapperHZ=new QueryWrapper();
		queryWrapperHZ.eq("hth",dqdazlglVo.getId());
		queryWrapperHZ.ne("fjlx","8");
		queryWrapperHZ.orderByAsc("fjlx");
		List<DqdazlglFjxx> listdazl = dqdazlglFjxxService.list(queryWrapperHZ);
		ArrayList<String> listDhzfj =new ArrayList();
		for(DqdazlglFjxx fjxx :listdazl){
			listDhzfj.add(fjxx.getWjlj());
		}
		if (listDhzfj.size()>0){
			//把除汇总附件以为的附件进行合并汇总。
			String hbfjPath = hbfj(listDhzfj,savePath,"全部资料汇总"+"_"+sjc);
			//更新汇总附件数据记录
			String fwljPath="/" +"dqdazl"+"/"+dqdazlglVo.getKhxm()+"_"+dqdazlglVo.getZjhm()+"/"+"全部资料汇总"+"_"+sjc+".pdf";
			Boolean aBoolean8 = zlxxXg(hbfjPath, "8", dqdazlglVo.getId(),fwljPath);
		}



		return Result.ok("编辑成功!");
	}




	 public  Boolean zlxxXg(String wllj,String fjlx,String id,String fwlj){
		try {
			LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			QueryWrapper queryWrapper4=new QueryWrapper();
			queryWrapper4.eq("hth",id);
			queryWrapper4.eq("fjlx",fjlx);
			DqdazlglFjxx wjxxhb = dqdazlglFjxxService.getOne(queryWrapper4);
			if(wjxxhb!=null){
				wjxxhb.setWjlj(wllj);
				wjxxhb.setFwlj(fwlj);
				dqdazlglFjxxService.update(wjxxhb,queryWrapper4);
			}else{
				DqdazlglFjxx fjxx =new DqdazlglFjxx();
				fjxx.setWjid(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")));
				fjxx.setHth(id);
				fjxx.setFwlj(fwlj);
				fjxx.setFjlx(fjlx);
				fjxx.setWjlj(wllj);
				//wjxxhb.setFwlj("/UPLOAD/"+filepath+fwljStr);
				fjxx.setLrbz(1);
				fjxx.setLrr(sysUser.getUsername());
				fjxx.setLrsj(new Timestamp(System.currentTimeMillis()));
				dqdazlglFjxxService.save(fjxx);
			}
			return true;
		}catch (Exception e){
			log.error("贷前资料附件更新失败，"+e.getMessage());
			return false;

		}

	 }



	 public  String hbfj(ArrayList<String> pathList,String savePath,String fileName){
		 String wjlj=savePath+fileName+".pdf";
		 ArrayList<String> listPdf =new ArrayList();//合并多个Pdf为一个pdf
		 ArrayList<String> deleteImages =new ArrayList();//转换后需要删除的图片和图片pdf
		 ArrayList<String> deletePdf =new ArrayList();//转换后需要删除的图片和图片pdf

		 if (true){

		 }
		 for (String path:pathList){
			 if (path.toLowerCase().endsWith("jpg") || path.toLowerCase().endsWith("png")){
				 try {
					 String ImagetoPdfwjlj= PdfUtils.ImageOnegeneratePdfFile(path,savePath,"图片转PDF后文件");
					 deleteImages.add(ImagetoPdfwjlj);
					 deleteImages.add(path);
					 listPdf.add(ImagetoPdfwjlj);
				 } catch (Exception e) {
					 e.printStackTrace();
				 }
			 }else if(path.toLowerCase().endsWith("pdf")){
				 deletePdf.add(path);
				 listPdf.add(path);
			 }
		 }
		 //1.合并pdf
		 File file = new File(savePath);
		 if (!file.exists()) {
			 file.mkdirs();
		 }
		 FileOutputStream fileOutputStream=null;
		 Document document=null;
		 PdfCopy copy=null;
		 PdfReader reader=null;
		 PdfReader pdfReader=null;
		 try {
			 File outFile = new File(wjlj);
			 fileOutputStream = new FileOutputStream(outFile);
			 pdfReader = new PdfReader(listPdf.get(0));
			 document = new Document(pdfReader.getPageSize(1));
			 copy = new PdfCopy(document, fileOutputStream);
			 document.open();
			 for (int i = 0; i < listPdf.size(); i++) {
				 reader = new PdfReader(listPdf.get(i));
				 int n = reader.getNumberOfPages();
				 for (int j = 1; j <= n; j++) {
					 document.newPage();
					 PdfImportedPage page = copy.getImportedPage(reader, j);
					 copy.addPage(page);
				 }
				 reader.close();
			 }
		 } catch (IOException e) {
			 e.printStackTrace();
		 } catch (DocumentException e) {
			 e.printStackTrace();
		 }finally {

			 if(document!=null){
				 document.close();
			 }
			 if(copy!=null){
				 copy.close();
			 }
			 if(pdfReader!=null){
				 pdfReader.close();
			 }
			 if(reader!=null){
				 reader.close();
			 }
			 if(fileOutputStream!=null){
				 try {
					 fileOutputStream.flush();
					 fileOutputStream.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
			 for(String listPdf1: deleteImages){
				 File file1 =new File(listPdf1);
				 if (file1.exists()) {
					 file1.delete();
				 }
			 }
			 for(String listPdf2: deletePdf){
				 File file2 =new File(listPdf2);
				 if (file2.exists()&&fileName.indexOf("全部资料汇总")<0) {
					 file2.delete();
				 }
			 }

		 }

		 return wjlj;
	 }








	 @AutoLog(value = "贷前档案资料管理-编辑")
	 @ApiOperation(value="贷前档案资料管理-编辑", notes="贷前档案资料管理-编辑")
	 @PutMapping(value = "/checkUpload")
	 public Result<?> checkUpload(@RequestBody Dqdazlgl form) {
		 if(form.getQrzt().equals("1")){
			 return Result.error("上传资料已确认，不能在上传！");
		 }
		 if (form.getHth()!=null){
		  	 QueryWrapper queryWrapper=new QueryWrapper();
			 queryWrapper.eq("hth",form.getHth());
			 queryWrapper.eq("shzt","1");
			 List<Dqdazlgl> list = dksjjktzService.list(queryWrapper);
			 if (list!=null&&list.size()>0){
				 return Result.error("账号已审核成功不能修改!");
			 }
		 }
		 return Result.ok("校验成功!");
	 }


	 /**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理-通过id删除")
	@ApiOperation(value="贷前档案资料管理-通过id删除", notes="贷前档案资料管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dqdazlglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理-批量删除")
	@ApiOperation(value="贷前档案资料管理-批量删除", notes="贷前档案资料管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dqdazlglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷前档案资料管理-通过id查询")
	@ApiOperation(value="贷前档案资料管理-通过id查询", notes="贷前档案资料管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dqdazlgl dqdazlgl = dqdazlglService.getById(id);
		return Result.ok(dqdazlgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dqdazlgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dqdazlgl dqdazlgl) {
      return super.exportXls(request, dqdazlgl, Dqdazlgl.class, "贷前档案资料管理");
  }
	 /**
	  * 确认
	  * @param dqdazlgl
	  * @param dqdazlgl
	  */
	 @RequestMapping(value = "/confirm")
	 public Result<?> confirm(@RequestBody Dqdazlgl dqdazlgl) {
			if ("1".equals(dqdazlgl.getQrzt())){
				return Result.error("已确认，请勿重复确认！");
			}
			QueryWrapper queryWrapper=new QueryWrapper();
		 queryWrapper.eq("HTH",dqdazlgl.getId());
		 List<DqdazlglFjxx> list = dqdazlglFjxxService.list(queryWrapper);
		 TreeSet set =new TreeSet();
		 for (DqdazlglFjxx fjxx:list){
			 if ("6".equals(fjxx.getFjlx())){
				 set.add("6");
			 }else if ("7".equals(fjxx.getFjlx())){
				 set.add("7");
			 }
		 }
		 if (set.size()!=2){
			 return Result.error("资料未上传齐全,不能确认!");
		 }
		 dqdazlgl.setQrzt("1");
		 boolean update = dqdazlglService.updateById(dqdazlgl);
		 System.out.println(update);
		 return Result.ok("确认成功");
	 }
	 /**
	  * 匹配
	  * @param dqdazlgl
	  * @param dqdazlgl
	  */
	 @RequestMapping(value = "/match")
	 public Result<?> match(@RequestBody Dqdazlgl dqdazlgl) {
		 QueryWrapper queryWrapper=new QueryWrapper();
		 queryWrapper.eq("csbm","00001");
		 Xddaglcsgl xddaglcsgl = iXddaglcsglService.getOne(queryWrapper);
		 String qyrq = xddaglcsgl.getCsz();
		 queryWrapper=new QueryWrapper();
		 queryWrapper.eq("HTH",dqdazlgl.getId());
		 List<DqdazlglFjxx> list = dqdazlglFjxxService.list(queryWrapper);
		 TreeSet set =new TreeSet();
		 for (DqdazlglFjxx fjxx:list){
			 if ("6".equals(fjxx.getFjlx())){
				 set.add("6");
			 }else if ("7".equals(fjxx.getFjlx())){
				 set.add("7");
			 }
		 }
		 if (set.size()!=2){
			 return Result.error("资料未上传齐全,不能匹配!");
		 }
		 queryWrapper=new QueryWrapper();
		 queryWrapper.eq("zjhm",dqdazlgl.getZjhm());
		 queryWrapper.eq("jgdm",dqdazlgl.getYwjg());
		 queryWrapper.last("and qyrq >=to_date('"+qyrq+"','yyyy-mm-dd')");
		 List<Dkdahtsjgl> dkhtsjxxList = dkdahtsjglService.list(queryWrapper);
		 return Result.ok(dkhtsjxxList);
	 }
	 /**
	  * 匹配确认
	  */
	 @RequestMapping(value = "/matchSave")
	 public Result<?> matchSave(@RequestBody JSONObject jsonObject) {
			String id=jsonObject.getString("id");
			String xlhstr="";
		 	String hth=jsonObject.getString("hth");
		 if (StringUtils.isEmpty(hth)) {
		 	return Result.error("请选择合同！");
		 }
		 	QueryWrapper queryWrapper=new QueryWrapper();
		 	queryWrapper.eq("hth",hth);
		 Dkdahtsjgl dkhtsjxx = dkdahtsjglService.getOne(queryWrapper);
		  Date qyrq=dkhtsjxx.getQyrq();
		  dkhtsjxx.setSfscda("是");
		 queryWrapper=new QueryWrapper();
		 queryWrapper.eq("jgdm",dkhtsjxx.getJgdm());
		 XddaglXlh xlh = xddaglXlhService.getOne(queryWrapper);
		  if (xlh==null){
			  xlh = new XddaglXlh();
			  xlhstr=haoAddOne("0");
			  xlh.setJgdm(dkhtsjxx.getJgdm());
			  xlh.setXlh(xlhstr);
			  xddaglXlhService.save(xlh);
		  }else {
			  xlhstr=haoAddOne(xlh.getXlh());
			  xlh.setXlh(xlhstr);
			  xddaglXlhService.update(xlh,queryWrapper);
		  }
			if (dkhtsjxx.getHth().lastIndexOf("-")>0){
				dkhtsjxx.setDabh(dkhtsjxx.getJgdm()+"-"+ dkhtsjxx.getHth().split("-")[2]+"-"+xlhstr);
			}else {
				dkhtsjxx.setDabh(dkhtsjxx.getJgdm()+"-"+dkhtsjxx.getHth().substring(10,14)+"-"+xlhstr);
			}
//		 String  updatedkhtsjxx = "update " + Xddagl_dkhtsjxx._TABLE_NAME + " set dabh=?,sfscda=? where hth=?";
//		 executeUpdate(updatedkhtsjxx, new Object[]{form.getDabh(),"是",form.getHth()});
			queryWrapper=new QueryWrapper();
			queryWrapper.eq("hth",dkhtsjxx.getHth());
		 	dkhtsjxx.setSfscda("是");
		 	dkdahtsjglService.update(dkhtsjxx,queryWrapper);
//	 	String  updateSql = "update " + Xddagl_dqzlgl._TABLE_NAME + " set dabh=?,hth=?,htrq=?,ywjg=?,ppzt=? where id=?";
//		 executeUpdate(updateSql, new Object[]{form.getDabh(),form.getHth(),qyrq,form.getJgdm(),"1",id});
		 Dqdazlgl dqdazlgl=new Dqdazlgl();
		 queryWrapper=new QueryWrapper();
		 queryWrapper.eq("id",id);
		 dqdazlgl.setDabh(dkhtsjxx.getDabh());
		 dqdazlgl.setHth(dkhtsjxx.getHth());
		 dqdazlgl.setHtrq(qyrq);
		 dqdazlgl.setYwjg(dkhtsjxx.getJgdm());
		 dqdazlgl.setPpzt("1");
		 dqdazlglService.update(dqdazlgl,queryWrapper);
		 return Result.ok("匹配成功");
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
      return super.importExcel(request, response, Dqdazlgl.class);
  }

	 public static void main(String[] args) {
		 System.out.println("PDF".equals("pdf"));
	 }
	public static String haoAddOne(String liuShuiHao){
		Integer intHao = Integer.parseInt(liuShuiHao);
		intHao++;
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		return df.format(intHao);
	}
	/**
	 * 查询附件
	 */
	@RequestMapping(value = "/queryFjxx",method = RequestMethod.GET)
	public Result<?> queryFjxx(@RequestParam(name = "hth",required = true)String hth){
		QueryWrapper<DqdazlglFjxx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("hth",hth);
		List<DqdazlglFjxx> list = dqdazlglFjxxService.list(queryWrapper);
		return Result.ok(list);
	}
}
