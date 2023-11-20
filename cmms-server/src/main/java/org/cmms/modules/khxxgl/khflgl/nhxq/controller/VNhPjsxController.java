package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import net.sf.saxon.trans.SymbolicName;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.K;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.base.entity.NotApprovalYearAuditTable;
import org.cmms.common.system.base.entity.SimpleStandardTable;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.MD5Util;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.utils.Base64Util;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.entity.ActXendSpls;
import org.cmms.modules.activiti.service.IActXendSplsService;
import org.cmms.modules.dzdkz.service.SysLoanInfoService;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxx;
import org.cmms.modules.khgl.grkhgl.service.ICamsZcsxGrpjsxxxService;
import org.cmms.modules.khgl.nh.entity.YhwywxlTotalVO;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.*;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IKhxxglHnkdService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IVNhPjsxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.verify.CamsNhpjsxNsbImportVerify;
import org.cmms.modules.khxxgl.khflgl.nhxq.verify.NsImportVerity;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysBasUser;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.entity.SysUserRole;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.xdgl.dksp.dkspkhzc.entity.DkspKhzc;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrkhpjsxService;
import org.cmms.modules.xdgl.nsb.entity.CamsNhpjsxNsb;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Encoder;

/**
 * @Description: 农户评级授信视图
 * @Author: jeecg-boot
 * @Date: 2023-02-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "农户评级授信视图")
@RestController
@RequestMapping("/nh/vNhPjsx")
public class VNhPjsxController extends JeecgController<VNhPjsx, IVNhPjsxService> {

    @Autowired
    IYxdyglMainService yxdyglMainService;
    @Autowired
    IVYxdyglMainService ivYxdyglMainService;
    @Autowired
    ListToDictUtil listToDictUtil;
    @Autowired
    ISysDictService sysDictService;
    @Autowired
    IYwhywwlxxService ywhywwlxxService;
    @Autowired
    INhxqService nhxqService;
    @Autowired
    NsImportVerity nsImportVerity;
    @Autowired
    ICamsZcsxGrpjsxxxService camsZcsxGrpjsxxxService;
    @Autowired
    ITjfxCsszService tjfxCsszService;
    /**
     * 分页列表查询
     *
     * @param vNhPjsx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "农户评级授信视图-分页列表查询")
    @ApiOperation(value = "农户评级授信视图-分页列表查询", notes = "农户评级授信视图-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(VNhPjsx vNhPjsx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String wgbh = null;
        if (StringUtils.isNotBlank(vNhPjsx.getWgbh()))
            wgbh = vNhPjsx.getWgbh();
        vNhPjsx.setWgbh(null);

        String sszh = null;
        if (StringUtils.isNotBlank(vNhPjsx.getSszh())) {
            sszh = vNhPjsx.getSszh();
            vNhPjsx.setSszh(null);
        }

        QueryWrapper<VNhPjsx> queryWrapper = QueryGenerator.initQueryWrapper(vNhPjsx, req.getParameterMap());
        Page<VNhPjsx> page = new Page<VNhPjsx>(pageNo, pageSize);
        if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {

                if (StringUtils.isNotBlank(wgbh)) {
                    queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
                }

            LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "vnhpjsx");
            List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
            boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
            if (b){
                if (StringUtils.isNotBlank(sszh)) {
                    queryWrapper.eq("sszh", sszh);
                }
            }else {
                queryWrapper.eq("sszh", getLoginUser().getOrgCode());
            }

        }

        //queryWrapper.orderByDesc("sxsj");
        IPage<VNhPjsx> pageList = service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param vNhPjsx
     * @return
     */
    @AutoLog(value = "农户评级授信视图-添加")
    @ApiOperation(value = "农户评级授信视图-添加", notes = "农户评级授信视图-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody VNhPjsx vNhPjsx) {
        service.save(vNhPjsx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param vNhPjsx
     * @return
     */
    @AutoLog(value = "农户评级授信视图-编辑")
    @ApiOperation(value = "农户评级授信视图-编辑", notes = "农户评级授信视图-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody VNhPjsx vNhPjsx) {
        service.updateById(vNhPjsx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "农户评级授信视图-通过id删除")
    @ApiOperation(value = "农户评级授信视图-通过id删除", notes = "农户评级授信视图-通过id删除")
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
    @AutoLog(value = "农户评级授信视图-批量删除")
    @ApiOperation(value = "农户评级授信视图-批量删除", notes = "农户评级授信视图-批量删除")
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
    @AutoLog(value = "农户评级授信视图-通过id查询")
    @ApiOperation(value = "农户评级授信视图-通过id查询", notes = "农户评级授信视图-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        VNhPjsx vNhPjsx = service.getById(id);
        return Result.ok(vNhPjsx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param vNhPjsx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, VNhPjsx vNhPjsx) {
        return super.exportXls(request, vNhPjsx, VNhPjsx.class, "农户评级授信视图");
    }

    @RequestMapping(value = "/exportTemplateXls2")
    public Result<?> exportTemplateXls2() {
        List<String> rowList = null;
        rowList = Lists.newArrayList(
                "所属支行", "所属网格", "姓名", "身份证号码",
                "正确身份证号码", "户号编码", "与户主关系", "联系电话",
                "原信用等级", "原授信金额", "新初评等级", "新初评授信",
                "新复评等级", "新复评授信", "信用产品", "所属客户经理工号"
        );
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("年审表");
        SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook, sheet);
        simpleStandardTable.setTableName("浏阳农商行年审表", rowList.size());
        simpleStandardTable.setTableHeader(rowList);
        try {
            String fileName = "年审表导入模版" + System.currentTimeMillis() + ".xls";
            String file = uploadpath + File.separator + fileName;
            //workbookWrite(request, response, "浏阳农商行年审表", workbook);
            OutputStream out = new FileOutputStream(file);
            workbook.write(out);
            workbook.close();
            out.flush();
            out.close();
            return Result.ok(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }


    @RequestMapping(value = "/exportNsb")
    public Result<?> exportNsb(HttpServletRequest request, VNhPjsx vNhPjsx, HttpServletResponse response) {
        String wgbh = null;
        if (StringUtils.isNotBlank(vNhPjsx.getWgbh()))
            wgbh = vNhPjsx.getWgbh();
        vNhPjsx.setWgbh(null);
        String sszh = null;
        if (StringUtils.isNotBlank(vNhPjsx.getSszh())) {
            sszh = vNhPjsx.getSszh();
            vNhPjsx.setSszh(null);
        }

        QueryWrapper<VNhPjsx> queryWrapper = QueryGenerator.initQueryWrapper(vNhPjsx, request.getParameterMap());

        if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {
            LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "vnhpjsx");
            List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
            boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
            if (b){
                if (StringUtils.isNotBlank(sszh)) {
                    queryWrapper.eq("sszh", sszh);
                }
            }else {
                queryWrapper.eq("sszh", getLoginUser().getOrgCode());
            }

            if (StringUtils.isNotBlank(wgbh)) {
                queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
            }

            /*if ("khjl".equalsIgnoreCase(getRedisRoleCode())) {
                if (StringUtils.isNotBlank(wgbh)) {
//					queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getWorkNo() + "' and  " +
//							"menu_id in (" +
//							"select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id )");

                    queryWrapper.inSql("wgbh",
                            "menu_id in (" +
                                    "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id )");
                }
                queryWrapper.eq("sskhjl", getWorkNo());
//				else {
//					queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + getWorkNo() + "'");
//				}
            } else if ("ZHHZ".equalsIgnoreCase(getRedisRoleCode()) || "khjl".equalsIgnoreCase(getRedisRoleCode()) || "zhfhz".equalsIgnoreCase(getRedisRoleCode())) {
                queryWrapper.eq("sszh", getLoginUser().getOrgCode());

                if (StringUtils.isNotBlank(wgbh)) {
                    queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
                }
            } else {
                if (StringUtils.isNotBlank(wgbh)) {
                    queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
                }
            }*/
        }

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
        queryWrapper.orderByAsc("hhbm", "yhzgx");
        List<VNhPjsx> list = service.list(queryWrapper);
        List<String> rowList = null;
        rowList = Lists.newArrayList(
                "所属支行", "所属网格", "姓名", "身份证号码",
                "正确身份证号码", "户号编码", "与户主关系", "联系电话",
                "原信用等级", "原授信金额", "新初评等级", "新初评授信",
                "新复评等级", "新复评授信", "信用产品", "所属客户经理工号",
                "贷款金额", "存款金额","存款日平","表内不良","表外不良","年审分类","年审分类原因","信贷系统授信金额","信贷系统冻结授信金额","近三年存款日平","逾期次数","户籍地址","信贷机构代码"
        );
        if (CollUtil.isNotEmpty(list)) {

            List<List<String>> nsbList = getNsbList(list);
            log.info("==={}条年审表开始生成===");
            log.info("===本次导出{}条年审表数据===", list.size());
            int couont = 65500;
            int n = list.size() / 65500 ;
            HSSFWorkbook workbook = new HSSFWorkbook();
            for (int i = 0; i < n + 1; i++) {
                HSSFSheet sheet = workbook.createSheet("年审表"+i);
                SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook, sheet);
                simpleStandardTable.setTableName("浏阳农商行年审表", rowList.size());
                simpleStandardTable.setTableHeader(rowList);
                if (i == n){
                    simpleStandardTable.setTableData(nsbList.subList(couont*i,nsbList.size()));
                }else {
                    simpleStandardTable.setTableData(nsbList.subList(couont*i,(i+1)*couont));
                }

                log.info("===数据完成===");
                String[] strings = {"A", "B", "C", "D", "E"};
                simpleStandardTable.setHSSFValidation(strings, 2, 65500, 10, 10);
                simpleStandardTable.setHSSFValidation(strings, 2, 65500, 12, 12);
                //查贷款种类
                List<DictModel> ly_xend_cpzl = sysDictService.queryDictItemsByCode("ly_xend_cpzl");
                String[] s2 = new String[ly_xend_cpzl.size()];
                for (int j = 0; j < ly_xend_cpzl.size(); j++) {
                    DictModel dictModel = ly_xend_cpzl.get(j);
                    if (dictModel != null && StringUtils.isNotBlank(dictModel.getText())){
                        s2[j] = dictModel.getText();
                    }
                }
                simpleStandardTable.setHSSFValidation(s2, 2, 65500, 14, 14);
            }


//            log.info("===本次导出{}条年审表数据===", list.size());
//            HSSFSheet sheet = workbook.createSheet("年审表");
//
//            SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook, sheet);
//            simpleStandardTable.setTableName("浏阳农商行年审表", rowList.size());
//            simpleStandardTable.setTableHeader(rowList);
//
//            simpleStandardTable.setTableData(nsbList);
            /*log.info("===数据完成===");
            String[] strings = {"A", "B", "C", "D", "E"};
            simpleStandardTable.setHSSFValidation(strings, 2, 60000, 10, 10);
            simpleStandardTable.setHSSFValidation(strings, 2, 60000, 12, 12);
            //查贷款种类
            List<DictModel> ly_xend_cpzl = sysDictService.queryDictItemsByCode("ly_xend_cpzl");
            String[] s2 = new String[ly_xend_cpzl.size()];
            for (int i = 0; i < ly_xend_cpzl.size(); i++) {
                DictModel dictModel = ly_xend_cpzl.get(i);
                if (dictModel != null && StringUtils.isNotBlank(dictModel.getText())){
                    s2[i] = dictModel.getText();
                }
            }
            simpleStandardTable.setHSSFValidation(s2, 2, 60000, 14, 14);*/

            //simpleStandardTable.setLyNsTableFooter(6, 12, 17);
            try {
                String fileName = "年审表" + System.currentTimeMillis() + ".xls";
                String file = uploadpath + File.separator + fileName;
                //workbookWrite(request, response, "浏阳农商行年审表", workbook);
                OutputStream out = new FileOutputStream(file);
                workbook.write(out);
                workbook.close();
                out.flush();
                out.close();
                return Result.ok(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error(e.getMessage());
            }
        }
        return Result.ok();
        //return null;
    }

    public List<List<String>> getNsbList(List<VNhPjsx> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            VNhPjsx vNhPjsx = list.get(i);
            List<String> colList = Lists.newArrayList();

            if (StringUtils.isNotBlank(vNhPjsx.getSszh())) {
                String s = sysDictService.queryTableDictTextByKey("hr_bas_organization", "ZZJC", "ZZBZ", vNhPjsx.getSszh());
                if (StringUtils.isNotBlank(s)) {
                    colList.add(s);
                } else {
                    colList.add("");
                }
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(vNhPjsx.getWgbh())) {
                String s = sysDictService.queryTableDictTextByKey("V_YXDYGL_MAIN", "WGMC_SHOW", "WGBH", vNhPjsx.getWgbh());
                if (StringUtils.isNotBlank(s)) {
                    colList.add(s);
                } else {
                    colList.add("");
                }
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(vNhPjsx.getKhmc())) {
                colList.add(vNhPjsx.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(vNhPjsx.getZjhm())) {
                colList.add(vNhPjsx.getZjhm());
            } else {
                colList.add("");
            }
            colList.add("");
            if (StringUtils.isNotBlank(vNhPjsx.getHhbm())) {
                colList.add(vNhPjsx.getHhbm());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(vNhPjsx.getYhzgx())) {
                String s = sysDictService.queryDictTextByKey("yhzgx", vNhPjsx.getYhzgx());
                if (StringUtils.isNotBlank(s)) {
                    colList.add(s);
                } else {
                    colList.add("");
                }
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(vNhPjsx.getSjhm())) {
                colList.add(vNhPjsx.getSjhm());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(vNhPjsx.getZzpddj())) {
                colList.add(vNhPjsx.getZzpddj());
            } else {
                colList.add("");
            }
            if (vNhPjsx.getZzsxed() != null) {
                colList.add(vNhPjsx.getZzsxed().toString());
            } else {
                colList.add("");
            }

            colList.add("");
            colList.add("");
            colList.add("");
            colList.add("");
            colList.add("");

            if (StringUtils.isNotBlank(vNhPjsx.getSskhjl())) {
                colList.add(vNhPjsx.getSskhjl());
            } else {
                colList.add("");
            }

            if (vNhPjsx.getDkje() != null) {
                colList.add(vNhPjsx.getDkje().toString());
            } else {
                colList.add("");
            }

            if (vNhPjsx.getCkje() != null) {
                colList.add(vNhPjsx.getCkje().toString());
            } else {
                colList.add("");
            }

            if (vNhPjsx.getCknrpye() != null) {
                colList.add(vNhPjsx.getCknrpye().toString());
            } else {
                colList.add("");
            }
            if (vNhPjsx.getBldkye() != null) {
                colList.add(vNhPjsx.getBldkye().toString());
            } else {
                colList.add("");
            }
            if (vNhPjsx.getBwbldkye() != null) {
                colList.add(vNhPjsx.getBwbldkye().toString());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(vNhPjsx.getNsfl())) {
                String s = sysDictService.queryDictTextByKey("ly_xend_nsfl", vNhPjsx.getNsfl());
                if (StringUtils.isNotBlank(s)) {
                    colList.add(s);
                } else {
                    colList.add("");
                }
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(vNhPjsx.getNsflyy())){
                colList.add(vNhPjsx.getNsflyy());
            }else {
                colList.add("");
            }

            if (vNhPjsx.getXdje() != null) {
                colList.add(vNhPjsx.getXdje().toString());
            } else {
                colList.add("");
            }
            if (vNhPjsx.getXddjje() != null) {
                colList.add(vNhPjsx.getXddjje().toString());
            } else {
                colList.add("");
            }
            if (vNhPjsx.getSanckrp() != null) {
                colList.add(vNhPjsx.getSanckrp().toString());
            } else {
                colList.add("");
            }
            if (vNhPjsx.getYqcs() != null) {
                colList.add(vNhPjsx.getYqcs().toString());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(vNhPjsx.getHjdz())) {
                colList.add(vNhPjsx.getHjdz());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(vNhPjsx.getXdjgdm())){
                String s = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "ywjgdm", vNhPjsx.getXdjgdm());
                if (StringUtils.isNotBlank(s)) {
                    colList.add(s);
                } else {
                    colList.add("");
                }
            }else {
                colList.add("");
            }

            listArrayList.add(colList);
        }
        return listArrayList;
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
        return super.importExcel(request, response, VNhPjsx.class);
    }


    @RequestMapping("/xendFtl")
    public Result<?> download(String id, HttpServletRequest request, HttpServletResponse response) {
        //模板中的值没有会报错 先默认空值
        Map<String, Object> data = new HashMap<>();
        data.put("zhen", "");
        data.put("cun", "");
        data.put("zu", "");
        data.put("khmc", "");
        data.put("sex", "");
        data.put("zjhm", "");
        data.put("hyzk", "");
        data.put("zy", "");
        data.put("sjhm", "");
        data.put("khmc1", "");
        data.put("yhzgx1", "");
        data.put("cszy1", "");
        data.put("zjhm1", "");
        data.put("sjhm1", "");
        data.put("khmc2", "");
        data.put("yhzgx2", "");
        data.put("cszy2", "");
        data.put("zjhm2", "");
        data.put("sjhm2", "");
        data.put("khmc3", "");
        data.put("yhzgx3", "");
        data.put("cszy3", "");
        data.put("zjhm3", "");
        data.put("sjhm3", "");
        data.put("gdzchj", "");
        data.put("gdzcZfts", "");
        data.put("gdzcZfmj", "");
        data.put("gdzcZfjz", "");
        data.put("gdzcQt", "");
        data.put("zzchj", "");
        data.put("ldzcHj", "");
        data.put("ldzcXjjwhck", "");
        data.put("ldzcYsk", "");
        data.put("ldzcQt", "");
        data.put("dkje", "");
        data.put("jkr", "");
        data.put("dqr", "");
        data.put("fzSrjkhqtjk", "");
        data.put("fzYfk", "");
        data.put("fzHj", "");
        data.put("fzHj2", "");
        data.put("jtjzc", "");
        data.put("srHj", "");
        data.put("srGsy", "");
        data.put("srLw", "");
        data.put("srQtsr", "");
        data.put("zcHj", "");
        data.put("zcSccb", "");
        data.put("zcRcsh", "");
        data.put("zcJy", "");
        data.put("zcQtzc", "");
        data.put("jtjsr", "");
        data.put("sxed", "");
        data.put("snpddj", "");
        data.put("snsxje", "");
        //查农户信息
        VNhPjsx byId = service.getById(id);
        if (byId != null) {
            if (StringUtils.isNotBlank(byId.getWgbh())) {
                //确实是否是组  要找出 镇 村 组
                VYxdyglMain byId1 = ivYxdyglMainService.getById(byId.getWgbh());
                String wgmcShow = byId1.getWgmcShow();
                String[] split = wgmcShow.split("-");
                for (int i = 0; i < split.length; i++) {
                    if (i == 0) {
                        data.put("zhen", split[0]);
                    }
                    if (i == 1) {
                        data.put("cun", split[1]);
                    }
                    if (i == 2) {
                        data.put("zu", split[2]);
                    }
                }

                if (StringUtils.isNotBlank(byId.getKhmc())) {
                    data.put("khmc", byId.getKhmc());
                }

                if (StringUtils.isNotBlank(byId.getZjhm())) {
                    data.put("zjhm", byId.getZjhm());
                }

                if (StringUtils.isNotBlank(byId.getXb())) {
                    String sex = sysDictService.queryDictTextByKey("sex", byId.getXb());
                    data.put("sex", sex);
                }

                if (StringUtils.isNotBlank(byId.getHyzk())) {
                    String str = sysDictService.queryDictTextByKey("hyzk", byId.getHyzk());
                    if (StringUtils.isNotBlank(str))
                        data.put("hyzk", str);
                }

                if (StringUtils.isNotBlank(byId.getCshygz())) {
                    String str = sysDictService.queryDictTextByKey("cszy", byId.getCshygz());
                    if (StringUtils.isNotBlank(str))
                        data.put("zy", str);
                }
                if (StringUtils.isNotBlank(byId.getSjhm())) {
                    data.put("sjhm", byId.getSjhm());
                }

                if (StringUtils.isNotBlank(byId.getHhbm())) {
                    LambdaQueryWrapper<VNhPjsx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                    lambdaQueryWrapper.eq(VNhPjsx::getHhbm, byId.getHhbm());
                    lambdaQueryWrapper.orderByAsc(VNhPjsx::getYhzgx);
                    List<VNhPjsx> list = service.list(lambdaQueryWrapper);
                    if (CollUtil.isNotEmpty(list)) {
                        int n = list.size() > 3 ? 3 : list.size();
                        for (int i = 0; i < n; i++) {
                            VNhPjsx vNhPjsx = list.get(i);
                            if (i == 0) {
                                if (StringUtils.isNotBlank(vNhPjsx.getKhmc())) {
                                    data.put("khmc1", vNhPjsx.getKhmc());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getYhzgx())) {
                                    String yhzgx = sysDictService.queryDictTextByKey("yhzgx", vNhPjsx.getYhzgx());
                                    if (StringUtils.isNotBlank(yhzgx))
                                        data.put("yhzgx1", yhzgx);
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getCshygz())) {
                                    String str = sysDictService.queryDictTextByKey("cszy", vNhPjsx.getCshygz());
                                    if (StringUtils.isNotBlank(str))
                                        data.put("cszy1", str);
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getZjhm())) {
                                    data.put("zjhm1", vNhPjsx.getZjhm());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getSjhm())) {
                                    data.put("sjhm1", vNhPjsx.getSjhm());
                                }
                            }
                            if (i == 1) {
                                if (StringUtils.isNotBlank(vNhPjsx.getKhmc())) {
                                    data.put("khmc2", vNhPjsx.getKhmc());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getYhzgx())) {
                                    String yhzgx = sysDictService.queryDictTextByKey("yhzgx", vNhPjsx.getYhzgx());
                                    if (StringUtils.isNotBlank(yhzgx))
                                        data.put("yhzgx2", yhzgx);
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getCshygz())) {
                                    String str = sysDictService.queryDictTextByKey("cszy", vNhPjsx.getCshygz());
                                    if (StringUtils.isNotBlank(str))
                                        data.put("cszy2", str);
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getZjhm())) {
                                    data.put("zjhm2", vNhPjsx.getZjhm());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getSjhm())) {
                                    data.put("sjhm2", vNhPjsx.getSjhm());
                                }
                            }
                            if (i == 2) {
                                if (StringUtils.isNotBlank(vNhPjsx.getKhmc())) {
                                    data.put("khmc3", vNhPjsx.getKhmc());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getYhzgx())) {
                                    String yhzgx = sysDictService.queryDictTextByKey("yhzgx", vNhPjsx.getYhzgx());
                                    if (StringUtils.isNotBlank(yhzgx))
                                        data.put("yhzgx3", yhzgx);
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getCshygz())) {
                                    String str = sysDictService.queryDictTextByKey("cszy", vNhPjsx.getCshygz());
                                    if (StringUtils.isNotBlank(str))
                                        data.put("cszy3", str);
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getZjhm())) {
                                    data.put("zjhm3", vNhPjsx.getZjhm());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getSjhm())) {
                                    data.put("sjhm3", vNhPjsx.getSjhm());
                                }
                            }
                        }
                    }
                }

                //调查日家庭资产情况
                if (byId.getGdzcHj() != null) {
                    data.put("gdzchj", byId.getGdzcHj());
                }
                if (byId.getGdzcZfts() != null) {
                    data.put("gdzcZfts", byId.getGdzcZfts());
                }
                if (byId.getGdzcZfmj() != null) {
                    data.put("gdzcZfmj", byId.getGdzcZfmj());
                }
                if (byId.getGdzcZfjz() != null) {
                    data.put("gdzcZfjz", byId.getGdzcZfjz());
                }
                if (byId.getGdzcQt() != null) {
                    data.put("gdzcQt", byId.getGdzcQt());
                }

                if (byId.getLdzcHj() != null) {
                    data.put("ldzcHj", byId.getLdzcHj());
                }
                if (byId.getLdzcXjjwhck() != null) {
                    data.put("ldzcXjjwhck", byId.getLdzcXjjwhck());
                }
                if (byId.getLdzcYsk() != null) {
                    data.put("ldzcYsk", byId.getLdzcYsk());
                }
                if (byId.getLdzcQt() != null) {
                    data.put("ldzcQt", byId.getLdzcQt());
                }

                if (byId.getZzchj() != null) {
                    data.put("zzchj", byId.getZzchj());
                }

                if (byId.getFzWhjk() != null) {
                    data.put("dkje", byId.getFzWhjk());
                }

                if (byId.getFzSrjkhqtjk() != null) {
                    data.put("fzSrjkhqtjk", byId.getFzSrjkhqtjk());
                }

                if (byId.getFzYfk() != null) {
                    data.put("fzYfk", byId.getFzYfk());
                }
                if (byId.getFzHj() != null) {
                    data.put("fzHj", byId.getFzHj());
                    data.put("fzHj2", byId.getFzHj());
                }
                if (byId.getJtjzc() != null) {
                    data.put("jtjzc", byId.getJtjzc());
                }
                if (byId.getSrHj() != null) {
                    data.put("srHj", byId.getSrHj());
                }
                if (byId.getSrGsy() != null) {
                    data.put("srGsy", byId.getSrGsy());
                }
                if (byId.getSrLw() != null) {
                    data.put("srLw", byId.getSrLw());
                }
                if (byId.getSrQtsr() != null) {
                    data.put("srQtsr", byId.getSrQtsr());
                }
                if (byId.getZcHj() != null) {
                    data.put("zcHj", byId.getZcHj());
                }
                if (byId.getZcSccb() != null) {
                    data.put("zcSccb", byId.getZcSccb());
                }
                if (byId.getZcRcsh() != null) {
                    data.put("zcRcsh", byId.getZcRcsh());
                }
                if (byId.getZcJy() != null) {
                    data.put("zcJy", byId.getZcJy());
                }
                if (byId.getZcQtzc() != null) {
                    data.put("zcQtzc", byId.getZcQtzc());
                }
                if (byId.getJtjsr() != null) {
                    data.put("jtjsr", byId.getJtjsr());
                }

                if (byId.getZzpddj() != null) {
                    data.put("snpddj", byId.getZzpddj());
                }
                if (byId.getZzsxed() != null) {
                    data.put("snsxje", byId.getZzsxed());
                }
            }
        }

        try {
            String fileName = byId.getKhmc() + "的授信审批表.docx";
            String path = uploadpath + File.separator + fileName;
            String resourceName = "xend.ftl";
            WordUtils.generateWord(data, path, resourceName);
            //boolean file = FileUtil.isFile(path);
            return Result.ok(fileName);

//		  inputStream = new BufferedInputStream(new FileInputStream(fileName));
//		  outputStream = response.getOutputStream();
//
//		  byte[] buf = new byte[1024];
//		  int len;
//		  while ((len = inputStream.read(buf)) > 0) {
//			  outputStream.write(buf, 0, len);
//		  }
//		  response.setContentType("application/force-download");// 设置强制下载不打开
//		  response.addHeader("Content-Disposition", "attachment;fileName=" + new String("xend.docx".getBytes("UTF-8"),"iso-8859-1"));
//		  outputStream.write(buf);
//		  outputStream.flush();
//		  outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return Result.error("");
    }


    @RequestMapping("/xendFtl2")
    public Result<?> download2(String id, HttpServletRequest request, HttpServletResponse response) {
        //模板中的值没有会报错 先默认空值
        Map<String, Object> data = new HashMap<>();

        //查农户信息
        VNhPjsx byId = service.getById(id);
        if (byId != null) {
            if (StringUtils.isNotBlank(byId.getWgbh())) {
                //确实是否是组  要找出 镇 村 组
                VYxdyglMain byId1 = ivYxdyglMainService.getById(byId.getWgbh());
                String wgmcShow = byId1.getWgmcShow();
                String[] split = wgmcShow.split("-");
                for (int i = 0; i < split.length; i++) {
                    if (i == 0) {
                        String s = split[0].replace("镇","").replace("乡","").replace("街道","");
                        data.put("zhen", s);
                    }
                    if (i == 1) {
                        data.put("cun", split[1].replace("村","").replace("社区",""));
                    }
                    if (i == 2) {
                        data.put("zu", split[2].replace("组","").replace("小区",""));
                    }
                }



                if (StringUtils.isNotBlank(byId.getXb())) {
                    String sex = sysDictService.queryDictTextByKey("sex", byId.getXb());
                    byId.setXb(sex);
                }

                if (StringUtils.isNotBlank(byId.getHyzk())) {
                    String str = sysDictService.queryDictTextByKey("hyzk_cj", byId.getHyzk());
                    if (StringUtils.isNotBlank(str)){
                        byId.setHyzk(str);
                    }
                }

                if (StringUtils.isNotBlank(byId.getCszy())) {
                    String str = sysDictService.queryDictTextByKey("cszy", byId.getCszy());
                    if (StringUtils.isNotBlank(str)){
                        byId.setCshygz(str);
                    }
                }


                if (StringUtils.isNotBlank(byId.getHhbm())) {
                    LambdaQueryWrapper<VNhPjsx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                    lambdaQueryWrapper.eq(VNhPjsx::getHhbm, byId.getHhbm());
                    lambdaQueryWrapper.orderByAsc(VNhPjsx::getYhzgx);
                    List<VNhPjsx> list = service.list(lambdaQueryWrapper);
                    if (CollUtil.isNotEmpty(list)) {
                        int n = list.size() > 3 ? 3 : list.size();
                        for (int i = 0; i < n; i++) {
                            VNhPjsx vNhPjsx = list.get(i);
                            if (i == 0) {
                                if (StringUtils.isNotBlank(vNhPjsx.getKhmc())) {
                                    data.put("khmc1", vNhPjsx.getKhmc());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getYhzgx())) {
                                    String yhzgx = sysDictService.queryDictTextByKey("yhzgx", vNhPjsx.getYhzgx());
                                    if (StringUtils.isNotBlank(yhzgx))
                                        data.put("yhzgx1", yhzgx);

                                    if ("1".equals(vNhPjsx.getYhzgx())){
                                        data.put("sfhz1","是");
                                    }else {
                                        data.put("sfhz1","否");
                                    }
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getCshygz())) {
                                    String str = sysDictService.queryDictTextByKey("cszy", vNhPjsx.getCshygz());
                                    if (StringUtils.isNotBlank(str))
                                        data.put("zy1", str);
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getZjhm())) {
                                    data.put("zjhm1", vNhPjsx.getZjhm());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getSjhm())) {
                                    data.put("sjhm1", vNhPjsx.getSjhm());
                                }
                            }
                            if (i == 1) {
                                if (StringUtils.isNotBlank(vNhPjsx.getKhmc())) {
                                    data.put("khmc2", vNhPjsx.getKhmc());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getYhzgx())) {
                                    String yhzgx = sysDictService.queryDictTextByKey("yhzgx", vNhPjsx.getYhzgx());
                                    if (StringUtils.isNotBlank(yhzgx))
                                        data.put("yhzgx2", yhzgx);

                                    if ("1".equals(vNhPjsx.getYhzgx())){
                                        data.put("sfhz2","是");
                                    }else {
                                        data.put("sfhz2","否");
                                    }
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getCshygz())) {
                                    String str = sysDictService.queryDictTextByKey("cszy", vNhPjsx.getCshygz());
                                    if (StringUtils.isNotBlank(str))
                                        data.put("zy2", str);
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getZjhm())) {
                                    data.put("zjhm2", vNhPjsx.getZjhm());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getSjhm())) {
                                    data.put("sjhm2", vNhPjsx.getSjhm());
                                }
                            }
                            if (i == 2) {
                                if (StringUtils.isNotBlank(vNhPjsx.getKhmc())) {
                                    data.put("khmc3", vNhPjsx.getKhmc());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getYhzgx())) {
                                    String yhzgx = sysDictService.queryDictTextByKey("yhzgx", vNhPjsx.getYhzgx());
                                    if (StringUtils.isNotBlank(yhzgx))
                                        data.put("yhzgx3", yhzgx);

                                    if ("1".equals(vNhPjsx.getYhzgx())){
                                        data.put("sfhz3","是");
                                    }else {
                                        data.put("sfhz3","否");
                                    }
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getCshygz())) {
                                    String str = sysDictService.queryDictTextByKey("cszy", vNhPjsx.getCshygz());
                                    if (StringUtils.isNotBlank(str))
                                        data.put("zy3", str);
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getZjhm())) {
                                    data.put("zjhm3", vNhPjsx.getZjhm());
                                }
                                if (StringUtils.isNotBlank(vNhPjsx.getSjhm())) {
                                    data.put("sjhm3", vNhPjsx.getSjhm());
                                }
                            }
                        }
                    }
                }

                //
                if (StringUtils.isNotBlank(byId.getShsySfxsfz())){
                    String ywbz = dxkcl(byId.getShsySfxsfz(), "ywbz");
                    data.put("ywwfxw",ywbz);
                }else {
                    String ywbz = dxkcl("ywbz");
                    data.put("ywwfxw",ywbz);
                }

                if (StringUtils.isNotBlank(byId.getDhzpjPxpj())){
                    String ywbz = dxkcl(byId.getDhzpjPxpj(), "grdk_khpx");
                    data.put("pxpj",ywbz);
                }else {
                    String ywbz = dxkcl("grdk_khpx");
                    data.put("pxpj",ywbz);
                }
                //grdk_khpx pjsx_pddj
                if (StringUtils.isNotBlank(byId.getCpdj())){
                    String ywbz = dxkcl(byId.getCpdj(), "pjsx_pddj");
                    data.put("cpdj",ywbz);
                }else {
                    String ywbz = dxkcl("pjsx_pddj");
                    data.put("cpdj",ywbz);
                }

                if (StringUtils.isNotBlank(byId.getZzpddj())){
                    String ywbz = dxkcl(byId.getZzpddj(), "pjsx_pddj");
                    data.put("fpdj",ywbz);
                }else {
                    String ywbz = dxkcl("pjsx_pddj");
                    data.put("fpdj",ywbz);
                }

                if (StringUtils.isNotBlank(byId.getCpzl())){

                    String cpzl = sysDictService.queryDictTextByKey("ly_xend_cpzl", byId.getCpzl());
                    String bp = sysDictService.queryDictTextByKey("ly_xend_cpzlll", byId.getCpzl());
                    if (StringUtils.isNotBlank(cpzl)){
                        data.put("sycp",byId.getCpzl());
                    }

                    if (StringUtils.isNotBlank(bp)){
                        data.put("bp",bp);
                    }

                }

                if (StringUtils.isNotBlank(byId.getSign2())){
                    String imgStr = getImageStr(uploadpath+byId.getSign2());
                    byId.setSign2(imgStr);
                    data.put("png0","0");
                }

                if (StringUtils.isNotBlank(byId.getKhjlqz())){
                    String imgStr = getImageStr(uploadpath+byId.getKhjlqz());
                    byId.setKhjlqz(imgStr);
                    data.put("png1","1");
                }

                if (StringUtils.isNotBlank(byId.getFxjlqz())){
                    String imgStr = getImageStr(uploadpath+byId.getFxjlqz());
                    byId.setFxjlqz(imgStr);
                    data.put("png2","2");
                }

                if (StringUtils.isNotBlank(byId.getZhhzqz())){
                    String imgStr = getImageStr(uploadpath+byId.getZhhzqz());
                    byId.setZhhzqz(imgStr);
                    data.put("png3","3");
                }


                if (byId.getZzsxed() != null){
                    data.put("fxjlsxje",byId.getZzsxed());
                }
                data.put("d",byId);
            }
        }

        try {
            String fileName = byId.getKhmc() + "的授信审批表.docx";
            String path = uploadpath + File.separator + fileName;
            String resourceName = "xend2.ftl";
            WordUtils.generateWord(data, path, resourceName);
            //boolean file = FileUtil.isFile(path);
            return Result.ok(fileName);

//		  inputStream = new BufferedInputStream(new FileInputStream(fileName));
//		  outputStream = response.getOutputStream();
//
//		  byte[] buf = new byte[1024];
//		  int len;
//		  while ((len = inputStream.read(buf)) > 0) {
//			  outputStream.write(buf, 0, len);
//		  }
//		  response.setContentType("application/force-download");// 设置强制下载不打开
//		  response.addHeader("Content-Disposition", "attachment;fileName=" + new String("xend.docx".getBytes("UTF-8"),"iso-8859-1"));
//		  outputStream.write(buf);
//		  outputStream.flush();
//		  outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return Result.error("");
    }


    public static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    public String dxkcl(String val, String type) {
        List<DictModel> dictModels = sysDictService.queryDictItemsByCode(type);
        if (CollUtil.isNotEmpty(dictModels)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < dictModels.size(); i++) {
                DictModel dictModel = dictModels.get(i);
                if (val.equals(dictModel.getValue())) {
                    stringBuffer.append(" ").append("☑").append(dictModel.getText());
                } else {
                    stringBuffer.append(" ").append("□").append(dictModel.getText());
                }
            }
            return stringBuffer.toString();
        }

        return "";
    }

    public String dxkcl(String type) {
        List<DictModel> dictModels = sysDictService.queryDictItemsByCode(type);
        if (CollUtil.isNotEmpty(dictModels)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < dictModels.size(); i++) {
                DictModel dictModel = dictModels.get(i);
                stringBuffer.append(" ").append("□").append(dictModel.getText());
            }
            return stringBuffer.toString();
        }

        return "";
    }

    @RequestMapping("/ywhywxx")
    public Result<?> ywhywxx(String hhbm) {
        JSONObject jsonObject = new JSONObject();

        YhwywxlTotalVO hj = new YhwywxlTotalVO();
        BigDecimal ckye = new BigDecimal("0");
        BigDecimal dqckye = new BigDecimal("0");
        BigDecimal cknrpye = new BigDecimal("0");
        BigDecimal dkje = new BigDecimal("0");
        BigDecimal dkye = new BigDecimal("0");
        BigDecimal bldkye = new BigDecimal("0");
        BigDecimal bwbldkye = new BigDecimal("0");

        hj.setCkye(ckye);
        hj.setDqckye(dqckye);
        hj.setCknrpye(cknrpye);
        hj.setDkje(dkje);
        hj.setDkye(dkye);
        hj.setBldkye(bldkye);
        hj.setBwbldkye(bwbldkye);
        hj.setSjyhsl(0);
        hj.setWsyhsl(0);
        hj.setSbksl(0);
        hj.setEtssl(0);

        LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Nhxq::getHhbm, hhbm);
        List<Nhxq> list = nhxqService.list(lambdaQueryWrapper);
        List<String> collect = list.stream().map(n -> n.getZjhm()).distinct().collect(Collectors.toList());
        if (!collect.isEmpty()) {
            LambdaQueryWrapper<Ywhywwlxx> ywhywwlxxLambdaQueryWrapper = new LambdaQueryWrapper<>();
            ywhywwlxxLambdaQueryWrapper.in(Ywhywwlxx::getZjhm, collect);
            List<Ywhywwlxx> list1 = ywhywwlxxService.list(ywhywwlxxLambdaQueryWrapper);

            if (!list1.isEmpty()) {
                //合计全家人信息
                jsonObject.put("list", list1);

                int sjyhsl = 0;
                int wsyhsl = 0;
                int sbksl = 0;
                int etssl = 0;
                for (int i = 0; i < list1.size(); i++) {
                    Ywhywwlxx ywhywwlxx = list1.get(i);
                    if (ywhywwlxx.getCkye() != null)
                        ckye = ckye.add(ywhywwlxx.getCkye());
                    if (ywhywwlxx.getDqckye() != null)
                        dqckye = dqckye.add(ywhywwlxx.getDqckye());
                    if (ywhywwlxx.getCknrpye() != null)
                        cknrpye = cknrpye.add(ywhywwlxx.getCknrpye());
                    if (ywhywwlxx.getDkje() != null)
                        dkje = dkje.add(ywhywwlxx.getDkje());
                    if (ywhywwlxx.getDkye() != null)
                        dkye = dkye.add(ywhywwlxx.getDkye());
                    if (ywhywwlxx.getBldkye() != null)
                        bldkye = bldkye.add(ywhywwlxx.getBldkye());
                    if (ywhywwlxx.getBwbldkye() != null)
                        bwbldkye = bwbldkye.add(ywhywwlxx.getBwbldkye());

                    if (StringUtils.isNotBlank(ywhywwlxx.getSfktsjyhyw()) && "1".equals(ywhywwlxx.getSfktsjyhyw())) {
                        sjyhsl++;
                    }
                    if (StringUtils.isNotBlank(ywhywwlxx.getSfktwsyhyw()) && "1".equals(ywhywwlxx.getSfktwsyhyw())) {
                        wsyhsl++;
                    }
                    if (StringUtils.isNotBlank(ywhywwlxx.getSfktsbk()) && "1".equals(ywhywwlxx.getSfktsbk())) {
                        sbksl++;
                    }
                    if (StringUtils.isNotBlank(ywhywwlxx.getSfbletcyw()) && "1".equals(ywhywwlxx.getSfbletcyw())) {
                        etssl++;
                    }
                }

                hj.setCkye(ckye);
                hj.setDqckye(dqckye);
                hj.setCknrpye(cknrpye);
                hj.setDkje(dkje);
                hj.setDkye(dkye);
                hj.setBldkye(bldkye);
                hj.setBwbldkye(bwbldkye);
                hj.setSjyhsl(sjyhsl);
                hj.setWsyhsl(wsyhsl);
                hj.setSbksl(sbksl);
                hj.setEtssl(etssl);
            }

        }
        jsonObject.put("hj", hj);
        return Result.ok(jsonObject);
    }

    @Autowired
    private IGrkhpjsxService grkhpjsxService;
    @Autowired
    private IActXendSplsService iActXendSplsService;

    @PutMapping(value = "/apply")
    public Result<?> edit2(@RequestBody VNhPjsx data) {
        if (StringUtils.isBlank(data.getId()))
            return Result.ok();
        VNhPjsx grkhpjsx = service.getById(data.getId());

        // 状态 1 客户经理申请   => 2 风险经理审批  => 3 支行行长审批
        //						0 拒绝
        ActXendSpls spls = new ActXendSpls();
        spls.setHhbm(grkhpjsx.getHhbm());
        spls.setZjhm(grkhpjsx.getZjhm());
        spls.setPddj(data.getZzpddj());
        spls.setJyed(data.getZzsxed());
        spls.setSpyj(data.getYj());
        spls.setUserid(getLoginUser().getId());
        spls.setYggh(getWorkNo());
        spls.setStatus("1");
        spls.setCpzl(data.getCpzl());
        spls.setCpzlll(data.getCpzlll());
        if (StringUtils.isNotBlank(data.getSfsx()) && "2".equals(data.getSfsx()))
            spls.setStatus(data.getSfsx());

        int status = 0;
        if (grkhpjsx.getStatus() == 1 || grkhpjsx.getStatus() == 4 || grkhpjsx.getStatus() == 0) {
            String id = IdUtil.fastUUID();
            status = 2;
            //grkhpjsxService.updateGrpjsxxxZjhm2(id, data.getZzpddj(), data.getZzsxed(), data.getYj(), data.getZjhm(),2);
        } else if (grkhpjsx.getStatus() == 2) {
            //拒绝
            if (StringUtils.isNotBlank(data.getSfsx()) && "2".equals(data.getSfsx())) {
                status = 0;
                //grkhpjsxService.updateGrpjsxxxZjhm2(grkhpjsx.getSpid(), data.getZzpddj(), data.getZzsxed(), data.getYj(), data.getZjhm(),0);
            } else {
                status = 3;
                //grkhpjsxService.updateGrpjsxxxZjhm2(grkhpjsx.getSpid(), data.getZzpddj(), data.getZzsxed(), data.getYj(), data.getZjhm(),3);
            }
        } else if (grkhpjsx.getStatus() == 3) {
            if (StringUtils.isNotBlank(data.getSfsx()) && "2".equals(data.getSfsx())) {
                status = 0;
                //grkhpjsxService.updateGrpjsxxxZjhm2(grkhpjsx.getSpid(), data.getZzpddj(), data.getZzsxed(), data.getYj(), data.getZjhm(),0);
            } else {
                status = 4;
                //grkhpjsxService.updateGrpjsxxxZjhm2(grkhpjsx.getSpid(), data.getZzpddj(), data.getZzsxed(), data.getYj(), data.getZjhm(), 4);
            }
        }
        if (status == 4) {
            grkhpjsxService.updateGrpjsxxxZjhm2(data.getZzpddj(), data.getZzsxed(), data.getYj(), data.getZjhm(), status,
                    data.getCpzl(),data.getCpzlll());
            //通过的数据需要同步到惠农快贷  同步到电子贷款证
            sysDzdkz(grkhpjsx, data.getZzsxed(), data.getZzpddj());
            //sysHnkd(grkhpjsx, data.getZzsxed(),data.getCpzlll());
        } else {
            grkhpjsxService.updateGrpjsxxxZjhmAndStatus(data.getZjhm(), status);

        }

        if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {
            String fwlj = "/sign/nhxq/";
            if (!FileUtil.isDirectory(uploadpath + fwlj)) {
                FileUtil.mkdir(uploadpath + fwlj);
            }

            QueryWrapper<Nhxq> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", data.getId());
            Nhxq nhxq = nhxqService.getOne(queryWrapper,false);

            if (StringUtils.isNotBlank(data.getKhjlqz())) {
                if (data.getKhjlqz().startsWith(CommonConstant.BASE64_PREFIX)) {
                    String fileName = IdUtil.fastSimpleUUID() + ".png";
                    String wjlj = uploadpath + fwlj + fileName;
                    Base64Util.toImage(data.getKhjlqz(), wjlj);
                    data.setKhjlqz(fwlj + fileName);
                    nhxq.setKhjlqz(data.getKhjlqz());
                } else {
                    data.setKhjlqz(null);
                }
            }

            if (StringUtils.isNotBlank(data.getFxjlqz())) {
                if (data.getFxjlqz().startsWith(CommonConstant.BASE64_PREFIX)) {
                    String fileName = IdUtil.fastSimpleUUID() + ".png";
                    String wjlj = uploadpath + fwlj + fileName;
                    Base64Util.toImage(data.getFxjlqz(), wjlj);
                    data.setFxjlqz(fwlj + fileName);
                    nhxq.setFxjlqz(data.getFxjlqz());
                } else {
                    data.setFxjlqz(null);
                }
            }

            if (StringUtils.isNotBlank(data.getZhhzqz())) {
                if (data.getZhhzqz().startsWith(CommonConstant.BASE64_PREFIX)) {
                    String fileName = IdUtil.fastSimpleUUID() + ".png";
                    String wjlj = uploadpath + fwlj + fileName;
                    Base64Util.toImage(data.getZhhzqz(), wjlj);
                    data.setZhhzqz(fwlj + fileName);
                    nhxq.setZhhzqz(data.getZhhzqz());
                } else {
                    data.setZhhzqz(null);
                }
            }

            nhxqService.updateById(nhxq);
        }

        iActXendSplsService.save(spls);
        return Result.ok();
    }


    @Autowired
    IKhxxglHnkdService khxxglHnkdService;

    public void sysDzdkz(VNhPjsx vNhPjsx, BigDecimal zzsxed, String zzpddj) {
        try {
            String type = "1";
            String sszh = null;
            if (StringUtils.isNotBlank(vNhPjsx.getSszh())) {

                if ("6".equals(vNhPjsx.getSszh()) || "2".equals(vNhPjsx.getSszh())
                        || "10".equals(vNhPjsx.getSszh()) || "18".equals(vNhPjsx.getSszh())) {
                    type = "2";
                }

                sszh = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "zzbz", vNhPjsx.getSszh());
            } else {
                sszh = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "zzbz", getLoginUser().getOrgCode());
            }
            sysLoanInfoService.comDzdkzUpdate(vNhPjsx.getKhmc() == null ? "" : vNhPjsx.getKhmc(),
                    vNhPjsx.getZjhm() == null ? "" : vNhPjsx.getZjhm(), zzpddj == null ? "" : zzpddj,
                    zzsxed == null ? new BigDecimal("0") : zzsxed, type, getWorkNo(), "", sszh.replace("支行", ""));
        } catch (Exception e) {
            log.info("===更新电子贷款证失败===");
        }
    }

    public void sysHnkd(VNhPjsx vNhPjsx, BigDecimal zzsxed,BigDecimal cpzlll) {
        try {
            LambdaQueryWrapper<KhxxglHnkd> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(KhxxglHnkd::getZjhm, vNhPjsx.getZjhm());
            KhxxglHnkd one = khxxglHnkdService.getOne(lambdaQueryWrapper);
            if (one == null) {

            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("===审批流程更新惠农快贷失败===");
        }
    }

    @RequestMapping("/splsXend")
    public Result<?> splsXend(String zjhm, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<ActXendSpls> page = new Page<ActXendSpls>(pageNo, pageSize);
        LambdaQueryWrapper<ActXendSpls> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ActXendSpls::getZjhm, zjhm);
        lambdaQueryWrapper.orderByAsc(ActXendSpls::getCreateTime);
        Page<ActXendSpls> page1 = iActXendSplsService.page(page, lambdaQueryWrapper);
        return Result.ok(page1);
    }

    @Autowired
    SysLoanInfoService sysLoanInfoService;

    @RequestMapping(value = "/importExcel2", method = RequestMethod.POST)
    public Result<?> importExcel2(@RequestBody JSONObject jsonObject) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
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
            params.setVerifyHanlder(nsImportVerity);
            FileOutputStream fos = null;
            try {
                ExcelImportResult<NsImportVO> importResult = ExcelImportUtil.importExcelVerify(file, NsImportVO.class, params);
                List<NsImportVO> list = importResult.getList();
                for (NsImportVO nsImportVO : list) {
                    try {
                        LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                        lambdaQueryWrapper.eq(Nhxq::getZjhm, nsImportVO.getZjhm());
                        long count = nhxqService.count(lambdaQueryWrapper);
                        if (count > 0) {
                            service.updateNh(nsImportVO);
                        } else {
                            Nhxq nhxq = new Nhxq();
                            nhxq.setZjhm(nsImportVO.getZjhm());
                            nhxq.setKhmc(nsImportVO.getKhmc());
                            nhxq.setCreateBy(getWorkNo());
                            nhxq.setCreateTime(new Date());
                            nhxq.setUpdateBy(getWorkNo());
                            nhxq.setUpdateTime(new Date());
                            if (StringUtils.isNotBlank(nsImportVO.getSszh()))
                                nhxq.setSszh(nsImportVO.getSszh());
                            if (StringUtils.isNotBlank(nsImportVO.getSskhjl()))
                                nhxq.setSskhjl(nsImportVO.getSskhjl());
                            if (StringUtils.isNotBlank(nsImportVO.getWgbh()))
                                nhxq.setWgbh(nsImportVO.getWgbh());
                            if (StringUtils.isNotBlank(nsImportVO.getHhbm()))
                                nhxq.setHhbm(nsImportVO.getHhbm());
                            if (StringUtils.isNotBlank(nsImportVO.getYhzgx()))
                                nhxq.setYhzgx(nsImportVO.getYhzgx());
                            if (StringUtils.isNotBlank(nsImportVO.getSjhm()))
                                nhxq.setSjhm(nsImportVO.getSjhm());
                            nhxqService.save(nhxq);
                        }
                        LambdaQueryWrapper<CamsZcsxGrpjsxxx> camsZcsxGrpjsxxxLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        camsZcsxGrpjsxxxLambdaQueryWrapper.eq(CamsZcsxGrpjsxxx::getZjhm, nsImportVO.getZjhm());
                        long count1 = camsZcsxGrpjsxxxService.count(camsZcsxGrpjsxxxLambdaQueryWrapper);
                        if (count1 > 0) {
                            service.updatePjsx(nsImportVO);
                        } else {
                            CamsZcsxGrpjsxxx camsZcsxGrpjsxxx = new CamsZcsxGrpjsxxx();
                            camsZcsxGrpjsxxx.setKhmc(nsImportVO.getKhmc());
                            camsZcsxGrpjsxxx.setZjhm(nsImportVO.getZjhm());
                            camsZcsxGrpjsxxx.setZzpddj(nsImportVO.getZzpddj());
                            camsZcsxGrpjsxxx.setZzsxed(new BigDecimal(nsImportVO.getZzsxed()));
                            camsZcsxGrpjsxxx.setCreateBy(getWorkNo());
                            camsZcsxGrpjsxxx.setCreateTime(new Date());
                            camsZcsxGrpjsxxx.setUpdateTime(new Date());
                            camsZcsxGrpjsxxx.setUpdateBy(getWorkNo());
                            camsZcsxGrpjsxxx.setStatus(1);
                            if (StringUtils.isNotBlank(nsImportVO.getWgbh()))
                                camsZcsxGrpjsxxx.setQydm(nsImportVO.getWgbh());
                            if (StringUtils.isNotBlank(nsImportVO.getCpzl())){
                                camsZcsxGrpjsxxx.setCpzl(nsImportVO.getCpzl());
                                String bp = sysDictService.queryDictTextByKey("ly_xend_cpzlll", nsImportVO.getCpzl());
                                camsZcsxGrpjsxxx.setCpzlll(new BigDecimal(bp));
                            }

                            camsZcsxGrpjsxxxService.save(camsZcsxGrpjsxxx);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    service.updateCpzlll();
                    try {
                        String type = "1";
                        String sszh = null;
                        if (StringUtils.isNotBlank(nsImportVO.getSszh())) {

                            if ("6".equals(nsImportVO.getSszh()) || "2".equals(nsImportVO.getSszh())
                                    || "10".equals(nsImportVO.getSszh()) || "18".equals(nsImportVO.getSszh())) {
                                type = "2";
                            }

                            sszh = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "zzbz", nsImportVO.getSszh());
                        } else {
                            sszh = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "zzbz", getLoginUser().getOrgCode());
                        }
                        sysLoanInfoService.comDzdkzUpdate(nsImportVO.getKhmc() == null ? "" : nsImportVO.getKhmc(),
                                nsImportVO.getZjhm() == null ? "" : nsImportVO.getZjhm(), nsImportVO.getZzpddj() == null ? "" : nsImportVO.getZzpddj(),
                                nsImportVO.getZzsxed() == null ? new BigDecimal("0") : new BigDecimal(nsImportVO.getZzsxed()), type, getWorkNo(), "", sszh.replace("支行", ""));
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.info("===更新电子贷款证失败===");
                    }


                    try {
                        LambdaQueryWrapper<KhxxglHnkd> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                        lambdaQueryWrapper.eq(KhxxglHnkd::getZjhm,nsImportVO.getZjhm());
                        List<KhxxglHnkd> hnkds = khxxglHnkdService.list(lambdaQueryWrapper);

                        String fxjl = service.getWorkNoByRole("风险经理", nsImportVO.getSszh());
                        String zhhz = service.getWorkNoByRole("支行行长", nsImportVO.getSszh());

                        if (hnkds.isEmpty()){
                            KhxxglHnkd khxxglHnkd = new KhxxglHnkd();
                            khxxglHnkd.setKhmc(nsImportVO.getKhmc());
                            khxxglHnkd.setZjhm(nsImportVO.getZjhm());
                            khxxglHnkd.setSjhm(nsImportVO.getSjhm());
                            khxxglHnkd.setSszh(nsImportVO.getSszh());

                            String sskhjl = nsImportVO.getSskhjl();
                            khxxglHnkd.setKhjl(sskhjl);
                            khxxglHnkd.setDczrr1(sskhjl);
                            khxxglHnkd.setGlzrr(sskhjl);
                            khxxglHnkd.setDyzrr(sskhjl);
                            if (StringUtils.isNotBlank(fxjl)){
                                khxxglHnkd.setDczrr2(fxjl);
                                khxxglHnkd.setSczrr(fxjl);
                            }
                            if (StringUtils.isNotBlank(zhhz)){
                                khxxglHnkd.setZzspzrr(zhhz);
                            }
                            khxxglHnkd.setSxed(new BigDecimal(nsImportVO.getZzsxed()));
                            khxxglHnkdService.save(khxxglHnkd);
                        }else {
                            KhxxglHnkd khxxglHnkd = hnkds.get(0);
                            khxxglHnkd.setKhmc(nsImportVO.getKhmc());
                            khxxglHnkd.setSjhm(nsImportVO.getSjhm());
                            String sskhjl = nsImportVO.getSskhjl();
                            khxxglHnkd.setKhjl(sskhjl);
                            khxxglHnkd.setDczrr1(sskhjl);
                            khxxglHnkd.setGlzrr(sskhjl);
                            khxxglHnkd.setDyzrr(sskhjl);
                            if (StringUtils.isNotBlank(fxjl)){
                                khxxglHnkd.setDczrr2(fxjl);
                                khxxglHnkd.setSczrr(fxjl);
                            }
                            if (StringUtils.isNotBlank(zhhz)){
                                khxxglHnkd.setZzspzrr(zhhz);
                            }
                            khxxglHnkd.setSxed(new BigDecimal(nsImportVO.getZzsxed()));
                            khxxglHnkd.setSszh(nsImportVO.getSszh());
                            khxxglHnkdService.updateById(khxxglHnkd);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        log.info("===更新惠农快贷失败===");
                    }
                }


                obj.put("filePath", filePath);

                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！", obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }


}
