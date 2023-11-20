package org.cmms.modules.jx.ckyx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
import org.cmms.modules.jx.ckyx.entity.TbTjfxCktzmxxx;
import org.cmms.modules.jx.ckyx.entity.TbTjfxCktzmxxxVO;
import org.cmms.modules.jx.ckyx.service.ITbTjfxCktzmxxxService;
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
 * @Description: 存款拓展明细
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款拓展明细")
@RestController
@RequestMapping("/mobile/tbTjfxCktzmxxxBankPmRest")
public class TbTjfxCktzmxxxController extends JeecgController<TbTjfxCktzmxxx, ITbTjfxCktzmxxxService> {
	@Autowired
	private ITbTjfxCktzmxxxService tbTjfxCktzmxxxService;
	
	@GetMapping(value = "/page")
	public Result<?> queryPageList(@RequestParam(value="yggh",required=false) String yggh,Page page) {
		LoginUser loginUser = getLoginUser();
		if (StringUtils.isBlank(yggh))
			yggh = loginUser.getWorkNo();
		return Result.ok(tbTjfxCktzmxxxService.getListByPage(page, yggh));
	}

}
