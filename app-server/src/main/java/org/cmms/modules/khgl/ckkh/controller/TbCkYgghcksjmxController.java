package org.cmms.modules.khgl.ckkh.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.C;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.encryption.RSAEncryptUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.appbase.datatime.service.IBasDataJobDaysService;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.khgl.ckkh.entity.*;
import org.cmms.modules.khgl.ckkh.service.IAppCkkhGzListService;
import org.cmms.modules.khgl.ckkh.service.IKhgxglCkzhghlsbService;
import org.cmms.modules.khgl.ckkh.service.IKhgxglCkzhghxxService;
import org.cmms.modules.khgl.ckkh.service.ITbCkYgghcksjmxService;
import org.cmms.modules.khgl.dkkh.entity.DkxtjcVO;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglGrkh;
import org.cmms.modules.khgxgl.service.IKhgxglDkkhxxglService;
import org.cmms.modules.khgxgl.service.IKhgxglKhzlglGrkhService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.ViewHrBasStaffPost;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.IViewHrBasStaffPostService;
import org.cmms.modules.zhgl.wdckls.entity.Wdckls;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 员工管户数据明细
 * @Author: jeecg-boot
 * @Date: 2022-03-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "员工管户数据明细")
@RestController
@RequestMapping("/ckkh/tbCkYgghcksjmx")
public class TbCkYgghcksjmxController extends JeecgController<TbCkYgghcksjmx, ITbCkYgghcksjmxService> {
    @Autowired
    private ITbCkYgghcksjmxService tbCkYgghcksjmxService;

    @Autowired
    private IKhgxglKhzlglGrkhService khgxglKhzlglGrkhService;

    @Autowired
    private ISysDictService dictService;

    @Autowired
    private IAppCkkhGzListService appCkkhGzListService;

    @Autowired
    IBasDataJobDaysService basDataJobDaysService;
    @Autowired
    IKhgxglDkkhxxglService khgxglDkkhxxglService;
    @Autowired
    IKhgxglCkzhghlsbService khgxglCkzhghlsbService;
    @Autowired
    ListToDictUtil listToDictUtil;
    @Autowired
    IViewHrBasStaffPostService viewHrBasStaffPostService;
    @Autowired
    IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    IKhgxglCkzhghxxService khgxglCkzhghxxService;
    public static String TB_CK_YGGHCKSJMX_TABLENAME = "TB_CK_YGGHCKSJMX";

    /**
     * 分页列表查询
     *
     * @param tbCkYgghcksjmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "员工管户数据明细-分页列表查询")
    @ApiOperation(value = "员工管户数据明细-分页列表查询", notes = "员工管户数据明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TbCkYgghcksjmx tbCkYgghcksjmx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TbCkYgghcksjmx> queryWrapper = QueryGenerator.initQueryWrapper(tbCkYgghcksjmx, req.getParameterMap());
        Page<TbCkYgghcksjmx> page = new Page<TbCkYgghcksjmx>(pageNo, pageSize);
        IPage<TbCkYgghcksjmx> pageList = tbCkYgghcksjmxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @RequestMapping("/getSxtj")
    public Result<?> getSxtj(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                             String qmyeS, String qmyeE, String tj) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        //String ghr = sysUser.getWorkNo();
        String ghr = null;
        String orgCode = sysUser.getOrgCode();
        if (StringUtils.isNotBlank(orgCode)) {
            HrBasOrganization byId = hrBasOrganizationService.getById(orgCode);
            if (byId != null && StringUtils.isNotBlank(byId.getYwjgdm())) {
                ghr = byId.getYwjgdm();
                System.out.println("===网点存款流水===" + ghr);
            } else {
                return Result.ok();
            }
        } else {
            return Result.ok();
        }

        Date maxExtDay = basDataJobDaysService.getMaxEendDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        String zr = DateUtil.format(DateUtil.offsetDay(maxExtDay, -1), "yyMMdd");
        final String jc = getJc(yyMMdd);

        String qmTable = TB_CK_YGGHCKSJMX_TABLENAME + "_" + yyMMdd;
        String zrTable = TB_CK_YGGHCKSJMX_TABLENAME + "_" + zr;
        String jcTable = TB_CK_YGGHCKSJMX_TABLENAME + "_" + jc;
        Page<TbCkYgghcksjmxVo> page = new Page<TbCkYgghcksjmxVo>(pageNo, pageSize);
        //替换金额
        if (StringUtils.isNotBlank(qmyeS) && StringUtils.isNotBlank(qmyeE)) {
            if (new BigDecimal(qmyeE).compareTo(new BigDecimal(qmyeS)) < 0) {
                String temp = qmyeS;
                qmyeS = qmyeE;
                qmyeE = temp;
            }
        }
        //判断筛选条件
        if ("0".equals(tj)) {
            IPage<TbCkYgghcksjmxVo> jsr = service.getJsr(page, ghr, qmyeS, qmyeE, qmTable, zrTable);
            return Result.ok(jsr);
        } else if ("1".equals(tj)) {
            IPage<TbCkYgghcksjmxVo> jyc = service.getJyc(page, ghr, qmyeS, qmyeE, qmTable);
            return Result.ok(jyc);
        } else if ("2".equals(tj)) {
            IPage<TbCkYgghcksjmxVo> jjc = service.getJjc(page, ghr, qmyeS, qmyeE, qmTable, jcTable);
            return Result.ok(jjc);
        } else {
            IPage<TbCkYgghcksjmxVo> jnc = service.getJnc(page, ghr, qmyeS, qmyeE, qmTable);
            return Result.ok(jnc);
        }

    }

    public String getJc(String yyMMdd) {
        String yy = yyMMdd.substring(0, 2);
        String mm = yyMMdd.substring(2, 4);
        Integer integer = Integer.valueOf(mm);
        if (integer <= 3) {
            mm = "0101";
        } else if (integer > 3 && integer <= 6) {
            mm = "0401";
        } else if (integer > 6 && integer <= 9) {
            mm = "0701";
        } else if (integer > 9 && integer <= 12) {
            mm = "1001";
        }
        return yy + mm;
    }


    @GetMapping(value = "/getJb")
    public Result<?> queryPageList2() {
        CkkhjbResultVO ckkhjbResultVO = new CkkhjbResultVO();


        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + yyMMdd);
        CkkhjbVO qm = service.getByYggh(getWorkNo());
        CkkhjbVO nc = null;
        CkkhjbVO yc = null;

        System.out.println(yyMMdd + "============");
        if (!yyMMdd.endsWith("0101")) {
            String ncStr = DateUtil.format(DateUtil.beginOfYear(new Date()), "yyMMdd");
            RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + ncStr);
            nc = service.getByYggh(getWorkNo());

            if (!yyMMdd.endsWith("01")) {
                String ycStr = DateUtil.format(DateUtil.beginOfMonth(new Date()), "yyMMdd");
                RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + ycStr);
                yc = service.getByYggh(getWorkNo());
            }
        }

        ckkhjbResultVO.jsResult(qm, yc, nc);
        return Result.ok(ckkhjbResultVO);
    }


    @RequestMapping("/getCkkhCount")
    public Result<?> getCkkhCount(String indexTab, String custName) {
        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + yyMMdd);
        Integer ghzs = 0;
        if ("3".equals(indexTab)) {
            ghzs = appCkkhGzListService.getGhzsByGrp(getWorkNo(), "", custName);
        } else {
            ghzs = service.getGhzs(getWorkNo(), "", custName);
        }
        return Result.ok(ghzs);
    }


    /**
     * 获取客户明细
     */
    @RequestMapping("/getCkkh")
    public Result<?> getCkkh(int rownumStart, int rownumEnd, String grp, String indexTab, String custName) {
        String redisQydm = getRedisQydm();
        if (StringUtils.isNotBlank(getRedisQydm()) && QydmEnums.CILI.getQydmCode().equals(redisQydm)) {
            //慈利版本查询 khgxgl_ckzhghxx
            Page page = new Page(rownumStart+1,5);
            IPage<CkkhCardVO> clckkh = null;
            if ("1".equals(indexTab)){
                 clckkh = appCkkhGzListService.getClckkh(page, getRealname(), grp, custName);
            }else {
                clckkh = service.getClckkh(page, getRealname(), grp, custName);
            }
            //计算生日和年龄
            if (CollUtil.isNotEmpty(clckkh.getRecords())){
                for (int i = 0; i < clckkh.getRecords().size(); i++) {
                    String zjhm = clckkh.getRecords().get(i).getZjhm();
                    if (IdcardUtil.isValidCard(zjhm)) {
                        int ageByIdCard = IdcardUtil.getAgeByIdCard(zjhm);
                        clckkh.getRecords().get(i).setNl(ageByIdCard);

                        int genderByIdCard = IdcardUtil.getGenderByIdCard(zjhm);
                        clckkh.getRecords().get(i).setXb(genderByIdCard);

                        DateTime birthDate = IdcardUtil.getBirthDate(zjhm);
                        String format = DateUtil.format(birthDate, DatePattern.NORM_DATE_PATTERN);
                        clckkh.getRecords().get(i).setCsrq(format);
                    }

                    String allCpxxByZjhm = khgxglDkkhxxglService.getAllCpxxByZjhm(zjhm);
                    clckkh.getRecords().get(i).setQtcp(allCpxxByZjhm);

                    if (!"1".equals(indexTab)){
                        boolean gz = appCkkhGzListService.isGz(zjhm, getWorkNo());
                        if (gz)
                            clckkh.getRecords().get(i).setIsGz("1");
                    }
                    //个人客户信息
                    LambdaQueryWrapper<KhgxglKhzlglGrkh> khgxglKhzlglGrkhLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    khgxglKhzlglGrkhLambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getZjhm, zjhm);
                    if (StringUtils.isNotBlank(getRedisUserJgdm())){
                        khgxglKhzlglGrkhLambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getJgdm,getRedisUserJgdm());
                    }
                    List<KhgxglKhzlglGrkh> khgxglKhzlglGrkhs = khgxglKhzlglGrkhService.list(khgxglKhzlglGrkhLambdaQueryWrapper);
                    if (CollUtil.isNotEmpty(khgxglKhzlglGrkhs)) {
                        KhgxglKhzlglGrkh khgxglKhzlglGrkh = khgxglKhzlglGrkhs.get(0);
                        clckkh.getRecords().get(i).setGrkh(khgxglKhzlglGrkh);
                    }
                }
            }
            return Result.ok(clckkh);
        } else {
            Date maxExtDay = basDataJobDaysService.getMaxExtDay();
            String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
            RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + yyMMdd);
            //管户总数 去重身份证
            Integer ghzs = 0;
            if ("1".equals(indexTab)) {
                ghzs = appCkkhGzListService.getGhzsByGrp(getWorkNo(), grp, custName);
            } else {
                ghzs = service.getGhzs(getWorkNo(), grp, custName);
            }

            CkkhKhTabResult ckkhKhTabResult = new CkkhKhTabResult();
            ckkhKhTabResult.setGhs(ghzs);

            List<CkkhCardVO> list = new ArrayList<>();
            if (ghzs > 0) {
                List<String> zjhms = null;
                if ("1".equals(indexTab)) {
                    zjhms = appCkkhGzListService.getZjhms(rownumStart, rownumEnd, getWorkNo(), grp);
                } else {
                    zjhms = service.getZjhms(rownumStart, rownumEnd, getWorkNo(), grp, custName);
                }

                if (CollUtil.isNotEmpty(zjhms)) {

                    for (int i = 0; i < zjhms.size(); i++) {
                        CkkhCardVO ckkhCardVO = new CkkhCardVO();
                        String zjhm = zjhms.get(i);
                        ckkhCardVO.setZjhm(zjhm);
                        //计算年龄
                        if (IdcardUtil.isValidCard(zjhm)) {
                            int ageByIdCard = IdcardUtil.getAgeByIdCard(zjhm);
                            ckkhCardVO.setNl(ageByIdCard);

                            int genderByIdCard = IdcardUtil.getGenderByIdCard(zjhm);
                            ckkhCardVO.setXb(genderByIdCard);

                            DateTime birthDate = IdcardUtil.getBirthDate(zjhm);
                            String format = DateUtil.format(birthDate, DatePattern.NORM_DATE_PATTERN);
                            ckkhCardVO.setCsrq(format);
                        }
                        //最近到期时间
                        //TbCkYgghcksjmx endDate = service.minEndDate(zjhm);
                        //ckkhCardVO.setMx(endDate);
                        //是否关注
                        boolean gz = appCkkhGzListService.isGz(zjhm, getWorkNo());
                        if (gz)
                            ckkhCardVO.setIsGz("1");
                        //个人客户信息
                        LambdaQueryWrapper<KhgxglKhzlglGrkh> khgxglKhzlglGrkhLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        khgxglKhzlglGrkhLambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getZjhm, zjhm);
                        if (StringUtils.isNotBlank(getRedisUserJgdm())){
                            khgxglKhzlglGrkhLambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getJgdm,getRedisUserJgdm());
                        }
                        List<KhgxglKhzlglGrkh> khgxglKhzlglGrkhs = khgxglKhzlglGrkhService.list(khgxglKhzlglGrkhLambdaQueryWrapper);
                        if (CollUtil.isNotEmpty(khgxglKhzlglGrkhs)) {
                            KhgxglKhzlglGrkh khgxglKhzlglGrkh = khgxglKhzlglGrkhs.get(0);
                            ckkhCardVO.setGrkh(khgxglKhzlglGrkh);
                        }

                        //查存款产品
                        String ckCpxx = service.getCkCpxx(zjhm);
                        ckkhCardVO.setClcp(ckCpxx);

                        String allCpxxByZjhm = khgxglDkkhxxglService.getAllCpxxByZjhm(zjhm);
                        ckkhCardVO.setQtcp(allCpxxByZjhm);
                        list.add(ckkhCardVO);
                    }
                }
            }

            if (CollUtil.isNotEmpty(list)) {
                List list1 = listToDictUtil.parseDictText(list);
                ckkhKhTabResult.setCardVOList(list1);
            }
            return Result.ok(ckkhKhTabResult);
        }
    }


    /**
     * 存款客户概要
     */
    @RequestMapping("/getGyByZjhm")
    public Result<?> getGyByZjhm(String zjhm) {
        CkkhGyResultVO ckkhGyResultVO = new CkkhGyResultVO();

        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + yyMMdd);
        CkkhGyVO qm = service.getByZjhm(zjhm);
        CkkhGyVO nc = null;
        CkkhGyVO yc = null;

        if (!yyMMdd.endsWith("0101")) {
            String ncStr = DateUtil.format(DateUtil.beginOfYear(new Date()), "yyMMdd");
            RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + ncStr);
            nc = service.getByZjhm(getWorkNo());

            if (!yyMMdd.endsWith("01")) {
                String ycStr = DateUtil.format(DateUtil.beginOfMonth(new Date()), "yyMMdd");
                RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + ycStr);
                yc = service.getByZjhm(getWorkNo());
            }
        }

        ckkhGyResultVO.jsResult(qm, yc, nc);
        return Result.ok(ckkhGyResultVO);
    }

    /**
     * 查 活期/定期 数据
     */
    @RequestMapping("/getHqOrDqByZjhm")
    public Result<?> getHqOrDqByZjhm(String zjhm, String type) {
        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + yyMMdd);
        LambdaQueryWrapper<TbCkYgghcksjmx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TbCkYgghcksjmx::getZjhm, zjhm);
        if (StringUtils.isNotBlank(type) && "1".equals(type))
            lambdaQueryWrapper.eq(TbCkYgghcksjmx::getAcctDesc, "S");
        if (StringUtils.isNotBlank(type) && "2".equals(type))
            lambdaQueryWrapper.eq(TbCkYgghcksjmx::getAcctDesc, "T");
        List<TbCkYgghcksjmx> list = service.list(lambdaQueryWrapper);

        List<CkkhHqVO> result = new ArrayList<>();
        if (CollUtil.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                CkkhHqVO ckkhHqVO = new CkkhHqVO();
                TbCkYgghcksjmx tbCkYgghcksjmx = list.get(i);
                BeanUtil.copyProperties(tbCkYgghcksjmx, ckkhHqVO);

                BigDecimal bigDecimal = new BigDecimal(0);
                BigDecimal bigDecimal2 = new BigDecimal(0);
                if (!yyMMdd.endsWith("01") && StringUtils.isNotBlank(tbCkYgghcksjmx.getCkzh())) {
                    String ycStr = DateUtil.format(DateUtil.beginOfMonth(new Date()), "yyMMdd");
                    RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + ycStr);
                    LambdaQueryWrapper<TbCkYgghcksjmx> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                    lambdaQueryWrapper1.eq(TbCkYgghcksjmx::getCkzh, tbCkYgghcksjmx.getCkzh());
                    List<TbCkYgghcksjmx> list1 = service.list(lambdaQueryWrapper1);
                    if (CollUtil.isNotEmpty(list1)) {
                        for (int j = 0; j < list1.size(); j++) {
                            TbCkYgghcksjmx tbCkYgghcksjmx1 = list1.get(j);
                            if (tbCkYgghcksjmx1.getQmye() != null)
                                bigDecimal = bigDecimal.add(tbCkYgghcksjmx1.getQmye());
                            if (tbCkYgghcksjmx1.getYrp3() != null)
                                bigDecimal2 = bigDecimal2.add(tbCkYgghcksjmx1.getYrp3());
                        }
                    }
                }
                if (StringUtils.isNotBlank(ckkhHqVO.getYggh())) {
                    String s = dictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", ckkhHqVO.getYggh());
                    ckkhHqVO.setYgghVal(s);
                }

                if (StringUtils.isNotBlank(ckkhHqVO.getYxlx())) {
                    String yxlx = dictService.queryDictTextByKey("yxlx", ckkhHqVO.getYxlx());
                    ckkhHqVO.setYxlxVal(yxlx);
                }

                ckkhHqVO.jsResult(bigDecimal, bigDecimal2);
                result.add(ckkhHqVO);
            }
        }

        return Result.ok(result);
    }

    /**
     * type 排序方式
     */
    @RequestMapping("/getPm")
    public Result<?> getPm(int rownumStart, int rownumEnd, String type) {

        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + yyMMdd);

        List<CkkhRankVO> rankList = service.getRankList(rownumStart, rownumEnd, getWorkNo(), type);
        if (CollUtil.isNotEmpty(rankList)) {
            for (int i = 0; i < rankList.size(); i++) {
                CkkhRankVO ckkhRankVO = rankList.get(i);
                if (StringUtils.isNotBlank(ckkhRankVO.getZjhm())) {
                    boolean gz = appCkkhGzListService.isGz(ckkhRankVO.getZjhm(), getWorkNo());
                    if (gz)
                        ckkhRankVO.setIsGz("1");

                    if (IdcardUtil.isValidCard(ckkhRankVO.getZjhm())) {
                        int genderByIdCard = IdcardUtil.getGenderByIdCard(ckkhRankVO.getZjhm());
                        ckkhRankVO.setSex(genderByIdCard);
                    }
                }
            }
        }
        return Result.ok(rankList);
    }

    @RequestMapping("/getZhlb")
    public Result<?> getZhlb(String zjhm) {
        if (StringUtils.isNotBlank(zjhm) && zjhm.length() > 20){
            zjhm = RSAEncryptUtil.desEncrypt(zjhm.replaceAll(" ","+"));
            log.info("===getZhlb解密后的zjhm{}===",zjhm);
        }
        String redisQydm = getRedisQydm();
        if (StringUtils.isNotBlank(redisQydm) && QydmEnums.CILI.getQydmCode().equals(redisQydm)){
            List<ZhlbVO> ckzhList = khgxglCkzhghxxService.getCkzhList(zjhm);
            return Result.ok(ckzhList);
        }else {
        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + yyMMdd);

        LambdaQueryWrapper<TbCkYgghcksjmx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TbCkYgghcksjmx::getZjhm, zjhm);
        List<ZhlbVO> result = new ArrayList<>();
        List<TbCkYgghcksjmx> list = service.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                ZhlbVO zhlbVO = new ZhlbVO();
                TbCkYgghcksjmx tbCkYgghcksjmx = list.get(i);
                BeanUtil.copyProperties(tbCkYgghcksjmx, zhlbVO);
                if (StringUtils.isNotBlank(tbCkYgghcksjmx.getAcctDesc())) {
                    if ("S".equalsIgnoreCase(tbCkYgghcksjmx.getAcctDesc())) {
                        zhlbVO.setCkxx("活期");
                    } else {
                        if (StringUtils.isNotBlank(tbCkYgghcksjmx.getCq())) {
                            zhlbVO.setCkxx(tbCkYgghcksjmx.getCq());
                        }
                    }
                }
                //去查营销人员
                StringBuffer ghr = new StringBuffer();
                StringBuffer yxbl = new StringBuffer();
                if (StringUtils.isNotBlank(tbCkYgghcksjmx.getCkzh())) {
                    LambdaQueryWrapper<KhgxglCkzhghlsb> khgxglCkzhghlsbLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    khgxglCkzhghlsbLambdaQueryWrapper.eq(KhgxglCkzhghlsb::getCkzh, tbCkYgghcksjmx.getCkzh());
                    khgxglCkzhghlsbLambdaQueryWrapper.eq(KhgxglCkzhghlsb::getGhlx, "1");
                    khgxglCkzhghlsbLambdaQueryWrapper.apply("  ksrq<=sysdate and (jsrq is null or jsrq>=sysdate) ");
                    List<KhgxglCkzhghlsb> khgxglCkzhghlsbs = khgxglCkzhghlsbService.list(khgxglCkzhghlsbLambdaQueryWrapper);
                    if (CollUtil.isNotEmpty(khgxglCkzhghlsbs)) {
                        for (int j = 0; j < khgxglCkzhghlsbs.size(); j++) {
                            KhgxglCkzhghlsb khgxglCkzhghlsb = khgxglCkzhghlsbs.get(j);
                            if (StringUtils.isNotBlank(khgxglCkzhghlsb.getGhr())) {
                                String s = dictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", khgxglCkzhghlsb.getGhr());
                                if (j < khgxglCkzhghlsbs.size() - 1) {
                                    ghr.append(s).append("丶");
                                    if (khgxglCkzhghlsb.getGhbl() != null) {
                                        yxbl.append(khgxglCkzhghlsb.getGhbl().toString()).append("丶");
                                    } else {
                                        yxbl.append("0").append("丶");
                                    }
                                } else {
                                    ghr.append(s);
                                    if (khgxglCkzhghlsb.getGhbl() != null) {
                                        yxbl.append(khgxglCkzhghlsb.getGhbl().toString());
                                    } else {
                                        yxbl.append("0");
                                    }
                                }
                            }
                        }
                    }
                }

                zhlbVO.setYxry(ghr.toString());
                zhlbVO.setYxbl(yxbl.toString());
                result.add(zhlbVO);
            }
        }
        return Result.ok(result);
        }
    }

    Map<String, List<QhckphVO>> map = new HashMap<>();

    @RequestMapping("/getCkRank")
    public Result<?> getCkRank() {
        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        String date = DateUtil.format(maxExtDay, DatePattern.NORM_DATE_PATTERN).replace("-", "/");
        RequestDataHelper.setRequestData(TB_CK_YGGHCKSJMX_TABLENAME, TB_CK_YGGHCKSJMX_TABLENAME + "_" + yyMMdd);


        if (CollUtil.isEmpty(map))
            putRank(maxExtDay, date, yyMMdd);

        List<QhckphVO> qhckphVOS = map.get(yyMMdd);
        if (CollUtil.isNotEmpty(qhckphVOS)) {
            return Result.ok(qhckphVOS);
        } else {
            putRank(maxExtDay, date, yyMMdd);
            return Result.ok(map.get(yyMMdd));
        }


//		 List<QhckphVO> ckRank = service.getCkRank();
//		 if (CollUtil.isNotEmpty(ckRank)){
//			 for (int i = 0; i < ckRank.size(); i++) {
//				 QhckphVO qhckphVO = ckRank.get(i);
//				 ckRank.get(i).setDate(date);
//				 QueryWrapper<ViewHrBasStaffPost> staffPostQueryWrapper = new QueryWrapper<>();
//				 staffPostQueryWrapper.eq("yggh", qhckphVO.getYggh());
//				 staffPostQueryWrapper.le("rgrq", maxExtDay);
//				 staffPostQueryWrapper.apply("  (lgrq is null or lgrq >= {0} )", maxExtDay);
//				 List<ViewHrBasStaffPost> list = viewHrBasStaffPostService.list(staffPostQueryWrapper);
//				 if (CollUtil.isNotEmpty(list)){
//					 ViewHrBasStaffPost viewHrBasStaffPost = list.get(0);
//					 ckRank.get(i).setPsot(viewHrBasStaffPost.getZzmc()+"--"+viewHrBasStaffPost.getGwmc());
//				 }
//				 String s = dictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", qhckphVO.getYggh());
//				 ckRank.get(i).setYgghVal(s);
//
//			 }
//		 }
    }

    public void putRank(Date maxExtDay, String date, String yymmdd) {
        List<QhckphVO> ckRank = service.getCkRank();
        if (CollUtil.isNotEmpty(ckRank)) {
            for (int i = 0; i < ckRank.size(); i++) {
                QhckphVO qhckphVO = ckRank.get(i);
                ckRank.get(i).setDate(date);
                QueryWrapper<ViewHrBasStaffPost> staffPostQueryWrapper = new QueryWrapper<>();
                staffPostQueryWrapper.eq("yggh", qhckphVO.getYggh());
                staffPostQueryWrapper.le("rgrq", maxExtDay);
                staffPostQueryWrapper.apply("  (lgrq is null or lgrq >= {0} )", maxExtDay);
                List<ViewHrBasStaffPost> list = viewHrBasStaffPostService.list(staffPostQueryWrapper);
                if (CollUtil.isNotEmpty(list)) {
                    ViewHrBasStaffPost viewHrBasStaffPost = list.get(0);
                    ckRank.get(i).setPsot(viewHrBasStaffPost.getZzmc() + "--" + viewHrBasStaffPost.getGwmc());
                }
                String s = dictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", qhckphVO.getYggh());
                ckRank.get(i).setYgghVal(s);
            }
        }
        map = new HashMap<>();
        map.put(yymmdd, ckRank);
    }
}
