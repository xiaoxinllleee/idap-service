package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.base.entity.SimpleStandardTable;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.khgl.khxx.entity.KhywxxSjyhPc;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khgl.sh.entity.ShglYwhywwlxx;
import org.cmms.modules.khgl.sh.entity.shhmcxx;
import org.cmms.modules.khgl.sh.service.IShglYwhywwlxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.*;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IKhxxglKhxqXxnyztService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IVKhxxglKhxqXxnyztService;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;
import org.cmms.modules.khxxgl.khflgl.shxq.service.IShxqService;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.pyxx.entity.Pyfjxx;
import org.cmms.modules.pad.pyxx.entity.Pyyxx;
import org.cmms.modules.pad.pyxx.service.IPyfjxxService;
import org.cmms.modules.pad.pyxx.service.IPyyxxService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysRoleService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.hutool.core.codec.Base64;

/**
 * @Description: 新型农业主体
 * @Author: jeecg-boot
 * @Date: 2022-12-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "新型农业主体")
@RestController
@RequestMapping("/khxxgl/khxxglKhxqXxnyzt")
public class KhxxglKhxqXxnyztController extends JeecgController<KhxxglKhxqXxnyzt, IKhxxglKhxqXxnyztService> {
    @Autowired
    ListToDictUtil listToDictUtil;
    @Autowired
    INhxqService nhxqService;
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    IPyyxxService pyyxxService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;
    @Autowired
    IYxdyglMainService yxdyglMainService;
    @Autowired
    private IShxqService shxqService;
    @Autowired
    private INhbkbpyService nhbkbpyService;
    @Autowired
    private IPyfjxxService pyfjxxService;
    @Autowired
    private IShglYwhywwlxxService iShglYwhywwlxxService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private IVKhxxglKhxqXxnyztService ivKhxxglKhxqXxnyztService;

    /**
     * 分页列表查询
     *
     * @param khxxglKhxqXxnyzt
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "新型农业主体-分页列表查询")
    @ApiOperation(value = "新型农业主体-分页列表查询", notes = "新型农业主体-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(KhxxglKhxqXxnyzt khxxglKhxqXxnyzt,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String wgbh = null;
        if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getWgbh())) {
            wgbh = khxxglKhxqXxnyzt.getWgbh();
            khxxglKhxqXxnyzt.setWgbh(null);
        }
        String bmdyxs = null;
        if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getBmdysx())) {
            bmdyxs = khxxglKhxqXxnyzt.getBmdysx();
            khxxglKhxqXxnyzt.setBmdysx(null);
        }
        QueryWrapper<KhxxglKhxqXxnyzt> queryWrapper = QueryGenerator.initQueryWrapper(khxxglKhxqXxnyzt, req.getParameterMap());
        if (getRedisQydm().equals(QydmEnums.TIANYI.getQydmCode())) {
//            if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002")) {
//                queryWrapper.eq("ghzrr", getWorkNo());
//            }
//            if (getRedisRoleCode().contains("RZ001")) {
//                queryWrapper.eq("sszh", getLoginUser().getOrgCode());
//            }

            if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002") || getRedisRoleCode().contains("RZ001")) {
                queryWrapper.eq("sszh", getLoginUser().getOrgCode());
            }
            queryWrapper.orderByDesc("wgbh");

        }


        if (StringUtils.isNotBlank(bmdyxs)) {
            if ("1".equals(bmdyxs)) {
                queryWrapper.gt("ysxedcj", 0);
            } else {
                queryWrapper.isNull("ysxedcj").or().le("ysxedcj", 0);
            }
        }

        if (StringUtils.isNotBlank(wgbh)) {
            queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
        }

        //永兴-客户经理只能查看修改自己所在支行数据，角色是管理员可以修改全行数据
        String qydm = getRedisQydm();
        LoginUser loginUser = getLoginUser();
        SysRole sysRole = sysRoleService.getById(loginUser.getRoles());
        if (StringUtils.isNotBlank(qydm) && qydm.equals(QydmEnums.YONGXING.getQydmCode()) && sysRole != null && !"admin".equals(sysRole.getRoleCode())) {
            queryWrapper.eq("sszh", loginUser.getOrgCode());
        }

        Page<KhxxglKhxqXxnyzt> page = new Page<KhxxglKhxqXxnyzt>(pageNo, pageSize);
        IPage<KhxxglKhxqXxnyzt> pageList = service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 分页列表查询-天易pc
     */
    @AutoLog(value = "新型农业主体-分页列表查询")
    @ApiOperation(value = "新型农业主体-分页列表查询", notes = "新型农业主体-分页列表查询")
    @GetMapping(value = "/listTyPc")
    public Result<?> listTyPc(VKhxxglKhxqXxnyzt vKhxxglKhxqXxnyzt,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                              HttpServletRequest req) {
        Date sjrq = vKhxxglKhxqXxnyzt.getSjrq();
        if (vKhxxglKhxqXxnyzt.getSjrq() == null) {
            Date maxDate = ivKhxxglKhxqXxnyztService.getMaxDate();
            sjrq = maxDate;
        } else {
            QueryWrapper<VKhxxglKhxqXxnyzt> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sjrq", sjrq);
            long  count = ivKhxxglKhxqXxnyztService.count(queryWrapper);
            if (count == 0) {
                return Result.error(510, "未找到当前查询数据日期的业务数据");
            }
        }
        vKhxxglKhxqXxnyzt.setSjrq(null);
        String wgbh = null;
        if (StringUtils.isNotBlank(vKhxxglKhxqXxnyzt.getWgbh())) {
            wgbh = vKhxxglKhxqXxnyzt.getWgbh();
            vKhxxglKhxqXxnyzt.setWgbh(null);
        }
        String bmdyxs = null;
        if (StringUtils.isNotBlank(vKhxxglKhxqXxnyzt.getBmdysx())) {
            bmdyxs = vKhxxglKhxqXxnyzt.getBmdysx();
            vKhxxglKhxqXxnyzt.setBmdysx(null);
        }
        QueryWrapper<VKhxxglKhxqXxnyzt> queryWrapper = QueryGenerator.initQueryWrapper(vKhxxglKhxqXxnyzt, req.getParameterMap());
        Date sjrq1 = sjrq;
        queryWrapper.and(wrapper->wrapper.eq("sjrq", sjrq1).or().isNull("sjrq"));
        if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002") || getRedisRoleCode().contains("RZ001")) {
            queryWrapper.eq("sszh", getLoginUser().getOrgCode());
        }
        queryWrapper.orderByDesc("wgbh");

        if (StringUtils.isNotBlank(bmdyxs)) {
            if ("1".equals(bmdyxs)) {
                queryWrapper.gt("ysxedcj", 0);
            } else {
                queryWrapper.isNull("ysxedcj").or().le("ysxedcj", 0);
            }
        }

        if (StringUtils.isNotBlank(wgbh)) {
            queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
        }

        Page<VKhxxglKhxqXxnyzt> page = new Page<VKhxxglKhxqXxnyzt>(pageNo, pageSize);
        IPage<VKhxxglKhxqXxnyzt> pageList = ivKhxxglKhxqXxnyztService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping(value = "/list2")
    public Result<?> queryPageList2(KhxxglKhxqXxnyzt khxxglKhxqXxnyzt,
                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                    HttpServletRequest req) {
        String wgbh = null;
        if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getWgbh())) {
            wgbh = khxxglKhxqXxnyzt.getWgbh();
            khxxglKhxqXxnyzt.setWgbh(null);
        }
        String bmdyxs = null;
        if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getBmdysx())) {
            bmdyxs = khxxglKhxqXxnyzt.getBmdysx();
            khxxglKhxqXxnyzt.setBmdysx(null);
        }
        QueryWrapper<KhxxglKhxqXxnyzt> queryWrapper = QueryGenerator.initQueryWrapper(khxxglKhxqXxnyzt, req.getParameterMap());
        if (getRedisQydm().equals(QydmEnums.TIANYI.getQydmCode())) {
            if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002")) {
                queryWrapper.eq("ghzrr", getWorkNo());
            }
            if (getRedisRoleCode().contains("RZ001")) {
                queryWrapper.eq("sszh", getLoginUser().getOrgCode());
            }
        }


        if (StringUtils.isNotBlank(bmdyxs)) {
            if ("1".equals(bmdyxs)) {
                queryWrapper.gt("ysxedcj", 0);
            } else {
                queryWrapper.isNull("ysxedcj").or().le("ysxedcj", 0);
            }
        }

        if (StringUtils.isNotBlank(wgbh)) {
            queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
        }
        queryWrapper.orderByDesc("wgbh");

        log.info(" ===list2 开始缓存查询条件 ===", queryWrapper.toString());
        plpy = queryWrapper;
        plpyByIndexAndPageList = null;
        plpyByIndexAndPageNo = null;

        Page<KhxxglKhxqXxnyzt> page = new Page<KhxxglKhxqXxnyzt>(pageNo, pageSize);
        IPage<KhxxglKhxqXxnyzt> pageList = service.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    //保存批量评议时的查询条件
    QueryWrapper<KhxxglKhxqXxnyzt> plpy = null;
    List<KhxxglKhxqXxnyzt> plpyByIndexAndPageList = null;
    Integer plpyByIndexAndPageNo = null;

    @RequestMapping("/plpyByIndexAndPage")
    public Result<?> plpyByIndexAndPage(Integer index, Integer isPl,
                                        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        log.info("=== 开始 新型农业主体 批量操作 ===");
        if (plpy == null) {
            return Result.error("请重新查询批量数据");
        }
        if (this.plpyByIndexAndPageNo == pageNo && isPl == 1) {
            if (CollUtil.isNotEmpty(plpyByIndexAndPageList)) {
                System.out.println("===查缓存===");

                int size = plpyByIndexAndPageList.size();
                if (size >= index) {
                    return Result.ok(plpyByIndexAndPageList.get(index));
                }
            }
        }

        plpyByIndexAndPageNo = pageNo;
        Page<KhxxglKhxqXxnyzt> page = new Page<KhxxglKhxqXxnyzt>(pageNo, pageSize);
        IPage<KhxxglKhxqXxnyzt> pageList = service.page(page, plpy);
        //拿到对应的数据
        List<KhxxglKhxqXxnyzt> records = pageList.getRecords();
        if (CollUtil.isNotEmpty(records)) {
            List list = listToDictUtil.parseDictText(records);
            System.out.println("===真查询===");
            plpyByIndexAndPageList = list;
            int size = list.size();
            if (size >= index) {
                return Result.ok(list.get(index));
            }
        }
        return Result.ok();
    }

    /**
     * 永兴-新型农业主体-添加
     *
     * @param khxxglKhxqXxnyzt
     * @return
     */
    @AutoLog(value = "新型农业主体-添加")
    @ApiOperation(value = "新型农业主体-添加", notes = "新型农业主体-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody KhxxglKhxqXxnyzt khxxglKhxqXxnyzt) {
        if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getXxztzjhm())) {
            khxxglKhxqXxnyzt.setXxztzjhm(Base64.decodeStr(khxxglKhxqXxnyzt.getXxztzjhm()));
        }
        if (getRedisQydm().equals(QydmEnums.TIANYI.getQydmCode())) {
            if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getJyzzjhm())) {
                khxxglKhxqXxnyzt.setJyzzjhm(Base64.decodeStr(khxxglKhxqXxnyzt.getJyzzjhm()));
            }
        }
        if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getWgbh())) {
            QueryWrapper<YxdyglMain> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("wgbh", khxxglKhxqXxnyzt.getWgbh());
            List<YxdyglMain> list = yxdyglMainService.list(queryWrapper);
            if (CollUtil.isNotEmpty(list)) {
                khxxglKhxqXxnyzt.setSszh(list.get(0).getZzbz());
            }
        }
        service.save(khxxglKhxqXxnyzt);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khxxglKhxqXxnyzt
     * @return
     */
    @AutoLog(value = "新型农业主体-编辑")
    @ApiOperation(value = "新型农业主体-编辑", notes = "新型农业主体-编辑")
    @PostMapping(value = "/edit")
    public Result<?> edit(@RequestBody KhxxglKhxqXxnyzt khxxglKhxqXxnyzt) {
        System.out.println(khxxglKhxqXxnyzt);
        if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getPyyKhid())) {
            String pyyKhid = khxxglKhxqXxnyzt.getPyyKhid();
            khxxglKhxqXxnyzt.setPfrzw(pyyKhid);
            Nhxq byId = nhxqService.getById(pyyKhid);
            if (byId != null) {
                if (StringUtils.isNotBlank(byId.getZjhm()))
                    khxxglKhxqXxnyzt.setPfrzjhm(byId.getZjhm());
                if (StringUtils.isNotBlank(byId.getKhmc())) ;
                khxxglKhxqXxnyzt.setPfr(byId.getKhmc());
            }
        } else if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getPyyid())) {
            String pyyid = khxxglKhxqXxnyzt.getPyyid();
            khxxglKhxqXxnyzt.setPfrzw(pyyid);
            Pyyxx byId = pyyxxService.getById(pyyid);
            if (byId != null) {
                if (StringUtils.isNotBlank(byId.getPyyzjhm()))
                    khxxglKhxqXxnyzt.setPfrzjhm(byId.getPyyzjhm());
                if (StringUtils.isNotBlank(byId.getPyyxm()))
                    khxxglKhxqXxnyzt.setPfr(byId.getPyyxm());
            }
        }
        if (StringUtils.isBlank(khxxglKhxqXxnyzt.getCreator())) {
            khxxglKhxqXxnyzt.setCreator(getWorkNo());
        }
        if (khxxglKhxqXxnyzt.getCreateTime() == null) {
            khxxglKhxqXxnyzt.setCreateTime(new Date());
        }

//        if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getPdfl())) {
//            khxxglKhxqXxnyzt.setCzpywcsj(new Date());
//        }

        //以下3个时间只记录第一次的时间
        if (khxxglKhxqXxnyzt.getCzpywcsj() == null) {
            if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getPdfl())) {
                khxxglKhxqXxnyzt.setCzpywcsj(new Date());
            }
        } else {
            khxxglKhxqXxnyzt.setCzpywcsj(null);
        }
        if (khxxglKhxqXxnyzt.getRhhdwcsj() == null) {
            if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getZhfl())) {
                khxxglKhxqXxnyzt.setRhhdwcsj(new Date());
            }
        } else {
            khxxglKhxqXxnyzt.setRhhdwcsj(null);
        }
        if (khxxglKhxqXxnyzt.getYxzfwcsj() == null) {
            if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getZhfl()) && khxxglKhxqXxnyzt.getYsxedcj() != null) {
                khxxglKhxqXxnyzt.setYxzfwcsj(new Date());
            }
        } else {
            khxxglKhxqXxnyzt.setYxzfwcsj(null);
        }

        khxxglKhxqXxnyzt.setUpdator(getWorkNo());
        khxxglKhxqXxnyzt.setUpdateTime(new Date());
        service.updateById(khxxglKhxqXxnyzt);
        //需要去农户找 如果找不到就要添加农户
        if (StringUtils.isBlank(khxxglKhxqXxnyzt.getHhbm())) {
            xzsj(khxxglKhxqXxnyzt);
        }
        return Result.ok("编辑成功!");
    }

    public boolean xzsj(KhxxglKhxqXxnyzt khxxglKhxqXxnyzt) {
        if (StringUtils.isBlank(khxxglKhxqXxnyzt.getWgbh())
                || StringUtils.isBlank(khxxglKhxqXxnyzt.getSszh())
                || StringUtils.isBlank(khxxglKhxqXxnyzt.getJyz())
                || StringUtils.isBlank(khxxglKhxqXxnyzt.getJyzzjhm())
        ) {
            log.info("===数据有问题{},无法新增数据===");
            return false;
        }
        try {
            QueryWrapper<KhglNhhzxxgl> khxx = new QueryWrapper<>();
            khxx.eq("hzzjhm", khxxglKhxqXxnyzt.getJyzzjhm());
            List<KhglNhhzxxgl> khxxList = khglNhhzxxglService.list(khxx);
            if (khxxList != null && khxxList.size() > 0) {
                return false;
            }

            QueryWrapper nh = new QueryWrapper();
            nh.eq("zjhm", khxxglKhxqXxnyzt.getJyzzjhm());
            List list = nhxqService.list(nh);
            if (CollUtil.isNotEmpty(list)) {
                return false;
            }

            QueryWrapper queryWrapperZzbz = new QueryWrapper();
            queryWrapperZzbz.eq("zzbz", khxxglKhxqXxnyzt.getSszh());
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);

            KhglNhhzxxgl nhhzxxgl = new KhglNhhzxxgl();
            String id = UUIDGenerator.generate();
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            nhhzxxgl.setId(id);
            String hhbm = khxxglKhxqXxnyzt.getWgbh() + sysDictService.queryhhbm("seq_hhbm.nextval");
            nhhzxxgl.setHhbm(hhbm);
            nhhzxxgl.setSszh(hrBasOrganization.getZzbz());
            nhhzxxgl.setHzxm(khxxglKhxqXxnyzt.getJyz());
            nhhzxxgl.setHzzjhm(khxxglKhxqXxnyzt.getJyzzjhm());
            nhhzxxgl.setZkhjl(khxxglKhxqXxnyzt.getGhzrr());
            nhhzxxgl.setSfzf("2");
            nhhzxxgl.setSfyxzf("2");
            nhhzxxgl.setLrsj(new Date());
            nhhzxxgl.setLrbz("1");
            nhhzxxgl.setLrr(sysUser.getUsername());
            khglNhhzxxglService.save(nhhzxxgl);


            Nhxq nhxqxx = new Nhxq();
            nhxqxx.setId(id);
            nhxqxx.setJgdm(khxxglKhxqXxnyzt.getSszh());
            nhxqxx.setHhbm(hhbm);


            nhxqxx.setSszh(hrBasOrganization.getZzbz());
            nhxqxx.setKhmc(khxxglKhxqXxnyzt.getJyz());
            nhxqxx.setWgbh(khxxglKhxqXxnyzt.getWgbh());
            nhxqxx.setKhlx("1");
            nhxqxx.setYhzgx("1");
            nhxqxx.setSfhz("1");
            //khglKhhmcxx.setXb(khhmcxx.getXb());
            nhxqxx.setZjhm(khxxglKhxqXxnyzt.getJyzzjhm());
            //khglKhhmcxx.setNl(khhmcxx.getNl());
            //nhxqxx.setZz(khhmcxx.getDz());
            //nhxqxx.setHjdz(khhmcxx.getDz());
            nhxqxx.setYhzgx("1");
            nhxqxx.setLrbz("1");
            nhxqxx.setLrsj(new Date());
            nhxqxx.setLrr(sysUser.getUsername());
            nhxqService.save(nhxqxx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "新型农业主体-通过id删除")
    @ApiOperation(value = "新型农业主体-通过id删除", notes = "新型农业主体-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "新型农业主体-批量删除")
    @ApiOperation(value = "新型农业主体-批量删除", notes = "新型农业主体-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "新型农业主体-通过id查询")
    @ApiOperation(value = "新型农业主体-通过id查询", notes = "新型农业主体-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        KhxxglKhxqXxnyzt khxxglKhxqXxnyzt = service.getById(id);
        return Result.ok(khxxglKhxqXxnyzt);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param
     */
    @RequestMapping(value = "/exportXlsTyPc")
    public ModelAndView exportXlsTyPc(HttpServletRequest request, VKhxxglKhxqXxnyzt vKhxxglKhxqXxnyzt) {
        // Step.1 组装查询条件
        if (vKhxxglKhxqXxnyzt.getSjrq() == null) {
            Date maxDate = ivKhxxglKhxqXxnyztService.getMaxDate();
            vKhxxglKhxqXxnyzt.setSjrq(maxDate);
        }
        String wgbh = null;
        if (StringUtils.isNotBlank(vKhxxglKhxqXxnyzt.getWgbh())) {
            wgbh = vKhxxglKhxqXxnyzt.getWgbh();
            vKhxxglKhxqXxnyzt.setWgbh(null);
        }
        String bmdyxs = null;
        if (StringUtils.isNotBlank(vKhxxglKhxqXxnyzt.getBmdysx())) {
            bmdyxs = vKhxxglKhxqXxnyzt.getBmdysx();
            vKhxxglKhxqXxnyzt.setBmdysx(null);
        }
        QueryWrapper<VKhxxglKhxqXxnyzt> queryWrapper = QueryGenerator.initQueryWrapper(vKhxxglKhxqXxnyzt, request.getParameterMap());
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

        if (getRedisRoleCode().contains("RC002") || getRedisRoleCode().contains("RZ002") || getRedisRoleCode().contains("RZ001")) {
            queryWrapper.eq("sszh", getLoginUser().getOrgCode());
        }
        queryWrapper.orderByDesc("wgbh");


        if (StringUtils.isNotBlank(bmdyxs)) {
            if ("1".equals(bmdyxs)) {
                queryWrapper.gt("ysxedcj", 0);
            } else {
                queryWrapper.isNull("ysxedcj").or().le("ysxedcj", 0);
            }
        }

        if (StringUtils.isNotBlank(wgbh)) {
            queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
        }

        // Step.2 获取导出数据
        List<VKhxxglKhxqXxnyzt> exportList = ivKhxxglKhxqXxnyztService.list(queryWrapper);
        Date sjrq=vKhxxglKhxqXxnyzt.getSjrq()==null?ivKhxxglKhxqXxnyztService.getMaxDate():vKhxxglKhxqXxnyzt.getSjrq();

        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "新型农业主体"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, VKhxxglKhxqXxnyzt.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("新型农业主体" + "报表", "导出人:" + sysUser.getRealname()+ "   数据日期:"+ org.cmms.common.util.DateUtil.format(sjrq,"yyyy-MM-dd"), "新型农业主体"));
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
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, KhxxglKhxqXxnyzt.class);
    }


    @GetMapping(value = "/getNhListByWgbh")
    public Result<?> getNhListByWgbh(String wgbh, String khmc) {
        QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();

        //需要往上找一级
        if (StringUtils.isNotBlank(wgbh)) {
            LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(YxdyglMain::getWgbh, wgbh);
            List<YxdyglMain> list = yxdyglMainService.list(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(list)) {
                if (StringUtils.isNotBlank(list.get(0).getParentId()))
                    wgbh = list.get(0).getParentId();
            }
            nhxqQueryWrapper.inSql("wgbh", "select wgbh from yxdygl_main where wgbh='" + wgbh + "' or parent_id='" + wgbh + "'");
        }
        nhxqQueryWrapper.like("khmc", khmc);
        List<Nhxq> nhxqList = nhxqService.list(nhxqQueryWrapper);
        return Result.ok(nhxqList);
    }

    /**
     * 通过xxztzjhm匹配农业主体信息
     */
    @GetMapping(value = "/queryNyztxxByXxztzjhm")
    public Result<?> queryNyztxxByXxztzjhm(@RequestParam(name = "hhbm", required = false) String hhbm,
                                           @RequestParam(name = "shid", required = false) String shid) {
        List<String> zjhm = new ArrayList<>();
        if (StringUtils.isNotBlank(hhbm)) {
            QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
            nhxqQueryWrapper.eq("hhbm", hhbm);
            zjhm = nhxqService.list(nhxqQueryWrapper).stream().map(Nhxq::getZjhm).collect(Collectors.toList());
        }
        if (StringUtils.isNotBlank(shid)) {
            QueryWrapper<Shxq> shxqQueryWrapper = new QueryWrapper<>();
            shxqQueryWrapper.eq("id", shid);
            zjhm = shxqService.list(shxqQueryWrapper).stream().map(Shxq::getFrzjhm).filter(Objects::nonNull).collect(Collectors.toList());
        }
        if (CollUtil.isNotEmpty(zjhm)) {
            QueryWrapper<KhxxglKhxqXxnyzt> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("xxztzjhm", zjhm).or().in("jyzzjhm", zjhm);
            return Result.ok(service.list(queryWrapper));
        }
        return Result.ok();
    }

    /**
     * 通过zjhm匹配商户信息
     */
    @GetMapping(value = "/queryShxxByJyzzjhm")
    public Result<?> queryShxxByJyzzjhm(@RequestParam("id") String id) {
        if (StringUtils.isNotBlank(id)) {
            QueryWrapper<KhxxglKhxqXxnyzt> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            KhxxglKhxqXxnyzt khxqXxnyzt = service.getOne(queryWrapper);

            List<String> zjhmList = new ArrayList<>();
            QueryWrapper<Shxq> shxqQueryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(khxqXxnyzt.getJyzzjhm())) {
                zjhmList.add(khxqXxnyzt.getJyzzjhm());
            }
            if (StringUtils.isNotBlank(khxqXxnyzt.getXxztzjhm())) {
                zjhmList.add(khxqXxnyzt.getXxztzjhm());
            }
            if (zjhmList.size() == 0) {
                return Result.ok();
            }
            shxqQueryWrapper.in("frzjhm", zjhmList);
            return Result.ok(shxqService.list(shxqQueryWrapper));
        }
        return Result.ok();
    }

    @GetMapping(value = "/tjfx2")
    public Result<?> tjfx2(KhxxglKhxqXxnyzt khxxglKhxqXxnyzt,
                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                           HttpServletRequest req) {
        if (StringUtils.isBlank(khxxglKhxqXxnyzt.getPyyxm()) || StringUtils.isBlank(khxxglKhxqXxnyzt.getPyyzjhm()))
            return Result.error("请选择查询时间");

        Page<XxnyztTjfx2> page = new Page<>(pageNo, pageSize);
        String qydm = getRedisQydm();
        IPage<XxnyztTjfx2> tjfx2 = null;
        if (StringUtils.isNotBlank(qydm) && (qydm.equals(QydmEnums.QIYANG.getQydmCode()) || qydm.equals(QydmEnums.YONGXING.getQydmCode()))) {
            tjfx2 = service.getTjfx2Qy(page, khxxglKhxqXxnyzt);
        } else {
            tjfx2 = service.getTjfx2(page, khxxglKhxqXxnyzt);
        }
        return Result.ok(tjfx2);
    }


    @GetMapping(value = "/getAllGhzrr")
    public Result<?> getAllGhzrr() {
        return Result.ok(service.getAllGhzrr());
    }


    @GetMapping(value = "/tjfx2mx")
    public Result<?> tjfx2mx(KhxxglKhxqXxnyzt khxxglKhxqXxnyzt,
                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                             HttpServletRequest req) {
        if (StringUtils.isBlank(khxxglKhxqXxnyzt.getPyyxm()) || StringUtils.isBlank(khxxglKhxqXxnyzt.getPyyzjhm()))
            return Result.error("请选择查询时间");
        DateTime yyyyMMdd = DateUtil.parse(khxxglKhxqXxnyzt.getPyyxm(), "yyyyMMddHHmmss");
        DateTime yyyyMMdd2 = DateUtil.parse(khxxglKhxqXxnyzt.getPyyzjhm(), "yyyyMMddHHmmss");

        khxxglKhxqXxnyzt.setPyyxm(null);
        khxxglKhxqXxnyzt.setPyyzjhm(null);

        String type = null;
        if (StringUtils.isNotBlank(khxxglKhxqXxnyzt.getXh())) {
            type = khxxglKhxqXxnyzt.getXh();
            khxxglKhxqXxnyzt.setXh(null);
        }
        QueryWrapper<KhxxglKhxqXxnyzt> queryWrapper = QueryGenerator.initQueryWrapper(khxxglKhxqXxnyzt, req.getParameterMap());
        if ("1".equals(type) || "3".equals(type)) {
            //村组评定完成总数
            queryWrapper.isNotNull("czpywcsj");
        }
        if ("2".equals(type)) {
            //待村组评定数
            //非存量客户 非黑名单客户 非无效经营 已经村组评定
            queryWrapper.and(i -> i.ne("sfysx", "1").or().isNull("sfysx"));
            queryWrapper.and(i -> i.ne("sfhmdkh", "1").or().isNull("sfhmdkh"));
            queryWrapper.and(i -> i.ne("sfzcjy", "2").or().isNull("sfzcjy"));
            queryWrapper.isNull("pdfl");
        }
//        if ("3".equals(type) || "5".equals(type) || "7".equals(type)){
//            //村组统计期间完成数  入户核定完成总数  有效走访
//            queryWrapper.ge("rhhdwcsj",yyyyMMdd);
//            queryWrapper.le("rhhdwcsj",yyyyMMdd2);
//
//        }

        if ("3".equals(type)) {
            queryWrapper.ge("czpywcsj", yyyyMMdd);
            queryWrapper.le("czpywcsj", yyyyMMdd2);
        }
        if ("4".equals(type) || "5".equals(type)) {
            //入户核定完成总数
            queryWrapper.isNotNull("rhhdwcsj");
        }
        if ("5".equals(type)) {
            queryWrapper.ge("rhhdwcsj", yyyyMMdd);
            queryWrapper.le("rhhdwcsj", yyyyMMdd2);
        }
        String qydm = getRedisQydm();
        if (StringUtils.isNotBlank(qydm) && (qydm.equals(QydmEnums.QIYANG.getQydmCode()) || qydm.equals(QydmEnums.YONGXING.getQydmCode()))) {
            if ("7".equals(type)) {
                queryWrapper.ge("yxzfwcsj", yyyyMMdd);
                queryWrapper.le("yxzfwcsj", yyyyMMdd2);
                queryWrapper.eq("sfyxzf", "1");
            }
            if ("6".equals(type) || "7".equals(type)) {
                queryWrapper.isNotNull("yxzfwcsj");
                queryWrapper.eq("sfyxzf", "1");
            }
        } else {
            if ("7".equals(type)) {
                queryWrapper.ge("yxzfwcsj", yyyyMMdd);
                queryWrapper.le("yxzfwcsj", yyyyMMdd2);
            }
            if ("6".equals(type) || "7".equals(type)) {
                queryWrapper.isNotNull("yxzfwcsj");
                queryWrapper.eq("zhfl", "1");
            }
        }

        Page<KhxxglKhxqXxnyzt> page = new Page<KhxxglKhxqXxnyzt>(pageNo, pageSize);
        IPage<KhxxglKhxqXxnyzt> pageList = service.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    @GetMapping(value = "/tjfx2exp")
    public void tjfx2exp(KhxxglKhxqXxnyzt khxxglKhxqXxnyzt,
                         HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        String qydm = khxxglKhxqXxnyzt.getQybm();
        List<XxnyztTjfx2> tjfx2 = null;
        List<String> list = null;
        if (StringUtils.isNotBlank(qydm) && qydm.equals(QydmEnums.QIYANG.getQydmCode())) {
            tjfx2 = service.getTjfx2Qy(khxxglKhxqXxnyzt);
            list = Lists.newArrayList(
                    "所属支行"
                    , "走访人"
                    , "走访数"
                    , "村组评定完成总数"
                    , "待村组评定数"
                    , "村组评定统计期间完成数"
                    , "入户核定完成总数"
                    , "入户核定统计期间完成数"
                    , "有效走访完成总数"
                    , "有效走访统计期间完成数"
            );
        }
        if (qydm.equals(QydmEnums.YONGXING.getQydmCode())) {
            tjfx2 = service.getTjfx2Qy(khxxglKhxqXxnyzt);
            list = Lists.newArrayList(
                    "所属支行"
                    , "采集人"
                    , "采集数"
                    , "村组评定完成总数"
                    , "待村组评定数"
                    , "村组评定统计期间完成数"
                    , "入户核定完成总数"
                    , "入户核定统计期间完成数"
                    , "有效走访完成总数"
                    , "有效走访统计期间完成数"
            );
        } else {
            tjfx2 = service.getTjfx2(khxxglKhxqXxnyzt);
            list = Lists.newArrayList(
                    "所属支行"
                    , "管户责任人"
                    , "管户数"
                    , "村组评定完成总数"
                    , "待村组评定数"
                    , "村组评定统计期间完成数"
                    , "入户核定完成总数"
                    , "入户核定统计期间完成数"
                    , "有效走访完成总数"
                    , "有效走访统计期间完成数"
            );
        }
        if (CollUtil.isNotEmpty(tjfx2)) {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("主体统计新报表明细");
            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook, sheet);

            simpleStandardTable.setTableName("主体统计新报表明细", list.size());
            simpleStandardTable.setTableHeader(list);

            List<List<String>> tjfx2List = getTjfx2List(tjfx2);
            simpleStandardTable.setTableData(tjfx2List);
            workbookWrite(request, response, "主体统计新报表明细", workbook);
        }
    }

    public List<List<String>> getTjfx2List(List<XxnyztTjfx2> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            XxnyztTjfx2 entity = list.get(i);
            List<String> colList = Lists.newArrayList();

            if (StringUtils.isNotBlank(entity.getSszh())) {
                String s = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "zzbz", entity.getSszh());
                colList.add(s);
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(entity.getGhzrr())) {
                String s = sysDictService.queryTableDictTextByKey("Hr_bas_staff", "ygxm", "yggh", entity.getGhzrr());
                colList.add(s);
            } else {
                colList.add("");
            }

            colList.add(entity.getGhs() + "");
            colList.add(entity.getCzzs() + "");
            colList.add(entity.getSyrs() + "");
            colList.add(entity.getCzzstj() + "");

            colList.add(entity.getRhzs() + "");
            colList.add(entity.getRhzstj() + "");

            colList.add(entity.getYxzs() + "");
            colList.add(entity.getYxzstj() + "");
            listArrayList.add(colList);
        }
        return listArrayList;
    }

    /**
     * 新型农业主体有效走访判断
     *
     * @param jsonObject
     * @return
     */
    @PostMapping("/getYXzf")
    public Result<?> getYXzf(@RequestBody JSONObject jsonObject) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        service.xxnyztYxzfInit(jsonObject.getString("id"), loginUser.getWorkNo());
        //永兴、祁阳-第一个有效走访的客户经理为该新型农业主体数据采集人
        KhxxglKhxqXxnyzt khxqXxnyzt = service.getById(jsonObject.getString("id"));
        if (khxqXxnyzt != null && "1".equals(khxqXxnyzt.getSfyxzf()) && StringUtils.isBlank(khxqXxnyzt.getGhzrr())) {
            khxqXxnyzt.setGhzrr(loginUser.getWorkNo());
            service.updateById(khxqXxnyzt);
        }
        return Result.ok();
    }

    /**
     * 新型农业主体有效走访明细
     *
     * @param id 新型农业主体id
     * @return
     */
    @GetMapping("/getZfzbxxByXxnyztId")
    public Result<?> getZfzbxxByXxnyztId(@RequestParam("id") String id) {
        return Result.ok(service.getZfzbxxByXxnyztId(id));
    }

    /**
     * 永兴-新型农业主体-匹配商户信息和贷款信息、批量评议信息
     *
     * @param id 新型农业主体id
     * @return
     */
    @GetMapping("/getOtherInfoById")
    public Result<?> getPlpyInfoById(@RequestParam(name = "id", required = false) String id,
                                     @RequestParam(name = "zjhm", required = false) String zjhm) throws IllegalAccessException {
        if (StringUtils.isNotBlank(id)) {
            //获取新型农业主体信息
            KhxxglKhxqXxnyzt khxqXxnyzt = service.getById(id);
            if (StringUtils.isNotBlank(zjhm)) {
                khxqXxnyzt.setJyzzjhm(zjhm);
            }
            if (khxqXxnyzt != null && StringUtils.isNotBlank(khxqXxnyzt.getJyzzjhm())) {
                //匹配农户批量评议信息
                QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zjhm", khxqXxnyzt.getJyzzjhm());
                List<Nhbkbpy> list = nhbkbpyService.list(queryWrapper);
                Nhbkbpy nhbkbpy = null;
                if (CollUtil.isNotEmpty(list)) {
                    nhbkbpy = list.stream().min(Comparator.comparing(Nhbkbpy::getJysxed)).get();
                }

                //匹配贷款信息
                QueryWrapper<ShglYwhywwlxx> shglYwhywwlxxQueryWrapper = new QueryWrapper<>();
                shglYwhywwlxxQueryWrapper.eq("zjhm", khxqXxnyzt.getJyzzjhm());
                List<ShglYwhywwlxx> shglYwhywwlxxList = iShglYwhywwlxxService.list(shglYwhywwlxxQueryWrapper);
                BigDecimal bigDecimal = new BigDecimal("0");
                if (CollUtil.isNotEmpty(shglYwhywwlxxList)) {
                    BigDecimal sum1 = shglYwhywwlxxList.stream().map(ShglYwhywwlxx::getDkye).reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal sum2 = shglYwhywwlxxList.stream().map(ShglYwhywwlxx::getBldkye).reduce(BigDecimal.ZERO, BigDecimal::add);
                    BigDecimal sum3 = shglYwhywwlxxList.stream().map(ShglYwhywwlxx::getBwbldkye).reduce(BigDecimal.ZERO, BigDecimal::add);
                    bigDecimal = (sum1.add(sum2)).add(sum3);
                }

                XxnyztCzpdVo xxnyztCzpdVo = new XxnyztCzpdVo();
                xxnyztCzpdVo.setYhfz(bigDecimal.divide(new BigDecimal("10000")));
                if (nhbkbpy != null && StringUtils.isBlank(khxqXxnyzt.getJkztqk()) && StringUtils.isBlank(khxqXxnyzt.getBysxqx())) {
                    BeanUtil.copyPropertiesIgnoreNull(nhbkbpy, xxnyztCzpdVo);
                }
                BeanUtil.copyPropertiesIsNull(xxnyztCzpdVo, khxqXxnyzt);
                //返回结果
                return Result.ok(khxqXxnyzt);
            }
        }
        return Result.ok();
    }

    /**
     * 永兴-新型农业主体详情页面-根据客户所选的网格和填写的经营者姓名匹配农户表获取电话号码
     *
     * @param wgbh 网格编号
     * @param jyz  经营者姓名
     * @return
     */
    @GetMapping("/getNhSjhmByJyz")
    public Result<?> getNhSjhmByJyz(@RequestParam("wgbh") String wgbh, @RequestParam("jyz") String jyz) {
        if (StringUtils.isNotBlank(wgbh) && StringUtils.isNotBlank(jyz)) {
            QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
            nhxqQueryWrapper.eq("khmc", jyz);
            nhxqQueryWrapper.inSql("wgbh", "select wgbh from yxdygl_main where wgbh='" + wgbh + "' or parent_id='" + wgbh + "'");
            List<Nhxq> nhxqList = nhxqService.list(nhxqQueryWrapper);
            return Result.ok(nhxqList.stream().filter(item -> StringUtils.isNotBlank(item.getSjhm()) && StringUtils.isNotBlank(item.getZjhm())).collect(Collectors.toList()));
        }
        return Result.ok();
    }

    /**
     * 新型农业主体-返回经营者的hhbm
     *
     * @param id 新型农业主体id
     * @return
     */
    @GetMapping("/getJtxxById")
    public Result<?> getJtxxById(@RequestParam(name = "id", required = false) String id,
                                 @RequestParam(name = "zjhm", required = false) String zjhm) {
        String zjhmTmp = null;
        if (StringUtils.isNotBlank(id)) {
            KhxxglKhxqXxnyzt khxqXxnyzt = service.getById(id);
            if (khxqXxnyzt != null && StringUtils.isNotBlank(khxqXxnyzt.getJyzzjhm())) {
                zjhmTmp = khxqXxnyzt.getJyzzjhm();
            }
        }
        if (StringUtils.isNotBlank(zjhm)) {
            zjhmTmp = zjhm;
        }
        if (StringUtils.isNotBlank(zjhmTmp)) {
            QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm", zjhmTmp);
            Nhxq nhxq = nhxqService.getOne(queryWrapper);
            if (nhxq != null && StringUtils.isNotBlank(nhxq.getHhbm())) {
                return Result.ok(nhxq.getHhbm());
            }
        }
        return Result.ok();
    }

    @GetMapping("getXxnyztInfoById")
    public Result<?> getXxnyztInfoById(@RequestParam("id") String id) {
        if (StringUtils.isNotBlank(id)) {
            return Result.ok(service.getById(id));
        }
        return Result.ok();
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/initData")
    public Result<?> initData(@RequestBody JSONObject jsonObject) {
        String sjrq = jsonObject.getString("sjrq");
        Result result = new Result<>();
        try {
            ivKhxxglKhxqXxnyztService.initData(sjrq.replace("-",""));
            result.setSuccess(true);
            result.setResult(ivKhxxglKhxqXxnyztService.getMaxDate());
            return result;
        } catch (Exception e) {
            System.out.println(e);
            log.error("提取失败", e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
}
