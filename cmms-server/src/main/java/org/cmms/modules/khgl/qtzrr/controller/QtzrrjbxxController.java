package org.cmms.modules.khgl.qtzrr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khgl.qtzrr.entity.*;
import org.cmms.modules.khgl.qtzrr.service.*;
import org.cmms.modules.khgl.qtzrr.vo.KhglQtzrrcjxxPage;
import org.cmms.modules.khgl.qtzrr.vo.KhglQtzrrhmcxxPage;
import org.cmms.modules.pad.qtzrrxxgl.entity.CamsZcsxQtzrrfcxxPad;
import org.cmms.modules.pad.qtzrrxxgl.entity.CamsZcsxQtzrrpjsxxxPad;
import org.cmms.modules.pad.qtzrrxxgl.entity.QtzrrJtcyxx;
import org.cmms.modules.pad.qtzrrxxgl.entity.VKhglQtzrrhzxxgl;
import org.cmms.modules.pad.qtzrrxxgl.service.ICamsZcsxQtzrrfcxxPadService;
import org.cmms.modules.pad.qtzrrxxgl.service.ICamsZcsxQtzrrpjsxxxPadService;
import org.cmms.modules.pad.qtzrrxxgl.service.IVKhglQtzrrhzxxglService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.yxdygl.czxxgl.service.IYxdygl_czxxglService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description: 其他自然人信息管理
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/qtzrr/qtzrrjbxx")
public class QtzrrjbxxController {

	@Autowired
	private ISysDictService sysDictService;
	@Autowired
	private IVKhglQtzrrjbxxService vKhglQtzrrjbxxService;
	@Autowired
	private ICamsZcsxQtzrrcjxxService camsZcsxQtzrrcjxxService;
	@Autowired
	private IQtzrrfcxxService qtzrrfcxxService;
	@Autowired
	private IQtzrrPjsxxxService qtzrrPjsxxxService;
	@Autowired
	private IYwhywwlxxService ywhywwlxxService;
	@Autowired
	private IQtzrrFjglService fjglService;
	@Autowired
	private IQtzrrhzzllbService khglQtzrrhzzllbService;
	@Autowired
	private IYxdygl_czxxglService yxdygl_czxxglService;
	@Autowired
	private Environment environment;
	@Autowired
	private ITjfxZhbyService tjfxZhbyService;
	@Autowired
	private ISysDictService iSysDictService;
	@Autowired
	private IKhglQtzrrhmcxxService khglQtzrrhmcxxService;
	@Autowired
	private IVKhglQtzrrhzxxglService vKhglQtzrrhzxxglService;
	@Autowired
	private ICamsZcsxQtzrrpjsxxxPadService camsZcsxQtzrrpjsxxxPadService;
	@Autowired
	private ICamsZcsxQtzrrfcxxPadService camsZcsxQtzrrfcxxPadService;

	/**
	  * 分页列表查询
	 * @param vKhglQtzrrjbxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<VKhglQtzrrjbxx>> queryPageList(VKhglQtzrrjbxx vKhglQtzrrjbxx,
													   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													   HttpServletRequest req) {
		Result<IPage<VKhglQtzrrjbxx>> result = new Result<IPage<VKhglQtzrrjbxx>>();
		QueryWrapper<VKhglQtzrrjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglQtzrrjbxx, req.getParameterMap());
		Page<VKhglQtzrrjbxx> page = new Page<VKhglQtzrrjbxx>(pageNo, pageSize);
		IPage<VKhglQtzrrjbxx> pageList = vKhglQtzrrjbxxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 *
	 * @param khglQtzrrhmcxxPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhglQtzrrhmcxxPage khglQtzrrhmcxxPage) {
		KhglQtzrrhmcxx khglQtzrrhmcxx = new KhglQtzrrhmcxx();
		BeanUtils.copyProperties(khglQtzrrhmcxxPage, khglQtzrrhmcxx);
		khglQtzrrhmcxxService.saveMain(khglQtzrrhmcxx, khglQtzrrhmcxxPage.getCamsZcsxQtzrrcjxxList(),khglQtzrrhmcxxPage.getQtzrrfcxxList(),khglQtzrrhmcxxPage.getYwhxgywList(),khglQtzrrhmcxxPage.getQtzrrPjsxxxList(),khglQtzrrhmcxxPage.getFjglList());
		return Result.ok("添加成功!");
	}

	/**
     * 编辑
	 * @param khglQtzrrhmcxxPage
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhglQtzrrhmcxxPage khglQtzrrhmcxxPage) {
		KhglQtzrrhmcxx khglQtzrrhmcxx = new KhglQtzrrhmcxx();
		BeanUtils.copyProperties(khglQtzrrhmcxxPage, khglQtzrrhmcxx);
		khglQtzrrhmcxxService.updateMain(khglQtzrrhmcxx, khglQtzrrhmcxxPage.getCamsZcsxQtzrrcjxxList(),khglQtzrrhmcxxPage.getQtzrrfcxxList(),khglQtzrrhmcxxPage.getYwhxgywList(),khglQtzrrhmcxxPage.getQtzrrPjsxxxList(),khglQtzrrhmcxxPage.getFjglList());
		return Result.ok("编辑成功!");
	}

	/**
     * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id删除")
	@ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			khglQtzrrhmcxxService.delMain(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}

	/**
     * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "1-批量删除")
	@ApiOperation(value="1-批量删除", notes="1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<KhglQtzrrhmcxx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<KhglQtzrrhmcxx> result = new Result<KhglQtzrrhmcxx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.khglQtzrrhmcxxService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}

	/**
     * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id查询")
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<KhglQtzrrhmcxx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<KhglQtzrrhmcxx> result = new Result<KhglQtzrrhmcxx>();
		KhglQtzrrhmcxx khglQtzrrhmcxx = khglQtzrrhmcxxService.getById(id);
		if(khglQtzrrhmcxx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khglQtzrrhmcxx);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param vKhglQtzrrjbxx
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, VKhglQtzrrjbxx vKhglQtzrrjbxx) {
		// Step.1 组装查询条件
		vKhglQtzrrjbxx.setSfcj(1);
		QueryWrapper<VKhglQtzrrjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglQtzrrjbxx, request.getParameterMap());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		Map<String,String[]> map = new HashMap<>();
		//Step.2 获取导出数据
		List<KhglQtzrrhmcxxPage> pageList = new ArrayList<KhglQtzrrhmcxxPage>();
		List<VKhglQtzrrjbxx> vKhglQtzrrjbxxList = vKhglQtzrrjbxxService.list(queryWrapper);
		for (VKhglQtzrrjbxx khglQtzrrjbxx : vKhglQtzrrjbxxList) {
			KhglQtzrrhmcxx khglQtzrrhmcxx = new KhglQtzrrhmcxx();
			khglQtzrrhmcxx.setZjhm(khglQtzrrjbxx.getZjhm());
			QueryWrapper queryWrapper1 = QueryGenerator.initQueryWrapper(khglQtzrrhmcxx,map);
			List<KhglQtzrrhmcxx> khhmcxxList = khglQtzrrhmcxxService.list(queryWrapper1);
			for (KhglQtzrrhmcxx temp : khhmcxxList) {
				KhglQtzrrhmcxxPage vo = new KhglQtzrrhmcxxPage();
				BeanUtils.copyProperties(temp, vo);
				List<CamsZcsxQtzrrcjxx> camsZcsxQtzrrcjxxList = camsZcsxQtzrrcjxxService.selectByMainId(temp.getZjhm());
				vo.setCamsZcsxQtzrrcjxxList(camsZcsxQtzrrcjxxList);
				List<Qtzrrfcxx> qtzrrfcxxList = qtzrrfcxxService.selectByMainId(temp.getZjhm());
				vo.setQtzrrfcxxList(qtzrrfcxxList);
				List<Ywhywwlxx> ywhxgywList = ywhywwlxxService.selectByMainId(temp.getZjhm());
				vo.setYwhxgywList(ywhxgywList);
				List<QtzrrPjsxxx> qtzrrPjsxxxList = qtzrrPjsxxxService.selectByMainId(temp.getZjhm());
				vo.setQtzrrPjsxxxList(qtzrrPjsxxxList);
				List<Fjgl> fjglList = fjglService.selectByMainId(temp.getZjhm());
				vo.setFjglList(fjglList);
				pageList.add(vo);
			}

		}
		//Step.3 调用AutoPoi导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		mv.addObject(NormalExcelConstants.FILE_NAME, "其他自然人基本信息");
		mv.addObject(NormalExcelConstants.CLASS, KhglQtzrrhmcxxPage.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("其他自然人基本信息数据", "导出人:"+sysUser.getRealname(), "其他自然人基本信息"));
		mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		return mv;
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param vKhglQtzrrjbxx
	 */
	@RequestMapping(value = "/exportCjxxXls")
	public ModelAndView exportCjxxXls(HttpServletRequest request, VKhglQtzrrjbxx vKhglQtzrrjbxx) {
		// Step.1 组装查询条件
		QueryWrapper<VKhglQtzrrjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglQtzrrjbxx, request.getParameterMap());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		Map<String,String[]> map = new HashMap<>();
		//Step.2 获取导出数据
		List<KhglQtzrrcjxxPage> pageList = new ArrayList<KhglQtzrrcjxxPage>();
		List<VKhglQtzrrjbxx> vKhglQtzrrjbxxList = vKhglQtzrrjbxxService.list(queryWrapper);
		for (VKhglQtzrrjbxx khglQtzrrjbxx : vKhglQtzrrjbxxList) {
			KhglQtzrrcjxxPage vo = new KhglQtzrrcjxxPage();
			BeanUtils.copyProperties(khglQtzrrjbxx, vo);
			pageList.add(vo);
		}
		//Step.3 调用AutoPoi导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		mv.addObject(NormalExcelConstants.FILE_NAME, "其他自然人采集信息");
		mv.addObject(NormalExcelConstants.CLASS, KhglQtzrrcjxxPage.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("其他自然人采集信息", "导出人:"+sysUser.getRealname(), "其他自然人采集信息"));
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
				List<KhglQtzrrhmcxxPage> list = ExcelImportUtil.importExcel(file.getInputStream(), KhglQtzrrhmcxxPage.class, params);
				for (KhglQtzrrhmcxxPage page : list) {
					KhglQtzrrhmcxx po = new KhglQtzrrhmcxx();
					BeanUtils.copyProperties(page, po);
					khglQtzrrhmcxxService.saveMain(po, page.getCamsZcsxQtzrrcjxxList(),page.getQtzrrfcxxList(),page.getYwhxgywList(),page.getQtzrrPjsxxxList(),page.getFjglList());
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

	/**
	 * 通过户号编码查询家庭房产情况
	 * @param hhbm
	 * @return
	 */
	@ApiOperation(value="通过户号编码查询家庭房产情况", notes="通过户号编码查询家庭房产情况")
	@RequestMapping(value = "/queryQtzrrFcxxByMainId", method = RequestMethod.GET)
	public Result<JSONArray> queryQtzrrFcxxByMainId(@RequestParam(name = "hhbm",required = true) String hhbm) {
		Result<JSONArray> result = new Result<>();
		Qtzrrfcxx check = new Qtzrrfcxx();
		check.setHhbm(hhbm);
		Map<String, String[]> map=new HashMap<>();
		QueryWrapper<Qtzrrfcxx> queryWrapper = QueryGenerator.initQueryWrapper(check,map);
		List<Qtzrrfcxx> qtzrrfcxxList = qtzrrfcxxService.list(queryWrapper);
		if (qtzrrfcxxList == null) {
			result.error500("未找到对应实体！");
		} else {
			JSONArray json=new JSONArray();
			for (Qtzrrfcxx qtzrrfcxx : qtzrrfcxxList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("hhbm",qtzrrfcxx.getHhbm());
				jsonObject.put("khmc",qtzrrfcxx.getKhmc());
				jsonObject.put("zjhm",qtzrrfcxx.getZjhm());
				jsonObject.put("fcwz",qtzrrfcxx.getFcwz());
				jsonObject.put("fcmj",qtzrrfcxx.getFcmj());
				jsonObject.put("fcjz",qtzrrfcxx.getFcjz());
				jsonObject.put("fcxz",qtzrrfcxx.getFcxz() == null ? "" : sysDictService.queryDictTextByKey("khgl_fcxz",qtzrrfcxx.getFcxz()));
				jsonObject.put("fcbm",qtzrrfcxx.getFcbm());
				jsonObject.put("fcdj",qtzrrfcxx.getFcdj());
				jsonObject.put("bz",qtzrrfcxx.getBz());
				json.add(jsonObject);
			}
			result.setResult(json);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	 * 通过hhbm查询
	 * @param zjhm
	 * @return
	 */
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@RequestMapping(value = "/queryQtzrrZcxxByMainId", method = RequestMethod.GET)
	public Result<JSONArray> queryQtzrrZcxxByMainId(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<JSONArray> result = new Result<>();
		List<QtzrrPjsxxx> qtzrrPjsxxxList = qtzrrPjsxxxService.selectByMainId(zjhm);
		if(qtzrrPjsxxxList==null) {
			result.error500("未找到对应实体");
		}else {
			result.setSuccess(true);
			JSONArray jsonArray = new JSONArray();
			for (QtzrrPjsxxx pjsxxx: qtzrrPjsxxxList ){
				//资产情况
				JSONObject jo = new JSONObject();
				jo.put("zclx","地产");
				jo.put("zcsl",pjsxxx.getDcsl());
				jo.put("zcjg",pjsxxx.getDcjz());
				jo.put("zcsm",pjsxxx.getDcxqsm());
				JSONObject jo1 = new JSONObject();
				jo1.put("zclx","交通工具");
				jo1.put("zcsl",pjsxxx.getJtgjsl());
				jo1.put("zcjg",pjsxxx.getJtgjjz());
				jo1.put("zcsm",pjsxxx.getJtgjxqsm());
				JSONObject jo2 = new JSONObject();
				jo2.put("zclx","存款");
				jo2.put("zcsl",pjsxxx.getCksl());
				jo2.put("zcjg",pjsxxx.getCkjz());
				jo2.put("zcsm",pjsxxx.getCkxqsm());
				JSONObject jo3 = new JSONObject();
				jo3.put("zclx","有价单证");
				jo3.put("zcsl",pjsxxx.getYjdzsl());
				jo3.put("zcjg",pjsxxx.getYjdzjz());
				jo3.put("zcsm",pjsxxx.getYjdzxqsm());
				JSONObject jo4 = new JSONObject();
				jo4.put("zclx","股权");
				jo4.put("zcsl",pjsxxx.getGqsl());
				jo4.put("zcjg",pjsxxx.getGqjz());
				jo4.put("zcsm",pjsxxx.getGqxqsm());
				JSONObject jo5 = new JSONObject();
				jo5.put("zclx","其他资产");
				jo5.put("zcsl",pjsxxx.getJtgjsl());
				jo5.put("zcjg",pjsxxx.getJtgjjz());
				jo5.put("zcsm",pjsxxx.getJtgjxqsm());
				jsonArray.add(jo);
				jsonArray.add(jo1);
				jsonArray.add(jo2);
				jsonArray.add(jo3);
				jsonArray.add(jo4);
				jsonArray.add(jo5);
			}
			result.setResult(jsonArray);
		}

		return result;
	}

	/**
	 * 通过zjhm查询
	 * @param zjhm
	 * @return
	 */
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@RequestMapping(value = "/queryQtzrrFzqkByMainId", method = RequestMethod.GET)
	public Result<JSONArray> queryQtzrrFzqkByMainId(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<JSONArray> result = new Result<>();
		List<QtzrrPjsxxx> qtzrrPjsxxxList = qtzrrPjsxxxService.selectByMainId(zjhm);
		if(qtzrrPjsxxxList==null) {
			result.error500("未找到对应实体");
		}else {
			result.setSuccess(true);
			JSONArray jsonArray = new JSONArray();
			for (QtzrrPjsxxx pjsxxx: qtzrrPjsxxxList ){
				//负债情况
				JSONObject jo1 = new JSONObject();
				jo1.put("jkfs","本系统");
				jo1.put("zqr",pjsxxx.getBxtjkzqr());
				jo1.put("jkje",pjsxxx.getBxtjksl());
				jo1.put("jksm",pjsxxx.getBxtjkxqsm());
				JSONObject jo2 = new JSONObject();
				jo2.put("jkfs","他行");
				jo2.put("zqr",pjsxxx.getThjkzqr());
				jo2.put("jkje",pjsxxx.getThjksl());
				jo2.put("jksm",pjsxxx.getThjkxqsm());
				JSONObject jo3 = new JSONObject();
				jo3.put("jkfs","信用卡");
				jo3.put("zqr",pjsxxx.getXykzqr());
				jo3.put("jkje",pjsxxx.getXyksl());
				jo3.put("jksm",pjsxxx.getXykxqsm());
				JSONObject jo4 = new JSONObject();
				jo4.put("jkfs","其他");
				jo4.put("zqr",pjsxxx.getQtfzzqr());
				jo4.put("jkje",pjsxxx.getQtfzsl());
				jo4.put("jksm",pjsxxx.getQtfzxqsm());
				JSONObject jo5 = new JSONObject();
				jo5.put("jkfs", "家庭年开支");
				jo5.put("zqr", pjsxxx.getJtnkzzqr());
				jo5.put("jkje", pjsxxx.getJtnkzsl());
				jo5.put("jksm", pjsxxx.getJtnkzxqsm());
				jsonArray.add(jo1);
				jsonArray.add(jo2);
				jsonArray.add(jo3);
				jsonArray.add(jo4);
				jsonArray.add(jo5);
			}
			result.setResult(jsonArray);
		}

		return result;
	}

	/**
	 * 其他自然人评级授信信息查看
	 * @param zjhm
	 * @return
	 */
	@GetMapping(value = "/queryQtzrrPjsxxxByMainId")
	public Result<QtzrrPjsxxx> queryQtzrrPjsxxxByMainId(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<QtzrrPjsxxx> result = new Result<QtzrrPjsxxx>();
		List<QtzrrPjsxxx> qtzrrPjsxxxList = qtzrrPjsxxxService.selectByMainId(zjhm);
		try {
			if (qtzrrPjsxxxList.size()!= 0) {
				result.setResult(qtzrrPjsxxxList.get(0));
			}
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

    /**
     * 查询家庭成员信息
     * @param hhbm
     * @return
     */
	@ApiOperation(value = "查询家庭成员信息", notes = "查询家庭成员信息")
	@RequestMapping(value = "/queryJtcy", method = RequestMethod.GET)
	public Result<JSONArray> queryJtcy(@RequestParam(name = "hhbm",required = true) String hhbm) {
		Result<JSONArray> result = new Result<>();
		List<String> list = new ArrayList<String>();
		KhglQtzrrhmcxx qtzrrhmcxxQuery = new KhglQtzrrhmcxx();
		qtzrrhmcxxQuery.setHhbm(hhbm);
		Map<String, String[]> map = new HashMap<>();
		QueryWrapper<KhglQtzrrhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(qtzrrhmcxxQuery, map);
		List<KhglQtzrrhmcxx> qtzrrhmcxxList = khglQtzrrhmcxxService.list(queryWrapper);
		if (qtzrrhmcxxList == null) {
			result.error500("未找到对应实体");
		}
		JSONArray json = new JSONArray();
		for (KhglQtzrrhmcxx qtzrrhmcxx : qtzrrhmcxxList) {
			JSONObject jo = new JSONObject();
			jo.put("hhbm", qtzrrhmcxx.getHhbm());
			jo.put("khmc", qtzrrhmcxx.getKhmc());
			jo.put("zjhm", qtzrrhmcxx.getZjhm());
			jo.put("yhzgx",qtzrrhmcxx.getYhzgx()== null ? "" : sysDictService.queryDictTextByKey("yhzgx", qtzrrhmcxx.getYhzgx()));
			jo.put("xb",qtzrrhmcxx.getXb()==null ? "" : sysDictService.queryDictTextByKey("sex", qtzrrhmcxx.getXb()));
			jo.put("sjhm", qtzrrhmcxx.getLxfs());
			Map<String,String[]> map2 = new HashMap<>();
			CamsZcsxQtzrrcjxx qtzrrcjxxQuery = new CamsZcsxQtzrrcjxx();
			qtzrrcjxxQuery.setZjhm(qtzrrhmcxx.getZjhm());
			QueryWrapper<CamsZcsxQtzrrcjxx> queryWrapper2 = QueryGenerator.initQueryWrapper(qtzrrcjxxQuery, map2);
			List<CamsZcsxQtzrrcjxx> camsZcsxQtzrrcjxxList =camsZcsxQtzrrcjxxService.list(queryWrapper2);
			if (camsZcsxQtzrrcjxxList.size() != 0) {
				jo.put("sjhm", camsZcsxQtzrrcjxxList.get(0).getSjhm());
			}
			//查询业务信息
			Ywhywwlxx ywhywwlxxQuery = new Ywhywwlxx();
			ywhywwlxxQuery.setZjhm(qtzrrhmcxx.getZjhm());
			//ywhywwlxxQuery.setHmcId(khhmcxx.getId());
			Map<String, String[]> map1 = new HashMap<>();
			QueryWrapper<Ywhywwlxx> queryWrapper1 = QueryGenerator.initQueryWrapper(ywhywwlxxQuery, map1);
			List<Ywhywwlxx> cyxx1 = ywhywwlxxService.list(queryWrapper1);
			if (cyxx1.size()!=0) {
				jo.put("ckye", cyxx1.get(0).getCkye());
				jo.put("ckrpye", cyxx1.get(0).getCkrpye());
				jo.put("dkye", cyxx1.get(0).getDkye());
				jo.put("bldkye", cyxx1.get(0).getBldkye());
				jo.put("bwbldkye", cyxx1.get(0).getBwbldkye());
			}
			json.add(jo);
			result.setResult(json);
			result.setSuccess(true);


		}
		return result;
	}

    /**
     * 查询附件
     * @param zjhm
     * @return
     */
    @ApiOperation(value = "查询附件", notes = "查询附件")
    @RequestMapping(value = "/queryFj", method = RequestMethod.GET)
    public JSONArray queryByFJ(@RequestParam(name = "zjhm",required = true) String zjhm) {
        Result<List<String>> result = new Result<>();
        List<String> list = new ArrayList<String>();
        Fjgl kg = new Fjgl();
        kg.setZjhm(zjhm);
        Map<String, String[]> map = new HashMap<>();
        QueryWrapper<Fjgl> queryWrapper = QueryGenerator.initQueryWrapper(kg, map);
        List<Fjgl> fjglList = fjglService.list(queryWrapper);
        if (fjglList == null) {
            result.error500("未找到对应实体");
        } else {
            result.setSuccess(true);
        }

		JSONArray json = new JSONArray();
        for (Fjgl a : fjglList) {
            JSONObject jo = new JSONObject();
            jo.put("hhbm", a.getHhbm());
            jo.put("zjhm", a.getZjhm());
            jo.put("zlmc", a.getZlmc());
            jo.put("zlbh",a.getZlbh());
            jo.put("fjbz", a.getBz());
            jo.put("fwlj", a.getFwlj());
			jo.put("zllj", a.getZllj());
            jo.put("scsj", a.getScsj());
            jo.put("scr", a.getScr());
            json.add(jo);
        }
        return json;
    }

	/**
	 * 查询户主附件
	 * @param hhbm
	 * @return
	 */
	@ApiOperation(value = "查询户主附件", notes = "查询户主附件")
	@RequestMapping(value = "/queryHzFj", method = RequestMethod.GET)
	public JSONArray queryHzFj(@RequestParam(name = "hhbm",required = true) String hhbm) {
		System.out.println("-----"+hhbm);
		Result<List<String>> result = new Result<>();
		List<String> list = new ArrayList<String>();
		KhglQtzrrhzzllb kg = new KhglQtzrrhzzllb();
		kg.setHhbm(hhbm);
		Map<String, String[]> map = new HashMap<>();
		QueryWrapper<KhglQtzrrhzzllb> queryWrapper = QueryGenerator.initQueryWrapper(kg, map);
		List<KhglQtzrrhzzllb> qtzrrhzzllbList = khglQtzrrhzzllbService.list(queryWrapper);
		if (qtzrrhzzllbList == null) {
			result.error500("未找到对应实体");
		} else {
			result.setSuccess(true);
		}

		JSONArray json = new JSONArray();
		for (KhglQtzrrhzzllb a : qtzrrhzzllbList) {
			JSONObject jo = new JSONObject();
			jo.put("hhbm", a.getHhbm());
			jo.put("zlmc", a.getZlmc());
			jo.put("zlbh",a.getZlbh());
			jo.put("fjbz", a.getBz());
			jo.put("fwlj", a.getFwlj());
			jo.put("zllj", a.getZllj());
			jo.put("scsj", a.getScsj());
			jo.put("scr", a.getScr());
			json.add(jo);
		}
		return json;
	}

    /**
     * 附件下载路径查询
     * @param zlbh
     * @return
     */
	@ApiOperation(value = "附件下载路径查询", notes = "附件下载路径")
	@RequestMapping(value = "/dpic", method = RequestMethod.GET)
	public JSON dpic(@RequestParam(name = "zlbh",required = true) String zlbh){
		Fjgl kg = new Fjgl();
		kg.setZlbh(zlbh);
		Map<String, String[]> map = new HashMap<>();
		QueryWrapper<Fjgl> queryWrapper = QueryGenerator.initQueryWrapper(kg, map);
		List<Fjgl> fjglList = fjglService.list(queryWrapper);
		JSONObject jo = new JSONObject();
		if(fjglList.size()!=0) {
			jo.put("zllj", fjglList.get(0).getFwlj());
		}
		return jo;
	}

	/**
	 * 附件下载路径查询
	 * @param zlbh
	 * @return
	 */
	@ApiOperation(value = "附件下载路径查询", notes = "附件下载路径")
	@RequestMapping(value = "/downLoad", method = RequestMethod.GET)
	public JSON downLoad(@RequestParam(name = "zlbh",required = true) String zlbh){
		KhglQtzrrhzzllb kg = new KhglQtzrrhzzllb();
		kg.setZlbh(zlbh);
		Map<String, String[]> map = new HashMap<>();
		QueryWrapper<KhglQtzrrhzzllb> queryWrapper = QueryGenerator.initQueryWrapper(kg, map);
		List<KhglQtzrrhzzllb> qtzrrhzzllbList = khglQtzrrhzzllbService.list(queryWrapper);
		JSONObject jo = new JSONObject();
		if(qtzrrhzzllbList.size()!=0) {
			jo.put("zllj", qtzrrhzzllbList.get(0).getFwlj());
		}
		return jo;

	}

    /**
     * 查询与我行相关业务
     * @param
     * @return
     */
	@ApiOperation(value="查询与我行相关业务", notes="查询与我行相关业务")
	@RequestMapping(value = "queryYwhxgywxx",method = RequestMethod.GET)
	public Result<Ywhywwlxx> shjbxxcx(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<Ywhywwlxx> result = new Result<Ywhywwlxx>();
		try {
			Ywhywwlxx kg = new Ywhywwlxx();
			kg.setZjhm(zjhm);
			Map<String, String[]> map = new HashMap<>();
			QueryWrapper<Ywhywwlxx> queryWrapper1 = QueryGenerator.initQueryWrapper(kg, map);
			List<Ywhywwlxx> gzap_jhxf_khjl = ywhywwlxxService.list(queryWrapper1);
			if(gzap_jhxf_khjl.size()!=0) {
				result.setResult(gzap_jhxf_khjl.get(0));
			}
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}

	/**
	 * 农户基本信息查看
	 * @param zjhm
	 * @return
	 */
	@GetMapping(value = "/nhjbxxcx")
	public Result<KhglQtzrrhmcxx> nhjbxxcx(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<KhglQtzrrhmcxx> result = new Result<KhglQtzrrhmcxx>();
		try {
			KhglQtzrrhmcxx qtzrrhmcxx = new KhglQtzrrhmcxx();
			qtzrrhmcxx.setZjhm(zjhm);
			Map<String,String[]> map = new HashMap<>();
			QueryWrapper<KhglQtzrrhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(qtzrrhmcxx,map);
			List<KhglQtzrrhmcxx> qtzrrhmcxxList = khglQtzrrhmcxxService.list(queryWrapper);
			result.setResult(qtzrrhmcxxList.get(0));
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 导出模板excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/qtzrrmxexportTemplateXls")
	public ModelAndView qtzrrmxexportTemplateXls(KhglQtzrrhmcxx khglQtzrrhmcxx,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		ModelAndView mv = new ModelAndView(new TemplateExcelView());
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,String[]> map1 = new HashMap<>();

		JSONArray jsonArray6 = new JSONArray();
		//导出设置权限本支行只能导出本支行的数据，如果是全行则可以导出全行数据
		String sszh = "";
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		if (sysUser.getOrgCode()!= null && sysUser.getOrgCode().equals("1")) {
			sszh = khglQtzrrhmcxx.getSszh();
		} else {
			sszh = sysUser.getOrgCode();
		}

		if (sszh.equals(khglQtzrrhmcxx.getSszh())) {

			// Step.1 组装查询条件
			//查询基本信息
			KhglQtzrrhmcxx qtzrrjbxx = new KhglQtzrrhmcxx();
			qtzrrjbxx.setZjhm(khglQtzrrhmcxx.getZjhm());
			qtzrrjbxx.setSszh(sszh);
			QueryWrapper<KhglQtzrrhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(qtzrrjbxx, request.getParameterMap());
			List<KhglQtzrrhmcxx> khglQtzrrhmcxxList = khglQtzrrhmcxxService.list(queryWrapper);
			List<String> list = new ArrayList<>();
			for (KhglQtzrrhmcxx qtzrrhmcxx : khglQtzrrhmcxxList) {
				//把农户基本信息中的字段编码转换成汉字
				qtzrrhmcxx.setSszh(qtzrrhmcxx.getSszh() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", qtzrrhmcxx.getSszh()));
				qtzrrhmcxx.setSfhz(qtzrrhmcxx.getSfhz() == null ? "" : sysDictService.queryDictTextByKey("sfbz", qtzrrhmcxx.getSfhz()));
				qtzrrhmcxx.setYhzgx(qtzrrhmcxx.getYhzgx() == null ? "" : sysDictService.queryDictTextByKey("yhzgx", qtzrrhmcxx.getYhzgx()));
				qtzrrhmcxx.setXb(qtzrrhmcxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", qtzrrhmcxx.getXb()));
				qtzrrhmcxx.setMz(qtzrrhmcxx.getMz() == null ? "" : sysDictService.queryDictTextByKey("mz", qtzrrhmcxx.getMz()));
				qtzrrhmcxx.setHyzk(qtzrrhmcxx.getHyzk() == null ? "" : sysDictService.queryDictTextByKey("hyzk", qtzrrhmcxx.getHyzk()));
				qtzrrhmcxx.setSfpkh(qtzrrhmcxx.getSfpkh() == null ? "" : sysDictService.queryDictTextByKey("sfbz", qtzrrhmcxx.getSfpkh()));
			}
			map.put("qtzrrjbxx", khglQtzrrhmcxxList);

			//查询农户采集信息
			CamsZcsxQtzrrcjxx camsZcsxQtzrrcjxx = new CamsZcsxQtzrrcjxx();
			camsZcsxQtzrrcjxx.setZjhm(khglQtzrrhmcxx.getZjhm());
			QueryWrapper<CamsZcsxQtzrrcjxx> queryWrapper1 = QueryGenerator.initQueryWrapper(camsZcsxQtzrrcjxx,map1);
			List<CamsZcsxQtzrrcjxx> camsZcsxQtzrrcjxxList = camsZcsxQtzrrcjxxService.list(queryWrapper1);
			for (CamsZcsxQtzrrcjxx qtzrrcjxx : camsZcsxQtzrrcjxxList) {
				//把采集信息表中的字段编码转换成汉字
				qtzrrcjxx.setSskhjl(qtzrrcjxx.getSskhjl() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", qtzrrcjxx.getSskhjl()));
				qtzrrcjxx.setWhcd(qtzrrcjxx.getWhcd() == null ? "" : sysDictService.queryDictTextByKey("whcd", qtzrrcjxx.getWhcd()));
				qtzrrcjxx.setHkxz(qtzrrcjxx.getHkxz() == null ? "" : sysDictService.queryDictTextByKey("khgl_hkxz", qtzrrcjxx.getHkxz()));
				qtzrrcjxx.setKhlx1(qtzrrcjxx.getKhlx1() == null ? "" :sysDictService.queryDictTextByKey("khlx", qtzrrcjxx.getKhlx1()));
				qtzrrcjxx.setKhlx2(qtzrrcjxx.getKhlx2() == null ? "" :sysDictService.queryDictTextByKey("khlx", qtzrrcjxx.getKhlx2()));
				qtzrrcjxx.setYwzn(qtzrrcjxx.getYwzn() == null ? "" :sysDictService.queryDictTextByKey("sfbz", qtzrrcjxx.getYwzn()));
				qtzrrcjxx.setJkzk(qtzrrcjxx.getJkzk() == null ? "" :sysDictService.queryDictTextByKey("jkzk", qtzrrcjxx.getJkzk()));
				qtzrrcjxx.setLdnl(qtzrrcjxx.getLdnl() == null ? "" :sysDictService.queryDictTextByKey("ldnl", qtzrrcjxx.getLdnl()));
				qtzrrcjxx.setJznx(qtzrrcjxx.getJznx() == null ? "" :sysDictService.queryDictTextByKey("jznx", qtzrrcjxx.getJznx()));
				qtzrrcjxx.setZgxl(qtzrrcjxx.getZgxl() == null ? "" :sysDictService.queryDictTextByKey("whcd", qtzrrcjxx.getZgxl()));
				qtzrrcjxx.setZgxw(qtzrrcjxx.getZgxw() == null ? "" :sysDictService.queryDictTextByKey("zgxw", qtzrrcjxx.getZgxw()));
				qtzrrcjxx.setSfycdg(qtzrrcjxx.getSfycdg() == null ? "" : sysDictService.queryDictTextByKey("sfbz", qtzrrcjxx.getSfycdg()));
				qtzrrcjxx.setSfsx(qtzrrcjxx.getSfsx() == null ? "" : sysDictService.queryDictTextByKey("sfbz", qtzrrcjxx.getSfsx()));
				qtzrrcjxx.setKfyyqk(qtzrrcjxx.getKfyyqk() == null ? "" : sysDictService.queryDictTextByKey("khgl_khzycd", qtzrrcjxx.getKfyyqk()));
				qtzrrcjxx.setKcqzyw(qtzrrcjxx.getKcqzyw() == null ? "" : sysDictService.queryDictTextByKey("khqzyw", qtzrrcjxx.getKcqzyw()));
				qtzrrcjxx.setKhsxqk(qtzrrcjxx.getKhsxqk() == null ? "" : sysDictService.queryDictTextByKey("khgl_sxqk", qtzrrcjxx.getKhsxqk()));
				qtzrrcjxx.setDdpzzhpj(qtzrrcjxx.getDdpzzhpj() == null ? "" : sysDictService.queryDictTextByKey("ddpzzhpj", qtzrrcjxx.getDdpzzhpj()));
			}
			map.put("qtzrrcjxx", camsZcsxQtzrrcjxxList);
			System.out.println("—--------"+map.get("qtzrrcjxx"));


			//查询家庭相关情况
			Ywhywwlxx cyxx = new Ywhywwlxx();
			JSONArray jsonArray1 = new JSONArray();
			KhglQtzrrhmcxx queryqtzrrjbxx = new KhglQtzrrhmcxx();
			queryqtzrrjbxx.setHhbm(khglQtzrrhmcxx.getHhbm());
			queryqtzrrjbxx.setSszh(sszh);
			//Map<String, String[]> map1 = new HashMap<>();
			QueryWrapper<KhglQtzrrhmcxx> queryWrapper2 = QueryGenerator.initQueryWrapper(queryqtzrrjbxx, map1);
			List<KhglQtzrrhmcxx> qtzrrjbxxList = khglQtzrrhmcxxService.list(queryWrapper2);
			for (KhglQtzrrhmcxx qtzrrjbxx1 : qtzrrjbxxList) {
				cyxx.setZjhm(qtzrrjbxx1.getZjhm());
				QueryWrapper<Ywhywwlxx> queryWrapper3 = QueryGenerator.initQueryWrapper(cyxx, map1);
				List<Ywhywwlxx> cyxx1 = ywhywwlxxService.list(queryWrapper3);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("hhbm", qtzrrjbxx1.getHhbm());
					jsonObject.put("khmc", qtzrrjbxx1.getKhmc());
					jsonObject.put("zjhm", qtzrrjbxx1.getZjhm());
					jsonObject.put("yhzgx",qtzrrjbxx1.getYhzgx()==null ? "" :sysDictService.queryDictTextByKey("yhzgx", qtzrrjbxx1.getYhzgx()));
					jsonObject.put("xb", qtzrrjbxx1.getXb()==null ? "" : sysDictService.queryDictTextByKey("sex", qtzrrjbxx1.getXb()));
				    Map<String,String[]> map2 = new HashMap<>();
				    CamsZcsxQtzrrcjxx camsZcsxQtzrrcjxx1 = new CamsZcsxQtzrrcjxx();
					camsZcsxQtzrrcjxx1.setZjhm(qtzrrjbxx1.getZjhm());
				    QueryWrapper<CamsZcsxQtzrrcjxx> queryWrapper4 = QueryGenerator.initQueryWrapper(camsZcsxQtzrrcjxx1,map2);
				    List<CamsZcsxQtzrrcjxx> camsZcsxQtzrrcjxxList1 =camsZcsxQtzrrcjxxService.list(queryWrapper4);
				    if (camsZcsxQtzrrcjxxList1.size()!=0) {
						jsonObject.put("sjhm", camsZcsxQtzrrcjxxList1.get(0).getSjhm());
					}
				if (cyxx1.size() != 0) {
					jsonObject.put("ckye", cyxx1.get(0).getCkye());
					jsonObject.put("ckrp", cyxx1.get(0).getCkrpye());
					jsonObject.put("dkye", cyxx1.get(0).getDkye());
					jsonObject.put("bnblye", cyxx1.get(0).getBldkye());
					jsonObject.put("bwblye", cyxx1.get(0).getBwbldkye());
				}
				jsonArray1.add(jsonObject);

			}
			map.put("jtcyxx", jsonArray1);

			//查询与我行相关业务
			Ywhywwlxx ywhxgyw = new Ywhywwlxx();
			ywhxgyw.setZjhm(khglQtzrrhmcxx.getZjhm());
			//ywhxgyw.setSszh(sszh);
			QueryWrapper<Ywhywwlxx> queryWrapper3 = QueryGenerator.initQueryWrapper(ywhxgyw, map1);
			List<Ywhywwlxx> ywhxgywList = ywhywwlxxService.list(queryWrapper3);
			for (Ywhywwlxx ywhxgyw1 : ywhxgywList) {
				//ywhxgyw1.setSfbldkh(ywhxgyw1.getSfbldkh() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfbldkh()));
				ywhxgyw1.setSfktckyw(ywhxgyw1.getSfktckyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfktckyw()));
				ywhxgyw1.setSfktdkyw(ywhxgyw1.getSfktdkyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfktdkyw()));
				ywhxgyw1.setSfktsjyhyw(ywhxgyw1.getSfktsjyhyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfktsjyhyw()));
				ywhxgyw1.setSfktwsyhyw(ywhxgyw1.getSfktwsyhyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfktwsyhyw()));
				ywhxgyw1.setSfbletcyw(ywhxgyw1.getSfbletcyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfbletcyw()));
				ywhxgyw1.setSfktsbk(ywhxgyw1.getSfktsbk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfktsbk()));
				ywhxgyw1.setSfktpos(ywhxgyw1.getSfktpos() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfktpos()));
				ywhxgyw1.setSfktjhzf(ywhxgyw1.getSfktjhzf() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfktjhzf()));
				ywhxgyw1.setSfblezf(ywhxgyw1.getSfblezf() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfblezf()));
				ywhxgyw1.setSfblejf(ywhxgyw1.getSfblejf() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfblejf()));
				ywhxgyw1.setSfblznzd(ywhxgyw1.getSfblznzd() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfblznzd()));
				ywhxgyw1.setSfbllcyw(ywhxgyw1.getSfbllcyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfbllcyw()));
				ywhxgyw1.setSfbldlbx(ywhxgyw1.getSfbldlbx() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfbldlbx()));
				ywhxgyw1.setSfgzgzh(ywhxgyw1.getSfgzgzh() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfgzgzh()));
				ywhxgyw1.setSfktxyk(ywhxgyw1.getSfktxyk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfktxyk()));
				ywhxgyw1.setSfktsmf(ywhxgyw1.getSfktsmf() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhxgyw1.getSfktsmf()));
			}
			map.put("ywhxgxx", ywhxgywList);

			//查询房产信息
			Qtzrrfcxx qtzrrfcxxQuery = new Qtzrrfcxx();
			qtzrrfcxxQuery.setZjhm(khglQtzrrhmcxx.getZjhm());
			QueryWrapper<Qtzrrfcxx> queryWrapper4 = QueryGenerator.initQueryWrapper(qtzrrfcxxQuery, map1);
			List<Qtzrrfcxx> qtzrrfcxxList = qtzrrfcxxService.list(queryWrapper4);
			for (Qtzrrfcxx qtzrrfcxx : qtzrrfcxxList) {
				qtzrrfcxx.setFcxz(qtzrrfcxx.getFcxz() == null ? "" : sysDictService.queryDictTextByKey("khgl_fcxz", qtzrrfcxx.getFcxz()));
			}
			map.put("qtzrrfcxx", qtzrrfcxxList);

			//评级信息
			List<QtzrrPjsxxx> qtzrrPjsxxxList = qtzrrPjsxxxService.selectByMainId(khglQtzrrhmcxx.getZjhm());
			for (QtzrrPjsxxx qtzrrPjsxxx : qtzrrPjsxxxList) {
				qtzrrPjsxxx.setWhcd(qtzrrPjsxxx.getWhcd() == null ? "" : sysDictService.queryDictTextByKey("whcd", qtzrrPjsxxx.getWhcd()));
				qtzrrPjsxxx.setJkzk(qtzrrPjsxxx.getJkzk() == null ? "" : sysDictService.queryDictTextByKey("jkzk", qtzrrPjsxxx.getJkzk()));
				qtzrrPjsxxx.setHyzk(qtzrrPjsxxx.getHyzk() == null ? "" : sysDictService.queryDictTextByKey("qtzrr_hyzk", qtzrrPjsxxx.getHyzk()));
				qtzrrPjsxxx.setZy(qtzrrPjsxxx.getZy() == null ? "" : sysDictService.queryDictTextByKey("qtzrr_zy", qtzrrPjsxxx.getZy()));
				qtzrrPjsxxx.setZw(qtzrrPjsxxx.getZw() == null ? "" : sysDictService.queryDictTextByKey("qtzrr_zw", qtzrrPjsxxx.getZw()));
				qtzrrPjsxxx.setCynx(qtzrrPjsxxx.getCynx() == null ? "" : sysDictService.queryDictTextByKey("cynx", qtzrrPjsxxx.getCynx()));
				qtzrrPjsxxx.setJzzk(qtzrrPjsxxx.getJzzk() == null ? "" : sysDictService.queryDictTextByKey("jzzk", qtzrrPjsxxx.getJzzk()));
				qtzrrPjsxxx.setJzsj(qtzrrPjsxxx.getJzsj() == null ? "" : sysDictService.queryDictTextByKey("jzsj", qtzrrPjsxxx.getJzsj()));
				qtzrrPjsxxx.setYwwfjl(qtzrrPjsxxx.getYwwfjl() == null ? "" : sysDictService.queryDictTextByKey("ywbz", qtzrrPjsxxx.getYwwfjl()));
				qtzrrPjsxxx.setShpj(qtzrrPjsxxx.getShpj() == null ? "" : sysDictService.queryDictTextByKey("qtzrr_shpj", qtzrrPjsxxx.getShpj()));
				qtzrrPjsxxx.setDkyqqk(qtzrrPjsxxx.getDkyqqk() == null ? "" : sysDictService.queryDictTextByKey("bjlxyqqk", qtzrrPjsxxx.getDkyqqk()));
				qtzrrPjsxxx.setThzxyqjl(qtzrrPjsxxx.getThzxyqjl() == null ? "" : sysDictService.queryDictTextByKey("thzxyqjl", qtzrrPjsxxx.getThzxyqjl()));
				qtzrrPjsxxx.setJjnl(qtzrrPjsxxx.getJjnl() == null ? "" : sysDictService.queryDictTextByKey("jjnl", qtzrrPjsxxx.getJjnl()));
				qtzrrPjsxxx.setZcfzl(qtzrrPjsxxx.getZcfzl() == null ? "" : sysDictService.queryDictTextByKey("qtzrr_zcfzl", qtzrrPjsxxx.getZcfzl()));
				qtzrrPjsxxx.setZyzjzb(qtzrrPjsxxx.getZyzjzb() == null ? "" : sysDictService.queryDictTextByKey("qtzrr_zyzjzb", qtzrrPjsxxx.getZyzjzb()));
				qtzrrPjsxxx.setNcsr(qtzrrPjsxxx.getNcsr() == null ? "" : sysDictService.queryDictTextByKey("qtzrr_ncsr", qtzrrPjsxxx.getNcsr()));
				qtzrrPjsxxx.setYxyswlsj(qtzrrPjsxxx.getYxyswlsj() == null ? "" : sysDictService.queryDictTextByKey("qtzrr_yxyswlsj", qtzrrPjsxxx.getYxyswlsj()));
				qtzrrPjsxxx.setYxyshzqk(qtzrrPjsxxx.getYxyshzqk() == null ? "" : sysDictService.queryDictTextByKey("qtzrr_yxyshzqk", qtzrrPjsxxx.getYxyshzqk()));
				qtzrrPjsxxx.setRsbx(qtzrrPjsxxx.getRsbx() == null ? "" : sysDictService.queryDictTextByKey("ywbz", qtzrrPjsxxx.getRsbx()));
				qtzrrPjsxxx.setCcbx(qtzrrPjsxxx.getCcbx() == null ? "" : sysDictService.queryDictTextByKey("ywbz", qtzrrPjsxxx.getCcbx()));
			}
			map.put("pjxx", qtzrrPjsxxxList);


			//资产情况
			JSONArray jsonArray2 = new JSONArray();
			for (QtzrrPjsxxx pjsxxx : qtzrrPjsxxxList) {
				JSONObject jo = new JSONObject();
				jo.put("zclx","地产");
				jo.put("zcsl",pjsxxx.getDcsl());
				jo.put("zcjg",pjsxxx.getDcjz());
				jo.put("zcsm",pjsxxx.getDcxqsm());
				JSONObject jo1 = new JSONObject();
				jo1.put("zclx","交通工具");
				jo1.put("zcsl",pjsxxx.getJtgjsl());
				jo1.put("zcjg",pjsxxx.getJtgjjz());
				jo1.put("zcsm",pjsxxx.getJtgjxqsm());
				JSONObject jo2 = new JSONObject();
				jo2.put("zclx","存款");
				jo2.put("zcsl",pjsxxx.getCksl());
				jo2.put("zcjg",pjsxxx.getCkjz());
				jo2.put("zcsm",pjsxxx.getCkxqsm());
				JSONObject jo3 = new JSONObject();
				jo3.put("zclx","有价单证");
				jo3.put("zcsl",pjsxxx.getYjdzsl());
				jo3.put("zcjg",pjsxxx.getYjdzjz());
				jo3.put("zcsm",pjsxxx.getYjdzxqsm());
				JSONObject jo4 = new JSONObject();
				jo4.put("zclx","股权");
				jo4.put("zcsl",pjsxxx.getGqsl());
				jo4.put("zcjg",pjsxxx.getGqjz());
				jo4.put("zcsm",pjsxxx.getGqxqsm());
				JSONObject jo5 = new JSONObject();
				jo5.put("zclx","其他资产");
				jo5.put("zcsl",pjsxxx.getJtgjsl());
				jo5.put("zcjg",pjsxxx.getJtgjjz());
				jo5.put("zcsm",pjsxxx.getJtgjxqsm());
				jsonArray2.add(jo);
				jsonArray2.add(jo1);
				jsonArray2.add(jo2);
				jsonArray2.add(jo3);
				jsonArray2.add(jo4);
				jsonArray2.add(jo5);
			}
			map.put("zcqk", jsonArray2);

			//负债情况
			JSONArray jsonArray3 = new JSONArray();
			for (QtzrrPjsxxx pjsxxx : qtzrrPjsxxxList) {
				JSONObject jo1 = new JSONObject();
				jo1.put("jkfs","本系统");
				jo1.put("zqr",pjsxxx.getBxtjkzqr());
				jo1.put("jkje",pjsxxx.getBxtjksl());
				jo1.put("jksm",pjsxxx.getBxtjkxqsm());
				JSONObject jo2 = new JSONObject();
				jo2.put("jkfs","他行");
				jo2.put("zqr",pjsxxx.getThjkzqr());
				jo2.put("jkje",pjsxxx.getThjksl());
				jo2.put("jksm",pjsxxx.getThjkxqsm());
				JSONObject jo3 = new JSONObject();
				jo3.put("jkfs","信用卡");
				jo3.put("zqr",pjsxxx.getXykzqr());
				jo3.put("jkje",pjsxxx.getXyksl());
				jo3.put("jksm",pjsxxx.getXykxqsm());
				JSONObject jo4 = new JSONObject();
				jo4.put("jkfs","其他");
				jo4.put("zqr",pjsxxx.getQtfzzqr());
				jo4.put("jkje",pjsxxx.getQtfzsl());
				jo4.put("jksm",pjsxxx.getQtfzxqsm());
				JSONObject jo5 = new JSONObject();
				jo5.put("jkfs", "家庭年开支");
				jo5.put("zqr", pjsxxx.getJtnkzzqr());
				jo5.put("jkje", pjsxxx.getJtnkzsl());
				jo5.put("jksm", pjsxxx.getJtnkzxqsm());
				jsonArray3.add(jo1);
				jsonArray3.add(jo2);
				jsonArray3.add(jo3);
				jsonArray3.add(jo4);
				jsonArray3.add(jo5);
			}
			map.put("fzqk", jsonArray3);
		}

		String port = environment.getProperty("common.path.export");
		//导出文件名称
		mv.addObject(JxlsConstants.FILE_NAME, "其他自然人详细信息表）");
		mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("其他自然人详细信息表.xls"));
		mv.addObject(JxlsConstants.SAVE_FILE_NAME, port + "/其他自然人详细信息表.xls");
		mv.addObject(JxlsConstants.MAP_DATA, map);
		return mv;

	}

	/**
	 * 其他自然人采集信息查看
	 * @param zjhm
	 * @return
	 */
	@RequestMapping(value = "/qtzrrcjxx", method = RequestMethod.GET)
	public Result<?> qtzrrcjxx(@RequestParam(name = "zjhm",required = true) String zjhm) {
		JSONObject jsonObject = new JSONObject();
		CamsZcsxQtzrrcjxx qtzrrcjxx = new CamsZcsxQtzrrcjxx();
		qtzrrcjxx.setZjhm(zjhm);
		try {
			Map<String,String[]> map = new HashMap<>();
			QueryWrapper<CamsZcsxQtzrrcjxx> queryWrapper = QueryGenerator.initQueryWrapper(qtzrrcjxx,map);
			List<CamsZcsxQtzrrcjxx> qtzrrcjxxList = camsZcsxQtzrrcjxxService.list(queryWrapper);
			if (qtzrrcjxxList.size()!=0) {
				jsonObject.put("qtzrrcjxxList", qtzrrcjxxList.get(0));
				jsonObject.put("zkhjl_dictText",qtzrrcjxxList.get(0).getSskhjl()==null ? " " : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",qtzrrcjxxList.get(0).getSskhjl()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return	Result.ok(jsonObject);
	}

	/**
	 * 导出其他自然人采集信息模板excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/khxxcjexportXls")
	public ModelAndView khxxcjexportXls(KhglQtzrrhmcxx qtzrrhmcxx, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		//AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new TemplateExcelView());
		JSONArray jsonArray = new JSONArray();
		Map<String,String[]> map = new HashMap<>();
		Map<String,Object> map1 = new HashMap<>();
		QueryWrapper<KhglQtzrrhmcxx> queryWrapper =  QueryGenerator.initQueryWrapper(qtzrrhmcxx,map);
		List<KhglQtzrrhmcxx> khglQtzrrhmcxxList  = khglQtzrrhmcxxService.list(queryWrapper);
		 for (KhglQtzrrhmcxx khglQtzrrhmcxx : khglQtzrrhmcxxList) {
		 	if ("2".equals(khglQtzrrhmcxx.getSfhz())){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("xm",khglQtzrrhmcxx.getKhmc());
				jsonObject.put("yhzgx",khglQtzrrhmcxx.getYhzgx()== null ? "" : sysDictService.queryDictTextByKey("yhzgx", khglQtzrrhmcxx.getYhzgx()));
				jsonObject.put("zjhm",khglQtzrrhmcxx.getZjhm());
				jsonObject.put("nl",khglQtzrrhmcxx.getNl());
				CamsZcsxQtzrrcjxx camsZcsxQtzrrcjxx = new CamsZcsxQtzrrcjxx();
				camsZcsxQtzrrcjxx.setZjhm(khglQtzrrhmcxx.getZjhm());
				QueryWrapper<CamsZcsxQtzrrcjxx> queryWrapper1 = QueryGenerator.initQueryWrapper(camsZcsxQtzrrcjxx,map);
				List<CamsZcsxQtzrrcjxx> camsZcsxQtzrrcjxxList = camsZcsxQtzrrcjxxService.list(queryWrapper1);
				if (camsZcsxQtzrrcjxxList.size() != 0 ){
					jsonObject.put("whcd",camsZcsxQtzrrcjxxList.get(0).getZgxl()== null ? "" : sysDictService.queryDictTextByKey("whcd", camsZcsxQtzrrcjxxList.get(0).getZgxl()));
					jsonObject.put("ldnlzk",camsZcsxQtzrrcjxxList.get(0).getLdnl()== null ? "" : sysDictService.queryDictTextByKey("ldnl", camsZcsxQtzrrcjxxList.get(0).getLdnl()));
				}
				jsonArray.add(jsonObject);

			}else {
		 		//查询户主信息及家庭资产/经营项目
				map1.put("hzxm",khglQtzrrhmcxx.getKhmc());
				map1.put("xb",khglQtzrrhmcxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", khglQtzrrhmcxx.getXb()));
				map1.put("zjhm",khglQtzrrhmcxx.getZjhm());
				map1.put("nl",khglQtzrrhmcxx.getNl());
				map1.put("jtrk",khglQtzrrhmcxxList.size());
				map1.put("lxdh",khglQtzrrhmcxx.getLxfs());
				QtzrrPjsxxx qtzrrPjsxxx = new QtzrrPjsxxx();
				qtzrrPjsxxx.setZjhm(khglQtzrrhmcxx.getZjhm());
				QueryWrapper queryWrapper1  =  QueryGenerator.initQueryWrapper(qtzrrPjsxxx,map);
				List<QtzrrPjsxxx> qtzrrPjsxxxList = qtzrrPjsxxxService.list(queryWrapper1);
				if (qtzrrPjsxxxList.size()!= 0){
					//用StringBuffer拼接资产情况 判断资产情况字段不为空的则拼接对应的经营项目
					StringBuffer stringBuffer1= new StringBuffer();
					stringBuffer1.append(qtzrrPjsxxxList.get(0).getDcjz()==null ? " " : "地产价值:" + qtzrrPjsxxxList.get(0).getDcjz()+"、"  );
					stringBuffer1.append(qtzrrPjsxxxList.get(0).getJtgjjz()==null ? " " : "交通工具价值:" + qtzrrPjsxxxList.get(0).getJtgjjz()+"、"  );
					stringBuffer1.append(qtzrrPjsxxxList.get(0).getCkjz()==null ? " " : "存款价值:" + qtzrrPjsxxxList.get(0).getCkjz()+"、"  );
					stringBuffer1.append(qtzrrPjsxxxList.get(0).getYjdzjz()==null ? " " : "有价单证价值:" + qtzrrPjsxxxList.get(0).getYjdzjz()+"、"  );
					stringBuffer1.append(qtzrrPjsxxxList.get(0).getGqjz()==null ? " " : "股权价值:" + qtzrrPjsxxxList.get(0).getGqjz()+ "、" );
					stringBuffer1.append(qtzrrPjsxxxList.get(0).getQtzcjz()==null ? " " : "其他资产价值:" + qtzrrPjsxxxList.get(0).getQtzcjz()+"、"  );
					map1.put("jtzczk",stringBuffer1.substring(0,stringBuffer1.length()-1));

					map1.put("mjjd",0);
					//查询银行贷款
					int bxtjkje = Integer.parseInt(qtzrrPjsxxxList.get(0).getBxtjksl()== null ? "0" : qtzrrPjsxxxList.get(0).getBxtjksl());
					int thjkje = Integer.parseInt(qtzrrPjsxxxList.get(0).getThjksl()== null ? "0" : qtzrrPjsxxxList.get(0).getThjksl());
					int xykjkje = Integer.parseInt(qtzrrPjsxxxList.get(0).getXyksl()== null ? "0" : qtzrrPjsxxxList.get(0).getXyksl());
					int yhdk = bxtjkje + thjkje + xykjkje;
					map1.put("yhdk",yhdk);

					int qtfzsl = Integer.parseInt(qtzrrPjsxxxList.get(0).getQtfzsl()== null ? "0" : qtzrrPjsxxxList.get(0).getQtfzsl());
					map1.put("qtjrjgjk",qtfzsl);

					//查询合计
					int hj = yhdk + qtfzsl;
					map1.put("hj",hj);
					//查询被他调查人签名
					CamsZcsxQtzrrcjxx camsZcsxQtzrrcjxx = new CamsZcsxQtzrrcjxx();
					camsZcsxQtzrrcjxx.setZjhm(khglQtzrrhmcxx.getZjhm());
					QueryWrapper<CamsZcsxQtzrrcjxx> queryWrapper2 = QueryGenerator.initQueryWrapper(camsZcsxQtzrrcjxx,map);
					List<CamsZcsxQtzrrcjxx> list = camsZcsxQtzrrcjxxService.list(queryWrapper2);
					map1.put("bdcrqm",list.get(0).getSign1());

				}
			}
		 }
		 	map1.put("qydm",iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101002"));
			map1.put("list", jsonArray);
			String port = environment.getProperty("common.path.export");
			//导出文件名称
			mv.addObject(JxlsConstants.FILE_NAME, "其他自然人信息采集表");
			mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("其他自然人信息采集表.xls"));
			mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/其他自然人信息采集表.xls");
			mv.addObject(JxlsConstants.MAP_DATA, map1);
			return mv;
	}

	/**
	 * 其他自然人评议授信信息打印
	 * @param hhbm
	 * @param zjhm
	 * @return
	 */
	@RequestMapping(value = "/qtzrrpysxxx", method = RequestMethod.GET)
	public Result<?> qtzrrpysxxx(@RequestParam(name = "hhbm",required = true) String hhbm,
							 @RequestParam(name = "zjhm",required = true) String zjhm) {

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject1 = new JSONObject();
		Map<String,String[]> map = new HashMap<>();

		try {
			//查询家庭成员信息
			KhglQtzrrhmcxx khglQtzrrhmcxxQuery = new KhglQtzrrhmcxx();
			khglQtzrrhmcxxQuery.setHhbm(hhbm);
			QueryWrapper<KhglQtzrrhmcxx> queryWrapper =  QueryGenerator.initQueryWrapper(khglQtzrrhmcxxQuery,map);
			queryWrapper.orderByAsc("yhzgx");
			List<KhglQtzrrhmcxx> khglQtzrrhmcxxList  = khglQtzrrhmcxxService.list(queryWrapper);
			for (KhglQtzrrhmcxx khglQtzrrhmcxx : khglQtzrrhmcxxList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("yhzgx", khglQtzrrhmcxx.getYhzgx() == null ? "" : sysDictService.queryDictTextByKey("yhzgx", khglQtzrrhmcxx.getYhzgx()));
				jsonObject.put("khmc", khglQtzrrhmcxx.getKhmc());
				jsonObject.put("xb", khglQtzrrhmcxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", khglQtzrrhmcxx.getXb()));
				jsonObject.put("zjhm", khglQtzrrhmcxx.getZjhm());
				jsonObject.put("lxfs", khglQtzrrhmcxx.getLxfs());
				if("1".equals(khglQtzrrhmcxx.getYhzgx())) {
					jsonObject1.put("zzjc", sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khglQtzrrhmcxx.getSszh()));
				}

				CamsZcsxQtzrrcjxx qtzrrcjxxQuery = new CamsZcsxQtzrrcjxx();
				qtzrrcjxxQuery.setZjhm(khglQtzrrhmcxx.getZjhm());
				QueryWrapper<CamsZcsxQtzrrcjxx> qtzrrcjxxQueryWrapper = QueryGenerator.initQueryWrapper(qtzrrcjxxQuery, map);
				List<CamsZcsxQtzrrcjxx> camsZcsxQtzrrcjxxList = camsZcsxQtzrrcjxxService.list(qtzrrcjxxQueryWrapper);
				if (camsZcsxQtzrrcjxxList.size() != 0) {
					CamsZcsxQtzrrcjxx qtzrrcjxx = camsZcsxQtzrrcjxxList.get(0);
					jsonObject.put("zy", qtzrrcjxx.getCshygz());
					jsonObject.put("gzdw", qtzrrcjxx.getGzdw());
					jsonObject.put("jkzk", qtzrrcjxx.getJkzk() == null ? "" : sysDictService.queryDictTextByKey("jkzk", qtzrrcjxx.getJkzk()));
					jsonObject.put("ldnl", qtzrrcjxx.getLdnl() == null ? "" : sysDictService.queryDictTextByKey("ldnl", qtzrrcjxx.getLdnl()));
				}
				jsonArray.add(jsonObject);
			}
			//与我行相关业务
			JSONObject ywhywwlxxObject = new JSONObject();
			Ywhywwlxx ywhywwlxxQuery = new Ywhywwlxx();
			ywhywwlxxQuery.setZjhm(zjhm);
			QueryWrapper<Ywhywwlxx> ywhywwlxxQueryWrapper = QueryGenerator.initQueryWrapper(ywhywwlxxQuery, map);
			List<Ywhywwlxx> ywhywwlxxList = ywhywwlxxService.list(ywhywwlxxQueryWrapper);
			if (ywhywwlxxList.size() != 0) {
				Ywhywwlxx ywhywwlxx = ywhywwlxxList.get(0);
				ywhywwlxxObject.put("ckye", ywhywwlxx.getCkye());
				ywhywwlxxObject.put("ckrpye", ywhywwlxx.getCknrpye());
				ywhywwlxxObject.put("dkye", ywhywwlxx.getDkye());
				ywhywwlxxObject.put("bldkye", ywhywwlxx.getBldkye());
				ywhywwlxxObject.put("sjyh", ywhywwlxx.getSfktsjyhyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktsjyhyw()));
				ywhywwlxxObject.put("etc", ywhywwlxx.getSfbletcyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfbletcyw()));
				ywhywwlxxObject.put("sbk", ywhywwlxx.getSfktsbk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktsbk()));
				ywhywwlxxObject.put("ezf", ywhywwlxx.getSfblezf() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfblezf()));
				ywhywwlxxObject.put("xyk", ywhywwlxx.getSfktxyk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktxyk()));
				ywhywwlxxObject.put("bmk", ywhywwlxx.getSfktfmk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktfmk()));
			}
			jsonObject1.put("ywhywwlxx", ywhywwlxxObject);

			//查询授信信息
			JSONObject pjsxxxJSON = new JSONObject();
			QtzrrPjsxxx qtzrrPjsxxxQuery = new QtzrrPjsxxx();
			qtzrrPjsxxxQuery.setHhbm(hhbm);
			QueryWrapper qtzrrPjsxxxQueryWrapper = QueryGenerator.initQueryWrapper(qtzrrPjsxxxQuery, map);
			qtzrrPjsxxxQueryWrapper.orderByAsc("lrsj");
			List<QtzrrPjsxxx> qtzrrPjsxxxList = qtzrrPjsxxxService.list(qtzrrPjsxxxQueryWrapper);
			BigDecimal dcjz = new BigDecimal(0); //地产价值
			BigDecimal jtgjjz = new BigDecimal(0); //交通工具价值
			BigDecimal ckjz = new BigDecimal(0); //存款价值
			BigDecimal yjdzjz = new BigDecimal(0); // 有价单证价值
			BigDecimal gqjz = new BigDecimal(0); //股权价值
			BigDecimal qtzcjz = new BigDecimal(0); //其他资产价值

			BigDecimal bxtjkje = new BigDecimal(0);
			BigDecimal thjkje = new BigDecimal(0);
			BigDecimal xykjkje = new BigDecimal(0);
			BigDecimal qtjkje = new BigDecimal(0);
			BigDecimal jtnkzje = new BigDecimal(0);



			if (qtzrrPjsxxxList.size()!= 0){
				QtzrrPjsxxx qtzrrPjsxxx = qtzrrPjsxxxList.get(0);
				pjsxxxJSON = JSON.parseObject(JSON.toJSONString(qtzrrPjsxxx));
				pjsxxxJSON.put("shpj", qtzrrPjsxxx.getShpj() == null ? "" : sysDictService.queryDictTextByKey("qtzrr_shpj", qtzrrPjsxxx.getShpj()));
				dcjz = stringToBigDecimal(qtzrrPjsxxx.getDcjz());
				jtgjjz = stringToBigDecimal(qtzrrPjsxxx.getJtgjjz());
				ckjz = stringToBigDecimal(qtzrrPjsxxx.getCkjz());
				yjdzjz = stringToBigDecimal(qtzrrPjsxxx.getYjdzjz());
				gqjz = stringToBigDecimal(qtzrrPjsxxx.getGqjz());
				qtzcjz = stringToBigDecimal(qtzrrPjsxxx.getQtzcjz());

				bxtjkje = stringToBigDecimal(qtzrrPjsxxx.getBxtjksl());
				thjkje = stringToBigDecimal(qtzrrPjsxxx.getThjksl());
				xykjkje = stringToBigDecimal(qtzrrPjsxxx.getXyksl());
				qtjkje = stringToBigDecimal(qtzrrPjsxxx.getQtfzsl());
				jtnkzje = stringToBigDecimal(qtzrrPjsxxx.getJtnkzsl());
			}
			//查询房产信息
			Qtzrrfcxx qtzrrfcxxQuery = new Qtzrrfcxx();
			qtzrrfcxxQuery.setHhbm(hhbm);
			QueryWrapper qtzrrfcxxQueryWrapper = QueryGenerator.initQueryWrapper(qtzrrfcxxQuery, map);
			List<Qtzrrfcxx> qtzrrfcxxList = qtzrrfcxxService.list(qtzrrfcxxQueryWrapper);
			BigDecimal fcjzSum = new BigDecimal(0);
			if (qtzrrfcxxList.size() != 0) {
				pjsxxxJSON.put("fcsl", qtzrrfcxxList.size());
				for (Qtzrrfcxx qtzrrfcxx : qtzrrfcxxList) {
					BigDecimal fcjz = qtzrrfcxx.getFcjz();
					if (fcjz != null) {
						fcjzSum = fcjzSum.add(fcjz);
					}
				}
				pjsxxxJSON.put("fcjz", fcjzSum);
			}
			BigDecimal zcjzSum = new BigDecimal(0);
			BigDecimal fzSum = new BigDecimal(0);

			zcjzSum = dcjz.add(gqjz).add(jtgjjz).add(ckjz).add(yjdzjz).add(qtzcjz).add(fcjzSum);
			fzSum = bxtjkje.add(thjkje).add(xykjkje).add(qtjkje).add(jtnkzje);

			pjsxxxJSON.put("zcjzSum", zcjzSum);
			pjsxxxJSON.put("fzSum", fzSum);

			jsonObject1.put("jtcyList",jsonArray);
			jsonObject1.put("qtzrrPjsxxx", pjsxxxJSON);
			jsonObject1.put("nd", DateUtil.format(new Date(), "yyyy"));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询客户评议授信信息失败！", e);
			return Result.error("查询客户评议授信信息失败！");
		}
		return	Result.ok(jsonObject1);
	}

	private BigDecimal stringToBigDecimal(String num) {
		if (StringUtils.isEmpty(num)) {
			return new BigDecimal(0);
		}
		try {
			return new BigDecimal(num);
		} catch (Throwable tx) {
			tx.printStackTrace();
			return new BigDecimal(0);
		}
	}

	/**
	 * 其他自然人信息采集打印
	 * @param hhbm
	 * @param zjhm
	 * @return
	 */
	@RequestMapping(value = "/qtzrrxxcjb", method = RequestMethod.GET)
	public Result<?> qtzrrxxcjb(@RequestParam(name = "hhbm",required = true) String hhbm,
							 @RequestParam(name = "zjhm",required = true) String zjhm) {

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject1 = new JSONObject();
		Map<String,String[]> map = new HashMap<>();

		try {
			JSONObject jsonObject2 = new JSONObject();
		  /*QueryWrapper<KhglQtzrrhmcxx> queryWrapper2 = QueryGenerator.initQueryWrapper(khglKhhmcxx,map);
		  List<KhglQtzrrhmcxx>  khhmcxxList =khglKhhmcxxService.list(queryWrapper2);
		  for (KhglQtzrrhmcxx khhmcxx : khhmcxxList) {
				 jsonObject2.put("hzmc",khhmcxx.getKhmc());
			}*/

			//查询家庭成员信息
			KhglQtzrrhmcxx qtzrrhmcxxQuery = new KhglQtzrrhmcxx();
			qtzrrhmcxxQuery.setHhbm(hhbm);
			//khhmcxx.setSfhz("2");
			QueryWrapper<KhglQtzrrhmcxx> queryWrapper =  QueryGenerator.initQueryWrapper(qtzrrhmcxxQuery,map);
			List<KhglQtzrrhmcxx> khglQtzrrhmcxxList  = khglQtzrrhmcxxService.list(queryWrapper);
			for (KhglQtzrrhmcxx khglQtzrrhmcxx : khglQtzrrhmcxxList) {
				if (khglQtzrrhmcxx.getSfhz()!= null && khglQtzrrhmcxx.getSfhz().equals("2")){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("xm",khglQtzrrhmcxx.getKhmc());
					jsonObject.put("yhzgx",khglQtzrrhmcxx.getYhzgx()== null ? "" : sysDictService.queryDictTextByKey("yhzgx", khglQtzrrhmcxx.getYhzgx()));
					jsonObject.put("zjhm",khglQtzrrhmcxx.getZjhm());
					jsonObject.put("nl",khglQtzrrhmcxx.getNl());
					CamsZcsxQtzrrcjxx camsZcsxQtzrrcjxx = new CamsZcsxQtzrrcjxx();
					camsZcsxQtzrrcjxx.setZjhm(khglQtzrrhmcxx.getZjhm());
					QueryWrapper<CamsZcsxQtzrrcjxx> queryWrapper1 = QueryGenerator.initQueryWrapper(camsZcsxQtzrrcjxx,map);
					List<CamsZcsxQtzrrcjxx> camsZcsxQtzrrcjxxList = camsZcsxQtzrrcjxxService.list(queryWrapper1);
					if (camsZcsxQtzrrcjxxList.size() != 0 ){
						jsonObject.put("whcd",camsZcsxQtzrrcjxxList.get(0).getZgxl()== null ? "" : sysDictService.queryDictTextByKey("whcd", camsZcsxQtzrrcjxxList.get(0).getZgxl()));
						jsonObject.put("ldnlzk",camsZcsxQtzrrcjxxList.get(0).getLdnl()== null ? "" : sysDictService.queryDictTextByKey("ldnl", camsZcsxQtzrrcjxxList.get(0).getLdnl()));
					}
					jsonArray.add(jsonObject);

				}else {
					//查询户主信息及家庭资产/经营项目
					jsonObject2.put("hzxm",khglQtzrrhmcxx.getKhmc());
					jsonObject2.put("xb",khglQtzrrhmcxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", khglQtzrrhmcxx.getXb()));
					jsonObject2.put("zjhm",khglQtzrrhmcxx.getZjhm());
					jsonObject2.put("nl",khglQtzrrhmcxx.getNl());
					jsonObject2.put("jtrk",khglQtzrrhmcxxList.size());
					jsonObject2.put("lxdh",khglQtzrrhmcxx.getLxfs());
					QtzrrPjsxxx qtzrrPjsxxxQuery = new QtzrrPjsxxx();
					qtzrrPjsxxxQuery.setZjhm(khglQtzrrhmcxx.getZjhm());
					QueryWrapper queryWrapper1  =  QueryGenerator.initQueryWrapper(qtzrrPjsxxxQuery,map);
					List<QtzrrPjsxxx> qtzrrPjsxxxList = qtzrrPjsxxxService.list(queryWrapper1);
					if (qtzrrPjsxxxList.size()!= 0){
						//用StringBuffer拼接资产情况 判断资产情况字段不为空的则拼接对应的经营项目
						StringBuffer stringBuffer1= new StringBuffer();
						stringBuffer1.append(qtzrrPjsxxxList.get(0).getDcjz()==null ? " " : "地产价值:" + qtzrrPjsxxxList.get(0).getDcjz()+ "、" );
						stringBuffer1.append(qtzrrPjsxxxList.get(0).getJtgjjz()==null ? " " : "交通工具价值:" + qtzrrPjsxxxList.get(0).getJtgjjz()+"、"  );
						stringBuffer1.append(qtzrrPjsxxxList.get(0).getCkjz()==null ? " " : "存款价值:" + qtzrrPjsxxxList.get(0).getCkjz()+"、"  );
						stringBuffer1.append(qtzrrPjsxxxList.get(0).getYjdzjz()==null ? " " : "有价单证价值:" + qtzrrPjsxxxList.get(0).getYjdzjz()+"、"  );
						stringBuffer1.append(qtzrrPjsxxxList.get(0).getGqjz()==null ? " " : "股权价值:" + qtzrrPjsxxxList.get(0).getGqjz()+ "、" );
						stringBuffer1.append(qtzrrPjsxxxList.get(0).getQtzcjz()==null ? " " : "其他资产价值:" + qtzrrPjsxxxList.get(0).getQtzcjz()+"、"  );
						jsonObject2.put("jtzczk",stringBuffer1.substring(0,stringBuffer1.length()-1));

						jsonObject2.put("mjjd",0);
						//查询银行贷款
						int bxtjkje = Integer.parseInt(qtzrrPjsxxxList.get(0).getBxtjksl()== null ? "0" : qtzrrPjsxxxList.get(0).getBxtjksl());
						int thjkje = Integer.parseInt(qtzrrPjsxxxList.get(0).getThjksl()== null ? "0" : qtzrrPjsxxxList.get(0).getThjksl());
						int xykjkje = Integer.parseInt(qtzrrPjsxxxList.get(0).getXyksl()== null ? "0" : qtzrrPjsxxxList.get(0).getXyksl());
						int yhdk = bxtjkje + thjkje + xykjkje;
						jsonObject2.put("yhdk",yhdk);

						int qtfzsl = Integer.parseInt(qtzrrPjsxxxList.get(0).getQtfzsl()== null ? "0" : qtzrrPjsxxxList.get(0).getQtfzsl());
						int jtnkzsl = Integer.parseInt(qtzrrPjsxxxList.get(0).getJtnkzsl()== null ? "0" : qtzrrPjsxxxList.get(0).getJtnkzsl());
						jsonObject2.put("qtjrjgjk",qtfzsl+jtnkzsl);

						//查询合计
						int hj = yhdk + qtfzsl + jtnkzsl;
						jsonObject2.put("hj",hj);

						//查询被他调查人签名
						CamsZcsxQtzrrcjxx camsZcsxQtzrrcjxx = new CamsZcsxQtzrrcjxx();
						camsZcsxQtzrrcjxx.setZjhm(khglQtzrrhmcxx.getZjhm());
						jsonObject2.put("bdcrqm","");
					}
					jsonObject2.put("qydm",iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101002"));
				}
			}

			jsonObject1.put("jsonArray",jsonArray);
			jsonObject1.put("jcxx",jsonObject2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return	Result.ok(jsonObject1);
	}

	/**
	 * 导出其他自然人评议授信信息模板excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/qtzrrpysxxxExportXls")
	public ModelAndView qtzrrpysxxxExportXls(@RequestParam(name = "hhbm",required = true) String hhbm,
										  @RequestParam(name = "ssxz",required = true) String ssxz,
										  @RequestParam(name = "xzc",required = true) String xzc,
										  @RequestParam(name = "xzz",required = false) String xzz,
										  HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView(new TemplateExcelView());

		Map<String,String[]> map = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		Map<String, Object> qtzrrxxMap = new HashMap<>();
		//查询家庭成员信息
		KhglQtzrrhmcxx qtzrrhmcxxQuery = new KhglQtzrrhmcxx();
		qtzrrhmcxxQuery.setHhbm(hhbm);
		QueryWrapper<KhglQtzrrhmcxx> queryWrapper =  QueryGenerator.initQueryWrapper(qtzrrhmcxxQuery,map);
		queryWrapper.orderByAsc("yhzgx");
		List<KhglQtzrrhmcxx> khglQtzrrhmcxxList  = khglQtzrrhmcxxService.list(queryWrapper);
		JSONArray jtcyArray = new JSONArray();
		String zjhm = "";
		for (KhglQtzrrhmcxx khglQtzrrhmcxx : khglQtzrrhmcxxList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("yhzgx", khglQtzrrhmcxx.getYhzgx() == null ? "" : sysDictService.queryDictTextByKey("yhzgx", khglQtzrrhmcxx.getYhzgx()));
			jsonObject.put("khmc", khglQtzrrhmcxx.getKhmc());
			jsonObject.put("xb", khglQtzrrhmcxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", khglQtzrrhmcxx.getXb()));
			jsonObject.put("zjhm", khglQtzrrhmcxx.getZjhm());
			jsonObject.put("lxfs", khglQtzrrhmcxx.getLxfs());
			if("1".equals(khglQtzrrhmcxx.getYhzgx())) {
				dataMap.put("zzjc", sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khglQtzrrhmcxx.getSszh()));
				zjhm = khglQtzrrhmcxx.getZjhm();
			}

			CamsZcsxQtzrrcjxx qtzrrcjxxQuery = new CamsZcsxQtzrrcjxx();
			qtzrrcjxxQuery.setZjhm(khglQtzrrhmcxx.getZjhm());
			QueryWrapper<CamsZcsxQtzrrcjxx> qtzrrcjxxQueryWrapper = QueryGenerator.initQueryWrapper(qtzrrcjxxQuery, map);
			List<CamsZcsxQtzrrcjxx> camsZcsxQtzrrcjxxList = camsZcsxQtzrrcjxxService.list(qtzrrcjxxQueryWrapper);
			if (camsZcsxQtzrrcjxxList.size() != 0) {
				CamsZcsxQtzrrcjxx qtzrrcjxx = camsZcsxQtzrrcjxxList.get(0);
				jsonObject.put("zy", qtzrrcjxx.getCshygz());
				jsonObject.put("gzdw", qtzrrcjxx.getGzdw());
				jsonObject.put("jkzk", qtzrrcjxx.getJkzk() == null ? "" : sysDictService.queryDictTextByKey("jkzk", qtzrrcjxx.getJkzk()));
				jsonObject.put("ldnl", qtzrrcjxx.getLdnl() == null ? "" : sysDictService.queryDictTextByKey("ldnl", qtzrrcjxx.getLdnl()));
			}
			jtcyArray.add(jsonObject);
		}
		dataMap.put("jtcyList", jtcyArray);
		//与我行相关业务
		Ywhywwlxx ywhywwlxxQuery = new Ywhywwlxx();
		ywhywwlxxQuery.setZjhm(zjhm);
		QueryWrapper<Ywhywwlxx> ywhywwlxxQueryWrapper = QueryGenerator.initQueryWrapper(ywhywwlxxQuery, map);
		List<Ywhywwlxx> ywhywwlxxList = ywhywwlxxService.list(ywhywwlxxQueryWrapper);
		if (ywhywwlxxList.size() != 0) {
			Ywhywwlxx ywhywwlxx = ywhywwlxxList.get(0);
			qtzrrxxMap.put("ckye", ywhywwlxx.getCkye());
			qtzrrxxMap.put("ckrpye", ywhywwlxx.getCknrpye());
			qtzrrxxMap.put("dkye", ywhywwlxx.getDkye());
			qtzrrxxMap.put("bldkye", ywhywwlxx.getBldkye());
			qtzrrxxMap.put("sjyh", ywhywwlxx.getSfktsjyhyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktsjyhyw()));
			qtzrrxxMap.put("etc", ywhywwlxx.getSfbletcyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfbletcyw()));
			qtzrrxxMap.put("sbk", ywhywwlxx.getSfktsbk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktsbk()));
			qtzrrxxMap.put("ezf", ywhywwlxx.getSfblezf() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfblezf()));
			qtzrrxxMap.put("xyk", ywhywwlxx.getSfktxyk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktxyk()));
			qtzrrxxMap.put("bmk", ywhywwlxx.getSfktfmk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktfmk()));
		}
		//查询授信信息
		JSONObject pjsxxxJSON = new JSONObject();
		QtzrrPjsxxx qtzrrPjsxxxQuery = new QtzrrPjsxxx();
		qtzrrPjsxxxQuery.setHhbm(hhbm);
		QueryWrapper qtzrrPjsxxxQueryWrapper = QueryGenerator.initQueryWrapper(qtzrrPjsxxxQuery, map);
		qtzrrPjsxxxQueryWrapper.orderByAsc("lrsj");
		List<QtzrrPjsxxx> qtzrrPjsxxxList = qtzrrPjsxxxService.list(qtzrrPjsxxxQueryWrapper);
		BigDecimal dcjz = new BigDecimal(0); //地产价值
		BigDecimal jtgjjz = new BigDecimal(0); //交通工具价值
		BigDecimal ckjz = new BigDecimal(0); //存款价值
		BigDecimal yjdzjz = new BigDecimal(0); // 有价单证价值
		BigDecimal gqjz = new BigDecimal(0); //股权价值
		BigDecimal qtzcjz = new BigDecimal(0); //其他资产价值

		BigDecimal bxtjkje = new BigDecimal(0);
		BigDecimal thjkje = new BigDecimal(0);
		BigDecimal xykjkje = new BigDecimal(0);
		BigDecimal qtjkje = new BigDecimal(0);
		BigDecimal jtnkzje = new BigDecimal(0);

		if (qtzrrPjsxxxList.size()!= 0){
			QtzrrPjsxxx qtzrrPjsxxx = qtzrrPjsxxxList.get(0);
			pjsxxxJSON = JSON.parseObject(JSON.toJSONString(qtzrrPjsxxx));
			pjsxxxJSON.put("shpj", qtzrrPjsxxx.getShpj() == null ? "" : sysDictService.queryDictTextByKey("khgl_shpj", qtzrrPjsxxx.getShpj()));
			dcjz = stringToBigDecimal(qtzrrPjsxxx.getDcjz());
			jtgjjz = stringToBigDecimal(qtzrrPjsxxx.getJtgjjz());
			ckjz = stringToBigDecimal(qtzrrPjsxxx.getCkjz());
			yjdzjz = stringToBigDecimal(qtzrrPjsxxx.getYjdzjz());
			gqjz = stringToBigDecimal(qtzrrPjsxxx.getGqjz());
			qtzcjz = stringToBigDecimal(qtzrrPjsxxx.getQtzcjz());

			bxtjkje = stringToBigDecimal(qtzrrPjsxxx.getBxtjksl());
			thjkje = stringToBigDecimal(qtzrrPjsxxx.getThjksl());
			xykjkje = stringToBigDecimal(qtzrrPjsxxx.getXyksl());
			qtjkje = stringToBigDecimal(qtzrrPjsxxx.getQtfzsl());
			jtnkzje = stringToBigDecimal(qtzrrPjsxxx.getJtnkzsl());
		}
		//查询房产信息
		Qtzrrfcxx qtzrrfcxxQuery = new Qtzrrfcxx();
		qtzrrfcxxQuery.setHhbm(hhbm);
		QueryWrapper qtzrrfcxxQueryWrapper = QueryGenerator.initQueryWrapper(qtzrrfcxxQuery, map);
		List<Qtzrrfcxx> qtzrrfcxxList = qtzrrfcxxService.list(qtzrrfcxxQueryWrapper);
		BigDecimal fcjzSum = new BigDecimal(0);
		if (qtzrrfcxxList.size() != 0) {
			pjsxxxJSON.put("fcsl", qtzrrfcxxList.size());
			for (Qtzrrfcxx qtzrrfcxx : qtzrrfcxxList) {
				BigDecimal fcjz = qtzrrfcxx.getFcjz();
				if (fcjz != null) {
					fcjzSum = fcjzSum.add(fcjz);
				}
			}
			pjsxxxJSON.put("fcjz", fcjzSum);
		}
		BigDecimal zcjzSum = new BigDecimal(0);
		BigDecimal fzSum = new BigDecimal(0);

		zcjzSum = dcjz.add(gqjz).add(jtgjjz).add(ckjz).add(yjdzjz).add(qtzcjz).add(fcjzSum);
		fzSum = bxtjkje.add(thjkje).add(xykjkje).add(qtjkje).add(jtnkzje);

		pjsxxxJSON.put("zcjzSum", zcjzSum);
		pjsxxxJSON.put("fzSum", fzSum);

		//循环转换
		for (Map.Entry<String, Object> entry : pjsxxxJSON.entrySet()) {
			qtzrrxxMap.put(entry.getKey(), entry.getValue());
		}

		dataMap.put("nd", DateUtil.format(new Date(), "yyyy"));
		dataMap.put("hhbm", hhbm);
		dataMap.put("ssxz_dictText", ssxz);
		dataMap.put("xzc_dictText", xzc);
		dataMap.put("xzz_dictText", xzz);

		dataMap.put("qtzrrxxMap", qtzrrxxMap);
		String port = environment.getProperty("common.path.export");
		mv.addObject(JxlsConstants.FILE_NAME, "其他自然人授信信息表");
		mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("其他自然人授信信息表.xls"));
		mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/其他自然人授信信息表.xls");
		mv.addObject(JxlsConstants.MAP_DATA, dataMap);

		return mv;
	}

	/**
	 * 其他自然人基本信息查看
	 * @param hhbm
	 * @return
	 */
	@GetMapping(value = "/queryQtzrrpssxxx")
	public Result<?> queryQtzrrpssxxx(@RequestParam(name = "hhbm",required = true) String hhbm) {
		JSONObject jsonObject = new JSONObject();
		Map<String,String[]> map = new HashMap<>();
		try {
			//查询家庭成员信息
			KhglQtzrrhmcxx qtzrrhmcxx = new KhglQtzrrhmcxx();
			qtzrrhmcxx.setHhbm(hhbm);
			QueryWrapper<KhglQtzrrhmcxx> queryWrapper =  QueryGenerator.initQueryWrapper(qtzrrhmcxx,map);
			List<KhglQtzrrhmcxx> khglQtzrrhmcxxList  = khglQtzrrhmcxxService.list(queryWrapper);

			QtzrrPjsxxx qtzrrPjsxxx = new QtzrrPjsxxx();
			qtzrrPjsxxx.setHhbm(hhbm);
			QueryWrapper<QtzrrPjsxxx> queryWrapper1 = QueryGenerator.initQueryWrapper(qtzrrPjsxxx,map);
			jsonObject.put("jcxx",qtzrrPjsxxxService.getOne(queryWrapper1));
			QtzrrPjsxxx qtzrrPjsxxx1 = qtzrrPjsxxxService.getOne(queryWrapper1);

			//查询易变现资产
			Double jtgjjz = 0.0;
			Double gqjz = 0.0;
			Double ckjz = 0.0;
			Double yjdzjz = 0.0;
			Double dcjz = 0.0;
			if(qtzrrPjsxxx1!=null) {
				gqjz = Double.valueOf(qtzrrPjsxxx1.getGqjz() == null ? "0" : qtzrrPjsxxx1.getGqjz());
				jtgjjz = Double.valueOf(qtzrrPjsxxx1.getJtgjjz() == null ? "0" : qtzrrPjsxxx1.getJtgjjz());
				ckjz = Double.valueOf(qtzrrPjsxxx1.getCkjz() == null ? "0" : qtzrrPjsxxx1.getCkjz());
				yjdzjz = Double.valueOf(qtzrrPjsxxx1.getYjdzjz() == null ? "0" : qtzrrPjsxxx1.getYjdzjz());
				dcjz = Double.valueOf(qtzrrPjsxxx1.getDcjz() == null ? "0" : qtzrrPjsxxx1.getDcjz());
			}
			double ybxzc = gqjz + ckjz + yjdzjz;
			jsonObject.put("ybxzc", ybxzc);

			//查询不易变现资产
			Qtzrrfcxx qtzrrfcxx =  qtzrrfcxxService.selectFcjz(hhbm);
			Double fczchj = 0.0;
			if (qtzrrfcxx != null){
				fczchj = qtzrrfcxx.getFcjz() == null ? 0 : qtzrrfcxx.getFcjz().doubleValue();;
			}
			double bybxzc =fczchj + dcjz + jtgjjz;
			jsonObject.put("bybxzc", bybxzc);

			for (KhglQtzrrhmcxx khglQtzrrhmcxx : khglQtzrrhmcxxList) {
				khglQtzrrhmcxx.setXb(khglQtzrrhmcxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex",khglQtzrrhmcxx.getXb()));
				khglQtzrrhmcxx.setHyzk(khglQtzrrhmcxx.getHyzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_hyzk",khglQtzrrhmcxx.getHyzk()));
				khglQtzrrhmcxx.setYhzgx(khglQtzrrhmcxx.getYhzgx() == null ? "" : sysDictService.queryDictTextByKey("yhzgx",khglQtzrrhmcxx.getYhzgx()));
			}
			jsonObject.put("jtcyxx",khglQtzrrhmcxxList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.ok(jsonObject);
	}

	/**
	 * 农户评价授信审批表 - 总行名称获取
	 * @return
	 */
	@RequestMapping(value = "/getBankName",method = RequestMethod.GET)
	public Result<String> getBankName() {
		Result<String> result = new Result<String>();
		try {
			String bankName = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101002");;
			result.setResult(bankName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*------------------------------------------修改-----------------------------------------------*/
	/*
   通过户号编码去花名册查询家庭信息
    */
	@GetMapping(value = "/queryHzxxByHhbm")
	public Result<?> queryHzxxByHhbm(@Param("hhbm") String hhbm) {
		try {
			if (hhbm != null) {
				QueryWrapper<VKhglQtzrrhzxxgl> khhmcQueryWrapper = new QueryWrapper<>();
				khhmcQueryWrapper.eq("hhbm", hhbm);
				List<VKhglQtzrrhzxxgl> list = vKhglQtzrrhzxxglService.list(khhmcQueryWrapper);
				if (list != null && list.size() > 0) {
					return Result.ok(list.get(0));
				}
			}
		} catch (Exception e) {
			return Result.error(e.toString());
		}
		return Result.ok("没有找到数据");
	}

	/*
     查询所属营销单元
    */
	@GetMapping(value = "/queryCzxxByYxdy")
	public Result<?> queryCzxxByYxdy(@Param("ssyxdy") String ssyxdy) {
		try {
			if (ssyxdy != null) {
				String s = sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl", "yjyxdymc||dymc", "dybh", ssyxdy);
				return Result.ok(s);
			}
		} catch (Exception e) {
			return Result.error(e.toString());
		}
		return Result.ok("没有找到数据");
	}

	/*
     查询家庭成员
    */
	@GetMapping(value = "/queryJtxxByHhbm")
	public Result<?> queryJtxxByHhbm(@Param("hhbm") String hhbm) {
		try {
			QueryWrapper<KhglQtzrrhmcxx> qtzrrhmcQueryWrapper = new QueryWrapper<>();
			qtzrrhmcQueryWrapper.eq("hhbm", hhbm);
			qtzrrhmcQueryWrapper.orderByAsc("yhzgx");
			List<KhglQtzrrhmcxx> list = khglQtzrrhmcxxService.list(qtzrrhmcQueryWrapper);
			List<QtzrrJtcyxx> qtzrrJtcyxxList = new ArrayList<QtzrrJtcyxx>();
			for (int i = 0; i < list.size(); i++) {
				QtzrrJtcyxx qtzrrJtcyxx = new QtzrrJtcyxx();
				BeanUtils.copyProperties(list.get(i), qtzrrJtcyxx);
				String yhzgx = qtzrrJtcyxx.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", qtzrrJtcyxx.getYhzgx());
				qtzrrJtcyxx.setYhzgx(yhzgx);
				String xb =qtzrrJtcyxx.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", qtzrrJtcyxx.getXb());
				qtzrrJtcyxx.setXb(xb);
				qtzrrJtcyxxList.add(qtzrrJtcyxx);
				List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
				if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
					if (qtzrrJtcyxxList.size() > 0) {
						ywhywwlxxes.get(0).setId(list.get(i).getId());
						BeanUtils.copyProperties(ywhywwlxxes.get(0), qtzrrJtcyxxList.get(i));
					}
				}
			}
			if (list != null && list.size() > 0) {
				return Result.ok(qtzrrJtcyxxList);
			}
		}catch (Exception e){
			e.printStackTrace();
			return  Result.error(e.toString());
		}
		return Result.ok("没有数据");
	}


	/*
       查询评级授信信息
     */
	@GetMapping(value = "/queryPjsxxxByHhbm")
	public Result<?> queryPjsxxxByHhbm(@Param("hhbm") String hhbm) {
		try {
			if (hhbm != null) {
				CamsZcsxQtzrrpjsxxxPad cms = new CamsZcsxQtzrrpjsxxxPad();
				QueryWrapper<CamsZcsxQtzrrpjsxxxPad> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("hhbm", hhbm);
				List<CamsZcsxQtzrrpjsxxxPad> list = camsZcsxQtzrrpjsxxxPadService.list(queryWrapper);
				if (list.size() > 0) {
					return Result.ok(list.get(0));
				}
			}
		} catch (Exception e) {
			return Result.error(e.toString());
		}
		return Result.ok("没有找到数据");
	}


	/*
       查询房产信息
     */
	@GetMapping(value = "/queryFcxxByHhbm")
	public Result<?> queryFcxxByHhbm(@Param("hhbm") String hhbm) {
		try {
			if (hhbm != null) {
				QueryWrapper<CamsZcsxQtzrrfcxxPad> khhmcQueryWrapper = new QueryWrapper<>();
				khhmcQueryWrapper.eq("hhbm", hhbm);
				List<CamsZcsxQtzrrfcxxPad> list = camsZcsxQtzrrfcxxPadService.list(khhmcQueryWrapper);
				if (list.size() > 0) {
					return Result.ok(list);
				}
			}
		} catch (Exception e) {
			return Result.error(e.toString());
		}
		return Result.ok("没有找到数据");
	}



	 /*
       查询资产信息
     */
	@RequestMapping(value = "/queryPjsxZcTable", method = RequestMethod.GET)
	public Result<?> queryPjsxZcTable(@Param("hhbm") String hhbm) {
		try {
			LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
			JSONArray jsonArray = new JSONArray();
			QueryWrapper<CamsZcsxQtzrrpjsxxxPad> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hhbm", hhbm);
			List<CamsZcsxQtzrrpjsxxxPad> qtzrrPjsxxxList = camsZcsxQtzrrpjsxxxPadService.list(queryWrapper);
			if (qtzrrPjsxxxList == null || qtzrrPjsxxxList.size()<=0) {
				return Result.ok("没有数据");
			} else {
				for (CamsZcsxQtzrrpjsxxxPad pjsxxx : qtzrrPjsxxxList) {
					//资产情况
					JSONObject jo = new JSONObject();
					jo.put("zclx", "地产");
					jo.put("zcsl", pjsxxx.getDcsl());
					jo.put("zcjz", pjsxxx.getDcjz());
					jo.put("zcsm", pjsxxx.getDcxqsm());
					JSONObject jo1 = new JSONObject();
					jo1.put("zclx", "交通工具");
					jo1.put("zcsl", pjsxxx.getJtgjsl());
					jo1.put("zcjz", pjsxxx.getJtgjjz());
					jo1.put("zcsm", pjsxxx.getJtgjxqsm());
					JSONObject jo2 = new JSONObject();
					jo2.put("zclx", "存款");
					jo2.put("zcsl", pjsxxx.getCksl());
					jo2.put("zcjz", pjsxxx.getCkjz());
					jo2.put("zcsm", pjsxxx.getCkxqsm());
					JSONObject jo3 = new JSONObject();
					jo3.put("zclx", "有价单证");
					jo3.put("zcsl", pjsxxx.getYjdzsl());
					jo3.put("zcjz", pjsxxx.getYjdzjz());
					jo3.put("zcsm", pjsxxx.getYjdzxqsm());
					JSONObject jo4 = new JSONObject();
					jo4.put("zclx", "股权");
					jo4.put("zcsl", pjsxxx.getGqsl());
					jo4.put("zcjz", pjsxxx.getGqjz());
					jo4.put("zcsm", pjsxxx.getGqxqsm());
					JSONObject jo5 = new JSONObject();
					jo5.put("zclx", "其他资产");
					jo5.put("zcsl", pjsxxx.getQtzcsl());
					jo5.put("zcjz", pjsxxx.getQtzcjz());
					jo5.put("zcsm", pjsxxx.getQtzcxqsm());
					jsonArray.add(jo);
					jsonArray.add(jo1);
					jsonArray.add(jo2);
					jsonArray.add(jo3);
					jsonArray.add(jo4);
					jsonArray.add(jo5);
				}
				return Result.ok(jsonArray);
			}
		} catch (Exception e) {
			return Result.error(e.toString());
		}
	}

	@RequestMapping(value = "/queryPjsxFzTable", method = RequestMethod.GET)
	public Result<?> queryPjsxFzTable(@Param("hhbm") String hhbm) {
		try {
			JSONArray jsonArray = new JSONArray();
			QueryWrapper<CamsZcsxQtzrrpjsxxxPad> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hhbm", hhbm);
			List<CamsZcsxQtzrrpjsxxxPad> qtzrrPjsxxxList = camsZcsxQtzrrpjsxxxPadService.list(queryWrapper);
			if (qtzrrPjsxxxList == null || qtzrrPjsxxxList.size()<=0) {
				return Result.ok("没有数据");
			} else {
				for (CamsZcsxQtzrrpjsxxxPad pjsxxx : qtzrrPjsxxxList) {
					JSONObject jo1 = new JSONObject();
					jo1.put("jkfs", "本系统");
					jo1.put("zqr", pjsxxx.getBxtjkzqr());
					jo1.put("jkje", pjsxxx.getBxtjksl());
					jo1.put("jksm", pjsxxx.getBxtjkxqsm());
					JSONObject jo2 = new JSONObject();
					jo2.put("jkfs", "他行");
					jo2.put("zqr", pjsxxx.getThjkzqr());
					jo2.put("jkje", pjsxxx.getThjksl());
					jo2.put("jksm", pjsxxx.getThjkxqsm());
					JSONObject jo3 = new JSONObject();
					jo3.put("jkfs", "信用卡");
					jo3.put("zqr", pjsxxx.getXykzqr());
					jo3.put("jkje", pjsxxx.getXyksl());
					jo3.put("jksm", pjsxxx.getXykxqsm());
					JSONObject jo4 = new JSONObject();
					jo4.put("jkfs", "其他");
					jo4.put("zqr", pjsxxx.getQtfzzqr());
					jo4.put("jkje", pjsxxx.getQtfzsl());
					jo4.put("jksm", pjsxxx.getQtfzxqsm());
					JSONObject jo5 = new JSONObject();
					jo5.put("jkfs", "家庭年开支");
					jo5.put("zqr", pjsxxx.getJtnkzzqr());
					jo5.put("jkje", pjsxxx.getJtnkzsl());
					jo5.put("jksm", pjsxxx.getJtnkzxqsm());
					jsonArray.add(jo1);
					jsonArray.add(jo2);
					jsonArray.add(jo3);
					jsonArray.add(jo4);
					jsonArray.add(jo5);
				}
				return Result.ok(jsonArray);
			}
		} catch (Exception e) {
			return Result.error(e.toString());
		}
	}

	//根据证件号码查询个人信息
	@GetMapping(value = "/queryQtzrrjbxx")
	public Result<?> queryQtzrrjbxx(@Param("zjhm") String zjhm) {
		QueryWrapper<VKhglQtzrrjbxx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zjhm",zjhm);
		VKhglQtzrrjbxx vKhglQtzrrjbxx = vKhglQtzrrjbxxService.getOne(queryWrapper);
		return Result.ok(vKhglQtzrrjbxx);
	}

}
