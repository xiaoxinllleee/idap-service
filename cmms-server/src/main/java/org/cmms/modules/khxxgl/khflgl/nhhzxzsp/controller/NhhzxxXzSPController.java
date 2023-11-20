package org.cmms.modules.khxxgl.khflgl.nhhzxzsp.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.*;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khxxgl.khflgl.nhhzxzsp.entity.NhhzxxXzSP;
import org.cmms.modules.khxxgl.khflgl.nhhzxzsp.service.INhhzxxXzSPService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.pad.nhxxgl.controller.KhglNhhzxxglController;
import org.cmms.modules.pad.nhxxgl.entity.*;
import org.cmms.modules.pad.nhxxgl.service.*;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.utils.DateUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 农户户主信息（新增待审核）
 * @Author: jeecg-boot
 * @Date: 2023-07-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "农户户主信息（新增待审核）")
@RestController
@RequestMapping("/khxxgl/khflgl/nhhzxxXzSP")
public class NhhzxxXzSPController extends JeecgController<NhhzxxXzSP, INhhzxxXzSPService> {
    @Autowired
    private INhhzxxXzSPService nhhzxxXzSPService;
    @Autowired
    private INhzzzlxxbService nhzzzlxxbService;
    @Autowired
    private IKhglNhhzxxglService nhhzxxglService;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private IHrBasOrganizationService orgService;
    @Autowired
    private IKhjbzlService khjbxxService;
    @Autowired
    private IVhrbasstaffpostService vHrBasStaffPostService;
    @Autowired
    private IYxglKhhfxxbService khhfxxbService;
    @Autowired
    private IKhxxglGrsxlxmxNhService nhGrsxLxmxService;
    @Autowired
    private IvKhglNhhzxxglService VNhhzxxglService;
    @Autowired
    private IKhxxglGrsxlxmxNh1Service nhGrsxLxmx1Service;
    @Autowired
    private IKhglNhhzzllbService nhhzzllbService;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private ISysDictService sysDictService;

    @Value(value = "${common.path.upload}")
    private String uploadpath;

    /**
     * 分页列表查询
     *
     * @param nhhzxxXzSP
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "农户户主信息（新增待审核）-分页列表查询")
    @ApiOperation(value = "农户户主信息（新增待审核）-分页列表查询", notes = "农户户主信息（新增待审核）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(NhhzxxXzSP nhhzxxXzSP,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<NhhzxxXzSP> queryWrapper = QueryGenerator.initQueryWrapper(nhhzxxXzSP, req.getParameterMap());
        Page<NhhzxxXzSP> page = new Page<NhhzxxXzSP>(pageNo, pageSize);
        IPage<NhhzxxXzSP> pageList = nhhzxxXzSPService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * Pad端 消息中心 新增户主审核结果 分页列表查询 暂时只展示5条
     *
     * @return
     */
    @AutoLog(value = "新增户主审核结果-分页列表查询")
    @ApiOperation(value = "新增户主审核结果-分页列表查询", notes = "新增户主审核结果-分页列表查询")
    @GetMapping(value = "/queryHzAddToExamine")
    public Result<?> queryHzAddToExamine(@RequestParam(name = "more") String more) {
//        log.info("当前登录操作员用户名----" + getLoginUser().getUsername());
        QueryWrapper<NhhzxxXzSP> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lrczy", getLoginUser().getUsername());
        queryWrapper.in("shzt", "1", "2");
        if ("true".equals(more)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = DateUtils.stringToDate(sdf.format(new Date())+" 00:00:00","yyyy-MM-dd HH:mm:ss");
            Date parseDate = DateUtils.getDateBefore(now,7);
            queryWrapper.ge("lrczsj",parseDate);
        } else {
            queryWrapper.and(i ->i.le("rownum","5"));
        }
        queryWrapper.orderByDesc("lrczsj");
        List<NhhzxxXzSP> list = nhhzxxXzSPService.list(queryWrapper);
        return Result.ok(list);
    }

    /**
     * 永兴 新增户主信息 待审批
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "永兴-App端新增农户户主保存")
    @ApiOperation(value = "永兴-App端新增农户户主保存", notes = "永兴-App端新增农户户主保存")
    @Transactional
    @RequestMapping(value = "/AddHzxxDsp", method = RequestMethod.POST)
    public Result<?> AddHzxxDsp(@RequestBody JSONObject jsonObject) {
        String shid = UUIDGenerator.generate();
        String hhbm = "";

        //Step1 保存 新增户主待审批数据
//        log.info("----新 增 户 主 信 息 待 审 批----"+jsonObject.getJSONObject("formData"));
        NhhzxxXzSP xzhzxx = JSON.toJavaObject(jsonObject.getJSONObject("formData"),NhhzxxXzSP.class);
        //对证件号码进行解密
        xzhzxx.setHzzjhm(Base64.decodeStr(xzhzxx.getHzzjhm()));
        hhbm = xzhzxx.getSswg()+sysDictService.queryhhbm("seq_hhbm.nextval");
        if (xzhzxx != null) {
            xzhzxx.setId(shid);
            xzhzxx.setHhbm(hhbm);
            xzhzxx.setShzt("0");
            xzhzxx.setLrbz("1");
            xzhzxx.setLrr(getLoginUser().getUsername());
            xzhzxx.setLrsj(new Date());
            xzhzxx.setLrczy(getLoginUser().getUsername());
            xzhzxx.setLrczsj(new Date());
        }
//        log.info("----新 增 户 主 信 息 待 审 批----"+xzhzxx);
        nhhzxxXzSPService.save(xzhzxx);

        //Step2 保存 新增户主 佐证资料 待审批数据
//        log.info("----新 增 户 主 佐 证 资 料 待 审 批----"+jsonObject.getJSONArray("uploadFiles"));
        JSONArray jsonArray = jsonObject.getJSONArray("uploadFiles");
        String jsonObjString = JSON.toJSONString(jsonArray);
        List<Nhzzzlxxb> nhzzzlxxbList = JSONArray.parseArray(jsonObjString, Nhzzzlxxb.class);
//        log.info("----新 增 户 主 佐 证 资 料 待 审 批----"+nhzzzlxxbList);
        for (Nhzzzlxxb nhzzzlxxb : nhzzzlxxbList) {
            // 插入新上传的佐证附件信息
            String id = UUIDGenerator.generate();
            Nhzzzlxxb nhzzzlxx = new Nhzzzlxxb();
            nhzzzlxx.setId(id);
            nhzzzlxx.setShid(shid);
            nhzzzlxx.setZzlx("1");
            nhzzzlxx.setQydm(nhzzzlxxb.getQydm());
            nhzzzlxx.setHhbm(hhbm);
            nhzzzlxx.setZjhm(xzhzxx.getHzzjhm());
            nhzzzlxx.setZllx("1");
            nhzzzlxx.setZlbh(nhzzzlxxb.getZlbh());
            nhzzzlxx.setZlmc(nhzzzlxxb.getZlmc());
            nhzzzlxx.setZldx(nhzzzlxxb.getZldx());
            nhzzzlxx.setZllj(uploadpath + "/" + nhzzzlxxb.getFwlj());
            nhzzzlxx.setFwlj(nhzzzlxxb.getFwlj());
            nhzzzlxx.setScr(getLoginUser().getUsername());
            nhzzzlxx.setScsj(new Date());
            nhzzzlxx.setBz("新增农户户主佐证资料");
            nhzzzlxx.setLrbz("1");
            nhzzzlxx.setLrr(getLoginUser().getUsername());
            nhzzzlxx.setLrsj(new Date());
            nhzzzlxxbService.save(nhzzzlxx);
            //log.info("----佐 证 资 料----"+nhzzzlxxb);
        }

        return Result.ok("添加户主信息成功，请耐心等待审核！");
    }

    /**
     * 永兴 新增户主信息 审核
     *
     * @param xzhzxx
     * @return
     */
    @AutoLog(value = "农户户主信息（新增待审核）-审核")
    @ApiOperation(value = "农户户主信息（新增待审核）-审核", notes = "农户户主信息（新增待审核）-审核")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody NhhzxxXzSP xzhzxx) {
        try {
            log.info("待审核户主信息："+xzhzxx);
            if ("1".equals(xzhzxx.getShzt())) {
                // 审核通过
                String qybm = getRedisQydm();
                // 所属支行（组织标识）转机构代码
                String ywjgdm = "";
                if (xzhzxx.getSszh() != null) {
                    QueryWrapper<HrBasOrganization> orgQueryWrapper = new QueryWrapper<>();
                    orgQueryWrapper.eq("zzbz",xzhzxx.getSszh());
                    HrBasOrganization org = orgService.getOne(orgQueryWrapper,false);
                    if (org != null) {
                        ywjgdm = org.getYwjgdm();
                    } else {
                        log.warn("该所属支行："+xzhzxx.getSszh()+"，未找到对应的业务机构代码！");
                    }
                }

                //判断 待审批客户 是否已录入为户主
                QueryWrapper<KhglNhhzxxgl> nhhzxxQuertWrapper = new QueryWrapper<>();
                nhhzxxQuertWrapper.eq("hzzjhm", xzhzxx.getHzzjhm());
                List<KhglNhhzxxgl> khxxList = nhhzxxglService.list(nhhzxxQuertWrapper);
                if (khxxList != null && khxxList.size() > 0) {
                    return Result.error("证件号码已存在户主信息！");
                }
                //判断 待审批客户 是否已录入为农户（非户主）家庭成员
                QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
                nhxqQueryWrapper.eq("zjhm", xzhzxx.getHzzjhm());
                nhxqQueryWrapper.isNotNull("hhbm");
                Nhxq nhRecord = nhxqService.getOne(nhxqQueryWrapper,false);
                if (nhRecord != null) {
                    return Result.error("证件号码已存在农户信息表！");
                }
                //保存 农户户主信息
                KhglNhhzxxgl nhhzxx = new KhglNhhzxxgl();
                nhhzxx.setId(xzhzxx.getId());
                nhhzxx.setSsyxdy(xzhzxx.getSswg());
                nhhzxx.setSszh(xzhzxx.getSszh());
                nhhzxx.setHhbm(xzhzxx.getHhbm());
                nhhzxx.setHzxm(xzhzxx.getHzxm());
                nhhzxx.setHzzjhm(xzhzxx.getHzzjhm());
                nhhzxx.setSxdx(xzhzxx.getHzxm());
                nhhzxx.setSxdxzjh(xzhzxx.getHzzjhm());
                nhhzxx.setZkhjl(getLoginUser().getWorkNo());
                nhhzxx.setKhlx(xzhzxx.getKhlx());
                nhhzxx.setSfzf("2");
                nhhzxx.setSfyxzf("2");
                nhhzxx.setLrbz("1");
                nhhzxx.setLrsj(new Date());
                nhhzxx.setLrr(getLoginUser().getUsername());
                nhhzxxglService.save(nhhzxx);

                //保存 农户信息
                Nhxq nhxx = new Nhxq();
                nhxx.setId(xzhzxx.getId());
                nhxx.setSszh(xzhzxx.getSszh());
                nhxx.setJgdm(ywjgdm);
                nhxx.setHhbm(xzhzxx.getHhbm());
                nhxx.setKhmc(xzhzxx.getHzxm());
                nhxx.setWgbh(xzhzxx.getSswg());
                nhxx.setSfhz("1");
                nhxx.setKhlx("1");
                nhxx.setYhzgx("1");
                nhxx.setSjhm(xzhzxx.getLxfs());
                nhxx.setZjhm(xzhzxx.getHzzjhm());
                if (IdcardUtil.isValidCard(xzhzxx.getHzzjhm())) {
                    int ageByIdCard = IdcardUtil.getAgeByIdCard(xzhzxx.getHzzjhm());
                    int genderByIdCard = IdcardUtil.getGenderByIdCard(xzhzxx.getHzzjhm());
                    nhxx.setXb(genderByIdCard == 1 ? "1" : "2");
                    nhxx.setNl(String.valueOf(ageByIdCard));
                }
                nhxx.setZz(xzhzxx.getDz());
                nhxx.setHjdz(xzhzxx.getDz());
                nhxx.setYhzgx("1");
                nhxx.setLrbz("1");
                nhxx.setLrsj(new Date());
                nhxx.setLrr(getLoginUser().getUsername());
                nhxqService.save(nhxx);

                //更新 客户基本信息
                QueryWrapper<Khjbzl> khjbxxQueryWrapper = new QueryWrapper();
                khjbxxQueryWrapper.eq("zjhm", nhxx.getZjhm());
                Khjbzl khjbxx = khjbxxService.getOne(khjbxxQueryWrapper,false);
                if (khjbxx != null) {
                    if (khjbxx.getKhxz() == null || khjbxx.getKhxz().isEmpty()) {
                        khjbxx.setKhxz("1");
                    } else {
                        boolean sfynh = false;
                        String[] split = khjbxx.getKhxz().split(",");
                        for (String khxz : split) {
                            if ("1".equals(khxz)) {
                                sfynh = true;
                                break;
                            }
                        }
                        if (!sfynh) {
                            khjbxx.setKhxz("1," + khjbxx.getKhxz());
                        }
                    }
                    khjbxx.setWgbh(StringUtils.isEmpty(khjbxx.getWgbh()) ? null : nhxx.getWgbh());
                    khjbxx.setSszh(StringUtils.isEmpty(khjbxx.getSszh()) ? null : nhxx.getSszh());
                    khjbxx.setJgdm(StringUtils.isEmpty(khjbxx.getJgdm()) ? null : nhxx.getJgdm());
                    khjbxx.setKhlx(StringUtils.isEmpty(khjbxx.getKhlx()) ? null : nhxx.getKhlx());
                    khjbxx.setLxfs(StringUtils.isEmpty(khjbxx.getLxfs()) ? null : nhxx.getSjhm());
                    khjbxx.setDz(StringUtils.isEmpty(khjbxx.getDz()) ? null : nhxx.getZz());
//                    if (StringUtils.isEmpty(khjbxx.getWgbh())) {}
//                    if (StringUtils.isEmpty(khjbxx.getSszh())) {}
//                    if (StringUtils.isEmpty(khjbxx.getJgdm())) {}
//                    if (StringUtils.isEmpty(khjbxx.getKhlx())) {}
//                    if (StringUtils.isEmpty(khjbxx.getLxfs())) {}
//                    if (StringUtils.isEmpty(khjbxx.getDz())) {}
                    khjbxxService.update(khjbxx, khjbxxQueryWrapper);
                } else {
                    khjbxx = new Khjbzl();
                    khjbxx.setWgbh(nhxx.getWgbh());
                    khjbxx.setJgdm(nhxx.getJgdm());
                    khjbxx.setSszh(nhxx.getSszh());
                    khjbxx.setKhmc(nhxx.getKhmc());
                    khjbxx.setZjlx("01");
                    khjbxx.setZjhm(nhxx.getZjhm());
                    khjbxx.setLxfs(nhxx.getSjhm());
                    khjbxx.setDz(nhxx.getZz());
                    khjbxx.setKhxz("1");
                    khjbxx.setKhlx(nhxx.getKhlx());
                    khjbxx.setKhlb("2");
                    khjbxx.setDabh(UUIDGenerator.generate());
                    khjbxx.setCreateTime(new Date());
                    khjbxx.setCreateBy(getLoginUser().getUsername());
                    khjbxxService.save(khjbxx);
                }

                //保存 回访信息
                saveYxglKhhfxxb(nhhzxx.getSsyxdy(), nhhzxx.getHzxm(), nhhzxx.getHzzjhm(), nhhzxx.getSszh(), nhhzxx.getZfry());

                if (!QydmEnums.LIUYANG.getQydmCode().equals(qybm)) {
                    if (false) {
                        QueryWrapper<KhxxglGrsxlxmxNh> nhGrsxLxmxQueryWrapper = new QueryWrapper<>();
                        nhGrsxLxmxQueryWrapper.eq("zjhm", nhhzxx.getHzzjhm());
                        KhxxglGrsxlxmxNh nhGrsxLxmxRecord = nhGrsxLxmxService.getOne(nhGrsxLxmxQueryWrapper,false);
                        if (nhGrsxLxmxRecord == null || VNhhzxxglService.init3(xzhzxx.getHhbm()) == 0) {
                            VNhhzxxglService.init1(xzhzxx.getHhbm());
                            //同步oracle到impala
                            EtlUtil.SHcallEtlRc(10, true, false, false, "tjfx_cssz", "idap");
                            //同步之前删除impala数据中khxxgl_grsxlxmx_nh表数据
                            nhGrsxLxmx1Service.delNhgrsxlxmx();
                            EtlUtil.SHcallEtlRc(10, true, false, true, "khxxgl_grsxlxmx_nh", "idap", xzhzxx.getHhbm());
                            nhGrsxLxmxService.delNhgrsxlxmxByHhbm(xzhzxx.getHhbm());
                            //调用python脚本
                            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxsxlxtj.py --hhbm " + xzhzxx.getHhbm() + "'");
                            //同步impala到oracle
                            sshUtil.execShell("sh /home/exportdata/P_TJFX_SXLXTJ_EXPORT.sh");
                            sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_SXLXTJ_IMPORT.sh");
                            VNhhzxxglService.init2(xzhzxx.getHhbm());
                        }
                    }
                    VNhhzxxglService.init(xzhzxx.getHhbm(), xzhzxx.getHzzjhm(), getLoginUser().getWorkNo(), getLoginUser().getUsername(), "");
                }

                //保存农户户主佐证资料信息
//                QueryWrapper<Nhzzzlxxb> hzzzzlxxQueryWrapper = new QueryWrapper<>();
//                hzzzzlxxQueryWrapper.eq("hhbm",xzhzxx.getHhbm());
//                hzzzzlxxQueryWrapper.eq("zjhm",xzhzxx.getHzzjhm());
//                hzzzzlxxQueryWrapper.eq("zzlx","1");
//                List<Nhzzzlxxb> nhhzzzzlList = nhzzzlxxbService.list(hzzzzlxxQueryWrapper);
//                for (Nhzzzlxxb record : nhhzzzzlList) {
//                    KhglNhhzzllb nhzzlb = new KhglNhhzzllb();
//                    nhzzlb.setId(record.getId());
//                    nhzzlb.setQydm(record.getQydm());
//                    nhzzlb.setHhbm(record.getHhbm());
//                    nhzzlb.setZlbh(IdUtil.simpleUUID());
//                    nhzzlb.setZllx(record.getZllx());
//                    nhzzlb.setZldx(record.getZldx());
//                    nhzzlb.setFwlj(record.getFwlj());
//                    nhzzlb.setZlmc(record.getZlmc());
//                    nhzzlb.setZllj(record.getZllj());
//                    nhzzlb.setScsj(new Date());
//                    nhzzlb.setScr(getLoginUser().getUsername());
//                    nhzzlb.setLrsj(new Date());
//                    nhzzlb.setLrr(getLoginUser().getUsername());
//                    nhhzzllbService.save(nhzzlb);
//                }

                //更新 审核状态 审核批注 审批通过后 待审批数据 保留，但只运行查看
                UpdateWrapper<NhhzxxXzSP> xzhzxxDspUpdateWrapper = new UpdateWrapper<>();
                xzhzxxDspUpdateWrapper.eq("id",xzhzxx.getId());
                xzhzxx.setShzt("1");
                xzhzxx.setLrbz("2");
                xzhzxx.setLrr(getLoginUser().getUsername());
                xzhzxx.setLrsj(new Date());
                nhhzxxXzSPService.update(xzhzxx,xzhzxxDspUpdateWrapper);
            } else {
                // 驳回

                //更新 审核状态 审核批注 审批通过后 待审批数据 保留，但只运行查看
                UpdateWrapper<NhhzxxXzSP> xzhzxxDspUpdateWrapper = new UpdateWrapper<>();
                xzhzxxDspUpdateWrapper.eq("id",xzhzxx.getId());
                xzhzxx.setShzt("2");
                xzhzxx.setLrbz("2");
                xzhzxx.setLrr(getLoginUser().getUsername());
                xzhzxx.setLrsj(new Date());
                nhhzxxXzSPService.update(xzhzxx,xzhzxxDspUpdateWrapper);
            }

            return Result.ok("审核成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("新增户主信息，审核失败！"+e);
            return Result.ok("审核失败！"+e.getMessage());
        }
    }

    /**
     * 保存回访信息
     *
     * @param yxdy
     * @param khmc
     * @param zjhm
     */
    protected void saveYxglKhhfxxb(String yxdy, String khmc, String zjhm, String kksszh, String zfyy) {
        Date hfrq = DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd"), "yyyy-MM-dd");
        this.saveYxglKhhfxxb(yxdy, khmc, zjhm, hfrq, getWorkNo(), getUsername(), kksszh, zfyy);
    }

    /**
     * 保存回访信息
     *
     * @param yxdy
     * @param khmc
     * @param zjhm
     */
    protected void saveYxglKhhfxxb(String yxdy, String khmc, String zjhm, Date hfrq, String workNo, String userName, String khsszh, String zfyy) {
        YxglKhhfxxb khhfxx = new YxglKhhfxxb();
        Vhrbasstaffpost v_hr_bas_staff_post = vHrBasStaffPostService.selectByYggh(workNo);
        if (v_hr_bas_staff_post != null) {
            khhfxx.setZzbz(v_hr_bas_staff_post.getZzbz());
            khhfxx.setYggh(v_hr_bas_staff_post.getYggh());
            khhfxx.setKhjlbh(v_hr_bas_staff_post.getKhjlbz());
            if (khsszh.equals(v_hr_bas_staff_post.getZzbz())) {
                khhfxx.setGsqk("1");
            } else {
                khhfxx.setGsqk("2");
            }
        } else {
            khhfxx.setGsqk("2");
        }
        khhfxx.setKhsszh(khsszh);
        khhfxx.setZfyy(zfyy);
        khhfxx.setYxdy(yxdy);
        khhfxx.setKhmc(khmc);
        khhfxx.setZjhm(zjhm);
        khhfxx.setHfrq(hfrq);
        khhfxx.setSjly("2");
        khhfxx.setLrr(userName);
        khhfxxbService.save(khhfxx);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户户主信息（新增待审核）-通过id删除")
    @ApiOperation(value = "农户户主信息（新增待审核）-通过id删除", notes = "农户户主信息（新增待审核）-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        nhhzxxXzSPService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户户主信息（新增待审核）-通过id查询")
    @ApiOperation(value = "农户户主信息（新增待审核）-通过id查询", notes = "农户户主信息（新增待审核）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        NhhzxxXzSP nhhzxxXzSP = nhhzxxXzSPService.getById(id);
        return Result.ok(nhhzxxXzSP);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param nhhzxxXzSP
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NhhzxxXzSP nhhzxxXzSP) {
        return super.exportXls(request, nhhzxxXzSP, NhhzxxXzSP.class, "农户户主信息（新增待审核）");
    }

}
