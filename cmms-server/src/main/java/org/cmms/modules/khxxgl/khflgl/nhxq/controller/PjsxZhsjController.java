package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.base.entity.SimpleStandardTable;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.bigscreen.entity.DpIndexZhxx;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.PjsxZhsj;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.VNhPjsx;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IPjsxZhsjService;

import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IVYxdyglMainService;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 评级授信支行数据
 * @Author: jeecg-boot
 * @Date: 2023-11-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "评级授信支行数据")
@RestController
@RequestMapping("/nhxq/pjsxZhsj")
public class PjsxZhsjController extends JeecgController<PjsxZhsj, IPjsxZhsjService> {
    @Autowired
    private IPjsxZhsjService pjsxZhsjService;
    @Autowired
    IYxdyglMainService yxdyglMainService;
    @Autowired
    private ISysDictService sysDictService;

    /**
     * 分页列表查询
     *
     * @param pjsxZhsj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "评级授信支行数据-分页列表查询")
    @ApiOperation(value = "评级授信支行数据-分页列表查询", notes = "评级授信支行数据-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(PjsxZhsj pjsxZhsj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {

        String sszh = null;
        if (StringUtils.isNotBlank(pjsxZhsj.getZzbz())) {
            sszh = pjsxZhsj.getZzbz();
            pjsxZhsj.setZzbz(null);
        }

        QueryWrapper<PjsxZhsj> queryWrapper = QueryGenerator.initQueryWrapper(pjsxZhsj, req.getParameterMap());

        if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {
            LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "vnhpjsx");
            List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
            boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
            if (b) {
                if (StringUtils.isNotBlank(sszh)) {
                    queryWrapper.eq("zzbz", sszh);
                }
            } else {
                queryWrapper.eq("zzbz", getLoginUser().getOrgCode());
            }
        }
        Page<PjsxZhsj> page = new Page<PjsxZhsj>(pageNo, pageSize);
        IPage<PjsxZhsj> pageList = pjsxZhsjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param pjsxZhsj
     * @return
     */
    @AutoLog(value = "评级授信支行数据-添加")
    @ApiOperation(value = "评级授信支行数据-添加", notes = "评级授信支行数据-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody PjsxZhsj pjsxZhsj) {
        String regex = "[\\u4e00-\\u9fa5]+";
        if (StringUtils.isBlank(pjsxZhsj.getKhmc())) {
            return Result.error("姓名不能为空");
        } else {
            if (!pjsxZhsj.getKhmc().matches(regex)) {
                return Result.error("姓名只能为中文");
            }
        }

        if (StringUtils.isNotBlank(pjsxZhsj.getZjhm())) {
            if (!IdcardUtil.isValidCard(pjsxZhsj.getZjhm())) {
                return Result.error("身份证号码格式错误");
            }
        }

        if (StringUtils.isNotBlank(pjsxZhsj.getHzzjhm())) {
            if (!IdcardUtil.isValidCard(pjsxZhsj.getHzzjhm())) {
                return Result.error("户主身份证号码格式错误");
            }
        }

        if (StringUtils.isNotBlank(pjsxZhsj.getSjhm())) {
            if (!Validator.isMobile(pjsxZhsj.getSjhm())) {
                return Result.error("手机号码格式错误");
            }
        }
        pjsxZhsj.setCreateTime(new Date());
        pjsxZhsj.setCreator(getWorkNo());
        pjsxZhsj.setZzbz(getLoginUser().getOrgCode());


        pjsxZhsjService.save(pjsxZhsj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param pjsxZhsj
     * @return
     */
    @AutoLog(value = "评级授信支行数据-编辑")
    @ApiOperation(value = "评级授信支行数据-编辑", notes = "评级授信支行数据-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody PjsxZhsj pjsxZhsj) {
        String regex = "[\\u4e00-\\u9fa5]+";
        if (StringUtils.isBlank(pjsxZhsj.getKhmc())) {
            return Result.error("姓名不能为空");
        } else {
            if (!pjsxZhsj.getKhmc().matches(regex)) {
                return Result.error("姓名只能为中文");
            }
        }

        if (StringUtils.isNotBlank(pjsxZhsj.getZjhm())) {
            if (!IdcardUtil.isValidCard(pjsxZhsj.getZjhm())) {
                return Result.error("身份证号码格式错误");
            }
        }

        if (StringUtils.isNotBlank(pjsxZhsj.getHzzjhm())) {
            if (!IdcardUtil.isValidCard(pjsxZhsj.getHzzjhm())) {
                return Result.error("户主身份证号码格式错误");
            }
        }

        if (StringUtils.isNotBlank(pjsxZhsj.getSjhm())) {
            if (!Validator.isMobile(pjsxZhsj.getSjhm())) {
                return Result.error("手机号码格式错误");
            }
        }
        pjsxZhsj.setUpdateTime(new Date());
        pjsxZhsj.setUpdator(getWorkNo());
        pjsxZhsj.setZzbz(getLoginUser().getOrgCode());
        pjsxZhsjService.updateById(pjsxZhsj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评级授信支行数据-通过id删除")
    @ApiOperation(value = "评级授信支行数据-通过id删除", notes = "评级授信支行数据-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        pjsxZhsjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "评级授信支行数据-批量删除")
    @ApiOperation(value = "评级授信支行数据-批量删除", notes = "评级授信支行数据-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.pjsxZhsjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评级授信支行数据-通过id查询")
    @ApiOperation(value = "评级授信支行数据-通过id查询", notes = "评级授信支行数据-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        PjsxZhsj pjsxZhsj = pjsxZhsjService.getById(id);
        return Result.ok(pjsxZhsj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param pjsxZhsj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PjsxZhsj pjsxZhsj) {
        return super.exportXls(request, pjsxZhsj, PjsxZhsj.class, "评级授信支行数据");
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
        return super.importExcel(request, response, PjsxZhsj.class);
    }


    @RequestMapping(value = "/importExcel2", method = RequestMethod.POST)
    public Result<?> importExcel2(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String filePaths = jsonObject.getString("filePaths");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }

        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            String absolutePath = file.getAbsolutePath();

            log.info("===当前导入文件{}===", absolutePath);
            if (!absolutePath.endsWith(".xlsx") && !absolutePath.endsWith(".xls")) {
                continue;
            }

            Workbook wb = null;
            int count = 0;
            boolean isExcel2003 = true;
            try {

                if (file.getName().matches("^.+\\.(?i)(xlsx)$")) {
                    isExcel2003 = false;
                }
                if (isExcel2003) {
                    wb = new HSSFWorkbook(new FileInputStream(file));
                } else {
                    wb = new XSSFWorkbook(new FileInputStream(file));
                }


            } catch (Exception e) {
                try {
                    if (isExcel2003) {
                        wb = new XSSFWorkbook(new FileInputStream(file));
                    } else {
                        wb = new HSSFWorkbook(new FileInputStream(file));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            try {
                Sheet sheet = wb.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();
                log.info("===当前文件有{}行数据===", lastRowNum);
                if (lastRowNum > 1) {
                    List<YxdyglMain> byQydm = yxdyglMainService.getByQydm(getRedisQydm());
                    List<YxdyglMain> zhenList = byQydm.stream().filter(item -> "1".equals(item.getWgxz())).collect(Collectors.toList());
                    List<YxdyglMain> cunList = byQydm.stream().filter(item -> "2".equals(item.getWgxz())).collect(Collectors.toList());


                    Map<String, String> zhenMap = zhenList.stream().collect(Collectors.toMap
                            (YxdyglMain::getWgmc, YxdyglMain::getWgbh, (value1, value2) -> value1));

                    Map<String, String> cunMap = cunList.stream().collect(Collectors.toMap
                            (YxdyglMain::getWgmc, YxdyglMain::getWgbh, (value1, value2) -> value1));

                    Map<String, String> cunPMap = cunList.stream().collect(Collectors.toMap
                            (YxdyglMain::getWgmc, YxdyglMain::getParentId, (value1, value2) -> value1));


                    for (int j = 1; j <= lastRowNum; j++) {

                        try {
                            String regex = "[\\u4e00-\\u9fa5]+";

                            Row row = sheet.getRow(j);
                            System.out.println(j + "============");
                            short lastCellNum = 10;
                            PjsxZhsj pjsxZhsj = null;

                            String zjhm = null;
                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                                zjhm = row.getCell(1).getStringCellValue().trim();

                                if (IdcardUtil.isValidCard(zjhm)) {

                                    LambdaQueryWrapper<PjsxZhsj> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                                    lambdaQueryWrapper.eq(PjsxZhsj::getZjhm, zjhm);
                                    PjsxZhsj one = service.getOne(lambdaQueryWrapper, false);
                                    if (one == null) {
                                        pjsxZhsj = new PjsxZhsj();
                                    } else {
                                        pjsxZhsj = one;
                                    }
                                    pjsxZhsj.setZjhm(zjhm);

                                } else {
                                    row.createCell(lastCellNum).setCellValue("身份证号码错误！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("身份证号码不能为空！");
                                continue;
                            }


                            if (row.getCell(0) != null) {
                                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                                String khmc = row.getCell(0).getStringCellValue().trim();
                                if (!khmc.matches(regex)) {
                                    row.createCell(lastCellNum).setCellValue("姓名只能为中文！");
                                    continue;
                                }

                                if (khmc.length() > 1 && khmc.length() < 6) {
                                    pjsxZhsj.setKhmc(khmc);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("姓名大于等于2个汉字，小于等于5个汉字！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("姓名不能为空！");
                                continue;
                            }


                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                                String sjhm = row.getCell(2).getStringCellValue().trim();

                                if (Validator.isMobile(sjhm)) {
                                    pjsxZhsj.setSjhm(sjhm);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("手机号码错误！");
                                    continue;
                                }
                            }

                            String zhen = null;
                            String cun = null;
                            String zu = null;
                            if (row.getCell(3) != null) {
                                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                                zhen = row.getCell(3).getStringCellValue().trim();
                            } else {
                                row.createCell(lastCellNum).setCellValue("乡镇不能为空！");
                                continue;
                            }
                            if (row.getCell(4) != null) {
                                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                cun = row.getCell(4).getStringCellValue().trim();
                            } else {
                                row.createCell(lastCellNum).setCellValue("村不能为空！");
                                continue;
                            }
                            if (row.getCell(5) != null) {
                                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                                zu = row.getCell(5).getStringCellValue().trim();
                            } else {
                                row.createCell(lastCellNum).setCellValue("组不能为空！");
                                continue;
                            }

                            String s = zhenMap.get(zhen);
                            if (StringUtils.isBlank(s)) {
                                row.createCell(lastCellNum).setCellValue("乡镇名称不对，请核对字典表！");
                                continue;
                            } else {
                                String s1 = cunMap.get(cun);
                                if (StringUtils.isBlank(s1)) {
                                    row.createCell(lastCellNum).setCellValue("村名称不对，请核对字典表！");
                                    continue;
                                } else {
                                    String s2 = cunPMap.get(cun);
                                    if (!s.equals(s2)) {
                                        row.createCell(lastCellNum).setCellValue("镇和村关系匹配错误，请核对字典表！");
                                        continue;
                                    }
                                }
                            }

                            pjsxZhsj.setZhen(zhen);
                            pjsxZhsj.setCun(cun);
                            pjsxZhsj.setZu(zu);

                            if (row.getCell(6) != null) {
                                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                                String hhbm = row.getCell(6).getStringCellValue().trim();
                                pjsxZhsj.setHhbm(hhbm);
                            }
                            if (row.getCell(7) != null) {
                                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                                String hzxm = row.getCell(7).getStringCellValue().trim();
                                if (hzxm.length() > 1 && hzxm.length() < 6) {
                                    pjsxZhsj.setHzxm(hzxm);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("户主姓名大于等于2个汉字，小于等于5个汉字！");
                                    continue;
                                }
                            }

                            if (row.getCell(8) != null) {
                                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                                String hzzjhm = row.getCell(8).getStringCellValue().trim();

                                if (IdcardUtil.isValidCard(hzzjhm)) {
                                    pjsxZhsj.setHzzjhm(hzzjhm);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("户主身份证号码错误！");
                                    continue;
                                }
                            }

                            List<DictModel> byCode = sysDictService.queryDictItemsByCode("yhzgx");
                            Map<String, String> byCodeMap = byCode.stream().collect(Collectors.toMap
                                    (DictModel::getText, DictModel::getValue, (value1, value2) -> value1));

                            if (row.getCell(9) != null) {
                                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                                String yhzgx = row.getCell(9).getStringCellValue().trim();
                                String s1 = byCodeMap.get(yhzgx);
                                if (StringUtils.isNotBlank(s1)) {
                                    pjsxZhsj.setYhzgx(s1);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("与户主关系错误，请核对字典！");
                                    continue;
                                }
                            }

                            if (StringUtils.isBlank(pjsxZhsj.getId())) {
                                pjsxZhsj.setCreateTime(new Date());
                                pjsxZhsj.setCreator(getWorkNo());
                                pjsxZhsj.setZzbz(getLoginUser().getOrgCode());
                                service.save(pjsxZhsj);
                            } else {
                                pjsxZhsj.setUpdateTime(new Date());
                                pjsxZhsj.setUpdator(getWorkNo());
                                pjsxZhsj.setZzbz(getLoginUser().getOrgCode());
                                service.updateById(pjsxZhsj);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    log.info("===表的行数过少，不导入数据===");
                }
                //wb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                String fileName = "支行数据导入结果" + System.currentTimeMillis() + ".xlsx";
                if (isExcel2003) {
                    fileName = "支行数据导入结果" + System.currentTimeMillis() + ".xls";
                }
                OutputStream out = new FileOutputStream(new File(uploadpath + File.separator + fileName));
                wb.write(out);
                wb.close();
                out.flush();
                out.close();
                return Result.ok("导入成功", fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return Result.ok();
    }


    @RequestMapping("/getByQydm")
    public Result<?> getByQydm(YxdyglMain yxdyglMain) {
        List<YxdyglMain> byQydm = yxdyglMainService.getByQydm(getRedisQydm(), yxdyglMain);
        return Result.ok(byQydm);
    }


    @Autowired
    ITjfxCsszService tjfxCsszService;

    @RequestMapping(value = "/exportNsb")
    public Result<?> exportNsb(HttpServletRequest request, PjsxZhsj pjsxZhsj, HttpServletResponse response) {
        String sszh = null;
        if (StringUtils.isNotBlank(pjsxZhsj.getZzbz())) {
            sszh = pjsxZhsj.getZzbz();
            pjsxZhsj.setZzbz(null);
        }

        QueryWrapper<PjsxZhsj> queryWrapper = QueryGenerator.initQueryWrapper(pjsxZhsj, request.getParameterMap());

        if (getRedisQydm().equals(QydmEnums.LIUYANG.getQydmCode())) {
            LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
            tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "vnhpjsx");
            List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
            boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
            if (b) {
                if (StringUtils.isNotBlank(sszh)) {
                    queryWrapper.eq("zzbz", sszh);
                }
            } else {
                queryWrapper.eq("zzbz", getLoginUser().getOrgCode());
            }
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
        List<PjsxZhsj> list = service.list(queryWrapper);
        List<String> rowList = null;
        rowList = Lists.newArrayList(
                "所属支行", "所属网格", "姓名", "身份证号码",
                "正确身份证号码", "户号编码", "与户主关系", "联系电话",
                "原信用等级", "原授信金额", "新初评等级", "新初评授信",
                "新复评等级", "新复评授信", "信用产品", "所属客户经理工号",
                "贷款金额", "存款金额", "存款日平", "表内不良", "表外不良", "年审分类", "年审分类原因", "信贷系统授信金额", "信贷系统冻结授信金额", "近三年存款日平", "逾期次数", "户籍地址", "信贷机构代码"
        );
        if (CollUtil.isNotEmpty(list)) {

            List<List<String>> nsbList = getNsbList(list);
            log.info("==={}条年审表开始生成===");
            log.info("===本次导出{}条年审表数据===", list.size());
            int couont = 65500;
            int n = list.size() / 65500;
            HSSFWorkbook workbook = new HSSFWorkbook();
            for (int i = 0; i < n + 1; i++) {
                HSSFSheet sheet = workbook.createSheet("年审表" + i);
                SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook, sheet);
                simpleStandardTable.setTableName("浏阳农商行年审表", rowList.size());
                simpleStandardTable.setTableHeader(rowList);
                if (i == n) {
                    simpleStandardTable.setTableData(nsbList.subList(couont * i, nsbList.size()));
                } else {
                    simpleStandardTable.setTableData(nsbList.subList(couont * i, (i + 1) * couont));
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
                    if (dictModel != null && StringUtils.isNotBlank(dictModel.getText())) {
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

    public List<List<String>> getNsbList(List<PjsxZhsj> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            PjsxZhsj pjsxZhsj = list.get(i);
            List<String> colList = Lists.newArrayList();

            if (StringUtils.isNotBlank(pjsxZhsj.getZzbz())) {
                String s = sysDictService.queryTableDictTextByKey("hr_bas_organization", "ZZJC", "ZZBZ", pjsxZhsj.getZzbz());
                if (StringUtils.isNotBlank(s)) {
                    colList.add(s);
                } else {
                    colList.add("");
                }
            } else {
                colList.add("");
            }

            String wg = "";

            if (StringUtils.isNotBlank(pjsxZhsj.getZhen())) {
                wg += pjsxZhsj.getZhen();
            }
            if (StringUtils.isNotBlank(pjsxZhsj.getCun())) {
                wg += pjsxZhsj.getCun();
            }
            if (StringUtils.isNotBlank(pjsxZhsj.getZu())) {
                wg += pjsxZhsj.getZu();
            }
            colList.add(wg);


            if (StringUtils.isNotBlank(pjsxZhsj.getKhmc())) {
                colList.add(pjsxZhsj.getKhmc());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(pjsxZhsj.getZjhm())) {
                colList.add(pjsxZhsj.getZjhm());
            } else {
                colList.add("");
            }
            colList.add("");
            if (StringUtils.isNotBlank(pjsxZhsj.getHhbm())) {
                colList.add(pjsxZhsj.getHhbm());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(pjsxZhsj.getYhzgx())) {
                String s = sysDictService.queryDictTextByKey("yhzgx", pjsxZhsj.getYhzgx());
                if (StringUtils.isNotBlank(s)) {
                    colList.add(s);
                } else {
                    colList.add("");
                }
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(pjsxZhsj.getSjhm())) {
                colList.add(pjsxZhsj.getSjhm());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(pjsxZhsj.getYxydj())) {
                colList.add(pjsxZhsj.getYxydj());
            } else {
                colList.add("");
            }
            if (pjsxZhsj.getYsxje() != null) {
                colList.add(pjsxZhsj.getYsxje().toString());
            } else {
                colList.add("");
            }

            colList.add("");
            colList.add("");
            colList.add("");
            colList.add("");
            colList.add("");

            if (StringUtils.isNotBlank(pjsxZhsj.getSskhjl())) {
                colList.add(pjsxZhsj.getSskhjl());
            } else {
                colList.add("");
            }

            if (pjsxZhsj.getDkje() != null) {
                colList.add(pjsxZhsj.getDkje().toString());
            } else {
                colList.add("");
            }

            if (pjsxZhsj.getCkje() != null) {
                colList.add(pjsxZhsj.getCkje().toString());
            } else {
                colList.add("");
            }

            if (pjsxZhsj.getCkrp() != null) {
                colList.add(pjsxZhsj.getCkrp().toString());
            } else {
                colList.add("");
            }
            if (pjsxZhsj.getBlbl() != null) {
                colList.add(pjsxZhsj.getBlbl().toString());
            } else {
                colList.add("");
            }
            if (pjsxZhsj.getBwbl() != null) {
                colList.add(pjsxZhsj.getBwbl().toString());
            } else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(pjsxZhsj.getNsfl())) {
                String s = sysDictService.queryDictTextByKey("ly_xend_nsfl", pjsxZhsj.getNsfl());
                if (StringUtils.isNotBlank(s)) {
                    colList.add(s);
                } else {
                    colList.add("");
                }
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(pjsxZhsj.getNsflyy())) {
                colList.add(pjsxZhsj.getNsflyy());
            } else {
                colList.add("");
            }

            if (pjsxZhsj.getXdje() != null) {
                colList.add(pjsxZhsj.getXdje().toString());
            } else {
                colList.add("");
            }
            if (pjsxZhsj.getXddjje() != null) {
                colList.add(pjsxZhsj.getXddjje().toString());
            } else {
                colList.add("");
            }
            if (pjsxZhsj.getSanckrp() != null) {
                colList.add(pjsxZhsj.getSanckrp().toString());
            } else {
                colList.add("");
            }
            if (pjsxZhsj.getYqcs() != null) {
                colList.add(pjsxZhsj.getYqcs().toString());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(pjsxZhsj.getHjdz())) {
                colList.add(pjsxZhsj.getHjdz());
            } else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(pjsxZhsj.getXdjgdm())) {
                String s = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "ywjgdm", pjsxZhsj.getXdjgdm());
                if (StringUtils.isNotBlank(s)) {
                    colList.add(s);
                } else {
                    colList.add("");
                }
            } else {
                colList.add("");
            }

            listArrayList.add(colList);
        }
        return listArrayList;
    }


    @RequestMapping("gxsj")
    public Result<?> gxsj() {
        LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "vnhpjsx");
        List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
        boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
        if (b) {
            service.gxsj(null);
        } else {
            service.gxsj(getLoginUser().getOrgCode());
        }
        return Result.ok("更新成功");
    }

    @Autowired
    IHrBasOrganizationService hrBasOrganizationService;

    @RequestMapping(value = "/importExcel3", method = RequestMethod.POST)
    public Result<?> importExcel3(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String filePaths = jsonObject.getString("filePaths");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }

        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            String absolutePath = file.getAbsolutePath();

            log.info("===当前导入文件{}===", absolutePath);
            if (!absolutePath.endsWith(".xlsx") && !absolutePath.endsWith(".xls")) {
                continue;
            }

            Workbook wb = null;
            int count = 0;
            boolean isExcel2003 = true;
            try {

                if (file.getName().matches("^.+\\.(?i)(xlsx)$")) {
                    isExcel2003 = false;
                }
                if (isExcel2003) {
                    wb = new HSSFWorkbook(new FileInputStream(file));
                } else {
                    wb = new XSSFWorkbook(new FileInputStream(file));
                }


            } catch (Exception e) {
                try {
                    if (isExcel2003) {
                        wb = new XSSFWorkbook(new FileInputStream(file));
                    } else {
                        wb = new HSSFWorkbook(new FileInputStream(file));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            try {
                Sheet sheet = wb.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();
                log.info("===当前文件有{}行数据===", lastRowNum);
                if (lastRowNum > 1) {
                    List<HrBasOrganization> list = hrBasOrganizationService.list();

                    Map<String, String> orgMap = list.stream().collect(Collectors.toMap
                            (HrBasOrganization::getZzjc, HrBasOrganization::getZzbz, (value1, value2) -> value1));
                    List<String> dj = new ArrayList<>();
                    dj.add("A");
                    dj.add("B");
                    dj.add("C");
                    dj.add("D");
                    dj.add("E");

                    for (int j = 2; j <= lastRowNum; j++) {

                        try {
                            String regex = "[\\u4e00-\\u9fa5]+";

                            Row row = sheet.getRow(j);
                            System.out.println(j + "============");
                            short lastCellNum = 16;
                            PjsxZhsj pjsxZhsj = null;

                            String zjhm = null;
                            if (row.getCell(3) != null) {
                                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                                zjhm = row.getCell(3).getStringCellValue().trim();
                                if (StringUtils.isNotBlank(zjhm)) {
                                    if (IdcardUtil.isValidCard(zjhm)) {
                                        LambdaQueryWrapper<PjsxZhsj> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                                        lambdaQueryWrapper.eq(PjsxZhsj::getZjhm, zjhm);
                                        PjsxZhsj one = service.getOne(lambdaQueryWrapper, false);
                                        if (one == null) {
                                            pjsxZhsj = new PjsxZhsj();
                                        } else {
                                            pjsxZhsj = one;
                                        }
                                        pjsxZhsj.setZjhm(zjhm);
                                    } else {
                                        row.createCell(lastCellNum).setCellValue("身份证号码错误！");
                                        continue;
                                    }
                                } else {
                                    row.createCell(lastCellNum).setCellValue("身份证号码不能为空！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("身份证号码不能为空！");
                                continue;
                            }


                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                                String khmc = row.getCell(2).getStringCellValue().trim();
                                if (StringUtils.isNotBlank(khmc)) {
                                    if (!khmc.matches(regex)) {
                                        row.createCell(lastCellNum).setCellValue("姓名只能为中文！");
                                        continue;
                                    }

                                    if (khmc.length() > 1 && khmc.length() < 6) {
                                        pjsxZhsj.setKhmc(khmc);
                                    } else {
                                        row.createCell(lastCellNum).setCellValue("姓名大于等于2个汉字，小于等于5个汉字！");
                                        continue;
                                    }
                                } else {
                                    row.createCell(lastCellNum).setCellValue("姓名不能为空！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("姓名不能为空！");
                                continue;
                            }

                            if (row.getCell(4) != null) {
                                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                String xzjhm = row.getCell(4).getStringCellValue().trim();
                                if (StringUtils.isNotBlank(xzjhm)) {
                                    if (IdcardUtil.isValidCard(xzjhm)) {
                                        pjsxZhsj.setZjhm(xzjhm);
                                    } else {
                                        row.createCell(lastCellNum).setCellValue("正确身份证号码错误！");
                                        continue;
                                    }
                                }
                            }

                            if (row.getCell(5) != null) {
                                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                                String hhbm = row.getCell(5).getStringCellValue().trim();
                                if (StringUtils.isNotBlank(hhbm)) {
                                    pjsxZhsj.setHhbm(hhbm);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("户号编码不能为空，可以填户主证件号码！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("户号编码不能为空，可以填户主证件号码！");
                                continue;
                            }

                            if (row.getCell(6) != null) {
                                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                                String yhzgx = row.getCell(6).getStringCellValue().trim();
                                String s = sysDictService.queryDictValueByKey("yhzgx", yhzgx);
                                if (StringUtils.isNotBlank(s)) {
                                    pjsxZhsj.setYhzgx(s);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("与户主关系错误！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("与户主关系不能为空！");
                                continue;
                            }


                            if (row.getCell(7) != null) {
                                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                                String sjhm = row.getCell(7).getStringCellValue().trim();
                                if (StringUtils.isNotBlank(sjhm)) {

                                    if (Validator.isMobile(sjhm)) {
                                        pjsxZhsj.setSjhm(sjhm);
                                    } else {
                                        row.createCell(lastCellNum).setCellValue("手机号码错误！");
                                        continue;
                                    }
                                } else {
                                    row.createCell(lastCellNum).setCellValue("手机号码不能为空！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("手机号码不能为空！");
                                continue;
                            }

                            if (row.getCell(0) != null) {
                                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                                String sszh = row.getCell(0).getStringCellValue().trim();
                                String s = orgMap.get(sszh);
                                if (StringUtils.isNotBlank(s)) {
                                    pjsxZhsj.setSszh(s);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("所属支行组织简称错误！");
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("所属支行不能为空！");
                                continue;
                            }

                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                                String sswg = row.getCell(1).getStringCellValue().trim();
                                if (StringUtils.isNotBlank(sswg)) {
                                    pjsxZhsj.setSswg(sswg);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("所属网格不能为空！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("所属网格不能为空！");
                                continue;
                            }

                            if (row.getCell(10) != null) {
                                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                                String xcpdj = row.getCell(10).getStringCellValue().trim();
                                if (dj.contains(xcpdj)) {
                                    pjsxZhsj.setXcpdj(xcpdj);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("新初评等级只能为 ABCDE 中的一个等级！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("新初评等级不能为空！");
                                continue;
                            }

                            if (row.getCell(11) != null) {
                                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                                String xcpsx = row.getCell(11).getStringCellValue().trim();
                                if (StringUtils.isNotBlank(xcpsx)) {
                                    if (StringUtils.isNumeric(xcpsx)) {
                                        pjsxZhsj.setXcpsx(new BigDecimal(xcpsx));
                                    } else {
                                        row.createCell(lastCellNum).setCellValue("新初评授信只能为0-50的数字！");
                                        continue;
                                    }
                                }

                            }
                            String xfpdj = null;
                            if (row.getCell(12) != null) {
                                row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                                xfpdj = row.getCell(12).getStringCellValue().trim();
                                if (dj.contains(xfpdj)) {
                                    pjsxZhsj.setXcpdj(xfpdj);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("新复评等级只能为 ABCDE 中的一个等级！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("新复评等级不能为空！");
                                continue;
                            }
                            String xfpsx = null;
                            if (row.getCell(13) != null) {
                                row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                                xfpsx = row.getCell(13).getStringCellValue().trim();
                                if (StringUtils.isNumeric(xfpsx)) {
                                    pjsxZhsj.setXcpsx(new BigDecimal(xfpsx));
                                } else {
                                    row.createCell(lastCellNum).setCellValue("新复评授信只能为0-50的数字！");
                                    continue;
                                }

                            } else {
                                row.createCell(lastCellNum).setCellValue("新复评授信不能为空！");
                                continue;
                            }
                            String xycpval = null;
                            if (row.getCell(14) != null) {
                                row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                                String xycp = row.getCell(14).getStringCellValue().trim();

                                String s = sysDictService.queryDictValueByKey("ly_xend_cpzl", xycp);
                                if (StringUtils.isNotBlank(s)) {
                                    xycpval = s;
                                    pjsxZhsj.setXycp(s);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("信用产品字典错误！");
                                    continue;
                                }

                            } else {
                                row.createCell(lastCellNum).setCellValue("信用产品不能为空！");
                                continue;
                            }
                            BigDecimal zzsxed = new BigDecimal(xfpsx);

                            if ("A".equals(xfpdj)) {
                                if (xycpval.equals("6") ||
                                        xycpval.equals("13")
                                        || xycpval.equals("14")) {
                                    if (zzsxed.compareTo(new BigDecimal("50")) > 0) {
                                        row.createCell(lastCellNum).setCellValue("新复评授信不能超过50万！");
                                        continue;
                                    }
                                } else if (xycpval.equals("7")) {
                                    if (zzsxed.compareTo(new BigDecimal("40")) > 0) {
                                        row.createCell(lastCellNum).setCellValue("新复评授信不能超过40万！");
                                        continue;
                                    }
                                } else {
                                    if (zzsxed.compareTo(new BigDecimal("30")) > 0) {
                                        row.createCell(lastCellNum).setCellValue("A级不能超过30万！");
                                        continue;
                                    }
                                    if (zzsxed.compareTo(new BigDecimal("20")) < 0) {
                                        row.createCell(lastCellNum).setCellValue("A级不能小于20万！");
                                        continue;
                                    }
                                }
                            }
                            if ("B".equals(xfpdj) && zzsxed.compareTo(new BigDecimal("20")) > 0) {
                                row.createCell(lastCellNum).setCellValue("B级不能超过20万！");
                                continue;
                            }
                            if ("B".equals(xfpdj) && zzsxed.compareTo(new BigDecimal("10")) < 0) {
                                row.createCell(lastCellNum).setCellValue("B级不能小于10万！");
                                continue;
                            }
                            if ("C".equals(xfpdj) && zzsxed.compareTo(new BigDecimal("10")) > 0) {
                                row.createCell(lastCellNum).setCellValue("C级不能超过10万！");
                                continue;
                            }
                            if ("C".equals(xfpdj) && zzsxed.compareTo(new BigDecimal("5")) < 0) {
                                row.createCell(lastCellNum).setCellValue("C级不能小于5万！");
                                continue;
                            }
                            if ("D".equals(xfpdj) && zzsxed.compareTo(new BigDecimal("5")) > 0) {
                                row.createCell(lastCellNum).setCellValue("D级不能超过5万！");
                                continue;
                            }
                            if ("D".equals(xfpdj) && zzsxed.compareTo(new BigDecimal("0")) < 0) {
                                row.createCell(lastCellNum).setCellValue("D级大于0万！");
                                continue;
                            }
                            if ("E".equals(xfpdj)) {
                                pjsxZhsj.setXfpsx(new BigDecimal("0"));
                            }

                            String khjl = null;
                            if (row.getCell(15) != null) {
                                row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                                khjl = row.getCell(15).getStringCellValue().trim();
                                if (StringUtils.isNotBlank(khjl)) {
                                    String s = sysDictService.queryTableDictTextByKey("Hr_bas_staff", "ygxm", "yggh", khjl);
                                    if (StringUtils.isNotBlank(s)) {
                                        pjsxZhsj.setSskhjl(khjl);
                                    }else {
                                        row.createCell(lastCellNum).setCellValue("所属客户经理工号错误！");
                                        continue;
                                    }
                                }
                            }
                            if (StringUtils.isBlank(khjl)) {
                                pjsxZhsj.setSskhjl(getWorkNo());
                            }

                            if (StringUtils.isBlank(pjsxZhsj.getId())) {
                                pjsxZhsj.setCreateTime(new Date());
                                pjsxZhsj.setCreator(getWorkNo());
                                pjsxZhsj.setZzbz(getLoginUser().getOrgCode());
                                service.save(pjsxZhsj);
                            } else {
                                pjsxZhsj.setUpdateTime(new Date());
                                pjsxZhsj.setUpdator(getWorkNo());
                                pjsxZhsj.setZzbz(getLoginUser().getOrgCode());
                                service.updateById(pjsxZhsj);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    log.info("===表的行数过少，不导入数据===");
                }
                //wb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                String fileName = "支行数据导入结果" + System.currentTimeMillis() + ".xlsx";
                if (isExcel2003) {
                    fileName = "支行数据导入结果" + System.currentTimeMillis() + ".xls";
                }
                OutputStream out = new FileOutputStream(new File(uploadpath + File.separator + fileName));
                wb.write(out);
                wb.close();
                out.flush();
                out.close();
                return Result.ok("导入成功", fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return Result.ok();
    }


}
