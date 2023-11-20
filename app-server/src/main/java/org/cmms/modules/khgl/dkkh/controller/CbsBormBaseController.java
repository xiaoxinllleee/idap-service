package org.cmms.modules.khgl.dkkh.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.encryption.RSAEncryptUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.dkkh.entity.BmkListVO;
import org.cmms.modules.khgl.dkkh.entity.BmkVO;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import org.cmms.modules.khgl.dkkh.entity.CmsBusinessContractInfo;
import org.cmms.modules.khgl.dkkh.service.ICbsBormBaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.dkkh.service.ICmsBusinessContractInfoService;
import org.cmms.modules.khgl.dkkh.service.IKhgxglDkkhghlsbService;
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
 * @Description: 贷款主档宽表
 * @Author: jeecg-boot
 * @Date:   2022-04-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款主档宽表")
@RestController
@RequestMapping("/dkkh/cbsBormBase")
public class CbsBormBaseController extends JeecgController<CbsBormBase, ICbsBormBaseService> {
	@Autowired
	private ICbsBormBaseService cbsBormBaseService;
	@Autowired
	ICmsBusinessContractInfoService cmsBusinessContractInfoService;
	@Autowired
	IKhgxglDkkhghlsbService khgxglDkkhghlsbService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cbsBormBase
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款主档宽表-分页列表查询")
	@ApiOperation(value="贷款主档宽表-分页列表查询", notes="贷款主档宽表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CbsBormBase cbsBormBase,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CbsBormBase> queryWrapper = QueryGenerator.initQueryWrapper(cbsBormBase, req.getParameterMap());
		Page<CbsBormBase> page = new Page<CbsBormBase>(pageNo, pageSize);
		IPage<CbsBormBase> pageList = cbsBormBaseService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param cbsBormBase
	 * @return
	 */
	@AutoLog(value = "贷款主档宽表-添加")
	@ApiOperation(value="贷款主档宽表-添加", notes="贷款主档宽表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CbsBormBase cbsBormBase) {
		cbsBormBaseService.save(cbsBormBase);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param cbsBormBase
	 * @return
	 */
	@AutoLog(value = "贷款主档宽表-编辑")
	@ApiOperation(value="贷款主档宽表-编辑", notes="贷款主档宽表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CbsBormBase cbsBormBase) {
		cbsBormBaseService.updateById(cbsBormBase);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款主档宽表-通过id删除")
	@ApiOperation(value="贷款主档宽表-通过id删除", notes="贷款主档宽表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cbsBormBaseService.removeById(id);
		return Result.ok("删除成功!");
	}
	

	@RequestMapping("/getBmkInfo")
	public Result<?> getBmkInfo(String zjhm){
		if (StringUtils.isNotBlank(zjhm) && zjhm.length() > 20)
			zjhm = RSAEncryptUtil.desEncrypt(zjhm.replaceAll(" ","+"));

		List<CbsBormBase> cardNoIsNotNull = service.getCardNoIsNotNull(zjhm);

		List<BmkVO> bmkVOList = new ArrayList<>();
		BmkVO mainKh = new BmkVO();
		//通过便民卡去找贷款合同
		if (CollUtil.isNotEmpty(cardNoIsNotNull)){
			for (int i = 0; i < cardNoIsNotNull.size(); i++) {
				BmkVO bmkVO = new BmkVO();
				CbsBormBase cbsBormBase = cardNoIsNotNull.get(i);
				bmkVO.setCopy(cbsBormBase);

				//查管户人 包收人
				if (StringUtils.isNotBlank(cbsBormBase.getBusinessNo())){
					String ghlxByHth = khgxglDkkhghlsbService.getGhlxByHth(cbsBormBase.getBusinessNo(), 2);
					String ghlxByHth2 = khgxglDkkhghlsbService.getGhlxByHth(cbsBormBase.getBusinessNo(), 3);
					bmkVO.setGhr(ghlxByHth);
					bmkVO.setBsr(ghlxByHth2);

					if (i==0){
						mainKh.setCopy(cbsBormBase);

						if (StringUtils.isNotBlank(cbsBormBase.getCardNo())){
							mainKh.setBusinessNo(cbsBormBase.getCardNo());
						}
						mainKh.setGhr(ghlxByHth);
						mainKh.setBsr(ghlxByHth2);
						CmsBusinessContractInfo byId = cmsBusinessContractInfoService.getById(cbsBormBase.getBusinessNo());
						if (byId != null){
							System.out.println("byId");
							System.out.println(byId);
							if (byId.getAppSum() != null){
								mainKh.setDkje(byId.getAppSum());
							}
							if (byId.getBalance() != null){
								mainKh.setDkye(byId.getBalance());
							}
							if (StringUtils.isNotBlank(byId.getAppMaturityDate())){
								mainKh.setEndDate(byId.getAppMaturityDate());
							}
							if (StringUtils.isNotBlank(byId.getSignDate())){
								mainKh.setQxDate(byId.getSignDate());
							}
						}
					}
				}
				bmkVOList.add(bmkVO);
			}
		}
		BmkListVO bmkListVO = new BmkListVO();
		bmkListVO.setBmkVO(mainKh);
		bmkListVO.setBmkVOList(bmkVOList);
		return Result.ok(bmkListVO);
	}

}
