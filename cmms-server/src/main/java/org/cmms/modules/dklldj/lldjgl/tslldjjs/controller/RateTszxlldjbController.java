package org.cmms.modules.dklldj.lldjgl.tslldjjs.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;

import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.service.IRateZxllcxService;
import org.cmms.modules.dklldj.lldjgl.tslldjjs.entity.RateTszxlldjb;
import org.cmms.modules.dklldj.lldjgl.tslldjjs.service.IRateTszxlldjbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 特殊利率定价
 * @Author: jeecg-boot
 * @Date:   2021-04-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="特殊利率定价")
@RestController
@RequestMapping("/rate/rateTszxlldjb")
public class RateTszxlldjbController extends JeecgController<RateTszxlldjb, IRateTszxlldjbService> {
	 @Autowired
	 private IRateTszxlldjbService iRateTszxlldjbService;
	 @Autowired
	 IDictValueQuery iDictValueQuery;
	 @Autowired
	 private IRateZxllcxService rateZxllcxService;

	/**
	 * 分页列表查询
	 *
	 * @param rateTszxlldjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "特殊利率定价-分页列表查询")
	@ApiOperation(value = "特殊利率定价-分页列表查询", notes = "特殊利率定价-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RateTszxlldjb rateTszxlldjb,
								   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
								   HttpServletRequest req) {
		Result<IPage<RateTszxlldjb>> result = new Result<IPage<RateTszxlldjb>>();
		QueryWrapper<RateTszxlldjb> queryWrapper = QueryGenerator.initQueryWrapper(rateTszxlldjb, req.getParameterMap());
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(IRateTszxlldjbService.class, iRateTszxlldjbService, pageNo, pageSize, queryWrapper, "djid");
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 特殊利率定价 / 添加
	 *
	 * @param rateTszxlldjb
	 * @return
	 */
	@AutoLog(value = "特殊利率定价-添加")
	@ApiOperation(value="特殊利率定价-添加", notes="特殊利率定价-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RateTszxlldjb rateTszxlldjb) {
		Date date = org.cmms.modules.util.DateUtil.getYearStartDay(rateTszxlldjb.getLrsj());
		QueryWrapper<RateTszxlldjb> queryWrapperZxlldjb = new QueryWrapper<>();
		queryWrapperZxlldjb.eq("djnf", date);
		queryWrapperZxlldjb.eq("zjhm", rateTszxlldjb.getZjhm());
		queryWrapperZxlldjb.eq("spzt",0);
		RateTszxlldjb one = iRateTszxlldjbService.getOne(queryWrapperZxlldjb,false);
		if (one != null) {
			return Result.error("同一年度该客户只允许有一条未确认的定价信息！");
		}
		rateTszxlldjb.setDjnf(date);
		rateTszxlldjb.setDjnf(DateUtil.beginOfYear(new Date()));
		rateTszxlldjb.setLrczy(getLoginUser().getUsername());
		rateTszxlldjb.setLrsj(new Timestamp(System.currentTimeMillis()));
		rateTszxlldjb.setLrbz(2);
		rateTszxlldjb.setSpzt(0);
		rateTszxlldjb.setXgzt(0);
		rateTszxlldjb.setZxll(new BigDecimal(String.valueOf(rateTszxlldjb.getZxll())).setScale(4, BigDecimal.ROUND_HALF_UP));
		rateTszxlldjb.setJzll(new BigDecimal(4.3500).setScale(4, BigDecimal.ROUND_HALF_UP));
		rateTszxlldjb.setDjid(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")));
		if (rateTszxlldjb.getDklx() < 4) {
			rateTszxlldjb.setDklxxq(rateTszxlldjb.getDklxxq() % 10);
		} else {
			rateTszxlldjb.setFdfd(new BigDecimal(0));
			rateTszxlldjb.setDklxxq(0);
		}
		service.save(rateTszxlldjb);
		return Result.ok("添加成功！");
	}

	/**
	 * 特殊利率定价 / 编辑
	 *
	 * @param rateTszxlldjb
	 * @return
	 */
	@AutoLog(value = "特殊利率定价-编辑")
	@ApiOperation(value="特殊利率定价-编辑", notes="特殊利率定价-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RateTszxlldjb rateTszxlldjb) {
		rateTszxlldjb.setXgsj(new Timestamp(System.currentTimeMillis()));
		rateTszxlldjb.setXgzt(1);
		rateTszxlldjb.setXgczy(getLoginUser().getUsername());
		service.updateById(rateTszxlldjb);

		if (rateTszxlldjb.getSpzt() == 1d) {//如果重新计算发现该记录是已确认则需要覆盖最新的定价记录
			QueryWrapper<RateZxllcx> queryWrapperZxllcx= new QueryWrapper<>();
			queryWrapperZxllcx.eq("djnf",rateTszxlldjb.getDjnf());
			queryWrapperZxllcx.eq("zjhm",rateTszxlldjb.getZjhm());
			RateZxllcx zxllcx = rateZxllcxService.getOne(queryWrapperZxllcx);
			if (zxllcx != null) {
				rateZxllcxService.remove(queryWrapperZxllcx);
			}
			RateZxllcx insert = new RateZxllcx();
			insert.setDjid(rateTszxlldjb.getDjid());
			insert.setDjrq(new Timestamp(System.currentTimeMillis()));
			insert.setDjnf(rateTszxlldjb.getDjnf());
			insert.setZzbz(rateTszxlldjb.getZzbz());
			insert.setZjhm(rateTszxlldjb.getZjhm());
			insert.setKhmc(rateTszxlldjb.getKhmc());
			insert.setFrdb(rateTszxlldjb.getFrdb());
			insert.setJjll(rateTszxlldjb.getJzll());
			insert.setSffd(rateTszxlldjb.getFdfd());
			insert.setZxll(rateTszxlldjb.getZxll());
			insert.setLrsj(new Timestamp(System.currentTimeMillis()));
			insert.setLrczy(getLoginUser().getUsername());
			rateZxllcxService.save(insert);
		}
		return Result.ok("编辑成功!");
	}

	/**
	 * 特殊利率定价 / 删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "特殊利率定价-删除")
	@ApiOperation(value="特殊利率定价-删除", notes="特殊利率定价-删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		QueryWrapper<RateTszxlldjb> queryWrapper = new QueryWrapper();
		queryWrapper.eq("djid",id);
		service.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 特殊利率定价 / 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "特殊利率定价-通过id查询")
	@ApiOperation(value="特殊利率定价-通过id查询", notes="特殊利率定价-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RateTszxlldjb rateTszxlldjb = service.getById(id);
		return Result.ok(rateTszxlldjb);
	}

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param rateTszxlldjb
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, RateTszxlldjb rateTszxlldjb) {
		 return super.exportXls(request, rateTszxlldjb, RateTszxlldjb.class, "特殊利率定价");
	 }


	 /**
	  * 特殊利率定价添加查询
	  *
	  * @param rateTszxlldjb
	  * @return
	  */
	 @AutoLog(value = "特殊利率定价添加查询")
	 @ApiOperation(value = "特殊利率定价添加查询", notes = "特殊利率定价添加查询")
	 @PostMapping(value = "/addQuery")
	 public Result<?> addQuery(@RequestBody RateTszxlldjb rateTszxlldjb) {
		 //先去查客户存在
		 //去查是否定价
		 service.save(rateTszxlldjb);
		 return Result.ok("添加成功！");
	 }


	 @RequestMapping("/updateSpzt")
	 public Result<?> updateSpzt(@RequestBody RateTszxlldjb rateZxlldjb){
		 System.out.println(rateZxlldjb.toString());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 rateZxlldjb.setXgczy(sysUser.getUsername());
		 rateZxlldjb.setXgsj(new Date());
		 rateZxlldjb.setSpr(sysUser.getUsername());
		 rateZxlldjb.setSpsj(new Date());
		 service.updateById(rateZxlldjb);
		 return Result.ok();
	 }

}
