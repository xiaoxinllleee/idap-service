package org.cmms.modules.jx.dqck.controller;

import java.util.*;
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
import org.cmms.modules.jx.common.entity.TbTjfxTzghxx;
import org.cmms.modules.jx.common.service.ITbTjfxTzghxxService;
import org.cmms.modules.jx.dqck.entity.TbTjfxCkdqkh;
import org.cmms.modules.jx.dqck.entity.TbTjfxCkdqkhVO;
import org.cmms.modules.jx.dqck.service.ITbTjfxCkdqkhService;
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
 * @Description: 存款到期
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款到期")
@RestController
@RequestMapping("/mobile/tbTjfxCkdqkhBankPmRest")
public class TbTjfxCkdqkhController extends JeecgController<TbTjfxCkdqkh, ITbTjfxCkdqkhService> {
	@Autowired
	private ITbTjfxCkdqkhService tbTjfxCkdqkhService;
	@Autowired
	ITbTjfxTzghxxService tbTjfxTzghxxService;
	

	@AutoLog(value = "存款到期-分页列表查询")
	@ApiOperation(value="存款到期-分页列表查询", notes="存款到期-分页列表查询")
	@GetMapping(value = "/page")
	public Result<?> queryPageList(Page page,@RequestParam(value="yggh",required=false) String yggh) {
		LoginUser loginUser = getLoginUser();
		if (StringUtils.isBlank(yggh))
			yggh = loginUser.getWorkNo();
		IPage<TbTjfxCkdqkhVO> listByPage = tbTjfxCkdqkhService.getListByPage(page, yggh);
		Map<String,Object> res = new HashMap<>();
		res.put("list", listByPage);
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("yggh",yggh);
		List<TbTjfxTzghxx> list = tbTjfxTzghxxService.list(queryWrapper);
		if (CollUtil.isNotEmpty(list)){
			res.put("dqcount",list.get(0).getDqghbs());
		}else {
			res.put("dqcount",0);
		}
		return Result.ok(res);
	}


}
