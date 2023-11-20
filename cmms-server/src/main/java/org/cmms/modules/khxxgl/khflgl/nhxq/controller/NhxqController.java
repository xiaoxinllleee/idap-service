package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Iterables;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.K;
import org.cmms.common.api.vo.Result;
import org.cmms.common.api.vo.ResultConstant;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.superSearch.DataFilterUtil;
import org.cmms.common.utils.Base64Util;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khgl.nh.entity.*;
import org.cmms.modules.khgl.nh.service.*;
import org.cmms.modules.khgl.nh.vo.KhglKhcjxxPage;
import org.cmms.modules.khgl.nh.vo.ZcsxVo;
import org.cmms.modules.khxxgl.khflgl.bbpyinfo.bbpyjcxx.entity.BkbpyHxxVo;
import org.cmms.modules.khxxgl.khflgl.bbpyinfo.bbpyjcxx.entity.BkbpyJcxx;
import org.cmms.modules.khxxgl.khflgl.bbpyinfo.bbpyjcxx.service.IBkbpyJcxxService;
import org.cmms.modules.khxxgl.khflgl.bbpyinfo.ypyxx.entity.BkbpyYpyxx;
import org.cmms.modules.khxxgl.khflgl.bbpyinfo.ypyxx.service.IBkbpyYpyxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.*;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.NhxqBakMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhJzyxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;
import org.cmms.modules.khxxgl.khflgl.shxq.service.IShxqService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.pad.nhxxgl.entity.*;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;
import org.cmms.modules.pad.nhxxgl.service.*;
import org.cmms.modules.pad.pyxx.entity.Nhplpy;
import org.cmms.modules.pad.pyxx.entity.Pydjcs;
import org.cmms.modules.pad.pyxx.entity.Pyfjxx;
import org.cmms.modules.pad.pyxx.service.IPydjcsService;
import org.cmms.modules.pad.pyxx.service.IPyfjxxService;
import org.cmms.modules.pad.shxxgl.entity.Xyk;
import org.cmms.modules.pad.shxxgl.service.IXykService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.tjfx.czxxtz.entity.Czxxtz;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import org.cmms.modules.tjfx.tjfxHnkd.service.ITjfxHnkdService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.utils.AppConstant;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Cqtj;
import org.cmms.modules.yxdygl.yxdyglmain.entity.VYxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IVYxdyglMainService;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecgframework.poi.excel.view.JeecgTemplateExcelView;
import org.jeecgframework.poi.excel.view.JeecgTemplateWordView;
import org.jxls.common.CellData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 农户信息
 * @Author: jeecg-boot
 * @Date: 2021-12-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "农户信息")
@RestController
@RequestMapping("/khflgl/nhxq")
public class NhxqController extends JeecgController<Nhxq, INhxqService> {
    @Autowired
    private INhxqService nhxqService;
    @Value(value = "${common.path.upload}")
    private String uploadpath;
    @Value(value = "${common.path.export}")
    private String exportpath;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private IKhjbzlService khjbzlService;
    @Autowired
    private IvKhglNhhzxxglService ivKhglNhhzxxglService;
    @Autowired
    private IKhglNhhzzllbService khglNhhzzllbService;
    @Autowired
    private ICamsZcsxNhfcxxPadService camsZcsxNhfcxxPad;
    @Autowired
    private ICamsZcsxNhpjsxxxPadService iCamsZcsxNhpjsxxxPadService;
    @Autowired
    private IKhglYwhywwlxxPadService khglYwhywwlxxPadMapper;
    @Autowired
    private IKhywxxDksjmxPadService khywxxDksjmxPadMapper;
    @Autowired
    private IKhywxxSjyhPadService khywxxSjyhPadMapper;
    @Autowired
    private IKhywxxEtcService khywxxEtcMapper;
    @Autowired
    private IXykService xykService;
    @Autowired
    private IFjglService iFjglService;
    @Autowired
    private INhbkbpyService iNhbkbpyService;
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private IPyfjxxService pyfjxxService;
    @Autowired
    private ISysDicService sysDicService;

    @Autowired
    private IBkbpyJcxxService bkbpyJcxxService;
    @Autowired
    private IBkbpyYpyxxService bkbpyYpyxxService;
    @Autowired
    private IYxdyglMainService yxdyglMainService;
    @Autowired
    IVYxdyglMainService vYxdyglMainService;
    @Autowired
    ListToDictUtil listToDictUtil;
    @Autowired
    private INhbkbpyService nhbkbpyService;
    @Autowired
    private IvKhglNhhzxxglService vKhglNhhzxxgl12;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IYxglKhhfxxbService yxglKhhfxxbService;
    @Autowired
    private IVhrbasstaffpostService vhrbasstaffpostService;

    @Autowired
    private IVKhglNhjbxxService ivKhglNhjbxxService;
    @Autowired
    private IPydjcsService pydjcsService;
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private IKhxxglGrsxlxmxNhService khxxglGrsxlxmxNhService;
    @Autowired
    private INhJzyxService nhJzyxService;
    @Autowired
    private IShxqService shxqService;
    @Autowired
    private IKhxxglGrsxlxmxNh1Service khxxglGrsxlxmxNh1Service;
    @Autowired
    private ITjfxHnkdService tjfxHnkdService;
    @Autowired
    private IKhxxglYwhywwlxxHService khxxglYwhywwlxxHService;
    @Autowired
    ITjfxCsszService tjfxCsszService;

    /**
     * 分页列表查询
     *
     * @param nhxq
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "农户信息-分页列表查询")
    @ApiOperation(value = "农户信息-分页列表查询", notes = "农户信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Nhxq nhxq,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "sfcj", required = false) String sfcj,
                                   @RequestParam(name = "sfscfj", required = false) String sfscfj,
                                   @RequestParam(name = "sfyxzf", required = false) String sfyxzf,
                                   HttpServletRequest req) {
        String wgbh = nhxq.getWgbh();
        nhxq.setWgbh(null);
        QueryWrapper<Nhxq> queryWrapper = QueryGenerator.initQueryWrapper(nhxq, req.getParameterMap());
        Page<Nhxq> page = new Page<Nhxq>(pageNo, pageSize);
        if (StringUtils.isNotEmpty(sfcj)) {
            if ("1".equals(sfcj)) {
                queryWrapper.isNotNull("up_dt");
            } else if ("2".equals(sfcj)) {
                queryWrapper.isNull("up_dt");
            }
        }

        if (StringUtils.isNotEmpty(sfscfj)) {
            if ("1".equals(sfscfj)) {
                queryWrapper.exists("select 1 from (" +
                        " select zjhm from cams_jbxx_nhzllb " +
                        " union " +
                        " select t2.zjhm from (select hhbm from khgl_nhhzzllb group by hhbm) t1,khxxgl_khxq_nh t2" +
                        " where t1.hhbm=t2.hhbm" +
                        " ) n2 where khxxgl_khxq_nh.zjhm=n2.zjhm");
//                queryWrapper.and(i -> i.inSql("zjhm","select zjhm from cams_jbxx_nhzllb").or()
//                        .inSql("hhbm", "select hhbm from khgl_nhhzzllb"));
            } else if ("2".equals(sfscfj)) {
                queryWrapper.notInSql("zjhm", "select zjhm from cams_jbxx_nhzllb").notInSql("hhbm", "select hhbm from khgl_nhhzzllb");
            }
        }

        if (StringUtils.isNotEmpty(sfyxzf)) {
            if ("1".equals(sfyxzf)) {
                queryWrapper.inSql("zjhm", "select hzzjhm from khgl_nhhzxxgl where sfyxzf='1'");
            } else if ("2".equals(sfyxzf)) {
                queryWrapper.inSql("zjhm", "select hzzjhm from khgl_nhhzxxgl where sfyxzf!='1'");
            }
        }

        // 查询网格时，同时查询下级网格的数据
        if (StringUtils.isNotEmpty(wgbh)) {
            String sqlSswg = "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior id=parent_id";
            queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
        }

        if (!getUsername().equals("admin")) {
            //in  用 list有个数限制问题， 此处改为inSql
            String sqlZjhm = "select zjhm from khxxgl_khxq_nh where wgbh IN " +
                    "       (SELECT menu_id FROM YXDYGL_PQQXGL t WHERE khjl = '" + getWorkNo() + "') " +
                    "     union all " +
                    "     SELECT zjhm FROM KHXXGL_KHSSKHJL t WHERE sskhjl = '" + getWorkNo() + "'";

            queryWrapper.and(i -> i.inSql("zjhm", sqlZjhm));
            /** 效率慢
             String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + getWorkNo() + "'";
             String sqlSsKhjl = "select  zjhm from KHXXGL_KHSSKHJL t where sskhjl =" + "'" + getWorkNo() + "'";

             queryWrapper.and(i -> i.inSql("wgbh",sqlSswg).or().inSql("zjhm",sqlSsKhjl));
             */
//            queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
        }
        IPage<Nhxq> pageList = nhxqService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 根据户号编码查询批量评议记录
     */
    @GetMapping(value = "/queryPlpyByHhbm")
    public Result<?> queryPlpyByHhbm(@RequestParam(name = "hhbm", required = true) String hhbm) {
        QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hhbm", hhbm);
        List<Nhbkbpy> nhbkbpyList = nhbkbpyService.list(queryWrapper);
        return Result.ok(nhbkbpyList);
    }


//    @GetMapping(value = "/queryPlpyByHhbm")
//    public Result<?> queryPlpyByHhbm(Nhbkbpy nhbkbpy,
//                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
//                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
//                                   @RequestParam(name = "hhbm", required = true) String hhbm,
//                                   HttpServletRequest req) {
//        QueryWrapper<Nhbkbpy> queryWrapper = QueryGenerator.initQueryWrapper(nhbkbpy, req.getParameterMap());
//        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        queryWrapper.eq("hhbm",hhbm);
//        queryWrapper.orderByDesc("pysj");
//        Page<Nhbkbpy> page = new Page<Nhbkbpy>(pageNo, pageSize);
//        IPage<Nhbkbpy> pageList = nhbkbpyService.page(page, queryWrapper);
//        return Result.ok(pageList);
//    }

    /**
     * 添加
     *
     * @param nhxq
     * @return
     */
    @AutoLog(value = "农户信息-添加")
    @ApiOperation(value = "农户信息-添加", notes = "农户信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Nhxq nhxq) {
        QueryWrapper queryWrapperNhxx = new QueryWrapper();
        queryWrapperNhxx.eq("zjhm", nhxq.getZjhm());
        Nhxq one = nhxqService.getOne(queryWrapperNhxx);
        if (one != null) {
            return Result.error("添加失败，证件号已存在！");
        }
        if (StringUtils.isEmpty(nhxq.getHhbm())) {
            nhxq.setHhbm(nhxq.getWgbh() + sysDictService.queryhhbm("seq_hhbm.nextval"));
        }
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper queryWrapperZzbz = new QueryWrapper();
        queryWrapperZzbz.eq("zzbz", nhxq.getSszh());
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
        nhxq.setJgdm(hrBasOrganization.getYwjgdm());
        nhxq.setSfhz(nhxq.getYhzgx().equals("1") ? "1" : "2");
        nhxq.setLrbz("1");
        nhxq.setLrsj(new Date());
        nhxq.setLrr(getUsername());
        nhxqService.save(nhxq);

        QueryWrapper<Khjbzl> queryWrapper = new QueryWrapper();
        queryWrapper.eq("zjhm", nhxq.getZjhm());
        Khjbzl khjbzl = khjbzlService.getOne(queryWrapper);
        if (khjbzl != null) {
            if (khjbzl.getKhxz() == null || khjbzl.getKhxz().isEmpty()) {
                khjbzl.setKhxz("1");
            } else {
                Boolean sfynh = false;
                String[] split = khjbzl.getKhxz().split(",");
                for (String khxz : split) {
                    if (khxz.equals("1")) {
                        sfynh = true;
                    }
                    ;
                }
                if (!sfynh) {
                    khjbzl.setKhxz("1," + khjbzl.getKhxz());
                }
            }
            if (StringUtils.isEmpty(khjbzl.getWgbh())) {
                khjbzl.setWgbh(nhxq.getWgbh());
            }
            if (StringUtils.isEmpty(khjbzl.getSszh())) {
                khjbzl.setSszh(nhxq.getSszh());
            }
            if (StringUtils.isEmpty(khjbzl.getJgdm())) {
                khjbzl.setJgdm(nhxq.getJgdm());
            }
            if (StringUtils.isEmpty(khjbzl.getKhlx())) {
                khjbzl.setKhlx(nhxq.getKhlx());
            }
            if (StringUtils.isEmpty(khjbzl.getLxfs())) {
                khjbzl.setLxfs(nhxq.getSjhm());
            }
            if (StringUtils.isEmpty(khjbzl.getDz())) {
                khjbzl.setDz(nhxq.getZz());
            }
            khjbzlService.update(khjbzl, queryWrapper);

        } else {
            Khjbzl khjbzlSave = new Khjbzl();
            khjbzlSave.setWgbh(nhxq.getWgbh());
            khjbzlSave.setJgdm(nhxq.getJgdm());
            khjbzlSave.setSszh(nhxq.getSszh());
            khjbzlSave.setKhmc(nhxq.getKhmc());
            khjbzlSave.setZjlx("01");
            khjbzlSave.setZjhm(nhxq.getZjhm());
            khjbzlSave.setLxfs(nhxq.getSjhm());
            khjbzlSave.setDz(nhxq.getZz());
            khjbzlSave.setKhxz("1");
            khjbzlSave.setKhlx(nhxq.getKhlx());
            khjbzlSave.setKhlb("2");
            khjbzlSave.setDabh(UUIDGenerator.generate());
            khjbzlSave.setCreateTime(new Date());
            khjbzlSave.setCreateBy(sysUser.getUsername());
            khjbzlService.save(khjbzlSave);
        }
        if (nhxq.getYhzgx().equals("1")) {
            QueryWrapper<KhglNhhzxxgl> queryWrapperHzxx = new QueryWrapper();
            queryWrapperHzxx.eq("hhbm", nhxq.getHhbm());
            KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(queryWrapperHzxx);
            if (khglNhhzxxgl == null) {
                KhglNhhzxxgl khglNhhzxxgl1 = new KhglNhhzxxgl();
                BeanUtils.copyProperties(nhxq, khglNhhzxxgl1);
                String id = UUIDGenerator.generate();
                khglNhhzxxgl1.setId(id);
                khglNhhzxxgl1.setSsyxdy(nhxq.getWgbh());
                khglNhhzxxgl1.setHzxm(nhxq.getKhmc());
                khglNhhzxxgl1.setHzzjhm(nhxq.getZjhm());
                khglNhhzxxgl1.setHhbm(nhxq.getHhbm());
                //khglNhhzxxgl1.setSxdx(nhxq.getKhmc());
                //khglNhhzxxgl1.setSxdxzjh(nhxq.getZjhm());
                khglNhhzxxgl1.setZkhjl(sysUser.getWorkNo());
                khglNhhzxxgl1.setSfzf("2");
                khglNhhzxxgl1.setSfyxzf("2");
                khglNhhzxxgl1.setLrsj(new Date());
                khglNhhzxxgl1.setLrbz("1");
                khglNhhzxxgl1.setLrr(sysUser.getUsername());
                khglNhhzxxglService.save(khglNhhzxxgl1);
            }
        }
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param nhxq
     * @return
     */
    @AutoLog(value = "农户信息-编辑")
    @ApiOperation(value = "农户信息-编辑", notes = "农户信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Nhxq nhxq) {
        nhxq.setSfhz(nhxq.getYhzgx().equals("1") ? "1" : "2");
        QueryWrapper queryWrapperZzbz = new QueryWrapper();
        queryWrapperZzbz.eq("ywjgdm", nhxq.getJgdm());
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
        nhxq.setSszh(hrBasOrganization.getZzbz());
        nhxq.setLrr(getUsername());
        nhxq.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
        nhxq.setUpTm(DateUtil.formatDateTime("HHmmss"));
        nhxqService.updateById(nhxq);

        QueryWrapper<Khjbzl> queryWrapper = new QueryWrapper();
        queryWrapper.eq("zjhm", nhxq.getZjhm());
        Khjbzl khjbzl = khjbzlService.getOne(queryWrapper);
        if (khjbzl != null) {
            if (StringUtils.isEmpty(khjbzl.getWgbh())) {
                khjbzl.setWgbh(nhxq.getWgbh());
            }
            if (StringUtils.isEmpty(khjbzl.getJgdm())) {
                khjbzl.setJgdm(nhxq.getJgdm());
            }

            if (StringUtils.isEmpty(khjbzl.getKhlx())) {
                khjbzl.setKhlx(nhxq.getKhlx());
            }

            if (StringUtils.isEmpty(khjbzl.getLxfs())) {
                khjbzl.setLxfs(nhxq.getSjhm());
            }
            if (StringUtils.isEmpty(khjbzl.getDz())) {
                khjbzl.setDz(nhxq.getZz());
            }

            khjbzlService.update(khjbzl, queryWrapper);
        }
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户信息-通过id删除")
    @ApiOperation(value = "农户信息-通过id删除", notes = "农户信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        nhxqService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "农户信息-批量删除")
    @ApiOperation(value = "农户信息-批量删除", notes = "农户信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.nhxqService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户信息-通过id查询")
    @ApiOperation(value = "农户信息-通过id查询", notes = "农户信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Nhxq nhxq = nhxqService.getById(id);
        return Result.ok(nhxq);
    }

    @GetMapping(value = "/queryByZjhm")
    public Result<?> queryByZjhm(@RequestParam(name = "zjhm", required = true) String zjhm) {
        LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Nhxq::getZjhm, zjhm);
        List<Nhxq> list = service.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            List list1 = listToDictUtil.parseDictText(list);
            return Result.ok(list1.get(0));
        }
        return Result.ok();
    }

    /**
     * 导出excel
     *
     * @param request
     * @param nhxq
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Nhxq nhxq,
                                  @RequestParam(name = "sfcj", required = false) String sfcj,
                                  @RequestParam(name = "sfscfj", required = false) String sfscfj,
                                  @RequestParam(name = "sfyxzf", required = false) String sfyxzf
    ) {
        String title = "农户信息";
        // Step.1 组装查询条件
        String wgbh = nhxq.getWgbh();
        nhxq.setWgbh(null);
        QueryWrapper<Nhxq> queryWrapper = QueryGenerator.initQueryWrapper(nhxq, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String selections = request.getParameter("selections");
        String rowKey = request.getParameter("rowKey");

        //20211201 过滤选中数据
        //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            if (oConvertUtils.isNotEmpty(rowKey)) {
                queryWrapper.in(rowKey, selectionList);
            } else {
                queryWrapper.in("ID", selectionList);
            }
        }

        if (StringUtils.isNotEmpty(sfcj)) {
            if ("1".equals(sfcj)) {
                queryWrapper.isNotNull("up_dt");
            } else if ("2".equals(sfcj)) {
                queryWrapper.isNull("up_dt");
            }
        }

        if (StringUtils.isNotEmpty(sfscfj)) {
            if ("1".equals(sfscfj)) {
                queryWrapper.exists("select 1 from (" +
                        " select zjhm from cams_jbxx_nhzllb " +
                        " union " +
                        " select t2.zjhm from (select hhbm from khgl_nhhzzllb group by hhbm) t1,khxxgl_khxq_nh t2" +
                        " where t1.hhbm=t2.hhbm" +
                        " ) n2 where khxxgl_khxq_nh.zjhm=n2.zjhm");
//                queryWrapper.and(i -> i.inSql("zjhm","select zjhm from cams_jbxx_nhzllb").or()
//                        .inSql("hhbm", "select hhbm from khgl_nhhzzllb"));
            } else if ("2".equals(sfscfj)) {
                queryWrapper.notInSql("zjhm", "select zjhm from cams_jbxx_nhzllb").notInSql("hhbm", "select hhbm from khgl_nhhzzllb");
            }
        }

        if (StringUtils.isNotEmpty(sfyxzf)) {
            if ("1".equals(sfyxzf)) {
                queryWrapper.inSql("zjhm", "select hzzjhm from khgl_nhhzxxgl where sfyxzf='1'");
            } else if ("2".equals(sfyxzf)) {
                queryWrapper.inSql("zjhm", "select hzzjhm from khgl_nhhzxxgl where sfyxzf!='1'");
            }
        }

        // 查询网格时，同时查询下级网格的数据
        if (StringUtils.isNotEmpty(wgbh)) {
            String sqlSswg = "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior id=parent_id";
            queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
        }

        if (!getUsername().equals("admin")) {
            //in  用 list有个数限制问题， 此处改为inSql
            String sqlZjhm = "select zjhm from khxxgl_khxq_nh where wgbh IN " +
                    "       (SELECT menu_id FROM YXDYGL_PQQXGL t WHERE khjl = '" + getWorkNo() + "') " +
                    "     union all " +
                    "     SELECT zjhm FROM KHXXGL_KHSSKHJL t WHERE sskhjl = '" + getWorkNo() + "'";

            queryWrapper.and(i -> i.inSql("zjhm", sqlZjhm));
            /** 效率慢
             String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + getWorkNo() + "'";
             String sqlSsKhjl = "select  zjhm from KHXXGL_KHSSKHJL t where sskhjl =" + "'" + getWorkNo() + "'";

             queryWrapper.and(i -> i.inSql("wgbh",sqlSswg).or().inSql("zjhm",sqlSsKhjl));
             */
//            queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
        }

        // Step.2 获取导出数据
        //List<T> pageList = service.list(queryWrapper);
        //List<T> exportList = null;
        List<Nhxq> exportList = service.list(queryWrapper);

        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, Nhxq.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);

        return mv;
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
        String filePaths = jsonObject.getString("filePaths");
        if (StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(false);
            //params.setVerifyHanlder(khdjpdImportVerify);
            FileOutputStream fos = null;
            try {
                ExcelImportResult<NhxqImportVo> importResult = ExcelImportUtil.importExcelVerify(file, NhxqImportVo.class, params);
                List<NhxqImportVo> list = importResult.getList();
                List<Nhxq> nhxqList = new ArrayList<>();
                list.stream().forEach(e -> {
                    Nhxq nhxq = new Nhxq();
                    BeanUtils.copyProperties(e, nhxq);
                    nhxq.setLrbz("0");
                    nhxq.setLrsj(new Date());
                    nhxq.setLrr(getUsername());
                    //改为存储过程中处理
//					 QueryWrapper queryWrapperZzbz =new QueryWrapper();
//					 queryWrapperZzbz.eq("ywjgdm",nhxq.getJgdm());
//					 HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
//					 nhxq.setSszh(hrBasOrganization.getZzbz());
//					 nhxq.setSfhz(nhxq.getYhzgx().equals("1")?"1":"2");
                    nhxqList.add(nhxq);
                });
                nhxqService.saveBatch(nhxqList);
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                nhxqService.init();
                String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
//                if (org.apache.commons.lang.StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
//                    //同步oracle到impala
//                    EtlUtil.SHcallEtlRc(10, true, false, false, "khxxgl_khxq_nh", "idap");
//                    EtlUtil.SHcallEtlRc(10, true, false, false, "khgl_nhhzxxgl", "idap");
//                    //调用python脚本
//                    sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_nhxxxx.py'");
//                    //同步impala到oracle
//                    sshUtil.execShell("sh /home/exportdata/P_NHXXXX_EXPORT.sh");
//                    sshUtil.execShell("su - oracle - /home/importdata/P_NHXXXX_IMPORT.sh");
//                }
                return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }


    /**
     * 提取
     */
    @RequestMapping(value = "/extract")
    public Result<?> extract() {
        Result result = new Result<>();
        try {
            nhxqService.init();
//            String qybm = sysDicService.queryByCode("101001").getValue();
//            if (org.apache.commons.lang.StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
//                //同步oracle到impala
//                EtlUtil.SHcallEtlRc(10, true, false, false, "khxxgl_khxq_nh", "idap");
//                EtlUtil.SHcallEtlRc(10, true, false, false, "khgl_nhhzxxgl", "idap");
//                //调用python脚本
//                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_nhxxxx.py'");
//                //同步impala到oracle
//                sshUtil.execShell("sh /home/exportdata/P_NHXXXX_EXPORT.sh");
//                sshUtil.execShell("su - oracle - /home/importdata/P_NHXXXX_IMPORT.sh");
//            }
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            log.error("提取失败", e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 导出Excel模板
     *
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls() {
        return super.exportTemplateXls(NhxqImportVo.class, "农户信息导入模板");
    }

    /**
     * 通过证件号码去查询整户的数据
     */
    @RequestMapping("/getHByZjhm")
    public Result<?> getHByZjhm(String zjhm) {
        List<Nhxq> hByZjhm = service.getHByZjhm(zjhm);
        if (CollUtil.isNotEmpty(hByZjhm))
            return Result.ok(hByZjhm);
        return Result.error(ResultConstant.WCXDXGSJ);
    }


    @RequestMapping("/getAllInfo")
    public Result<?> getAllInfo(String id) {
        System.out.println("getAllInfo");
        System.out.println(id);

        NhxqPersonVO nhxqPersonVO = new NhxqPersonVO();
        //查农户详情
        Nhxq byId = nhxqService.getById(id);
        if (byId != null) {
            nhxqPersonVO.setNhxq(byId);

            String hhbm = byId.getHhbm();
            String zjhm = byId.getZjhm();

            QueryWrapper<vKhglNhhzxxgl> vKhglNhhzxxglhhbm = new QueryWrapper<>();
            vKhglNhhzxxglhhbm.eq("hhbm", hhbm);
            List<vKhglNhhzxxgl> list = ivKhglNhhzxxglService.list(vKhglNhhzxxglhhbm);
            if (CollUtil.isNotEmpty(list)) {
                nhxqPersonVO.setVKhglNhhzxxgls(list);
            }

            //查户主
            QueryWrapper<KhglNhhzxxgl> khglNhhzxxglhhbm = new QueryWrapper<>();
            khglNhhzxxglhhbm.eq("hhbm", hhbm);
            KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(khglNhhzxxglhhbm);
            if (khglNhhzxxgl != null)
                nhxqPersonVO.setKhglNhhzxxgl(khglNhhzxxgl);

            //家庭信息
            QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
            nhxqQueryWrapper.eq("hhbm", hhbm);
            nhxqQueryWrapper.orderByAsc("yhzgx");
            List<Nhxq> nhxqs = nhxqService.list(nhxqQueryWrapper);
            List<Nhxq> nhxqss = new ArrayList<>();
            if (CollUtil.isNotEmpty(nhxqs)) {
                for (int i = 0; i < nhxqs.size(); i++) {
                    Nhxq nhxq = nhxqs.get(i);
                    if (StringUtils.isNotBlank(nhxq.getXb())) {
                        nhxq.setXb(sysDictService.queryDictTextByKey("sex", nhxq.getXb()));
                    }
                    if (StringUtils.isNotBlank(nhxq.getYhzgx())) {
                        nhxq.setYhzgx(sysDictService.queryDictTextByKey("yhzgx", nhxq.getYhzgx()));
                    }
                    nhxqss.add(nhxq);
                }
                nhxqPersonVO.setNhxqs(nhxqss);
            }

            //附件
            QueryWrapper<KhglNhhzzllb> fjxxQueryWrapper = new QueryWrapper<>();
            fjxxQueryWrapper.eq("hhbm", hhbm);
            List<KhglNhhzzllb> khglNhhzzllbs = khglNhhzzllbService.list(fjxxQueryWrapper);
            List<KhglNhhzzllb> khglNhhzzllbss = new ArrayList<>();
            if (CollUtil.isNotEmpty(khglNhhzzllbs)) {

                for (int i = 0; i < khglNhhzzllbs.size(); i++) {
                    KhglNhhzzllb khglNhhzzllb = khglNhhzzllbs.get(i);
                    if (StringUtils.isNotBlank(khglNhhzzllb.getZllx())) {
                        String zllx = khglNhhzzllb.getZllx();
                        if ("1".equals(zllx)) {
                            khglNhhzzllb.setZllx("入户照片");
                        } else if ("2".equals(zllx)) {
                            khglNhhzzllb.setZllx("房屋照片");
                        } else if ("3".equals(zllx)) {
                            khglNhhzzllb.setZllx("与客户合影");
                        } else if ("4".equals(zllx)) {
                            khglNhhzzllb.setZllx("室内照片");
                        } else if ("5".equals(zllx)) {
                            khglNhhzzllb.setZllx("户口薄");
                        } else {
                            khglNhhzzllb.setZllx("其他附件");
                        }
                    }
                    khglNhhzzllbss.add(khglNhhzzllb);
                }
                nhxqPersonVO.setKhglNhhzzllbs(khglNhhzzllbss);
            }

            QueryWrapper<Fjgl> fjglQueryWrapper = new QueryWrapper<>();
            fjglQueryWrapper.eq("zjhm", zjhm);
            List<Fjgl> fjgls = iFjglService.list(fjglQueryWrapper);
            List<Fjgl> fjglss = new ArrayList<>();
            if (CollUtil.isNotEmpty(fjgls)) {
                for (int i = 0; i < fjgls.size(); i++) {
                    Fjgl fjgl = fjgls.get(i);
                    if (StringUtils.isNotBlank(fjgl.getZllx())) {
                        fjgl.setZllx(sysDictService.queryDictTextByKey("bkb_grfjxx", fjgl.getZllx()));
                    }
                    fjglss.add(fjgl);
                }
                nhxqPersonVO.setFjgls(fjglss);
            }

            //房产信息
            QueryWrapper<CamsZcsxNhfcxxPad> NhfcxxPadWrapper = new QueryWrapper<>();
            NhfcxxPadWrapper.eq("hhbm", hhbm);
            List<CamsZcsxNhfcxxPad> camsZcsxNhfcxxPads = camsZcsxNhfcxxPad.list(NhfcxxPadWrapper);
            if (CollUtil.isNotEmpty(camsZcsxNhfcxxPads)) {
                nhxqPersonVO.setCamsZcsxNhfcxxPads(camsZcsxNhfcxxPads);
            }

            //评议授信
            QueryWrapper<CamsZcsxNhpjsxxxPad> camsZcsxNhpjsxxxPadQueryWrapper = new QueryWrapper<>();
            camsZcsxNhpjsxxxPadQueryWrapper.eq("hhbm", hhbm);
            List<CamsZcsxNhpjsxxxPad> camsZcsxNhpjsxxxPads = iCamsZcsxNhpjsxxxPadService.list(camsZcsxNhpjsxxxPadQueryWrapper);
            if (CollUtil.isNotEmpty(camsZcsxNhpjsxxxPads)) {
                nhxqPersonVO.setCamsZcsxNhpjsxxxPads(camsZcsxNhpjsxxxPads.get(0));
            }

			 /*QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("hhbm", hhbm);
			 List<CamsZcsxNhpjsxxxPad> list = iCamsZcsxNhpjsxxxPadService.list(queryWrapper);*/
			/* QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("hhbm", hhbm);
			 List<CamsZcsxNhpjsxxxPad> nhPjsxxxList = iCamsZcsxNhpjsxxxPadService.list(queryWrapper);*/

            //业务信息
            QueryWrapper<KhglYwhywwlxxPad> ywhywwlxxPadQueryWrapper = new QueryWrapper<>();
            ywhywwlxxPadQueryWrapper.eq("zjhm", zjhm);
            List<KhglYwhywwlxxPad> cdkywxxList = khglYwhywwlxxPadMapper.list(ywhywwlxxPadQueryWrapper);
            if (CollUtil.isNotEmpty(cdkywxxList)) {
                nhxqPersonVO.setCdkywxxList(cdkywxxList);
            }
            //贷款数据明细
            QueryWrapper<KhywxxDksjmxPad> dksjmx = new QueryWrapper<>();
            dksjmx.eq("zjhm", zjhm);
            List<KhywxxDksjmxPad> dksjmxList = khywxxDksjmxPadMapper.list(dksjmx);
            List<KhywxxDksjmxPad> dksjmxLists = new ArrayList<>();
            if (CollUtil.isNotEmpty(dksjmxList)) {
                for (int i = 0; i < dksjmxList.size(); i++) {
                    KhywxxDksjmxPad khywxxDksjmxPad = dksjmxList.get(i);
                    khywxxDksjmxPad.setDkxt(khywxxDksjmxPad.getDkxt() == null ? "" : sysDictService.queryDictTextByKey("dkxt", khywxxDksjmxPad.getDkxt()));
                    khywxxDksjmxPad.setDbfs(khywxxDksjmxPad.getDbfs() == null ? "" : sysDictService.queryDictTextByKey("dbfs", khywxxDksjmxPad.getDbfs()));
                    khywxxDksjmxPad.setDkpz(khywxxDksjmxPad.getDkpz() == null ? "" : sysDictService.queryDictTextByKey("dkzl", khywxxDksjmxPad.getDkpz()));
                    khywxxDksjmxPad.setDyzrr(khywxxDksjmxPad.getDyzrr() == null ? "" : sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", khywxxDksjmxPad.getDyzrr()));
                    if (!StringUtils.isEmpty(khywxxDksjmxPad.getKhjlbz())) {
                        khywxxDksjmxPad.setKhjlbz(sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", khywxxDksjmxPad.getKhjlbz()));
                    }
                    dksjmxLists.add(khywxxDksjmxPad);
                }
                nhxqPersonVO.setDksjmxList(dksjmxLists);
            }

            //手机银行
            QueryWrapper<KhywxxSjyhPad> sjyh = new QueryWrapper<>();
            sjyh.eq("zjhm", zjhm);
            List<KhywxxSjyhPad> sjyhList = khywxxSjyhPadMapper.list(sjyh);
            List<KhywxxSjyhPad> sjyhLists = new ArrayList<>();
            if (CollUtil.isNotEmpty(sjyhList)) {
                for (int i = 0; i < sjyhList.size(); i++) {
                    KhywxxSjyhPad khywxxSjyhPad = sjyhList.get(i);
                    khywxxSjyhPad.setOpenJgdm(khywxxSjyhPad.getOpenJgdm() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khywxxSjyhPad.getOpenJgdm()));
                    khywxxSjyhPad.setOpenType(khywxxSjyhPad.getOpenType() == null ? "" : sysDictService.queryDictTextByKey("sjyh_khlx", khywxxSjyhPad.getOpenType()));
                    khywxxSjyhPad.setStatus(khywxxSjyhPad.getStatus() == null ? "" : sysDictService.queryDictTextByKey("khywxx_kxhzt", khywxxSjyhPad.getStatus()));
                    sjyhLists.add(khywxxSjyhPad);
                }
                nhxqPersonVO.setSjyhList(sjyhLists);
            }

            //ETC信息
            QueryWrapper<KhywxxEtc> etc = new QueryWrapper<>();
            etc.eq("zjhm", zjhm);
            List<KhywxxEtc> etcList = khywxxEtcMapper.list(etc);
            List<KhywxxEtc> etcLists = new ArrayList<>();
            if (CollUtil.isNotEmpty(etcList)) {
                for (int i = 0; i < etcList.size(); i++) {
                    KhywxxEtc khywxxEtc = etcList.get(i);
                    khywxxEtc.setOpenJgdm(khywxxEtc.getOperJgdm() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", khywxxEtc.getOpenJgdm()));
                    khywxxEtc.setOperYggh(khywxxEtc.getOperYggh() == null ? "" : sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", khywxxEtc.getOperYggh()));
                    khywxxEtc.setZhlx(khywxxEtc.getZhlx() == null ? "" : sysDictService.queryDictTextByKey("etc_zhlx", khywxxEtc.getZhlx()));
                    khywxxEtc.setStatus(khywxxEtc.getStatus() == null ? "" : sysDictService.queryDictTextByKey("etc_zhlx", khywxxEtc.getStatus()));
                    etcLists.add(khywxxEtc);
                }
                nhxqPersonVO.setEtcList(etcLists);
            }

            //信用卡
            QueryWrapper<Xyk> xykQueryWrapper = new QueryWrapper<>();
            xykQueryWrapper.eq("zjhm", zjhm);
            List<Xyk> xykList = xykService.list(xykQueryWrapper);
            if (CollUtil.isNotEmpty(xykList)) {
                nhxqPersonVO.setXykList(xykList);
            }

            //背靠背评议
            QueryWrapper<Nhbkbpy> nhbkbpyQueryWrapper = new QueryWrapper<Nhbkbpy>();
            nhbkbpyQueryWrapper.eq("zjhm", zjhm);
            nhbkbpyQueryWrapper.orderByDesc("lrsj");
            List<Nhbkbpy> nhbkbpies = iNhbkbpyService.list(nhbkbpyQueryWrapper);
            List<Nhbkbpy> nhbkbpiess = new ArrayList<>();
            if (CollUtil.isNotEmpty(nhbkbpies)) {
                for (int i = 0; i < nhbkbpies.size(); i++) {
                    Nhbkbpy nhbkbpy = nhbkbpies.get(i);
                    if (nhbkbpy.getPyls() != null) {
                        Integer pyls = nhbkbpy.getPyls();
                        String s = Convert.numberToChinese(pyls, false);
                        nhbkbpy.setPylsVal("第" + s + "轮");
                    } else {
                        String s = Convert.numberToChinese(i + 1, false);
                        nhbkbpy.setPylsVal("第" + s + "轮");
                    }
                    if (StringUtils.isNotBlank(nhbkbpy.getPylx())) {
                        nhbkbpy.setPylxVal(sysDictService.queryDictTextByKey("bkbpy_pylx", nhbkbpy.getPylx()));
                    }

                    if (StringUtils.isNotBlank(nhbkbpy.getSfljqk())) {
                        nhbkbpy.setSfljqk(sysDictService.queryDictTextByKey("sfbz", nhbkbpy.getSfljqk()));
                    }
                    nhbkbpiess.add(nhbkbpy);
                }
                nhxqPersonVO.setNhbkbpies(nhbkbpiess);
            }

            //评议附件
            QueryWrapper<Pyfjxx> pyfjxxQueryWrapper = new QueryWrapper<>();
            pyfjxxQueryWrapper.eq("hhbm", hhbm);
            pyfjxxQueryWrapper.eq("zjhm", zjhm);
            pyfjxxQueryWrapper.orderByDesc("pylx");
            List<Pyfjxx> pyfjxxes = pyfjxxService.list(pyfjxxQueryWrapper);
            List<Pyfjxx> pyfjxxess = new ArrayList<>();
            if (CollUtil.isNotEmpty(pyfjxxes)) {
                for (int i = 0; i < pyfjxxes.size(); i++) {
                    Pyfjxx pyfjxx = pyfjxxes.get(i);
                    if (StringUtils.isNotBlank(pyfjxx.getPylx())) {
                        pyfjxx.setPylx(sysDictService.queryDictTextByKey("bkbpy_pylx", pyfjxx.getPylx()));
                    }
                    pyfjxxess.add(pyfjxx);
                }
                nhxqPersonVO.setPyfjxxes(pyfjxxess);
            }

        }
        return Result.ok(nhxqPersonVO);
    }


    @RequestMapping("/downloadFj")
    public Result<?> downloadFj(String val, String zjhm, String hhbm) {

        if ("1".equals(val)) {
            QueryWrapper<Fjgl> fjglQueryWrapper = new QueryWrapper<>();
            fjglQueryWrapper.eq("zjhm", zjhm);
            List<Fjgl> fjgls = iFjglService.list(fjglQueryWrapper);
            List<File> stringList = new ArrayList<>();
            if (CollUtil.isNotEmpty(fjgls)) {
                for (int i = 0; i < fjgls.size(); i++) {
                    Fjgl fjgl = fjgls.get(i);
                    if (StringUtils.isNotBlank(fjgl.getZllj())) {
                        boolean file = FileUtil.isFile(fjgl.getZllj());
                        if (file) {
                            stringList.add(FileUtil.file(fjgl.getZllj()));
                        }
                    }
                }
            }

            QueryWrapper<Pyfjxx> pyfjxxQueryWrapper = new QueryWrapper<>();
            pyfjxxQueryWrapper.eq("hhbm", hhbm);
            pyfjxxQueryWrapper.eq("zjhm", zjhm);
            pyfjxxQueryWrapper.orderByDesc("pylx");
            List<Pyfjxx> pyfjxxes = pyfjxxService.list(pyfjxxQueryWrapper);
            if (CollUtil.isNotEmpty(pyfjxxes)) {
                for (int i = 0; i < pyfjxxes.size(); i++) {
                    Pyfjxx pyfjxx = pyfjxxes.get(i);
                    if (StringUtils.isNotBlank(pyfjxx.getZllj())) {
                        boolean file = FileUtil.isFile(pyfjxx.getZllj());
                        if (file) {
                            stringList.add(FileUtil.file(pyfjxx.getZllj()));
                        }
                    }
                }
            }

            if (CollUtil.isNotEmpty(stringList)) {
                File[] strings = new File[stringList.size()];
                stringList.toArray(strings);
                String result = File.separator + System.currentTimeMillis() + ".zip";
                String path = uploadpath + result;
                ZipUtil.zip(FileUtil.file(path), false, strings);
                return Result.ok(result);
            } else {
                return Result.error("找不到本地文件");
            }
        } else if ("2".equals(val)) {
            QueryWrapper<KhglNhhzzllb> fjxxQueryWrapper = new QueryWrapper<>();
            fjxxQueryWrapper.eq("hhbm", hhbm);
            List<KhglNhhzzllb> khglNhhzzllbs = khglNhhzzllbService.list(fjxxQueryWrapper);
            if (CollUtil.isNotEmpty(khglNhhzzllbs)) {
                List<File> stringList = new ArrayList<>();

                for (int i = 0; i < khglNhhzzllbs.size(); i++) {
                    KhglNhhzzllb khglNhhzzllb = khglNhhzzllbs.get(i);
                    if (StringUtils.isNotBlank(khglNhhzzllb.getZllj())) {
                        boolean file = FileUtil.isFile(khglNhhzzllb.getZllj());
                        if (file) {
                            stringList.add(FileUtil.file(khglNhhzzllb.getZllj()));
                        }
                    }
                }
                if (CollUtil.isNotEmpty(stringList)) {
                    File[] strings = new File[stringList.size()];
                    stringList.toArray(strings);
                    String result = File.separator + System.currentTimeMillis() + ".zip";
                    String path = uploadpath + result;
                    ZipUtil.zip(FileUtil.file(path), false, strings);
                    return Result.ok(result);
                }

            } else {
                return Result.error("找不到本地文件");
            }
        }

        return Result.error("找不到本地文件");

    }


    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportPyxxXls")
    public ModelAndView exportXls(String wgbh, HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        SysDic sysDic = sysDicService.queryByCode("101002");
        SysDic qybmSysDic = sysDicService.queryByCode("101001");
        String qybm = qybmSysDic.getValue();
        System.out.println("区域编码----->" + qybm);
        QueryWrapper<BkbpyJcxx> queryWrapper1 = new QueryWrapper();
        //同时查询下级网格数据 分配到组的地区可以直接选择村导出所有数据
        String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + wgbh + "' or parent_id='" + wgbh + "'";
        queryWrapper1.select("hhbm,max(sfbysx) as sfbysx,max(sfsx) as sfsx,max(bysxqx) as bysxqx,max(zcdkye) as zcdkye,max(bnbldkye) as bnbldkye,max(bwbldkye) as bwbldkye,max(sfss) as sfss,max(sffx) as sffx,max(sfsd) as sfsd,max(sfwbdbh) as sfwbdbh,max(ckye) as ckye,max(ckrpye) as ckrpye,max(cknrpye) as cknrpye ")
//                .eq("wgbh",wgbh)
                .and(i -> i.inSql("wgbh", sqlSswg))
                .groupBy("hhbm");
        if (QybmEnum.XINTIAN.getQybm().equals(qybm)) {
            queryWrapper1.orderByAsc("hhbm");
        }
        List<BkbpyJcxx> Hxxlist = bkbpyJcxxService.list(queryWrapper1);
        List<BkbpyHxxVo> list = new LinkedList<>();
        List<BkbpyHxxVo> bysxList = new LinkedList<>(); //不予授信
        List<BkbpyHxxVo> sxwyxList = new LinkedList<>(); //授信未用信
        List<BkbpyHxxVo> sxyyxList = new LinkedList<>(); //授信已用信

        //查询出所有明细数据
        QueryWrapper<BkbpyJcxx> jcxxQueryWrapper = new QueryWrapper<>();
        jcxxQueryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
        List<BkbpyJcxx> jcxxList = bkbpyJcxxService.list(jcxxQueryWrapper);

        //查询出所有户信息
        QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
        nhhzxxglQueryWrapper.and(i -> i.inSql("ssyxdy", sqlSswg));
        List<KhglNhhzxxgl> nhhzxxglList = khglNhhzxxglService.list(nhhzxxglQueryWrapper);

        for (BkbpyJcxx bkbpyJcxx : Hxxlist) {
            BkbpyHxxVo bkbpyHxxVo = new BkbpyHxxVo();
            BeanUtils.copyProperties(bkbpyJcxx, bkbpyHxxVo);
//            QueryWrapper<BkbpyJcxx> queryWrapper=new QueryWrapper();
//            queryWrapper.eq("hhbm",bkbpyJcxx.getHhbm());
//            List<BkbpyJcxx> bkbpyJcxxList = bkbpyJcxxService.list(queryWrapper);
            List<BkbpyJcxx> bkbpyJcxxList = jcxxList.stream().filter(item -> item.getHhbm().equals(bkbpyJcxx.getHhbm())).collect(Collectors.toList());
//            if(bkbpyJcxxList.size()<6){
//                int y=6-bkbpyJcxxList.size();
//                for(int x=0;x<=y;x++){
//                    BkbpyJcxx  bkbpyJcxx1=new BkbpyJcxx();
//                    bkbpyJcxxList.add(bkbpyJcxx1);
//                }
//            }
            KhglNhhzxxgl khglNhhzxxgl = nhhzxxglList.stream().filter(item -> item.getHhbm().equals(bkbpyJcxx.getHhbm())).findFirst().orElse(null);
            if (khglNhhzxxgl != null) {
                khglNhhzxxgl.setSfjwxhy(StringUtils.isEmpty(khglNhhzxxgl.getSfjwxhy()) ? "" : sysDictService.queryDictTextByKey("sfbz", khglNhhzxxgl.getSfjwxhy()));
                khglNhhzxxgl.setDkyxs(StringUtils.isEmpty(khglNhhzxxgl.getDkyxs()) ? "" : sysDictService.queryDictTextByKey("sfbz", khglNhhzxxgl.getDkyxs()));
                String zfmjr = khglNhhzxxgl.getZfmjr();
                if (StringUtils.isNotEmpty(zfmjr)) {
                    List<BkbpyJcxx> zfmjrList = jcxxList.stream().filter(item -> item.getZjhm().equals(zfmjr)).collect(Collectors.toList());
                    if (!zfmjrList.isEmpty()) {
                        khglNhhzxxgl.setZfmjr(zfmjrList.get(0).getKhmc());
                    }
                }
            }
            bkbpyHxxVo.setHxx(khglNhhzxxgl);

            //证件号码过滤
            DataFilterUtil.filterZjhmValue(bkbpyJcxxList);
            bkbpyHxxVo.setBkbpyJcxxList(listToDictUtil.parseDictText(bkbpyJcxxList));
            //新田排除黑名单
            if (QybmEnum.XINTIAN.getQybm().equals(qybm)) {
                List<String> hhbmList = Hxxlist.stream().filter(e -> "3".equals(e.getKhlx())).map(BkbpyJcxx::getHhbm).distinct().collect(Collectors.toList());
                if (CollUtil.isEmpty(hhbmList) || !hhbmList.contains(bkbpyHxxVo.getHhbm())) {
                    list.add(bkbpyHxxVo);
                }
            } else {
                if ("1".equals(bkbpyJcxx.getSfbysx())) {
                    bysxList.add(bkbpyHxxVo);
                } else if ("2".equals(bkbpyJcxx.getSfsx())) {
                    sxwyxList.add(bkbpyHxxVo);
                } else if ("3".equals(bkbpyJcxx.getSfsx())) {
                    sxyyxList.add(bkbpyHxxVo);
                } else {
                    list.add(bkbpyHxxVo);
                }
            }
        }
        //双峰农商行背靠背评议表_template

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new TemplateExcelView());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", listToDictUtil.parseDictText(list));
        map.put("bysxList", listToDictUtil.parseDictText(bysxList));
        map.put("sxwyxList", listToDictUtil.parseDictText(sxwyxList));
        map.put("sxyyxList", listToDictUtil.parseDictText(sxyyxList));
        YxdyglMain byId = yxdyglMainService.getById(wgbh);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("wgbh", wgbh);
        VYxdyglMain vYxdyglMain = vYxdyglMainService.getOne(queryWrapper);
        if (byId != null) {
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(byId.getZzbz());
//            YxdyglMain byId1 = yxdyglMainService.getById(byId.getParentId());
            map.put("wgmc", vYxdyglMain.getWgmcShow());
//            map.put("pwgmc", byId1!=null?byId1.getWgmc():"");
            map.put("zzjc", hrBasOrganization != null ? hrBasOrganization.getZzjc() : "");
        } else {
            map.put("wgmc", "");
//            map.put("pwgmc","");
            map.put("zzjc", "");

        }
        String tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("农商行背靠背评议表.xls");
        if (QybmEnum.NINGYUAN.getQybm().equals(qybm)) {
            tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("宁远农商行背靠背评议表.xls");
        } else if (QybmEnum.SHUANGFENG.getQybm().equals(qybm)) {
            tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("双峰农商行背靠背评议表.xls");
        } else if (QybmEnum.XINHUA.getQybm().equals(qybm)) {
            tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("新化农商行背靠背评议表.xls");
        } else if (QybmEnum.LANSHAN.getQybm().equals(qybm) || QybmEnum.TIANYI.getQybm().equals(qybm)
                || QybmEnum.QIYANG.getQybm().equals(qybm) || QybmEnum.YONGXING.getQybm().equals(qybm)
        ) {
            tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("蓝山农商行背靠背评议表.xls");
        } else if (QybmEnum.DAOXIAN.getQybm().equals(qybm)) {
            tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("道县农商行走访采集表.xls");
        } else if (QybmEnum.XINTIAN.getQybm().equals(qybm)) {
            tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("新田农商行背靠背评议表.xls");
        }
        DateFormat dateFormat = new SimpleDateFormat(DateUtil.chineseDtFormat);
        String dateStr = DateUtil.getDateString(new Date(), dateFormat);
        map.put("date", dateStr);
        map.put("bank", sysDic.getValue());
        mv.addObject(JxlsConstants.FILE_NAME, sysDic.getValue() + "背靠背评议表");
        mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, tempFileName);
        mv.addObject(JxlsConstants.SAVE_FILE_NAME, exportpath + "/农商行背靠背评议表.xls");
        mv.addObject(JxlsConstants.MAP_DATA, map);

        return mv;
    }


    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportYpyxxXls")
    public ModelAndView exportYpyxxXls(String wgbh, HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        SysDic sysDic = sysDicService.queryByCode("101002");
        SysDic qybmSysDic = sysDicService.queryByCode("101001");
        String qybm = qybmSysDic.getValue();
        QueryWrapper<BkbpyJcxx> queryWrapper1 = new QueryWrapper();
        //同时查询下级网格数据 分配到组的地区可以直接选择村导出所有数据
        String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + wgbh + "' or parent_id='" + wgbh + "'";
        queryWrapper1.select("hhbm,max(sfbysx) as sfbysx,max(sfsx) as sfsx,max(bysxqx) as bysxqx,max(zcdkye) as zcdkye,max(bnbldkye) as bnbldkye,max(bwbldkye) as bwbldkye,max(sfss) as sfss,max(sffx) as sffx,max(sfsd) as sfsd,max(sfwbdbh) as sfwbdbh,max(ckye) as ckye,max(ckrpye) as ckrpye,max(cknrpye) as cknrpye ")
//                .eq("wgbh",wgbh)
                .and(i -> i.inSql("wgbh", sqlSswg))
                .groupBy("hhbm");
        List<BkbpyJcxx> Hxxlist = bkbpyJcxxService.list(queryWrapper1);
        List<BkbpyHxxVo> list = new LinkedList<>();
        List<BkbpyHxxVo> bysxList = new LinkedList<>(); //不予授信
        List<BkbpyHxxVo> sxwyxList = new LinkedList<>(); //授信未用信
        List<BkbpyHxxVo> sxyyxList = new LinkedList<>(); //授信已用信
        for (BkbpyJcxx bkbpyJcxx : Hxxlist) {
            BkbpyHxxVo bkbpyHxxVo = new BkbpyHxxVo();
            BeanUtils.copyProperties(bkbpyJcxx, bkbpyHxxVo);
            QueryWrapper<BkbpyJcxx> queryWrapper = new QueryWrapper();
            queryWrapper.eq("hhbm", bkbpyJcxx.getHhbm());
            List<BkbpyJcxx> bkbpyJcxxList = bkbpyJcxxService.list(queryWrapper);
            QueryWrapper<BkbpyYpyxx> ypyxxQueryWrapper = new QueryWrapper<>();
            ypyxxQueryWrapper.eq("hhbm", bkbpyJcxx.getHhbm());
            ypyxxQueryWrapper.orderByAsc("pyls");
            List<BkbpyYpyxx> bkbpyYpyxxList = bkbpyYpyxxService.list(ypyxxQueryWrapper);

//            if(bkbpyJcxxList.size()<6){
//                int y=6-bkbpyJcxxList.size();
//                for(int x=0;x<=y;x++){
//                    BkbpyJcxx  bkbpyJcxx1=new BkbpyJcxx();
//                    bkbpyJcxxList.add(bkbpyJcxx1);
//                }
//            }
            //证件号码过滤
            DataFilterUtil.filterZjhmValue(bkbpyJcxxList);
            DataFilterUtil.filterZjhmValue(bkbpyYpyxxList);
            bkbpyHxxVo.setBkbpyJcxxList(listToDictUtil.parseDictText(bkbpyJcxxList));
            bkbpyHxxVo.setBkbpyYpyxxList(listToDictUtil.parseDictText(bkbpyYpyxxList));
            if ("1".equals(bkbpyJcxx.getSfbysx())) {
                bysxList.add(bkbpyHxxVo);
            } else if ("2".equals(bkbpyJcxx.getSfsx())) {
                sxwyxList.add(bkbpyHxxVo);
            } else if ("3".equals(bkbpyJcxx.getSfsx())) {
                sxyyxList.add(bkbpyHxxVo);
            } else {
                list.add(bkbpyHxxVo);
            }
        }
        //双峰农商行背靠背评议表_template

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new TemplateExcelView());
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("list", listToDictUtil.parseDictText(list));
        map.put("bysxList", listToDictUtil.parseDictText(bysxList));
        map.put("sxwyxList", listToDictUtil.parseDictText(sxwyxList));
        map.put("sxyyxList", listToDictUtil.parseDictText(sxyyxList));
        YxdyglMain byId = yxdyglMainService.getById(wgbh);
        if (byId != null) {
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(byId.getZzbz());
            YxdyglMain byId1 = yxdyglMainService.getById(byId.getParentId());
            map.put("wgmc", byId.getWgmc());
            map.put("pwgmc", byId1 != null ? byId1.getWgmc() : "");
            map.put("zzjc", hrBasOrganization != null ? hrBasOrganization.getZzjc() : "");
        } else {
            map.put("wgmc", "");
            map.put("pwgmc", "");
            map.put("zzjc", "");

        }
        String tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("基础信息收集操作表.xls");
        DateFormat dateFormat = new SimpleDateFormat(DateUtil.chineseDtFormat);
        String dateStr = DateUtil.getDateString(new Date(), dateFormat);
        map.put("date", dateStr);
        map.put("bank", sysDic.getValue());
        mv.addObject(JxlsConstants.FILE_NAME, sysDic.getValue() + "基础信息收集操作表");
        mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, tempFileName);
        mv.addObject(JxlsConstants.SAVE_FILE_NAME, exportpath + "/基础信息收集操作表.xls");
        mv.addObject(JxlsConstants.MAP_DATA, map);

        return mv;
    }

    @RequestMapping("/impHjsj")
    public Result<?> impHjsj(@RequestBody JSONObject jsonObject) {

        String filePaths = jsonObject.getString("filePaths");
        if (StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            System.out.println(baseFilePath);
            File file = new File(baseFilePath);

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                Workbook excelWorkbook = ExcelUtils.getExcelWorkbook(fileInputStream, file.getName());
                Sheet sheetAt = excelWorkbook.getSheetAt(0);
                int lastRowNum = sheetAt.getLastRowNum();
                if (lastRowNum > 4) {
                    Row row = null;
                    row = sheetAt.getRow(1);
                    Cell cell = row.getCell(0);
                    String wgbh = null;
                    if (cell != null) {
                        String trim = cell.getStringCellValue().trim();
                        LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                        lambdaQueryWrapper.eq(YxdyglMain::getWgmc, trim);
                        lambdaQueryWrapper.eq(YxdyglMain::getWgxz, "2");
                        List<YxdyglMain> list = yxdyglMainService.list(lambdaQueryWrapper);
                        if (CollUtil.isNotEmpty(list)) {
                            if (list.size() > 1) {
                                return Result.error("所属村重复");
                            } else {
                                wgbh = list.get(0).getWgbh();
                            }
                        } else {
                            return Result.error("所属村查询为空");
                        }

                    } else {
                        return Result.error("所属村不能为空");
                    }

                    for (int i = 4; i < lastRowNum - 1; i++) {
                        row = sheetAt.getRow(i);
                        String hhbm = null;
                        String zjhm = null;
                        String khmc = null;
                        String yhzgx = null;
                        String hjdz = null;
                        if (row.getCell(0) != null) {
                            hhbm = row.getCell(0).getStringCellValue().trim();
                        } else {
                            continue;
                        }

                        if (row.getCell(1) != null) {
                            khmc = row.getCell(1).getStringCellValue().trim();
                        } else {
                            continue;
                        }
                        if (row.getCell(2) != null) {
                            zjhm = row.getCell(2).getStringCellValue().trim();
                            if (StringUtils.isNotBlank(zjhm)) {
                                if (!IdcardUtil.isValidCard(zjhm)) {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                        if (row.getCell(3) != null) {
                            yhzgx = row.getCell(3).getStringCellValue().trim();
                            String s = DictTextToValusUtil.yhzgx2(yhzgx);
                            yhzgx = s;
                        } else {
                            continue;
                        }
                        if (row.getCell(4) != null) {
                            hjdz = row.getCell(4).getStringCellValue().trim();
                        } else {
                            continue;
                        }


                        //保存数据
                        Khjbzl khjbzl = new Khjbzl();
                        khjbzl.setWgbh(wgbh);
                        khjbzl.setKhmc(khmc);
                        khjbzl.setZjhm(zjhm);
                        khjbzl.setCreateBy("hjdr");
                        khjbzl.setCreateTime(new Date());
                        khjbzlService.save(khjbzl);

                        Nhxq nhxq = new Nhxq();
                        nhxq.setWgbh(wgbh);
                        nhxq.setKhmc(khmc);
                        nhxq.setHhbm(hhbm);
                        nhxq.setYhzgx(yhzgx);
                        nhxq.setZjhm(zjhm);
                        nhxq.setHjdz(hjdz);
                        nhxq.setZz(hjdz);
                        service.save(nhxq);

                    }
                }


                return Result.ok("文件导入完成！成功导入数据行数:" + lastRowNum, obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
            }
        }
        return Result.ok("文件导入失败！");
    }

    @GetMapping(value = "/queryJtcyxx")
    public Result<?> queryHhbm(@Param("hhbm") String hhbm) {
        try {
            QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
            khhmcQueryWrapper.eq("hhbm", hhbm);
            khhmcQueryWrapper.orderByAsc("yhzgx");
            List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
            if (list != null && list.size() > 0) {
                List list1 = listToDictUtil.parseDictText(list);
                return Result.ok(list1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
        return Result.ok("没有数据");
    }

    @GetMapping(value = "/list020")
    public Result<?> queryPageListly(Nhxq nhxq,
                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     @RequestParam(name = "sfcj", required = false) String sfcj,
                                     @RequestParam(name = "sfscfj", required = false) String sfscfj,
                                     HttpServletRequest req) {
        String wgbh = null;
        if (org.apache.commons.lang3.StringUtils.isNotBlank(nhxq.getWgbh()))
            wgbh = nhxq.getWgbh();
        nhxq.setWgbh(null);

        QueryWrapper<Nhxq> queryWrapper = QueryGenerator.initQueryWrapper(nhxq, req.getParameterMap());
        Page<Nhxq> page = new Page<Nhxq>(pageNo, pageSize);

        if (org.apache.commons.lang3.StringUtils.isNotBlank(nhxq.getWgbh())) {
            queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "' and  " +
                    "menu_id in (" +
                    "select wgbh from yxdygl_main start with wgbh='" + nhxq.getWgbh() + "' connect by prior wgbh=parent_id )");
        } else {
            queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "'");
        }

        //queryWrapper.last(" order by update_time desc nulls last ");
        IPage<Nhxq> pageList = nhxqService.page(page, queryWrapper);

        return Result.ok(pageList);
    }

    @RequestMapping("/getByHhbm")
    public Result<?> getByHhbm(String hhbm) {
        if (StringUtils.isBlank(hhbm))
            return Result.error("户号编码不能为空！");
        LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Nhxq::getHhbm, hhbm);
        List<Nhxq> list = service.list(lambdaQueryWrapper);
        return Result.ok(list);
    }

    @RequestMapping("/editNhxq")
    public Result<?> editNhxq(Nhxq nhxq) {
        System.out.println("==========================1");
        System.out.println(nhxq);
        System.out.println("==========================1");

        if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {

            String fwlj = "/sign/nhxq/";
            if (!FileUtil.isDirectory(uploadpath + fwlj)) {
                FileUtil.mkdir(uploadpath + fwlj);
            }

            if (StringUtils.isNotBlank(nhxq.getSign1())) {
                if (nhxq.getSign1().startsWith(CommonConstant.BASE64_PREFIX)) {
                    String fileName = IdUtil.fastSimpleUUID() + ".png";
                    String wjlj = uploadpath + fwlj + fileName;
                    Base64Util.toImage(nhxq.getSign1(), wjlj);
                    nhxq.setSign1(fwlj + fileName);
                } else {
                    nhxq.setSign1(null);
                }
            }

            if (StringUtils.isNotBlank(nhxq.getSign2())) {
                if (nhxq.getSign2().startsWith(CommonConstant.BASE64_PREFIX)) {
                    String fileName = IdUtil.fastSimpleUUID() + ".png";
                    String wjlj = uploadpath + fwlj + fileName;
                    Base64Util.toImage(nhxq.getSign2(), wjlj);
                    nhxq.setSign2(fwlj + fileName);
                } else {
                    nhxq.setSign2(null);
                }
            }

            if (StringUtils.isNotBlank(nhxq.getSign3())) {
                if (nhxq.getSign3().startsWith(CommonConstant.BASE64_PREFIX)) {
                    String fileName = IdUtil.fastSimpleUUID() + ".png";
                    String wjlj = uploadpath + fwlj + fileName;
                    Base64Util.toImage(nhxq.getSign3(), wjlj);
                    nhxq.setSign3(fwlj + fileName);
                } else {
                    nhxq.setSign3(null);
                }
            }

        }
        System.out.println("==========================2");
        System.out.println(nhxq.toString());
        System.out.println("==========================2");

        service.updateById(nhxq);

        return Result.ok();
    }

    /**
     * 通过excel导入走访信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcelZfxx", method = RequestMethod.POST)
    public Result<?> importExcelZfxx(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jsonObject) throws Exception {
        String filePaths = jsonObject.getString("filePaths");
        File file = new File(uploadpath + File.separator + filePaths);

        boolean isExcel2003 = true;
        if (file.getName().matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(new FileInputStream(file));
        } else {
            wb = new XSSFWorkbook(new FileInputStream(file));
        }

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("导入结果：");
        int numberOfNames = wb.getNumberOfSheets();
        log.info("一共有{}个sheet", numberOfNames);
        if (numberOfNames > 0) {
            //导入第一个sheet页
            String sheetName = wb.getSheetName(0);
            Sheet sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum() + 1;
            log.info("===开始导入走访信息，本次一共有{}行数据===", lastRowNum);
            int kh = 0;
            int count = 0;
            String hhbmUsing = "";
            String zfrq = "";
            Date zfrqDate = null;
            //数据从第四行开始
            for (int i = 1; i < lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (i == 1) {
                    zfrq = row.getCell(10).getStringCellValue();
                    if (StringUtils.isNotEmpty(zfrq)) {
                        zfrqDate = DateUtil.parseDateFormat(zfrq, DateUtil.chineseDtFormat);
                    }
                    continue;
                }
                if (i == 2 || i == 3) {
                    continue;
                }
                if (kh > 2) {
                    stringBuffer.append(" 走访信息表共有" + lastRowNum + "行数据，处理数据" + count + "行!  在" + i + "行连续3条空数据，提前结束了本轮导入！");
                    break;
                }
                String hhbm = getCellValue(row, 1);
                String khmc = getCellValue(row, 3);
                String id = getCellValue(row, 5);
                String zjhm = getCellValue(row, 6);
                String sjhm = getCellValue(row, 7);
                String zz = getCellValue(row, 8);
                if (StringUtils.isBlank(zjhm)) {
                    kh++;
                    continue;
                } else {
                    kh = 0;
                }
                Nhxq nhxq = null;
                //如果ID不为空，则根据ID获取农户信息 因为证件号码做了脱敏处理
                if (StringUtils.isNotEmpty(id)) {
                    nhxq = nhxqService.getById(id);
                    if (nhxq == null) {
                        log.info("未找到对应的农户信息，ID[{}]，证件号码[{}]", id, zjhm);
                        continue;
                    }
                    zjhm = nhxq.getZjhm();
                    hhbm = nhxq.getHhbm();
                } else {
                    QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
                    nhxqQueryWrapper.eq("zjhm", zjhm);
                    nhxq = nhxqService.getOne(nhxqQueryWrapper);
                    if (nhxq == null) {
                        log.info("未找到对应的农户信息，证件号码[{}]", zjhm);
                        continue;
                    }
                    hhbm = nhxq.getHhbm();
                }

                if ((StringUtils.isNotEmpty(sjhm) && !sjhm.equals(nhxq.getSjhm())) ||
                        (StringUtils.isNotEmpty(zz) && !zz.equals(nhxq.getZz()))) {
                    Nhxq nhxqUpdate = new Nhxq();
                    nhxqUpdate.setSjhm(sjhm);
                    nhxqUpdate.setZz(zz);
                    //更新手机号码
                    UpdateWrapper<Nhxq> nhxqUpdateWrapper = new UpdateWrapper<>();
                    nhxqUpdateWrapper.eq("id", nhxq.getId());
                    nhxqService.update(nhxqUpdate, nhxqUpdateWrapper);
                }
                //同一户只处理第一次
                if (StringUtils.isEmpty(hhbmUsing) || !hhbmUsing.equals(hhbm)) {
                    String zfdz = getCellValue(row, 15);
                    String zfmjr = getCellValue(row, 16);
                    String ymbkhgx = getCellValue(row, 17);
                    String sfjwxhy = getCellValue(row, 18);
                    String dkyxs = getCellValue(row, 19);
                    String zfry = getCellValue(row, 20);
                    String bz = getCellValue(row, 21);

                    QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
                    nhhzxxglQueryWrapper.eq("hhbm", hhbm);
                    KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(nhhzxxglQueryWrapper);

                    KhglNhhzxxgl nhhzxxglUpdate = new KhglNhhzxxgl();
                    nhhzxxglUpdate.setZfdz(StringUtils.nvl(khglNhhzxxgl.getZfdz(), zfdz));
                    nhhzxxglUpdate.setZfmjr(StringUtils.nvl(khglNhhzxxgl.getZfmjr(), zfmjr));
                    nhhzxxglUpdate.setYmbkhgx(StringUtils.nvl(khglNhhzxxgl.getYmbkhgx(), ymbkhgx));
                    nhhzxxglUpdate.setSfjwxhy(StringUtils.nvl(khglNhhzxxgl.getSfjwxhy(), DictTextToValusUtil.sfbz(sfjwxhy)));
                    nhhzxxglUpdate.setDkyxs(StringUtils.nvl(khglNhhzxxgl.getDkyxs(), DictTextToValusUtil.sfbz(dkyxs)));
                    nhhzxxglUpdate.setZfry(StringUtils.nvl(khglNhhzxxgl.getZfry(), zfry));
                    nhhzxxglUpdate.setBz(StringUtils.nvl(khglNhhzxxgl.getBz(), bz));
                    if (zfrqDate != null && khglNhhzxxgl.getZfrq() == null) {
                        nhhzxxglUpdate.setZfrq(zfrqDate);
                    }
                    UpdateWrapper<KhglNhhzxxgl> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("hhbm", hhbm);
                    khglNhhzxxglService.update(nhhzxxglUpdate, updateWrapper);
                    LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    String username = sysUser.getUsername();
                    String workNo = sysUser.getWorkNo();
                    if (zfrqDate != null && StringUtils.isNotEmpty(zfry)) {
                        //走访人员
                        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<SysUser>();
                        sysUserQueryWrapper.eq("realname", zfry);
                        sysUserQueryWrapper.or().eq("username", zfry);
                        sysUserQueryWrapper.or().eq("work_no", zfry);
                        List<SysUser> sysUserList = sysUserService.list(sysUserQueryWrapper);
                        if (!sysUserList.isEmpty()) {
                            SysUser zfryUser = sysUserList.get(0);
                            username = zfryUser.getUsername();
                            workNo = zfryUser.getWorkNo();
                        }
                        saveYxglKhhfxxb(khglNhhzxxgl.getSsyxdy(), khglNhhzxxgl.getHzxm(), khglNhhzxxgl.getHzzjhm(), zfrqDate, workNo, username, khglNhhzxxgl.getSszh(), khglNhhzxxgl.getZfyy());

                        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                        String qybm = getRedisQydm();
                        if (false) {
                            QueryWrapper<KhxxglGrsxlxmxNh> queryWrapper = new QueryWrapper<>();
                            queryWrapper.eq("zjhm", khglNhhzxxgl.getHzzjhm());
                            KhxxglGrsxlxmxNh khxxglGrsxlxmxNh = khxxglGrsxlxmxNhService.getOne(queryWrapper);
                            if (khxxglGrsxlxmxNh != null && vKhglNhhzxxgl12.init3(khglNhhzxxgl.getHhbm()) > 0) {
                                vKhglNhhzxxgl12.init(khglNhhzxxgl.getHhbm(), khglNhhzxxgl.getHzzjhm(), workNo, username, DateUtil.format(zfrqDate, "yyyyMMdd"));
                            } else {
                                vKhglNhhzxxgl12.init1(khglNhhzxxgl.getHhbm());
                                //同步oracle到impala
                                EtlUtil.SHcallEtlRc(10, true, false, false, "tjfx_cssz", "idap");
                                //同步之前删除impala数据中khxxgl_grsxlxmx_nh表数据
                                khxxglGrsxlxmxNh1Service.delNhgrsxlxmx();
                                EtlUtil.SHcallEtlRc(10, true, false, true, "khxxgl_grsxlxmx_nh", "idap", khglNhhzxxgl.getHhbm());
                                khxxglGrsxlxmxNhService.delNhgrsxlxmxByHhbm(khglNhhzxxgl.getHhbm());
                                //调用python脚本
                                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxSsxlxtj.py --hhbm " + khglNhhzxxgl.getHhbm() + "'");
                                //同步impala到oracle
                                sshUtil.execShell("sh /home/exportdata/P_TJFX_SXLXTJ_EXPORT.sh");
                                sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_SXLXTJ_IMPORT.sh");
                                vKhglNhhzxxgl12.init2(khglNhhzxxgl.getHhbm());
                                vKhglNhhzxxgl12.init(khglNhhzxxgl.getHhbm(), khglNhhzxxgl.getHzzjhm(), workNo, username, DateUtil.format(zfrqDate, "yyyyMMdd"));
                            }
                        } else {
                            vKhglNhhzxxgl12.init(khglNhhzxxgl.getHhbm(), khglNhhzxxgl.getHzzjhm(), workNo, username, DateUtil.format(zfrqDate, "yyyyMMdd"));
                        }
                    }

                    count++;
                }
            }
            stringBuffer.append("  走访信息表共有" + lastRowNum + "行数据，处理数据" + count + "行!  ");
        } else {
            stringBuffer.append("未找到需要导入的sheet页");
        }
        log.info("===={}====", stringBuffer.toString());
        return Result.ok(stringBuffer.toString());
    }

    private String getCellValue(Row row, int cellIndex) {
        if (row != null) {
            Cell cell = row.getCell(cellIndex);
            if (cell != null) {
                int cellType = cell.getCellType();
                if (cellType == Cell.CELL_TYPE_NUMERIC) {
                    Double cellValue = cell.getNumericCellValue();
                    if (cellValue != null) {
                        DecimalFormat df = new DecimalFormat("0");
                        return df.format(cellValue);
                    }
                }
                return cell.getStringCellValue();
            }
        }
        return null;
    }

    /**
     * 保存回访信息
     *
     * @param yxdy
     * @param khmc
     * @param zjhm
     */
    protected void saveYxglKhhfxxb(String yxdy, String khmc, String zjhm, Date hfrq, String workNo, String userName, String khsszh, String zfyy) {
        YxglKhhfxxb khhfxxb = new YxglKhhfxxb();
        Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(workNo);
        if (vhrbasstaffpost != null) {
            khhfxxb.setZzbz(vhrbasstaffpost.getZzbz());
            khhfxxb.setYggh(vhrbasstaffpost.getYggh());
            khhfxxb.setKhjlbh(vhrbasstaffpost.getKhjlbz());
            if (khsszh.equals(vhrbasstaffpost.getZzbz())) {
                khhfxxb.setGsqk("1");
            } else {
                khhfxxb.setGsqk("2");
            }
        } else {
            khhfxxb.setGsqk("2");
        }
        khhfxxb.setKhsszh(khsszh);
        khhfxxb.setZfyy(zfyy);
        khhfxxb.setYxdy(yxdy);
        khhfxxb.setKhmc(khmc);
        khhfxxb.setZjhm(zjhm);
        khhfxxb.setHfrq(hfrq);
        khhfxxb.setSjly("2");
        khhfxxb.setLrr(userName);
        yxglKhhfxxbService.save(khhfxxb);
    }

    @PostMapping(value = "/editJzyxzf")
    public Result<?> editJzyxzf(@RequestBody Nhxq nhxq) {
        System.out.println(nhxq);
        if (StringUtils.isBlank(nhxq.getJzyxwczfr())) {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            nhxq.setJzyxwczfr(sysUser.getWorkNo());
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(nhxq.getZjhm()) && nhxq.getZjhm().contains("*"))
            nhxq.setZjhm(null);
        service.updateById(nhxq);
        //精准营销走访个人信息保存后判断是否完成精准营销
        nhJzyxService.jzyxInit(nhxq.getId());
        return Result.ok();

    }

    @GetMapping(value = "/list2")
    public Result<?> queryPageList2(NhJzyx nhJzyx,
                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                    HttpServletRequest req) {
        String wgbh = nhJzyx.getWgbh();
        String zz = nhJzyx.getZz();
        nhJzyx.setWgbh(null);
        nhJzyx.setZz(null);
        List<String> hhbmList = null;
        if (StringUtils.isNotBlank(nhJzyx.getZjhm()) || StringUtils.isNotBlank(nhJzyx.getKhmc())) {
            QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(nhJzyx.getZjhm())) {
                nhxqQueryWrapper.eq("zjhm", nhJzyx.getZjhm());
            }
            if (!StringUtils.isEmpty(nhJzyx.getKhmc())) {
                nhxqQueryWrapper.like("khmc", nhJzyx.getKhmc().replace("*", ""));
            }
            int dateNum = (int) nhxqService.count(nhxqQueryWrapper);
            if (dateNum > 1000) {
                return Result.error("符合条件的数据过多，请完善查询条件");
            }
            List<Nhxq> nhxqList = nhxqService.list(nhxqQueryWrapper);
            if (CollUtil.isNotEmpty(nhxqList)) {
                hhbmList = nhxqList.stream().map(Nhxq::getHhbm).distinct().collect(Collectors.toList());
            }
        }
        if (CollUtil.isNotEmpty(hhbmList)) {
            nhJzyx.setZjhm(null);
            nhJzyx.setKhmc(null);
        }
        QueryWrapper<NhJzyx> queryWrapper = QueryGenerator.initQueryWrapper(nhJzyx, req.getParameterMap());
        // 查询网格时，同时查询下级网格的数据
        if (StringUtils.isNotEmpty(wgbh)) {
            String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + wgbh + "' or parent_id='" + wgbh + "'";
            queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
        }
        Page<NhJzyx> page = new Page<NhJzyx>(pageNo, pageSize);
        if (!getUsername().equals("admin")) {
            //in  用 list有个数限制问题， 此处改为inSql
            String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + getWorkNo() + "'";
            queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
        }
        //地址模糊查询
        if (StringUtils.isNotBlank(zz)) {
            queryWrapper.like("zz", zz);
        }

        if (CollUtil.isNotEmpty(hhbmList)) {
            queryWrapper.in("hhbm", hhbmList);
        }

        queryWrapper.orderByDesc("wgbh");
        queryWrapper.orderByAsc("hhbm");
        queryWrapper.orderByAsc("zjhm");

        //通过视图处理
//        queryWrapper.inSql("zjhm"," select distinct zjhm from CAMS_ZCSX_NHBKBPY t where pylc like '%3%' and zhsded > 0 ");
        IPage<NhJzyx> pageList = nhJzyxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    /**
     * 导出excel
     *
     * @param request
     * @param nhJzyx
     */
    @RequestMapping(value = "/exportXls2")
    public ModelAndView exportXls(HttpServletRequest request, NhJzyx nhJzyx) {
        // Step.1 组装查询条件
        String wgbh = nhJzyx.getWgbh();
        String zz = nhJzyx.getZz();
        nhJzyx.setWgbh(null);
        nhJzyx.setZz(null);
        List<String> hhbmList = null;
        if (StringUtils.isNotBlank(nhJzyx.getZjhm()) || StringUtils.isNotBlank(nhJzyx.getKhmc())) {
            QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(nhJzyx.getZjhm())) {
                nhxqQueryWrapper.eq("zjhm", nhJzyx.getZjhm());
            }
            if (StringUtils.isNotBlank(nhJzyx.getKhmc())) {
                nhxqQueryWrapper.like("khmc", nhJzyx.getKhmc().replace("*", ""));
            }
            List<Nhxq> nhxqList = nhxqService.list(nhxqQueryWrapper);
            if (CollUtil.isNotEmpty(nhxqList)) {
                hhbmList = nhxqList.stream().map(Nhxq::getHhbm).distinct().collect(Collectors.toList());
            }
        }
        nhJzyx.setZjhm(null);
        nhJzyx.setKhmc(null);
        QueryWrapper<NhJzyx> queryWrapper = QueryGenerator.initQueryWrapper(nhJzyx, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String selections = request.getParameter("selections");
        String rowKey = request.getParameter("rowKey");

        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            if (oConvertUtils.isNotEmpty(rowKey)) {
                queryWrapper.in(rowKey, selectionList);
            } else {
                queryWrapper.in("ID", selectionList);
            }
        }
        if (StringUtils.isNotEmpty(wgbh)) {
            String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + wgbh + "' or parent_id='" + wgbh + "'";
            queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
        }
        if (!getUsername().equals("admin")) {
            //in  用 list有个数限制问题， 此处改为inSql
            String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + getWorkNo() + "'";
            queryWrapper.and(i -> i.inSql("wgbh", sqlSswg));
        }
        //地址模糊查询
        if (StringUtils.isNotBlank(zz)) {
            queryWrapper.like("zz", zz);
        }

        if (CollUtil.isNotEmpty(hhbmList)) {
            queryWrapper.in("hhbm", hhbmList);
        }

        queryWrapper.orderByDesc("wgbh");

        // Step.2 获取导出数据
        List<NhJzyx> exportList = nhJzyxService.list(queryWrapper);
        List<NhJzyxVo> nhJzyxVoList = new ArrayList<>();
        exportList.forEach(e -> {
            NhJzyxVo nhJzyxVo = new NhJzyxVo();
            BeanUtils.copyProperties(e, nhJzyxVo);
            List<YxdyglMain> yxdyglMainList = yxdyglMainService.getWgbhParentWgbh(e.getWgbh());
            YxdyglMain yxdyglMain = new YxdyglMain();
            if (CollUtil.isNotEmpty(yxdyglMainList)) {
                yxdyglMain = yxdyglMainList.get(0);
            }
            nhJzyxVo.setXzc(yxdyglMain.getWgbh());
            nhJzyxVo.setCz(e.getWgbh());
            nhJzyxVoList.add(nhJzyxVo);
        });

        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "精准营销走访"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, NhJzyxVo.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("精准营销走访" + "报表", "导出人:" + sysUser.getRealname(), "精准营销走访"));
        mv.addObject(NormalExcelConstants.DATA_LIST, nhJzyxVoList);
        return mv;
    }

    /**
     * 导出excel
     * 客户经理营销管理 / 客户信息管理 / 客户分类管理 / 农户信息管理 / 导出采集信息
     *
     * @param request
     * @param vKhglNhjbxx
     */
    @RequestMapping(value = "/exportCjxxXls")
    public ModelAndView exportCjxxXls(HttpServletRequest request, VKhglNhjbxx vKhglNhjbxx) {
        // Step.1 组装查询条件
        QueryWrapper<VKhglNhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglNhjbxx, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Map<String, String[]> map = new HashMap<>();
        //Step.2 获取导出数据
        List<KhglKhcjxxPage> pageList = new ArrayList<KhglKhcjxxPage>();
        List<VKhglNhjbxx> vKhglNhjbxxList = ivKhglNhjbxxService.list(queryWrapper);
        for (VKhglNhjbxx khglNhjbxx : vKhglNhjbxxList) {
            Pydjcs pydjcs = pydjcsService.getPddjAndJysxde(khglNhjbxx.getPypjdf());
            KhglKhcjxxPage vo = new KhglKhcjxxPage();
            BeanUtils.copyProperties(khglNhjbxx, vo);
            if (pydjcs != null) {
                vo.setCzlwsxed(new BigDecimal(pydjcs.getJysxed()));
            }
            pageList.add(vo);
        }
        //Step.3 调用AutoPoi导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "农户采集信息");
        mv.addObject(NormalExcelConstants.CLASS, KhglKhcjxxPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户采集信息", "导出人:" + sysUser.getRealname(), "农户采集信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 导出excel
     * 客户经理营销管理 / 客户信息管理 / 客户分类管理 / 农户信息管理 / 导出信用等级评定表
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/xydjpdbExportXlsUrl")
    public ModelAndView xydjpdbExportXlsUrl(VKhglNhjbxx vKhglNhjbxx, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // Step.1 组装查询条件
        QueryWrapper<VKhglNhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglNhjbxx, request.getParameterMap());
        queryWrapper.eq("sfhz", "1");
        queryWrapper.orderByAsc("xzz");
        //AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new TemplateExcelView());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xzc", StringUtils.isEmpty(vKhglNhjbxx.getXzc()) ? "" : sysDictService.queryTableDictTextByKey("yxdygl_main", "wgmc", "wgbh", vKhglNhjbxx.getXzc()));
        List<VKhglNhjbxx> nhjbxxList = ivKhglNhjbxxService.list(queryWrapper);
        for (VKhglNhjbxx nhjbxx : nhjbxxList) {
            nhjbxx.setXzz(StringUtils.isEmpty(nhjbxx.getXzz()) ? "" : sysDictService.queryTableDictTextByKey("yxdygl_main", "wgmc", "wgbh", nhjbxx.getXzz()));
        }
        map.put("list", nhjbxxList);
        String port = environment.getProperty("common.path.export");
        //导出文件名称
        mv.addObject(JxlsConstants.FILE_NAME, "农户信用等级评定表");
        mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, org.cmms.common.util.FileUtil.getTempFilePath("农户信用等级评定表.xls"));
        mv.addObject(JxlsConstants.SAVE_FILE_NAME, port + "/农户信用等级评定表.xls");
        mv.addObject(JxlsConstants.MAP_DATA, map);
        return mv;
    }

    /**
     * 精准营销-查询个人走访信息和商户信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryShxxAndZfxx")
    public Result<?> queryShxxAndZfxx(@RequestParam(name = "id", required = true) String id) {
        Nhxq nhxq = nhxqService.getById(id);
        Shxq shxq = null;
        Nhbkbpy nhbkbpy = null;
        if (StringUtils.isNotBlank(nhxq.getZjhm())) {
            //获取商户信息
            QueryWrapper<Shxq> queryWrapper1 = new QueryWrapper<Shxq>();
            queryWrapper1.eq("frzjhm", nhxq.getZjhm());
            shxq = shxqService.getOne(queryWrapper1);

            //获取走访信息中额度最高的一条走访数据
            LambdaQueryWrapper<Nhbkbpy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Nhbkbpy::getZjhm, nhxq.getZjhm());
            lambdaQueryWrapper.like(Nhbkbpy::getPylc, "3");
            lambdaQueryWrapper.orderByDesc(Nhbkbpy::getZhsded);
            List<Nhbkbpy> list = nhbkbpyService.list(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(list)) {
                nhbkbpy = list.get(0);
            }
        }
        Map resultMap = new HashMap<>();
        resultMap.put("shxx", shxq);
        resultMap.put("zfxx", nhbkbpy);
        return Result.ok(resultMap);
    }

    /**
     * 精准营销-根据走访id获取农户信息
     */
    @GetMapping("/getNhxxByZfid")
    public Result<?> getNhxxByZfid(@RequestParam(name = "id") String id) {
        Nhbkbpy nhbkbpy = nhbkbpyService.getById(id);
        Nhxq nhxq = null;
        if (StringUtils.isNotBlank(nhbkbpy.getZjhm())) {
            nhxq = nhxqService.getByZjhm(nhbkbpy.getZjhm());
        }
        return Result.ok(nhxq);
    }


    /**
     * 祁阳-农户信息管理-导出福祥e贷
     */
    @RequestMapping(value = "/exportExportFxedXls")
    public void exportExportFxedXls(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Nhxq nhxq) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            if (nhxq != null && StringUtils.isNotBlank(nhxq.getHhbm())) {
                QueryWrapper<KhglNhhzxxgl> khglNhhzxxglQueryWrapper = new QueryWrapper<>();
                khglNhhzxxglQueryWrapper.eq("hhbm", nhxq.getHhbm());
                KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(khglNhhzxxglQueryWrapper, false);
                if (khglNhhzxxgl != null && StringUtils.isNotBlank(khglNhhzxxgl.getSxdxzjh())) {
                    Map<String, Object> data = new HashMap<>();
                    //所属支行
                    data.put("sszh", StringUtils.isNotBlank(nhxq.getJgdm()) ? sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", nhxq.getJgdm()) : null);
                    //导出日期年月日
                    data.put("date", DateUtil.getChineseDateString(new Date()));
                    //家庭信息
                    QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
                    nhxqQueryWrapper.eq("hhbm", nhxq.getHhbm());
                    nhxqQueryWrapper.between("nl", 0, 60);
                    nhxqQueryWrapper.orderByAsc("yhzgx");
                    List<Nhxq> nhxqList = nhxqService.list(nhxqQueryWrapper);
                    nhxqList.forEach(e -> {
                        e.setXb(StringUtils.isNotBlank(e.getXb()) ? sysDictService.queryDictTextByKey("sex", e.getXb()) : null);
                        e.setYhzgx(StringUtils.isNotBlank(e.getYhzgx()) ? sysDictService.queryDictTextByKey("yhzgx", e.getYhzgx()) : null);
                    });
                    data.put("jtxx", nhxqList);
                    //授信对象
                    data.put("sxdx", khglNhhzxxgl.getSxdx());
                    //职务
                    //职称学历
                    //从业年限
                    QueryWrapper<TjfxHnkd> tjfxHnkdQueryWrapper = new QueryWrapper<>();
                    tjfxHnkdQueryWrapper.eq("khsfzhm", khglNhhzxxgl.getSxdxzjh());
                    tjfxHnkdQueryWrapper.orderByDesc("sjrq");
                    List<TjfxHnkd> tjfxHnkdList = tjfxHnkdService.list(tjfxHnkdQueryWrapper);
                    TjfxHnkd tjfxHnkd = CollUtil.isNotEmpty(tjfxHnkdList) ? tjfxHnkdList.get(0) : null;
                    data.put("zw", tjfxHnkd == null ? "" : tjfxHnkd.getXdrzw());
                    data.put("zc", tjfxHnkd == null ? "" : tjfxHnkd.getZc());
                    data.put("xl", tjfxHnkd == null ? "" : tjfxHnkd.getZgxl());
                    data.put("cynx", tjfxHnkd == null ? "" : tjfxHnkd.getCxnx());
                    //获取评级授信信息
                    QueryWrapper<CamsZcsxNhpjsxxxPad> pjxxQueryWrapper = new QueryWrapper<>();
                    pjxxQueryWrapper.eq("hhbm", nhxq.getHhbm());
                    CamsZcsxNhpjsxxxPad pjxx = iCamsZcsxNhpjsxxxPadService.getOne(pjxxQueryWrapper, false);
                    //民间借款
                    BigDecimal mjjk = pjxx != null && StringUtils.isNotBlank(pjxx.getMjjkje()) ? new BigDecimal(pjxx.getMjjkje()) : new BigDecimal("0");
                    data.put("mjjk", mjjk.compareTo(new BigDecimal("0")) > 0 ? mjjk.divide(new BigDecimal("10000")) : "");
                    //农商行借款
                    QueryWrapper<KhxxglYwhywwlxxH> ywxxQueryWrapper = new QueryWrapper<>();
                    ywxxQueryWrapper.eq("hhbm", nhxq.getHhbm());
                    KhxxglYwhywwlxxH ywxx = khxxglYwhywwlxxHService.getOne(ywxxQueryWrapper, false);
                    BigDecimal nshjk = ywxx != null && ywxx.getDkje() != null ? ywxx.getDkje() : new BigDecimal("0");
                    data.put("nshjk", nshjk.compareTo(new BigDecimal("0")) > 0 ? nshjk.divide(new BigDecimal("10000")) : "");
                    //其他金融机构借款
                    BigDecimal qtjrjgjk = pjxx != null && StringUtils.isNotBlank(pjxx.getThjksl()) ? new BigDecimal(pjxx.getThjksl()) : new BigDecimal("0");
                    data.put("qtjrjgjk", qtjrjgjk.compareTo(new BigDecimal("0")) > 0 ? qtjrjgjk.divide(new BigDecimal("10000")) : "");
                    //家庭负债合计
                    data.put("jtfzhj", (mjjk.add(nshjk).add(qtjrjgjk)).divide(new BigDecimal("10000")));
                    //工资性收入
                    BigDecimal gzxsr = pjxx != null && StringUtils.isNotBlank(pjxx.getGzxxmjsr()) ? new BigDecimal(pjxx.getGzxxmjsr()) : new BigDecimal("0");
                    data.put("gzxsr", gzxsr.compareTo(new BigDecimal("0")) > 0 ? gzxsr.divide(new BigDecimal("10000")) : "");
                    //住房公积金年个人交纳额
                    BigDecimal zfgjj = pjxx != null && StringUtils.isNotBlank(pjxx.getZfgjjxmjsr()) ? new BigDecimal(pjxx.getZfgjjxmjsr()) : new BigDecimal("0");
                    data.put("zfgjj", zfgjj.compareTo(new BigDecimal("0")) > 0 ? zfgjj.divide(new BigDecimal("10000")) : "");
                    //其他收入
                    BigDecimal zz = pjxx != null && StringUtils.isNotBlank(pjxx.getZzxmjsr()) ? new BigDecimal(pjxx.getZzxmjsr()) : new BigDecimal("0");
                    BigDecimal yz = pjxx != null && StringUtils.isNotBlank(pjxx.getYzxmjsr()) ? new BigDecimal(pjxx.getYzxmjsr()) : new BigDecimal("0");
                    BigDecimal sy = pjxx != null && StringUtils.isNotBlank(pjxx.getSyxmjsr()) ? new BigDecimal(pjxx.getSyxmjsr()) : new BigDecimal("0");
                    BigDecimal nw = pjxx != null && StringUtils.isNotBlank(pjxx.getNwxmjsr()) ? new BigDecimal(pjxx.getNwxmjsr()) : new BigDecimal("0");
                    BigDecimal qt = pjxx != null && StringUtils.isNotBlank(pjxx.getQtxmjsr()) ? new BigDecimal(pjxx.getQtxmjsr()) : new BigDecimal("0");
                    BigDecimal qtsr = zz.add(yz).add(sy).add(nw).add(qt);
                    data.put("qtsr", qtsr.compareTo(new BigDecimal("0")) > 0 ? qtsr.divide(new BigDecimal("10000")) : "");
                    //家庭收入合计
                    BigDecimal nsrhj = gzxsr.add(zfgjj).add(qtsr);
                    data.put("nsrhj", nsrhj.divide(new BigDecimal("10000")));
                    //生活消费支出
                    BigDecimal shxfzc = pjxx != null && StringUtils.isNotBlank(pjxx.getShxfzcjkje()) ? new BigDecimal(pjxx.getShxfzcjkje()) : new BigDecimal("0");
                    data.put("shxfzc", shxfzc.compareTo(new BigDecimal("0")) > 0 ? shxfzc.divide(new BigDecimal("10000")) : "");
                    //家庭重大支出
                    BigDecimal jtzdzc = pjxx != null && StringUtils.isNotBlank(pjxx.getJtzdzcjkje()) ? new BigDecimal(pjxx.getJtzdzcjkje()) : new BigDecimal("0");
                    data.put("jtzdzc", jtzdzc.compareTo(new BigDecimal("0")) > 0 ? jtzdzc.divide(new BigDecimal("10000")) : "");
                    //子女教育支出
                    BigDecimal znjyzc = pjxx != null && StringUtils.isNotBlank(pjxx.getZnjyjkje()) ? new BigDecimal(pjxx.getZnjyjkje()) : new BigDecimal("0");
                    data.put("znjyzc", znjyzc.compareTo(new BigDecimal("0")) > 0 ? znjyzc.divide(new BigDecimal("10000")) : "");
                    //其他支出
                    BigDecimal other = pjxx != null && StringUtils.isNotBlank(pjxx.getQtfzsl()) ? new BigDecimal(pjxx.getQtfzsl()) : new BigDecimal("0");
                    BigDecimal xyk = pjxx != null && StringUtils.isNotBlank(pjxx.getXyksl()) ? new BigDecimal(pjxx.getXyksl()) : new BigDecimal("0");
                    BigDecimal qtzc = nshjk.add(mjjk).add(other).add(qtjrjgjk).add(xyk);
                    data.put("qtzc", qtzc.compareTo(new BigDecimal("0")) > 0 ? qtzc.divide(new BigDecimal("10000")) : "");
                    //家庭支出合计
                    BigDecimal nzchj = shxfzc.add(jtzdzc).add(znjyzc).add(qtzc);
                    data.put("nzchj", nzchj.divide(new BigDecimal("10000")));
                    //家庭年纯收入
                    data.put("jtncsr", (nsrhj.subtract(nzchj)).divide(new BigDecimal("10000")));
                    //道德品质及社会评价
                    QueryWrapper<Nhbkbpy> nhplpyQueryWrapper = new QueryWrapper<>();
                    nhplpyQueryWrapper.eq("zjhm", khglNhhzxxgl.getSxdxzjh());
                    List<Nhbkbpy> nhplpyList = nhbkbpyService.list(nhplpyQueryWrapper);
                    Nhbkbpy nhbkbpy = null;
                    if (nhplpyList.size() > 0) {
                        nhbkbpy = nhplpyList.stream().max(Comparator.comparing(Nhbkbpy::getPysj)).get();
                    }
                    data.put("ddpzjshpj", nhbkbpy != null && StringUtils.isNotBlank(nhbkbpy.getShswjry()) ? nhbkbpy.getShswjry() : '0');
                    //与农商行往来时间
                    QueryWrapper<KhglYwhywwlxxPad> zzrqQueryWrapper = new QueryWrapper<>();
                    zzrqQueryWrapper.eq("zjhm", khglNhhzxxgl.getSxdxzjh());
                    KhglYwhywwlxxPad khglYwhywwlxxPad = khglYwhywwlxxPadMapper.getOne(zzrqQueryWrapper, false);
                    String dateLx = "0";
                    if (khglYwhywwlxxPad != null && khglYwhywwlxxPad.getDkzzffrq() != null && khglYwhywwlxxPad.getCkzzkhrq() != null) {
                        Date minDate = null;
                        if (khglYwhywwlxxPad.getDkzzffrq() != null && khglYwhywwlxxPad.getCkzzkhrq() == null) {
                            minDate = khglYwhywwlxxPad.getDkzzffrq();
                        } else if (khglYwhywwlxxPad.getDkzzffrq() == null && khglYwhywwlxxPad.getCkzzkhrq() != null) {
                            minDate = khglYwhywwlxxPad.getCkzzkhrq();
                        } else {
                            minDate = khglYwhywwlxxPad.getCkzzkhrq().compareTo(khglYwhywwlxxPad.getDkzzffrq()) > 0 ? khglYwhywwlxxPad.getDkzzffrq() : khglYwhywwlxxPad.getCkzzkhrq();
                        }
                        Period period = Period.between(LocalDate.parse(DateUtil.format(minDate, "yyyy-MM-dd")), LocalDate.parse(DateUtil.format(new Date(), "yyyy-MM-dd")));
                        int years = period.getYears();
                        if (years < 3) {
                            dateLx = "1";
                        } else if (years >= 3 && years < 6) {
                            dateLx = "2";
                        } else if (years >= 6 && years < 10) {
                            dateLx = "3";
                        } else {
                            dateLx = "4";
                        }
                    }
                    data.put("ynshwlsj", dateLx);
                    //家庭资产
                    //获取房产信息
                    QueryWrapper<CamsZcsxNhfcxxPad> khhmcQueryWrapper = new QueryWrapper<>();
                    khhmcQueryWrapper.eq("hhbm", khglNhhzxxgl.getHhbm());
                    List<CamsZcsxNhfcxxPad> list = camsZcsxNhfcxxPad.list(khhmcQueryWrapper);
                    List<FxedJtzcVo> jtzcList = new ArrayList<>();
                    if (list.size() > 0) {
                        list.forEach(e -> {
                            FxedJtzcVo fxedJtzcVo = new FxedJtzcVo();
                            fxedJtzcVo.setZclx("房产");
                            fxedJtzcVo.setZcxx(e.getFcwz());
                            BigDecimal fcjz = e.getFcjz() == null ? new BigDecimal("0") : e.getFcjz().divide(new BigDecimal("10000"));
                            fxedJtzcVo.setZcjz(fcjz);
                            jtzcList.add(fxedJtzcVo);
                        });
                    }
                    if (pjxx != null) {
                        FxedJtzcVo fxedJtzcVo1 = new FxedJtzcVo();
                        fxedJtzcVo1.setZclx("股权");
                        fxedJtzcVo1.setZcxx(pjxx.getGqxqsm());
                        fxedJtzcVo1.setZcjz(pjxx.getGqjz() == null ? new BigDecimal("0") : new BigDecimal(pjxx.getGqjz()).divide(new BigDecimal("10000")));
                        FxedJtzcVo fxedJtzcVo2 = new FxedJtzcVo();
                        fxedJtzcVo2.setZclx("农机具");
                        fxedJtzcVo2.setZcxx(pjxx.getNjjqxsm());
                        fxedJtzcVo2.setZcjz(pjxx.getNjjjz() == null ? new BigDecimal("0") : new BigDecimal(pjxx.getNjjjz()).divide(new BigDecimal("10000")));
                        FxedJtzcVo fxedJtzcVo3 = new FxedJtzcVo();
                        fxedJtzcVo3.setZclx("家用电器");
                        fxedJtzcVo3.setZcxx(pjxx.getJydqxqsm());
                        fxedJtzcVo3.setZcjz(pjxx.getJydqjz() == null ? new BigDecimal("0") : new BigDecimal(pjxx.getJydqjz()).divide(new BigDecimal("10000")));
                        FxedJtzcVo fxedJtzcVo4 = new FxedJtzcVo();
                        fxedJtzcVo4.setZclx("交通工具");
                        fxedJtzcVo4.setZcxx(pjxx.getJtgjxqsm());
                        fxedJtzcVo4.setZcjz(pjxx.getJtgjjz() == null ? new BigDecimal("0") : new BigDecimal(pjxx.getJtgjjz()).divide(new BigDecimal("10000")));
                        FxedJtzcVo fxedJtzcVo5 = new FxedJtzcVo();
                        fxedJtzcVo5.setZclx("其他资产");
                        fxedJtzcVo5.setZcxx(pjxx.getQtzcxqsm());
                        fxedJtzcVo5.setZcjz(pjxx.getQtzcjz() == null ? new BigDecimal("0") : new BigDecimal(pjxx.getQtzcjz()).divide(new BigDecimal("10000")));
                        jtzcList.add(fxedJtzcVo1);
                        jtzcList.add(fxedJtzcVo2);
                        jtzcList.add(fxedJtzcVo3);
                        jtzcList.add(fxedJtzcVo4);
                        jtzcList.add(fxedJtzcVo5);
                    }
                    data.put("jtzc", jtzcList);

                    String fileName = "福祥e贷业务申请审批表" + DateUtil.format(new Date(), "yyyyMMddHHmmssSSS") + ".docx";
                    String exportFilePath = exportpath + File.separator + fileName;
                    WordUtils.generateWord(data, exportFilePath, "福祥e贷业务申请审批表.ftl");
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


    @RequestMapping("/addNhxq")
    public Result<?> addNhxq(Nhxq nhxq) {
        log.info("=== addNhxq {} ===", nhxq);

        if (org.apache.commons.lang3.StringUtils.isBlank(nhxq.getZjhm()))
            return Result.error("证件号码不能为空");
        LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Nhxq::getZjhm, nhxq.getZjhm());
        long count = nhxqService.count(lambdaQueryWrapper);
        if (count > 0)
            return Result.error("该证件号码已经存在");
        nhxq.setSskhjl(getWorkNo());
        nhxq.setCreateTime(new Date());
        nhxq.setCreateBy(getWorkNo());
        nhxq.setUpdateTime(new Date());
        nhxq.setUpdateBy(getWorkNo());
        if (org.apache.commons.lang3.StringUtils.isBlank(nhxq.getHhbm())) {
            nhxq.setHhbm(IdUtil.fastSimpleUUID());
        }

        nhxq.setLrbz("1");
        boolean save = nhxqService.save(nhxq);
        if (save)
            return Result.ok("成功");
        return Result.ok("失败");
    }

    @Autowired
    NhxqBakMapper nhxqBakMapper;

    @RequestMapping("/removeHhbmById")
    public Result<?> removeHhbmById(String id) {
        if (org.apache.commons.lang3.StringUtils.isBlank(id)) {
            return Result.error("删除成员不存在");
        }
        Nhxq byId = service.getById(id);
        if (byId != null) {
            service.removeById(id);
            byId.setUpdateBy(getWorkNo());
            byId.setUpdateTime(new Date());

            NhxqBak nhxqBak = new NhxqBak();
            BeanUtils.copyProperties(byId, nhxqBak);
            nhxqBakMapper.insert(nhxqBak);
        }
        return Result.ok("成功");
    }

    @RequestMapping("/khzy020")
    public Result<?> khzy020(Nhxq nhxq,
                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                             HttpServletRequest req) {
        nhxq.setZz(null);
        String wgbh = null;
        if (org.apache.commons.lang3.StringUtils.isNotBlank(nhxq.getWgbh())) {
            wgbh = nhxq.getWgbh();
            nhxq.setWgbh(null);
        }
        QueryWrapper<Nhxq> queryWrapper = QueryGenerator.initQueryWrapper(nhxq, req.getParameterMap());
        queryWrapper.eq("sszh", getLoginUser().getOrgCode());
        if (getRedisRoleName().contains("客户经理")) {
            queryWrapper.eq("sskhjl", getWorkNo());
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(wgbh)) {
            queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
        }
        Page<Nhxq> page = new Page<Nhxq>(pageNo, pageSize);
        IPage<Nhxq> pageList = nhxqService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    @RequestMapping("/khzyok020")
    public Result<?> khzyok020(Nhxq nhxq, HttpServletRequest request) {
        String selections = request.getParameter("selections");
        String rowKey = request.getParameter("rowKey");

        //20211201 过滤选中数据
        //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            //service.khzyokList(selectionList, nhxq.getSskhjl());
            for (String s :
                    selectionList) {
                service.khzyoid(s, nhxq.getZz());
            }
            return Result.ok();
        }


        String khjl = getWorkNo();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(nhxq.getSskhjl())) {
            khjl = nhxq.getSskhjl();
        }
        service.khzyok(nhxq, getLoginUser().getOrgCode(), khjl);
        return Result.ok();
    }

    /**
     * 审批通过后的数据导入惠农快贷白名单
     */
    @RequestMapping("/sphdrhnkdbmd")
    public Result<?> sphdrhnkdbmd() {
        log.info("=====sphdrhnkdbmd开始更新惠农快贷客户类型=====");
        try {
            LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "vnhpjsx");
            List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
            boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
            if (b) {
                service.drhnkdbmd();
            } else {
                service.drhnkdbmdsszh(getLoginUser().getOrgCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("=====sphdrhnkdbmd更新惠农快贷客户类型完成=====");
        return Result.ok();
    }

    /**
     * 祁阳-导出黑名单数据
     */
    @RequestMapping("/exportHmdXls")
    public ModelAndView exportHmdXls(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // Step.1 组装查询条件
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        // Step.2 获取导出数据
        List<NhHmdDataVo> exportList = service.getHmdData();
        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "农户黑名单数据表"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, NhHmdDataVo.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户黑名单数据表", "导出人:" + sysUser.getRealname(), "农户黑名单数据表"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
        return mv;
    }

    /**
     * 新田-导入评议数据
     */
    @RequestMapping(value = "/importPysjXtXls", method = RequestMethod.POST)
    public Result<?> importPysjXtXls(@RequestBody JSONObject jsonObject) {
        String filePaths = jsonObject.getString("filePaths");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                // 读取Excel文件
                Workbook workbook = WorkbookFactory.create(file);
                log.info("==================共有" + workbook.getNumberOfSheets() + "个Sheet页");
                //遍历sheet页
                for (Integer i = 1; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    if (StringUtils.isBlank(workbook.getSheetName(i))) {
                        return Result.error("Sheet页名称为空！！");
                    }
                    //评议轮数
                    Integer pyls = i;
                    log.info("======================当前导入数据Sheet页索引:" + pyls + "==================================");
                    //评议员姓名
                    String pyyxm = null;
                    //评议员证件号码
                    String pyyzjhm = null;
                    //网格名称
                    String wgmc = null;
                    List<Nhbkbpy> nhbkbpyList = new ArrayList<>();
                    List<Nhbkbpy> ypyList = new ArrayList<>();
                    for (Row row : sheet) {
                        System.out.println("当前行数-------------------------"+row.getRowNum());
                        Cell khmc=row.getCell(2);
                        if (khmc!=null && khmc.getCellType()!=Cell.CELL_TYPE_BLANK) {
                            System.out.println("当前客户-------------------------"+khmc.getStringCellValue());
                        }
                        if (row.getRowNum() == 0 || row.getRowNum() == 2 || row.getRowNum() == 3) {
                            continue;
                        } else if (row.getRowNum() == 1) {
                            wgmc = row.getCell(2).getStringCellValue();
                            if (StringUtils.isBlank(wgmc)) {
                                return Result.error("当前导入sheet页索引：" + pyls + "(第" + pyls + "轮),缺失网格名称！！请排查后重新导入！！");
                            }
                            pyyxm = row.getCell(9).getStringCellValue();
                            if (StringUtils.isBlank(pyyxm)) {
                                return Result.error("当前导入sheet页索引：" + pyls + "(第" + pyls + "轮),缺失评议员姓名！！请排查后重新导入！！");
                            }
                            row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                            pyyzjhm = row.getCell(13).getStringCellValue();
                            if (StringUtils.isBlank(pyyzjhm)) {
                                return Result.error("当前导入sheet页索引：" + pyls + "(第" + pyls + "轮),缺失评议员证件号码！！请排查后重新导入！！");
                            }
                            if (!IdcardUtil.isValidCard(pyyzjhm)) {
                                return Result.error("当前导入sheet页索引：" + pyls + "(第" + pyls + "轮),评议员证件号码不规范！！！请重新填写后导入！！");
                            }
                        } else {
                            //第8个单元格：是否了解情况，如果为空就说明是没有评议的客户，不导入到评议表,不做处理
                            Cell cell=row.getCell(7);
                            if (cell==null || cell.getCellType()==Cell.CELL_TYPE_BLANK) {
                                continue;
                            }
                            Nhbkbpy nhbkbpy = new Nhbkbpy();
                            //网格编号
                            QueryWrapper<VYxdyglMain> vYxdyglMainQueryWrapper = new QueryWrapper<>();
                            vYxdyglMainQueryWrapper.eq("wgmc_show", wgmc);
                            VYxdyglMain vYxdyglMain = vYxdyglMainService.getOne(vYxdyglMainQueryWrapper, false);
                            nhbkbpy.setQydm(vYxdyglMain == null ? null : vYxdyglMain.getWgbh());
                            //获取当前网格下当前轮数下已存在的评议信息
                            if (vYxdyglMain != null && StringUtils.isNotBlank(vYxdyglMain.getWgbh())) {
                                QueryWrapper<Nhbkbpy> nhbkbpyQueryWrapper = new QueryWrapper<>();
                                nhbkbpyQueryWrapper.eq("pyls", pyls).eq("qydm", vYxdyglMain.getWgbh());
                                ypyList = nhbkbpyService.list(nhbkbpyQueryWrapper);
                            }
                            //评议轮数
                            nhbkbpy.setPyls(pyls);
                            //评议姓名
                            nhbkbpy.setPyyxm(pyyxm);
                            //评议员证件号码
                            nhbkbpy.setPyyzjhm(pyyzjhm);
                            //评议时间
                            nhbkbpy.setPysj(new Date());
                            //评议类型
                            nhbkbpy.setPylx("9");
                            //户号编码
                            String hhbm = row.getCell(0).getStringCellValue();
                            if (StringUtils.isBlank(hhbm)) {
                                hhbm = getMergedRegionValue(sheet, row.getRowNum(), 0);
                            }
                            nhbkbpy.setHhbm(hhbm);
                            //客户名称
                            nhbkbpy.setKhmc(row.getCell(2).getStringCellValue());
                            //证件号码
                            QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
                            nhxqQueryWrapper.eq("hhbm", hhbm);
                            nhxqQueryWrapper.eq("khmc", row.getCell(2).getStringCellValue());
                            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                            nhxqQueryWrapper.eq("nl", row.getCell(3).getStringCellValue());
                            nhxqQueryWrapper.eq("yhzgx", sysDictService.queryDictValueByKey("yhzgx", row.getCell(1).getStringCellValue()));
                            Nhxq nhxq = nhxqService.getOne(nhxqQueryWrapper, false);
                            nhbkbpy.setZjhm(nhxq == null ? null : nhxq.getZjhm());
                            //是否了解情况
                            nhbkbpy.setSfljqk(StringUtils.isNotBlank(row.getCell(7).getStringCellValue()) ? sysDictService.queryDictValueByKey("sfljqk", row.getCell(7).getStringCellValue()) : null);
                            //不予授信情形
                            if (StringUtils.isNotBlank(row.getCell(8).getStringCellValue())) {
                                nhbkbpy.setBysxqx(sysDictService.queryDictValueByKey("py_bysxqx_ny", row.getCell(8).getStringCellValue()));
                                //不予授信类型
                                nhbkbpy.setBysxlx("9");
                                nhbkbpy.setPylx("0");
                            }
                            //备注
                            nhbkbpy.setBz(row.getCell(9).getStringCellValue());
                            //农村房产情况
                            String ncfcqk = row.getCell(11).getStringCellValue();
                            nhbkbpy.setNcfcqk(StringUtils.isNotBlank(ncfcqk) ? sysDictService.queryDictValueByKey("ncfcqk_xt", ncfcqk) : null);
                            //城区有无房产
                            nhbkbpy.setCqywfc(StringUtils.isNotBlank(row.getCell(12).getStringCellValue()) ? sysDictService.queryDictValueByKey("ywbz", row.getCell(12).getStringCellValue()) : null);
                            //有无车辆
                            nhbkbpy.setYwcl(StringUtils.isNotBlank(row.getCell(13).getStringCellValue()) ? sysDictService.queryDictValueByKey("ywbz", row.getCell(13).getStringCellValue()) : null);
                            //是否在本地
                            nhbkbpy.setSfzbd(StringUtils.isNotBlank(row.getCell(14).getStringCellValue()) ? sysDictService.queryDictValueByKey("sfzbd", row.getCell(14).getStringCellValue()) : null);
                            //长期居住地
                            nhbkbpy.setCqjzd(StringUtils.isNotBlank(row.getCell(15).getStringCellValue()) ? sysDictService.queryDictValueByKey("sfzbd", row.getCell(15).getStringCellValue()) : null);
                            //主营项目
                            nhbkbpy.setZyxm(StringUtils.isNotBlank(row.getCell(16).getStringCellValue()) ? sysDictService.queryDictValueByKey("zyxm_xt", row.getCell(16).getStringCellValue()) : null);
                            //收入
                            String tmp = StringUtils.isNotBlank(row.getCell(17).getStringCellValue()) ? sysDictService.queryDictValueByKey("bkbpy_sr_xt", row.getCell(17).getStringCellValue()) : null;
                            nhbkbpy.setSr(StringUtils.isNotBlank(tmp) ? Integer.valueOf(tmp) : null);
                            //基础模型测算
                            row.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
                            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                            CellValue cellValue = evaluator.evaluate(row.getCell(18));
                            BigDecimal ed = row.getCell(18).getStringCellValue() == null ? new BigDecimal("0") : new BigDecimal(cellValue.getStringValue());
                            nhbkbpy.setJcmxcs(ed);
                            //建议授信额度
                            nhbkbpy.setJysxed(ed);
                            //录入标志
                            nhbkbpy.setLrbz("3");
                            nhbkbpyList.add(nhbkbpy);
                        }
                    }
                    //一户只能评议一个家庭成员
                    Map<String, Long> mapPyxx = nhbkbpyList.stream().collect(Collectors.groupingBy(Nhbkbpy::getHhbm, Collectors.counting()));
                    Boolean con = false;
                    for (Map.Entry<String, Long> entry : mapPyxx.entrySet()) {
                        if (Integer.valueOf(entry.getValue().intValue()) > 1) {
                            con = true;
                            break;
                        }
                    }
                    if (con) {
                        return Result.error("当前导入sheet页索引：" + pyls + "(第" + pyls + "轮),一户只能导入一个家庭成员的评议信息！！请排查后重新导入！！");
                    }
                    if (CollUtil.isNotEmpty(ypyList)) {
                        List<String> hhbmList = ypyList.stream().map(Nhbkbpy::getHhbm).distinct().collect(Collectors.toList());
                        nhbkbpyList = nhbkbpyList.stream().filter(e -> hhbmList.contains(e.getHhbm())).collect(Collectors.toList());
                        if (CollUtil.isNotEmpty(nhbkbpyList)) {
                            List<String> khmcList = nhbkbpyList.stream().map(Nhbkbpy::getKhmc).distinct().collect(Collectors.toList());
                            String khmc = String.join(",", khmcList);
                            return Result.error("当前导入sheet页索引：" + pyls + "(第" + pyls + "轮),导入的评议对象(" + khmc + ")在系统中已经存在评议信息！！！");
                        }
                    }
                    nhbkbpyService.saveBatch(nhbkbpyList);
                }

                return Result.ok("文件导入完成！");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

    /**
     * 获取合并单元格的值
     */
    public String getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return fCell.getStringCellValue();
                }
            }
        }

        return null;
    }
}
