package org.cmms.modules.xddagl.gwgl.sfwgl.controller;

import java.sql.Timestamp;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
import net.sf.saxon.expr.TryCatch;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.xddagl.gwgl.sfwgl.entity.Sfwgl;
import org.cmms.modules.xddagl.gwgl.sfwgl.entity.SfwglFjVo;
import org.cmms.modules.xddagl.gwgl.sfwgl.entity.SfwglVo;
import org.cmms.modules.xddagl.gwgl.sfwgl.service.ISfwglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddagl.gwgl.sfwgl.verify.SfwglVerify;
import org.cmms.modules.xddagl.gwgl.sfwglfjxx.entity.SfwglFjxx;
import org.cmms.modules.xddagl.gwgl.sfwglfjxx.service.ISfwglFjxxService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 收发文管理
 * @Author: jeecg-boot
 * @Date:   2022-01-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="收发文管理")
@RestController
@RequestMapping("/sfwgl/sfwgl")
public class SfwglController extends JeecgController<Sfwgl, ISfwglService> {
	@Autowired
	private ISfwglService sfwglService;
	@Autowired
	private SfwglVerify sfwglVerify;
	@Value(value = "${common.path.upload}")
	private String uploadpath;
	@Autowired
	IDictValueQuery iDictValueQuery;
	@Autowired
	private ISfwglFjxxService sfwglFjxxService;
	@Autowired
	private IHrBasOrganizationService iHrBasOrganizationService;
	 @Autowired
	 private ISysUserService sysUserService;

	/**
	 * 分页列表查询
	 *
	 * @param sfwgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "收发文管理-分页列表查询")
	@ApiOperation(value="收发文管理-分页列表查询", notes="收发文管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Sfwgl sfwgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Sfwgl> queryWrapper = null;
		Page<Sfwgl> page = new Page<Sfwgl>(pageNo, pageSize);
		IPage<Sfwgl> pageList=null;
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String zzbz=sysUserService.getUserByName(loginUser.getUsername()).getOrgCode();
		if ("admin".equals(loginUser.getUsername()) || zzbz==null || StringUtils.isEmpty(zzbz)){
			queryWrapper = QueryGenerator.initQueryWrapper(sfwgl, req.getParameterMap());
			pageList = sfwglService.page(page, queryWrapper);
		}else{
			pageList = sfwglService.getQuery(page,sfwgl,zzbz);
		}
		return Result.ok(pageList);
	}
	 @GetMapping(value = "/addInit")
	 public Result<?> addInit(@RequestParam("id")String id) {
		 String[] split =null;
		 if (!StringUtils.isEmpty(id)){
		 		QueryWrapper queryWrapper=new QueryWrapper();
		 		queryWrapper.eq("id",id);
			 	Sfwgl sfwgl = sfwglService.getOne(queryWrapper);
			 	if(!StringUtils.isEmpty(sfwgl.getSwdw())){
					split = sfwgl.getSwdw().split(",");
				}
		 }
		 QueryWrapper queryWrapper=new QueryWrapper();
		 queryWrapper.orderByAsc("PXXH");
		 List<HrBasOrganization> list = iHrBasOrganizationService.list(queryWrapper);
		 list.remove(0);
		 Map map=new HashMap();
		 List<Boolean> swdwFlag = new ArrayList<>();
		 if (!StringUtils.isEmpty(id)){
			 for (int i=0;i<list.size();i++){
				 swdwFlag.add(false);
				 String zzbz = list.get(i).getZzbz();
				 //zzbz匹配的上,设置为false没有默认选择
				 if (split!=null){
					 for (int j=0;j<split.length;j++){
						 if (split[j].equals(zzbz)){
							 swdwFlag.set(i,true);
							 break;
						 }
					 }
				 }
			 }
		 }

		 map.put("list",list);
		 map.put("swdw",swdwFlag);
		 return Result.ok(map);
	 }
	 /**
	  * 附件上传
	  */
	 @AutoLog(value = "收发文管理-附件上传")
	 @ApiOperation(value="收发文管理-附件上传", notes="收发文管理-附件上传")
	 public Boolean fjsc(SfwglFjVo sfwglFjVo){
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 JSONArray fjxxs = sfwglFjVo.getImgdate();
		 SfwglFjxx fjxx = new SfwglFjxx();
		 QueryWrapper queryWrapper=new QueryWrapper();
		 queryWrapper.eq("FWBH",sfwglFjVo.getFwbh());
		 Sfwgl sfwgl = sfwglService.getOne(queryWrapper);
		 if (fjxxs != null && fjxxs.size()>0){
		 	for (int i = 0; i<fjxxs.size(); i++){
				String wllj = uploadpath + "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
				String fwlj = "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
				fjxx.setWjid(UUID.randomUUID().toString().replaceAll("-", ""));
				fjxx.setWjlj(wllj);
				fjxx.setFwlj(fwlj);
				fjxx.setFwbhid(sfwgl.getId());
				fjxx.setLrbz(1);
				fjxx.setLrr(sysUser.getUsername());
				fjxx.setLrsj(new Timestamp(System.currentTimeMillis()));
				try {
					sfwglFjxxService.save(fjxx);
				} catch (Exception e){
					e.printStackTrace();
					return  false;
				}

			}
		 }
		 return true;
	 }


	/**
	 * 添加
	 *
	 * @param sfwglFjVo
	 * @return
	 */
	@AutoLog(value = "收发文管理-添加")
	@ApiOperation(value="收发文管理-添加", notes="收发文管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SfwglFjVo sfwglFjVo) {
		Boolean flag=true;
		Sfwgl sfwgl=new Sfwgl();
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("FWBH",sfwglFjVo.getFwbh());
		if (sfwglService.getOne(queryWrapper) !=null){
			return Result.error("发文编号重复");
		}
		BeanUtils.copyProperties(sfwglFjVo,sfwgl);
		sfwglService.save(sfwgl);
		if (sfwglFjVo.getImgdate().size()>0){
			flag = fjsc(sfwglFjVo);
		}
		if (!flag){
			Result.ok("添加失败");
		}
		return Result.ok("添加成功");
	}
	/**
	 * 编辑
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "收发文管理-编辑")
	@ApiOperation(value="收发文管理-编辑", notes="收发文管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SfwglFjVo sfwglFjVo) {
		Boolean flag=true;
		Sfwgl sfwgl=new Sfwgl();
		BeanUtils.copyProperties(sfwglFjVo,sfwgl);
		sfwglService.updateById(sfwgl);
		//如有pdf进行上传则调用
		if (sfwglFjVo.getImgdate().size()>0){
			flag = fjsc(sfwglFjVo);
		}
		if (!flag){
			Result.ok("编辑失败");
		}
		return Result.ok("编辑成功");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "收发文管理-通过id删除")
	@ApiOperation(value="收发文管理-通过id删除", notes="收发文管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("id") String id) {
		QueryWrapper<Sfwgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id",id);
		sfwglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "收发文管理-批量删除")
	@ApiOperation(value="收发文管理-批量删除", notes="收发文管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sfwglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "收发文管理-通过id查询")
	@ApiOperation(value="收发文管理-通过id查询", notes="收发文管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Sfwgl sfwgl = sfwglService.getById(id);
		return Result.ok(sfwgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sfwgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Sfwgl sfwgl) {
      return super.exportXls(request, sfwgl, Sfwgl.class, "收发文管理");
  }

	 /**
	  * 导出模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 return super.exportTemplateXls(SfwglVo.class, "收发文管理导入模板");
	 }
	 /**
	  * 通过excel导入数据
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcelByTemplate(jsonObject,request, response, Sfwgl.class,SfwglVo.class,sfwglVerify);
	 }

}
