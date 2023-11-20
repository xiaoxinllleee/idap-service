package org.cmms.modules.khgl.khxx.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.khgl.khxx.entity.*;
import org.cmms.modules.khgl.khxx.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.nh.entity.*;
import org.cmms.modules.khgl.nh.service.ICamsZcsxNhcjxxService;
import org.cmms.modules.khgl.nh.service.IKhglKhhmcxxService;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khgl.sh.entity.Sjyh;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 客户信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户信息管理")
@RestController
@RequestMapping("/khgl.khxx/vKhglKhjbxx")
public class vKhglKhjbxxController extends JeecgController<vKhglKhjbxx, IvKhglKhjbxxService> {
	 @Autowired
	 private IvKhglKhjbxxService vKhglKhjbxxService;
	 @Autowired
	 private ICamsZcsxNhcjxxService camsZcsxNhcjxxService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;
	 @Autowired
	 private IKhglKhhmcxxService khglKhhmcxxService;
	 @Autowired
	 private ISysDictService sysDictService;
	 @Autowired
	 private IYwhywwlxxService ywhywwlxxService;
	 @Autowired
	 private IKhywxxDksjmxPcService khywxxDksjmxService;
	 @Autowired
	 private IKhywxxEtcPcService khywxxEtcService;
	 @Autowired
	 private IKhywxxSjyhPcService khywxxSjyhService;
	 @Autowired
	 private IKhywxxWsyhPcService khywxxWsyhService;
	 @Autowired
	 private IKhywxxXjlghjcService khywxxXjlghjcService;
	 @Autowired
	 private IKhywxxZjghlService khywxxZjghlService;
	 @Autowired
	 private Environment environment;


	 /**
	 * 分页列表查询
	 *
	 * @param vKhglKhjbxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户信息管理-分页列表查询")
	@ApiOperation(value="客户信息管理-分页列表查询", notes="客户信息管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(vKhglKhjbxx vKhglKhjbxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<vKhglKhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(vKhglKhjbxx, req.getParameterMap());
		Page<vKhglKhjbxx> page = new Page<vKhglKhjbxx>(pageNo, pageSize);
		IPage<vKhglKhjbxx> pageList = vKhglKhjbxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param vKhglKhjbxx
	 * @return
	 */
	@AutoLog(value = "客户信息管理-添加")
	@ApiOperation(value="客户信息管理-添加", notes="客户信息管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody vKhglKhjbxx vKhglKhjbxx) {
		vKhglKhjbxxService.save(vKhglKhjbxx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param vKhglKhjbxx
	 * @return
	 */
	@AutoLog(value = "客户信息管理-编辑")
	@ApiOperation(value="客户信息管理-编辑", notes="客户信息管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody vKhglKhjbxx vKhglKhjbxx) {
		vKhglKhjbxxService.updateById(vKhglKhjbxx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息管理-通过id删除")
	@ApiOperation(value="客户信息管理-通过id删除", notes="客户信息管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vKhglKhjbxxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户信息管理-批量删除")
	@ApiOperation(value="客户信息管理-批量删除", notes="客户信息管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vKhglKhjbxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息管理-通过id查询")
	@ApiOperation(value="客户信息管理-通过id查询", notes="客户信息管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		vKhglKhjbxx vKhglKhjbxx = vKhglKhjbxxService.getById(id);
		return Result.ok(vKhglKhjbxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vKhglKhjbxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, vKhglKhjbxx vKhglKhjbxx) {
      return super.exportXls(request, vKhglKhjbxx, vKhglKhjbxx.class, "客户信息管理");
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
      return super.importExcel(request, response, vKhglKhjbxx.class);
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
			 //查询业务信息
			 Ywhywwlxx ywhywwlxxQuery = new Ywhywwlxx();
			 ywhywwlxxQuery.setZjhm(khhmcxx.getZjhm());
			 Map<String, String[]> map1 = new HashMap<>();
			 QueryWrapper<Ywhywwlxx> queryWrapper1 = QueryGenerator.initQueryWrapper(ywhywwlxxQuery, map1);
			 List<Ywhywwlxx> cyxx1 = ywhywwlxxService.list(queryWrapper1);
			 for (Ywhywwlxx ywhywwlxx : cyxx1) {
				 jo.put("ckye", ywhywwlxx.getCkye());
				 jo.put("ckrpye", ywhywwlxx.getCkrpye());
				 jo.put("dkye", ywhywwlxx.getDkye());
				 jo.put("bldkye", ywhywwlxx.getBldkye());
				 jo.put("bwbldkye",ywhywwlxx.getBwbldkye());
			 }
			 json.add(jo);
			 result.setResult(json);
			 result.setSuccess(true);
		 }
		 return result;
	 }


	 /**
	  * 查询与我行相关业务
	  * @param
	  * @return
	  */
	 @ApiOperation(value="查询与我行相关业务", notes="查询与我行相关业务")
	 @RequestMapping(value = "queryYwhxgywxx",method = RequestMethod.GET)
	 public Result<?> shjbxxcx(@RequestParam(name = "zjhm",required = true) String zjhm) {
	 	JSONObject jsonObject = new JSONObject();
		 try {
		 	 //业务往来信息
		 	 QueryWrapper<Ywhywwlxx> queryWrapper = new QueryWrapper<>();
		 	 queryWrapper.eq("zjhm",zjhm);
		 	 List<Ywhywwlxx> ywhywwlxxList = ywhywwlxxService.list(queryWrapper);

		 	/*//业务往来信息
			 QueryWrapper<Ywhywwlxx> ywxxqueryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("zjhm",zjhm);
			 List<Ywhywwlxx> ywhywwlxxList = ywhywwlxxService.list(ywxxqueryWrapper);
*/
			 //贷款数据明细
		 	 QueryWrapper<KhywxxDksjmxPc> dksjmxQueryWrapper = new QueryWrapper<>();
			 dksjmxQueryWrapper.eq("zjhm",zjhm).gt("dkye",0);
			 List<KhywxxDksjmxPc> khywxxDksjmxList = khywxxDksjmxService.list(dksjmxQueryWrapper);
			 for (KhywxxDksjmxPc dksjmx : khywxxDksjmxList) {
				 dksjmx.setDyzrr(dksjmx.getDyzrr() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmx.getDyzrr()));
				 dksjmx.setKhjlbz(dksjmx.getKhjlbz() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmx.getKhjlbz()));
				 dksjmx.setDkpz(dksjmx.getDkpz() == null ? "" : sysDictService.queryDictTextByKey("dkzl", dksjmx.getDkpz()));
				 dksjmx.setDkxt(dksjmx.getDkxt() == null ? "" : sysDictService.queryDictTextByKey("dkxt", dksjmx.getDkxt()));
				 dksjmx.setDbfs(dksjmx.getDbfs() ==null ? ""  : sysDictService.queryDictTextByKey("dbfs", dksjmx.getDbfs()));
			 }

			 //贷款历史数据明细
			 QueryWrapper<KhywxxDksjmxPc> dklssjmxQueryWrapper = new QueryWrapper<>();
			 dklssjmxQueryWrapper.eq("zjhm",zjhm).le("dkye",0);
			 List<KhywxxDksjmxPc> khywxxDklssjmxList = khywxxDksjmxService.list(dklssjmxQueryWrapper);
			 for (KhywxxDksjmxPc dksjmx : khywxxDklssjmxList) {
				 dksjmx.setDyzrr(dksjmx.getDyzrr() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmx.getDyzrr()));
				 dksjmx.setKhjlbz(dksjmx.getKhjlbz() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmx.getKhjlbz()));
				 dksjmx.setDkpz(dksjmx.getDkpz() == null ? "" : sysDictService.queryDictTextByKey("dkzl", dksjmx.getDkpz()));
				 dksjmx.setDkxt(dksjmx.getDkxt() == null ? "" : sysDictService.queryDictTextByKey("dkxt", dksjmx.getDkxt()));
				 dksjmx.setDbfs(dksjmx.getDbfs() ==null ? ""  : sysDictService.queryDictTextByKey("dbfs", dksjmx.getDbfs()));
			 }

			 //手机银行信息
			 QueryWrapper<KhywxxSjyhPc> sjyhQueryWrapper = new QueryWrapper<>();
			 sjyhQueryWrapper.eq("zjhm",zjhm);
			 List<KhywxxSjyhPc> khywxxSjyhList = khywxxSjyhService.list(sjyhQueryWrapper);
			 for (KhywxxSjyhPc sjyh : khywxxSjyhList) {
				 sjyh.setCancelGyh(sjyh.getCancelGyh() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", sjyh.getCancelGyh()));
				 sjyh.setStatus(sjyh.getStatus() == null ? "" :sysDictService.queryDictTextByKey("khywxx_kxhzt", sjyh.getStatus()));
				 sjyh.setOpenType(sjyh.getOpenType() == null ? "" : sysDictService.queryDictTextByKey("sjyh_khlx", sjyh.getOpenType()));
			 }

			 //网上银行
			 QueryWrapper<KhywxxWsyhPc> wsyhQueryWrapper = new QueryWrapper<>();
			 wsyhQueryWrapper.eq("zjhm",zjhm);
			 List<KhywxxWsyhPc> khywxxWsyhPcList = khywxxWsyhService.list(wsyhQueryWrapper);

			 //ETC信息
			 QueryWrapper<KhywxxEtcPc> etcQueryWrapper = new QueryWrapper<>();
			 etcQueryWrapper.eq("zjhm",zjhm);
			 List<KhywxxEtcPc> khywxxEtcList = khywxxEtcService.list(etcQueryWrapper);

			 //现金流归行检测
			 QueryWrapper<KhywxxXjlghjc> xjlghjcQueryWrapper = new QueryWrapper<>();
			 xjlghjcQueryWrapper.eq("ident_no",zjhm);
			 List<KhywxxXjlghjc> khywxxXjlghjcList = khywxxXjlghjcService.list(xjlghjcQueryWrapper);

			 //资金归行率
			 QueryWrapper<KhywxxZjghl> zjghlQueryWrapper = new QueryWrapper<>();
			 zjghlQueryWrapper.eq("zjhm",zjhm);
			 List<KhywxxZjghl> khywxxZjghlList = khywxxZjghlService.list(zjghlQueryWrapper);


			 if (ywhywwlxxList.size() != 0){
				 jsonObject.put("ywhywwlxx",ywhywwlxxList.get(0));
			 }else {
			 	 JSONObject object = new JSONObject();
				 jsonObject.put("ywhywwlxx",object);
			 }
			 jsonObject.put("ywhywwlxxList",ywhywwlxxList);
			 jsonObject.put("khywxxDksjmxList",khywxxDksjmxList);
			 jsonObject.put("khywxxDklssjmxList",khywxxDklssjmxList);
			 jsonObject.put("khywxxSjyhList",khywxxSjyhList);
			 jsonObject.put("khywxxWsyhPcList",khywxxWsyhPcList);
			 jsonObject.put("khywxxEtcList",khywxxEtcList);
			 jsonObject.put("khywxxXjlghjcList",khywxxXjlghjcList);
			 jsonObject.put("khywxxZjghlList",khywxxZjghlList);

			 return Result.ok(jsonObject);
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
			 e.printStackTrace();
			 return Result.error(e.toString());
		 }
	 }



	 /**
	  * 导出导出信息模板excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(vKhglKhjbxx khglKhjbxx, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 JSONArray jsonArray = new JSONArray();
		 Map<String,String[]> map = new HashMap<>();
		 Map<String,Object> map1 = new HashMap<>();
		 String dz ="";
		 //查询家庭成员信息
		 QueryWrapper<KhglKhhmcxx> queryWrapper =  new QueryWrapper<>();
		 queryWrapper.eq("hhbm",khglKhjbxx.getHhbm());
		 List<KhglKhhmcxx> khglKhhmcxxList  = khglKhhmcxxService.list(queryWrapper);
		 for (KhglKhhmcxx khglKhhmcxx : khglKhhmcxxList) {
			 khglKhhmcxx.setYhzgx(khglKhhmcxx.getYhzgx()== null ? "" : sysDictService.queryDictTextByKey("yhzgx", khglKhhmcxx.getYhzgx()));
			 khglKhhmcxx.setHyzk(khglKhhmcxx.getHyzk()== null ? "" : sysDictService.queryDictTextByKey("hyzk", khglKhhmcxx.getHyzk()));
			 khglKhhmcxx.setKhlx(khglKhhmcxx.getKhlx()== null ? "" : sysDictService.queryDictTextByKey("khlx", khglKhhmcxx.getKhlx()));
			 khglKhhmcxx.setXb(khglKhhmcxx.getXb()== null ? "" : sysDictService.queryDictTextByKey("sex", khglKhhmcxx.getXb()));
			 if (khglKhhmcxx.getSfhz().equals("1")){
				 dz=khglKhhmcxx.getDz();
			 }

		 }
		 //查询业务信息
		 Map<String,Object> map2 = vKhglKhjbxxService.selectByHhbm(khglKhjbxx.getHhbm());
		 map1.put("dz",dz);
		 if(map2 != null){
		 map1.put("ckye",map2.get("CKYE"));
		 map1.put("hqckye",map2.get("HQCKYE"));
		 map1.put("dqckye",map2.get("DQCKYE"));
		 map1.put("ckzzkhrq",map2.get("CKZZKHRQ"));
		 map1.put("dkje",map2.get("DKJE"));
		 map1.put("dkye",map2.get("DKYE"));
		 map1.put("bldkye",map2.get("BLDKYE"));
		 map1.put("bwbldkye",map2.get("BWBLDKYE"));
		 }
		 map1.put("list", khglKhhmcxxList);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "客户信息导出");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("客户信息导出.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/客户信息导出.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map1);
		 return mv;
	 }

	 /**
	  * 导出导出信息批量模板excel
	  *
	  * @param request
	  * @param khglKhjbxx
	  */
	 @RequestMapping(value = "/batchExportTemplateXls")
	 public ModelAndView batchExportTemplateXls(HttpServletRequest request, vKhglKhjbxx khglKhjbxx) {
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Map<String,Object> map = new HashMap<>();

	 	 QueryWrapper<vKhglKhjbxx> queryWrapper = QueryGenerator.initQueryWrapper(khglKhjbxx,request.getParameterMap());
		 queryWrapper.orderByDesc("hhbm");
		 queryWrapper.orderByAsc("yhzgx");
		 List<vKhglKhjbxx> khglKhjbxxList = vKhglKhjbxxService.list(queryWrapper);
		 //遍历转换数据字典
		 //将list转换成户主list
		 KhglHzxx khglHzxx = new KhglHzxx();
		 String hhbm = "";
		 List<vKhglKhjbxx> list = new ArrayList<>();
		 List<KhglHzxx> khglHzxxList = new ArrayList<>();
		 for (vKhglKhjbxx vKhglKhjbxx : khglKhjbxxList) {
			 vKhglKhjbxx.setYhzgx(vKhglKhjbxx.getSfhz()== null ? "" : sysDictService.queryDictTextByKey("sfbz", vKhglKhjbxx.getSfhz()));
			 vKhglKhjbxx.setYhzgx(vKhglKhjbxx.getYhzgx()== null ? "" : sysDictService.queryDictTextByKey("yhzgx", vKhglKhjbxx.getYhzgx()));
			 vKhglKhjbxx.setXzc(vKhglKhjbxx.getXzc()== null ? "" : sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl","dymc", "dybh", vKhglKhjbxx.getXzc()));
			 vKhglKhjbxx.setYhzgx(vKhglKhjbxx.getYhzgx()== null ? "" : sysDictService.queryDictTextByKey("yhzgx", vKhglKhjbxx.getYhzgx()));
			 vKhglKhjbxx.setXzc(vKhglKhjbxx.getXzc()== null ? "" : sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl","dymc", "dybh", vKhglKhjbxx.getXzc()));
			 if(StringUtils.isEmpty(hhbm)) {
				 hhbm = vKhglKhjbxx.getHhbm();
				 if ("1".equalsIgnoreCase(vKhglKhjbxx.getYhzgx())) {
					 khglHzxx.setHzxm(vKhglKhjbxx.getKhmc());
				 }
				 list.add(vKhglKhjbxx);
			 } else if(!hhbm.equalsIgnoreCase(vKhglKhjbxx.getHhbm())) {
			 	 khglHzxx.setKhjbxxList(list);
				 khglHzxxList.add(khglHzxx);
				 hhbm = vKhglKhjbxx.getHhbm();
				 khglHzxx = new KhglHzxx();
				 if ("1".equalsIgnoreCase(vKhglKhjbxx.getYhzgx())) {
					 khglHzxx.setHzxm(vKhglKhjbxx.getKhmc());
				 }
				 list = new ArrayList<>();
				 list.add(vKhglKhjbxx);
			 } else {
				 if ("1".equalsIgnoreCase(vKhglKhjbxx.getYhzgx())) {
					 khglHzxx.setHzxm(vKhglKhjbxx.getKhmc());
				 }
				 list.add(vKhglKhjbxx);
			 }
//			 vKhglKhjbxx.setYhzgx(vKhglKhjbxx.getYhzgx()== null ? "" : sysDictService.queryDictTextByKey("yhzgx", vKhglKhjbxx.getYhzgx()));
//			 vKhglKhjbxx.setXzc(vKhglKhjbxx.getXzc()== null ? "" : sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl","dymc", "dybh", vKhglKhjbxx.getXzc()));
		 }
		 SimpleDateFormat dft = new SimpleDateFormat("yyyy");
		 String nd = dft.format(new Date());
		 map.put("nd",nd);
		 map.put("list",khglHzxxList);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "助力乡村振兴入户走访台账");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("助力乡村振兴入户走访台账.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/助力乡村振兴入户走访台账.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
		 return mv;
	 }

}
