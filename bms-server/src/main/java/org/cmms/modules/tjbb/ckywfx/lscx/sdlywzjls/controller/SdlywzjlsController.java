package org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.entity.Sdlywzjls;
import org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.mapper.SdlywzjlsMapper;
import org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.service.ISdlywzjlsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 收单类业务资金流水
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="收单类业务资金流水")
@RestController
@RequestMapping("/sdlywzjls/sdlywzjls")
public class SdlywzjlsController extends JeecgController<Sdlywzjls, ISdlywzjlsService> {
	 @Autowired
	 private ISdlywzjlsService sdlywzjlsService;
	 @Autowired
	 private RedisUtil redisUtil;
	 @Autowired
	 private ISysDictService iSysDictService;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;

	/**
	 * 分页列表查询
	 *
	 * @param sdlywzjls
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "收单类业务资金流水-分页列表查询")
	@ApiOperation(value="收单类业务资金流水-分页列表查询", notes="收单类业务资金流水-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Sdlywzjls sdlywzjls,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(sdlywzjls, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(ISdlywzjlsService.class,sdlywzjlsService,pageNo,pageSize,queryWrapper,"lsh");
		return Result.ok(pageList);
	}

	 /**
	  * 提取
	  */
	 @AutoLog(value = "收单类业务资金流水-提取")
	 @ApiOperation(value="收单类业务资金流水-提取", notes="收单类业务资金流水-提取")
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject) {
		 String ksrq = jsonObject.getString("ksrq");
		 String jsrq = jsonObject.getString("jsrq");
		 if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(jsrq, "yyyy-MM-dd"))) {
			 return Result.error("结束日期必须小于当前系统日期！");
		 }
		 ksrq = ksrq.replaceAll("-", "");
		 jsrq = jsrq.replaceAll("-", "");
		 Result result = new Result<>();
		 if ("true".equalsIgnoreCase(sfdsjpt)) {
			 HashMap<String, String> params = new HashMap<>();
			 params.put("p_ksrq", ksrq);
			 params.put("p_jsrq", jsrq);
			 params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ckyw_sdlywzjls");
			 // `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
			 // count_tjbb_ckyw_sdlywzjls
			 boolean completionSignal = EtlUtil.callEtl("tjbb_common_init", params, 20);
			 result.setSuccess(completionSignal);
		 } else {
			 try {
				 sdlywzjlsService.pSdlywzjls(ksrq, jsrq);
				 result.setSuccess(true);
				 return result;
			 } catch (Throwable e) {
				 System.out.println(e);
				 log.error("提取失败", e.getMessage());
				 result.setSuccess(false);
			 }
		 }
		 // return Result.ok("提取成功！");
		 return result;
	 }

	/**
	 * 添加
	 *
	 * @param sdlywzjls
	 * @return
	 */
	@AutoLog(value = "收单类业务资金流水-添加")
	@ApiOperation(value="收单类业务资金流水-添加", notes="收单类业务资金流水-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Sdlywzjls sdlywzjls) {
		sdlywzjlsService.save(sdlywzjls);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param sdlywzjls
	 * @return
	 */
	@AutoLog(value = "收单类业务资金流水-编辑")
	@ApiOperation(value="收单类业务资金流水-编辑", notes="收单类业务资金流水-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Sdlywzjls sdlywzjls) {
		sdlywzjlsService.updateById(sdlywzjls);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "收单类业务资金流水-通过id删除")
	@ApiOperation(value="收单类业务资金流水-通过id删除", notes="收单类业务资金流水-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sdlywzjlsService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "收单类业务资金流水-批量删除")
	@ApiOperation(value="收单类业务资金流水-批量删除", notes="收单类业务资金流水-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sdlywzjlsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "收单类业务资金流水-通过id查询")
	@ApiOperation(value="收单类业务资金流水-通过id查询", notes="收单类业务资金流水-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Sdlywzjls sdlywzjls = sdlywzjlsService.getById(id);
		return Result.ok(sdlywzjls);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sdlywzjls
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Sdlywzjls sdlywzjls) {
      return super.exportXls(request, sdlywzjls, Sdlywzjls.class, "收单类业务资金流水");
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
      return super.importExcel(request, response, Sdlywzjls.class);
  }

}
