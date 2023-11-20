package org.cmms.modules.tjbb.gxywqkb.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.models.auth.In;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.tjbb.gxywqkb.entity.Gxywqkb;
import org.cmms.modules.tjbb.gxywqkb.service.IGxywqkbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.EtlUtil;
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
 * @Description: 各项业务情况表
 * @Author: Penghr
 * @Date: 2022-12-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "各项业务情况表")
@RestController
@RequestMapping("/tjbb/gxywqkb")
public class GxywqkbController extends JeecgController<Gxywqkb, IGxywqkbService> {
    @Autowired
    private IGxywqkbService gxywqkbService;
    @Value("${com.etl.sfdsjpt:false}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param gxywqkb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "各项业务情况表-分页列表查询")
    @ApiOperation(value = "各项业务情况表-分页列表查询", notes = "各项业务情况表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Gxywqkb gxywqkb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Gxywqkb> queryWrapper = QueryGenerator.initQueryWrapper(gxywqkb, req.getParameterMap());
        IPage<Gxywqkb> pageList = PageUtil.toPage(IGxywqkbService.class, gxywqkbService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

    /**
     * 各项业务情况表-数据统计
     *
     * @param requestbody
     * @return
     */
    @AutoLog(value = "各项业务情况表-数据统计")
    @ApiOperation(value = "各项业务情况表-数据统计", notes = "各项业务情况表-数据统计")
    @RequestMapping(value = "/statistics")
    public Result<?> statistics(@RequestBody Gxywqkb requestbody) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            int lastDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);//获取月最大天数
            //本期日期
            String bqrq = simpleDateFormat.format(requestbody.getDataDate()).replace("-","");
            log.info("各项业务情况表-数据统计-本期日期：" + bqrq);
            //昨日日期
//            c.setTime(requestbody.getDataDate());
//            c.add(Calendar.DATE, -1);
//            String zrrq = simpleDateFormat.format(c.getTime());
//            log.info("各项业务情况表-数据统计-昨日日期：" + zrrq);
            //上月月底
//            c.set(Calendar.YEAR, Integer.parseInt(bqrq.substring(0, 4)));
//            c.set(Calendar.MONTH, Integer.parseInt(bqrq.substring(5, 7)) - 1);
//            c.set(Calendar.DAY_OF_MONTH, lastDay);
//            c.add(Calendar.DATE, -1);
//            String syyd = simpleDateFormat.format(c.getTime());
//            log.info("各项业务情况表-数据统计-上月日期：" + syyd);
            //年初日期
//            c.setTime(requestbody.getDataDate());
//            c.clear(Calendar.MONTH);
//            c.set(Calendar.DAY_OF_MONTH, 1);
//            String ncrq = simpleDateFormat.format(c.getTime());
//            log.info("各项业务情况表-数据统计-年初日期：" + ncrq);
            //上年同期
//            c.setTime(requestbody.getDataDate());
//            c.add(Calendar.YEAR, -1);
//            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
//            String sntq = simpleDateFormat.format(c.getTime());
//            log.info("各项业务情况表-数据统计-上年同期：" + sntq);

            if ("true".equals(sfdsjpt)) {
                HashMap<String, String> params = new HashMap<>();
                params.put("fiscal_date", bqrq);
//                params.put("zrrq", zrrq);
//                params.put("syyd", syyd);
//                params.put("ncrq", ncrq);
//                params.put("sntq", sntq);
                params.put("etl_task", "kiss.domain.application.tjbb.proc_tjbb_xdb_gxywqk");
                log.info("数据统计ETL参数传递：" + params);
                boolean flag = EtlUtil.callEtl("tjbb_common_init", params, 15);
                log.info("各项业务情况表-数据统计-是否成功？-" + flag);
                if (flag) {
                    return Result.ok("数据统计成功！");
                } else {
                    return Result.error("数据统计失败，请联系管理员处理！");
                }
            } else {
                log.info("各项业务情况表-数据统计-未做Oracle数据提取");
                return Result.ok();
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("数据统计失败！" + throwable.getMessage());
            return Result.error("数据统计失败，请联系系统管理员！");
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int lastDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);//获取月最大天数
        //本期日期
        String bqrq = simpleDateFormat.format(new Date());
        System.out.println("本期日期：" + bqrq);
        //昨日日期
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        String zrrq = simpleDateFormat.format(c.getTime());
        System.out.println("昨日日期：" + zrrq);
        //本月月底
        c.set(Calendar.YEAR, Integer.parseInt(bqrq.substring(0, 4)));
        c.set(Calendar.MONTH, Integer.parseInt(bqrq.substring(5, 7)));
        c.set(Calendar.DAY_OF_MONTH, lastDay);
        c.add(Calendar.DATE, -1);
        String byyd = simpleDateFormat.format(c.getTime());
        System.out.println("本月月底：" + byyd);
        //上月月底
        c.set(Calendar.YEAR, Integer.parseInt(bqrq.substring(0, 4)));
        c.set(Calendar.MONTH, Integer.parseInt(bqrq.substring(5, 7)) - 1);
        c.set(Calendar.DAY_OF_MONTH, lastDay);
        c.add(Calendar.DATE, -1);
        String syyd = simpleDateFormat.format(c.getTime());
        System.out.println("上月月度：" + syyd);
        //年初日期
        c.setTime(new Date());
        c.clear(Calendar.MONTH);
        c.set(Calendar.DAY_OF_MONTH, 1);
        String ncrq = simpleDateFormat.format(c.getTime());
        System.out.println("年初日期：" + ncrq);
        //上年同期
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String sntq = simpleDateFormat.format(c.getTime());
        System.out.println("上年同期：" + sntq);
        //上年年底
        c.setTime(new Date());
        c.clear(Calendar.MONTH);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DATE, -1);
        String snnd = simpleDateFormat.format(c.getTime());
        log.info("上年年底：" + snnd);
    }

    /**
     * 添加
     *
     * @param gxywqkb
     * @return
     */
//	@AutoLog(value = "各项业务情况表-添加")
//	@ApiOperation(value="各项业务情况表-添加", notes="各项业务情况表-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody Gxywqkb gxywqkb) {
//		gxywqkbService.save(gxywqkb);
//		return Result.ok("添加成功！");
//	}

    /**
     * 编辑
     *
     * @param gxywqkb
     * @return
     */
//	@AutoLog(value = "各项业务情况表-编辑")
//	@ApiOperation(value="各项业务情况表-编辑", notes="各项业务情况表-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody Gxywqkb gxywqkb) {
//		gxywqkbService.updateById(gxywqkb);
//		return Result.ok("编辑成功!");
//	}

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
//	@AutoLog(value = "各项业务情况表-通过id删除")
//	@ApiOperation(value="各项业务情况表-通过id删除", notes="各项业务情况表-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		gxywqkbService.removeById(id);
//		return Result.ok("删除成功!");
//	}

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
//	@AutoLog(value = "各项业务情况表-批量删除")
//	@ApiOperation(value="各项业务情况表-批量删除", notes="各项业务情况表-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.gxywqkbService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功！");
//	}

    /**
     * 通过数据日期/序号查询
     *
     * @param data_date 数据日期
     * @param xh        序号
     * @return
     */
    @AutoLog(value = "各项业务情况表-通过数据日期/序号查询")
    @ApiOperation(value = "各项业务情况表-通过数据日期/序号查询", notes = "各项业务情况表-通过数据日期/序号查询")
    @GetMapping(value = "/queryByDatadateAndXh")
    public Result<?> queryById(@RequestParam(name = "data_date", required = true) Date data_date,
                               @RequestParam(name = "xh", required = true) String xh) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String datadate = simpleDateFormat.format(data_date);
        Gxywqkb gxywqkb = gxywqkbService.queryByDatadateAndXh(datadate, xh);
        return Result.ok(gxywqkb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        //return super.exportXls(request, gxywqkb, Gxywqkb.class, "各项业务情况");
        QueryWrapper<Gxywqkb> queryWrapper = new QueryWrapper<>();
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                Gxywqkb gxywqkb = JSON.parseObject(deString, Gxywqkb.class);
                queryWrapper = QueryGenerator.initQueryWrapper(gxywqkb, request.getParameterMap());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("导出错误！各项业务情况："+throwable.getMessage());
        }
        queryWrapper.orderByAsc("data_date", "xh");
        List<Gxywqkb> pageList = gxywqkbService.list(queryWrapper);

        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "新邵农商银行各项业务情况");
        mv.addObject(NormalExcelConstants.CLASS, Gxywqkb.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("各项业务情况", "导出人:" + getLoginUser().getRealname() + "（单位/万元、%）", "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
//    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//      return super.importExcel(request, response, Gxywqkb.class);
//    }

}
