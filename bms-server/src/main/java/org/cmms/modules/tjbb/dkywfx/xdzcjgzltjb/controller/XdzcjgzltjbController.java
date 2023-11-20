package org.cmms.modules.tjbb.dkywfx.xdzcjgzltjb.controller;

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
import org.cmms.modules.tjbb.dkywfx.xdzcjgzltjb.entity.Xdzcjgzltjb;
import org.cmms.modules.tjbb.dkywfx.xdzcjgzltjb.service.IXdzcjgzltjbService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.gxywqkb.entity.Gxywqkb;
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
 * @Description: 信贷资产结构质量统计表（一）
 * @Author: Penghr
 * @Date: 2022-12-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "信贷资产结构质量统计表（一）")
@RestController
@RequestMapping("/tjbb/dkywfx/xdzcjgzltjb")
public class XdzcjgzltjbController extends JeecgController<Xdzcjgzltjb, IXdzcjgzltjbService> {
    @Autowired
    private IXdzcjgzltjbService xdzcjgzltjbService;
	@Value("${com.etl.sfdsjpt:false}")
	private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param xdzcjgzltjb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "信贷资产结构质量统计表（一）-分页列表查询")
    @ApiOperation(value = "信贷资产结构质量统计表（一）-分页列表查询", notes = "信贷资产结构质量统计表（一）-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Xdzcjgzltjb xdzcjgzltjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Xdzcjgzltjb> queryWrapper = QueryGenerator.initQueryWrapper(xdzcjgzltjb, req.getParameterMap());
        IPage<Xdzcjgzltjb> pageList = PageUtil.toPage(IXdzcjgzltjbService.class, xdzcjgzltjbService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

	/**
	 * 信贷资产结构质量统计表（一）-数据统计
	 *
	 * @param requestbody
	 * @return
	 */
	@AutoLog(value = "信贷资产结构质量统计表（一）-数据统计")
	@ApiOperation(value = "信贷资产结构质量统计表（一）-数据统计", notes = "信贷资产结构质量统计表（一）-数据统计")
	@RequestMapping(value = "/statistics")
	public Result<?> statistics(@RequestBody Xdzcjgzltjb requestbody) {
    	try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			int lastDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);//获取月最大天数
			//本期日期
			String bqrq = simpleDateFormat.format(requestbody.getDataDate()).replace("-","");
			log.info("信贷资产结构质量统计表（一）-数据统计-本期日期：" + bqrq);
			//年初日期
//			c.setTime(requestbody.getDataDate());
//			c.clear(Calendar.MONTH);
//			c.set(Calendar.DAY_OF_MONTH, 1);
//			String ncrq = simpleDateFormat.format(c.getTime());
//			log.info("信贷资产结构质量统计表（一）-数据统计-年初日期：" + ncrq);
			//上月月底
//			c.set(Calendar.YEAR, Integer.parseInt(bqrq.substring(0, 4)));
//			c.set(Calendar.MONTH, Integer.parseInt(bqrq.substring(5, 7)) - 1);
//			c.set(Calendar.DAY_OF_MONTH, lastDay);
//			c.add(Calendar.DATE, -1);
//			String syyd = simpleDateFormat.format(c.getTime());
//			log.info("信贷资产结构质量统计表（一）-数据统计-上月日期：" + syyd);

    		if ("true".equals(sfdsjpt)) {
				HashMap<String, String> params = new HashMap<>();
				params.put("fiscal_date", bqrq);
//				params.put("ncrq", ncrq);
//				params.put("syyd", syyd);
				params.put("etl_task", "kiss.domain.application.tjbb.proc_tjbb_fxb_xdzcjgzltj");
				log.info("数据统计ETL参数传递：" + params);
				boolean flag = EtlUtil.callEtl("tjbb_common_init", params, 15);
				log.info("信贷资产结构质量统计表（一）-数据统计-是否成功？-" + flag);
				if (flag) {
					return Result.ok("数据统计成功！");
				} else {
					return Result.error("数据统计失败，请联系管理员处理！");
				}
			} else {
				log.info("信贷资产结构质量统计表（一）-数据统计-未做Oracle数据提取");
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
     * @param xdzcjgzltjb
     * @return
     */
//    @AutoLog(value = "信贷资产结构质量统计表（一）-添加")
//    @ApiOperation(value = "信贷资产结构质量统计表（一）-添加", notes = "信贷资产结构质量统计表（一）-添加")
//    @PostMapping(value = "/add")
//    public Result<?> add(@RequestBody Xdzcjgzltjb xdzcjgzltjb) {
//        xdzcjgzltjbService.save(xdzcjgzltjb);
//        return Result.ok("添加成功！");
//    }

    /**
     * 编辑
     *
     * @param xdzcjgzltjb
     * @return
     */
//    @AutoLog(value = "信贷资产结构质量统计表（一）-编辑")
//    @ApiOperation(value = "信贷资产结构质量统计表（一）-编辑", notes = "信贷资产结构质量统计表（一）-编辑")
//    @PutMapping(value = "/edit")
//    public Result<?> edit(@RequestBody Xdzcjgzltjb xdzcjgzltjb) {
//        xdzcjgzltjbService.updateById(xdzcjgzltjb);
//        return Result.ok("编辑成功!");
//    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
//    @AutoLog(value = "信贷资产结构质量统计表（一）-通过id删除")
//    @ApiOperation(value = "信贷资产结构质量统计表（一）-通过id删除", notes = "信贷资产结构质量统计表（一）-通过id删除")
//    @DeleteMapping(value = "/delete")
//    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
//        xdzcjgzltjbService.removeById(id);
//        return Result.ok("删除成功!");
//    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
//    @AutoLog(value = "信贷资产结构质量统计表（一）-批量删除")
//    @ApiOperation(value = "信贷资产结构质量统计表（一）-批量删除", notes = "信贷资产结构质量统计表（一）-批量删除")
//    @DeleteMapping(value = "/deleteBatch")
//    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
//        this.xdzcjgzltjbService.removeByIds(Arrays.asList(ids.split(",")));
//        return Result.ok("批量删除成功！");
//    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
//    @AutoLog(value = "信贷资产结构质量统计表（一）-通过id查询")
//    @ApiOperation(value = "信贷资产结构质量统计表（一）-通过id查询", notes = "信贷资产结构质量统计表（一）-通过id查询")
//    @GetMapping(value = "/queryById")
//    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
//        Xdzcjgzltjb xdzcjgzltjb = xdzcjgzltjbService.getById(id);
//        return Result.ok(xdzcjgzltjb);
//    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        // return super.exportXls(request, xdzcjgzltjb, Xdzcjgzltjb.class, "信贷资产结构质量统计表（一）");
		QueryWrapper<Xdzcjgzltjb> queryWrapper = new QueryWrapper<>();
		try {
			String paramsStr = request.getParameter("paramsStr");
			if (oConvertUtils.isNotEmpty(paramsStr)) {
				String deString = URLDecoder.decode(paramsStr, "UTF-8");
				Xdzcjgzltjb xdzcjgzltjb = JSON.parseObject(deString, Xdzcjgzltjb.class);
				queryWrapper = QueryGenerator.initQueryWrapper(xdzcjgzltjb, request.getParameterMap());
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			log.error("导出错误！信贷资产结构质量统计表（一）："+throwable.getMessage());
		}
		queryWrapper.orderByAsc("data_date", "jgbm");
		List<Xdzcjgzltjb> pageList = xdzcjgzltjbService.list(queryWrapper);

		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		//导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "新邵农商银行信贷资产结构质量统计表（一）");
		mv.addObject(NormalExcelConstants.CLASS, Xdzcjgzltjb.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("信贷资产结构质量统计表（一）", "导出人:" + getLoginUser().getRealname() + "（单位/万元、%）", "导出信息"));
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
//        return super.importExcel(request, response, Xdzcjgzltjb.class);
//    }

}
