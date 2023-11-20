package org.cmms.modules.ywgl.dkyw.lsdksjgl.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.Lsdksjgl;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.entity.LsdksjglVo;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.service.ILsdksjglService;
import org.cmms.modules.ywgl.dkyw.lsdksjgl.verify.LsdksjglImportVerify;

import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 历史贷款数据管理
 * @Author: jeecg-boot
 * @Date: 2021-09-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "历史贷款数据管理")
@RestController
@RequestMapping("/lsdksjgl/lsdksjgl")
public class LsdksjglController extends JeecgController<Lsdksjgl, ILsdksjglService> {
    @Autowired
    private ILsdksjglService lsdksjglService;
    @Autowired
    private LsdksjglImportVerify LsdksjglImportVerify;
    @Autowired
    private IDkzdkbService dkzdkbService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;  //获取组织标识
    @Autowired
    private IHrBasStaffService hrBasStaffService;

    /**
     * 分页列表查询
     *
     * @param lsdksjgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "历史贷款数据管理-分页列表查询")
    @ApiOperation(value = "历史贷款数据管理-分页列表查询", notes = "历史贷款数据管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Lsdksjgl lsdksjgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Lsdksjgl> queryWrapper = QueryGenerator.initQueryWrapper(lsdksjgl, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(ILsdksjglService.class, lsdksjglService, pageNo, pageSize, queryWrapper, "acct_no");
        return Result.ok(pageList);
    }

    @GetMapping(value = "/getYgxx")
    public Result<?> getYgxx(Lsdksjgl lsdksjgl) {
        QueryWrapper<HrBasStaff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("khjlbh", lsdksjgl.getCustManagerId());
        HrBasStaff one = hrBasStaffService.getOne(queryWrapper);
        return Result.ok(one);
    }

    /**
     * 添加
     *
     * @param lsdksjgl
     * @return
     */
    @AutoLog(value = "历史贷款数据管理-添加")
    @ApiOperation(value = "历史贷款数据管理-添加", notes = "历史贷款数据管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Lsdksjgl lsdksjgl) {
        try {
            QueryWrapper<Lsdksjgl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("acct_no", lsdksjgl.getAcctNo());
            Lsdksjgl lsdksj = lsdksjglService.getOne(queryWrapper);
            if (lsdksj != null) {
                return Result.error("数据已经存在！");
            }
            QueryWrapper<Dkzdkb> queryWrapperDkzb = new QueryWrapper<>();
            queryWrapperDkzb.eq("acct_no", lsdksjgl.getAcctNo());
            Dkzdkb dkyeb = dkzdkbService.getOne(queryWrapperDkzb);
            if (dkyeb != null) {
                return Result.error("贷款余额表中存在该笔贷款！");
            }
            QueryWrapper<HrBasOrganization> hrBasOrganizationQueryWrapper = new QueryWrapper<>();
            hrBasOrganizationQueryWrapper.eq("ywjgdm", lsdksjgl.getOrg());
            HrBasOrganization hrBasOrganizationServiceOne = hrBasOrganizationService.getOne(hrBasOrganizationQueryWrapper);
            if (hrBasOrganizationServiceOne == null) {
                return Result.error("未找到机构代码对应的机构信息！");
            }
            lsdksjgl.setFinInsName(hrBasOrganizationServiceOne.getZzmc());
            lsdksjgl.setBalance(new BigDecimal(0));
            lsdksjgl.setLrbz(1);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            lsdksjgl.setLrczy(sysUser.getUsername());
            lsdksjgl.setLrsj(new Timestamp(System.currentTimeMillis()));
            lsdksjgl.setLrbz(1);
            lsdksjglService.save(lsdksjgl);
            return Result.ok("添加成功！");
        } catch (Throwable tx) {
            return Result.error("系统错误！" + tx.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param lsdksjgl
     * @return
     */
    @AutoLog(value = "历史贷款数据管理-编辑")
    @ApiOperation(value = "历史贷款数据管理-编辑", notes = "历史贷款数据管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Lsdksjgl lsdksjgl) {
        QueryWrapper<HrBasOrganization> hrBasOrganizationQueryWrapper = new QueryWrapper<>();
        hrBasOrganizationQueryWrapper.eq("ywjgdm", lsdksjgl.getOrg());
        HrBasOrganization hrBasOrganizationServiceOne = hrBasOrganizationService.getOne(hrBasOrganizationQueryWrapper);
        if (hrBasOrganizationServiceOne == null) {
            return Result.error("未找到机构代码对应的机构信息！");
        }
        lsdksjgl.setFinInsName(hrBasOrganizationServiceOne.getZzmc());
        lsdksjgl.setLrbz(2);
        lsdksjgl.setLrczy(getLoginUser().getUsername());
        lsdksjgl.setXgsj(new Timestamp(System.currentTimeMillis()));
        QueryWrapper<Lsdksjgl> queryWrapper = new QueryWrapper<Lsdksjgl>();
        queryWrapper.eq("acct_No", lsdksjgl.getAcctNo());
        lsdksjgl.setAcctNo(null);
        lsdksjglService.update(lsdksjgl, queryWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "历史贷款数据管理-通过id删除")
    @ApiOperation(value = "历史贷款数据管理-通过id删除", notes = "历史贷款数据管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("acctNo") String acctNo) {
        QueryWrapper<Lsdksjgl> queryWrapper = new QueryWrapper<Lsdksjgl>();
        queryWrapper.eq("acct_No", acctNo);
        lsdksjglService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "历史贷款数据管理-批量删除")
    @ApiOperation(value = "历史贷款数据管理-批量删除", notes = "历史贷款数据管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.lsdksjglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "历史贷款数据管理-通过id查询")
    @ApiOperation(value = "历史贷款数据管理-通过id查询", notes = "历史贷款数据管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Lsdksjgl lsdksjgl = lsdksjglService.getById(id);
        return Result.ok(lsdksjgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param lsdksjgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Lsdksjgl lsdksjgl) {
        return super.exportXls(request, lsdksjgl, Lsdksjgl.class, "历史贷款数据管理");
    }


    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "历史贷款数据管理导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, LsdksjglVo.class);
        ExportParams exportParams = new ExportParams("历史贷款数据管理导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;

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
        return super.importExcelByTemplate(jsonObject, request, response, Lsdksjgl.class,LsdksjglVo.class, LsdksjglImportVerify);
    }


}
