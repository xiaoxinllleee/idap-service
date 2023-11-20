package org.cmms.modules.khxxgl.khflgl.nhxq.controller;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.DictModel;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;

import java.util.Date;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.activiti.entity.ActHmdSpls;
import org.cmms.modules.activiti.service.IActHmdSplsService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.PjsxHmd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.PjsxZhsj;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.PjsxHmdMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IPjsxHmdService;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysRoleService;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.tjfx.jcsjgl.cssz.entity.TjfxCssz;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
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
 * @Description: 评级授信黑名单
 * @Author: jeecg-boot
 * @Date: 2023-11-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "评级授信黑名单")
@RestController
@RequestMapping("/pjsx/pjsxHmd")
public class PjsxHmdController extends JeecgController<PjsxHmd, IPjsxHmdService> {
    @Autowired
    private IPjsxHmdService pjsxHmdService;
    @Autowired
    ITjfxCsszService tjfxCsszService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysUserService sysUserService;
    /**
     * 分页列表查询
     *
     * @param pjsxHmd
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "评级授信黑名单-分页列表查询")
    @ApiOperation(value = "评级授信黑名单-分页列表查询", notes = "评级授信黑名单-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(PjsxHmd pjsxHmd,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String rdzh = null;
        if (StringUtils.isNotBlank(pjsxHmd.getRdzh())) {
            rdzh = pjsxHmd.getRdzh();
            pjsxHmd.setRdzh(null);
        }
        QueryWrapper<PjsxHmd> queryWrapper = QueryGenerator.initQueryWrapper(pjsxHmd, req.getParameterMap());

        LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "pjsxhmd");
        List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
        boolean b = list.stream().anyMatch(x -> getWorkNo().equals(x.getCsz()));
        if (b) {
            if (StringUtils.isNotBlank(rdzh)) {
                queryWrapper.eq("rdzh", rdzh).or().like("rdzh", rdzh);
            }
        } else {
            queryWrapper.eq("rdzh", getLoginUser().getOrgCode()).or().like("rdzh", getLoginUser().getOrgCode());
        }
        queryWrapper.orderByDesc("pdsj");

        Page<PjsxHmd> page = new Page<PjsxHmd>(pageNo, pageSize);
        IPage<PjsxHmd> pageList = pjsxHmdService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @RequestMapping("/spr")
    public Result<?> spr() {
        LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "pjsxhmd");
        List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
        return Result.ok(list);
    }

    @RequestMapping("/sprall")
    public Result<?> sprall() {
        JSONObject jsonObject = new JSONObject();
        LambdaQueryWrapper<TjfxCssz> tbTjfxCsszLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tbTjfxCsszLambdaQueryWrapper.eq(TjfxCssz::getCsmc, "pjsxhmd");
        List<TjfxCssz> list = tjfxCsszService.list(tbTjfxCsszLambdaQueryWrapper);
        for (int i = 0; i < list.size(); i++) {
            String str = "";
            TjfxCssz tjfxCssz = list.get(i);
            if (StringUtils.isNotBlank(tjfxCssz.getCsz())){
                String s = sysDictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", tjfxCssz.getCsz());

                if (i == list.size() - 1) {
                    str += tjfxCssz.getCsz() + " - " + s;
                } else {
                    str += tjfxCssz.getCsz() + " - " + s+ ", ";
                }
            }
            jsonObject.put("zhspr",str);
        }


        String fxjlid = null;
        String zhhzid = null;
        Object o1 = redisUtil.get("fxjlid");
        if (o1 != null) {
            fxjlid = o1.toString();
        } else {
            LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SysRole::getRoleName, "风险经理");
            SysRole one = sysRoleService.getOne(lambdaQueryWrapper);
            if (one != null && StringUtils.isNotBlank(one.getId())) {
                redisUtil.set("fxjlid", one.getId());
                fxjlid = one.getId();
            }
        }
        Object o2 = redisUtil.get("zhhzid");
        if (o2 != null) {
            zhhzid = o2.toString();
        } else {
            LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SysRole::getRoleName, "支行行长");
            SysRole one = sysRoleService.getOne(lambdaQueryWrapper);
            if (one != null && StringUtils.isNotBlank(one.getId())) {
                redisUtil.set("zhhzid", one.getId());
                zhhzid = one.getId();
            }
        }

        if (StringUtils.isNotBlank(fxjlid)) {
            String string = getString(fxjlid);
            jsonObject.put("fxjl", string);
        }

        if (StringUtils.isNotBlank(zhhzid)) {
            String string = getString(zhhzid);
            jsonObject.put("zhhz", string);
        }

        return Result.ok(jsonObject);
    }

    public String getString(String roleId) {
        if (StringUtils.isNotBlank(roleId)) {
            LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
            sysUserLambdaQueryWrapper.eq(SysUser::getOrgCode, getLoginUser().getOrgCode());
            sysUserLambdaQueryWrapper.like(SysUser::getRoles, roleId);
            List<SysUser> sysUsers = sysUserService.list(sysUserLambdaQueryWrapper);

            if (CollUtil.isNotEmpty(sysUsers)) {
                String s = "";
                for (int i = 0; i < sysUsers.size(); i++) {
                    SysUser sysUser = sysUsers.get(i);
                    if (i == sysUsers.size() - 1) {
                        s += sysUser.getWorkNo() + " - " + sysUser.getRealname();
                    } else {
                        s += sysUser.getWorkNo() + " - " + sysUser.getRealname() + " , ";
                    }
                }
                return s;
            }
        }
        return null;
    }

    @Autowired
    IActHmdSplsService actHmdSplsService;

    @RequestMapping("/spls")
    public Result<?> spls(ActHmdSpls actHmdSpls,
                          @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                          HttpServletRequest req) {
        if (StringUtils.isBlank(actHmdSpls.getZjhm())) {
            return Result.error("身份证号码不能为空！");
        }
        QueryWrapper<ActHmdSpls> queryWrapper = QueryGenerator.initQueryWrapper(actHmdSpls, req.getParameterMap());
        Page<ActHmdSpls> page = new Page<ActHmdSpls>(pageNo, pageSize);
        IPage<ActHmdSpls> pageList = actHmdSplsService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    /**
     * 添加
     *
     * @param pjsxHmd
     * @return
     */
    @AutoLog(value = "评级授信黑名单-添加")
    @ApiOperation(value = "评级授信黑名单-添加", notes = "评级授信黑名单-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody PjsxHmd pjsxHmd) {
        pjsxHmd.setCreateTime(new Date());
        pjsxHmd.setCreator(getWorkNo());
        pjsxHmd.setUpdateTime(new Date());
        pjsxHmd.setUpdator(getWorkNo());
        pjsxHmd.setRdzh(getLoginUser().getOrgCode());
        pjsxHmd.setStatus("3");
        pjsxHmdService.save(pjsxHmd);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param pjsxHmd
     * @return
     */
    @AutoLog(value = "评级授信黑名单-编辑")
    @ApiOperation(value = "评级授信黑名单-编辑", notes = "评级授信黑名单-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody PjsxHmd pjsxHmd) {
        pjsxHmd.setUpdateTime(new Date());
        pjsxHmd.setUpdator(getWorkNo());
        if (StringUtils.isNotBlank(pjsxHmd.getStatus()) && "2".equals(pjsxHmd.getStatus())){
            pjsxHmd.setStatus("3");
        }
        pjsxHmdService.updateById(pjsxHmd);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评级授信黑名单-通过id删除")
    @ApiOperation(value = "评级授信黑名单-通过id删除", notes = "评级授信黑名单-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        pjsxHmdService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "评级授信黑名单-批量删除")
    @ApiOperation(value = "评级授信黑名单-批量删除", notes = "评级授信黑名单-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.pjsxHmdService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "评级授信黑名单-通过id查询")
    @ApiOperation(value = "评级授信黑名单-通过id查询", notes = "评级授信黑名单-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        PjsxHmd pjsxHmd = pjsxHmdService.getById(id);
        return Result.ok(pjsxHmd);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param pjsxHmd
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PjsxHmd pjsxHmd) {
        return super.exportXls(request, pjsxHmd, PjsxHmd.class, "评级授信黑名单");
    }

    @Autowired
    private ISysDictService sysDictService;

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
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
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
                    List<DictModel> pjsxZhsjs = sysDictService.queryDictItemsByCode("pjsx_hmd_rdly");

                    Map<String, String> map = pjsxZhsjs.stream().collect(Collectors.toMap
                            (DictModel::getText, DictModel::getValue, (value1, value2) -> value1));


                    for (int j = 1; j <= lastRowNum; j++) {

                        try {
                            String regex = "[\\u4e00-\\u9fa5]+";
                            Row row = sheet.getRow(j);
                            short lastCellNum = 5;
                            PjsxHmd pjsxHmd = null;

                            String zjhm = null;
                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                                zjhm = row.getCell(2).getStringCellValue().trim();

                                if (IdcardUtil.isValidCard(zjhm)) {

                                    LambdaQueryWrapper<PjsxHmd> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                                    lambdaQueryWrapper.eq(PjsxHmd::getZjhm, zjhm);
                                    PjsxHmd one = service.getOne(lambdaQueryWrapper, false);
                                    if (one == null) {
                                        pjsxHmd = new PjsxHmd();
                                    } else {
                                        pjsxHmd = one;
                                    }
                                    pjsxHmd.setZjhm(zjhm);

                                } else {
                                    row.createCell(lastCellNum).setCellValue("身份证号码错误！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("身份证号码不能为空！");
                                continue;
                            }


                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                                String khmc = row.getCell(1).getStringCellValue().trim();
                                if (!khmc.matches(regex)) {
                                    row.createCell(lastCellNum).setCellValue("姓名只能为中文！");
                                    continue;
                                }

                                if (khmc.length() > 1 && khmc.length() < 6) {
                                    pjsxHmd.setKhmc(khmc);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("姓名大于等于2个汉字，小于等于5个汉字！");
                                    continue;
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("姓名不能为空！");
                                continue;
                            }


                            if (row.getCell(0) != null && DateUtil.isCellDateFormatted(row.getCell(0))) {
                                Date dateCellValue = row.getCell(0).getDateCellValue();
                                if (dateCellValue != null) {
                                    pjsxHmd.setPdsj(dateCellValue);
                                } else {
                                    row.createCell(lastCellNum).setCellValue("认定时间格式错误！");
                                    continue;
                                }
                            } else {
                                System.out.println("111111111111");
                                pjsxHmd.setPdsj(new Date());
                            }


                            if (row.getCell(3) != null) {
                                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                                String rdly = row.getCell(3).getStringCellValue().trim();

                                String s = map.get(rdly);
                                if (StringUtils.isBlank(s)) {
                                    row.createCell(lastCellNum).setCellValue("认定理由与字典值不符！");
                                    continue;
                                } else {
                                    pjsxHmd.setRdly(s);
                                }
                            } else {
                                row.createCell(lastCellNum).setCellValue("认定理由不能为空！");
                                continue;
                            }


                            if (row.getCell(4) != null) {
                                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                String rdzh = row.getCell(4).getStringCellValue().trim();

                                String[] split = rdzh.split("/");
                                String fh = "";
                                String jg = ",";
                                for (int i = 0; i < split.length; i++) {
                                    String s = split[i];
                                    String s1 = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzbz", "zzjc", s);
                                    if (StringUtils.isBlank(s1)) {
                                        fh += s + " 支行名称错误。";
                                    } else {
                                        jg += s1 + ",";
                                    }
                                }
                                if (StringUtils.isNotBlank(fh)) {
                                    row.createCell(lastCellNum).setCellValue(fh + "！");
                                    continue;
                                } else {
                                    pjsxHmd.setRdzh(jg);
                                }

                            }


                            if (StringUtils.isBlank(pjsxHmd.getId())) {
                                pjsxHmd.setCreateTime(new Date());
                                pjsxHmd.setCreator(getWorkNo());
                                pjsxHmd.setUpdateTime(new Date());
                                pjsxHmd.setUpdator(getWorkNo());
                                pjsxHmd.setStatus("3");
                                service.save(pjsxHmd);
                            } else {
                                pjsxHmd.setUpdateTime(new Date());
                                pjsxHmd.setUpdator(getWorkNo());
                                service.updateById(pjsxHmd);
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
                String fileName = "黑名单导入结果" + System.currentTimeMillis() + ".xlsx";
                if (isExcel2003) {
                    fileName = "黑名单导入结果" + System.currentTimeMillis() + ".xls";
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


    @RequestMapping("/updateSpls")
    public Result<?> updateSpls(PjsxHmd pjsxHmd){
        //通过还是拒绝
        if (pjsxHmd.getTy().equals("1")){
            if ("3".equals(pjsxHmd.getStatus()) || "4".equals(pjsxHmd.getStatus())){
                Integer integer = Integer.valueOf(pjsxHmd.getStatus())+1;
                service.updateStatus(pjsxHmd.getId(),integer+"");
            }else if ("5".equals(pjsxHmd.getStatus())){
                service.updateStatus(pjsxHmd.getId(),"1");
            }
        }else {
            service.updateStatus(pjsxHmd.getId(),"2");
        }

        ActHmdSpls actHmdSpls = new ActHmdSpls();
        actHmdSpls.setUserid(getLoginUser().getId());
        actHmdSpls.setYggh(getWorkNo());
        actHmdSpls.setSpyj(pjsxHmd.getYj());
        actHmdSpls.setZjhm(pjsxHmd.getZjhm());
        actHmdSpls.setCreateBy(getWorkNo());
        actHmdSpls.setCreateTime(new Date());
        actHmdSpls.setStatus(pjsxHmd.getTy());
        actHmdSplsService.save(actHmdSpls);
        return Result.ok();
    }

}
