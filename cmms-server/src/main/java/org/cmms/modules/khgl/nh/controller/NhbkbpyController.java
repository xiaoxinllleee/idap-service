package org.cmms.modules.khgl.nh.controller;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.base.entity.XTwgsxbmdTable;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DictTextToValusUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.Base64Util;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.khgl.nh.entity.*;
import org.cmms.modules.khgl.nh.service.*;
import org.cmms.modules.khgl.nh.verify.ZcsxImportVerify;
import org.cmms.modules.khgl.nh.vo.*;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.entity.KhxxglYwhywwlxxH;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.nhxxgl.service.IKhxxglYwhywwlxxHService;
import org.cmms.modules.pad.pyxx.entity.Nhplpy;
import org.cmms.modules.pad.pyxx.service.INhplpyService;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.qhywjd.qhckqk.entity.TjfxQhckqk;
import org.cmms.modules.utils.DateUtils;
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

import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.jxls.common.CellData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 背靠背评议
 * @Author: jeecg-boot
 * @Date: 2022-04-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "背靠背评议")
@RestController
@RequestMapping("/nh/nhbkbpy")
public class NhbkbpyController extends JeecgController<Nhbkbpy, INhbkbpyService> {

    @Autowired
    ICamsPlpyYsfjService camsPlpyYsfjService;
    @Autowired
    ICamsPlpyYsxqService camsPlpyYsxqService;
    @Autowired
    ICamsPlpyYsxxService camsPlpyYsxxService;
    @Autowired
    IYxdyglMainService yxdyglMainService;
    @Autowired
    IVYxdyglMainService vYxdyglMainService;
    @Autowired
    INhplpyService nhplpyService;
    @Autowired
    INhxqService nhxqService;
    @Autowired
    ISysDictService sysDictService;
    @Autowired
    private ISysDicService sysDicService;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;
    @Autowired
    private IKhxxglYwhywwlxxHService khxxglYwhywwlxxHService;
    @Autowired
    ListToDictUtil listToDictUtil;
    @Autowired
    private IVNhbkbpyService vNhbkbpyService;
    @Autowired
    private ZcsxImportVerify zcsxImportVerify;
    @Value(value = "${common.path.export}")
    private String exportpath;

    /**
     * 分页列表查询
     *
     * @param nhbkbpy
     * @param pageNo
     * @param pageSize
     * @param req
     * @param bysxType 不予授信类型：1评议不予授信；2审定不予授信
     * @return
     */
    @AutoLog(value = "背靠背评议-分页列表查询")
    @ApiOperation(value = "背靠背评议-分页列表查询", notes = "背靠背评议-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Nhbkbpy nhbkbpy,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "cfplx", required = false) String cfplx,
                                   @RequestParam(name = "bysxType",required = false) String bysxType,
                                   HttpServletRequest req) {
        //QueryWrapper<Nhbkbpy> queryWrapper = QueryGenerator.initQueryWrapper(nhbkbpy, req.getParameterMap());
        //String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + getLoginUser().getWorkNo() + "'";
        //queryWrapper.and(i -> i.inSql("qydm", sqlSswg));

        String qydm = null;
        if (StringUtils.isNotBlank(nhbkbpy.getQydm()))
            qydm = nhbkbpy.getQydm();
        nhbkbpy.setQydm(null);
        QueryWrapper<Nhbkbpy> queryWrapper = QueryGenerator.initQueryWrapper(nhbkbpy, req.getParameterMap());

        if (StringUtils.isNotBlank(qydm)) {
            queryWrapper.inSql("qydm", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "' and  " +
                    "menu_id in (" +
                    "select wgbh from yxdygl_main start with wgbh='" + qydm + "' connect by prior wgbh=parent_id )");

        } else {
            queryWrapper.inSql("qydm", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "'");
        }
        if (StringUtils.isNotEmpty(cfplx)) {
            if ("1".equals(cfplx)) {
                //初评
                queryWrapper.like("pylc","1");
            } else if ("2".equals(cfplx)) {
                queryWrapper.eq("pylc","2");
            }
        }
        if (StringUtils.isNotBlank(bysxType)){
            if ("1".equals(bysxType)){
                queryWrapper.inSql("zjhm","select zjhm from (select zjhm,pylc from CAMS_ZCSX_NHBKBPY where bysxqx is not null group by zjhm,pylc) group by zjhm having count(pylc)>1");
            } else if ("2".equals(bysxType)){
                queryWrapper.like("pylc","3");
                queryWrapper.apply("(zhsded=0)");
            }
        }
        Page<Nhbkbpy> page = new Page<Nhbkbpy>(pageNo, pageSize);
        IPage<Nhbkbpy> pageList = service.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    //抽取记录 用于后续保存
    NhbkbpyYsVO cxtj = null;
    List<String> cqjlList = null;

    @GetMapping(value = "/ysList")
    public Result<?> ysList(NhbkbpyYsVO nhbkbpy,
                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                            HttpServletRequest req) {
        List<Nhbkbpy> nhbkbpies = service.randomList(nhbkbpy);
        if (CollUtil.isNotEmpty(nhbkbpies)) {
            cqjlList = new ArrayList<>();
            nhbkbpies.forEach(s -> {
                cqjlList.add(s.getId());
            });
        }
        cxtj = nhbkbpy;
        return Result.ok(nhbkbpies);
    }

    public static final String dz = "images/plpy/";

    @PostMapping("/saveYsInfo")
    public Result<?> saveYsInfo(@RequestBody JSONObject jsonObject) {
        System.out.println(cxtj);
        System.out.println(cqjlList);
        System.out.println(jsonObject);
        String ysrhy = jsonObject.getString("ysrhy");
        String ysrqz = jsonObject.getString("ysrqz");
        String bz = jsonObject.getString("bz");
        CamsPlpyYsxx camsPlpyYsxx = new CamsPlpyYsxx();
        String id = IdUtil.fastSimpleUUID();
        if (cxtj != null && CollUtil.isNotEmpty(cqjlList)) {
            if (StringUtils.isNotBlank(cxtj.getQydm())) {
                camsPlpyYsxx.setWgbh(cxtj.getQydm());
            }
            if (cxtj.getPyls() != null) {
                camsPlpyYsxx.setPyls(cxtj.getPyls());
            }
            if (cxtj.getCqbl() != null) {
                camsPlpyYsxx.setCqbl(cxtj.getCqbl());
            }
            camsPlpyYsxx.setCreateBy(getUsername());
            camsPlpyYsxx.setYsr(getUsername());
            camsPlpyYsxx.setCreateTime(new Date());
            camsPlpyYsxx.setId(id);
            camsPlpyYsxx.setYsrqz(id);
            camsPlpyYsxx.setYsrhy(id);


            if (StringUtils.isNotBlank(ysrhy)) {
                String[] split = ysrhy.split(",");
                for (int i = 0; i < split.length; i++) {
                    CamsPlpyYsfj hy = new CamsPlpyYsfj();
                    hy.setFwlj(split[i]);
                    hy.setOtherId(id);
                    hy.setZllx("1");
                    camsPlpyYsfjService.save(hy);
                }
            }

            if (StringUtils.isNotBlank(ysrqz)) {
                String fwlj = dz + System.currentTimeMillis() + ".png";
                Base64Util.toImage(ysrqz, uploadpath + "/" + fwlj);
                CamsPlpyYsfj qz = new CamsPlpyYsfj();
                qz.setFwlj(fwlj);
                qz.setOtherId(id);
                qz.setZllx("2");
                camsPlpyYsfjService.save(qz);
            }

            List<CamsPlpyYsxq> camsPlpyYsxqs = new ArrayList<>();
            for (int i = 0; i < cqjlList.size(); i++) {
                CamsPlpyYsxq camsPlpyYsxq = new CamsPlpyYsxq();
                camsPlpyYsxq.setId(id);
                camsPlpyYsxq.setNhbkbpyid(cqjlList.get(i));
                camsPlpyYsxqs.add(camsPlpyYsxq);
            }
            camsPlpyYsxqService.saveBatch(camsPlpyYsxqs);

            camsPlpyYsxx.setBz(bz);
            camsPlpyYsxxService.save(camsPlpyYsxx);
            return Result.ok();

        } else {
            return Result.error("");
        }
    }


    /**
     * 添加
     *
     * @param nhbkbpy
     * @return
     */
    @AutoLog(value = "背靠背评议-添加")
    @ApiOperation(value = "背靠背评议-添加", notes = "背靠背评议-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Nhbkbpy nhbkbpy) {
        service.save(nhbkbpy);
        return Result.ok("添加成功！");
    }


    /**
     * 添加
     *
     * @param nhbkbpy
     * @return
     */
    @AutoLog(value = "背靠背评议-添加")
    @ApiOperation(value = "背靠背评议-添加", notes = "背靠背评议-添加")
    @PostMapping(value = "/saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody Nhbkbpy nhbkbpy) {
        if(org.cmms.common.util.StringUtils.isEmpty(nhbkbpy.getBysxqx())){
            nhbkbpy.setSfjysx("1");
        }else{
            nhbkbpy.setSfjysx("2");
        }
        nhbkbpy.setPyyxm(getRealname());
        nhbkbpy.setPyyzjhm(getWorkNo());
        nhbkbpy.setJysxed(nhbkbpy.getPyyjyed());
        QueryWrapper<Nhbkbpy>queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("zjhm",nhbkbpy.getZjhm());
        service.remove(queryWrapper);
        service.save(nhbkbpy);
        return Result.ok("添加成功！");
    }
    /**
     * 添加
     *
     * @param nhbkbpy
     * @return
     */
    @AutoLog(value = "背靠背评议-添加")
    @ApiOperation(value = "背靠背评议-添加", notes = "背靠背评议-添加")
    @PostMapping(value = "/getOne")
    public Result<?> getOne(@RequestBody Nhbkbpy nhbkbpy) {
        QueryWrapper<Nhbkbpy>queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("zjhm",nhbkbpy.getZjhm());
        service.getOne(queryWrapper,false);
        return Result.ok(service.getOne(queryWrapper,false));
    }

    @GetMapping(value = "/getNhbkbpyInfo")
    public Result<?> getNhbkbpyInfo(String pyyid, String pyyKhid, String pyyzjhm, String id) {
        if (StringUtils.isNotEmpty(pyyid)) {
            //ID不为空，则为选择已有的评议员信息
            //根据评议员ID查询评议员信息
            Nhplpy nhplpy = nhplpyService.getById(pyyid);
            pyyzjhm = nhplpy.getPyyzjhm();
        } else if(StringUtils.isNotEmpty(pyyKhid)) {
            //评议员客户ID不为空，则为选择客户信息
            Nhxq nhxq = nhxqService.getById(pyyKhid);
            pyyzjhm = nhxq.getZjhm();
        } else {
            //手动输入 不作处理
        }
        String sxdxid = "";
        KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getById(id);
        if (StringUtils.isNotEmpty(khglNhhzxxgl.getSxdxzjh())) {
            Nhxq nhxq = nhxqService.getByZjhm(khglNhhzxxgl.getSxdxzjh());
            sxdxid = nhxq.getId();
        }

        QueryWrapper<Nhbkbpy> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("zjhm", khglNhhzxxgl.getSxdxzjh());
        queryWrapper.eq("pyyzjhm", pyyzjhm);
        Nhbkbpy nhbkbpy = service.getOne(queryWrapper,false);
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(nhbkbpy);
        jsonObject.put("sxdxid", sxdxid);
        jsonObject.put("sxdx", khglNhhzxxgl.getSxdx());
        return Result.ok(jsonObject);
    }


    /**
     * 编辑
     *
     * @param nhbkbpy
     * @return
     */
    @AutoLog(value = "背靠背评议-编辑")
    @ApiOperation(value = "背靠背评议-编辑", notes = "背靠背评议-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Nhbkbpy nhbkbpy) {
        service.updateById(nhbkbpy);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "背靠背评议-通过id删除")
    @ApiOperation(value = "背靠背评议-通过id删除", notes = "背靠背评议-通过id删除")
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
    @AutoLog(value = "背靠背评议-批量删除")
    @ApiOperation(value = "背靠背评议-批量删除", notes = "背靠背评议-批量删除")
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
    @AutoLog(value = "背靠背评议-通过id查询")
    @ApiOperation(value = "背靠背评议-通过id查询", notes = "背靠背评议-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Nhbkbpy nhbkbpy = service.getById(id);
        return Result.ok(nhbkbpy);
    }

    @GetMapping(value = "/queryByHhbm")
    public Result<?> queryByHhbm(@RequestParam(name = "hhbm", required = true) String hhbm) {
        QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hhbm", hhbm);
        List<Nhbkbpy> nhbkbpyList = service.list(queryWrapper);
        return Result.ok(nhbkbpyList);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param nhbkbpy
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Nhbkbpy nhbkbpy, String cfplx,String bysxType) {
        String qydm = null;
        if (StringUtils.isNotBlank(nhbkbpy.getQydm()))
            qydm = nhbkbpy.getQydm();
        nhbkbpy.setQydm(null);
        SysDic sysDic = sysDicService.queryByCode("101001");
        String qybm = sysDic.getValue();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "背靠背评议"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("背靠背评议", "导出人:" + sysUser.getRealname(), "背靠背评议"));

        if(QybmEnum.TIANYI.getQybm().equals(qybm)) {
            VNhbkbpy vNhbkbpy = new VNhbkbpy();
            BeanUtils.copyProperties(nhbkbpy, vNhbkbpy);

            QueryWrapper<VNhbkbpy> queryWrapper = QueryGenerator.initQueryWrapper(vNhbkbpy, request.getParameterMap());
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

            if (StringUtils.isNotBlank(qydm)) {
                queryWrapper.inSql("qydm", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "' and  " +
                        "menu_id in (" +
                        "select wgbh from yxdygl_main start with wgbh='" + qydm + "' connect by prior wgbh=parent_id )");

            } else {
                queryWrapper.inSql("qydm", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "'");
            }
            if (StringUtils.isNotEmpty(cfplx)) {
                if ("1".equals(cfplx)) {
                    //初评
                    queryWrapper.like("pylc","1");
                } else if ("2".equals(cfplx)) {
                    queryWrapper.eq("pylc","2");
                }
            }
            if (StringUtils.isNotBlank(bysxType)){
                if ("1".equals(bysxType)){
                    queryWrapper.inSql("zjhm","select zjhm from (select zjhm,pylc from CAMS_ZCSX_NHBKBPY where bysxqx is not null group by zjhm,pylc) group by zjhm having count(pylc)>1");
                } else if ("2".equals(bysxType)){
                    queryWrapper.like("pylc","3");
                    queryWrapper.apply("(zhsded=0)");
                }
            }
            List<VNhbkbpy> nhbkbpyList = vNhbkbpyService.list(queryWrapper);
            // Step.2 获取导出数据
            List<NhbkbpyExportTY> exportList = JSON.parseArray(JSON.toJSONString(nhbkbpyList), NhbkbpyExportTY.class);
            // Step.3 AutoPoi 导出Excel
            mv.addObject(NormalExcelConstants.CLASS, NhbkbpyExportTY.class);
            mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
            return mv;
        } else {
            QueryWrapper<Nhbkbpy> queryWrapper = QueryGenerator.initQueryWrapper(nhbkbpy, request.getParameterMap());
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

            if (StringUtils.isNotBlank(qydm)) {
                queryWrapper.inSql("qydm", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "' and  " +
                        "menu_id in (" +
                        "select wgbh from yxdygl_main start with wgbh='" + qydm + "' connect by prior wgbh=parent_id )");

            } else {
                queryWrapper.inSql("qydm", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getLoginUser().getWorkNo() + "'");
            }
            List<Nhbkbpy> nhbkbpyList = service.list(queryWrapper);

            if (QybmEnum.SHUANGFENG.getQybm().equals(qybm) ||
                    QybmEnum.XINHUA.getQybm().equals(qybm) ||
                    QybmEnum.LEIYANG.getQybm().equals(qybm) ||
                    QybmEnum.XIANGXIANG.getQybm().equals(qybm) ||
                    QybmEnum.RUCHENG.getQybm().equals(qybm)
            ) {

                // Step.2 获取导出数据
                List<NhbkbpyExportSF> exportList = JSON.parseArray(JSON.toJSONString(nhbkbpyList), NhbkbpyExportSF.class);
                // Step.3 AutoPoi 导出Excel
                mv.addObject(NormalExcelConstants.CLASS, NhbkbpyExportSF.class);
                mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
            } else if (QybmEnum.QIDONG.getQybm().equals(qybm)) {
                // Step.2 获取导出数据
                List<NhbkbpyExportQD> exportList = JSON.parseArray(JSON.toJSONString(nhbkbpyList), NhbkbpyExportQD.class);
                // Step.3 AutoPoi 导出Excel
                mv.addObject(NormalExcelConstants.CLASS, NhbkbpyExportQD.class);
                mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
            } else if (QybmEnum.TIANYI.getQybm().equals(qybm)) {

                // Step.2 获取导出数据
                List<NhbkbpyExportTY> exportList = JSON.parseArray(JSON.toJSONString(nhbkbpyList), NhbkbpyExportTY.class);
                // Step.3 AutoPoi 导出Excel
                mv.addObject(NormalExcelConstants.CLASS, NhbkbpyExportTY.class);
                mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
            } else {
                // Step.2 获取导出数据
                List<NhbkbpyExport> exportList = JSON.parseArray(JSON.toJSONString(nhbkbpyList), NhbkbpyExport.class);
                // Step.3 AutoPoi 导出Excel
                mv.addObject(NormalExcelConstants.CLASS, NhbkbpyExport.class);
                mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
            }
            return mv;
        }
    }



    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jsonObject) throws Exception {
        String filePaths = jsonObject.getString("filePaths");
        File file = new File(uploadpath + File.separator + filePaths);
        System.out.println(uploadpath + File.separator + filePaths);

        boolean isExcel2003 = true;
        System.out.println(file.getName());
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
            boolean exist = false;
            for (int i = 0; i < numberOfNames; i++) {
                String sheetName = wb.getSheetName(i);
                log.info("当前sheetName为{}", sheetName);
                if (sheetName.contains("轮评议表") && sheetName.contains("第")) {
                    exist = true;
                    Sheet sheet = wb.getSheet(sheetName);
                    int i1 = DictTextToValusUtil.parsePyls(sheetName);
                    log.info("是第{}轮评议", i1);
                    if (i1 > 0) {
                        String s = impPylsData(sheet, i1);
                        stringBuffer.append(s).append("    ||   ");
                    }
                }
            }
            if (!exist) {
                stringBuffer.append("未找到需要导入的sheet页，请将需要导入的sheet页名字改为《第N轮评议表》");
            }
        } else {
            stringBuffer.append("未找到需要导入的sheet页");
        }
        log.info("===={}====", stringBuffer.toString());
        return Result.ok(stringBuffer.toString());
    }

    public String impPylsData(Sheet sheetAt, Integer pyls) {
        int lastRowNum = sheetAt.getLastRowNum() + 1;
        log.info("===开始导入{}次评议，本次一共有{}行数据===", pyls, lastRowNum);
        String wgmc = "";
        String wgbh = "";
        String pysj = "";
        Date pysjDate = null;
        String pyyxm = "";
        String pyyzjhm = "";
//        String hhbm = "";
        int kh = 0;
        int count = 0;
        SysDic qybmSysDic = sysDicService.queryByCode("101001");
        String qybm = qybmSysDic.getValue();
        for (int i = 1; i < lastRowNum; i++) {
            try {
                Row row = sheetAt.getRow(i);
                if (i == 1) {
                    //行政村名称 3  11
                    wgmc = row.getCell(3).getStringCellValue();
                    log.info("网格名称：{}", wgmc);
                    if (StringUtils.isBlank(wgmc)) {
                        return "行政村名称为空";
                    } else {
                        QueryWrapper queryWrapper = new QueryWrapper();
                        queryWrapper.eq("wgmc_show", wgmc);
                        List<VYxdyglMain> list = vYxdyglMainService.list(queryWrapper);
                        if (CollUtil.isNotEmpty(list)) {
                            wgbh = list.get(0).getWgbh();
                            log.info("===本轮评议网格{}===", wgbh);
                        } else {
                            return "行政村名称找不到数据";
                        }
                    }
                    pysj = row.getCell(10).getStringCellValue();
                    pyyxm = row.getCell(13).getStringCellValue();
                    pyyzjhm = row.getCell(16).getStringCellValue();
                    if (QybmEnum.XINHUA.getQybm().equals(qybm)) {
                        pyyzjhm = row.getCell(17).getStringCellValue();
                    }
                    if (StringUtils.isNotEmpty(pysj)) {
                        pysjDate = DateUtil.parseDateFormat(pysj, DateUtil.chineseDtFormat);
                    }
                    QueryWrapper<Nhplpy> nhplpyQueryWrapper = new QueryWrapper<>();
                    nhplpyQueryWrapper.eq("pyyzjhm", pyyzjhm);
                    nhplpyQueryWrapper.eq("pywg", wgbh);
                    List<Nhplpy> nhplpyList = nhplpyService.list(nhplpyQueryWrapper);
                    if (nhplpyList.isEmpty()) {
                        Nhplpy nhplpy = new Nhplpy();
                        nhplpy.setPyyxm(pyyxm);
                        nhplpy.setPyyzjhm(pyyzjhm);
                        nhplpy.setPyls(pyls + "");
                        nhplpy.setPywg(wgbh);
                        nhplpy.setCreateBy(getWorkNo());
                        nhplpyService.save(nhplpy);
                    }
                    log.info("===本轮评议人=>{}===", pyyzjhm);
                    i += 2;
                } else {

                    if (kh > 2) {
                        return "  第" + pyls + "轮评议表共有" + lastRowNum + "行数据，导入数据" + count + "行!  在" + i + "行连续3条空数据，提前结束了本轮导入！";
                    }
                    //与户主关系  2
                    String yhzgx = getCellValue(row, 2);
//                    if (yhzgx.contains("户主") || yhzgx.contains("本人")) {
//                        hhbm = IdUtil.fastSimpleUUID();
//                    }
                    String hhbm = getCellValue(row, 1);
                    String khmc = getCellValue(row, 3);
                    String id = getCellValue(row, 5);
                    String zjhm = getCellValue(row, 6);
                    String sjhm = getCellValue(row, 7);
                    String zz = getCellValue(row, 8);
                    int nl = 0;
                    if (StringUtils.isBlank(zjhm)) {
                        kh++;
                        continue;
                    } else {
                        kh = 0;
                        if (IdcardUtil.isValidCard(zjhm)) {
                            nl = IdcardUtil.getAgeByIdCard(zjhm);
                        }
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

                    //String sjhm = row.getCell(6).getStringCellValue();
                    //String dz = row.getCell(7).getStringCellValue();
                    String sfljqk = getCellValue(row, 9);
                    if (StringUtils.isEmpty(sfljqk)) {
                        //如果是否了解情况为空，不进行处理
                        continue;
                    }


                    //判断是否已经存在评议员的数据
                    QueryWrapper<Nhbkbpy> nhbkbpyQueryWrapper = new QueryWrapper<>();
                    nhbkbpyQueryWrapper.eq("zjhm", zjhm);
                    nhbkbpyQueryWrapper.eq("pyyzjhm", pyyzjhm);
                    List<Nhbkbpy> nhbkbpyList = service.list(nhbkbpyQueryWrapper);
                    if (!nhbkbpyList.isEmpty()) {
                        log.info("已经存在的评议记录，评议员[{}]，客户[{}]", pyyzjhm, zjhm);
                        continue;
                    }
                    QueryWrapper<KhglNhhzxxgl> hzQueryWrapper = new QueryWrapper<>();
                    hzQueryWrapper.eq("hhbm", hhbm);
                    KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(hzQueryWrapper);
                    //如果授信对象为空，则更新
                    if (StringUtils.isEmpty(khglNhhzxxgl.getSxdxzjh())) {
                        //更新户主表授信对象证件号
                        KhglNhhzxxgl khglNhhzxxglUpdate = new KhglNhhzxxgl();
                        khglNhhzxxglUpdate.setSxdxzjh(zjhm);
                        khglNhhzxxglUpdate.setSxdx(khmc);
                        UpdateWrapper<KhglNhhzxxgl> updateWrapper = new UpdateWrapper<>();
                        updateWrapper.eq("hhbm", hhbm);
                        khglNhhzxxglService.update(khglNhhzxxglUpdate,updateWrapper);
                    }
                    String bfhsxyy = row.getCell(10).getStringCellValue(); //不符合授信原因
                    String bz = row.getCell(11).getStringCellValue();
                    Nhbkbpy nhbkbpy = new Nhbkbpy();
                    nhbkbpy.setBysxqx(DictTextToValusUtil.bysxqx(bfhsxyy));
                    if (QybmEnum.NINGYUAN.getQybm().equals(qybm)) {
                        String ncfcqk = row.getCell(12).getStringCellValue();
                        String cqywfc = row.getCell(13).getStringCellValue();
                        String ywcl = row.getCell(14).getStringCellValue();
                        String sfzbd = row.getCell(15).getStringCellValue();
                        String cqjzd = row.getCell(16).getStringCellValue();
                        String zyxm = row.getCell(17).getStringCellValue();
                        String gzlx = row.getCell(18).getStringCellValue();
                        String sr = row.getCell(19).getStringCellValue();
                        double jcmxcs = row.getCell(20).getNumericCellValue();

                        nhbkbpy.setJcmxcs(new BigDecimal(jcmxcs));
                        nhbkbpy.setJysxed(new BigDecimal(jcmxcs));
                        nhbkbpy.setNcfcqk(DictTextToValusUtil.ncfcqk(ncfcqk));
                        nhbkbpy.setCqywfc(DictTextToValusUtil.ywbz(cqywfc));
                        nhbkbpy.setYwcl(DictTextToValusUtil.ywcl(ywcl));
                        nhbkbpy.setSfzbd(DictTextToValusUtil.sfzbd(sfzbd));
                        nhbkbpy.setCqjzd(cqjzd);
                        nhbkbpy.setZyxm(zyxm);
                        nhbkbpy.setGzlx(DictTextToValusUtil.gzlx(gzlx));
                        nhbkbpy.setJtsr(DictTextToValusUtil.jtsr(sr));
                    } else if (QybmEnum.LANSHAN.getQybm().equals(qybm) || QybmEnum.TIANYI.getQybm().equals(qybm) || QybmEnum.YONGXING.getQybm().equals(qybm)) {
                        String ncfcqk = row.getCell(12).getStringCellValue();
                        String ncfcqkbz = row.getCell(13).getStringCellValue();
                        String cqywfc = row.getCell(14).getStringCellValue();
                        String cqywfcbz = row.getCell(15).getStringCellValue();
                        String ywcl = row.getCell(16).getStringCellValue();
                        String ywclbz = row.getCell(17).getStringCellValue();
                        String sfzbd = row.getCell(18).getStringCellValue();
                        String cqjzd = row.getCell(19).getStringCellValue();
                        String cqjzdbz = row.getCell(20).getStringCellValue();
                        String zyxm = row.getCell(21).getStringCellValue();
                        String gzlx = row.getCell(22).getStringCellValue();
                        String sr = row.getCell(23).getStringCellValue();
                        double jcmxcs = row.getCell(24).getNumericCellValue();
                        double pyyjyed = row.getCell(25).getNumericCellValue();

                        nhbkbpy.setJcmxcs(new BigDecimal(jcmxcs));
                        nhbkbpy.setJysxed(new BigDecimal(jcmxcs));
                        nhbkbpy.setPyyjyed(new BigDecimal(pyyjyed));

                        nhbkbpy.setNcfcqk(DictTextToValusUtil.ncfcqk(ncfcqk));
                        nhbkbpy.setNcfcqkBz(ncfcqkbz);
                        nhbkbpy.setCqywfc(DictTextToValusUtil.ywbz(cqywfc));
                        nhbkbpy.setCqywfcBz(cqywfcbz);
                        nhbkbpy.setYwcl(DictTextToValusUtil.ywcl(ywcl));
                        nhbkbpy.setYwclBz(ywclbz);
                        nhbkbpy.setSfzbd(DictTextToValusUtil.sfzbd(sfzbd));
                        nhbkbpy.setCqjzd(DictTextToValusUtil.sfzbd(cqjzd));
                        nhbkbpy.setCqjzdbz(cqjzdbz);
                        nhbkbpy.setZyxm(zyxm);
                        nhbkbpy.setGzlx(DictTextToValusUtil.gzlx(gzlx));
                        nhbkbpy.setJtsr(DictTextToValusUtil.jtsr(sr));


                    } else if (QybmEnum.SHUANGFENG.getQybm().equals(qybm)) {
                        String jkzk = row.getCell(12).getStringCellValue();
                        String jtsr = row.getCell(13).getStringCellValue();
                        String fwjz = row.getCell(14).getStringCellValue();
                        String ywcl = row.getCell(15).getStringCellValue();
                        String khxyd = row.getCell(16).getStringCellValue();
                        double jcmxcs = row.getCell(17).getNumericCellValue();
                        double pyyjyed = row.getCell(18).getNumericCellValue();
                        String pydf = null;

                        if ("是".equals(sfljqk)) {
                            nhbkbpy.setJcmxcs(new BigDecimal(jcmxcs));
                            nhbkbpy.setJysxed(new BigDecimal(jcmxcs));
                            if (StringUtils.isNotEmpty(pydf)) {
                                nhbkbpy.setPydf(pydf);
                            }
                        }

                        nhbkbpy.setPyyjyed(new BigDecimal(pyyjyed));
                        nhbkbpy.setJkztqk(DictTextToValusUtil.jkzk(jkzk));
                        nhbkbpy.setJtsr(DictTextToValusUtil.jtsr2(jtsr));
                        nhbkbpy.setFwjzqk(DictTextToValusUtil.fwjz(fwjz));
                        nhbkbpy.setYwcl(DictTextToValusUtil.ywcl(ywcl));
                        nhbkbpy.setXyzk(DictTextToValusUtil.khxyd(khxyd));
                    } else if (QybmEnum.XINHUA.getQybm().equals(qybm)) {
                        String jkzk = row.getCell(12).getStringCellValue();  //健康状况
                        String jtldrs = row.getCell(13).getStringCellValue(); //家庭劳动人数
                        String jtsr = row.getCell(14).getStringCellValue(); //家庭收入
                        String fwjz = row.getCell(15).getStringCellValue(); //房屋价值
                        String ywcl = row.getCell(16).getStringCellValue(); //有无车辆
                        String khxyd = row.getCell(17).getStringCellValue(); //客户信誉度
                        String jtywgzry = row.getCell(18).getStringCellValue(); //家庭有无公职人员
                        String jtywdxs = row.getCell(19).getStringCellValue(); //家庭有无大学生

                        double jcmxcs = 0;
                        double pyyjyed = row.getCell(20).getNumericCellValue();
                        String pydf = null;

                        //获取家庭存款日平合计
                        QueryWrapper<KhxxglYwhywwlxxH> ywhywwlxxHQueryWrapper = new QueryWrapper<>();
                        ywhywwlxxHQueryWrapper.eq("hhbm", hhbm);
                        List<KhxxglYwhywwlxxH> khxxglYwhywwlxxHList = khxxglYwhywwlxxHService.list(ywhywwlxxHQueryWrapper);
                        BigDecimal jtckrphj = new BigDecimal(0);
                        if (!khxxglYwhywwlxxHList.isEmpty()) {
                            jtckrphj = khxxglYwhywwlxxHList.get(0).getCkrpye();
                        }
                        nhbkbpy.setJtckrphj(jtckrphj);
                        Map<String, Integer> map = DictTextToValusUtil.jcmxcs_xinhua(jtckrphj, jkzk, jtldrs, jtsr, fwjz, ywcl, khxyd, jtywgzry, jtywdxs);
                        jcmxcs = map.get("result");
                        pydf = String.valueOf(map.get("pydf"));

                        if (StringUtils.isNotEmpty(bfhsxyy)) {
                            List<DictModel> bysxqxDictModels = sysDictService.queryDictItemsByCode("py_bysxqx_sf");
                            nhbkbpy.setBysxqx(DictTextToValusUtil.bysxqxByDict(bysxqxDictModels, bfhsxyy));
                        }
                        if ("是".equals(sfljqk) && StringUtils.isEmpty(bfhsxyy)) {
                            nhbkbpy.setJcmxcs(new BigDecimal(jcmxcs));
                            nhbkbpy.setJysxed(new BigDecimal(jcmxcs));
                            if (StringUtils.isNotEmpty(pydf)) {
                                nhbkbpy.setPydf(pydf);
                            }
                        }

                        nhbkbpy.setPyyjyed(new BigDecimal(pyyjyed));
                        nhbkbpy.setJkztqk(DictTextToValusUtil.jkzk(jkzk));
                        nhbkbpy.setJtndlrsqk(DictTextToValusUtil.jtldlrs(jtldrs));
                        nhbkbpy.setJtsr(DictTextToValusUtil.jtsr_xinhua(jtsr));
                        nhbkbpy.setFwjzqk(DictTextToValusUtil.fwjz_xinhua(fwjz));
                        nhbkbpy.setYwcl(DictTextToValusUtil.ywcl_xinhua(ywcl));
                        nhbkbpy.setXyzk(DictTextToValusUtil.khxyd(khxyd));
                        nhbkbpy.setXfdyhgzry(DictTextToValusUtil.ywbz(jtywgzry));
                        nhbkbpy.setJtywdxs(DictTextToValusUtil.ywbz(jtywdxs));
                    }


//                    Nhxq nhxq = new Nhxq();
//                    nhxq.setWgbh(wgbh);
//                    nhxq.setHhbm(hhbm);
//                    nhxq.setYhzgx(DictTextToValusUtil.yhzgx(yhzgx));
//                    nhxq.setKhmc(khmc);
//                    nhxq.setZjhm(zjhm);
//                    nhxq.setSjhm(sjhm);
//                    nhxq.setZz(dz);
//                    nhxq.setLrr("antoimp");
//                    nhxqList.add(nhxq);

                    //基础模型测试
//                    int jcmxcs = DictTextToValusUtil.jcmxcs(ncfcqk, cqywfc, ywcl, gzlx, sr);



                    nhbkbpy.setQydm(wgbh);
                    nhbkbpy.setHhbm(hhbm);
                    nhbkbpy.setZjhm(zjhm);
                    nhbkbpy.setKhmc(khmc);
                    nhbkbpy.setPyls(pyls);
                    if (nl > 0) {
                        nhbkbpy.setNl(nl);
                    }
                    nhbkbpy.setSfljqk(DictTextToValusUtil.sfljqk(sfljqk));
                    nhbkbpy.setBz(bz);

                    nhbkbpy.setLrr(getWorkNo());
                    if (pysjDate != null) {
                        nhbkbpy.setPysj(pysjDate);
                    }
                    nhbkbpy.setPyyzjhm(pyyzjhm);
                    nhbkbpy.setPyyxm(pyyxm);
                    nhbkbpy.setPylx("9");
                    if (StringUtils.isNotBlank(nhbkbpy.getZjhm())) {
                        boolean save = service.save(nhbkbpy);
                        if (save)
                            count++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.info("==={}行出现了问题==", i);
                log.error("导入评议表异常", e);
            }

        }
        return "  第" + pyls + "轮评议表共有" + lastRowNum + "行数据，导入数据" + count + "行!  ";
    }

    private String getCellValue(Row row, int cellIndex) {
        if (row != null) {
            Cell cell = row.getCell(cellIndex);
            if (cell != null) {
                int cellType = cell.getCellType();
                if (cellType == Cell.CELL_TYPE_NUMERIC) {
                    Double cellValue = cell.getNumericCellValue();
                    if(cellValue != null) {
                        DecimalFormat df = new DecimalFormat("0");
                        return df.format(cellValue);
                    }
                }
                return cell.getStringCellValue();
            }
        }
        return null;
    }

    @RequestMapping("/getAllBkbpybz")
    public Result<?> getAllBkbpybz(String hhbm) {
        String allBkbpybz = service.getAllBkbpybz(hhbm);
        return Result.ok(allBkbpybz);
    }

    @RequestMapping("/bmddc")
    public void dc(HttpServletRequest request,String yggh,String qydm,
                   HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        String wgmc = null;
        if (StringUtils.isNotBlank(qydm)) {
            QueryWrapper<YxdyglMain> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("wgbh", qydm);
            List<YxdyglMain> list = yxdyglMainService.list(queryWrapper);
            if (CollUtil.isNotEmpty(list)) {
                wgmc = list.get(0).getWgmc();
            }
        }
        SysDic sysDic = sysDicService.queryByCode("101002");
        String qymc = sysDic.getValue();
        String code = sysDic.getCode();
        //String yggh = getLoginUser().getWorkNo();
        int maxPyls = service.getMaxPyls(qydm, yggh);
        log.info("===背靠背评议白名单导出wgbh是{},网格名称是{},导出员工是{},最大轮数为{}===", qydm, wgmc, yggh, maxPyls);

        if (maxPyls > 0) {
            for (int i = 1; i <= maxPyls; i++) {
                log.info("===正在导出第{}轮===", i);
                String pyyxm = "";
                if (StringUtils.isNotBlank(qydm)){
                    pyyxm = service.getpyyxm(qydm,i);
                }

                List<List<String>> dataList = Lists.newArrayList();
                String s = Convert.numberToChinese(i, false);
                HSSFSheet sheet = workbook.createSheet("第" + s + "轮");
                XTwgsxbmdTable xTwgsxbmdTable = new XTwgsxbmdTable(workbook, sheet);
                xTwgsxbmdTable.setTableName(qymc + "“无感授信”基础信息收集操作表（第" + s + "轮）白名单");
                xTwgsxbmdTable.setRowTwoNam(wgmc, pyyxm, "");
                xTwgsxbmdTable.setTableHeader();
                List<XtBmd> bmdList = service.getBmdList(qydm, yggh, i,code);
                if (CollUtil.isNotEmpty(bmdList)) {
                    int size = bmdList.size();
                    log.info("===本轮导出数据为{}===",size);
                    for (int j = 0; j < size; j++) {
                        List<String> colList = Lists.newArrayList();
                        XtBmd xtBmd = bmdList.get(j);
                        colList.add(xtBmd.getHhbm());

                        if (StringUtils.isNotBlank(xtBmd.getYhzgx())) {
                            String yhzgx = sysDictService.queryDictTextByKey("yhzgx", xtBmd.getYhzgx());
                            colList.add(yhzgx);
                        } else {
                            colList.add("");
                        }
                        if (StringUtils.isNotBlank(xtBmd.getKhmc())) {
                            colList.add(xtBmd.getKhmc());
                        } else {
                            colList.add("");
                        }
                        if (StringUtils.isNotBlank(xtBmd.getNl())) {
                            colList.add(xtBmd.getNl());
                        } else {
                            if (StringUtils.isNotBlank(xtBmd.getZjhm())) {
                                if (IdcardUtil.isValidCard(xtBmd.getZjhm())){
                                    int ageByIdCard = IdcardUtil.getAgeByIdCard(xtBmd.getZjhm());
                                    colList.add(ageByIdCard+"");
                                }else {
                                    colList.add("");
                                }
                            }else {
                                colList.add("");
                            }
                        }
                        if (StringUtils.isNotBlank(xtBmd.getZjhm())) {
                            colList.add(xtBmd.getZjhm());
                        } else {
                            colList.add("");
                        }
                        if (StringUtils.isNotBlank(xtBmd.getSjhm())) {
                            colList.add(xtBmd.getSjhm());
                        } else {
                            colList.add("");
                        }
                        if (StringUtils.isNotBlank(xtBmd.getHjdz())) {
                            colList.add(xtBmd.getHjdz());
                        } else {
                            colList.add("");
                        }

                        if (StringUtils.isNotBlank(xtBmd.getSfljqk())) {
                            colList.add("1".equals(xtBmd.getSfljqk()) ? "是" : "否");
                        } else {
                            colList.add("");
                        }

                        if (StringUtils.isNotBlank(xtBmd.getBysxqx())) {
                            String bysxqx = xtBmd.getBysxqx();
                            StringBuffer stringBuffer = new StringBuffer();
                            String[] split = bysxqx.split(",");
                            for (int k = 0; k < split.length; k++) {
                                String py_bysxqx_ny = sysDictService.queryDictTextByKey("py_bysxqx_ny", split[k]);
                                if (StringUtils.isNotBlank(py_bysxqx_ny)) {
                                    if (k == split.length - 1) {
                                        stringBuffer.append(py_bysxqx_ny);
                                    } else {
                                        stringBuffer.append(py_bysxqx_ny).append(",");
                                    }
                                }
                            }
                            colList.add(stringBuffer.toString());
                        } else {
                            colList.add("");
                        }

                        if (StringUtils.isNotBlank(xtBmd.getBz())) {
                            colList.add(xtBmd.getBz());
                        } else {
                            colList.add("");
                        }

                        if (StringUtils.isNotBlank(xtBmd.getSfjysx())) {
                            colList.add("1".equals(xtBmd.getSfjysx()) ? "是" : "否");
                        } else {
                            colList.add("");
                        }
                        if (StringUtils.isNotBlank(xtBmd.getNcfcqk())) {
                            if (qydm.equals(QybmEnum.XINTIAN.getQybm())){
                                String ncfcqk = sysDictService.queryDictTextByKey("ncfcqk_xt", xtBmd.getNcfcqk());
                                colList.add(ncfcqk);
                            }else {
                                String ncfcqk = sysDictService.queryDictTextByKey("ncfcqk", xtBmd.getNcfcqk());
                                colList.add(ncfcqk);
                            }
                        } else {
                            colList.add("");
                        }

                        if (StringUtils.isNotBlank(xtBmd.getCqywfc())) {
                            colList.add("1".equals(xtBmd.getCqywfc()) ? "有" : "无");
                        } else {
                            colList.add("");
                        }

                        if (StringUtils.isNotBlank(xtBmd.getYwcl())) {
                            colList.add("1".equals(xtBmd.getYwcl()) ? "有" : "无");
                        } else {
                            colList.add("");
                        }

                        if (StringUtils.isNotBlank(xtBmd.getSfzbd())) {
                            colList.add("1".equals(xtBmd.getSfzbd())? "是" : "否");
                        } else {
                            colList.add("");
                        }
                        if (StringUtils.isNotBlank(xtBmd.getCqjzd())) {
                            String sfzbd = sysDictService.queryDictTextByKey("sfzbd", xtBmd.getCqjzd());
                            if (StringUtils.isNotBlank(sfzbd)){
                                colList.add(sfzbd);
                            }else {
                                colList.add(xtBmd.getCqjzd());
                            }
                        } else {
                            colList.add("");
                        }
                        if (StringUtils.isNotBlank(xtBmd.getZyxm())) {
                            String zyxm = sysDictService.queryDictTextByKey("zyxm_xt", xtBmd.getZyxm());
                            if (StringUtils.isEmpty(zyxm)) {
                                //宁远是直接填的内容
                               colList.add(xtBmd.getZyxm());
                            } else {
                                colList.add(zyxm);
                            }
                        } else {
                            colList.add("");
                        }

                        if (StringUtils.isNotBlank(xtBmd.getSr())) {
                            String sr = sysDictService.queryDictTextByKey("bkbpy_sr", xtBmd.getSr());
                            colList.add(sr);
                        } else {
                            colList.add("");
                        }
                        if (StringUtils.isNotBlank(xtBmd.getJysxed())) {
                            colList.add(xtBmd.getJysxed());
                        } else {
                            colList.add("");
                        }
                        if (StringUtils.isNotBlank(xtBmd.getSfycdg())) {
                            colList.add("1".equals(xtBmd.getSfycdg()) ? "是" : "否");
                        } else {
                            colList.add("");
                        }

                        dataList.add(colList);
                    }
                }
                xTwgsxbmdTable.setTableData(dataList);
            }

        } else {
            HSSFSheet sheet = workbook.createSheet("第一轮");
            XTwgsxbmdTable xTwgsxbmdTable = new XTwgsxbmdTable(workbook, sheet);
            xTwgsxbmdTable.setTableName(qymc + "“无感授信”基础信息收集操作表（第一轮）白名单");
            xTwgsxbmdTable.setTableHeader();
        }


        workbookWrite(request, response, "白名单", workbook);
    }

    @RequestMapping("/lsadd")
    public Result<?> lsadd(@RequestBody Nhbkbpy nhbkbpy){
        System.out.println(nhbkbpy);
        //评议员信息
        if (StringUtils.isNotBlank(nhbkbpy.getPyyzjhm())){
            String pyyzjhm = nhbkbpy.getPyyzjhm();
            if (!IdcardUtil.isValidCard(pyyzjhm)){
                Nhplpy byId = nhplpyService.getById(pyyzjhm);
                if (byId != null){
                    if (StringUtils.isNotBlank(byId.getPyyzjhm()))
                        nhbkbpy.setPyyzjhm(byId.getPyyzjhm());
                }
            }
        }

        if (StringUtils.isNotBlank(nhbkbpy.getZjhm())){
            String zjhm = nhbkbpy.getZjhm();
            Nhxq byId = nhxqService.getById(zjhm);
            nhbkbpy.setZjhm(byId.getZjhm());
            nhbkbpy.setKhmc(byId.getKhmc());
            if (IdcardUtil.isValidCard(byId.getZjhm())){
                nhbkbpy.setNl(IdcardUtil.getAgeByIdCard(byId.getZjhm()));
                nhbkbpy.setXb(IdcardUtil.getGenderByIdCard(byId.getZjhm())==1?"1":"2");
            }

            if (StringUtils.isNotBlank(nhbkbpy.getSign1())){
                String sign1 = nhbkbpy.getSign1();
                nhbkbpy.setSign1(null);
                byId.setSjhm(sign1);
                nhxqService.updateById(byId);
            }
        }

        nhbkbpy.setLrr(getWorkNo());
        nhbkbpy.setPysj(new Date());
        service.save(nhbkbpy);

        return Result.ok();
    }


    @RequestMapping("/getPyxq")
    public Result<?> getPyxq(String zjhm){
        LambdaQueryWrapper<Nhbkbpy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Nhbkbpy::getZjhm,zjhm);
        lambdaQueryWrapper.like(Nhbkbpy::getPylc,"3");
        lambdaQueryWrapper.orderByDesc(Nhbkbpy::getZhsded);
        List<Nhbkbpy> list = service.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list))
            return Result.ok(list.get(0));
        return Result.error("未查询到评议信息");
    }

    /**
     * 祁阳-整村授信导入模板
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importZcsxTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "整村授信");
        modelAndView.addObject(NormalExcelConstants.CLASS, ZcsxVo.class);
        ExportParams exportParams = new ExportParams("整村授信导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
    }

    /**
     * 祁阳-整村授信导入
     * @param jsonObject
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/importZcsxXls", method = RequestMethod.POST)
    public Result<?> importZcsxXls(@RequestBody JSONObject jsonObject) {
        String filePaths = jsonObject.getString("filePaths");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
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
            if (zcsxImportVerify != null) {
                params.setVerifyHanlder(zcsxImportVerify);
            }
            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                ExcelImportResult<ZcsxVo> importResult = ExcelImportUtil.importExcelVerify(file, ZcsxVo.class, params);
                List<ZcsxVo> list = importResult.getList();
                List<Nhbkbpy> nhbkbpyList = new ArrayList<>();
                list.stream().forEach(e -> {
                    //更新农户表手机号码字段
                    QueryWrapper<Nhxq> nhxqQueryWrapper=new QueryWrapper<>();
                    nhxqQueryWrapper.eq("zjhm",e.getZjhm());
                    Nhxq nhxq=nhxqService.getOne(nhxqQueryWrapper,false);
                    if (nhxq==null){
                        return;
                    }
                    if (StringUtils.isNotBlank(e.getLxdh())){
                        nhxq.setSjhm(e.getLxdh());
                        nhxqService.updateById(nhxq);
                    }

                    //更新户主表授信对象
                    if (StringUtils.isNotBlank(nhxq.getHhbm())) {
                        QueryWrapper<KhglNhhzxxgl> nhhzxxglQueryWrapper = new QueryWrapper<>();
                        nhhzxxglQueryWrapper.eq("hhbm", nhxq.getHhbm());
                        KhglNhhzxxgl khglNhhzxxgl=khglNhhzxxglService.getOne(nhhzxxglQueryWrapper,false);
                        if(khglNhhzxxgl!=null){
                            khglNhhzxxgl.setSxdx(e.getPydx());
                            khglNhhzxxgl.setSxdxzjh(e.getZjhm());
                            khglNhhzxxglService.updateById(khglNhhzxxgl);
                        }
                    }

                    //插入整村授信数据
                    Nhbkbpy nhbkbpy=new Nhbkbpy();
                    nhbkbpy.setZjhm(e.getZjhm());
                    nhbkbpy.setKhmc(e.getPydx());
                    nhbkbpy.setBysxqx(e.getBysxqx());
                    nhbkbpy.setHyzk(e.getHyzk());
                    nhbkbpy.setShswjry(e.getShswjry());
                    nhbkbpy.setShgxzk(e.getShgxzk());
                    nhbkbpy.setShxgqk(e.getShxgqk());
                    nhbkbpy.setZmjgljk(e.getZmjgljk());
                    nhbkbpy.setJysxed(e.getJysxed()==null?null:e.getJysxed().multiply(new BigDecimal("10000")));
                    nhbkbpy.setPyyxm(e.getPyyxm());
                    nhbkbpy.setPyyzjhm(e.getPyyzjhm());
                    nhbkbpy.setPysj(e.getPysj());
                    nhbkbpy.setPylx("9");

                    nhbkbpy.setHhbm(nhxq.getHhbm());
                    nhbkbpy.setQydm(nhxq.getWgbh());
                    nhbkbpy.setLrsj(new Date());
                    nhbkbpy.setLrbz("3");
                    nhbkbpy.setLrr(getUsername());
                    nhbkbpyList.add(nhbkbpy);
                });
                service.saveBatch(nhbkbpyList);
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

    @GetMapping("/getNhxxById")
    public Result<?> getNhxxById(@RequestParam("id")String id){
        if (StringUtils.isNotBlank(id)){
            return Result.ok(nhxqService.getById(id));
        }
        return Result.ok();
    }

    /**
     * 导出评议数据-宁远
     *
     * @return
     */
    @RequestMapping(value = "/exportPysjXls")
    public ModelAndView exportPysjXls(HttpServletRequest request, String wgbh) {
        if (StringUtils.isNotBlank(wgbh)) {
            try {
                //获取网格信息
                YxdyglMain yxdyglMain = yxdyglMainService.getById(wgbh);
                Integer maxPylc = service.getPylsByWgbh(wgbh);
                //如果选择的网格没有评议数据，就默认展示第一轮的sheet页，展示所有改网格的农户数据
                maxPylc=maxPylc==null || maxPylc<=0?1:maxPylc;
                //填充数据
                ModelAndView mv = new ModelAndView(new TemplateExcelView());
                Map<String, Object> map = new HashMap<String, Object>();
                for (Integer i = 1; i <= maxPylc; i++) {
                    List<NhbkbpyNyVo> nhbkbpyNyVoList = service.queryPyxxNy(wgbh, i);
                    NhbkbpyNyVo tmpNhbkbpy = nhbkbpyNyVoList.stream().filter(item -> StringUtils.isNotBlank(item.getPyyxm())).findFirst().orElse(null);
                    String listName = "list" + i;
                    String pyyxmName = "pyyxm" + i;
                    String pyyzjhmName = "pyyzjhm" + i;
                    ;
                    map.put(listName, listToDictUtil.parseDictText(nhbkbpyNyVoList));
                    map.put(pyyxmName, tmpNhbkbpy != null ? tmpNhbkbpy.getPyyxm() : null);
                    map.put(pyyzjhmName, tmpNhbkbpy != null ? tmpNhbkbpy.getPyyzjhm() : null);
                }
                map.put("wgmc", yxdyglMain.getWgmc());
                map.put("wgbh", wgbh);
                File file = org.cmms.common.util.FileUtil.getTempFilePathNy("宁远农商行背靠背评议表2.xls");
                FileInputStream fis = new FileInputStream(file);
                HSSFWorkbook xssfWorkbook = new HSSFWorkbook(fis);
                //删除多余的sheet页
                for (Integer i = 29; i >= 0; i--) {
                    if (maxPylc>i){
                        break;
                    }
                    xssfWorkbook.removeSheetAt(i);
                }
                FileOutputStream out = new FileOutputStream(file);
                xssfWorkbook.write(out);
                fis.close();
                xssfWorkbook.close();
                String tempFileName = file.getPath();
                mv.addObject(JxlsConstants.FILE_NAME, "宁远农商行背靠背评议明细表");
                mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, tempFileName);
                mv.addObject(JxlsConstants.SAVE_FILE_NAME, exportpath + "/农商行背靠背评议明细表.xls");
                mv.addObject(JxlsConstants.MAP_DATA, map);
                return mv;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 导入评议数据-宁远
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/importPysjXls", method = RequestMethod.POST)
    public Result<?> importPysjXls(@RequestBody JSONObject jsonObject) {
        String filePaths = jsonObject.getString("filePaths");
        String wgbh = jsonObject.getString("wgbh");
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
                String importWgbh=workbook.getSheetAt(0).getRow(1).getCell(7).getStringCellValue();
                if (StringUtils.isBlank(importWgbh) || !wgbh.equals(importWgbh)){
                    return Result.error("导入文件中的网格编号为空或者选择要导入的网格与导入文件中的网格不匹配！");
                }
                //先删除客户页面要导入的网格在评议表的数据
                QueryWrapper<Nhbkbpy> queryWrapper = new QueryWrapper<>();
                queryWrapper.inSql("qydm", "select wgbh from yxdygl_main where parent_id=" + wgbh + "");

                //遍历excel
                for (Integer i = 0; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    if (StringUtils.isBlank(sheet.getSheetName())) {
                        break;
                    }
                    //评议轮数
                    Integer pyls = i + 1;
                    //评议员姓名
                    String pyyxm = null;
                    //评议员证件号码
                    String pyyzjhm = null;
                    List<Nhbkbpy> nhbkbpyList = new ArrayList<>();
                    for (Row row : sheet) {
                        if (row.getRowNum() == 0 || row.getRowNum() == 2 || row.getRowNum() == 3) {
                            continue;
                        } else if (row.getRowNum() == 1) {
                            //如果是第2行，就获取网格评议员信息
                            pyyxm = row.getCell(11).getStringCellValue();
                            if (StringUtils.isBlank(pyyxm)){
                                return Result.error("当前导入sheet页索引："+pyls+"(第"+pyls+"轮),缺失评议员姓名！！请排查后重新导入！！");
                            }
                            row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
                            pyyzjhm = row.getCell(17).getStringCellValue();
                            if (StringUtils.isBlank(pyyzjhm)){
                                return Result.error("当前导入sheet页索引："+pyls+"(第"+pyls+"轮),缺失评议员证件号码！！请排查后重新导入！！");
                            }
                            if (!IdcardUtil.isValidCard(pyyzjhm)){
                                return Result.error("当前导入sheet页索引："+pyls+"(第"+pyls+"轮),评议员证件号码不规范！！！请重新填写后导入！！");
                            }
                        } else {
                            //第10个单元格：是否了解情况，如果为空就说明是没有评议的客户，不导入到评议表
                            if (StringUtils.isBlank(row.getCell(9).getStringCellValue())) {
                                continue;
                            }
                            Nhbkbpy nhbkbpy = new Nhbkbpy();
                            //村组编号
                            nhbkbpy.setQydm(wgbh);
                            //评议轮数
                            nhbkbpy.setPyls(pyls);
                            //评议姓名
                            nhbkbpy.setPyyxm(pyyxm);
                            //评议员证件号码
                            nhbkbpy.setPyyzjhm(pyyzjhm);
                            //户号编码
                            nhbkbpy.setHhbm(row.getCell(2).getStringCellValue());
                            //客户名称
                            nhbkbpy.setKhmc(row.getCell(4).getStringCellValue());
                            //证件号码
                            nhbkbpy.setZjhm(row.getCell(6).getStringCellValue());
                            //是否了解情况
                            nhbkbpy.setSfljqk(StringUtils.isNotBlank(row.getCell(9).getStringCellValue()) ? sysDictService.queryDictValueByKey("sfljqk", row.getCell(9).getStringCellValue()) : null);
                            //不予授信情形特殊处理
                            if (StringUtils.isNotBlank(row.getCell(10).getStringCellValue())){
                                List<String> dictTextList= Arrays.asList(row.getCell(10).getStringCellValue().split(","));
                                StringBuffer sb=new StringBuffer();
                                dictTextList.forEach(e->{
                                    sb.append(sysDictService.queryDictValueByKey("py_bysxqx_ny",e)).append(",");
                                });
                                //不予授信情形
                                nhbkbpy.setBysxqx(sb.toString());
                                //不予授信类型
                                nhbkbpy.setBysxlx("9");
                            }
                            //备注
                            row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                            nhbkbpy.setBz(row.getCell(11).getStringCellValue());
                            //农村房产情况
                            nhbkbpy.setNcfcqk(StringUtils.isNotBlank(row.getCell(12).getStringCellValue()) ? sysDictService.queryDictValueByKey("ncfcqk", row.getCell(12).getStringCellValue()) : null);
                            //农村房产情况备注
                            row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                            nhbkbpy.setNcfcqkBz(row.getCell(13).getStringCellValue());
                            //城区有无房产
                            nhbkbpy.setCqywfc(StringUtils.isNotBlank(row.getCell(14).getStringCellValue()) ? sysDictService.queryDictValueByKey("ywbz", row.getCell(14).getStringCellValue()) : null);
                            //城区有无房产备注
                            row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                            nhbkbpy.setCqywfcBz(row.getCell(15).getStringCellValue());
                            //有无车辆
                            nhbkbpy.setYwcl(StringUtils.isNotBlank(row.getCell(16).getStringCellValue()) ? sysDictService.queryDictValueByKey("ywbz", row.getCell(16).getStringCellValue()) : null);
                            //有无车辆备注
                            row.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
                            nhbkbpy.setYwclBz(row.getCell(17).getStringCellValue());
                            //是否在本地
                            nhbkbpy.setSfzbd(StringUtils.isNotBlank(row.getCell(18).getStringCellValue()) ? sysDictService.queryDictValueByKey("sfzbd", row.getCell(18).getStringCellValue()) : null);
                            //长期居住地
                            nhbkbpy.setCqjzd(StringUtils.isNotBlank(row.getCell(19).getStringCellValue()) ? sysDictService.queryDictValueByKey("sfzbd", row.getCell(19).getStringCellValue()) : null);
                            //长期居住地备注
                            row.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
                            nhbkbpy.setCqjzdbz(row.getCell(20).getStringCellValue());
                            //主营项目
                            row.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
                            nhbkbpy.setZyxm(row.getCell(21).getStringCellValue());
                            //工作类型
                            nhbkbpy.setGzlx(StringUtils.isNotBlank(row.getCell(22).getStringCellValue()) ? sysDictService.queryDictValueByKey("gzlx", row.getCell(22).getStringCellValue()) : null);
                            //收入
                            String tmp = StringUtils.isNotBlank(row.getCell(23).getStringCellValue()) ? sysDictService.queryDictValueByKey("bkbpy_sr", row.getCell(23).getStringCellValue()) : null;
                            nhbkbpy.setSr(StringUtils.isNotBlank(tmp)?Integer.valueOf(tmp):null);
                            //基础模型测算
                            row.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
                            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                            CellValue cellValue = evaluator.evaluate(row.getCell(24));
                            BigDecimal ed=row.getCell(24).getStringCellValue() == null ? null : new BigDecimal(cellValue.getStringValue());
                            nhbkbpy.setJcmxcs(ed);
                            //建议授信额度
                            nhbkbpy.setJysxed(ed);
                            //评议员建议额度
                            row.getCell(25).setCellType(Cell.CELL_TYPE_STRING);
                            nhbkbpy.setPyyjyed(StringUtils.isNotBlank(row.getCell(25).getStringCellValue()) ? new BigDecimal(row.getCell(25).getStringCellValue()) : null);
                            //评议类型
                            nhbkbpy.setPylx(StringUtils.isNotBlank(row.getCell(26).getStringCellValue()) ? sysDictService.queryDictValueByKey("bkbpy_pylx", row.getCell(26).getStringCellValue()) : null);
                            //评议时间
                            row.getCell(27).setCellType(Cell.CELL_TYPE_STRING);
                            if (StringUtils.isNotBlank(row.getCell(27).getStringCellValue())) {
                                String pysj = DateUtil.format(HSSFDateUtil.getJavaDate(Double.valueOf(row.getCell(27).getStringCellValue())),"yyyy-MM-dd");
                                nhbkbpy.setPysj(DateUtil.string2Date(pysj, "yyyy-MM-dd"));
                            }
                            //录入标志
                            nhbkbpy.setLrbz("3");
                            nhbkbpyList.add(nhbkbpy);
                        }
                    }
                    //一户只能评议一个家庭成员
                    Map<String,Long> mapPyxx=nhbkbpyList.stream().collect(Collectors.groupingBy(Nhbkbpy::getHhbm,Collectors.counting()));
                    Boolean con=false;
                    for (Map.Entry<String, Long> entry : mapPyxx.entrySet()) {
                        if (Integer.valueOf(entry.getValue().intValue())>1){
                            con=true;
                            break;
                        }
                    }
                    if (con){
                        return Result.error("当前导入sheet页索引："+pyls+"(第"+pyls+"轮),一户只能导入一个家庭成员的评议信息！！请排查后重新导入！！");
                    }
                    //如果上面条件都满足，就先删除该网格的评议数据，在导入评议数据
                    service.remove(queryWrapper);
                    service.saveBatch(nhbkbpyList);
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

}
