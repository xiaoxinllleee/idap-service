package org.cmms.modules.jx.dkkh.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.appbase.tbtjfxcssz.service.ITbTjfxCsszService;
import org.cmms.modules.jx.common.entity.TbTjfxQhdksj;
import org.cmms.modules.jx.common.mapper.TbTjfxDkghhzxxMapper;
import org.cmms.modules.jx.common.service.ITBTjfxService;
import org.cmms.modules.jx.dkkh.entity.TbTjfxDkghmxxxx;
import org.cmms.modules.jx.dkkh.service.ITbTjfxDkghmxxxxService;
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
 * @Description: 贷款管户明细信息
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款管户明细信息")
@RestController
@RequestMapping("/mobile/tbTjfxDkghmxxxBankPmRest")
public class TbTjfxDkghmxxxxController extends JeecgController<TbTjfxDkghmxxxx, ITbTjfxDkghmxxxxService> {
	@Autowired
	private ITbTjfxDkghmxxxxService tbTjfxDkghmxxxxService;
	@Autowired
	ITBTjfxService itbTjfxServicel;

	@Autowired
	ITbTjfxCsszService tbTjfxCsszService;
	@Autowired
	TbTjfxDkghhzxxMapper tbTjfxDkghhzxxMapper;

	 @RequestMapping(value = "getBankWideLoans", method = RequestMethod.GET)
	 public Result<?> getBankWideLoans() {
		 TbTjfxQhdksj bankWideLoans = itbTjfxServicel.getBankWideLoans();
		 return Result.ok(bankWideLoans);
	 }

	 /**
	  * @return
	  * @Author lx
	  * @Description //TODO 获取机构贷款排行榜
	  * @Date 2021/5/28
	  * @Param
	  **/
	 @RequestMapping(value = "getListOfInstitutionalLoanPage", method = RequestMethod.GET)
	 public Result<?> getListOfInstitutionalLoans(@RequestParam(value = "zzbz", required = false) String zzbz
			 ,Page page) {

		 try {
			 return Result.ok(tbTjfxDkghmxxxxService.getListOfInstitutionalLoans(page,zzbz));
		 } catch (Exception e) {
			 e.printStackTrace();
		 }

		 return Result.error("请求错误");
	 }


	 /*@RequestMapping(value="/page",method= RequestMethod.GET)
	 public Result<?> page(Page page, @RequestParam(value="yggh",required=true) String yggh) {
		 LoginUser loginUser = getLoginUser();
		 if (StringUtils.isBlank(yggh))
			 yggh = loginUser.getWorkNo();
		 QueryWrapper queryWrapper = new QueryWrapper();
		 queryWrapper.eq("yggh",yggh);
		 Date theMaxDate = tbTjfxCsszService.getTheMaxDate();
		 if (theMaxDate != null)
		 	queryWrapper.eq("tjrq",theMaxDate);
		 List list = tbTjfxDkghhzxxMapper.selectList(queryWrapper);
		 return Result.ok(tbTjfxDkghmxxxxService.getPageByYggh(page, yggh));
	 }*/

}
