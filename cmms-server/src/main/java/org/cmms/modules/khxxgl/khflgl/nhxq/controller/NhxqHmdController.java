package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.api.vo.ResultConstant;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.common.util.superSearch.DataFilterUtil;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.service.IFjglService;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khxxgl.khflgl.bbpyinfo.bbpyjcxx.entity.BkbpyHxxVo;
import org.cmms.modules.khxxgl.khflgl.bbpyinfo.bbpyjcxx.entity.BkbpyJcxx;
import org.cmms.modules.khxxgl.khflgl.bbpyinfo.bbpyjcxx.service.IBkbpyJcxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhxqHmd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhxqImportVo;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhxqPersonVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqHmdService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.pad.nhxxgl.entity.*;
import org.cmms.modules.pad.nhxxgl.service.*;
import org.cmms.modules.pad.pyxx.entity.Pyfjxx;
import org.cmms.modules.pad.pyxx.service.IPyfjxxService;
import org.cmms.modules.pad.shxxgl.entity.Xyk;
import org.cmms.modules.pad.shxxgl.service.IXykService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 农户信息
 * @Author: jeecg-boot
 * @Date: 2021-12-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "农户信息黑名单")
@RestController
@RequestMapping("/khflgl/nhxqhmd")
public class NhxqHmdController extends JeecgController<NhxqHmd, INhxqHmdService> {
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private INhxqHmdService nhxqHmdService;
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
    private IYxdyglMainService yxdyglMainService;
    @Autowired
    ListToDictUtil listToDictUtil;
    @Autowired
    private INhbkbpyService nhbkbpyService;
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;


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
    public Result<?> queryPageList(NhxqHmd nhxq,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "sfcj", required = false) String sfcj,
                                   @RequestParam(name = "sfscfj", required = false) String sfscfj,
                                   HttpServletRequest req) {
        QueryWrapper<NhxqHmd> queryWrapper = QueryGenerator.initQueryWrapper(nhxq, req.getParameterMap());
        Page<NhxqHmd> page = new Page<NhxqHmd>(pageNo, pageSize);
        if (StringUtils.isNotEmpty(sfcj)) {
            if ("1".equals(sfcj)) {
                queryWrapper.ne("lrr", "system");
            } else if ("2".equals(sfcj)) {
                queryWrapper.eq("lrr", "system");
            }
        }

        if (StringUtils.isNotEmpty(sfscfj)) {
            if ("1".equals(sfscfj)) {
                queryWrapper.inSql("zjhm", "select zjhm from cams_jbxx_nhzllb");
            } else if ("2".equals(sfscfj)) {
                queryWrapper.notInSql("zjhm", "select zjhm from cams_jbxx_nhzllb");
            }
        }
        IPage<NhxqHmd> pageList = nhxqHmdService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 根据户号编码查询批量评议记录
     */
    @GetMapping(value = "/queryPlpyByHhbm")
    public Result<?> queryPlpyByHhbm(@RequestParam(name = "hhbm", required = true) String hhbm){
        QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hhbm",hhbm);
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
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        QueryWrapper queryWrapperZzbz = new QueryWrapper();
        queryWrapperZzbz.eq("ywjgdm", nhxq.getJgdm());
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
        nhxq.setSszh(hrBasOrganization.getZzbz());
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

    /**
     * 导出excel
     *
     * @param request
     * @param nhxq
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NhxqHmd nhxq) {
        return super.exportXls(request, nhxq, NhxqHmd.class, "农户信息");
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
//                String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
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
//            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
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
        List<Nhxq> hByZjhm = nhxqService.getHByZjhm(zjhm);
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
                    if (!org.apache.commons.lang.StringUtils.isEmpty(khywxxDksjmxPad.getKhjlbz())) {
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
                        nhbkbpy.setSfljqk(sysDictService.queryDictTextByKey("sfljqk", nhbkbpy.getSfljqk()));
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

            QueryWrapper<Pyfjxx> pyfjxxQueryWrapper=new QueryWrapper<>();
            pyfjxxQueryWrapper.eq("hhbm",hhbm);
            pyfjxxQueryWrapper.eq("zjhm",zjhm);
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
                String result =  File.separator + System.currentTimeMillis() + ".zip";
                String path = uploadpath + result;
                ZipUtil.zip(FileUtil.file(path), false, strings);
                return Result.ok(result);
            }else {
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
                    String result =  File.separator + System.currentTimeMillis() + ".zip";
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
    public ModelAndView exportXls(String wgbh,HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        SysDic sysDic = sysDicService.queryByCode("101002");
        SysDic qybmSysDic = sysDicService.queryByCode("101001");
        String qybm = qybmSysDic.getValue();
        QueryWrapper<BkbpyJcxx> queryWrapper1=new QueryWrapper();
        //同时查询下级网格数据 分配到组的地区可以直接选择村导出所有数据
        String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + wgbh + "' or parent_id='" + wgbh + "'";
        queryWrapper1.select("hhbm,max(sfbysx) as sfbysx,max(sfsx) as sfsx,max(bysxqx) as bysxqx,max(zcdkye) as zcdkye,max(bnbldkye) as bnbldkye,max(bwbldkye) as bwbldkye,max(sfss) as sfss,max(sffx) as sffx,max(sfsd) as sfsd,max(sfwbdbh) as sfwbdbh,max(ckye) as ckye,max(ckrpye) as ckrpye,max(cknrpye) as cknrpye ")
//                .eq("wgbh",wgbh)
                .and(i -> i.inSql("wgbh", sqlSswg))
                .groupBy("hhbm");
        List<BkbpyJcxx> Hxxlist = bkbpyJcxxService.list(queryWrapper1);
        List<BkbpyHxxVo> list =new LinkedList<>();
        List<BkbpyHxxVo> bysxList =new LinkedList<>(); //不予授信
        List<BkbpyHxxVo> sxwyxList =new LinkedList<>(); //授信未用信
        List<BkbpyHxxVo> sxyyxList =new LinkedList<>(); //授信已用信
        for(BkbpyJcxx bkbpyJcxx :Hxxlist){
            BkbpyHxxVo bkbpyHxxVo =new BkbpyHxxVo();
            BeanUtils.copyProperties(bkbpyJcxx, bkbpyHxxVo);
            QueryWrapper<BkbpyJcxx> queryWrapper=new QueryWrapper();
            queryWrapper.eq("hhbm",bkbpyJcxx.getHhbm());
            List<BkbpyJcxx> bkbpyJcxxList = bkbpyJcxxService.list(queryWrapper);
//            if(bkbpyJcxxList.size()<6){
//                int y=6-bkbpyJcxxList.size();
//                for(int x=0;x<=y;x++){
//                    BkbpyJcxx  bkbpyJcxx1=new BkbpyJcxx();
//                    bkbpyJcxxList.add(bkbpyJcxx1);
//                }
//            }
            //证件号码过滤
            DataFilterUtil.filterZjhmValue(bkbpyJcxxList);
            bkbpyHxxVo.setBkbpyJcxxList(listToDictUtil.parseDictText(bkbpyJcxxList));
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
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("list", listToDictUtil.parseDictText(list));
        map.put("bysxList", listToDictUtil.parseDictText(bysxList));
        map.put("sxwyxList", listToDictUtil.parseDictText(sxwyxList));
        map.put("sxyyxList", listToDictUtil.parseDictText(sxyyxList));
        YxdyglMain byId = yxdyglMainService.getById(wgbh);
        if(byId!=null){
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(byId.getZzbz());
            YxdyglMain byId1 = yxdyglMainService.getById(byId.getParentId());
            map.put("wgmc", byId.getWgmc());
            map.put("pwgmc", byId1!=null?byId1.getWgmc():"");
            map.put("zzjc", hrBasOrganization!=null?hrBasOrganization.getZzjc():"");
        }else{
            map.put("wgmc", "");
            map.put("pwgmc","");
            map.put("zzjc","");

        }
        String tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("农商行背靠背评议表.xls");
        if (QybmEnum.NINGYUAN.getQybm().equals(qybm) ||
                QybmEnum.LANSHAN.getQybm().equals(qybm)
            ) {
            tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("宁远农商行背靠背评议表.xls");
        } else if (QybmEnum.SHUANGFENG.getQybm().equals(qybm) ||
                QybmEnum.XINHUA.getQybm().equals(qybm)) {
            tempFileName = org.cmms.common.util.FileUtil.getTempFilePath("双峰农商行背靠背评议表.xls");
        }
        DateFormat dateFormat = new SimpleDateFormat(DateUtil.chineseDtFormat);
        String dateStr=DateUtil.getDateString(new Date(),dateFormat);
        map.put("date",dateStr);
        map.put("bank",sysDic.getValue());
        mv.addObject(JxlsConstants.FILE_NAME, sysDic.getValue()+"背靠背评议表");
        mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, tempFileName);
        mv.addObject(JxlsConstants.SAVE_FILE_NAME, exportpath+"/农商行背靠背评议表.xls");
        mv.addObject(JxlsConstants.MAP_DATA, map);

        return mv;
    }


    @RequestMapping("/impHjsj")
    public Result<?> impHjsj(@RequestBody JSONObject jsonObject){

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
                Workbook excelWorkbook = ExcelUtils.getExcelWorkbook(fileInputStream,file.getName());
                Sheet sheetAt = excelWorkbook.getSheetAt(0);
                int lastRowNum = sheetAt.getLastRowNum();
                if (lastRowNum > 4){
                    Row row = null;
                    row = sheetAt.getRow(1);
                    Cell cell = row.getCell(0);
                    String wgbh = null;
                    if (cell != null){
                        String trim = cell.getStringCellValue().trim();
                        LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                        lambdaQueryWrapper.eq(YxdyglMain::getWgmc,trim);
                        lambdaQueryWrapper.eq(YxdyglMain::getWgxz,"2");
                        List<YxdyglMain> list = yxdyglMainService.list(lambdaQueryWrapper);
                        if (CollUtil.isNotEmpty(list)){
                            if (list.size() > 1){
                                return Result.error("所属村重复");
                            }else {
                                wgbh = list.get(0).getWgbh();
                            }
                        }else {
                            return Result.error("所属村查询为空");
                        }

                    }else {
                        return Result.error("所属村不能为空");
                    }

                    for (int i = 4; i < lastRowNum - 1; i++) {
                        row = sheetAt.getRow(i);
                        String hhbm = null;
                        String zjhm = null;
                        String khmc = null;
                        String yhzgx = null;
                        String hjdz = null;
                         if (row.getCell(0) != null){
                             hhbm = row.getCell(0).getStringCellValue().trim();
                         }else {
                             continue;
                         }

                        if (row.getCell(1) != null){
                             khmc = row.getCell(1).getStringCellValue().trim();
                        }else {
                            continue;
                        }
                        if (row.getCell(2) != null){
                            zjhm = row.getCell(2).getStringCellValue().trim();
                            if (StringUtils.isNotBlank(zjhm)){
                                if (!IdcardUtil.isValidCard(zjhm)){
                                    continue;
                                }
                            }else {
                                continue;
                            }
                        }else {
                            continue;
                        }
                        if (row.getCell(3) != null){
                            yhzgx = row.getCell(3).getStringCellValue().trim();
                            String s = DictTextToValusUtil.yhzgx2(yhzgx);
                            yhzgx = s;
                        }else {
                            continue;
                        }
                        if (row.getCell(4) != null){
                            hjdz = row.getCell(4).getStringCellValue().trim();
                        }else {
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
                        nhxqService.save(nhxq);

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
}
