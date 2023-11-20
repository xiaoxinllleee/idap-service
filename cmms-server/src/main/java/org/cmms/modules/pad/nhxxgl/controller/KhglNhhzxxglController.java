package org.cmms.modules.pad.nhxxgl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import freemarker.core.JSONOutputFormat;
import lombok.Data;
import net.sf.jsqlparser.statement.Commit;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.K;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.exception.JeecgBootException;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.common.utils.AreaUtil;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khgl.jhsh.entity.TgacsTpsMchntInfo;
import org.cmms.modules.khgl.jhsh.service.ITgacsTpsMchntInfoService;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import org.cmms.modules.khgl.khhmc.service.IKhfjxxglService;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khxxgl.clkhxx.entity.Clkhgl;
import org.cmms.modules.khxxgl.clkhxx.service.IClkhglService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhycxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztYsxdx;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhycxqService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IXxnyztYsxdxService;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;
import org.cmms.modules.khxxgl.khflgl.shxq.service.IShxqService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.khxxgl.wbsjgl.bwtzgl.entity.LoanBwdkSjmx;
import org.cmms.modules.khxxgl.wbsjgl.bwtzgl.service.ILoanBwdkSjmxService;
import org.cmms.modules.khxxgl.wbsjgl.sjxfsj.entity.Sjxfsj;
import org.cmms.modules.khxxgl.wbsjgl.sjxfsj.service.ISjxfsjService;
import org.cmms.modules.pad.khhfxx.entity.HfsjtjNh;
import org.cmms.modules.pad.khhfxx.service.IHfsjtjNhService;
import org.cmms.modules.pad.nhxxgl.entity.*;
import org.cmms.modules.pad.nhxxgl.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.pyxx.entity.Nhbkbpyfsxx;
import org.cmms.modules.pad.pyxx.entity.Pyyxx;
import org.cmms.modules.pad.pyxx.service.IPyyxxService;
import org.cmms.modules.pad.shxxgl.entity.Xyk;
import org.cmms.modules.pad.shxxgl.service.IXykService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.*;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import org.cmms.modules.tjfx.tjfxHnkd.service.ITjfxHnkdService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.yxdygl.pqzrrgl.service.ITjfxcsszService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 农户户主信息管理
 * @Author: jeecg-boot
 * @Date: 2020-07-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "农户户主信息管理")
@RestController
@RequestMapping("/khglNhhzxxgl/khglNhhzxxgl")
public class KhglNhhzxxglController extends JeecgController<KhglNhhzxxgl, IKhglNhhzxxglService> {
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;
    @Autowired
    private INhbkbpyService iNhbkbpyService;
    @Autowired
    private IYwhywwlxxService ywhywwlxxService;
    @Autowired
    private IKhglNhhzzllbService iKhglNhhzzllbService;
    @Autowired
    private ICamsZcsxNhfcxxPadService camsZcsxNhfcxxPad;
    @Autowired
    private ICamsZcsxNhpjsxxxPadService iCamsZcsxNhpjsxxxPadService;
    @Autowired
    private ICamsZcsxNhcjxxPadService camsZcsxNhcjxxPadService;
    @Autowired
    private ICamsZcsxNhjbxxPadService iCamsZcsxNhjbxxPadService;
    @Autowired
    private IPyyxxService iPyyxxService;
    @Autowired
    private INhbkbpyService nhbkbpyService;
    @Autowired
    private IvKhglNhhzxxglService vKhglNhhzxxgl12;
    @Autowired
    private IvKhglNhhzxxglQueryService vkhglNhhzxxglQueryService;
    @Autowired
    private IKhywxxWsyhService khywxxWsyhMapper;
    @Autowired
    private IKhywxxSjyhPadService khywxxSjyhPadMapper;
    @Autowired
    private IKhywxxEtcService khywxxEtcMapper;
    @Autowired
    private IKhywxxDksjmxPadService khywxxDksjmxPadMapper;
    @Autowired
    private IKhglYwhywwlxxPadService khglYwhywwlxxPadMapper;
    @Autowired
    private IKhywxxSbkService khywxxSbkService;
    @Autowired
    private AreaUtil areaUtil;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IYxglKhhfxxbService yxglKhhfxxbService;
    @Autowired
    private IVhrbasstaffpostService vhrbasstaffpostService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private ITjfxcsszService tjfxcsszService;
    @Autowired
    private ITjfxCsszService iTjfxCsszService;
    @Autowired
    private IXykService xykService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private IKhjbzlService khjbzlService;
    @Autowired
    private IKhxxglYwhywwlxxHService khxxglYwhywwlxxHService;
    @Autowired
    private ITjfxCsszService tjfxCsszService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ILoanBwdkSjmxService loanBwdkSjmxService;
    @Autowired
    private IShxqService shxqService;
    @Autowired
    private ISjxfsjService sjxfsjService;
    @Autowired
    private IClkhglService clkhglService;
    @Autowired
    private IKhfjxxglService khfjxxglService;
    @Autowired
    IXxnyztYsxdxService xxnyztYsxdxService;
    @Autowired
    private ITgacsTpsMchntInfoService tgacsTpsMchntInfoService;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private IKhxxglGrsxlxmxNhService khxxglGrsxlxmxNhService;
    @Autowired
    private ISysDicService sysDicService;
    @Autowired
    private IJtcyYcLogService jtcyYcLogService;
    @Autowired
    private IKhxxglGrsxlxmxNh1Service khxxglGrsxlxmxNh1Service;
    @Autowired
    private IHfsjtjNhService hfsjtjNhService;
    @Autowired
    private ITjfxHnkdService tjfxHnkdService;
    @Autowired
    private INhycxqService nhycxqService;
    /**
     * 分页列表查询
     *
     * @param khglNhhzxxglQuery
     * @param currentPage
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1-分页列表查询")
    @ApiOperation(value = "1-分页列表查询", notes = "1-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(vKhglNhhzxxglQuery khglNhhzxxglQuery,
                                   @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "locationQuery", required = false, defaultValue = "false") Boolean locationQuery,
                                   String lxfs,
                                   String dz,
                                   String yssxz,
                                   String yxzc,
                                   String cldkkhType,
                                   HttpServletRequest req) {
        System.out.println("=============进入请求查询农户户主信息=================" + DateUtil.currentDateTime());
        log.info("进入请求查询农户户主信息");
        String hzxm = khglNhhzxxglQuery.getHzxm();
        String hzzjhm = khglNhhzxxglQuery.getHzzjhm();
        String ssyxdy = khglNhhzxxglQuery.getSsyxdy();
        khglNhhzxxglQuery.setHzxm(null);
        khglNhhzxxglQuery.setHzzjhm(null);
        khglNhhzxxglQuery.setSsyxdy(null);
        QueryWrapper<vKhglNhhzxxglQuery> queryWrapper = QueryGenerator.initQueryWrapper(khglNhhzxxglQuery, req.getParameterMap());
        System.out.println("=============查询户主信息111=================" + DateUtil.currentDateTime());
        log.info("查询户主信息");
        Page<vKhglNhhzxxglQuery> page = new Page<vKhglNhhzxxglQuery>(currentPage, pageSize);
        // 查询网格时，同时查询下级网格的数据
        if (StringUtils.isNotEmpty(ssyxdy) && StringUtils.isBlank(cldkkhType)) {
//            String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + ssyxdy + "' or parent_id='" + ssyxdy + "'";
            String sqlSswg = "select wgbh from yxdygl_main start with wgbh='" + ssyxdy + "' connect by prior id=parent_id";
            queryWrapper.and(i -> i.inSql("ssyxdy", sqlSswg));
        }
        // 根据姓名或者证件号码查询户主信息
        khglNhhzxxglQuery.setHzxm(hzxm);
        khglNhhzxxglQuery.setHzzjhm(hzzjhm);
        List<String> hhbmList = vKhglNhhzxxgl12.selectHzByCy(lxfs, yssxz, yxzc, dz, khglNhhzxxglQuery, req);
        System.out.println("=============查询户主信息222=================" + DateUtil.currentDateTime());
        if (hhbmList != null && hhbmList.size() > 0) {
            if (!StringUtils.isEmpty(khglNhhzxxglQuery.getHzxm())) {
                queryWrapper.and(wrapper -> wrapper.like("hzxm", khglNhhzxxglQuery.getHzxm()).or().in("hhbm", hhbmList));
            }
            if (!StringUtils.isEmpty(khglNhhzxxglQuery.getHzzjhm())) {
                queryWrapper.and(wrapper -> wrapper.eq("hzzjhm", khglNhhzxxglQuery.getHzzjhm()).or().in("hhbm", hhbmList));
            }
            if (!StringUtils.isEmpty(lxfs) || !StringUtils.isEmpty(yssxz) || !StringUtils.isEmpty(yxzc) || !StringUtils.isEmpty(dz)) {
                queryWrapper.and(wrapper -> wrapper.in("hhbm", hhbmList));
            }
        } else {
            if (!StringUtils.isEmpty(khglNhhzxxglQuery.getHzxm())) {
                queryWrapper.like("hzxm", khglNhhzxxglQuery.getHzxm());
            }
            if (!StringUtils.isEmpty(khglNhhzxxglQuery.getHzzjhm())) {
                queryWrapper.eq("hzzjhm", khglNhhzxxglQuery.getHzzjhm());
            }
            if (!StringUtils.isEmpty(lxfs) || !StringUtils.isEmpty(yssxz) || !StringUtils.isEmpty(yxzc) || !StringUtils.isEmpty(dz)) {
                queryWrapper.eq("id", "-1");
            }
        }
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        if (!sysUser.getUsername().equals("admin") && StringUtils.isBlank(cldkkhType)) {
            //in  用 list有个数限制问题， 此处改为inSql
            String sqlZjhm = "select hzzjhm from khgl_nhhzxxgl where ssyxdy IN " +
                    "       (SELECT menu_id FROM YXDYGL_PQQXGL t WHERE khjl = '" + sysUser.getWorkNo() + "') " +
                    "     union all " +
                    "     SELECT zjhm FROM KHXXGL_KHSSKHJL t WHERE sskhjl = '" + sysUser.getWorkNo() + "'";

            String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + sysUser.getWorkNo() + "'";
            String sqlSsKhjl = "select  zjhm from KHXXGL_KHSSKHJL t where sskhjl =" + "'" + sysUser.getWorkNo() + "'";
//            queryWrapper.and(i -> i.inSql("ssyxdy",sqlSswg).or().inSql("hzzjhm",sqlSsKhjl));
            queryWrapper.and(i -> i.inSql("hzzjhm", sqlZjhm));
//            queryWrapper.and(i -> i.inSql("ssyxdy", sqlSswg));
        }

        queryWrapper.orderByDesc("lrsj");
        queryWrapper.orderByAsc("id");
        IPage<vKhglNhhzxxglQuery> pageList = vkhglNhhzxxglQueryService.page(page, queryWrapper);
        System.out.println("=============查询户主信息333=================" + DateUtil.currentDateTime());
        if (locationQuery) {

            queryWrapper.isNotNull("longitude");
            queryWrapper.isNotNull("latitude");
            long total = pageList.getTotal();
            pageList = vkhglNhhzxxglQueryService.page(page, queryWrapper);
            pageList.setSize(total);
        }

        Page<vKhglNhhzxxgl> khglNhhzxxglPageList = new Page<>(pageList.getCurrent(), pageList.getSize(), pageList.getTotal());
        List<vKhglNhhzxxglQuery> vKhglNhhzxxglQueryList = pageList.getRecords();
        List<String> ids = new ArrayList<>();
        if (vKhglNhhzxxglQueryList != null && !vKhglNhhzxxglQueryList.isEmpty()) {
            for (vKhglNhhzxxglQuery nhhzxxglQuery : vKhglNhhzxxglQueryList) {
                ids.add(nhhzxxglQuery.getId());
            }
        }
        if (ids.size() > 0) {
            List<vKhglNhhzxxgl> list = (List) vKhglNhhzxxgl12.listByIds(ids);
            khglNhhzxxglPageList.setRecords(list);
        }
        System.out.println("=============查询户主信息完成=================" + DateUtil.currentDateTime());
        log.info("查询户主信息完成");
        return Result.ok(khglNhhzxxglPageList);
    }

    /**
     * 分页列表查询
     *
     * @param khglNhhzxxglQuery
     * @param currentPage
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1-分页列表查询")
    @ApiOperation(value = "1-分页列表查询", notes = "1-分页列表查询")
    @GetMapping(value = "/hmdlist")
    public Result<?> queryHmdPageList(vKhglNhhzxxglQuery khglNhhzxxglQuery,
                                      @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      @RequestParam(name = "locationQuery", required = false, defaultValue = "false") Boolean locationQuery,
                                      String lxfs,
                                      String dz,
                                      String yssxz,
                                      String yxzc,
                                      String cldkkhType,
                                      HttpServletRequest req) {
        System.out.println("=============进入请求查询农户户主信息=================" + DateUtil.currentDateTime());
        log.info("进入请求查询农户户主信息");
        String hzxm = khglNhhzxxglQuery.getHzxm();
        String hzzjhm = khglNhhzxxglQuery.getHzzjhm();
        String ssyxdy = khglNhhzxxglQuery.getSsyxdy();
        khglNhhzxxglQuery.setHzxm(null);
        khglNhhzxxglQuery.setHzzjhm(null);
        khglNhhzxxglQuery.setSsyxdy(null);
        QueryWrapper<vKhglNhhzxxglQuery> queryWrapper = QueryGenerator.initQueryWrapper(khglNhhzxxglQuery, req.getParameterMap());

        System.out.println("=============查询户主信息111=================" + DateUtil.currentDateTime());
        log.info("查询户主信息");
        Page<vKhglNhhzxxglQuery> page = new Page<vKhglNhhzxxglQuery>(currentPage, pageSize);

        //查询网格时，同时查询下级网格的数据
        /*if (StringUtils.isNotEmpty(ssyxdy) && StringUtils.isBlank(cldkkhType)) {
            String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + ssyxdy + "' or parent_id='" + ssyxdy + "'";
            queryWrapper.and(i -> i.inSql("ssyxdy", sqlSswg));
        }*/
        //根据姓名或者证件号码查询户主信息
        khglNhhzxxglQuery.setHzxm(hzxm);
        khglNhhzxxglQuery.setHzzjhm(hzzjhm);
        List<String> hhbmList = vKhglNhhzxxgl12.selectHzByCy(lxfs, yssxz, yxzc, dz, khglNhhzxxglQuery, req);
        System.out.println("=============查询户主信息222=================" + DateUtil.currentDateTime());
        if (hhbmList != null && hhbmList.size() > 0) {
            if (!StringUtils.isEmpty(khglNhhzxxglQuery.getHzxm())) {
                queryWrapper.and(wrapper -> wrapper.like("hzxm", khglNhhzxxglQuery.getHzxm()).or().in("hhbm", hhbmList));
            }
            if (!StringUtils.isEmpty(khglNhhzxxglQuery.getHzzjhm())) {
                queryWrapper.and(wrapper -> wrapper.eq("hzzjhm", khglNhhzxxglQuery.getHzzjhm()).or().in("hhbm", hhbmList));
            }
            if (!StringUtils.isEmpty(lxfs) || !StringUtils.isEmpty(yssxz) || !StringUtils.isEmpty(yxzc) || !StringUtils.isEmpty(dz)) {
                queryWrapper.and(wrapper -> wrapper.in("hhbm", hhbmList));
            }
        } else {
            if (!StringUtils.isEmpty(khglNhhzxxglQuery.getHzxm())) {
                queryWrapper.like("hzxm", khglNhhzxxglQuery.getHzxm());
            }
            if (!StringUtils.isEmpty(khglNhhzxxglQuery.getHzzjhm())) {
                queryWrapper.eq("hzzjhm", khglNhhzxxglQuery.getHzzjhm());
            }
            if (!StringUtils.isEmpty(lxfs) || !StringUtils.isEmpty(yssxz) || !StringUtils.isEmpty(yxzc) || !StringUtils.isEmpty(dz)) {
                queryWrapper.eq("id", "-1");
            }
        }
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

    /*    if (!sysUser.getUsername().equals("admin") && StringUtils.isBlank(cldkkhType)) {
            //in  用 list有个数限制问题， 此处改为inSql
            String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + sysUser.getWorkNo() + "'";
            String sqlSsKhjl = "select  zjhm from KHXXGL_KHSSKHJL t where sskhjl =" + "'" + sysUser.getWorkNo() + "'";

//            queryWrapper.and(i -> i.inSql("ssyxdy",sqlSswg).or().inSql("hzzjhm",sqlSsKhjl));
            queryWrapper.and(i -> i.inSql("ssyxdy", sqlSswg));
        }*/
        //筛选黑名单数据
        String hmdzjhms = "select  zjhm from khxxgl_khxq_nh_hmd";
        queryWrapper.and(i -> i.inSql("hzzjhm", hmdzjhms));
        queryWrapper.orderByDesc("lrsj");
        queryWrapper.orderByAsc("id");
        IPage<vKhglNhhzxxglQuery> pageList = vkhglNhhzxxglQueryService.page(page, queryWrapper);
        System.out.println("=============查询户主信息333=================" + DateUtil.currentDateTime());
        if (locationQuery) {
            queryWrapper.isNotNull("longitude");
            queryWrapper.isNotNull("latitude");
            long total = pageList.getTotal();
            pageList = vkhglNhhzxxglQueryService.page(page, queryWrapper);
            pageList.setSize(total);
        }

        Page<vKhglNhhzxxgl> khglNhhzxxglPageList = new Page<>(pageList.getCurrent(), pageList.getSize(), pageList.getTotal());
        List<vKhglNhhzxxglQuery> vKhglNhhzxxglQueryList = pageList.getRecords();
        List<String> ids = new ArrayList<>();
        if (vKhglNhhzxxglQueryList != null && !vKhglNhhzxxglQueryList.isEmpty()) {
            for (vKhglNhhzxxglQuery nhhzxxglQuery : vKhglNhhzxxglQueryList) {
                ids.add(nhhzxxglQuery.getId());
            }
        }
        if (ids.size() > 0) {
            List<vKhglNhhzxxgl> list = (List) vKhglNhhzxxgl12.listByIds(ids);
            khglNhhzxxglPageList.setRecords(list);
        }
        System.out.println("=============查询户主信息完成=================" + DateUtil.currentDateTime());
        log.info("查询户主信息完成");
        return Result.ok(khglNhhzxxglPageList);
    }

    /**
     * 分页列表查询
     *
     * @param khglNhhzxxgl
     * @param pageSize
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1-分页列表查询")
    @ApiOperation(value = "1-分页列表查询", notes = "1-分页列表查询")
    @GetMapping(value = "/queryWdcjList")
    public Result<?> queryWdcjList(vKhglNhhzxxglQuery khglNhhzxxgl, String daysBegin, String daysEnd,
                                   @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<vKhglNhhzxxglQuery> queryWrapper = QueryGenerator.initQueryWrapper(khglNhhzxxgl, req.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<SysRole> userRoleCode = sysUserRoleService.getUserRoleCode(sysUser.getId());
        if (!"zhjhy".equals(userRoleCode.get(0).getRoleCode())) {
            queryWrapper.eq("lrr", sysUser.getUsername());
        } else {
            //祁阳 稽核员查询所有采集过的数据
            queryWrapper.eq("sfcj", "1");
        }
        if (StringUtils.isNotBlank(daysBegin) && StringUtils.isNotBlank(daysEnd)) {
            String begin = daysBegin.replace("-", "");
            String end = daysEnd.replace("-", "");
            queryWrapper.between("up_dt", begin, end);
        }
        queryWrapper.orderByDesc("up_dt");
        queryWrapper.orderByAsc("id");
        Page<vKhglNhhzxxglQuery> page = new Page<vKhglNhhzxxglQuery>(currentPage, pageSize);
        IPage<vKhglNhhzxxglQuery> pageList = vkhglNhhzxxglQueryService.page(page, queryWrapper);
        if (pageList.getRecords().size() <= 0) {
            if (!StringUtils.isEmpty(khglNhhzxxgl.getHzxm()) || !StringUtils.isEmpty(khglNhhzxxgl.getHzzjhm())) {
                //根据家庭成员查询户主信息
                vKhglNhhzxxglQuery vKhglNhhzxxglQuery = new vKhglNhhzxxglQuery();
                BeanUtils.copyProperties(khglNhhzxxgl, vKhglNhhzxxglQuery);
                List<String> hhbmList = vKhglNhhzxxgl12.selectHzByCy("", "", "", "", vKhglNhhzxxglQuery, req);
                QueryWrapper<vKhglNhhzxxgl> queryWrapper2 = new QueryWrapper<>();
                if (hhbmList.size() > 0) {
                    queryWrapper2.in("hhbm", hhbmList);
                } else {
                    queryWrapper2.isNull("hhbm");
                }
                if (!"zhjhy".equals(userRoleCode.get(0).getRoleCode())) {
                    queryWrapper2.eq("lrr", sysUser.getUsername());
                } else {
                    //祁阳 稽核员查询所有采集过的数据
                    queryWrapper2.eq("sfcj", "1");
                }
                queryWrapper2.orderByDesc("up_dt");
                queryWrapper.orderByAsc("id");
                //queryWrapper2.isNotNull("lrsj");
                Page<vKhglNhhzxxgl> page1 = new Page<vKhglNhhzxxgl>(currentPage, pageSize);
                IPage<vKhglNhhzxxgl> vKhglNhhzxxglIPage = vKhglNhhzxxgl12.page(page1, queryWrapper2);

                return Result.ok(vKhglNhhzxxglIPage);
            }
        }

        Page<vKhglNhhzxxgl> khglNhhzxxglPageList = new Page<>(pageList.getCurrent(), pageList.getSize(), pageList.getTotal());
        List<vKhglNhhzxxglQuery> vKhglNhhzxxglQueryList = pageList.getRecords();
        List<String> ids = new ArrayList<>();
        if (vKhglNhhzxxglQueryList != null && !vKhglNhhzxxglQueryList.isEmpty()) {
            for (vKhglNhhzxxglQuery nhhzxxglQuery : vKhglNhhzxxglQueryList) {
                ids.add(nhhzxxglQuery.getId());
            }
        }
        if (ids.size() > 0) {
            List<vKhglNhhzxxgl> list = (List) vKhglNhhzxxgl12.listByIds(ids);
            ;
            khglNhhzxxglPageList.setRecords(list);
        }
        return Result.ok(khglNhhzxxglPageList);
    }

    /**
     * 获取操作信息: 区域编码/区域名称/操作员真实姓名
     *
     * @param req
     * @return
     */
    @GetMapping(value = "/queryCzxxByAdd")
    public Result<?> queryCzxxByAdd(HttpServletRequest req) {
        try {
//            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//            String realname = loginUser.getRealname();
            List<Map<String, String>> list = new ArrayList<>();
            Map<Object, Object> map = areaUtil.getEjyxdyqx(req);
            Set<Map.Entry<Object, Object>> entries = map.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                Map<String, String> map1 = new HashMap<>();
                map1.put("qybm", entry.getKey().toString());
                map1.put("mc", entry.getValue().toString());
                map1.put("realname", getLoginUser().getRealname());
                list.add(map1);
            }
            return Result.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("操作失败！" + e.getMessage());
            return Result.error("操作失败，请联系系统管理员！");
        }
    }

    /**
     * 通过户号编码去花名册查询家庭信息
     *
     * @param hhbm 户号编码
     * @return
     */
    @GetMapping(value = "/queryHzxxByHhbm")
    public Result<?> queryHzxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            if (hhbm != null) {
                QueryWrapper<vKhglNhhzxxgl> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("hhbm", hhbm);
                List<vKhglNhhzxxgl> list = vKhglNhhzxxgl12.list(khhmcQueryWrapper);
                if (list != null && list.size() > 0) {
                    return Result.ok(list.get(0));
                } else {
                    return Result.ok("未找到数据！");
                }
            } else {
                return Result.error("未获取到户号编码！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("通过户号编码去花名册查询家庭信息失败！" + e.getMessage());
            return Result.error("查询家庭信息失败，请联系系统管理员！");
        }
    }

    /**
     * 通过户号编码去花名册查询授信对象证件号码
     *
     * @param hhbm 户号编码
     * @return
     */
    @GetMapping(value = "/querySxdxZjhm")
    public Result<?> querySxdxZjhm(@Param("hhbm") String hhbm) {
        try {
            JSONObject jsonObject = new JSONObject();
            QueryWrapper<KhglNhhzxxgl> khhmcQueryWrapper = new QueryWrapper<>();
            khhmcQueryWrapper.eq("hhbm", hhbm);
            KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(khhmcQueryWrapper, false);
            if (khglNhhzxxgl.getSxdxzjh() != null) {
                QueryWrapper<Nhxq> khhmcxxQueryWrapper = new QueryWrapper<>();
                khhmcxxQueryWrapper.eq("zjhm", khglNhhzxxgl.getSxdxzjh());
                Nhxq khhmcxx = nhxqService.getOne(khhmcxxQueryWrapper, false);
                if (khhmcxx != null) {
                    jsonObject.put("sxdxzjhmflage", khhmcxx.getZjhm());
                    jsonObject.put("sxdxid", khhmcxx.getId());
                    jsonObject.put("sxdx", khhmcxx.getKhmc());
                    return Result.ok(jsonObject);
                } else {
                    return Result.ok("未找到任何农户数据！");
                }
            } else {
                return Result.ok("未找到任何农户户主数据！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("通过户号编码查询授信对象信息失败！" + e);
            return Result.error("查询授信对象信息失败，请联系系统管理员！");
        }
    }

    /**
     * 查询住地邮编/手机号码(联系方式)
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryGrxxByZjhm")
    public Result<?> queryGrxxByZjhm(@Param("id") String id) {
        try {
            if (id != null) {
                QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("id", id);
                List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
                if (list != null && list.size() > 0) {
                    Nhxq khhmcxx = list.get(0);
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(khhmcxx).toString());
                    jsonObject.put("sszh_dictText", khhmcxx.getSszh() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khhmcxx.getSszh()));
                    jsonObject.put("wgbh_dictText", khhmcxx.getWgbh() == null ? "" : sysDictService.queryTableDictTextByKey("v_yxdygl_main", "wgmc_show", "wgbh", khhmcxx.getWgbh()));
                    String zdyb = tjfxCsszService.queryCszByCsbm("CS0012");
                    jsonObject.put("zdyb", khhmcxx.getZdyb() == null ? zdyb : khhmcxx.getZdyb());
                    if (StringUtils.isEmpty(khhmcxx.getSjhm())) {
                        QueryWrapper<Clkhgl> clkhglQueryWrapper = new QueryWrapper<>();
                        clkhglQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
                        Clkhgl clkhgl = clkhglService.getOne(clkhglQueryWrapper, false);
                        if (clkhgl != null) {
                            jsonObject.put("sjhm", clkhgl.getLxfs());
                        }
                    }
                    return Result.ok(jsonObject);
                } else {
                    return Result.ok("未找到任何数据！");
                }
            } else {
                return Result.error("请求参数[id]为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询住地邮编/手机号码(联系方式)失败！" + e.getMessage());
            return Result.error("查询住地邮编/手机号码(联系方式)失败，请联系系统管理员！");
        }
    }


    /**
     * 查询住地邮编/手机号码(联系方式)
     *
     * @return
     */
    @GetMapping(value = "/queryGrxxByZjxx")
    public Result<?> queryGrxxByZjxx(@Param("zjhm") String zjhm) {
        try {
            if (zjhm != null) {
                QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("zjhm", zjhm);
                List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
                if (list != null && list.size() > 0) {
                    Nhxq khhmcxx = list.get(0);
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(khhmcxx).toString());
                    jsonObject.put("sszh_dictText", khhmcxx.getSszh() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khhmcxx.getSszh()));
                    jsonObject.put("wgbh_dictText", khhmcxx.getWgbh() == null ? "" : sysDictService.queryTableDictTextByKey("v_yxdygl_main", "wgmc_show", "wgbh", khhmcxx.getWgbh()));
                    String zdyb = tjfxCsszService.queryCszByCsbm("CS0012");
                    jsonObject.put("zdyb", khhmcxx.getZdyb() == null ? zdyb : khhmcxx.getZdyb());
                    if (StringUtils.isEmpty(khhmcxx.getSjhm())) {
                        QueryWrapper<Clkhgl> clkhglQueryWrapper = new QueryWrapper<>();
                        clkhglQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
                        Clkhgl clkhgl = clkhglService.getOne(clkhglQueryWrapper, false);
                        if (clkhgl != null) {
                            jsonObject.put("sjhm", clkhgl.getLxfs());
                        }
                    }
                    return Result.ok(jsonObject);
                } else {
                    return Result.ok("未找到任何数据！");
                }
            } else {
                return Result.error("请求参数[id]为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询住地邮编/手机号码(联系方式)失败！" + e.getMessage());
            return Result.error("查询住地邮编/手机号码(联系方式)失败，请联系系统管理员！");
        }
    }

    /**
     * 根据证件号码获取农户采集信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryGrcjxxByZjhm")
    public Result<?> queryGrcjxxByZjhm(@Param("id") String id) {
        try {
            if (id != null) {
                QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("id", id);
                Nhxq khhmcxx = nhxqService.getOne(khhmcQueryWrapper);
                if (khhmcxx != null) {
                    QueryWrapper<CamsZcsxNhcjxxPad> nhcjxxWrapper = new QueryWrapper<>();
                    nhcjxxWrapper.eq("zjhm", khhmcxx.getZjhm());
                    List<CamsZcsxNhcjxxPad> list = camsZcsxNhcjxxPadService.list(nhcjxxWrapper);
                    if (list != null && list.size() > 0) {
                        CamsZcsxNhcjxxPad camsZcsxNhcjxxPad = list.get(0);
                        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(camsZcsxNhcjxxPad).toString());
                        return Result.ok(jsonObject);
                    } else {
                        return Result.ok("未找到任何农户采集信息！");
                    }
                } else {
                    return Result.ok("未找到任何数据！");
                }
            } else {
                return Result.error("请求参数[id]为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询农户采集信息失败！" + e.getMessage());
            return Result.error("查询农户采集信息失败，请联系系统管理员！");
        }
    }

    /**
     * 根据户号编码获取农户房产信息
     *
     * @param hhbm
     * @return
     */
    @GetMapping(value = "/queryFcxxByHhbm")
    public Result<?> queryFcxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            if (hhbm != null) {
                QueryWrapper<CamsZcsxNhfcxxPad> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("hhbm", hhbm);
                List<CamsZcsxNhfcxxPad> list = camsZcsxNhfcxxPad.list(khhmcQueryWrapper);
                if (list.size() > 0) {
                    return Result.ok(list);
                } else {
//                 List<Nhbkbpyfsxx> list1 =  khglNhhzxxglService.selectpyxx(hhbm);
//                   return Result.ok(list1);
                    return Result.ok("未找到农户房产信息！");
                }
            } else {
                return Result.error("请求参数[hhbm]为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询农户房产信息失败！" + e.getMessage());
            return Result.error("查询农户房产信息失败，请联系系统管理员！");
        }
    }

    /**
     * 获取三级营销单元名称
     *
     * @param ssyxdy
     * @return
     */
    @GetMapping(value = "/queryCzxxByYxdy")
    public Result<?> queryCzxxByYxdy(@Param("ssyxdy") String ssyxdy) {
        try {
            if (ssyxdy != null) {
                //二级营销单元
                String s = sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl", "yjyxdymc||dymc", "dybh", ssyxdy);
                if (StringUtils.isEmpty(s)) {
                    //三级营销单元
                    s = sysDictService.queryTableDictTextByKey("v_yxdygl_sjyxdygl", "yjyxdymc||ejyxdymc||dymc", "dybh", ssyxdy);
                }
                return Result.ok(s);
            } else {
                return Result.ok("未找到任何数据！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取三级营销单元名称失败！" + e.getMessage());
            return Result.error("获取三级营销单元名称失败，请联系系统管理员！");
        }
    }

    /**
     * 获取评级授信资产情况
     *
     * @param hhbm 户号编码
     * @return
     */
    @RequestMapping(value = "/queryPjsxZcTable", method = RequestMethod.GET)
    public Result<?> queryPjsxZcTable(@Param("hhbm") String hhbm) {
        try {
            //LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
            JSONArray jsonArray = new JSONArray();
            QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("hhbm", hhbm);
            List<CamsZcsxNhpjsxxxPad> nhPjsxxxList = iCamsZcsxNhpjsxxxPadService.list(queryWrapper);
            if (nhPjsxxxList == null || nhPjsxxxList.size() <= 0) {
                return Result.ok("没有数据");
            } else {
                for (CamsZcsxNhpjsxxxPad pjsxxx : nhPjsxxxList) {
                    //资产情况
                    JSONObject jo = new JSONObject();
                    jo.put("zclx", "股权");
                    jo.put("zcsl", pjsxxx.getGqsl());
                    jo.put("zcjz", pjsxxx.getGqjz());
                    jo.put("zcsm", pjsxxx.getGqxqsm());
                    JSONObject jo1 = new JSONObject();
                    jo1.put("zclx", "农机具");
                    jo1.put("zcsl", pjsxxx.getNjjsl());
                    jo1.put("zcjz", pjsxxx.getNjjjz());
                    jo1.put("zcsm", pjsxxx.getNjjqxsm());
                    JSONObject jo2 = new JSONObject();
                    jo2.put("zclx", "家用电器");
                    jo2.put("zcsl", pjsxxx.getJydqsl());
                    jo2.put("zcjz", pjsxxx.getJydqjz());
                    jo2.put("zcsm", pjsxxx.getJydqxqsm());
                    JSONObject jo3 = new JSONObject();
                    jo3.put("zclx", "交通工具");
                    jo3.put("zcsl", pjsxxx.getJtgjsl());
                    jo3.put("zcjz", pjsxxx.getJtgjjz());
                    jo3.put("zcsm", pjsxxx.getJtgjxqsm());
                    JSONObject jo5 = new JSONObject();
                    jo5.put("zclx", "种植成品");
                    jo5.put("zcsl", pjsxxx.getZzcpsl());
                    jo5.put("zcjz", pjsxxx.getZzcpjz());
                    jo5.put("zcsm", pjsxxx.getZzcpxqsm());
                    JSONObject jo6 = new JSONObject();
                    jo6.put("zclx", "养殖成品");
                    jo6.put("zcsl", pjsxxx.getYzcpsl());
                    jo6.put("zcjz", pjsxxx.getYzcpjz());
                    jo6.put("zcsm", pjsxxx.getYzcpxqsm());
                    JSONObject jo4 = new JSONObject();
                    jo4.put("zclx", "其他资产");
                    jo4.put("zcsl", pjsxxx.getQtzcsl());
                    jo4.put("zcjz", pjsxxx.getQtzcjz());
                    jo4.put("zcsm", pjsxxx.getQtzcxqsm());
                    jsonArray.add(jo);
                    jsonArray.add(jo1);
                    jsonArray.add(jo2);
                    jsonArray.add(jo3);
                    if ("415".equalsIgnoreCase(qybm)) {
                        jsonArray.add(jo5);
                        jsonArray.add(jo6);
                    }
                    jsonArray.add(jo4);
                }
                return Result.ok(jsonArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取评级授信资产情况失败！" + e.getMessage());
            return Result.error("获取评级授信资产情况失败，请联系系统管理员！");
        }
    }

    /**
     * 获取评级授信负债情况
     *
     * @param hhbm 户号编码
     * @return
     */
    @RequestMapping(value = "/queryPjsxFzTable", method = RequestMethod.GET)
    public Result<?> queryPjsxFzTable(@Param("hhbm") String hhbm) {
        try {
            JSONArray jsonArray = new JSONArray();
            QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("hhbm", hhbm);
            List<CamsZcsxNhpjsxxxPad> nhPjsxxxList = iCamsZcsxNhpjsxxxPadService.list(queryWrapper);
            if (nhPjsxxxList == null || nhPjsxxxList.size() <= 0) {
                return Result.ok("没有数据");
            } else {
                String qydm=getRedisQydm();
                for (CamsZcsxNhpjsxxxPad pjsxxx : nhPjsxxxList) {
                    JSONObject jo1 = new JSONObject();
                    jo1.put("jkfs", "本系统");
                    jo1.put("zqr", pjsxxx.getBxtjkzqr());
                    jo1.put("jkje", pjsxxx.getBxtjksl());
                    jo1.put("jksm", pjsxxx.getBxtjkxqsm());
                    JSONObject jo2 = new JSONObject();
                    jo2.put("jkfs", "他行");
                    jo2.put("zqr", pjsxxx.getThjkzqr());
                    jo2.put("jkje", pjsxxx.getThjksl());
                    jo2.put("jksm", pjsxxx.getThjkxqsm());
                    JSONObject jo3 = new JSONObject();
                    jo3.put("jkfs", "信用卡");
                    jo3.put("zqr", pjsxxx.getXykzqr());
                    jo3.put("jkje", pjsxxx.getXyksl());
                    jo3.put("jksm", pjsxxx.getXykxqsm());
                    JSONObject jo4 = new JSONObject();
                    jo4.put("jkfs", "其他");
                    jo4.put("zqr", pjsxxx.getQtfzzqr());
                    jo4.put("jkje", pjsxxx.getQtfzsl());
                    jo4.put("jksm", pjsxxx.getQtfzxqsm());
                    jsonArray.add(jo1);
                    jsonArray.add(jo2);
                    jsonArray.add(jo3);
                    //祁阳多加借款方式
                    if (StringUtils.isNotBlank(qydm) && QydmEnums.QIYANG.getQydmCode().equals(qydm)){
                        JSONObject jo5 = new JSONObject();
                        jo5.put("jkfs", "民间借款");
                        jo5.put("zqr", pjsxxx.getMjjkzqr());
                        jo5.put("jkje", pjsxxx.getMjjkje());
                        jo5.put("jksm", pjsxxx.getMjjksm());
                        JSONObject jo6 = new JSONObject();
                        jo6.put("jkfs", "生活消费支出");
                        jo6.put("zqr", pjsxxx.getShxfzczqr());
                        jo6.put("jkje", pjsxxx.getShxfzcjkje());
                        jo6.put("jksm", pjsxxx.getShxfzcsm());
                        JSONObject jo7 = new JSONObject();
                        jo7.put("jkfs", "子女教育支出");
                        jo7.put("zqr", pjsxxx.getZnjyzqr());
                        jo7.put("jkje", pjsxxx.getZnjyjkje());
                        jo7.put("jksm", pjsxxx.getZnjysm());
                        JSONObject jo8 = new JSONObject();
                        jo8.put("jkfs", "家庭重大支出");
                        jo8.put("zqr", pjsxxx.getJtzdzczqr());
                        jo8.put("jkje", pjsxxx.getJtzdzcjkje());
                        jo8.put("jksm", pjsxxx.getJtzdzcsm());
                        jsonArray.add(jo5);
                        jsonArray.add(jo6);
                        jsonArray.add(jo7);
                        jsonArray.add(jo8);
                    }
                    jsonArray.add(jo4);
                }
                return Result.ok(jsonArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取获取评级授信负债情况失败！" + e.getMessage());
            return Result.error("获取获取评级授信负债情况失败，请联系系统管理员！");
        }
    }

    /**
     * 获取评级授信经营情况
     *
     * @param hhbm
     * @return
     */
    @RequestMapping(value = "/queryPjsxJyqkTable", method = RequestMethod.GET)
    public Result<?> queryPjsxJyqkTable(@Param("hhbm") String hhbm) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
            JSONArray jsonArray = new JSONArray();
            QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("hhbm", hhbm);
            List<CamsZcsxNhpjsxxxPad> nhPjsxxxList = iCamsZcsxNhpjsxxxPadService.list(queryWrapper);
            if (nhPjsxxxList == null || nhPjsxxxList.size() <= 0) {
                return Result.ok("没有数据");
            } else {
                for (CamsZcsxNhpjsxxxPad pjsxxx : nhPjsxxxList) {
                    //资产情况
                    JSONObject jo1 = new JSONObject();
                    jo1.put("xmlx", "种植");
                    jo1.put("xmqk", pjsxxx.getZzxmqk());
                    jo1.put("xmnsr", pjsxxx.getZzxmsr());
                    jo1.put("xmnzc", pjsxxx.getZzxmzc());
                    JSONObject jo2 = new JSONObject();
                    jo2.put("xmlx", "养殖");
                    jo2.put("xmqk", pjsxxx.getYzxmqk());
                    jo2.put("xmnsr", pjsxxx.getYzxmsr());
                    jo2.put("xmnzc", pjsxxx.getYzxmzc());
                    JSONObject jo3 = new JSONObject();
                    jo3.put("xmlx", "商业");
                    jo3.put("xmqk", pjsxxx.getSyxmqk());
                    jo3.put("xmnsr", pjsxxx.getSyxmsr());
                    jo3.put("xmnzc", pjsxxx.getSyxmzc());
                    JSONObject jo4 = new JSONObject();
                    jo4.put("xmlx", "劳务");
                    jo4.put("xmnsr", pjsxxx.getNwxmsr());
                    jo4.put("xmqk", pjsxxx.getNwxmqk());
                    jo4.put("xmnzc", pjsxxx.getNwxmzc());
                    JSONObject jo6 = new JSONObject();
                    jo6.put("xmlx", "租金");
                    jo6.put("xmnsr", pjsxxx.getZjxmsr());
                    jo6.put("xmqk", pjsxxx.getZjxmqk());
                    jo6.put("xmnzc", pjsxxx.getZjxmzc());
                    JSONObject jo7 = new JSONObject();
                    jo7.put("xmlx", "国家补贴");
                    jo7.put("xmnsr", pjsxxx.getGjbtxmsr());
                    jo7.put("xmqk", pjsxxx.getGjbtxmqk());
                    jo7.put("xmnzc", pjsxxx.getGjbtxmzc());
                    JSONObject jo5 = new JSONObject();
                    jo5.put("xmlx", "其他");
                    jo5.put("xmnsr", pjsxxx.getQtxmsr());
                    jo5.put("xmqk", pjsxxx.getQtxmqk());
                    jo5.put("xmnzc", pjsxxx.getQtxmzc());

                    JSONObject jo8 = new JSONObject();
                    jo8.put("xmlx", "工资性收入");
                    jo8.put("xmnsr", pjsxxx.getGzxxmsr());
                    jo8.put("xmqk", pjsxxx.getGzxxmqk());
                    jo8.put("xmnzc", pjsxxx.getGzxxmzc());
                    JSONObject jo9 = new JSONObject();
                    jo9.put("xmlx", "住房公积金年个人交纳额");
                    jo9.put("xmnsr", pjsxxx.getZfgjjxmsr());
                    jo9.put("xmqk", pjsxxx.getZfgjjxmqk());
                    jo9.put("xmnzc", pjsxxx.getZfgjjxmzc());

                    jsonArray.add(jo1);
                    jsonArray.add(jo2);
                    jsonArray.add(jo3);
                    jsonArray.add(jo4);
                    if ("415".equalsIgnoreCase(qybm)) {
                        jsonArray.add(jo6);
                        jsonArray.add(jo7);
                    }
                    if (QydmEnums.QIYANG.getQydmCode().equals(qybm)){
                        jsonArray.add(jo8);
                        jsonArray.add(jo9);
                    }
                    jsonArray.add(jo5);
                }
                return Result.ok(jsonArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取评级授信经营情况失败！" + e.getMessage());
            return Result.error("获取评级授信经营情况失败，请联系系统管理员！");
        }
    }


    /**
     * 通过证件号码去花名册查询家庭成员信息
     *
     * @param zjhm
     * @param khmc
     * @return
     */
    @GetMapping(value = "/queryJtcyxxByZjhm")
    public Result<?> queryJtcyxxByZjhm(@Param("zjhm") String zjhm, String khmc) {
        try {
            if (StringUtils.isEmpty(zjhm) && StringUtils.isEmpty(khmc)) {
                return Result.ok(false);
            }
            QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotEmpty(zjhm)) {
                khhmcQueryWrapper.eq("zjhm", zjhm);
            }
            if (StringUtils.isNotEmpty(khmc)) {
                khhmcQueryWrapper.eq("khmc", khmc);
            }
            List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
            if (getRedisQydm().equals(QydmEnums.TIANYI.getQydmCode())){
                QueryWrapper<Nhycxq> nhycxqQueryWrapper=new QueryWrapper<>();
                if (StringUtils.isNotEmpty(zjhm)) {
                    nhycxqQueryWrapper.eq("zjhm", zjhm);
                }
                if (StringUtils.isNotEmpty(khmc)) {
                    nhycxqQueryWrapper.eq("khmc", khmc);
                }
                List<Nhycxq> nhycList=nhycxqService.list(nhycxqQueryWrapper);
                nhycList.forEach(e->{
                    Nhxq nhxq=new Nhxq();
                    BeanUtils.copyProperties(e,nhxq);
                    nhxq.setSfycnh("1");
                    list.add(nhxq);
                });
            }
            if (list != null && list.size() > 0) {
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    Nhxq nhxqxx = list.get(i);
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(nhxqxx).toString());
                    String xb = jsonObject.getString("xb");
                    jsonObject.put("xb_dictText", xb == null ? "" : sysDictService.queryDictTextByKey("sex", xb));
                    String yhzgx = jsonObject.getString("yhzgx");
                    jsonObject.put("yhzgx_dictText", yhzgx == null ? "" : sysDictService.queryDictTextByKey("yhzgx", yhzgx));
                    String hhbm = nhxqxx.getHhbm();
                    //判断是否授信
                    QueryWrapper<CamsZcsxNhpjsxxxPad> nhpjsxxxQueryWrapper = new QueryWrapper<>();
                    nhpjsxxxQueryWrapper.eq("hhbm", hhbm);
                    nhpjsxxxQueryWrapper.isNotNull("nhzzsxed");
                    CamsZcsxNhpjsxxxPad nhpjsxxx = iCamsZcsxNhpjsxxxPadService.getOne(nhpjsxxxQueryWrapper,false);
                    if (nhpjsxxx != null) {
                        if (list.size() == 1) {
                            return Result.error("待添加客户所在户已评级授信，不能进行此操作");
                        } else {
                            jsonObject.put("errorInfo", "已评级授信");
                        }
                    }

                    //判断是否户主
                    String sfhz = nhxqxx.getSfhz() == null ? nhxqxx.getYhzgx() : nhxqxx.getSfhz();
                    if ("1".equalsIgnoreCase(sfhz)) {
                        //如果是户主，查询有没有家庭成员
                        QueryWrapper<Nhxq> jtcyQueryWrapper = new QueryWrapper<>();
                        jtcyQueryWrapper.eq("hhbm", hhbm);
                        jtcyQueryWrapper.eq("sfhz", "2");
                        List<Nhxq> jtcyList = nhxqService.list(jtcyQueryWrapper);
                        if (jtcyList != null && jtcyList.size() > 0) {
                            //有家庭成员，将成员列表返回到界面，用户需要选择新的户主与调整其他成员的与户主关系
                            JSONArray jtcyArray = JSONArray.parseArray(JSONArray.toJSONString(jtcyList));
                            for (int j = 0; j < jtcyArray.size(); j++) {
                                JSONObject jtcy = jtcyArray.getJSONObject(j);
                                xb = jtcy.getString("xb");
                                jtcy.put("xb_dictText", xb == null ? "" : sysDictService.queryDictTextByKey("sex", xb));
                                yhzgx = jtcy.getString("yhzgx");
                                jtcy.put("yhzgx_dictText", yhzgx == null ? "" : sysDictService.queryDictTextByKey("yhzgx", yhzgx));
                            }
                            jsonObject.put("jtcyList", jtcyArray);
                        }
                    }

                    if (!StringUtils.isEmpty(nhxqxx.getWgbh())) {
                        String s = sysDictService.queryTableDictTextByKey("v_yxdygl_main", "wgmc_show", "wgbh", nhxqxx.getWgbh());
                        jsonObject.put("ssyxdy_dictText", s);
                    }

                    //查询户主名称
                    QueryWrapper<KhglNhhzxxgl> hzxx = new QueryWrapper<>();
                    hzxx.eq("hhbm", nhxqxx.getHhbm());
                    List<KhglNhhzxxgl> hzxxList = khglNhhzxxglService.list(hzxx);
                    if (hzxxList != null && hzxxList.size() > 0) {
                        KhglNhhzxxgl nhhzxxgl = hzxxList.get(0);
                        jsonObject.put("hzzjhm", nhhzxxgl.getHzzjhm());
                        jsonObject.put("hzxm", nhhzxxgl.getHzxm());
                    }
                    jsonArray.add(jsonObject);
                }
                return Result.ok(jsonArray);
            }
            return Result.ok(false);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取家庭成员信息失败！" + e.getMessage());
            return Result.error("获取家庭成员信息失败，请联系系统管理员！");
        }
    }

    /**
     * 添加家庭成员保存
     *
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/editHhbmByZjhmAndHhbm")
    public Result<?> editHhbmByZjhmAndHhbm(@RequestBody JSONObject jsonObject) {
        String hhbm = jsonObject.getString("hhbm");
        String yhzgx = jsonObject.getString("yhzgx");
        String id = jsonObject.getString("id");
        String hhbm_old = jsonObject.getString("hhbm_old");
        //待添加成员是否为户主
        String sfhz = jsonObject.getString("sfhz");
        if ("1".equalsIgnoreCase(sfhz)) {
            //待添加成员原先家庭中是否有家庭成员
            JSONArray jtcyList = jsonObject.getJSONArray("jtcyList");
            if (jtcyList != null && jtcyList.size() > 0) {
                //有家庭成员
                //更新家庭成员的与户主关系、是否户主
                String jtcyHzid = "";
                for (int i = 0; i < jtcyList.size(); i++) {
                    JSONObject jtcy = jtcyList.getJSONObject(i);
                    String jtcyYhzgx = jtcy.getString("xyhzgx");
                    String jtcySfhz = "2";

                    if ("1".equalsIgnoreCase(jtcyYhzgx)) {
                        jtcySfhz = "1";
                        jtcyHzid = jtcy.getString("id");
                    }
                    Nhxq khglKhhmcxx = new Nhxq();
                    khglKhhmcxx.setYhzgx(jtcyYhzgx);
                    khglKhhmcxx.setSfhz(jtcySfhz);

                    UpdateWrapper<Nhxq> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("id", jtcy.getString("id"));
                    nhxqService.update(khglKhhmcxx, updateWrapper);
                }
                //更新户主表中的户主信息
                QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", jtcyHzid);
                Nhxq khhmcxx = nhxqService.getOne(queryWrapper);

                KhglNhhzxxgl nhhzxxgl = new KhglNhhzxxgl();
                QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
                nhhzxxglQueryWrapper.eq("hhbm", hhbm_old);
                KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(nhhzxxglQueryWrapper);
                if (!StringUtils.isEmpty(khglNhhzxxgl.getSxdxzjh()) && khglNhhzxxgl.getSxdxzjh().equalsIgnoreCase(khglNhhzxxgl.getHzzjhm())) {
                    //如果授信对象是户主
                    nhhzxxgl.setSxdxzjh(khhmcxx.getZjhm());
                    nhhzxxgl.setSxdx(khhmcxx.getKhmc());
                }


                nhhzxxgl.setHzxm(khhmcxx.getKhmc());
                nhhzxxgl.setHzzjhm(khhmcxx.getZjhm());
                UpdateWrapper<KhglNhhzxxgl> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("hhbm", hhbm_old);
                khglNhhzxxglService.update(nhhzxxgl, updateWrapper);
            } else {
                //没有家庭成员，
                //更新待添加成员花名册中的户号编码、与户主关系、是否户主
                //删除户主信息表中的记录
                QueryWrapper<KhglNhhzxxgl> deleteWrapper = new QueryWrapper<>();
                deleteWrapper.eq("hhbm", hhbm_old);
                khglNhhzxxglService.remove(deleteWrapper);
            }
        }
        if (QydmEnums.TIANYI.getQydmCode().equals(getRedisQydm())){
            Nhxq khglKhhmcxx = new Nhxq();
            QueryWrapper<Nhycxq> nhycxqQueryWrapper=new QueryWrapper<>();
            nhycxqQueryWrapper.eq("id",id);
            Nhycxq nhycxq=nhycxqService.getOne(nhycxqQueryWrapper,false);
            if (nhycxq != null) {
                //添加的是被移除的成员
                BeanUtils.copyProperties(nhycxq,khglKhhmcxx);
            }

            QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
            nhhzxxglQueryWrapper.eq("hhbm", hhbm);
            KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(nhhzxxglQueryWrapper);
            Nhxq nhxq1 = nhxqService.getByZjhm(khglNhhzxxgl.getHzzjhm());
            if (khglNhhzxxgl != null) {
                khglKhhmcxx.setWgbh(StringUtils.isNotBlank(khglNhhzxxgl.getSsyxdy()) ? khglNhhzxxgl.getSsyxdy() : null);
                khglKhhmcxx.setJgdm(nhxq1 != null && StringUtils.isNotBlank(nhxq1.getJgdm()) ? nhxq1.getJgdm() : null);
                khglKhhmcxx.setSszh(StringUtils.isNotBlank(khglNhhzxxgl.getSszh()) ? khglNhhzxxgl.getSszh() : null);
            }
            khglKhhmcxx.setHhbm(hhbm);
            khglKhhmcxx.setYhzgx(yhzgx);
            khglKhhmcxx.setSfhz("2");
            khglKhhmcxx.setUpdateTime(new Date());
            khglKhhmcxx.setUpdateBy(getUsername());
            try {
                if (nhycxq != null) {
                    nhxqService.save(khglKhhmcxx);
                    nhycxqService.removeById(id);
                } else {
                    UpdateWrapper<Nhxq> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("id", id);
                    nhxqService.update(khglKhhmcxx, updateWrapper);
                }
            } catch (Exception e) {
                log.error("添加成员失败", e);
                return Result.error("系统错误，添加成员失败！");
            }
            return Result.ok("修改成功");
        }else {
            Nhxq khglKhhmcxx = new Nhxq();
            khglKhhmcxx.setHhbm(hhbm);
            khglKhhmcxx.setYhzgx(yhzgx);
            khglKhhmcxx.setSfhz("2");
            UpdateWrapper<Nhxq> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

            try {
                khglKhhmcxx.setUpdateTime(new Date());
                khglKhhmcxx.setUpdateBy(sysUser.getUsername());
                nhxqService.update(khglKhhmcxx, updateWrapper);

            } catch (Exception e) {
                log.error("添加成员失败", e);
                return Result.error("系统错误，添加成员失败！");
            }
            return Result.ok("修改成功");
        }
    }

    @GetMapping(value = "/queryPjsxxxByHhbm")
    public Result<?> queryPjsxxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            if (hhbm != null) {
                CamsZcsxNhpjsxxxPad cms = new CamsZcsxNhpjsxxxPad();
                QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("hhbm", hhbm);
                List<CamsZcsxNhpjsxxxPad> list = iCamsZcsxNhpjsxxxPadService.list(queryWrapper);
                if (list.size() > 0) {
                    return Result.ok(list.get(0));
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有找到数据");
    }

    @GetMapping(value = "/DeleterJtxxByHhbmAndZjhm")
    public Result<?> DeleterJtxxByHhbmAndZjhm(@Param("id") String id,@Param("ycyy") String ycyy) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Nhxq khmcxxQuery = nhxqService.getOne(queryWrapper);

        try {
            //判断是否授信
            String hhbm = khmcxxQuery.getHhbm();
            String sfhz = khmcxxQuery.getSfhz();
            QueryWrapper<CamsZcsxNhpjsxxxPad> nhpjsxxxQueryWrapper = new QueryWrapper<>();
            nhpjsxxxQueryWrapper.eq("hhbm", hhbm);
            nhpjsxxxQueryWrapper.isNotNull("nhzzsxed");
            CamsZcsxNhpjsxxxPad nhpjsxxx = iCamsZcsxNhpjsxxxPadService.getOne(nhpjsxxxQueryWrapper);
            if (nhpjsxxx != null) {
                return Result.error("当前户已评级授信，不能进行此操作");
            }
            //判断是否有家庭成员
            QueryWrapper<Nhxq> jtcyQueryWrapper = new QueryWrapper<>();
            jtcyQueryWrapper.eq("hhbm", hhbm);

            List<Nhxq> jtcyList = nhxqService.list(jtcyQueryWrapper);
            if (jtcyList == null && jtcyList.size() == 0) {
                return Result.error("当前户不存在其他成员，无需进行此操作");
            } else {
                if (getRedisQydm().equals(QydmEnums.TIANYI.getQydmCode())) {
                    //天易对移除人员单独处理:将移除的农户放入到农户移除表中
                    Nhycxq nhycxq=new Nhycxq();
                    BeanUtils.copyProperties(khmcxxQuery,nhycxq);
                    nhycxqService.save(nhycxq);

                    //删除农户表数据
                    nhxqService.removeById(id);

                    //记录移除操作
                    JtcyYcLog jtcyYcLog=new JtcyYcLog();
                    jtcyYcLog.setSfzh(khmcxxQuery.getZjhm());
                    jtcyYcLog.setXm(khmcxxQuery.getKhmc());
                    jtcyYcLog.setYyhzgx(khmcxxQuery.getYhzgx());
                    jtcyYcLog.setYhhbm(khmcxxQuery.getHhbm());
                    jtcyYcLog.setYcyy(ycyy);
                    jtcyYcLog.setYcrq(DateUtil.getDayBegin(new Date()));
                    jtcyYcLogService.save(jtcyYcLog);
                } else {
                //更新户号编码、与户主关系、户主表中增加记录
                //查询原户主信息
                QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
                nhhzxxglQueryWrapper.eq("hhbm", hhbm);
                KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(nhhzxxglQueryWrapper);


                String newHhbm = khmcxxQuery.getWgbh() + sysDictService.queryhhbm("seq_hhbm.nextval");
                UpdateWrapper<Nhxq> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", id);
                Nhxq khhmcxx = new Nhxq();
                khhmcxx.setHhbm(newHhbm);
                khhmcxx.setYhzgx("1");
                khhmcxx.setSfhz("1");
                khhmcxx.setUpdateTime(new Date());
                khhmcxx.setUpdateBy(sysUser.getUsername());
                nhxqService.update(khhmcxx, updateWrapper);
                KhglNhhzxxgl nhhzxxgl = new KhglNhhzxxgl();
                nhhzxxgl.setSszh(khglNhhzxxgl.getSszh());
                nhhzxxgl.setSsxz(khglNhhzxxgl.getSsxz());
                nhhzxxgl.setXzc(khglNhhzxxgl.getXzc());
                nhhzxxgl.setSsyxdy(khglNhhzxxgl.getSsyxdy());
                nhhzxxgl.setZkhjl(khglNhhzxxgl.getZkhjl());
                nhhzxxgl.setHhbm(newHhbm);
                nhhzxxgl.setHzxm(khmcxxQuery.getKhmc());
                nhhzxxgl.setHzzjhm(khmcxxQuery.getZjhm());
                nhhzxxgl.setKhlx(khglNhhzxxgl.getKhlx());
                nhhzxxgl.setLrsj(new Date());
                nhhzxxgl.setLrr(sysUser.getUsername());
                khglNhhzxxglService.save(nhhzxxgl);

                JtcyYcLog jtcyYcLog=new JtcyYcLog();
                jtcyYcLog.setSfzh(khmcxxQuery.getZjhm());
                jtcyYcLog.setXm(khmcxxQuery.getKhmc());
                jtcyYcLog.setYyhzgx(khmcxxQuery.getYhzgx());
                jtcyYcLog.setYhhbm(khmcxxQuery.getHhbm());
                jtcyYcLog.setYcyy(ycyy);
                jtcyYcLog.setYcrq(DateUtil.getDayBegin(new Date()));
                jtcyYcLog.setXhhbm(newHhbm);
                jtcyYcLogService.save(jtcyYcLog);

                khglNhhzxxglService.updateKhlx(hhbm,newHhbm);
                }
            }
        } catch (Exception e) {
            log.error("移除家庭成员失败", e);
            return Result.error("系统错误，移除家庭成员失败！");
        }
        return Result.ok("删除成功");
    }
    @GetMapping(value = "/DeleterJtxxByHhbmAndZjhmQY")
    public Result<?> DeleterJtxxByHhbmAndZjhmQY(@Param("id") String id,@Param("ycyy") String ycyy) {
        String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());

        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Nhxq khmcxxQuery = nhxqService.getOne(queryWrapper);

        try {
            //判断是否授信
            String hhbm = khmcxxQuery.getHhbm();
            String sfhz = khmcxxQuery.getSfhz();
            QueryWrapper<CamsZcsxNhpjsxxxPad> nhpjsxxxQueryWrapper = new QueryWrapper<>();
            nhpjsxxxQueryWrapper.eq("hhbm", hhbm);
            nhpjsxxxQueryWrapper.isNotNull("nhzzsxed");
            CamsZcsxNhpjsxxxPad nhpjsxxx = iCamsZcsxNhpjsxxxPadService.getOne(nhpjsxxxQueryWrapper);
            if (nhpjsxxx != null) {
                return Result.error("当前户已评级授信，不能进行此操作");
            }
            //判断是否有家庭成员
            QueryWrapper<Nhxq> jtcyQueryWrapper = new QueryWrapper<>();
            jtcyQueryWrapper.eq("hhbm", hhbm);

            List<Nhxq> jtcyList = nhxqService.list(jtcyQueryWrapper);
            if (jtcyList == null && jtcyList.size() == 0) {
                return Result.error("当前户不存在其他成员，无需进行此操作");
            } else {
                //更新户号编码、与户主关系、户主表中增加记录
                //查询原户主信息
                QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
                nhhzxxglQueryWrapper.eq("hhbm", hhbm);
                KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(nhhzxxglQueryWrapper);


                String newHhbm = khmcxxQuery.getWgbh() + sysDictService.queryhhbm("seq_hhbm.nextval");
                UpdateWrapper<Nhxq> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", id);
                Nhxq khhmcxx = new Nhxq();
                khhmcxx.setHhbm(newHhbm);
                khhmcxx.setYhzgx("1");
                khhmcxx.setSfhz("1");
                khhmcxx.setUpdateTime(new Date());
                khhmcxx.setUpdateBy(sysUser.getUsername());
                nhxqService.update(khhmcxx, updateWrapper);
                KhglNhhzxxgl nhhzxxgl = new KhglNhhzxxgl();
                nhhzxxgl.setSszh(khglNhhzxxgl.getSszh());
                nhhzxxgl.setSsxz(khglNhhzxxgl.getSsxz());
                nhhzxxgl.setXzc(khglNhhzxxgl.getXzc());
                nhhzxxgl.setSsyxdy(khglNhhzxxgl.getSsyxdy());
                nhhzxxgl.setZkhjl(khglNhhzxxgl.getZkhjl());
                nhhzxxgl.setHhbm(newHhbm);
                nhhzxxgl.setHzxm(khmcxxQuery.getKhmc());
                nhhzxxgl.setHzzjhm(khmcxxQuery.getZjhm());
                nhhzxxgl.setKhlx(khglNhhzxxgl.getKhlx());
                nhhzxxgl.setLrsj(new Date());
                nhhzxxgl.setLrr(sysUser.getUsername());
                khglNhhzxxglService.save(nhhzxxgl);
                JtcyYcLog jtcyYcLog=new JtcyYcLog();
                jtcyYcLog.setSfzh(khmcxxQuery.getZjhm());
                jtcyYcLog.setXm(khmcxxQuery.getKhmc());
                jtcyYcLog.setYyhzgx(khmcxxQuery.getYhzgx());
                jtcyYcLog.setYhhbm(khmcxxQuery.getHhbm());
                jtcyYcLog.setYcyy(ycyy);
                jtcyYcLog.setYcrq(DateUtil.getDayBegin(new Date()));
                jtcyYcLog.setXhhbm(newHhbm);
                jtcyYcLogService.save(jtcyYcLog);
                khglNhhzxxglService.updateKhlx(hhbm,newHhbm);
            }
        } catch (Exception e) {
            log.error("移除家庭成员失败", e);
            return Result.error("系统错误，移除家庭成员失败！");
        }
        return Result.ok("删除成功");
    }

    @RequestMapping(value = "/EditJtxx", method = RequestMethod.POST)
    public Result<?> EditJtxx(@RequestBody KhglNhhzxxglPage page) {
        try {
            QueryWrapper<KhglNhhzxxgl> getTlsj = new QueryWrapper<>();
            getTlsj.eq("hhbm", page.getHhbm());
            Integer tlsj = khglNhhzxxglService.getOne(getTlsj).getTlsj();
            if (tlsj == null) {
                tlsj = 0;
            }
            KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
            QueryWrapper<Nhxq> hzgl = new QueryWrapper<>();
            if (!StringUtils.isEmpty(page.getSxdxzjh())) {
                hzgl.eq("id", page.getSxdxzjh());
                Nhxq list1 = nhxqService.getOne(hzgl);
                if (list1 != null) {
                    page.setSxdx(list1.getKhmc());
                    page.setSxdxzjh(list1.getZjhm());
                }
            }
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            BeanUtils.copyProperties(page, khglNhhzxxgl);
//            khglNhhzxxgl.setLrr(sysUser.getUsername());
//            khglNhhzxxgl.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
//            khglNhhzxxgl.setUpTm(DateUtil.formatDateTime("HHmmss"));
            if (page.getTlsj() != null) {
                khglNhhzxxgl.setTlsj(tlsj + page.getTlsj());
            }
            UpdateWrapper<KhglNhhzxxgl> khglNhhzxxglUpdateWrapper = new UpdateWrapper<>();
            khglNhhzxxglUpdateWrapper.eq("hhbm", page.getHhbm());
            khglNhhzxxgl.setUpDt(DateUtil.format(new Date(),"yyyyMMdd"));
            khglNhhzxxgl.setUpTm(DateUtil.format(new Date(),"HHmmss"));
            khglNhhzxxglService.update(khglNhhzxxgl, khglNhhzxxglUpdateWrapper);

            Nhxq khglKhhmcxx = new Nhxq();
            BeanUtils.copyProperties(page, khglKhhmcxx);
            BeanUtils.copyProperties(khglNhhzxxgl, khglKhhmcxx);
            khglKhhmcxx.setKhmc(khglNhhzxxgl.getHzxm());
            khglKhhmcxx.setWgbh(khglNhhzxxgl.getSsyxdy());
            khglKhhmcxx.setLrr(sysUser.getUsername());
            if (page.getTlsj() != null) {
                khglKhhmcxx.setTlsj(tlsj + page.getTlsj());
            }
            khglKhhmcxx.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
            khglKhhmcxx.setUpTm(DateUtil.formatDateTime("HHmmss"));
            UpdateWrapper<Nhxq> khglKhhmcxxUpdateWrapper = new UpdateWrapper<>();
            khglKhhmcxxUpdateWrapper.eq("zjhm", khglNhhzxxgl.getHzzjhm());
            nhxqService.update(khglKhhmcxx, khglKhhmcxxUpdateWrapper);
            saveYxglKhhfxxb(page.getSsyxdy(), page.getHzxm(), page.getHzzjhm(), page.getSszh(), page.getZfyy(),page.getZhpfr());

            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = getRedisQydm();
            if (false) {
                QueryWrapper<KhxxglGrsxlxmxNh> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zjhm", page.getHzzjhm());
                KhxxglGrsxlxmxNh khxxglGrsxlxmxNh = khxxglGrsxlxmxNhService.getOne(queryWrapper);
                if (khxxglGrsxlxmxNh == null || vKhglNhhzxxgl12.init3(page.getHhbm()) == 0) {
                    vKhglNhhzxxgl12.init1(page.getHhbm());
                    //同步oracle到impala
                    EtlUtil.SHcallEtlRc(10, true, false, false, "tjfx_cssz", "idap");
                    //同步之前删除impala数据中khxxgl_grsxlxmx_nh表数据
                    khxxglGrsxlxmxNh1Service.delNhgrsxlxmx();
                    EtlUtil.SHcallEtlRc(10, true, false, true, "khxxgl_grsxlxmx_nh", "idap", page.getHhbm());
                    khxxglGrsxlxmxNhService.delNhgrsxlxmxByHhbm(page.getHhbm());
                    //调用python脚本
                    sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxsxlxtj.py --hhbm " + page.getHhbm() + "'");
                    //同步impala到oracle
                    sshUtil.execShell("sh /home/exportdata/P_TJFX_SXLXTJ_EXPORT.sh");
                    sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_SXLXTJ_IMPORT.sh");
                    vKhglNhhzxxgl12.init2(page.getHhbm());
                }
            }
            vKhglNhhzxxgl12.init(page.getHhbm(), page.getHzzjhm(), sysUser.getWorkNo(), sysUser.getUsername(), "");
        } catch(JeecgBootException jbe) {
            log.error("修改家庭信息失败", jbe);
            return Result.error(jbe.getMessage());
        } catch (Exception e) {
            log.error("修改家庭信息失败", e);
            return Result.error("系统错误，修改家庭信息失败！");
        }
        return Result.ok("修改成功");
    }

    @RequestMapping(value = "/editSxdx", method = RequestMethod.POST)
    public Result<?> editSxdx(@RequestBody JSONObject jsonObject) {
        QueryWrapper<Nhxq> hzgl = new QueryWrapper<>();
        String id = jsonObject.getString("id");
        String hhbm = jsonObject.getString("hhbm");
        if (StringUtils.isNotEmpty(id)) {
            hzgl.eq("id", id);
            Nhxq nhxq = nhxqService.getOne(hzgl);
            if (nhxq != null) {
                QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
                nhhzxxglQueryWrapper.eq("hhbm", hhbm);
                KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(nhhzxxglQueryWrapper);
                if (StringUtils.isNotEmpty(khglNhhzxxgl.getSxdxzjh())) {
                    //如果原授信对象不为空，则不做处理
                    return Result.ok("已有授信对象，不做处理");
                }
                if (!nhxq.getHhbm().equalsIgnoreCase(hhbm)) {
                    return Result.error("数据异常：所选授信对象不是本户成员！");
                }
                //更新授信对象
                KhglNhhzxxgl khglNhhzxxglUpdate = new KhglNhhzxxgl();
                khglNhhzxxglUpdate.setSxdx(nhxq.getKhmc());
                khglNhhzxxglUpdate.setSxdxzjh(nhxq.getZjhm());
                UpdateWrapper<KhglNhhzxxgl> khglNhhzxxglUpdateWrapper = new UpdateWrapper<>();
                khglNhhzxxglUpdateWrapper.eq("hhbm", jsonObject.getString("hhbm"));
                khglNhhzxxglService.update(khglNhhzxxglUpdate, khglNhhzxxglUpdateWrapper);
                //同步更新背靠背评议中的证件号码
                if (StringUtils.isNotEmpty(khglNhhzxxgl.getSxdxzjh())) {
                    Nhbkbpy nhbkbpyUpdate = new Nhbkbpy();
                    nhbkbpyUpdate.setZjhm(nhxq.getZjhm());
                    nhbkbpyUpdate.setKhmc(nhxq.getKhmc());
                    UpdateWrapper<Nhbkbpy> nhbkbpyUpdateWrapper = new UpdateWrapper<>();
                    nhbkbpyUpdateWrapper.eq("hhbm", jsonObject.getString("hhbm"));
                    nhbkbpyUpdateWrapper.eq("zjhm", khglNhhzxxgl.getSxdxzjh());
                    iNhbkbpyService.update(nhbkbpyUpdate, nhbkbpyUpdateWrapper);
                }
            }
        }

        return Result.ok("修改成功");
    }

    @RequestMapping(value = "/AddGrhmcxx", method = RequestMethod.POST)
    public Result<?> AddGrhmcxx(@RequestBody Nhxq jsonObject) {
        try {
            if (jsonObject != null) {
                QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", jsonObject.getId());
                List<Nhxq> list = nhxqService.list(queryWrapper);

                if (list.size() == 0) {
                    jsonObject.setLrbz("1");
                    jsonObject.setLrr(getUsername());
                    jsonObject.setLrsj(new Date());
                    nhxqService.save(jsonObject);
                } else {
                    //2020/12/14 zhouquan 由于蓝山版本点击修改个人信息 里面证件号码是脱敏状态所以做修改
                    jsonObject.setZjhm(list.get(0).getZjhm());
                    jsonObject.setLrr(getUsername());
                    jsonObject.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
                    jsonObject.setUpTm(DateUtil.formatDateTime("HHmmss"));
                    UpdateWrapper<Nhxq> khglKhhmcxxUpdateWrapper = new UpdateWrapper<>();
                    khglKhhmcxxUpdateWrapper.eq("id", jsonObject.getId());
                    nhxqService.update(jsonObject, khglKhhmcxxUpdateWrapper);
                }
                //同步更新户主信息表
                LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                UpdateWrapper<KhglNhhzxxgl> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("hhbm", jsonObject.getHhbm());
                KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
//                khglNhhzxxgl.setLrr(sysUser.getUsername());
//                khglNhhzxxgl.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
//                khglNhhzxxgl.setUpTm(DateUtil.formatDateTime("HHmmss"));
                if ("1".equalsIgnoreCase(jsonObject.getSfhz())) {
                    khglNhhzxxgl.setHzxm(jsonObject.getKhmc());
                    khglNhhzxxgl.setHzzjhm(jsonObject.getZjhm());
                    khglNhhzxxglService.update(khglNhhzxxgl, updateWrapper);
                }

                //同步客户基本资料信息表
                QueryWrapper<Khjbzl> queryWrapperKhjbzl = new QueryWrapper();
                queryWrapperKhjbzl.eq("zjhm", jsonObject.getZjhm());
                Khjbzl khjbzl = khjbzlService.getOne(queryWrapperKhjbzl);
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
                    if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getWgbh())) {
                        khjbzl.setWgbh(jsonObject.getWgbh());
                    }
                    if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getSszh())) {
                        khjbzl.setSszh(jsonObject.getSszh());
                    }
                    if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getJgdm())) {
                        khjbzl.setJgdm(jsonObject.getJgdm());
                    }
                    if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getKhlx())) {
                        khjbzl.setKhlx(jsonObject.getKhlx());
                    }
                    if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getLxfs())) {
                        khjbzl.setLxfs(jsonObject.getSjhm());
                    }
                    if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getDz())) {
                        khjbzl.setDz(jsonObject.getZz());
                    }
                    khjbzlService.update(khjbzl, queryWrapperKhjbzl);

                } else {
                    Khjbzl khjbzlSave = new Khjbzl();
                    khjbzlSave.setWgbh(jsonObject.getWgbh());
                    khjbzlSave.setJgdm(jsonObject.getJgdm());
                    khjbzlSave.setSszh(jsonObject.getSszh());
                    khjbzlSave.setKhmc(jsonObject.getKhmc());
                    khjbzlSave.setZjlx("01");
                    khjbzlSave.setZjhm(jsonObject.getZjhm());
                    khjbzlSave.setLxfs(jsonObject.getSjhm());
                    khjbzlSave.setDz(jsonObject.getZz());
                    khjbzlSave.setKhxz("1");
                    khjbzlSave.setKhlx(jsonObject.getKhlx());
                    khjbzlSave.setKhlb("2");
                    khjbzlSave.setDabh(UUIDGenerator.generate());
                    khjbzlSave.setCreateTime(new Date());
                    khjbzlSave.setCreateBy(sysUser.getUsername());
                    khjbzlService.save(khjbzlSave);
                }

                //同步客户附加信息表
                QueryWrapper<Khfjxxgl> khfjxxglQueryWrapper = new QueryWrapper<>();
                khfjxxglQueryWrapper.eq("zjhm", jsonObject.getZjhm());
                Khfjxxgl khfjxxgl = khfjxxglService.getOne(khfjxxglQueryWrapper);
                if (khfjxxgl != null) {
                    khfjxxgl.setSfxdry(jsonObject.getSfxdry());
                    khfjxxgl.setSfzdjb(jsonObject.getHbjl());
                    khfjxxgl.setSfss(jsonObject.getWhss());
                    khfjxxgl.setSffx(jsonObject.getSffx());
                    khfjxxgl.setSfzpry(jsonObject.getZpry());
                    khfjxxgl.setSfffjz(jsonObject.getSfffjz());
                    if (StringUtils.isNotEmpty(jsonObject.getKhfq())) {
                        khfjxxgl.setSfgzry(jsonObject.getKhfq().equals("1") ? "1" : "2");
                    }
                    khfjxxgl.setUpdateBy(sysUser.getUsername());
                    khfjxxgl.setUpdateTime(new Date());
                    khfjxxglService.update(khfjxxgl, khfjxxglQueryWrapper);
                } else {
                    Khfjxxgl khfjxxgl1 = new Khfjxxgl();
                    khfjxxgl1.setKhmc(jsonObject.getKhmc());
                    khfjxxgl1.setZjhm(jsonObject.getZjhm());
                    khfjxxgl1.setSfxdry(jsonObject.getSfxdry());
                    khfjxxgl1.setSfzdjb(jsonObject.getHbjl());
                    khfjxxgl1.setSfss(jsonObject.getWhss());
                    khfjxxgl1.setSffx(jsonObject.getSffx());
                    khfjxxgl1.setSfzpry(jsonObject.getZpry());
                    khfjxxgl1.setSfffjz(jsonObject.getSfffjz());
                    if (StringUtils.isNotEmpty(jsonObject.getKhfq())) {
                        khfjxxgl1.setSfgzry(jsonObject.getKhfq().equals("1") ? "1" : "2");
                    }
                    khfjxxgl1.setCreateBy(sysUser.getUsername());
                    khfjxxgl1.setCreateTime(new Date());
                    khfjxxglService.save(khfjxxgl1);
                }


                saveYxglKhhfxxb(jsonObject.getWgbh(), jsonObject.getKhmc(), jsonObject.getZjhm(), jsonObject.getSszh(), jsonObject.getZfyy(),jsonObject.getZhpfr());

                LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                String qybm = getRedisQydm();
                if (false) {
                    QueryWrapper<KhxxglGrsxlxmxNh> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("zjhm", jsonObject.getZjhm());
                    KhxxglGrsxlxmxNh khxxglGrsxlxmxNh = khxxglGrsxlxmxNhService.getOne(queryWrapper1);
                    if (khxxglGrsxlxmxNh == null || vKhglNhhzxxgl12.init3(jsonObject.getHhbm()) == 0) {
                        vKhglNhhzxxgl12.init1(jsonObject.getHhbm());
                        //同步oracle到impala
                        EtlUtil.SHcallEtlRc(10, true, false, false, "tjfx_cssz", "idap");
                        //同步之前删除impala数据中khxxgl_grsxlxmx_nh表数据
                        khxxglGrsxlxmxNh1Service.delNhgrsxlxmx();
                        EtlUtil.SHcallEtlRc(10, true, false, true, "khxxgl_grsxlxmx_nh", "idap", jsonObject.getHhbm());
                        khxxglGrsxlxmxNhService.delNhgrsxlxmxByHhbm(jsonObject.getHhbm());
                        //调用python脚本
                        sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxsxlxtj.py --hhbm " + jsonObject.getHhbm() + "'");
                        //同步impala到oracle
                        sshUtil.execShell("sh /home/exportdata/P_TJFX_SXLXTJ_EXPORT.sh");
                        sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_SXLXTJ_IMPORT.sh");
                        vKhglNhhzxxgl12.init2(jsonObject.getHhbm());
                    }
                }
                vKhglNhhzxxgl12.init(jsonObject.getHhbm(), jsonObject.getZjhm(), sysUser.getWorkNo(), sysUser.getUsername(), "");
            } else {
                return Result.error("修改失败");
            }
        } catch(JeecgBootException jbe) {
            log.error("个人信息修改失败", jbe);
            return Result.error(jbe.getMessage());
        }  catch (Exception e) {
            log.error("个人信息修改失败", e);
            return Result.error("系统错误，修改失败！");
        }
        return Result.ok("修改成功");
    }

    @RequestMapping(value = "/AddGrcjxx", method = RequestMethod.POST)
    public Result<?> AddGrcjxx(@RequestBody CamsZcsxNhcjxxPad jsonObject) {
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date);
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            jsonObject.setLrr(loginUser.getUsername());

            //2020/12/14 zhouquan

            QueryWrapper<Nhxq> khhmcxxQueryWrapper = new QueryWrapper<>();
            khhmcxxQueryWrapper.eq("id", jsonObject.getId());
            Nhxq khhmcxx = nhxqService.getOne(khhmcxxQueryWrapper);
            jsonObject.setZjhm(khhmcxx.getZjhm());

            if (jsonObject != null) {
                QueryWrapper<CamsZcsxNhcjxxPad> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zjhm", jsonObject.getZjhm());
                List<CamsZcsxNhcjxxPad> list = camsZcsxNhcjxxPadService.list(queryWrapper);
                if (list.size() == 0) {
                    jsonObject.setUpDt(dateStr);
                    camsZcsxNhcjxxPadService.save(jsonObject);
                } else {
                    UpdateWrapper<CamsZcsxNhcjxxPad> khglKhhmcxxUpdateWrapper = new UpdateWrapper<>();
                    khglKhhmcxxUpdateWrapper.eq("zjhm", jsonObject.getZjhm());
                    jsonObject.setUpDt(dateStr);
                    camsZcsxNhcjxxPadService.update(jsonObject, khglKhhmcxxUpdateWrapper);
                }

                String qybm = getRedisQydm();
                if (false) {
                    QueryWrapper<KhxxglGrsxlxmxNh> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("zjhm", khhmcxx.getZjhm());
                    KhxxglGrsxlxmxNh khxxglGrsxlxmxNh = khxxglGrsxlxmxNhService.getOne(queryWrapper1);
                    if (khxxglGrsxlxmxNh == null || vKhglNhhzxxgl12.init3(khhmcxx.getHhbm()) == 0) {
                        vKhglNhhzxxgl12.init1(khhmcxx.getHhbm());
                        //同步oracle到impala
                        EtlUtil.SHcallEtlRc(10, true, false, false, "tjfx_cssz", "idap");
                        //同步之前删除impala数据中khxxgl_grsxlxmx_nh表数据
                        khxxglGrsxlxmxNh1Service.delNhgrsxlxmx();
                        EtlUtil.SHcallEtlRc(10, true, false, true, "khxxgl_grsxlxmx_nh", "idap", khhmcxx.getHhbm());
                        khxxglGrsxlxmxNhService.delNhgrsxlxmxByHhbm(khhmcxx.getHhbm());
                        //调用python脚本
                        sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxsxlxtj.py --hhbm " + khhmcxx.getHhbm() + "'");
                        //同步impala到oracle
                        sshUtil.execShell("sh /home/exportdata/P_TJFX_SXLXTJ_EXPORT.sh");
                        sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_SXLXTJ_IMPORT.sh");
                        vKhglNhhzxxgl12.init2(khhmcxx.getHhbm());
                    }
                }
                vKhglNhhzxxgl12.init(khhmcxx.getHhbm(), khhmcxx.getZjhm(), loginUser.getWorkNo(), loginUser.getUsername(), "");
            } else {
                return Result.error("修改失败");
            }
        } catch(JeecgBootException jbe) {
            log.error("个人采集信息修改失败", jbe);
            return Result.error(jbe.getMessage());
        } catch (Exception e) {
            log.error("个人采集信息修改失败", e);
            return Result.error("系统错误，修改失败！");
        }
        return Result.ok("修改成功");
    }

    @Transactional
    @RequestMapping(value = "/AddFcxx", method = RequestMethod.POST)
    public Result<?> AddFcxx(@RequestBody CamsZcsxNhfcxxPadReceive jsonObject) {
        try {
            List<CamsZcsxNhfcxxPad> purchaseOrders = jsonObject.getCams();
            UpdateWrapper<CamsZcsxNhfcxxPad> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("hhbm", jsonObject.getHhbm());
            List<CamsZcsxNhfcxxPad> list = camsZcsxNhfcxxPad.list(updateWrapper);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (list != null && list.size() > 0) {
                camsZcsxNhfcxxPad.remove(updateWrapper);
                for (int i = 0; i < purchaseOrders.size(); i++) {
                    if (i > 0) {
                        purchaseOrders.get(i).setHhbm(jsonObject.getHhbm());
                        purchaseOrders.get(i).setKhmc(jsonObject.getKhmc());
                        purchaseOrders.get(i).setLrsj(new Date());
                        purchaseOrders.get(i).setLrr(sysUser.getUsername());
                        purchaseOrders.get(i).setFcbm(UUID.randomUUID().toString().substring(0, 16));
                        camsZcsxNhfcxxPad.save(purchaseOrders.get(i));
                    }
                }
            } else {
                for (int i = 0; i < purchaseOrders.size(); i++) {
                    if (i > 0) {
                        purchaseOrders.get(i).setHhbm(jsonObject.getHhbm());
                        purchaseOrders.get(i).setKhmc(jsonObject.getKhmc());
                        purchaseOrders.get(i).setLrsj(new Date());
                        purchaseOrders.get(i).setLrr(sysUser.getUsername());
                        purchaseOrders.get(i).setFcbm(UUID.randomUUID().toString().substring(0, 16));
                        camsZcsxNhfcxxPad.save(purchaseOrders.get(i));
                    }
                }
            }
        } catch (Exception e) {
            log.error("编辑房产信息失败", e);
            return Result.error("系统错误，编辑房产信息失败！");
        }
        return Result.ok("添加成功");
    }

    @Transactional
    @RequestMapping(value = "/AddPjsxxx", method = RequestMethod.POST)
    public Result<?> AddPjsxxx(@RequestBody CamsZcsxNhpjsxxxPad jsonObject) {
        try {
            //通过授信对象id 查询证件号码 update 2020/11/20 zhouquan
            QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", jsonObject.getZjhm());
            Nhxq khhmcxx = nhxqService.getOne(queryWrapper);

            if (khhmcxx != null) {
                jsonObject.setZjhm(khhmcxx.getZjhm());
                jsonObject.setKhmc(khhmcxx.getKhmc());
            }

            QueryWrapper<CamsZcsxNhpjsxxxPad> pjsxxx = new QueryWrapper<>();
            pjsxxx.eq("hhbm", jsonObject.getHhbm());
            List<CamsZcsxNhpjsxxxPad> list = iCamsZcsxNhpjsxxxPadService.list(pjsxxx);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (list != null && list.size() > 0) {
                UpdateWrapper<CamsZcsxNhpjsxxxPad> cms = new UpdateWrapper<>();
                cms.eq("hhbm", jsonObject.getHhbm());
                jsonObject.setLrr(sysUser.getUsername());
                jsonObject.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
                jsonObject.setUpTm(DateUtil.formatDateTime("HHmmss"));
                iCamsZcsxNhpjsxxxPadService.update(jsonObject, cms);
            } else {
                jsonObject.setLrbz("1");
                jsonObject.setLrsj(new Date());
                jsonObject.setLrr(sysUser.getUsername());
                iCamsZcsxNhpjsxxxPadService.save(jsonObject);
            }
        } catch (Exception e) {
            log.error("编辑评级授信信息失败", e);
            return Result.error("系统错误，编辑评级授信信息失败！");
        }
        return Result.ok("添加成功");
    }

    @RequestMapping(value = "/AddHzxx020", method = RequestMethod.POST)
    public Result<?> AddHzxx020(@RequestBody Map<String, Object> models) {
        try{
        KhglNhhzxxgl nhhzxxgl = JSON.parseObject(JSON.toJSONString(models), KhglNhhzxxgl.class);
        KhglKhhmcxx khhmcxx = JSON.parseObject(JSON.toJSONString(models), KhglKhhmcxx.class);

        QueryWrapper<Nhxq> nh = new QueryWrapper<>();
        nh.eq("zjhm", nhhzxxgl.getHzzjhm());
        List<Nhxq> list = nhxqService.list(nh);
        if (CollUtil.isNotEmpty(list)) {
            return Result.error("证件号码已存在农户信息表！");
        }
        String id = UUIDGenerator.generate();


        Nhxq nhxqxx = new Nhxq();
        nhxqxx.setId(id);
        nhxqxx.setSszh(nhhzxxgl.getSszh());
        QueryWrapper queryWrapperZzbz = new QueryWrapper();
        queryWrapperZzbz.eq("zzbz", nhhzxxgl.getSszh());
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
        if (StringUtils.isNotBlank(hrBasOrganization.getYwjgdm()))
            nhxqxx.setJgdm(hrBasOrganization.getYwjgdm());
        if (StringUtils.isNotBlank(nhhzxxgl.getHhbm())){
            nhxqxx.setHhbm(nhhzxxgl.getHhbm());
        }else {
            nhxqxx.setHhbm(IdUtil.fastSimpleUUID());
        }
        nhxqxx.setKhmc(nhhzxxgl.getHzxm());
        nhxqxx.setWgbh(nhhzxxgl.getSsyxdy());
        nhxqxx.setKhlx("1");
        nhxqxx.setYhzgx("1");
        nhxqxx.setSfhz("1");
        //khglKhhmcxx.setXb(khhmcxx.getXb());
        nhxqxx.setSjhm(khhmcxx.getLxfs());
        nhxqxx.setZjhm(nhhzxxgl.getHzzjhm());
        if (IdcardUtil.isValidCard(nhhzxxgl.getHzzjhm())) {
            int genderByIdCard = IdcardUtil.getGenderByIdCard(nhhzxxgl.getHzzjhm());
            int ageByIdCard = IdcardUtil.getAgeByIdCard(nhhzxxgl.getHzzjhm());
            nhxqxx.setXb(genderByIdCard == 1 ? "1" : "2");
            nhxqxx.setNl(String.valueOf(ageByIdCard));
        }

        //khglKhhmcxx.setNl(khhmcxx.getNl());
        nhxqxx.setZz(khhmcxx.getDz());
        nhxqxx.setHjdz(khhmcxx.getDz());
        nhxqxx.setYhzgx("1");
        nhxqxx.setLrbz("1");
        nhxqxx.setLrsj(new Date());
        nhxqxx.setLrr(getLoginUser().getWorkNo());
        nhxqxx.setUpdateTime(new Date());
        nhxqxx.setUpdateBy(getWorkNo());
        nhxqService.save(nhxqxx);
        }catch (Exception e){
            e.printStackTrace();
            return Result.ok("添加失败");
        }
        return Result.ok("添加成功");
    }
    @Transactional
    @RequestMapping(value = "/AddHzxx", method = RequestMethod.POST)
    public Result<?> AddHzxx(@RequestBody Map<String, Object> models) {
        try {
            KhglNhhzxxgl nhhzxxgl = JSON.parseObject(JSON.toJSONString(models), KhglNhhzxxgl.class);
            KhglKhhmcxx khhmcxx = JSON.parseObject(JSON.toJSONString(models), KhglKhhmcxx.class);

            //对证件号码进行解密
            nhhzxxgl.setHzzjhm(Base64.decodeStr(nhhzxxgl.getHzzjhm()));
            //判断证件号码是否存在
            QueryWrapper<KhglNhhzxxgl> khxx = new QueryWrapper<>();
            khxx.eq("hzzjhm", nhhzxxgl.getHzzjhm());
            List<KhglNhhzxxgl> khxxList = khglNhhzxxglService.list(khxx);
            String qydm=getRedisQydm();
            if (khxxList != null && khxxList.size() > 0) {
                if (StringUtils.isNotBlank(qydm)&& QybmEnum.RUCHENG.getQybm().equals(qydm)) {
                    String wgbh = sysDictService.queryTableDictTextByKey("v_yxdygl_main", "wgmc_show", "wgbh", khxxList.get(0).getSsyxdy());
                    String sszh = sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khxxList.get(0).getSszh());
                    return Result.error("证件号码已存在户主信息！[" + wgbh + "-" + sszh + "]");
                }
                return Result.error("证件号码已存在户主信息！");
            }

            QueryWrapper<Nhxq> nh = new QueryWrapper<>();
            nh.eq("zjhm", nhhzxxgl.getHzzjhm());
            nh.isNotNull("hhbm");
            List<Nhxq> list = nhxqService.list(nh);
            if (CollUtil.isNotEmpty(list)) {
                if (StringUtils.isNotBlank(qydm)&& QybmEnum.RUCHENG.getQybm().equals(qydm)) {
                    String wgbh = sysDictService.queryTableDictTextByKey("v_yxdygl_main", "wgmc_show", "wgbh", list.get(0).getWgbh());
                    String sszh = sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", list.get(0).getSszh());
                    return Result.error("证件号码已存在农户信息表！[" + wgbh + "-" + sszh + "]");
                }
                return Result.error("证件号码已存在农户信息表！");
            }

            String id = UUIDGenerator.generate();
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            nhhzxxgl.setId(id);
            nhhzxxgl.setHhbm(nhhzxxgl.getSsyxdy() + sysDictService.queryhhbm("seq_hhbm.nextval"));
            nhhzxxgl.setSxdx(nhhzxxgl.getHzxm());
            nhhzxxgl.setSxdxzjh(nhhzxxgl.getHzzjhm());
            nhhzxxgl.setZkhjl(sysUser.getWorkNo());
            nhhzxxgl.setSfzf("2");
            nhhzxxgl.setSfyxzf("2");
            nhhzxxgl.setLrsj(new Date());
            nhhzxxgl.setLrbz("1");
            nhhzxxgl.setLrr(sysUser.getUsername());

            khglNhhzxxglService.save(nhhzxxgl);

            //同步保留花名册信息
            Nhxq nhxqxx = new Nhxq();
            nhxqxx.setId(id);
            nhxqxx.setSszh(nhhzxxgl.getSszh());
            QueryWrapper queryWrapperZzbz = new QueryWrapper();
            queryWrapperZzbz.eq("zzbz", nhhzxxgl.getSszh());
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
            if (StringUtils.isNotBlank(hrBasOrganization.getYwjgdm()))
                nhxqxx.setJgdm(hrBasOrganization.getYwjgdm());
            nhxqxx.setHhbm(nhhzxxgl.getHhbm());
            nhxqxx.setKhmc(nhhzxxgl.getHzxm());
            nhxqxx.setWgbh(nhhzxxgl.getSsyxdy());
            nhxqxx.setKhlx("1");
            nhxqxx.setYhzgx("1");
            nhxqxx.setSfhz("1");
            //khglKhhmcxx.setXb(khhmcxx.getXb());
            nhxqxx.setSjhm(khhmcxx.getLxfs());
            nhxqxx.setZjhm(nhhzxxgl.getHzzjhm());
            if (IdcardUtil.isValidCard(nhhzxxgl.getHzzjhm())) {
                int genderByIdCard = IdcardUtil.getGenderByIdCard(nhhzxxgl.getHzzjhm());
                int ageByIdCard = IdcardUtil.getAgeByIdCard(nhhzxxgl.getHzzjhm());
                nhxqxx.setXb(genderByIdCard == 1 ? "1" : "2");
                nhxqxx.setNl(String.valueOf(ageByIdCard));
            }

            //khglKhhmcxx.setNl(khhmcxx.getNl());
            nhxqxx.setZz(khhmcxx.getDz());
            nhxqxx.setHjdz(khhmcxx.getDz());
            nhxqxx.setYhzgx("1");
            nhxqxx.setLrbz("1");
            nhxqxx.setLrsj(new Date());
            nhxqxx.setLrr(sysUser.getUsername());
            nhxqService.save(nhxqxx);

            //天易删除农户移除表数据
            if (qydm.equals(QydmEnums.TIANYI.getQydmCode())){
                QueryWrapper<Nhycxq> nhycxqQueryWrapper=new QueryWrapper<>();
                nhycxqQueryWrapper.eq("zjhm",nhhzxxgl.getHzzjhm());
                nhycxqService.remove(nhycxqQueryWrapper);
            }

            //更新客户基本资料信息表
            QueryWrapper<Khjbzl> queryWrapper = new QueryWrapper();
            queryWrapper.eq("zjhm", nhxqxx.getZjhm());
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
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getWgbh())) {
                    khjbzl.setWgbh(nhxqxx.getWgbh());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getSszh())) {
                    khjbzl.setSszh(nhxqxx.getSszh());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getJgdm())) {
                    khjbzl.setJgdm(nhxqxx.getJgdm());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getKhlx())) {
                    khjbzl.setKhlx(nhxqxx.getKhlx());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getLxfs())) {
                    khjbzl.setLxfs(nhxqxx.getSjhm());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getDz())) {
                    khjbzl.setDz(nhxqxx.getZz());
                }
                khjbzlService.update(khjbzl, queryWrapper);

            } else {
                Khjbzl khjbzlSave = new Khjbzl();
                khjbzlSave.setWgbh(nhxqxx.getWgbh());
                khjbzlSave.setJgdm(nhxqxx.getJgdm());
                khjbzlSave.setSszh(nhxqxx.getSszh());
                khjbzlSave.setKhmc(nhxqxx.getKhmc());
                khjbzlSave.setZjlx("01");
                khjbzlSave.setZjhm(nhxqxx.getZjhm());
                khjbzlSave.setLxfs(nhxqxx.getSjhm());
                khjbzlSave.setDz(nhxqxx.getZz());
                khjbzlSave.setKhxz("1");
                khjbzlSave.setKhlx(nhxqxx.getKhlx());
                khjbzlSave.setKhlb("2");
                khjbzlSave.setDabh(UUIDGenerator.generate());
                khjbzlSave.setCreateTime(new Date());
                khjbzlSave.setCreateBy(sysUser.getUsername());
                khjbzlService.save(khjbzlSave);
            }
            saveYxglKhhfxxb(nhhzxxgl.getSsyxdy(), nhhzxxgl.getHzxm(), nhhzxxgl.getHzzjhm(), nhhzxxgl.getSszh(), nhhzxxgl.getZfry(),nhhzxxgl.getZhpfr());
            if (QydmEnums.LIUYANG.getQydmCode().equals(getRedisQydm())) {

            } else {
                LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                String qybm = getRedisQydm();
                if (false) {
                    QueryWrapper<KhxxglGrsxlxmxNh> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("zjhm", nhhzxxgl.getHzzjhm());
                    KhxxglGrsxlxmxNh khxxglGrsxlxmxNh = khxxglGrsxlxmxNhService.getOne(queryWrapper1);
                    if (khxxglGrsxlxmxNh == null || vKhglNhhzxxgl12.init3(khhmcxx.getHhbm()) == 0) {
                        vKhglNhhzxxgl12.init1(nhhzxxgl.getHhbm());
                        //同步oracle到impala
                        EtlUtil.SHcallEtlRc(10, true, false, false, "tjfx_cssz", "idap");
                        //同步之前删除impala数据中khxxgl_grsxlxmx_nh表数据
                        khxxglGrsxlxmxNh1Service.delNhgrsxlxmx();
                        EtlUtil.SHcallEtlRc(10, true, false, true, "khxxgl_grsxlxmx_nh", "idap", nhhzxxgl.getHhbm());
                        khxxglGrsxlxmxNhService.delNhgrsxlxmxByHhbm(nhhzxxgl.getHhbm());
                        //调用python脚本
                        sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxsxlxtj.py --hhbm " + nhhzxxgl.getHhbm() + "'");
                        //同步impala到oracle
                        sshUtil.execShell("sh /home/exportdata/P_TJFX_SXLXTJ_EXPORT.sh");
                        sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_SXLXTJ_IMPORT.sh");
                        vKhglNhhzxxgl12.init2(nhhzxxgl.getHhbm());
                    }
                }
                vKhglNhhzxxgl12.init(nhhzxxgl.getHhbm(), nhhzxxgl.getHzzjhm(), sysUser.getWorkNo(), sysUser.getUsername(), "");
            }
        } catch(JeecgBootException jbe) {
            log.error("个人采集信息修改失败", jbe);
            return Result.error(jbe.getMessage());
        } catch (Exception e) {
            log.error("个人采集信息修改失败", e);
            return Result.error("系统错误，添加失败！");
        }
        return Result.ok("添加成功");
    }

    /**
     * 添加家庭成员保存（未创建农户档案的）
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/AddJtxxByHhbm", method = RequestMethod.POST)
    public Result<?> AddJtxxByHhbm(@RequestBody JSONObject jsonObject) {
        Nhxq khglKhhmcxx = new Nhxq();

        try {
            QueryWrapper nh = new QueryWrapper();
            nh.eq("zjhm", jsonObject.getString("zjhm"));
            List list = nhxqService.list(nh);
            if (CollUtil.isNotEmpty(list)) {
                return Result.error("证件号码已存在农户信息表！");
            }
            //查询户主信息
            QueryWrapper<KhglNhhzxxgl> hzxx = new QueryWrapper<>();
            hzxx.eq("hhbm", jsonObject.getString("hhbm"));
            List<KhglNhhzxxgl> hzxxList = khglNhhzxxglService.list(hzxx);
            if (hzxxList != null && hzxxList.size() > 0) {
                KhglNhhzxxgl nhhzxxgl = hzxxList.get(0);
                khglKhhmcxx.setSszh(nhhzxxgl.getSszh());
                khglKhhmcxx.setWgbh(nhhzxxgl.getSsyxdy());
                Nhxq nhxq1 = nhxqService.getByZjhm(nhhzxxgl.getHzzjhm());
                khglKhhmcxx.setJgdm(StringUtils.isNotBlank(nhxq1.getJgdm())?nhxq1.getJgdm():null);
            }
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            khglKhhmcxx.setHhbm(jsonObject.getString("hhbm"));
            khglKhhmcxx.setKhmc(jsonObject.getString("khmc"));
            khglKhhmcxx.setYhzgx(jsonObject.getString("yhzgx"));
            khglKhhmcxx.setXb(jsonObject.getString("xb"));
            khglKhhmcxx.setSjhm(jsonObject.getString("lxfs"));
            khglKhhmcxx.setZjhm(jsonObject.getString("zjhm"));
            khglKhhmcxx.setNl(jsonObject.getString("nl"));
            khglKhhmcxx.setSfhz("2");
            khglKhhmcxx.setKhlx("1");
            khglKhhmcxx.setLrsj(new Date());
            khglKhhmcxx.setLrr(sysUser.getUsername());
            nhxqService.save(khglKhhmcxx);
            //同步客户基本资料信息表
            QueryWrapper<Khjbzl> queryWrapperKhjbzl = new QueryWrapper();
            queryWrapperKhjbzl.eq("zjhm", khglKhhmcxx.getZjhm());
            Khjbzl khjbzl = khjbzlService.getOne(queryWrapperKhjbzl);
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
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getWgbh())) {
                    khjbzl.setWgbh(khglKhhmcxx.getWgbh());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getSszh())) {
                    khjbzl.setSszh(khglKhhmcxx.getSszh());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getJgdm())) {
                    khjbzl.setJgdm(khglKhhmcxx.getJgdm());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getKhlx())) {
                    khjbzl.setKhlx(khglKhhmcxx.getKhlx());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getLxfs())) {
                    khjbzl.setLxfs(khglKhhmcxx.getSjhm());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getDz())) {
                    khjbzl.setDz(khglKhhmcxx.getZz());
                }
                khjbzlService.update(khjbzl, queryWrapperKhjbzl);

            } else {
                Khjbzl khjbzlSave = new Khjbzl();
                khjbzlSave.setWgbh(khglKhhmcxx.getWgbh());
                khjbzlSave.setJgdm(khglKhhmcxx.getJgdm());
                khjbzlSave.setSszh(khglKhhmcxx.getSszh());
                khjbzlSave.setKhmc(khglKhhmcxx.getKhmc());
                khjbzlSave.setZjlx("01");
                khjbzlSave.setZjhm(khglKhhmcxx.getZjhm());
                khjbzlSave.setLxfs(khglKhhmcxx.getSjhm());
                khjbzlSave.setDz(khglKhhmcxx.getZz());
                khjbzlSave.setKhxz("1");
                khjbzlSave.setKhlx(khglKhhmcxx.getKhlx());
                khjbzlSave.setKhlb("2");
                khjbzlSave.setDabh(UUIDGenerator.generate());
                khjbzlSave.setCreateTime(new Date());
                khjbzlSave.setCreateBy(sysUser.getUsername());
                khjbzlService.save(khjbzlSave);
            }


        } catch (Exception e) {
            log.error("添加家庭成员信息失败", e);
            return Result.error("系统错误，添加失败！");
        }
        return Result.ok("添加成功");
    }


    @GetMapping(value = "/queryFjxxByHhbm")
    public Result<?> queryFjxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            if (hhbm != null) {
                QueryWrapper<KhglNhhzzllb> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("hhbm", hhbm);
                List<KhglNhhzzllb> list = iKhglNhhzzllbService.list(khhmcQueryWrapper);
                List<FjxxRecive> list1 = new ArrayList<>();
              /*  for (int i = 0; i < list.size(); i++) {
                    FjxxRecive fjxxRecive = new FjxxRecive();
                    fjxxRecive.setName(list.get(i).getZlmc());
                    fjxxRecive.setUrl(list.get(i).getFwlj());
                    list1.add(fjxxRecive);
                }*/
                if (list != null && list.size() > 0) {
                    return Result.ok(list);
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有附件数据");
    }

    /**
     * 查询业务往来信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryYwwlxxById")
    public Result<?> queryYwwlxxById(@Param("id") String id) {
        try {
            QueryWrapper<KhglNhhzxxgl> khglNhhzxxglQueryWrapper = new QueryWrapper<>();
            khglNhhzxxglQueryWrapper.eq("id", id);
            KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(khglNhhzxxglQueryWrapper);
            QueryWrapper<KhglYwhywwlxxPad> ywhywwlxxPadQueryWrapper = new QueryWrapper<>();
            ywhywwlxxPadQueryWrapper.eq("zjhm", khglNhhzxxgl.getHzzjhm());
            List<KhglYwhywwlxxPad> ywhywwlxxPadList = khglYwhywwlxxPadMapper.list(ywhywwlxxPadQueryWrapper);
            if (ywhywwlxxPadList != null && ywhywwlxxPadList.size() > 0) {
                return Result.ok(ywhywwlxxPadList.get(0));
            }
        } catch (Exception e) {
            log.error("查询业务信息失败", e);
            return Result.error("查询业务信息失败");
        }
        return Result.ok("未查询到业务信息");
    }

    @GetMapping(value = "/queryYwxxByZjhm")
    public Result<?> queryYwxxByZjhm(@Param("id") String id) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            if (!StringUtils.isEmpty(id)) {
                QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("id", id);

                Nhxq khhmcxx = nhxqService.getOne(khhmcQueryWrapper, false);
                QueryWrapper<KhglYwhywwlxxPad> ywhywwlxxPadQueryWrapper = new QueryWrapper<>();
                ywhywwlxxPadQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
                List<KhglYwhywwlxxPad> cdkywxxList = khglYwhywwlxxPadMapper.list(ywhywwlxxPadQueryWrapper);
                jsonObject.put("cdkywxx", cdkywxxList);
                /*if (cdkywxxList != null && cdkywxxList.size()>0){
                    Map<String,KhglYwhywwlxxPad> map = new HashMap<>();
                    map.put("cdkywxx", cdkywxxList.get(0));
                    jsonArray.add(map);
                }*/
                QueryWrapper<KhywxxSbk> sbkQueryWrapper = new QueryWrapper<>();
                sbkQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
                List<KhywxxSbk> sbkList = khywxxSbkService.list(sbkQueryWrapper);
                for (KhywxxSbk sbkxx : sbkList) {
                    //处理社保卡状态
                    if (sbkxx.getZt() != null) {
                        String ztBinary = Integer.toBinaryString(Integer.valueOf(sbkxx.getZt()));
                        List<String> ztxxList = new ArrayList<>();
                        for (int i = ztBinary.length() - 1; i >= 0; i--) {
                            String zt = ztBinary.substring(i, i + 1);
                            if ("1".equalsIgnoreCase(zt)) {
                                String ztxx = sysDictService.queryDictTextByKey("sbkzt", String.valueOf(ztBinary.length() - i));
                                ztxxList.add(ztxx);
                            }
                        }
                        sbkxx.setZt(StringUtils.join(ztxxList, ','));
                    }
                }
                jsonObject.put("sbksjmx", sbkList);
                QueryWrapper<KhywxxDksjmxPad> dksjmx = new QueryWrapper<>();
                dksjmx.eq("zjhm", khhmcxx.getZjhm());
                List<KhywxxDksjmxPad> dksjmxList = khywxxDksjmxPadMapper.list(dksjmx);
                for (KhywxxDksjmxPad khywxxDksjmxPad : dksjmxList) {
                    khywxxDksjmxPad.setSszh(khywxxDksjmxPad.getSszh() == null ? "" : sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "ywjgdm", khywxxDksjmxPad.getSszh()));
                    khywxxDksjmxPad.setDkxt(khywxxDksjmxPad.getDkxt() == null ? "" : sysDictService.queryDictTextByKey("dkxt", khywxxDksjmxPad.getDkxt()));
                    khywxxDksjmxPad.setDbfs(khywxxDksjmxPad.getDbfs() == null ? "" : sysDictService.queryDictTextByKey("dbfs", khywxxDksjmxPad.getDbfs()));
                    khywxxDksjmxPad.setDkpz(khywxxDksjmxPad.getDkpz() == null ? "" : sysDictService.queryDictTextByKey("dkzl", khywxxDksjmxPad.getDkpz()));
                    khywxxDksjmxPad.setDyzrr(khywxxDksjmxPad.getDyzrr() == null ? "" : sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", khywxxDksjmxPad.getDyzrr()));
                    if (!StringUtils.isEmpty(khywxxDksjmxPad.getKhjlbz())) {
                        khywxxDksjmxPad.setKhjlbz(sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", khywxxDksjmxPad.getKhjlbz()));
                    }
                }
                jsonObject.put("dksjmx", dksjmxList);

                /*if (dksjmxList != null && dksjmxList.size()>0){
                    Map<String,List<KhywxxDksjmxPad>> map = new HashMap<>();
                    for (int i = 0; i < dksjmxList.size(); i++) {
                        dksjmxList.get(i).setDkxt(sysDictService.queryDictTextByKey("dkxt", dksjmxList.get(i).getDkxt()));
                        dksjmxList.get(i).setDbfs(sysDictService.queryDictTextByKey("dbfs", dksjmxList.get(i).getDbfs()));
                        dksjmxList.get(i).setDkpz(dksjmxList.get(i).getDkpz() == null ? "" : sysDictService.queryDictTextByKey("dkzl", dksjmxList.get(i).getDkpz()));
                        dksjmxList.get(i).setDyzrr(dksjmxList.get(i).getDyzrr() == null ? "" : sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmxList.get(i).getDyzrr()));
                        if (!StringUtils.isEmpty(dksjmxList.get(i).getKhjlbz())) {
                            dksjmxList.get(i).setKhjlbz(sysDictService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh", dksjmxList.get(i).getKhjlbz()));
                        }
                    }
                    map.put("dksjmx", dksjmxList);
                    jsonArray.add(map);
                }*/

                QueryWrapper<LoanBwdkSjmx> bwdk = new QueryWrapper<>();
                bwdk.eq("zjhm", khhmcxx.getZjhm());
                List<LoanBwdkSjmx> bwdkList = loanBwdkSjmxService.list(bwdk);
                for (LoanBwdkSjmx loanBwdkSjmx : bwdkList) {
                    loanBwdkSjmx.setJgdm(loanBwdkSjmx.getJgdm() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", loanBwdkSjmx.getJgdm()));
                    loanBwdkSjmx.setZrbwlxShow(loanBwdkSjmx.getZrbwlx() == null ? "" : sysDictService.queryDictTextByKey("zrbwlx", loanBwdkSjmx.getZrbwlx().toString()));
                }
                jsonObject.put("bwdk", bwdkList);

                QueryWrapper<KhywxxSjyhPad> sjyh = new QueryWrapper<>();
                sjyh.eq("zjhm", khhmcxx.getZjhm());
                List<KhywxxSjyhPad> sjyhList = khywxxSjyhPadMapper.list(sjyh);
                for (KhywxxSjyhPad khywxxSjyhPad : sjyhList) {
                    khywxxSjyhPad.setOpenJgdm(khywxxSjyhPad.getOpenJgdm() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khywxxSjyhPad.getOpenJgdm()));
                    khywxxSjyhPad.setOpenType(khywxxSjyhPad.getOpenType() == null ? "" : sysDictService.queryDictTextByKey("sjyh_khlx", khywxxSjyhPad.getOpenType()));
                    khywxxSjyhPad.setStatus(khywxxSjyhPad.getStatus() == null ? "" : sysDictService.queryDictTextByKey("khywxx_kxhzt", khywxxSjyhPad.getStatus()));
                }
                jsonObject.put("sjyh", sjyhList);

                /*if (sjyhList != null && sjyhList.size()>0){
                    Map<String,KhywxxSjyhPad> map = new HashMap<>();
                    sjyhList.get(0).setOpenJgdm(sysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz", sjyhList.get(0).getOpenJgdm()));
                    sjyhList.get(0).setOpenType(sysDictService.queryDictTextByKey("sjyh_khlx", sjyhList.get(0).getOpenType()));
                    sjyhList.get(0).setStatus(sysDictService.queryDictTextByKey("khywxx_kxhzt", sjyhList.get(0).getStatus()));
                    jsonObject.put("sjyh", sjyhList);
                    map.put("sjyh", sjyhList.get(0));
                    jsonArray.add(map);
                }*/
                QueryWrapper<KhywxxEtc> etc = new QueryWrapper<>();
                etc.eq("zjhm", khhmcxx.getZjhm());
                List<KhywxxEtc> etcList = khywxxEtcMapper.list(etc);
                for (KhywxxEtc khywxxEtc : etcList) {
                    khywxxEtc.setOpenJgdm(khywxxEtc.getOperJgdm() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "ywjgdm", khywxxEtc.getOpenJgdm()));
                    khywxxEtc.setOperYggh(khywxxEtc.getOperYggh() == null ? "" : sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", khywxxEtc.getOperYggh()));
                    khywxxEtc.setZhlx(khywxxEtc.getZhlx() == null ? "" : sysDictService.queryDictTextByKey("etc_zhlx", khywxxEtc.getZhlx()));
                    khywxxEtc.setStatus(khywxxEtc.getStatus() == null ? "" : sysDictService.queryDictTextByKey("etc_zhlx", khywxxEtc.getStatus()));
                }
                jsonObject.put("etc", etcList);
               /* if (etcList != null && etcList.size()>0){
                    Map<String,KhywxxEtc> map = new HashMap<>();
                    etcList.get(0).setOpenJgdm(etcList.get(0).getOperJgdm() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",etcList.get(0).getOpenJgdm()));
                    etcList.get(0).setOperYggh(etcList.get(0).getOperYggh() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",etcList.get(0).getOperYggh()));
                    etcList.get(0).setZhlx(etcList.get(0).getZhlx() == null ? "" : sysDictService.queryDictTextByKey("etc_zhlx", etcList.get(0).getZhlx()));
                    etcList.get(0).setStatus(etcList.get(0).getStatus() == null ? "" : sysDictService.queryDictTextByKey("etc_zhlx", etcList.get(0).getStatus()));
                    map.put("etc",etcList.get(0));
                    jsonArray.add(map);
                }*/

                //信用卡
                QueryWrapper<Xyk> xykQueryWrapper = new QueryWrapper<>();
                xykQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
                List<Xyk> xykList = xykService.list(xykQueryWrapper);
                jsonObject.put("xykList", xykList);
                return Result.ok(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
        return Result.ok("没有数据");
    }

    /**
     * 查询商户信息
     *
     * @param hhbm
     * @return
     */
    @GetMapping(value = "/queryShxxByHhbm")
    public Result<?> queryShxxByHhbm(@Param("hhbm") String hhbm) {
        List<Shxq> shxqs = shxqService.selectByShxx(hhbm);
        return Result.ok(shxqs);
    }

    @GetMapping(value = "/queryJtxxByHhbm")
    public Result<?> queryJtxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            if(hhbm!=null&&hhbm!=""){
                QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("hhbm", hhbm);
                khhmcQueryWrapper.orderByAsc("yhzgx");

                List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);

                List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
                for (int i = 0; i < list.size(); i++) {
                    NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
                    Nhxq nhxq = list.get(i);
                    BeanUtils.copyProperties(nhxq, nhJtcyxx1);
                    nhJtcyxx1.setLxfs(nhxq.getSjhm());
                    nhJtcyxx1.setDz(nhxq.getZz());
                    String yhzgx = nhJtcyxx1.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", nhJtcyxx1.getYhzgx());
                    nhJtcyxx1.setYhzgx(yhzgx);
                    String xb = nhJtcyxx1.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", nhJtcyxx1.getXb());
                    String sfsw = nhJtcyxx1.getSfsw() == null ? " " : sysDictService.queryDictTextByKey("sfbz", nhJtcyxx1.getSfsw());
                    String xtpdjg = nhJtcyxx1.getXtpdjg() == null ? " " : sysDictService.queryDictTextByKey("xtpdjg", nhJtcyxx1.getXtpdjg().toString());
                    nhJtcyxx1.setXb(xb);
                    nhJtcyxx1.setSfsw(sfsw);
                    nhJtcyxx1.setSfdb(nhxq.getSfdbh());
                    nhJtcyxx1.setXtpdjg(xtpdjg);
                    nhJtcyxx.add(nhJtcyxx1);

                    List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
                    if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
                        Ywhywwlxx ywhywwlxx = ywhywwlxxes.get(0);
                        System.out.println(ywhywwlxx);
                        if (QydmEnums.TIANYI.getQydmCode().equals(getRedisQydm())) {
                            //先判断贷款余额
                            //if (ywhywwlxx.getDkye() != null && ywhywwlxx.getDkye().intValue() > 0){
                            if (nhJtcyxx.size() > 0) {
                                ywhywwlxxes.get(0).setId(list.get(i).getId());
                                BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i),new String[]{"khmc"});
                                nhJtcyxx.get(i).setBlye();
                            }

                            String s1 = ywhywwlxxService.endDate(nhxq.getZjhm());
                            if (StringUtils.isNotEmpty(s1)) {
                                nhJtcyxx.get(i).setEndDate(s1);
                            }
                            //}
                        } else {
                            if (nhJtcyxx.size() > 0) {
                                ywhywwlxxes.get(0).setId(list.get(i).getId());
                                BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i),new String[]{"khmc"});
                                nhJtcyxx.get(i).setBlye();
                            }
                        }
                    }

                    //天易-背靠背初评-如果是存量客户则展示最新一笔贷款的贷款金额、贷款余额、贷款支行
                    if(QydmEnums.TIANYI.getQydmCode().equals(getRedisQydm())){
                        List<DkxxVo> dkxxVoList=khglNhhzxxglService.getJtcyDkxxByZjhm(list.get(i).getZjhm());
                        if (CollUtil.isNotEmpty(dkxxVoList)) {
                            DkxxVo dkxxVo=dkxxVoList.get(0);
                            if (StringUtils.isNotBlank(dkxxVo.getDkzh())) {
                                nhJtcyxx.get(i).setDkzh(dkxxVo.getDkzh());
                            }
                            if (dkxxVo.getOnedkje() != null) {
                                nhJtcyxx.get(i).setOnedkje(dkxxVo.getOnedkje());
                            }
                            if (dkxxVo.getOnedkye() != null) {
                                nhJtcyxx.get(i).setOnedkye(dkxxVo.getOnedkye());
                            }
                        }
                    }

                    //判断是否开通他行社保卡
                    QueryWrapper<Sjxfsj> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("zjhm", nhxq.getZjhm());
                    queryWrapper.ne("yhmc", "农村信用合作社");
                    List<Sjxfsj> sjxfsjList = sjxfsjService.list(queryWrapper);
                    if (!sjxfsjList.isEmpty()) {
                        nhJtcyxx.get(i).setSfktthsbk("1");
                    }
                    //判断是否开通聚合商户
                    try {
                   /* QueryWrapper<TgacsTpsMchntInfo> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("legal_person_id", nhxq.getZjhm());
                    List<TgacsTpsMchntInfo> jhsh = tgacsTpsMchntInfoService.list(queryWrapper1);
                    if (!jhsh.isEmpty()) {
                        nhJtcyxx.get(i).setSfktjhzf("1");
                    }*/
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (QydmEnums.TIANYI.getQydmCode().equals(getRedisQydm())) {
                        //判断是不是已授信对象
                        if (StringUtils.isNotBlank(nhxq.getZjhm())) {
                            String ysxdx = nhbkbpyService.getYsxdx(nhxq.getZjhm());
                            if (StringUtils.isNotBlank(ysxdx))
                                nhJtcyxx1.setSfsxdx(true);
                        }
                    }

                    nhJtcyxx1.setHhbm(nhxq.getHhbm());
                    //祁阳匹配是否导入惠农快贷
                    nhJtcyxx1.setSfdrhnkd("2");
                    QueryWrapper<TjfxHnkd> tjfxHnkdQueryWrapper=new QueryWrapper<>();
                    tjfxHnkdQueryWrapper.eq("khsfzhm",nhxq.getZjhm());
                    List<TjfxHnkd> tjfxHnkdList=tjfxHnkdService.list(tjfxHnkdQueryWrapper);
                    if (tjfxHnkdList.size()>0){
                        nhJtcyxx1.setSfdrhnkd("1");
                    }
                }
                if (list != null && list.size() > 0) {
                    return Result.ok(nhJtcyxx);
                }
            }else{
                return Result.ok("户号编码为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
        return Result.ok("没有数据");
    }

    /**
     * 添加
     *
     * @param khglNhhzxxgl
     * @return
     */
    @AutoLog(value = "1-添加")
    @ApiOperation(value = "1-添加", notes = "1-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody KhglNhhzxxgl khglNhhzxxgl) {
        khglNhhzxxglService.save(khglNhhzxxgl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khglNhhzxxgl
     * @return
     */
    @AutoLog(value = "1-编辑")
    @ApiOperation(value = "1-编辑", notes = "1-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody KhglNhhzxxgl khglNhhzxxgl) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        khglNhhzxxgl.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
        khglNhhzxxgl.setUpTm(DateUtil.formatDateTime("HHmmss"));
        khglNhhzxxglService.updateById(khglNhhzxxgl);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1-通过id删除")
    @ApiOperation(value = "1-通过id删除", notes = "1-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        khglNhhzxxglService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "1-批量删除")
    @ApiOperation(value = "1-批量删除", notes = "1-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.khglNhhzxxglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }


    @GetMapping(value = "/getHzxxByHhbm")
    public Result<?> getHzxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            if (hhbm != null) {
                QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
                nhhzxxglQueryWrapper.eq("hhbm", hhbm);
                List<KhglNhhzxxgl> list = khglNhhzxxglService.list(nhhzxxglQueryWrapper);
                if (list != null && list.size() > 0) {
                    return Result.ok(list.get(0));
                } else {
                    return Result.ok("未找到数据！");
                }
            } else {
                return Result.error("未获取到户号编码！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("通过户号编码获取户主信息失败！" + e.getMessage());
            return Result.error("查询家庭信息失败，请联系系统管理员！");
        }
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1-通过id查询")
    @ApiOperation(value = "1-通过id查询", notes = "1-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getById(id);
        return Result.ok(khglNhhzxxgl);
    }

    /**
     * 通过证件号码去花名册查询家庭信息
     *
     * @param
     * @return
     */
    @GetMapping(value = "/queryPyxx")
    public Result<?> queryPyxx(@RequestParam(name = "nhid", required = false) String nhid,
                               @RequestParam(name = "hzid", required = false) String hzid,
                               @RequestParam(name = "id", required = false) String id) {
        Nhxq nhxq = null;
        KhglNhhzxxgl khglNhhzxxgl = null;
        String qybm = sysDicService.queryByCode("101001").getValue();
        if (StringUtils.isNotEmpty(nhid)) {
            QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
            nhxqQueryWrapper.eq("id", nhid);
            nhxq = nhxqService.getOne(nhxqQueryWrapper);
        }
        if (StringUtils.isNotEmpty(hzid) || StringUtils.isNotEmpty(id)) {
            QueryWrapper<KhglNhhzxxgl> khglNhhzxxglQueryWrapper = new QueryWrapper<>();
            khglNhhzxxglQueryWrapper.eq("id", StringUtils.isNotEmpty(hzid) ? hzid : id);
            khglNhhzxxgl = khglNhhzxxglService.getOne(khglNhhzxxglQueryWrapper);
        }
        List<Nhbkbpy> result = new ArrayList<>();

        try {
            //第二种
            /*Base64.Decoder decoder = Base64.getDecoder();
            String pwdDe = new String(decoder.decode(zjhm),"UTF-8");
            System.out.println("解密："+pwdDe);*/
            /*if (StringUtils.isEmpty(khglNhhzxxgl.getSxdxzjh())) {
                return Result.error("未设置授信对象！");
            }*/
            QueryWrapper<Nhbkbpy> pyxxQueryWrapper = new QueryWrapper<Nhbkbpy>();
            if (!QybmEnum.TIANYI.getQybm().equals(qybm)) {
                if (khglNhhzxxgl.getSxdxzjh() != null) {
                    pyxxQueryWrapper.eq("zjhm", khglNhhzxxgl.getSxdxzjh());
                }
                pyxxQueryWrapper.eq("hhbm", khglNhhzxxgl.getHhbm());
            } else {
                pyxxQueryWrapper.eq("hhbm", nhxq == null ? khglNhhzxxgl.getHhbm() : nhxq.getHhbm());
            }
            pyxxQueryWrapper.orderByDesc("lrsj");
            List<Nhbkbpy> list = iNhbkbpyService.list(pyxxQueryWrapper);
            if (CollUtil.isNotEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    Nhbkbpy nhbkbpy = list.get(i);
                    if (nhbkbpy.getPyls() != null) {
                        Integer pyls = nhbkbpy.getPyls();
                        System.out.println("===========>pyls");
                        System.out.println(pyls);
                        String s = Convert.numberToChinese(pyls, false);
                        nhbkbpy.setPylsVal("第" + s + "轮");
                    } else {
                        String s = Convert.numberToChinese(i + 1, false);
                        System.out.println("sssssss===");
                        System.out.println(s);
                        nhbkbpy.setPylsVal("第" + s + "轮");
                    }
                    result.add(nhbkbpy);
                }
            }

            return Result.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询评议信息错误！", e);
            return Result.error("查询评议信息错误！");
        }
    }

    /**
     * Pad端-工作平台-我的评议-分页列表查询
     *
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "Pad端-工作平台-我的评议-分页列表查询")
    @ApiOperation(value = "Pad端-工作平台-我的评议-分页列表查询", notes = "Pad端-工作平台-我的评议-分页列表查询")
    @GetMapping(value = "/pyxxlist")
    public Result<?> queryPyxxList(Nhbkbpy Pyxx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Nhbkbpy> queryWrapper = QueryGenerator.initQueryWrapper(Pyxx, req.getParameterMap());
        queryWrapper.in("lrr", getLoginUser().getUsername());
        queryWrapper.orderByDesc("pysj", "qydm", "zjhm");
        Page<Nhbkbpy> page = new Page<Nhbkbpy>(pageNo, pageSize);
        IPage<Nhbkbpy> pageList = nhbkbpyService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    @GetMapping(value = "/queryPyyxx")
    public Result<?> queryPyyxx(@Param("qydm") String qydm) {
        QueryWrapper<Pyyxx> pyxxQueryWrapper = new QueryWrapper<Pyyxx>();
        if (!StringUtils.isEmpty(qydm)) {
            pyxxQueryWrapper.eq("qydm", qydm);
        }
        List<Pyyxx> list = iPyyxxService.list(pyxxQueryWrapper);
        return Result.ok(list);
    }

    /**
     * 查询我的授信信息
     *
     * @param nhpjsxxx
     * @return
     */
    @GetMapping(value = "/queryNhsxxxList")
    public Result<?> queryNhsxxxList(CamsZcsxNhpjsxxxPad nhpjsxxx,
                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     HttpServletRequest req) {
        QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = QueryGenerator.initQueryWrapper(nhpjsxxx, req.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        queryWrapper.eq("lrr", sysUser.getUsername());
        queryWrapper.orderByDesc("up_dt");
        Page<CamsZcsxNhpjsxxxPad> page = new Page<CamsZcsxNhpjsxxxPad>(pageNo, pageSize);
        IPage<CamsZcsxNhpjsxxxPad> pageList = iCamsZcsxNhpjsxxxPadService.page(page, queryWrapper);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(pageList).toString());
        JSONArray records = jsonObject.getJSONArray("records");
        if (records != null && records.size() > 0) {
            for (int i = 0; i < records.size(); i++) {
                JSONObject sxxx = records.getJSONObject(i);
                //查询对应的联系方式
                QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("zjhm", sxxx.getString("zjhm"));
                List<Nhxq> khhmcxxList = nhxqService.list(khhmcQueryWrapper);

                if (khhmcxxList != null && khhmcxxList.size() > 0) {
                    String lxfs = khhmcxxList.get(0).getSjhm();
                    sxxx.put("lxfs", lxfs);
                }
            }
        }
        return Result.ok(jsonObject);
    }

    /**
     * 保存回访信息
     *
     * @param yxdy
     * @param khmc
     * @param zjhm
     */
    protected void saveYxglKhhfxxb(String yxdy, String khmc, String zjhm, String kksszh, String zfyy,String zhpfr) {
        Date hfrq = DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd"), "yyyy-MM-dd");

        this.saveYxglKhhfxxb(yxdy, khmc, zjhm, hfrq, getWorkNo(), getUsername(), kksszh, zfyy,zhpfr);
    }

    /**
     * 保存回访信息
     *
     * @param yxdy
     * @param khmc
     * @param zjhm
     */
    protected void saveYxglKhhfxxb(String yxdy, String khmc, String zjhm, Date hfrq, String workNo, String userName, String khsszh, String zfyy,String zhpfr) {
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
        khhfxxb.setZhpfr(zhpfr);
        khhfxxb.setSjly("2");
        khhfxxb.setLrr(userName);
        yxglKhhfxxbService.save(khhfxxb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param khglNhhzxxgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, KhglNhhzxxgl khglNhhzxxgl) {
        return super.exportXls(request, khglNhhzxxgl, KhglNhhzxxgl.class, "1");
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
        return super.importExcel(request, response, KhglNhhzxxgl.class);
    }

    /**
     * 获取户号编码
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryHhbm", method = RequestMethod.GET)
    public Result<?> hhbm(@RequestParam(name = "id", required = true) String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("hhbm", sysDictService.queryhhbm("seq_hhbm.nextval"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(jsonObject);
    }

    /**
     * 获取是否必须上传附件
     *
     * @param csbm
     * @return
     */
    @RequestMapping(value = "/querySfbxScfj", method = RequestMethod.GET)
    public Result<?> querySfbxScfj(@RequestParam(name = "csbm", required = true) String csbm) {
        JSONObject jsonObject = new JSONObject();
        try {
            String csz = iTjfxCsszService.queryCszByCsbm(csbm);
            jsonObject.put("csz", csz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(jsonObject);
    }

    /**
     * 祁阳新加逻辑，背靠背评议至少需要两人评议，否则无法进行下一步
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryPyjl")
    public Result<?> queryPyjl(@RequestParam(name = "id", required = true) String id) {
        QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Nhxq khhmcxx = nhxqService.getOne(queryWrapper);
        QueryWrapper<Nhbkbpy> pyxxQueryWrapper = new QueryWrapper<Nhbkbpy>();
        pyxxQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
        pyxxQueryWrapper.eq("hhbm", khhmcxx.getHhbm());
        pyxxQueryWrapper.orderByDesc("lrsj");
        List<Nhbkbpy> list = iNhbkbpyService.list(pyxxQueryWrapper);
        if (list.size() != 0) {
            return Result.ok(list);
        } else {
            List<Nhbkbpy> list1 = new ArrayList<>();
            return Result.ok(list1);
        }

    }

    /**
     * 修改戶主
     *
     * @param jsonArray
     * @return
     */
    @AutoLog(value = "1-修改戶主")
    @ApiOperation(value = "1-修改戶主", notes = "1-修改戶主")
    @RequestMapping(value = "/editHhxx", method = RequestMethod.POST)
    public Result<?> editHhxx(@RequestBody JSONArray jsonArray) {
        List<Nhxq> khglKhhmcxxList = JSONArray.parseArray(JSON.toJSONString(jsonArray), Nhxq.class);
        for (Nhxq khhmcxx : khglKhhmcxxList) {
            Nhxq khhmcxx1 = new Nhxq();
            UpdateWrapper<Nhxq> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("hhbm", khhmcxx.getHhbm()).eq("id", khhmcxx.getId());
            //如果前台设置了与户主关系为户主则设置户主
            if (khhmcxx.getYhzgx().equalsIgnoreCase("1")) {
                khhmcxx1.setYhzgx(khhmcxx.getYhzgx());
                khhmcxx1.setSfhz("1");
                khhmcxx1.setSjhm(khhmcxx.getSjhm());
                khhmcxx1.setZz(khhmcxx.getZz());
                khhmcxx1.setSfsw(khhmcxx.getSfsw());
                nhxqService.update(khhmcxx1, updateWrapper);

                //由于前台传过来的证件号码属于脱敏状态特需根据id查询证件号
                QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", khhmcxx.getId());
                Nhxq khglKhhmcxx = nhxqService.getOne(queryWrapper);

                //修改户主表信息
                KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
                khglNhhzxxgl.setHzzjhm(khglKhhmcxx.getZjhm());
                khglNhhzxxgl.setHzxm(khglKhhmcxx.getKhmc());
                UpdateWrapper<KhglNhhzxxgl> nhhzxxglWrapper = new UpdateWrapper<KhglNhhzxxgl>();
                nhhzxxglWrapper.eq("hhbm", khhmcxx.getHhbm());
                khglNhhzxxglService.update(khglNhhzxxgl, nhhzxxglWrapper);
            } else {
                khhmcxx1.setYhzgx(khhmcxx.getYhzgx());
                khhmcxx1.setSfhz("2");
                khhmcxx1.setSjhm(khhmcxx.getSjhm());
                khhmcxx1.setZz(khhmcxx.getZz());
                khhmcxx1.setSfsw(khhmcxx.getSfsw());
                nhxqService.update(khhmcxx1, updateWrapper);

            }

        }

        /*QueryWrapper<KhglKhhmcxx> khhmcxxQueryWrapper = new QueryWrapper<>();
        khhmcxxQueryWrapper.eq("hhbm",jsonObject.getString("hhbm"));
        List<KhglKhhmcxx> khglKhhmcxxList = khglKhhmcxxPadService.list(khhmcxxQueryWrapper);
        for (KhglKhhmcxx khhmcxx : khglKhhmcxxList) {
            //修改花名册中原户主信息
            if (khhmcxx.getZjhm().equalsIgnoreCase(jsonObject.getString("yhzzjhm"))){
                khhmcxx.setSfhz("2");
                khhmcxx.setYhzgx(jsonObject.getString("yyhzgx"));
                UpdateWrapper<KhglKhhmcxx> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("zjhm",jsonObject.getString("yhzzjhm"));
                khglKhhmcxxPadService.update(khhmcxx,updateWrapper);

            }
            //修改花名册中新户主信息
            if (khhmcxx.getId().equalsIgnoreCase(jsonObject.getString("xhzzjhm"))){
                khhmcxx.setSfhz("1");
                khhmcxx.setYhzgx("1");
                UpdateWrapper<KhglKhhmcxx> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id",jsonObject.getString("xhzzjhm"));
                khglKhhmcxxPadService.update(khhmcxx,updateWrapper);

                //修改户主表信息
                KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
                khglNhhzxxgl.setHzzjhm(khhmcxx.getZjhm());
                khglNhhzxxgl.setHzxm(khhmcxx.getKhmc());
                UpdateWrapper<KhglNhhzxxgl> nhhzxxglWrapper = new UpdateWrapper<KhglNhhzxxgl>();
                nhhzxxglWrapper.eq("hhbm",jsonObject.getString("hhbm"));
                khglNhhzxxglService.update(khglNhhzxxgl,nhhzxxglWrapper);
            }
        }*/
        //修改花名册中原户主信息
       /* KhglKhhmcxx khhmcxx = new KhglKhhmcxx();
        khhmcxx.setSfhz("2");
        khhmcxx.setYhzgx(jsonObject.getString("yyhzgx"));
        UpdateWrapper<KhglKhhmcxx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("hhbm",jsonObject.getString("hhbm")).eq("zjhm",jsonObject.getString("yhzzjhm"));
        khglKhhmcxxPadService.update(khhmcxx,updateWrapper);*/


        return Result.ok("修改成功！");
    }

    @GetMapping(value = "/queryJtcyxx")
    public Result<?> queryHhbm(@Param("hhbm") String hhbm) {
        try {
            QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
            khhmcQueryWrapper.eq("hhbm", hhbm);
            khhmcQueryWrapper.orderByAsc("yhzgx");
            List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
            if (list != null && list.size() > 0) {
                return Result.ok(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
        return Result.ok("没有数据");
    }

    @GetMapping(value = "/queryRyList")
    public Result<?> queryRyList() {
        List<String> lrryList = vKhglNhhzxxgl12.getLrryList();
        List<String> pfrList = vKhglNhhzxxgl12.getPfrList();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lrryList", lrryList);
        jsonObject.put("pfrList", pfrList);
        return Result.ok(jsonObject);
    }

    /**
     * 根据网格查询所有客户信息
     *
     * @return
     */
    @GetMapping(value = "/getNhListByWgbh")
    public Result<?> getNhListByWgbh(String wgbh, String khmc) {
        QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(wgbh))
            nhxqQueryWrapper.inSql("wgbh", "select wgbh from yxdygl_main where wgbh='" + wgbh + "' or parent_id='" + wgbh + "'");
        nhxqQueryWrapper.like("khmc", khmc);
        List<Nhxq> nhxqList = nhxqService.list(nhxqQueryWrapper);
        return Result.ok(nhxqList);
    }

    @GetMapping(value = "/queryHywxxByHhbm")
    public Result<?> queryHywxxByHhbm(String hhbm) {
        QueryWrapper<KhxxglYwhywwlxxH> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hhbm", hhbm);
        List<KhxxglYwhywwlxxH> ywhywwlxxHList = khxxglYwhywwlxxHService.list(queryWrapper);
        if (ywhywwlxxHList.isEmpty()) {
            return Result.error("未查询到数据");
        } else {
            return Result.ok(ywhywwlxxHList.get(0));
        }
    }

    //农户新型农业主体数据查询  用证件号去查Hhbm
    @GetMapping(value = "/queryJtxxByZjhm")
    public Result<?> queryJtxxByZjhm(@Param("zjhm") String zjhm,
                                     @Param("id") String id) {
        if (StringUtils.isBlank(zjhm))
            return Result.error("证件号码不能为空!");
        if (StringUtils.isBlank(id))
            return Result.error("id不能为空!");
        try {
            QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
            khhmcQueryWrapper.inSql("hhbm", "select max(hhbm) from KHXXGL_KHXQ_NH where zjhm = '" + zjhm + "'");
            khhmcQueryWrapper.orderByAsc("yhzgx");
            List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
            if (CollUtil.isEmpty(list)) {
                Nhxq nhxq = new Nhxq();
                nhxq.setZjhm(zjhm);
                list.add(nhxq);
            }
            List<XxnyztYsxdx> xxnyztYsxdxes = new ArrayList<XxnyztYsxdx>();
            for (int i = 0; i < list.size(); i++) {
                Nhxq nhxq = list.get(i);
                List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(nhxq.getZjhm());
                if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
                    //要筛选出来
                    Ywhywwlxx ywhywwlxx = ywhywwlxxes.get(0);
                    if (ywhywwlxx.getDkye() != null
                            && ywhywwlxx.getDkye().compareTo(new BigDecimal(0)) > 0
                    ) {
                        XxnyztYsxdx xxnyztYsxdx = new XxnyztYsxdx();
                        xxnyztYsxdx.setLrbz("0");
                        if (StringUtils.isNotBlank(ywhywwlxx.getKhmc()))
                            xxnyztYsxdx.setYsxdx(ywhywwlxx.getKhmc());
                        xxnyztYsxdx.setYsxdxzjhm(ywhywwlxx.getZjhm());
                        if (ywhywwlxx.getDkje() != null) {
                            xxnyztYsxdx.setYsxed(ywhywwlxx.getDkje());
                        } else {
                            xxnyztYsxdx.setYsxed(new BigDecimal(0));
                        }

                        if (ywhywwlxx.getDkye() != null) {
                            xxnyztYsxdx.setYyxed(ywhywwlxx.getDkye());
                        } else {
                            xxnyztYsxdx.setYsxed(new BigDecimal(0));
                        }

                        xxnyztYsxdxes.add(xxnyztYsxdx);
                    }
                }

            }

            //去查填写的表
            LambdaQueryWrapper<XxnyztYsxdx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(XxnyztYsxdx::getXxnyztId, id);
            List<XxnyztYsxdx> list1 = xxnyztYsxdxService.list(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(list1))
                xxnyztYsxdxes.addAll(list1);


            if (list != null && list.size() > 0) {
                return Result.ok(xxnyztYsxdxes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
        return Result.ok("没有数据");
    }

    @RequestMapping(value = "/editHzxx", method = RequestMethod.POST)
    public Result<?> editHzxx(@RequestBody KhglNhhzxxglPage page) {
        try {
            KhglNhhzxxgl khglNhhzxxgl = new KhglNhhzxxgl();
            QueryWrapper<Nhxq> hzgl = new QueryWrapper<>();
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            BeanUtils.copyProperties(page, khglNhhzxxgl);
            String username = sysUser.getUsername();
            String workNo = sysUser.getWorkNo();
            if (StringUtils.isNotEmpty(page.getZfry())) {
                //走访人员
                QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<SysUser>();
                sysUserQueryWrapper.eq("realname", page.getZfry());
                sysUserQueryWrapper.or().eq("username", page.getZfry());
                sysUserQueryWrapper.or().eq("work_no", page.getZfry());
                List<SysUser> sysUserList = sysUserService.list(sysUserQueryWrapper);
                if (!sysUserList.isEmpty()) {
                    SysUser zfryUser = sysUserList.get(0);
                    username = zfryUser.getUsername();
                    workNo = zfryUser.getWorkNo();
                }
            }
            khglNhhzxxgl.setZfry(page.getZfry());

            UpdateWrapper<KhglNhhzxxgl> khglNhhzxxglUpdateWrapper = new UpdateWrapper<>();
            khglNhhzxxglUpdateWrapper.eq("hhbm", page.getHhbm());
            khglNhhzxxglService.update(khglNhhzxxgl, khglNhhzxxglUpdateWrapper);

            saveYxglKhhfxxb(page.getSsyxdy(), page.getHzxm(), page.getHzzjhm(), page.getZfrq(), workNo, username, page.getSszh(), page.getZfyy(),page.getZhpfr());

            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = getRedisQydm();
            if (false) {
                QueryWrapper<KhxxglGrsxlxmxNh> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("zjhm", page.getHzzjhm());
                KhxxglGrsxlxmxNh khxxglGrsxlxmxNh = khxxglGrsxlxmxNhService.getOne(queryWrapper1);
                if (khxxglGrsxlxmxNh == null || vKhglNhhzxxgl12.init3(page.getHhbm()) == 0) {
                    vKhglNhhzxxgl12.init1(page.getHhbm());
                    //同步oracle到impala
                    EtlUtil.SHcallEtlRc(10, true, false, false, "tjfx_cssz", "idap");
                    //同步之前删除impala数据中khxxgl_grsxlxmx_nh表数据
                    khxxglGrsxlxmxNh1Service.delNhgrsxlxmx();
                    EtlUtil.SHcallEtlRc(10, true, false, true, "khxxgl_grsxlxmx_nh", "idap", page.getHhbm());
                    khxxglGrsxlxmxNhService.delNhgrsxlxmxByHhbm(page.getHhbm());
                    //调用python脚本
                    sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxsxlxtj.py --hhbm " + page.getHhbm() + "'");
                    //同步impala到oracle
                    sshUtil.execShell("sh /home/exportdata/P_TJFX_SXLXTJ_EXPORT.sh");
                    sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_SXLXTJ_IMPORT.sh");
                    vKhglNhhzxxgl12.init2(page.getHhbm());
                }
            }
            vKhglNhhzxxgl12.init(page.getHhbm(), page.getHzzjhm(), workNo, username, DateUtil.format(page.getZfrq(), "yyyyMMdd"));
        } catch(JeecgBootException jbe) {
            log.error("修改家庭信息失败", jbe);
            return Result.error(jbe.getMessage());
        } catch (Exception e) {
            log.error("修改家庭信息失败", e);
            return Result.error("系统错误，修改家庭信息失败！");
        }
        return Result.ok("修改成功");
    }


    @RequestMapping(value = "/saveHzxx", method = RequestMethod.POST)
    public Result<?> saveHzxx(@RequestBody KhglNhhzxxgl page) {
        // 前台传过来加密的数据  我这里不修改
        if (StringUtils.isNotBlank(page.getHzzjhm()) && page.getHzzjhm().contains("*"))
            page.setHzzjhm(null);

        boolean update = service.updateById(page);
        if (update) {
            return Result.ok();
        }
        return Result.error("保存失败");
    }

    @Autowired
    ListToDictUtil listToDictUtil;

    @RequestMapping(value = "/queryHzByHhbm", method = RequestMethod.GET)
    public Result<?> saveHzxx(String hhbm) {
        LambdaQueryWrapper<KhglNhhzxxgl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(KhglNhhzxxgl::getHhbm, hhbm);
        List<KhglNhhzxxgl> list = service.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            List list1 = listToDictUtil.parseDictText(list);
            return Result.ok(list1.get(0));
        }
        return Result.ok();
    }

    /**
     * 祁阳:农户走访一天只能一个人修改保存
     *
     * @param hzid
     * @return
     */
    @GetMapping("/getHzzfxxByOne")
    public Result<?> getHzzfxxByOne(@RequestParam("hzid") String hzid,@RequestParam("hfid")String hfid) {
        KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getById(hzid);
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String nowStringDate = DateUtil.format(new Date(), "yyyy-MM-dd");
        //获取回访信息
        QueryWrapper<HfsjtjNh> queryWrapper =new QueryWrapper<HfsjtjNh>();
        queryWrapper.eq("id",hfid);
        queryWrapper.eq("hfrq",DateUtil.string2Date(nowStringDate,"yyyy-MM-dd"));
        List<HfsjtjNh> hfsjtjNhList=hfsjtjNhService.list(queryWrapper);
        if (CollUtil.isEmpty(hfsjtjNhList)){
            return Result.ok(false);
        }
//        List<String> hfrqList=hfsjtjNhList.stream().map(HfsjtjNh::getHfrq).map(e->DateUtil.format(e,"yyyyMMdd")).collect(Collectors.toList());
        List<String> ygghList=hfsjtjNhList.stream().map(HfsjtjNh::getYggh).collect(Collectors.toList());

        if (ygghList.contains(sysUser.getWorkNo())) {
            return Result.ok(false);
        }
        return Result.ok(true);
    }

}
