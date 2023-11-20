package org.cmms.modules.dkjkpt.dhgztxxt.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dhgztxxt.entity.DkjkptDhjcFjxx;
import org.cmms.modules.dkjkpt.dhgztxxt.entity.FxgljcDhjctx;
import org.cmms.modules.dkjkpt.dhgztxxt.entity.FxgljcDhjctxVO;
import org.cmms.modules.dkjkpt.dhgztxxt.service.IDkjkptDhjcFjxxService;
import org.cmms.modules.dkjkpt.dhgztxxt.service.IFxgljcDhjctxService;
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.entity.FxgljcDhjc;
import org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.service.IFxgljcDhjcService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 湘潭贷后检查提醒
 * @Author: jeecg-boot
 * @Date:   2023-09-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="湘潭贷后检查提醒")
@RestController
@RequestMapping("/dhjctx110/fxgljcDhjctx")
public class FxgljcDhjctxController extends JeecgController<FxgljcDhjctx, IFxgljcDhjctxService> {
	@Autowired
	private IFxgljcDhjctxService fxgljcDhjctxService;
	@Autowired
	private IFxgljcDhjcService fxgljcDhjcService;
	@Autowired
	private IDkjkptDhjcFjxxService dkjkptDhjcFjxxService;


	/**
	 * 分页列表查询
	 *
	 * @param fxgljcDhjctx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "湘潭贷后检查提醒-分页列表查询")
	@ApiOperation(value="湘潭贷后检查提醒-分页列表查询", notes="湘潭贷后检查提醒-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(FxgljcDhjctx fxgljcDhjctx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FxgljcDhjctx> queryWrapper = QueryGenerator.initQueryWrapper(fxgljcDhjctx, req.getParameterMap());
		queryWrapper.ge("jkrq",DateUtil.parse("2023-01-01"));
		Page<FxgljcDhjctx> page = new Page<FxgljcDhjctx>(pageNo, pageSize);
		IPage<FxgljcDhjctx> pageList = fxgljcDhjctxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param fxgljcDhjctx
	 * @return
	 */
	@AutoLog(value = "湘潭贷后检查提醒-添加")
	@ApiOperation(value="湘潭贷后检查提醒-添加", notes="湘潭贷后检查提醒-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody FxgljcDhjctx fxgljcDhjctx) {
		fxgljcDhjctxService.save(fxgljcDhjctx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "湘潭贷后检查提醒-编辑")
	@ApiOperation(value="湘潭贷后检查提醒-编辑", notes="湘潭贷后检查提醒-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody FxgljcDhjctxVO fxgljcDhjctxVO) {
		FxgljcDhjctx fxgljcDhjctx = new FxgljcDhjctx();
		BeanUtils.copyProperties(fxgljcDhjctxVO, fxgljcDhjctx);
		JSONArray fjxxs = fxgljcDhjctxVO.getImgdate();
		String fjnf = fxgljcDhjctxVO.getFjnf();
		String zllx = fxgljcDhjctxVO.getZllx();
		String bz = fxgljcDhjctxVO.getBz();
		QueryWrapper<FxgljcDhjctx> queryWrapper= new QueryWrapper<>();
		queryWrapper.eq("tjnf",fxgljcDhjctx.getTjnf());
		queryWrapper.eq("jgdm",fxgljcDhjctx.getJgdm());
		queryWrapper.eq("zjhm",fxgljcDhjctx.getZjhm());
		fxgljcDhjctxService.update(fxgljcDhjctx,queryWrapper);

		QueryWrapper<FxgljcDhjc> dhjc = new QueryWrapper<>();
		dhjc.eq("sjrq",fxgljcDhjctx.getTjnf());
		dhjc.eq("zjhm",fxgljcDhjctx.getZjhm());
		dhjc.eq("jgdm",fxgljcDhjctx.getJgdm());
		FxgljcDhjc fxgljcDhjc = fxgljcDhjcService.getOne(dhjc);
		if (fxgljcDhjc != null){
			fxgljcDhjc.setBcnjsfywc("1");
			fxgljcDhjcService.update(fxgljcDhjc,dhjc);
		}

		DkjkptDhjcFjxx wjxx = new DkjkptDhjcFjxx();
		if (fjxxs != null && fjxxs.size() > 0) {
			for (int i = 0; i < fjxxs.size(); i++) {
				if (fjxxs != null) {
					String wllj = uploadpath + "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
					String fwlj =  "/" +fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
					String size =  fjxxs.getJSONObject(i).getString("size");
					wjxx.setFjlx(fxgljcDhjctx.getTxlx());
					wjxx.setJgdm(fxgljcDhjctx.getJgdm());
					wjxx.setFjnf(DateUtil.parse(fjnf));
					wjxx.setZjhm(fxgljcDhjctx.getZjhm());
					wjxx.setWjdx(new BigDecimal(size));
					wjxx.setFwlj(fwlj);
					wjxx.setFjsjjd(zllx);
					wjxx.setWjlj(wllj);
					wjxx.setBz(bz);
					wjxx.setLrbz(1);
					wjxx.setLrr(getUsername());
					wjxx.setLrsj(new Timestamp(System.currentTimeMillis()));
					dkjkptDhjcFjxxService.save(wjxx);
				}
			}
		}

		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湘潭贷后检查提醒-通过id删除")
	@ApiOperation(value="湘潭贷后检查提醒-通过id删除", notes="湘潭贷后检查提醒-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		fxgljcDhjctxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "湘潭贷后检查提醒-批量删除")
	@ApiOperation(value="湘潭贷后检查提醒-批量删除", notes="湘潭贷后检查提醒-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fxgljcDhjctxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "湘潭贷后检查提醒-通过id查询")
	@ApiOperation(value="湘潭贷后检查提醒-通过id查询", notes="湘潭贷后检查提醒-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		FxgljcDhjctx fxgljcDhjctx = fxgljcDhjctxService.getById(id);
		return Result.ok(fxgljcDhjctx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param fxgljcDhjctx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, FxgljcDhjctx fxgljcDhjctx) {
      return super.exportXls(request, fxgljcDhjctx, FxgljcDhjctx.class, "湘潭贷后检查提醒");
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
      return super.importExcel(request, response, FxgljcDhjctx.class);
  }

}
