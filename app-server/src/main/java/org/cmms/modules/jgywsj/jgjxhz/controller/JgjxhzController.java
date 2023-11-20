package org.cmms.modules.jgywsj.jgjxhz.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.jgywsj.jgjxhz.entity.TbTjfxJgjxhz;
import org.cmms.modules.jgywsj.jgjxhz.service.IJgjxhzService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 机构绩效汇总
 * @Author: jeecg-boot
 * @Date:   2021-05-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="机构绩效汇总")
@RestController
@RequestMapping("/mobile/jgjxhz")
public class JgjxhzController extends JeecgController<TbTjfxJgjxhz, IJgjxhzService> {
	@Autowired
	private IJgjxhzService jgjxhzService;

	 @Autowired
	 private IHrBasOrganizationService hrBasOrganizationService;

	 @Autowired
	 private IVhrbasstaffpostService vhrbasstaffpostService;

	 @Autowired
	 private RedisUtil redisUtil;
	
	/**
	 * 分页列表查询
	 *
	 * @param jgjxhz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "机构绩效汇总-分页列表查询")
	@ApiOperation(value="机构绩效汇总-分页列表查询", notes="机构绩效汇总-分页列表查询")
	@GetMapping(value = "/page")
	public Result<?> queryPageList(TbTjfxJgjxhz jgjxhz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   @RequestParam(name="zzbz", required = false) String zzbz,
								   HttpServletRequest req) {
		QueryWrapper<TbTjfxJgjxhz> queryWrapper = QueryGenerator.initQueryWrapper(jgjxhz, req.getParameterMap());
		if (StringUtils.isEmpty(zzbz)) {
			//如果没上送组织标志，默认查询所有权限机构的数据
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(loginUser.getWorkNo());
			if (vhrbasstaffpost != null) {
				List<HrBasOrganization> organizationList = hrBasOrganizationService.queryAuthOrgList(vhrbasstaffpost.getZzbz());
				List<String> zzbzs = organizationList.stream().map(HrBasOrganization::getZzbz).collect(Collectors.toList());
				queryWrapper.in("zzbz", zzbzs);
			} else {
				return Result.error("未查询到任何数据！");
			}
		}
		//获取数据日期，默认查询数据日期的数据
		String sjrq = (String)redisUtil.get("app_sjrq");
		queryWrapper.eq("tjrq", DateUtil.parseDateFormat(sjrq, "yyyyMMdd"));

		Page<TbTjfxJgjxhz> page = new Page<TbTjfxJgjxhz>(pageNo, pageSize);
		IPage<TbTjfxJgjxhz> pageList = jgjxhzService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param jgjxhz
	 * @return
	 */
	@AutoLog(value = "机构绩效汇总-添加")
	@ApiOperation(value="机构绩效汇总-添加", notes="机构绩效汇总-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TbTjfxJgjxhz jgjxhz) {
		jgjxhzService.save(jgjxhz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param jgjxhz
	 * @return
	 */
	@AutoLog(value = "机构绩效汇总-编辑")
	@ApiOperation(value="机构绩效汇总-编辑", notes="机构绩效汇总-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TbTjfxJgjxhz jgjxhz) {
		jgjxhzService.updateById(jgjxhz);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机构绩效汇总-通过id删除")
	@ApiOperation(value="机构绩效汇总-通过id删除", notes="机构绩效汇总-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		jgjxhzService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "机构绩效汇总-批量删除")
	@ApiOperation(value="机构绩效汇总-批量删除", notes="机构绩效汇总-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jgjxhzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "机构绩效汇总-通过id查询")
	@ApiOperation(value="机构绩效汇总-通过id查询", notes="机构绩效汇总-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TbTjfxJgjxhz jgjxhz = jgjxhzService.getById(id);
		return Result.ok(jgjxhz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param jgjxhz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TbTjfxJgjxhz jgjxhz) {
      return super.exportXls(request, jgjxhz, TbTjfxJgjxhz.class, "机构绩效汇总");
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
      return super.importExcel(request, response, TbTjfxJgjxhz.class);
  }

}
