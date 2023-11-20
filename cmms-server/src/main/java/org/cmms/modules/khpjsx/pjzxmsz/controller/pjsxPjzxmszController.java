package org.cmms.modules.khpjsx.pjzxmsz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.khpjsx.pjsxsjx.entity.PjsxSjxxx;
import org.cmms.modules.khpjsx.pjsxsjx.service.IPjsxSjxxxService;
import org.cmms.modules.khpjsx.pjzxmsz.entity.PJSX_PJZXMGZSZ_GS;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszQj;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszXl;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmsz;
import org.cmms.modules.khpjsx.pjzxmsz.service.IpjsxPjzxmgzszQjService;
import org.cmms.modules.khpjsx.pjzxmsz.service.IpjsxPjzxmgzszXlService;
import org.cmms.modules.khpjsx.pjzxmsz.service.IpjsxPjzxmszService;
import org.cmms.modules.khpjsx.pjzxmsz.service.impl.PJSX_PJZXMGZSZ_GSServiceImpl;
import org.cmms.modules.khpjsx.pjzxmsz.vo.pjsxPjzxmszPage;
import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Description: 评级子项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@RestController
@RequestMapping("/pjzxmsz/pjsxPjzxmsz")
@Slf4j
public class pjsxPjzxmszController {
	@Autowired
	private IpjsxPjzxmszService pjsxPjzxmszService;
	@Autowired
	private IpjsxPjzxmgzszXlService pjsxPjzxmgzszXlService;
	@Autowired
	private IpjsxPjzxmgzszQjService pjsxPjzxmgzszQjService;

	@Autowired
	private PJSX_PJZXMGZSZ_GSServiceImpl pjsx_pjzxmgzsz_gsService;
	@Autowired
	private ISysDictService iSysDictService;
	@Autowired
	private IPjsxSjxxxService pjsxSjxxxService;
	/**
	 * 分页列表查询
	 *
	 * @param pjsxPjzxmsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(pjsxPjzxmsz pjsxPjzxmsz,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<pjsxPjzxmsz> queryWrapper = QueryGenerator.initQueryWrapper(pjsxPjzxmsz, req.getParameterMap());
		Page<pjsxPjzxmsz> page = new Page<pjsxPjzxmsz>(pageNo, pageSize);
		IPage<pjsxPjzxmsz> pageList = pjsxPjzxmszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	@RequestMapping(value = "/check")
	public Result<?> check(@RequestBody JSONObject param){
		Result<PjsxSjxxx> result = new Result<>();
		QueryWrapper<PjsxSjxxx> pjsxPjzxmszQueryWrapper=new QueryWrapper<>();
		pjsxPjzxmszQueryWrapper.eq("sjxid",param.get("fxmbh")).eq("khlx",param.get("khlx"));
		List<PjsxSjxxx> list = pjsxSjxxxService.list(pjsxPjzxmszQueryWrapper);
		if (list.size()>0){
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
		}
		return result;
	}

		@RequestMapping(value = "/add")
		public Result<?> add(@RequestBody JSONObject param){
			pjsxPjzxmsz pjsxPjzxmsz = new pjsxPjzxmsz();
			JSONObject ftable=param.getJSONObject("ftable");
			String qydm=iSysDictService.queryTableDictTextByKey("sys_dic","value","code","101001");
			pjsxPjzxmsz.setQydm(qydm);
			pjsxPjzxmsz.setBz(ftable.getString("bz"));pjsxPjzxmsz.setFxmbh(param.getString("fxmbh"));pjsxPjzxmsz.setFz(Integer.parseInt(ftable.getString("fz")));
			pjsxPjzxmsz.setJsfs(param.getString("jsfs"));pjsxPjzxmsz.setKhlx(param.getString("khlx"));
			pjsxPjzxmsz.setPxxh(Integer.parseInt(ftable.getString("pxxh")));pjsxPjzxmsz.setSfqy(param.getString("sfqy"));
			pjsxPjzxmsz.setXmmc(ftable.getString("xmmc"));
			pjsxPjzxmsz.setXmbh(param.getString("fxmbh"));
			pjsxPjzxmszService.save(pjsxPjzxmsz);
			JSONArray ztable = param.getJSONArray("ztable");
			if(param.getString("jsfs").equals("1")){

				for (int i = 0; i < ztable.size(); i++) {
					pjsxPjzxmgzszXl pjsxPjzxmgzszXl=new pjsxPjzxmgzszXl();
					pjsxPjzxmgzszXl.setQydm(qydm);
					pjsxPjzxmgzszXl.setXmbhId(param.getString("fxmbh"));
					pjsxPjzxmgzszXl.setXmbh(ztable.getJSONObject(i).getString("xmbh"));
					pjsxPjzxmgzszXl.setFz(Double.parseDouble(ztable.getJSONObject(i).getString("fz")));
					pjsxPjzxmgzszXl.setKey(ztable.getJSONObject(i).getString("key"));
					pjsxPjzxmgzszXl.setValue(Integer.parseInt(ztable.getJSONObject(i).getString("value")));
					pjsxPjzxmgzszXl.setKhlx(param.getString("khlx"));
					pjsxPjzxmgzszXlService.save(pjsxPjzxmgzszXl);
				}
			}else if(param.getString("jsfs").equals("2")){
				for (int i = 0; i < ztable.size(); i++) {
					pjsxPjzxmgzszQj pjsxPjzxmgzszQj=new pjsxPjzxmgzszQj();
					pjsxPjzxmgzszQj.setXmbhId(param.getString("fxmbh"));
					pjsxPjzxmgzszQj.setXmbh(ztable.getJSONObject(i).getString("xmbh"));
					pjsxPjzxmgzszQj.setFz(Double.parseDouble(ztable.getJSONObject(i).getString("fz")));
					pjsxPjzxmgzszQj.setQydm(qydm);
					pjsxPjzxmgzszQj.setSjzbegin(ztable.getJSONObject(i).getString("sjzbegin"));
					pjsxPjzxmgzszQj.setSjzend(ztable.getJSONObject(i).getString("sjzend"));
					pjsxPjzxmgzszQj.setKhlx(param.getString("khlx"));
					pjsxPjzxmgzszQjService.save(pjsxPjzxmgzszQj);
				}
			}else{
				for (int i = 0; i < ztable.size(); i++) {
					PJSX_PJZXMGZSZ_GS pjsxPjzxmgzszGs=new PJSX_PJZXMGZSZ_GS();
					pjsxPjzxmgzszGs.setQydm(qydm);
					pjsxPjzxmgzszGs.setXmbhId(param.getString("fxmbh"));
					pjsxPjzxmgzszGs.setBzz(Double.parseDouble(ztable.getJSONObject(i).getString("bzz")));
					pjsxPjzxmgzszGs.setXmbh(ztable.getJSONObject(i).getString("xmbh"));
					pjsxPjzxmgzszGs.setMffx(ztable.getJSONObject(i).getString("mffx"));
					pjsxPjzxmgzszGs.setDwfz(Double.parseDouble(ztable.getJSONObject(i).getString("dwfz")));
					pjsxPjzxmgzszGs.setJj(ztable.getJSONObject(i).getString("jj"));
					pjsxPjzxmgzszGs.setSj(ztable.getJSONObject(i).getString("sj"));
					pjsxPjzxmgzszGs.setZdf(ztable.getJSONObject(i).getDoubleValue("zdf"));
					pjsxPjzxmgzszGs.setXs((Double.parseDouble(ztable.getJSONObject(i).getString("xs"))));
					pjsxPjzxmgzszGs.setKhlx(param.getString("khlx"));
					pjsx_pjzxmgzsz_gsService.save(pjsxPjzxmgzszGs);
				}
			}
			return Result.ok("添加成功!");
		}

	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody JSONObject param) {
		pjsxPjzxmsz pjsxPjzxmsz = new pjsxPjzxmsz();
		JSONObject ftable=param.getJSONObject("ftable");
		String qydm=iSysDictService.queryTableDictTextByKey("sys_dic","value","code","101001");
		pjsxPjzxmsz.setBz(ftable.getString("bz"));pjsxPjzxmsz.setFxmbh(param.getString("fxmbh"));pjsxPjzxmsz.setFz(Integer.parseInt(ftable.getString("fz")));
		pjsxPjzxmsz.setJsfs(param.getString("jsfs"));pjsxPjzxmsz.setKhlx(param.getString("khlx"));
		pjsxPjzxmsz.setPxxh(Integer.parseInt(ftable.getString("pxxh")));pjsxPjzxmsz.setSfqy(param.getString("sfqy"));
		pjsxPjzxmsz.setXmmc(ftable.getString("xmmc"));
		pjsxPjzxmsz.setQydm(qydm);pjsxPjzxmsz.setXmbh(ftable.getString("xmbh"));
		UpdateWrapper<pjsxPjzxmsz> pjsxPjzxmszXL =new UpdateWrapper<>();
		pjsxPjzxmszXL.eq("qydm",qydm).eq("fxmbh",param.getString("fxmbh")).eq("khlx",param.getString("khlx"));
		pjsxPjzxmszService.update(pjsxPjzxmsz,pjsxPjzxmszXL);
		JSONArray ztable = param.getJSONArray("ztable");
		ArrayList<pjsxPjzxmgzszXl> gzsz =new ArrayList<>();
		gzsz.addAll(ztable.toJavaList(pjsxPjzxmgzszXl.class));
		if(param.getString("jsfs").equals("1")){
			QueryWrapper<pjsxPjzxmgzszXl> query=new QueryWrapper<>();
			query.eq("xmbh_id",param.getString("fxmbh"))
					.eq("qydm",qydm).eq("khlx",param.getString("khlx"));
			pjsxPjzxmgzszXlService.remove(query);
			for (int i = 0; i < ztable.size(); i++) {
				pjsxPjzxmgzszXl pjsxPjzxmgzszXl=new pjsxPjzxmgzszXl();
				pjsxPjzxmgzszXl.setXmbh(ztable.getJSONObject(i).getString("xmbh"));
				pjsxPjzxmgzszXl.setXmbhId(param.getString("fxmbh"));
				pjsxPjzxmgzszXl.setFz(Double.parseDouble(ztable.getJSONObject(i).getString("fz")));
				pjsxPjzxmgzszXl.setKey(ztable.getJSONObject(i).getString("key"));
				pjsxPjzxmgzszXl.setValue(Integer.parseInt(ztable.getJSONObject(i).getString("value")));
				pjsxPjzxmgzszXl.setQydm(qydm);
				pjsxPjzxmgzszXl.setKhlx(param.getString("khlx"));

				pjsxPjzxmgzszXlService.save(pjsxPjzxmgzszXl);

			}
		}else if(param.getString("jsfs").equals("2")){
			QueryWrapper<pjsxPjzxmgzszQj> query=new QueryWrapper<>();
			query.eq("xmbh_id",param.getString("fxmbh"))
					.eq("qydm",qydm).eq("khlx",param.getString("khlx"));
			pjsxPjzxmgzszQjService.remove(query);
			for (int i = 0; i < ztable.size(); i++) {
				pjsxPjzxmgzszQj pjsxPjzxmgzszQj=new pjsxPjzxmgzszQj();
				pjsxPjzxmgzszQj.setXmbh(ztable.getJSONObject(i).getString("xmbh"));
				pjsxPjzxmgzszQj.setFz(Double.parseDouble(ztable.getJSONObject(i).getString("fz")));
				pjsxPjzxmgzszQj.setSjzbegin(ztable.getJSONObject(i).getString("sjzbegin"));
				pjsxPjzxmgzszQj.setSjzend(ztable.getJSONObject(i).getString("sjzend"));
				pjsxPjzxmgzszQj.setQydm(qydm);
				pjsxPjzxmgzszQj.setXmbhId(param.getString("fxmbh"));
				pjsxPjzxmgzszQj.setKhlx(param.getString("khlx"));
					pjsxPjzxmgzszQjService.save(pjsxPjzxmgzszQj);


			}
		}else{
			QueryWrapper<PJSX_PJZXMGZSZ_GS> query=new QueryWrapper<>();
			query.eq("xmbh_id",param.getString("fxmbh"))
					.eq("qydm",qydm).eq("khlx",param.getString("khlx"));
			pjsx_pjzxmgzsz_gsService.remove(query);
			for (int i = 0; i < ztable.size(); i++) {
				PJSX_PJZXMGZSZ_GS pjsxPjzxmgzszGs=new PJSX_PJZXMGZSZ_GS();
				pjsxPjzxmgzszGs.setQydm(qydm);
				pjsxPjzxmgzszGs.setXmbhId(param.getString("fxmbh"));
				pjsxPjzxmgzszGs.setBzz(Double.parseDouble(ztable.getJSONObject(i).getString("bzz")));
				pjsxPjzxmgzszGs.setXmbh(ztable.getJSONObject(i).getString("xmbh"));
				pjsxPjzxmgzszGs.setMffx(ztable.getJSONObject(i).getString("mffx"));
				pjsxPjzxmgzszGs.setDwfz(Double.parseDouble(ztable.getJSONObject(i).getString("dwfz")));
				pjsxPjzxmgzszGs.setJj(ztable.getJSONObject(i).getString("jj"));
				pjsxPjzxmgzszGs.setSj(ztable.getJSONObject(i).getString("sj"));
				pjsxPjzxmgzszGs.setXs((Double.parseDouble(ztable.getJSONObject(i).getString("xs"))));
				pjsxPjzxmgzszGs.setKhlx(param.getString("khlx"));
				pjsxPjzxmgzszGs.setZdf(ztable.getJSONObject(i).getDoubleValue("zdf"));
					pjsx_pjzxmgzsz_gsService.save(pjsxPjzxmgzszGs);


			}
		}
		return Result.ok("修改成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@ApiOperation(value="删除", notes="查询与我行相关业务")
	@RequestMapping(value = "/delete",method = RequestMethod.PUT)
	public Result<?> delete(@RequestBody JSONObject param) {
		pjsxPjzxmsz xmsz = new pjsxPjzxmsz();
		Map<String, String[]> map = new HashMap<>();
		QueryWrapper<pjsxPjzxmsz> queryWrapper = QueryGenerator.initQueryWrapper(xmsz, map);
		queryWrapper.eq("fxmbh", param.get("xmbh")).eq("qydm", param.get("qydm")).eq("khlx",param.get("khlx"));
		try {
			pjsxPjzxmszService.remove(queryWrapper);
			if (param.get("jsfs").equals("1")) {
				pjsxPjzxmgzszXl xl = new pjsxPjzxmgzszXl();
				Map<String, String[]> mapXl = new HashMap<>();
				QueryWrapper<pjsxPjzxmgzszXl> queryWrapperXl = QueryGenerator.initQueryWrapper(xl, mapXl);
				queryWrapperXl.eq("xmbh_id", param.get("xmbh")).eq("qydm", param.get("qydm")).eq("khlx",param.get("khlx"));
				pjsxPjzxmgzszXlService.remove(queryWrapperXl);
			} else if ((param.get("jsfs").equals("2"))) {
					pjsxPjzxmgzszQj qj = new pjsxPjzxmgzszQj();
					Map<String, String[]> mapQj = new HashMap<>();
					QueryWrapper<pjsxPjzxmgzszQj> queryWrapperQj = QueryGenerator.initQueryWrapper(qj, mapQj);
					queryWrapperQj.eq("xmbh_id", param.get("xmbh")).eq("qydm", param.get("qydm")).eq("khlx",param.get("khlx"));
					pjsxPjzxmgzszQjService.remove(queryWrapperQj);
			} else {
				PJSX_PJZXMGZSZ_GS gs = new PJSX_PJZXMGZSZ_GS();
				Map<String, String[]> mapGs = new HashMap<>();
				QueryWrapper<PJSX_PJZXMGZSZ_GS> queryWrapperGs = QueryGenerator.initQueryWrapper(gs, mapGs);
				queryWrapperGs.eq("xmbh_id", param.get("xmbh")).eq("qydm", param.get("qydm")).eq("khlx",param.get("khlx"));
				pjsx_pjzxmgzsz_gsService.remove(queryWrapperGs);
			}
			return Result.ok("删除成功!");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Result.error(e.getMessage());
		}
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pjsxPjzxmszService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		pjsxPjzxmsz pjsxPjzxmsz = pjsxPjzxmszService.getById(id);
		return Result.ok(pjsxPjzxmsz);
	}

	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/querypjsxPjzxmgzszXlByMainId")
	public Result<?> querypjsxPjzxmgzszXlListByMainId(@RequestParam(name="xmbh",required=true) String id
			,@RequestParam(name="khlx",required=true) String khlx) {
		List<pjsxPjzxmgzszXl> pjsxPjzxmgzszXlList = pjsxPjzxmgzszXlService.selectByMainId(id,khlx);
		return Result.ok(pjsxPjzxmgzszXlList);
	}
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/querypjsxPjzxmgzszQjByMainId")
	public Result<?> querypjsxPjzxmgzszQjListByMainId(@RequestParam(name="xmbh",required=true) String id
			,@RequestParam(name="khlx",required=true) String khlx) {
		List<pjsxPjzxmgzszQj> pjsxPjzxmgzszQjList = pjsxPjzxmgzszQjService.selectByMainId(id,khlx);
		return Result.ok(pjsxPjzxmgzszQjList);
	}
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/querypjsxPjzxmgzszGsByMainId")
	public Result<?> querypjsxPjzxmgzszGsListByMainId(@RequestParam(name="xmbh",required=true) String id
			,@RequestParam(name="khlx",required=true) String khlx) {
		List<PJSX_PJZXMGZSZ_GS> pjsxPjzxmgzszGsList = pjsx_pjzxmgzsz_gsService.selectByMainId(id,khlx);
		return Result.ok(pjsxPjzxmgzszGsList);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pjsxPjzxmsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, pjsxPjzxmsz pjsxPjzxmsz) {
      // Step.1 组装查询条件
      QueryWrapper<pjsxPjzxmsz> queryWrapper = QueryGenerator.initQueryWrapper(pjsxPjzxmsz, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<pjsxPjzxmszPage> pageList = new ArrayList<pjsxPjzxmszPage>();
      List<pjsxPjzxmsz> pjsxPjzxmszList = pjsxPjzxmszService.list(queryWrapper);
      for (pjsxPjzxmsz temp : pjsxPjzxmszList) {
          pjsxPjzxmszPage vo = new pjsxPjzxmszPage();
          BeanUtils.copyProperties(temp, vo);
          List<pjsxPjzxmgzszXl> pjsxPjzxmgzszXlList = pjsxPjzxmgzszXlService.selectByMainId(temp.getXmbh(),temp.getKhlx());
          vo.setPjsxPjzxmgzszXlList(pjsxPjzxmgzszXlList);
          List<pjsxPjzxmgzszQj> pjsxPjzxmgzszQjList = pjsxPjzxmgzszQjService.selectByMainId(temp.getXmbh(),temp.getKhlx());
          vo.setPjsxPjzxmgzszQjList(pjsxPjzxmgzszQjList);
          List<PJSX_PJZXMGZSZ_GS> pjsxPjzxmgzszGsList = pjsx_pjzxmgzsz_gsService.selectByMainId(temp.getXmbh(),temp.getKhlx());
          vo.setPjsxPjzxmgzszGsList(pjsxPjzxmgzszGsList);
          pageList.add(vo);
      }
      //Step.3 调用AutoPoi导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "评级子项目设置");
      mv.addObject(NormalExcelConstants.CLASS, pjsxPjzxmszPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("评级子项目设置数据", "导出人:"+sysUser.getRealname(), "评级子项目设置"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<pjsxPjzxmszPage> list = ExcelImportUtil.importExcel(file.getInputStream(), pjsxPjzxmszPage.class, params);
              for (pjsxPjzxmszPage page : list) {
                  pjsxPjzxmsz po = new pjsxPjzxmsz();
                  BeanUtils.copyProperties(page, po);
                  pjsxPjzxmszService.saveMain(po, page.getPjsxPjzxmgzszXlList(),page.getPjsxPjzxmgzszQjList(),page.getPjsxPjzxmgzszGsList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

}
