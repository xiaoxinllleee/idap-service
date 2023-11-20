package org.cmms.modules.home.controller;

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
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.appbase.vo.Tabbar;
import org.cmms.modules.home.entity.AppHomepage;
import org.cmms.modules.home.entity.AppHomepageVO;
import org.cmms.modules.home.service.IAppHomepageService;
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
 * @Description: APP主页配置
 * @Author: jeecg-boot
 * @Date:   2022-02-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="APP主页配置")
@RestController
@RequestMapping("/home/appHomepage")
public class AppHomepageController extends JeecgController<AppHomepage, IAppHomepageService> {
	
	/**
	 * 分页列表查询
	 *
	 * @param appHomepage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "APP主页配置-分页列表查询")
	@ApiOperation(value="APP主页配置-分页列表查询", notes="APP主页配置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppHomepage appHomepage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppHomepage> queryWrapper = QueryGenerator.initQueryWrapper(appHomepage, req.getParameterMap());
		Page<AppHomepage> page = new Page<AppHomepage>(pageNo, pageSize);
		IPage<AppHomepage> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/listAll")
	 public Result<?> listAll() {
		AppHomepageVO result = new AppHomepageVO();
		List<AppHomepage> list = service.getByUserName(getUsername());
		List<AppHomepage> list1 = new ArrayList<>();
		List<AppHomepage> list2 = new ArrayList<>();
		List<AppHomepage> list3 = new ArrayList<>();
		if (CollUtil.isNotEmpty(list)){
			for (int i = 0; i < list.size(); i++) {
				AppHomepage appHomepage = list.get(i);
				if (StringUtils.isNotBlank(appHomepage.getType())  && "1".equals(appHomepage.getType())){
					list1.add(appHomepage);
				}else if (StringUtils.isNotBlank(appHomepage.getType())  && "2".equals(appHomepage.getType())){
					list2.add(appHomepage);
				}else if (StringUtils.isNotBlank(appHomepage.getType())  && "3".equals(appHomepage.getType())){
					list3.add(appHomepage);
				}
			}
		}
		if (CollUtil.isNotEmpty(list1))
			result.setKhgls(list1);
		 if (CollUtil.isNotEmpty(list2))
			 result.setYwgls(list2);
		 if (CollUtil.isNotEmpty(list3))
			 result.setZhgls(list3);
		 return Result.ok(result);
	 }


	 @RequestMapping("/getFoot")
	 public Result<?> getFoot(){
		Tabbar tabbar = new Tabbar();
		List<AppHomepage> footByUserName = service.getFootByUserName(getUsername());
		if (CollUtil.isNotEmpty(footByUserName)){
			for (int i = 0; i < footByUserName.size(); i++) {
				AppHomepage appHomepage = footByUserName.get(i);
				if (StringUtils.isNotBlank(appHomepage.getTitle())){
//					if (appHomepage.getTitle().contains("业绩")){
//						tabbar.setIsyj("1");
//					}
					if (appHomepage.getTitle().contains("机构")){
						tabbar.setJg("1");
					}
				}
			}
		}
		 return Result.ok(tabbar);
	 }

	 @RequestMapping("/getAll")
	 public Result<?> getAll(){
		List<AppHomepage> list = service.list();
		return Result.ok(list);
	 }
	
	/**
	 * 添加
	 *
	 * @param appHomepage
	 * @return
	 */
	@AutoLog(value = "APP主页配置-添加")
	@ApiOperation(value="APP主页配置-添加", notes="APP主页配置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppHomepage appHomepage) {
		appHomepage.setCreateTime(new Date());
		service.save(appHomepage);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param appHomepage
	 * @return
	 */
	@AutoLog(value = "APP主页配置-编辑")
	@ApiOperation(value="APP主页配置-编辑", notes="APP主页配置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppHomepage appHomepage) {
		appHomepage.setUpdateTime(new Date());
		service.updateById(appHomepage);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "APP主页配置-通过id删除")
	@ApiOperation(value="APP主页配置-通过id删除", notes="APP主页配置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "APP主页配置-批量删除")
	@ApiOperation(value="APP主页配置-批量删除", notes="APP主页配置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "APP主页配置-通过id查询")
	@ApiOperation(value="APP主页配置-通过id查询", notes="APP主页配置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppHomepage appHomepage = service.getById(id);
		return Result.ok(appHomepage);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param appHomepage
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppHomepage appHomepage) {
      return super.exportXls(request, appHomepage, AppHomepage.class, "APP主页配置");
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
      return super.importExcel(request, response, AppHomepage.class);
  }

}
