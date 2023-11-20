package org.cmms.modules.xyjlcx.xybg.xybgcx.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.khgl.dkkh.service.ICbsBormBaseService;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import org.cmms.modules.khgl.khhmc.service.IKhfjxxglService;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.wbsjgl.tpjchmd.entity.Tpjchmd;
import org.cmms.modules.khxxgl.wbsjgl.tpjchmd.service.ITpjchmdService;
import org.cmms.modules.khxxgl.yjzrbg.entity.CamsZcsxYjzrbgbh;
import org.cmms.modules.khxxgl.yjzrbg.entity.YjzrbgScore;
import org.cmms.modules.khxxgl.yjzrbg.entity.ZxbgPdfImg;
import org.cmms.modules.khxxgl.yjzrbg.service.ICamsZcsxYjzrbgService;
import org.cmms.modules.khxxgl.yjzrbg.service.ICamsZcsxYjzrbgbhService;
import org.cmms.modules.khxxgl.yjzrbg.service.IZxbgPdfImgService;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.entity.Kjbxx;
import org.cmms.modules.sjxf.hxxt.jjk.kjbxx.service.IKjbxxService;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.entity.Grkhb;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.service.IGrkhbService;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.entity.Khxxb;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.service.IKhxxbService;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.mapper.SysDictMapper;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.Bwdksjmx;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.service.IBwdksjmxService;
import org.cmms.modules.xyjlcx.bwdkgl.sbgxmx.entity.Sbgxmx;
import org.cmms.modules.xyjlcx.bwdkgl.sbgxmx.service.ISbgxmxService;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.entity.Hmdgl;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.service.IHmdglService;
import org.cmms.modules.xyjlcx.jcsjgl.csgl.entity.Csgl;
import org.cmms.modules.xyjlcx.jcsjgl.csgl.service.ICsglService;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity.Djkdksjmx;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.service.IDjkdksjmxService;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.entity.Dkkhglrgl;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.service.IDkkhglrglService;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.entity.Ssgl;
import org.cmms.modules.xyjlcx.sszxgl.ssgl.service.ISsglService;
import org.cmms.modules.xyjlcx.sszxgl.zxgl.entity.Zxgl;
import org.cmms.modules.xyjlcx.sszxgl.zxgl.service.IZxglService;
import org.cmms.modules.xyjlcx.xybg.cxjltz.entity.Cxjltz;
import org.cmms.modules.xyjlcx.xybg.cxjltz.service.ICxjltzService;
import org.cmms.modules.xyjlcx.xybg.dksj.entity.Dksj;
import org.cmms.modules.xyjlcx.xybg.dksj.service.IDksjService;
import org.cmms.modules.xyjlcx.xybg.qxmx.entity.Qxmx;
import org.cmms.modules.xyjlcx.xybg.qxmx.service.IQxmxService;
import org.cmms.modules.xyjlcx.xybg.xybgcx.entity.CreditReportNo;
import org.cmms.modules.xyjlcx.xybg.xybgcx.service.ICreditReportNoService;
import org.cmms.modules.xyjlcx.xybg.xybgcx.service.ICreditReportQueryService;
import org.cmms.modules.xyjlcx.xybg.xybgcx.vo.JbxxGlrxx;
import org.cmms.modules.xyjlcx.xybg.xybgcx.vo.TmpCreditDbxxVO;
import org.cmms.modules.xyjlcx.xybg.xybgcx.vo.TmpCreditDksjVO;
import org.cmms.modules.xyjlcx.xybg.xybgcx.vo.TmpCreditXytssjVO;
import org.cmms.modules.xyjlcx.xybg.xyts.entity.Xyts;
import org.cmms.modules.xyjlcx.xybg.xyts.service.IXytsService;
import org.cmms.modules.ywgl.djkyw.djkxxgl.entity.Djkxxgl;
import org.cmms.modules.ywgl.djkyw.djkxxgl.service.IDjkxxglService;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.service.ILsdksjglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Api(tags = "信用报告查询")
@RestController
@RequestMapping("/creditreportqueryTy")
public class CreditReportQueryTyController extends JeecgController<Cxjltz, ICreditReportQueryService> {
    @Autowired
    private ICreditReportQueryService iCreditReportQueryService;
    @Autowired
    private ICreditReportNoService iCreditReportNoService;
    @Autowired
    private IDkkhglrglService iDkkhglrglService;
    @Autowired
    private IDjkdksjmxService iDjkdksjmxService;
    @Autowired
    private IBwdksjmxService iBwdksjmxService;
    @Autowired
    private ICxjltzService iCxjltzService;
    @Autowired
    private ICsglService iCsglService;
    @Autowired
    private IHmdglService iHmdglService;
    @Autowired
    private IZxglService iZxglService;
    @Autowired
    private ISsglService iSsglService;
    @Autowired
    private ISbgxmxService iSbgxmxService;
    @Autowired
    private IKhxxbService iKhxxbService;
    @Autowired
    private IGrkhbService iGrkhbService;
    @Autowired
    private IKjbxxService iKjbxxService;
    @Autowired
    private IDjkxxglService iDjkxxglService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    SysDictMapper sysDictMapper;
    @Autowired
    private ISysLogService iSysLogService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ListToDictUtil listToDictUtil;
    @Autowired
    private IQxmxService qxmxService;
    @Autowired
    private IDksjService dksjService;
    @Autowired
    private IXytsService xytsService;

    @Autowired
    private ISysDicService sysDicService;
    @Autowired
    private ICamsZcsxYjzrbgbhService camsZcsxYjzrbgbhService;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private ICbsBormBaseService cbsBormBaseService;
    @Autowired
    IYwhywwlxxService ywhywwlxxService;
    @Autowired
    INhbkbpyService nhbkbpyService;
    @Autowired
    ITpjchmdService tpjchmdService;
    @Autowired
    IZxbgPdfImgService zxbgPdfImgService;
    @Autowired
    private ICamsZcsxYjzrbgService camsZcsxYjzrbgService;

    @Autowired
    ILsdksjglService lsdksjglService;
    @Autowired
    private IKhfjxxglService khfjxxglService;

    /**
     * 获取客户及其关联人信息详情
     *
     * @param bcxrzjhm 被查询人证件号码
     * @param cxyy     查询原因
     * @return
     */
    @GetMapping(value = "/query")
    public Result<?> query(@RequestParam(name = "bcxrzjhm", required = true) String bcxrzjhm,
                           @RequestParam(name = "cxyy", required = true) String cxyy) {
        JSONObject object = new JSONObject();
        try {
            String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());//区域代码
            //InetAddress ip4 = InetAddress.getLocalHost();//获取IP
            //生成报告编号
            String cxJgdm = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "zzbz", getLoginUser().getOrgCode());
            int xh = 1;
            QueryWrapper<CreditReportNo> creditBgbh = new QueryWrapper<>();
            creditBgbh.eq("jgdm", cxJgdm);
            creditBgbh.eq("cxrq", new Timestamp(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyyMMdd"), "yyyyMMdd").getTime()));
            CreditReportNo bgbhQuery = iCreditReportNoService.getOne(creditBgbh, false);
            if (bgbhQuery != null) {
                xh = bgbhQuery.getXh().intValue();
            }
            int finalXh = xh;
            //报告编号查询
            CompletableFuture<String[]> completableFuture1 = CompletableFuture.supplyAsync(() -> {
                log.info("==============异步执行报告编号查询=============");
                String[] strs = new String[3];
                try {
                    String bgbh = cxJgdm + "-" + DateUtil.formatDateTime("yyyyMMdd");
                    //查询报告编号中的序号
                    bgbh += "-" + finalXh;
                    strs[0] = cxJgdm;
                    strs[1] = String.valueOf(finalXh);
                    strs[2] = bgbh;
                    if (bgbhQuery != null) {
                        CreditReportNo update = new CreditReportNo();
                        update.setXh(finalXh + 1);
                        UpdateWrapper<CreditReportNo> updateWrapper = new UpdateWrapper<>();
                        updateWrapper.eq("jgdm", cxJgdm);
                        updateWrapper.eq("cxrq", new Timestamp(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyyMMdd"), "yyyyMMdd").getTime()));
                        iCreditReportNoService.update(update, updateWrapper);
                    } else {
                        //没记录则新增一条记录
                        CreditReportNo insert = new CreditReportNo();
                        insert.setJgdm(cxJgdm);
                        insert.setCxrq(new Timestamp(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyyMMdd"), "yyyyMMdd").getTime()));
                        insert.setXh(finalXh + 1);
                        iCreditReportNoService.save(insert);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return strs;
            });
            //客户姓名查询
            CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
                log.info("==============异步执行客户姓名查询==============");
                String khxm = "";
                //String jgdm = "";
                try {
                    //先查询信贷系统个人基本信息表
                    Khxxb grkhjbxx = iKhxxbService.getKhxxbOracle(bcxrzjhm);
                    if (grkhjbxx != null) {
                        khxm = grkhjbxx.getCustName();
                        //jgdm = grkhjbxx.getCreateBranchNo();
                    } else {
                        //查询贷记卡贷款数据明细表
                        QueryWrapper<Djkdksjmx> creditDjkdksjmx = new QueryWrapper<>();
                        creditDjkdksjmx.eq("zjhm", bcxrzjhm);
                        Djkdksjmx djkdksjmx = iDjkdksjmxService.getOne(creditDjkdksjmx, false);
                        if (djkdksjmx != null) {
                            khxm = djkdksjmx.getKhmc();
                            //jgdm = djkdksjmx.getYwjg();
                        } else {
                            //查询表外贷款数据明细表
                            QueryWrapper<Bwdksjmx> creditBwdksjmx = new QueryWrapper<>();
                            creditBwdksjmx.eq("zjhm", bcxrzjhm);
                            Bwdksjmx bwdksjmx = iBwdksjmxService.getOne(creditBwdksjmx, false);
                            if (bwdksjmx != null) {
                                khxm = bwdksjmx.getKhmc();
                                //jgdm = bwdksjmx.getJgdm();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return khxm;
            });
            //查询对应的关联人信息
            CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(() -> {
                log.info("==============异步根据证件号码查询对应的关联人信息==============");
                List<Dkkhglrgl> dkkhglrgl = new ArrayList<>();
                int x = finalXh;
                try {
                    QueryWrapper<Dkkhglrgl> creditDkkhglrgl = new QueryWrapper<>();
                    creditDkkhglrgl.eq("jkrzjhm", bcxrzjhm);
                    dkkhglrgl = iDkkhglrglService.list(creditDkkhglrgl);
                    for (Dkkhglrgl form : dkkhglrgl) {
                        String glrBgbh = cxJgdm + "-" + DateUtil.formatDateTime("yyyyMMdd") + "-" + ++x;
                        form.setGlrbgbh(glrBgbh);
                    }
                    object.put("table", dkkhglrgl);
                    List<Dkkhglrgl> dkkhglrgl2 = new ArrayList<>();
                    creditDkkhglrgl = new QueryWrapper<>();
                    creditDkkhglrgl.eq("glrzjhm", bcxrzjhm);
                    List<Dkkhglrgl> khglrgl = iDkkhglrglService.list(creditDkkhglrgl);
                    for (Dkkhglrgl form : khglrgl) {
                        String jkrzjhm2 = form.getJkrzjhm();
                        String glrzjhm2 = form.getGlrzjhm();
                        boolean exists = false;
                        if (dkkhglrgl.size() > 0) {
                            for (Dkkhglrgl record : dkkhglrgl) {
                                String jkrzjhm = record.getJkrzjhm();
                                String glrzjhm = record.getGlrzjhm();
                                // 若`借款人证件号`等于`关联人证件号`且`关联人证件号`等于`借款人证件号`，则剔除该条记录
                                if (!(jkrzjhm2.equalsIgnoreCase(glrzjhm) && glrzjhm2.equalsIgnoreCase(jkrzjhm))) {
                                    dkkhglrgl2.add(form);
                                    break;
                                } else {
                                    exists = true;
                                }
                            }
                        } else {
                            dkkhglrgl2.add(form);
                        }
                        if (!exists) {
                            String glrBgbh = cxJgdm + "-" + DateUtil.formatDateTime("yyyyMMdd") + "-" + ++x;
                            form.setGlrbgbh(glrBgbh);
                        }
                    }
                    object.put("table2", dkkhglrgl2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            String[] bbhbstr = completableFuture1.get();
            String khxm = completableFuture2.get();
            completableFuture3.get();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bgbh", bbhbstr[2]);
            jsonObject.put("zjhm", bcxrzjhm);
            jsonObject.put("khxm", khxm);
            jsonObject.put("cxyy", cxyy);
            jsonObject.put("cxyyDictText", iSysDictService.queryDictTextByKey("cxyy", cxyy));
            object.put("bcxrxx", jsonObject);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("系统错误，请联系管理员处理！", throwable.getMessage());
        }
        return Result.ok(object);
    }

    /**
     * 信用报告查询 / 生成报告
     *
     * @param bgbh         报告编号
     * @param cxyy         查询原因（汉字）
     * @param cxyyDictText 查询原因（数字）
     * @param zjhm         证件号码
     * @param xm           客户姓名
     * @return
     */
    @GetMapping(value = "/report")
    public Result<?> report(@RequestParam(name = "bgbh", required = true) String bgbh,
                            @RequestParam(name = "cxyy", required = true) String cxyy,
                            @RequestParam(name = "cxyyDictText", required = true) String cxyyDictText,
                            @RequestParam(name = "zjhm", required = true) String zjhm,
                            @RequestParam(name = "xm", required = true) String xm) {
        //初始化数据
        iCreditReportQueryService.CreditInitExtract(zjhm);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long beginTime = System.currentTimeMillis();
        Date date = new Date(beginTime);
        log.info("信用报告查询-征信报告生成-开始时间：" + simpleDateFormat.format(date));
        List<Bwdksjmx> bwtmdk = new ArrayList();
        JSONObject object = new JSONObject();
        try {
            //区域代码
            String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
            //获取IP
            //InetAddress ip4 = InetAddress.getLocalHost();
            HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
            LoginUser loginUser = getLoginUser();
            CompletableFuture<JSONObject> completableFuture1 = CompletableFuture.supplyAsync(() -> {
                log.info("=============@@@@@@@@@@@@@@@1.异步查询基本信息-身份信息@@@@@@@@@@@@@@@=============");
                JSONObject jsonObject = new JSONObject();
                try {
                    //基本信息-身份信息
                    String jgdm = ""; // 机构代码
                    String xb = ""; // 性别
                    String csrq = ""; // 出生日期
                    String csnf = ""; // 出生年份
                    String hyzk = ""; // 婚姻状况
                    String sjhm = ""; // 手机号码
                    String zzdh = ""; // 住宅电话
                    String xl = ""; // 学历
                    String txdz = ""; // 通讯地址
                    String xm1 = "";
                    Long timeConsumingStart_1 = System.currentTimeMillis();
                    Date date1 = new Date(timeConsumingStart_1);
                    //先查询信贷系统个人基本信息表
                    Khxxb grkhjbxx = iKhxxbService.getKhxxbOracle(zjhm);
                    if (grkhjbxx != null) {
                        xm1 = grkhjbxx.getCustName();
                        jgdm = grkhjbxx.getCreateBranchNo();
                        Grkhb grxx = iGrkhbService.getGrkhbOracle(grkhjbxx.getCustId());
                        if (grxx != null) {
                            xb = grxx.getGender() == null ? "" : iSysDictService.queryDictTextByKey("grkhxb", grxx.getGender());
                            csrq = grxx.getBirthday();
                            csnf = grxx.getBirthday() == null ? "" : grxx.getBirthday().substring(0, 4);
                            hyzk = grxx.getMarriage() == null ? "" : iSysDictService.queryDictTextByKey("credit_hyzk", grxx.getMarriage());
                            sjhm = grxx.getMobilePhone();
                            zzdh = grxx.getHomeTel();
                            xl = grxx.getHighestSchooling() == null ? "" : iSysDictService.queryDictTextByKey("credit_xl", grxx.getHighestSchooling());
                            txdz = grxx.getPostAddr();
                        }
                        if (StringUtils.isEmpty(xb)) {
                            if (zjhm.length() == 18) {
                                String sCardNum = zjhm.substring(16, 17);
                                if (Integer.parseInt(sCardNum) % 2 != 0) {
                                    xb = "男";
                                } else {
                                    xb = "女";
                                }
                            }
                        }
                    } else {
                        Long djksjmxStartHS = System.currentTimeMillis();
                        //查询贷记卡贷款数据明细表
                        QueryWrapper<Djkdksjmx> creditDjkdksjmx = new QueryWrapper<>();
                        creditDjkdksjmx.eq("zjhm", zjhm);
                        Djkdksjmx djkdksjmx = iDjkdksjmxService.getOne(creditDjkdksjmx, false);
                        Long djksjmxEndHS = System.currentTimeMillis();
                        if (djkdksjmx != null) {
                            xm1 = djkdksjmx.getKhmc();
                            jgdm = djkdksjmx.getYwjg();
                            if (StringUtils.isEmpty(xb)) {
                                xb = iSysDictService.queryDictTextByKey("sex", djkdksjmx.getXb().toString());
                            }
                            if (StringUtils.isEmpty(hyzk)) {
                                hyzk = iSysDictService.queryDictTextByKey("credit_hyzk", djkdksjmx.getHyzk().toString());
                            }
                            if (StringUtils.isEmpty(sjhm)) {
                                sjhm = djkdksjmx.getSjhm();
                            }
                            if (StringUtils.isEmpty(txdz)) {
                                txdz = djkdksjmx.getJtzz();
                            }
                            djksjmxEndHS = System.currentTimeMillis();
                        } else {
                            //查询表外贷款数据明细表
                            QueryWrapper<Bwdksjmx> creditBwdksjmx = new QueryWrapper<>();
                            creditBwdksjmx.eq("zjhm", zjhm);
                            Bwdksjmx bwdksjmx = iBwdksjmxService.getOne(creditBwdksjmx, false);
                            Long bwdksjmxEndHS = System.currentTimeMillis();
                            if (bwdksjmx != null) {
                                djksjmxStartHS = System.currentTimeMillis();
                                xm1 = bwdksjmx.getKhmc();
                                jgdm = bwdksjmx.getJgdm();
                                if (StringUtils.isEmpty(sjhm)) {
                                    sjhm = bwdksjmx.getLxfs();
                                }
                                if (StringUtils.isEmpty(txdz)) {
                                    txdz = bwdksjmx.getKhdz();
                                }
                                bwdksjmxEndHS = System.currentTimeMillis();
                            }
                        }
                    }
                    jsonObject.put("bgbh", bgbh);
                    jsonObject.put("zjhm", zjhm);
                    jsonObject.put("xm", xm1);
                    jsonObject.put("cxjgdm", loginUser.getOrgCode());
                    jsonObject.put("jgmc", iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", jgdm));
                    jsonObject.put("cxjgmc", iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", loginUser.getOrgCode()));
                    jsonObject.put("cxyy", cxyy);
                    jsonObject.put("cxyyShow", iSysDictService.queryDictTextByKey("cxyy", cxyy));
                    jsonObject.put("cxczyxm", loginUser.getRealname());
                    jsonObject.put("bgsj", DateUtil.formatDateTime("yyyy.MM.dd HH:mm:ss"));
                    jsonObject.put("xb", xb);
                    jsonObject.put("csrq", csrq);
                    jsonObject.put("csnf", csnf);
                    jsonObject.put("hyzk", hyzk);
                    jsonObject.put("sjhm", sjhm);
                    jsonObject.put("zzdh", zzdh); //住宅电话
                    jsonObject.put("xl", xl);     //学历
                    jsonObject.put("txdz", txdz); //通讯地址
                    //object.put("jbxx", jsonObject);
                    Long endTime = System.currentTimeMillis();
                    log.info("##############1.异步查询基本信息-身份信息##############-总耗时：" + (endTime - beginTime) / 1000 + " (s)");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jsonObject;
            });
            CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
                log.info("==============@@@@@@@@@@@@@@@2.异步信用报告查询-征信报告生成-保存查询记录@@@@@@@@@@@@@@@==============");
                try {
                    //生成一条查询记录
                    Cxjltz cxjlmxInsert = new Cxjltz();
                    cxjlmxInsert.setBgbh(bgbh);
                    cxjlmxInsert.setCxrq(new Timestamp(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyyMMdd"), "yyyyMMdd").getTime()));
                    cxjlmxInsert.setBcxrzjhm(zjhm);
                    cxjlmxInsert.setBcxrxm(xm);
                    cxjlmxInsert.setCzyjgdm(iSysDictService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "zzbz", loginUser.getOrgCode()));
                    cxjlmxInsert.setCxczy(loginUser.getUsername());
                    cxjlmxInsert.setCxyy(cxyy);
                    cxjlmxInsert.setCxczyxm(loginUser.getRealname());
                    cxjlmxInsert.setLrbz(1);
                    cxjlmxInsert.setLrr(loginUser.getUsername());
                    cxjlmxInsert.setLrsj(new Timestamp(System.currentTimeMillis()));
                    cxjlmxInsert.setCxrip(IPUtils.getIpAddr(request));
                    iCxjltzService.save(cxjlmxInsert);
                    Long endTime = System.currentTimeMillis();
                    log.info("##############2.异步信用报告查询-征信报告生成-保存查询记录##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            QueryWrapper<Dkkhglrgl> creditDkkhglrgl = new QueryWrapper<>();
            creditDkkhglrgl.eq("jkrzjhm", zjhm);
            List<Dkkhglrgl> dkkhglrgl = iDkkhglrglService.list(creditDkkhglrgl);
            CompletableFuture<JSONObject> completableFuture3 = CompletableFuture.supplyAsync(() -> {
                log.info("==============@@@@@@@@@@@@@@@3.异步信用报告查询-征信报告生成-关联人信息==============");
                JSONObject jsonObject = new JSONObject();
                try {
                    //基本信息-关联人信息
                    for (Dkkhglrgl form : dkkhglrgl) {
                        form.setZjlxDictText(iSysDictService.queryDictTextByKey("dkjkpt_zjlx", form.getGlrzjlx().toString()));
                        form.setGllxDictText(iSysDictService.queryDictTextByKey("dkjkpt_glgx", form.getGlgx().toString()));
                    }
                    jsonObject.put("glrTable", dkkhglrgl);
                    List<Dkkhglrgl> dkkhglrgl2 = new ArrayList<>();
                    QueryWrapper<Dkkhglrgl> creditDkkhglrgl1 = new QueryWrapper<>();
                    creditDkkhglrgl1.eq("glrzjhm", zjhm);
                    List<Dkkhglrgl> khglrgl = iDkkhglrglService.list(creditDkkhglrgl1);
                    for (Dkkhglrgl form : khglrgl) {
                        form.setZjlxDictText(iSysDictService.queryDictTextByKey("dkjkpt_zjlx", form.getGlrzjlx().toString()));
                        form.setGllxDictText(iSysDictService.queryDictTextByKey("dkjkpt_glgx", form.getGlgx().toString()));
                        String jkrzjhm2 = form.getJkrzjhm();
                        String glrzjhm2 = form.getGlrzjhm();
                        if (dkkhglrgl.size() > 0) {
                            for (Dkkhglrgl record : dkkhglrgl) {
                                String jkrzjhm = record.getJkrzjhm();
                                String glrzjhm = record.getGlrzjhm();
                                // 若`借款人证件号`等于`关联人证件号`且`关联人证件号`等于`借款人证件号`，则剔除该条记录
                                if (!(jkrzjhm2.equalsIgnoreCase(glrzjhm) && glrzjhm2.equalsIgnoreCase(jkrzjhm))) {
                                    dkkhglrgl2.add(form);
                                    break;
                                }
                            }
                        } else {
                            dkkhglrgl2.add(form);
                        }
                    }
                    jsonObject.put("glrTable2", dkkhglrgl2);
                    Long endTime = System.currentTimeMillis();
                    log.info("##############3.异步信用报告查询-征信报告生成-关联人信息##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jsonObject;
            });

            CompletableFuture<JSONObject> completableFuture4 = CompletableFuture.supplyAsync(() -> {
                log.info("==============@@@@@@@@@@@@@@@4.异步信用报告查询-征信报告生成-信用提示信息@@@@@@@@@@@@@@@==============");
                JSONObject jsonObject = new JSONObject();
                try {
                   /* Long timeConsumingStart_4 = System.currentTimeMillis();
                    Date date1 = new Date(timeConsumingStart_4);
                    log.info("信用报告查询-征信报告生成-信用提示信息-开始："+ simpleDateFormat.format(date1));*/
                    //信息概要-信用提示
                    List<TmpCreditXytssjVO> tmpCreditXytssj = iCreditReportQueryService.getXytssjOracle(zjhm);
                    tmpCreditXytssj = listToDictUtil.parseDictText(tmpCreditXytssj);
                    jsonObject.put("xytsTable", tmpCreditXytssj);
                    Long endTime = System.currentTimeMillis();
                    log.info("##############4.异步信用报告查询-征信报告生成-信用提示信息##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jsonObject;
            });

            JSONArray qtxxgyJSON = new JSONArray();
            CompletableFuture<JSONArray> completableFuture5 = CompletableFuture.supplyAsync(() -> {
                log.info("==============@@@@@@@@@@@@@@@5.异步信用报告查询-征信报告生成-其他信息概要@@@@@@@@@@@@@@@==============");
                JSONArray qtxxgyJSON5 = new JSONArray();
                try {
                    //信息概要-其他信息概要
                    //判断本人或者关联人是否有福祥便民卡
                    //便民卡判断逻辑：卡档案中卡类型为20的记录，卡状态为正常（20）
                    //StringBuilder sb = new StringBuilder();
                    //int xh = 1;
                    List<Kjbxx> cbscCardList1 = iKjbxxService.getBmkxxOracle(zjhm);
                    for (Kjbxx form : cbscCardList1) {
                        StringBuilder sb = new StringBuilder();
                        String ywjgdm = form.getIssueBranch().toString();
                        if ("095".equals(qydm)) {
                            ywjgdm = "0" + form.getIssueBranch();
                        }
                        if (ywjgdm.length() == 4) {
                            //因`cbsc_card`卡基本信息表内`issue_branch`发卡机构下发数据缺失机构代码前面第一个0，故这里手工补一个0在前面
                            ywjgdm = "0" + ywjgdm;
                        }
                        sb/*.append(xh++)*/.append("被查询人本人在").append(iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", ywjgdm)).append("有福祥便民卡，卡号为").append(form.getNo()).append("。");
                        qtxxgyJSON5.add(sb.toString());
                    }
                    for (Dkkhglrgl form : dkkhglrgl) {
                        String glrzjhm = form.getGlrzjhm();
                        String glrxm = form.getGlrxm();
                        List<Kjbxx> cbscCardList2 = iKjbxxService.getBmkxxOracle(glrzjhm);
                        for (Kjbxx record : cbscCardList2) {
                            StringBuilder sb = new StringBuilder();
                            String ywjgdm = record.getIssueBranch().toString();
                            if ("095".equals(qydm)) {
                                ywjgdm = "0" + record.getIssueBranch();
                            }
                            if (ywjgdm.length() == 4) {
                                //因`cbsc_card`卡基本信息表内`issue_branch`发卡机构下发数据缺失机构代码前面第一个0，故这里手工补一个0在前面
                                ywjgdm = "0" + ywjgdm;
                            }
                            sb/*.append(xh++)*/.append("被查询人的关联人").append(glrxm).append("在").append(iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", ywjgdm)).append("有福祥便民卡，卡号为").append(record.getNo()).append("。");
                            qtxxgyJSON5.add(sb.toString());
                        }
                    }
                    Long endTime = System.currentTimeMillis();
                    log.info("##############5.异步信用报告查询-征信报告生成-其他信息概要##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return qtxxgyJSON5;
            });

            CompletableFuture<JSONArray> completableFuture6 = CompletableFuture.supplyAsync(() -> {
                JSONArray qtxxgyJSON6 = new JSONArray();

                log.info("==============@@@@@@@@@@@@@@@6.异步信用报告查询-征信报告生成-本人/关联人是否被纳入黑名单@@@@@@@@@@@@@@@==============");
                try {
                    //int xh = 1;
                    //本人/关联人是否被纳入黑名单
                    StringBuilder brhmdxx = getHmdxx(zjhm);
                    if (brhmdxx.length() > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb/*.append(xh++)*/.append("被查询人本人").append(brhmdxx);
                        qtxxgyJSON6.add(sb.toString());
                    }
                    for (Dkkhglrgl form : dkkhglrgl) {
                        StringBuilder glrhmdxx = getHmdxx(form.getGlrzjhm());
                        if (glrhmdxx.length() > 0) {
                            StringBuilder sb = new StringBuilder();
                            sb/*.append(xh++)*/.append("被查询人关联人").append(form.getGlrxm()).append(glrhmdxx);
                            qtxxgyJSON6.add(sb.toString());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTime = System.currentTimeMillis();
                log.info("##############6.异步信用报告查询-征信报告生成-本人/关联人是否被纳入黑名单##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");

                return qtxxgyJSON6;
            });

            CompletableFuture<JSONArray> completableFuture7 = CompletableFuture.supplyAsync(() -> {
                log.info("==============@@@@@@@@@@@@@@@7.异步信用报告查询-征信报告生成-本人/关联人是否被起诉、执行@@@@@@@@@@@@@@@==============");
                JSONArray qtxxgyJSON7 = new JSONArray();
                try {
                    int xh = 1;
                    //本人/关联人是否被起诉、执行
                    StringBuilder sszxxx = getSszxxx(zjhm);
                    if (sszxxx.length() > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(xh++).append(".被查询人本人").append(sszxxx);
                        qtxxgyJSON7.add(sb.toString());
                    }
                    for (Dkkhglrgl form : dkkhglrgl) {
                        StringBuilder glrsszxxx = getSszxxx(form.getGlrzjhm());
                        if (glrsszxxx.length() > 0) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(xh++).append(".被查询人关联人").append(form.getGlrxm()).append(glrsszxxx);
                            qtxxgyJSON7.add(sb.toString());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTime = System.currentTimeMillis();
                log.info("##############7.异步信用报告查询-征信报告生成-本人/关联人是否被起诉、执行##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");

                return qtxxgyJSON7;
            });

            CompletableFuture<JSONObject> completableFuture8 = CompletableFuture.supplyAsync(() -> {
                log.info("==============@@@@@@@@@@@@@@@8.异步信用报告查询-征信报告生成-本人/关联人是否有同名表外贷款@@@@@@@@@@@@@@@==============");
                JSONObject jsonObject = new JSONObject();
                JSONArray qtxxgyJSON8 = new JSONArray();

                try {
                    /**
                     * 本人/关联人是否有同名表外贷款
                     * 表外贷款数据中可能存在没有证件号码或者证件类型为0的数据
                     * 贷款日期-出生日期大于等于18岁的才核实
                     */
                    String csnf = "";
                    //int xh = 1;
                    //获取出生日期
                    if (zjhm.length() == 18) {
                        csnf = zjhm.substring(6, 10);
                    }
                    Integer csnfInt = null;
                    int nf = 18;
                    //查询参数
                    QueryWrapper<Csgl> creditCsgl = new QueryWrapper<>();
                    creditCsgl.eq("csbm", "P00003");
                    Csgl nfcsgl = iCsglService.getOne(creditCsgl, false);
                    if (nfcsgl != null) {
                        try {
                            nf = Integer.parseInt(nfcsgl.getCsz());
                            csnfInt = Integer.parseInt(csnf);
                        } catch (Throwable tx) {
                            tx.printStackTrace();
                        }
                    }
                    //疑似不良（0 否 1 是）
                    int ysbl = 0;
                    List<Bwdksjmx> bwdksjmxList = new ArrayList<>();
                    bwdksjmxList = iBwdksjmxService.queryBwtmdkOracle(xm, csnf, String.valueOf(nf));
                    List<Bwdksjmx> bwdksjmxListFilter = new ArrayList<>();
                    for (int i = 0; i < bwdksjmxList.size(); i++) {
                        Bwdksjmx bwdksjmx = bwdksjmxList.get(i);
                        Date dkrq = bwdksjmx.getDkrq();
                        if (dkrq != null && csnfInt != null) {
                            String dkrqStr = DateUtil.format(dkrq, "yyyy");
                            Integer dkrqInt = Integer.parseInt(dkrqStr);
                            if (dkrqInt - csnfInt >= nf) {
                                bwdksjmxListFilter.add(bwdksjmx);
                            }
                        }
                    }
                    JSONObject checkObject = new JSONObject();
                    //System.out.println("# 疑似不良 同名 表外贷款数据明细："+bwdksjmx);
                    if (bwdksjmxListFilter.size() > 0) {
                        ysbl = 1;
                        StringBuilder sb = new StringBuilder();
                        sb/*.append(xh++)*/.append("被查询人本人").append("有同名表外贷款，需要客户经理核实是否为同一人。");
                        qtxxgyJSON8.add(sb.toString());
                        JSONObject check = check(xm, zjhm, bgbh, loginUser.getOrgCode(), csnf, "", bwtmdk);
                        checkObject.put("bcxbrBwtmDkmx", check);
                    }
                    for (Dkkhglrgl form : dkkhglrgl) {
                        String glrxm = form.getGlrxm();
                        bwdksjmxList = iBwdksjmxService.queryBwtmdkOracle(glrxm, csnf, String.valueOf(nf));
                        bwdksjmxListFilter = new ArrayList<>();
                        for (int i = 0; i < bwdksjmxList.size(); i++) {
                            Bwdksjmx bwdksjmx = bwdksjmxList.get(i);
                            Date dkrq = bwdksjmx.getDkrq();
                            if (dkrq != null && csnfInt != null) {
                                String dkrqStr = DateUtil.format(dkrq, "yyyy");
                                Integer dkrqInt = Integer.parseInt(dkrqStr);
                                if (dkrqInt - csnfInt >= nf) {
                                    bwdksjmxListFilter.add(bwdksjmx);
                                }
                            }
                        }
                        if (bwdksjmxListFilter.size() > 0) {
                            ysbl = 1;
                            StringBuilder sb = new StringBuilder();
                            sb/*.append(xh++)*/.append("被查询人关联人").append(glrxm).append("有同名表外贷款，需要客户经理核实是否为同一人。");
                            qtxxgyJSON8.add(sb.toString());
                            JSONObject check = check(xm, zjhm, bgbh, loginUser.getOrgCode(), csnf, glrxm, bwtmdk);
                            checkObject.put("bcxglrBwtmDkmx", check);
                        }
                    }
                    jsonObject.put("ysbl", ysbl);
                    jsonObject.put("bwtmdk", checkObject);
                    jsonObject.put("qtxxgy", qtxxgyJSON8);
                    //object.put("xxgy", jsonObject);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTime = System.currentTimeMillis();
                log.info("##############8.异步信用报告查询-关联人是否有同名表外贷款##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");

                return jsonObject;
            });

            CompletableFuture<JSONArray> completableFuture9 = CompletableFuture.supplyAsync(() -> {
                JSONArray qtxxgyJSON9 = new JSONArray();
                log.info("==============@@@@@@@@@@@@@@@9.异步信用报告查询-征信报告生成-本人/关联人是否有收本挂息记录@@@@@@@@@@@@@@@==============");
                try {
                    //int xh = 1;
                    //本人/关联人是否有收本挂息记录
                    StringBuilder shgxxx = getSbgxxx(zjhm);
                    if (shgxxx.length() > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb/*.append(xh++)*/.append("被查询人本人").append(shgxxx).append("。");
                        qtxxgyJSON9.add(sb.toString());
                    }
                    for (Dkkhglrgl form : dkkhglrgl) {
                        StringBuilder glrshgxxx = getSbgxxx(form.getGlrzjhm());
                        if (glrshgxxx.length() > 0) {
                            StringBuilder sb = new StringBuilder();
                            sb/*.append(xh++)*/.append("被查询人关联人").append(form.getGlrxm()).append(glrshgxxx).append("。");
                            qtxxgyJSON9.add(sb.toString());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTime = System.currentTimeMillis();
                log.info("##############9.异步信用报告查询-本人/关联人是否有收本挂息记录##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");

                return qtxxgyJSON9;
            });

            CompletableFuture<JSONObject> completableFuture10 = CompletableFuture.supplyAsync(() -> {
                log.info("==============@@@@@@@@@@@@@@@10.异步信用报告查询-征信报告生成-贷款数据@@@@@@@@@@@@@@@==============");
                JSONObject jsonObject = new JSONObject();

                try {
                    //贷款数据
                    //总累计欠息次数
                    int zljqxcs = 0;
                    //三 信贷交易信息明细/（一） 贷款/贷款用信明细/贷款金额(合计)&&核心余额(合计)
                    BigDecimal dkjeSum = new BigDecimal(0);
                    BigDecimal hxyeSum = new BigDecimal(0);
                    Long beginTime1 = System.currentTimeMillis();
                    List<TmpCreditDksjVO> tmpCreditDksj = iCreditReportQueryService.getDksjmxOracle(zjhm);
                    Long endTime1 = System.currentTimeMillis();
                    log.info("##############10.异步信用报告查询-贷款数据##############查询贷款数据耗时：" + (endTime1 - beginTime1) / 1000 + " (s)");

                    List<Qxmx> listQxmx = iCreditReportQueryService.getqxmxOracle(zjhm);
                    Long endTime2 = System.currentTimeMillis();

                    log.info("##############10.异步信用报告查询-贷款数据##############查询欠息明细耗时：" + (endTime2 - endTime1) / 1000 + " (s)");

                    for (TmpCreditDksjVO dksj : tmpCreditDksj) {
                        int zhlx = Integer.parseInt(dksj.getZhlx());
                        dksj.setWjflbzShow(dksj.getWjflbz() == null ? "\\" : iSysDictService.queryDictTextByKey("wjflbz", dksj.getWjflbz()));
                        dksj.setDkzhztShow(dksj.getZhzt() == null ? "\\" : iSysDictService.queryDictTextByKey("dkzhzt", dksj.getZhzt()));
                        dksj.setDkzlShow(zhlx == 1 ? dksj.getYwzl() == null ? "\\" : iSysDictService.queryDictTextByKey("dkzl", dksj.getYwzl()) : "已核销贷款");
                        String zxqxr = dksj.getZxqxr();
                        if (!StringUtils.isEmpty(zxqxr) && zxqxr.length() == 8) {
                            dksj.setZxqxrShow(DateUtil.formatDateTime("yyyy.MM.dd", DateUtil.parseDateFormat(zxqxr, "yyyyMMdd")));
                        } else {
                            dksj.setZxqxrShow(zxqxr == null ? "\\" : zxqxr);
                        }
                        if (dksj.getHxye() != null) {
                            dksj.setHxye(dksj.getHxye().abs());
                        }
                        BigDecimal dkje = new BigDecimal(dksj.getDkje() == null ? 0 : dksj.getDkje().intValue());
                        BigDecimal hxye = new BigDecimal(dksj.getHxye() == null ? 0 : dksj.getHxye().intValue());
                        dkjeSum = dkjeSum.add(dkje);
                        hxyeSum = hxyeSum.add(hxye);
                        int ljqxcs = dksj.getLjqxcs() == null ? 0 : dksj.getLjqxcs();
                        zljqxcs += ljqxcs; //总累计欠息次数
                        List<Qxmx> listQxmxAcctNo = listQxmx.stream().filter(item -> item.getAcctNo().equalsIgnoreCase(dksj.getDkzh())).collect(Collectors.toList());
                        dksj.setDkqxdjbTable(listQxmxAcctNo);
                    }
                    Long endTime3 = System.currentTimeMillis();

                    Date sjgxr = new Timestamp(System.currentTimeMillis());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    sjgxr = sdf.parse(iSysLogService.zrcksjrq());//Oracle
                    Long endTime4 = System.currentTimeMillis();

                    log.info("##############10.异步信用报告查询-贷款数据##############查询数据日期耗时：" + (endTime4 - endTime3) / 1000 + " (s)");

                    //System.out.println("系统日志打印`总累计欠息次数`=> " + zljqxcs);
                    jsonObject.put("zljqxcs", zljqxcs);
                    tmpCreditDksj = listToDictUtil.parseDictText(tmpCreditDksj);
                    jsonObject.put("dksjTable", tmpCreditDksj);
                    jsonObject.put("dksjTable2", tmpCreditDksj);
                    jsonObject.put("dkjeSum", dkjeSum);
                    jsonObject.put("hxyeSum", hxyeSum);
                    jsonObject.put("sjgxrShow", DateUtil.format(new Date(sjgxr.getTime()), "yyyy年MM月dd日"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTime = System.currentTimeMillis();
                log.info("##############10.异步信用报告查询-贷款数据##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");

                return jsonObject;
            });

            CompletableFuture<JSONObject> completableFuture11 = CompletableFuture.supplyAsync(() -> {
                log.info("==============@@@@@@@@@@@@@@@11.异步信用报告查询-征信报告生成-贷记卡@@@@@@@@@@@@@@@==============");
                JSONObject jsonObject = new JSONObject();
                try {
                    //贷记卡
                    QueryWrapper<Djkdksjmx> creditDjkdksjmx = new QueryWrapper<>();
                    creditDjkdksjmx.eq("zjhm", zjhm);
                    List<Djkdksjmx> djkdksjmx = iDjkdksjmxService.list(creditDjkdksjmx);
                    for (Djkdksjmx form : djkdksjmx) {
                        form.setJgmc(iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", form.getYwjg()));
                        form.setKztbzShow(iSysDictService.queryDictTextByKey("djkzl", form.getKztbz()));
                        form.setKzlShow(iSysDictService.queryDictTextByKey("kzl", form.getKzl()));
                        form.setYgxm(iSysDictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", form.getYggh()));
                        form.setTzyeLrsj(DateUtil.formatDateTime("yyyy年MM月dd日", form.getLrsj()));
                        if (form.getTzye() == null) {
                            form.setTzye(new BigDecimal(0));
                        }
                        //查询贷记卡逾期期数
                        QueryWrapper<Djkxxgl> erpBasDjkblxx = new QueryWrapper<>();
                        erpBasDjkblxx.eq("tjyf", new Timestamp(DateUtil.parseDateFormat(DateUtil.format(DateUtil.getFirstday_Month(new Date(), -1), "yyyyMM") + "01", "yyyyMMdd").getTime()));//获取上月月份的数据
                        erpBasDjkblxx.eq("acct_no", form.getKh());
                        Djkxxgl djkblxx = iDjkxxglService.getOne(erpBasDjkblxx, false);
                        if (djkblxx != null) {
                            form.setYqqs(djkblxx.getYqqs().intValue());
                            form.setYqqsLrsj(DateUtil.formatDateTime("yyyy年MM月dd日", djkblxx.getLrsj()));
                        } else {
                            form.setYqqs(0);
                            form.setYqqsLrsj(DateUtil.format(DateUtil.getLastday_Month(new Date(), -1), "yyyy年MM月dd日"));
                            erpBasDjkblxx = new QueryWrapper<>();
                            erpBasDjkblxx.eq("tjyf", new Timestamp(DateUtil.parseDateFormat(DateUtil.format(DateUtil.getFirstday_Month(new Date(), -1), "yyyyMM") + "01", "yyyyMMdd").getTime()));//获取上月月份的数据
                            erpBasDjkblxx.isNotNull("lrsj");
                            Djkxxgl djkblxx1 = iDjkxxglService.getOne(erpBasDjkblxx, false);
                            if (djkblxx != null) {
                                if (djkblxx1.getLrsj() != null) {
                                    form.setYqqsLrsj(DateUtil.formatDateTime("yyyy年MM月dd日", djkblxx.getLrsj()));
                                }
                            }
                        }
                    }

                    int no = 1;
                    JSONArray djkxxArray = new JSONArray();
                    DecimalFormat df = new DecimalFormat("#,###");
                    for (Djkdksjmx form : djkdksjmx) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(no).append(".").append(DateUtil.format(form.getFkrq(), "yyyy年MM月dd日")).append("，").append(form.getJgmc()).append("发放的贷记卡")
                                .append("，卡种").append(form.getKzlShow()).append("，").append("卡号").append(form.getKh()).append("，")
                                .append("推广员工").append(form.getYgxm()).append("，").append("卡状态").append(form.getKztbzShow()).append("，")
                                .append("授信额度").append(df.format(form.getSxje())).append("元，")
                                .append("截止").append(form.getTzyeLrsj()).append("已透支余额").append(df.format(form.getTzye())).append("元，")
                                .append("截止").append(form.getYqqsLrsj()).append("已逾期期数").append(form.getYqqs()).append("期。");
                        djkxxArray.add(sb.toString());
                        no++;
                    }
                    jsonObject.put("djkdksjms", djkxxArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTime = System.currentTimeMillis();
                log.info("##############11.异步信用报告查询-贷记卡##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");

                return jsonObject;
            });

            CompletableFuture<JSONObject> completableFuture12 = CompletableFuture.supplyAsync(() -> {
                log.info("==============@@@@@@@@@@@@@@@12.异步信用报告查询-征信报告生成-担保信息@@@@@@@@@@@@@@@==============");
                JSONObject jsonObject = new JSONObject();

                try {
                    //担保信息
                    HashSet set = new HashSet();
                    BigDecimal dbjeSum = new BigDecimal(0);
                    BigDecimal dbyeSum = new BigDecimal(0);
                    List<TmpCreditDbxxVO> dbxx = new ArrayList<>();
                    List<Dksj> dbxxHive = new ArrayList<>();
                    List<TmpCreditDbxxVO> dbxxOracle = iCreditReportQueryService.getdbxxOracle(zjhm);
                    for (TmpCreditDbxxVO form : dbxxOracle) {
                        set.add(form.getZjhm());
                        BigDecimal dbje = new BigDecimal(form.getDkje().intValue());
                        BigDecimal dbye = new BigDecimal(form.getHxye().intValue());
                        dbjeSum = dbjeSum.add(dbje);
                        dbyeSum = dbyeSum.add(dbye);
                        String zxqxr = form.getZxqxr();
                        if (!StringUtils.isEmpty(zxqxr) && zxqxr.length() == 8) {
                            String zxqxrShow = DateUtil.formatDateTime("yyyy.MM.dd", DateUtil.parseDateFormat(zxqxr, "yyyyMMdd"));
                            form.setZxqxr("".equalsIgnoreCase(zxqxrShow) ? "\\" : zxqxrShow);
                        } else {
                            form.setZxqxr(zxqxr == null ? "\\" : zxqxr);
                        }
                        if (form.getHxye() != null) {
                            form.setHxye(form.getHxye().abs());
                        }
                    }
                    jsonObject.put("dbrs", set.size());
                    jsonObject.put("dbjehj", dbjeSum);
                    jsonObject.put("dbyehj", dbyeSum);
                    jsonObject.put("dbxxTable", dbxx);
                    jsonObject.put("dbxxTable", dbxx);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTime = System.currentTimeMillis();
                log.info("##############12.异步信用报告查询-担保信##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");

                return jsonObject;
            });

            CompletableFuture<List<Cxjltz>> completableFuture13 = CompletableFuture.supplyAsync(() -> {
                log.info("==============@@@@@@@@@@@@@@@13.异步信用报告查询-征信报告生成-查询明细@@@@@@@@@@@@@@@==============");
                List<Cxjltz> cxjlmx = new ArrayList<>();
                try {
                    //查询记录（P00010：信用报告查询中查询记录明细的周期。 单位：月）
                    QueryWrapper<Csgl> creditCsgl = new QueryWrapper<>();
                    creditCsgl.eq("csbm", "P00010");
                    Csgl csgl = iCsglService.getOne(creditCsgl, false);
                    QueryWrapper<Cxjltz> creditCxjlmx = new QueryWrapper<>();
                    creditCxjlmx.eq("bcxrzjhm", zjhm);
                    creditCxjlmx.orderByDesc("cxrq");
                    creditCxjlmx.orderByDesc("bgbh");
                    if (csgl != null) {
                        long time = DateUtil.getFrontMonthTime(System.currentTimeMillis(), Integer.parseInt(csgl.getCsz()));
                        creditCxjlmx.ge("cxrq", new Timestamp(time));
                    }
                    cxjlmx = iCxjltzService.list(creditCxjlmx);
                    cxjlmx = listToDictUtil.parseDictText(cxjlmx);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTime = System.currentTimeMillis();
                log.info("##############13.异步信用报告查询-查询明细##############总耗时：" + (endTime - beginTime) / 1000 + " (s)");

                return cxjlmx;
            });

            JSONObject jbxx = completableFuture1.get();
            completableFuture2.get();
            JSONObject glrInfo = completableFuture3.get();
            JSONObject jsonObject1 = completableFuture4.get();
            JSONArray jsonArray5 = completableFuture5.get();
            JSONArray jsonArray6 = completableFuture6.get();
            JSONArray jsonArray7 = completableFuture7.get();
            JSONObject jsonObject2 = completableFuture8.get();
            JSONArray jsonArray8 = (JSONArray) jsonObject2.get("qtxxgy");
            JSONArray jsonArray9 = completableFuture9.get();
            JSONObject xdjymxTable = completableFuture10.get();
            JSONObject djkTable = completableFuture11.get();
            JSONObject dbxx = completableFuture12.get();
            List<Cxjltz> cxjlmxTable = completableFuture13.get();

            JSONArray jsonArrayQtxxgy = new JSONArray();
            if (jsonArray5!=null) {
                jsonArrayQtxxgy.addAll(jsonArray5);
            }
            if (jsonArray6!=null) {
                jsonArrayQtxxgy.addAll(jsonArray6);
            }
            if (jsonArray7!=null) {
                jsonArrayQtxxgy.addAll(jsonArray7);
            }
            if (jsonArray8!=null) {
                jsonArrayQtxxgy.addAll(jsonArray8);
            }
            if (jsonArray9!=null) {
                jsonArrayQtxxgy.addAll(jsonArray9);
            }
            JSONObject jsonObjec3 = new JSONObject();
            jsonObjec3.putAll(jsonObject1);
            jsonObjec3.putAll(jsonObject2);
            jsonObjec3.put("qtxxgy", jsonArrayQtxxgy);

            object.put("jbxx", jbxx);
            object.put("glrInfo", glrInfo);
            object.put("xxgy", jsonObjec3);
            object.put("bwtmdkList", bwtmdk);
            object.put("xdjymxTable", xdjymxTable);
            object.put("djkTable", djkTable);
            object.put("dbxx", dbxx);
            object.put("cxjlmxTable", cxjlmxTable);

            Long endTime = System.currentTimeMillis();
            date = new Date(endTime);
            log.info("信用报告查询-征信报告生成-结束时间：" + simpleDateFormat.format(date));
            log.info("信用报告查询-征信报告生成-总耗时：" + (endTime - beginTime) / 1000 + " (s)");

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("系统错误，请联系管理员处理！", throwable.getMessage());
        }
        return Result.ok(object);
    }

    /**
     * 疑似不良核查
     *
     * @param xm     客户姓名
     * @param zjhm   证件号码
     * @param bgbh   报告编号
     * @param cxjgdm 查询机构代码
     * @param csnf   出生年份
     * @param glrxm  关联人姓名
     * @return
     */
    public JSONObject check(String xm, String zjhm, String bgbh, String cxjgdm, String csnf, String glrxm, List<Bwdksjmx> bwtmdk) {
        JSONObject jsonObject = new JSONObject();
        try {
            int nf = 18;
            //贷款日期-出生日期大于等于18岁的才核实
            //获取出生日期
            if (zjhm.length() == 18) {
                csnf = zjhm.substring(6, 10);
            }
            //查询参数
            QueryWrapper<Csgl> creditCsgl = new QueryWrapper<>();
            creditCsgl.eq("csbm", "P00003");
            Csgl nfcsgl = iCsglService.getOne(creditCsgl, false);
            if (nfcsgl != null) {
                try {
                    nf = Integer.parseInt(nfcsgl.getCsz());
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
            //查询表外同名贷款
            String khmc = glrxm;
            if (StringUtils.isEmpty(glrxm)) {
                khmc = xm;
            }
            List<Bwdksjmx> bwdksjmx = iBwdksjmxService.queryBwtmdkOracle(khmc, csnf, String.valueOf(nf));
            for (Bwdksjmx form : bwdksjmx) {
                form.setJgmc(iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", form.getJgdm()));
                form.setHxye(form.getHxye() == null ? new BigDecimal(0) : new BigDecimal(form.getHxye().abs().toString()));
                form.setZrbwlxShow(iSysDictService.queryDictTextByKey("zrbw", form.getZrbwlx()));
                form.setVerificationResults("");
                bwtmdk.add(form);
            }
            //System.out.println("#############################信用报告查询：check：关联人姓名："+glrxm);
            //System.out.println("#############################信用报告查询：check：同名表外贷款明细 [共 "+bwdksjmx.size()+" 条记录,明细如下]=>"+bwdksjmx);
            jsonObject.put("glrxm", glrxm);
            jsonObject.put("tmbwdkmxTable", bwdksjmx);
            jsonObject.put("reportNo", bgbh);
            jsonObject.put("xm", xm);
            jsonObject.put("cxjgdm", cxjgdm);
            jsonObject.put("cxjgmc", iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", cxjgdm));
            jsonObject.put("cxsjShow", DateUtil.formatDateTime("yyyy年MM月dd日"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 获取黑名单相关信息
     *
     * @param zjhm 证件号码
     * @return
     */
    private StringBuilder getHmdxx(String zjhm) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        StringBuilder nryy = new StringBuilder();
        Date djrq = null;
        String lrr = ""; // bljlxwms
        QueryWrapper<Hmdgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm", zjhm);
        queryWrapper.eq("yxbz", 1);
        List<Hmdgl> hmdglList = iHmdglService.list(queryWrapper);
        int i = 0;
        int size = hmdglList.size();
        for (Hmdgl form : hmdglList) {
            if (i == 0) {//第一条记录
                djrq = form.getDjrq();
                lrr = form.getLrr();
            }
            String bljlxwms = iSysDictService.queryDictTextByKey("bljlxwms", String.valueOf(form.getBljlxwms().intValue()));
            if (i == (size - 1)) {
                //最后一条记录
                nryy.append(bljlxwms).append("。");
            } else {
                nryy.append(bljlxwms).append("、");
            }
            i++;
        }

        String jgmc = "";
        StringBuilder sb = new StringBuilder();
        if (hmdglList.size() > 0) {
            if ("system".equals(lrr)) {
                lrr = "系统";
            } else {
                String userId = iSysDictService.queryTableDictTextByKey("sys_user", "id", "username", lrr);
                String lrrjgdm = iSysDictService.queryTableDictTextByKey("sys_user_depart", "dep_id", "user_id", userId);
                jgmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", lrrjgdm);
            }
            String llrName = iSysDictService.queryTableDictTextByKey("sys_user", "realname", "username", lrr);

            sb.append("于").append(DateUtil.formatDateTime("yyyy/MM/dd", new Date(djrq.getTime()))).append("被").append(jgmc == null ? "" : jgmc).append(llrName == null ? "" : llrName)
                    .append("纳入黑名单，不良行为描述是：").append(nryy);
        }
        return sb;
    }

    /**
     * 获取诉讼执行相关信息
     *
     * @param zjhm 证件号码
     * @return
     */
    private StringBuilder getSszxxx(String zjhm) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        StringBuilder sb = new StringBuilder();
        BigDecimal ssjeSum = new BigDecimal(0);
        Date qsrq = null;
        String ssywjg = "";
        //是否被起诉、执行
        QueryWrapper<Ssgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm", zjhm);
        List<Ssgl> ssglList = iSsglService.list(queryWrapper);
        int i = 0;
        for (Ssgl form : ssglList) {
            double ssje = form.getJe() == null ? 0d : form.getJe().doubleValue();
            ssjeSum = ssjeSum.add(new BigDecimal(ssje));
            if (i == 0) {
                qsrq = form.getQsrq();
                ssywjg = form.getYwjg();
            }
        }
        if (ssjeSum.compareTo(new BigDecimal(0)) > 0) {
            int zxcs = 0; //执行次数
            BigDecimal yzxjeSum = new BigDecimal(0); //累计已执行金额
            BigDecimal dzxjeSum = new BigDecimal(0); //累计待执行金额
            //查询执行信息
            QueryWrapper<Zxgl> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("zjhm", zjhm);
            List<Zxgl> zxglList = iZxglService.list(queryWrapper1);
            i = 0;
            for (Zxgl form : zxglList) {
                double yzxbj = form.getZxbj() == null ? 0d : form.getZxbj().doubleValue(); //已执行本金
                double yzxlx = form.getZxlx() == null ? 0d : form.getZxlx().doubleValue(); //已执行利息
                double dzxje = form.getDzxje() == null ? 0d : form.getDzxje().doubleValue(); //待执行金额
                yzxjeSum = yzxjeSum.add(new BigDecimal(yzxbj)).add(new BigDecimal(yzxlx));
                if (i == 0) {
                    dzxjeSum = dzxjeSum.add(new BigDecimal(dzxje));
                }
                zxcs++;
            }
            if (qsrq != null) {
                sb.append("于").append(DateUtil.formatDateTime("yyyy.MM.dd", new Date(qsrq.getTime()))).append("，");
            }
            sb.append("被我行").append(iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", ssywjg))
                    .append("起诉，涉及金额`").append(ssjeSum).append("`元，")
                    .append("执行次数：").append(zxcs).append("次")
                    .append("，累计已执行金额").append(yzxjeSum).append("元")
                    .append("，待执行金额").append(dzxjeSum).append("元。");
        }
        return sb;
    }

    /**
     * 获取收本挂息记录
     *
     * @param zjhm 证件号码
     * @return
     */
    private StringBuilder getSbgxxx(String zjhm) {
        StringBuilder stringBuilder = new StringBuilder();
        int sbgxje = 0;
        int sbgxye = 0;
        //是否有收本挂息记录
        QueryWrapper<Sbgxmx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm", zjhm);
        List<Sbgxmx> sbgxmxList = iSbgxmxService.list(queryWrapper);
        if (sbgxmxList.size() > 0) {
            for (Sbgxmx form : sbgxmxList) {
                sbgxje = form.getGxje().intValue();
                sbgxye = form.getGxye().intValue();
                stringBuilder.append("于").append(DateUtil.formatDateTime("yyyy.MM.dd", form.getTbrq())).append("在`")
                        .append(iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", form.getYwjg()))
                        .append("`有收本挂息行为，挂息金额：").append(sbgxje)
                        .append("元，挂息余额：").append(sbgxye).append("元").append("。");
            }
        }
        return stringBuilder;
    }


    /**
     * 一键准入报告-打印-查询信息
     * 法人行社所在地名称，类似于临武、浏阳；报告序号；查询机构名称；查询操作员用户名
     *
     * @return
     */
    @AutoLog(value = "一键准入报告-打印-查询信息")
    @ApiOperation(value = "一键准入报告-打印-查询信息", notes = "一键准入报告-打印-查询信息")
    @GetMapping(value = "/yjzrbgquery")
    public Result<?> query() {
        try {
            JSONObject object = new JSONObject();
            String AREA_NAME = "";
            QueryWrapper<SysDic> sysDicQueryWrapper = new QueryWrapper<>();
            sysDicQueryWrapper.eq("code", "101002");
            SysDic sysDic = sysDicService.getOne(sysDicQueryWrapper, false);
            if (sysDic != null && org.apache.commons.lang3.StringUtils.isNotBlank(sysDic.getValue())) {
                String value = sysDic.getValue();
                if (value.contains("农商行"))
                    AREA_NAME = sysDic.getValue().replace("农商行", "");
                if (value.contains("农村商业银行"))
                    AREA_NAME = sysDic.getValue().replace("农村商业银行", "");
            }
            String Q_BRANCH_NAME = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", getLoginUser().getOrgCode());
            String Q_USER_NAME = getLoginUser().getRealname();
            int BGXH = 0;
            QueryWrapper<CamsZcsxYjzrbgbh> bgbhQueryWrapper = new QueryWrapper<>();
            String userJgdm = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "zzbz", getLoginUser().getOrgCode());
            bgbhQueryWrapper.eq("jgdm", userJgdm);
            bgbhQueryWrapper.eq("cxrq", new Timestamp(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyyMMdd"), "yyyyMMdd").getTime()));
            CamsZcsxYjzrbgbh yjzrbgbh = camsZcsxYjzrbgbhService.getOne(bgbhQueryWrapper, false);
            if (yjzrbgbh != null) {
                BGXH = yjzrbgbh.getXh() + 1;
                CamsZcsxYjzrbgbh update = new CamsZcsxYjzrbgbh();
                update.setXh(BGXH);
                UpdateWrapper<CamsZcsxYjzrbgbh> bgbhUpdateWrapper = new UpdateWrapper<>();
                bgbhUpdateWrapper.eq("jgdm", userJgdm);
                bgbhUpdateWrapper.eq("cxrq", new Timestamp(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyyMMdd"), "yyyyMMdd").getTime()));
                camsZcsxYjzrbgbhService.update(update, bgbhUpdateWrapper);
            } else {
                CamsZcsxYjzrbgbh insert = new CamsZcsxYjzrbgbh();
                insert.setJgdm(userJgdm);
                insert.setCxrq(new Timestamp(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyyMMdd"), "yyyyMMdd").getTime()));
                insert.setXh(BGXH);
                camsZcsxYjzrbgbhService.save(insert);
            }
            object.put("AREA_NAME", AREA_NAME);
            object.put("Q_BRANCH_NO", userJgdm);
            object.put("Q_BRANCH_NAME", Q_BRANCH_NAME);
            object.put("Q_USER_NAME", Q_USER_NAME);
            object.put("BGXH", BGXH);
            return Result.ok(object);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("一键准入报告-打印-查询信息失败！" + e.getMessage());
            return Result.error("一键准入报告-打印-查询信息失败，请联系管理员！");
        }
    }

    /**
     * 一键准入报告-打印-数据生成
     * 一 基本信息 / 四 信息概要 / 五 信贷交易明细
     *
     * @param zjhm
     * @return
     */
    @AutoLog(value = "一键准入报告-打印-查询信息")
    @ApiOperation(value = "一键准入报告-打印-查询信息", notes = "一键准入报告-打印-查询信息")
    @GetMapping(value = "/yjzrbgreport")
    public Result<?> report(@RequestParam(name = "name", required = true) String name,
                            @RequestParam(name = "zjhm", required = true) String zjhm) {
        JSONObject object = new JSONObject();
        try {
            service.CreditInitExtractIdap(zjhm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //获取户号编码
            QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
            nhxqQueryWrapper.eq("zjhm", zjhm);
            Nhxq nhxx = nhxqService.getOne(nhxqQueryWrapper, false);
            //Module1. 一 基本信息
            //No1.身份信息
            /*CompletableFuture<List<JbxxSfxx>> JbxxSfxxCompletableFuture = CompletableFuture.supplyAsync(() -> {
                List list = null;
                try {
                    List<JbxxSfxx> jbxxSfxxList = iCreditReportQueryService.getJbxxSfxx(zjhm);
                    for (JbxxSfxx sfxx : jbxxSfxxList) {
                        //TODO 农户信息内未包含住宅地址，暂定都为未知
                        sfxx.setZzdh("/");
                        //出生日期格式化处理
                        sfxx.setCsrq(sfxx.getCsrq().length() == 8 ? DateUtils.date2Str(DateUtils.str2Date(sfxx.getCsrq(), new SimpleDateFormat("yyyyMMdd")), new SimpleDateFormat("yyyy.MM.dd")) : sfxx.getCsrq());
                        //数据字典转换
						*//*sfxx.setXb(sfxx.getXb() == null ? "" : iSysDictService.queryDictTextByKey("sex", sfxx.getXb()));
						sfxx.setHyzk(sfxx.getHyzk() == null ? "" : iSysDictService.queryDictTextByKey("hyzk", sfxx.getHyzk()));
						sfxx.setXl(sfxx.getXl() == null ? "" : iSysDictService.queryDictTextByKey("whcd", sfxx.getXl()));*//*
                    }
                    list = listToDictUtil.parseDictText(jbxxSfxxList);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("一键准入报告-打印-基本信息-身份信息-获取失败！");
                }
                return list;
            });*/
            //No2.关联人信息
            CompletableFuture<List<Nhxq>> JbxxGlrxxCompletableFuture = CompletableFuture.supplyAsync(() -> {
                List list = null;
                try {
                    if (nhxx != null && org.apache.commons.lang3.StringUtils.isNotBlank(nhxx.getZjhm())) {
                        LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                        lambdaQueryWrapper.eq(Nhxq::getHhbm, nhxx.getHhbm());
                        List<Nhxq> nhxqs = nhxqService.list(lambdaQueryWrapper);
//                        List<JbxxGlrxx> jbxxGlrxxList = iCreditReportQueryService.getJbxxGlrxx(zjhm, nhxx.getHhbm());
//                        for (JbxxGlrxx jbxxGlrxx : jbxxGlrxxList) {
//                            //TODO 暂定农户证件类型都为身份证
//                            jbxxGlrxx.setZjlx("身份证");
//                        }
                        list = listToDictUtil.parseDictText(nhxqs);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("一键准入报告-打印-一 基本信息-关联人信息-获取失败！");
                }
                return list;
            });
            //Module2. 二 核查内容
            //Module3. 三 业务情况
            //No1.存款信息
            //No2.现金流
            //No3.电子银行信息
            //Module4. 四 信息概要
            //No1.信用提示
            CompletableFuture<List<Xyts>> XytsCompletableFuture = CompletableFuture.supplyAsync(() -> {
                List list = null;
                try {
                    QueryWrapper<Xyts> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("zjhm", zjhm);
                    List<Xyts> xytsList = xytsService.list(queryWrapper);
                    list = listToDictUtil.parseDictText(xytsList);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("一键准入报告-打印-四 信息概要-信用提示-获取失败！");
                }
                return list;

            });
            //No2.其它信息概要
            CompletableFuture<JSONArray> qtxxgyCompletableFuture = CompletableFuture.supplyAsync(() -> {
                JSONArray jsonArray = new JSONArray();
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    // 被查询人
                    // P1.福祥便民卡
                    List<Kjbxx> cbscCardList = iKjbxxService.getBmkxxOracle(zjhm);
                    for (Kjbxx kjbxx : cbscCardList) {
                        stringBuilder = new StringBuilder();
                        String ywjgdm = kjbxx.getIssueBranch().toString();
                        if (ywjgdm.length() == 4) {
                            // TODO 卡基本信息表.发卡机构缺失首位数字0，在此补充
                            ywjgdm = "0" + ywjgdm;
                        }
                        String ywjgmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", ywjgdm);
                        stringBuilder.append("被查询人本人").append(ywjgmc.length() == 0 ? "" : "在" + ywjgmc).append("有福祥便民卡，卡号：").append(kjbxx.getNo() + "。");
                        jsonArray.add(stringBuilder.toString());
                    }
                    // P2.被查询人是否被起诉、执行
                    StringBuilder sszxxx = getSszxxx(zjhm);
                    if (sszxxx.length() > 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("被查询人本人").append(sszxxx);
                        jsonArray.add(stringBuilder.toString());
                    }
                    // P3.被查询人是否被纳入黑名单
                    StringBuilder brhmdxx = getHmdxx(zjhm);
                    if (brhmdxx.length() > 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("被查询人本人").append(brhmdxx);
                        jsonArray.add(stringBuilder.toString());
                    }
                    // P4.被查询人是否有收本挂息记录
                    StringBuilder sbgxxx = getSbgxxx(zjhm);
                    if (sbgxxx.length() > 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("被查询人本人").append(sbgxxx);
                        jsonArray.add(stringBuilder.toString());
                    }
                    // P5.被查询人是否有同名表外贷款
                    // TODO 表外贷款数据中 存在证件号码为空或证件类型为0的数据,贷款日期-出生日期大于等于18岁的才核实
                    int nf = 18;
                    String csnf = "";
                    Integer csnfInt = null;
                    if (zjhm.length() == 18) {
                        csnf = zjhm.substring(6, 10);
                    }
                    // 查询同名表外贷款核查起始年龄
                    QueryWrapper<Csgl> creditCsgl = new QueryWrapper<>();
                    creditCsgl.eq("csbm", "P00003");
                    Csgl nfcsgl = iCsglService.getOne(creditCsgl, false);
                    if (nfcsgl != null) {
                        try {
                            nf = Integer.parseInt(nfcsgl.getCsz());
                            csnfInt = Integer.parseInt(csnf);
                        } catch (Throwable tx) {
                            tx.printStackTrace();
                            log.error("一键准入报告-打印-查询同名表外贷款核查起始年龄失败！");
                        }
                    }
                    List<Bwdksjmx> bwdksjmxListFilter = new ArrayList<>();
                    List<Bwdksjmx> bwdksjmxList = iBwdksjmxService.queryBwtmdkOracle(name, csnf, String.valueOf(nf));
                    for (Bwdksjmx record : bwdksjmxList) {
                        Bwdksjmx bwdksjmx = new Bwdksjmx();
                        BeanUtil.copyPropertiesIgnoreNull(record, bwdksjmx);
                        Date dkrq = bwdksjmx.getDkrq();
                        if (dkrq != null && csnfInt != null) {
                            String dkrqStr = DateUtil.format(dkrq, "yyyy");
                            Integer dkrqInt = Integer.parseInt(dkrqStr);
                            if (dkrqInt - csnfInt >= nf) {
                                bwdksjmxListFilter.add(bwdksjmx);
                            }
                        }
                    }
                    if (bwdksjmxListFilter.size() > 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("被查询人本人").append("有同名表外贷款，需要客户经理核实是否为同一人。");
                        jsonArray.add(stringBuilder.toString());
                    }

                    // 关联人
                    if (nhxx != null) {
                        List<JbxxGlrxx> jbxxGlrxxList = iCreditReportQueryService.getJbxxGlrxx(zjhm, nhxx.getHhbm());
                        for (JbxxGlrxx glrxx : jbxxGlrxxList) {
                            // P1.福祥便民卡
                            cbscCardList = iKjbxxService.getBmkxxOracle(glrxx.getZjhm());
                            for (Kjbxx kjbxx : cbscCardList) {
                                stringBuilder = new StringBuilder();
                                String ywjgdm = kjbxx.getIssueBranch().toString();
                                if (ywjgdm.length() == 4) {
                                    // TODO 卡基本信息表.发卡机构缺失首位数字0，在此补充
                                    ywjgdm = "0" + ywjgdm;
                                }
                                String ywjgmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", ywjgdm);
                                stringBuilder.append("被查询人关联人").append(glrxx.getKhmc()).append(ywjgmc.length() == 0 ? "" : "在" + ywjgmc).append("有福祥便民卡，卡号：").append(kjbxx.getNo() + "。");
                                jsonArray.add(stringBuilder.toString());
                            }
                            // P2.被查询人关联人是否被起诉、执行
                            StringBuilder glrsszxxx = getSszxxx(glrxx.getZjhm());
                            if (glrsszxxx.length() > 0) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("被查询人关联人").append(glrxx.getKhmc()).append(glrsszxxx);
                                jsonArray.add(stringBuilder.toString());
                            }
                            // P3.被查询人关联人是否被纳入黑名单
                            StringBuilder glrhmdxx = getHmdxx(glrxx.getZjhm());
                            if (glrhmdxx.length() > 0) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("被查询人关联人").append(glrxx.getKhmc()).append(glrhmdxx);
                                jsonArray.add(stringBuilder.toString());
                            }
                            // P4.被查询人关联人是否有收本挂息记录
                            StringBuilder glrshgxxx = getSbgxxx(glrxx.getZjhm());
                            if (glrshgxxx.length() > 0) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("被查询人关联人").append(glrxx.getKhmc()).append(glrshgxxx);
                                jsonArray.add(stringBuilder.toString());
                            }
                            // P5.被查询人关联人是否有同名表外贷款
                            if (glrxx.getZjhm().length() == 18) {
                                csnf = glrxx.getZjhm().substring(6, 10);
                            }
                            csnfInt = Integer.parseInt(csnf);
                            bwdksjmxListFilter = new ArrayList<>();
                            bwdksjmxList = iBwdksjmxService.queryBwtmdkOracle(glrxx.getKhmc(), csnf, String.valueOf(nf));
                            for (Bwdksjmx record : bwdksjmxList) {
                                Bwdksjmx bwdksjmx = new Bwdksjmx();
                                BeanUtil.copyPropertiesIgnoreNull(record, bwdksjmx);
                                Date dkrq = bwdksjmx.getDkrq();
                                if (dkrq != null && csnfInt != null) {
                                    String dkrqStr = DateUtil.format(dkrq, "yyyy");
                                    Integer dkrqInt = Integer.parseInt(dkrqStr);
                                    if (dkrqInt - csnfInt >= nf) {
                                        bwdksjmxListFilter.add(bwdksjmx);
                                    }
                                }
                            }
                            if (bwdksjmxListFilter.size() > 0) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("被查询人关联人").append("有同名表外贷款，需要客户经理核实是否为同一人。");
                                jsonArray.add(stringBuilder.toString());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("一键准入报告-打印-四 信息概要-其它信息概要-获取失败！");
                }
                return jsonArray;
            });
            //Module5. 五 信贷交易信息明细
            //No1.贷款
            CompletableFuture<JSONObject> dksjCompletableFuture = CompletableFuture.supplyAsync(() -> {
                JSONObject jsonObject = new JSONObject();
                try {
                    //贷款金额(合计) & 核心余额(合计)
                    BigDecimal dkjeSum = new BigDecimal(0);
                    BigDecimal hxyeSum = new BigDecimal(0);
                    //欠息逾期明细
                    List<Qxmx> listQxmx = iCreditReportQueryService.getqxmxOracle(zjhm);

                    List<TmpCreditDksjVO> dksjList = iCreditReportQueryService.getDksjmx(zjhm);
                    for (TmpCreditDksjVO form : dksjList) {
                        // 账号类型
                        int zhlx = Integer.parseInt(form.getZhlx());
                        form.setDkzlShow(zhlx == 1 ? form.getYwzl() == null ? "" : iSysDictService.queryDictTextByKey("dkzl", form.getYwzl()) : "已核销贷款");
                        // 最小欠息日
                        String zxqxr = form.getZxqxr();
                        if (!StringUtils.isEmpty(zxqxr) && zxqxr.length() == 8) {
                            form.setZxqxr(DateUtil.formatDateTime("yyyy.MM.dd", DateUtil.parseDateFormat(zxqxr, "yyyyMMdd")));
                        } else {
                            form.setZxqxr(zxqxr == null ? "" : zxqxr);
                        }
                        if (form.getHxye() != null) {
                            form.setHxye(form.getHxye().abs());
                        }
                        BigDecimal dkje = new BigDecimal(form.getDkje() == null ? 0 : form.getDkje().intValue());
                        BigDecimal hxye = new BigDecimal(form.getHxye() == null ? 0 : form.getHxye().intValue());
                        dkjeSum = dkjeSum.add(dkje);
                        hxyeSum = hxyeSum.add(hxye);
                        List<Qxmx> listQxmxAcctNo = listQxmx.stream().filter(item -> item.getAcctNo().equalsIgnoreCase(form.getDkzh())).collect(Collectors.toList());
                        form.setDkqxdjbTable(listQxmxAcctNo);
                    }
                    List list = listToDictUtil.parseDictText(dksjList);
                    jsonObject.put("dksj", list);
                    jsonObject.put("dkjeSum", dkjeSum.setScale(2, BigDecimal.ROUND_HALF_UP));
                    jsonObject.put("hxyeSum", hxyeSum.setScale(2, BigDecimal.ROUND_HALF_UP));
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("一键准入报告-打印-五 信贷交易信息明细-贷款-获取失败！");
                }
                return jsonObject;
            });
            //No2.贷记卡
            CompletableFuture<JSONArray> djkCompletableFuture = CompletableFuture.supplyAsync(() -> {
                JSONArray jsonArray = new JSONArray();
                DecimalFormat df = new DecimalFormat("#,###");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                try {
                    QueryWrapper<Djkdksjmx> creditDjkdksjmx = new QueryWrapper<>();
                    creditDjkdksjmx.eq("zjhm", zjhm);
                    List<Djkdksjmx> djksjmxList = iDjkdksjmxService.list(creditDjkdksjmx);
                    for (Djkdksjmx form : djksjmxList) {
                        form.setJgmc(form.getYwjg() == null ? "" : iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", form.getYwjg()));
                        form.setKztbzShow(form.getKztbz() == null ? "" : iSysDictService.queryDictTextByKey("djkzl", form.getKztbz()));
                        form.setKzlShow(form.getKzl() == null ? "" : iSysDictService.queryDictTextByKey("kzl", form.getKzl()));
                        form.setYgxm(form.getYggh() == null ? "" : iSysDictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", form.getYggh()));
                        form.setTzyeLrsj(simpleDateFormat.format(form.getLrsj()));
                        form.setTzye(form.getTzye() == null ? new BigDecimal(0).setScale(2, RoundingMode.HALF_UP) : form.getTzye());
                        //查询贷记卡逾期期数
                        QueryWrapper<Djkxxgl> erpBasDjkblxx = new QueryWrapper<>();
                        //获取上月月份的数据
                        erpBasDjkblxx.eq("tjyf", new Timestamp(DateUtil.parseDateFormat(DateUtil.format(DateUtil.getFirstday_Month(new Date(), -1), "yyyyMM") + "01", "yyyyMMdd").getTime()));
                        erpBasDjkblxx.eq("acct_no", form.getKh());
                        Djkxxgl djkblxx = iDjkxxglService.getOne(erpBasDjkblxx, false);
                        if (djkblxx != null) {
                            form.setYqqs(djkblxx.getYqqs().intValue());
                            form.setYqqsLrsj(DateUtil.formatDateTime("yyyy年MM月dd日", djkblxx.getLrsj()));
                        } else {
                            form.setYqqs(0);
                            form.setYqqsLrsj(DateUtil.format(DateUtil.getLastday_Month(new Date(), -1), "yyyy年MM月dd日"));
                            erpBasDjkblxx = new QueryWrapper<>();
                            //获取上月月份的数据
                            erpBasDjkblxx.eq("tjyf", new Timestamp(DateUtil.parseDateFormat(DateUtil.format(DateUtil.getFirstday_Month(new Date(), -1), "yyyyMM") + "01", "yyyyMMdd").getTime()));
                            erpBasDjkblxx.isNotNull("lrsj");
                            Djkxxgl djkblxx1 = iDjkxxglService.getOne(erpBasDjkblxx, false);
                            if (djkblxx != null) {
                                if (djkblxx1.getLrsj() != null) {
                                    form.setYqqsLrsj(DateUtil.formatDateTime("yyyy年MM月dd日", djkblxx.getLrsj()));
                                }
                            }
                        }
                    }

                    for (Djkdksjmx form : djksjmxList) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(DateUtil.format(form.getFkrq(), "yyyy年MM月dd日")).append("，").append(form.getJgmc()).append("发放的贷记卡")
                                .append("，卡种").append(form.getKzlShow()).append("，").append("卡号").append(form.getKh()).append("，")
                                .append("推广员工").append(form.getYgxm()).append("，").append("卡状态").append(form.getKztbzShow()).append("，")
                                .append("授信额度").append(df.format(form.getSxje())).append("元，")
                                .append("截止").append(form.getTzyeLrsj()).append("已透支余额").append(df.format(form.getTzye())).append("元，")
                                .append("截止").append(form.getYqqsLrsj()).append("已逾期期数").append(form.getYqqs()).append("期。");
                        jsonArray.add(stringBuilder.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("一键准入报告-打印-五 信贷交易信息明细-贷记卡-获取失败！");
                }
                return jsonArray;
            });

            //object.put("jbxx_sfxx",JbxxSfxxCompletableFuture.get());
            //object.put("jbxx_glrxx",JbxxGlrxxCompletableFuture.get());
            object.put("xxgy_xyts", XytsCompletableFuture.get());
            object.put("xxgy_qtxxgy", qtxxgyCompletableFuture.get());
            object.put("xdjyxxmx_dk", dksjCompletableFuture.get());
            object.put("xdjyxxmx_djk", djkCompletableFuture.get());

            return Result.ok(object);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("一键准入报告-打印-数据生成失败！" + e.getMessage());
            return Result.error("一键准入报告-打印-数据生成失败，请联系管理员！");
        }
    }


    @GetMapping(value = "/yjzrbgPrint")
    public Result<?> yjzrbgPrint(String zjhm) {
        //String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());//区域代码

        JSONObject object = new JSONObject();
        YjzrbgScore yjzrbgScore = new YjzrbgScore();
        yjzrbgScore.setZjhm(zjhm);

        //数据提取
        Object o = redisUtil.get("yjzrbg" + zjhm);
        if (o == null) {
            try {
                service.CreditInitExtractIdap(zjhm);
                redisUtil.set("yjzrbg" + zjhm, "1", 60 * 60 * 24);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
        nhxqQueryWrapper.eq("zjhm", zjhm);
        Nhxq nhxx = nhxqService.getOne(nhxqQueryWrapper, false);

        List<Nhxq> nhxqList = null;
        if (nhxx != null) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(nhxx.getXtpdjg())) {
                if ("3".equals(nhxx.getXtpdjg())) {
                    yjzrbgScore.setHmd(2);
                    yjzrbgScore.setHmdval("农户信息认定为黑名单;");
                } else {
                    yjzrbgScore.setHmd(1);
                }
            }

            Object o1 = listToDictUtil.parseDictText(nhxx);
            object.put("nhxx", o1);

            if (org.apache.commons.lang3.StringUtils.isNotBlank(nhxx.getHhbm())) {
                LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(Nhxq::getHhbm, nhxx.getHhbm());
                nhxqList = nhxqService.list(lambdaQueryWrapper);
            }

            if (CollUtil.isNotEmpty(nhxqList)) {
                List list = listToDictUtil.parseDictText(nhxqList);
                object.put("nhxqList", list);
            }
        }

        LambdaQueryWrapper<Ywhywwlxx> lambdaQueryWrapper = new LambdaQueryWrapper<Ywhywwlxx>();
        lambdaQueryWrapper.eq(Ywhywwlxx::getZjhm, zjhm);
        Ywhywwlxx ywhywwlxx = ywhywwlxxService.getOne(lambdaQueryWrapper, false);
        if (ywhywwlxx != null) {
            Object ywhywwlxxdict = listToDictUtil.parseDictText(ywhywwlxx);
            yjzrbgScore.setYwhywwlxx(ywhywwlxxdict);
            if (ywhywwlxx.getBwbldkye() != null) {
                //yjzrbgScore.setBlkh(false);
                //yjzrbgScore.setBlkhval("在我行存在表外不良贷款");
                yjzrbgScore.setNbzx(2);
                yjzrbgScore.setNbzxval("在我行存在表外不良贷款;");
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
                long l = cn.hutool.core.date.DateUtil.betweenDay(dkzzffrq, new Date(), true);
                if (l > 0l) {
                    yjzrbgScore.setDkyewlsj(50);
                } else if (l > 1095l) {
                    yjzrbgScore.setDkyewlsj(100);
                } else if (l > 1825l) {
                    yjzrbgScore.setDkyewlsj(150);
                }
            }
            int dzyhktqk = 0;
            if (org.apache.commons.lang3.StringUtils.isNotBlank(ywhywwlxx.getSfktsbk()) && "1".equals(ywhywwlxx.getSfktsbk())) {
                dzyhktqk += 50;
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(ywhywwlxx.getSfktsjyhyw()) && "1".equals(ywhywwlxx.getSfktsjyhyw())) {
                dzyhktqk += 50;
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(ywhywwlxx.getSfktwsyhyw()) && "1".equals(ywhywwlxx.getSfktwsyhyw())) {
                dzyhktqk += 50;
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(ywhywwlxx.getSfktxyk()) && "1".equals(ywhywwlxx.getSfktxyk())) {
                dzyhktqk += 50;
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(ywhywwlxx.getSfktxyk()) && "1".equals(ywhywwlxx.getSfktxyk())) {
                dzyhktqk += 50;
            }
            yjzrbgScore.setDzyhktqk(dzyhktqk);
        }
//        BigDecimal maxJysxed = nhbkbpyService.getMaxJysxed(zjhm);
//        if (maxJysxed != null && maxJysxed.compareTo(new BigDecimal("0")) > 0) {
//            yjzrbgScore.setBkbpy(100);
//        }

        try {
            LambdaQueryWrapper<Nhbkbpy> nhbkbpyLambdaQueryWrapper = new LambdaQueryWrapper<>();
            nhbkbpyLambdaQueryWrapper.eq(Nhbkbpy::getHhbm, nhxx.getHhbm());
            List<Nhbkbpy> nhbkbpyList = nhbkbpyService.list(nhbkbpyLambdaQueryWrapper);
            //高危预警
            StringBuilder stringBuilder = new StringBuilder();

            List n = listToDictUtil.parseDictText(nhbkbpyList);

            for (int i = 0; i < n.size(); i++) {
                JSONObject jsonObject = (JSONObject) n.get(i);
                Object o1 = jsonObject.get("bysxqx_dictText");
                Object o2 = jsonObject.get("khmc");
                if (o1 != null) {
                    stringBuilder.append(o2).append("有不予授信情形：").append(o1).append(";");
                }

            }

            if (stringBuilder.toString().length() > 0) {
                yjzrbgScore.setBkbpy(2);
                yjzrbgScore.setBkbpyval(stringBuilder.toString());
            }


            object.put("nhbkbpyList", n);

        } catch (Exception e) {

        }


        LambdaQueryWrapper<Tpjchmd> tpjchmdLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tpjchmdLambdaQueryWrapper.eq(Tpjchmd::getZjhm, zjhm);
        long count = tpjchmdService.count(tpjchmdLambdaQueryWrapper);
        if (count > 0) {
            yjzrbgScore.setTpjch(2);
            yjzrbgScore.setTpjchval("此人为脱贫及监测户;");
        }


        //No1.信用提示
        try {
            QueryWrapper<Xyts> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm", zjhm);
            List<Xyts> xytsList = xytsService.list(queryWrapper);

            int sum = xytsList.stream().mapToInt(Xyts::getBjyqcs).sum();
            int sum2 = xytsList.stream().mapToInt(Xyts::getLxyqcs).sum();
            int sum3 = 0;
            if (sum > 0) {
                yjzrbgScore.setNbzx(1);
                yjzrbgScore.setNbzxval("本金逾期次数" + sum + "次；");
                sum3+=sum;
            }
            if (sum2 > 0) {
                yjzrbgScore.setNbzx(1);
                yjzrbgScore.setNbzxval("利息逾期次数" + sum2 + "次；");
                sum3+=sum2;
            }
            if (sum3 == 1){
                yjzrbgScore.setYqkh(-10);
            }else if (sum3 == 2){
                yjzrbgScore.setYqkh(-12);
            }else if(sum3 == 3){
                yjzrbgScore.setYqkh(-50);
            }else if (sum3 > 3){
                yjzrbgScore.setYqkh(-100);

                yjzrbgScore.setNbzx(2);
                yjzrbgScore.setNbzxval("本金和利息逾期次数大于等于3次;");
            }

            List list = listToDictUtil.parseDictText(xytsList);
            if (!list.isEmpty()) {
                object.put("xxgy_xyts", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("一键准入报告-打印-四 信息概要-信用提示-获取失败！");
        }

        //No2.其它信息概要
        try {
            JSONArray jsonArray = new JSONArray();
            StringBuilder stringBuilder = new StringBuilder();
            // 被查询人
            // P1.福祥便民卡
            List<Kjbxx> cbscCardList = iKjbxxService.getBmkxxOracle(zjhm);
            for (Kjbxx kjbxx : cbscCardList) {
                stringBuilder = new StringBuilder();
                String ywjgdm = kjbxx.getIssueBranch().toString();
                if (ywjgdm.length() == 4) {
                    // TODO 卡基本信息表.发卡机构缺失首位数字0，在此补充
                    ywjgdm = "0" + ywjgdm;
                }
                String ywjgmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", ywjgdm);
                stringBuilder.append("被查询人本人").append(ywjgmc.length() == 0 ? "" : "在" + ywjgmc).append("有福祥便民卡，卡号：").append(kjbxx.getNo() + "。");
                jsonArray.add(stringBuilder.toString());
            }
            // P2.被查询人是否被起诉、执行
            StringBuilder sszxxx = getSszxxx(zjhm);
            if (sszxxx.length() > 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("被查询人本人").append(sszxxx);
                jsonArray.add(stringBuilder.toString());
            }
            // P3.被查询人是否被纳入黑名单
            StringBuilder brhmdxx = getHmdxx(zjhm);
            if (brhmdxx.length() > 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("被查询人本人").append(brhmdxx);
                jsonArray.add(stringBuilder.toString());
                yjzrbgScore.setHmd(2);
                yjzrbgScore.setHmdval(stringBuilder.toString());
            }
            // P4.被查询人是否有收本挂息记录
            StringBuilder sbgxxx = getSbgxxx(zjhm);
            if (sbgxxx.length() > 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("被查询人本人").append(sbgxxx);
                jsonArray.add(stringBuilder.toString());
            }
            // P5.被查询人是否有同名表外贷款
            // TODO 表外贷款数据中 存在证件号码为空或证件类型为0的数据,贷款日期-出生日期大于等于18岁的才核实
            int nf = 18;
            String csnf = "";
            Integer csnfInt = null;
            if (zjhm.length() == 18) {
                csnf = zjhm.substring(6, 10);
            }
            // 查询同名表外贷款核查起始年龄
            QueryWrapper<Csgl> creditCsgl = new QueryWrapper<>();
            creditCsgl.eq("csbm", "P00003");
            Csgl nfcsgl = iCsglService.getOne(creditCsgl, false);
            if (nfcsgl != null) {
                try {
                    nf = Integer.parseInt(nfcsgl.getCsz());
                    csnfInt = Integer.parseInt(csnf);
                } catch (Throwable tx) {
                    tx.printStackTrace();
                    log.error("一键准入报告-打印-查询同名表外贷款核查起始年龄失败！");
                }
            }
            List<Bwdksjmx> bwdksjmxListFilter = new ArrayList<>();
            List<Bwdksjmx> bwdksjmxList = iBwdksjmxService.queryBwtmdkOracle(nhxx.getKhmc(), csnf, String.valueOf(nf));
            for (Bwdksjmx record : bwdksjmxList) {
                Bwdksjmx bwdksjmx = new Bwdksjmx();
                BeanUtil.copyPropertiesIgnoreNull(record, bwdksjmx);
                Date dkrq = bwdksjmx.getDkrq();
                if (dkrq != null && csnfInt != null) {
                    String dkrqStr = DateUtil.format(dkrq, "yyyy");
                    Integer dkrqInt = Integer.parseInt(dkrqStr);
                    if (dkrqInt - csnfInt >= nf) {
                        bwdksjmxListFilter.add(bwdksjmx);
                    }
                }
            }
            if (bwdksjmxListFilter.size() > 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("被查询人本人").append("有同名表外贷款，需要客户经理核实是否为同一人。");
                jsonArray.add(stringBuilder.toString());

                yjzrbgScore.setNbzx(2);
                yjzrbgScore.setNbzxval(stringBuilder.toString());
            }

            // 关联人
            if (CollUtil.isNotEmpty(nhxqList)) {
                //List<JbxxGlrxx> jbxxGlrxxList = iCreditReportQueryService.getJbxxGlrxx(zjhm, nhxx.getHhbm());
                for (Nhxq glrxx : nhxqList) {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(glrxx.getZjhm())) {
                        continue;
                    } else {
                        //排除掉本人
                        if (zjhm.equals(glrxx.getZjhm()))
                            continue;
                    }
                    // P1.福祥便民卡
                    cbscCardList = iKjbxxService.getBmkxxOracle(glrxx.getZjhm());
                    for (Kjbxx kjbxx : cbscCardList) {
                        stringBuilder = new StringBuilder();
                        String ywjgdm = kjbxx.getIssueBranch().toString();
                        if (ywjgdm.length() == 4) {
                            // TODO 卡基本信息表.发卡机构缺失首位数字0，在此补充
                            ywjgdm = "0" + ywjgdm;
                        }
                        String ywjgmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", ywjgdm);
                        stringBuilder.append("被查询人关联人").append(glrxx.getKhmc()).append(ywjgmc.length() == 0 ? "" : "在" + ywjgmc).append("有福祥便民卡，卡号：").append(kjbxx.getNo() + "。");
                        jsonArray.add(stringBuilder.toString());
                    }
                    // P2.被查询人关联人是否被起诉、执行
                    StringBuilder glrsszxxx = getSszxxx(glrxx.getZjhm());
                    if (glrsszxxx.length() > 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("被查询人关联人").append(glrxx.getKhmc()).append(glrsszxxx);
                        jsonArray.add(stringBuilder.toString());

                        yjzrbgScore.setNbzx(2);
                        yjzrbgScore.setNbzxval(stringBuilder.toString());
                    }
                    // P3.被查询人关联人是否被纳入黑名单
                    StringBuilder glrhmdxx = getHmdxx(glrxx.getZjhm());
                    if (glrhmdxx.length() > 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("被查询人关联人").append(glrxx.getKhmc()).append(glrhmdxx);
                        jsonArray.add(stringBuilder.toString());

                        yjzrbgScore.setHmd(2);
                        yjzrbgScore.setHmdval(stringBuilder.toString());
                    }
                    // P4.被查询人关联人是否有收本挂息记录
                    StringBuilder glrshgxxx = getSbgxxx(glrxx.getZjhm());
                    if (glrshgxxx.length() > 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("被查询人关联人").append(glrxx.getKhmc()).append(glrshgxxx);
                        jsonArray.add(stringBuilder.toString());
                    }
                    // P5.被查询人关联人是否有同名表外贷款
                    if (glrxx.getZjhm().length() == 18) {
                        csnf = glrxx.getZjhm().substring(6, 10);
                    }
                    csnfInt = Integer.parseInt(csnf);
                    bwdksjmxListFilter = new ArrayList<>();
                    bwdksjmxList = iBwdksjmxService.queryBwtmdkOracle(glrxx.getKhmc(), csnf, String.valueOf(nf));
                    for (Bwdksjmx record : bwdksjmxList) {
                        Bwdksjmx bwdksjmx = new Bwdksjmx();
                        BeanUtil.copyPropertiesIgnoreNull(record, bwdksjmx);
                        Date dkrq = bwdksjmx.getDkrq();
                        if (dkrq != null && csnfInt != null) {
                            String dkrqStr = DateUtil.format(dkrq, "yyyy");
                            Integer dkrqInt = Integer.parseInt(dkrqStr);
                            if (dkrqInt - csnfInt >= nf) {
                                bwdksjmxListFilter.add(bwdksjmx);
                            }
                        }
                    }
                    if (bwdksjmxListFilter.size() > 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("被查询人关联人").append("有同名表外贷款，需要客户经理核实是否为同一人。");
                        jsonArray.add(stringBuilder.toString());

                        yjzrbgScore.setNbzx(2);
                        yjzrbgScore.setNbzxval(stringBuilder.toString());
                    }
                }
            }

            object.put("xxgy_qtxxgy", jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("一键准入报告-打印-四 信息概要-其它信息概要-获取失败！");
        }

        try {
            JSONObject jsonObject = new JSONObject();
            //贷款金额(合计) & 核心余额(合计)
            BigDecimal dkjeSum = new BigDecimal(0);
            BigDecimal hxyeSum = new BigDecimal(0);
            //欠息逾期明细
            List<Qxmx> listQxmx = iCreditReportQueryService.getqxmxOracle(zjhm);

            List<TmpCreditDksjVO> dksjList = iCreditReportQueryService.getDksjmx(zjhm);
            for (TmpCreditDksjVO form : dksjList) {
                // 账号类型
                int zhlx = Integer.parseInt(form.getZhlx());
                form.setDkzlShow(zhlx == 1 ? form.getYwzl() == null ? "" : iSysDictService.queryDictTextByKey("dkzl", form.getYwzl()) : "已核销贷款");
                // 最小欠息日
                String zxqxr = form.getZxqxr();
                if (!StringUtils.isEmpty(zxqxr) && zxqxr.length() == 8) {
                    form.setZxqxr(DateUtil.formatDateTime("yyyy.MM.dd", DateUtil.parseDateFormat(zxqxr, "yyyyMMdd")));
                } else {
                    form.setZxqxr(zxqxr == null ? "" : zxqxr);
                }
                if (form.getHxye() != null) {
                    form.setHxye(form.getHxye().abs());
                }
                BigDecimal dkje = new BigDecimal(form.getDkje() == null ? 0 : form.getDkje().intValue());
                BigDecimal hxye = new BigDecimal(form.getHxye() == null ? 0 : form.getHxye().intValue());
                dkjeSum = dkjeSum.add(dkje);
                hxyeSum = hxyeSum.add(hxye);
                List<Qxmx> listQxmxAcctNo = listQxmx.stream().filter(item -> item.getAcctNo().equalsIgnoreCase(form.getDkzh())).collect(Collectors.toList());
                form.setDkqxdjbTable(listQxmxAcctNo);
            }
            List list = listToDictUtil.parseDictText(dksjList);
            jsonObject.put("dksj", list);
            jsonObject.put("dkjeSum", dkjeSum.setScale(2, BigDecimal.ROUND_HALF_UP));
            jsonObject.put("hxyeSum", hxyeSum.setScale(2, BigDecimal.ROUND_HALF_UP));

            object.put("xdjyxxmx_dk", jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("一键准入报告-打印-五 信贷交易信息明细-贷款-获取失败！");
        }


        JSONArray jsonArray = new JSONArray();
        DecimalFormat df = new DecimalFormat("#,###");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            QueryWrapper<Djkdksjmx> creditDjkdksjmx = new QueryWrapper<>();
            creditDjkdksjmx.eq("zjhm", zjhm);
            List<Djkdksjmx> djksjmxList = iDjkdksjmxService.list(creditDjkdksjmx);
            for (Djkdksjmx form : djksjmxList) {
                form.setJgmc(form.getYwjg() == null ? "" : iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", form.getYwjg()));
                form.setKztbzShow(form.getKztbz() == null ? "" : iSysDictService.queryDictTextByKey("djkzl", form.getKztbz()));
                form.setKzlShow(form.getKzl() == null ? "" : iSysDictService.queryDictTextByKey("kzl", form.getKzl()));
                form.setYgxm(form.getYggh() == null ? "" : iSysDictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", form.getYggh()));
                form.setTzyeLrsj(simpleDateFormat.format(form.getLrsj()));
                form.setTzye(form.getTzye() == null ? new BigDecimal(0).setScale(2, RoundingMode.HALF_UP) : form.getTzye());
                //查询贷记卡逾期期数
                QueryWrapper<Djkxxgl> erpBasDjkblxx = new QueryWrapper<>();
                //获取上月月份的数据
                erpBasDjkblxx.eq("tjyf", new Timestamp(DateUtil.parseDateFormat(DateUtil.format(DateUtil.getFirstday_Month(new Date(), -1), "yyyyMM") + "01", "yyyyMMdd").getTime()));
                erpBasDjkblxx.eq("acct_no", form.getKh());
                Djkxxgl djkblxx = iDjkxxglService.getOne(erpBasDjkblxx, false);
                if (djkblxx != null) {
                    form.setYqqs(djkblxx.getYqqs().intValue());
                    form.setYqqsLrsj(DateUtil.formatDateTime("yyyy年MM月dd日", djkblxx.getLrsj()));
                } else {
                    form.setYqqs(0);
                    form.setYqqsLrsj(DateUtil.format(DateUtil.getLastday_Month(new Date(), -1), "yyyy年MM月dd日"));
                    erpBasDjkblxx = new QueryWrapper<>();
                    //获取上月月份的数据
                    erpBasDjkblxx.eq("tjyf", new Timestamp(DateUtil.parseDateFormat(DateUtil.format(DateUtil.getFirstday_Month(new Date(), -1), "yyyyMM") + "01", "yyyyMMdd").getTime()));
                    erpBasDjkblxx.isNotNull("lrsj");
                    Djkxxgl djkblxx1 = iDjkxxglService.getOne(erpBasDjkblxx, false);
                    if (djkblxx != null) {
                        if (djkblxx1.getLrsj() != null) {
                            form.setYqqsLrsj(DateUtil.formatDateTime("yyyy年MM月dd日", djkblxx.getLrsj()));
                        }
                    }
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (Djkdksjmx form : djksjmxList) {
                stringBuilder.append(DateUtil.format(form.getFkrq(), "yyyy年MM月dd日")).append("，").append(form.getJgmc()).append("发放的贷记卡")
                        .append("，卡种").append(form.getKzlShow()).append("，").append("卡号").append(form.getKh()).append("，")
                        .append("推广员工").append(form.getYgxm()).append("，").append("卡状态").append(form.getKztbzShow()).append("，")
                        .append("授信额度").append(df.format(form.getSxje())).append("元，")
                        .append("截止").append(form.getTzyeLrsj()).append("已透支余额").append(df.format(form.getTzye())).append("元，")
                        .append("截止").append(form.getYqqsLrsj()).append("已逾期期数").append(form.getYqqs()).append("期。");
            }
            jsonArray.add(stringBuilder.toString());

            object.put("xdjyxxmx_djk", jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("一键准入报告-打印-五 信贷交易信息明细-贷记卡-获取失败！");
        }


        try {
            String sfzszxbg = sysDictMapper.queryDictTextByKey("sfzszxbg", "1");
            //String sfzszxbg = iSysDictService.queryDictTextByKey("sfzszxbg", "1");
            if (sfzszxbg == null || "1".equals(sfzszxbg)) {

                LambdaQueryWrapper<ZxbgPdfImg> zxbgPdfImgLambdaQueryWrapper = new LambdaQueryWrapper<>();
                zxbgPdfImgLambdaQueryWrapper.eq(ZxbgPdfImg::getZjhm, zjhm);
                List<ZxbgPdfImg> list = zxbgPdfImgService.list(zxbgPdfImgLambdaQueryWrapper);
                if (CollUtil.isNotEmpty(list)) {
                    yjzrbgScore.setZxbg(1);
                    //拿到了html的地址
                    ZxbgPdfImg zxbgPdfImg = list.get(0);
                    StringBuffer stringBuffer = new StringBuffer();
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(zxbgPdfImg.getWljl()) && FileUtil.isFile(zxbgPdfImg.getWljl()) && zxbgPdfImg.getWljl().contains("html")) {
                        BufferedReader utf8Reader = FileUtil.getUtf8Reader(zxbgPdfImg.getWljl());
                        Stream<String> lines = utf8Reader.lines();
                        //为了解决最后乱码的问题
                        lines.forEach(s -> {
                                    if (s.contains("class=\"u-msg-list\"")) {
                                        //s.replace("class=\"u-msg-list\""," style=\" text-align: left;line-height: 150%;padding-top: 10px;padding-bottom: 10px \" ");
                                        s.replace("list-style-type: none;", "");
                                    } else if (s.contains("<input style=\"float: right\" type=\"button\" name=\"button_export\" title=\"打印报告\" onclick=\"window.print();\" value=\"打印报告\">")) {
                                        s.replace("<input style=\"float: right\" type=\"button\" name=\"button_export\" title=\"打印报告\" onclick=\"window.print();\" value=\"打印报告\">", "");
                                    } else {
                                        stringBuffer.append(s);
                                    }
                                }
                        );



                        String string = stringBuffer.toString();
                        int s2 = string.indexOf("信息主体对信用报告内容提出了");
                        int s3 = string.indexOf("请浏览时注意阅读相关内容");
                        if (s2 > 0) {
                            String substring = string.substring(s2, s3 + 13);
                            if (!substring.contains("了0笔")) {
                                yjzrbgScore.setZxbg(2);
                                yjzrbgScore.setZxbgval(substring);
                            }
                        }

                        //return Result.ok(stringBuffer.toString());
                        object.put("html", string);
                    }

                    if (org.apache.commons.lang3.StringUtils.isNotBlank(zxbgPdfImg.getWljl()) && FileUtil.isFile(zxbgPdfImg.getWljl()) && zxbgPdfImg.getWljl().contains("pdf")) {
                        object.put("pdf", zxbgPdfImg);
                        //return Result.ok(zxbgPdfImg);
                    }
                }
            }
        } catch (Exception e) {
            log.info("===征信报告获取失败===");
        }


        //附加信息
        try {
            LambdaQueryWrapper<Lsdksjgl> lsdksjglLambdaQueryWrapper = new LambdaQueryWrapper<>();
            lsdksjglLambdaQueryWrapper.eq(Lsdksjgl::getCtfcCd,zjhm);
            long count1 = lsdksjglService.count(lsdksjglLambdaQueryWrapper);
            System.out.println(zjhm);
            System.out.println(count1);
            if (count1 > 0){
                yjzrbgScore.setNbzx(2);
                yjzrbgScore.setNbzxval("本行曾有不良;");
            }


            //LambdaQueryWrapper<Khfjxxgl> khfjxxglLambdaQueryWrapper = new LambdaQueryWrapper<>();
            //khfjxxglLambdaQueryWrapper.eq(Khfjxxgl::getZjhm,zjhm);
            Khfjxxgl byId = khfjxxglService.getById(zjhm);
            if (byId != null){
                if (org.apache.commons.lang3.StringUtils.isNotBlank(byId.getSfxdry())
                && "1".equals(byId.getSfxdry())){
                    yjzrbgScore.setNbzx(2);
                    yjzrbgScore.setNbzxval("是吸毒人员;");
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(byId.getSfss())
                        && "1".equals(byId.getSfss())){
                    yjzrbgScore.setNbzx(2);
                    yjzrbgScore.setNbzxval("是诉讼人员;");
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(byId.getSfdb())
                        && "1".equals(byId.getSfdb())){
                    yjzrbgScore.setNbzx(2);
                    yjzrbgScore.setNbzxval("是五保低保户;");
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(byId.getSffx())
                        && "1".equals(byId.getSffx())){
                    yjzrbgScore.setNbzx(2);
                    yjzrbgScore.setNbzxval("是服刑人员;");
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(byId.getSfzpry())
                        && "1".equals(byId.getSfzpry())){
                    yjzrbgScore.setNbzx(2);
                    yjzrbgScore.setNbzxval("是诈骗人员;");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            log.info("===附加信息查询失败===");
        }


        yjzrbgScore.jszf();
        try {
            camsZcsxYjzrbgService.updateScore(yjzrbgScore);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("===更新信用评分失败===");
        }
        object.put("jsdf", yjzrbgScore);

        return Result.ok(object);
    }

}
