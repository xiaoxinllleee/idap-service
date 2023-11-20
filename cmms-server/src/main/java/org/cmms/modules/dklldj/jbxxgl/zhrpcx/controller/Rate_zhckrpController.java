package org.cmms.modules.dklldj.jbxxgl.zhrpcx.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import freemarker.template.SimpleDate;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.entity.CkzhglxxHistory;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.entity.Rate_khjbxxb;
import org.cmms.modules.dklldj.jbxxgl.zhrpcx.entity.Rate_zhckrp;
import org.cmms.modules.dklldj.jbxxgl.zhrpcx.service.IRate_zhckrpService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dklldj.lldjgl.lldjsq.service.ILldjsqService;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.entity.Kzhglgx;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.service.IKzhglgxService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.util.DateUtil;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 账号日平查询
 * @Author: jeecg-boot
 * @Date: 2020-03-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "账号日平查询")
@RestController
@RequestMapping("/rate_zhckrp/rate_zhckrp")
public class Rate_zhckrpController extends JeecgController<Rate_zhckrp, IRate_zhckrpService> {
    @Autowired
    private IRate_zhckrpService rate_zhckrpService;
    @Autowired
    private ISysLogService sysLogService;
	@Autowired
	private ISysDictService iSysDictService;
	@Autowired
    private IKzhglgxService kzhglgxService;
	@Autowired
    private ICkzdkbService ckzdkbService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param rate_zhckrp
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "账号日平查询-分页列表查询")
    @ApiOperation(value = "账号日平查询-分页列表查询", notes = "账号日平查询-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Rate_zhckrp rate_zhckrp,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {

        Result<IPage<Rate_zhckrp>> result = new Result<IPage<Rate_zhckrp>>();
        QueryWrapper<Rate_zhckrp> queryWrapper = QueryGenerator.initQueryWrapper(rate_zhckrp, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IRate_zhckrpService.class,rate_zhckrpService,pageNo,pageSize,queryWrapper,"tjyf","tjlx","zjhm","ckzh");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param rate_zhckrp
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Rate_zhckrp rate_zhckrp) {
        return super.exportXls(request, rate_zhckrp, Rate_zhckrp.class, "账号日平信息");
    }

    /**
     * 账号日平查询 / 提取
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/extract", method = RequestMethod.PUT)
    public Result<?> extract(@RequestBody JSONObject jsonObject) {
		Result result = new Result<>();
        String tjyf = jsonObject.getString("tjyf");
        String zjhm = jsonObject.getString("zjhm");
        String ckzh = jsonObject.getString("ckzh");
        String kh   = jsonObject.getString("kh");
        if (StringUtils.isEmpty(ckzh) && StringUtils.isNotEmpty(kh)) {
            QueryWrapper<Kzhglgx> CbscLinkQueryWrapper = new QueryWrapper<>();
            CbscLinkQueryWrapper.eq("card","0"+kh);
            Kzhglgx CbscLink = kzhglgxService.getOne(CbscLinkQueryWrapper,false);
            if (CbscLink != null) {
                QueryWrapper<Ckzdkb> CbsInvmBaseQueryWrapper = new QueryWrapper<>();
                CbsInvmBaseQueryWrapper.eq("sub_acct_no",CbscLink.getAccount());
                Ckzdkb CbsInvmBase = ckzdkbService.getOne(CbsInvmBaseQueryWrapper,false);
                if (CbsInvmBase != null) {
                    ckzh = CbsInvmBase.getSubAcctNo();
                }
            } else {
                return Result.error("该卡号在核心系统中查不到，请确认卡号是否填写正确！");
            }
        }
		// `浏阳`调用ETL工具类执行ETL调度
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            Date cksjrq = DateUtil.string2Date(sysLogService.cksjrqBig(),"yyyyMMdd");
            tjyf = DateUtil.getSjQmrq(DateUtil.getLastDayOfMonthStr(tjyf,"yyyyMMdd"), cksjrq, "yyyy-MM-dd").replace("-", "");
            HashMap<String, String> params = new HashMap<>();
            params.put("fiscal_date", tjyf);
            params.put("ident_no", zjhm==null?"":zjhm);
            params.put("acct_no", ckzh==null?"":ckzh);
            // `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            params.put("etl_task","kiss.domain.application.rate.proc_rate_zhckrptj");
            // count_rate_zhckrptj
            boolean completionSignal = EtlUtil.callEtl("rate_common_init", params, 20);
            result.setSuccess(completionSignal);
            return result;
        } else {
			try {
				if (zjhm == null && ckzh == null) {
					return Result.error("[证件号码,账号]两个必须填一个！");
				}
				if (tjyf == null || "".equalsIgnoreCase(tjyf)) {
					return Result.error("请选择统计月份");
				}
				Map<String, String> map = new HashMap<>();
				map.put("tjyf", tjyf);
				map.put("zjhm", zjhm);
				map.put("ckzh", ckzh);
				rate_zhckrpService.extract(map);
				return Result.ok("提取成功");
			} catch (Exception e) {
				System.out.println(e);
				log.error("提取失败", e.getMessage());
				return Result.error("提取失败");
			}
		}
    }
}
