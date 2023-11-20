package org.cmms.modules.ckjkpt.khgl.ygglckhz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity.CkjkptKhfxYgglkhckqsfx;
import org.cmms.modules.ckjkpt.khgl.ygglckhz.entity.CkjkptKhfzYgglckhzqsfx;
import org.cmms.modules.ckjkpt.khgl.ygglckhz.service.ICkjkptKhfzYgglckhzqsfxService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.DateUtil;
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
 * @Description: 员工揽储趋势分析
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工揽储趋势分析")
@RestController
@RequestMapping("/yglcckhz/ckjkptKhfzYgglckhzqsfx")
public class CkjkptKhfzYgglckhzqsfxController extends JeecgController<CkjkptKhfzYgglckhzqsfx, ICkjkptKhfzYgglckhzqsfxService> {
	@Autowired
	private ICkjkptKhfzYgglckhzqsfxService ckjkptKhfzYgglckhzqsfxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ckjkptKhfzYgglckhzqsfx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "员工揽储趋势分析-分页列表查询")
	@ApiOperation(value="员工揽储趋势分析-分页列表查询", notes="员工揽储趋势分析-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CkjkptKhfzYgglckhzqsfx ckjkptKhfzYgglckhzqsfx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		/*QueryWrapper<CkjkptKhfzYgglckhzqsfx> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzYgglckhzqsfx, req.getParameterMap());
		Page<CkjkptKhfzYgglckhzqsfx> page = new Page<CkjkptKhfzYgglckhzqsfx>(pageNo, pageSize);
		IPage<CkjkptKhfzYgglckhzqsfx> pageList = ckjkptKhfzYgglckhzqsfxService.page(page, queryWrapper);
		return Result.ok(pageList);*/
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfzYgglckhzqsfx, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptKhfzYgglckhzqsfxService.class,ckjkptKhfzYgglckhzqsfxService,pageNo,pageSize,queryWrapper,"tjyf","yggh","zzbz");
		return Result.ok(pageList);
	}


	 /**
	  * 分页列表查询
	  * @return
	  */
	 @AutoLog(value = "客户近10日存款余额-分页列表查询")
	 @ApiOperation(value="客户近10日存款余额-分页列表查询", notes="客户近10日存款余额-分页列表查询")
	 @PutMapping(value = "/queryList")
	 public Result<?> queryList(@RequestBody CkjkptKhfzYgglckhzqsfx ckjkptKhfzYgglckhzqsfx) {
		 List<CkjkptKhfzYgglckhzqsfx> list=ckjkptKhfzYgglckhzqsfxService.queryjsryeqs(ckjkptKhfzYgglckhzqsfx.getZzbz(),ckjkptKhfzYgglckhzqsfx.getGwbz(),ckjkptKhfzYgglckhzqsfx.getYggh());
		 JSONArray jsonArray=new JSONArray();
		 for(CkjkptKhfzYgglckhzqsfx c:list){
			 JSONObject jsonObject=new JSONObject();
			 jsonObject.put("type", DateUtil.date2String(c.getTjyf(),"yyyy-MM-dd"));
			 jsonObject.put("存款余额",c.getCkye());
			 jsonArray.add(jsonObject);
		 }
		 return Result.ok(jsonArray);
	 }

	
	/**
	 * 添加
	 *
	 * @param ckjkptKhfzYgglckhzqsfx
	 * @return
	 */
	@AutoLog(value = "员工揽储趋势分析-添加")
	@ApiOperation(value="员工揽储趋势分析-添加", notes="员工揽储趋势分析-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CkjkptKhfzYgglckhzqsfx ckjkptKhfzYgglckhzqsfx) {
		ckjkptKhfzYgglckhzqsfxService.save(ckjkptKhfzYgglckhzqsfx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckjkptKhfzYgglckhzqsfx
	 * @return
	 */
	@AutoLog(value = "员工揽储趋势分析-编辑")
	@ApiOperation(value="员工揽储趋势分析-编辑", notes="员工揽储趋势分析-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CkjkptKhfzYgglckhzqsfx ckjkptKhfzYgglckhzqsfx) {
		ckjkptKhfzYgglckhzqsfxService.updateById(ckjkptKhfzYgglckhzqsfx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工揽储趋势分析-通过id删除")
	@ApiOperation(value="员工揽储趋势分析-通过id删除", notes="员工揽储趋势分析-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckjkptKhfzYgglckhzqsfxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "员工揽储趋势分析-批量删除")
	@ApiOperation(value="员工揽储趋势分析-批量删除", notes="员工揽储趋势分析-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckjkptKhfzYgglckhzqsfxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "员工揽储趋势分析-通过id查询")
	@ApiOperation(value="员工揽储趋势分析-通过id查询", notes="员工揽储趋势分析-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CkjkptKhfzYgglckhzqsfx ckjkptKhfzYgglckhzqsfx = ckjkptKhfzYgglckhzqsfxService.getById(id);
		return Result.ok(ckjkptKhfzYgglckhzqsfx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckjkptKhfzYgglckhzqsfx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CkjkptKhfzYgglckhzqsfx ckjkptKhfzYgglckhzqsfx) {
      return super.exportXls(request, ckjkptKhfzYgglckhzqsfx, CkjkptKhfzYgglckhzqsfx.class, "员工揽储趋势分析");
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
      return super.importExcel(request, response, CkjkptKhfzYgglckhzqsfx.class);
  }

}
