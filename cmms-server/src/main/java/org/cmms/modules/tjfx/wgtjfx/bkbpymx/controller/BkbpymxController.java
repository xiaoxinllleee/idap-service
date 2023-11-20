package org.cmms.modules.tjfx.wgtjfx.bkbpymx.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.wgtjfx.bkbpymx.entity.Bkbpymx;
import org.cmms.modules.tjfx.wgtjfx.bkbpymx.service.IBkbpymxService;
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
 * @Description: 背靠背评议明细
 * @Author: jeecg-boot
 * @Date:   2022-04-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="背靠背评议明细")
@RestController
@RequestMapping("/wgtjfx/bkbpymx")
public class BkbpymxController extends JeecgController<Bkbpymx, IBkbpymxService> {
	@Autowired
	private IBkbpymxService bkbpymxService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private ISysDictService sysDictService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bkbpymx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "背靠背评议明细-分页列表查询")
	@ApiOperation(value="背靠背评议明细-分页列表查询", notes="背靠背评议明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Bkbpymx bkbpymx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		boolean qxfk = false;
		if (StringUtils.isNotEmpty(bkbpymx.getLx())){
			if ("qxfk".equals(bkbpymx.getLx())){
				bkbpymx.setLx(null);
				qxfk = true;
			}
		}
		String wgbh = bkbpymx.getWgbh();
		bkbpymx.setWgbh(null);
		QueryWrapper<Bkbpymx> queryWrapper = QueryGenerator.initQueryWrapper(bkbpymx, req.getParameterMap());
		if (StringUtils.isNotEmpty(wgbh)) {
			//查询下级网格
			queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id ");
		}
		if (qxfk)
			queryWrapper.in("fkdj","2","3");
		Page<Bkbpymx> page = new Page<Bkbpymx>(pageNo, pageSize);
		IPage<Bkbpymx> pageList = bkbpymxService.page(page, queryWrapper);
		String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getUsername());
		String bysxqxDictCode = "py_bysxqx";
		if ("405".equals(qydm)) {
			bysxqxDictCode = "py_bysxqx_ny";
		} else if ("310".equals(qydm) || "320".equals(qydm)) {
			bysxqxDictCode = "py_bysxqx_sf";
		} else if ("420".equals(qydm)) {
			bysxqxDictCode = "py_bysxqx_ls";
		} else if ("095".equals(qydm)) {
			bysxqxDictCode = "py_bysxqx_ty";
		}
		List<Bkbpymx> bkbpymxList = pageList.getRecords();
		for (Bkbpymx pymx : bkbpymxList) {
			if (StringUtils.isNotEmpty(pymx.getBysxqx())) {
				StringBuffer textValue=new StringBuffer();
				List<String> containsKeys = new ArrayList<>();
				String[] keys = pymx.getBysxqx().split(",");
				for (String k : keys) {
					String tmpValue = null;
					if (k.trim().length() == 0) {
						continue; //跳过循环
					}
					if(containsKeys.contains(k)) {
						continue; //跳过循环
					}
					containsKeys.add(k);

					tmpValue = sysDictService.queryDictTextByKey(bysxqxDictCode, k.trim());
					if (tmpValue != null) {
						if (!"".equals(textValue.toString())) {
							textValue.append(",");
						}
						textValue.append(tmpValue);
					}
				}

				pymx.setBysxqx(textValue.toString());
			}
		}
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param bkbpymx
	 * @return
	 */
	@AutoLog(value = "背靠背评议明细-添加")
	@ApiOperation(value="背靠背评议明细-添加", notes="背靠背评议明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Bkbpymx bkbpymx) {
		bkbpymxService.save(bkbpymx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param bkbpymx
	 * @return
	 */
	@AutoLog(value = "背靠背评议明细-编辑")
	@ApiOperation(value="背靠背评议明细-编辑", notes="背靠背评议明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Bkbpymx bkbpymx) {
		bkbpymxService.updateById(bkbpymx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "背靠背评议明细-通过id删除")
	@ApiOperation(value="背靠背评议明细-通过id删除", notes="背靠背评议明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bkbpymxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "背靠背评议明细-批量删除")
	@ApiOperation(value="背靠背评议明细-批量删除", notes="背靠背评议明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bkbpymxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "背靠背评议明细-通过id查询")
	@ApiOperation(value="背靠背评议明细-通过id查询", notes="背靠背评议明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Bkbpymx bkbpymx = bkbpymxService.getById(id);
		return Result.ok(bkbpymx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param bkbpymx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Bkbpymx bkbpymx) {
      return super.exportXls(request, bkbpymx, Bkbpymx.class, "背靠背评议明细");
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
      return super.importExcel(request, response, Bkbpymx.class);
  }

}
