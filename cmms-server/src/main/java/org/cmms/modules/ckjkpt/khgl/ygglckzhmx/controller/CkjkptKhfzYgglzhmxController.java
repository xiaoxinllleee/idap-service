package org.cmms.modules.ckjkpt.khgl.ygglckzhmx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity.CkjkptKhfzYgglkhmx;
import org.cmms.modules.ckjkpt.khgl.ygglckzhmx.entity.CkjkptKhfzYgglzhmx;
import org.cmms.modules.ckjkpt.khgl.ygglckzhmx.service.ICkjkptKhfzYgglzhmxService;

import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ckjkpt.util.mapper.CkjkptCallMapper;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.slf4j.Logger;
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
 * @Description: 员工关联存款账户明细
 * @Author: jeecg-boot
 * @Date: 2021-07-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "员工关联存款账户明细")
@RestController
@RequestMapping("/ygglckzhmx/ckjkptKhfzYgglzhmx")
public class CkjkptKhfzYgglzhmxController extends JeecgController<CkjkptKhfzYgglzhmx, ICkjkptKhfzYgglzhmxService> {
    @Autowired
    private ICkjkptKhfzYgglzhmxService ckjkptKhfzYgglzhmxService;
    @Autowired(required = false)
    private CkjkptCallMapper ckjkptCallMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysLogService sysLogService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param ckjkptKhfzYgglzhmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "员工关联存款账户明细-分页列表查询")
    @ApiOperation(value = "员工关联存款账户明细-分页列表查询", notes = "员工关联存款账户明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CkjkptKhfzYgglzhmx ckjkptKhfzYgglzhmx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   // String ckyeS, String ckyeE,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzYgglzhmx, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptKhfzYgglzhmxService.class,ckjkptKhfzYgglzhmxService,pageNo,pageSize,queryWrapper,"tjyf","zzbz","yggh","zjhm");
        return Result.ok(pageList);
    }

    /**
     * 分页列表查询
     * @return
     */
    @AutoLog(value = "员工关联存款客户明细-分页列表查询")
    @ApiOperation(value="员工关联存款客户明细-分页列表查询", notes="员工关联存款客户明细-分页列表查询")
    @PutMapping(value = "/queryList")
    public Result<?> queryList(@RequestBody CkjkptKhfzYgglzhmx ckjkptKhfzYgglzhmx) {
        QueryWrapper<CkjkptKhfzYgglzhmx> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("zjhm",ckjkptKhfzYgglzhmx.getZjhm());
        queryWrapper.last(" and tjyf >=(select add_months(max(tjyf),-12) from Ckjkpt_khfz_ygglkhmx) order by tjyf asc");
        List<CkjkptKhfzYgglzhmx> list = ckjkptKhfzYgglzhmxService.list(queryWrapper);
        JSONArray jsonArray=new JSONArray();
        for(CkjkptKhfzYgglzhmx c: list){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("type", org.cmms.modules.util.DateUtil.date2String(c.getTjyf(),"yyyy-MM-dd"));
            jsonObject.put("存款余额",c.getCkye());
            jsonObject.put("存款月日平",c.getCkrpye());
            jsonArray.add(jsonObject);
        }

        return Result.ok(jsonArray);
    }


    /**
     * 导出excel
     *
     * @param request
     * @param ckjkptKhfzYgglzhmx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CkjkptKhfzYgglzhmx ckjkptKhfzYgglzhmx, String ckyeS, String ckyeE) {
        // Step.1 组装查询条件
        QueryWrapper<CkjkptKhfzYgglzhmx> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzYgglzhmx, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (!StringUtils.isBlank(ckyeS)) {
            queryWrapper.ge("ckye", ckyeS);
        }
        if (!StringUtils.isBlank(ckyeE)) {
            queryWrapper.le("ckye", ckyeE);
        }
        // Step.2 获取导出数据
        List<CkjkptKhfzYgglzhmx> list = service.list(queryWrapper);
        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "员工关联存款账户明细"); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, CkjkptKhfzYgglzhmx.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("员工关联存款账户明细" + "报表", "导出人:" + sysUser.getRealname(), "员工关联存款账户明细"));
        mv.addObject(NormalExcelConstants.DATA_LIST, list);
        return mv;
        // return super.exportXls(request, ckjkptKhfzYgglzhmx, CkjkptKhfzYgglzhmx.class, "员工关联存款账户明细");
    }

    /**
     * 员工关联存款账户明细 - 提取
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
		Result result = new Result<>();
		String tjyf = jsonObject.getString("tjyf");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        Date date = null;
        try {
            date = DateUtil.getFirstday_Month(new Date(), 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (-1 == date.compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
            return Result.error("选择的月份必须小于等于当前月");
        }
        // tjyf = tjyf.replaceAll("-", "");
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            Date cksjrq = DateUtil.string2Date(sysLogService.cksjrqBig(),"yyyyMMdd");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            String SysMonths = dateFormat.format(new Date()).substring(0, 7).concat("-01");
            System.out.println("---------------前端提取提取000000:"+tjyf);
            System.out.println("---------------SysMonths000000:"+SysMonths);
            if (tjyf != null && (tjyf.equals(SysMonths))) {
                System.out.println("---------------前端提取提取:"+tjyf);
                tjyf=org.cmms.modules.util.DateUtil.getSjQmrq(tjyf,cksjrq,"yyyy-MM-dd");
                System.out.println("---------------数据日期:"+DateUtil.getDateString(cksjrq));
                System.out.println("---------------转换后提取日期:"+tjyf);
                HashMap<String, String> params = new HashMap<>();
                params.put("fiscal_date", tjyf);
                params.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_khfz_ygglzhmx");
                // count_ckjkpt_khfz_ygglzhmx
                boolean falg = EtlUtil.callEtl("ckjkpt_common_init", params, 15);
                result.setSuccess(falg);
            } else {
                System.out.println("---------------前端提取提取his:"+tjyf);
                tjyf=org.cmms.modules.util.DateUtil.getSjQmrq(tjyf,cksjrq,"yyyy-MM-dd");
                System.out.println("---------------数据日期his:"+DateUtil.getDateString(cksjrq));
                System.out.println("---------------转换后提取日期his:"+tjyf);
                HashMap<String, String> params = new HashMap<>();
                params.put("fiscal_date", tjyf);
                params.put("etl_task","kiss.domain.application.ckjkpt.proc_ckjkpt_khfz_ygglzhmx_his");
                // count_ckjkpt_khfz_ygglzhmx_his
                boolean falg = EtlUtil.callEtl("ckjkpt_common_init", params, 15);
                result.setSuccess(falg);
            }

        } else {
            try {
                tjyf = tjyf.replace("-", "");
                ckjkptCallMapper.pYgglzhmx(tjyf);
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                System.out.println(e);
                log.error("提取失败", e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }


    /**
     * 添加
     *
     * @param ckjkptKhfzYgglzhmx
     * @return
     */
    @AutoLog(value = "员工关联存款账户明细-添加")
    @ApiOperation(value = "员工关联存款账户明细-添加", notes = "员工关联存款账户明细-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CkjkptKhfzYgglzhmx ckjkptKhfzYgglzhmx) {
        ckjkptKhfzYgglzhmxService.save(ckjkptKhfzYgglzhmx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param ckjkptKhfzYgglzhmx
     * @return
     */
    @AutoLog(value = "员工关联存款账户明细-编辑")
    @ApiOperation(value = "员工关联存款账户明细-编辑", notes = "员工关联存款账户明细-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CkjkptKhfzYgglzhmx ckjkptKhfzYgglzhmx) {
        ckjkptKhfzYgglzhmxService.updateById(ckjkptKhfzYgglzhmx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "员工关联存款账户明细-通过id删除")
    @ApiOperation(value = "员工关联存款账户明细-通过id删除", notes = "员工关联存款账户明细-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        ckjkptKhfzYgglzhmxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "员工关联存款账户明细-批量删除")
    @ApiOperation(value = "员工关联存款账户明细-批量删除", notes = "员工关联存款账户明细-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.ckjkptKhfzYgglzhmxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "员工关联存款账户明细-通过id查询")
    @ApiOperation(value = "员工关联存款账户明细-通过id查询", notes = "员工关联存款账户明细-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CkjkptKhfzYgglzhmx ckjkptKhfzYgglzhmx = ckjkptKhfzYgglzhmxService.getById(id);
        return Result.ok(ckjkptKhfzYgglzhmx);
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
        return super.importExcel(request, response, CkjkptKhfzYgglzhmx.class);
    }

}
