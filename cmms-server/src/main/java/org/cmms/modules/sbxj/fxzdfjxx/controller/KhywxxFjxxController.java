package org.cmms.modules.sbxj.fxzdfjxx.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;
import org.cmms.modules.sbxj.fxzdfjxx.entity.KhywxxFjxx;
import org.cmms.modules.sbxj.fxzdfjxx.service.IKhywxxFjxxService;

import org.cmms.modules.sbxj.fxzdxjjlb.entity.KhywxxFxzdxjjlb;
import org.cmms.modules.sbxj.fxzdxjjlb.service.IKhywxxFxzdxjjlbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 福祥站点附件表
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="福祥站点附件表")
@RestController
@RequestMapping("/fxzdfjxx/khywxxFjxx")
public class KhywxxFjxxController extends JeecgController<KhywxxFjxx, IKhywxxFjxxService> {
	@Autowired
	private IKhywxxFjxxService khywxxFjxxService;
	@Autowired
	private IKhywxxFxzdxjjlbService khywxxFxzdxjjlbService;

	/**
	 * 分页列表查询
	 *
	 * @param khywxxFjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "福祥站点附件表-分页列表查询")
	@ApiOperation(value="福祥站点附件表-分页列表查询", notes="福祥站点附件表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhywxxFjxx khywxxFjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhywxxFjxx> queryWrapper = QueryGenerator.initQueryWrapper(khywxxFjxx, req.getParameterMap());
		Page<KhywxxFjxx> page = new Page<KhywxxFjxx>(pageNo, pageSize);
		IPage<KhywxxFjxx> pageList = khywxxFjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 通过zdbh查询
	  * @return
	  */
	 @GetMapping(value = "/queryFjxxByMainId")
	 public Result<?> queryFjxxByMainId(@RequestParam(name="id",required=true) String id) {
		 QueryWrapper<KhywxxFjxx> queryWrapper=new QueryWrapper();
		 queryWrapper.eq("id",id);
		 List<KhywxxFjxx> khywxxFjxxes = khywxxFjxxService.list(queryWrapper);
		 JSONArray jsonArray =new JSONArray();
		 if (CollUtil.isNotEmpty(khywxxFjxxes)){
			 for(KhywxxFjxx khywxxFjxx:khywxxFjxxes){
				 jsonArray.add(khywxxFjxx.getFjlj());
			 }
		 }
		 return Result.ok(khywxxFjxxes);
	 }

	/**
	 * 添加
	 *
	 * @param khywxxFjxx
	 * @return
	 */
	@AutoLog(value = "福祥站点附件表-添加")
	@ApiOperation(value="福祥站点附件表-添加", notes="福祥站点附件表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhywxxFjxx khywxxFjxx) {
		khywxxFjxxService.save(khywxxFjxx);
		return Result.ok("添加成功！");
	}

	/* @RequestMapping(value = "/saveImage",method = RequestMethod.POST)
	 public Result<?> saveNhhzfjImage(@RequestBody  List<KhywxxFjxx>  jsonObject) {
		 QueryWrapper<KhywxxFxzdxjjlb> queryWrapper = new QueryWrapper<KhywxxFxzdxjjlb>();
		 String format = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		 queryWrapper.eq("create_time",DateUtil.parse(format));
		 KhywxxFxzdxjjlb id = khywxxFxzdxjjlbService.getOne(queryWrapper);
		 System.out.println("11111111=="+id.getId());
		 try {
			 if (jsonObject!=null  && jsonObject.size()>0){
				 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				 List<KhywxxFjxx> list = new ArrayList<>();

				 for (int i = 0; i < jsonObject.size(); i++) {
					 System.out.println("=======有"+jsonObject.size()+"个图片");
					 if (StringUtils.isEmpty(jsonObject.get(i).getId()) && StringUtils.isNotEmpty(jsonObject.get(i).getFjlj())) {
						 log.info("=========="+jsonObject);
						 log.info("===附件类型======="+jsonObject.get(i).getFjlx());
						 log.info("===附件id======="+jsonObject.get(i).getId());
						 KhywxxFjxx fjgl = new KhywxxFjxx();
						 if (id != null){
							 fjgl.setId(id.getId());
						 }
						 fjgl.setZdbh(jsonObject.get(i).getZdbh());
						 fjgl.setFjlx(jsonObject.get(i).getFjlx());
						 fjgl.setFjdx(jsonObject.get(i).getFjdx());
						 fjgl.setFjlj("/" +jsonObject.get(i).getFjlj());
						 fjgl.setFjfwlj("/"+jsonObject.get(i).getFjlj());
						 fjgl.setScsj(new Date());
						 fjgl.setScr(sysUser.getUsername());
						 list.add(fjgl);
					 }
				 }
				 System.out.println("list=="+list);
				 khywxxFjxxService.saveBatch(list);

			 }
		 }catch (Exception e){
			 log.error("巡检记录附件失败", e);
			 return  Result.error("巡检记录附件失败");
		 }
		 return Result.ok("保存成功");
	 }*/


	/**
	 * 编辑
	 *
	 * @param khywxxFjxx
	 * @return
	 */
	@AutoLog(value = "福祥站点附件表-编辑")
	@ApiOperation(value="福祥站点附件表-编辑", notes="福祥站点附件表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhywxxFjxx khywxxFjxx) {
		khywxxFjxxService.updateById(khywxxFjxx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "福祥站点附件表-通过id删除")
	@ApiOperation(value="福祥站点附件表-通过id删除", notes="福祥站点附件表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		khywxxFjxxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "福祥站点附件表-批量删除")
	@ApiOperation(value="福祥站点附件表-批量删除", notes="福祥站点附件表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.khywxxFjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "福祥站点附件表-通过id查询")
	@ApiOperation(value="福祥站点附件表-通过id查询", notes="福祥站点附件表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KhywxxFjxx khywxxFjxx = khywxxFjxxService.getById(id);
		return Result.ok(khywxxFjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khywxxFjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhywxxFjxx khywxxFjxx) {
      return super.exportXls(request, khywxxFjxx, KhywxxFjxx.class, "福祥站点附件表");
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
      return super.importExcel(request, response, KhywxxFjxx.class);
  }

}
