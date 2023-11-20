package org.cmms.modules.yxdygl.cwhfjl.controller;

import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.yxdygl.cwhfjl.entity.Cwhfjl;
import org.cmms.modules.yxdygl.cwhfjl.entity.CwhfjlFjxx;
import org.cmms.modules.yxdygl.cwhfjl.entity.CwhfxxVO;
import org.cmms.modules.yxdygl.cwhfjl.service.ICwhfjlFjxxService;
import org.cmms.modules.yxdygl.cwhfjl.service.ICwhfjlService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;
import org.cmms.modules.yxdygl.yxdyglmain.entity.AppYxdyglMainVO;
import org.cmms.modules.yxdygl.yxdyglmain.entity.WgxxFjVO;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 村委回访
 * @Author: jeecg-boot
 * @Date:   2023-06-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="村委回访")
@RestController
@RequestMapping("/cwhfjl/cwhfjl")
public class CwhfjlController extends JeecgController<Cwhfjl, ICwhfjlService> {
	@Autowired
	private ICwhfjlService cwhfjlService;
	 @Autowired
	 private ICwhfjlFjxxService cwhfjlFjxxService;
	/**
	 * 分页列表查询
	 *
	 * @param cwhfjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "村委回访-分页列表查询")
	@ApiOperation(value="村委回访-分页列表查询", notes="村委回访-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Cwhfjl cwhfjl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Cwhfjl> queryWrapper = QueryGenerator.initQueryWrapper(cwhfjl, req.getParameterMap());
		queryWrapper.orderByDesc("hfrq");
		Page<Cwhfjl> page = new Page<Cwhfjl>(pageNo, pageSize);
		IPage<Cwhfjl> pageList = cwhfjlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param cwhfjl
	 * @return
	 */
	@AutoLog(value = "村委回访-添加")
	@ApiOperation(value="村委回访-添加", notes="村委回访-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Cwhfjl cwhfjl) {
		cwhfjlService.save(cwhfjl);
		return Result.ok("添加成功！");
	}

	 @RequestMapping(value = "/edit")
	 public Result<?> edit(CwhfxxVO cwhfxxVO) {
		 System.out.println(cwhfxxVO.toString());
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 Cwhfjl cwhfjl = new Cwhfjl();
		 BeanUtils.copyProperties(cwhfxxVO, cwhfjl);
		 cwhfjl.setHfr(loginUser.getWorkNo());
		 cwhfjl.setHfrq(new Date());
		 cwhfjlService.save(cwhfjl);
		 //附件有变化时 才会传值进来 当有值 先删除 后新增即可
		 if (CollUtil.isNotEmpty(cwhfxxVO.getYxdyfjxxList())) {
			 List<WgxxFjVO> yxdyfjxxList = cwhfxxVO.getYxdyfjxxList();
			 List<CwhfjlFjxx> list = new ArrayList<>();
			 for (int i = 0; i < yxdyfjxxList.size(); i++) {
				 WgxxFjVO wgxxFjVO = yxdyfjxxList.get(i);
				 CwhfjlFjxx yxdyfjxx = new CwhfjlFjxx();
				 yxdyfjxx.setHfId(cwhfjl.getId());
				 yxdyfjxx.setFjlx(wgxxFjVO.getZllx() + "");
				 yxdyfjxx.setFjmc(wgxxFjVO.getName());
				 yxdyfjxx.setScsj(new Date());
				 yxdyfjxx.setScr(loginUser.getUsername());
				 yxdyfjxx.setFjdx(new BigDecimal(wgxxFjVO.getSize()));
				 yxdyfjxx.setFjlj(wgxxFjVO.getFwlj());
				 yxdyfjxx.setFwlj(wgxxFjVO.getFwlj());
				 yxdyfjxx.setCreateBy(loginUser.getUsername());
				 yxdyfjxx.setUpdateBy(loginUser.getUsername());
				 list.add(yxdyfjxx);
			 }
			 cwhfjlFjxxService.saveBatch(list);
		 }
		 return Result.ok();
	 }
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "村委回访-通过id删除")
	@ApiOperation(value="村委回访-通过id删除", notes="村委回访-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cwhfjlService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "村委回访-批量删除")
	@ApiOperation(value="村委回访-批量删除", notes="村委回访-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cwhfjlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "村委回访-通过id查询")
	@ApiOperation(value="村委回访-通过id查询", notes="村委回访-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Cwhfjl cwhfjl = cwhfjlService.getById(id);
		return Result.ok(cwhfjl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cwhfjl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Cwhfjl cwhfjl) {
      return super.exportXls(request, cwhfjl, Cwhfjl.class, "村委回访");
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
      return super.importExcel(request, response, Cwhfjl.class);
  }

}
