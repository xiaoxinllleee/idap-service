package org.cmms.modules.gr.mrjbxx.controller;

import java.text.DecimalFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.gr.mrjbxx.entity.TbTjfxMrjbxx;
import org.cmms.modules.gr.mrjbxx.service.ITbTjfxMrjbxxService;
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
 * @Description: 每日简报信息
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="每日简报信息")
@RestController
@RequestMapping("/mobile/tbTjfxMrjbxx")
public class TbTjfxMrjbxxController extends JeecgController<TbTjfxMrjbxx, ITbTjfxMrjbxxService> {
	 @Autowired(required = true)
	 private ITbTjfxMrjbxxService iTbTjfxMrjbxxService;
	@AutoLog(value = "获取每日简报信息-获取每日简报信息")
	@ApiOperation(value = "获取每日简报信息", notes = "获取每日简报信息")
	@RequestMapping(value="/getInfoByYggh",method=RequestMethod.GET)
	public Result<?> getInfoByYggh(@RequestParam(value="yggh") String yggh)  {
		QueryWrapper<TbTjfxMrjbxx> queryWrapper=new QueryWrapper<TbTjfxMrjbxx>();
		queryWrapper.eq("yggh",yggh);
		queryWrapper.orderByAsc("XSSX");
		List<TbTjfxMrjbxx> list = iTbTjfxMrjbxxService.list(queryWrapper);
		List<Map<String,Object>> res = new ArrayList<>();
		Map<String,Object> map;
		DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
		if(list!=null) {
			String value="";
			for(TbTjfxMrjbxx mrjb:list) {
				if(mrjb.getSfxs()==1) {
					map = new HashMap<>();
					map.put(mrjb.getJbmc(), mrjb.getXsfh()+decimalFormat.format(mrjb.getJbjg())+mrjb.getJbdw());
					res.add(map);
				}
			}
		}
		return Result.ok(res);
	}
	

}
