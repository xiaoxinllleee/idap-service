package org.cmms.modules.jx.srtx.controller;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.jx.srtx.entity.TbTjfxSrtxxx;
import org.cmms.modules.jx.srtx.entity.TbTjfxSrtxxxVo;
import org.cmms.modules.jx.srtx.service.ITbTjfxSrtxxxService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 生日提醒信息表
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
@Slf4j
@Api(tags="生日提醒信息表")
@RestController
@RequestMapping("/mobile/tbTjfxSrtxxxBankPmRest")
public class TbTjfxSrtxxxController extends JeecgController<TbTjfxSrtxxx, ITbTjfxSrtxxxService> {
	@Autowired
	private ITbTjfxSrtxxxService tbTjfxSrtxxxService;
	
	/**
	 * 分页列表查询
	 * @return
	 */
	@AutoLog(value = "生日提醒信息表-分页查询")
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public Result<?> page(
			@RequestParam(value="yggh",required=false) String yggh,
			@RequestParam(value="page",required=false,defaultValue="1") int pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize
	)  {
		if (StringUtils.isBlank(yggh)){
			LoginUser loginUser = getLoginUser();
			String id = loginUser.getWorkNo();
			yggh = id;
		}
		Page<TbTjfxSrtxxxVo> page=new Page(pageNo,pageSize);
		IPage<TbTjfxSrtxxxVo> iPage=tbTjfxSrtxxxService.getPageByYggh(page,yggh);
		 for (TbTjfxSrtxxxVo t:iPage.getRecords()){
			 Date csrq = t.getCsrq();
			 int srts = org.cmms.modules.util.DateUtil.getTheBirthDayForLong(csrq);  //生日天数得到
			 t.setSrts(srts);
		 }
		return Result.ok(iPage);
	}

}
