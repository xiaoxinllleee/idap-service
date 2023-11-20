package org.cmms.modules.jx.cktzxxhz.controller;

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
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.jx.cktzxxhz.entity.TbTjfxCktzhzxx;
import org.cmms.modules.jx.cktzxxhz.service.ITbTjfxCktzhzxxService;
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
 * @Description: 存款拓展汇总表
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款拓展汇总表")
@RestController
@RequestMapping("/mobile/tbTjfxCktzhzxxBankPmRest/")
public class TbTjfxCktzhzxxController extends JeecgController<TbTjfxCktzhzxx, ITbTjfxCktzhzxxService> {
	@Autowired
	private ITbTjfxCktzhzxxService tbTjfxCktzhzxxService;

	@RequestMapping("/getInfo")
	 public Result<?> getInfo(@RequestParam(value="yggh",required=false) String yggh){

		if (StringUtils.isBlank(yggh)){
			LoginUser loginUser = getLoginUser();
			String id = loginUser.getWorkNo();
			yggh = id;
		}

		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("yggh",yggh);

		List<TbTjfxCktzhzxx> list = tbTjfxCktzhzxxService.list(queryWrapper);
		if (CollUtil.isNotEmpty(list))
			return Result.ok(list.get(0));
		return Result.ok();
	}


}
