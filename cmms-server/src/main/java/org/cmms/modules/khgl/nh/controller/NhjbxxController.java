package org.cmms.modules.khgl.nh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.Query;
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
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.khgl.nh.entity.*;
import org.cmms.modules.khgl.nh.service.*;
import org.cmms.modules.khgl.nh.vo.KhglKhcjxxPage;
import org.cmms.modules.khgl.nh.vo.KhglKhhmcxxPage;
import org.cmms.modules.khgl.sh.entity.VShxxgl;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.nhxxgl.entity.CamsZcsxNhfcxxPad;
import org.cmms.modules.pad.nhxxgl.entity.CamsZcsxNhpjsxxxPad;
import org.cmms.modules.pad.nhxxgl.entity.NhJtcyxx;
import org.cmms.modules.pad.nhxxgl.entity.vKhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.service.ICamsZcsxNhfcxxPadService;
import org.cmms.modules.pad.nhxxgl.service.ICamsZcsxNhpjsxxxPadService;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.pad.nhxxgl.service.IvKhglNhhzxxglService;
import org.cmms.modules.pad.pyxx.entity.Nhbkbpyfsxx;
import org.cmms.modules.pad.pyxx.entity.Pydjcs;
import org.cmms.modules.pad.pyxx.entity.Pyfjxx;
import org.cmms.modules.pad.pyxx.service.IPydjcsService;
import org.cmms.modules.pad.pyxx.service.IPyfjxxService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.yxdygl.czxxgl.entity.Yxdygl_czxxgl;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 农户信息管理
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/nh/nhjbxx")
public class NhjbxxController {

	@Autowired
	private ISysDictService sysDictService;
	@Autowired
	private IVKhglNhjbxxService ivKhglNhjbxxService;
	@Autowired
	private  ICamsZcsxNhcjxxService camsZcsxNhcjxxService;
	@Autowired
    private INhbkbpyService nhbkbpyService;
	@Autowired
	private INhfcxxService nhfcxxService;
	@Autowired
	private INhPjsxxxService nhPjsxxxService;
	@Autowired
	private IYwhywwlxxService ywhywwlxxService;
	@Autowired
	private IFjglService fjglService;
	@Autowired
	private IKhglNhService khglNhhzzllbService;
    @Autowired
    private IKhYgxxService iKhYgxxService;
	@Autowired
	private IYxdygl_czxxglService yxdygl_czxxglService;
	@Autowired
	private Environment environment;
	@Autowired
	private ITjfxZhbyService tjfxZhbyService;
	@Autowired
	private ISysDictService iSysDictService;

	@Autowired
	private IKhglKhhmcxxService khglKhhmcxxService;
	@Autowired
	private INhxqService nhxqService;
	@Autowired
	private IvKhglNhhzxxglService vKhglNhhzxxgl12;
	@Autowired
	private ICamsZcsxNhpjsxxxPadService iCamsZcsxNhpjsxxxPadService;
	@Autowired
	private IKhglNhhzxxglService khglNhhzxxglService;
	@Autowired
	private ICamsZcsxNhfcxxPadService camsZcsxNhfcxxPad;

	@Autowired
	private IPyfjxxService iPyfjxxService;
	@Autowired
	private IPydjcsService pydjcsService;


	/**
	  * 分页列表查询
	 * @param vKhglNhjbxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<VKhglNhjbxx>> queryPageList(VKhglNhjbxx vKhglNhjbxx,
											   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
											   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
											   HttpServletRequest req) {
		Result<IPage<VKhglNhjbxx>> result = new Result<IPage<VKhglNhjbxx>>();
		QueryWrapper<VKhglNhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglNhjbxx, req.getParameterMap());
		Page<VKhglNhjbxx> page = new Page<VKhglNhjbxx>(pageNo, pageSize);
		IPage<VKhglNhjbxx> pageList = ivKhglNhjbxxService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 *
	 * @param khglKhhmcxxPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhglKhhmcxxPage khglKhhmcxxPage) {
		KhglKhhmcxx khglKhhmcxx = new KhglKhhmcxx();
		BeanUtils.copyProperties(khglKhhmcxxPage, khglKhhmcxx);
		khglKhhmcxxService.saveMain(khglKhhmcxx,khglKhhmcxxPage.getCamsZcsxNhcjxxList(),khglKhhmcxxPage.getNhfcxxList(),khglKhhmcxxPage.getYwhxgywList(),khglKhhmcxxPage.getNhPjsxxxList(),khglKhhmcxxPage.getFjglList());
		return Result.ok("添加成功!");
	}

	/**
     * 编辑
	 * @param khglKhhmcxxPage
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhglKhhmcxxPage khglKhhmcxxPage) {
		KhglKhhmcxx khglKhhmcxx = new KhglKhhmcxx();
		BeanUtils.copyProperties(khglKhhmcxxPage, khglKhhmcxx);
		//nhjbxxService.updateMain(sjhbxx,khglKhhmcxxPage.getNhfcxxList(),khglKhhmcxxPage.getYwhxgywList(),khglKhhmcxxPage.getNhPjsxxxList(),khglKhhmcxxPage.getFjglList(),khglKhhmcxxPage.getNhbkbpyList());
		khglKhhmcxxService.updateMain(khglKhhmcxx,khglKhhmcxxPage.getCamsZcsxNhcjxxList(),khglKhhmcxxPage.getNhfcxxList(),khglKhhmcxxPage.getYwhxgywList(),khglKhhmcxxPage.getNhPjsxxxList(),khglKhhmcxxPage.getFjglList());
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
			khglKhhmcxxService.delMain(id);
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
	public Result<KhglKhhmcxx> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<KhglKhhmcxx> result = new Result<KhglKhhmcxx>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.khglKhhmcxxService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<KhglKhhmcxx> queryById(@RequestParam(name="id",required=true) String id) {
		Result<KhglKhhmcxx> result = new Result<KhglKhhmcxx>();
		KhglKhhmcxx khglKhhmcxx = khglKhhmcxxService.getById(id);
		if(khglKhhmcxx==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khglKhhmcxx);
			result.setSuccess(true);
		}
		return result;
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param nhjbxx
	 */
	/*@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, KhglKhhmcxx nhjbxx) {
		// Step.1 组装查询条件
		QueryWrapper<KhglKhhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(nhjbxx, request.getParameterMap());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		//Step.2 获取导出数据
		List<KhglKhhmcxxPage> pageList = new ArrayList<KhglKhhmcxxPage>();
		List<KhglKhhmcxx> khglKhhmcxxList = khglKhhmcxxService.list(queryWrapper);
		for (KhglKhhmcxx temp : khglKhhmcxxList) {
			KhglKhhmcxxPage vo = new KhglKhhmcxxPage();
			BeanUtils.copyProperties(temp, vo);
			List<CamsZcsxNhcjxx> camsZcsxNhcjxxList = camsZcsxNhcjxxService.selectByMainId(temp.getZjhm());
			vo.setCamsZcsxNhcjxxList(camsZcsxNhcjxxList);
			List<Nhfcxx> nhfcxxList = nhfcxxService.selectByMainId(temp.getZjhm());
			vo.setNhfcxxList(nhfcxxList);
			List<Ywhywwlxx> ywhxgywList = ywhywwlxxService.selectByMainId(temp.getZjhm());
			vo.setYwhxgywList(ywhxgywList);
			List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.selectByMainId(temp.getZjhm());
			vo.setNhPjsxxxList(nhPjsxxxList);
			List<Fjgl> fjglList = fjglService.selectByMainId(temp.getZjhm());
			vo.setFjglList(fjglList);
			List<Nhbkbpy> nhbkbpyList = nhbkbpyService.selectByMainId(temp.getZjhm());
			vo.setNhbkbpyList(nhbkbpyList);
			pageList.add(vo);
		}
		//Step.3 调用AutoPoi导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		mv.addObject(NormalExcelConstants.FILE_NAME, "农户基本信息");
		mv.addObject(NormalExcelConstants.CLASS, KhglKhhmcxxPage.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户基本信息数据", "导出人:"+sysUser.getRealname(), "农户基本信息"));
		mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		return mv;
	}*/

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param vKhglNhjbxx
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, VKhglNhjbxx vKhglNhjbxx) {
		// Step.1 组装查询条件
		vKhglNhjbxx.setSfcj(1);
		QueryWrapper<VKhglNhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglNhjbxx, request.getParameterMap());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		Map<String,String[]> map = new HashMap<>();
		//Step.2 获取导出数据
		List<KhglKhhmcxxPage> pageList = new ArrayList<KhglKhhmcxxPage>();
		List<VKhglNhjbxx> vKhglNhjbxxList = ivKhglNhjbxxService.list(queryWrapper);
		for (VKhglNhjbxx khglNhjbxx : vKhglNhjbxxList) {
			KhglKhhmcxx khglKhhmcxx = new KhglKhhmcxx();
			khglKhhmcxx.setZjhm(khglNhjbxx.getZjhm());
			QueryWrapper queryWrapper1 = QueryGenerator.initQueryWrapper(khglKhhmcxx,map);
			List<KhglKhhmcxx> khhmcxxList = khglKhhmcxxService.list(queryWrapper1);
			for (KhglKhhmcxx temp : khhmcxxList) {
				KhglKhhmcxxPage vo = new KhglKhhmcxxPage();
				BeanUtils.copyProperties(temp, vo);
				List<CamsZcsxNhcjxx> camsZcsxNhcjxxList = camsZcsxNhcjxxService.selectByMainId(temp.getZjhm());
				vo.setCamsZcsxNhcjxxList(camsZcsxNhcjxxList);
				List<Nhfcxx> nhfcxxList = nhfcxxService.selectByMainId(temp.getZjhm());
				vo.setNhfcxxList(nhfcxxList);
				List<Ywhywwlxx> ywhxgywList = ywhywwlxxService.selectByMainId(temp.getZjhm());
				vo.setYwhxgywList(ywhxgywList);
				List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.selectByMainId(temp.getZjhm());
				vo.setNhPjsxxxList(nhPjsxxxList);
				List<Fjgl> fjglList = fjglService.selectByMainId(temp.getZjhm());
				vo.setFjglList(fjglList);
			/*List<Nhbkbpy> nhbkbpyList = nhbkbpyService.selectByMainId(temp.getZjhm());
			vo.setNhbkbpyList(nhbkbpyList);*/
				pageList.add(vo);
			}

		}
		//Step.3 调用AutoPoi导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		mv.addObject(NormalExcelConstants.FILE_NAME, "农户基本信息");
		mv.addObject(NormalExcelConstants.CLASS, KhglKhhmcxxPage.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户基本信息数据", "导出人:"+sysUser.getRealname(), "农户基本信息"));
		mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		return mv;
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param vKhglNhjbxx
	 */
	@RequestMapping(value = "/exportCjxxXls")
	public ModelAndView exportCjxxXls(HttpServletRequest request, VKhglNhjbxx vKhglNhjbxx) {
		// Step.1 组装查询条件
//		vKhglNhjbxx.setSfcj(1);
		QueryWrapper<VKhglNhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglNhjbxx, request.getParameterMap());
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		Map<String,String[]> map = new HashMap<>();
		//Step.2 获取导出数据
		List<KhglKhcjxxPage> pageList = new ArrayList<KhglKhcjxxPage>();
		List<VKhglNhjbxx> vKhglNhjbxxList = ivKhglNhjbxxService.list(queryWrapper);
		for (VKhglNhjbxx khglNhjbxx : vKhglNhjbxxList) {
			Pydjcs pydjcs = pydjcsService.getPddjAndJysxde(khglNhjbxx.getPypjdf());
			KhglKhcjxxPage vo = new KhglKhcjxxPage();
			BeanUtils.copyProperties(khglNhjbxx, vo);
			if (pydjcs != null) {
				vo.setCzlwsxed(new BigDecimal(pydjcs.getJysxed()));
			}
			pageList.add(vo);
		}
		//Step.3 调用AutoPoi导出Excel
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		mv.addObject(NormalExcelConstants.FILE_NAME, "农户采集信息");
		mv.addObject(NormalExcelConstants.CLASS, KhglKhcjxxPage.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户采集信息", "导出人:"+sysUser.getRealname(), "农户采集信息"));
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
				List<KhglKhhmcxxPage> list = ExcelImportUtil.importExcel(file.getInputStream(), KhglKhhmcxxPage.class, params);
				for (KhglKhhmcxxPage page : list) {
					KhglKhhmcxx po = new KhglKhhmcxx();
					BeanUtils.copyProperties(page, po);
					khglKhhmcxxService.saveMain(po, page.getCamsZcsxNhcjxxList(),page.getNhfcxxList(),page.getYwhxgywList(),page.getNhPjsxxxList(),page.getFjglList());
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
     * 评议信息查看
     * @param zjhm
     * @return
     */
    @GetMapping(value = "/viewDetail")
    public Result<JSONObject> viewDetail(@RequestParam(name = "zjhm",required = true) String zjhm) {
      Result<JSONObject> result = new Result<>();
      try {
          List<Nhbkbpy> pyxxList = nhbkbpyService.viewDetail(zjhm);
          //计算平均得分与对应的额度
		  BigDecimal pydfSum = new BigDecimal(0);
		  BigDecimal pydfAvg = new BigDecimal(0);
		  Date zjpysj = null;
		  boolean existsBysx = false;
		  for (Nhbkbpy nhbkbpy : pyxxList) {
		      String pydf = nhbkbpy.getPydf();
		      String pylx = nhbkbpy.getPylx();
			  Date pysj = nhbkbpy.getPysj();
			  if(zjpysj == null || zjpysj.compareTo(pysj) < 0) {
				  zjpysj = pysj;
			  }
		      if("0".equals(pylx)) {
		      	  //存在不予授信
				  existsBysx = true;
				  break;
			  }
			  if (!StringUtils.isEmpty(pydf)) {
				  pydfSum = pydfSum.add(new BigDecimal(pydf));
			  }
		  }
		  if(existsBysx) {
			  pydfSum = new BigDecimal(0);
		  }
		  JSONObject jsonObject = new JSONObject();
		  jsonObject.put("pyxxList", pyxxList);
		  JSONArray jsonArray = new JSONArray();
		  if(!pyxxList.isEmpty()) {
		  	  pydfAvg = pydfSum.divide(new BigDecimal(pyxxList.size()), 2, BigDecimal.ROUND_HALF_UP);
			  Pydjcs pydjcs = pydjcsService.getPddjAndJysxde(pydfAvg);
			  JSONObject avgObject = new JSONObject();
			  avgObject.put("pypjdf", pydfAvg);
			  avgObject.put("cxd", pydjcs.getXydj());
			  avgObject.put("cxd_dictText", sysDictService.queryDictTextByKey("xydj", pydjcs.getXydj()));
			  avgObject.put("jysxed", pydjcs.getJysxed());
			  avgObject.put("zjpysj", DateUtil.format(zjpysj, "yyyy-MM-dd"));
			  jsonArray.add(avgObject);
		  }
		  jsonObject.put("ztqkList", jsonArray);
          result.setResult(jsonObject);
          result.setSuccess(true);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return result;
    }

    /**
     * 评议情况明细查看
     * @param zjhm
     * @param pyyzjhm
     * @return
     */
    @GetMapping(value = "/PymxData")
    public Result<Nhbkbpy> PymxData(@RequestParam(name = "zjhm",required = true) String zjhm,
                                    @RequestParam(name = "pyyzjhm",required = true) String pyyzjhm) {
        Result<Nhbkbpy> result = new Result<Nhbkbpy>();
        try {
            /*System.out.println("客户证件号码：" + zjhm);
            System.out.println("评议员证件号码：" + pyyzjhm);*/
            Nhbkbpy pymx = nhbkbpyService.PymxData(zjhm,pyyzjhm);
            result.setResult(pymx);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

	/**
	 * 农户基本信息（详情）：通过户号编码查询家庭房产情况
	 * @param hhbm
	 * @return
	 */
	@ApiOperation(value="农户基本信息（详情）：通过户号编码查询家庭房产情况", notes="农户基本信息（详情）：通过户号编码查询家庭房产情况")
	@RequestMapping(value = "/queryCams_Zcsx_NhfxxxByMainId", method = RequestMethod.GET)
	public Result<JSONArray> queryByDybh(@RequestParam(name = "hhbm",required = true) String hhbm) {
		Result<JSONArray> result = new Result<>();
		Nhfcxx check = new Nhfcxx();
		check.setHhbm(hhbm);
		Map<String, String[]> map=new HashMap<>();
		QueryWrapper<Nhfcxx> queryWrapper = QueryGenerator.initQueryWrapper(check,map);
		List<Nhfcxx> nhfcxxList = nhfcxxService.list(queryWrapper);
		if (nhfcxxList == null) {
			result.error500("未找到对应实体！");
		} else {
			JSONArray json=new JSONArray();
			for (Nhfcxx nhfcxx : nhfcxxList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("hhbm",nhfcxx.getHhbm());
				jsonObject.put("khmc",nhfcxx.getKhmc());
				jsonObject.put("zjhm",nhfcxx.getZjhm());
				jsonObject.put("fcwz",nhfcxx.getFcwz());
				jsonObject.put("fcmj",nhfcxx.getFcmj());
				jsonObject.put("fcjz",nhfcxx.getFcjz());
				jsonObject.put("fcxz",nhfcxx.getFcxz() == null ? "" : sysDictService.queryDictTextByKey("khgl_fcxz",nhfcxx.getFcxz()));
				jsonObject.put("fcbm",nhfcxx.getFcbm());
				jsonObject.put("fcdj",nhfcxx.getFcdj());
				jsonObject.put("bz",nhfcxx.getBz());
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
	@RequestMapping(value = "/queryCams_Zcsx_NhzcxxByMainId", method = RequestMethod.GET)
	public Result<JSONArray> querynhpjxx(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<JSONArray> result = new Result<>();
		List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.selectByMainId(zjhm);
		if(nhPjsxxxList==null) {
			result.error500("未找到对应实体");
		}else {
			result.setSuccess(true);
			JSONArray jsonArray = new JSONArray();
			for (NhPjsxxx pjsxxx: nhPjsxxxList ){
				//资产情况
				JSONObject jo = new JSONObject();
				jo.put("zclx","股权");
				jo.put("zcsl",pjsxxx.getGqsl());
				jo.put("zcjg",pjsxxx.getGqjz());
				jo.put("zcsm",pjsxxx.getGqxqsm());
				JSONObject jo1 = new JSONObject();
				jo1.put("zclx","农机具");
				jo1.put("zcsl",pjsxxx.getNjjsl());
				jo1.put("zcjg",pjsxxx.getNjjjz());
				jo1.put("zcsm",pjsxxx.getNjjqxsm());
				JSONObject jo2 = new JSONObject();
				jo2.put("zclx","家用电器");
				jo2.put("zcsl",pjsxxx.getJydqsl());
				jo2.put("zcjg",pjsxxx.getJydqjz());
				jo2.put("zcsm",pjsxxx.getJydqxqsm());
				JSONObject jo3 = new JSONObject();
				jo3.put("zclx","交通工具");
				jo3.put("zcsl",pjsxxx.getJtgjsl());
				jo3.put("zcjg",pjsxxx.getJtgjjz());
				jo3.put("zcsm",pjsxxx.getJtgjxqsm());
				JSONObject jo4 = new JSONObject();
				jo4.put("zclx","其他资产");
				jo4.put("zcsl",pjsxxx.getJtgjsl());
				jo4.put("zcjg",pjsxxx.getJtgjjz());
				jo4.put("zcsm",pjsxxx.getJtgjxqsm());
				jsonArray.add(jo);
				jsonArray.add(jo1);
				jsonArray.add(jo2);
				jsonArray.add(jo3);
				jsonArray.add(jo4);
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
	@RequestMapping(value = "/queryCams_Zcsx_NhfzqkByMainId", method = RequestMethod.GET)
	public Result<JSONArray> querynhfzqk(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<JSONArray> result = new Result<>();
		List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.selectByMainId(zjhm);
		if(nhPjsxxxList==null) {
			result.error500("未找到对应实体");
		}else {
			result.setSuccess(true);
			JSONArray jsonArray = new JSONArray();
			for (NhPjsxxx pjsxxx: nhPjsxxxList ){
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
				jsonArray.add(jo1);
				jsonArray.add(jo2);
				jsonArray.add(jo3);
				jsonArray.add(jo4);
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
	@RequestMapping(value = "/queryCams_Zcsx_NhjyqkByMainId", method = RequestMethod.GET)
	public Result<JSONArray> querynhjyqk(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<JSONArray> result = new Result<>();
		List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.selectByMainId(zjhm);
		if(nhPjsxxxList==null) {
			result.error500("未找到对应实体");
		}else {
			result.setSuccess(true);
			JSONArray jsonArray = new JSONArray();
			for (NhPjsxxx pjsxxx: nhPjsxxxList ){
				//经营情况
				JSONObject jo1 = new JSONObject();
				jo1.put("xmlx","种植");
				jo1.put("xmqk",pjsxxx.getZzxmqk());
				jo1.put("xmjsr",pjsxxx.getZzxmjsr());
				JSONObject jo2 = new JSONObject();
				jo2.put("xmlx","养殖");
				jo2.put("xmqk",pjsxxx.getYzxmqk());
				jo2.put("xmjsr",pjsxxx.getYzxmjsr());
				JSONObject jo3 = new JSONObject();
				jo3.put("xmlx","商业");
				jo3.put("xmqk",pjsxxx.getSyxmqk());
				jo3.put("xmjsr",pjsxxx.getSyxmjsr());
				JSONObject jo4 = new JSONObject();
				jo4.put("xmlx","劳务");
				jo4.put("xmqk",pjsxxx.getNwxmqk());
				jo4.put("xmjsr",pjsxxx.getNwxmjsr());
				JSONObject jo5 = new JSONObject();
				jo5.put("xmlx","其他");
				jo5.put("xmqk",pjsxxx.getQtxmqk());
				jo5.put("xmjsr",pjsxxx.getQtxmjsr());
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
	 * 农户基本信息查看
	 * @param zjhm
	 * @return
	 */
	@GetMapping(value = "/queryCams_Zcsx_NhpjxxByMainId")
	public Result<NhPjsxxx> querpjxx(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<NhPjsxxx> result = new Result<NhPjsxxx>();
		List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.selectByMainId(zjhm);
		try {
			if (nhPjsxxxList.size()!= 0) {
				result.setResult(nhPjsxxxList.get(0));
			}
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
	@RequestMapping(value = "/exportTemplateXls")
	public ModelAndView exportTemplateXls(Nhbkbpy nhbkbpy,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// Step.1 组装查询条件
		QueryWrapper<Nhbkbpy> queryWrapper = QueryGenerator.initQueryWrapper(nhbkbpy, request.getParameterMap());
		//AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new TemplateExcelView());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("tbsj", DateUtil.formatDateTime("yyyy-MM-dd"));
		List<Nhbkbpy> pageList = nhbkbpyService.list(queryWrapper);
		for (Nhbkbpy nhbkbpy1 : pageList){
			map.put("khxm",nhbkbpy1.getKhmc());
			map.put("jtzz","");
			map.put("sfzh",nhbkbpy1.getZjhm());
			map.put("pydf",nhbkbpy1.getPydf());
			map.put("sxed",nhbkbpy1.getJysxed());
			map.put("jtndlrsqk",nhbkbpy1.getJtndlrsqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtldlrsqk",nhbkbpy1.getJtndlrsqk()));
			map.put("xb",nhbkbpy1.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex",nhbkbpy1.getXb()));
			map.put("hyzk",nhbkbpy1.getHyzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_hyzk",nhbkbpy1.getHyzk()));
			map.put("nlqk",nhbkbpy1.getNnqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_nlqk",nhbkbpy1.getNnqk()));
			map.put("jkzkqk",nhbkbpy1.getJkztqk() == null ? "" :  sysDictService.queryDictTextByKey("bkbpy_qkms",nhbkbpy1.getJkztqk()));
			map.put("jynl",nhbkbpy1.getJynl() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_qkms",nhbkbpy1.getJynl()));
			map.put("fwjzjqk",nhbkbpy1.getFwjzqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_fwjzqk",nhbkbpy1.getFwjzqk()));
			map.put("dznyxfqk",nhbkbpy1.getDznyxfpqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_dznyxfpqk",nhbkbpy1.getDznyxfpqk()));
			map.put("njjqk",nhbkbpy1.getNjjqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_njjqk",nhbkbpy1.getNjjqk()));
			map.put("jyqk",nhbkbpy1.getJyqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jyqk",nhbkbpy1.getJyqk()));
			map.put("jtcsrqk",nhbkbpy1.getJtcsrqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtcsrqk",nhbkbpy1.getJtcsrqk()));
			map.put("jtzsr",nhbkbpy1.getJtzsr() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtzsrzczb",nhbkbpy1.getJtzsr()));
			map.put("jkrhkyy",nhbkbpy1.getJkrhkyy() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jkrhkyy",nhbkbpy1.getJkrhkyy()));
			map.put("jkrbjyq",nhbkbpy1.getJkrbjyq() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jkrbjyq",nhbkbpy1.getJkrbjyq()));
			map.put("jkrlxyq",nhbkbpy1.getJkrlxyq() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jkrlxyq",nhbkbpy1.getJkrlxyq()));
			map.put("ckywwl",nhbkbpy1.getCkywwl() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_ckywwlqk",nhbkbpy1.getCkywwl()));
			map.put("zyqk",nhbkbpy1.getZwqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_zyqk",nhbkbpy1.getZwqk()));
			map.put("jtcygx",nhbkbpy1.getJtcygx() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtcygx",nhbkbpy1.getJtcygx()));
			map.put("jtrkszjn",nhbkbpy1.getJtrkszhjn() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtrkszhjn",nhbkbpy1.getJtrkszhjn()));
			map.put("xyzk",nhbkbpy1.getXyzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_qkms",nhbkbpy1.getXyzk()));
			map.put("shsyjry",nhbkbpy1.getShswjry() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_shswjry",nhbkbpy1.getShswjry()));
			map.put("shgxzk",nhbkbpy1.getShgxzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_shgxzk",nhbkbpy1.getShgxzk()));
			map.put("shxgqk",nhbkbpy1.getShxgqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_shxgqk",nhbkbpy1.getShxgqk()));
			map.put("jtysgjqk",nhbkbpy1.getJtysgjqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtysgjqk",nhbkbpy1.getJtysgjqk()));
			map.put("zmjglqk",nhbkbpy1.getZmjgljk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_zmjgxjkqk",nhbkbpy1.getZmjgljk()));
			map.put("jtjgdkqk",nhbkbpy1.getJrjgdkqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_qtjgdkqk",nhbkbpy1.getJrjgdkqk()));
			map.put("xydj",nhbkbpy1.getCxd() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_xydj",nhbkbpy1.getCxd()));

		}
		String port = environment.getProperty("common.path.export");

		//导出文件名称
		mv.addObject(JxlsConstants.FILE_NAME, "农户背靠背外部评审表）");
		mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("农户背靠背外部评审表.xls"));
		mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/农户背靠背外部评审表.xls");
		mv.addObject(JxlsConstants.MAP_DATA, map);
		return mv;
	}

	/**
	 * 导出背靠背总体评议模板excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportPyztqkTemplateXls")
	public ModelAndView exportZtqkTemplateXls(VKhglNhjbxx vKhglNhjbxx,
											  @RequestParam(name="pypjdf") String pypjdf, @RequestParam(name = "cxd") String cxd,
											  @RequestParam(name="jysxed") BigDecimal jysxed,
											  HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// Step.1 组装查询条件
		QueryWrapper<VKhglNhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglNhjbxx, request.getParameterMap());

		//AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new TemplateExcelView());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("tbsj", DateUtil.formatDateTime("yyyy-MM-dd"));
		map.put("pypjdf", pypjdf);
		map.put("cxd", StringUtils.isEmpty(cxd) ? "" : sysDictService.queryDictTextByKey("xydj", cxd));
		map.put("jysxed", jysxed);
		List<VKhglNhjbxx> nhjbxxList = ivKhglNhjbxxService.list(queryWrapper);
		if (nhjbxxList != null && !nhjbxxList.isEmpty()) {
			VKhglNhjbxx nhjbxx = nhjbxxList.get(0);
			map.put("xzc", StringUtils.isEmpty(nhjbxx.getXzc()) ? "" : sysDictService.queryTableDictTextByKey("YXDYGL_EJYXDYGL", "dymc", "dybh", nhjbxx.getXzc()));
			map.put("xzz", StringUtils.isEmpty(nhjbxx.getXzz()) ? "" : sysDictService.queryTableDictTextByKey("YXDYGL_SJYXDYGL", "dymc", "dybh", nhjbxx.getXzz()));
			map.put("hhbm", nhjbxx.getHhbm());
			map.put("khmc", nhjbxx.getKhmc());
			map.put("zjhm", nhjbxx.getZjhm());
			map.put("nl", nhjbxx.getNl());
			map.put("xb", StringUtils.isEmpty(nhjbxx.getXb()) ? "" : sysDictService.queryDictTextByKey("sex", nhjbxx.getXb()));
		}
		QueryWrapper<Nhbkbpy> nhbkbpyQueryWrapper = new QueryWrapper<>();
		nhbkbpyQueryWrapper.eq("zjhm", vKhglNhjbxx.getZjhm());
		List<Nhbkbpy> nhbkbpyList = nhbkbpyService.list(nhbkbpyQueryWrapper);
		map.put("pyxxList", nhbkbpyList);
		String port = environment.getProperty("common.path.export");

		//导出文件名称
		mv.addObject(JxlsConstants.FILE_NAME, "农户背靠背评议总体情况表）");
		mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("农户背靠背评议总体情况表.xls"));
		mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/农户背靠背评议总体情况表.xls");
		mv.addObject(JxlsConstants.MAP_DATA, map);
		return mv;
	}

	/**
	 * 导出信用等级评定表excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/xydjpdbExportXlsUrl")
	public ModelAndView xydjpdbExportXlsUrl(VKhglNhjbxx vKhglNhjbxx,
											  HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// Step.1 组装查询条件
		QueryWrapper<VKhglNhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglNhjbxx, request.getParameterMap());
		queryWrapper.eq("sfhz", "1");
		queryWrapper.orderByAsc("xzz");
		//AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new TemplateExcelView());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("xzc", StringUtils.isEmpty(vKhglNhjbxx.getXzc()) ? "" : sysDictService.queryTableDictTextByKey("YXDYGL_EJYXDYGL", "dymc", "dybh", vKhglNhjbxx.getXzc()));
		List<VKhglNhjbxx> nhjbxxList = ivKhglNhjbxxService.list(queryWrapper);
		for (VKhglNhjbxx nhjbxx : nhjbxxList) {
			nhjbxx.setXzz(StringUtils.isEmpty(nhjbxx.getXzz()) ? "" : sysDictService.queryTableDictTextByKey("YXDYGL_SJYXDYGL", "dymc", "dybh", nhjbxx.getXzz()));
		}
		map.put("list", nhjbxxList);
		String port = environment.getProperty("common.path.export");

		//导出文件名称
		mv.addObject(JxlsConstants.FILE_NAME, "农户信用等级评定表");
		mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("农户信用等级评定表.xls"));
		mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/农户信用等级评定表.xls");
		mv.addObject(JxlsConstants.MAP_DATA, map);
		return mv;
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
		KhglKhhmcxx khhmcxxQuery = new KhglKhhmcxx();
		khhmcxxQuery.setHhbm(hhbm);
		Map<String, String[]> map = new HashMap<>();
		QueryWrapper<KhglKhhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(khhmcxxQuery, map);
		List<KhglKhhmcxx> khhmcxxQueryList = khglKhhmcxxService.list(queryWrapper);
		if (khhmcxxQueryList == null) {
			result.error500("未找到对应实体");
		}
		JSONArray json = new JSONArray();
		for (KhglKhhmcxx khhmcxx : khhmcxxQueryList) {
			JSONObject jo = new JSONObject();
			jo.put("hhbm", khhmcxx.getHhbm());
			jo.put("khmc", khhmcxx.getKhmc());
			jo.put("zjhm", khhmcxx.getZjhm());
			jo.put("yhzgx",khhmcxx.getYhzgx()== null ? "" : sysDictService.queryDictTextByKey("yhzgx", khhmcxx.getYhzgx()));
			jo.put("xb",khhmcxx.getXb()==null ? "" : sysDictService.queryDictTextByKey("sex", khhmcxx.getXb()));
			jo.put("sjhm", khhmcxx.getLxfs());
			Map<String,String[]> map2 = new HashMap<>();
			CamsZcsxNhcjxx nhcjxxQuery = new CamsZcsxNhcjxx();
			nhcjxxQuery.setZjhm(khhmcxx.getZjhm());
			QueryWrapper<CamsZcsxNhcjxx> queryWrapper2 = QueryGenerator.initQueryWrapper(nhcjxxQuery, map2);
			List<CamsZcsxNhcjxx> camsZcsxNhcjxxList =camsZcsxNhcjxxService.list(queryWrapper2);
			if (camsZcsxNhcjxxList.size() != 0) {
				jo.put("sjhm", camsZcsxNhcjxxList.get(0).getSjhm());
			}
			//查询业务信息
			Ywhywwlxx ywhywwlxxQuery = new Ywhywwlxx();
			ywhywwlxxQuery.setZjhm(khhmcxx.getZjhm());
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
     * 查询评定管理信息
     * @param zjhm
     * @return
     */
	@ApiOperation(value = "查询评定管理信息", notes = "查询评定管理信息")
	@RequestMapping(value = "/queryPdsm", method = RequestMethod.GET)
	public JSONArray queryPdsm(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<List<String>> result = new Result<>();
		List<String> list = new ArrayList<String>();
		CamsZcsxNhcjxx kg = new CamsZcsxNhcjxx();
		kg.setZjhm(zjhm);
		Map<String, String[]> map = new HashMap<>();
		QueryWrapper<CamsZcsxNhcjxx> queryWrapper = QueryGenerator.initQueryWrapper(kg,map);
		List<CamsZcsxNhcjxx> camsZcsxNhcjxxList = camsZcsxNhcjxxService.list(queryWrapper);
		if (camsZcsxNhcjxxList == null) {
			result.error500("未找到对应实体");
		} else {
			result.setSuccess(true);
		}
		JSONArray json = new JSONArray();
		JSONObject jo = new JSONObject();
		if (camsZcsxNhcjxxList.size()!=0) {
			jo.put("pdfs", "系统评定");
			String xtpdjg = camsZcsxNhcjxxList.get(0).getXtpdjg();
			jo.put("pdjg", StringUtils.isEmpty(xtpdjg) ? "" : sysDictService.queryDictTextByKey("pdgl_pdjg", xtpdjg));
			jo.put("pdsm", camsZcsxNhcjxxList.get(0).getXtpdsm());
			JSONObject jo1 = new JSONObject();
			jo1.put("pdfs", "外部评定");
			String gjrpdjg = camsZcsxNhcjxxList.get(0).getGjrpdjg();
			jo1.put("pdjg", StringUtils.isEmpty(gjrpdjg) ? "" : sysDictService.queryDictTextByKey("pdgl_pdjg", gjrpdjg));
			jo1.put("pdsm", camsZcsxNhcjxxList.get(0).getGjrpdsm());
			JSONObject jo2 = new JSONObject();
			jo2.put("pdfs", "最终评定");
			String zzpdjg = camsZcsxNhcjxxList.get(0).getZzpdjg();
			jo2.put("pdjg", StringUtils.isEmpty(zzpdjg) ? "" : sysDictService.queryDictTextByKey("pdgl_pdjg", zzpdjg));
			jo2.put("pdsm", camsZcsxNhcjxxList.get(0).getZzpdsm());
			json.add(jo);
			json.add(jo1);
			json.add(jo2);
		}
		return json;
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
        List<Fjgl> gzap_jhxf_khjl = fjglService.list(queryWrapper);
        if (gzap_jhxf_khjl == null) {
            result.error500("未找到对应实体");
        } else {
            result.setSuccess(true);
        }

		JSONArray json = new JSONArray();
        for (Fjgl a : gzap_jhxf_khjl) {
            JSONObject jo = new JSONObject();
            jo.put("hhbm", a.getHhbm());
            jo.put("zjhm", a.getZjhm());
            jo.put("zlmc", a.getZlmc());
            jo.put("zlbh",a.getZlbh());
            jo.put("fjbz", a.getBz());
            jo.put("fwlj", a.getFwlj());
			jo.put("zllj", a.getZllj());
            HrBasStaff hr=new HrBasStaff();
            hr.setYggh(a.getScr());
            Map<String, String[]> map2 = new HashMap<>();
            QueryWrapper<HrBasStaff> queryWrapper_hr = QueryGenerator.initQueryWrapper(hr, map2);
            /*List<HrBasStaff> hrstaff = iKhYgxxService.list(queryWrapper_hr);
            if (hrstaff.size()!=0){
				jo.put("scr", hrstaff.get(0).getYgxm());
			}else{
				jo.put("scr", a.getScr());
			}*/
            jo.put("scsj", a.getScsj());
            json.add(jo);
        }
        return json;
    }

	/**
	 * 查询农户附件
	 * @param hhbm
	 * @return
	 */
	@ApiOperation(value = "查询农户附件", notes = "查询农户附件")
	@RequestMapping(value = "/queryNhFj", method = RequestMethod.GET)
	public JSONArray queryByNhFJ(@RequestParam(name = "hhbm",required = true) String hhbm) {
		System.out.println("-----"+hhbm);
		Result<List<String>> result = new Result<>();
		List<String> list = new ArrayList<String>();
		KhglNhhzzllb kg = new KhglNhhzzllb();
		kg.setHhbm(hhbm);
		Map<String, String[]> map = new HashMap<>();
		QueryWrapper<KhglNhhzzllb> queryWrapper = QueryGenerator.initQueryWrapper(kg, map);
		List<KhglNhhzzllb> gzap_jhxf_khjl = khglNhhzzllbService.list(queryWrapper);
		if (gzap_jhxf_khjl == null) {
			result.error500("未找到对应实体");
		} else {
			result.setSuccess(true);
		}

		JSONArray json = new JSONArray();
		for (KhglNhhzzllb a : gzap_jhxf_khjl) {
			JSONObject jo = new JSONObject();
			jo.put("hhbm", a.getHhbm());
			jo.put("zlmc", a.getZlmc());
			jo.put("zlbh",a.getZlbh());
			jo.put("fjbz", a.getBz());
			jo.put("fwlj", a.getFwlj());
			jo.put("zllj", a.getZllj());
			HrBasStaff hr=new HrBasStaff();
			hr.setYggh(a.getScr());
			Map<String, String[]> map2 = new HashMap<>();
			QueryWrapper<HrBasStaff> queryWrapper_hr = QueryGenerator.initQueryWrapper(hr, map2);
			/*List<HrBasStaff> hrstaff = iKhYgxxService.list(queryWrapper_hr);
			if (hrstaff.size()!=0){
				jo.put("scr", hrstaff.get(0).getYgxm());
			}else{
				jo.put("scr", a.getScr());
			}*/
			jo.put("scsj", a.getScsj());
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
		List<Fjgl> gzap_jhxf_khjl = fjglService.list(queryWrapper);
		JSONObject jo = new JSONObject();
		if(gzap_jhxf_khjl.size()!=0) {
			jo.put("zllj", gzap_jhxf_khjl.get(0).getFwlj());
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
		KhglNhhzzllb kg = new KhglNhhzzllb();
		kg.setZlbh(zlbh);
		Map<String, String[]> map = new HashMap<>();
		QueryWrapper<KhglNhhzzllb> queryWrapper = QueryGenerator.initQueryWrapper(kg, map);
		List<KhglNhhzzllb> gzap_jhxf_khjl = khglNhhzzllbService.list(queryWrapper);
		JSONObject jo = new JSONObject();
		if(gzap_jhxf_khjl.size()!=0) {
			jo.put("zllj", gzap_jhxf_khjl.get(0).getFwlj());
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

	/*@RequestMapping(value = "/extract" , method = RequestMethod.PUT)
	public Result<?> extract() {
		Result<List<KhglKhhmcxx>> result =new Result<>();
		try {
			nhjbxxService.extract("20190101");
			result.setSuccess(true);
			return  result;
		} catch (Exception e) {
			System.out.println(e);
			log.error("提取失败",e.getMessage());
			result.setSuccess(false);
		}
		return result;
	}

    @RequestMapping(value = "/extractPjsx" , method = RequestMethod.PUT)
    public Result<?> extractPjsx() {
        Result<List<KhglKhhmcxx>> result =new Result<>();
        try {
			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            nhjbxxService.extractPjsx("1",date);
            result.setSuccess(true);
            return  result;
        } catch (Exception e) {
            System.out.println(e);
            log.error("提取失败",e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }*/

	/**
	 * 农户基本信息查看
	 * @param zjhm
	 * @return
	 */
	@GetMapping(value = "/nhjbxxcx")
	public Result<KhglKhhmcxx> nhjbxxcx(@RequestParam(name = "zjhm",required = true) String zjhm) {
		Result<KhglKhhmcxx> result = new Result<KhglKhhmcxx>();
		try {
			KhglKhhmcxx nhjbxx = new KhglKhhmcxx();
			nhjbxx.setZjhm(zjhm);
			Map<String,String[]> map = new HashMap<>();
			QueryWrapper<KhglKhhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(nhjbxx,map);
			//List<KhglKhhmcxx> pyxxList = iNhbkbpyService.viewDetail(zjhm);
			List<KhglKhhmcxx> nhjbxxList = khglKhhmcxxService.list(queryWrapper);
			result.setResult(nhjbxxList.get(0));
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
	@RequestMapping(value = "/nhmxexportTemplateXls")
	public ModelAndView nhmxexportTemplateXls(KhglKhhmcxx khglKhhmcxx,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		ModelAndView mv = new ModelAndView(new TemplateExcelView());
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,String[]> map1 = new HashMap<>();

		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArray6 = new JSONArray();
		//导出设置权限本支行只能导出本支行的数据，如果是全行则可以导出全行数据
		String sszh = "";
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		if (sysUser.getOrgCode()!= null && sysUser.getOrgCode().equals("1")) {
			sszh = khglKhhmcxx.getSszh();
		} else {
			sszh = sysUser.getOrgCode();
		}

		if (sszh.equals(khglKhhmcxx.getSszh())) {

			// Step.1 组装查询条件
			//查询基本信息
			KhglKhhmcxx nhjbxx = new KhglKhhmcxx();
			nhjbxx.setZjhm(khglKhhmcxx.getZjhm());
			nhjbxx.setSszh(sszh);
			QueryWrapper<KhglKhhmcxx> queryWrapper = QueryGenerator.initQueryWrapper(nhjbxx, request.getParameterMap());
			List<KhglKhhmcxx> khglKhhmcxxList = khglKhhmcxxService.list(queryWrapper);
			List<String> list = new ArrayList<>();
			for (KhglKhhmcxx khhmcxx : khglKhhmcxxList) {
				//把农户基本信息中的字段编码转换成汉字
				khhmcxx.setSszh(khhmcxx.getSszh() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khhmcxx.getSszh()));
				khhmcxx.setSfhz(khhmcxx.getSfhz() == null ? "" : sysDictService.queryDictTextByKey("sfbz", khhmcxx.getSfhz()));
				khhmcxx.setYhzgx(khhmcxx.getYhzgx() == null ? "" : sysDictService.queryDictTextByKey("yhzgx", khhmcxx.getYhzgx()));
				khhmcxx.setXb(khhmcxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", khhmcxx.getXb()));
				khhmcxx.setMz(khhmcxx.getMz() == null ? "" : sysDictService.queryDictTextByKey("mz", khhmcxx.getMz()));
				khhmcxx.setHyzk(khhmcxx.getHyzk() == null ? "" : sysDictService.queryDictTextByKey("hyzk", khhmcxx.getHyzk()));
				khhmcxx.setSfpkh(khhmcxx.getSfpkh() == null ? "" : sysDictService.queryDictTextByKey("sfbz", khhmcxx.getSfpkh()));
			}
			map.put("nhjbxx", khglKhhmcxxList);

			//查询农户采集信息
			CamsZcsxNhcjxx camsZcsxNhcjxx = new CamsZcsxNhcjxx();
			camsZcsxNhcjxx.setZjhm(khglKhhmcxx.getZjhm());
			QueryWrapper<CamsZcsxNhcjxx> queryWrapper1 = QueryGenerator.initQueryWrapper(camsZcsxNhcjxx,map1);
			List<CamsZcsxNhcjxx> camsZcsxNhcjxxList = camsZcsxNhcjxxService.list(queryWrapper1);
			for (CamsZcsxNhcjxx nhcjxx : camsZcsxNhcjxxList) {
				//把采集信息表中的字段编码转换成汉字
				nhcjxx.setSskhjl(nhcjxx.getSskhjl() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", nhcjxx.getSskhjl()));
				nhcjxx.setWhcd(nhcjxx.getWhcd() == null ? "" : sysDictService.queryDictTextByKey("whcd", nhcjxx.getWhcd()));
				nhcjxx.setHkxz(nhcjxx.getHkxz() == null ? "" : sysDictService.queryDictTextByKey("khgl_hkxz", nhcjxx.getHkxz()));
				nhcjxx.setKhlx1(nhcjxx.getKhlx1() == null ? "" :sysDictService.queryDictTextByKey("khlx", nhcjxx.getKhlx1()));
				nhcjxx.setKhlx2(nhcjxx.getKhlx2() == null ? "" :sysDictService.queryDictTextByKey("khlx", nhcjxx.getKhlx2()));
				nhcjxx.setYwzn(nhcjxx.getYwzn() == null ? "" :sysDictService.queryDictTextByKey("sfbz", nhcjxx.getYwzn()));
				nhcjxx.setJkzk(nhcjxx.getJkzk() == null ? "" :sysDictService.queryDictTextByKey("jkzk", nhcjxx.getJkzk()));
				nhcjxx.setLdnl(nhcjxx.getLdnl() == null ? "" :sysDictService.queryDictTextByKey("ldnl", nhcjxx.getLdnl()));
				nhcjxx.setJznx(nhcjxx.getJznx() == null ? "" :sysDictService.queryDictTextByKey("jznx", nhcjxx.getJznx()));
				//nhcjxx.setJzzt(nhcjxx.getJzzt() == null ? "" :sysDictService.queryDictTextByKey("jzzt", nhcjxx.getJzzt()));
				nhcjxx.setZgxl(nhcjxx.getZgxl() == null ? "" :sysDictService.queryDictTextByKey("whcd", nhcjxx.getZgxl()));
				nhcjxx.setZgxw(nhcjxx.getZgxw() == null ? "" :sysDictService.queryDictTextByKey("zgxw", nhcjxx.getZgxw()));
				nhcjxx.setSfbldkh(nhcjxx.getSfbldkh() == null ? "" :sysDictService.queryDictTextByKey("sfbz", nhcjxx.getSfbldkh()));
				nhcjxx.setSfpkh(nhcjxx.getSfpkh() == null ? "" :sysDictService.queryDictTextByKey("sfbz", nhcjxx.getSfpkh()));
				nhcjxx.setSfdbh(nhcjxx.getSfdbh() == null ? "" : sysDictService.queryDictTextByKey("sfbz", nhcjxx.getSfdbh()));
				nhcjxx.setSfycdg(nhcjxx.getSfycdg() == null ? "" : sysDictService.queryDictTextByKey("sfbz", nhcjxx.getSfycdg()));
				nhcjxx.setSfsx(nhcjxx.getSfsx() == null ? "" : sysDictService.queryDictTextByKey("sfbz", nhcjxx.getSfsx()));
				nhcjxx.setKfyyqk(nhcjxx.getKfyyqk() == null ? "" : sysDictService.queryDictTextByKey("khgl_khzycd", nhcjxx.getKfyyqk()));
				nhcjxx.setKcqzyw(nhcjxx.getKcqzyw() == null ? "" : sysDictService.queryDictTextByKey("khqzyw", nhcjxx.getKcqzyw()));
				nhcjxx.setKhsxqk(nhcjxx.getKhsxqk() == null ? "" : sysDictService.queryDictTextByKey("khgl_sxqk", nhcjxx.getKhsxqk()));
				nhcjxx.setDdpzzhpj(nhcjxx.getDdpzzhpj() == null ? "" : sysDictService.queryDictTextByKey("ddpzzhpj", nhcjxx.getDdpzzhpj()));

				//查询评定管理数据
				JSONObject jo = new JSONObject();
				jo.put("pdfs", "系统评定");
				jo.put("pdjg", nhcjxx.getXtpdjg() == null ? "" : sysDictService.queryDictTextByKey("pdgl_pdjg", nhcjxx.getXtpdjg()));
				jo.put("pdsm", nhcjxx.getXtpdsm());
				JSONObject jo1 = new JSONObject();
				jo1.put("pdfs", "外部评定");
				jo1.put("pdjg", nhcjxx.getGjrpdjg() == null ? "" : sysDictService.queryDictTextByKey("pdgl_pdjg", nhcjxx.getGjrpdjg()));
				jo1.put("pdsm", nhcjxx.getGjrpdsm());
				JSONObject jo2 = new JSONObject();
				jo2.put("pdfs", "最终评定");
				jo2.put("pdjg", nhcjxx.getZzpdjg() == null ? "" : sysDictService.queryDictTextByKey("pdgl_pdjg", nhcjxx.getZzpdjg()));
				jo2.put("pdsm", nhcjxx.getZzpdsm());
				jsonArray.add(jo);
				jsonArray.add(jo1);
				jsonArray.add(jo2);
			}
			map.put("nhcjxx", camsZcsxNhcjxxList);
			map.put("pdxx", jsonArray);
			System.out.println("@@@@@@@"+map.get("pdxx"));
			System.out.println("—--------"+map.get("nhcjxx"));


			//查询家庭相关情况
			Ywhywwlxx cyxx = new Ywhywwlxx();
			JSONArray jsonArray1 = new JSONArray();
			KhglKhhmcxx querynhjbxx = new KhglKhhmcxx();
			querynhjbxx.setHhbm(khglKhhmcxx.getHhbm());
			querynhjbxx.setSszh(sszh);
			//Map<String, String[]> map1 = new HashMap<>();
			QueryWrapper<KhglKhhmcxx> queryWrapper2 = QueryGenerator.initQueryWrapper(querynhjbxx, map1);
			List<KhglKhhmcxx> nhjbxxList = khglKhhmcxxService.list(queryWrapper2);
			for (KhglKhhmcxx nhjbxx1 : nhjbxxList) {
				cyxx.setZjhm(nhjbxx1.getZjhm());
				//cyxx.setHmcId(nhjbxx1.getId());
				QueryWrapper<Ywhywwlxx> queryWrapper3 = QueryGenerator.initQueryWrapper(cyxx, map1);
				List<Ywhywwlxx> cyxx1 = ywhywwlxxService.list(queryWrapper3);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("hhbm", nhjbxx1.getHhbm());
					jsonObject.put("khmc", nhjbxx1.getKhmc());
					jsonObject.put("zjhm", nhjbxx1.getZjhm());
					jsonObject.put("yhzgx",nhjbxx1.getYhzgx()==null ? "" :sysDictService.queryDictTextByKey("yhzgx", nhjbxx1.getYhzgx()));
					jsonObject.put("xb", nhjbxx1.getXb()==null ? "" : sysDictService.queryDictTextByKey("sex", nhjbxx1.getXb()));
				    Map<String,String[]> map2 = new HashMap<>();
				    CamsZcsxNhcjxx camsZcsxNhcjxx1 = new CamsZcsxNhcjxx();
				    camsZcsxNhcjxx1.setZjhm(nhjbxx1.getZjhm());
				    QueryWrapper<CamsZcsxNhcjxx> queryWrapper4 = QueryGenerator.initQueryWrapper(camsZcsxNhcjxx,map2);
				    List<CamsZcsxNhcjxx> camsZcsxNhcjxxList1 =camsZcsxNhcjxxService.list(queryWrapper4);
				    if (camsZcsxNhcjxxList1.size()!=0) {
						jsonObject.put("sjhm", camsZcsxNhcjxxList1.get(0).getSjhm());
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
			ywhxgyw.setZjhm(khglKhhmcxx.getZjhm());
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
			Nhfcxx nhfcxx1 = new Nhfcxx();
			nhfcxx1.setZjhm(khglKhhmcxx.getZjhm());
			QueryWrapper<Nhfcxx> queryWrapper4 = QueryGenerator.initQueryWrapper(nhfcxx1, map1);
			List<Nhfcxx> nhfcxx = nhfcxxService.list(queryWrapper4);
			for (Nhfcxx nhfcxx2 : nhfcxx) {
				nhfcxx2.setFcxz(nhfcxx2.getFcxz() == null ? "" : sysDictService.queryDictTextByKey("khgl_fcxz", nhfcxx2.getFcxz()));
			}
			map.put("nhfcxx", nhfcxx);

			//评级信息
			List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.selectByMainId(khglKhhmcxx.getZjhm());
			for (NhPjsxxx nhPjsxxx : nhPjsxxxList) {
				nhPjsxxx.setZhjzsl(nhPjsxxx.getZhjzsl() == null ? "" : sysDictService.queryDictTextByKey("khgl_zhjzsl", nhPjsxxx.getZhjzsl()));
				nhPjsxxx.setYbhlwsj(nhPjsxxx.getYbhlwsj() == null ? "" : sysDictService.queryDictTextByKey("khgl_jyxmcysj", nhPjsxxx.getYbhlwsj()));
				nhPjsxxx.setJyxmcysj(nhPjsxxx.getJyxmcysj() == null ? "" : sysDictService.queryDictTextByKey("khgl_jyxmcysj", nhPjsxxx.getJyxmcysj()));
				nhPjsxxx.setNhshpj(nhPjsxxx.getNhshpj() == null ? "" : sysDictService.queryDictTextByKey("khgl_shpj", nhPjsxxx.getNhshpj()));
				nhPjsxxx.setNdlrs(nhPjsxxx.getNdlrs() == null ? "" : sysDictService.queryDictTextByKey("khgl_ldlrs", nhPjsxxx.getNdlrs()));
			}
			map.put("pjxx", nhPjsxxxList);


			//资产情况
			JSONArray jsonArray2 = new JSONArray();
			for (NhPjsxxx pjsxxx : nhPjsxxxList) {
				JSONObject jo = new JSONObject();
				jo.put("zclx", "股权");
				jo.put("zcsl", pjsxxx.getGqsl());
				jo.put("zcjg", pjsxxx.getGqjz());
				jo.put("zcsm", pjsxxx.getGqxqsm());
				JSONObject jo1 = new JSONObject();
				jo1.put("zclx", "农机具");
				jo1.put("zcsl", pjsxxx.getNjjsl());
				jo1.put("zcjg", pjsxxx.getNjjjz());
				jo1.put("zcsm", pjsxxx.getNjjqxsm());
				JSONObject jo2 = new JSONObject();
				jo2.put("zclx", "家用电器");
				jo2.put("zcsl", pjsxxx.getJydqsl());
				jo2.put("zcjg", pjsxxx.getJydqjz());
				jo2.put("zcsm", pjsxxx.getJydqxqsm());
				JSONObject jo3 = new JSONObject();
				jo3.put("zclx", "交通工具");
				jo3.put("zcsl", pjsxxx.getJtgjsl());
				jo3.put("zcjg", pjsxxx.getJtgjjz());
				jo3.put("zcsm", pjsxxx.getJtgjxqsm());
				JSONObject jo4 = new JSONObject();
				jo4.put("zclx", "其他资产");
				jo4.put("zcsl", pjsxxx.getJtgjsl());
				jo4.put("zcjg", pjsxxx.getJtgjjz());
				jo4.put("zcsm", pjsxxx.getJtgjxqsm());
				jsonArray2.add(jo);
				jsonArray2.add(jo1);
				jsonArray2.add(jo2);
				jsonArray2.add(jo3);
				jsonArray2.add(jo4);
			}
			map.put("zcqk", jsonArray2);

			//负债情况
			JSONArray jsonArray3 = new JSONArray();
			for (NhPjsxxx pjsxxx : nhPjsxxxList) {

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
				jsonArray3.add(jo1);
				jsonArray3.add(jo2);
				jsonArray3.add(jo3);
				jsonArray3.add(jo4);
			}
			map.put("fzqk", jsonArray3);

			//经营情况
			JSONArray jsonArray4 = new JSONArray();
			for (NhPjsxxx pjsxxx : nhPjsxxxList) {
				JSONObject jo1 = new JSONObject();
				jo1.put("xmlx", "种植");
				jo1.put("xmqk", pjsxxx.getZzxmqk());
				jo1.put("xmjsr", pjsxxx.getZzxmjsr());
				JSONObject jo2 = new JSONObject();
				jo2.put("xmlx", "养殖");
				jo2.put("xmqk", pjsxxx.getYzxmqk());
				jo2.put("xmjsr", pjsxxx.getYzxmjsr());
				JSONObject jo3 = new JSONObject();
				jo3.put("xmlx", "商业");
				jo3.put("xmqk", pjsxxx.getSyxmqk());
				jo3.put("xmjsr", pjsxxx.getSyxmjsr());
				JSONObject jo4 = new JSONObject();
				jo4.put("xmlx", "劳务");
				jo4.put("xmqk", pjsxxx.getNwxmqk());
				jo4.put("xmjsr", pjsxxx.getNwxmjsr());
				JSONObject jo5 = new JSONObject();
				jo5.put("xmlx", "其他");
				jo5.put("xmqk", pjsxxx.getQtxmqk());
				jo5.put("xmjsr", pjsxxx.getQtxmjsr());
				jsonArray4.add(jo1);
				jsonArray4.add(jo2);
				jsonArray4.add(jo3);
				jsonArray4.add(jo4);
				jsonArray4.add(jo5);
			}
			map.put("jyqk", jsonArray4);
		}

			String port = environment.getProperty("common.path.export");
			//导出文件名称
			mv.addObject(JxlsConstants.FILE_NAME, "农户详细信息表）");
			mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("农户详细信息表.xls"));
			mv.addObject(JxlsConstants.SAVE_FILE_NAME, port + "/农户详细信息表.xls");
			mv.addObject(JxlsConstants.MAP_DATA, map);
			return mv;

	}

	/**
	 * 农户采集信息查看
	 * @param zjhm
	 * @return
	 */
	@RequestMapping(value = "/nhcjxx", method = RequestMethod.GET)
	public Result<?> nhcjxx(@RequestParam(name = "zjhm",required = true) String zjhm) {
		//Result<CamsZcsxNhcjxx> result = new Result<CamsZcsxNhcjxx>();
		JSONObject jsonObject = new JSONObject();
		CamsZcsxNhcjxx nhcjxx = new CamsZcsxNhcjxx();
		nhcjxx.setZjhm(zjhm);
		try {
			Map<String,String[]> map = new HashMap<>();
			QueryWrapper<CamsZcsxNhcjxx> queryWrapper = QueryGenerator.initQueryWrapper(nhcjxx,map);
			List<CamsZcsxNhcjxx> nhcjxxList = camsZcsxNhcjxxService.list(queryWrapper);
			if (nhcjxxList.size()!=0) {
				jsonObject.put("nhcjxxList", nhcjxxList.get(0));
				jsonObject.put("zkhjl_dictText",nhcjxxList.get(0).getSskhjl()==null ? " " : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh",nhcjxxList.get(0).getSskhjl()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return	Result.ok(jsonObject);
	}

	/**
	 * 导出农户采集信息模板excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/khxxcjexportXls")
	public ModelAndView khxxcjexportXls(KhglKhhmcxx khhmcxx, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {


		//AutoPoi 导出Excel
		ModelAndView mv = new ModelAndView(new TemplateExcelView());
		JSONArray jsonArray = new JSONArray();
		Map<String,String[]> map = new HashMap<>();
		Map<String,Object> map1 = new HashMap<>();
       //查询家庭成员信息
	 	/*KhglKhhmcxx khhmcxx = new KhglKhhmcxx();
	 	khhmcxx.setHhbm(json.getString("hhbm"));*/
		QueryWrapper<KhglKhhmcxx> queryWrapper =  QueryGenerator.initQueryWrapper(khhmcxx,map);
		List<KhglKhhmcxx> khglKhhmcxxList  = khglKhhmcxxService.list(queryWrapper);
		 for (KhglKhhmcxx khglKhhmcxx1 : khglKhhmcxxList) {
		 	if (khglKhhmcxx1.getSfhz().equals("2")){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("xm",khglKhhmcxx1.getKhmc());
				jsonObject.put("yhzgx",khglKhhmcxx1.getYhzgx()== null ? "" : sysDictService.queryDictTextByKey("yhzgx", khglKhhmcxx1.getYhzgx()));
				jsonObject.put("zjhm",khglKhhmcxx1.getZjhm());
				jsonObject.put("nl",khglKhhmcxx1.getNl());
				CamsZcsxNhcjxx camsZcsxNhcjxx = new CamsZcsxNhcjxx();
				camsZcsxNhcjxx.setZjhm(khglKhhmcxx1.getZjhm());
				QueryWrapper<CamsZcsxNhcjxx> queryWrapper1 = QueryGenerator.initQueryWrapper(camsZcsxNhcjxx,map);
				List<CamsZcsxNhcjxx> camsZcsxNhcjxxList = camsZcsxNhcjxxService.list(queryWrapper1);
				if (camsZcsxNhcjxxList.size() != 0 ){
					jsonObject.put("whcd",camsZcsxNhcjxxList.get(0).getZgxl()== null ? "" : sysDictService.queryDictTextByKey("whcd", camsZcsxNhcjxxList.get(0).getZgxl()));
					jsonObject.put("ldnlzk",camsZcsxNhcjxxList.get(0).getLdnl()== null ? "" : sysDictService.queryDictTextByKey("ldnl", camsZcsxNhcjxxList.get(0).getLdnl()));
				}
				jsonArray.add(jsonObject);

			}else {
		 		//查询户主信息及家庭资产/经营项目
				map1.put("hzxm",khglKhhmcxx1.getKhmc());
				map1.put("xb",khglKhhmcxx1.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", khglKhhmcxx1.getXb()));
				map1.put("zjhm",khglKhhmcxx1.getZjhm());
				map1.put("nl",khglKhhmcxx1.getNl());
				map1.put("jtrk",khglKhhmcxxList.size());
				map1.put("lxdh",khglKhhmcxx1.getLxfs());
				NhPjsxxx nhPjsxxx = new NhPjsxxx();
				nhPjsxxx.setZjhm(khglKhhmcxx1.getZjhm());
				QueryWrapper queryWrapper1  =  QueryGenerator.initQueryWrapper(nhPjsxxx,map);
				List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.list(queryWrapper1);
				if (nhPjsxxxList.size()!= 0){
					//用StringBuffer拼接经营情况 判断项目情况字段不为空的则拼接对应的经营项目
					StringBuffer stringBuffer= new StringBuffer();
					stringBuffer.append(nhPjsxxxList.get(0).getZzxmqk()==null ? " " : "种植、");
					stringBuffer.append(nhPjsxxxList.get(0).getYzxmqk()==null ? " " : "养殖、"  );
					stringBuffer.append(nhPjsxxxList.get(0).getSyxmqk()==null ? " " : "商业、"  );
					stringBuffer.append(nhPjsxxxList.get(0).getNwxmqk()==null ? " " : "劳务、"  );
					stringBuffer.append(nhPjsxxxList.get(0).getQtxmqk()==null ? " " : "其他、"  );
					map1.put("jyqk",stringBuffer.substring(0,stringBuffer.length()-1));
					//用StringBuffer拼接资产情况 判断资产情况字段不为空的则拼接对应的经营项目
					StringBuffer stringBuffer1= new StringBuffer();
					stringBuffer1.append(nhPjsxxxList.get(0).getGqjz()==null ? " " : "股权价值:" + nhPjsxxxList.get(0).getGqjz()+ "、" );
					stringBuffer1.append(nhPjsxxxList.get(0).getNjjjz()==null ? " " : "农机具价值:" + nhPjsxxxList.get(0).getNjjjz()+"、"  );
					stringBuffer1.append(nhPjsxxxList.get(0).getJydqjz()==null ? " " : "家用电器价值:" + nhPjsxxxList.get(0).getJydqjz()+"、"  );
					stringBuffer1.append(nhPjsxxxList.get(0).getJtgjjz()==null ? " " : "交通工具价值:" + nhPjsxxxList.get(0).getJtgjjz()+"、"  );
					stringBuffer1.append(nhPjsxxxList.get(0).getQtzcjz()==null ? " " : "其他资产价值:" + nhPjsxxxList.get(0).getQtzcjz()+"、"  );
					map1.put("jtzczk",stringBuffer1.substring(0,stringBuffer1.length()-1));

					map1.put("mjjd",0);
					//查询银行贷款
					int bxtjkje = Integer.parseInt(nhPjsxxxList.get(0).getBxtjksl()== null ? "0" : nhPjsxxxList.get(0).getBxtjksl());
					int thjkje = Integer.parseInt(nhPjsxxxList.get(0).getThjksl()== null ? "0" : nhPjsxxxList.get(0).getThjksl());
					int xykjkje = Integer.parseInt(nhPjsxxxList.get(0).getXyksl()== null ? "0" : nhPjsxxxList.get(0).getXyksl());
					int yhdk = bxtjkje + thjkje + xykjkje;
					map1.put("yhdk",yhdk);

					int qtfzsl = Integer.parseInt(nhPjsxxxList.get(0).getQtfzsl()== null ? "0" : nhPjsxxxList.get(0).getQtfzsl());
					map1.put("qtjrjgjk",qtfzsl);

					//查询合计
					int hj = yhdk + qtfzsl;
					map1.put("hj",hj);


					//把家庭所经营项目年收入相加
					int zzxmsr = Integer.parseInt(nhPjsxxxList.get(0).getZzxmsr()== null ? "0" : nhPjsxxxList.get(0).getZzxmsr());
					int yzxmsr = Integer.parseInt(nhPjsxxxList.get(0).getYzxmsr()== null ? "0" : nhPjsxxxList.get(0).getYzxmsr());
					int syxmsr = Integer.parseInt(nhPjsxxxList.get(0).getSyxmsr()== null ? "0" : nhPjsxxxList.get(0).getSyxmsr());
					int nwxwsr = Integer.parseInt(nhPjsxxxList.get(0).getNwxmsr()==null ? "0" : nhPjsxxxList.get(0).getNwxmsr());
					int qtxmsr = Integer.parseInt(nhPjsxxxList.get(0).getQtxmsr()==null ? "0" : nhPjsxxxList.get(0).getQtxmsr());
					int jyxmnsr = zzxmsr+yzxmsr+syxmsr+nwxwsr+qtxmsr;
					map1.put("jyxmnsr",jyxmnsr);

					//把家庭所经营项目年支出相加
					int zzxmzc = Integer.parseInt(nhPjsxxxList.get(0).getZzxmzc()== null ? "0" : nhPjsxxxList.get(0).getZzxmzc());
					int yzxmzc = Integer.parseInt(nhPjsxxxList.get(0).getYzxmzc()== null ? "0" : nhPjsxxxList.get(0).getYzxmzc());
					int syxmzc = Integer.parseInt(nhPjsxxxList.get(0).getSyxmzc()== null ? "0" : nhPjsxxxList.get(0).getSyxmzc());
					int nwxwzc = Integer.parseInt(nhPjsxxxList.get(0).getNwxmzc()==null ? "0" : nhPjsxxxList.get(0).getNwxmzc());
					int qtxmzc = Integer.parseInt(nhPjsxxxList.get(0).getQtxmzc()==null ? "0" : nhPjsxxxList.get(0).getQtxmzc());
					int jyxmnzc = zzxmzc+yzxmzc+syxmzc+nwxwzc+qtxmzc;
					map1.put("jyxmnzc",jyxmnzc);

					//把家庭所经营项目纯收入相加
					int zzxmcsr = Integer.parseInt(nhPjsxxxList.get(0).getZzxmjsr()== null ? "0" : nhPjsxxxList.get(0).getZzxmjsr());
					int yzxmcsr = Integer.parseInt(nhPjsxxxList.get(0).getYzxmjsr()== null ? "0" : nhPjsxxxList.get(0).getYzxmjsr());
					int syxmcsr = Integer.parseInt(nhPjsxxxList.get(0).getSyxmjsr()== null ? "0" : nhPjsxxxList.get(0).getSyxmjsr());
					int nwxwcsr = Integer.parseInt(nhPjsxxxList.get(0).getNwxmjsr()==null ? "0" : nhPjsxxxList.get(0).getNwxmjsr());
					//int jyxmncsr = zzxmcsr+yzxmcsr+syxmcsr+nwxwcsr;
					int jyxmncsr = jyxmnsr-jyxmnzc;
					map1.put("jyxmncsr",jyxmncsr);
					//查询被他调查人签名
					CamsZcsxNhcjxx camsZcsxNhcjxx = new CamsZcsxNhcjxx();
					camsZcsxNhcjxx.setZjhm(khglKhhmcxx1.getZjhm());
					QueryWrapper<CamsZcsxNhcjxx> queryWrapper2 = QueryGenerator.initQueryWrapper(camsZcsxNhcjxx,map);
					List<CamsZcsxNhcjxx> list = camsZcsxNhcjxxService.list(queryWrapper2);
					map1.put("bdcrqm",list.get(0).getSign1());

				}
			}
		 }
		 	map1.put("qydm",iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101002"));
			map1.put("list", jsonArray);
			String port = environment.getProperty("common.path.export");
			//导出文件名称
			mv.addObject(JxlsConstants.FILE_NAME, "农户信息采集表");
			mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("农户信息采集表.xls"));
			mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/农户信息采集表.xls");
			mv.addObject(JxlsConstants.MAP_DATA, map1);
			return mv;
	}

	/**
	 * 农户评议授信信息打印
	 * @param hhbm
	 * @param zjhm
	 * @return
	 */
	@RequestMapping(value = "/nhpysxxx", method = RequestMethod.GET)
	public Result<?> nhpysxxx(@RequestParam(name = "hhbm",required = true) String hhbm,
							 @RequestParam(name = "zjhm",required = true) String zjhm) {

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject1 = new JSONObject();
		Map<String,String[]> map = new HashMap<>();

		try {
			//查询家庭成员信息
			KhglKhhmcxx khhmcxxQuery = new KhglKhhmcxx();
			khhmcxxQuery.setHhbm(hhbm);
			QueryWrapper<KhglKhhmcxx> queryWrapper =  QueryGenerator.initQueryWrapper(khhmcxxQuery,map);
			queryWrapper.orderByAsc("yhzgx");
			List<KhglKhhmcxx> khglKhhmcxxList  = khglKhhmcxxService.list(queryWrapper);
			for (KhglKhhmcxx khglKhhmcxx : khglKhhmcxxList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("yhzgx", khglKhhmcxx.getYhzgx() == null ? "" : sysDictService.queryDictTextByKey("yhzgx", khglKhhmcxx.getYhzgx()));
				jsonObject.put("khmc", khglKhhmcxx.getKhmc());
				jsonObject.put("xb", khglKhhmcxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", khglKhhmcxx.getXb()));
				jsonObject.put("zjhm", khglKhhmcxx.getZjhm());
				jsonObject.put("lxfs", khglKhhmcxx.getLxfs());
				if("1".equals(khglKhhmcxx.getYhzgx())) {
					jsonObject1.put("zzjc", sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khglKhhmcxx.getSszh()));
				}

				CamsZcsxNhcjxx nhcjxxQuery = new CamsZcsxNhcjxx();
				nhcjxxQuery.setZjhm(khglKhhmcxx.getZjhm());
				QueryWrapper<CamsZcsxNhcjxx> nhcjxxQueryWrapper = QueryGenerator.initQueryWrapper(nhcjxxQuery, map);
				List<CamsZcsxNhcjxx> camsZcsxNhcjxxList = camsZcsxNhcjxxService.list(nhcjxxQueryWrapper);
				if (camsZcsxNhcjxxList.size() != 0) {
					CamsZcsxNhcjxx nhcjxx = camsZcsxNhcjxxList.get(0);
					jsonObject.put("zy", nhcjxx.getCshygz());
					jsonObject.put("gzdw", nhcjxx.getGzdw());
					jsonObject.put("jkzk", nhcjxx.getJkzk() == null ? "" : sysDictService.queryDictTextByKey("jkzk", nhcjxx.getJkzk()));
					jsonObject.put("ldnl", nhcjxx.getLdnl() == null ? "" : sysDictService.queryDictTextByKey("ldnl", nhcjxx.getLdnl()));
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
			//计算平均得分与对应的额度
			List<Nhbkbpy> pyxxList = nhbkbpyService.viewDetail(zjhm);

			BigDecimal pydfSum = new BigDecimal(0);
			BigDecimal pydfAvg = new BigDecimal(0);
			Date zjpysj = null;
			boolean existsBysx = false;
			for (Nhbkbpy nhbkbpy : pyxxList) {
				String pydf = nhbkbpy.getPydf();
				String pylx = nhbkbpy.getPylx();
				Date pysj = nhbkbpy.getPysj();
				if(zjpysj == null || zjpysj.compareTo(pysj) < 0) {
					zjpysj = pysj;
				}
				if("0".equals(pylx)) {
					//存在不予授信
					existsBysx = true;
					break;
				}
				if (!StringUtils.isEmpty(pydf)) {
					pydfSum = pydfSum.add(new BigDecimal(pydf));
				}
			}
			if(existsBysx) {
				pydfSum = new BigDecimal(0);
			}
			JSONObject pyxxObject = new JSONObject();
			pyxxObject.put("pysl", pyxxList.size());
			if(!pyxxList.isEmpty()) {
				pydfAvg = pydfSum.divide(new BigDecimal(pyxxList.size()), 2, BigDecimal.ROUND_HALF_UP);
				Pydjcs pydjcs = pydjcsService.getPddjAndJysxde(pydfAvg);
				pyxxObject.put("pypjdf", pydfAvg);
				pyxxObject.put("cxd", pydjcs.getXydj());
				pyxxObject.put("cxd_dictText", sysDictService.queryDictTextByKey("xydj", pydjcs.getXydj()));
				pyxxObject.put("jysxed", pydjcs.getJysxed());
				pyxxObject.put("zjpysj", DateUtil.format(zjpysj, "yyyy-MM-dd"));
			}
			jsonObject1.put("pyxx", pyxxObject);

			//查询授信信息
			JSONObject pjsxxxJSON = new JSONObject();
			NhPjsxxx nhPjsxxxQuery = new NhPjsxxx();
			nhPjsxxxQuery.setHhbm(hhbm);
			QueryWrapper nhPjsxxxQueryWrapper = QueryGenerator.initQueryWrapper(nhPjsxxxQuery, map);
			nhPjsxxxQueryWrapper.orderByAsc("lrsj");
			List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.list(nhPjsxxxQueryWrapper);
			BigDecimal jydqjz = new BigDecimal(0); //家用电器价值
			BigDecimal jtgjjz = new BigDecimal(0); //交通工具价值
			BigDecimal qtzcjz = new BigDecimal(0); //其他资产价值

			BigDecimal bxtjkje = new BigDecimal(0);
			BigDecimal thjkje = new BigDecimal(0);
			BigDecimal xykjkje = new BigDecimal(0);
			BigDecimal qtjkje = new BigDecimal(0);

			BigDecimal zzxmsr = new BigDecimal(0);
			BigDecimal zzxmzc = new BigDecimal(0);
			BigDecimal zzxmjsr = new BigDecimal(0);
			BigDecimal yzxmsr = new BigDecimal(0);
			BigDecimal yzxmzc = new BigDecimal(0);
			BigDecimal yzxmjsr = new BigDecimal(0);
			BigDecimal syxmsr = new BigDecimal(0);
			BigDecimal syxmzc = new BigDecimal(0);
			BigDecimal syxmjsr = new BigDecimal(0);
			BigDecimal lwxmsr = new BigDecimal(0);
			BigDecimal lwxmzc = new BigDecimal(0);
			BigDecimal lwxmjsr = new BigDecimal(0);
			BigDecimal qtxmsr = new BigDecimal(0);
			BigDecimal qtxmzc = new BigDecimal(0);
			BigDecimal qtxmjsr = new BigDecimal(0);


			if (nhPjsxxxList.size()!= 0){
				NhPjsxxx nhPjsxxx = nhPjsxxxList.get(0);
				pjsxxxJSON = JSON.parseObject(JSON.toJSONString(nhPjsxxx));
				pjsxxxJSON.put("shpj", nhPjsxxx.getNhshpj() == null ? "" : sysDictService.queryDictTextByKey("khgl_shpj", nhPjsxxx.getNhshpj()));
				jydqjz = stringToBigDecimal(nhPjsxxx.getJydqjz());
				jtgjjz = stringToBigDecimal(nhPjsxxx.getJtgjjz());
				qtzcjz = stringToBigDecimal(nhPjsxxx.getQtzcjz());
				bxtjkje = stringToBigDecimal(nhPjsxxx.getBxtjksl());
				thjkje = stringToBigDecimal(nhPjsxxx.getThjksl());
				xykjkje = stringToBigDecimal(nhPjsxxx.getXyksl());
				qtjkje = stringToBigDecimal(nhPjsxxx.getQtfzsl());
				zzxmsr = stringToBigDecimal(nhPjsxxx.getZzxmsr());
				zzxmzc = stringToBigDecimal(nhPjsxxx.getZzxmzc());
				zzxmjsr = stringToBigDecimal(nhPjsxxx.getZzxmjsr());
				yzxmsr = stringToBigDecimal(nhPjsxxx.getYzxmsr());
				yzxmzc = stringToBigDecimal(nhPjsxxx.getYzxmzc());
				yzxmjsr = stringToBigDecimal(nhPjsxxx.getYzxmjsr());
				syxmsr = stringToBigDecimal(nhPjsxxx.getSyxmsr());
				syxmzc = stringToBigDecimal(nhPjsxxx.getSyxmzc());
				syxmjsr = stringToBigDecimal(nhPjsxxx.getSyxmjsr());
				lwxmsr = stringToBigDecimal(nhPjsxxx.getNwxmsr());
				lwxmzc = stringToBigDecimal(nhPjsxxx.getNwxmzc());
				lwxmjsr = stringToBigDecimal(nhPjsxxx.getNwxmjsr());
				qtxmsr = stringToBigDecimal(nhPjsxxx.getQtxmsr());
				qtxmzc = stringToBigDecimal(nhPjsxxx.getQtxmzc());
				qtxmjsr = stringToBigDecimal(nhPjsxxx.getQtxmjsr());
			}
			//查询房产信息
			Nhfcxx nhfcxxQuery = new Nhfcxx();
			nhfcxxQuery.setHhbm(hhbm);
			QueryWrapper nhfcxxQueryWrapper = QueryGenerator.initQueryWrapper(nhfcxxQuery, map);
			List<Nhfcxx> nhfcxxList = nhfcxxService.list(nhfcxxQueryWrapper);
			BigDecimal fcjzSum = new BigDecimal(0);
			if (nhfcxxList.size() != 0) {
				pjsxxxJSON.put("fcsl", nhfcxxList.size());
				for (Nhfcxx nhfcxx : nhfcxxList) {
					BigDecimal fcjz = nhfcxx.getFcjz();
					if (fcjz != null) {
						fcjzSum = fcjzSum.add(fcjz);
					}
				}
				pjsxxxJSON.put("fcjz", fcjzSum);
			}
			BigDecimal zcjzSum = new BigDecimal(0);
			BigDecimal fzSum = new BigDecimal(0);
			BigDecimal srSum = new BigDecimal(0);
			BigDecimal zcSum = new BigDecimal(0);
			BigDecimal jsrSum = new BigDecimal(0);

			zcjzSum = jydqjz.add(jtgjjz).add(qtzcjz).add(fcjzSum);
			fzSum = bxtjkje.add(thjkje).add(xykjkje).add(qtjkje);
			srSum = zzxmsr.add(yzxmsr).add(syxmsr).add(lwxmsr).add(qtxmsr);
			zcSum = zzxmzc.add(yzxmzc).add(syxmzc).add(lwxmzc).add(qtxmzc);
			jsrSum = zzxmjsr.add(yzxmjsr).add(syxmjsr).add(lwxmjsr).add(qtxmjsr);

			pjsxxxJSON.put("zcjzSum", zcjzSum);
			pjsxxxJSON.put("fzSum", fzSum);
			pjsxxxJSON.put("srSum", srSum);
			pjsxxxJSON.put("zcSum", zcSum);
			pjsxxxJSON.put("jsrSum", jsrSum);

			jsonObject1.put("jtcyList",jsonArray);
			jsonObject1.put("nhPjsxxx", pjsxxxJSON);
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
	 * 农户信息采集打印
	 * @param hhbm
	 * @param zjhm
	 * @return
	 */
	@RequestMapping(value = "/nhxxcjb", method = RequestMethod.GET)
	public Result<?> nhxxcjb(@RequestParam(name = "hhbm",required = true) String hhbm,
							 @RequestParam(name = "zjhm",required = true) String zjhm) {

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject1 = new JSONObject();
		Map<String,String[]> map = new HashMap<>();

		try {
			JSONObject jsonObject2 = new JSONObject();
		  /*QueryWrapper<KhglKhhmcxx> queryWrapper2 = QueryGenerator.initQueryWrapper(khglKhhmcxx,map);
		  List<KhglKhhmcxx>  khhmcxxList =khglKhhmcxxService.list(queryWrapper2);
		  for (KhglKhhmcxx khhmcxx : khhmcxxList) {
				 jsonObject2.put("hzmc",khhmcxx.getKhmc());
			}*/

			//查询家庭成员信息
			KhglKhhmcxx khhmcxx = new KhglKhhmcxx();
			khhmcxx.setHhbm(hhbm);
			//khhmcxx.setSfhz("2");
			QueryWrapper<KhglKhhmcxx> queryWrapper =  QueryGenerator.initQueryWrapper(khhmcxx,map);
			List<KhglKhhmcxx> khglKhhmcxxList  = khglKhhmcxxService.list(queryWrapper);
			for (KhglKhhmcxx khglKhhmcxx1 : khglKhhmcxxList) {
				if (khglKhhmcxx1.getSfhz()!= null && khglKhhmcxx1.getSfhz().equals("2")){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("xm",khglKhhmcxx1.getKhmc());
					jsonObject.put("yhzgx",khglKhhmcxx1.getYhzgx()== null ? "" : sysDictService.queryDictTextByKey("yhzgx", khglKhhmcxx1.getYhzgx()));
					jsonObject.put("zjhm",khglKhhmcxx1.getZjhm());
					jsonObject.put("nl",khglKhhmcxx1.getNl());
					CamsZcsxNhcjxx camsZcsxNhcjxx = new CamsZcsxNhcjxx();
					camsZcsxNhcjxx.setZjhm(khglKhhmcxx1.getZjhm());
					QueryWrapper<CamsZcsxNhcjxx> queryWrapper1 = QueryGenerator.initQueryWrapper(camsZcsxNhcjxx,map);
					List<CamsZcsxNhcjxx> camsZcsxNhcjxxList = camsZcsxNhcjxxService.list(queryWrapper1);
					if (camsZcsxNhcjxxList.size() != 0 ){
						jsonObject.put("whcd",camsZcsxNhcjxxList.get(0).getZgxl()== null ? "" : sysDictService.queryDictTextByKey("whcd", camsZcsxNhcjxxList.get(0).getZgxl()));
						jsonObject.put("ldnlzk",camsZcsxNhcjxxList.get(0).getLdnl()== null ? "" : sysDictService.queryDictTextByKey("ldnl", camsZcsxNhcjxxList.get(0).getLdnl()));
					}
					jsonArray.add(jsonObject);

				}else {
					//查询户主信息及家庭资产/经营项目
					jsonObject2.put("hzxm",khglKhhmcxx1.getKhmc());
					jsonObject2.put("xb",khglKhhmcxx1.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", khglKhhmcxx1.getXb()));
					jsonObject2.put("zjhm",khglKhhmcxx1.getZjhm());
					jsonObject2.put("nl",khglKhhmcxx1.getNl());
					jsonObject2.put("jtrk",khglKhhmcxxList.size());
					jsonObject2.put("lxdh",khglKhhmcxx1.getLxfs());
					NhPjsxxx nhPjsxxx = new NhPjsxxx();
					nhPjsxxx.setZjhm(khglKhhmcxx1.getZjhm());
					QueryWrapper queryWrapper1  =  QueryGenerator.initQueryWrapper(nhPjsxxx,map);
					List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.list(queryWrapper1);
					if (nhPjsxxxList.size()!= 0){
						//用StringBuffer拼接经营情况 判断项目情况字段不为空的则拼接对应的经营项目
						StringBuffer stringBuffer= new StringBuffer();
						stringBuffer.append(nhPjsxxxList.get(0).getZzxmqk()==null ? " " : "种植、");
						stringBuffer.append(nhPjsxxxList.get(0).getYzxmqk()==null ? " " : "养殖、"  );
						stringBuffer.append(nhPjsxxxList.get(0).getSyxmqk()==null ? " " : "商业、"  );
						stringBuffer.append(nhPjsxxxList.get(0).getNwxmqk()==null ? " " : "劳务、"  );
						stringBuffer.append(nhPjsxxxList.get(0).getQtxmqk()==null ? " " : "其他、"  );
						jsonObject2.put("jyqk",stringBuffer.substring(0,stringBuffer.length()-1));
						//用StringBuffer拼接资产情况 判断资产情况字段不为空的则拼接对应的经营项目
						StringBuffer stringBuffer1= new StringBuffer();
						stringBuffer1.append(nhPjsxxxList.get(0).getGqjz()==null ? " " : "股权价值:" + nhPjsxxxList.get(0).getGqjz()+ "、" );
						stringBuffer1.append(nhPjsxxxList.get(0).getNjjjz()==null ? " " : "农机具价值:" + nhPjsxxxList.get(0).getNjjjz()+"、"  );
						stringBuffer1.append(nhPjsxxxList.get(0).getJydqjz()==null ? " " : "家用电器价值:" + nhPjsxxxList.get(0).getJydqjz()+"、"  );
						stringBuffer1.append(nhPjsxxxList.get(0).getJtgjjz()==null ? " " : "交通工具价值:" + nhPjsxxxList.get(0).getJtgjjz()+"、"  );
						stringBuffer1.append(nhPjsxxxList.get(0).getQtzcjz()==null ? " " : "其他资产价值:" + nhPjsxxxList.get(0).getQtzcjz()+"、"  );
						jsonObject2.put("jtzczk",stringBuffer1.substring(0,stringBuffer1.length()-1));



						jsonObject2.put("mjjd",0);
						//查询银行贷款
						int bxtjkje = Integer.parseInt(nhPjsxxxList.get(0).getBxtjksl()== null ? "0" : nhPjsxxxList.get(0).getBxtjksl());
						int thjkje = Integer.parseInt(nhPjsxxxList.get(0).getThjksl()== null ? "0" : nhPjsxxxList.get(0).getThjksl());
						int xykjkje = Integer.parseInt(nhPjsxxxList.get(0).getXyksl()== null ? "0" : nhPjsxxxList.get(0).getXyksl());
						int yhdk = bxtjkje + thjkje + xykjkje;
						jsonObject2.put("yhdk",yhdk);

						int qtfzsl = Integer.parseInt(nhPjsxxxList.get(0).getQtfzsl()== null ? "0" : nhPjsxxxList.get(0).getQtfzsl());
						jsonObject2.put("qtjrjgjk",qtfzsl);

						//查询合计
						int hj = yhdk + qtfzsl;
						jsonObject2.put("hj",hj);


						//把家庭所经营项目年收入相加
						int zzxmsr = Integer.parseInt(nhPjsxxxList.get(0).getZzxmsr()== null ? "0" : nhPjsxxxList.get(0).getZzxmsr());
						int yzxmsr = Integer.parseInt(nhPjsxxxList.get(0).getYzxmsr()== null ? "0" : nhPjsxxxList.get(0).getYzxmsr());
						int syxmsr = Integer.parseInt(nhPjsxxxList.get(0).getSyxmsr()== null ? "0" : nhPjsxxxList.get(0).getSyxmsr());
						int nwxwsr = Integer.parseInt(nhPjsxxxList.get(0).getNwxmsr()==null ? "0" : nhPjsxxxList.get(0).getNwxmsr());
						int qtxmsr = Integer.parseInt(nhPjsxxxList.get(0).getQtxmsr()==null ? "0" : nhPjsxxxList.get(0).getQtxmsr());

						int jyxmnsr = zzxmsr+yzxmsr+syxmsr+nwxwsr+qtxmsr;
						jsonObject2.put("jyxmnsr",jyxmnsr);

						//把家庭所经营项目年支出相加
						int zzxmzc = Integer.parseInt(nhPjsxxxList.get(0).getZzxmzc()== null ? "0" : nhPjsxxxList.get(0).getZzxmzc());
						int yzxmzc = Integer.parseInt(nhPjsxxxList.get(0).getYzxmzc()== null ? "0" : nhPjsxxxList.get(0).getYzxmzc());
						int syxmzc = Integer.parseInt(nhPjsxxxList.get(0).getSyxmzc()== null ? "0" : nhPjsxxxList.get(0).getSyxmzc());
						int nwxwzc = Integer.parseInt(nhPjsxxxList.get(0).getNwxmzc()==null ? "0" : nhPjsxxxList.get(0).getNwxmzc());
						int qtxmzc = Integer.parseInt(nhPjsxxxList.get(0).getQtxmzc()==null ? "0" : nhPjsxxxList.get(0).getQtxmzc());

						int jyxmnzc = zzxmzc+yzxmzc+syxmzc+nwxwzc+qtxmzc;
						jsonObject2.put("jyxmnzc",jyxmnzc);

						//把家庭所经营项目纯收入相加
						int zzxmcsr = Integer.parseInt(nhPjsxxxList.get(0).getZzxmjsr()== null ? "0" : nhPjsxxxList.get(0).getZzxmjsr());
						int yzxmcsr = Integer.parseInt(nhPjsxxxList.get(0).getYzxmjsr()== null ? "0" : nhPjsxxxList.get(0).getYzxmjsr());
						int syxmcsr = Integer.parseInt(nhPjsxxxList.get(0).getSyxmjsr()== null ? "0" : nhPjsxxxList.get(0).getSyxmjsr());
						int nwxwcsr = Integer.parseInt(nhPjsxxxList.get(0).getNwxmjsr()==null ? "0" : nhPjsxxxList.get(0).getNwxmjsr());
						int qtxmcsr = Integer.parseInt(nhPjsxxxList.get(0).getQtxmjsr()==null ? "0" : nhPjsxxxList.get(0).getQtxmjsr());

						//int jyxmncsr = zzxmcsr+yzxmcsr+syxmcsr+nwxwcsr+qtxmcsr;
						int jyxmncsr = jyxmnsr-jyxmnzc;
						jsonObject2.put("jyxmncsr",jyxmncsr);
						//查询被他调查人签名
						CamsZcsxNhcjxx camsZcsxNhcjxx = new CamsZcsxNhcjxx();
						camsZcsxNhcjxx.setZjhm(khglKhhmcxx1.getZjhm());
						/*QueryWrapper<CamsZcsxNhcjxx> queryWrapper2 = QueryGenerator.initQueryWrapper(camsZcsxNhcjxx,map);
						List<CamsZcsxNhcjxx> list = camsZcsxNhcjxxService.list(queryWrapper2);
						if (list.size() > 0){
						jsonObject2.put("bdcrqm",list.get(0).getSign1());
						}else {
							jsonObject2.put("bdcrqm","");
						}*/
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
	 * 背靠背评议表打印
	 * @param zjhm
	 * @param pyyzjhm
	 * @return
	 */
	@RequestMapping(value = "/nhbkbpy", method = RequestMethod.GET)
	public Result<?> nhbkbpy(@RequestParam(name = "zjhm",required = true) String zjhm,
							 @RequestParam(name = "pyyzjhm",required = true) String pyyzjhm,
						     @RequestParam(name ="pysj",required = true) String pysj) {
		JSONObject jsonObject1 = new JSONObject();
		try {
			Date pyrq =null;
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
			pyrq = formatter.parse(pysj);
			VKhglNhjbxx vKhglNhjbxx = new VKhglNhjbxx();
			Map<String,String[]> map = new HashMap<>();
			vKhglNhjbxx.setZjhm(zjhm);
			QueryWrapper<VKhglNhjbxx> queryWrapper1 = QueryGenerator.initQueryWrapper(vKhglNhjbxx,map);
			VKhglNhjbxx vKhglNhjbxx1 = ivKhglNhjbxxService.getOne(queryWrapper1);
			jsonObject1.put("xzc",vKhglNhjbxx1.getXzc() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("YXDYGL_EJYXDYGL", "dymc", "dybh", vKhglNhjbxx1.getXzc()));
			jsonObject1.put("xzz",vKhglNhjbxx1.getXzz() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("YXDYGL_SJYXDYGL", "dymc", "dybh", vKhglNhjbxx1.getXzz()));


			Nhbkbpy nhbkbpy = new Nhbkbpy();
			nhbkbpy.setZjhm(zjhm);
			nhbkbpy.setPyyzjhm(pyyzjhm);
			nhbkbpy.setPysj(pyrq);
			QueryWrapper<Nhbkbpy> queryWrapper = QueryGenerator.initQueryWrapper(nhbkbpy,map);
			List<Nhbkbpy> nhbkbpyList = nhbkbpyService.list(queryWrapper);
			for (Nhbkbpy nhbkbpy1 : nhbkbpyList) {
				jsonObject1.put("hhbm",nhbkbpy1.getHhbm());
				jsonObject1.put("xm",nhbkbpy1.getKhmc());
				jsonObject1.put("zjhm",nhbkbpy1.getZjhm());
				jsonObject1.put("nl",nhbkbpy1.getNl());
				jsonObject1.put("xb",nhbkbpy1.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex",nhbkbpy1.getXb()));
				jsonObject1.put("hyzk",nhbkbpy1.getHyzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_hyzk",nhbkbpy1.getHyzk()));
				jsonObject1.put("nlqk",nhbkbpy1.getNnqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_nlqk",nhbkbpy1.getNnqk()));
				jsonObject1.put("jkzkqk",nhbkbpy1.getJkztqk() == null ? "" :  sysDictService.queryDictTextByKey("bkbpy_qkms",nhbkbpy1.getJkztqk()));
				jsonObject1.put("jynl",nhbkbpy1.getJynl() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_qkms",nhbkbpy1.getJynl()));
				jsonObject1.put("fwjzjqk",nhbkbpy1.getFwjzqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_fwjzqk",nhbkbpy1.getFwjzqk()));
				jsonObject1.put("dznyxfqk",nhbkbpy1.getDznyxfpqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_dznyxfpqk",nhbkbpy1.getDznyxfpqk()));
				jsonObject1.put("njjqk",nhbkbpy1.getNjjqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_njjqk",nhbkbpy1.getNjjqk()));
				jsonObject1.put("jyqk",nhbkbpy1.getJyqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jyqk",nhbkbpy1.getJyqk()));
				jsonObject1.put("jtcsrqk",nhbkbpy1.getJtcsrqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtcsrqk",nhbkbpy1.getJtcsrqk()));
				jsonObject1.put("jtzsr",nhbkbpy1.getJtzsr() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtzsrzczb",nhbkbpy1.getJtzsr()));
				jsonObject1.put("jkrhkyy",nhbkbpy1.getJkrhkyy() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jkrhkyy",nhbkbpy1.getJkrhkyy()));
				jsonObject1.put("jkrbjyq",nhbkbpy1.getJkrbjyq() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jkrbjyq",nhbkbpy1.getJkrbjyq()));
				jsonObject1.put("jkrlxyq",nhbkbpy1.getJkrlxyq() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jkrlxyq",nhbkbpy1.getJkrlxyq()));
				jsonObject1.put("ckywwl",nhbkbpy1.getCkywwl() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_ckywwlqk",nhbkbpy1.getCkywwl()));
				jsonObject1.put("zyqk",nhbkbpy1.getZwqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_zyqk",nhbkbpy1.getZwqk()));
				jsonObject1.put("jtcygx",nhbkbpy1.getJtcygx() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtcygx",nhbkbpy1.getJtcygx()));
				jsonObject1.put("jtndlrsqk",nhbkbpy1.getJtndlrsqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtldlrsqk",nhbkbpy1.getJtndlrsqk()));
				jsonObject1.put("xyzk",nhbkbpy1.getXyzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_qkms",nhbkbpy1.getXyzk()));
				jsonObject1.put("shsyjry",nhbkbpy1.getShswjry() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_shswjry",nhbkbpy1.getShswjry()));
				jsonObject1.put("jtrkszjn",nhbkbpy1.getJtrkszhjn() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtrkszhjn",nhbkbpy1.getJtrkszhjn()));
				jsonObject1.put("shgxzk",nhbkbpy1.getShgxzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_shgxzk",nhbkbpy1.getShgxzk()));
				jsonObject1.put("shxgqk",nhbkbpy1.getShxgqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_shxgqk",nhbkbpy1.getShxgqk()));
				jsonObject1.put("jtysgjqk",nhbkbpy1.getJtysgjqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtysgjqk",nhbkbpy1.getJtysgjqk()));
				jsonObject1.put("zmjgxqk",nhbkbpy1.getZmjgljk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_zmjgxjkqk",nhbkbpy1.getZmjgljk()));
				jsonObject1.put("qtjgdkqk",nhbkbpy1.getJrjgdkqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_qtjgdkqk",nhbkbpy1.getJrjgdkqk()));
				jsonObject1.put("pydf",nhbkbpy1.getPydf());
				jsonObject1.put("xydj",nhbkbpy1.getCxd() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_xydj",nhbkbpy1.getCxd()));
				jsonObject1.put("jysxed",nhbkbpy1.getJysxed());
				jsonObject1.put("pyyxm",nhbkbpy1.getPyyxm());
				jsonObject1.put("pyyzjhm",nhbkbpy1.getPyyzjhm());
				jsonObject1.put("pyyqm",nhbkbpy1.getSign1());
				jsonObject1.put("pysj",nhbkbpy1.getPysj());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Result.ok(jsonObject1);
	}

	/**
	 * 导出农户采集信息模板excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/nhbkbpyexpor")
	public ModelAndView khxxcjexportXls(Nhbkbpy nhbkbpy, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView(new TemplateExcelView());

		Map<String,Object> map = new HashMap<>();
		KhglKhhmcxx khglKhhmcxx = new KhglKhhmcxx();
		Yxdygl_czxxgl yxdyglCzxxgl = new Yxdygl_czxxgl();
		khglKhhmcxx.setZjhm(nhbkbpy.getZjhm());
		QueryWrapper<KhglKhhmcxx> queryWrapper1 = QueryGenerator.initQueryWrapper(khglKhhmcxx,request.getParameterMap());
		List<KhglKhhmcxx> khglKhhmcxxList = khglKhhmcxxService.list(queryWrapper1);
		for (KhglKhhmcxx khhmcxx : khglKhhmcxxList) {
			yxdyglCzxxgl.setVillage(khhmcxx.getSsyxdy());
			QueryWrapper<Yxdygl_czxxgl> queryWrapper = QueryGenerator.initQueryWrapper(yxdyglCzxxgl,request.getParameterMap());
			List<Yxdygl_czxxgl> yxdyglCzxxglList = yxdygl_czxxglService.list(queryWrapper);
			if (yxdyglCzxxglList.size() > 0 ) {
				map.put("xzz", yxdyglCzxxglList.get(0).getOrganize());
				map.put("xzc", yxdyglCzxxglList.get(0).getVillage());
			}
		}
		QueryWrapper<Nhbkbpy> queryWrapper = QueryGenerator.initQueryWrapper(nhbkbpy,request.getParameterMap());
		List<Nhbkbpy> nhbkbpyList = nhbkbpyService.list(queryWrapper);
		for (Nhbkbpy nhbkbpy1 : nhbkbpyList) {
			map.put("hhbm",nhbkbpy1.getHhbm());
			map.put("xm",nhbkbpy1.getKhmc());
			map.put("zjhm",nhbkbpy1.getZjhm());
			map.put("nl",nhbkbpy1.getNl());
			map.put("xb",nhbkbpy1.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex",nhbkbpy1.getXb()));
			map.put("hyzk",nhbkbpy1.getHyzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_hyzk",nhbkbpy1.getHyzk()));
			map.put("nlqk",nhbkbpy1.getNnqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_nlqk",nhbkbpy1.getNnqk()));
			map.put("jkzkqk",nhbkbpy1.getJkztqk() == null ? "" :  sysDictService.queryDictTextByKey("bkbpy_qkms",nhbkbpy1.getJkztqk()));
			map.put("jynl",nhbkbpy1.getJynl() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_qkms",nhbkbpy1.getJynl()));
			map.put("fwjzjqk",nhbkbpy1.getFwjzqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_fwjzqk",nhbkbpy1.getFwjzqk()));
			map.put("dznyxfqk",nhbkbpy1.getDznyxfpqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_dznyxfpqk",nhbkbpy1.getDznyxfpqk()));
			map.put("njjqk",nhbkbpy1.getNjjqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_njjqk",nhbkbpy1.getNjjqk()));
			map.put("jyqk",nhbkbpy1.getJyqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jyqk",nhbkbpy1.getJyqk()));
			map.put("jtcsrqk",nhbkbpy1.getJtcsrqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtcsrqk",nhbkbpy1.getJtcsrqk()));
			map.put("jtzsr",nhbkbpy1.getJtzsr() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtzsrzczb",nhbkbpy1.getJtzsr()));
			map.put("jkrhkyy",nhbkbpy1.getJkrhkyy() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jkrhkyy",nhbkbpy1.getJkrhkyy()));
			map.put("jkrbjyq",nhbkbpy1.getJkrbjyq() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jkrbjyq",nhbkbpy1.getJkrbjyq()));
			map.put("jkrlxyq",nhbkbpy1.getJkrlxyq() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jkrlxyq",nhbkbpy1.getJkrlxyq()));
			map.put("ckywwl",nhbkbpy1.getCkywwl() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_ckywwlqk",nhbkbpy1.getCkywwl()));
			map.put("zyqk",nhbkbpy1.getZwqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_zyqk",nhbkbpy1.getZwqk()));
			map.put("jtcygx",nhbkbpy1.getJtcygx() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtcygx",nhbkbpy1.getJtcygx()));
			map.put("jtndlrsqk",nhbkbpy1.getJtndlrsqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtldlrsqk",nhbkbpy1.getJtndlrsqk()));
			map.put("xyzk",nhbkbpy1.getXyzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_qkms",nhbkbpy1.getXyzk()));
			map.put("shsyjry",nhbkbpy1.getShswjry() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_shswjry",nhbkbpy1.getShswjry()));
			map.put("jtrkszjn",nhbkbpy1.getJtrkszhjn() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtrkszhjn",nhbkbpy1.getJtrkszhjn()));
			map.put("shgxzk",nhbkbpy1.getShgxzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_shgxzk",nhbkbpy1.getShgxzk()));
			map.put("shxgqk",nhbkbpy1.getShxgqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_shxgqk",nhbkbpy1.getShxgqk()));
			map.put("jtysgjqk",nhbkbpy1.getJtysgjqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_jtysgjqk",nhbkbpy1.getJtysgjqk()));
			map.put("zmjgxqk",nhbkbpy1.getZmjgljk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_zmjgxjkqk",nhbkbpy1.getZmjgljk()));
			map.put("qtjgdkqk",nhbkbpy1.getJrjgdkqk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_qtjgdkqk",nhbkbpy1.getJrjgdkqk()));
			map.put("pydf",nhbkbpy1.getPydf());
			map.put("xydj",nhbkbpy1.getCxd() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_xydj",nhbkbpy1.getCxd()));
			map.put("jysxed",nhbkbpy1.getJysxed());
			map.put("pyyxm",nhbkbpy1.getPyyxm());
			map.put("pyyzjhm",nhbkbpy1.getPyyzjhm());
			map.put("pyyqm",nhbkbpy1.getSign1());
			map.put("pysj",nhbkbpy1.getPysj());
		}
		String port = environment.getProperty("common.path.export");
		mv.addObject(JxlsConstants.FILE_NAME, "农户背靠背评议表");
		mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("农户背靠背评议表.xls"));
		mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/农户背靠背评议表.xls");
		mv.addObject(JxlsConstants.MAP_DATA, map);

		return mv;
	}

	/**
	 * 导出农户评议授信信息模板excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/nhpysxxxExportXls")
	public ModelAndView nhpysxxxExportXls(@RequestParam(name = "hhbm",required = true) String hhbm,
										  @RequestParam(name = "ssxz",required = true) String ssxz,
										  @RequestParam(name = "xzc",required = true) String xzc,
										  @RequestParam(name = "xzz",required = false) String xzz,
										  HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView(new TemplateExcelView());

		Map<String,String[]> map = new HashMap<>();
		Map<String, Object> dataMap = new HashMap<>();
		Map<String, Object> nhxxMap = new HashMap<>();
		//查询家庭成员信息
		KhglKhhmcxx khhmcxxQuery = new KhglKhhmcxx();
		khhmcxxQuery.setHhbm(hhbm);
		QueryWrapper<KhglKhhmcxx> queryWrapper =  QueryGenerator.initQueryWrapper(khhmcxxQuery,map);
		queryWrapper.orderByAsc("yhzgx");
		List<KhglKhhmcxx> khglKhhmcxxList  = khglKhhmcxxService.list(queryWrapper);
		JSONArray jtcyArray = new JSONArray();
		String zjhm = "";
		for (KhglKhhmcxx khglKhhmcxx : khglKhhmcxxList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("yhzgx", khglKhhmcxx.getYhzgx() == null ? "" : sysDictService.queryDictTextByKey("yhzgx", khglKhhmcxx.getYhzgx()));
			jsonObject.put("khmc", khglKhhmcxx.getKhmc());
			jsonObject.put("xb", khglKhhmcxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex", khglKhhmcxx.getXb()));
			jsonObject.put("zjhm", khglKhhmcxx.getZjhm());
			jsonObject.put("lxfs", khglKhhmcxx.getLxfs());
			if("1".equals(khglKhhmcxx.getYhzgx())) {
				dataMap.put("zzjc", sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khglKhhmcxx.getSszh()));
				zjhm = khglKhhmcxx.getZjhm();
			}

			CamsZcsxNhcjxx nhcjxxQuery = new CamsZcsxNhcjxx();
			nhcjxxQuery.setZjhm(khglKhhmcxx.getZjhm());
			QueryWrapper<CamsZcsxNhcjxx> nhcjxxQueryWrapper = QueryGenerator.initQueryWrapper(nhcjxxQuery, map);
			List<CamsZcsxNhcjxx> camsZcsxNhcjxxList = camsZcsxNhcjxxService.list(nhcjxxQueryWrapper);
			if (camsZcsxNhcjxxList.size() != 0) {
				CamsZcsxNhcjxx nhcjxx = camsZcsxNhcjxxList.get(0);
				jsonObject.put("zy", nhcjxx.getCshygz());
				jsonObject.put("gzdw", nhcjxx.getGzdw());
				jsonObject.put("jkzk", nhcjxx.getJkzk() == null ? "" : sysDictService.queryDictTextByKey("jkzk", nhcjxx.getJkzk()));
				jsonObject.put("ldnl", nhcjxx.getLdnl() == null ? "" : sysDictService.queryDictTextByKey("ldnl", nhcjxx.getLdnl()));
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
			nhxxMap.put("ckye", ywhywwlxx.getCkye());
			nhxxMap.put("ckrpye", ywhywwlxx.getCknrpye());
			nhxxMap.put("dkye", ywhywwlxx.getDkye());
			nhxxMap.put("bldkye", ywhywwlxx.getBldkye());
			nhxxMap.put("sjyh", ywhywwlxx.getSfktsjyhyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktsjyhyw()));
			nhxxMap.put("etc", ywhywwlxx.getSfbletcyw() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfbletcyw()));
			nhxxMap.put("sbk", ywhywwlxx.getSfktsbk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktsbk()));
			nhxxMap.put("ezf", ywhywwlxx.getSfblezf() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfblezf()));
			nhxxMap.put("xyk", ywhywwlxx.getSfktxyk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktxyk()));
			nhxxMap.put("bmk", ywhywwlxx.getSfktfmk() == null ? "" : sysDictService.queryDictTextByKey("sfbz", ywhywwlxx.getSfktfmk()));
		}
		//计算平均得分与对应的额度
		List<Nhbkbpy> pyxxList = nhbkbpyService.viewDetail(zjhm);

		BigDecimal pydfSum = new BigDecimal(0);
		BigDecimal pydfAvg = new BigDecimal(0);
		Date zjpysj = null;
		boolean existsBysx = false;
		for (Nhbkbpy nhbkbpy : pyxxList) {
			String pydf = nhbkbpy.getPydf();
			String pylx = nhbkbpy.getPylx();
			Date pysj = nhbkbpy.getPysj();
			if(zjpysj == null || zjpysj.compareTo(pysj) < 0) {
				zjpysj = pysj;
			}
			if("0".equals(pylx)) {
				//存在不予授信
				existsBysx = true;
				break;
			}
			if (!StringUtils.isEmpty(pydf)) {
				pydfSum = pydfSum.add(new BigDecimal(pydf));
			}
		}
		if(existsBysx) {
			pydfSum = new BigDecimal(0);
		}
		JSONObject pyxxObject = new JSONObject();
		nhxxMap.put("pysl", pyxxList.size());
		if(!pyxxList.isEmpty()) {
			pydfAvg = pydfSum.divide(new BigDecimal(pyxxList.size()), 2, BigDecimal.ROUND_HALF_UP);
			Pydjcs pydjcs = pydjcsService.getPddjAndJysxde(pydfAvg);
			nhxxMap.put("pypjdf", pydfAvg);
			nhxxMap.put("cxd", pydjcs.getXydj());
			nhxxMap.put("cxd_dictText", sysDictService.queryDictTextByKey("xydj", pydjcs.getXydj()));
			nhxxMap.put("jysxed", pydjcs.getJysxed());
			nhxxMap.put("zjpysj", DateUtil.format(zjpysj, "yyyy-MM-dd"));
		}

		//查询授信信息
		JSONObject pjsxxxJSON = new JSONObject();
		NhPjsxxx nhPjsxxxQuery = new NhPjsxxx();
		nhPjsxxxQuery.setHhbm(hhbm);
		QueryWrapper nhPjsxxxQueryWrapper = QueryGenerator.initQueryWrapper(nhPjsxxxQuery, map);
		nhPjsxxxQueryWrapper.orderByAsc("lrsj");
		List<NhPjsxxx> nhPjsxxxList = nhPjsxxxService.list(nhPjsxxxQueryWrapper);
		BigDecimal jydqjz = new BigDecimal(0); //家用电器价值
		BigDecimal jtgjjz = new BigDecimal(0); //交通工具价值
		BigDecimal qtzcjz = new BigDecimal(0); //其他资产价值

		BigDecimal bxtjkje = new BigDecimal(0);
		BigDecimal thjkje = new BigDecimal(0);
		BigDecimal xykjkje = new BigDecimal(0);
		BigDecimal qtjkje = new BigDecimal(0);

		BigDecimal zzxmsr = new BigDecimal(0);
		BigDecimal zzxmzc = new BigDecimal(0);
		BigDecimal zzxmjsr = new BigDecimal(0);
		BigDecimal yzxmsr = new BigDecimal(0);
		BigDecimal yzxmzc = new BigDecimal(0);
		BigDecimal yzxmjsr = new BigDecimal(0);
		BigDecimal syxmsr = new BigDecimal(0);
		BigDecimal syxmzc = new BigDecimal(0);
		BigDecimal syxmjsr = new BigDecimal(0);
		BigDecimal lwxmsr = new BigDecimal(0);
		BigDecimal lwxmzc = new BigDecimal(0);
		BigDecimal lwxmjsr = new BigDecimal(0);
		BigDecimal qtxmsr = new BigDecimal(0);
		BigDecimal qtxmzc = new BigDecimal(0);
		BigDecimal qtxmjsr = new BigDecimal(0);


		if (nhPjsxxxList.size()!= 0){
			NhPjsxxx nhPjsxxx = nhPjsxxxList.get(0);
			pjsxxxJSON = JSON.parseObject(JSON.toJSONString(nhPjsxxx));
			pjsxxxJSON.put("shpj", nhPjsxxx.getNhshpj() == null ? "" : sysDictService.queryDictTextByKey("khgl_shpj", nhPjsxxx.getNhshpj()));
			jydqjz = stringToBigDecimal(nhPjsxxx.getJydqjz());
			jtgjjz = stringToBigDecimal(nhPjsxxx.getJtgjjz());
			qtzcjz = stringToBigDecimal(nhPjsxxx.getQtzcjz());
			bxtjkje = stringToBigDecimal(nhPjsxxx.getBxtjksl());
			thjkje = stringToBigDecimal(nhPjsxxx.getThjksl());
			xykjkje = stringToBigDecimal(nhPjsxxx.getXyksl());
			qtjkje = stringToBigDecimal(nhPjsxxx.getQtfzsl());
			zzxmsr = stringToBigDecimal(nhPjsxxx.getZzxmsr());
			zzxmzc = stringToBigDecimal(nhPjsxxx.getZzxmzc());
			zzxmjsr = stringToBigDecimal(nhPjsxxx.getZzxmjsr());
			yzxmsr = stringToBigDecimal(nhPjsxxx.getYzxmsr());
			yzxmzc = stringToBigDecimal(nhPjsxxx.getYzxmzc());
			yzxmjsr = stringToBigDecimal(nhPjsxxx.getYzxmjsr());
			syxmsr = stringToBigDecimal(nhPjsxxx.getSyxmsr());
			syxmzc = stringToBigDecimal(nhPjsxxx.getSyxmzc());
			syxmjsr = stringToBigDecimal(nhPjsxxx.getSyxmjsr());
			lwxmsr = stringToBigDecimal(nhPjsxxx.getNwxmsr());
			lwxmzc = stringToBigDecimal(nhPjsxxx.getNwxmzc());
			lwxmjsr = stringToBigDecimal(nhPjsxxx.getNwxmjsr());
			qtxmsr = stringToBigDecimal(nhPjsxxx.getQtxmsr());
			qtxmzc = stringToBigDecimal(nhPjsxxx.getQtxmzc());
			qtxmjsr = stringToBigDecimal(nhPjsxxx.getQtxmjsr());
		}
		//查询房产信息
		Nhfcxx nhfcxxQuery = new Nhfcxx();
		nhfcxxQuery.setHhbm(hhbm);
		QueryWrapper nhfcxxQueryWrapper = QueryGenerator.initQueryWrapper(nhfcxxQuery, map);
		List<Nhfcxx> nhfcxxList = nhfcxxService.list(nhfcxxQueryWrapper);
		BigDecimal fcjzSum = new BigDecimal(0);
		if (nhfcxxList.size() != 0) {
			pjsxxxJSON.put("fcsl", nhfcxxList.size());
			for (Nhfcxx nhfcxx : nhfcxxList) {
				BigDecimal fcjz = nhfcxx.getFcjz();
				if (fcjz != null) {
					fcjzSum = fcjzSum.add(fcjz);
				}
			}
			pjsxxxJSON.put("fcjz", fcjzSum);
		}
		BigDecimal zcjzSum = new BigDecimal(0);
		BigDecimal fzSum = new BigDecimal(0);
		BigDecimal srSum = new BigDecimal(0);
		BigDecimal zcSum = new BigDecimal(0);
		BigDecimal jsrSum = new BigDecimal(0);

		zcjzSum = jydqjz.add(jtgjjz).add(qtzcjz).add(fcjzSum);
		fzSum = bxtjkje.add(thjkje).add(xykjkje).add(qtjkje);
		srSum = zzxmsr.add(yzxmsr).add(syxmsr).add(lwxmsr).add(qtxmsr);
		zcSum = zzxmzc.add(yzxmzc).add(syxmzc).add(lwxmzc).add(qtxmzc);
		jsrSum = zzxmjsr.add(yzxmjsr).add(syxmjsr).add(lwxmjsr).add(qtxmjsr);

		pjsxxxJSON.put("zcjzSum", zcjzSum);
		pjsxxxJSON.put("fzSum", fzSum);
		pjsxxxJSON.put("srSum", srSum);
		pjsxxxJSON.put("zcSum", zcSum);
		pjsxxxJSON.put("jsrSum", jsrSum);

		//循环转换
		for (Map.Entry<String, Object> entry : pjsxxxJSON.entrySet()) {
			nhxxMap.put(entry.getKey(), entry.getValue());
		}

		dataMap.put("nd", DateUtil.format(new Date(), "yyyy"));
		dataMap.put("hhbm", hhbm);
		dataMap.put("ssxz_dictText", ssxz);
		dataMap.put("xzc_dictText", xzc);
		dataMap.put("xzz_dictText", xzz);

		dataMap.put("nhxxMap", nhxxMap);
		String port = environment.getProperty("common.path.export");
		mv.addObject(JxlsConstants.FILE_NAME, "农户评议授信信息表");
		mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("农户评议授信信息表.xls"));
		mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/农户评议授信信息表.xls");
		mv.addObject(JxlsConstants.MAP_DATA, dataMap);

		return mv;
	}

	/**
	 * 农户基本信息查看
	 * @param hhbm
	 * @return
	 */
	@GetMapping(value = "/querynhpssxxx")
	public Result<?> querynhpssxxx(@RequestParam(name = "hhbm",required = true) String hhbm) {
		JSONObject jsonObject = new JSONObject();
		Map<String,String[]> map = new HashMap<>();
		try {
			//查询家庭成员信息
			KhglKhhmcxx khhmcxx = new KhglKhhmcxx();
			khhmcxx.setHhbm(hhbm);
			QueryWrapper<KhglKhhmcxx> queryWrapper =  QueryGenerator.initQueryWrapper(khhmcxx,map);
			List<KhglKhhmcxx> khglKhhmcxxList  = khglKhhmcxxService.list(queryWrapper);

			NhPjsxxx nhPjsxxx = new NhPjsxxx();
			nhPjsxxx.setHhbm(hhbm);
			QueryWrapper<NhPjsxxx> queryWrapper1 = QueryGenerator.initQueryWrapper(nhPjsxxx,map);
			jsonObject.put("jcxx",nhPjsxxxService.getOne(queryWrapper1));
			NhPjsxxx nhPjsxxx1 = nhPjsxxxService.getOne(queryWrapper1);

			//查询易变现资产
			Double njjjz = 0.0;
			Double jydqjz = 0.0;
			Double jtgjjz = 0.0;
			Double gqjz = 0.0;
			if(nhPjsxxx1!=null) {
				gqjz = Double.valueOf(nhPjsxxx1.getGqjz() == null ? "0" : nhPjsxxx1.getGqjz());
				njjjz = Double.valueOf(nhPjsxxx1.getNjjjz() == null ? "0" : nhPjsxxx1.getNjjjz());
				jydqjz = Double.valueOf(nhPjsxxx1.getJydqjz() == null ? "0" : nhPjsxxx1.getJydqjz());
				jtgjjz = Double.valueOf(nhPjsxxx1.getJtgjjz() == null ? "0" : nhPjsxxx1.getJtgjjz());
			}
			jsonObject.put("ybxzc", gqjz);

			//查询不易变现资产
			Nhfcxx nhfcxx =  nhfcxxService.selectFcjz(hhbm);
			Double fczchj = 0.0;
			if (nhfcxx != null){
				fczchj = nhfcxx.getFcjz() == null ? 0 : nhfcxx.getFcjz().doubleValue();;
			}
			double bybxzc =fczchj + njjjz + jydqjz + jtgjjz;
			jsonObject.put("bybxzc", bybxzc);

			for (KhglKhhmcxx khglKhhmcxx : khglKhhmcxxList) {
				khglKhhmcxx.setXb(khglKhhmcxx.getXb() == null ? "" : sysDictService.queryDictTextByKey("sex",khglKhhmcxx.getXb()));
				khglKhhmcxx.setHyzk(khglKhhmcxx.getHyzk() == null ? "" : sysDictService.queryDictTextByKey("bkbpy_hyzk",khglKhhmcxx.getHyzk()));
				khglKhhmcxx.setYhzgx(khglKhhmcxx.getYhzgx() == null ? "" : sysDictService.queryDictTextByKey("yhzgx",khglKhhmcxx.getYhzgx()));
			}
			jsonObject.put("jtcyxx",khglKhhmcxxList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.ok(jsonObject);
	}

	/**
	 * 农户评议信息明细-附件展示
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/AnnexList",method = RequestMethod.GET)
	public Result<?> AnnexList(@RequestParam(name = "zjhm",required = true) String param) {
		JSONObject object = new JSONObject();
		try {
			// 纸质评议-评议类型:1
			JSONArray typeOneFileList = new JSONArray();
			// 电话评议-评议类型:2
			JSONArray typeTwoFileList = new JSONArray();
			// 微信评议-评议类型:3
			JSONArray typeThreeFileList = new JSONArray();
			// 开会评议-评议类型:4
			JSONArray typeFourFileList = new JSONArray();
			// 电子评议-评议类型:5
			JSONArray typeFiveFileList = new JSONArray();
			Map<String, String[]> map = new HashMap<>();
			Pyfjxx check = new Pyfjxx();
			check.setZjhm(param);
			QueryWrapper<Pyfjxx> queryWrapper = QueryGenerator.initQueryWrapper(check, map);
			List<Pyfjxx> pyfjxxList = iPyfjxxService.list(queryWrapper);
			for (Pyfjxx pyfjxx : pyfjxxList) {
				if (pyfjxx.getPylx() != null) {
					JSONObject jsonObject = new JSONObject();
					if ("1".equalsIgnoreCase(pyfjxx.getPylx())){
						jsonObject.put("uid",pyfjxx.getZlxh());
						jsonObject.put("name",pyfjxx.getZlmc());
						jsonObject.put("status","done");
						jsonObject.put("url",pyfjxx.getFwlj());
						typeOneFileList.add(jsonObject);
					} else if ("2".equalsIgnoreCase(pyfjxx.getPylx())) {
						jsonObject.put("uid",pyfjxx.getZlxh());
						jsonObject.put("name",pyfjxx.getZlmc());
						jsonObject.put("status","done");
						jsonObject.put("url",pyfjxx.getFwlj());
						typeTwoFileList.add(jsonObject);
					} else if ("3".equalsIgnoreCase(pyfjxx.getPylx())) {
						jsonObject.put("uid",pyfjxx.getZlxh());
						jsonObject.put("name",pyfjxx.getZlmc());
						jsonObject.put("status","done");
						jsonObject.put("url",pyfjxx.getFwlj());
						typeThreeFileList.add(jsonObject);
					} else if ("4".equalsIgnoreCase(pyfjxx.getPylx())) {
						jsonObject.put("uid",pyfjxx.getZlxh());
						jsonObject.put("name",pyfjxx.getZlmc());
						jsonObject.put("status","done");
						jsonObject.put("url",pyfjxx.getFwlj());
						typeFourFileList.add(jsonObject);
					} else if ("5".equalsIgnoreCase(pyfjxx.getPylx())) {
						jsonObject.put("uid",pyfjxx.getZlxh());
						jsonObject.put("name",pyfjxx.getZlmc());
						jsonObject.put("status","done");
						jsonObject.put("url",pyfjxx.getFwlj());
						typeFiveFileList.add(jsonObject);
					}
				}
			}
			object.put("typeOneFileList",typeOneFileList);
			object.put("typeTwoFileList",typeTwoFileList);
			object.put("typeThreeFileList",typeThreeFileList);
			object.put("typeFourFileList",typeFourFileList);
			object.put("typeFiveFileList",typeFiveFileList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.ok(object);
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

	/**
	 * 农户评议信息（评议附件）：评议表
	 * @param zjhm
	 * @param pylx
	 * @param zllx
	 * @return
	 */
	@RequestMapping(value = "/SenateTableList",method = RequestMethod.GET)
	public Result<?> SenateTableList(@RequestParam(name = "zjhm",required = true)String zjhm,
									 @RequestParam(name = "pylx",required = true)String pylx,
									 @RequestParam(name = "zllx",required = true)String zllx) {
		JSONObject object = new JSONObject();
		try {
			JSONArray SenateTableFileList = new JSONArray();
			QueryWrapper<Pyfjxx> queryWrapper = new QueryWrapper<Pyfjxx>();
			queryWrapper.eq("zjhm",zjhm).eq("pylx",pylx).eq("zllx",zllx);
			List<Pyfjxx> returnList = iPyfjxxService.list(queryWrapper);
			for (Pyfjxx pyfjxx : returnList) {
				if (returnList != null && returnList.size() > 0) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("uid",pyfjxx.getZlxh());
					jsonObject.put("name",pyfjxx.getZlmc());
					jsonObject.put("status","done");
					jsonObject.put("url",pyfjxx.getFwlj());
					SenateTableFileList.add(jsonObject);
				}
			}
			object.put("SenateTableFileList",SenateTableFileList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询评议附件：评议表失败！");
		}
		return Result.ok(object);
	}


	/*------------------------------------------修改-----------------------------------------------*/

	/*
   通过户号编码去花名册查询家庭信息
    */
	@GetMapping(value = "/queryHzxxByHhbm")
	public Result<?> queryHzxxByHhbm(@Param("hhbm") String hhbm) {
		try {
			if (hhbm != null) {
				QueryWrapper<vKhglNhhzxxgl> khhmcQueryWrapper = new QueryWrapper<>();
				khhmcQueryWrapper.eq("hhbm", hhbm);
				List<vKhglNhhzxxgl> list = vKhglNhhzxxgl12.list(khhmcQueryWrapper);
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
			QueryWrapper<KhglKhhmcxx> khhmcQueryWrapper = new QueryWrapper<>();
			khhmcQueryWrapper.eq("hhbm", hhbm);
			khhmcQueryWrapper.orderByAsc("yhzgx");
			List<KhglKhhmcxx> list = khglKhhmcxxService.list(khhmcQueryWrapper);
			List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
			for (int i = 0; i < list.size(); i++) {
				NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
				BeanUtils.copyProperties(list.get(i), nhJtcyxx1);
				String yhzgx = nhJtcyxx1.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", nhJtcyxx1.getYhzgx());
				nhJtcyxx1.setYhzgx(yhzgx);
				String xb =nhJtcyxx1.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", nhJtcyxx1.getXb());
				nhJtcyxx1.setXb(xb);
				nhJtcyxx.add(nhJtcyxx1);
				List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
				if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
					if (nhJtcyxx.size() > 0) {
						ywhywwlxxes.get(0).setId(list.get(i).getId());
						BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i));
					}
				}
			}
			if (list != null && list.size() > 0) {
				return Result.ok(nhJtcyxx);
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
				CamsZcsxNhpjsxxxPad cms = new CamsZcsxNhpjsxxxPad();
				QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("hhbm", hhbm);
				List<CamsZcsxNhpjsxxxPad> list = iCamsZcsxNhpjsxxxPadService.list(queryWrapper);
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
				QueryWrapper<CamsZcsxNhfcxxPad> khhmcQueryWrapper = new QueryWrapper<>();
				khhmcQueryWrapper.eq("hhbm", hhbm);
				List<CamsZcsxNhfcxxPad> list = camsZcsxNhfcxxPad.list(khhmcQueryWrapper);
				if (list.size() > 0) {
					return Result.ok(list);
				}else {
					List<Nhbkbpyfsxx> list1 =  khglNhhzxxglService.selectpyxx(hhbm);
					return Result.ok(list1);
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
			QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hhbm", hhbm);
			List<CamsZcsxNhpjsxxxPad> nhPjsxxxList = iCamsZcsxNhpjsxxxPadService.list(queryWrapper);
			if (nhPjsxxxList == null || nhPjsxxxList.size()<=0) {
				List<Nhbkbpyfsxx> list1 =  khglNhhzxxglService.selectpyxx(hhbm);
				if (list1 == null || list1.size()<=0) {
					return Result.ok("没有数据");
				}else {
					for (Nhbkbpyfsxx nhbkbpyfsxx : list1) {
						//资产情况
						JSONObject jo = new JSONObject();
						jo.put("zclx", "股权");
						jo.put("zcsl", "");
						jo.put("zcjz", "");
						jo.put("zcsm", "");
						JSONObject jo1 = new JSONObject();
						jo1.put("zclx", "农机具");
						jo1.put("zcsl", "");
						jo1.put("zcjz", nhbkbpyfsxx.getNjjqk());
						jo1.put("zcsm", "");
						JSONObject jo2 = new JSONObject();
						jo2.put("zclx", "家用电器");
						jo2.put("zcsl", "");
						jo2.put("zcjz", "");
						jo2.put("zcsm", "");
						JSONObject jo3 = new JSONObject();
						jo3.put("zclx", "交通工具");
						jo3.put("zcsl", "");
						jo3.put("zcjz", nhbkbpyfsxx.getJtysgjqk());
						jo3.put("zcsm","");
						JSONObject jo5 = new JSONObject();
						jo5.put("zclx", "种植成品");
						jo5.put("zcsl", "");
						jo5.put("zcjz", "");
						jo5.put("zcsm", "");
						JSONObject jo6 = new JSONObject();
						jo6.put("zclx", "养殖成品");
						jo6.put("zcsl", "");
						jo6.put("zcjz", "");
						jo6.put("zcsm", "");
						JSONObject jo4 = new JSONObject();
						jo4.put("zclx", "其他资产");
						jo4.put("zcsl", "");
						jo4.put("zcjz", nhbkbpyfsxx.getDznyxfpqk());
						jo4.put("zcsm", "");
						jsonArray.add(jo);
						jsonArray.add(jo1);
						jsonArray.add(jo2);
						jsonArray.add(jo3);
						jsonArray.add(jo4);
						jsonArray.add(jo5);
						jsonArray.add(jo6);
					}
					return Result.ok(jsonArray);
				}
			} else {
				for (CamsZcsxNhpjsxxxPad pjsxxx : nhPjsxxxList) {
					//资产情况
					JSONObject jo = new JSONObject();
					jo.put("zclx", "股权");
					jo.put("zcsl", pjsxxx.getGqsl());
					jo.put("zcjz", pjsxxx.getGqjz());
					jo.put("zcsm", pjsxxx.getGqxqsm());
					JSONObject jo1 = new JSONObject();
					jo1.put("zclx", "农机具");
					jo1.put("zcsl", pjsxxx.getNjjsl());
					jo1.put("zcjz", pjsxxx.getNjjjz());
					jo1.put("zcsm", pjsxxx.getNjjqxsm());
					JSONObject jo2 = new JSONObject();
					jo2.put("zclx", "家用电器");
					jo2.put("zcsl", pjsxxx.getJydqsl());
					jo2.put("zcjz", pjsxxx.getJydqjz());
					jo2.put("zcsm", pjsxxx.getJydqxqsm());
					JSONObject jo3 = new JSONObject();
					jo3.put("zclx", "交通工具");
					jo3.put("zcsl", pjsxxx.getJtgjsl());
					jo3.put("zcjz", pjsxxx.getJtgjjz());
					jo3.put("zcsm", pjsxxx.getJtgjxqsm());
					JSONObject jo5 = new JSONObject();
					jo5.put("zclx", "种植成品");
					jo5.put("zcsl", pjsxxx.getZzcpsl());
					jo5.put("zcjz", pjsxxx.getZzcpjz());
					jo5.put("zcsm", pjsxxx.getZzcpxqsm());
					JSONObject jo6 = new JSONObject();
					jo6.put("zclx", "养殖成品");
					jo6.put("zcsl", pjsxxx.getYzcpsl());
					jo6.put("zcjz", pjsxxx.getYzcpjz());
					jo6.put("zcsm", pjsxxx.getYzcpxqsm());
					JSONObject jo4 = new JSONObject();
					jo4.put("zclx", "其他资产");
					jo4.put("zcsl", pjsxxx.getQtzcsl());
					jo4.put("zcjz", pjsxxx.getQtzcjz());
					jo4.put("zcsm", pjsxxx.getQtzcxqsm());
					jsonArray.add(jo);
					jsonArray.add(jo1);
					jsonArray.add(jo2);
					jsonArray.add(jo3);
					jsonArray.add(jo4);
					jsonArray.add(jo5);
					jsonArray.add(jo6);
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
			QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hhbm", hhbm);
			List<CamsZcsxNhpjsxxxPad> nhPjsxxxList = iCamsZcsxNhpjsxxxPadService.list(queryWrapper);
			if (nhPjsxxxList == null || nhPjsxxxList.size()<=0) {
				List<Nhbkbpyfsxx> list1 =  khglNhhzxxglService.selectpyxx(hhbm);
				if (list1 == null || list1.size()<=0) {
					return Result.ok("没有数据");
				}else {
					for (Nhbkbpyfsxx nhbkbpyfsxx : list1) {
						JSONObject jo1 = new JSONObject();
						jo1.put("jkfs", "本系统");
						jo1.put("zqr", "");
						jo1.put("jkje", "");
						jo1.put("jksm", "");
						JSONObject jo2 = new JSONObject();
						jo2.put("jkfs", "他行");
						jo2.put("zqr", "");
						jo2.put("jkje", "");
						jo2.put("jksm", "");
						JSONObject jo3 = new JSONObject();
						jo3.put("jkfs", "信用卡");
						jo3.put("zqr", "");
						jo3.put("jkje", "");
						jo3.put("jksm", "");
						JSONObject jo4 = new JSONObject();
						jo4.put("jkfs", "其他");
						jo4.put("zqr", "");
						jo4.put("jkje", "");
						/*jo4.put("jkje", nhbkbpyfsxx.getDznyxfpqk());*/
						jo4.put("jksm", "");
						jsonArray.add(jo1);
						jsonArray.add(jo2);
						jsonArray.add(jo3);
						jsonArray.add(jo4);

					}
					return Result.ok(jsonArray);
				}
			} else {
				for (CamsZcsxNhpjsxxxPad pjsxxx : nhPjsxxxList) {
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
					jsonArray.add(jo1);
					jsonArray.add(jo2);
					jsonArray.add(jo3);
					jsonArray.add(jo4);
				}
				return Result.ok(jsonArray);
			}
		} catch (Exception e) {
			return Result.error(e.toString());
		}
	}


	@RequestMapping(value = "/queryPjsxJyqkTable", method = RequestMethod.GET)
	public Result<?> queryPjsxJyqkTable(@Param("hhbm") String hhbm) {
		try {
			LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
			JSONArray jsonArray = new JSONArray();
			QueryWrapper<CamsZcsxNhpjsxxxPad> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("hhbm", hhbm);
			List<CamsZcsxNhpjsxxxPad> nhPjsxxxList = iCamsZcsxNhpjsxxxPadService.list(queryWrapper);
			if (nhPjsxxxList == null || nhPjsxxxList.size()<=0) {
				List<Nhbkbpyfsxx> list1 =  khglNhhzxxglService.selectpyxx(hhbm);
				if (list1 == null || list1.size()<=0) {
					return Result.ok("没有数据");
				}else {
					for (Nhbkbpyfsxx nhbkbpyfsxx : list1) {
						JSONObject jo1 = new JSONObject();
						jo1.put("xmlx", "种植");
						jo1.put("xmqk", "");
						jo1.put("xmnsr", "");
						jo1.put("xmnzc", "");
						JSONObject jo2 = new JSONObject();
						jo2.put("xmlx", "养殖");
						jo2.put("xmqk", "");
						jo2.put("xmnsr", "");
						jo2.put("xmnzc", "");
						JSONObject jo3 = new JSONObject();
						jo3.put("xmlx", "商业");
						jo3.put("xmqk", "");
						jo3.put("xmnsr", "");
						jo3.put("xmnzc", "");
						JSONObject jo4 = new JSONObject();
						jo4.put("xmlx", "劳务");
						jo4.put("xmnsr", "");
						jo4.put("xmqk", "");
						jo4.put("xmnzc", "");
						JSONObject jo6 = new JSONObject();
						jo6.put("xmlx", "租金");
						jo6.put("xmnsr", "");
						jo6.put("xmqk", "");
						jo6.put("xmnzc", "");
						JSONObject jo7 = new JSONObject();
						jo7.put("xmlx", "国家补贴");
						jo7.put("xmnsr", "");
						jo7.put("xmqk", "");
						jo7.put("xmnzc", "");
						JSONObject jo5 = new JSONObject();
						jo5.put("xmlx", "其他");
						jo5.put("xmnsr", nhbkbpyfsxx.getJtcsrqk());
						jo5.put("xmqk", "");
						jo5.put("xmnzc", "");
						jsonArray.add(jo1);
						jsonArray.add(jo2);
						jsonArray.add(jo3);
						jsonArray.add(jo4);
						jsonArray.add(jo5);
						jsonArray.add(jo6);
						jsonArray.add(jo7);
					}
					return Result.ok(jsonArray);
				}
			} else {
				for (CamsZcsxNhpjsxxxPad pjsxxx : nhPjsxxxList) {
					//资产情况
					JSONObject jo1 = new JSONObject();
					jo1.put("xmlx", "种植");
					jo1.put("xmqk", pjsxxx.getZzxmqk());
					jo1.put("xmnsr", pjsxxx.getZzxmsr());
					jo1.put("xmnzc", pjsxxx.getZzxmzc());
					JSONObject jo2 = new JSONObject();
					jo2.put("xmlx", "养殖");
					jo2.put("xmqk", pjsxxx.getYzxmqk());
					jo2.put("xmnsr", pjsxxx.getYzxmsr());
					jo2.put("xmnzc", pjsxxx.getYzxmzc());
					JSONObject jo3 = new JSONObject();
					jo3.put("xmlx", "商业");
					jo3.put("xmqk", pjsxxx.getSyxmqk());
					jo3.put("xmnsr", pjsxxx.getSyxmsr());
					jo3.put("xmnzc", pjsxxx.getSyxmzc());
					JSONObject jo4 = new JSONObject();
					jo4.put("xmlx", "劳务");
					jo4.put("xmnsr", pjsxxx.getNwxmsr());
					jo4.put("xmqk", pjsxxx.getNwxmqk());
					jo4.put("xmnzc", pjsxxx.getNwxmzc());
					JSONObject jo6 = new JSONObject();
					jo6.put("xmlx", "租金");
					jo6.put("xmnsr", pjsxxx.getZjxmsr());
					jo6.put("xmqk", pjsxxx.getZjxmqk());
					jo6.put("xmnzc", pjsxxx.getZjxmzc());
					JSONObject jo7 = new JSONObject();
					jo7.put("xmlx", "国家补贴");
					jo7.put("xmnsr", pjsxxx.getGjbtxmsr());
					jo7.put("xmqk", pjsxxx.getGjbtxmqk());
					jo7.put("xmnzc", pjsxxx.getGjbtxmzc());
					JSONObject jo5 = new JSONObject();
					jo5.put("xmlx", "其他");
					jo5.put("xmnsr", pjsxxx.getQtxmsr());
					jo5.put("xmqk", pjsxxx.getQtxmqk());
					jo5.put("xmnzc", pjsxxx.getQtxmzc());
					jsonArray.add(jo1);
					jsonArray.add(jo2);
					jsonArray.add(jo3);
					jsonArray.add(jo4);
					jsonArray.add(jo5);
					jsonArray.add(jo6);
					jsonArray.add(jo7);
				}
				return Result.ok(jsonArray);
			}
		} catch (Exception e) {
			return Result.error(e.toString());
		}
	}

	//根据证件号码查询个人信息
	@GetMapping(value = "/queryNhjbxx")
	public Result<?> queryShjbxx(@Param("zjhm") String zjhm) {
		QueryWrapper<VKhglNhjbxx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zjhm",zjhm);
		VKhglNhjbxx vKhglNhjbxx = ivKhglNhjbxxService.getOne(queryWrapper);
		return Result.ok(vKhglNhjbxx);
	}

	@GetMapping(value = "/queryByZjhm")
	public Result<?> queryByZjhm(@Param("zjhm") String zjhm) {
		try {
			QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<>();
			nhxqQueryWrapper.eq("zjhm", zjhm);
			nhxqQueryWrapper.orderByAsc("yhzgx");
			List<Nhxq> list = nhxqService.list(nhxqQueryWrapper);
			List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
			for (int i = 0; i < list.size(); i++) {
				NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
				BeanUtils.copyProperties(list.get(i), nhJtcyxx1);
				nhJtcyxx.add(nhJtcyxx1);
				List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
				if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
					if (nhJtcyxx.size() > 0) {
						ywhywwlxxes.get(0).setId(list.get(i).getId());
						BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i));
					}
				}
			}
			if (list != null && list.size() > 0) {
				return Result.ok(nhJtcyxx);
			}
		}catch (Exception e){
			e.printStackTrace();
			return  Result.error(e.toString());
		}
		return Result.ok("没有数据");
	}
}
