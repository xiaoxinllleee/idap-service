package org.cmms.modules.zhgl.wdckls.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;

import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.appbase.datatime.service.IBasDataJobDaysService;
import org.cmms.modules.zhgl.wdckls.entity.Wdckls;
import org.cmms.modules.zhgl.wdckls.service.IWdcklsService;
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
 * @Description: 网点存款流失
 * @Author: jeecg-boot
 * @Date:   2022-03-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="网点存款流失")
@RestController
@RequestMapping("/wdckls/wdckls")
public class WdcklsController extends JeecgController<Wdckls, IWdcklsService> {
	@Autowired
	private IWdcklsService wdcklsService;

	 @Autowired
	 IBasDataJobDaysService basDataJobDaysService;

	/**
	 * 分页列表查询
	 *
	 * @param wdckls
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "网点存款流失-分页列表查询")
	@ApiOperation(value="网点存款流失-分页列表查询", notes="网点存款流失-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Wdckls wdckls,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String qmyeS,String qmyeE,
								   String tj,
								   HttpServletRequest req) {
		QueryWrapper<Wdckls> queryWrapper = QueryGenerator.initQueryWrapper(wdckls, req.getParameterMap());
		if(!tj.isEmpty()){
			String[] split = tj.split(",");
			for (int i=0;i<split.length;i++){
				switch (split[i]){
				case   "0":
					queryWrapper.ge("qmyejsr",qmyeS);
					queryWrapper.le("qmyejsr",qmyeE);
					break;
				case "1":
					queryWrapper.ge("qmyejyc",qmyeS);
					queryWrapper.ge("qmyejyc",qmyeE);
				case "2":
					queryWrapper.ge("qmyejjc",qmyeS);
					queryWrapper.ge("qmyejjc",qmyeE);
				case "3":
					queryWrapper.ge("qmyejnc",qmyeS);
					queryWrapper.ge("qmyejnc",qmyeE);
				}
			}
		}else{
			if (!StringUtils.isBlank(qmyeS))queryWrapper.ge("qmye",qmyeS);
			if (!StringUtils.isBlank(qmyeE))queryWrapper.le("qmye",qmyeE);
		}

		Page<Wdckls> page = new Page<Wdckls>(pageNo, pageSize);
		IPage<Wdckls> pageList = wdcklsService.page(page, queryWrapper);

		return Result.ok(pageList);
	}
	/**
	 * 添加
	 *
	 * @param wdckls
	 * @return
	 */
	@AutoLog(value = "网点存款流失-添加")
	@ApiOperation(value="网点存款流失-添加", notes="网点存款流失-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Wdckls wdckls) {
		wdcklsService.save(wdckls);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param wdckls
	 * @return
	 */
	@AutoLog(value = "网点存款流失-编辑")
	@ApiOperation(value="网点存款流失-编辑", notes="网点存款流失-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Wdckls wdckls) {
		wdcklsService.updateById(wdckls);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网点存款流失-通过id删除")
	@ApiOperation(value="网点存款流失-通过id删除", notes="网点存款流失-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		wdcklsService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "网点存款流失-批量删除")
	@ApiOperation(value="网点存款流失-批量删除", notes="网点存款流失-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wdcklsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网点存款流失-通过id查询")
	@ApiOperation(value="网点存款流失-通过id查询", notes="网点存款流失-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Wdckls wdckls = wdcklsService.getById(id);
		return Result.ok(wdckls);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param wdckls
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Wdckls wdckls) {
      return super.exportXls(request, wdckls, Wdckls.class, "网点存款流失");
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
      return super.importExcel(request, response, Wdckls.class);
  }

}
