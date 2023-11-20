package org.cmms.modules.xyjlcx.jkyjgl.wglrmx.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.jkyjgl.wglrmx.entity.Wglrmx;
import org.cmms.modules.xyjlcx.jkyjgl.wglrmx.mapper.WglrmxMapper;
import org.cmms.modules.xyjlcx.jkyjgl.wglrmx.service.IWglrmxService;
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
 * @Description: 无关联人明细
 * @Author: jeecg-boot
 * @Date: 2021-08-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "无关联人明细")
@RestController
@RequestMapping("/wglrmx/wglrmx")
public class WglrmxController extends JeecgController<Wglrmx, IWglrmxService> {
    @Autowired
    private IWglrmxService wglrmxService;
	@Autowired
	private ISysDictService iSysDictService;
    @Autowired(required = false)
    private WglrmxMapper wglrmxMapper;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param wglrmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "无关联人明细-分页列表查询")
    @ApiOperation(value = "无关联人明细-分页列表查询", notes = "无关联人明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Wglrmx wglrmx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Wglrmx>> result = new Result<IPage<Wglrmx>>();
        QueryWrapper<Wglrmx> queryWrapper = QueryGenerator.initQueryWrapper(wglrmx, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IWglrmxService.class,wglrmxService,pageNo,pageSize,queryWrapper,"zjhm");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 无关联人明细 / 提取
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public Result<?> init() {
        Result result = new Result<>();
		// `浏阳`调用ETL工具类执行ETL调度
		if ("true".equalsIgnoreCase(sfdsjpt)) {
			// `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            HashMap<String, String> params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_wglrtq");
			boolean completionSignal = EtlUtil.callEtl("zx_common_init", params, 15);
			result.setSuccess(completionSignal);
		} else {
			try {
				wglrmxMapper.pWglrmx();
				result.setSuccess(true);
				return result;
			} catch (Throwable e) {
				log.error("信用记录查询 / 无关联人明细 / 提取失败！"+e.getMessage());
				result.setSuccess(false);
			}
		}
		return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param wglrmx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Wglrmx wglrmx) {
        return super.exportXls(request, wglrmx, Wglrmx.class, "无关联人明细");
    }

}
