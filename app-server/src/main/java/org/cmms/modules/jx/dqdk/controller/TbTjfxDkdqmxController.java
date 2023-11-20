package org.cmms.modules.jx.dqdk.controller;

import java.math.BigDecimal;
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
import org.cmms.modules.jx.dqdk.entity.TbTjfxDkdqmx;
import org.cmms.modules.jx.dqdk.entity.TbTjfxDkdqmxVO;
import org.cmms.modules.jx.dqdk.service.ITbTjfxDkdqmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.utils.BigDecimalRoundUtil;
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
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="到期贷款")
@RestController
@RequestMapping("/mobile/tbTjfxDkdqmxBankPmRest")
public class TbTjfxDkdqmxController extends JeecgController<TbTjfxDkdqmx, ITbTjfxDkdqmxService> {
	@Autowired
	private ITbTjfxDkdqmxService tbTjfxDkdqmxService;

	@AutoLog(value = "到期贷款-分页列表查询")
	@ApiOperation(value="到期贷款-分页列表查询", notes="到期贷款-分页列表查询")
	@GetMapping(value = "/page")
	public Result<?> queryPageList(@RequestParam(value="yggh",required=true) String yggh,Page page) {
		LoginUser loginUser = getLoginUser();
		if (StringUtils.isBlank(yggh))
			yggh = loginUser.getWorkNo();
		List<TbTjfxDkdqmxVO> listByYggh = tbTjfxDkdqmxService.getListByYggh(yggh);
		IPage<TbTjfxDkdqmxVO> pageData = tbTjfxDkdqmxService.getListByYggh(page, yggh);

		int jqdqcount=0,xzyqcount=0;
		BigDecimal jqdqje= new BigDecimal(0);
		BigDecimal xzyqje= new BigDecimal(0);

		for (TbTjfxDkdqmxVO t: listByYggh) {
			if (t.getLx() == 1){
				jqdqcount++;
				jqdqje = jqdqje.add(t.getDkje());
			}else {
				xzyqcount++;
				xzyqje = xzyqje.add(t.getDkje());
			}
		}

		jqdqje = BigDecimalRoundUtil.round(jqdqje);
		xzyqje = BigDecimalRoundUtil.round(xzyqje);
		Map<String,Object> res = new HashMap<>();
		res.put("list", pageData.getRecords());
		res.put("jqdqje", jqdqje);
		res.put("xzyqje", xzyqje);
		res.put("jqdqcount",jqdqcount);
		res.put("xzyqcount",xzyqcount);
		res.put("count", pageData.getTotal());

		return Result.ok(res);
	}

}
