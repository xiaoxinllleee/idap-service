package org.cmms.modules.tjfx.zfsjtj.qhhfsjmx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.zfsjtj.qhhfsjmx.entity.Hfsjmx;
import org.cmms.modules.tjfx.zfsjtj.qhhfsjmx.service.IHfsjmxService;
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
 * @Description: 回访数据明细
 * @Author: jeecg-boot
 * @Date:   2023-05-05
 * @Version: V1.0
 */
@Slf4j
@Api(tags="回访数据明细")
@RestController
@RequestMapping("/qhhfsjmx/hfsjmx")
public class HfsjmxController extends JeecgController<Hfsjmx, IHfsjmxService> {
	@Autowired
	private IHfsjmxService hfsjmxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param hfsjmx
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "回访数据明细-分页列表查询")
	@ApiOperation(value="回访数据明细-分页列表查询", notes="回访数据明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Hfsjmx hfsjmx,String daysBegin, String daysEnd,String sfqh,
								   @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String zhpfr=hfsjmx.getZhpfr();
		hfsjmx.setZhpfr(null);
		QueryWrapper<Hfsjmx> queryWrapper = QueryGenerator.initQueryWrapper(hfsjmx, req.getParameterMap());
		String qydm=getRedisQydm();
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		if (StringUtils.isNotBlank(sfqh) && "2".equals(sfqh)) {
			if (StringUtils.isNotBlank(qydm) && qydm.equals(QydmEnums.QIYANG.getQydmCode()) && !"admin".equals(loginUser.getUsername())) {
				String orgZzbz = loginUser.getOrgCode();
				if (StringUtils.isNotBlank(orgZzbz)) {
					queryWrapper.eq("zzbz", orgZzbz);
				}
			}
		}
		if (qydm.equals(QydmEnums.YONGXING.getQydmCode()) || qydm.equals(QydmEnums.LANSHAN.getQydmCode())){
			queryWrapper.in("khlx","1","2","3","4");
		}else {
			queryWrapper.in("khlx","1","2");
		}
		if (StringUtils.isNotBlank(daysBegin) && StringUtils.isNotBlank(daysEnd)) {
			queryWrapper.between("to_char(hfrq,'yyyyMMdd')", daysBegin, daysEnd);
		}
		if (StringUtils.isNotBlank(zhpfr)){
			queryWrapper.like("zhpfr",zhpfr);
		}
		queryWrapper.orderByDesc("hfrq");
		queryWrapper.orderByDesc("zfsj");
		Page<Hfsjmx> page = new Page<Hfsjmx>(currentPage, pageSize);
		IPage<Hfsjmx> pageList = hfsjmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param hfsjmx
	 * @return
	 */
	@AutoLog(value = "回访数据明细-添加")
	@ApiOperation(value="回访数据明细-添加", notes="回访数据明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Hfsjmx hfsjmx) {
		hfsjmxService.save(hfsjmx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param hfsjmx
	 * @return
	 */
	@AutoLog(value = "回访数据明细-编辑")
	@ApiOperation(value="回访数据明细-编辑", notes="回访数据明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Hfsjmx hfsjmx) {
		hfsjmxService.updateById(hfsjmx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "回访数据明细-通过id删除")
	@ApiOperation(value="回访数据明细-通过id删除", notes="回访数据明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		hfsjmxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "回访数据明细-批量删除")
	@ApiOperation(value="回访数据明细-批量删除", notes="回访数据明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hfsjmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "回访数据明细-通过id查询")
	@ApiOperation(value="回访数据明细-通过id查询", notes="回访数据明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Hfsjmx hfsjmx = hfsjmxService.getById(id);
		return Result.ok(hfsjmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param hfsjmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Hfsjmx hfsjmx) {
      return super.exportXls(request, hfsjmx, Hfsjmx.class, "回访数据明细");
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
      return super.importExcel(request, response, Hfsjmx.class);
  }

}
