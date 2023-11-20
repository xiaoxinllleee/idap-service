package org.cmms.modules.khgl.dkkh.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.appbase.datatime.service.IBasDataJobDaysService;
import org.cmms.modules.khgl.dkkh.entity.DkhtVO;
import org.cmms.modules.khgl.dkkh.entity.KhgxglDkkhghlsb;
import org.cmms.modules.khgl.dkkh.entity.TbDkYgdkzhsjmx;
import org.cmms.modules.khgl.dkkh.service.IKhgxglDkkhghlsbService;
import org.cmms.modules.khgl.dkkh.service.ITbDkYgdkzhsjmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglGrkh;
import org.cmms.modules.khgxgl.service.IKhgxglKhzlglGrkhService;
import org.cmms.modules.system.service.ISysDictService;
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
 * @Description: 贷款员工管理综合数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款员工管理综合数据明细")
@RestController
@RequestMapping("/dkkh/tbDkYgdkzhsjmx")
public class TbDkYgdkzhsjmxController extends JeecgController<TbDkYgdkzhsjmx, ITbDkYgdkzhsjmxService> {


	 @Autowired
	 private IKhgxglKhzlglGrkhService khgxglKhzlglGrkhService;
	 @Autowired
	 private IKhgxglDkkhghlsbService khgxglDkkhghlsbService;
	 @Autowired
	 private ISysDictService dictService;
	 @Autowired
	 IBasDataJobDaysService basDataJobDaysService;
	/**
	 * APP贷款合同
	 * 对公 个人
	 * 五级分类
	 * 管户 包收  用lrbz来传参数 2管户 3包收
	 *
	 * */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TbDkYgdkzhsjmx tbDkYgdkzhsjmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {

		Date maxExtDay = basDataJobDaysService.getMaxExtDay();
		String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("TB_DK_YGDKZHSJMX","TB_DK_YGDKZHSJMX"+"_"+yyMMdd);
		RequestDataHelper.setRequestData(requestData);


		Page<TbDkYgdkzhsjmx> page = new Page<TbDkYgdkzhsjmx>(pageNo, pageSize);
		List<TbDkYgdkzhsjmx> dataList = new ArrayList<>();
		if (tbDkYgdkzhsjmx.getLrbz() > 0){
			IPage<TbDkYgdkzhsjmx> appList = service.getAppList(page, tbDkYgdkzhsjmx,getWorkNo());
			dataList = appList.getRecords();
		}else {
			LambdaQueryWrapper<TbDkYgdkzhsjmx>  tbDkYgdkzhsjmxLambdaQueryWrapper = new LambdaQueryWrapper<>();
			tbDkYgdkzhsjmxLambdaQueryWrapper.eq(TbDkYgdkzhsjmx::getYggh,getWorkNo());
			if (StringUtils.isNotBlank(tbDkYgdkzhsjmx.getCustType()))
				tbDkYgdkzhsjmxLambdaQueryWrapper.eq(TbDkYgdkzhsjmx::getCustType,tbDkYgdkzhsjmx.getCustType());
			if (StringUtils.isNotBlank(tbDkYgdkzhsjmx.getFiveClassType())){
				tbDkYgdkzhsjmxLambdaQueryWrapper.eq(TbDkYgdkzhsjmx::getFiveClassType,tbDkYgdkzhsjmx.getFiveClassType());
			}
			if (StringUtils.isNotBlank(tbDkYgdkzhsjmx.getCustName())){
				tbDkYgdkzhsjmxLambdaQueryWrapper.like(TbDkYgdkzhsjmx::getCustName,tbDkYgdkzhsjmx.getCustName());
			}
			Page<TbDkYgdkzhsjmx> appList = service.page(page, tbDkYgdkzhsjmxLambdaQueryWrapper);
			dataList = appList.getRecords();
		}

		List<DkhtVO> dkhtVOS = new ArrayList<>();
		if (CollUtil.isNotEmpty(dataList)){
			for (int i = 0; i < dataList.size(); i++) {
				DkhtVO dkhtVO = new DkhtVO();
				TbDkYgdkzhsjmx old = dataList.get(i);
				//加载贷款信息
				dkhtVO.initDkzhsjmx(old);

				List<String> ghByHth = khgxglDkkhghlsbService.getGhBsByHth(old.getHth(),2);
				String ygxms = getYgxms(ghByHth);
				dkhtVO.setGhr(ygxms);

				List<String> bsByHth = khgxglDkkhghlsbService.getGhBsByHth(old.getHth(),3);
				String bsrs = getYgxms(bsByHth);
				dkhtVO.setBsr(bsrs);

				//担保方式
				String dbfs = service.dbfs(old.getHth());
				if (StringUtils.isNotBlank(dbfs)){
					String xm = dictService.queryDictTextByKey("dbfs",dbfs);
					dkhtVO.setDbfs(xm);
				}


				//加载个人信息
				LambdaQueryWrapper<KhgxglKhzlglGrkh> khgxglKhzlglGrkhLambdaQueryWrapper = new LambdaQueryWrapper<>();
				khgxglKhzlglGrkhLambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getZjhm,old.getZjhm());
				if (StringUtils.isNotBlank(getRedisUserJgdm())){
					khgxglKhzlglGrkhLambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getJgdm,getRedisUserJgdm());
				}
				List<KhgxglKhzlglGrkh> khgxglKhzlglGrkhs = khgxglKhzlglGrkhService.list(khgxglKhzlglGrkhLambdaQueryWrapper);
				if (CollUtil.isNotEmpty(khgxglKhzlglGrkhs)){
					KhgxglKhzlglGrkh khgxglKhzlglGrkh = khgxglKhzlglGrkhs.get(0);
					if (khgxglKhzlglGrkh != null){
						dkhtVO.initGrxx(khgxglKhzlglGrkh);
					}
				}

				//转换字典
				if (StringUtils.isNotBlank(dkhtVO.getFiveClassType())){
					String wjflbz = dictService.queryDictTextByKey("wjflbz", dkhtVO.getFiveClassType());
					dkhtVO.setFiveClassTypeVal(wjflbz);
				}
				if (StringUtils.isNotBlank(dkhtVO.getDklx())){
					if ("01".equals(dkhtVO.getDklx())){
						dkhtVO.setDklxVal("个人");
					}else {
						dkhtVO.setDklxVal("对公");
					}
				}

			dkhtVOS.add(dkhtVO);
			}
		}
		return Result.ok(dkhtVOS);
	}
	

	public String getYgxms(List<String> list){
		String result = null;
		if (CollUtil.isNotEmpty(list)){
			for (int i = 0; i < list.size(); i++) {
				String s = list.get(i);
				String xm = dictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", s);
				if (result == null){
					result = xm;
				}else {
					result += "丶"+xm;
				}
			}
			return result;
		}
		return null;
	}

}
