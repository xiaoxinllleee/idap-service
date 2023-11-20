package org.cmms.modules.khxxgl.khjbzl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khgs.service.IkhsskhjlService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.yxdygl.pqqxgl.entity.VYxdyglPqqxgl;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxgl;
import org.cmms.modules.yxdygl.pqqxgl.service.IVYxdyglPqqxglService;
import org.cmms.modules.yxdygl.pqqxgl.service.IYxdyglPqqxglService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
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
 * @Description: 客户画像
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户画像")
@RestController
@RequestMapping("/khjbzl/khjbzl")
public class KhjbzlController extends JeecgController<Khjbzl, IKhjbzlService> {
	 @Autowired
	 private IKhjbzlService khjbzlService;
	 @Autowired
	 private IYxdyglPqqxglService yxdyglPqqxglService;
	 @Autowired
	 private IkhsskhjlService khsskhjlService;
	 @Autowired
	 private IYxdyglMainService yxdyglMainService;
	 @Autowired
	 private INhxqService nhxqService;
	 @Autowired
	 private IVYxdyglPqqxglService ivYxdyglPqqxglService;
	 @Autowired
	 private ISysLogService sysLogService;
	/**
	 * 分页列表查询
	 *
	 * @param khjbzl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户画像-分页列表查询")
	@ApiOperation(value="客户画像-分页列表查询", notes="客户画像-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Khjbzl khjbzl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   @RequestParam(name="getAll", defaultValue="2", required = false) String getAll,
								   HttpServletRequest req) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		QueryWrapper<Khjbzl> queryWrapper = QueryGenerator.initQueryWrapper(khjbzl, req.getParameterMap());
		if(!sysUser.getUsername().equals("admin") && "2".equals(getAll)){
			//in  用 list有个数限制问题， 此处改为inSql
			//List<String> menuIdsByKhjlgh = yxdyglPqqxglService.getMenuIdsByKhjlgh(sysUser.getUsername());
			//List<String> zjhms = khsskhjlService.getKhjbzlZjhmKhjlgh(sysUser.getUsername());
			String sqlSswg="select  menu_id from YXDYGL_PQQXGL t where khjl ="+"'"+sysUser.getWorkNo()+"'";
			String sqlSsKhjl="select  zjhm from KHXXGL_KHSSKHJL t where sskhjl ="+"'"+sysUser.getWorkNo()+"'";

			//TODO 查询效率太慢，暂时去掉客户权限 liuwei 2022-3-1 17:46:27
//			queryWrapper.and(i -> i.inSql("wgbh",sqlSswg).or().inSql("zjhm",sqlSsKhjl));
			//queryWrapper.and(i -> i.inSql("wgbh",sqlSswg));
			//queryWrapper.and(i -> i.inSql("wgbh",sqlSswg));
			String sqlZjhm = "select zjhm from khxxgl_khxq_nh where wgbh IN " +
					"       (SELECT menu_id FROM YXDYGL_PQQXGL t WHERE khjl = '" + getWorkNo() + "') " +
					"     union all " +
					"     SELECT zjhm FROM KHXXGL_KHSSKHJL t WHERE sskhjl = '" + getWorkNo() + "'";
			queryWrapper.and(i -> i.inSql("zjhm", sqlZjhm));
		}
		Page<Khjbzl> page = new Page<Khjbzl>(pageNo, pageSize);
		IPage<Khjbzl> pageList = khjbzlService.page(page, queryWrapper);

		return Result.ok(pageList);
	}

	 /**
	  * 分页列表查询
	  *
	  * @param khjbzl
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "客户画像-分页列表查询")
	 @ApiOperation(value="客户画像-分页列表查询", notes="客户画像-分页列表查询")
	 @GetMapping(value = "/queryByzjhm")
	 public Result<?> queryByzjhm(Khjbzl khjbzl, HttpServletRequest req) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 QueryWrapper<Khjbzl> queryWrapper = QueryGenerator.initQueryWrapper(khjbzl, req.getParameterMap());
		 Khjbzl one = khjbzlService.getOne(queryWrapper);
		 if(one==null){
			 return Result.error("查询的证件号不存在！");
		 }else{
		 	if(one.getWgbh()==null||one.getWgbh().equals("")){
				return Result.error("客户["+one.getKhmc()+"]未分配所属网格，暂无权限查看!");
			}else{
				YxdyglMain yxdyglMain = yxdyglMainService.getById(one.getWgbh());
				QueryWrapper<VYxdyglPqqxgl> queryWrapper1 =new QueryWrapper();
				queryWrapper1.eq("MENU_ID",one.getWgbh());
				queryWrapper1.eq("sfzkhjl","1");
				VYxdyglPqqxgl one1 = ivYxdyglPqqxglService.getOne(queryWrapper1);
				if(one1==null){
					return Result.error("客户["+one.getKhmc()+"]所属网格为<"+yxdyglMain.getWgmc()+">,暂未分配网格权限!");
				}else{
					return Result.error("权限不足,客户["+one.getKhmc()+"]所属网格为<"+yxdyglMain.getWgmc()+">,网格长为<"+one1.getYgxm()+">!");
				}
			}
		 }
	 }

	/**
	 * 添加
	 *
	 * @param khjbzl
	 * @return
	 */
	@AutoLog(value = "客户画像-添加")
	@ApiOperation(value="客户画像-添加", notes="客户画像-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Khjbzl khjbzl) {
		khjbzlService.save(khjbzl);
		return Result.ok("添加成功！");
	}


	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/extract")
	 public Result<?> extract() {
		 Result result = new Result<>();
		 try {
			 Date cksjrq =sysLogService.cksjrq();
			 khjbzlService.extract(DateUtil.getDateString(cksjrq));
			 result.setSuccess(true);
			 return result;
		 } catch (Exception e) {
			 System.out.println(e);
			 log.error("提取失败", e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init() {
		 Result result = new Result<>();
		 try {
			 nhxqService.init();
			 result.setSuccess(true);
			 return result;
		 } catch (Exception e) {
			 System.out.println(e);
			 log.error("提取失败", e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
	/**
	 * 编辑
	 *
	 * @param khjbzl
	 * @return
	 */
	@AutoLog(value = "客户画像-编辑")
	@ApiOperation(value="客户画像-编辑", notes="客户画像-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Khjbzl khjbzl) {
		khjbzlService.updateById(khjbzl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户画像-通过id删除")
	@ApiOperation(value="客户画像-通过id删除", notes="客户画像-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khjbzlService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户画像-批量删除")
	@ApiOperation(value="客户画像-批量删除", notes="客户画像-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khjbzlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户画像-通过id查询")
	@ApiOperation(value="客户画像-通过id查询", notes="客户画像-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Khjbzl khjbzl = khjbzlService.getById(id);
		return Result.ok(khjbzl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khjbzl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Khjbzl khjbzl) {
      return super.exportXls(request, khjbzl, Khjbzl.class, "客户画像");
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
      return super.importExcel(request, response, Khjbzl.class);
  }

  @GetMapping("/getKhhxByZjhm")
  public Result<?> getKhhxByZjhm(@RequestParam("zjhm")String zjhm,
								 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								 HttpServletRequest req) {
  	if (StringUtils.isNotBlank(zjhm)){
  		Khjbzl khjbzl=new Khjbzl();
  		khjbzl.setZjhm(zjhm);
		QueryWrapper<Khjbzl> queryWrapper = QueryGenerator.initQueryWrapper(khjbzl, req.getParameterMap());
		Page<Khjbzl> page = new Page<Khjbzl>(pageNo, pageSize);
		IPage<Khjbzl> pageList = khjbzlService.page(page, queryWrapper);
  		return Result.ok(pageList);
	}
  	return Result.ok();
  }

}
