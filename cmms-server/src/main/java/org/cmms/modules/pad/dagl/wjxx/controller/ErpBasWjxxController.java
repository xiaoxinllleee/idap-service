package org.cmms.modules.pad.dagl.wjxx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.dagl.bwbldk.entity.VLoanBwdkSjmx;
import org.cmms.modules.pad.dagl.bwbldk.service.IVLoanBwdkSjmxService;
import org.cmms.modules.pad.dagl.wjxx.entity.ErpBasWjxx;
import org.cmms.modules.pad.dagl.wjxx.service.IErpBasWjxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.nhxxgl.entity.CamsJbxxNhzllbPad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 文件信息
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="文件信息")
@RestController
@RequestMapping("/wjxx/erpBasWjxx")
public class ErpBasWjxxController extends JeecgController<ErpBasWjxx, IErpBasWjxxService> {
	@Autowired
	private IErpBasWjxxService erpBasWjxxService;
	@Autowired
	private IVLoanBwdkSjmxService ivLoanBwdkSjmxService;
	@Autowired
	private INhxqService iNhxqService;


	/**
	 * 分页列表查询
	 *
	 * @param erpBasWjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文件信息-分页列表查询")
	@ApiOperation(value="文件信息-分页列表查询", notes="文件信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpBasWjxx erpBasWjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpBasWjxx> queryWrapper = QueryGenerator.initQueryWrapper(erpBasWjxx, req.getParameterMap());
		Page<ErpBasWjxx> page = new Page<ErpBasWjxx>(pageNo, pageSize);
		queryWrapper.orderByDesc("lrsj");
		IPage<ErpBasWjxx> pageList = erpBasWjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 @RequestMapping(value = "/queryBwbldkFjxx",method = RequestMethod.GET)
	 public Result<?> queryBwbldkFjxx( @RequestParam(name = "ywid") Integer ywid, @RequestParam(name = "dkzh") String dkzh) {
		 try {
			 QueryWrapper<ErpBasWjxx> fjxxQueryWrapper = new QueryWrapper<>();
			 fjxxQueryWrapper.eq("ywid", ywid);
			 List<ErpBasWjxx> list = erpBasWjxxService.list(fjxxQueryWrapper);
			 if (list != null && list.size()>0) {
				 return Result.ok(list);
			 }
		 } catch (Exception e) {
			 return Result.error(e.toString());
		 }
		 return Result.ok("查询成功");
	 }

	 @RequestMapping(value = "/saveBwbldkFjxxList",method = RequestMethod.GET)
	 public Result<?> saveBwbldkFjxxList( @RequestParam(name = "id") String id) {
		 try {
		 	 QueryWrapper<Nhxq> nhxqQueryWrapper=new QueryWrapper<>();
		 	 nhxqQueryWrapper.eq("id",id);
			 QueryWrapper<VLoanBwdkSjmx> bwdkSJmxQueryWrapper = new QueryWrapper<>();
			 bwdkSJmxQueryWrapper.eq("zjhm", iNhxqService.getOne(nhxqQueryWrapper).getZjhm());
			 List<VLoanBwdkSjmx> list = ivLoanBwdkSjmxService.list(bwdkSJmxQueryWrapper);
			 List<Integer> ywidList=list.stream().map(item->item.getYwid()).collect(Collectors.toList());

			 if (ywidList !=null && ywidList.size()>0) {
				 QueryWrapper<ErpBasWjxx> fjxxQueryWrapper = new QueryWrapper<>();
				 fjxxQueryWrapper.in("ywid", ywidList);
				 List<ErpBasWjxx> list1 = erpBasWjxxService.list(fjxxQueryWrapper);

				 if (list1 != null && list1.size() > 0) {
					 return Result.ok(list1);
				 }
			 }
		 } catch (Exception e) {
			 return Result.error(e.toString());
		 }
		 return Result.ok("查询成功");
	 }



	 /**
	 * 添加
	 *
	 * @param erpBasWjxx
	 * @return
	 */
	@AutoLog(value = "文件信息-添加")
	@ApiOperation(value="文件信息-添加", notes="文件信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpBasWjxx erpBasWjxx) {
		if (erpBasWjxx.getYwid()!=null){
			QueryWrapper<ErpBasWjxx> deleteQueryWrapper=new QueryWrapper<>();
			deleteQueryWrapper.eq("ywid",erpBasWjxx.getYwid());
			erpBasWjxxService.remove(deleteQueryWrapper);
		}
		erpBasWjxx.setYwid(RandomUtils.nextInt(32));
		erpBasWjxxService.save(erpBasWjxx);

		//同步贷款不良表
		VLoanBwdkSjmx vLoanBwdkSjmx=new VLoanBwdkSjmx();
		vLoanBwdkSjmx.setDkzh(erpBasWjxx.getDkzh());
		vLoanBwdkSjmx.setYwid(erpBasWjxx.getYwid());
		ivLoanBwdkSjmxService.updateById(vLoanBwdkSjmx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param erpBasWjxx
	 * @return
	 */
	@AutoLog(value = "文件信息-编辑")
	@ApiOperation(value="文件信息-编辑", notes="文件信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpBasWjxx erpBasWjxx) {
		erpBasWjxxService.updateById(erpBasWjxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文件信息-通过id删除")
	@ApiOperation(value="文件信息-通过id删除", notes="文件信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		erpBasWjxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "文件信息-批量删除")
	@ApiOperation(value="文件信息-批量删除", notes="文件信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpBasWjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文件信息-通过id查询")
	@ApiOperation(value="文件信息-通过id查询", notes="文件信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpBasWjxx erpBasWjxx = erpBasWjxxService.getById(id);
		return Result.ok(erpBasWjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpBasWjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpBasWjxx erpBasWjxx) {
      return super.exportXls(request, erpBasWjxx, ErpBasWjxx.class, "文件信息");
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
      return super.importExcel(request, response, ErpBasWjxx.class);
  }

}
