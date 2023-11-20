package org.cmms.modules.tjfx.zfsjmx.qhsjmx.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.zfsjmx.qhsjmx.entity.ZfsjmxQh;
import org.cmms.modules.tjfx.zfsjmx.qhsjmx.service.IZfsjmxQhService;
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
 * @Description: 全行走访数据统计
 * @Author: Penghr
 * @Date:   2020-03-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行走访数据统计")
@RestController
@RequestMapping("/tjfx.zfsjmx/qhsjmx")
public class ZfsjmxQhController extends JeecgController<ZfsjmxQh, IZfsjmxQhService> {
	@Autowired
	private IZfsjmxQhService zfsjmxQhService;

	/**
	 * 分页列表查询
	 *
	 * @param zfsjmxQh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行走访数据统计-分页列表查询")
	@ApiOperation(value="全行走访数据统计-分页列表查询", notes="全行走访数据统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZfsjmxQh zfsjmxQh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZfsjmxQh> queryWrapper = QueryGenerator.initQueryWrapper(zfsjmxQh, req.getParameterMap());
		Page<ZfsjmxQh> page = new Page<ZfsjmxQh>(pageNo, pageSize);
		IPage<ZfsjmxQh> pageList = zfsjmxQhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zfsjmxQh
	 * @return
	 */
	@AutoLog(value = "全行走访数据统计-添加")
	@ApiOperation(value="全行走访数据统计-添加", notes="全行走访数据统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZfsjmxQh zfsjmxQh) {
		zfsjmxQhService.save(zfsjmxQh);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zfsjmxQh
	 * @return
	 */
	@AutoLog(value = "全行走访数据统计-编辑")
	@ApiOperation(value="全行走访数据统计-编辑", notes="全行走访数据统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZfsjmxQh zfsjmxQh) {
		zfsjmxQhService.updateById(zfsjmxQh);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行走访数据统计-通过id删除")
	@ApiOperation(value="全行走访数据统计-通过id删除", notes="全行走访数据统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zfsjmxQhService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "全行走访数据统计-批量删除")
	@ApiOperation(value="全行走访数据统计-批量删除", notes="全行走访数据统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zfsjmxQhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行走访数据统计-通过id查询")
	@ApiOperation(value="全行走访数据统计-通过id查询", notes="全行走访数据统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZfsjmxQh zfsjmxQh = zfsjmxQhService.getById(id);
		return Result.ok(zfsjmxQh);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param zfsjmxQh
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZfsjmxQh zfsjmxQh) {
      return super.exportXls(request, zfsjmxQh, ZfsjmxQh.class, "全行走访数据统计");
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
      return super.importExcel(request, response, ZfsjmxQh.class);
    }

     /**
      * 全行走访数据统计-提取
      * @param object
      * @return
      */
    @PutMapping(value = "/init")
    public Result<?> InitDataToQh(@RequestBody JSONObject object) {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("tjyf", object.getString("tjyf"));
            zfsjmxQhService.InitDataToQh(param);
        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败！");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功！");
    }
	 /**
	  * 全行走访数据统计-提取
	  * @param object
	  * @return
	  */
	 @PutMapping(value = "/padinit")
	 public Result<?> csinit(@RequestBody JSONObject object) {
		 try {
			 SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
			 String tjyf = ft.format(object.getDate("tjyf"));
			 Map<String, String> param = new HashMap<>();
			 param.put("tjyf",tjyf );
			 zfsjmxQhService.InitDataToQh(param);
		 } catch (Exception e) {
			 log.error(e.getMessage(), "提取失败！");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功！");
	 }

 }
