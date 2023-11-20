package org.cmms.modules.sjxf.qtxt.etcxx.djkdkjlb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.models.auth.In;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.sjxf.qtxt.etcxx.djkdkjlb.entity.Djkdkjlb;
import org.cmms.modules.sjxf.qtxt.etcxx.djkdkjlb.service.IDjkdkjlbService;
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
 * @Description: ETC贷记卡垫款记录表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="ETC贷记卡垫款记录表")
@RestController
@RequestMapping("/djkdkjlb/djkdkjlb")
public class DjkdkjlbController extends JeecgController<Djkdkjlb, IDjkdkjlbService> {
	@Autowired
	private IDjkdkjlbService djkdkjlbService;

	/**
	 * 分页列表查询
	 *
	 * @param djkdkjlb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ETC贷记卡垫款记录表-分页列表查询")
	@ApiOperation(value="ETC贷记卡垫款记录表-分页列表查询", notes="ETC贷记卡垫款记录表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Djkdkjlb djkdkjlb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Djkdkjlb> queryWrapper = QueryGenerator.initQueryWrapper(djkdkjlb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDjkdkjlbService.class,djkdkjlbService,pageNo,pageSize,queryWrapper,"kh","dkrq","dklsh" );
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param djkdkjlb
	 * @return
	 */
	@AutoLog(value = "ETC贷记卡垫款记录表-添加")
	@ApiOperation(value="ETC贷记卡垫款记录表-添加", notes="ETC贷记卡垫款记录表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Djkdkjlb djkdkjlb) {
		djkdkjlbService.save(djkdkjlb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param djkdkjlb
	 * @return
	 */
	@AutoLog(value = "ETC贷记卡垫款记录表-编辑")
	@ApiOperation(value="ETC贷记卡垫款记录表-编辑", notes="ETC贷记卡垫款记录表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Djkdkjlb djkdkjlb) {
		djkdkjlbService.updateById(djkdkjlb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC贷记卡垫款记录表-通过id删除")
	@ApiOperation(value="ETC贷记卡垫款记录表-通过id删除", notes="ETC贷记卡垫款记录表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		djkdkjlbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ETC贷记卡垫款记录表-批量删除")
	@ApiOperation(value="ETC贷记卡垫款记录表-批量删除", notes="ETC贷记卡垫款记录表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.djkdkjlbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ETC贷记卡垫款记录表-通过id查询")
	@ApiOperation(value="ETC贷记卡垫款记录表-通过id查询", notes="ETC贷记卡垫款记录表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Djkdkjlb djkdkjlb = djkdkjlbService.getById(id);
		return Result.ok(djkdkjlb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param djkdkjlb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Djkdkjlb djkdkjlb) {
      return super.exportXls(request, djkdkjlb, Djkdkjlb.class, "ETC贷记卡垫款记录表");
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
      return super.importExcel(request, response, Djkdkjlb.class);
  }

}
