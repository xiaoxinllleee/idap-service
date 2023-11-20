package org.cmms.modules.jx.wdyy.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.jx.wdyy.entity.HistoryPageSearchDTO;
import org.cmms.modules.jx.wdyy.entity.KhgxglCkkhyxdjb;
import org.cmms.modules.jx.wdyy.service.IKhgxglCkkhyxdjbService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 存款客户营销登记簿表
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款客户营销登记簿表")
@RestController
@RequestMapping("/mobile/khgxglCkkhyxdjbRest")
public class KhgxglCkkhyxdjbController extends JeecgController<KhgxglCkkhyxdjb, IKhgxglCkkhyxdjbService> {
	@Autowired
	private IKhgxglCkkhyxdjbService khgxglCkkhyxdjbService;
	@Autowired
	IDictValueQuery iDictValueQuery;

	
	@GetMapping(value = "/historyPage")
	public Result<?> queryPageList(HistoryPageSearchDTO historyPageSearchDTO, Page page) {
		System.out.println(historyPageSearchDTO.toString());
		return Result.ok(khgxglCkkhyxdjbService.getByPage(page,historyPageSearchDTO));
	}
	
	/**
	 * 添加
	 *
	 * @param khgxglCkkhyxdjb
	 * @return
	 */
	@AutoLog(value = "存款客户营销登记簿表-添加")
	@ApiOperation(value="存款客户营销登记簿表-添加", notes="存款客户营销登记簿表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhgxglCkkhyxdjb khgxglCkkhyxdjb) {
		if (StringUtils.isBlank(khgxglCkkhyxdjb.getJgdm()))
			return Result.error("业务网点不能为空");
		if (StringUtils.isBlank(khgxglCkkhyxdjb.getZjhm()))
			return Result.error("证件号码不能为空");
		if (khgxglCkkhyxdjb.getYyrq() == null){
			return Result.error("预约日期不能为空");
		}
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("jgdm",khgxglCkkhyxdjb.getJgdm());
		queryWrapper.eq("zjhm",khgxglCkkhyxdjb.getZjhm());
		queryWrapper.eq("yyrq",khgxglCkkhyxdjb.getYyrq());
		List list = service.list(queryWrapper);
		if (CollUtil.isNotEmpty(list)){
			return Result.error("同一机构一个客户一天只能预约一次！");
		}

		if (StringUtils.isNotBlank(khgxglCkkhyxdjb.getYggh())){
			 String yggh = khgxglCkkhyxdjb.getYggh();
			 String ygxm = khgxglCkkhyxdjb.getYgxm();
			String[] split = yggh.split(",");
			String[] split1 = ygxm.split(",");
			for (int i = 0; i < split.length; i++) {
				khgxglCkkhyxdjb.setYybh(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID_SJYH.nextval")));
				khgxglCkkhyxdjb.setYgxm(null);
				khgxglCkkhyxdjb.setYxbl(new BigDecimal(split1[i]));
				khgxglCkkhyxdjb.setYggh(split[i]);
				khgxglCkkhyxdjbService.save(khgxglCkkhyxdjb);
			}
		}
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param khgxglCkkhyxdjb
	 * @return
	 */
	@AutoLog(value = "存款客户营销登记簿表-编辑")
	@ApiOperation(value="存款客户营销登记簿表-编辑", notes="存款客户营销登记簿表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhgxglCkkhyxdjb khgxglCkkhyxdjb) {
		khgxglCkkhyxdjbService.updateById(khgxglCkkhyxdjb);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款客户营销登记簿表-通过id删除")
	@ApiOperation(value="存款客户营销登记簿表-通过id删除", notes="存款客户营销登记簿表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khgxglCkkhyxdjbService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款客户营销登记簿表-批量删除")
	@ApiOperation(value="存款客户营销登记簿表-批量删除", notes="存款客户营销登记簿表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khgxglCkkhyxdjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款客户营销登记簿表-通过id查询")
	@ApiOperation(value="存款客户营销登记簿表-通过id查询", notes="存款客户营销登记簿表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhgxglCkkhyxdjb khgxglCkkhyxdjb = khgxglCkkhyxdjbService.getById(id);
		return Result.ok(khgxglCkkhyxdjb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khgxglCkkhyxdjb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhgxglCkkhyxdjb khgxglCkkhyxdjb) {
      return super.exportXls(request, khgxglCkkhyxdjb, KhgxglCkkhyxdjb.class, "存款客户营销登记簿表");
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
      return super.importExcel(request, response, KhgxglCkkhyxdjb.class);
  }

}
