package org.cmms.modules.ywgl.cdkfx.xzblkkmx.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.cdkfx.xzblkkmx.entity.ErpJxkhKhjlxzblkk;
import org.cmms.modules.ywgl.cdkfx.xzblkkmx.service.IErpJxkhKhjlxzblkkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 新增不良扣款明细
 * @Author: jeecg-boot
 * @Date: 2021-06-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "新增不良扣款明细")
@RestController
@RequestMapping("/xzblkkmx/erpJxkhKhjlxzblkk")
public class ErpJxkhKhjlxzblkkController extends JeecgController<ErpJxkhKhjlxzblkk, IErpJxkhKhjlxzblkkService> {
    @Autowired
    private IErpJxkhKhjlxzblkkService erpJxkhKhjlxzblkkService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param erpJxkhKhjlxzblkkVo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "新增不良扣款明细-分页列表查询")
    @ApiOperation(value = "新增不良扣款明细-分页列表查询", notes = "新增不良扣款明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ErpJxkhKhjlxzblkk erpJxkhKhjlxzblkkVo,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ErpJxkhKhjlxzblkk> queryWrapper = QueryGenerator.initQueryWrapper(erpJxkhKhjlxzblkkVo, req.getParameterMap());
        IPage pageList = PageUtil.toPage(IErpJxkhKhjlxzblkkService.class, erpJxkhKhjlxzblkkService, pageNo, pageSize, queryWrapper, "jgdm", "yggh");
        return Result.ok(pageList);
    }

	/**
	 * 数据提取
	 *
	 * @param tjyf
	 * @return
	 */
	@RequestMapping(value = "/count")
    public Result<?> count(String tjyf) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        tjyf = tjyf.replaceAll("-", "");
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> parm = new HashMap<>();
            parm.put("ld_gzyf", tjyf);
            parm.put("ls_lrczy", "system");
            parm.put("etl_task","kiss.domain.application.cdkyw.proc_ywgl_cdkfx_jxkh_khjlxzblkk");
            // count_ywgl_cdkfx_jxkh_khjlxzblkk
            boolean falg = EtlUtil.callEtl("cdkyw_common_init", parm, 20);
            result.setSuccess(falg);
        } else {
            try {
                erpJxkhKhjlxzblkkService.pJxkhKhjlxzblkk(tjyf, sysUser.getRealname());
                result.setSuccess(true);
            } catch (Throwable e) {
                e.printStackTrace();
                result.setSuccess(false);
                return Result.error("提取失败：请查看系统监控日志信息");
            }
        }
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param erpJxkhKhjlxzblkk
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ErpJxkhKhjlxzblkk erpJxkhKhjlxzblkk) {
        return super.exportXls(request, erpJxkhKhjlxzblkk, ErpJxkhKhjlxzblkk.class, "新增不良扣款明细");
    }

}
