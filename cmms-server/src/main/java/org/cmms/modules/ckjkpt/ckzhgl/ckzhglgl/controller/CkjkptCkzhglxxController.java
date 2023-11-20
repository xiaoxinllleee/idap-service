package org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.util.oConvertUtils;
import org.cmms.config.MybatisPlusConfig;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.entity.CkjkptCkzhglxx;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.entity.CkzhglxxHistory;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.service.ICkzhglxxHistoryService;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.service.IHrBasStaffPostVOService;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.vo.CkjkptCkzhglxxVo;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.service.ICkjkptCkzhglxxService;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.vo.HrBasStaffPostVO;
import org.cmms.modules.ckjkpt.jcyj.zhckpldyj.entity.CkjkptZhckpldgl;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasOranizationVo;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;

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
 * @Description: 存款账号关联管理
 * @Author: jeecg-boot
 * @Date: 2021-10-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "存款账号关联管理")
@RestController
@RequestMapping("/ckzhglgl/ckjkptCkzhglxx")
public class CkjkptCkzhglxxController extends JeecgController<CkjkptCkzhglxx, ICkjkptCkzhglxxService> {

    @Autowired
    private ICkzhglxxHistoryService iCkzhglxxHistoryService;
    @Autowired
    private ICkjkptCkzhglxxService ckjkptCkzhglxxService;
    @Autowired
    private IHrBasStaffPostVOService iHrBasStaffPostVOService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IHrBasPostService hrBasPostService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * @param ckzhglxxHistory
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "存款账号关联管理-分页列表查询")
    @ApiOperation(value = "存款账号关联管理-分页列表查询", notes = "存款账号关联管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkzhglxxHistory ckzhglxxHistory,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req,
                                   String sjzzbzQuery,
                                   String zzbzQuery,
                                   String tjyf) throws ParseException {
        //如果查询的不是本月的数据，则查询历史数据
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String SysMonths = dateFormat.format(new Date()).substring(0, 7).concat("-01");
        ckzhglxxHistory.setSjzzbz(null);
        ckzhglxxHistory.setSjzzmc(null);
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckzhglxxHistory, req.getParameterMap());
        if(StringUtils.isNotEmpty(zzbzQuery)) {
            queryWrapper.eq("zzbz", zzbzQuery);
        }
        if (StringUtils.isNotEmpty(sjzzbzQuery)) {
            queryWrapper.inSql("zzbz", "select zzbz from V_HR_BAS_ORGANIZATION where sjzzbz_r="+ sjzzbzQuery);
        }
        if (tjyf != null && tjyf.equals(SysMonths)) {

            IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptCkzhglxxService.class,ckjkptCkzhglxxService,pageNo,pageSize,queryWrapper,"glid");
            return Result.ok(pageList);
        } else {
            queryWrapper.eq("fiscal_date",tjyf.replaceAll("-",""));
            IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkzhglxxHistoryService.class,iCkzhglxxHistoryService,pageNo,pageSize,queryWrapper,"glid");
            return Result.ok(pageList);
        }
    }
    /**
     * 分配存款账号
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "认领存款账号")
    @ApiOperation(value = "认领存款账号", notes = "存款账号关联管理")
    @PostMapping(value = "/preservation")
    public Result<?> preservation(@RequestBody JSONObject jsonObject) {
        List<CkjkptCkzhglxx> jsonList = (List<CkjkptCkzhglxx>) jsonObject.get("array");
        ObjectMapper objectMapper = new ObjectMapper();
        List<CkjkptCkzhglxx> list = objectMapper.convertValue(jsonList, new TypeReference<List<CkjkptCkzhglxx>>() {
        });
        String zzbz = jsonObject.getString("zzbz");
        String gwbz = jsonObject.getString("gwbz");
        String yggh = jsonObject.getString("yggh");
        String rlyf = jsonObject.getString("rlyf");
        String gyh = jsonObject.getString("gyh");
        String khjlbz = jsonObject.getString("khjlbz");
        String ywjgdm = jsonObject.getString("ywjgdm");
        Date date = new Date();
        if (-1 == date.compareTo(DateUtil.parseDateFormat(rlyf, "yyyy-MM-dd"))) {
            return Result.error("选择的月份必须小于当前月");
        }
        Date nowDate = DateUtil.string2Date(org.cmms.modules.util.DateUtil.currentTimestamp2String("yyyy-MM") + "-01", "yyyy-MM-dd");
        Date fpDate = DateUtil.parseDateFormat(rlyf, "yyyy-MM-dd");
        while (fpDate.compareTo(nowDate) <= 0) {
            CkjkptCkzhglxx ckzhglxx = new CkjkptCkzhglxx();
            String tjyfStr = org.cmms.modules.util.DateUtil.date2String(fpDate, "yyyy-MM-dd");
            String ckzhTableName = "Ckjkpt_ckzhglxx" + "_" + tjyfStr.substring(0, 4) + tjyfStr.substring(5, 7);
            MybatisPlusConfig.myTableNameCkzhglxx.set(ckzhTableName);
            if (fpDate.compareTo(nowDate) == 0) {
                ckzhTableName = "Ckjkpt_ckzhglxx";
                MybatisPlusConfig.myTableNameCkzhglxx.set(ckzhTableName);
            }
            try {
                fpDate = DateUtil.getFirstday_Month(fpDate, 1);
            } catch (Throwable t) {
                t.printStackTrace();
                break;
            }
            for (int i = 0; i < list.size(); i++) {
                CkjkptCkzhglxx ckjkptCkzhglxx = list.get(i);
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("zzbz", zzbz);
                queryWrapper.eq("gwbz", gwbz);
                queryWrapper.eq("yggh", yggh);
                queryWrapper.eq("ckzh", ckjkptCkzhglxx.getCkzh());
                CkjkptCkzhglxx one = ckjkptCkzhglxxService.getOne(queryWrapper);
                if (one != null) {
                    one.setZzbz(zzbz);
                    one.setGwbz(gwbz);
                    one.setYggh(yggh);
                    one.setGyh(gyh);
                    one.setKhjlbz(khjlbz);
                    one.setGyh(gyh);
                    one.setXgsj(new Timestamp(System.currentTimeMillis()));
                    one.setXgczy(getLoginUser().getRealname());
                    QueryWrapper query = new QueryWrapper();
                    query.eq("glid", ckzhglxx.getGlid());
                    ckjkptCkzhglxxService.update(ckzhglxx, query);
                } else {
                    ckzhglxx.setGwbz(gwbz);
                    ckzhglxx.setZzbz(zzbz);
                    ckzhglxx.setYggh(yggh);
                    ckzhglxx.setCkzh(ckjkptCkzhglxx.getCkzh());
                    ckzhglxx.setZhmc(ckjkptCkzhglxx.getZhmc());
                    ckzhglxx.setZhxz(ckjkptCkzhglxx.getZhxz());
                    ckzhglxx.setGyh(gyh);
                    ckzhglxx.setKhjlbz(khjlbz);
                    ckzhglxx.setYwjgdm(ywjgdm);
                    ckzhglxx.setCkrpye(ckjkptCkzhglxx.getCkrpye());
                    ckzhglxx.setNckrpye(ckjkptCkzhglxx.getNckrpye());
                    //获取最新的id
                    String maxId = ckjkptCkzhglxxService.getMaxId();
                    ckzhglxx.setGlid(Long.valueOf(maxId));
                    ckzhglxx.setGlbz(1);
                    ckzhglxx.setYxbz(1);
                    ckzhglxx.setGlbl(BigDecimal.valueOf(100));
                    ckzhglxx.setLrczy(getLoginUser().getRealname());
                    ckzhglxx.setLrbz(1);
                    ckzhglxx.setLrsj(new Timestamp(System.currentTimeMillis()));
                    ckjkptCkzhglxxService.save(ckzhglxx);
                }
            }

        }
        return Result.ok();
    }

    /**
     * 获取认领列表
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "认领列表")
    @ApiOperation(value = "认领", notes = "待分配存款帐号管理")
    @PostMapping(value = "/getListClaim")
    public Result<?> getListClaim(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        String ywjgdm = jsonObject.getString("zzbz");
        String khjlbz = jsonObject.getString("khjlbz");
        String rglx = jsonObject.getString("rglx");
        String gwbz = jsonObject.getString("gwbz");
        String yggh = jsonObject.getString("yggh");
        List<HrBasStaffPostVo> list = hrBasPostService.getListClaim(ywjgdm, rglx, gwbz, khjlbz,yggh);
        return Result.ok(list);
    }

    /**
     * 添加
     *
     * @return
     */
    @AutoLog(value = "存款账号关联管理-添加")
    @ApiOperation(value = "存款账号关联管理-添加", notes = "存款账号关联管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody JSONObject jsonObject) {
        String ckzh = jsonObject.getString("ckzh");
        String zhxz = jsonObject.getString("zhxz");
        String zzbz = jsonObject.getString("zzbz");
        String tjyf = jsonObject.getString("tjyf");
        String gwbz = jsonObject.getString("gwbz");
        String yggh = jsonObject.getString("yggh");
        String zhmc = jsonObject.getString("zhmc");
        String khjlbz = jsonObject.getString("khjlbz");
        String gyh = jsonObject.getString("gyh");
        String ywjgdm = jsonObject.getString("ywjgdm");
        Long glbl = Long.valueOf(jsonObject.getString("glbl"));


        if (zhxz.equals("2")) {
            List<CbsInvmBase> list = ckjkptCkzhglxxService.viewCkzhExit("", "", ckzh);
            CbsInvmBase base = list.get(0);
            List<HrBasOranizationVo> hrListYWJGDM = hrBasPostService.ygghInfo(base.getBranchNo(), "");
            HrBasOranizationVo hrYWJGDM = hrListYWJGDM.get(0);
            List<HrBasOranizationVo> hrListzzbz = hrBasPostService.ygghInfo("", zzbz);
            HrBasOranizationVo hrZZBZ = hrListzzbz.get(0);
            if (StringUtils.isEmpty(hrZZBZ.getSjzzbzR()) && StringUtils.isEmpty(hrYWJGDM.getSjzzbzR()) && !hrZZBZ.getSjzzbzR().equals(hrYWJGDM)) {
                return Result.error("失败,只能添加员工所在支行的存款账号！");
            }
        }

        Date date = new Date();
        if (-1 == date.compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
            return Result.error("选择的月份必须小于当前月");
        }
        String maxId = ckjkptCkzhglxxService.getMaxId();
        Date nowDate = DateUtil.string2Date(org.cmms.modules.util.DateUtil.currentTimestamp2String("yyyy-MM") + "-01", "yyyy-MM-dd");
        Date tjyfDate = DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd");
        while (tjyfDate.compareTo(nowDate) <= 0) {
            String tjyfStr = org.cmms.modules.util.DateUtil.date2String(tjyfDate, "yyyy-MM-dd");
            try {
                tjyfDate = DateUtil.getFirstday_Month(tjyfDate, 1);
            } catch (Throwable tx) {
                tx.printStackTrace();
                break;
            }
            CkjkptCkzhglxx ckzhglxx = new CkjkptCkzhglxx();
            //如果添加的不是当月数据就改表名
            String tableName = "CKJKPT_CKZHGLXX";
            if (tjyfStr != null && !tjyfStr.equals(DateUtil.formatDateTime("yyyy-MM", System.currentTimeMillis()) + "-01")) {
                tableName = tableName += "_" + tjyfStr.substring(0, 4) + tjyfStr.substring(5, 7);
            }
            MybatisPlusConfig.myTableNameCkzhglxx.set(tableName);

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("ckzh", ckzh);
            queryWrapper.eq("yxbz", 1);
            List<CkjkptCkzhglxx> listCkzhglxx = ckjkptCkzhglxxService.list(queryWrapper);
            if (listCkzhglxx.size() > 0) {
                for (int i = 0; i < listCkzhglxx.size(); i++) {
                    glbl += Integer.valueOf(listCkzhglxx.get(i).getGlbl() + "");
                }
            }
            if (glbl > 100) {
                return Result.error("关联失败，本账号系统中有效的关联比率为" + glbl +
                        "%，本次添加的关联比率不能超过：" + (100 - glbl) + "%！");
            }
            ckjkptCkzhglxxService.getDkzh(ckzh);
            ckzhglxx.setGwbz(gwbz);
            ckzhglxx.setZzbz(zzbz);
            ckzhglxx.setYggh(yggh);
            ckzhglxx.setCkzh(ckzh);
            ckzhglxx.setZhmc(zhmc);
            ckzhglxx.setZhxz(Integer.valueOf(zhxz));
            ckzhglxx.setGyh(gyh);
            ckzhglxx.setKhjlbz(khjlbz);
            ckzhglxx.setYwjgdm(ywjgdm);
            ckzhglxx.setGlid(Long.valueOf(maxId));
            ckzhglxx.setGlbl(BigDecimal.valueOf(Double.valueOf(glbl)));
            ckzhglxx.setYxbz(1);
            ckzhglxx.setGlbz(1);
            ckzhglxx.setLrbz(1);
            ckzhglxx.setLrczy(getLoginUser().getRealname());
            ckzhglxx.setLrsj(new Date());
            ckjkptCkzhglxxService.save(ckzhglxx);
        }

        System.out.println(jsonObject);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "存款账号关联管理-编辑")
    @ApiOperation(value = "存款账号关联管理-编辑", notes = "存款账号关联管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody JSONObject jsonObject) {
        System.out.println("统计月份：" + jsonObject.getString("tjyf"));
        System.out.println("JSONObject: " + jsonObject);
        CkjkptCkzhglxx ckzhglxx = JSONObject.toJavaObject(jsonObject, CkjkptCkzhglxx.class);
        String tjyf = jsonObject.getString("tjyf");
        System.out.println("存款账号关联信息: " + ckzhglxx);
        Date nowDate = DateUtil.string2Date(org.cmms.modules.util.DateUtil.currentTimestamp2String("yyyy-MM") + "-01", "yyyy-MM-dd");
        Date fpDate = DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd");
        String ckzhTableName = "Ckjkpt_ckzhglxx" + "_" + tjyf.substring(0, 4) + tjyf.substring(5, 7);
        MybatisPlusConfig.myTableNameCkzhglxx.set(ckzhTableName);
        if (fpDate.compareTo(nowDate) == 0) {
            ckzhTableName = "Ckjkpt_ckzhglxx";
            MybatisPlusConfig.myTableNameCkzhglxx.set(ckzhTableName);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ckzh", ckzhglxx.getCkzh());
        queryWrapper.eq("yxbz", 1);
        List<CkjkptCkzhglxx> list = ckjkptCkzhglxxService.list();
        Long glbl = 0L;
        for (int i = 0; i < list.size(); i++) {
            glbl += Long.valueOf(list.get(i).getGlbl() + "");
        }
        if (glbl - Long.valueOf(ckzhglxx.getGlbl() + "") > 100L) {
            return Result.error("关联失败，本账号系统中有效的关联比率为" + glbl + "%，本次添加的关联比率不能超过：" + (100 - glbl) + "%！");
        }
        queryWrapper = new QueryWrapper();
        queryWrapper.eq("glid", ckzhglxx.getGlid());
        queryWrapper.eq("ckzh", ckzhglxx.getCkzh());
        queryWrapper.eq("yxbz", ckzhglxx.getYxbz());

        ckzhglxx.setXgsj(new Date());
        ckzhglxx.setXgczy(getLoginUser().getRealname());
        ckjkptCkzhglxxService.update(ckzhglxx, queryWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "存款账号关联管理-通过id删除")
    @ApiOperation(value = "存款账号关联管理-通过id删除", notes = "存款账号关联管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        QueryWrapper<CkjkptCkzhglxx> queryWrapper = new QueryWrapper<CkjkptCkzhglxx>();
        queryWrapper.eq("glid", id);
        ckjkptCkzhglxxService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "存款账号关联管理-批量删除")
    @ApiOperation(value = "存款账号关联管理-批量删除", notes = "存款账号关联管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.ckjkptCkzhglxxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存款账号关联管理-通过id查询")
    @ApiOperation(value = "存款账号关联管理-通过id查询", notes = "存款账号关联管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CkjkptCkzhglxx ckjkptCkzhglxx = ckjkptCkzhglxxService.getById(id);
        return Result.ok(ckjkptCkzhglxx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param ckjkptCkzhglxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CkjkptCkzhglxx ckjkptCkzhglxx,
                                  String sjzzbzQuery,
                                  String zzbzQuery,
                                  String tjyf) {
        String title = "存款账号关联管理";
        // Step.1 组装查询条件
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptCkzhglxx, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String selections = request.getParameter("selections");
        String rowKey = request.getParameter("rowKey");

        //20211201 过滤选中数据
        //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            if(oConvertUtils.isNotEmpty(rowKey)){
                queryWrapper.in(rowKey,selectionList);
            }else{
                queryWrapper.in("ID",selectionList);
            }
        }
        //如果查询的不是本月的数据，则查询历史数据
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String SysMonths = dateFormat.format(new Date()).substring(0, 7).concat("-01");
        ckjkptCkzhglxx.setSjzzbz(null);
        ckjkptCkzhglxx.setSjzzmc(null);
        if(StringUtils.isNotEmpty(zzbzQuery)) {
            queryWrapper.eq("zzbz", zzbzQuery);
        }
        if (StringUtils.isNotEmpty(sjzzbzQuery)) {
            queryWrapper.inSql("zzbz", "select zzbz from V_HR_BAS_ORGANIZATION where sjzzbz_r="+ sjzzbzQuery);
        }
        if (tjyf != null && tjyf.equals(SysMonths)) {
            List<CkjkptCkzhglxx> exportList = ckjkptCkzhglxxService.list(queryWrapper);
            ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
            mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
            mv.addObject(NormalExcelConstants.CLASS, CkjkptCkzhglxx.class);
            mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
            mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
            return mv;
        } else {
            queryWrapper.eq("fiscal_date",tjyf.replaceAll("-",""));
            List<CkzhglxxHistory> exportList = iCkzhglxxHistoryService.list(queryWrapper);
            ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
            mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
            mv.addObject(NormalExcelConstants.CLASS, CkzhglxxHistory.class);
            mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
            mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
            return mv;
        }



        //20211201 BY LIUXIANGQUN 过滤选择的数据改为执行接sql  in  查询
        // 过滤选中数据
		/*if (oConvertUtils.isNotEmpty(selections)) {
			List<String> selectionList = Arrays.asList(selections.split(","));
			exportList = pageList.stream().filter(item -> selectionList.contains(getId(item))).collect(Collectors.toList());
		} else {
			exportList = pageList;
		}*/

        // Step.3 AutoPoi 导出Excel

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
        return super.importExcel(request, response, CkjkptCkzhglxx.class);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/extract")
    public Result<?> extract() {
        // P_DFPCKZH_INIT
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> parm = new HashMap<>();
            String maxId = ckjkptCkzhglxxService.getMaxId();
            parm.put("ckzhglxx_max_glid",maxId);
            boolean falg = false;
            Integer pm = ckjkptCkzhglxxService.judgeExtract();
            if (pm != 0) {
                parm.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_dfpckzh_init_one");
                // count_ckjkpt_dfpckzh_init_one
                falg = EtlUtil.callEtl("ckjkpt_common_init", parm, 15);
            } else {
                parm.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_dfpckzh_init_two");
                // count_ckjkpt_dfpckzh_init_two
                falg = EtlUtil.callEtl("ckjkpt_common_init", parm, 15);
            }
            result.setSuccess(falg);
        } else {
            try {
                ckjkptCkzhglxxService.extract();
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                System.out.println(e);
                log.error("提取失败", e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 判读是否有对应的存款账号
     *
     * @return
     */
    @RequestMapping(value = "/judgeCkzh", method = RequestMethod.GET)
    public Result<?> judgeCkzh(@Param("ckzh") String ckzh) {
        String maxDataDate = ckjkptCkzhglxxService.getMaxDataDate();
        String b_date = maxDataDate.substring(0, 6) + "01";
        String e_date = maxDataDate.substring(0, 6) + "31";
        List<CbsInvmBase> list = ckjkptCkzhglxxService.viewCkzhExit(b_date, e_date, ckzh);
        if (list.size() > 0) {
            String zhxz = "";
            CbsInvmBase base = list.get(0);
            if (base.getAcctGrp().equals("C") && base.getAcctDesc().equals("T")) {
                zhxz = "4";
            } else if (base.getAcctGrp().equals("P") && base.getAcctDesc().equals("T")) {
                zhxz = "3";
            } else if (base.getAcctGrp().equals("C") && base.getAcctDesc().equals("S")) {
                zhxz = "2";
            } else {
                zhxz = "1";
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("zhmc", base.getCustName());
            map.put("jgdm", base.getBranchNo());
            map.put("zhzt", base.getCurrStatus());
            map.put("zjhm", base.getIdentNo());
            map.put("zhxz", zhxz);
            return Result.ok(map);
        }
        return Result.error("未找到对应的存款账号");
    }

    /**
     * 校验是否存在需要转移的有效存款关联信息
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "存款账号关联管理-校验是否存在需要转移的有效存款关联信息")
    @ApiOperation(value = "存款账号关联管理-校验是否存在需要转移的有效存款关联信息", notes = "存款账号关联管理-校验是否存在需要转移的有效存款关联信息")
    @RequestMapping(value = "/TransferCheck")
    public Result<?> TransferCheck(@RequestBody(required = false) JSONObject jsonObject) {
        Result<JSONObject> result = new Result<>();
        JSONObject object = new JSONObject();
        String TableName = jsonObject.getString("TableName");
        String check = jsonObject.getString("check");
        String tjyf = jsonObject.getString("tjyf");
        String zzbz = jsonObject.getString("zzbz");
        String gwbz = jsonObject.getString("gwbz");
        String yggh = jsonObject.getString("yggh");
        String ckzh = jsonObject.getString("ckzh");
        String ywjgdm = jsonObject.getString("ywjgdm");
        String glbz = jsonObject.getString("glbz");
        String ckyeBegin = jsonObject.getString("CkyeBegin");
        String ckyeEnd = jsonObject.getString("CkyeEnd");
        String ckrpyeBegin = jsonObject.getString("CkrpyeBegin");
        String ckrpyeEnd = jsonObject.getString("CkrpyeEnd");
        String nckrpyeBegin = jsonObject.getString("NckrpyeBegin");
        String nckrpyeEnd = jsonObject.getString("NckrpyeEnd");
        try {
            JSONObject subObject = new JSONObject();
            QueryWrapper<HrBasStaffPostVO> staffPostqueryWrapper = new QueryWrapper<>();
            staffPostqueryWrapper.eq("zzbz", zzbz);
            staffPostqueryWrapper.eq("gwbz", gwbz);
            staffPostqueryWrapper.eq("yggh", yggh);
            List<HrBasStaffPostVO> staffPostVOList = iHrBasStaffPostVOService.list(staffPostqueryWrapper);
            if (staffPostVOList.size() > 0) {
                //获取员工姓名，员工工号，客户经理标识
                subObject.put("ygxm", staffPostVOList.get(0).getYgxm());
                subObject.put("yggh", staffPostVOList.get(0).getYggh());
                subObject.put("khjlbz", staffPostVOList.get(0).getKhjlbz());
                object.put("staffpost", subObject);

                // MybatisPlusConfig.myTableNameCkzhglxx.set(TableName);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                String SysMonString = dateFormat.format(new Date()).substring(0, 7).concat("-01");
                if (tjyf.equalsIgnoreCase(SysMonString)) {
                    QueryWrapper<CkjkptCkzhglxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("yxbz", "1");
                    queryWrapper.eq("zzbz", zzbz);
                    queryWrapper.eq("gwbz", gwbz);
                    queryWrapper.eq("yggh", yggh);
                    if (!StringUtils.isBlank(ckzh)) queryWrapper.eq("ckzh", ckzh);
                    if (!StringUtils.isBlank(ywjgdm)) queryWrapper.eq("ywjgdm", ywjgdm);
                    if (!StringUtils.isBlank(glbz)) queryWrapper.eq("glbz", glbz);
                    if (!StringUtils.isBlank(ckyeBegin)) queryWrapper.ge("ckye", Double.parseDouble(ckyeBegin)).and(wrapper -> wrapper.le("ckye", Double.parseDouble(ckyeBegin)));
                    if (!StringUtils.isBlank(ckyeEnd)) queryWrapper.ge("ckye", Double.parseDouble(ckyeEnd)).and(wrapper -> wrapper.le("ckye", Double.parseDouble(ckyeEnd)));
                    if (!StringUtils.isBlank(ckrpyeBegin)) queryWrapper.ge("ckrpye", Double.parseDouble(ckrpyeBegin)).and(wrapper -> wrapper.le("ckrpye", Double.parseDouble(ckrpyeBegin)));
                    if (!StringUtils.isBlank(ckrpyeEnd)) queryWrapper.ge("ckrpye", Double.parseDouble(ckrpyeEnd)).and(wrapper -> wrapper.le("ckrpye", Double.parseDouble(ckrpyeEnd)));
                    if (!StringUtils.isBlank(nckrpyeBegin)) queryWrapper.ge("nckrpye", Double.parseDouble(nckrpyeBegin)).and(wrapper -> wrapper.le("nckrpye", Double.parseDouble(nckrpyeBegin)));
                    if (!StringUtils.isBlank(nckrpyeEnd)) queryWrapper.ge("nckrpye", Double.parseDouble(nckrpyeEnd)).and(wrapper -> wrapper.le("nckrpye", Double.parseDouble(nckrpyeEnd)));
                    List<CkjkptCkzhglxx> ckzhglxxList = ckjkptCkzhglxxService.list(queryWrapper);
                    if (ckzhglxxList.size() > 0) {
                        int i = 0;
                        subObject = new JSONObject();
                        // 转移条件含带了`存款账号`
                        if ("true".equalsIgnoreCase(check)) {
                            for (CkjkptCkzhglxx ckzhglxx : ckzhglxxList) {
                                JSONObject JsonObject = new JSONObject();
                                // MybatisPlusConfig.myTableNameCkzhglxx.set(TableName);
                                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                QueryWrapper<CkjkptCkzhglxx> ckzhglQueryWrapper = new QueryWrapper<>();
                                ckzhglQueryWrapper.eq("yxbz", "1");
                                ckzhglQueryWrapper.ne("glid", ckzhglxx.getGlid());
                                ckzhglQueryWrapper.eq("ckzh", ckzhglxx.getCkzh());
                                ckzhglQueryWrapper.eq("data_date", dateFormat.parse(tjyf));
                                List<CkjkptCkzhglxx> ckzhglList = ckjkptCkzhglxxService.list(ckzhglQueryWrapper);
                                if (ckzhglList.size() <= 0) {
                                    JsonObject.put("yglbl", "0");
                                } else {
                                    JsonObject.put("yglbl", ckzhglList.get(0).getGlbl().toString());
                                }
                                JsonObject.put("ckzh", ckzhglxx.getCkzh());
                                JsonObject.put("zhmc", ckzhglxx.getZhmc());
                                JsonObject.put("yggh", ckzhglxx.getYggh());
                                JsonObject.put("zhxz_dictText", iSysDictService.queryDictTextByKey("zhxz", ckzhglxx.getZhxz().toString()));
                                JsonObject.put("ygxm", iSysDictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", ckzhglxx.getYggh()));
                                JsonObject.put("zzmc", iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", ckzhglxx.getZzbz()));
                                JsonObject.put("gwmc", iSysDictService.queryTableDictTextByKey("hr_bas_post", "gwmc", "gwbz", ckzhglxx.getGwbz().toString()));
                                subObject.put(i+"", JsonObject);
                                i += 1;
                            }
                            object.put("checktrue", subObject);
                        }
                        // 转移条件未含带`存款账号`
                        subObject = new JSONObject();
                        subObject.put("rowCount", String.valueOf(ckzhglxxList.size()));
                        subObject.put("zzbz", zzbz);
                        subObject.put("ywjgdm", ywjgdm);
                        subObject.put("zzmc", iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", zzbz));
                        subObject.put("gwbz", gwbz);
                        subObject.put("gwmc", iSysDictService.queryTableDictTextByKey("hr_bas_post", "gwmc", "gwbz", gwbz));
                        subObject.put("glbz", glbz);
                        object.put("checkfalse", subObject);

                        result.setSuccess(true);
                        result.setResult(object);
                    } else {
                        result.setSuccess(false);
                        result.setMessage("未查询到需要转移的有效存款关联信息！");
                    }
                } else {
                    QueryWrapper<CkzhglxxHistory> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("data_date", dateFormat.parse(tjyf));
                    queryWrapper.eq("yxbz", "1");
                    queryWrapper.eq("zzbz", zzbz);
                    queryWrapper.eq("gwbz", gwbz);
                    queryWrapper.eq("yggh", yggh);
                    if (!StringUtils.isBlank(ckzh)) queryWrapper.eq("ckzh", ckzh);
                    if (!StringUtils.isBlank(ywjgdm)) queryWrapper.eq("ywjgdm", ywjgdm);
                    if (!StringUtils.isBlank(glbz)) queryWrapper.eq("glbz", glbz);
                    if (!StringUtils.isBlank(ckyeBegin)) queryWrapper.ge("ckye", Double.parseDouble(ckyeBegin)).and(wrapper -> wrapper.le("ckye", Double.parseDouble(ckyeBegin)));
                    if (!StringUtils.isBlank(ckyeEnd)) queryWrapper.ge("ckye", Double.parseDouble(ckyeEnd)).and(wrapper -> wrapper.le("ckye", Double.parseDouble(ckyeEnd)));
                    if (!StringUtils.isBlank(ckrpyeBegin)) queryWrapper.ge("ckrpye", Double.parseDouble(ckrpyeBegin)).and(wrapper -> wrapper.le("ckrpye", Double.parseDouble(ckrpyeBegin)));
                    if (!StringUtils.isBlank(ckrpyeEnd)) queryWrapper.ge("ckrpye", Double.parseDouble(ckrpyeEnd)).and(wrapper -> wrapper.le("ckrpye", Double.parseDouble(ckrpyeEnd)));
                    if (!StringUtils.isBlank(nckrpyeBegin)) queryWrapper.ge("nckrpye", Double.parseDouble(nckrpyeBegin)).and(wrapper -> wrapper.le("nckrpye", Double.parseDouble(nckrpyeBegin)));
                    if (!StringUtils.isBlank(nckrpyeEnd)) queryWrapper.ge("nckrpye", Double.parseDouble(nckrpyeEnd)).and(wrapper -> wrapper.le("nckrpye", Double.parseDouble(nckrpyeEnd)));
                    List<CkzhglxxHistory> ckzhglxxHistoryList = iCkzhglxxHistoryService.list(queryWrapper);
                    if (ckzhglxxHistoryList.size() > 0) {
                        int i = 0;
                        subObject = new JSONObject();
                        // 转移条件含带了`存款账号`
                        if ("true".equalsIgnoreCase(check)) {
                            for (CkzhglxxHistory ckzhglxxHistory : ckzhglxxHistoryList) {
                                JSONObject JsonObject = new JSONObject();
                                // MybatisPlusConfig.myTableNameCkzhglxx.set(TableName);
                                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                QueryWrapper<CkzhglxxHistory> ckzhglQueryWrapper = new QueryWrapper<>();
                                ckzhglQueryWrapper.eq("data_date", dateFormat.parse(tjyf));
                                ckzhglQueryWrapper.eq("yxbz", "1");
                                ckzhglQueryWrapper.ne("glid", ckzhglxxHistory.getGlid());
                                ckzhglQueryWrapper.eq("ckzh", ckzhglxxHistory.getCkzh());
                                List<CkzhglxxHistory> ckzhglList = iCkzhglxxHistoryService.list(ckzhglQueryWrapper);
                                if (ckzhglList.size() <= 0) {
                                    JsonObject.put("yglbl", "0");
                                } else {
                                    JsonObject.put("yglbl", ckzhglList.get(0).getGlbl().toString());
                                }
                                JsonObject.put("ckzh", ckzhglxxHistory.getCkzh());
                                JsonObject.put("zhmc", ckzhglxxHistory.getZhmc());
                                JsonObject.put("yggh", ckzhglxxHistory.getYggh());
                                JsonObject.put("zhxz_dictText", iSysDictService.queryDictTextByKey("zhxz", ckzhglxxHistory.getZhxz().toString()));
                                JsonObject.put("ygxm", iSysDictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", ckzhglxxHistory.getYggh()));
                                JsonObject.put("zzmc", iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", ckzhglxxHistory.getZzbz()));
                                JsonObject.put("gwmc", iSysDictService.queryTableDictTextByKey("hr_bas_post", "gwmc", "gwbz", ckzhglxxHistory.getGwbz().toString()));
                                subObject.put(i+"", JsonObject);
                                i += 1;
                            }
                            object.put("checktrue", subObject);
                        }
                        // 转移条件未含带`存款账号`
                        subObject = new JSONObject();
                        subObject.put("rowCount", String.valueOf(ckzhglxxHistoryList.size()));
                        subObject.put("zzbz", zzbz);
                        subObject.put("ywjgdm", ywjgdm);
                        subObject.put("zzmc", iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", zzbz));
                        subObject.put("gwbz", gwbz);
                        subObject.put("gwmc", iSysDictService.queryTableDictTextByKey("hr_bas_post", "gwmc", "gwbz", gwbz));
                        subObject.put("glbz", glbz);
                        object.put("checkfalse", subObject);

                        result.setSuccess(true);
                        result.setResult(object);
                    } else {
                        result.setSuccess(false);
                        result.setMessage("未查询到需要转移的有效存款关联信息！");
                    }
                }
            } else {
                result.setSuccess(false);
                result.setMessage("未查询到该员工的信息！");
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("未知错误，请联系管理员处理！", throwable.getMessage());
        }
        return result;
    }

    /**
     * 查询员工信息
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "存款账号关联管理-查询员工信息")
    @ApiOperation(value = "存款账号关联管理-查询员工信息", notes = "存款账号关联管理-查询员工信息")
    @RequestMapping(value = "/EmpCheck")
    public Result<?> EmpCheck(@RequestBody(required = false) JSONObject jsonObject) {
        Result<Map<String, String>> result = new Result<>();
        Map<String, String> hashMap = new HashMap<>();
        String zzbz = jsonObject.getString("zzbz");
        String gwbz = jsonObject.getString("gwbz");
        String yggh = jsonObject.getString("yggh");
        try {
            QueryWrapper<HrBasStaffPostVO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zzbz", zzbz);
            queryWrapper.eq("gwbz", gwbz);
            queryWrapper.eq("yggh", yggh);
            List<HrBasStaffPostVO> staffPostVOList = iHrBasStaffPostVOService.list(queryWrapper);
            if (staffPostVOList.size() > 0) {
                hashMap.put("ygxm", staffPostVOList.get(0).getYgxm());
                hashMap.put("yggh", staffPostVOList.get(0).getYggh());
                hashMap.put("khjlbz", staffPostVOList.get(0).getKhjlbz());
                result.setSuccess(true);
                result.setResult(hashMap);
            } else {
                result.setSuccess(false);
                result.setMessage("未查询到该员工的信息！");
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("未知错误，请联系管理员处理！", throwable.getMessage());
        }
        return result;
    }

    /**
     * eweb:v_hr_bas_staff_post
     */
    @AutoLog(value = "员工岗位信息表-分页列表查询")
    @ApiOperation(value="员工岗位信息表-分页列表查询", notes="员工岗位信息表-分页列表查询")
    @GetMapping(value = "/staffpostlist")
    public Result<?> staffpostlist(HrBasStaffPostVO hrBasStaffPostVO,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<HrBasStaffPostVO> queryWrapper = QueryGenerator.initQueryWrapper(hrBasStaffPostVO, req.getParameterMap());
        Page<HrBasStaffPostVO> page = new Page<HrBasStaffPostVO>(pageNo, pageSize);
        IPage<HrBasStaffPostVO> pageList = iHrBasStaffPostVOService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 员工管理存款账户明细 - 转移保存
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "员工管理存款账户明细-转移保存")
    @ApiOperation(value = "员工管理存款账户明细-转移保存", notes = "员工管理存款账户明细-转移保存")
    @RequestMapping(value = "/save")
    public Result<?> save(@RequestBody JSONObject jsonObject) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String month = "";
        String tableName = "ckjkpt_ckzhglxx";
        String tjyf = jsonObject.getString("tjyf");
        String check = jsonObject.getString("check");
        if (!StringUtils.isEmpty(check)) {
            String zzbz = jsonObject.getString("zzbz");
            String gwbz = jsonObject.getString("gwbz");
            String yggh = jsonObject.getString("yggh");
            String khjlbz = jsonObject.getString("khjlbz");
            String ywjgdm = jsonObject.getString("ywjgdm");
            String glbz = jsonObject.getString("glbz");
            String newYggh = jsonObject.getString("newYggh");
            String newZzbz = jsonObject.getString("newZzbz");
            String newGwbz = jsonObject.getString("newGwbz");
            String newGyh = jsonObject.getString("newGyh");
            String newKhjlbz = jsonObject.getString("newKhjlbz");
            //校验新转移的员工工号是否存在员工岗位信息
            QueryWrapper<HrBasStaffPostVO> staffPostQueryWrapper = new QueryWrapper<>();
            staffPostQueryWrapper.eq("zzbz", newZzbz);
            staffPostQueryWrapper.eq("gwbz", newGwbz);
            staffPostQueryWrapper.eq("yggh", newYggh);
            List<HrBasStaffPostVO> staffPostList = iHrBasStaffPostVOService.list(staffPostQueryWrapper);
            if (!(staffPostList.size() > 0)) {
                return Result.error("员工工号`" + newYggh + "`在岗位信息中不存在！");
            }
            try {
                //校验所选统计月份是否小于等于当前系统月份
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date chooseTjyf = dateFormat.parse(tjyf);
                //获取系统当前月份
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, 0);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = simpleDateFormat.format(calendar.getTime());
                Date currentMonth = dateFormat.parse(currentDate);
                // 当`统计月份`等于`系统当前月份`时返回0，大于返回1，小于返回-1
                while (chooseTjyf.compareTo(currentMonth) <= 0) {
                    //System.out.println("统计月份"+ chooseTjyf);
                    month = simpleDateFormat.format(chooseTjyf);
                    tableName = tableName + "_" + month.substring(0, 4) + month.substring(5, 7);
                    if (chooseTjyf.compareTo(currentMonth) == 0) {
                        tableName = "ckjkpt_ckzhglxx";
                    }
                    CkjkptCkzhglxx update = new CkjkptCkzhglxx();
                    update.setZzbz(newZzbz);
                    update.setYggh(newYggh);
                    update.setGwbz(newGwbz);
                    update.setGyh(newGyh);
                    update.setKhjlbz(newKhjlbz);
                    update.setLrbz(2);
                    update.setGlbz(1);
                    update.setYxbz(1);
                    update.setXgsj(new Timestamp(System.currentTimeMillis()));
                    update.setXgczy(loginUser.getUsername());
                    UpdateWrapper<CkjkptCkzhglxx> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("zzbz", zzbz);
                    updateWrapper.eq("gwbz", gwbz);
                    updateWrapper.eq("yggh", yggh);
                    if (!StringUtils.isBlank(ywjgdm)) {
                        updateWrapper.eq("ywjgdm", ywjgdm);
                    }
                    if (!StringUtils.isBlank(glbz)) {
                        updateWrapper.eq("glbz", glbz);
                    }
                    boolean ok = ckjkptCkzhglxxService.update(update, updateWrapper);
                    if (!ok) {
                        return Result.error("保存失败！请联系管理员查看系统日志！");
                    }
                    chooseTjyf = dateFormat.parse(org.cmms.modules.util.DateUtil.getFirstDayOfNextMonth(month, "yyyy-MM-dd"));
                }
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        } else {
            //根据存款账号转移

        }
        Result result = new Result<>();
        return result;
    }

}
