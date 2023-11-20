package org.cmms.modules.ywgl.yxbldkgl.dklxqkbjyqtz.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.util.DateUtil;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.ywgl.yxbldkgl.dklxqkbjyqtz.entity.Dklxqkbjyqtz;
import org.cmms.modules.ywgl.yxbldkgl.dklxqkbjyqtz.service.IDklxqkbjyqtzService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 贷款五级分类人工复核
 * @Author: Penghr
 * @Date:   2022-08-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款五级分类人工复核")
@RestController
@RequestMapping("/yxbldkgl/dklxqkbjyqtz")
public class DklxqkbjyqtzController extends JeecgController<Dklxqkbjyqtz, IDklxqkbjyqtzService> {
	@Autowired
	private IDklxqkbjyqtzService dklxqkbjyqtzService;
	@Autowired
	private IDkzdkbService iDkzdkbService;
	@Autowired
	private ISysLogService iSysLogService;
	@Value("${com.etl.sfdsjpt}")
	private String sfdsjpt;

	/**
	 * 分页列表查询
	 *
	 * @param dklxqkbjyqtz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款五级分类人工复核-分页列表查询")
	@ApiOperation(value="贷款五级分类人工复核-分页列表查询", notes="贷款五级分类人工复核-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dklxqkbjyqtz dklxqkbjyqtz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dklxqkbjyqtz> queryWrapper = QueryGenerator.initQueryWrapper(dklxqkbjyqtz, req.getParameterMap());
		IPage<Dklxqkbjyqtz> pageList = PageUtil.toPage(IDklxqkbjyqtzService.class,dklxqkbjyqtzService,pageNo,pageSize,queryWrapper,"BRANCH_NO","IDENT_NO","ACCT_NO");
		return Result.ok(pageList);
	}

	/**
	 * 数据抽取
	 *
	 * @return jsonObject
	 */
	@AutoLog(value = "贷款五级分类人工复核-数据抽取")
	@ApiOperation(value = "贷款五级分类人工复核-数据抽取", notes = "贷款五级分类人工复核-数据抽取")
	@RequestMapping(value = "/extraction")
	public Result<?> Extraction(@RequestBody Dklxqkbjyqtz requestparam) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			Date maxdataloaddate;
			String fiscal_date = "";
			Date tjyf = requestparam.getTjyf();
			log.info("统计月份月初日期: "+simpleDateFormat.format(tjyf));
			Date monthlastdate = org.cmms.common.util.DateUtil.getLastday_Month(tjyf,0);
			log.info("统计月份月底日期: "+simpleDateFormat.format(monthlastdate));
			//获取最大贷款数据入库日期
			if ("true".equals(sfdsjpt)) {
				maxdataloaddate = org.cmms.common.util.DateUtil.string2Date(iDkzdkbService.queryMaxDataDate(),"yyyyMMdd");
			} else {
				maxdataloaddate = iSysLogService.dksjrq();
			}
			log.info("最大贷款数据入库日期: "+simpleDateFormat.format(maxdataloaddate));
			//若统计月份为当前月份，则数据日期取最大贷款数据入库日期
			//若统计月份非当前月份，则数据日期取统计月份月底日期
			String sysmonth = simpleDateFormat.format(new Date()).substring(0,6);
			String tjyfmonth = simpleDateFormat.format(tjyf).substring(0,6);
			if (tjyfmonth.equals(sysmonth)) {
				fiscal_date = DateUtil.date2String(maxdataloaddate,"yyyyMMdd");
			} else {
				fiscal_date = DateUtil.date2String(monthlastdate,"yyyy-MM-dd");
			}
			log.info("fiscal_date: "+fiscal_date);
			if ("true".equals(sfdsjpt)) {
				 HashMap<String, String> params = new HashMap<>();
				 params.put("fiscal_date",fiscal_date);
				 params.put("etl_task", "kiss.domain.application.cdkyw.proc_yxbldkgl_lxqkbjyqtz");
				 boolean flag = EtlUtil.callEtl("cdkyw_common_init", params, 15);
				 log.info("贷款五级分类人工复核-数据抽取-是否成功？-"+flag);
				 if (flag) {
					 return Result.ok("数据抽取成功！");
				 } else {
					 return Result.error("数据抽取失败，请联系管理员处理！");
				 }
			} else {
				log.info("贷款五级分类人工复核-数据抽取-未做Oracle数据提取");
				return Result.ok();
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			return Result.error(throwable.getMessage());
		}
	}

	/**
	 * 解除手工标记（弃用）
	 * @param dklxqkbjyqtz
	 * @return
	 */
//	@AutoLog(value = "贷款五级分类人工复核-解除手工标记")
//	@ApiOperation(value="贷款五级分类人工复核-解除手工标记", notes="贷款五级分类人工复核-解除手工标记")
//	@PutMapping(value = "/cancelMark")
//	public Result<?> cancelMark(@RequestBody Dklxqkbjyqtz dklxqkbjyqtz) {
//		try {
//			UpdateWrapper<Dklxqkbjyqtz> updateWrapper = new UpdateWrapper<>();
//			updateWrapper.eq("tjyf",dklxqkbjyqtz.getTjyf());
//			updateWrapper.eq("branch_no",dklxqkbjyqtz.getBranchNo());
//			updateWrapper.eq("ident_no",dklxqkbjyqtz.getIdentNo());
//			updateWrapper.eq("acct_no",dklxqkbjyqtz.getAcctNo());
//			//表主键不能更新（Kudu）
//			dklxqkbjyqtz.setTjyf(null);
//			dklxqkbjyqtz.setBranchNo(null);
//			dklxqkbjyqtz.setIdentNo(null);
//			dklxqkbjyqtz.setAcctNo(null);
//			dklxqkbjyqtz.setSfjcsgbj(1);
//			dklxqkbjyqtz.setJcsgbjrq(new Timestamp(System.currentTimeMillis()));
//			dklxqkbjyqtz.setLrbz(2);
//			dklxqkbjyqtz.setLrr(getLoginUser().getUsername());
//			dklxqkbjyqtz.setLrsj(new Timestamp(System.currentTimeMillis()));
//			dklxqkbjyqtzService.update(dklxqkbjyqtz,updateWrapper);
//			return Result.ok();
//		} catch (Throwable throwable) {
//			throwable.printStackTrace();
//			return Result.error(throwable.getMessage());
//		}
//	}

	/**
	 * 添加
	 *
	 * @param dklxqkbjyqtz
	 * @return
	 */
	@AutoLog(value = "贷款五级分类人工复核-添加")
	@ApiOperation(value="贷款五级分类人工复核-添加", notes="贷款五级分类人工复核-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dklxqkbjyqtz dklxqkbjyqtz) {
		dklxqkbjyqtzService.save(dklxqkbjyqtz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dklxqkbjyqtz
	 * @return
	 */
	@AutoLog(value = "贷款五级分类人工复核-编辑")
	@ApiOperation(value="贷款五级分类人工复核-编辑", notes="贷款五级分类人工复核-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dklxqkbjyqtz dklxqkbjyqtz) {
		try {
			UpdateWrapper<Dklxqkbjyqtz> updateWrapper = new UpdateWrapper<>();
			updateWrapper.eq("tjyf",dklxqkbjyqtz.getTjyf());
			updateWrapper.eq("branch_no",dklxqkbjyqtz.getBranchNo());
			updateWrapper.eq("ident_no",dklxqkbjyqtz.getIdentNo());
			updateWrapper.eq("acct_no",dklxqkbjyqtz.getAcctNo());
			//表主键不能更新（Kudu）
			dklxqkbjyqtz.setTjyf(null);
			dklxqkbjyqtz.setBranchNo(null);
			dklxqkbjyqtz.setIdentNo(null);
			dklxqkbjyqtz.setAcctNo(null);
			dklxqkbjyqtz.setLrbz(2);
			dklxqkbjyqtz.setLrr(getLoginUser().getUsername());
			dklxqkbjyqtz.setLrsj(new Timestamp(System.currentTimeMillis()));
			dklxqkbjyqtzService.update(dklxqkbjyqtz,updateWrapper);
			return Result.ok("编辑成功!");
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("贷款五级分类人工复核-编辑失败！"+e.getMessage());
			return Result.error("编辑失败，请联系系统管理员！");
		}
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款五级分类人工复核-通过id删除")
	@ApiOperation(value="贷款五级分类人工复核-通过id删除", notes="贷款五级分类人工复核-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dklxqkbjyqtzService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款五级分类人工复核-批量删除")
	@ApiOperation(value="贷款五级分类人工复核-批量删除", notes="贷款五级分类人工复核-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dklxqkbjyqtzService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款五级分类人工复核-通过id查询")
	@ApiOperation(value="贷款五级分类人工复核-通过id查询", notes="贷款五级分类人工复核-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dklxqkbjyqtz dklxqkbjyqtz = dklxqkbjyqtzService.getById(id);
		return Result.ok(dklxqkbjyqtz);
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param dklxqkbjyqtz
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, Dklxqkbjyqtz dklxqkbjyqtz) {
		return super.exportXls(request, dklxqkbjyqtz, Dklxqkbjyqtz.class, "贷款五级分类人工复核");
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
		return super.importExcel(request, response, Dklxqkbjyqtz.class);
	}

}
