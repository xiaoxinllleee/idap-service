package org.cmms.modules.sjxf.qtxt.fsyw.fsywjyls.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.sjxf.qtxt.fsyw.fsywjyls.entity.Fsywjyls;
import org.cmms.modules.sjxf.qtxt.fsyw.fsywjyls.service.IFsywjylsService;
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
 * @Description: 非税业务交易流水
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags="非税业务交易流水")
@RestController
@RequestMapping("/fsywjyls/fsywjyls")
public class FsywjylsController extends JeecgController<Fsywjyls, IFsywjylsService> {
	@Autowired
	private IFsywjylsService fsywjylsService;

	/**
	 * 分页列表查询
	 *
	 * @param fsywjyls
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "非税业务交易流水-分页列表查询")
	@ApiOperation(value="非税业务交易流水-分页列表查询", notes="非税业务交易流水-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Fsywjyls fsywjyls,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Fsywjyls> queryWrapper = QueryGenerator.initQueryWrapper(fsywjyls, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IFsywjylsService.class,fsywjylsService,pageNo,pageSize,queryWrapper,"workdate","worktime","ph");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param fsywjyls
	 * @return
	 */
	@AutoLog(value = "非税业务交易流水-添加")
	@ApiOperation(value="非税业务交易流水-添加", notes="非税业务交易流水-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Fsywjyls fsywjyls) {
		fsywjylsService.save(fsywjyls);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param fsywjyls
	 * @return
	 */
	@AutoLog(value = "非税业务交易流水-编辑")
	@ApiOperation(value="非税业务交易流水-编辑", notes="非税业务交易流水-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Fsywjyls fsywjyls) {
		fsywjylsService.updateById(fsywjyls);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "非税业务交易流水-通过id删除")
	@ApiOperation(value="非税业务交易流水-通过id删除", notes="非税业务交易流水-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		fsywjylsService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "非税业务交易流水-批量删除")
	@ApiOperation(value="非税业务交易流水-批量删除", notes="非税业务交易流水-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fsywjylsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "非税业务交易流水-通过id查询")
	@ApiOperation(value="非税业务交易流水-通过id查询", notes="非税业务交易流水-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Fsywjyls fsywjyls = fsywjylsService.getById(id);
		return Result.ok(fsywjyls);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param fsywjyls
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Fsywjyls fsywjyls) {
      return super.exportXls(request, fsywjyls, Fsywjyls.class, "非税业务交易流水");
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
      return super.importExcel(request, response, Fsywjyls.class);
  }

}
