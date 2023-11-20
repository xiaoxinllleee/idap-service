package org.cmms.modules.ywgl.ckyw.ckclgl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.ywgl.ckyw.ckclgl.entity.Ckclgl;
import org.cmms.modules.ywgl.ckyw.ckclgl.mapper.CkclglMapper;
import org.cmms.modules.ywgl.ckyw.ckclgl.service.ICkclglService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 存款存量管理
 * @Author: jeecg-boot
 * @Date:   2021-10-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存款存量管理")
@RestController
@RequestMapping("/ckclgl/ckclgl")
public class CkclglController extends JeecgController<Ckclgl, ICkclglService> {
	@Autowired
	private ICkclglService ckclglService;

	
	/**
	 * 分页列表查询
	 *
	 * @param ckclgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存款存量管理-分页列表查询")
	@ApiOperation(value="存款存量管理-分页列表查询", notes="存款存量管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Ckclgl ckclgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Ckclgl> queryWrapper = QueryGenerator.initQueryWrapper(ckclgl, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ICkclglService.class,ckclglService,pageNo,pageSize,queryWrapper,"clnf","zzbz","yggh");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @DS("cdkyw")
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject){
		 String clnf = jsonObject.getString("clnf");
		 clnf = clnf.replaceAll("-","");
		 Result result = new Result<>();
		 try{
		 	 ckclglService.pCkclgl(clnf);
			 result.setSuccess(true);
			 return result;
		 }catch (Throwable e){
			 System.out.println(e);
			 log.error("提取失败", e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }

	 /**
	  * 查找带回
	  */
//	 @AutoLog(value = "存款存量管理-查找带回")
//	 @ApiOperation(value = "存款存量管理-查找带回",notes = "存款存量管理-查找带回")
//	 @PostMapping(value = "/getListClaim")
//	 public Result<?> getListClaim(@RequestBody JSONObject jsonObject){
//		 System.out.println(jsonObject);
//		 String ywjgdm = jsonObject.getString("zzbz");
//		 String khjlbz = jsonObject.getString("khjlbz");
//		 String rglx = jsonObject.getString("rglx");
//		 String gwbz = jsonObject.getString("gwbz");
//		 List<HrBasStaffPostVO> list = ckclglService.getListClaim(ywjgdm, rglx, gwbz, khjlbz);
//		 return Result.ok(list);
//	 }

	 /**
	 * 添加
	 *
	 * @param ckclgl
	 * @return
	 */
	@AutoLog(value = "存款存量管理-添加")
	@ApiOperation(value="存款存量管理-添加", notes="存款存量管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Ckclgl ckclgl) {
		ckclglService.save(ckclgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param ckclgl
	 * @return
	 */
	@AutoLog(value = "存款存量管理-编辑")
	@ApiOperation(value="存款存量管理-编辑", notes="存款存量管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Ckclgl ckclgl) {
		ckclglService.updateById(ckclgl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "存款存量管理-通过id删除")
	@ApiOperation(value="存款存量管理-通过id删除", notes="存款存量管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("zzbz")String zzbz,@Param("yggh")String yggh,@Param("clnf")String clnf) {
		QueryWrapper<Ckclgl> queryWrapper = new QueryWrapper<Ckclgl>();
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("yggh",yggh);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		try {
			queryWrapper.eq("clnf",sdf.parse(clnf));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ckclglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存款存量管理-批量删除")
	@ApiOperation(value="存款存量管理-批量删除", notes="存款存量管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ckclglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存款存量管理-通过id查询")
	@ApiOperation(value="存款存量管理-通过id查询", notes="存款存量管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Ckclgl ckclgl = ckclglService.getById(id);
		return Result.ok(ckclgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param ckclgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Ckclgl ckclgl) {
      return super.exportXls(request, ckclgl, Ckclgl.class, "存款存量管理");
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
      return super.importExcel(request, response, Ckclgl.class);
  }

}
