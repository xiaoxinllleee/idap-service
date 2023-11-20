package org.cmms.modules.jx.ckgl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.jx.ckgl.entity.TbTjfxCkzrzzmxxx;
import org.cmms.modules.jx.ckgl.entity.TbTjfxCkzrzzmxxxVo;
import org.cmms.modules.jx.ckgl.service.ITbTjfxCkzrzzmxxxService;
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
 * @Description: 存款拓展明细信息
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款自然增长明细相关")
@RestController
@RequestMapping("/mobile/tbTjfxCkzrzzmxxxBankPmRest")
public class TbTjfxCkzrzzmxxxController extends JeecgController<TbTjfxCkzrzzmxxx, ITbTjfxCkzrzzmxxxService> {
	@Autowired
	private ITbTjfxCkzrzzmxxxService tbTjfxCkzrzzmxxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @return
	 */
	@AutoLog(value = "存款拓展明细信息-分页列表查询")
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public Result<?> page(
			@RequestParam(value="yggh",required=false) String yggh,
			@RequestParam(value="pageNo",required=false,defaultValue="1") int pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize
	)  {
			if (StringUtils.isBlank(yggh)){
				LoginUser loginUser = getLoginUser();
				String id = loginUser.getWorkNo();
				yggh=id;
			}
			Page<TbTjfxCkzrzzmxxxVo> page=new Page<>(pageNo,pageSize);
			IPage<TbTjfxCkzrzzmxxxVo>	list=tbTjfxCkzrzzmxxxService.getListByYggh(page,yggh);
		return Result.ok(list);
	}


}
