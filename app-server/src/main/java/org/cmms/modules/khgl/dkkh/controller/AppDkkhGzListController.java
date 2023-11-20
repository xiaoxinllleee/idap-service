package org.cmms.modules.khgl.dkkh.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.encryption.RSAEncryptUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.appbase.datatime.service.IBasDataJobDaysService;
import org.cmms.modules.khgl.ckkh.entity.AppCkkhGzList;
import org.cmms.modules.khgl.dkkh.entity.AppDkkhGzList;
import org.cmms.modules.khgl.dkkh.entity.TbDkYgghdksjmx;
import org.cmms.modules.khgl.dkkh.service.IAppDkkhGzListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.dkkh.service.ITbDkYgghdksjmxService;
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
 * @Description: 贷款客户关注列表
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款客户关注列表")
@RestController
@RequestMapping("/dkkh/appDkkhGzList")
public class AppDkkhGzListController extends JeecgController<AppDkkhGzList, IAppDkkhGzListService> {
	@Autowired
	private IAppDkkhGzListService appDkkhGzListService;
	@Autowired
	private ITbDkYgghdksjmxService tbDkYgghdksjmxService;
	@Autowired
	IBasDataJobDaysService basDataJobDaysService;
	/**
	 * 分页列表查询
	 *
	 * @param appDkkhGzList
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款客户关注列表-分页列表查询")
	@ApiOperation(value="贷款客户关注列表-分页列表查询", notes="贷款客户关注列表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppDkkhGzList appDkkhGzList,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppDkkhGzList> queryWrapper = QueryGenerator.initQueryWrapper(appDkkhGzList, req.getParameterMap());
		Page<AppDkkhGzList> page = new Page<AppDkkhGzList>(pageNo, pageSize);
		IPage<AppDkkhGzList> pageList = appDkkhGzListService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param appDkkhGzList
	 * @return
	 */
	@AutoLog(value = "贷款客户关注列表-添加")
	@ApiOperation(value="贷款客户关注列表-添加", notes="贷款客户关注列表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppDkkhGzList appDkkhGzList) {
		if (StringUtils.isNotBlank(appDkkhGzList.getZjhm()) && appDkkhGzList.getZjhm().length() > 20){
			 String zjhm = appDkkhGzList.getZjhm();
			 String s = RSAEncryptUtil.desEncrypt(zjhm);
			 appDkkhGzList.setZjhm(s);
		}

		Date maxExtDay = basDataJobDaysService.getMaxExtDay();
		String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("TB_DK_YGGHDKSJMX","TB_DK_YGGHDKSJMX_"+yyMMdd);
		RequestDataHelper.setRequestData(requestData);

		if (StringUtils.isNotBlank(appDkkhGzList.getZjhm())){
			LambdaQueryWrapper<TbDkYgghdksjmx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
			lambdaQueryWrapper.eq(TbDkYgghdksjmx::getZjhm,appDkkhGzList.getZjhm());
			lambdaQueryWrapper.eq(TbDkYgghdksjmx::getYggh,getWorkNo());
			List<TbDkYgghdksjmx> list = tbDkYgghdksjmxService.list(lambdaQueryWrapper);

			if (CollUtil.isNotEmpty(list)){
				for (int i = 0; i < list.size(); i++) {
					TbDkYgghdksjmx tbDkYgghdksjmx = list.get(i);
					AppDkkhGzList save = new AppDkkhGzList();
					save.setZjhm(appDkkhGzList.getZjhm());
					save.setYggh(getWorkNo());
					if (StringUtils.isNotBlank(tbDkYgghdksjmx.getCustType()))
						save.setCustType(tbDkYgghdksjmx.getCustType());
					if (StringUtils.isNotBlank(tbDkYgghdksjmx.getFiveClassType()))
						save.setFiveClassType(tbDkYgghdksjmx.getFiveClassType());
					if (StringUtils.isNotBlank(tbDkYgghdksjmx.getCustName()))
						save.setKhmc(tbDkYgghdksjmx.getCustName());
					service.save(save);
				}
			}
		}
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param appDkkhGzList
	 * @return
	 */
	@AutoLog(value = "贷款客户关注列表-编辑")
	@ApiOperation(value="贷款客户关注列表-编辑", notes="贷款客户关注列表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppDkkhGzList appDkkhGzList) {
		appDkkhGzListService.updateById(appDkkhGzList);
		return Result.ok("编辑成功!");
	}

	@GetMapping(value = "/delete")
	public Result<?> delete(String zjhm) {
		if (StringUtils.isNotBlank(zjhm) && zjhm.length() > 20)
			zjhm = RSAEncryptUtil.desEncrypt(zjhm.replaceAll(" ","+"));
		LambdaQueryWrapper<AppDkkhGzList> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(AppDkkhGzList::getZjhm,zjhm);
		lambdaQueryWrapper.eq(AppDkkhGzList::getYggh,getWorkNo());
		service.remove(lambdaQueryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款客户关注列表-批量删除")
	@ApiOperation(value="贷款客户关注列表-批量删除", notes="贷款客户关注列表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appDkkhGzListService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款客户关注列表-通过id查询")
	@ApiOperation(value="贷款客户关注列表-通过id查询", notes="贷款客户关注列表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppDkkhGzList appDkkhGzList = appDkkhGzListService.getById(id);
		return Result.ok(appDkkhGzList);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param appDkkhGzList
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppDkkhGzList appDkkhGzList) {
      return super.exportXls(request, appDkkhGzList, AppDkkhGzList.class, "贷款客户关注列表");
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
      return super.importExcel(request, response, AppDkkhGzList.class);
  }

}
