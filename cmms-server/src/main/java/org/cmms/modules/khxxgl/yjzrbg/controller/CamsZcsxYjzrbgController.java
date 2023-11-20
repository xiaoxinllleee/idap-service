package org.cmms.modules.khxxgl.yjzrbg.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.ViewBrowers;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khxxgl.wbsjgl.tpjchmd.entity.Tpjchmd;
import org.cmms.modules.khxxgl.wbsjgl.tpjchmd.service.ITpjchmdService;
import org.cmms.modules.khxxgl.yjzrbg.entity.*;
import org.cmms.modules.khxxgl.yjzrbg.service.*;

import org.cmms.modules.util.CryptoUtils;
import org.cmms.modules.util.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 一键准入报告
 * @Author: jeecg-boot
 * @Date: 2023-04-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "一键准入报告")
@RestController
@RequestMapping("/yjzrbg/camsZcsxYjzrbg")
public class CamsZcsxYjzrbgController extends JeecgController<CamsZcsxYjzrbg, ICamsZcsxYjzrbgService> implements Job {
    @Autowired
    private ICamsZcsxYjzrbgService camsZcsxYjzrbgService;
    @Autowired
    IYwhywwlxxService ywhywwlxxService;
    @Autowired
    INhbkbpyService nhbkbpyService;
    @Autowired
    IZxbgPdfImgService zxbgPdfImgService;
    @Autowired
    IZxbgUserService zxbgUserService;
    @Autowired
    ITpjchmdService tpjchmdService;
    @Autowired
    ListToDictUtil listToDictUtil;
    @Autowired
    IZxbgQueryInfoService zxbgQueryInfoService;
    @Autowired
    ICamsZcsxYjzrbgEdcsService camsZcsxYjzrbgEdcsService;
    @Autowired
    RedisUtil redisUtil;
    /**
     * 分页列表查询
     *
     * @param camsZcsxYjzrbg
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "一键准入报告-分页列表查询")
    @ApiOperation(value = "一键准入报告-分页列表查询", notes = "一键准入报告-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CamsZcsxYjzrbg camsZcsxYjzrbg,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        //同步家庭数据  一天同步一次即可
        Object yjzrbglist = redisUtil.get("yjzrbglist");
        if (yjzrbglist == null){
            log.info("===同步农户数据到一键准入报告开始===");
            service.mergeNhxx();
            redisUtil.set("yjzrbglist","ok");
            log.info("===同步农户数据到一键准入报告结束===");
        }


        BigDecimal bigDecimal = null;
        BigDecimal bigDecimal2 = null;
        if (camsZcsxYjzrbg.getCsed() != null){
            bigDecimal = camsZcsxYjzrbg.getCsed();
            camsZcsxYjzrbg.setCsed(null);
        }
        if (camsZcsxYjzrbg.getCsede() != null){
            bigDecimal2 = camsZcsxYjzrbg.getCsede();
            camsZcsxYjzrbg.setCsede(null);
        }

        QueryWrapper<CamsZcsxYjzrbg> queryWrapper = QueryGenerator.initQueryWrapper(camsZcsxYjzrbg, req.getParameterMap());
        Page<CamsZcsxYjzrbg> page = new Page<CamsZcsxYjzrbg>(pageNo, pageSize);

        if (bigDecimal != null){
            queryWrapper.ge("csed",bigDecimal.toString());
        }
        if (bigDecimal2 != null){
            queryWrapper.le("csed",bigDecimal2.toString());
        }


        IPage<CamsZcsxYjzrbg> pageList = camsZcsxYjzrbgService.page(page, queryWrapper);
//        for (int i = 0; i < pageList.getRecords().size(); i++) {
//            LambdaQueryWrapper<CamsZcsxYjzrbgEdcs> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//            lambdaQueryWrapper.eq(CamsZcsxYjzrbgEdcs::getZjhm,pageList.getRecords().get(i).getZjhm());
//            List<CamsZcsxYjzrbgEdcs> list = camsZcsxYjzrbgEdcsService.list(lambdaQueryWrapper);
//            if (CollUtil.isNotEmpty(list)){
//                if (list.get(0).getHzed() != null){
//                    pageList.getRecords().get(i).setHzed(list.get(0).getHzed());
//                }
//            }
//        }
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param camsZcsxYjzrbg
     * @return
     */
    @AutoLog(value = "一键准入报告-添加")
    @ApiOperation(value = "一键准入报告-添加", notes = "一键准入报告-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CamsZcsxYjzrbg camsZcsxYjzrbg) {
        camsZcsxYjzrbgService.save(camsZcsxYjzrbg);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param camsZcsxYjzrbg
     * @return
     */
    @AutoLog(value = "一键准入报告-编辑")
    @ApiOperation(value = "一键准入报告-编辑", notes = "一键准入报告-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CamsZcsxYjzrbg camsZcsxYjzrbg) {
        camsZcsxYjzrbgService.updateById(camsZcsxYjzrbg);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "一键准入报告-通过id删除")
    @ApiOperation(value = "一键准入报告-通过id删除", notes = "一键准入报告-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        camsZcsxYjzrbgService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "一键准入报告-批量删除")
    @ApiOperation(value = "一键准入报告-批量删除", notes = "一键准入报告-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.camsZcsxYjzrbgService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "一键准入报告-通过id查询")
    @ApiOperation(value = "一键准入报告-通过id查询", notes = "一键准入报告-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CamsZcsxYjzrbg camsZcsxYjzrbg = camsZcsxYjzrbgService.getById(id);
        return Result.ok(camsZcsxYjzrbg);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param camsZcsxYjzrbg
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CamsZcsxYjzrbg camsZcsxYjzrbg) {
        return super.exportXls(request, camsZcsxYjzrbg, CamsZcsxYjzrbg.class, "一键准入报告");
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
        return super.importExcel(request, response, CamsZcsxYjzrbg.class);
    }


    @RequestMapping("/getYwhywxx")
    public Result<?> getYwhywxx(String zjhm) {





        YjzrbgScore yjzrbgScore = new YjzrbgScore();
        yjzrbgScore.setZjhm(zjhm);
        LambdaQueryWrapper<Ywhywwlxx> lambdaQueryWrapper = new LambdaQueryWrapper<Ywhywwlxx>();
        lambdaQueryWrapper.eq(Ywhywwlxx::getZjhm, zjhm);
        List<Ywhywwlxx> list = ywhywwlxxService.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            List list2 = listToDictUtil.parseDictText(list);
            Object o = list2.get(0);
            Ywhywwlxx ywhywwlxx = list.get(0);
            yjzrbgScore.setYwhywwlxx(o);
            if (ywhywwlxx.getBwbldkye() != null) {
                yjzrbgScore.setBlkh(false);
                yjzrbgScore.setBlkhval("在我行存在表外不良贷款");
            }
            if (ywhywwlxx.getDkje() != null || ywhywwlxx.getDkye() != null) {
                yjzrbgScore.setDkkh(-30);
            }
            if (ywhywwlxx.getCknrpye() != null) {
                BigDecimal divide = ywhywwlxx.getCknrpye().divide(new BigDecimal("100000"), 0, BigDecimal.ROUND_UP).multiply(new BigDecimal("50"));
                yjzrbgScore.setCkrj(divide.intValue());
            }
            if (ywhywwlxx.getDkzzffrq() != null) {
                Date dkzzffrq = ywhywwlxx.getDkzzffrq();
                long l = DateUtil.betweenDay(dkzzffrq, new Date(), true);
                if (l > 0l) {
                    yjzrbgScore.setDkyewlsj(50);
                } else if (l > 1095l) {
                    yjzrbgScore.setDkyewlsj(100);
                } else if (l > 1825l) {
                    yjzrbgScore.setDkyewlsj(150);
                }
            }
            int dzyhktqk = 0;
            if (StringUtils.isNotBlank(ywhywwlxx.getSfktsbk()) && "1".equals(ywhywwlxx.getSfktsbk())) {
                dzyhktqk += 50;
            }
            if (StringUtils.isNotBlank(ywhywwlxx.getSfktsjyhyw()) && "1".equals(ywhywwlxx.getSfktsjyhyw())) {
                dzyhktqk += 50;
            }
            if (StringUtils.isNotBlank(ywhywwlxx.getSfktwsyhyw()) && "1".equals(ywhywwlxx.getSfktwsyhyw())) {
                dzyhktqk += 50;
            }
            if (StringUtils.isNotBlank(ywhywwlxx.getSfktxyk()) && "1".equals(ywhywwlxx.getSfktxyk())) {
                dzyhktqk += 50;
            }
            if (StringUtils.isNotBlank(ywhywwlxx.getSfktxyk()) && "1".equals(ywhywwlxx.getSfktxyk())) {
                dzyhktqk += 50;
            }
            yjzrbgScore.setDzyhktqk(dzyhktqk);
        }
        BigDecimal maxJysxed = nhbkbpyService.getMaxJysxed(zjhm);
        if (maxJysxed != null && maxJysxed.compareTo(new BigDecimal("0")) > 0) {
            yjzrbgScore.setBkbpy(100);
        }

        LambdaQueryWrapper<Tpjchmd> tpjchmdLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tpjchmdLambdaQueryWrapper.eq(Tpjchmd::getZjhm,zjhm);
        List<Tpjchmd> list1 = tpjchmdService.list(tpjchmdLambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list1)){
            yjzrbgScore.setTpjch(2);
            yjzrbgScore.setTpjchval("此人为脱贫及监测户");
        }else {
            yjzrbgScore.setTpjch(1);
        }

        yjzrbgScore.jszf();

        service.updateScore(yjzrbgScore);
        return Result.ok(yjzrbgScore);
    }

    public StringBuffer getlogin(ViewBrowers vb) {
        StringBuffer buf = new StringBuffer();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
        headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headerMap.put("Accept-Encoding", "gzip, deflate");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.9");
        headerMap.put("Connection", "keep-alive");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        try {
            buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/logon.action", new HashMap(), headerMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buf;
    }

    public StringBuffer tologin(ViewBrowers vb,ZxbgUser zxbgUser) {
        StringBuffer buf = new StringBuffer();
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userId", zxbgUser.getUsername());
        paramMap.put("password", zxbgUser.getPassword());
        paramMap.put("resUrl", "null");
        paramMap.put("tlrFingerValue", "");
        paramMap.put("name", "null");
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
        headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headerMap.put("Accept-Encoding", "gzip, deflate");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.9");
        headerMap.put("Connection", "keep-alive");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        if(StringUtils.isNotEmpty(zxbgUser.getIp())){
            headerMap.put(" X-Forwarded-For", zxbgUser.getIp());
        }
        headerMap.put("Host", "edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Origin", "http://edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Referer", "http://edqpcsapp.hnrcc.bank:17011/QPCS/logout.action0");
        try {
            buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/logon.action", paramMap, headerMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return buf.append("登录接口失败=").append(e.getMessage());
        }
        return buf;
    }

    public StringBuffer getzzbglb(ViewBrowers vb, Long nodate,ZxbgUser zxbgUser) {
        StringBuffer buf = new StringBuffer();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
        headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headerMap.put("Accept-Encoding", "gzip, deflate");
        headerMap.put("Accept-Language", "zh-CN");
        headerMap.put("Connection", "keep-alive");
        headerMap.put("Content-Type", " text/html; charset=UTF-8");
        if(StringUtils.isNotEmpty(zxbgUser.getIp())){
            headerMap.put(" X-Forwarded-For", zxbgUser.getIp());
        }
        headerMap.put("Host", "edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Origin", "http://edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Referer", "http://edqpcsapp.hnrcc.bank:17011/QPCS/logon.action");
        try {
            buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qryPersonCreditApply.action?customerQuery.creditType=0&nodate=" + nodate, new HashMap(), headerMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return buf.append("fail=").append(e.getMessage());
        }

        return buf;
    }

    public StringBuffer getzzbglbBycx(ViewBrowers vb, Long nodate,ZxbgUser zxbgUser) {
        StringBuffer buf = new StringBuffer();
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("customerQuery.creditType", "0");
        paramMap.put("customerQuery.workflowState", "");
        paramMap.put("customerQuery.customName", "");
        paramMap.put("customerQuery.workflowStateDesc", "");
        paramMap.put("customerQuery.queryStartDate", org.cmms.common.util.DateUtil.getWebDateString(new Date()));
        paramMap.put("customerQuery.queryEndDate", org.cmms.common.util.DateUtil.getWebDateString(new Date()));

        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
        headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headerMap.put("Accept-Encoding", "gzip, deflate");
        headerMap.put("Accept-Language", "zh-CN");
        headerMap.put("Connection", "keep-alive");
        if(StringUtils.isNotEmpty(zxbgUser.getIp())){
            headerMap.put(" X-Forwarded-For", zxbgUser.getIp());
        }
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerMap.put("Host", "edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Origin", "http://edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Referer", "http://edqpcsapp.hnrcc.bank:17011/QPCS/qryPersonCreditApply.action?customerQuery.creditType=0&nodate=" + nodate);

        try {
            buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qryPersonCreditApply.action", paramMap, headerMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buf;
    }


    public void getZxbghtml(String html, List<Map<String, String>> mapList) {
        List<Element> list = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Element body = document.body();
        Elements elementsByClass = body.getElementsByClass("c-info-list");
        for (Element tables : elementsByClass) {
            Elements trs = tables.getElementsByTag("tr");
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                for (int i = 0; i < tds.size(); i++) {
                    Element td = tds.get(i);
                    if (i == tds.size() - 1) {
                        Elements img = td.getElementsByTag("img");
                        if (img.size() >= 2) {
                            Element element = img.get(1);
                            String title = element.attr("title");
                            if ("查看影像".equals(title)) {
                                System.out.println("==================================");
                                list.add(tr);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("ZZZZZZZZZZZZZZZZZList" + list.size());
        for (Element tr : list) {
            Elements tds = tr.getElementsByTag("td");
            Map map = new HashMap();
            for (int i = 0; i < tds.size(); i++) {
                if (i == 1) {
                    map.put("xm", tds.get(i).text());
                } else if (i == 3) {
                    map.put("sfzh", tds.get(i).text());
                } else if (i == 5) {
                    map.put("rq", tds.get(i).text());
                } else if (i == tds.size() - 1) {
                    Elements img = tds.get(i).getElementsByTag("img");
                    if (img.size() >= 2) {
                        Element element = img.get(1);
                        String onclick = element.attr("onclick");
                        String substring = onclick.substring(onclick.indexOf("(") + 1, onclick.indexOf(")")).replaceAll("'", "");
                        map.put("cs", substring);
                    }
                }
            }
            mapList.add(map);
        }
    }

    public StringBuffer tozxbb1(ViewBrowers vb, String id,ZxbgUser zxbgUser) {
        StringBuffer buf = new StringBuffer();
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("customerQuery.creditType", "0");
        paramMap.put("customerQuery.workflowState", "");
        paramMap.put("customerQuery.id", id);
        paramMap.put("customerQuery.customName", "");
        paramMap.put("customerQuery.workflowStateDesc", "");


        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headerMap.put("Accept-Encoding", "gzip, deflate");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.9");
        headerMap.put("Connection", "keep-alive");
        if(StringUtils.isNotEmpty(zxbgUser.getIp())){
            headerMap.put(" X-Forwarded-For", zxbgUser.getIp());
        }
        headerMap.put("Host", "edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Origin", "http://edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Referer", "http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/qryPersonCreditApply.action?customerQuery.creditType=0");

        try {
            buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/getValidCreditReportInfo.action", paramMap, headerMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buf;
    }

    public StringBuffer tozxbb2Referer(ViewBrowers vb, String id,ZxbgUser zxbgUser) {
        String[] cs = id.split(",");
        StringBuffer buf = new StringBuffer();

        HashMap<String, String> paramMap = new HashMap<String, String>();
	/*	paramMap.put("customerQueryId",cs[0]);
		paramMap.put("fileGroupTypeId",cs[1]);
		paramMap.put("mainId",cs[2]);
		paramMap.put("creditType","0");
		paramMap.put("returnUrl","qryPersonCreditApply");
		paramMap.put("activeId",cs[4]);
		paramMap.put("realMainId",cs[2]);
		paramMap.put("isCreditQuery","true");
*/
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headerMap.put("Accept-Encoding", "gzip, deflate");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.9");
        headerMap.put("Connection", "keep-alive");
        if(StringUtils.isNotEmpty(zxbgUser.getIp())){
            headerMap.put(" X-Forwarded-For", zxbgUser.getIp());
        }
        headerMap.put("Host", "edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Origin", "http://edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Referer", "http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/getValidCreditReportInfo.action");

        try {
            buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/viewCreditApplyImage.jsp?customerQueryId=" + cs[0] + "&fileGroupTypeId=" + cs[1] + "&mainId=" + cs[2] + "&creditType=0&returnUrl=qryPersonCreditApply&activeId=" + cs[4] + "&realMainId=" + cs[2] + "&isCreditQuery=true",
                    paramMap, headerMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buf;
    }

    public StringBuffer tozxbb2(ViewBrowers vb, String id,ZxbgUser zxbgUser) {
        String[] cs = id.split(",");
        StringBuffer buf = new StringBuffer();
        HashMap<String, String> paramMap = new HashMap<String, String>();
        /*paramMap.put("cmd","{creditQry_Id:'"+cs[0]+"',creditType:'0',nocache:'78795886'}");*/

        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerMap.put("Accept", "application/json, text/javascript, */*");
        headerMap.put("Accept-Encoding", "gzip, deflate");
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Accept-Language", "zh-CN");
        headerMap.put("Connection", "keep-alive");
        if(StringUtils.isNotEmpty(zxbgUser.getIp())){
            headerMap.put(" X-Forwarded-For", zxbgUser.getIp());
        }
        headerMap.put("Host", "edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Origin", "http://edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Referer", "http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/viewCreditApplyImage.jsp?customerQueryId=" + cs[0] + "&fileGroupTypeId=" + cs[1] + "&mainId=" + cs[2] + "&creditType=0&returnUrl=qryPersonCreditApply&activeId=" + cs[4] + "&realMainId=" + cs[2] + "&isCreditQuery=true");

        try {
            buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/getKeywords.action?cmd=" + URLEncoder.encode("{creditQry_Id:'" + cs[0] + "',creditType:'0',nocache:'78795886'}", "utf-8"), paramMap, headerMap, "utf-8");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return buf;
    }


    public StringBuffer tozxbb3(ViewBrowers vb, String id,ZxbgUser zxbgUser) {
        String[] cs = id.split(",");
        StringBuffer buf = new StringBuffer();
        HashMap<String, String> paramMap = new HashMap<String, String>();
        /*paramMap.put("cmd","{docId:'"+cs[2]+"',typeId:'"+cs[1]+"',activeId:'"+cs[4]+"',nocache:'73262601'}");*/

        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerMap.put("Accept", "application/json, text/javascript, */*");
        headerMap.put("Accept-Encoding", "gzip, deflate");
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Accept-Language", "zh-CN");
        if(StringUtils.isNotEmpty(zxbgUser.getIp())){
            headerMap.put(" X-Forwarded-For", zxbgUser.getIp());
        }
        headerMap.put("Connection", "keep-alive");
        headerMap.put("Host", "edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Origin", "http://edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Referer", "http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/viewCreditApplyImage.jsp?customerQueryId=" + cs[0] + "&fileGroupTypeId=" + cs[1] + "&mainId=" + cs[2] + "&creditType=0&returnUrl=qryPersonCreditApply&activeId=" + cs[4] + "&realMainId=" + cs[2] + "&isCreditQuery=true");

        try {
            buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/getDocListByParentCode.action?cmd=" + URLEncoder.encode("{docId:'" + cs[2] + "',typeId:'" + cs[1] + "',activeId:'" + cs[4] + "',nocache:'73262601'}", "utf-8"), paramMap, headerMap, "utf-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buf;
    }


    public StringBuffer tozxbb6(ViewBrowers vb, String id, String typeCode,ZxbgUser zxbgUser) {
        StringBuffer buf = new StringBuffer();
        String[] cs = id.split(",");
        HashMap<String, String> paramMap = new HashMap<String, String>();
        /*paramMap.put("cmd","{docId:'"+cs[2]+"',typeCode:'"+typeCode+"',types:'',activeId:'"+cs[4]+"',nocache:'89313252'}");*/

        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET4.0E; .NET4.0C)");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        headerMap.put("Accept", "application/json, text/javascript, */*");
        headerMap.put("Accept-Encoding", "gzip, deflate");
        headerMap.put("Cache-Control", "no-cache");
        headerMap.put("Accept-Language", "zh-CN");
        headerMap.put("Connection", "keep-alive");
        if(StringUtils.isNotEmpty(zxbgUser.getIp())){
            headerMap.put(" X-Forwarded-For", zxbgUser.getIp());
        }
        headerMap.put("Host", "edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Origin", "http://edqpcsapp.hnrcc.bank:17011");
        headerMap.put("Referer", "http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/viewCreditApplyImage.jsp?customerQueryId=" + cs[0] + "&fileGroupTypeId=" + cs[1] + "&mainId=" + cs[2] + "&creditType=0&returnUrl=qryPersonCreditApply&activeId=" + cs[4] + "&realMainId=" + cs[2] + "&isCreditQuery=true");

        try {
            buf = vb.sendForm("http://edqpcsapp.hnrcc.bank:17011/QPCS/qpcs/creditApply/getDocListByParentCode.action?cmd=" + URLEncoder.encode("{docId:'" + cs[2] + "',typeCode:'" + typeCode + "',types:'',activeId:'" + cs[4] + "',nocache:'89313252'}", "utf-8"), paramMap, headerMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buf;
    }

    /**
     * @return
     */
    @GetMapping(value = "/getzxbg")
    public Result<?> getzxbb() {
        LambdaQueryWrapper<ZxbgUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ZxbgUser::getType,"1");
        queryWrapper.eq(ZxbgUser::getSfcx,"1");
        List<ZxbgUser> list = zxbgUserService.list(queryWrapper);
        for (int i = 0; i < list.size(); i++) {
            log.info("===本次查询{}个用户的征信报告===",list.size());
            ZxbgUser zxbgUser = list.get(i);
            ZxbgQueryInfo zxbgQueryInfo = new ZxbgQueryInfo();
            zxbgQueryInfo.setUsername(zxbgUser.getUsername());

            ViewBrowers vb = new ViewBrowers(null);
            List<Map<String, String>> zxbghtml = new ArrayList<>();
            try {
                //1.访问登陆页面
                //StringBuffer loginBuf = getlogin(vb);
                //2.进行登陆
                StringBuffer loginbuf = tologin(vb,zxbgUser);
                log.info("=========loginbuf:" + loginbuf);
                log.info("=========cookie:" + vb.getCookieStr());

                if (loginbuf.toString().contains("用户名或密码错误")){
                    zxbgQueryInfo.setResult(zxbgUser.getUsername()+"用户名或密码错误");
                    if (loginbuf.toString().length() < 3000)
                        zxbgQueryInfo.setResultReason(loginbuf.toString());
                    zxbgQueryInfoService.save(zxbgQueryInfo);

                    zxbgUser.setErrorReason("用户名或密码错误");
                    zxbgUser.setSfcx("2");
                    zxbgUserService.updateById(zxbgUser);
                    continue;
                }

                if (loginbuf.toString().contains("登录失败")){
                    zxbgQueryInfo.setResult("登录失败");
                    if (loginbuf.toString().length() < 3000)
                        zxbgQueryInfo.setResultReason(loginbuf.toString());
                    zxbgQueryInfoService.save(zxbgQueryInfo);
                    continue;
                }

                //3.访问征信列表页面
                Long nodate = System.currentTimeMillis();
                StringBuffer zxbblb = getzzbglb(vb, nodate,zxbgUser);
                log.info("=========访问征信列表页面:" + zxbblb);
                if (zxbblb.toString().startsWith("fail")){
                    zxbgQueryInfo.setResult("访问征信列表页面失败");
                    if (zxbblb.toString().length() < 3000)
                        zxbgQueryInfo.setResultReason(zxbblb.toString());
                    zxbgQueryInfoService.save(zxbgQueryInfo);
                    continue;
                }

                //4.根据查询条件查询当日的征信报告 (需要扩展分页请求)
                StringBuffer zxbblbBycx = getzzbglbBycx(vb, nodate,zxbgUser);//分页的话再这套一层循环
                log.info("=========根据查询条件查询当日的征信报告:" + zxbblbBycx);
                List<Map<String, String>> zxbginfo = new ArrayList<>();
                getZxbghtml(zxbblbBycx.toString(), zxbginfo);
                for (Map<String, String> map : zxbginfo) {
                    for (String key : map.keySet()) {
                        System.out.println("===================key= " + key + " and ====================value= " + map.get(key));
                    }
                }

                for (Map<String, String> map : zxbginfo) {
                    String cs = map.get("cs");
                    if (org.cmms.common.util.StringUtils.isNotEmpty(cs)) {
                        //接口1
                        StringBuffer info1 = tozxbb1(vb, cs.split(",")[0],zxbgUser);
                        System.out.println("接口1返回信息====================" + info1);
                        //接口2referer
                        StringBuffer info2refere = tozxbb2Referer(vb, cs,zxbgUser);
                        System.out.println("接口2前置refre====================" + info2refere);
                        //接口2
                        StringBuffer info2 = tozxbb2(vb, cs,zxbgUser);
                        System.out.println("接口2返回信息====================" + info2);
                        //接口3
                        StringBuffer info3 = tozxbb3(vb, cs,zxbgUser);
                        System.out.println("接口3返回信息====================" + info3);
                        JSONObject jsonObject = JSON.parseObject(info3.toString());
                        JSONArray subList = (JSONArray) jsonObject.get("subList");
                        JSONObject jsonObjectTypeCode = (JSONObject) subList.get(1);
                        String typeCode = jsonObjectTypeCode.getString("TYPE_CODE");

                        StringBuffer info6 = tozxbb6(vb, cs, typeCode,zxbgUser);
                        JSONObject jsonObject6 = JSON.parseObject(info6.toString());
                        JSONArray attachList = (JSONArray) jsonObject6.get("attachList");
                        JSONObject jsonObjectHttpUrl = (JSONObject) attachList.get(0);
                        String httpUrl = jsonObjectHttpUrl.getString("httpUrl");
                        System.out.println("接口6返回信息====================" + info6);
                        System.out.println("最终征信html地址====================" + httpUrl);
                        StringBuffer stringBuffer = vb.sendForm(httpUrl, new HashMap(), "utf-8");
                        System.out.println("征信报告html====================" + stringBuffer);
                        log.info("征信报告html===================={}", stringBuffer);
                        //把征信报告写入系统
						String sfzh = map.get("sfzh");
						String xm = map.get("xm");
						String rq = map.get("rq");

                        String path = uploadpath + "/zxbg/" +sfzh+"-"+ rq + ".html";
                        if (FileUtil.isFile(path)){
                            zxbgQueryInfo.setResult("征信已存在系统");
                            zxbgQueryInfoService.save(zxbgQueryInfo);
                            continue;
                        }
                        File touch = FileUtil.touch(path);
                        FileUtil.appendString(stringBuffer.toString(), touch, "UTF-8");

                        ZxbgPdfImg zxbgPdfImg = new ZxbgPdfImg();
                        zxbgPdfImg.setId(IdUtil.fastSimpleUUID());
                        zxbgPdfImg.setWljl(path);
                        zxbgPdfImg.setFwlj(path.replace(uploadpath, ""));
                        zxbgPdfImg.setKhmc(xm);
                        zxbgPdfImg.setZjhm(sfzh);
                        zxbgPdfImgService.save(zxbgPdfImg);

                        LambdaQueryWrapper<CamsZcsxYjzrbg> camsZcsxYjzrbgLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        camsZcsxYjzrbgLambdaQueryWrapper.eq(CamsZcsxYjzrbg::getZjhm,sfzh);
                        long count = service.count(camsZcsxYjzrbgLambdaQueryWrapper);
                        if (count == 0){
                            CamsZcsxYjzrbg camsZcsxYjzrbg = new CamsZcsxYjzrbg();
                            camsZcsxYjzrbg.setId(IdUtil.fastSimpleUUID());
                            camsZcsxYjzrbg.setZjhm(sfzh);
                            camsZcsxYjzrbg.setHczt("征信核查");
                            camsZcsxYjzrbg.setKhmc(xm);
                            camsZcsxYjzrbg.setNbzx("1");
                            service.save(camsZcsxYjzrbg);
                        }

                        zxbgQueryInfo.setFwlj(path);
                        zxbgQueryInfo.setResult("获取征信成功");
                        zxbgQueryInfoService.save(zxbgQueryInfo);

                        Map<String, String> zxbbmap = new HashMap<>();
                        zxbbmap.put("sfzh", map.get("sfzh"));
                        zxbbmap.put("xm", map.get("xm"));
                        zxbbmap.put("rq", map.get("rq"));
                        zxbbmap.put("html", stringBuffer.toString());
                        zxbghtml.add(zxbbmap);
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return Result.ok("操作成功！");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //执行定时任务
        log.info("===征信报告定时任务开始执行===");
        getzxbb();
        log.info("===二代征信执行完毕===");
        log.info("===移动端执行开始===");
        toYdyxpt();
        log.info("===征信报告定时任务结束执行===");

    }


    @GetMapping(value = "/toYdyxpt")
    public Result<?> toYdyxpt(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type","2");
        List<ZxbgUser> list = zxbgUserService.list(queryWrapper);
        for (int k = 0; k < list.size(); k++) {
            ZxbgUser zxbgUser = list.get(k);
            ZxbgQueryInfo zxbgQueryInfo = new ZxbgQueryInfo();
            zxbgQueryInfo.setUsername(zxbgUser.getUsername());

            ViewBrowers vb = new ViewBrowers(null);
            //StringBuffer buffer1 = goYdyxptLogin(vb);
            String token;
            JSONObject parse = null;
            try {
                long begin = System.currentTimeMillis();
                while (true) {
                    //获取验证码
                    StringBuffer yzmbuff = toCaptchaImage(vb);
                    if (StringUtils.isBlank(yzmbuff)){
                        zxbgQueryInfo.setResult("失败");
                        zxbgQueryInfo.setResultReason("获取验证码错误");
                        zxbgQueryInfoService.save(zxbgQueryInfo);
                        return Result.ok();
                    }
                    String yzmCode = CryptoUtils.decryptAES("B70E4921CD3AF568", yzmbuff.toString());
                    JSONObject jsonObject = JSON.parseObject(yzmCode);
                    String uuid = jsonObject.getString("uuid");
                    if (StringUtils.isBlank(uuid))
                        return Result.ok();
                    log.info("=======================================uuid" + uuid);
                    StringBuffer buffer = toYdyxptLogin(vb, String.valueOf(2), uuid,zxbgUser);
                    String jiemizfc = CryptoUtils.decryptAES("B70E4921CD3AF568", buffer.toString());
                    log.info("=======================================登陆返回解密参数" + jiemizfc);
                    parse = JSONObject.parseObject(jiemizfc);
                    if (parse != null) {
                        token = parse.get("token") == null ? "" : parse.get("token").toString();
                        if (!token.isEmpty()) {
                            log.info("=================登陆成功==================");
                            log.info("=================登陆返回的解密结果" + parse.toJSONString());
                            StringBuffer stringBuffer = GoBusinessApplyInfo(vb, token);
                            log.info("=================GoBusinessApplyInfo返回加密信息" + stringBuffer.toString());
                            String businessApplyInfo = CryptoUtils.decryptAES("B70E4921CD3AF568", stringBuffer.toString());
                            log.info("=================GoBusinessApplyInfo返回解密信息" + businessApplyInfo);
                            if (org.cmms.common.util.StringUtils.isNotEmpty(businessApplyInfo)) {
                                JSONObject businessApplyInfoJson = JSON.parseObject(businessApplyInfo);
                                JSONArray rows = businessApplyInfoJson.getJSONArray("rows");
                                for (Object a : rows) {
                                    JSONObject row = (JSONObject) a;
                                    String applyId = row.getString("applyId");
                                    String offlineId = row.getString("offlineId");
                                    String custName = row.getString("custName");
                                    String ctfcCd = row.getString("ctfcCd");
                                    StringBuffer stringBuffer1 = GetBusinessMenu(vb, token, applyId);
                                    log.info("=================GetBusinessMenu返回加密信息" + stringBuffer1.toString());
                                    String businessMenu = CryptoUtils.decryptAES("B70E4921CD3AF568", stringBuffer1.toString());
                                    log.info("=================GetBusinessMenu返回解密信息" + businessMenu);

                                    if (org.cmms.common.util.StringUtils.isNotEmpty(businessMenu)) {
                                        JSONObject businessMenuJson = JSON.parseObject(businessMenu);
                                        JSONArray datas = businessMenuJson.getJSONArray("data");
                                        for (Object b : datas) {
                                            JSONObject data = (JSONObject) b;
                                            String docName = data.getString("docName");
                                            if (org.cmms.common.util.StringUtils.isNotEmpty(docName) && docName.indexOf("信贷单元") > -1) {
                                                String docPath = data.getString("docPath");
                                                if (org.cmms.common.util.StringUtils.isNotEmpty(docPath)) {
                                                    String docPathAll = "http://ifmcms.hnrcc.bank/XDXT/profile" + docPath;
                                                    log.info("=================PDFpath==================" + docPathAll);
                                                    String des = uploadpath + "/zxbgpdf/" + ctfcCd + "/";
                                                    if (!FileUtil.isDirectory(des)){
                                                        FileUtil.mkdir(des);
                                                    }
                                                    FileUtils.fileUrl(docPathAll, docName, des, "pdf");
                                                    zxbgQueryInfo.setResult("成功");
                                                    zxbgQueryInfo.setResultReason("获取成功，文件在"+des+"目录下");
                                                    zxbgQueryInfo.setFwlj(des+docName+".pdf");
                                                    zxbgQueryInfoService.save(zxbgQueryInfo);

                                                    LambdaQueryWrapper<CamsZcsxYjzrbg> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                                                    lambdaQueryWrapper.eq(CamsZcsxYjzrbg::getZjhm, ctfcCd);
                                                    CamsZcsxYjzrbg one = service.getOne(lambdaQueryWrapper, false);
                                                    if (one == null) {
                                                        CamsZcsxYjzrbg camsZcsxYjzrbg = new CamsZcsxYjzrbg();
                                                        camsZcsxYjzrbg.setCreator("zxbgpdf");
                                                        camsZcsxYjzrbg.setCreateTime(new Date());
                                                        camsZcsxYjzrbg.setZjhm(ctfcCd);
                                                        camsZcsxYjzrbg.setKhmc(custName);
                                                        camsZcsxYjzrbg.setHczt("1");
                                                        service.save(camsZcsxYjzrbg);
                                                    }

                                                    boolean file = FileUtil.isFile(des + docName + ".pdf");

                                                    if (file) {
                                                        log.info("===pdf获取成功,开始转换成图片===");
                                                        try {
                                                            InputStream is = new FileInputStream(des + docName + ".pdf");
                                                            PDDocument doc = PDDocument.load(is);
                                                            PDFRenderer renderer = new PDFRenderer(doc);
                                                            int pageCount = doc.getNumberOfPages();
                                                            int reNum = 0;
                                                            for (int i = 0; i < pageCount; i++) {
                                                                // dpi，图片像素点，dpi越高图片体积越大，216很清晰，105体积稳定
                                                                BufferedImage image = renderer.renderImageWithDPI(i, 216);

                                                                // 格式为JPG
                                                                FileOutputStream fileOutputStream = new FileOutputStream(des+docName + i + ".jpg");
                                                                ImageIO.write(image, "jpg", fileOutputStream);
                                                                //list.add(fwpath+"/"+zjhm+i+".jpg");
                                                                fileOutputStream.flush();
                                                                fileOutputStream.close();
                                                                //拿到已经生成好的文件 获取大小  直接在BufferedImage 拿不到大小 也可能是我没有找到拿的方式
                                                                boolean ywcFile = FileUtil.isFile(des+docName + i + ".jpg");
                                                                if (ywcFile){
                                                                    File yFile = FileUtil.file(des + docName + i + ".jpg");
                                                                    long size = FileUtil.size(yFile);
                                                                    //文件大于600KB的情况下  可以认为是征信报告
                                                                    if (size > 600000){
                                                                        //把文件修改为  0 1 2 3 的后缀
                                                                        FileUtil.rename(yFile,des + docName + reNum + ".jpg",true);
                                                                        reNum++;
                                                                    }else {
                                                                        //不是则删除
                                                                        FileUtil.del(yFile);
                                                                    }
                                                                }
                                                            }
                                                            LambdaQueryWrapper<ZxbgPdfImg> zxbgPdfImgLambdaQueryWrapper = new LambdaQueryWrapper<>();
                                                            zxbgPdfImgLambdaQueryWrapper.eq(ZxbgPdfImg::getZjhm, ctfcCd);
                                                            zxbgPdfImgService.remove(zxbgPdfImgLambdaQueryWrapper);

                                                            ZxbgPdfImg zxbgPdfImg = new ZxbgPdfImg();
                                                            zxbgPdfImg.setZjhm(ctfcCd);
                                                            zxbgPdfImg.setLrr("zxbgpdf");
                                                            zxbgPdfImg.setLrsj(new Date());
                                                            zxbgPdfImg.setWljl(des + docName + ".pdf");
                                                            zxbgPdfImg.setFwlj("/zxbgpdf/" + ctfcCd + "/"+docName+".pdf");
                                                            //使用新的数量
                                                            zxbgPdfImg.setImgNumber(reNum + "");
                                                            zxbgPdfImg.setKhmc(custName);
                                                            zxbgPdfImgService.save(zxbgPdfImg);

                                                        } catch (Exception e) {
                                                            System.out.println("=== pdf转图片出错===");
                                                            e.printStackTrace();
                                                        }
                                                        log.info("===完成转换成图片===");
                                                    }


                                                }

                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                    long end = System.currentTimeMillis();
                    if (end - begin > 5 * 60 * 1000) {
                        log.info("=================超过五分钟退出==================");
                        break;
                    }
                }

            } catch (Exception e) {
                log.error(e.getMessage(), e);
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return Result.ok();
    }

    public StringBuffer toCaptchaImage(ViewBrowers vb){
        StringBuffer buf=new StringBuffer();
        try {
            HashMap<String,String> paramMap = new HashMap<String,String>();
            HashMap<String,String> headerMap = new HashMap<String,String>();
            headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
            headerMap.put("Accept","application/json, text/plain, */*");
            headerMap.put("Accept-Encoding","gzip, deflate");
            headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
            headerMap.put("Connection","keep-alive");
            headerMap.put("Referer","http://ifmcms.hnrcc.bank/");

            buf = vb.sendForm("http://ifmcms.hnrcc.bank/XDXT/captchaImage",paramMap, headerMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return buf;
    }

    public StringBuffer toYdyxptLogin(ViewBrowers vb,String code,String uuid,ZxbgUser zxbgUser){
        StringBuffer buf =new StringBuffer();
        okhttp3.Response response = null;
        try {
			/*HashMap<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("username","3650065");
			paramMap.put("password","MTIzNDU2Nzg=");
			paramMap.put("code",code);
			paramMap.put("uuid", uuid);
			paramMap.put("isAndroid","0");
			paramMap.put("imei","");*/
            JSONObject jsonObject=new JSONObject(new LinkedHashMap());
            if (StringUtils.isNotBlank(zxbgUser.getUsername())){
                jsonObject.put("username",zxbgUser.getUsername());
            }
            if (StringUtils.isNotBlank(zxbgUser.getPassword())){
                String password = zxbgUser.getPassword();
                jsonObject.put("password", Base64.encode(password));
            }
            jsonObject.put("code",code);
            jsonObject.put("uuid", uuid);
            jsonObject.put("isAndroid","0");
            jsonObject.put("imei","");
            log.info("=======================================登陆请求参数"+jsonObject.toJSONString());
            String postString = CryptoUtils.encryptAES("B70E4921CD3AF568", jsonObject.toJSONString());
            log.info("=======================================登陆请求加密参数"+postString);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, postString);
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url("http://10.8.9.61/XDXT/login")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            response = client.newCall(request).execute();
            String responseString = response.body().string();
            log.info("=======================================登陆返回加密参数"+responseString);
            buf=new StringBuffer(responseString);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            e.printStackTrace();
        }finally {
            response.close();
        }
        return buf;
    }

    public StringBuffer GoBusinessApplyInfo(ViewBrowers vb,String token){
        StringBuffer buf=new StringBuffer();
        try {
            HashMap<String,String> paramMap = new HashMap<String,String>();

            HashMap<String,String> headerMap = new HashMap<String,String>();
            headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            headerMap.put("Accept-Encoding","gzip, deflate");
            headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
            headerMap.put("Authorization","Bearer"+" "+token);
            headerMap.put("Cookie","Admin-Token="+token);
            headerMap.put("Connection","keep-alive");
            headerMap.put("Host","ifmcms.hnrcc.bank");
            headerMap.put("Referer","http://ifmcms.hnrcc.bank/");
            headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
            String  post="{\"pageNum\":1,\"pageSize\":10,\"custSfz\":null,\"custName\":null,\"qryStatus\":\"1\",\"businessType\":\"\",\"validDays\":7,\"proId\":\"\"}";
            String postString = CryptoUtils.encryptAES("B70E4921CD3AF568", post);
            vb.setCookieStr("Admin-Token="+token);
            buf = vb.sendProxyForm("http://ifmcms.hnrcc.bank/XDXT/crm/businessApplyInfo/list?0="+URLEncoder.encode(postString),paramMap, headerMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf;
    }



    public StringBuffer GetBusinessMenu(ViewBrowers vb,String token,String applyId){
        StringBuffer buf=new StringBuffer();
        try {
            HashMap<String,String> paramMap = new HashMap<String,String>();

            HashMap<String,String> headerMap = new HashMap<String,String>();
            headerMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            headerMap.put("Accept-Encoding","gzip, deflate");
            headerMap.put("Accept-Language","zh-CN,zh;q=0.9");
            headerMap.put("Authorization","Bearer"+" "+token);
            headerMap.put("Cookie","Admin-Token="+token);
            headerMap.put("Connection","keep-alive");
            headerMap.put("Host","ifmcms.hnrcc.bank");
            headerMap.put("Referer","http://ifmcms.hnrcc.bank/");
            headerMap.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
            String  post="{\"applyId\":"+Integer.valueOf(applyId)+"}";
            String postString = CryptoUtils.encryptAES("B70E4921CD3AF568", post);
            vb.setCookieStr("Admin-Token="+token);
            buf = vb.sendProxyForm("http://ifmcms.hnrcc.bank/XDXT/crm/menu/getBusinessMenu?0="+URLEncoder.encode(postString),paramMap, headerMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf;
    }


}
