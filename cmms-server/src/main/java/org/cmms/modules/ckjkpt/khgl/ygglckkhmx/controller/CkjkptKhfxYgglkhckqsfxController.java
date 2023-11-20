package org.cmms.modules.ckjkpt.khgl.ygglckkhmx.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity.CkjkptKhfxYgglkhckqsfx;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.service.ICkjkptKhfxYgglkhckqsfxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户近10日存款余额
 * @Author: jeecg-boot
 * @Date:   2021-11-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户近10日存款余额")
@RestController
@RequestMapping("/ygglckkhmx/ckjkptKhfxYgglkhckqsfx")
public class CkjkptKhfxYgglkhckqsfxController extends JeecgController<CkjkptKhfxYgglkhckqsfx, ICkjkptKhfxYgglkhckqsfxService> {
	@Autowired
	private ICkjkptKhfxYgglkhckqsfxService ckjkptKhfxYgglkhckqsfxService;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;
	/**
	 * 分页列表查询
	 *
	 * @param ckjkptKhfxYgglkhckqsfx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户近10日存款余额-分页列表查询")
	@ApiOperation(value="客户近10日存款余额-分页列表查询", notes="客户近10日存款余额-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CkjkptKhfxYgglkhckqsfx ckjkptKhfxYgglkhckqsfx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		/*QueryWrapper<CkjkptKhfxYgglkhckqsfx> queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfxYgglkhckqsfx, req.getParameterMap());
		Page<CkjkptKhfxYgglkhckqsfx> page = new Page<CkjkptKhfxYgglkhckqsfx>(pageNo, pageSize);
		IPage<CkjkptKhfxYgglkhckqsfx> pageList = ckjkptKhfxYgglkhckqsfxService.page(page, queryWrapper);
		return Result.ok(pageList);*/
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(ckjkptKhfxYgglkhckqsfx, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkjkptKhfxYgglkhckqsfxService.class,ckjkptKhfxYgglkhckqsfxService,pageNo,pageSize,queryWrapper,"tjyf","zzbz","yggh","zjhm");
		return Result.ok(pageList);
	}

	 /**
	  * 分页列表查询
	  *
	  * @param ckjkptKhfxYgglkhckqsfx
	  * @return
	  */
	 @AutoLog(value = "客户近10日存款余额-分页列表查询")
	 @ApiOperation(value="客户近10日存款余额-分页列表查询", notes="客户近10日存款余额-分页列表查询")
	 @PutMapping(value = "/queryList")
	 public Result<?> queryList(@RequestBody CkjkptKhfxYgglkhckqsfx ckjkptKhfxYgglkhckqsfx) {
			 List<CkjkptKhfxYgglkhckqsfx> list=new ArrayList<>();
		 if ("true".equals(sfdsjpt)){
			 list=ckjkptKhfxYgglkhckqsfxService.queryjsryeqsDsj(ckjkptKhfxYgglkhckqsfx.getZzbz(),ckjkptKhfxYgglkhckqsfx.getGwbz(),ckjkptKhfxYgglkhckqsfx.getYggh(),ckjkptKhfxYgglkhckqsfx.getZjhm());
		 }else {
			 list=ckjkptKhfxYgglkhckqsfxService.queryjsryeqs(ckjkptKhfxYgglkhckqsfx.getZzbz(),ckjkptKhfxYgglkhckqsfx.getGwbz(),ckjkptKhfxYgglkhckqsfx.getYggh(),ckjkptKhfxYgglkhckqsfx.getZjhm());
		 }
		 JSONArray jsonArray=new JSONArray();
		 for(CkjkptKhfxYgglkhckqsfx c:list){
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
	 * @param ckjkptKhfxYgglkhckqsfx
	 * @return
	 */
	@AutoLog(value = "客户近10日存款余额-添加")
	@ApiOperation(value="客户近10日存款余额-添加", notes="客户近10日存款余额-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CkjkptKhfxYgglkhckqsfx ckjkptKhfxYgglkhckqsfx) {
		ckjkptKhfxYgglkhckqsfxService.save(ckjkptKhfxYgglkhckqsfx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param ckjkptKhfxYgglkhckqsfx
	 * @return
	 */
	@AutoLog(value = "客户近10日存款余额-编辑")
	@ApiOperation(value="客户近10日存款余额-编辑", notes="客户近10日存款余额-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CkjkptKhfxYgglkhckqsfx ckjkptKhfxYgglkhckqsfx) {
		ckjkptKhfxYgglkhckqsfxService.updateById(ckjkptKhfxYgglkhckqsfx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户近10日存款余额-通过id删除")
	@ApiOperation(value="客户近10日存款余额-通过id删除", notes="客户近10日存款余额-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ckjkptKhfxYgglkhckqsfxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户近10日存款余额-批量删除")
	@ApiOperation(value="客户近10日存款余额-批量删除", notes="客户近10日存款余额-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckjkptKhfxYgglkhckqsfxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户近10日存款余额-通过id查询")
	@ApiOperation(value="客户近10日存款余额-通过id查询", notes="客户近10日存款余额-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CkjkptKhfxYgglkhckqsfx ckjkptKhfxYgglkhckqsfx = ckjkptKhfxYgglkhckqsfxService.getById(id);
		return Result.ok(ckjkptKhfxYgglkhckqsfx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckjkptKhfxYgglkhckqsfx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CkjkptKhfxYgglkhckqsfx ckjkptKhfxYgglkhckqsfx) {
      return super.exportXls(request, ckjkptKhfxYgglkhckqsfx, CkjkptKhfxYgglkhckqsfx.class, "客户近10日存款余额");
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
      return super.importExcel(request, response, CkjkptKhfxYgglkhckqsfx.class);
  }

}
