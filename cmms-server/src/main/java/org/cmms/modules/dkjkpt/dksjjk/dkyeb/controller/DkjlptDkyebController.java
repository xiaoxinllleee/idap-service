package org.cmms.modules.dkjkpt.dksjjk.dkyeb.controller;

import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dksjjk.dkyeb.entity.DkjlptDkyeb;
import org.cmms.modules.dkjkpt.dksjjk.dkyeb.service.IDkjlptDkyebService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款余额表
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款余额表")
@RestController
@RequestMapping("/dkjkpt/dksjjk/dkyeb")
public class DkjlptDkyebController extends JeecgController<DkjlptDkyeb, IDkjlptDkyebService> {
	@Autowired
	private IDkjlptDkyebService dkjlptDkyebService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjlptDkyeb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款余额表-分页列表查询")
	@ApiOperation(value="贷款余额表-分页列表查询", notes="贷款余额表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DkjlptDkyeb dkjlptDkyeb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DkjlptDkyeb> queryWrapper = QueryGenerator.initQueryWrapper(dkjlptDkyeb, req.getParameterMap());
		Page<DkjlptDkyeb> page = new Page<DkjlptDkyeb>(pageNo, pageSize);
		Date parse = DateUtil.parse("2022-12-31", "yyyy-MM-dd");
		queryWrapper.ge("dqrq", parse);
		queryWrapper.orderByDesc("tjyf","zjhm");
		IPage<DkjlptDkyeb> pageList = dkjlptDkyebService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjlptDkyeb
	 * @return
	 */
	@AutoLog(value = "贷款余额表-添加")
	@ApiOperation(value="贷款余额表-添加", notes="贷款余额表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DkjlptDkyeb dkjlptDkyeb) {
		dkjlptDkyebService.save(dkjlptDkyeb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjlptDkyeb
	 * @return
	 */
	@AutoLog(value = "贷款余额表-编辑")
	@ApiOperation(value="贷款余额表-编辑", notes="贷款余额表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DkjlptDkyeb dkjlptDkyeb) {
		dkjlptDkyebService.updateById(dkjlptDkyeb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款余额表-通过id删除")
	@ApiOperation(value="贷款余额表-通过id删除", notes="贷款余额表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkjlptDkyebService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款余额表-批量删除")
	@ApiOperation(value="贷款余额表-批量删除", notes="贷款余额表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjlptDkyebService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款余额表-通过id查询")
	@ApiOperation(value="贷款余额表-通过id查询", notes="贷款余额表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DkjlptDkyeb dkjlptDkyeb = dkjlptDkyebService.getById(id);
		return Result.ok(dkjlptDkyeb);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param dkjlptDkyeb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DkjlptDkyeb dkjlptDkyeb) {
      return super.exportXls(request, dkjlptDkyeb, DkjlptDkyeb.class, "贷款余额表");
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
      return super.importExcel(request, response, DkjlptDkyeb.class);
    }

	 /**
	  * 贷款余额表-提取
	  * @param object
	  * @return
	  */
    @RequestMapping(value = "/init", method = RequestMethod.PUT)
	public Result<?> InitData(@RequestBody JSONObject object) {
		System.out.println("tjyf------"+object.getString("tjrq"));
    	try {
    		dkjlptDkyebService.InitData(object.getString("tjrq"));
		} catch (Exception e) {
			log.error("提取失败！",e.getMessage());
			return Result.error(e.getMessage());
		}
    	return Result.ok("提取成功！");
	}
}
