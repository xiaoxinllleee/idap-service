package org.cmms.modules.khlc.jczbgl.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khlc.jczbgl.entity.VpmaFBaseIndexType;
import org.cmms.modules.khlc.jczbgl.service.IVpmaFBaseIndexTypeService;
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
 * @Description: 基础指标树包含指标
 * @Author: jeecg-boot
 * @Date:   2021-01-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="基础指标树包含指标")
@RestController
@RequestMapping("/khlc/jczbgl/vpmaFBaseIndexType")
public class VpmaFBaseIndexTypeController extends JeecgController<VpmaFBaseIndexType, IVpmaFBaseIndexTypeService> {
	@Autowired
	private IVpmaFBaseIndexTypeService vpmaFBaseIndexTypeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vpmaFBaseIndexType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "基础指标树包含指标-分页列表查询")
	@ApiOperation(value="基础指标树包含指标-分页列表查询", notes="基础指标树包含指标-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VpmaFBaseIndexType vpmaFBaseIndexType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VpmaFBaseIndexType> queryWrapper = QueryGenerator.initQueryWrapper(vpmaFBaseIndexType, req.getParameterMap());
		Page<VpmaFBaseIndexType> page = new Page<VpmaFBaseIndexType>(pageNo, pageSize);
		IPage<VpmaFBaseIndexType> pageList = vpmaFBaseIndexTypeService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param vpmaFBaseIndexType
	 * @return
	 */
	@AutoLog(value = "基础指标树包含指标-添加")
	@ApiOperation(value="基础指标树包含指标-添加", notes="基础指标树包含指标-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VpmaFBaseIndexType vpmaFBaseIndexType) {
		vpmaFBaseIndexTypeService.save(vpmaFBaseIndexType);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vpmaFBaseIndexType
	 * @return
	 */
	@AutoLog(value = "基础指标树包含指标-编辑")
	@ApiOperation(value="基础指标树包含指标-编辑", notes="基础指标树包含指标-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VpmaFBaseIndexType vpmaFBaseIndexType) {
		vpmaFBaseIndexTypeService.updateById(vpmaFBaseIndexType);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础指标树包含指标-通过id删除")
	@ApiOperation(value="基础指标树包含指标-通过id删除", notes="基础指标树包含指标-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vpmaFBaseIndexTypeService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "基础指标树包含指标-批量删除")
	@ApiOperation(value="基础指标树包含指标-批量删除", notes="基础指标树包含指标-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vpmaFBaseIndexTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "基础指标树包含指标-通过id查询")
	@ApiOperation(value="基础指标树包含指标-通过id查询", notes="基础指标树包含指标-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VpmaFBaseIndexType vpmaFBaseIndexType = vpmaFBaseIndexTypeService.getById(id);
		return Result.ok(vpmaFBaseIndexType);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vpmaFBaseIndexType
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VpmaFBaseIndexType vpmaFBaseIndexType) {
      return super.exportXls(request, vpmaFBaseIndexType, VpmaFBaseIndexType.class, "基础指标树包含指标");
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
       return super.importExcel(request, response, VpmaFBaseIndexType.class);
  }


  @RequestMapping("/treeData")
  public Result<?> treeDataJczb(){
		List<VpmaFBaseIndexType> pmaFBaseIndexTypes = vpmaFBaseIndexTypeService.listTree();
		return Result.ok(pmaFBaseIndexTypes);
  }


  @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
  public Result<?> queryTreeList(Integer khfs,Integer zblx) {
		Result<JSONArray> result = new Result<>();
		try {
			JSONArray jsonArray = vpmaFBaseIndexTypeService.queryTreeList(khfs,zblx);
			result.setResult(jsonArray);
			result.setSuccess(true);
		} catch (Exception e) {
				 log.error(e.getMessage(),e);
		}
		 return result;
   }
}
