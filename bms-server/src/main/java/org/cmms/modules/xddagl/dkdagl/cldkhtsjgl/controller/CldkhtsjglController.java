package org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.Cldkhtsjgl;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.CldkhtsjglFjxx;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.CldkhtsjglVO;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglFjxxService;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglService;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.verify.CldkhtsjglImportVerify;
import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.entity.Dkdahtsjgl;
import org.cmms.modules.xddagl.dkdagl.xddaglxlh.entity.XddaglXlh;
import org.cmms.modules.xddagl.dkdagl.xddaglxlh.service.IXddaglXlhService;
import org.cmms.modules.xddagl.xtgl.xddaglcsgl.service.IXddaglcsglService;
import org.cmms.modules.xddagl.xtgl.xddagldkhtsjls.entity.Xddagldkhtsjls;
import org.cmms.modules.xddagl.xtgl.xddagldkhtsjls.service.IXddagldkhtsjlsService;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 存量贷款合同数据管理
 * @Author: jeecg-boot
 * @Date: 2022-01-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "存量贷款合同数据管理")
@RestController
@RequestMapping("/cldkhtsjgl/cldkhtsjgl")
public class CldkhtsjglController extends JeecgController<Cldkhtsjgl, ICldkhtsjglService> {
    @Autowired
    private ICldkhtsjglService cldkhtsjglService;
    @Autowired
    private CldkhtsjglImportVerify cldkhtsjglImportVerify;
    @Autowired
    private ICldkhtsjglFjxxService cldkhtsjglFjxxService;
    @Autowired
    private IXddagldkhtsjlsService xddagldkhtsjlsService;
    @Value(value = "${common.path.upload}")
    private String uploadpath;
    @Autowired
    IDictValueQuery iDictValueQuery;
    @Autowired
    private IXddaglXlhService xddaglXlhService;
    @Autowired
    private IXddaglcsglService iXddaglcsglService;
    private static final String STR_FORMAT = "000000";

    /**
     * 分页列表查询
     *
     * @param cldkhtsjgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "存量贷款合同数据管理-分页列表查询")
    @ApiOperation(value = "存量贷款合同数据管理-分页列表查询", notes = "存量贷款合同数据管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Cldkhtsjgl cldkhtsjgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("csbm","00001");
        String qyrq = iXddaglcsglService.getOne(wrapper).getCsz();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date = sdf.parse(qyrq);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        QueryWrapper<Cldkhtsjgl> queryWrapper = QueryGenerator.initQueryWrapper(cldkhtsjgl, req.getParameterMap());
        queryWrapper.lt("qyrq", date);
        Page<Cldkhtsjgl> page = new Page<Cldkhtsjgl>(pageNo, pageSize);
        IPage<Cldkhtsjgl> pageList = cldkhtsjglService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    /**
     * 移交
     */
    @GetMapping("/queryByDkzrr")
    public Result<?> queryByDkzrr(@RequestParam(name = "dkzrr", required = true) String dkzrr) {
        QueryWrapper<Cldkhtsjgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dkzrr", dkzrr);
        List<Cldkhtsjgl> list = cldkhtsjglService.list(queryWrapper);
        return Result.ok(list);
    }

    @PostMapping("/preservation")
    public Result<?> preservation(@RequestBody JSONObject jsonObject){
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String dkzrr = jsonObject.getString("dkzrr");
        Xddagldkhtsjls form = new Xddagldkhtsjls();
        List<Cldkhtsjgl> jsonList = (List<Cldkhtsjgl>)jsonObject.get("array");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Cldkhtsjgl> list = objectMapper.convertValue(jsonList, new TypeReference<List<Cldkhtsjgl>>() {
        });
        QueryWrapper queryWrapper=null;
        Cldkhtsjgl cldkhtsjgl=null;
        for (Cldkhtsjgl l:list){
            queryWrapper=new QueryWrapper();
            cldkhtsjgl=new Cldkhtsjgl();

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
            cldkhtsjgl.setDkzrr(dkzrr);
            cldkhtsjglService.update(cldkhtsjgl,queryWrapper);
            xddagldkhtsjlsService.save(form);
        }
        return Result.ok("移交成功！");
    }
    /**
     * 生成二维码判断字段是否为空
     */
    @PutMapping(value = "/scewmpd")
    public Result<?> scewmpd(@RequestBody Cldkhtsjgl cldkhtsjgl) {
        if (cldkhtsjgl.getDabh() != null && cldkhtsjgl.getJgdm() != null &&
                cldkhtsjgl.getDkpz() != null && cldkhtsjgl.getKhmc() != null &&
                cldkhtsjgl.getZjhm() != null && cldkhtsjgl.getHth() != null){
            return Result.ok("生成成功！");
        }else {
            return Result.error("二维码生成失败！");
        }
    }
    @AutoLog(value = "生成二维码")
    @ApiOperation(value = "生成二维码-编辑",notes = "生成二维码-编辑")
    @PutMapping(value = "/scewm")
    public Result<?> scewm(@RequestBody CldkhtsjglVO cldkhtsjglVO) {
        Cldkhtsjgl form = new Cldkhtsjgl();
        BeanUtils.copyProperties(cldkhtsjglVO,form);

        String dkpz=form.getDkpz();
        String jgmc=form.getJgdm();
        String savetpPath = uploadpath+"/xddazl/ewm/"+form.getKhmc()+"_"+form.getZjhm()+"_"+form.getHth()+"_tp.png";
        String savePath = uploadpath+"/xddazl/ewm/"+form.getKhmc()+"_"+form.getZjhm()+"_"+form.getHth()+".png";

        String savetpPathYl = "/xddazl/ewm/"+form.getKhmc()+"_"+form.getZjhm()+"_"+form.getHth()+"_tp.png";
        String savePathYl = "/xddazl/ewm/"+form.getKhmc()+"_"+form.getZjhm()+"_"+form.getHth()+".png";

        //1.机构号2.客户姓名   3.身份证  4.合同号   5.信贷产品  6.档案编号
        try {
            generateQRCodeImage(jgmc+"\n"+form.getKhmc()+"\n"+form.getZjhm() +"\n"+form.getHth()+"\n"+dkpz+"\n"+form.getDabh(), 200, 200, savePath);
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
        g2.drawString(dkpz,10,130); //向图片上写字符串
        g2.drawString(form.getDabh(),10,150); //向图片上写字符串
        ImageIO.setUseCache(false);
        try {
            ImageIO.write(bi,"PNG",new FileOutputStream(savetpPath));//保存图片 JPEG表示保存格式
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject=new JSONObject();
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
     * 提取
     */
    @RequestMapping(value = "/init")
    public Result<?> init() {
        Result result = new Result<>();
        try {
            cldkhtsjglService.pCldkhtsjgl();
            result.setSuccess(true);
            return result;
        } catch (Throwable e) {
            System.out.println(e);
            log.error("提取失败", e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 添加
     *
     * @param cldkhtsjgl
     * @return
     */
    @AutoLog(value = "存量贷款合同数据管理-添加")
    @ApiOperation(value = "存量贷款合同数据管理-添加", notes = "存量贷款合同数据管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Cldkhtsjgl cldkhtsjgl) {
        cldkhtsjglService.save(cldkhtsjgl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param
     * @return
     */
    @AutoLog(value = "存量贷款合同数据管理-编辑")
    @ApiOperation(value = "存量贷款合同数据管理-编辑", notes = "存量贷款合同数据管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CldkhtsjglVO cldkhtsjglvo) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String xlhstr = "";
        long sjc = System.currentTimeMillis();
        Cldkhtsjgl cldkhtsjgl = new Cldkhtsjgl();
        BeanUtils.copyProperties(cldkhtsjglvo, cldkhtsjgl);
        JSONArray fjxxs = cldkhtsjglvo.getImgdate();
        String dhglsj = cldkhtsjglvo.getDhglsj();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CldkhtsjglFjxx fjxx = new CldkhtsjglFjxx();
        if (fjxxs != null && fjxxs.size() > 0) {
            for (int i = 0; i < fjxxs.size(); i++) {
                String id = UUIDGenerator.generate();
                String fjname = (String) fjxxs.getJSONObject(i).get("name");
                String fjlx = fjname.split("_")[0];
                if ("1".equals(fjlx)) {
                    QueryWrapper<CldkhtsjglFjxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("hth", cldkhtsjgl.getHth());
                    queryWrapper.eq("fjlx", "1");
                    List<CldkhtsjglFjxx> list = cldkhtsjglFjxxService.list(queryWrapper);
                    if (list.size() > 0) {
                        return Result.error("已存在此合同的档案信息,请勿重复上传！");
                    }
                }
                String wllj = uploadpath + "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
                String fwlj = "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
                fjxx.setWjid(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")));
                fjxx.setHth(cldkhtsjgl.getHth());
                fjxx.setDkzl(cldkhtsjgl.getDkpz());
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
                if ("1".equals(fjlx)) {
                    cldkhtsjgl.setSfscda("是");
                    QueryWrapper<XddaglXlh> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("jgdm", cldkhtsjgl.getJgdm());
                    XddaglXlh xlh = xddaglXlhService.getOne(queryWrapper);
                    if (xlh == null) {
                        xlhstr = haoAddOne("0");
                        XddaglXlh xlh1 = new XddaglXlh();
                        xlh1.setXlh(xlhstr);
                        xlh1.setJgdm(cldkhtsjgl.getJgdm());
                        xddaglXlhService.save(xlh1);
                    } else {
                        xlhstr = xlh.getXlh();
                        xlh.setXlh(xlhstr);
                        xlh.setJgdm(cldkhtsjgl.getJgdm());
                        xddaglXlhService.update(xlh, queryWrapper);
                    }
                    if (cldkhtsjgl.getHth().lastIndexOf("-") > 0) {
                        cldkhtsjgl.setDabh(cldkhtsjgl.getJgdm() + "-" + cldkhtsjgl.getHth().split("-")[2] + "-" + xlhstr);
                    } else {
                        cldkhtsjgl.setDabh(cldkhtsjgl.getJgdm() + "-" + cldkhtsjgl.getHth().substring(10, 14) + "-" + xlhstr);
                    }

                }

            }
        }
        QueryWrapper<Cldkhtsjgl> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("hth", cldkhtsjgl.getHth());
        Cldkhtsjgl cldkhtsjgl1 = cldkhtsjglService.getOne(queryWrapper1);
        if (StringUtils.isNotEmpty(cldkhtsjgl.getSfscda())) {
            cldkhtsjgl1.setSfscda(cldkhtsjgl.getSfscda());
        }
        if(StringUtils.isNotEmpty(cldkhtsjgl.getDabh())) {
            cldkhtsjgl1.setDabh(cldkhtsjgl.getDabh());
        }
        cldkhtsjgl1.setDkzrr(cldkhtsjgl.getDkzrr());
        cldkhtsjgl1.setDkpz(cldkhtsjgl.getDkpz());
        cldkhtsjgl1.setDkpzbc(cldkhtsjgl.getDkpzbc());
        cldkhtsjgl1.setLxdz(cldkhtsjgl.getLxdz());
        cldkhtsjgl1.setLxdh(cldkhtsjgl.getLxdh());
        cldkhtsjgl1.setLrbz(2);
        cldkhtsjgl1.setLrsj(new Date());
        cldkhtsjgl1.setLrr(sysUser.getUsername());
        cldkhtsjglService.update(cldkhtsjgl1, queryWrapper1);
        return Result.ok("操作成功!");
    }


    public static String haoAddOne(String liuShuiHao) {
        Integer intHao = Integer.parseInt(liuShuiHao);
        intHao++;
        DecimalFormat df = new DecimalFormat(STR_FORMAT);
        return df.format(intHao);
    }


    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存量贷款合同数据管理-通过id删除")
    @ApiOperation(value = "存量贷款合同数据管理-通过id删除", notes = "存量贷款合同数据管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        cldkhtsjglService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "存量贷款合同数据管理-批量删除")
    @ApiOperation(value = "存量贷款合同数据管理-批量删除", notes = "存量贷款合同数据管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.cldkhtsjglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存量贷款合同数据管理-通过id查询")
    @ApiOperation(value = "存量贷款合同数据管理-通过id查询", notes = "存量贷款合同数据管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Cldkhtsjgl cldkhtsjgl = cldkhtsjglService.getById(id);
        return Result.ok(cldkhtsjgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param cldkhtsjgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Cldkhtsjgl cldkhtsjgl) {
        return super.exportXls(request, cldkhtsjgl, Cldkhtsjgl.class, "存量贷款合同数据管理");
    }

    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        //return super.exportTemplateXls(SsglVO.class, "诉讼管理导入模板");
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "存量贷款合同数据管理导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, CldkhtsjglVO.class);
        ExportParams exportParams = new ExportParams("存量贷款合同数据管理导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
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
        return super.importExcelByTemplate(jsonObject, request, response, Cldkhtsjgl.class,CldkhtsjglVO.class, cldkhtsjglImportVerify);
    }

}
