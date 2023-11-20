package org.cmms.modules.xdgl.nsb.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.C;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.base.entity.NsbErrorTable;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DictTextToValusUtil;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dzdkz.service.SysLoanInfoService;
import org.cmms.modules.khxxgl.khflgl.nhxq.verify.CamsNhpjsxNsbImportVerify;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.xdgl.nsb.entity.CamsNhpjsxNsb;
import org.cmms.modules.xdgl.nsb.entity.CommonImpFileLog;
import org.cmms.modules.xdgl.nsb.service.ICamsNhpjsxNsbService;
import org.cmms.modules.xdgl.nsb.service.ICommonImpFileLogService;
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
 * @Description: 浏阳农户评级授信农户年审表
 * @Author: jeecg-boot
 * @Date: 2022-10-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "浏阳农户评级授信农户年审表")
@RestController
@RequestMapping("/nsb/camsNhpjsxNsb")
public class CamsNhpjsxNsbController extends JeecgController<CamsNhpjsxNsb, ICamsNhpjsxNsbService> {
    @Autowired
    private ICamsNhpjsxNsbService camsNhpjsxNsbService;
    @Autowired
    IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    ICommonImpFileLogService commonImpFileLogService;
    @Autowired
    ISysDictService dictService;
    @Autowired
    CamsNhpjsxNsbImportVerify camsNhpjsxNsbImportVerify;
    @Autowired
    ISysDictService sysDictService;

    @Autowired
    SysLoanInfoService sysLoanInfoService;

    /**
     * 分页列表查询
     *
     * @param camsNhpjsxNsb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "浏阳农户评级授信农户年审表-分页列表查询")
    @ApiOperation(value = "浏阳农户评级授信农户年审表-分页列表查询", notes = "浏阳农户评级授信农户年审表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CamsNhpjsxNsb camsNhpjsxNsb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CamsNhpjsxNsb> queryWrapper = QueryGenerator.initQueryWrapper(camsNhpjsxNsb, req.getParameterMap());
        Page<CamsNhpjsxNsb> page = new Page<CamsNhpjsxNsb>(pageNo, pageSize);
        IPage<CamsNhpjsxNsb> pageList = camsNhpjsxNsbService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param camsNhpjsxNsb
     * @return
     */
    @AutoLog(value = "浏阳农户评级授信农户年审表-添加")
    @ApiOperation(value = "浏阳农户评级授信农户年审表-添加", notes = "浏阳农户评级授信农户年审表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CamsNhpjsxNsb camsNhpjsxNsb) {
        camsNhpjsxNsbService.save(camsNhpjsxNsb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param camsNhpjsxNsb
     * @return
     */
    @AutoLog(value = "浏阳农户评级授信农户年审表-编辑")
    @ApiOperation(value = "浏阳农户评级授信农户年审表-编辑", notes = "浏阳农户评级授信农户年审表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CamsNhpjsxNsb camsNhpjsxNsb) {
        camsNhpjsxNsbService.updateById(camsNhpjsxNsb);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "浏阳农户评级授信农户年审表-通过id删除")
    @ApiOperation(value = "浏阳农户评级授信农户年审表-通过id删除", notes = "浏阳农户评级授信农户年审表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        camsNhpjsxNsbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "浏阳农户评级授信农户年审表-批量删除")
    @ApiOperation(value = "浏阳农户评级授信农户年审表-批量删除", notes = "浏阳农户评级授信农户年审表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.camsNhpjsxNsbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "浏阳农户评级授信农户年审表-通过id查询")
    @ApiOperation(value = "浏阳农户评级授信农户年审表-通过id查询", notes = "浏阳农户评级授信农户年审表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CamsNhpjsxNsb camsNhpjsxNsb = camsNhpjsxNsbService.getById(id);
        return Result.ok(camsNhpjsxNsb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param camsNhpjsxNsb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CamsNhpjsxNsb camsNhpjsxNsb) {
        return super.exportXls(request, camsNhpjsxNsb, CamsNhpjsxNsb.class, "浏阳农户评级授信农户年审表");
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
        return super.importExcel(request, response, CamsNhpjsxNsb.class);
    }

    @RequestMapping(value = "/importExcel2", method = RequestMethod.POST)
    public Result<?> importExcel2(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        int count = 0;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            String absolutePath = file.getOriginalFilename();
            log.info("===当前导入文件{}===", absolutePath);
            if (!absolutePath.endsWith(".xlsx") && !absolutePath.endsWith(".xls")) {
                continue;
            }
            String path = uploadpath + File.separator + "del" + File.separator + file.getOriginalFilename();
            if (!FileUtil.exist(path))
                FileUtil.touch(path);
            try {
                file.transferTo(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (FileUtil.isFile(path)) {
                File tempFile = new File(path);
                Workbook wb = null;
                int rd = 0;
                boolean isExcel2003 = true;
                try {

                    if (file.getName().matches("^.+\\.(?i)(xlsx)$")) {
                        isExcel2003 = false;
                    }
                    if (isExcel2003) {
                        wb = new HSSFWorkbook(new FileInputStream(tempFile));
                    } else {
                        wb = new XSSFWorkbook(new FileInputStream(tempFile));
                    }


                } catch (Exception e) {
                    try {
                        if (isExcel2003) {
                            wb = new XSSFWorkbook(new FileInputStream(tempFile));
                        } else {
                            wb = new HSSFWorkbook(new FileInputStream(tempFile));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                try {
                    Sheet sheet = wb.getSheetAt(0);
                    int lastRowNum = sheet.getLastRowNum();
                    log.info("===当前文件有{}行数据===", lastRowNum);
                    if (lastRowNum >= 3) {

                        String hhbm = null;
                        String sskhjl = null;

                        for (int j = 2; j < lastRowNum; j++) {
                            try {
                                Row row = sheet.getRow(j);
                                String type = "1";
                                String zzjc = "1";
                                CamsNhpjsxNsb camsNhpjsxNsb = new CamsNhpjsxNsb();
                                StringBuffer stringBuffer = new StringBuffer();
                                if (row.getCell(0) != null) {
                                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                                    String sszh = row.getCell(0).getStringCellValue().trim();
                                    //去找所属支行
                                    if (sszh.contains("淮川") || sszh.contains("营业部")
                                            || sszh.contains("城关") || sszh.contains("百园")) ;
                                    type = "2";
                                    String s = sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzbz", "zzjc", sszh);
                                    if (StringUtils.isNotBlank(s)){
                                        zzjc = sszh;
                                        camsNhpjsxNsb.setSszh(s);
                                    }else {
                                        stringBuffer.append("所属支行匹配失败。");
                                    }
                                }
                                if (row.getCell(1) != null) {
                                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                                    String wgmc = row.getCell(1).getStringCellValue().trim();
                                    camsNhpjsxNsb.setDrwgmc(wgmc);
                                    //去找所属支行
                                    String s = sysDictService.queryTableDictTextByKey("V_YXDYGL_MAIN", "wgbh", "wgmc_show", wgmc);
                                    if (StringUtils.isNotBlank(s)){
                                        camsNhpjsxNsb.setWgbh(s);
                                    }else {
                                        stringBuffer.append("所属网格匹配失败。");
                                    }
                                }


                                if (row.getCell(2) != null) {
                                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                                    String wgmc = row.getCell(2).getStringCellValue().trim();
                                    camsNhpjsxNsb.setKhmc(wgmc);
                                }

                                if (row.getCell(3) != null) {
                                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                                    String zjhm = row.getCell(3).getStringCellValue().trim();
                                    camsNhpjsxNsb.setZjhm(zjhm);
                                    LambdaQueryWrapper<CamsNhpjsxNsb> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                                    lambdaQueryWrapper.eq(CamsNhpjsxNsb::getZjhm,zjhm);
                                    service.remove(lambdaQueryWrapper);
                                    if (IdcardUtil.isValidCard(zjhm)) {

                                    } else {
                                        stringBuffer.append("身份证号码格式错误。");
                                    }
                                }else {
                                    stringBuffer.append("身份证号码不能为空。");
                                }

                                if (row.getCell(4) != null) {
                                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                    String yhzgx = row.getCell(4).getStringCellValue().trim();
                                    if (yhzgx.contains("户主"))
                                    {
                                        hhbm = IdUtil.fastSimpleUUID();
                                        camsNhpjsxNsb.setHhbm(hhbm);
                                    }
                                    //去找所属支行
                                    String s = sysDictService.queryDictValueByKey("yhzgx",yhzgx);
                                    System.out.println(s);
                                    if (StringUtils.isNotBlank(s)){
                                        camsNhpjsxNsb.setYhzgx(s);
                                    }else {
                                        stringBuffer.append("与户主关系匹配失败。");
                                    }
                                }

                                if (row.getCell(5) != null) {
                                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                                    String sjhm = row.getCell(5).getStringCellValue().trim();
                                    //去找所属支行
                                    camsNhpjsxNsb.setSjhm(sjhm);
                                }

                                if (row.getCell(8) != null) {
                                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                                    String cpdj = row.getCell(8).getStringCellValue().trim();
                                    //去找所属支行
                                    camsNhpjsxNsb.setCpdj(cpdj);
                                }
                                if (row.getCell(9) != null) {
                                    row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                                    String cpje = row.getCell(9).getStringCellValue().trim();

                                    if (NumberUtil.isNumber(cpje)) {
                                        camsNhpjsxNsb.setCpje(new BigDecimal(cpje));
                                    } else {
                                        camsNhpjsxNsb.setCpje(BigDecimal.ZERO);
                                    }
                                }

                                if (row.getCell(10) != null) {
                                    row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                                    String cpdj = row.getCell(10).getStringCellValue().trim();
                                    //去找所属支行
                                    camsNhpjsxNsb.setFpdj(cpdj);
                                }
                                if (row.getCell(11) != null) {
                                    row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                                    String cpje = row.getCell(11).getStringCellValue().trim();

                                    if (NumberUtil.isNumber(cpje)) {
                                        camsNhpjsxNsb.setFpje(new BigDecimal(cpje));
                                    } else {
                                        camsNhpjsxNsb.setFpje(BigDecimal.ZERO);
                                    }
                                }

                                camsNhpjsxNsb.setBz(stringBuffer.toString());
                                camsNhpjsxNsb.setCreateTime(new Date());
                                camsNhpjsxNsb.setCreator(getWorkNo());
                                String yyyy = DateUtil.format(new Date(), "yyyy");
                                camsNhpjsxNsb.setSynf(yyyy);

                                try {
                                    sysLoanInfoService.comDzdkzUpdate(camsNhpjsxNsb.getKhmc()==null?"":camsNhpjsxNsb.getKhmc(),
                                            camsNhpjsxNsb.getZjhm()==null?"":camsNhpjsxNsb.getZjhm(),camsNhpjsxNsb.getFpdj()==null?"":camsNhpjsxNsb.getFpdj(),
                                            camsNhpjsxNsb.getFpje()==null?new BigDecimal("0"):camsNhpjsxNsb.getFpje(),type,getWorkNo(),"",zzjc);
                                    boolean save = service.save(camsNhpjsxNsb);
                                }catch (Exception e){
                                    log.info("===更新电子贷款证失败===");
                                }

                                boolean save = service.save(camsNhpjsxNsb);
                                if (save)
                                    count++;

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        log.info("===表的行数过少，不导入数据===");
                    }
                    wb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }

        }
        return Result.ok("文件导入成功！数据行数：" + count);
    }


    @RequestMapping(value = "/reset")
    public Result<?> reset() {
        String path = uploadpath + File.separator + "drnsb";
        List<File> files = FileUtil.loopFiles(path);
        if (CollUtil.isNotEmpty(files)) {
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                String name = file.getName();
                if (name.endsWith(".et")) {
                    log.info("==={}文件所在路径{}===", name, file.getAbsolutePath());
                    String replace = name.replace(".et", ".xlsx");
                    log.info("=== 重命名文件{} ===", replace);
                    FileUtil.rename(file, replace, true);
                }
            }
        }
        return Result.ok();
    }


    @RequestMapping(value = "/drnsb")
    public Result<?> drnsb() {
        String path = uploadpath + File.separator + "drnsb";
        List<File> files = FileUtil.loopFiles(path);
        if (CollUtil.isNotEmpty(files)) {
            log.info("===当前导入{}个文件===", files.size());
            LambdaQueryWrapper<HrBasOrganization> hrBasOrganizationLambdaQueryWrapper = new LambdaQueryWrapper<>();
            hrBasOrganizationLambdaQueryWrapper.isNotNull(HrBasOrganization::getZzjc);
            List<HrBasOrganization> hrBasOrganizations = hrBasOrganizationService.list(hrBasOrganizationLambdaQueryWrapper);

            for (int i = 0; i < files.size(); i++) {
                CommonImpFileLog commonImpFileLog = new CommonImpFileLog();
                File file = files.get(i);
                String absolutePath = file.getAbsolutePath();
                //先查这个文件  如果能存在则不导入
                LambdaQueryWrapper<CommonImpFileLog> commonImpFileLogLambdaQueryWrapper = new LambdaQueryWrapper<>();
                commonImpFileLogLambdaQueryWrapper.eq(CommonImpFileLog::getFileAddr, absolutePath);
                List<CommonImpFileLog> list = commonImpFileLogService.list(commonImpFileLogLambdaQueryWrapper);
                if (CollUtil.isNotEmpty(list)) {
                    log.info("==={}文件已经导入===", absolutePath);
                    continue;
                }

                commonImpFileLog.setFileAddr(absolutePath);
                commonImpFileLog.setFileName(file.getName());

                log.info("===当前导入文件{}===", absolutePath);
                if (!absolutePath.endsWith(".xlsx") && !absolutePath.endsWith(".xls")) {
                    continue;
                }
                //根据文件夹的名称匹配他的所属支行
                String sszh = null;
                if (CollUtil.isNotEmpty(hrBasOrganizations)) {
                    String replace = absolutePath.replace(uploadpath, "");
                    String substring = replace.substring(6, replace.indexOf("支行"));
                    for (int j = 0; j < hrBasOrganizations.size(); j++) {
                        String zzjc = hrBasOrganizations.get(j).getZzjc().replace("分理处", "");
                        zzjc = zzjc.replace("支行", "");
                        String zzbz = hrBasOrganizations.get(j).getZzbz();
                        //获取支行名称
                        if (substring.contains(zzjc)) {
                            sszh = zzbz;
                            break;
                        }
                    }
                }

                Workbook wb = null;
                int count = 0;
                int rd = 0;
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
                    commonImpFileLog.setFileSize(lastRowNum);
                    log.info("===当前文件有{}行数据,包含表头===", lastRowNum);
                    if (lastRowNum >= 3) {
                        /**
                         *  第一行是年审表表头
                         *  第二行是镇村组 也有的不是 或者 下面表头覆盖
                         * */

                        String zcz = null;
                        Row row = sheet.getRow(1);
                        if (row.getCell(1) != null) {
                            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                            zcz = row.getCell(1).getStringCellValue().trim();
                        }

                        Row row1 = sheet.getRow(2);
                        Map<String, Integer> map = new HashMap<>();
                        short lastCellNum = row1.getLastCellNum();
                        for (int j = 0; j < lastCellNum; j++) {
                            if (row1.getCell(j) != null) {
                                row1.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                                String biaotou = row1.getCell(j).getStringCellValue().trim();
                                map.put(biaotou, j);
                            }
                        }
                        String hhbm = null;
                        String sskhjl = null;

                        for (int j = 3; j < 50000; j++) {
                            try {

                                rd = j;
                                Row row2 = sheet.getRow(j);
                                if (row2 == null)
                                    break;

                                String khmc = null;
                                String zjhm = null;
                                String yhzgx = null;
                                String sjhm = null;
                                String cpdj = null;
                                String cpsx = null;
                                String fpdj = null;
                                String fpsx = null;
                                String cplx = null;
                                String czinfo = null;

                                Integer khmcxh = map.get("姓名");
                                if (khmcxh != null) {
                                    if (row2.getCell(khmcxh) != null) {
                                        row2.getCell(khmcxh).setCellType(Cell.CELL_TYPE_STRING);
                                        khmc = row2.getCell(khmcxh).getStringCellValue().trim();
                                        if (khmc.contains("初评小组签字"))
                                            break;
                                    }
                                }

                                Integer xhzjhm = map.get("身份证号码");
                                if (xhzjhm != null) {
                                    if (row2.getCell(xhzjhm) != null) {
                                        row2.getCell(xhzjhm).setCellType(Cell.CELL_TYPE_STRING);
                                        zjhm = row2.getCell(xhzjhm).getStringCellValue().trim();
                                        if (IdcardUtil.isValidCard(zjhm)) {
                                            //重复的进行更新

                                        } else {
                                            continue;
                                        }
                                    }
                                } else {
                                    continue;
                                }

                                Integer xhyhzgx = map.get("与户主关系");
                                if (xhyhzgx != null) {
                                    if (row2.getCell(xhyhzgx) != null) {
                                        row2.getCell(xhyhzgx).setCellType(Cell.CELL_TYPE_STRING);
                                        yhzgx = row2.getCell(xhyhzgx).getStringCellValue().trim();
                                        yhzgx = DictTextToValusUtil.yhzgxnsb(yhzgx);
                                        if (yhzgx.equals("1")) {
                                            hhbm = IdUtil.fastSimpleUUID();
                                        }
                                    }
                                }


                                Integer xhsjhm = map.get("联系电话");
                                if (xhsjhm != null) {
                                    if (row2.getCell(xhsjhm) != null) {
                                        row2.getCell(xhsjhm).setCellType(Cell.CELL_TYPE_STRING);
                                        sjhm = row2.getCell(xhsjhm).getStringCellValue().trim();
                                    }
                                }
                                Integer xhcpdj = map.get("新初评等级");
                                if (xhcpdj != null) {
                                    if (row2.getCell(xhcpdj) != null) {
                                        row2.getCell(xhcpdj).setCellType(Cell.CELL_TYPE_STRING);
                                        cpdj = row2.getCell(xhcpdj).getStringCellValue().trim();
                                    }
                                }

                                Integer xhfpdj = map.get("新复评等级");
                                if (xhfpdj != null) {
                                    if (row2.getCell(xhfpdj) != null) {
                                        row2.getCell(xhfpdj).setCellType(Cell.CELL_TYPE_STRING);
                                        fpdj = row2.getCell(xhfpdj).getStringCellValue().trim();
                                    }
                                }

                                Integer xhcpsx = map.get("新初评授信");
                                if (xhcpsx != null) {
                                    if (row2.getCell(xhcpsx) != null) {
                                        row2.getCell(xhcpsx).setCellType(Cell.CELL_TYPE_STRING);
                                        cpsx = row2.getCell(xhcpsx).getStringCellValue().trim();
                                    }
                                }

                                Integer xhfpsx = map.get("新复评授信");
                                if (xhfpsx != null) {
                                    if (row2.getCell(xhfpsx) != null) {
                                        row2.getCell(xhfpsx).setCellType(Cell.CELL_TYPE_STRING);
                                        fpsx = row2.getCell(xhfpsx).getStringCellValue().trim();
                                    }
                                }

                                Integer xhkhjl = map.get("所属客户经理");
                                if (xhkhjl != null) {
                                    if (row2.getCell(xhkhjl) != null) {
                                        row2.getCell(xhkhjl).setCellType(Cell.CELL_TYPE_STRING);
                                        sskhjl = row2.getCell(xhkhjl).getStringCellValue().trim();
                                    }
                                }

                                Integer xhcplx = map.get("产品类型");
                                if (xhcplx != null) {
                                    if (row2.getCell(xhcplx) != null) {
                                        row2.getCell(xhcplx).setCellType(Cell.CELL_TYPE_STRING);
                                        cplx = row2.getCell(xhcplx).getStringCellValue().trim();
                                    }
                                }

                                //这里要出来镇村组数据
                                Integer xhczhen = map.get("镇");
                                if (xhczhen != null) {
                                    if (row2.getCell(xhczhen) != null) {
                                        row2.getCell(xhczhen).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo += row2.getCell(xhczhen).getStringCellValue().trim();
                                    }
                                }

                                Integer xhcun = map.get("村");
                                if (xhcun != null) {
                                    if (row2.getCell(xhcun) != null) {
                                        row2.getCell(xhcun).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo += row2.getCell(xhcun).getStringCellValue().trim();
                                    }
                                }

                                Integer xhzu = map.get("组");
                                if (xhzu != null) {
                                    if (row2.getCell(xhzu) != null) {
                                        row2.getCell(xhzu).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo += row2.getCell(xhzu).getStringCellValue().trim();
                                    }
                                }


                                Integer xhsscz = map.get("所属村组");
                                if (xhsscz != null) {
                                    if (row2.getCell(xhsscz) != null) {
                                        row2.getCell(xhsscz).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhsscz).getStringCellValue().trim();
                                    }
                                }

                                Integer xhdz = map.get("地址");
                                if (xhdz != null) {
                                    if (row2.getCell(xhdz) != null) {
                                        row2.getCell(xhdz).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhdz).getStringCellValue().trim();
                                    }
                                }

                                Integer xhzcz = map.get("镇村组");
                                if (xhzcz != null) {
                                    if (row2.getCell(xhzcz) != null) {
                                        row2.getCell(xhzcz).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhzcz).getStringCellValue().trim();
                                    }
                                }

                                Integer xhzc = map.get("村组");
                                if (xhzc != null) {
                                    if (row2.getCell(xhzc) != null) {
                                        row2.getCell(xhzc).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhzc).getStringCellValue().trim();
                                    }
                                }

                                Integer xhczdz = map.get("村组地址");
                                if (xhczdz != null) {
                                    if (row2.getCell(xhczdz) != null) {
                                        row2.getCell(xhczdz).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhczdz).getStringCellValue().trim();
                                    }
                                }

                                String zzczxx = null;
                                if (StringUtils.isNotBlank(zcz))
                                    zzczxx = zcz;
                                if (StringUtils.isNotBlank(czinfo))
                                    zzczxx = czinfo;

                                CamsNhpjsxNsb camsNhpjsxNsb = new CamsNhpjsxNsb();
                                camsNhpjsxNsb.setCreator("cxdr");
                                camsNhpjsxNsb.setUpdator("cxdr");
                                camsNhpjsxNsb.setSynf("2022");
                                camsNhpjsxNsb.setDrwgmc(zzczxx);
                                camsNhpjsxNsb.setSszh(sszh);
                                camsNhpjsxNsb.setKhmc(khmc);
                                camsNhpjsxNsb.setDrsskhjl(sskhjl);
                                camsNhpjsxNsb.setZjhm(zjhm);
                                camsNhpjsxNsb.setSjhm(sjhm);
                                camsNhpjsxNsb.setYhzgx(yhzgx);
                                camsNhpjsxNsb.setCpdj(cpdj);
                                if (StringUtils.isNotBlank(cpsx)) {
                                    if (NumberUtil.isNumber(cpsx)) {
                                        camsNhpjsxNsb.setCpje(new BigDecimal(cpsx));
                                    } else {
                                        camsNhpjsxNsb.setCpje(BigDecimal.ZERO);
                                    }
                                }
                                camsNhpjsxNsb.setFpdj(fpdj);
                                if (StringUtils.isNotBlank(fpsx)) {
                                    if (NumberUtil.isNumber(fpsx)) {
                                        camsNhpjsxNsb.setFpje(new BigDecimal(fpsx));
                                    } else {
                                        camsNhpjsxNsb.setFpje(BigDecimal.ZERO);
                                    }
                                }
                                camsNhpjsxNsb.setDrcplx(cplx);
                                camsNhpjsxNsb.setHhbm(hhbm);
                                camsNhpjsxNsb.setBz(absolutePath);

                                //查一下证件号码


                                boolean save = service.save(camsNhpjsxNsb);
                                if (save)

                                    count++;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        log.info("===表的行数过少，不导入数据===");
                    }
                    wb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    commonImpFileLog.setFailReason("导入表格的sheet有多页");
                }

                commonImpFileLog.setImpNumber(count);
                commonImpFileLog.setFileNumber(rd);
                commonImpFileLogService.save(commonImpFileLog);
                log.info("===当前文件{}导入完成===", absolutePath);

            }
        }
        return Result.ok();
    }


    @RequestMapping("/nsbcw")
    public Result<?> nsbcw(String type) {
        if (StringUtils.isBlank(type)) {
            return Result.error("错误类型不能为空");
        }

        //删除
        String rootPath = uploadpath + File.separator + "errorNsb";
        if (FileUtil.exist(rootPath)) {
            FileUtil.del(rootPath);
        }

        LambdaQueryWrapper<HrBasOrganization> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(HrBasOrganization::getSjzzbz, "1");
        lambdaQueryWrapper.eq(HrBasOrganization::getZzlb, "4");

        List<HrBasOrganization> hrBasOrganizationList = hrBasOrganizationService.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(hrBasOrganizationList)) {
            for (int i = 0; i < hrBasOrganizationList.size(); i++) {

                HrBasOrganization hrBasOrganization = hrBasOrganizationList.get(i);
                log.info("==={}===查询数据", hrBasOrganization.getZzjc());
                List<String> allByErrorType = service.getAllByErrorType(hrBasOrganization.getZzbz(), type);
                allByErrorType.add("");
                if (CollUtil.isNotEmpty(allByErrorType)) {
                    //先加载目录
//                  String file =   uploadpath+File.separator+"errorNsb"+File.separator+hrBasOrganization.getZzjc();
//                  if (!FileUtil.exist(file)){
//                      FileUtil.mkdir(file);
//                  }

                    for (int j = 0; j < allByErrorType.size(); j++) {
                        LambdaQueryWrapper<CamsNhpjsxNsb> camsNhpjsxNsbLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        camsNhpjsxNsbLambdaQueryWrapper.eq(CamsNhpjsxNsb::getSszh, hrBasOrganization.getZzbz());
                        if (StringUtils.isBlank(allByErrorType.get(j))) {
                            camsNhpjsxNsbLambdaQueryWrapper.isNull(CamsNhpjsxNsb::getDrwgmc);
                        } else {
                            camsNhpjsxNsbLambdaQueryWrapper.eq(CamsNhpjsxNsb::getDrwgmc, allByErrorType.get(j));
                        }
                        camsNhpjsxNsbLambdaQueryWrapper.eq(CamsNhpjsxNsb::getErrortype, type);
                        List<CamsNhpjsxNsb> list = service.list(camsNhpjsxNsbLambdaQueryWrapper);

                        log.info("==={}===查询", allByErrorType.get(j));

                        if (CollUtil.isNotEmpty(list)) {
                            String file = uploadpath + File.separator + "errorNsb" + File.separator + hrBasOrganization.getZzjc();
                            if (!FileUtil.exist(file)) {
                                FileUtil.mkdir(file);
                            }

                            try {


                                List<List<String>> dataList = Lists.newArrayList();
                                for (int k = 0; k < list.size(); k++) {
                                    List<String> colList = Lists.newArrayList();

                                    CamsNhpjsxNsb camsNhpjsxNsb = list.get(k);

                                    if (StringUtils.isNotBlank(camsNhpjsxNsb.getKhmc())) {
                                        colList.add(camsNhpjsxNsb.getKhmc());
                                    } else {
                                        colList.add("");
                                    }

                                    if (StringUtils.isNotBlank(camsNhpjsxNsb.getZjhm())) {
                                        colList.add(camsNhpjsxNsb.getZjhm());
                                    } else {
                                        colList.add("");
                                    }

                                    if (StringUtils.isNotBlank(camsNhpjsxNsb.getYhzgx())) {
                                        String yhzgx = dictService.queryDictTextByKey("yhzgx", camsNhpjsxNsb.getYhzgx());
                                        colList.add(yhzgx);
                                    } else {
                                        colList.add("");
                                    }

                                    if (StringUtils.isNotBlank(camsNhpjsxNsb.getSjhm())) {
                                        colList.add(camsNhpjsxNsb.getSjhm());
                                    } else {
                                        colList.add("");
                                    }

                                    if (StringUtils.isNotBlank(camsNhpjsxNsb.getCpdj())) {
                                        colList.add(camsNhpjsxNsb.getCpdj());
                                    } else {
                                        colList.add("");
                                    }

                                    if (camsNhpjsxNsb.getCpje() != null) {
                                        colList.add(camsNhpjsxNsb.getCpje().toString());
                                    } else {
                                        colList.add("");

                                    }

                                    if (StringUtils.isNotBlank(camsNhpjsxNsb.getFpdj())) {
                                        colList.add(camsNhpjsxNsb.getFpdj());
                                    } else {
                                        colList.add("");
                                    }

                                    if (camsNhpjsxNsb.getFpje() != null) {
                                        colList.add(camsNhpjsxNsb.getFpje().toString());
                                    } else {
                                        colList.add("");

                                    }

                                    if (StringUtils.isNotBlank(camsNhpjsxNsb.getDrcplx())) {
                                        colList.add(camsNhpjsxNsb.getDrcplx());
                                    } else {
                                        colList.add("");
                                    }

                                    if (StringUtils.isNotBlank(camsNhpjsxNsb.getDrsskhjl())) {
                                        colList.add(camsNhpjsxNsb.getDrsskhjl());
                                    } else {
                                        colList.add("");
                                    }

                                    if (StringUtils.isNotBlank(camsNhpjsxNsb.getDrwgmc())) {
                                        colList.add(camsNhpjsxNsb.getDrwgmc());
                                    } else {
                                        colList.add("");
                                    }

                                    dataList.add(colList);
                                }


                                HSSFWorkbook workbook = new HSSFWorkbook();
                                HSSFSheet sheet = workbook.createSheet("年审表");
                                NsbErrorTable nsbErrorTable = new NsbErrorTable(workbook, sheet);
                                nsbErrorTable.setTableName("浏阳农商银行" + hrBasOrganization.getZzjc() + "农户评级授信年审表");
                                nsbErrorTable.setTableHeader();
                                nsbErrorTable.setRowTwoNam(allByErrorType.get(j));
                                nsbErrorTable.setTableData(dataList);

                                nsbErrorTable.setTableFooter();
                                FileOutputStream fileOutputStream = null;
                                if (StringUtils.isBlank(allByErrorType.get(j))) {
                                    fileOutputStream = new FileOutputStream(file + File.separator + "镇村组未空请补充" + ".xlsx");
                                } else {
                                    fileOutputStream = new FileOutputStream(file + File.separator + allByErrorType.get(j) + ".xlsx");
                                }
                                workbook.write(fileOutputStream);
                                workbook.close();
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                    }


                }
            }

            log.info("===所有支行查询完毕===");
        }
        return Result.ok();
    }


    @RequestMapping(value = "/drnsb2")
    public Result<?> drnsb2(String logbz) {
        String path = uploadpath + File.separator + "drnsb2";
        List<File> files = FileUtil.loopFiles(path);
        if (CollUtil.isNotEmpty(files)) {
            log.info("===当前导入{}个文件===", files.size());
            LambdaQueryWrapper<HrBasOrganization> hrBasOrganizationLambdaQueryWrapper = new LambdaQueryWrapper<>();
            hrBasOrganizationLambdaQueryWrapper.isNotNull(HrBasOrganization::getZzjc);
            List<HrBasOrganization> hrBasOrganizations = hrBasOrganizationService.list(hrBasOrganizationLambdaQueryWrapper);

            for (int i = 0; i < files.size(); i++) {
                CommonImpFileLog commonImpFileLog = new CommonImpFileLog();
                File file = files.get(i);
                String absolutePath = file.getAbsolutePath();
                //先查这个文件  如果能存在则不导入
                LambdaQueryWrapper<CommonImpFileLog> commonImpFileLogLambdaQueryWrapper = new LambdaQueryWrapper<>();
                commonImpFileLogLambdaQueryWrapper.eq(CommonImpFileLog::getFileAddr, absolutePath);
                List<CommonImpFileLog> list = commonImpFileLogService.list(commonImpFileLogLambdaQueryWrapper);
                if (CollUtil.isNotEmpty(list)) {
                    log.info("==={}文件已经导入===", absolutePath);
                    continue;
                }

                commonImpFileLog.setFileAddr(absolutePath);
                commonImpFileLog.setFileName(file.getName());

                log.info("===当前导入文件{}===", absolutePath);
                if (!absolutePath.endsWith(".xlsx") && !absolutePath.endsWith(".xls")) {
                    continue;
                }
                //根据文件夹的名称匹配他的所属支行
                String sszh = null;
                if (CollUtil.isNotEmpty(hrBasOrganizations)) {
                    String replace = absolutePath.replace(uploadpath, "");
                    String substring = replace.substring(6, replace.indexOf("支行"));
                    for (int j = 0; j < hrBasOrganizations.size(); j++) {
                        String zzjc = hrBasOrganizations.get(j).getZzjc().replace("分理处", "");
                        zzjc = zzjc.replace("支行", "");
                        String zzbz = hrBasOrganizations.get(j).getZzbz();
                        //获取支行名称
                        if (substring.contains(zzjc)) {
                            sszh = zzbz;
                            break;
                        }
                    }
                }

                Workbook wb = null;
                int count = 0;
                int updateCount = 0;
                int rd = 0;
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
                    commonImpFileLog.setFileSize(lastRowNum);
                    log.info("===当前文件有{}行数据===", lastRowNum);
                    if (lastRowNum >= 1) {
                        Map<String, Integer> map = new HashMap<>();

                        for (int o = 0; o < 3; o++) {
                            Row row1 = sheet.getRow(o);
                            if (row1 != null) {
                                if (row1.getCell(o) != null) {
                                    row1.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                                    String dy = row1.getCell(0).getStringCellValue().trim();
                                    System.out.println(dy);
                                    if (dy.contains("姓名")) {
                                        short lastCellNum = row1.getLastCellNum();
                                        for (int j = 0; j < lastCellNum; j++) {
                                            if (row1.getCell(j) != null) {
                                                row1.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                                                String biaotou = row1.getCell(j).getStringCellValue().trim();
                                                map.put(biaotou, j);
                                            }
                                        }
                                        break;
                                    }
                                }
                            }

                        }


                        String hhbm = null;
                        String sskhjl = null;

                        for (int j = 1; j < 50000; j++) {
                            try {

                                rd = j;
                                Row row2 = sheet.getRow(j);
                                if (row2 == null)
                                    break;

                                String khmc = null;
                                String zjhm = null;
                                String yhzgx = null;
                                String sjhm = null;
                                String cpdj = null;
                                String cpsx = null;
                                String fpdj = null;
                                String fpsx = null;
                                String cplx = null;
                                String czinfo = null;

                                Integer khmcxh = map.get("姓名");
                                if (khmcxh != null) {
                                    if (row2.getCell(khmcxh) != null) {
                                        row2.getCell(khmcxh).setCellType(Cell.CELL_TYPE_STRING);
                                        khmc = row2.getCell(khmcxh).getStringCellValue().trim();
                                        if (khmc.contains("初评小组签字"))
                                            break;
                                    }
                                }

                                Integer xhzjhm = map.get("身份证号码");
                                if (xhzjhm != null) {
                                    if (row2.getCell(xhzjhm) != null) {
                                        row2.getCell(xhzjhm).setCellType(Cell.CELL_TYPE_STRING);
                                        zjhm = row2.getCell(xhzjhm).getStringCellValue().trim();
                                        if (IdcardUtil.isValidCard(zjhm)) {
                                            //重复的进行更新

                                        } else {
                                            continue;
                                        }
                                    }
                                } else {
                                    continue;
                                }


                                Integer xhsjhm = map.get("联系电话");
                                if (xhsjhm != null) {
                                    if (row2.getCell(xhsjhm) != null) {
                                        row2.getCell(xhsjhm).setCellType(Cell.CELL_TYPE_STRING);
                                        sjhm = row2.getCell(xhsjhm).getStringCellValue().trim();
                                    }
                                }
                                Integer xhcpdj = map.get("新初评等级");
                                if (xhcpdj != null) {
                                    if (row2.getCell(xhcpdj) != null) {
                                        row2.getCell(xhcpdj).setCellType(Cell.CELL_TYPE_STRING);
                                        cpdj = row2.getCell(xhcpdj).getStringCellValue().trim();
                                    }
                                }

                                Integer xhfpdj = map.get("新复评等级");
                                if (xhfpdj != null) {
                                    if (row2.getCell(xhfpdj) != null) {
                                        row2.getCell(xhfpdj).setCellType(Cell.CELL_TYPE_STRING);
                                        fpdj = row2.getCell(xhfpdj).getStringCellValue().trim();
                                    }
                                }

                                Integer xhcpsx = map.get("新初评授信");
                                if (xhcpsx != null) {
                                    if (row2.getCell(xhcpsx) != null) {
                                        row2.getCell(xhcpsx).setCellType(Cell.CELL_TYPE_STRING);
                                        cpsx = row2.getCell(xhcpsx).getStringCellValue().trim();
                                    }
                                }

                                Integer xhfpsx = map.get("新复评授信");
                                if (xhfpsx != null) {
                                    if (row2.getCell(xhfpsx) != null) {
                                        row2.getCell(xhfpsx).setCellType(Cell.CELL_TYPE_STRING);
                                        fpsx = row2.getCell(xhfpsx).getStringCellValue().trim();
                                    }
                                }


                                Integer xhzcz = map.get("镇村组");
                                if (xhzcz != null) {
                                    if (row2.getCell(xhzcz) != null) {
                                        row2.getCell(xhzcz).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhzcz).getStringCellValue().trim();
                                    }
                                }

                                Integer xhzc = map.get("村组");
                                if (xhzc != null) {
                                    if (row2.getCell(xhzc) != null) {
                                        row2.getCell(xhzc).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhzc).getStringCellValue().trim();
                                    }
                                }

                                Integer xhczdz = map.get("村组地址");
                                if (xhczdz != null) {
                                    if (row2.getCell(xhczdz) != null) {
                                        row2.getCell(xhczdz).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhczdz).getStringCellValue().trim();
                                    }
                                }

                                String zzczxx = null;
                                if (StringUtils.isNotBlank(czinfo))
                                    zzczxx = czinfo;

                                CamsNhpjsxNsb camsNhpjsxNsb = new CamsNhpjsxNsb();
                                camsNhpjsxNsb.setCreator("cxdr");
                                camsNhpjsxNsb.setUpdator("cxdr");
                                camsNhpjsxNsb.setSynf("2022");
                                camsNhpjsxNsb.setDrwgmc(zzczxx);
                                camsNhpjsxNsb.setSszh(sszh);
                                camsNhpjsxNsb.setKhmc(khmc);
                                camsNhpjsxNsb.setDrsskhjl(sskhjl);
                                camsNhpjsxNsb.setZjhm(zjhm);
                                camsNhpjsxNsb.setSjhm(sjhm);
                                camsNhpjsxNsb.setYhzgx(yhzgx);
                                if (StringUtils.isNotBlank(cpdj))
                                    camsNhpjsxNsb.setCpdj(cpdj);
                                if (StringUtils.isNotBlank(cpsx)) {
                                    if (NumberUtil.isNumber(cpsx)) {
                                        camsNhpjsxNsb.setCpje(new BigDecimal(cpsx));
                                    } else {
                                        camsNhpjsxNsb.setCpje(BigDecimal.ZERO);
                                    }
                                }
                                if (StringUtils.isNotBlank(fpdj))
                                    camsNhpjsxNsb.setFpdj(fpdj);
                                if (StringUtils.isNotBlank(fpsx)) {
                                    if (NumberUtil.isNumber(fpsx)) {
                                        camsNhpjsxNsb.setFpje(new BigDecimal(fpsx));
                                    } else {
                                        camsNhpjsxNsb.setFpje(BigDecimal.ZERO);
                                    }
                                }
                                camsNhpjsxNsb.setDrcplx(cplx);
                                camsNhpjsxNsb.setHhbm(hhbm);
                                camsNhpjsxNsb.setBz(absolutePath);

                                //查一下证件号码
                                LambdaQueryWrapper<CamsNhpjsxNsb> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                                lambdaQueryWrapper.eq(CamsNhpjsxNsb::getZjhm, zjhm);
                                List<CamsNhpjsxNsb> list1 = service.list(lambdaQueryWrapper);
                                if (CollUtil.isNotEmpty(list1)) {
                                    if (StringUtils.isNotBlank(logbz)) {
                                        log.info("==={}===", camsNhpjsxNsb.toString());
                                    }
                                    //根据身份证更新
                                    service.updateByDao(camsNhpjsxNsb);
                                    updateCount++;

                                } else {
                                    boolean save = service.save(camsNhpjsxNsb);
                                    if (save)
                                        count++;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        log.info("===表的行数过少，不导入数据===");
                    }
                    wb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    commonImpFileLog.setFailReason("导入表格的sheet有多页");
                }

                commonImpFileLog.setImpNumber(count);
                commonImpFileLog.setUpdateNumber(updateCount);
                commonImpFileLog.setFileNumber(rd);
                commonImpFileLogService.save(commonImpFileLog);
                log.info("===当前文件{}导入完成===", absolutePath);

            }
        }
        return Result.ok();
    }

    @RequestMapping(value = "/drnsb0")
    public Result<?> drnsb0(String logbz) {
        String path = uploadpath + File.separator + "drnsb2";
        List<File> files = FileUtil.loopFiles(path);
        if (CollUtil.isNotEmpty(files)) {
            log.info("===当前导入{}个文件===", files.size());
            LambdaQueryWrapper<HrBasOrganization> hrBasOrganizationLambdaQueryWrapper = new LambdaQueryWrapper<>();
            hrBasOrganizationLambdaQueryWrapper.isNotNull(HrBasOrganization::getZzjc);
            List<HrBasOrganization> hrBasOrganizations = hrBasOrganizationService.list(hrBasOrganizationLambdaQueryWrapper);

            for (int i = 0; i < files.size(); i++) {
                CommonImpFileLog commonImpFileLog = new CommonImpFileLog();
                File file = files.get(i);
                String absolutePath = file.getAbsolutePath();
                //先查这个文件  如果能存在则不导入
                LambdaQueryWrapper<CommonImpFileLog> commonImpFileLogLambdaQueryWrapper = new LambdaQueryWrapper<>();
                commonImpFileLogLambdaQueryWrapper.eq(CommonImpFileLog::getFileAddr, absolutePath);
                List<CommonImpFileLog> list = commonImpFileLogService.list(commonImpFileLogLambdaQueryWrapper);
                if (CollUtil.isNotEmpty(list)) {
                    log.info("==={}文件已经导入===", absolutePath);
                    continue;
                }

                commonImpFileLog.setFileAddr(absolutePath);
                commonImpFileLog.setFileName(file.getName());

                log.info("===当前导入文件{}===", absolutePath);
                if (!absolutePath.endsWith(".xlsx") && !absolutePath.endsWith(".xls")) {
                    continue;
                }
                //根据文件夹的名称匹配他的所属支行
                String sszh = null;
                if (CollUtil.isNotEmpty(hrBasOrganizations)) {
                    String replace = absolutePath.replace(uploadpath, "");
                    String substring = replace.substring(6, replace.indexOf("支行"));
                    for (int j = 0; j < hrBasOrganizations.size(); j++) {
                        String zzjc = hrBasOrganizations.get(j).getZzjc().replace("分理处", "");
                        zzjc = zzjc.replace("支行", "");
                        String zzbz = hrBasOrganizations.get(j).getZzbz();
                        //获取支行名称
                        if (substring.contains(zzjc)) {
                            sszh = zzbz;
                            break;
                        }
                    }
                }

                Workbook wb = null;
                int count = 0;
                int updateCount = 0;
                int rd = 0;
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
                    commonImpFileLog.setFileSize(lastRowNum);
                    log.info("===当前文件有{}行数据===", lastRowNum);
                    if (lastRowNum >= 1) {
                        /**
                         *  第一行是年审表表头
                         *  第二行是镇村组 也有的不是 或者 下面表头覆盖
                         * */

//                        String zcz = null;
//                        Row row = sheet.getRow(1);
//                        if (row.getCell(1) != null) {
//                            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
//                            zcz = row.getCell(1).getStringCellValue().trim();
//                        }

                        Row row1 = sheet.getRow(0);
                        Map<String, Integer> map = new HashMap<>();
                        short lastCellNum = row1.getLastCellNum();
                        for (int j = 0; j < lastCellNum; j++) {
                            if (row1.getCell(j) != null) {
                                row1.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                                String biaotou = row1.getCell(j).getStringCellValue().trim();
                                map.put(biaotou, j);
                            }
                        }
                        String hhbm = null;
                        String sskhjl = null;

                        for (int j = 1; j < 50000; j++) {
                            try {

                                rd = j;
                                Row row2 = sheet.getRow(j);
                                if (row2 == null)
                                    break;

                                String khmc = null;
                                String zjhm = null;
                                String yhzgx = null;
                                String sjhm = null;
                                String cpdj = null;
                                String cpsx = null;
                                String fpdj = null;
                                String fpsx = null;
                                String cplx = null;
                                String czinfo = null;


                                Integer xhfpsx = map.get("新复评授信");
                                if (xhfpsx != null) {
                                    if (row2.getCell(xhfpsx) != null) {
                                        row2.getCell(xhfpsx).setCellType(Cell.CELL_TYPE_STRING);
                                        fpsx = row2.getCell(xhfpsx).getStringCellValue().trim();
                                        if (StringUtils.isNotBlank(fpsx)) {
                                            if (NumberUtil.isNumber(fpsx)) {
                                                if (Integer.parseInt(fpsx) != 0) {
                                                    continue;
                                                } else {
                                                    fpsx = "0";
                                                }
                                            } else {
                                                fpsx = "0";
                                            }
                                        }

                                    }
                                }


                                Integer khmcxh = map.get("姓名");
                                if (khmcxh != null) {
                                    if (row2.getCell(khmcxh) != null) {
                                        row2.getCell(khmcxh).setCellType(Cell.CELL_TYPE_STRING);
                                        khmc = row2.getCell(khmcxh).getStringCellValue().trim();
                                        if (khmc.contains("初评小组签字"))
                                            break;
                                    }
                                }

                                Integer xhzjhm = map.get("身份证号码");
                                if (xhzjhm != null) {
                                    if (row2.getCell(xhzjhm) != null) {
                                        row2.getCell(xhzjhm).setCellType(Cell.CELL_TYPE_STRING);
                                        zjhm = row2.getCell(xhzjhm).getStringCellValue().trim();
                                        if (IdcardUtil.isValidCard(zjhm)) {
                                            //重复的进行更新

                                        } else {
                                            continue;
                                        }
                                    }
                                } else {
                                    continue;
                                }


                                Integer xhsjhm = map.get("联系电话");
                                if (xhsjhm != null) {
                                    if (row2.getCell(xhsjhm) != null) {
                                        row2.getCell(xhsjhm).setCellType(Cell.CELL_TYPE_STRING);
                                        sjhm = row2.getCell(xhsjhm).getStringCellValue().trim();
                                    }
                                }
                                Integer xhcpdj = map.get("新初评等级");
                                if (xhcpdj != null) {
                                    if (row2.getCell(xhcpdj) != null) {
                                        row2.getCell(xhcpdj).setCellType(Cell.CELL_TYPE_STRING);
                                        cpdj = row2.getCell(xhcpdj).getStringCellValue().trim();
                                    }
                                }

                                Integer xhfpdj = map.get("新复评等级");
                                if (xhfpdj != null) {
                                    if (row2.getCell(xhfpdj) != null) {
                                        row2.getCell(xhfpdj).setCellType(Cell.CELL_TYPE_STRING);
                                        fpdj = row2.getCell(xhfpdj).getStringCellValue().trim();
                                    }
                                }

                                Integer xhcpsx = map.get("新初评授信");
                                if (xhcpsx != null) {
                                    if (row2.getCell(xhcpsx) != null) {
                                        row2.getCell(xhcpsx).setCellType(Cell.CELL_TYPE_STRING);
                                        cpsx = row2.getCell(xhcpsx).getStringCellValue().trim();
                                    }
                                }


                                Integer xhzcz = map.get("镇村组");
                                if (xhzcz != null) {
                                    if (row2.getCell(xhzcz) != null) {
                                        row2.getCell(xhzcz).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhzcz).getStringCellValue().trim();
                                    }
                                }

                                Integer xhzc = map.get("村组");
                                if (xhzc != null) {
                                    if (row2.getCell(xhzc) != null) {
                                        row2.getCell(xhzc).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhzc).getStringCellValue().trim();
                                    }
                                }

                                Integer xhczdz = map.get("村组地址");
                                if (xhczdz != null) {
                                    if (row2.getCell(xhczdz) != null) {
                                        row2.getCell(xhczdz).setCellType(Cell.CELL_TYPE_STRING);
                                        czinfo = row2.getCell(xhczdz).getStringCellValue().trim();
                                    }
                                }

                                String zzczxx = null;
                                if (StringUtils.isNotBlank(czinfo))
                                    zzczxx = czinfo;

                                CamsNhpjsxNsb camsNhpjsxNsb = new CamsNhpjsxNsb();
                                camsNhpjsxNsb.setCreator("cxdr");
                                camsNhpjsxNsb.setUpdator("cxdr");
                                camsNhpjsxNsb.setSynf("2022");
                                camsNhpjsxNsb.setDrwgmc(zzczxx);
                                camsNhpjsxNsb.setSszh(sszh);
                                camsNhpjsxNsb.setKhmc(khmc);
                                camsNhpjsxNsb.setDrsskhjl(sskhjl);
                                camsNhpjsxNsb.setZjhm(zjhm);
                                camsNhpjsxNsb.setSjhm(sjhm);
                                camsNhpjsxNsb.setYhzgx(yhzgx);
                                if (StringUtils.isNotBlank(cpdj))
                                    camsNhpjsxNsb.setCpdj(cpdj);
                                if (StringUtils.isNotBlank(cpsx)) {
                                    if (NumberUtil.isNumber(cpsx)) {
                                        camsNhpjsxNsb.setCpje(new BigDecimal(cpsx));
                                    } else {
                                        camsNhpjsxNsb.setCpje(BigDecimal.ZERO);
                                    }
                                }
                                if (StringUtils.isNotBlank(fpdj))
                                    camsNhpjsxNsb.setFpdj(fpdj);
                                if (StringUtils.isNotBlank(fpsx)) {
                                    if (NumberUtil.isNumber(fpsx)) {
                                        camsNhpjsxNsb.setFpje(new BigDecimal(fpsx));
                                    } else {
                                        camsNhpjsxNsb.setFpje(BigDecimal.ZERO);
                                    }
                                }
                                camsNhpjsxNsb.setDrcplx(cplx);
                                camsNhpjsxNsb.setHhbm(hhbm);
                                camsNhpjsxNsb.setBz(absolutePath);

                                //查一下证件号码
                                LambdaQueryWrapper<CamsNhpjsxNsb> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                                lambdaQueryWrapper.eq(CamsNhpjsxNsb::getZjhm, zjhm);
                                List<CamsNhpjsxNsb> list1 = service.list(lambdaQueryWrapper);
                                if (CollUtil.isNotEmpty(list1)) {
                                    if (StringUtils.isNotBlank(logbz)) {
                                        log.info("==={}===", camsNhpjsxNsb.toString());
                                    }
                                    //根据身份证更新
                                    service.updateByDao(camsNhpjsxNsb);
                                    updateCount++;

                                } else {
                                    boolean save = service.save(camsNhpjsxNsb);
                                    if (save)
                                        count++;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        log.info("===表的行数过少，不导入数据===");
                    }
                    wb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    commonImpFileLog.setFailReason("导入表格的sheet有多页");
                }

                commonImpFileLog.setImpNumber(count);
                commonImpFileLog.setUpdateNumber(updateCount);
                commonImpFileLog.setFileNumber(rd);
                commonImpFileLogService.save(commonImpFileLog);
                log.info("===当前文件{}导入完成===", absolutePath);

            }
        }
        return Result.ok();
    }

    @RequestMapping(value = "/drnsb3")
    public Result<?> drnsb3() {
        String path = uploadpath + File.separator + "drsjhm";
        List<File> files = FileUtil.loopFiles(path);
        if (CollUtil.isNotEmpty(files)) {
            log.info("===当前导入{}个文件===", files.size());

            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                String absolutePath = file.getAbsolutePath();
                log.info("===当前导入文件{}===", absolutePath);
                if (!absolutePath.endsWith(".xlsx") && !absolutePath.endsWith(".xls")) {
                    continue;
                }
                //根据文件夹的名称匹配他的所属支行

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
                    if (lastRowNum > 0) {


                        for (int j = 0; j < lastRowNum; j++) {
                            try {
                                Row row = sheet.getRow(j);
                                String zjhm = null;
                                String sjhm = null;
                                if (row.getCell(0) != null) {
                                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                                    zjhm = row.getCell(0).getStringCellValue().trim();
                                }
                                if (row.getCell(1) != null) {
                                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                                    sjhm = row.getCell(1).getStringCellValue().trim();
                                }

                                System.out.println(zjhm);
                                System.out.println(sjhm);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else {
                        log.info("===表的行数过少，不导入数据===");
                    }
                    wb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                log.info("===当前文件{}导入完成===", absolutePath);

            }
        }
        return Result.ok();
    }

    @RequestMapping("/tq")
    public Result<?> tq(){
        try{
            service.tq();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.ok();
    }
}
