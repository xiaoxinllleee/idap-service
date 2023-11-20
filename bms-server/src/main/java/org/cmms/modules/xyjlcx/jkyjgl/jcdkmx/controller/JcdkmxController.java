package org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.entity.Jcdkmx;
import org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.mapper.JcdkmxMapper;
import org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.service.IJcdkmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 交叉贷款明细
 * @Author: jeecg-boot
 * @Date: 2021-08-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "交叉贷款明细")
@RestController
@RequestMapping("/jcdkmx/jcdkmx")
public class JcdkmxController extends JeecgController<Jcdkmx, IJcdkmxService> {
    @Autowired
    private IJcdkmxService jcdkmxService;
    @Autowired
    private ISysDictService iSysDictService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param jcdkmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "交叉贷款明细-分页列表查询")
    @ApiOperation(value = "交叉贷款明细-分页列表查询", notes = "交叉贷款明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Jcdkmx jcdkmx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Jcdkmx>> result = new Result<IPage<Jcdkmx>>();
        QueryWrapper<Jcdkmx> queryWrapper = QueryGenerator.initQueryWrapper(jcdkmx, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IJcdkmxService.class, jcdkmxService, pageNo, pageSize, queryWrapper, "zjhm", "jczjhm");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 交叉贷款明细 / 提取
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public Result<?> init() {
		Result result = new Result<>();
		String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC", "VALUE", "CODE", "101001");
        if ("true".equalsIgnoreCase(sfdsjpt)) {
			// `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            HashMap<String, String> params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_jcdk");
			boolean completionSignal = EtlUtil.callEtl("zx_common_init", params, 20);
			result.setSuccess(completionSignal);
		} else {
			try {
				jcdkmxService.pJcdkmx();
				result.setSuccess(true);
				return result;
			} catch (Throwable e) {
				log.error("信用记录查询 / 交叉贷款明细 / 提取失败！"+e.getMessage());
				result.setSuccess(false);
			}
		}
		return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jcdkmx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Jcdkmx jcdkmx) {
        return super.exportXls(request, jcdkmx, Jcdkmx.class, "交叉贷款明细");
    }

}
