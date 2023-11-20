package org.cmms.modules.performance.mobilebank.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.performance.mobilebank.entity.WdyjSjyhkhghxxb;
import org.cmms.modules.performance.mobilebank.service.IWdyjSjyhkhghxxbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 手机银行客户管户信息
 * @Author: Penghr
 * @Date:   2021-02-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="手机银行客户管户信息")
@RestController
@RequestMapping("/performance/sjyhkhghxx")
public class WdyjSjyhkhghxxbController extends JeecgController<WdyjSjyhkhghxxb, IWdyjSjyhkhghxxbService> {
	@Autowired
	private IWdyjSjyhkhghxxbService wdyjSjyhkhghxxbService;

	/**
	 * 分页列表查询
	 *
	 * @param wdyjSjyhkhghxxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "手机银行客户管户信息-分页列表查询")
	@ApiOperation(value="手机银行客户管户信息-分页列表查询", notes="手机银行客户管户信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(WdyjSjyhkhghxxb wdyjSjyhkhghxxb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WdyjSjyhkhghxxb> queryWrapper = QueryGenerator.initQueryWrapper(wdyjSjyhkhghxxb, req.getParameterMap());
		Page<WdyjSjyhkhghxxb> page = new Page<WdyjSjyhkhghxxb>(pageNo, pageSize);
		IPage<WdyjSjyhkhghxxb> pageList = wdyjSjyhkhghxxbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param wdyjSjyhkhghxxb
	 * @return
	 */
	@AutoLog(value = "手机银行客户管户信息-添加")
	@ApiOperation(value="手机银行客户管户信息-添加", notes="手机银行客户管户信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody WdyjSjyhkhghxxb wdyjSjyhkhghxxb) {
		wdyjSjyhkhghxxbService.save(wdyjSjyhkhghxxb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param wdyjSjyhkhghxxb
	 * @return
	 */
	@AutoLog(value = "手机银行客户管户信息-编辑")
	@ApiOperation(value="手机银行客户管户信息-编辑", notes="手机银行客户管户信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody WdyjSjyhkhghxxb wdyjSjyhkhghxxb) {
		wdyjSjyhkhghxxbService.updateById(wdyjSjyhkhghxxb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "手机银行客户管户信息-通过id删除")
	@ApiOperation(value="手机银行客户管户信息-通过id删除", notes="手机银行客户管户信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		wdyjSjyhkhghxxbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "手机银行客户管户信息-批量删除")
	@ApiOperation(value="手机银行客户管户信息-批量删除", notes="手机银行客户管户信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.wdyjSjyhkhghxxbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "手机银行客户管户信息-通过id查询")
	@ApiOperation(value="手机银行客户管户信息-通过id查询", notes="手机银行客户管户信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WdyjSjyhkhghxxb wdyjSjyhkhghxxb = wdyjSjyhkhghxxbService.getById(id);
		return Result.ok(wdyjSjyhkhghxxb);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param wdyjSjyhkhghxxb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WdyjSjyhkhghxxb wdyjSjyhkhghxxb) {
        return super.exportXls(request, wdyjSjyhkhghxxb, WdyjSjyhkhghxxb.class, "手机银行客户管户信息");
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
        return super.importExcel(request, response, WdyjSjyhkhghxxb.class);
    }

}
