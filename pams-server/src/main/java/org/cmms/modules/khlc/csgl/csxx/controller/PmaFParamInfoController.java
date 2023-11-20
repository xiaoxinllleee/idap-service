package org.cmms.modules.khlc.csgl.csxx.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.khlc.csgl.csxx.entity.PmaFParamInfo;
import org.cmms.modules.khlc.csgl.csxx.entity.PmaFParamInfoVO;
import org.cmms.modules.khlc.csgl.csxx.service.IPmaFParamInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.util.PamsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 参数信息
 * @Author: jeecg-boot
 * @Date:   2021-03-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="参数信息")
@RestController
@RequestMapping("/csgl/csxx/pmaFParamInfo")
public class PmaFParamInfoController extends JeecgController<PmaFParamInfo, IPmaFParamInfoService> {
	@Autowired
	private IPmaFParamInfoService pmaFParamInfoService;

	@Autowired
	private IHrBasOrganizationService hrBasOrganizationService;


	/**
	 * 分页列表查询
	 *
	 * @param pmaFParamInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "参数信息-分页列表查询")
	@ApiOperation(value="参数信息-分页列表查询", notes="参数信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaFParamInfo pmaFParamInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<IPage<PmaFParamInfo>> result = new Result<IPage<PmaFParamInfo>>();
		Page<PmaFParamInfo> page = new Page<PmaFParamInfo>(pageNo, pageSize);
		String menuId = req.getParameter("dirId");
		String csbh = req.getParameter("paramId");
		String csmc = req.getParameter("paramName");
		IPage<PmaFParamInfo> pageList = pmaFParamInfoService.getParamInfoByJdId(page,menuId,csmc,csbh);

		return Result.ok(pageList);
	}

	 @AutoLog(value = "参数信息-分页列表查询")
	 @ApiOperation(value="参数信息-分页列表查询", notes="参数信息-分页列表查询")
	 @GetMapping(value = "/listNoDir")
	 public Result<?> queryPageListNoDir(PmaFParamInfo pmaFParamInfo,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<PmaFParamInfo> queryWrapper = QueryGenerator.initQueryWrapper(pmaFParamInfo, req.getParameterMap());
		 Page<PmaFParamInfo> page = new Page<PmaFParamInfo>(pageNo, pageSize);
		 IPage<PmaFParamInfo> pageList = pmaFParamInfoService.page(page, queryWrapper);
		 return Result.ok(pageList);
	 }
	
	/**
	 * 添加
	 *
	 * @param pmaFParamInfo
	 * @return
	 */
	@AutoLog(value = "参数信息-添加")
	@ApiOperation(value="参数信息-添加", notes="参数信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaFParamInfo pmaFParamInfo) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		pmaFParamInfo.setOrgId(sysUser.getOrgCode());
		pmaFParamInfoService.save(pmaFParamInfo);
		return Result.ok("添加成功！");
	}

	 @PostMapping(value = "/addjgcsxx")
	 public Result<?> addjgcsxx(@RequestBody PmaFParamInfoVO pmaFParamInfo) {

		if (pmaFParamInfo != null && CollUtil.isNotEmpty(pmaFParamInfo.getJgcsxx())){
			int i1 = pmaFParamInfoService.deleteByParamIdAndArea(pmaFParamInfo.getParamId(), PamsConstant.PARAM_INFO_AREA_JG);
			log.info("本次删除{}下机构参数{}条",pmaFParamInfo.getParamId(),i1);
			PmaFParamInfo insert = new PmaFParamInfo();
			BeanUtil.copyProperties(pmaFParamInfo,insert);
			List<PmaFParamInfoVO> jgcsxx = pmaFParamInfo.getJgcsxx();
			String paramId = pmaFParamInfo.getParamId();
			System.out.println(paramId);
			insert.setParamId(paramId);
			insert.setCreateBy(getLoginUser().getUsername());
			insert.setArea(PamsConstant.PARAM_INFO_AREA_JG);
			for (int i = 0; i < jgcsxx.size(); i++) {
				PmaFParamInfoVO pmaFParamInfo1 = jgcsxx.get(i);
				insert.setId(IdUtil.simpleUUID());
				insert.setCreateTime(new Date());
				insert.setParamValue(pmaFParamInfo1.getParamValue());
				insert.setMaxLimit(pmaFParamInfo1.getMaxLimit());
				insert.setMinLimit(pmaFParamInfo1.getMinLimit());
				insert.setJgdm(pmaFParamInfo1.getJgdm());
				pmaFParamInfoService.save(insert);
			}
		}
		 return Result.ok("添加成功！");
	 }


	 @PostMapping(value = "/addygcsxx")
	 public Result<?> addygcsxx(@RequestBody PmaFParamInfoVO pmaFParamInfo) {

		 if (pmaFParamInfo != null && CollUtil.isNotEmpty(pmaFParamInfo.getJgcsxx())){
			 int i1 = pmaFParamInfoService.deleteByParamIdAndArea(pmaFParamInfo.getParamId(), PamsConstant.PARAM_INFO_AREA_YG);
			 log.info("本次删除{}下机构参数{}条",pmaFParamInfo.getParamId(),i1);
			 PmaFParamInfo insert = new PmaFParamInfo();
			 BeanUtil.copyProperties(pmaFParamInfo,insert);
			 List<PmaFParamInfoVO> jgcsxx = pmaFParamInfo.getJgcsxx();
			 String paramId = pmaFParamInfo.getParamId();
			 System.out.println(paramId);
			 insert.setParamId(paramId);
			 insert.setCreateBy(getLoginUser().getUsername());
			 insert.setArea(PamsConstant.PARAM_INFO_AREA_YG);
			 for (int i = 0; i < jgcsxx.size(); i++) {
				 PmaFParamInfoVO pmaFParamInfo1 = jgcsxx.get(i);
				 insert.setId(IdUtil.simpleUUID());
				 insert.setCreateTime(new Date());
				 insert.setParamValue(pmaFParamInfo1.getParamValue());
				 insert.setMaxLimit(pmaFParamInfo1.getMaxLimit());
				 insert.setMinLimit(pmaFParamInfo1.getMinLimit());
				 insert.setYggh(pmaFParamInfo1.getYggh());
				 String ywjgdmByZzbz = hrBasOrganizationService.getYwjgdmByZzbz(pmaFParamInfo1.getZzbz());
				 if (StringUtils.isNoneBlank(ywjgdmByZzbz))
				 	insert.setJgdm(ywjgdmByZzbz);
				 pmaFParamInfoService.save(insert);
			 }
		 }
		 return Result.ok("添加成功！");
	 }
	
	/**
	 * 编辑
	 *
	 * @param pmaFParamInfo
	 * @return
	 */
	@AutoLog(value = "参数信息-编辑")
	@ApiOperation(value="参数信息-编辑", notes="参数信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaFParamInfo pmaFParamInfo) {
		pmaFParamInfoService.updateById(pmaFParamInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "参数信息-通过id删除")
	@ApiOperation(value="参数信息-通过id删除", notes="参数信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pmaFParamInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "参数信息-批量删除")
	@ApiOperation(value="参数信息-批量删除", notes="参数信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaFParamInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "参数信息-通过id查询")
	@ApiOperation(value="参数信息-通过id查询", notes="参数信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaFParamInfo pmaFParamInfo = pmaFParamInfoService.getById(id);
		return Result.ok(pmaFParamInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaFParamInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaFParamInfo pmaFParamInfo) {
      return super.exportXls(request, pmaFParamInfo, PmaFParamInfo.class, "参数信息");
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
      return super.importExcel(request, response, PmaFParamInfo.class);
  }


	 /**
	  * 通过id查询
	  *
	  * @param paramId,area
	  * @return
	  */
	 @AutoLog(value = "参数信息-通过id查询")
	 @ApiOperation(value="参数信息-通过id查询", notes="参数信息-通过id查询")
	 @GetMapping(value = "/queryByParamIdAndArea")
	 public Result<?> queryByParamIdAndArea(@RequestParam(name="paramId",required=true) String paramId
	 ,@RequestParam(name="area",required=true) String area) {
		 QueryWrapper queryWrapper = new QueryWrapper();
		 queryWrapper.eq("PARAM_ID",paramId);
		 queryWrapper.eq("AREA",area);
		 List<PmaFParamInfo> list = pmaFParamInfoService.list(queryWrapper);
		 return Result.ok(list);
	 }

}
