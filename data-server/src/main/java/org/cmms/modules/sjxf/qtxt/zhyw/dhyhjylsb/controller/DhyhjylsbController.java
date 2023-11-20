package org.cmms.modules.sjxf.qtxt.zhyw.dhyhjylsb.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.sjxf.qtxt.zhyw.dhyhjylsb.entity.Dhyhjylsb;
import org.cmms.modules.sjxf.qtxt.zhyw.dhyhjylsb.service.IDhyhjylsbService;
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
 * @Description: 电话银行交易流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags="电话银行交易流水表")
@RestController
@RequestMapping("/dhyhjylsb/dhyhjylsb")
public class DhyhjylsbController extends JeecgController<Dhyhjylsb, IDhyhjylsbService> {
	@Autowired
	private IDhyhjylsbService dhyhjylsbService;

	/**
	 * 分页列表查询
	 *
	 * @param dhyhjylsb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "电话银行交易流水表-分页列表查询")
	@ApiOperation(value="电话银行交易流水表-分页列表查询", notes="电话银行交易流水表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dhyhjylsb dhyhjylsb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dhyhjylsb> queryWrapper = QueryGenerator.initQueryWrapper(dhyhjylsb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IDhyhjylsbService.class,dhyhjylsbService,pageNo,pageSize,queryWrapper,"seqno","tradetime","tradedate");
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dhyhjylsb
	 * @return
	 */
	@AutoLog(value = "电话银行交易流水表-添加")
	@ApiOperation(value="电话银行交易流水表-添加", notes="电话银行交易流水表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dhyhjylsb dhyhjylsb) {
		dhyhjylsbService.save(dhyhjylsb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dhyhjylsb
	 * @return
	 */
	@AutoLog(value = "电话银行交易流水表-编辑")
	@ApiOperation(value="电话银行交易流水表-编辑", notes="电话银行交易流水表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dhyhjylsb dhyhjylsb) {
		dhyhjylsbService.updateById(dhyhjylsb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "电话银行交易流水表-通过id删除")
	@ApiOperation(value="电话银行交易流水表-通过id删除", notes="电话银行交易流水表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dhyhjylsbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "电话银行交易流水表-批量删除")
	@ApiOperation(value="电话银行交易流水表-批量删除", notes="电话银行交易流水表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dhyhjylsbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "电话银行交易流水表-通过id查询")
	@ApiOperation(value="电话银行交易流水表-通过id查询", notes="电话银行交易流水表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dhyhjylsb dhyhjylsb = dhyhjylsbService.getById(id);
		return Result.ok(dhyhjylsb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dhyhjylsb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dhyhjylsb dhyhjylsb) {
      return super.exportXls(request, dhyhjylsb, Dhyhjylsb.class, "电话银行交易流水表");
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
      return super.importExcel(request, response, Dhyhjylsb.class);
  }

}
