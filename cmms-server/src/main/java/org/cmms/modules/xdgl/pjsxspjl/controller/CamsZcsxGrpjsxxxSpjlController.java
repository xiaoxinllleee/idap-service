package org.cmms.modules.xdgl.pjsxspjl.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Lists;
import freemarker.template.TemplateException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.PermissionData;
import org.cmms.common.system.base.entity.LyNewNsbTable;
import org.cmms.common.system.base.entity.LyNewNsbZhTable;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.common.util.DictTextToValusUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.cmms.modules.khgl.grkhgl.mapper.KhhmcxxMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.qxfk.service.IQxfkPdfImgService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.xdgl.grkhpjsx.entity.FamerExportWord;
import org.cmms.modules.xdgl.grkhpjsx.entity.Vgrpjsxspjl;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrkhpjsxService;
import org.cmms.modules.xdgl.grkhpjsx.service.IVgrpjsxspjlService;
import org.cmms.modules.xdgl.nsb.entity.CamsNhpjsxNsb;
import org.cmms.modules.xdgl.nsb.service.ICamsNhpjsxNsbService;
import org.cmms.modules.xdgl.pjsxspjl.entity.CamsZcsxGrpjsxxxSpjl;
import org.cmms.modules.xdgl.pjsxspjl.entity.LyNewNsVO;
import org.cmms.modules.xdgl.pjsxspjl.service.ICamsZcsxGrpjsxxxSpjlService;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.mapper.EjyxdyglMapper;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.entity.VSjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.mapper.SjyxdyglMapper;
import org.cmms.modules.yxdygl.sjyxdygl.service.IVSjyxdyglService;
import org.cmms.modules.yxdygl.yjyxdygl.entity.Yjyxdygl;
import org.cmms.modules.yxdygl.yjyxdygl.mapper.YjyxdyglMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 评级授信审批记录
 * @Author: jeecg-boot
 * @Date: 2020-08-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "评级授信审批记录")
@RestController
@RequestMapping("/xdgl/pjsxspjl/camsZcsxGrpjsxxxSpjl")
public class CamsZcsxGrpjsxxxSpjlController extends JeecgController<CamsZcsxGrpjsxxxSpjl, ICamsZcsxGrpjsxxxSpjlService> {

    @Autowired
    IGrkhpjsxService iGrkhpjsxService;
    @Autowired
    IDictValueQuery iDictValueQuery;
    @Autowired
    IVgrpjsxspjlService iVgrpjsxspjlService;
    @Autowired
    private ISysDictService dictService;
    @Autowired
    YjyxdyglMapper yjyxdyglMapper;
    @Autowired
    EjyxdyglMapper ejyxdyglMapper;
    @Autowired
    SjyxdyglMapper sjyxdyglMapper;
    @Autowired
    IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    IVSjyxdyglService ivSjyxdyglService;
    @Autowired
    INhxqService nhxqService;
    @Autowired
    IQxfkPdfImgService qxfkPdfImgService;
    @Autowired
    ICamsNhpjsxNsbService camsNhpjsxNsbService;
    @Autowired
    RedisUtil redisUtil;

    QueryWrapper<Vgrpjsxspjl> commonQW = null;

    /**
     * 分页列表查询
     *
     * @param camsZcsxGrpjsxxxSpjl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "评级授信审批记录-分页列表查询")
    @ApiOperation(value = "评级授信审批记录-分页列表查询", notes = "评级授信审批记录-分页列表查询")
    @GetMapping(value = "/list")
    @PermissionData(pageComponent = "xdgl/pjsxspjl/CamsZcsxGrpjsxxxSpjlList")
    public Result<?> queryPageList(Vgrpjsxspjl camsZcsxGrpjsxxxSpjl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String nf = null;
        if (StringUtils.isNoneBlank(camsZcsxGrpjsxxxSpjl.getYj())) {
            nf = camsZcsxGrpjsxxxSpjl.getYj();
            camsZcsxGrpjsxxxSpjl.setYj(null);
        }
        camsZcsxGrpjsxxxSpjl.setStatus(2);
        QueryWrapper<Vgrpjsxspjl> queryWrapper = QueryGenerator.initQueryWrapper(camsZcsxGrpjsxxxSpjl, req.getParameterMap());
        if (nf != null)
            queryWrapper.apply("sqrq > to_date(" + nf + ",'yyyy')");
        queryWrapper.orderByDesc("sqrq");

        commonQW = queryWrapper;
        Page<Vgrpjsxspjl> page = new Page<Vgrpjsxspjl>(pageNo, pageSize);
        IPage<Vgrpjsxspjl> pageList = iVgrpjsxspjlService.page(page, queryWrapper);
        pageList.setTotal(305441l);
        pageList.setPages(30545l);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param camsZcsxGrpjsxxxSpjl
     * @return
     */
    @AutoLog(value = "评级授信审批记录-添加")
    @ApiOperation(value = "评级授信审批记录-添加", notes = "评级授信审批记录-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxxSpjl) {
        service.save(camsZcsxGrpjsxxxSpjl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param camsZcsxGrpjsxxxSpjl
     * @return
     */
    @AutoLog(value = "评级授信审批记录-编辑")
    @ApiOperation(value = "评级授信审批记录-编辑", notes = "评级授信审批记录-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxxSpjl) {
        service.updateById(camsZcsxGrpjsxxxSpjl);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评级授信审批记录-通过id删除")
    @ApiOperation(value = "评级授信审批记录-通过id删除", notes = "评级授信审批记录-通过id删除")
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
    @AutoLog(value = "评级授信审批记录-批量删除")
    @ApiOperation(value = "评级授信审批记录-批量删除", notes = "评级授信审批记录-批量删除")
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
    @AutoLog(value = "评级授信审批记录-通过id查询")
    @ApiOperation(value = "评级授信审批记录-通过id查询", notes = "评级授信审批记录-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxxSpjl = service.getById(id);
        return Result.ok(camsZcsxGrpjsxxxSpjl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param camsZcsxGrpjsxxxSpjl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxxSpjl) {
        return super.exportXls(request, camsZcsxGrpjsxxxSpjl, CamsZcsxGrpjsxxxSpjl.class, "评级授信审批记录");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            String originalFilename = file.getOriginalFilename();
            boolean isExcel2003 = true;
            if (file.getName().matches("^.+\\.(?i)(xlsx)$")) {
                isExcel2003 = false;
            }
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(file.getInputStream());
            } else {
                wb = new XSSFWorkbook(file.getInputStream());
            }
            Sheet sheet = wb.getSheetAt(0);
            /*for (int r = 3; r <= sheet.getLastRowNum() - 1; r++) {
                Row row = sheet.getRow(r);
                String idn = "";
                String dj = "";
                String je = "";
                Double jed = 0d;
                String name = "";
                //新版本
			  if (row.getCell(4) != null) {
				  row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				  idn = row.getCell(4).getStringCellValue().trim();
				  System.out.println(idn);
			  } else {
				  log.error("导入失败(第" + (r + 1) + "行,身份证未填写)");
				  continue;
			  }

			  if (row.getCell(14) != null) {
				  row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
				  dj = row.getCell(14).getStringCellValue().trim();
			  } else {
				  log.error("导入失败(第" + (r + 1) + "行,复评信用等级未填写)");
				  continue;
			  }

			  if (row.getCell(15) != null) {
				  row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
				  je = row.getCell(15).getStringCellValue().trim();
			  } else {
				  log.error("导入失败(第" + (r + 1) + "行,复评授信金额未填写)");
				  continue;
			  }

                if (row.getCell(0) != null) {
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    name = row.getCell(0).getStringCellValue().trim();
                }
                if (row.getCell(1) != null) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    idn = row.getCell(1).getStringCellValue().trim();
                } else {
                    log.error("导入失败(第" + (r + 1) + "行,证件号码未填写)");
                    continue;
                }

                if (row.getCell(10) != null) {
                    row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                    dj = row.getCell(10).getStringCellValue().trim();
                } else {
                    continue;
                }

                if (row.getCell(11) != null) {
                    row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                    je = row.getCell(11).getStringCellValue().trim();
                } else {
                    continue;
                }

                if (isInteger(je)) {
                    if (StringUtils.isNoneBlank(je)) {
                        jed = Double.parseDouble(je);
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }

                if ("特级".equals(dj) || dj.contains("A")) {
                    dj = "A";
                    if (jed > 30)
                        jed = 30d;
                } else if ("优秀".equals(dj) || dj.contains("B")) {
                    dj = "B";
                    if (jed > 20)
                        jed = 20d;
                } else if ("较好".equals(dj) || dj.contains("C")) {
                    dj = "C";
                    if (jed > 10)
                        jed = 10d;
                } else if ("一般".equals(dj) || dj.contains("D")) {
                    dj = "D";
                    if (jed > 5)
                        jed = 5d;
                } else if ("级外".equals(dj) || dj.contains("E")) {
                    dj = "E";
                    jed = 0d;
                } else {
                    continue;
                }


                service.updateFp(idn, dj, jed, name);

            }*/

            //20221012最新版年审
            Row row1 = sheet.getRow(1);
            String zun = null;
            if (row1.getCell(11) != null) {
                row1.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                zun = row1.getCell(11).getStringCellValue().trim();
            } else {
                log.info("===导入文件镇村组为空===");
            }
            int count = 0;
            for (int r = 3; r <= sheet.getLastRowNum() - 1; r++){
                 Row row = sheet.getRow(r);
                 String khmc = null;
                 String zjhm = null;
                 String yhzgx = null;
                 String lxfs = null;
                 String cpdj = null;
                 String cpsx = null;
                 String fpdj = null;
                 String fpsx = null;
                 String cplx = null;
                 String sskhjl = null;

                if (row.getCell(0) != null) {
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    khmc = row.getCell(0).getStringCellValue().trim();
                } else {
                    log.info("===导入客户姓名为空===");
                }

                if (row.getCell(1) != null) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    zjhm = row.getCell(1).getStringCellValue().trim();
                } else {
                    log.info("===导入zjhm为空===");
                }

                if (row.getCell(2) != null) {
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    yhzgx = row.getCell(2).getStringCellValue().trim();
                    yhzgx = DictTextToValusUtil.yhzgxnsb(yhzgx);
                } else {
                    log.info("===导入yhzgx为空===");
                }

                if (row.getCell(3) != null) {
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    lxfs = row.getCell(3).getStringCellValue().trim();
                } else {
                    log.info("===导入lxfs为空===");
                }

                if (row.getCell(6) != null) {
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    cpdj = row.getCell(6).getStringCellValue().trim();
                } else {
                    log.info("===导入 cpdj 为空===");
                }

                if (row.getCell(7) != null) {
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    cpsx = row.getCell(7).getStringCellValue().trim();
                } else {
                    log.info("===导入 cpsx 为空===");
                }

                if (row.getCell(8) != null) {
                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                    fpdj = row.getCell(8).getStringCellValue().trim();
                } else {
                    log.info("===导入 fpdj 为空===");
                }

                if (row.getCell(9) != null) {
                    row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                    fpsx = row.getCell(9).getStringCellValue().trim();
                } else {
                    log.info("===导入 fpsx 为空===");
                }

                if (row.getCell(10) != null) {
                    row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                    cplx = row.getCell(10).getStringCellValue().trim();
                } else {
                    log.info("===导入 cplx 为空===");
                }

                if (row.getCell(11) != null) {
                    row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                    sskhjl = row.getCell(11).getStringCellValue().trim();
                } else {
                    log.info("===导入 sskhjl 为空===");
                }

                CamsNhpjsxNsb camsNhpjsxNsb = new CamsNhpjsxNsb();
                camsNhpjsxNsb.setDrwgmc(zun);
                camsNhpjsxNsb.setKhmc(khmc);
                camsNhpjsxNsb.setZjhm(zjhm);
                camsNhpjsxNsb.setYhzgx(yhzgx);
                camsNhpjsxNsb.setSjhm(lxfs);
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
                camsNhpjsxNsb.setCplx(cplx);
                camsNhpjsxNsb.setSskhjl(sskhjl);
                boolean save = camsNhpjsxNsbService.save(camsNhpjsxNsb);
                if (save)
                    count++;
            }
            log.info("===导入成功{}条===",count);
        }
        return Result.ok("文件导入成功！导入数据 ");
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    @Autowired
    KhhmcxxMapper khhmcxxMapper;

    @Value(value = "${common.path.upload}")
    private String uploadpath;

    /**
     * 获取共同债务人
     */
    @RequestMapping("/jointbDebtor")
    public Result<?> jointbDebtor(CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxxSpjl) {
        if (camsZcsxGrpjsxxxSpjl != null && camsZcsxGrpjsxxxSpjl.getHhbm() != null)
            System.out.println(camsZcsxGrpjsxxxSpjl.getHhbm());
        List<Khhmcxx> khhmcxxes = khhmcxxMapper.jointbDebtor(camsZcsxGrpjsxxxSpjl.getHhbm());
        return Result.ok(khhmcxxes);
    }

    @RequestMapping("/infoCollection")
    public Result<?> infoCollection(String zjhm, String hhbm, HttpServletResponse response) throws Exception {
        FamerExportWord famerExportWord = service.getWord(zjhm, hhbm);
        WordUtils wordUtils = new WordUtils("infoCollect.docx", "infoCollect.ftl");
        //String responseFileName = StringUtils.join("信息采集表_", System.currentTimeMillis(), ".zip");
        //wordUtils.setZipHeader(response, responseFileName, wordUtils.utf8().name());

        File tempDirectory = wordUtils.generateDirectory(uploadpath + File.separator + "cjxx" + File.separator);
        File wordFile = new File(tempDirectory, StringUtils.join(
                famerExportWord.getName() + hhbm,
                "_",
                "等级评定信息采集表",
                ".docx"
        ));
        log.info("生成农户信用等级评定信息采集表 位置在 |{}|", wordFile.getAbsolutePath());
        try {
            wordUtils.generateDocxFile(new FileOutputStream(wordFile), famerExportWord);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("生成文件异常");
        } catch (TemplateException e) {
            e.printStackTrace();
            return Result.error("生成文件异常");
        }
        return Result.ok(famerExportWord.getName() + hhbm + "_等级评定信息采集表.docx");
    }


    @RequestMapping(value = "downloadAlreadyApprovalYearAudit")
    public void downloadAlreadyApprovalYearAudit(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        if (commonQW != null) {
            List<Vgrpjsxspjl> list = iVgrpjsxspjlService.list(commonQW);
            List<List<String>> dataList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    Vgrpjsxspjl camsZcsxGrpjsxxxSpjl = list.get(i);
                    List<String> colList = Lists.newArrayList();
                    /*
                     * 乡镇	村组名称	姓名	身份证号	联系电话	家庭人口	资产总额	负债	年收入	主要从事项目	信用等级	授信金额	审批时间
                     * */
                    String town = StringUtils.EMPTY;
                    if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getYjyxdybh())) {
                        String yj = iDictValueQuery.getyjValue(camsZcsxGrpjsxxxSpjl.getYjyxdybh());
                        //String yj = yjmap.get(camsZcsxGrpjsxxxSpjl.getYjyxdybh());
                        if (StringUtils.isNotBlank(yj))
                            town = yj;
                    }
                    colList.add(town);

                    String addr = StringUtils.EMPTY;
                    if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getEjyxdybh())) {
                        //String ej = ejmap.get(camsZcsxGrpjsxxxSpjl.getEjyxdybh());
                        String ej = iDictValueQuery.getejValue(camsZcsxGrpjsxxxSpjl.getEjyxdybh());
                        if (StringUtils.isNotBlank(ej))
                            addr += ej;
                    }

                    if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSjyxdybh())) {
                        //String sj = ejmap.get(camsZcsxGrpjsxxxSpjl.getSjyxdybh());
                        String sj = iDictValueQuery.getsjValue(camsZcsxGrpjsxxxSpjl.getSjyxdybh());
                        if (StringUtils.isNotBlank(sj))
                            addr += sj;
                    }
                    colList.add(addr);
                    colList.add(camsZcsxGrpjsxxxSpjl.getKhmc());
                    colList.add(camsZcsxGrpjsxxxSpjl.getZjhm());
                    colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSjhm()) ? camsZcsxGrpjsxxxSpjl.getSjhm() : StringUtils.EMPTY);
                    //家庭人数
                    if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getHhbm())) {
                        QueryWrapper queryWrapper = new QueryWrapper();
                        queryWrapper.eq("HHBM", camsZcsxGrpjsxxxSpjl.getHhbm());
                        int result = khhmcxxMapper.selectCount(queryWrapper).intValue();
                        colList.add(result > 1 ? String.valueOf(result) : String.valueOf(1));
                    } else {
                        colList.add("1");
                    }
                    /*
                     * 资产总额	负债	年收入	主要从事项目	信用等级	授信金额	审批时间
                     * */
                    if (camsZcsxGrpjsxxxSpjl.getJtjzc() != null) {
                        colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getJtjzc().toString()) ? camsZcsxGrpjsxxxSpjl.getJtjzc().toString() : StringUtils.EMPTY);
                    } else {
                        colList.add(StringUtils.EMPTY);
                    }

                    if (camsZcsxGrpjsxxxSpjl.getFzHj() != null) {
                        colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getFzHj().toString()) ? camsZcsxGrpjsxxxSpjl.getFzHj().toString() : StringUtils.EMPTY);
                    } else {
                        colList.add(StringUtils.EMPTY);
                    }

                    if (camsZcsxGrpjsxxxSpjl.getJtjsr() != null) {
                        colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getJtjsr().toString()) ? camsZcsxGrpjsxxxSpjl.getJtjsr().toString() : StringUtils.EMPTY);
                    } else {
                        colList.add(StringUtils.EMPTY);
                    }
                    //从事职业
                    String zszy = StringUtils.EMPTY;
                    if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getCszy())) {
                        //String zy = cszyMap.get(camsZcsxGrpjsxxxSpjl.getCszy());
                        String zy = iDictValueQuery.getCszyValue(camsZcsxGrpjsxxxSpjl.getCszy());
                        if (StringUtils.isNotBlank(zy))
                            zszy = zy;
                    }
                    colList.add(zszy);

                    String zzdj = StringUtils.EMPTY;
                    if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getZzpddj())) {
                        //String dj = pjMap.get(camsZcsxGrpjsxxxSpjl.getZzpddj());
                        String dj = iDictValueQuery.getPddjValue(camsZcsxGrpjsxxxSpjl.getZzpddj());
                        if (StringUtils.isNotBlank(dj))
                            zzdj = dj;
                    }
                    colList.add(zzdj);
                    if (camsZcsxGrpjsxxxSpjl.getZzsxed() != null) {
                        colList.add(StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getZzsxed().toString()) ? camsZcsxGrpjsxxxSpjl.getZzsxed().toString() : StringUtils.EMPTY);
                    } else {
                        colList.add(StringUtils.EMPTY);
                    }
                    if (camsZcsxGrpjsxxxSpjl.getUpdateTime() != null) {
                        colList.add(DateUtil.format(camsZcsxGrpjsxxxSpjl.getUpdateTime(), "yyyy-MM-dd"));
                    } else {
                        colList.add(StringUtils.EMPTY);
                    }

                    //
                    colList.add(StringUtils.EMPTY);
                    colList.add(StringUtils.EMPTY);
                    dataList.add(colList);
                }
            }

            super.alreadyApprovalYearAuditTableExport(request, response, "年审表", dataList);
        }
    }

    //按组导出年审表
    @RequestMapping(value = "downloadAlreadyApprovalYearAudit2")
    public void downloadAlreadyApprovalYearAudit2() throws Exception {
        //获取所有的村组
        List<Ejyxdygl> ejyxdygls = ejyxdyglMapper.selectList(null);
        List<Yjyxdygl> yjyxdygls = yjyxdyglMapper.selectList(null);
        List<Sjyxdygl> sjyxdygls = sjyxdyglMapper.selectList(null);
        List<HrBasOrganization> hrBasOrganizationList = hrBasOrganizationService.list(null);
        Map<String, String> yjyxdyMap = null;
        Map<String, String> ejyxdyMap = null;
        Map<String, String> hrMap = null;
        if (yjyxdygls != null && yjyxdygls.size() > 0) {
            yjyxdyMap = new HashMap<>();
            for (int i = 0; i < yjyxdygls.size(); i++) {
                yjyxdyMap.put(yjyxdygls.get(i).getDybh(), yjyxdygls.get(i).getDymc());
            }
        }
        if (ejyxdygls != null && ejyxdygls.size() > 0) {
            ejyxdyMap = new HashMap<>();
            for (int i = 0; i < ejyxdygls.size(); i++) {
                ejyxdyMap.put(ejyxdygls.get(i).getDybh(), ejyxdygls.get(i).getDymc());
            }
        }

        if (hrBasOrganizationList != null && hrBasOrganizationList.size() > 0) {
            hrMap = new HashMap<>();
            for (int i = 0; i < hrBasOrganizationList.size(); i++) {
                hrMap.put(hrBasOrganizationList.get(i).getZzbz(), hrBasOrganizationList.get(i).getZzjc());
            }
        }

        int n = sjyxdygls.size();
        for (int i = 0; i < n; i++) {
            //先创建目录
            Sjyxdygl sjyxdygl = sjyxdygls.get(i);
            String dybh = sjyxdygl.getDybh();
            String dymc = sjyxdygl.getDymc();
            String ejyxdybh = sjyxdygl.getEjyxdybh();
            String yjyxdybh = sjyxdygl.getYjyxdybh();
            String s = yjyxdyMap.get(yjyxdybh);
            String s1 = ejyxdyMap.get(ejyxdybh);
            String sszh = sjyxdygl.getSszh();
            String zzjc = hrMap.get(sszh);
            String path = uploadpath + File.separator + "nsb" + File.separator + s + File.separator + s1;
            if (!cn.hutool.core.io.FileUtil.isDirectory(new File(path))) {
                cn.hutool.core.io.FileUtil.mkdir(new File(path));
            }
            List<LyNewNsVO> byWgbh = service.getByWgbh(dybh);
            if (CollUtil.isNotEmpty(byWgbh)) {
                List<List<String>> dataList = Lists.newArrayList();

                int n2 = byWgbh.size();
                log.info("==={}新年审表导出{}条数据===", dymc, n2);
                for (int j = 0; j < n2; j++) {
                    LyNewNsVO lyNewNsVO = byWgbh.get(j);
                    List<String> colList = Lists.newArrayList();
                    if (StringUtils.isNotBlank(lyNewNsVO.getKhmc())) {
                        colList.add(lyNewNsVO.getKhmc());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getZjhm())) {
                        colList.add(lyNewNsVO.getZjhm());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getYhzgx())) {
                        String yhzgx = dictService.queryDictTextByKey("yhzgx", lyNewNsVO.getYhzgx());
                        colList.add(yhzgx);
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getLxfs())) {
                        colList.add(lyNewNsVO.getLxfs());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getZzpddj())) {
                        colList.add(lyNewNsVO.getZzpddj());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getZzsxed())) {
                        colList.add(lyNewNsVO.getZzsxed());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getCurr())) {
                        colList.add(lyNewNsVO.getCurr());
                    } else {
                        colList.add("");
                    }
                    if (StringUtils.isNotBlank(lyNewNsVO.getFreeze())) {
                        colList.add(lyNewNsVO.getFreeze());
                    } else {
                        colList.add("");
                    }

                    if (lyNewNsVO.getDkje() != null) {
                        colList.add(lyNewNsVO.getDkje().toString());
                    } else {
                        colList.add("");
                    }

                    if (lyNewNsVO.getDkye() != null) {
                        colList.add(lyNewNsVO.getDkye().toString());
                    } else {
                        colList.add("");
                    }

                    if (lyNewNsVO.getCknrpye() != null) {
                        colList.add(lyNewNsVO.getCknrpye().toString());
                    } else {
                        colList.add("");
                    }

                    if (lyNewNsVO.getBldkye() != null) {
                        colList.add(lyNewNsVO.getBldkye().toString());
                    } else {
                        colList.add("");
                    }

                    if (lyNewNsVO.getBwbldkye() != null) {
                        colList.add(lyNewNsVO.getBwbldkye().toString());
                    } else {
                        colList.add("");
                    }

					 /*if (StringUtils.isNotBlank(lyNewNsVO.getDkye())){
						 colList.add(lyNewNsVO.getDkye());
					 }else {
						 colList.add("");
					 }

					 if (StringUtils.isNotBlank(lyNewNsVO.getCknrpye())){
						 colList.add(lyNewNsVO.getCknrpye());
					 }else {
						 colList.add("");
					 }

					 if (StringUtils.isNotBlank(lyNewNsVO.getBldkye())){
						 colList.add(lyNewNsVO.getBldkye());
					 }else {
						 colList.add("");
					 }

					 if (StringUtils.isNotBlank(lyNewNsVO.getBwbldkye())){
						 colList.add(lyNewNsVO.getBwbldkye());
					 }else {
						 colList.add("");
					 }*/

                    if (StringUtils.isNotBlank(lyNewNsVO.getYqcs())) {
                        colList.add(lyNewNsVO.getYqcs());
                    } else {
                        colList.add("");
                    }

                    colList.add("");
                    colList.add("");
                    colList.add("");
                    colList.add("");
                    dataList.add(colList);
                }

                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("年审表");
                LyNewNsbTable lyNewNsb = new LyNewNsbTable(workbook, sheet);
                lyNewNsb.setTableName("浏阳农商银行" + zzjc + "农户评级授信年审表");
                lyNewNsb.setTableHeader();
                lyNewNsb.setRowTwoNam(s + s1 + dymc);
                lyNewNsb.setTableData(dataList);
                String[] strings = {"A", "B", "C", "D", "E"};
                lyNewNsb.setHSSFValidation(strings, 3, dataList.size() + 3, 4, 4);
                lyNewNsb.setHSSFValidation(strings, 3, dataList.size() + 3, 12, 12);
                lyNewNsb.setHSSFValidation(strings, 3, dataList.size() + 3, 14, 14);
                lyNewNsb.setTableFooter();
                FileOutputStream fileOutputStream = new FileOutputStream(path + File.separator + dymc + ".xlsx");
                workbook.write(fileOutputStream);
                workbook.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                log.info("==={}生成完毕===", dymc);

            } else {
                continue;
            }


        }

        log.info("===导出完毕===");
    }

    //按支行导出年审表
    @RequestMapping(value = "downloadAlreadyApprovalYearAuditZh")
    public void downloadAlreadyApprovalYearAuditZh() throws Exception {
        //获取所有的村组
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sjzzbz", "1");
        List<HrBasOrganization> hrBasOrganizationList = hrBasOrganizationService.list(queryWrapper);
        Map<String, String> hrMap = null;

        if (hrBasOrganizationList != null && hrBasOrganizationList.size() > 0) {
            hrMap = new HashMap<>();
            for (int i = 0; i < hrBasOrganizationList.size(); i++) {
                hrMap.put(hrBasOrganizationList.get(i).getZzbz(), hrBasOrganizationList.get(i).getZzjc());
            }
        }
        List<VSjyxdygl> vSjyxdygls = ivSjyxdyglService.list(null);
        Map<String, String> dyMap = null;

        if (CollUtil.isNotEmpty(vSjyxdygls)) {
            dyMap = new HashMap<>();
            for (int i = 0; i < vSjyxdygls.size(); i++) {
                VSjyxdygl vSjyxdygl = vSjyxdygls.get(i);
                dyMap.put(vSjyxdygl.getDybh(), vSjyxdygl.getYjyxdymc() + vSjyxdygl.getEjyxdymc() + vSjyxdygl.getDymc());
            }
        }

        int n = hrBasOrganizationList.size();
        for (int i = 0; i < n; i++) {
            HrBasOrganization hrBasOrganization = hrBasOrganizationList.get(i);
            String zzjc = hrBasOrganization.getZzjc();
            String path = uploadpath + File.separator + "nsbw" + File.separator + zzjc;
            if (!cn.hutool.core.io.FileUtil.isDirectory(new File(path))) {
                cn.hutool.core.io.FileUtil.mkdir(new File(path));
            }
            List<LyNewNsVO> bySszh = service.getBySszh(hrBasOrganization.getZzbz());
            if (CollUtil.isNotEmpty(bySszh)) {
                List<List<String>> dataList = Lists.newArrayList();
                int n2 = bySszh.size();
                log.info("==={}新年审表导出{}条数据===", zzjc, n2);
                for (int j = 0; j < n2; j++) {
                    LyNewNsVO lyNewNsVO = bySszh.get(j);
                    List<String> colList = Lists.newArrayList();
                    if (StringUtils.isNotBlank(lyNewNsVO.getKhmc())) {
                        colList.add(lyNewNsVO.getKhmc());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getZjhm())) {
                        colList.add(lyNewNsVO.getZjhm());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getYhzgx())) {
                        String yhzgx = dictService.queryDictTextByKey("yhzgx", lyNewNsVO.getYhzgx());
                        colList.add(yhzgx);
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getLxfs())) {
                        colList.add(lyNewNsVO.getLxfs());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getZzpddj())) {
                        colList.add(lyNewNsVO.getZzpddj());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getZzsxed())) {
                        colList.add(lyNewNsVO.getZzsxed());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getCurr())) {
                        colList.add(lyNewNsVO.getCurr());
                    } else {
                        colList.add("");
                    }
                    if (StringUtils.isNotBlank(lyNewNsVO.getFreeze())) {
                        colList.add(lyNewNsVO.getFreeze());
                    } else {
                        colList.add("");
                    }

                    if (lyNewNsVO.getDkje() != null) {
                        colList.add(lyNewNsVO.getDkje().toString());
                    } else {
                        colList.add("");
                    }

                    if (lyNewNsVO.getDkye() != null) {
                        colList.add(lyNewNsVO.getDkye().toString());
                    } else {
                        colList.add("");
                    }

                    if (lyNewNsVO.getCknrpye() != null) {
                        colList.add(lyNewNsVO.getCknrpye().toString());
                    } else {
                        colList.add("");
                    }

                    if (lyNewNsVO.getBldkye() != null) {
                        colList.add(lyNewNsVO.getBldkye().toString());
                    } else {
                        colList.add("");
                    }

                    if (lyNewNsVO.getBwbldkye() != null) {
                        colList.add(lyNewNsVO.getBwbldkye().toString());
                    } else {
                        colList.add("");
                    }

                    if (StringUtils.isNotBlank(lyNewNsVO.getYqcs())) {
                        colList.add(lyNewNsVO.getYqcs());
                    } else {
                        colList.add("");
                    }

                    colList.add("");
                    colList.add("");
                    colList.add("");
                    colList.add("");
                    if (StringUtils.isNotBlank(lyNewNsVO.getSszh())) {
                        String s = hrMap.get(lyNewNsVO.getSszh());
                        colList.add(s);
                    } else {
                        colList.add("");
                    }
                    if (StringUtils.isNotBlank(lyNewNsVO.getSsyxdy())) {
                        String s = dyMap.get(lyNewNsVO.getSsyxdy());
                        colList.add(s);
                    } else {
                        colList.add("");
                    }

                    dataList.add(colList);
                }
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("年审表");
                LyNewNsbZhTable lyNewNsb = new LyNewNsbZhTable(workbook, sheet);
                lyNewNsb.setTableName("浏阳农商银行" + zzjc + "农户评级授信年审表");
                lyNewNsb.setTableHeader();
                lyNewNsb.setRowTwoNam();
                lyNewNsb.setTableData(dataList);
                String[] strings = {"A", "B", "C", "D", "E"};
                lyNewNsb.setHSSFValidation(strings, 3, dataList.size() + 3, 4, 4);
                lyNewNsb.setHSSFValidation(strings, 3, dataList.size() + 3, 12, 12);
                lyNewNsb.setHSSFValidation(strings, 3, dataList.size() + 3, 14, 14);
                lyNewNsb.setTableFooter();
                FileOutputStream fileOutputStream = new FileOutputStream(path + File.separator + zzjc + ".xlsx");
                workbook.write(fileOutputStream);
                workbook.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                log.info("==={}生成完毕===", zzjc);
            }
        }

        log.info("===导出完毕===");
    }

    @Value(value = "${qxfk:dev}")
    private String qxfk;

    @GetMapping(value = "/batchQueryQxfk")
    @PermissionData(pageComponent = "xdgl/pjsxspjl/CamsZcsxGrpjsxxxSpjlList")
    public Result<?> batchQueryQxfk(Vgrpjsxspjl camsZcsxGrpjsxxxSpjl,
                                    HttpServletRequest req) {
        log.info("===开始批量七星风控查询===");
        QueryWrapper<Vgrpjsxspjl> queryWrapper = QueryGenerator.initQueryWrapper(camsZcsxGrpjsxxxSpjl, req.getParameterMap());

        if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSpid())) {
            String spid = camsZcsxGrpjsxxxSpjl.getSpid();
            log.info("===本次是多选批量查询{}===",spid);
             String[] split = spid.split(",");
             queryWrapper.in("spid",split);
        } else {
            if (StringUtils.isBlank(camsZcsxGrpjsxxxSpjl.getSszh()) || StringUtils.isBlank(camsZcsxGrpjsxxxSpjl.getQydm())) {
                return Result.error("批量查询必须选择支行和所属网格");
            }
        }
        queryWrapper.ne("xdxtsxje", 2);
        List<Vgrpjsxspjl> list = iVgrpjsxspjlService.list(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            int size = list.size();
            log.info("===批量查询七星风控，本次查询数据{}===", size);
            for (int i = 0; i < size; i++) {
                String zjhm = null;
                String khmc = null;
                String sjhm = null;
                Vgrpjsxspjl vgrpjsxspjl = list.get(i);
                if (StringUtils.isNotBlank(vgrpjsxspjl.getZjhm())) {
                    zjhm = vgrpjsxspjl.getZjhm();
                    if (IdcardUtil.isValidCard(zjhm)) {
                        int ageByIdCard = IdcardUtil.getAgeByIdCard(zjhm);
                        if (ageByIdCard < 18)
                            continue;
                        if (ageByIdCard > 60)
                            continue;
                        ;
                    } else {
                        continue;
                    }
                } else {
                    log.info("==={}证件号码为空===", i);
                    continue;
                }
                if (StringUtils.isNotBlank(vgrpjsxspjl.getKhmc())) {
                    khmc = vgrpjsxspjl.getKhmc();
                } else {
                    log.info("==={}客户姓名为空===", i);
                    continue;
                }
                if (StringUtils.isNotBlank(vgrpjsxspjl.getSjhm())) {
                    sjhm = vgrpjsxspjl.getSjhm();
                } else {
                    sjhm = "13888888888";
                }
                if (vgrpjsxspjl.getBldkye() != null && vgrpjsxspjl.getBldkye().compareTo(new BigDecimal(0)) > 0)
                    continue;
                if (vgrpjsxspjl.getBwbldkye() != null && vgrpjsxspjl.getBwbldkye().compareTo(new BigDecimal(0)) > 0)
                    continue;
                if (vgrpjsxspjl.getYqcs() != null && vgrpjsxspjl.getYqcs() > 3)
                    continue;
                qxfkPdfImgService.queryQxfk(zjhm, khmc, sjhm, getWorkNo(), qxfk);
            }
        } else {
            log.info("当前查询数据为0");
        }
        log.info("===批量风控查询完成===");
        return Result.ok();
    }

    @RequestMapping("/updateKhmc")
    public Result<?> updateKhmc(@RequestBody CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxxSpjl) {
        if (StringUtils.isBlank(camsZcsxGrpjsxxxSpjl.getZjhm()) || StringUtils.isBlank(camsZcsxGrpjsxxxSpjl.getKhmc())) {
            return Result.error("证件号码或者客户名称不能为空");
        }

        UpdateWrapper<CamsZcsxGrpjsxxxSpjl> camsZcsxGrpjsxxxSpjlUpdateWrapper = new UpdateWrapper<>();
        camsZcsxGrpjsxxxSpjlUpdateWrapper.set("khmc", camsZcsxGrpjsxxxSpjl.getKhmc()).eq("zjhm", camsZcsxGrpjsxxxSpjl.getZjhm());
        boolean update = service.update(null, camsZcsxGrpjsxxxSpjlUpdateWrapper);
        if (update) {
            UpdateWrapper<Khhmcxx> khhmcxxUpdateWrapper = new UpdateWrapper<>();
            khhmcxxUpdateWrapper.set("khmc", camsZcsxGrpjsxxxSpjl.getKhmc()).eq("zjhm", camsZcsxGrpjsxxxSpjl.getZjhm());
            khhmcxxMapper.update(null, khhmcxxUpdateWrapper);

            UpdateWrapper<Nhxq> nhxqUpdateWrapper = new UpdateWrapper<>();
            nhxqUpdateWrapper.set("khmc", camsZcsxGrpjsxxxSpjl.getKhmc()).eq("zjhm", camsZcsxGrpjsxxxSpjl.getZjhm());
            nhxqService.update(null, nhxqUpdateWrapper);
            return Result.ok();
        }
        return Result.error("修改客户名称失败");
    }

    @RequestMapping("/slshnkd")
    private Result<?> slshnkd(@RequestBody Vgrpjsxspjl camsZcsxGrpjsxxxSpjl){
        Object hnkdIsUse = redisUtil.get("hnkdIsUse");
        if (hnkdIsUse != null){
            if ("1".equals(hnkdIsUse.toString())){

            }
        }
        redisUtil.set("hnkdIsUse","1");
        for (int i = 0; i < 2000; i++) {
            //todo 模拟一下
            try{
                Thread.sleep(6000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {

            }
        }
//        if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSpid())){
//            CamsZcsxGrpjsxxxSpjl byId = service.getById(camsZcsxGrpjsxxxSpjl.getSpid());
//            if (byId != null){
//                //todo 模拟一下
//                try{
//                    Thread.sleep(6000);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }

        return Result.ok();
    }
}
