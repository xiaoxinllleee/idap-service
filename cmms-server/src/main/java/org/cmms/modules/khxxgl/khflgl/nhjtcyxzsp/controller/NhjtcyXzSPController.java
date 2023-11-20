package org.cmms.modules.khxxgl.khflgl.nhjtcyxzsp.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONString;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khxxgl.khflgl.nhjtcyxzsp.entity.NhhzXzcyYjtcyxx;
import org.cmms.modules.khxxgl.khflgl.nhjtcyxzsp.entity.NhjtcyXzSP;
import org.cmms.modules.khxxgl.khflgl.nhjtcyxzsp.service.INhhzXzcyYjtcyxxService;
import org.cmms.modules.khxxgl.khflgl.nhjtcyxzsp.service.INhjtcyXzSPService;

import java.util.Date;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;
import org.cmms.modules.pad.nhxxgl.entity.Nhzzzlxxb;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzzllbService;
import org.cmms.modules.pad.nhxxgl.service.INhzzzlxxbService;
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
 * @Description: 家庭成员信息（新增待审批）
 * @Author: jeecg-boot
 * @Date: 2023-07-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "家庭成员信息（新增待审批）")
@RestController
@RequestMapping("/khxxgl/khflgl/nhjtcyXzSP")
public class NhjtcyXzSPController extends JeecgController<NhjtcyXzSP, INhjtcyXzSPService> {
    @Autowired
    private INhjtcyXzSPService nhjtcyXzSPService;
    @Autowired
    private INhzzzlxxbService nhzzzlxxbService;
    @Autowired
    private IKhglNhhzxxglService nhhzxxglService;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private IKhjbzlService khjbzlService;
    @Autowired
    private INhhzXzcyYjtcyxxService nhhzXzcyYjtcyxxService;
    @Autowired
    private IKhglNhhzzllbService nhhzzllbService;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    /**
     * 分页列表查询
     *
     * @param nhjtcyXzSP
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "家庭成员信息（新增待审批）-分页列表查询")
    @ApiOperation(value = "家庭成员信息（新增待审批）-分页列表查询", notes = "家庭成员信息（新增待审批）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(NhjtcyXzSP nhjtcyXzSP,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<NhjtcyXzSP> queryWrapper = QueryGenerator.initQueryWrapper(nhjtcyXzSP, req.getParameterMap());
        Page<NhjtcyXzSP> page = new Page<NhjtcyXzSP>(pageNo, pageSize);
        IPage<NhjtcyXzSP> pageList = nhjtcyXzSPService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    /**
     * Pad端 消息中心 新增家庭成员审核结果 分页列表查询 暂时只展示5条
     *
     * @return
     */
    @AutoLog(value = "新增家庭成员审核结果-分页列表查询")
    @ApiOperation(value = "新增家庭成员审核结果-分页列表查询", notes = "新增家庭成员审核结果-分页列表查询")
    @GetMapping(value = "/queryJtcyAddToExamine")
    public Result<?> queryJtcyAddToExamine(@RequestParam(name = "more") String more) {
//        log.info("当前登录操作员用户名----"+getLoginUser().getUsername());
        QueryWrapper<NhjtcyXzSP> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lrczy",getLoginUser().getUsername());
        queryWrapper.in("shzt","1","2");
        if ("true".equals(more)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = DateUtils.stringToDate(sdf.format(new Date())+" 00:00:00","yyyy-MM-dd HH:mm:ss");
            Date parseDate = DateUtils.getDateBefore(now,7);
            queryWrapper.ge("lrczsj",parseDate);
        } else {
            queryWrapper.and(i ->i.le("rownum","5"));
        }
        queryWrapper.orderByDesc("lrczsj");
        List<NhjtcyXzSP> list = nhjtcyXzSPService.list(queryWrapper);
        return Result.ok(list);
    }

    /**
     * 永兴 新增家庭成员信息 待审批 未找到客户档案的新增家庭成员
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "永兴-App端新增农户家庭成员信息保存")
    @ApiOperation(value = "永兴-App端新增农户家庭成员信息保存", notes = "永兴-App端新增农户家庭成员信息保存")
    @Transactional
    @RequestMapping(value = "/AddNoRecordJtcyxxDsp", method = RequestMethod.POST)
    public Result<?> AddNoRecordJtcyxxDsp(@RequestBody JSONObject jsonObject) {
        try {
            String shid = UUIDGenerator.generate();

            NhjtcyXzSP xzjtcyxx = JSON.toJavaObject(jsonObject.getJSONObject("formData"), NhjtcyXzSP.class);
            log.info("----新 增 家 庭 成 员 信 息----" + xzjtcyxx);
            if (xzjtcyxx != null) {
                xzjtcyxx.setId(shid);
                xzjtcyxx.setShzt("0");
                xzjtcyxx.setLrbz("1");
                xzjtcyxx.setLrr(getLoginUser().getUsername());
                xzjtcyxx.setLrsj(new Date());
                xzjtcyxx.setLrczy(getLoginUser().getUsername());
                xzjtcyxx.setLrczsj(new Date());
                //通过户号编码获取新户主姓名
                QueryWrapper<KhglNhhzxxgl> nhhzxxQueryWrapper = new QueryWrapper<>();
                nhhzxxQueryWrapper.eq("hhbm", xzjtcyxx.getHhbm());
                KhglNhhzxxgl hzxx = nhhzxxglService.getOne(nhhzxxQueryWrapper, false);
                if (hzxx != null) {
                    xzjtcyxx.setHzxm(hzxx.getHzxm());
                }
            }
            nhjtcyXzSPService.save(xzjtcyxx);

            JSONArray jsonArray = jsonObject.getJSONArray("uploadFiles");
            String jsonObjString = JSON.toJSONString(jsonArray);
            List<Nhzzzlxxb> nhzzzlxxbList = JSONArray.parseArray(jsonObjString, Nhzzzlxxb.class);
            log.info("----新 增 家 庭 成 员 佐 证 附 件----" + nhzzzlxxbList);
            for (Nhzzzlxxb nhzzzlxxb : nhzzzlxxbList) {
                //插入新上传的佐证附件信息
                Nhzzzlxxb nhzzzlxx = new Nhzzzlxxb();
                nhzzzlxx.setId(UUIDGenerator.generate());
                nhzzzlxx.setShid(shid);
                nhzzzlxx.setZzlx("2");
                nhzzzlxx.setQydm(null);
                nhzzzlxx.setHhbm(xzjtcyxx.getHhbm());
                nhzzzlxx.setZjhm(xzjtcyxx.getZjhm());
                nhzzzlxx.setZllx("1");
                nhzzzlxx.setZlbh(nhzzzlxxb.getZlbh());
                nhzzzlxx.setZlmc(nhzzzlxxb.getZlmc());
                nhzzzlxx.setZldx(nhzzzlxxb.getZldx());
                nhzzzlxx.setZllj(uploadpath + "/" + nhzzzlxxb.getFwlj());
                nhzzzlxx.setFwlj(nhzzzlxxb.getFwlj());
                nhzzzlxx.setScr(getLoginUser().getUsername());
                nhzzzlxx.setScsj(new Date());
                nhzzzlxx.setBz("新增(未建档)家庭成员佐证资料");
                nhzzzlxx.setLrbz("1");
                nhzzzlxx.setLrr(getLoginUser().getUsername());
                nhzzzlxx.setLrsj(new Date());
                nhzzzlxxbService.save(nhzzzlxx);
            }

            return Result.ok("添加家庭成员信息成功，请耐心等待审核！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加家庭成员信息失败！" + e);
            return Result.error("添加家庭成员信息失败！" + e.getMessage());
        }
    }

    /**
     * 永兴 新增家庭成员信息 待审批 已有客户档案的待添加客户/户主信息（及原家庭成员信息）
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "永兴-App端新增农户家庭成员信息保存")
    @ApiOperation(value = "永兴-App端新增农户家庭成员信息保存", notes = "永兴-App端新增农户家庭成员信息保存")
    @Transactional
    @RequestMapping(value = "/AddJtcyxxDsp", method = RequestMethod.POST)
    public Result<?> AddJtcyxxDsp(@RequestBody JSONObject jsonObject) {
        try {
            String shid = UUIDGenerator.generate();
            String jtcyId = jsonObject.getString("id");//待添加家庭成员 农户信息ID

            NhjtcyXzSP xzjtcyxx = JSON.toJavaObject(jsonObject.getJSONObject("formData"), NhjtcyXzSP.class);
            log.info("----新 增 家 庭 成 员 信 息----" + xzjtcyxx);
            if (xzjtcyxx.getZjhm() == null || xzjtcyxx.getZjhm().contains("*")) {
                //若待添加家庭成员证件号码为空或带*号，则重新查询证件号码
                QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
                nhxqQueryWrapper.eq("id", jtcyId);
                Nhxq nhxq = nhxqService.getOne(nhxqQueryWrapper, false);
                if (nhxq != null) {
                    xzjtcyxx.setZjhm(nhxq.getZjhm());
                }
            }
            if (xzjtcyxx != null) {
                xzjtcyxx.setId(shid);
                xzjtcyxx.setShzt("0");
                xzjtcyxx.setLrbz("1");
                xzjtcyxx.setLrr(getLoginUser().getUsername());
                xzjtcyxx.setLrsj(new Date());
                xzjtcyxx.setLrczy(getLoginUser().getUsername());
                xzjtcyxx.setLrczsj(new Date());
                //通过家庭成员证件号码获取所属网格
                QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
                nhxqQueryWrapper.eq("zjhm", xzjtcyxx.getZjhm());
                Nhxq nhxq = nhxqService.getOne(nhxqQueryWrapper, false);
                if (nhxq != null) {
                    xzjtcyxx.setOldSswg(nhxq.getWgbh());
                }
                if ("1".equals(xzjtcyxx.getOldYhzgx())) {
                    JSONArray jsonArray = jsonObject.getJSONArray("jtcyList");
                    List<NhhzXzcyYjtcyxx> yjtcyList = JSONObject.parseArray(jsonArray.toJSONString(), NhhzXzcyYjtcyxx.class);
                    log.info("----新 增 成 员 (户主) 原 家 庭 成 员 信 息----" + yjtcyList);
                    if (CollUtil.isNotEmpty(yjtcyList)) {
                        for (NhhzXzcyYjtcyxx record : yjtcyList) {
                            record.setShid(shid);
                            record.setLrr(getLoginUser().getUsername());
                            record.setLrsj(new Date());
                        }
                        nhhzXzcyYjtcyxxService.saveBatch(yjtcyList);
                    }
                }
            }
            nhjtcyXzSPService.save(xzjtcyxx);

            JSONArray jsonArray = jsonObject.getJSONArray("uploadFiles");
            String jsonObjString = JSON.toJSONString(jsonArray);
            List<Nhzzzlxxb> nhzzzlxxbList = JSONArray.parseArray(jsonObjString, Nhzzzlxxb.class);
            log.info("----新 增 家 庭 成 员 佐 证 附 件----" + nhzzzlxxbList);
            for (Nhzzzlxxb nhzzzlxxb : nhzzzlxxbList) {
                //插入新上传的佐证附件信息
                Nhzzzlxxb nhzzzlxx = new Nhzzzlxxb();
                nhzzzlxx.setId(UUIDGenerator.generate());
                nhzzzlxx.setShid(shid);
                nhzzzlxx.setZzlx("2");
                nhzzzlxx.setQydm(xzjtcyxx.getSswg());
                nhzzzlxx.setHhbm(xzjtcyxx.getHhbm());
                nhzzzlxx.setZjhm(xzjtcyxx.getZjhm());
                nhzzzlxx.setZllx("1");
                nhzzzlxx.setZlbh(nhzzzlxxb.getZlbh());
                nhzzzlxx.setZlmc(nhzzzlxxb.getZlmc());
                nhzzzlxx.setZldx(nhzzzlxxb.getZldx());
                nhzzzlxx.setZllj(uploadpath + "/" + nhzzzlxxb.getFwlj());
                nhzzzlxx.setFwlj(nhzzzlxxb.getFwlj());
                nhzzzlxx.setScr(getLoginUser().getUsername());
                nhzzzlxx.setScsj(new Date());
                nhzzzlxx.setBz("新增家庭成员佐证资料");
                nhzzzlxx.setLrbz("1");
                nhzzzlxx.setLrr(getLoginUser().getUsername());
                nhzzzlxx.setLrsj(new Date());
                nhzzzlxxbService.save(nhzzzlxx);
            }

            return Result.ok("添加家庭成员信息成功，请耐心等待审核！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加家庭成员信息失败！" + e);
            return Result.error("添加家庭成员信息失败！" + e.getMessage());
        }
    }

    /**
     * 永兴 新增家庭成员信息 审核
     *
     * @param xzjtcyxx
     * @return
     */
    @AutoLog(value = "家庭成员信息（新增待审批）-审核")
    @ApiOperation(value = "家庭成员信息（新增待审批）-审核", notes = "家庭成员信息（新增待审批）-审核")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody NhjtcyXzSP xzjtcyxx) {
        try {
            log.info("待审核家庭成员信息：" + xzjtcyxx);
            if ("1".equals(xzjtcyxx.getShzt())) {
                // 审核通过
                // TODO 以原户号编码有或无来判断 是否为未创建农户资料信息档案 的凭证 是否合理？ @Penghr 2023年7月18日
                if (xzjtcyxx.getOldHhbm() == null) {
                    // 未创建农户资料信息档案 待添加的家庭成员
                    QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
                    nhxqQueryWrapper.eq("zjhm", xzjtcyxx.getZjhm());
                    List<Nhxq> nhxqList = nhxqService.list(nhxqQueryWrapper);
                    if (CollUtil.isNotEmpty(nhxqList)) {
                        return Result.error("证件号码在农户信息表已存在，请核实！");
                    }

                    // 保存 农户信息
                    Nhxq nhxq = new Nhxq();
                    QueryWrapper<KhglNhhzxxgl> nhhzxxQueryWrapper = new QueryWrapper<>();
                    nhhzxxQueryWrapper.eq("hhbm", xzjtcyxx.getHhbm());
                    KhglNhhzxxgl nhhzxx = nhhzxxglService.getOne(nhhzxxQueryWrapper, false);
                    if (nhhzxx != null) {
                        nhxq.setSszh(nhhzxx.getSszh());
                        nhxq.setWgbh(nhhzxx.getSsyxdy());
                        Nhxq record = nhxqService.getByZjhm(nhhzxx.getHzzjhm());
                        nhxq.setJgdm(StringUtils.isNotBlank(record.getJgdm()) ? record.getJgdm() : null);
                    }
                    nhxq.setHhbm(xzjtcyxx.getHhbm());
                    nhxq.setKhmc(xzjtcyxx.getKhmc());
                    nhxq.setYhzgx(xzjtcyxx.getYhzgx());
                    nhxq.setXb(xzjtcyxx.getXb());
                    nhxq.setSjhm(xzjtcyxx.getLxfs());
                    nhxq.setZjhm(xzjtcyxx.getZjhm());
                    nhxq.setNl(xzjtcyxx.getNl());
                    nhxq.setSfhz("2");
                    nhxq.setKhlx("1");
                    nhxq.setLrsj(new Date());
                    nhxq.setLrr(getLoginUser().getUsername());
                    nhxqService.save(nhxq);

                    //同步 客户基本资料信息表
                    QueryWrapper<Khjbzl> khjbzlQueryWrapper = new QueryWrapper<>();
                    khjbzlQueryWrapper.eq("zjhm",nhxq.getZjhm());
                    Khjbzl khjbzl = khjbzlService.getOne(khjbzlQueryWrapper,false);
                    if (khjbzl != null) {
                        if (khjbzl.getKhxz() == null || khjbzl.getKhxz().isEmpty()) {
                            khjbzl.setKhxz("1");
                        } else {
                            boolean sfynh = false;
                            String[] split = khjbzl.getKhxz().split(",");
                            for (String khxz : split) {
                                if ("1".equals(khxz)) {
                                    sfynh = true;
                                }
                            }
                            if (!sfynh) {
                                khjbzl.setKhxz("1," + khjbzl.getKhxz());
                            }
                        }
                        khjbzl.setWgbh(StringUtils.isEmpty(khjbzl.getWgbh()) ? null : nhxq.getWgbh());
                        khjbzl.setSszh(StringUtils.isEmpty(khjbzl.getSszh()) ? null : nhxq.getSszh());
                        khjbzl.setJgdm(StringUtils.isEmpty(khjbzl.getJgdm()) ? null : nhxq.getJgdm());
                        khjbzl.setKhlx(StringUtils.isEmpty(khjbzl.getKhlx()) ? null : nhxq.getKhlx());
                        khjbzl.setLxfs(StringUtils.isEmpty(khjbzl.getLxfs()) ? null : nhxq.getSjhm());
                        khjbzl.setDz(StringUtils.isEmpty(khjbzl.getDz()) ? null : nhxq.getZz());
//                        if (StringUtils.isEmpty(khjbzl.getWgbh())) {}
//                        if (StringUtils.isEmpty(khjbzl.getSszh())) {}
//                        if (StringUtils.isEmpty(khjbzl.getJgdm())) {}
//                        if (StringUtils.isEmpty(khjbzl.getKhlx())) {}
//                        if (StringUtils.isEmpty(khjbzl.getLxfs())) {}
//                        if (StringUtils.isEmpty(khjbzl.getDz())) {}
                        khjbzlService.update(khjbzl,khjbzlQueryWrapper);
                    } else {
                        Khjbzl record = new Khjbzl();
                        record.setWgbh(nhxq.getWgbh());
                        record.setJgdm(nhxq.getJgdm());
                        record.setSszh(nhxq.getSszh());
                        record.setKhmc(nhxq.getKhmc());
                        record.setZjlx("01");
                        record.setZjhm(nhxq.getZjhm());
                        record.setLxfs(nhxq.getSjhm());
                        record.setDz(nhxq.getZz());
                        record.setKhxz("1");
                        record.setKhlx(nhxq.getKhlx());
                        record.setKhlb("2");
                        record.setDabh(UUIDGenerator.generate());
                        record.setCreateTime(new Date());
                        record.setCreateBy(getLoginUser().getUsername());
                        khjbzlService.save(record);
                    }

                    //保存农户户主佐证资料信息
//                    QueryWrapper<Nhzzzlxxb> hzzzzlxxQueryWrapper = new QueryWrapper<>();
//                    hzzzzlxxQueryWrapper.eq("zjhm",xzjtcyxx.getZjhm());
//                    hzzzzlxxQueryWrapper.eq("zzlx","2");
//                    List<Nhzzzlxxb> nhhzzzzlList = nhzzzlxxbService.list(hzzzzlxxQueryWrapper);
//                    for (Nhzzzlxxb record : nhhzzzzlList) {
//                        KhglNhhzzllb nhzzlb = new KhglNhhzzllb();
//                        nhzzlb.setId(record.getId());
//                        nhzzlb.setQydm(record.getQydm());
//                        nhzzlb.setHhbm(record.getHhbm());
//                        nhzzlb.setZlbh(IdUtil.simpleUUID());
//                        nhzzlb.setZllx(record.getZllx());
//                        nhzzlb.setZldx(record.getZldx());
//                        nhzzlb.setFwlj(record.getFwlj());
//                        nhzzlb.setZlmc(record.getZlmc());
//                        nhzzlb.setZllj(record.getZllj());
//                        nhzzlb.setScsj(new Date());
//                        nhzzlb.setScr(getLoginUser().getUsername());
//                        nhzzlb.setLrsj(new Date());
//                        nhzzlb.setLrr(getLoginUser().getUsername());
//                        nhhzzllbService.save(nhzzlb);
//                    }
                } else {
                    // 已有原户号编码 待添加的家庭成员
                    // 待添加成员是否为户主
                    if ("1".equals(xzjtcyxx.getOldYhzgx())) {
                        //待添加成员原先家庭中是否有家庭成员
                        QueryWrapper<NhhzXzcyYjtcyxx> xzcyYjtcyxxQueryWrapper = new QueryWrapper<>();
                        xzcyYjtcyxxQueryWrapper.eq("hhbm",xzjtcyxx.getOldHhbm());
                        List<NhhzXzcyYjtcyxx> yjtcyxxList = nhhzXzcyYjtcyxxService.list(xzcyYjtcyxxQueryWrapper);
                        if (yjtcyxxList != null && yjtcyxxList.size() > 0) {
                            String jtcyHzid = "";
                            for (NhhzXzcyYjtcyxx record : yjtcyxxList) {
                                String jtcySfhz = "2";
                                String jtcyYhzgx = record.getXyhzgx();
                                if ("1".equals(jtcyYhzgx)) {
                                    jtcySfhz = "1";
                                    jtcyHzid = record.getId();
                                }
                                Nhxq nhxq = new Nhxq();
                                nhxq.setYhzgx(jtcyYhzgx);
                                nhxq.setSfhz(jtcySfhz);
                                UpdateWrapper<Nhxq> nhxqUpdateWrapper = new UpdateWrapper<>();
                                nhxqUpdateWrapper.eq("id",record.getId());
                                nhxqService.update(nhxq,nhxqUpdateWrapper);
                            }
                            // 更新 户主表中的户主信息
                            KhglNhhzxxgl nhhzxx = new KhglNhhzxxgl();
                            QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
                            nhxqQueryWrapper.eq("id",jtcyHzid);
                            Nhxq nhxq = nhxqService.getOne(nhxqQueryWrapper,false);

                            QueryWrapper<KhglNhhzxxgl> nhhzxxQueryWrapper = new QueryWrapper<>();
                            nhhzxxQueryWrapper.eq("hhbm",xzjtcyxx.getOldHhbm());
                            KhglNhhzxxgl record = nhhzxxglService.getOne(nhhzxxQueryWrapper,false);
                            if (!StringUtils.isEmpty(record.getSxdxzjh()) && record.getSxdxzjh().equals(record.getHzzjhm())) {
                                //如果授信对象是户主
                                nhhzxx.setSxdx(nhxq.getKhmc());
                                nhhzxx.setSxdxzjh(nhxq.getZjhm());
                            }
                            nhhzxx.setHzxm(nhxq.getKhmc());
                            nhhzxx.setHzzjhm(nhxq.getZjhm());
                            UpdateWrapper<KhglNhhzxxgl> updateWrapper = new UpdateWrapper<>();
                            updateWrapper.eq("hhbm", xzjtcyxx.getOldHhbm());
                            // 更新 原家庭成员被选为户主的 更新为原家庭户主
                            nhhzxxglService.update(nhhzxx, updateWrapper);
                        } else {
                            //没有家庭成员，
                            //更新待添加成员花名册中的户号编码、与户主关系、是否户主
                            //删除户主信息表中的记录
                            QueryWrapper<KhglNhhzxxgl> deleteWrapper = new QueryWrapper<>();
                            deleteWrapper.eq("hhbm", xzjtcyxx.getOldHhbm());
                            nhhzxxglService.remove(deleteWrapper);
                        }

                    }

                    Nhxq nhxqxx = new Nhxq();
                    nhxqxx.setSfhz("2");
                    nhxqxx.setHhbm(xzjtcyxx.getHhbm());
                    nhxqxx.setYhzgx(xzjtcyxx.getYhzgx());
                    nhxqxx.setUpdateTime(new Date());
                    nhxqxx.setUpdateBy(getLoginUser().getUsername());
                    UpdateWrapper<Nhxq> nhxqUpdateWrapper = new UpdateWrapper<>();
                    nhxqUpdateWrapper.eq("zjhm", xzjtcyxx.getZjhm());
                    nhxqService.update(nhxqxx, nhxqUpdateWrapper);

                    //保存农户户主佐证资料信息
//                    QueryWrapper<Nhzzzlxxb> hzzzzlxxQueryWrapper = new QueryWrapper<>();
//                    hzzzzlxxQueryWrapper.eq("zjhm",xzjtcyxx.getZjhm());
//                    hzzzzlxxQueryWrapper.eq("zzlx","2");
//                    List<Nhzzzlxxb> nhhzzzzlList = nhzzzlxxbService.list(hzzzzlxxQueryWrapper);
//                    for (Nhzzzlxxb record : nhhzzzzlList) {
//                        KhglNhhzzllb nhzzlb = new KhglNhhzzllb();
//                        nhzzlb.setId(record.getId());
//                        nhzzlb.setQydm(record.getQydm());
//                        nhzzlb.setHhbm(record.getHhbm());
//                        nhzzlb.setZlbh(IdUtil.simpleUUID());
//                        nhzzlb.setZllx(record.getZllx());
//                        nhzzlb.setZldx(record.getZldx());
//                        nhzzlb.setFwlj(record.getFwlj());
//                        nhzzlb.setZlmc(record.getZlmc());
//                        nhzzlb.setZllj(record.getZllj());
//                        nhzzlb.setScsj(new Date());
//                        nhzzlb.setScr(getLoginUser().getUsername());
//                        nhzzlb.setLrsj(new Date());
//                        nhzzlb.setLrr(getLoginUser().getUsername());
//                        nhhzzllbService.save(nhzzlb);
//                    }
                }

                //更新 审核状态 审核批注 审批通过后 待审批数据 保留，但只运行查看
                UpdateWrapper<NhjtcyXzSP> xzjtcyxxDspUpdateWrapper = new UpdateWrapper<>();
                xzjtcyxxDspUpdateWrapper.eq("id",xzjtcyxx.getId());
                xzjtcyxx.setShzt("1");
                xzjtcyxx.setLrbz("2");
                xzjtcyxx.setLrr(getLoginUser().getUsername());
                xzjtcyxx.setLrsj(new Date());
                nhjtcyXzSPService.update(xzjtcyxx,xzjtcyxxDspUpdateWrapper);
            } else {
                // 驳回
                //更新 审核状态 审核批注 审批通过后 待审批数据 保留，但只运行查看
                UpdateWrapper<NhjtcyXzSP> xzjtcyxxDspUpdateWrapper = new UpdateWrapper<>();
                xzjtcyxxDspUpdateWrapper.eq("id",xzjtcyxx.getId());
                xzjtcyxx.setShzt("2");
                xzjtcyxx.setLrbz("2");
                xzjtcyxx.setLrr(getLoginUser().getUsername());
                xzjtcyxx.setLrsj(new Date());
                nhjtcyXzSPService.update(xzjtcyxx,xzjtcyxxDspUpdateWrapper);
            }
            return Result.ok("审核成功!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("新增家庭成员信息，审核失败！" + e);
            return Result.ok("审核失败！" + e.getMessage());
        }
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "家庭成员信息（新增待审批）-通过id删除")
    @ApiOperation(value = "家庭成员信息（新增待审批）-通过id删除", notes = "家庭成员信息（新增待审批）-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        nhjtcyXzSPService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "家庭成员信息（新增待审批）-批量删除")
    @ApiOperation(value = "家庭成员信息（新增待审批）-批量删除", notes = "家庭成员信息（新增待审批）-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.nhjtcyXzSPService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "家庭成员信息（新增待审批）-通过id查询")
    @ApiOperation(value = "家庭成员信息（新增待审批）-通过id查询", notes = "家庭成员信息（新增待审批）-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        NhjtcyXzSP nhjtcyXzSP = nhjtcyXzSPService.getById(id);
        return Result.ok(nhjtcyXzSP);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param nhjtcyXzSP
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NhjtcyXzSP nhjtcyXzSP) {
        return super.exportXls(request, nhjtcyXzSP, NhjtcyXzSP.class, "家庭成员信息（新增待审批）");
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
        return super.importExcel(request, response, NhjtcyXzSP.class);
    }

}
