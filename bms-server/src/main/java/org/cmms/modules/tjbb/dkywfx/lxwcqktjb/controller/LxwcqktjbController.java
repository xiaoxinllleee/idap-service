package org.cmms.modules.tjbb.dkywfx.lxwcqktjb.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.tjbb.dkywfx.lxwcqktjb.entity.Lxwcqktjb;
import org.cmms.modules.tjbb.dkywfx.lxwcqktjb.service.ILxwcqktjbService;

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
 * @Description: 利息完成情况统计表（五）
 * @Author: Penghr
 * @Date: 2022-12-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "利息完成情况统计表（五）")
@RestController
@RequestMapping("/tjbb/dkywfx/lxwcqktjb")
public class LxwcqktjbController extends JeecgController<Lxwcqktjb, ILxwcqktjbService> {
    @Autowired
    private ILxwcqktjbService lxwcqktjbService;
    @Value("${com.etl.sfdsjpt:false}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param lxwcqktjb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "利息完成情况统计表（五）-分页列表查询")
    @ApiOperation(value = "利息完成情况统计表（五）-分页列表查询", notes = "利息完成情况统计表（五）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Lxwcqktjb lxwcqktjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Lxwcqktjb> queryWrapper = QueryGenerator.initQueryWrapper(lxwcqktjb, req.getParameterMap());
        IPage<Lxwcqktjb> pageList = PageUtil.toPage(ILxwcqktjbService.class, lxwcqktjbService, pageNo, pageSize, queryWrapper, "tjyf");
        return Result.ok(pageList);
    }

    /**
     * 利息完成情况统计表（五）：数据统计
     *
     * @param requestbody
     * @return
     */
    @AutoLog(value = "利息完成情况统计表（五）-数据统计")
    @ApiOperation(value = "利息完成情况统计表（五）-数据统计", notes = "利息完成情况统计表（五）-数据统计")
    @RequestMapping(value = "/statistics")
    public Result<?> statistics(@RequestBody Lxwcqktjb requestbody) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            int lastDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);//获取月最大天数
            //会计日期
            String fiscal_date = simpleDateFormat.format(requestbody.getTjyf()).replace("-","");
            log.info("利息完成情况统计表（五）-数据统计-会计日期：" + fiscal_date);
            //上年底
//            c.setTime(requestbody.getTjyf());
//            c.clear(Calendar.MONTH);
//            c.set(Calendar.DAY_OF_MONTH, 1);
//            c.add(Calendar.DATE, -1);
//            String snnd = simpleDateFormat.format(c.getTime());
//            log.info("利息完成情况统计表（五）-数据统计-上年底：" + snnd);
            //本月底
//            c.set(Calendar.YEAR, Integer.parseInt(fiscal_date.substring(0, 4)));
//            c.set(Calendar.MONTH, Integer.parseInt(fiscal_date.substring(5, 7)));
//            c.set(Calendar.DAY_OF_MONTH, lastDay);
//            c.add(Calendar.DATE, -1);
//            String byyd = simpleDateFormat.format(c.getTime());
//            log.info("利息完成情况统计表（五）-数据统计-本月月底：" + byyd);
            //上月底
//            c.set(Calendar.YEAR, Integer.parseInt(fiscal_date.substring(0, 4)));
//            c.set(Calendar.MONTH, Integer.parseInt(fiscal_date.substring(5, 7)) - 1);
//            c.set(Calendar.DAY_OF_MONTH, lastDay);
//            c.add(Calendar.DATE, -1);
//            String syyd = simpleDateFormat.format(c.getTime());
//            log.info("利息完成情况统计表（五）-数据统计-上月月底：" + syyd);
            //上年同期
//            c.setTime(requestbody.getTjyf());
//            c.add(Calendar.YEAR, -1);
//            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
//            String sntq = simpleDateFormat.format(c.getTime());
//            log.info("利息完成情况统计表（五）-数据统计-上年同期：" + sntq);

            if ("true".equals(sfdsjpt)) {
                HashMap<String, String> params = new HashMap<>();
                params.put("fiscal_date", fiscal_date);
//                params.put("snnd", snnd);
//                params.put("byyd", byyd);
//                params.put("syyd", syyd);
//                params.put("sntq", sntq);
                params.put("etl_task", "kiss.domain.application.tjbb.proc_tjbb_xdb_lxwcqktj");
                log.info("数据统计ETL参数传递：" + params);
                boolean flag = EtlUtil.callEtl("tjbb_common_init", params, 15);
                log.info("利息完成情况统计表（五）-数据统计-是否成功？-" + flag);
                if (flag) {
                    return Result.ok("数据统计成功！");
                } else {
                    return Result.error("数据统计失败，请联系管理员处理！");
                }
            } else {
                log.info("利息完成情况统计表（五）-数据统计-未做Oracle数据提取");
                return Result.ok();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("数据统计失败！" + throwable.getMessage());
            return Result.error("数据统计失败，请联系系统管理员！");
        }
    }

    /**
     * 添加
     *
     * @param lxwcqktjb
     * @return
     */
//	@AutoLog(value = "利息完成情况统计表（五）-添加")
//	@ApiOperation(value="利息完成情况统计表（五）-添加", notes="利息完成情况统计表（五）-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody Lxwcqktjb lxwcqktjb) {
//		lxwcqktjbService.save(lxwcqktjb);
//		return Result.ok("添加成功！");
//	}

    /**
     * 编辑
     *
     * @param lxwcqktjb
     * @return
     */
//	@AutoLog(value = "利息完成情况统计表（五）-编辑")
//	@ApiOperation(value="利息完成情况统计表（五）-编辑", notes="利息完成情况统计表（五）-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody Lxwcqktjb lxwcqktjb) {
//		lxwcqktjbService.updateById(lxwcqktjb);
//		return Result.ok("编辑成功!");
//	}

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
//	@AutoLog(value = "利息完成情况统计表（五）-通过id删除")
//	@ApiOperation(value="利息完成情况统计表（五）-通过id删除", notes="利息完成情况统计表（五）-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		lxwcqktjbService.removeById(id);
//		return Result.ok("删除成功!");
//	}

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
//	@AutoLog(value = "利息完成情况统计表（五）-批量删除")
//	@ApiOperation(value="利息完成情况统计表（五）-批量删除", notes="利息完成情况统计表（五）-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.lxwcqktjbService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功！");
//	}

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
//    @AutoLog(value = "利息完成情况统计表（五）-通过id查询")
//    @ApiOperation(value = "利息完成情况统计表（五）-通过id查询", notes = "利息完成情况统计表（五）-通过id查询")
//    @GetMapping(value = "/queryById")
//    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
//        Lxwcqktjb lxwcqktjb = lxwcqktjbService.getById(id);
//        return Result.ok(lxwcqktjb);
//    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        // return super.exportXls(request, lxwcqktjb, Lxwcqktjb.class, "利息完成情况统计表（五）");
        QueryWrapper<Lxwcqktjb> queryWrapper = new QueryWrapper<>();
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                Lxwcqktjb lxwcqktjb = JSON.parseObject(deString, Lxwcqktjb.class);
                queryWrapper = QueryGenerator.initQueryWrapper(lxwcqktjb, request.getParameterMap());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("导出错误！利息完成情况统计表（五）：" + throwable.getMessage());
        }
        queryWrapper.orderByAsc("tjyf", "jgbm");
        List<Lxwcqktjb> pageList = lxwcqktjbService.list(queryWrapper);

        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "新邵农村商业银行利息完成情况统计表（五）");
        mv.addObject(NormalExcelConstants.CLASS, Lxwcqktjb.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("新邵农村商业银行利息完成情况统计表（五）", "导出人:" + getLoginUser().getRealname() + "（单位/万元、%）", "导出信息"));
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
//      return super.importExcel(request, response, Lxwcqktjb.class);
//    }

}
