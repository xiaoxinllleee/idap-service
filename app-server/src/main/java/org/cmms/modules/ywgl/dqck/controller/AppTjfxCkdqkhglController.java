package org.cmms.modules.ywgl.dqck.controller;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.appbase.datatime.service.IBasDataJobDaysService;
import org.cmms.modules.utils.AppConstant;
import org.cmms.modules.ywgl.dqck.entity.AppDqckVO;
import org.cmms.modules.ywgl.dqck.entity.AppTjfxCkdqkh;
import org.cmms.modules.ywgl.dqck.service.IAppTjfxCkdqkhglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sun.awt.image.PixelConverter;

/**
 * @Description: 到期存款
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="到期存款")
@RestController
@RequestMapping("/dqck/tbTjfxCkdqkh")
public class AppTjfxCkdqkhglController extends JeecgController<AppTjfxCkdqkh, IAppTjfxCkdqkhglService> {
	@Autowired
	private IAppTjfxCkdqkhglService tbTjfxCkdqkhService;
	@Autowired
	IBasDataJobDaysService basDataJobDaysService;
	/**
	 * 分页列表查询
	 *
	 * @return
	 */
	@AutoLog(value = "到期存款-分页列表查询")
	@ApiOperation(value="到期存款-分页列表查询", notes="到期存款-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
			 						String dqrq,String ckye,String ye,String keyOrder,String type) { //ye:0为全部,1为查看有余额 keyOrder:1为日期排序2为ckye排序
	    Page page=new Page(pageNo,pageSize);
//		QueryWrapper queryWrapper =new QueryWrapper();
//		if ("1".equals(keyOrder)){
//            if (!dqrq.isEmpty() && "asc".equals(dqrq)){
//                queryWrapper.orderByAsc("end_Date");
//            }else {
//                queryWrapper.orderByDesc("end_Date");
//            }
//        }else {
//            if (!ckye.isEmpty() && "asc".equals(ckye)){
//                queryWrapper.orderByAsc("ckye");
//            }else {
//                queryWrapper.orderByDesc("ckye");
//            }
//        }
//		if ("1".equals(ye)){
//			queryWrapper.gt("ckye",0);
//		}
//
//        Page pageList = tbTjfxCkdqkhService.page(page, queryWrapper);
		Date maxExtDay = basDataJobDaysService.getMaxExtDay();
		String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
		RequestDataHelper.setRequestData(AppConstant.TB_CK_YGZHCKSJMX,AppConstant.TB_CK_YGZHCKSJMX+"_"+yyMMdd);

		IPage<AppDqckVO> appList = service.getAppList(page, type, getWorkNo());
		List<AppDqckVO> records = appList.getRecords();
		if (CollUtil.isNotEmpty(records)){
			for (int i = 0; i < records.size(); i++) {
				AppDqckVO appDqckVO = records.get(i);
				if (StringUtils.isNotBlank(appDqckVO.getIntToDt())){
					DateTime parse = DateUtil.parse(appDqckVO.getIntToDt());
					long l = DateUtil.betweenDay(parse, new Date(), true);
					appDqckVO.setDqts(l+"");
				}
				if (StringUtils.isNotBlank(appDqckVO.getZjhm())){
					if (IdcardUtil.isValidCard(appDqckVO.getZjhm())){
						int genderByIdCard = IdcardUtil.getGenderByIdCard(appDqckVO.getZjhm());
						appDqckVO.setSex(genderByIdCard+"");
						int ageByIdCard = IdcardUtil.getAgeByIdCard(appDqckVO.getZjhm());
						appDqckVO.setNl(ageByIdCard+"");

					}
				}
				if (StringUtils.isNotBlank(appDqckVO.getCkzh()) && appDqckVO.getCkzh().length() > 12){
					 String ckzh = appDqckVO.getCkzh();
					 String s = DesensitizedUtil.bankCard(ckzh);
					 appDqckVO.setCkzh(s);
				}
				if (StringUtils.isNotBlank(appDqckVO.getCq())){
					 String cq = appDqckVO.getCq();
					 int i1 = Integer.parseInt(cq);
					 appDqckVO.setCq(i1+"");
				}

			}
		}
		return Result.ok(appList);
	}

	/**
	 * 添加
	 *
	 * @param appTjfxCkdqkh
	 * @return
	 */
	@AutoLog(value = "到期存款-添加")
	@ApiOperation(value="到期存款-添加", notes="到期存款-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppTjfxCkdqkh appTjfxCkdqkh) {
		tbTjfxCkdqkhService.save(appTjfxCkdqkh);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param appTjfxCkdqkh
	 * @return
	 */
	@AutoLog(value = "到期存款-编辑")
	@ApiOperation(value="到期存款-编辑", notes="到期存款-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppTjfxCkdqkh appTjfxCkdqkh) {
		tbTjfxCkdqkhService.updateById(appTjfxCkdqkh);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "到期存款-通过id删除")
	@ApiOperation(value="到期存款-通过id删除", notes="到期存款-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tbTjfxCkdqkhService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "到期存款-批量删除")
	@ApiOperation(value="到期存款-批量删除", notes="到期存款-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tbTjfxCkdqkhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "到期存款-通过id查询")
	@ApiOperation(value="到期存款-通过id查询", notes="到期存款-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppTjfxCkdqkh appTjfxCkdqkh = tbTjfxCkdqkhService.getById(id);
		return Result.ok(appTjfxCkdqkh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param appTjfxCkdqkh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppTjfxCkdqkh appTjfxCkdqkh) {
      return super.exportXls(request, appTjfxCkdqkh, AppTjfxCkdqkh.class, "到期存款");
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
      return super.importExcel(request, response, AppTjfxCkdqkh.class);
  }

}
