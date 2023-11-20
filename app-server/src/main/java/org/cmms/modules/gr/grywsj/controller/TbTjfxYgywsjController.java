package org.cmms.modules.gr.grywsj.controller;

import java.util.*;

import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.gr.grywsj.entity.TbTjfxYgywsj;
import org.cmms.modules.gr.grywsj.service.ITbTjfxYgywsjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 个人业务数据
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人业务数据")
@RestController
@RequestMapping("/mobile/tbTjfxYgywsjBankPmRest")
public class TbTjfxYgywsjController extends JeecgController<TbTjfxYgywsj, ITbTjfxYgywsjService> {
	@Autowired
	private ITbTjfxYgywsjService tbTjfxYgywsjService;
	@Autowired
	private IHrBasOrganizationService iHrBasOrganizationService;


	/**
	 * 通过员工工号查询
	 *
	 * @param yggh
	 * @return
	 */
	@AutoLog(value = "个人业务数据-通过员工工号查询")
	@ApiOperation(value="个人业务数据-通过员工工号查询", notes="个人业务数据-通过员工工号查询")
	@GetMapping(value = "/lastestYgywjx")
	public Result<?> queryById(@RequestParam("yggh") String yggh) {
		QueryWrapper queryWrapper=new QueryWrapper();
			queryWrapper.eq("YGGH",yggh);
			queryWrapper.orderByAsc("zbid");
		List list = tbTjfxYgywsjService.list(queryWrapper);
		return Result.ok(list);
	}

	 @AutoLog(value = "个人具体指标业务数据查询-通过员工工号和指标id查询")
	 @ApiOperation(value="个人具体指标业务数据查询-通过员工工号和指标id查询", notes="个人具体指标业务数据查询-通过员工工号和指标id查询")
	 @GetMapping(value = "/lastestYgywjxByZblb")
	 public Result<?> queryByZbid(@RequestParam("yggh") String yggh,@RequestParam("zbid") String zbid) {
		 QueryWrapper queryWrapper=new QueryWrapper();
		 queryWrapper.eq("YGGH",yggh);
		 queryWrapper.eq("zbid",zbid);
		 queryWrapper.orderByDesc("tjrq");
		 TbTjfxYgywsj grywsj = tbTjfxYgywsjService.getOne(queryWrapper);
		 return Result.ok(grywsj);
	 }

	 @AutoLog(value = "个人往期具体指标业务数据查询-通过员工工号和指标id查询")
	 @ApiOperation(value="个人往期具体指标业务数据查询-通过员工工号和指标id查询", notes="个人具体指标业务数据查询-通过员工工号和指标id查询")
	 @GetMapping(value = "/getWqsj")
	 public Result<?> getWqsj(@RequestParam("yggh") String yggh,@RequestParam("zbid") String zbid,
							   @RequestParam(name = "pageNo",defaultValue = "1")Integer pageNo,
							  @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize) {
		Page<TbTjfxYgywsj> page=new Page<TbTjfxYgywsj>(pageNo,pageSize);
		 IPage<TbTjfxYgywsj> list = tbTjfxYgywsjService.getWqDate(page, yggh, zbid);
		 Map<String,Object> map=new HashMap<>();
		 if(list!=null) {
		    for (int i=0;i<list.getTotal();i++){
				 HrBasOrganization hbo = iHrBasOrganizationService.queryByZzbz(list.getRecords().get(i).getZzbz());
				if (hbo!=null){
					map.put("zzmc", hbo.getZzmc());
					map.put("zzjc", hbo.getZzjc());
				}
			}
		 }
		map.put("list",list.getRecords());
		 map.put("total",list.getTotal());
		 return Result.ok(map);
	 }
}
