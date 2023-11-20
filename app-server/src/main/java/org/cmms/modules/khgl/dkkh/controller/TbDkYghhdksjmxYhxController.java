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
import org.cmms.common.util.encryption.RSAEncryptUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.appbase.datatime.service.IBasDataJobDaysService;
import org.cmms.modules.khgl.dkkh.entity.BnblVO;
import org.cmms.modules.khgl.dkkh.entity.BwblVO;
import org.cmms.modules.khgl.dkkh.entity.TbDkYgghdksjmx;
import org.cmms.modules.khgl.dkkh.entity.TbDkYghhdksjmxYhx;
import org.cmms.modules.khgl.dkkh.service.IKhgxglDkkhghlsbService;
import org.cmms.modules.khgl.dkkh.service.ITbDkYghhdksjmxYhxService;
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
 * @Description: 员工管户贷款已核销
 * @Author: jeecg-boot
 * @Date:   2022-04-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="员工管户贷款已核销")
@RestController
@RequestMapping("/dkkh/tbDkYghhdksjmxYhx")
public class TbDkYghhdksjmxYhxController extends JeecgController<TbDkYghhdksjmxYhx, ITbDkYghhdksjmxYhxService> {
	@Autowired
	IBasDataJobDaysService basDataJobDaysService;
	@Autowired
	IKhgxglDkkhghlsbService khgxglDkkhghlsbService;
	@RequestMapping("/list")
	public Result<?> list(String zjhm){
		if (StringUtils.isNotBlank(zjhm) && zjhm.length() > 20)
			zjhm = RSAEncryptUtil.desEncrypt(zjhm.replaceAll(" ","+"));

		Date maxExtDay = basDataJobDaysService.getMaxExtDay();
		String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
		Map<String, Object> requestData = new HashMap<>();
		requestData.put("TB_DK_YGGHDKSJMX_YHX","TB_DK_YGGHDKSJMX_YHX"+"_"+yyMMdd);
		RequestDataHelper.setRequestData(requestData);

		LambdaQueryWrapper<TbDkYghhdksjmxYhx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(TbDkYghhdksjmxYhx::getZjhm,zjhm);
		List<TbDkYghhdksjmxYhx> list = service.list(lambdaQueryWrapper);


		List<BwblVO> bwblVOS = new ArrayList<>();
		if (CollUtil.isNotEmpty(list)){
			for (int i = 0; i < list.size(); i++) {
				BwblVO bwblVO = new BwblVO();
				TbDkYghhdksjmxYhx tbDkYghhdksjmxYhx = list.get(i);
				bwblVO.init(tbDkYghhdksjmxYhx);
				String ghlxByHth = khgxglDkkhghlsbService.getGhlxByHth(tbDkYghhdksjmxYhx.getHth(), 2);
				String ghlxByHth2 = khgxglDkkhghlsbService.getGhlxByHth(tbDkYghhdksjmxYhx.getHth(), 3);
				bwblVO.setGhr(ghlxByHth);
				bwblVO.setBsr(ghlxByHth2);
				bwblVOS.add(bwblVO);
			}
		}
		return Result.ok(list);
	}

}
