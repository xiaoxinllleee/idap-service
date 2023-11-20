package org.cmms.modules.hr.xsgl.gwkhsz.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.modules.hr.xsgl.gwkhsz.entity.PmaGwkhsz;
import org.cmms.modules.hr.xsgl.gwkhsz.entity.PmaGwkhszDTO;
import org.cmms.modules.hr.xsgl.gwkhsz.entity.PmaGwkhszJG;
import org.cmms.modules.hr.xsgl.gwkhsz.service.IPmaGwkhszService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 岗位考核设置
 * @Author: jeecg-boot
 * @Date:   2021-10-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="岗位考核设置")
@RestController
@RequestMapping("/jxkh/pmaGwkhsz")
public class PmaGwkhszController extends JeecgController<PmaGwkhsz, IPmaGwkhszService> {
	
	/**
	 * 分页列表查询
	 *
	 * @param pmaGwkhsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "岗位考核设置-分页列表查询")
	@ApiOperation(value="岗位考核设置-分页列表查询", notes="岗位考核设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaGwkhsz pmaGwkhsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PmaGwkhsz> queryWrapper = QueryGenerator.initQueryWrapper(pmaGwkhsz, req.getParameterMap());
		queryWrapper.isNull("zzbz");
		Page<PmaGwkhsz> page = new Page<PmaGwkhsz>(pageNo, pageSize);
		IPage<PmaGwkhsz> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 @GetMapping(value = "/listAll")
	 public Result<?> listAll(@RequestParam(name="schemeId",required=true) String schemeId
			 ,@RequestParam(name="indexId",required=true) String indexId) {
		 LambdaQueryWrapper<PmaGwkhsz> lambdaQueryWrapper = new LambdaQueryWrapper<PmaGwkhsz>();
		 lambdaQueryWrapper.eq(PmaGwkhsz::getSchemeId,schemeId);
		 lambdaQueryWrapper.eq(PmaGwkhsz::getIndexId,indexId);
		 lambdaQueryWrapper.isNotNull(PmaGwkhsz::getZzbz);
		 List<PmaGwkhsz> list = service.list(lambdaQueryWrapper);
		 return Result.ok(list);
	 }
	
	/**
	 * 添加
	 *
	 * @param pmaGwkhsz
	 * @return
	 */
	@AutoLog(value = "岗位考核设置-添加")
	@ApiOperation(value="岗位考核设置-添加", notes="岗位考核设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PmaGwkhsz pmaGwkhsz) {
		service.save(pmaGwkhsz);
		return Result.ok("添加成功！");
	}


	@PostMapping(value = "/addJg")
	public Result<?> addJg(@RequestBody PmaGwkhszDTO pmaGwkhsz) {
		LambdaQueryWrapper<PmaGwkhsz> lambdaQueryWrapper = new LambdaQueryWrapper<PmaGwkhsz>();
		lambdaQueryWrapper.eq(PmaGwkhsz::getIndexId,pmaGwkhsz.getIndexId());
		lambdaQueryWrapper.eq(PmaGwkhsz::getSchemeId,pmaGwkhsz.getSchemeId());
		lambdaQueryWrapper.isNotNull(PmaGwkhsz::getZzbz);
		service.remove(lambdaQueryWrapper);

		List<String> zzbzs = new ArrayList<>();
		if (CollUtil.isNotEmpty(pmaGwkhsz.getJginfos())){
			List<PmaGwkhszJG> jginfos = pmaGwkhsz.getJginfos();
			for (int i = 0; i < jginfos.size(); i++) {
				PmaGwkhszJG pmaGwkhszJG = jginfos.get(i);
				if (!zzbzs.contains(pmaGwkhszJG.getZzbz())){
					PmaGwkhsz insert = new PmaGwkhsz();
					insert.setIndexId(pmaGwkhsz.getIndexId());
					insert.setSchemeId(pmaGwkhsz.getSchemeId());
					insert.setZbdj(pmaGwkhszJG.getZbdj());
					insert.setZbdw(pmaGwkhszJG.getZbdw());
					insert.setTjxs(pmaGwkhszJG.getTjxs());
					insert.setZbqz(pmaGwkhszJG.getZbqz());
					insert.setZzbz(pmaGwkhszJG.getZzbz());
					if (pmaGwkhszJG.getRwwdj() != null)
						insert.setRwwdj(pmaGwkhszJG.getRwwdj());
					service.save(insert);
				}
				zzbzs.add(pmaGwkhszJG.getZzbz());
			}
		}

		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param pmaGwkhsz
	 * @return
	 */
	@AutoLog(value = "岗位考核设置-编辑")
	@ApiOperation(value="岗位考核设置-编辑", notes="岗位考核设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaGwkhsz pmaGwkhsz) {
		service.updateById(pmaGwkhsz);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "岗位考核设置-通过id删除")
	@ApiOperation(value="岗位考核设置-通过id删除", notes="岗位考核设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		PmaGwkhsz byId = service.getById(id);
		if (byId != null){
			LambdaQueryWrapper<PmaGwkhsz> lambdaQueryWrapper = new LambdaQueryWrapper<PmaGwkhsz>();
			lambdaQueryWrapper.eq(PmaGwkhsz::getIndexId,byId.getIndexId());
			lambdaQueryWrapper.eq(PmaGwkhsz::getSchemeId,byId.getSchemeId());
			service.remove(lambdaQueryWrapper);
		}
		service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "岗位考核设置-批量删除")
	@ApiOperation(value="岗位考核设置-批量删除", notes="岗位考核设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "岗位考核设置-通过id查询")
	@ApiOperation(value="岗位考核设置-通过id查询", notes="岗位考核设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaGwkhsz pmaGwkhsz = service.getById(id);
		return Result.ok(pmaGwkhsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaGwkhsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaGwkhsz pmaGwkhsz) {
      return super.exportXls(request, pmaGwkhsz, PmaGwkhsz.class, "岗位考核设置");
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
      return super.importExcel(request, response, PmaGwkhsz.class);
  }

}
