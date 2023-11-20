package org.cmms.modules.ywgl.cdkfx.xzbldkmx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.utils.PageUtil;
import org.cmms.config.MybatisPlusConfig;
import org.cmms.modules.system.controller.IdentEncryptionController;
import org.cmms.modules.ywgl.cdkfx.xzbldkmx.entity.MidDmpmDkyebmxqktj;
import org.cmms.modules.ywgl.cdkfx.xzbldkmx.service.IMidDmpmDkyebmxqktjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 新增不良贷款明细
 * @Author: jeecg-boot
 * @Date: 2021-06-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "新增不良贷款明细")
@RestController
@RequestMapping("/xzbldkmx/midDmpmDkyebmxqktj")
public class MidDmpmDkyebmxqktjController extends JeecgController<MidDmpmDkyebmxqktj, IMidDmpmDkyebmxqktjService> {
    @Autowired
    private IMidDmpmDkyebmxqktjService midDmpmDkyebmxqktjService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "新增不良贷款明细-分页列表查询")
    @ApiOperation(value = "新增不良贷款明细-分页列表查询", notes = "新增不良贷款明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(MidDmpmDkyebmxqktj midDmpmDkyebmxqktj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<MidDmpmDkyebmxqktj> queryWrapper = QueryGenerator.initQueryWrapper(midDmpmDkyebmxqktj, req.getParameterMap());
        if ("true".equals(sfdsjpt)) {
			if (DateUtil.getMonthBeginDay(new Date()).equals(midDmpmDkyebmxqktj.getTjyf())) {
				queryWrapper.isNotNull("fiscal_date");
			} else {
				String tjyf = DateUtil.format(midDmpmDkyebmxqktj.getTjyf(), "yyyyMMdd");
				log.info("新增不良贷款明细-分页列表查询-统计月份：" + tjyf);
				queryWrapper.eq("fiscal_date", tjyf);
			}
		}
        queryWrapper.eq("shbz", 0).gt("blbz", 0);
        IPage pageList = PageUtil.toPage(IMidDmpmDkyebmxqktjService.class, midDmpmDkyebmxqktjService, pageNo, pageSize, queryWrapper, "jgdm", "custid");
        return Result.ok(pageList);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param midDmpmDkyebmxqktj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request,
                                  MidDmpmDkyebmxqktj midDmpmDkyebmxqktj,
                                  String begindayString,
                                  String enddayString) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        // Step.1 组装查询条件
        QueryWrapper<MidDmpmDkyebmxqktj> queryWrapper = QueryGenerator.initQueryWrapper(midDmpmDkyebmxqktj, request.getParameterMap());
        try {
            if (begindayString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String[] split = begindayString.split(",");
                queryWrapper.between("BEGINDAY", sdf.parse(split[0]), sdf.parse(split[1]));
            }
            if (enddayString != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String[] split = enddayString.split(",");
                queryWrapper.between("ENDDAY", sdf.parse(split[0]), sdf.parse(split[1]));
            }
        } catch (ParseException p) {
            p.printStackTrace();
        }
        queryWrapper.eq("shbz", 0).gt("blbz", 0).orderByDesc("jgdm");
        List<MidDmpmDkyebmxqktj> pageList = midDmpmDkyebmxqktjService.list(queryWrapper);
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new IdentEncryptionController());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "新增不良贷款明细");
        mv.addObject(NormalExcelConstants.CLASS, MidDmpmDkyebmxqktj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("新增不良贷款明细", "导出人:" + sysUser.getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

}
