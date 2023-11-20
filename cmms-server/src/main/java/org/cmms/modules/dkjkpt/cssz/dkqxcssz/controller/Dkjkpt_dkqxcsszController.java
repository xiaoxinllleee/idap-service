package org.cmms.modules.dkjkpt.cssz.dkqxcssz.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.dkjkpt.cssz.dkqxcssz.entity.Dkjkpt_dkqxcssz;
import org.cmms.modules.dkjkpt.cssz.dkqxcssz.service.IDkjkpt_dkqxcsszService;
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
 * @Description: 贷款期限参数设置
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款期限参数设置")
@RestController
@RequestMapping("/DKJKPT_DKQXCSSZ/dkjkpt_dkqxcssz")
public class Dkjkpt_dkqxcsszController extends JeecgController<Dkjkpt_dkqxcssz, IDkjkpt_dkqxcsszService> {
	@Autowired
	private IDkjkpt_dkqxcsszService dkjkpt_dkqxcsszService;

	/**
	 * 分页列表查询
	 *
	 * @param dkjkpt_dkqxcssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款期限参数设置-分页列表查询")
	@ApiOperation(value="贷款期限参数设置-分页列表查询", notes="贷款期限参数设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkjkpt_dkqxcssz dkjkpt_dkqxcssz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkjkpt_dkqxcssz> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_dkqxcssz, req.getParameterMap());
		Page<Dkjkpt_dkqxcssz> page = new Page<Dkjkpt_dkqxcssz>(pageNo, pageSize);
		IPage<Dkjkpt_dkqxcssz> pageList = dkjkpt_dkqxcsszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dkjkpt_dkqxcssz
	 * @return
	 */
	@AutoLog(value = "贷款期限参数设置-添加")
	@ApiOperation(value="贷款期限参数设置-添加", notes="贷款期限参数设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkjkpt_dkqxcssz dkjkpt_dkqxcssz) {
		dkjkpt_dkqxcsszService.save(dkjkpt_dkqxcssz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dkjkpt_dkqxcssz
	 * @return
	 */
	@AutoLog(value = "贷款期限参数设置-编辑")
	@ApiOperation(value="贷款期限参数设置-编辑", notes="贷款期限参数设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dkjkpt_dkqxcssz dkjkpt_dkqxcssz) {

		dkjkpt_dkqxcsszService.updateById(dkjkpt_dkqxcssz);
		return Result.ok("编辑成功!");
	}

	 /**
	  * 编辑
	  *
	  * @param dkjkpt_dkqxcssz
	  * @return
	  */
	 @AutoLog(value = "贷款期限参数设置-编辑")
	 @ApiOperation(value="贷款期限参数设置-编辑", notes="贷款期限参数设置-编辑")
	 @PutMapping(value = "/updateByCsbh")
	 public Result<?> updateByCsbh(@RequestBody Dkjkpt_dkqxcssz dkjkpt_dkqxcssz) {
		 int count = dkjkpt_dkqxcsszService.updateByCsbh(dkjkpt_dkqxcssz);
		 if (count==1){
			 return Result.ok("编辑成功!");
		 }else {
			 return Result.ok("编辑失败，参数编号已存在!");

		 }
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款期限参数设置-通过id删除")
	@ApiOperation(value="贷款期限参数设置-通过id删除", notes="贷款期限参数设置-通过id删除")
	@PostMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="csbh",required=true) String id) {
		dkjkpt_dkqxcsszService.removeById(id);
		return Result.ok("删除成功!");
	}

	 /**
	  * 通过id删除
	  *
	  * @param csbh
	  * @return
	  */
	 @AutoLog(value = "贷款期限参数设置-通过id删除")
	 @ApiOperation(value="贷款期限参数设置-通过id删除", notes="贷款期限参数设置-通过id删除")
	 @DeleteMapping(value = "/deleteByCsbh")
	 public Result<?> deleteByCsbh(@RequestParam(name="csbh",required=true) String csbh) {
		 dkjkpt_dkqxcsszService.deleteByCsbh(csbh);
		 return Result.ok("删除成功!");
	 }

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款期限参数设置-批量删除")
	@ApiOperation(value="贷款期限参数设置-批量删除", notes="贷款期限参数设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkpt_dkqxcsszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款期限参数设置-通过id查询")
	@ApiOperation(value="贷款期限参数设置-通过id查询", notes="贷款期限参数设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkjkpt_dkqxcssz dkjkpt_dkqxcssz = dkjkpt_dkqxcsszService.getById(id);
		return Result.ok(dkjkpt_dkqxcssz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkpt_dkqxcssz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkjkpt_dkqxcssz dkjkpt_dkqxcssz) {
      return super.exportXls(request, dkjkpt_dkqxcssz, Dkjkpt_dkqxcssz.class, "贷款期限参数设置");
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
      return super.importExcel(request, response, Dkjkpt_dkqxcssz.class);
  }

}
