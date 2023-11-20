package org.cmms.modules.ywgl.dqdk.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.appbase.datatime.service.IBasDataJobDaysService;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglGrkh;
import org.cmms.modules.khgxgl.service.IKhgxglKhzlglGrkhService;
import org.cmms.modules.utils.AppConstant;
import org.cmms.modules.ywgl.dqdk.entity.AppDqdkVO;
import org.cmms.modules.ywgl.dqdk.entity.AppTjfxDkdqkh;
import org.cmms.modules.ywgl.dqdk.service.IAppTjfxDkdqkhService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="到期贷款")
@RestController
@RequestMapping("/dqdk/appTjfxDkdqkh")
public class AppTjfxDkdqkhController extends JeecgController<AppTjfxDkdqkh, IAppTjfxDkdqkhService> {
	@Autowired
	private IAppTjfxDkdqkhService appTjfxDkdqkhService;
	@Autowired
	private ListToDictUtil listToDictUtil;
	 @Autowired
	 IBasDataJobDaysService basDataJobDaysService;
	@Autowired
	IKhgxglKhzlglGrkhService khgxglKhzlglGrkhService;
	/**
	 * 分页列表查询
	 *
	 * @return
	 */
	@AutoLog(value = "到期贷款-分页列表查询")
	@ApiOperation(value="到期贷款-分页列表查询", notes="到期贷款-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									String dqrq,String dkye,String ye,
								   String keyOrder,String khlx) { //ye:0为全部,1为查看有余额 keyOrder:1为日期排序2为ckye排序
		Page page=new Page(pageNo,pageSize);
//		QueryWrapper queryWrapper =new QueryWrapper();
//		queryWrapper.eq("khlx",khlx);
//		if ("1".equals(keyOrder)){
//			if (!dqrq.isEmpty() && "asc".equals(dqrq)){
//				queryWrapper.orderByAsc("end_Date");
//			}else {
//				queryWrapper.orderByDesc("end_Date");
//			}
//		}else {
//			if (!dkye.isEmpty() && "asc".equals(dkye)){
//				queryWrapper.orderByAsc("dkye");
//			}else {
//				queryWrapper.orderByDesc("dkye");
//			}
//		}
//
//		if ("1".equals(ye)){
//			queryWrapper.gt("dkye",0);
//		}
//		Page pageList = appTjfxDkdqkhService.page(page, queryWrapper);
// 		pageList.setRecords(listToDictUtil.parseDictText(pageList.getRecords()));

		Date maxExtDay = basDataJobDaysService.getMaxExtDay();
		String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
		RequestDataHelper.setRequestData(AppConstant.TB_DK_YGDKZHSJMX,AppConstant.TB_DK_YGDKZHSJMX+"_"+yyMMdd);

		IPage<AppDqdkVO> listByPage = service.getListByPage(page, dqrq, dkye, ye, khlx, getWorkNo());
		List<AppDqdkVO> records = listByPage.getRecords();
		if (CollUtil.isNotEmpty(records)){
			for (int i = 0; i < records.size(); i++) {
				AppDqdkVO appDqdkVO = records.get(i);
				if (appDqdkVO.getDqrq() != null){
					long l = DateUtil.betweenDay(appDqdkVO.getDqrq(), new Date(), true);
					appDqdkVO.setDqts(l+"");
				}
				/*if (StringUtils.isNotBlank(appDqdkVO.getDkzh())){
					appDqdkVO.setDkzh(DesensitizedUtil.bankCard(appDqdkVO.getDkzh()));
				}*/
				if (StringUtils.isNotBlank(appDqdkVO.getZjhm())){
					if (IdcardUtil.isValidCard(appDqdkVO.getZjhm())){
						int genderByIdCard = IdcardUtil.getGenderByIdCard(appDqdkVO.getZjhm());
						appDqdkVO.setSex(genderByIdCard+"");
						int ageByIdCard = IdcardUtil.getAgeByIdCard(appDqdkVO.getZjhm());
						appDqdkVO.setNl(ageByIdCard+"");
					}

					LambdaQueryWrapper<KhgxglKhzlglGrkh> lambdaQueryWrapper = new LambdaQueryWrapper<>();
					lambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getZjhm,appDqdkVO.getZjhm());
					if (StringUtils.isNotBlank(getRedisUserJgdm())){
						lambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getJgdm,getRedisUserJgdm());
					}
					List<KhgxglKhzlglGrkh> list = khgxglKhzlglGrkhService.list(lambdaQueryWrapper);
					if (
							CollUtil.isNotEmpty(list)
					) {
						KhgxglKhzlglGrkh khgxglKhzlglGrkh = list.get(0);
						if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getKhbh())){
							appDqdkVO.setKhbh(khgxglKhzlglGrkh.getKhbh());
						}
						if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getSjhm())){
							appDqdkVO.setSjhm(khgxglKhzlglGrkh.getSjhm());
						}
					}
				}
			}
		}
		return Result.ok(listByPage);
	}

	/**
	 * 添加
	 *
	 * @param appTjfxDkdqkh
	 * @return
	 */
	@AutoLog(value = "到期贷款-添加")
	@ApiOperation(value="到期贷款-添加", notes="到期贷款-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppTjfxDkdqkh appTjfxDkdqkh) {
		appTjfxDkdqkhService.save(appTjfxDkdqkh);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param appTjfxDkdqkh
	 * @return
	 */
	@AutoLog(value = "到期贷款-编辑")
	@ApiOperation(value="到期贷款-编辑", notes="到期贷款-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppTjfxDkdqkh appTjfxDkdqkh) {
		appTjfxDkdqkhService.updateById(appTjfxDkdqkh);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "到期贷款-通过id删除")
	@ApiOperation(value="到期贷款-通过id删除", notes="到期贷款-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		appTjfxDkdqkhService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "到期贷款-批量删除")
	@ApiOperation(value="到期贷款-批量删除", notes="到期贷款-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appTjfxDkdqkhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "到期贷款-通过id查询")
	@ApiOperation(value="到期贷款-通过id查询", notes="到期贷款-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppTjfxDkdqkh appTjfxDkdqkh = appTjfxDkdqkhService.getById(id);
		return Result.ok(appTjfxDkdqkh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param appTjfxDkdqkh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppTjfxDkdqkh appTjfxDkdqkh) {
      return super.exportXls(request, appTjfxDkdqkh, AppTjfxDkdqkh.class, "到期贷款");
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
      return super.importExcel(request, response, AppTjfxDkdqkh.class);
  }

}
