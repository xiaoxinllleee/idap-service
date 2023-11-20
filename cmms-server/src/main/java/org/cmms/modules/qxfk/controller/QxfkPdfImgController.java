package org.cmms.modules.qxfk.controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.ckkh.entity.QhckphVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.qxfk.entity.QxfkPdfImg;
import org.cmms.modules.qxfk.qx.dto.PdfInfo;
import org.cmms.modules.qxfk.qx.dto.QxJsonInfo;
import org.cmms.modules.qxfk.qx.dto.QxJsonVO;
import org.cmms.modules.qxfk.qx.dto.QxPdf;
import org.cmms.modules.qxfk.qx.util.ApiQxUtils;
import org.cmms.modules.qxfk.qx.util.JsonUtil;
import org.cmms.modules.qxfk.service.IQxfkPdfImgService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 七星风控
 * @Author: jeecg-boot
 * @Date: 2022-07-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "七星风控")
@RestController
@RequestMapping("/qxfk/qxfkPdfImg")
public class QxfkPdfImgController extends JeecgController<QxfkPdfImg, IQxfkPdfImgService> {
    @Autowired
    private IQxfkPdfImgService qxfkPdfImgService;
    @Autowired
    INhxqService nhxqService;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param qxfkPdfImg
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "七星风控-分页列表查询")
    @ApiOperation(value = "七星风控-分页列表查询", notes = "七星风控-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(QxfkPdfImg qxfkPdfImg,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<QxfkPdfImg> queryWrapper = QueryGenerator.initQueryWrapper(qxfkPdfImg, req.getParameterMap());
        queryWrapper.eq("lrr", getWorkNo());
        queryWrapper.orderByDesc("lrsj");
        Page<QxfkPdfImg> page = new Page<QxfkPdfImg>(pageNo, pageSize);
        IPage<QxfkPdfImg> pageList = qxfkPdfImgService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @Value(value = "${qxfk:dev}")
    private String qxfk;

    /**
     * 通过id查询
     *
     * @return
     */
    @AutoLog(value = "七星风控-通过id查询")
    @ApiOperation(value = "七星风控-通过id查询", notes = "七星风控-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(String zjhm,
                               String sjhm,
                               String name,
                               String id,
                               String type,
                               String sfsq) {
        System.out.println(zjhm);
        String redisQydm = getRedisQydm();
        if (QydmEnums.QIYANG.getQydmCode().equals(redisQydm) || QydmEnums.LANSHAN.getQydmCode().equals(redisQydm)
        || QydmEnums.XINTIAN.getQydmCode().equals(redisQydm)
        ) {
            //祁阳蓝山 写死的测试数据
            QxfkPdfImg qxfkPdfImg = new QxfkPdfImg();
            qxfkPdfImg.setId("1");
            qxfkPdfImg.setQueryTime(DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
            qxfkPdfImg.setFwlj("/qxfk/430103202206061088/430103202206061088.pdf");
            return Result.ok(qxfkPdfImg);
        }
        Nhxq byId = null;
        if (StringUtils.isBlank(id)) {
            //判断zjhm
            if (StringUtils.isNotBlank(zjhm) && !zjhm.contains("*")) {
                LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(Nhxq::getZjhm, zjhm);
                List<Nhxq> list = nhxqService.list(lambdaQueryWrapper);
                if (CollUtil.isNotEmpty(list)) {
                    byId = list.get(0);
                    id = byId.getId();
                } else {
                    return Result.error("身份证号查询不到农户信息");
                }
            } else {
                return Result.ok();
            }
        } else {
            byId = nhxqService.getById(id);
        }
        List<String> list = new ArrayList<>();

        //todo一年查询一次
        QxfkPdfImg lssj = service.getById(id);
        if (lssj != null)
            return Result.ok(lssj);

        if (byId != null) {
            if (StringUtils.isNotBlank(byId.getZjhm())) {
                zjhm = byId.getZjhm();

                Object o = redisUtil.get("qxfk:" + id);
                if (o != null)
                    return Result.ok(o);
            }
            if (StringUtils.isNotBlank(byId.getKhmc()))
                name = byId.getKhmc();
            if (StringUtils.isNotBlank(byId.getSjhm()))
                sjhm = byId.getSjhm();
        } else {
            return Result.ok();
        }

        //一天查询一次  不然频繁去查询   性能有影响

        if (StringUtils.isBlank(sjhm) || !Validator.isMobile(sjhm)) {
            sjhm = "13888888888";
        }
        log.info("===开始七星风控 zjhm={},sjhm={},name={} ===", zjhm, sjhm, name);

        String fwpath = "qxfk" + "/" + zjhm;
        String path = uploadpath + "/" + fwpath;
        boolean directory = FileUtil.isDirectory(path);
        if (!directory) {
            FileUtil.mkdir(path);
        }

        String wljl = path + "/" + zjhm + ".pdf";
        String img = path + "/" + zjhm;

        log.info("===pdf生成文件夹地址{}===", wljl);
        log.info("===pdf转图片生成文件夹地址{}===", img);

        String ywdate = JsonUtil.getYwdate(zjhm, sjhm, qxfk, name, "1");
        QxPdf qxPdf = ApiQxUtils.proApply(ywdate, qxfk);

        log.info("===查询结果{}===", qxPdf);

        String reportCode = null;

        String pdfData = null;
        if (qxPdf != null) {
            if (StringUtils.isNotBlank(qxPdf.getCode()) && "2000".equals(qxPdf.getCode())) {
                if (qxPdf.getInfo() != null) {
                    PdfInfo info = qxPdf.getInfo();
                    if (info.getReportCode() != null) {
                        reportCode = info.getReportCode();
                        pdfData = JsonUtil.getPdfData(info.getReportCode(), qxfk);
                    }
                }
            } else {
                QxfkPdfImg qxfkPdfImg = new QxfkPdfImg();
                qxfkPdfImg.setId(byId.getId());
                qxfkPdfImg.setLrr(getWorkNo());
                qxfkPdfImg.setLrsj(new Date());
                qxfkPdfImg.setZjhm(zjhm);
                qxfkPdfImg.setRiskLevel(qxPdf.getMessage());
                service.save(qxfkPdfImg);
                return Result.error(qxPdf.getMessage());
            }
        } else {
            return Result.ok("未查询出数据");
        }

        //正常 关注 高危 对应等级 1 2 3
        String risk = null;
        String queryTime = null;
        //获取到文件code
        if (StringUtils.isNotBlank(pdfData)) {

            if ("1".equals(sfsq)) {
                QxPdf pdf = ApiQxUtils.getPDF(pdfData, qxfk);
                log.info("===pdf查询结果{},{},{}===", pdf.getSuccess(), pdf.getCode(), pdf.getMessage());
                if (pdf != null) {
                    if (StringUtils.isNotBlank(pdf.getCode()) && "2000".equals(pdf.getCode())) {
                        if (pdf.getInfo() != null) {
                            PdfInfo info = pdf.getInfo();
                            if (info.getReportFile() != null) {
                                ApiQxUtils.makePdf(info.getReportFile(), wljl);
                            }
                        }
                    }
                }
            }

            QxJsonVO json = ApiQxUtils.getJSON(pdfData, qxfk);

            if (json != null) {
                if (StringUtils.isNotBlank(json.getCode()) && "2000".equals(json.getCode())) {
                    QxJsonInfo info = json.getInfo();
                    if (StringUtils.isNotBlank(info.getRiskLevel())) {
                        risk = info.getRiskLevel();
                    }
                    if (StringUtils.isNotBlank(info.getQueryTime())) {
                        queryTime = info.getQueryTime();
                    }
                }
            }

        }


        //表示已经有文件生成
        boolean file = FileUtil.isFile(wljl);

        if (file) {
            log.info("===paf转换成功,开始转换成图片===");
            try {
                InputStream is = new FileInputStream(wljl);
                PDDocument doc = PDDocument.load(is);
                PDFRenderer renderer = new PDFRenderer(doc);
                int pageCount = doc.getNumberOfPages();
                for (int i = 0; i < pageCount; i++) {
                    // dpi，图片像素点，dpi越高图片体积越大，216很清晰，105体积稳定
                    BufferedImage image = renderer.renderImageWithDPI(i, 216);
                    // 格式为JPG
                    FileOutputStream fileOutputStream = new FileOutputStream(img + i + ".jpg");
                    ImageIO.write(image, "jpg", fileOutputStream);
                    //list.add(fwpath+"/"+zjhm+i+".jpg");
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                System.out.println("=== pdf转图片出错===");
                e.printStackTrace();
            }
            log.info("===完成转换成图片===");
        }
        //缓存一天时间
        //redisUtil.set("qxfk:"+zjhm,list, 60*60*24);
        service.removeById(id);

        QxfkPdfImg qxfkPdfImg = new QxfkPdfImg();
        qxfkPdfImg.setId(byId.getId());
        qxfkPdfImg.setLrr(getWorkNo());
        qxfkPdfImg.setLrsj(new Date());
        qxfkPdfImg.setZjhm(zjhm);
        qxfkPdfImg.setReportCode(reportCode);
        qxfkPdfImg.setWljl(wljl);
        qxfkPdfImg.setFwlj("/qxfk/" + zjhm + "/" + zjhm + ".pdf");
        qxfkPdfImg.setRiskLevel(risk);
        if (StringUtils.isNotBlank(risk)) {
            if ("正常".equals(risk)) {
                qxfkPdfImg.setRiskLevelCode("1");
            } else if ("关注".equals(risk)) {
                qxfkPdfImg.setRiskLevelCode("2");
            } else {
                qxfkPdfImg.setRiskLevelCode("3");
            }
        }
        qxfkPdfImg.setQueryTime(queryTime);
        if (StringUtils.isNotBlank(byId.getHhbm())) {
            qxfkPdfImg.setHhbm(byId.getHhbm());
        }
        service.save(qxfkPdfImg);
        redisUtil.set("qxfk:" + byId.getId(), qxfkPdfImg, 60 * 60 * 24);

        return Result.ok(qxfkPdfImg);
    }

    @RequestMapping("/getByZjhm")
    public Result<?> getByZjhm(String zjhm) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("zjhm", zjhm);
        List list = qxfkPdfImgService.list(queryWrapper);
        if (CollUtil.isNotEmpty(list))
            return Result.ok(list.get(0));
        return Result.error("数据未找到");
    }

    /**
     * 查询七星风控
     * id:nhxq的id用来去查农户的证件号码    为什么不直接用zjhm，因为ipad端证件号码是加密的
     * zjhm
     * khmc
     * sjhm:手机号码不是必填项 手机号码在缺少时填写 此号码是和七星协商 对浏阳进行的特殊处理 138 8888 8888
     * sfsq:是否授权 农户授权后才能查询PDF  没有授权只能给一个汇总的数据
     */

    @RequestMapping("/queryQxfk")
    public Result<?> queryQxfk(String id, String zjhm, String khmc, String sjhm) {
        //先判断id 和 zjhm号码的状态 优先id 去覆盖 zjhm khmc sjhm
        if (StringUtils.isNotBlank(id)) {
            Nhxq byId = nhxqService.getById(id);
            if (byId != null) {
                if (StringUtils.isNotBlank(byId.getZjhm()))
                    zjhm = byId.getZjhm();
                if (StringUtils.isNotBlank(byId.getKhmc()))
                    khmc = byId.getKhmc();
                if (StringUtils.isNotBlank(byId.getSjhm()))
                    sjhm = byId.getSjhm();
            }
            return Result.error("农户数据查询无结果");
        }
        if (StringUtils.isBlank(zjhm) || StringUtils.isBlank(khmc))
            return Result.error("农户证件号码或者农户姓名不能为空");
        if (StringUtils.isBlank(sjhm))
            sjhm = "13888888888";
        log.info("===当前查询手机号{}===", sjhm);
        QxfkPdfImg result = null;
        Object o = redisUtil.get("qxfk:" + zjhm);
        if (o != null)
            return Result.ok(o);
        String repCode = null;
        String pdfData = null;
        //获取今年查询的数据  年初-年末
        LambdaQueryWrapper<QxfkPdfImg> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(QxfkPdfImg::getZjhm, zjhm);
        lambdaQueryWrapper.ge(QxfkPdfImg::getLrsj, DateUtil.beginOfYear(new Date()));
        lambdaQueryWrapper.le(QxfkPdfImg::getLrsj, DateUtil.endOfYear(new Date()));
        lambdaQueryWrapper.orderByDesc(QxfkPdfImg::getLrsj);
        List<QxfkPdfImg> list = service.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            QxfkPdfImg old = list.get(0);
            int size = list.size();
            if (size == 1) {
                //没有等级表示查询json数据时出现了网络故障
                if (StringUtils.isNotBlank(old.getRiskLevel())) {
                    //判断是否间隔3个月
                    DateTime dateTime = DateUtil.offsetMonth(old.getLrsj(), 3);
                    if (DateUtil.compare(dateTime, new Date()) > 0) {
                        return Result.error("查询间隔需要3个月以上，上次查询时间" + old.getQueryTime());
                    }
                } else {
                    repCode = old.getReportCode();
                    pdfData = JsonUtil.getPdfData(repCode, qxfk);
                    log.info("===继续完成未查询的{}===", repCode);
                    return service.QxJsonData(pdfData, zjhm, repCode, qxfk, getWorkNo(), "2", sjhm, khmc);
                }
            } else {
                result = old;
                redisUtil.set("qxfk:" + zjhm, result, 60 * 60 * 24);
                log.info("===风控数据本年度已经查询2次，返回数据为{}===", result);
                return Result.ok(result);
            }
        }
        //组装好数据进行查询
        String ywdate = JsonUtil.getYwdate(zjhm, sjhm, qxfk, khmc, "1");
        QxPdf qxPdf = ApiQxUtils.proApply(ywdate, qxfk);
        if (qxPdf != null) {
            if (StringUtils.isNotBlank(qxPdf.getCode())) {
                String code = qxPdf.getCode();
                if ("2000".equals(code)) {
                    if (qxPdf.getInfo() != null && StringUtils.isNotBlank(qxPdf.getInfo().getReportCode())) {
                        repCode = qxPdf.getInfo().getReportCode();
                        pdfData = JsonUtil.getPdfData(repCode, qxfk);
                    }
                } else {
                    result = new QxfkPdfImg();
                    result.setZjhm(zjhm);
                    result.setLrr(getWorkNo());
                    result.setLrsj(new Date());
                    result.setImgFwlj(code);
                    if (StringUtils.isNotBlank(qxPdf.getMessage()))
                        result.setImgWllj(qxPdf.getMessage());
                    service.save(result);
                    log.info("===查询失败{}===", result);
                    return Result.error("查询接口失败");
                }

            }
            if (pdfData != null) {
                return service.QxJsonData(pdfData, zjhm, repCode, qxfk, getWorkNo(), "1", sjhm, khmc);
            }

            //查json数据
            /*QxJsonVO json = ApiQxUtils.getJSON(pdfData, qxfk);
            if (json != null) {
                String code = json.getCode();
                if ("2000".equals(code)) {
                    QxJsonInfo info = json.getInfo();
                    String riskLevel = info.getRiskLevel();
                    String queryTime = info.getQueryTime();
                    result = new QxfkPdfImg();
                    result.setZjhm(zjhm);
                    result.setLrr(getWorkNo());
                    result.setLrsj(new Date());
                    result.setReportCode(repCode);
                    result.setRiskLevel(riskLevel);
                    if (StringUtils.isNotBlank(riskLevel)) {
                        if ("正常".equals(riskLevel)) {
                            result.setRiskLevelCode("1");
                        } else if ("关注".equals(riskLevel)) {
                            result.setRiskLevelCode("2");
                        } else {
                            result.setRiskLevelCode("3");
                        }
                    }
                    result.setQueryTime(queryTime);
                    service.save(result);
                    return Result.ok(result);
                } else {
                    result = new QxfkPdfImg();
                    result.setZjhm(zjhm);
                    result.setLrr(getWorkNo());
                    result.setLrsj(new Date());
                    result.setImgFwlj(code);
                    if (StringUtils.isNotBlank(qxPdf.getMessage()))
                        result.setImgWllj(qxPdf.getMessage());
                    service.save(result);
                    log.info("===查询失败{}===", result);
                    return Result.error("详情查询接口失败");
                }
            } else {
                result = new QxfkPdfImg();
                result.setZjhm(zjhm);
                result.setLrr(getWorkNo());
                result.setLrsj(new Date());
                result.setReportCode(repCode);
                result.setImgWllj("网络故障，未查询到风控数据");
                service.save(result);
                log.info("===JSON网络故障，未查询到风控数据{}===", result);
                return Result.error("查询接口失败");
            }*/

        } else {
            return Result.error("七星风控网络错误，请联系管理员开通！");
        }
        return Result.ok();
    }


    @RequestMapping("/getByRepCode")
    public Result<?> getByRepCode(String zjhm) {
        if (StringUtils.isBlank(zjhm))
            return Result.error("身份证号码不能为空");
        QueryWrapper<QxfkPdfImg> queryWrapper = new QueryWrapper();
        queryWrapper.eq("zjhm", zjhm);
        queryWrapper.orderByDesc("lrsj");
        List<QxfkPdfImg> list = service.list(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            try {

                QxfkPdfImg qxfkPdfImg = list.get(0);
                if (StringUtils.isNotBlank(qxfkPdfImg.getReportCode())) {
                    String reportCode = qxfkPdfImg.getReportCode();
                    String pdfData = JsonUtil.getPdfData(reportCode, qxfk);
                    Object json = ApiQxUtils.getAllJSON(pdfData, qxfk);
                    if (json != null) {
                        return Result.ok(json);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error("七星接口错误或者网络波动，请联系管理员");
            }
        }
        return Result.error("查询失败");
    }

    @RequestMapping("/getByCode")
    public Result<?> getByCode(String reportCode) {
        if (StringUtils.isBlank(reportCode))
            return Result.error("报告编码不能为空");
        try {
            String pdfData = JsonUtil.getPdfData(reportCode, qxfk);
            Object json = ApiQxUtils.getAllJSON(pdfData, qxfk);
            if (json != null) {
                return Result.ok(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("七星接口错误或者网络波动，请联系管理员");
        }
        return Result.error("查询失败");
    }
}
