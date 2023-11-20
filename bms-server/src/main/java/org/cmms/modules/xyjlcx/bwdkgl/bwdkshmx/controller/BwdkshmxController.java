package org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Hash;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.entity.Bwdkshmx;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.mapper.BwdkshmxMapper;
import org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.service.IBwdkshmxService;
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
 * @Description: 表外贷款收回明细
 * @Author: jeecg-boot
 * @Date: 2021-08-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "表外贷款收回明细")
@RestController
@RequestMapping("/bwdkshmx/bwdkshmx")
public class BwdkshmxController extends JeecgController<Bwdkshmx, IBwdkshmxService> {
    @Autowired
    private IBwdkshmxService bwdkshmxService;
    @Autowired
    private IDkzdkbService dkzdkbService;
    @Autowired
    private ListToDictUtil listToDictUtil;
    @Autowired
    private ISysDictService sysDictService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param bwdkshmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "表外贷款收回明细-分页列表查询")
    @ApiOperation(value = "表外贷款收回明细-分页列表查询", notes = "表外贷款收回明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Bwdkshmx bwdkshmx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Bwdkshmx>> result = new Result<IPage<Bwdkshmx>>();
        QueryWrapper<Bwdkshmx> queryWrapper = QueryGenerator.initQueryWrapper(bwdkshmx, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IBwdkshmxService.class, bwdkshmxService, pageNo, pageSize, queryWrapper, "rzwd", "rzsj", "zh", "shrq");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 表外贷款收回明细 / 修改
     * @param rzwd
     * @return
     */
    /*@AutoLog(value = "表外贷款收回明细-修改")
    @ApiOperation(value = "表外贷款收回明细-修改", notes = "表外贷款收回明细-修改")
    @RequestMapping(value = "/add")
    public Result<?> add(@RequestParam(value = "rzwd",required = true) String rzwd) {
        JSONObject view = new JSONObject();
        try {
            String sjrq = DateUtils.date2Str(new Date(),new SimpleDateFormat("yyyy-MM-dd"));
            //String maxsjrq = DateUtils.date2Str(dkzdkbService.queryMaxDataDate(), new SimpleDateFormat("yyyy-MM-dd"));
            //view.put("sjgxrShow",maxsjrq == null ? sjrq : maxsjrq);
            view.put("sjgxrShow",sjrq);
            Date beginDate = DateUtil.getFirstday_Month(new Date(), 0);
            String nowDay = DateUtil.formatDateTime("dd");
            if (Integer.parseInt(nowDay) <= 5) {
                beginDate = DateUtil.getFirstday_Month(new Date(), -1);
            }
            String shrq = DateUtils.date2Str(beginDate, new SimpleDateFormat("yyyyMMdd"));
            List<Bwdkshmx> bwdkshmxList = bwdkshmxService.queryBwdkshmxOracle(rzwd,shrq);
            bwdkshmxList = listToDictUtil.parseDictText(bwdkshmxList);
            view.put("table",bwdkshmxList);
            view.put("rzwd",rzwd);
            view.put("rzwdShow", sysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","ywjgdm",rzwd));
            return Result.ok(view);
        } catch (Throwable e) {
            log.error("信用记录查询 / 表外贷款收回明细 / 修改失败！"+e.getMessage());
            return Result.error("修改失败!");
        }
    }*/

    /**
     * 表外贷款收回明细 / 提取
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "表外贷款收回明细-提取")
    @ApiOperation(value = "表外贷款收回明细-提取", notes = "表外贷款收回明细-提取")
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
        String rzsjBegin = jsonObject.getString("rzsjBegin");
        String rzsjEnd   = jsonObject.getString("rzsjEnd");
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            rzsjBegin = rzsjBegin.replaceAll("-", "");
            rzsjEnd = rzsjEnd.replaceAll("-", "");
            params.put("begin_day", rzsjBegin);
            params.put("end_day", rzsjEnd);
            params.put("etl_task", "kiss.domain.application.zx.proc_credit_bwdkshmx");
            // `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            boolean completionSignal = EtlUtil.callEtl("zx_common_init", params, 20);
            result.setSuccess(completionSignal);
        } else {
            try {
                bwdkshmxService.pBwdkshmx(rzsjBegin, rzsjEnd);
                result.setSuccess(true);
                return result;
            } catch (Throwable e) {
                log.error("信用记录查询 / 表外贷款收回明细 / 提取失败！"+e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 表外贷款收回明细 / 编辑
     *
     * @param bwdkshmx
     * @return
     */
    @AutoLog(value = "表外贷款收回明细-编辑")
    @ApiOperation(value = "表外贷款收回明细-编辑", notes = "表外贷款收回明细-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Bwdkshmx bwdkshmx) {
        try {
            QueryWrapper<Bwdkshmx> queryWrapper = new QueryWrapper<Bwdkshmx>();
            queryWrapper.eq("rzwd",bwdkshmx.getRzwd());
            queryWrapper.eq("rzsj",bwdkshmx.getRzsj());
            queryWrapper.eq("zh",bwdkshmx.getZh());
            queryWrapper.eq("shrq",bwdkshmx.getShrq());
            //表主键不能更新
            bwdkshmx.setRzwd(null);
            bwdkshmx.setRzsj(null);
            bwdkshmx.setZh(null);
            bwdkshmx.setShrq(null);
            bwdkshmx.setLrbz(2);
            bwdkshmx.setLrr(getLoginUser().getUsername());
            bwdkshmx.setLrsj(new Timestamp(System.currentTimeMillis()));
            bwdkshmxService.update(bwdkshmx, queryWrapper);
            return Result.ok("编辑成功!");
        } catch (Throwable throwable) {
            log.error("信用记录查询 / 表外贷款收回明细 / 编辑失败！"+throwable.getMessage());
            return Result.error("编辑失败!");
        }
    }

    /**
     * 表外贷款收回明细 / 删除
     *
     * @param rzwd
     * @param rzsjStr
     * @param zh
     * @param shrqStr
     * @return
     */
    @AutoLog(value = "表外贷款收回明细-删除")
    @ApiOperation(value = "表外贷款收回明细-删除", notes = "表外贷款收回明细-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(value = "rzwd", required = true) String rzwd,
                            @RequestParam(value = "rzsj", required = true) String rzsjStr,
                            @RequestParam(value = "zh", required = true) String zh,
                            @RequestParam(value = "shrq", required = true) String shrqStr) {
        try {
            Date shrq = DateUtil.string2Date(shrqStr,"yyyy-MM-dd");
            Date rzsj = DateUtil.string2Date(rzsjStr,"yyyy-MM-dd");
            QueryWrapper<Bwdkshmx> queryWrapper = new QueryWrapper<Bwdkshmx>();
            queryWrapper.eq("rzwd",rzwd);
            queryWrapper.eq("rzsj",rzsj);
            queryWrapper.eq("zh",zh);
            queryWrapper.eq("shrq",shrq);
            bwdkshmxService.remove(queryWrapper);
            return Result.ok("删除成功!");
        } catch (Throwable throwable) {
            log.error("信用记录查询 / 表外贷款收回明细 / 删除失败！"+throwable.getMessage());
            return Result.error("删除失败!");
        }
    }

    /**
     * 导出excel
     *
     * @param request
     * @param bwdkshmx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Bwdkshmx bwdkshmx) {
        return super.exportXls(request, bwdkshmx, Bwdkshmx.class, "表外贷款收回明细");
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
        return super.importExcel(request, response, Bwdkshmx.class);
    }

}
