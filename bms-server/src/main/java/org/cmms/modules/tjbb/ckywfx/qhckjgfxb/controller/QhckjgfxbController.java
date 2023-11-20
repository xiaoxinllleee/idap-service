package org.cmms.modules.tjbb.ckywfx.qhckjgfxb.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.entity.Ckkhcdknrptj;
import org.cmms.modules.tjbb.ckywfx.qhckjgfxb.entity.Qhckjgfxb;
import org.cmms.modules.tjbb.ckywfx.qhckjgfxb.mapper.QhckjgfxbMapper;
import org.cmms.modules.tjbb.ckywfx.qhckjgfxb.service.IQhckjgfxbService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.cmms.modules.util.EtlUtil.redisUtil;

/**
 * @Description: 全行存款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-10-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="全行存款结构分析表")
@RestController
@RequestMapping("/qhckjgfxb/qhckjgfxb")
public class QhckjgfxbController extends JeecgController<Qhckjgfxb, IQhckjgfxbService> {
	@Autowired
	private IQhckjgfxbService qhckjgfxbService;
	@Autowired
	private RedisUtil redisUtil;
	@Value("${com.etl.sfdsjpt}")
	private String sfdsjpt;

	/**
	 * 分页列表查询
	 *
	 * @param qhckjgfxb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "全行存款结构分析表-分页列表查询")
	@ApiOperation(value="全行存款结构分析表-分页列表查询", notes="全行存款结构分析表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Qhckjgfxb qhckjgfxb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(qhckjgfxb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IQhckjgfxbService.class,qhckjgfxbService,pageNo,pageSize,queryWrapper,"tjrq");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	@RequestMapping(value = "init")
	public Result<?> init(@RequestBody JSONObject jsonObject) {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		//String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
		String tjrq = jsonObject.getString("tjrq");
		if (tjrq == null || StringUtils.isBlank(tjrq)) {
			return Result.error("统计日期不能为空！");
		}
		if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjrq, "yyyy-MM-dd"))) {
			return Result.error("统计日期必须小于当前系统日期！");
		}
		// tjrq = tjrq.replaceAll("-", "");
		Result result = new Result<>();
		if ("true".equalsIgnoreCase(sfdsjpt)) {
			HashMap<String, String> params = new HashMap<>();
			params.put("fiscal_date", tjrq);
			params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ckyw_ckjgfxb_qh");
			// count_tjbb_ckyw_ckjgfxb_qh
			boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
			result.setSuccess(falg);
		} else {
			try {
				qhckjgfxbService.pQhckjgfxb(tjrq);
				result.setSuccess(true);
				return result;
			} catch (Exception e) {
				System.out.println(e);
				log.error("提取失败", e.getMessage());
				result.setSuccess(false);
			}
		}
		return result;
	}

	/**
	 * 添加
	 *
	 * @param qhckjgfxb
	 * @return
	 */
	@AutoLog(value = "全行存款结构分析表-添加")
	@ApiOperation(value="全行存款结构分析表-添加", notes="全行存款结构分析表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Qhckjgfxb qhckjgfxb) {
		qhckjgfxbService.save(qhckjgfxb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param qhckjgfxb
	 * @return
	 */
	@AutoLog(value = "全行存款结构分析表-编辑")
	@ApiOperation(value="全行存款结构分析表-编辑", notes="全行存款结构分析表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Qhckjgfxb qhckjgfxb) {
		qhckjgfxbService.updateById(qhckjgfxb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行存款结构分析表-通过id删除")
	@ApiOperation(value="全行存款结构分析表-通过id删除", notes="全行存款结构分析表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		qhckjgfxbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "全行存款结构分析表-批量删除")
	@ApiOperation(value="全行存款结构分析表-批量删除", notes="全行存款结构分析表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qhckjgfxbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "全行存款结构分析表-通过id查询")
	@ApiOperation(value="全行存款结构分析表-通过id查询", notes="全行存款结构分析表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Qhckjgfxb qhckjgfxb = qhckjgfxbService.getById(id);
		return Result.ok(qhckjgfxb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request,Qhckjgfxb qhckjgfxb ) {
	  return super.exportXls(request, qhckjgfxb, Qhckjgfxb.class, "全行存款结构分析表");
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
      return super.importExcel(request, response, Qhckjgfxb.class);
  }

}
