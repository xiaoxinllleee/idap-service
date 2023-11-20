package org.cmms.modules.khgl.ckkh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.encryption.RSAEncryptUtil;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.ckkh.entity.AppCkkhGzList;
import org.cmms.modules.khgl.ckkh.service.IAppCkkhGzListService;
import org.cmms.modules.khgl.ckkh.service.ITbCkYgghcksjmxService;
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
 * @Description: app存款客户关注列表
 * @Author: jeecg-boot
 * @Date:   2022-03-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="app存款客户关注列表")
@RestController
@RequestMapping("/khgl/appCkkhGzList")
public class AppCkkhGzListController extends JeecgController<AppCkkhGzList, IAppCkkhGzListService> {


	@Autowired
	ITbCkYgghcksjmxService tbCkYgghcksjmxService;

	/**
	 * 分页列表查询
	 *
	 * @param appCkkhGzList
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "app存款客户关注列表-分页列表查询")
	@ApiOperation(value="app存款客户关注列表-分页列表查询", notes="app存款客户关注列表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppCkkhGzList appCkkhGzList,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppCkkhGzList> queryWrapper = QueryGenerator.initQueryWrapper(appCkkhGzList, req.getParameterMap());
		Page<AppCkkhGzList> page = new Page<AppCkkhGzList>(pageNo, pageSize);
		IPage<AppCkkhGzList> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param appCkkhGzList
	 * @return
	 */
	@AutoLog(value = "app存款客户关注列表-添加")
	@ApiOperation(value="app存款客户关注列表-添加", notes="app存款客户关注列表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppCkkhGzList appCkkhGzList) {
		if (StringUtils.isNotBlank(appCkkhGzList.getZjhm()) && appCkkhGzList.getZjhm().length() > 20) {
			String zjhm = appCkkhGzList.getZjhm();
			log.info("==={}===",appCkkhGzList.getZjhm());
			String s = RSAEncryptUtil.desEncrypt(zjhm);
			log.info("==={}===",s);
			appCkkhGzList.setZjhm(s);
		}


		String redisQydm = getRedisQydm();
		if (StringUtils.isNotBlank(redisQydm) && QydmEnums.CILI.getQydmCode().equals(redisQydm)){
			AppCkkhGzList save = new AppCkkhGzList();
			save.setZjhm(appCkkhGzList.getZjhm());
			save.setYggh(getWorkNo());
			service.save(save);
		}else {
			if (StringUtils.isNotBlank(appCkkhGzList.getZjhm())){
				List<String> acctGrpByZjhm = tbCkYgghcksjmxService.getAcctGrpByZjhm(appCkkhGzList.getZjhm(), getWorkNo());
				AppCkkhGzList save = new AppCkkhGzList();
				save.setZjhm(appCkkhGzList.getZjhm());
				save.setYggh(getWorkNo());
				if (CollUtil.isNotEmpty(acctGrpByZjhm)){
					for (int i = 0; i < acctGrpByZjhm.size(); i++) {
						save.setAcctGrp(acctGrpByZjhm.get(i));
						service.save(save);
					}
				}else {
					service.save(save);
				}
			}
		}
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param appCkkhGzList
	 * @return
	 */
	@AutoLog(value = "app存款客户关注列表-编辑")
	@ApiOperation(value="app存款客户关注列表-编辑", notes="app存款客户关注列表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppCkkhGzList appCkkhGzList) {
		//查询证件号码是对公还是对私

		service.updateById(appCkkhGzList);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param zjhm
	 * @return
	 */
	@AutoLog(value = "app存款客户关注列表-通过id删除")
	@ApiOperation(value="app存款客户关注列表-通过id删除", notes="app存款客户关注列表-通过id删除")
	@RequestMapping(value = "/delete")
	public Result<?> delete(String zjhm) {
		if (StringUtils.isNotBlank(zjhm) && zjhm.length() > 20)
			zjhm = RSAEncryptUtil.desEncrypt(zjhm.replaceAll(" ","+"));

		LambdaQueryWrapper<AppCkkhGzList> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(AppCkkhGzList::getZjhm,zjhm);
		lambdaQueryWrapper.eq(AppCkkhGzList::getYggh,getWorkNo());
		service.remove(lambdaQueryWrapper);
		return Result.ok("删除成功!");
	}
	

	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "app存款客户关注列表-通过id查询")
	@ApiOperation(value="app存款客户关注列表-通过id查询", notes="app存款客户关注列表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppCkkhGzList appCkkhGzList = service.getById(id);
		return Result.ok(appCkkhGzList);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param appCkkhGzList
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppCkkhGzList appCkkhGzList) {
      return super.exportXls(request, appCkkhGzList, AppCkkhGzList.class, "app存款客户关注列表");
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
      return super.importExcel(request, response, AppCkkhGzList.class);
  }

}
