package org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.CldkhtsjglFjxx;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglFjxxService;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.entity.DkdahtsjglVo;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.entity.Dkdahtsjglbc;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.entity.DkdahtsjglbcFjVo;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.entity.DkdahtsjglbcVo;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.service.IDkdahtsjglbcService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjglbc.verify.DkdahtsjglbcVerify;
import org.cmms.modules.xddagl.dkdagl.xddaglxlh.entity.XddaglXlh;
import org.cmms.modules.xddagl.dkdagl.xddaglxlh.service.IXddaglXlhService;
import org.cmms.modules.xddagl.dkdaglfjxx.entity.DkdaglFjxx;
import org.cmms.modules.xddagl.dkdaglfjxx.service.IDkdaglFjxxService;
import org.cmms.modules.xddagl.xtgl.xddagldkhtsjls.entity.Xddagldkhtsjls;
import org.cmms.modules.xddagl.xtgl.xddagldkhtsjls.service.IXddagldkhtsjlsService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款合同数据管理(补充)
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款合同数据管理(补充)")
@RestController
@RequestMapping("/dkdahtsjglbc/dkdahtsjglbc")
public class DkdahtsjglbcController extends JeecgController<Dkdahtsjglbc, IDkdahtsjglbcService> {
	@Autowired
	private IDkdahtsjglbcService dkdahtsjglbcService;
	@Autowired
	private DkdahtsjglbcVerify dkdahtsjglbcVerify;
	@Autowired
	private IDkdaglFjxxService dkdaglFjxxService;
	@Autowired
	private ICldkhtsjglFjxxService cldkhtsjglFjxxService;
	@Value(value = "${common.path.upload}")
	private String uploadpath;
	@Autowired
	IDictValueQuery iDictValueQuery;
	@Autowired
	private IXddaglXlhService xddaglXlhService;
	@Autowired
	private IXddagldkhtsjlsService xddagldkhtsjlsService;

	private static final String STR_FORMAT = "000000";

	 /**
	 * 分页列表查询
	 *
	 * @param dkdahtsjglbc
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-分页列表查询")
	@ApiOperation(value="贷款合同数据管理(补充)-分页列表查询", notes="贷款合同数据管理(补充)-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkdahtsjglbc dkdahtsjglbc,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkdahtsjglbc> queryWrapper = QueryGenerator.initQueryWrapper(dkdahtsjglbc, req.getParameterMap());
		Page<Dkdahtsjglbc> page = new Page<Dkdahtsjglbc>(pageNo, pageSize);
		IPage<Dkdahtsjglbc> pageList = dkdahtsjglbcService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkdahtsjglbc
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-添加")
	@ApiOperation(value="贷款合同数据管理(补充)-添加", notes="贷款合同数据管理(补充)-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkdahtsjglbc dkdahtsjglbc) {
		dkdahtsjglbcService.save(dkdahtsjglbc);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-编辑")
	@ApiOperation(value="贷款合同数据管理(补充)-编辑", notes="贷款合同数据管理(补充)-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkdahtsjglbcFjVo dkdahtsjglbcFjVo) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String xlhstr = "";

		Dkdahtsjglbc dkdahtsjglbc = new Dkdahtsjglbc();
		BeanUtils.copyProperties(dkdahtsjglbcFjVo,dkdahtsjglbc);

		JSONArray fjxxs = dkdahtsjglbcFjVo.getImgdate();
		String dhglsj = dkdahtsjglbcFjVo.getDhglsj();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		CldkhtsjglFjxx fjxx = new CldkhtsjglFjxx();
		if (fjxxs != null && fjxxs.size()>0){
			for (int i = 0; i<fjxxs.size(); i++){
				String id = UUIDGenerator.generate();
				String fjname = (String) fjxxs.getJSONObject(i).get("name");
				String fjlx = fjname.split("_")[0];
				if ("1".equals(fjlx)){
					QueryWrapper<CldkhtsjglFjxx> queryWrapper = new QueryWrapper<>();
					queryWrapper.eq("hth",dkdahtsjglbc.getHth());
					queryWrapper.eq("fjlx","1");
					List<CldkhtsjglFjxx> list = cldkhtsjglFjxxService.list(queryWrapper);
					if (list.size()>0){
						return Result.error("已存在此合同的档案信息,请勿重复上传！");
					}
				}

				String wllj = uploadpath + "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
				String fwlj =  "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
				fjxx.setWjid(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")));
				fjxx.setHth(dkdahtsjglbc.getHth());
				fjxx.setFjlx(fjlx);
				try {
					fjxx.setDhglsj(sdf.parse(dhglsj));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				fjxx.setWjlj(wllj);
				fjxx.setFwlj(fwlj);
				fjxx.setLrbz(1);
				fjxx.setLrr(sysUser.getUsername());
				fjxx.setLrsj(new Timestamp(System.currentTimeMillis()));
				cldkhtsjglFjxxService.save(fjxx);

				if (fjlx.equals("1")){
					dkdahtsjglbc.setSfscda("是");
					QueryWrapper<XddaglXlh> queryWrapper = new QueryWrapper<>();
					queryWrapper.eq("jgdm",dkdahtsjglbc.getJgdm());
					XddaglXlh xlh = xddaglXlhService.getOne(queryWrapper);
					if (xlh == null){
						xlhstr = haoAddOne("0");
						XddaglXlh xlh1 = new XddaglXlh();
						xlh1.setXlh(xlhstr);
						xlh1.setJgdm(dkdahtsjglbc.getJgdm());
						xddaglXlhService.save(xlh1);
					}else {
						xlhstr = xlh.getXlh();
						xlh.setXlh(xlhstr);
						xlh.setJgdm(dkdahtsjglbc.getJgdm());
						xddaglXlhService.update(xlh,queryWrapper);
					}
					if (dkdahtsjglbc.getHth().lastIndexOf("-")>0){
						dkdahtsjglbc.setDabh(dkdahtsjglbc.getJgdm()+"-"+dkdahtsjglbc.getHth().split("-")[2]+"-"+xlhstr);
					}else {
						dkdahtsjglbc.setDabh(dkdahtsjglbc.getJgdm()+"-"+dkdahtsjglbc.getHth().substring(10,14)+"-"+xlhstr);
					}
				}
			}
		}
		QueryWrapper<Dkdahtsjglbc> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("hth",dkdahtsjglbc.getHth());
		Dkdahtsjglbc dkdahtsjglbc1 = dkdahtsjglbcService.getOne(queryWrapper);
		if (StringUtils.isNotEmpty(dkdahtsjglbc.getSfscda())) {
			dkdahtsjglbc1.setSfscda(dkdahtsjglbc.getSfscda());
		}
		if(StringUtils.isNotEmpty(dkdahtsjglbc.getDabh())) {
			dkdahtsjglbc1.setDabh(dkdahtsjglbc.getDabh());
		}
		dkdahtsjglbc1.setDkzrr(dkdahtsjglbc.getDkzrr());
		dkdahtsjglbc1.setLxdz(dkdahtsjglbc.getLxdz());
		dkdahtsjglbc1.setLxdh(dkdahtsjglbc.getLxdh());
		dkdahtsjglbc1.setLrbz(2);
		dkdahtsjglbc1.setLrsj(new Date());
		dkdahtsjglbc1.setLrr(sysUser.getUsername());
		dkdahtsjglbcService.update(dkdahtsjglbc1,queryWrapper);
		return Result.ok("操作成功!");
	}


	 public static String haoAddOne(String liuShuiHao){
		 Integer intHao = Integer.parseInt(liuShuiHao);
		 intHao++;
		 DecimalFormat df = new DecimalFormat(STR_FORMAT);
		 return df.format(intHao);
	 }

	 /**
	  * 移交
	  */
	 @PostMapping("/preservation")
	 public Result<?> preservation(@RequestBody JSONObject jsonObject){
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String dkzrr = jsonObject.getString("dkzrr");
		 Xddagldkhtsjls form = new Xddagldkhtsjls();
		 List<Dkdahtsjglbc> jsonList = (List<Dkdahtsjglbc>)jsonObject.get("array");
		 ObjectMapper objectMapper = new ObjectMapper();
		 List<Dkdahtsjglbc> list = objectMapper.convertValue(jsonList, new TypeReference<List<Dkdahtsjglbc>>() {
		 });
		 QueryWrapper queryWrapper=null;
		 Dkdahtsjglbc dkdahtsjglbc=null;
		 for (Dkdahtsjglbc l:list){
			 queryWrapper=new QueryWrapper();
			 dkdahtsjglbc=new Dkdahtsjglbc();

			 form.setJgdm(l.getJgdm());
			 form.setZjhm(l.getZjhm());
			 form.setKhmc(l.getKhmc());
			 form.setKhlx(l.getKhlx());
			 form.setHth(l.getHth());
			 form.setYwbh(l.getYwbh());
			 form.setDkzrr(l.getDkzrr());
			 form.setYjhzrr(dkzrr);
			 form.setCzr(sysUser.getUsername());
			 form.setYjrq(new Date());

			 queryWrapper.eq("hth",l.getHth());
			 dkdahtsjglbc.setDkzrr(dkzrr);
			 dkdahtsjglbcService.update(dkdahtsjglbc,queryWrapper);
			 xddagldkhtsjlsService.save(form);
		 }
		 return Result.ok("移交成功！");
	 }

	 /**
	  * 判断生成二维码
	  * @param dkdahtsjglbc
	  * @return
	  */
	 @PutMapping(value = "scewmpd")
	 public Result<?> scewmpd(@RequestBody Dkdahtsjglbc dkdahtsjglbc){
	 	if (dkdahtsjglbc.getJgdm() !=null && dkdahtsjglbc.getKhmc() != null &&dkdahtsjglbc.getZjhm() != null &&
				dkdahtsjglbc.getHth() != null && dkdahtsjglbc.getDabh() != null){
			return Result.ok("生成成功");
		}else {
			return Result.error("生成失败！");
		}
	 }

	 /**
	  * 生成二维码
	  * @param form
	  * @return
	  */
	 @AutoLog(value = "贷款合同数据管理(补充)-生成二维码")
	 @ApiOperation(value="贷款合同数据管理(补充)-生成二维码", notes="贷款合同数据管理(补充)-生成二维码")
	 @PutMapping(value = "/scewm")
	 public Result<?> scewm(@RequestBody Dkdahtsjglbc form){

		 String jgmc=form.getJgdm();
		 String savetpPath = uploadpath+"/xddazl/ewm/"+form.getKhmc()+"_"+form.getZjhm()+"_"+form.getHth()+"_tp.png";
		 String savePath = uploadpath+"/xddazl/ewm/"+form.getKhmc()+"_"+form.getZjhm()+"_"+form.getHth()+".png";

		 String savetpPathYl = "/xddazl/ewm/"+form.getKhmc()+"_"+form.getZjhm()+"_"+form.getHth()+"_tp.png";
		 String savePathYl = "/xddazl/ewm/"+form.getKhmc()+"_"+form.getZjhm()+"_"+form.getHth()+".png";

		 //1.机构号2.客户姓名   3.身份证  4.合同号   5.档案编号
		 try {
			 generateQRCodeImage(jgmc+"\n"+form.getKhmc()+"\n"+form.getZjhm() +"\n"+form.getHth()+"\n"+form.getDabh(), 200, 200, savePath);
		 } catch (WriterException e) {
			 System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
		 } catch (IOException e) {
			 System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
		 }

		 BufferedImage bi = new BufferedImage(250,200,BufferedImage.TYPE_INT_RGB);//INT精确度达到一定,RGB三原色，高度70,宽度150
//得到它的绘制环境(这张图片的笔)
		 Graphics2D g2 = (Graphics2D) bi.getGraphics();

		 g2.fillRect(0,0,250,200);//填充一个矩形 左上角坐标(0,0),宽70,高150;填充整张图片
//设置颜色
		 g2.setColor(Color.WHITE);
		 g2.fillRect(0,0,250,200);//填充整张图片(其实就是设置背景颜色)

		 g2.setColor(Color.BLACK);
		 g2.setFont(new java.awt.Font("宋体", com.itextpdf.text.Font.BOLD,16)); //设置字体:字体、字号、大小
		 g2.setColor(Color.BLACK);//设置背景颜色
		 g2.drawString(jgmc,10,50); //向图片上写字符串
		 g2.drawString(form.getKhmc(),10,70); //向图片上写字符串
		 g2.drawString(form.getZjhm(),10,90); //向图片上写字符串
		 g2.drawString(form.getHth(),10,110); //向图片上写字符串
		 g2.drawString(form.getDabh(),10,130); //向图片上写字符串
		 ImageIO.setUseCache(false);
		 try {
			 ImageIO.write(bi,"PNG",new FileOutputStream(savetpPath));//保存图片 JPEG表示保存格式
		 } catch (IOException e) {
			 e.printStackTrace();
			 return Result.error("生成失败！");
		 }

		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("savetpPath",savetpPathYl);
		 jsonObject.put("savePath",savePathYl);
		 return Result.ok(jsonObject);
	 }
	 private  void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
		 String filepath= uploadpath+"/xddazl/ewm";
		 File file = new File(filepath);
		 if(!file.exists()){
			 file.mkdirs();
		 }
		 QRCodeWriter qrCodeWriter = new QRCodeWriter();
		 Map<EncodeHintType, Object> hints = new HashMap<>();
		 hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		 hints.put(EncodeHintType.MARGIN,1);   //先设置margin为1
		 BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height,hints);
		 Path path = FileSystems.getDefault().getPath(filePath);
		 MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	 }

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-通过id删除")
	@ApiOperation(value="贷款合同数据管理(补充)-通过id删除", notes="贷款合同数据管理(补充)-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkdahtsjglbcService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-批量删除")
	@ApiOperation(value="贷款合同数据管理(补充)-批量删除", notes="贷款合同数据管理(补充)-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkdahtsjglbcService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款合同数据管理(补充)-通过id查询")
	@ApiOperation(value="贷款合同数据管理(补充)-通过id查询", notes="贷款合同数据管理(补充)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkdahtsjglbc dkdahtsjglbc = dkdahtsjglbcService.getById(id);
		return Result.ok(dkdahtsjglbc);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkdahtsjglbc
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkdahtsjglbc dkdahtsjglbc) {
      return super.exportXls(request, dkdahtsjglbc, Dkdahtsjglbc.class, "贷款合同数据管理(补充)");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(DkdahtsjglbcVo.class, "贷款合同数据管理(补充)导入模板");
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
      return super.importExcelByTemplate(jsonObject,request, response, Dkdahtsjglbc.class,DkdahtsjglbcVo.class,dkdahtsjglbcVerify);
  }

}
