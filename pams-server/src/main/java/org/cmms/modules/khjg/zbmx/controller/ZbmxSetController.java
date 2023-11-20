package org.cmms.modules.khjg.zbmx.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.aspectj.util.LangUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.base.entity.CkmxTable;
import org.cmms.common.system.base.entity.DkmxTable;
import org.cmms.common.system.base.entity.SimpleStandardTable;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.khjg.jgkhjg.entity.Jgkhjg;
import org.cmms.modules.khjg.jgkhjg.service.IJgkhjgService;
import org.cmms.modules.khjg.zbmx.entity.ZbmxSet;
import org.cmms.modules.khjg.zbmx.service.IZbmxSetService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 指标明细设置
 * @Author: jeecg-boot
 * @Date:   2023-04-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="指标明细设置")
@RestController
@RequestMapping("/zbmx/zbmxSet")
public class ZbmxSetController extends JeecgController<ZbmxSet, IZbmxSetService> {
	@Autowired
	private IZbmxSetService zbmxSetService;
	 @Autowired
	 private IJgkhjgService jgkhjgService;
	 @Autowired
	 private ISysDictService sysDictService;
	/**
	 * 分页列表查询
	 *
	 * @param zbmxSet
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "指标明细设置-分页列表查询")
	@ApiOperation(value="指标明细设置-分页列表查询", notes="指标明细设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZbmxSet zbmxSet,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZbmxSet> queryWrapper = QueryGenerator.initQueryWrapper(zbmxSet, req.getParameterMap());
		Page<ZbmxSet> page = new Page<ZbmxSet>(pageNo, pageSize);
		IPage<ZbmxSet> pageList = zbmxSetService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zbmxSet
	 * @return
	 */
	@AutoLog(value = "指标明细设置-添加")
	@ApiOperation(value="指标明细设置-添加", notes="指标明细设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZbmxSet zbmxSet) {
		zbmxSetService.save(zbmxSet);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zbmxSet
	 * @return
	 */
	@AutoLog(value = "指标明细设置-编辑")
	@ApiOperation(value="指标明细设置-编辑", notes="指标明细设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZbmxSet zbmxSet) {
		zbmxSetService.updateById(zbmxSet);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标明细设置-通过id删除")
	@ApiOperation(value="指标明细设置-通过id删除", notes="指标明细设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zbmxSetService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "指标明细设置-批量删除")
	@ApiOperation(value="指标明细设置-批量删除", notes="指标明细设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zbmxSetService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 查询指标明细（通过set表中配置的sql查出明细数据）
	 * @return
	 */
	@AutoLog(value = "指标明细设置-通过id查询")
	@ApiOperation(value="指标明细设置-通过id查询", notes="指标明细设置-通过id查询")
	@PutMapping(value = "/queryByZbid")
	public Result<?> queryByZbid(@RequestBody Jgkhjg khjg) {
		QueryWrapper queryWrapper =new QueryWrapper();
		queryWrapper.eq("ZBID",khjg.getZbid());
		ZbmxSet zbmxSet = zbmxSetService.getOne(queryWrapper);
		if(zbmxSet!=null){
			if("2".equals(zbmxSet.getMxlx())){
				String sqlStr= replaceStringVal(zbmxSet.getJssql(), khjg);
				StringBuffer sqlBuf= new StringBuffer();
				sqlBuf.append("SELECT * FROM (  SELECT A.*, ROWNUM RN  FROM (" );
				sqlBuf.append(sqlStr);
				sqlBuf.append(") A  WHERE ROWNUM <= ").append(1*10).append(")");
				sqlBuf.append("WHERE RN >").append(0);
				System.out.println(sqlBuf.toString());

				StringBuffer sqlBufCount= new StringBuffer();
				sqlBufCount.append("SELECT count(1) FROM (" );
				sqlBufCount.append(sqlStr);
				sqlBufCount.append(") ");
				Long total = zbmxSetService.execCount(sqlBufCount.toString());

				List<LinkedHashMap> maps = zbmxSetService.execZbmx(sqlBuf.toString());
				JSONArray zbmxjg = JSONArray.parseArray(JSON.toJSONString(maps));
				JSONArray columns =new JSONArray();
				String zszd = zbmxSet.getZszd();
				String[] split = zszd.split(",");
				for(String zdxx:split){
					JSONObject column =new JSONObject();
					column.put("title",zdxx.split(":")[1]);
					column.put("dataIndex",zdxx.split(":")[0]);
					column.put("align","center");
					columns.add(column);
				}
				zbmxSet.setTotal(total);
				zbmxSet.setZszdxx(columns);
				zbmxSet.setZbmxjg(zbmxjg);
			}
			Result.ok(zbmxSet);
		}else{
			Result.error("暂没找到该指标的明细配置");
		}
		return Result.ok(zbmxSet);
	}

	 /**
	  * 指标明细分页查询
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "指标明细设置-分页列表查询")
	 @ApiOperation(value="指标明细设置-分页列表查询", notes="指标明细设置-分页列表查询")
	 @GetMapping(value = "/listZmbx")
	 public Result<?> listZmbx(Jgkhjg khjg,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {

		 IPage<Object> page = new Page<>(pageNo, pageSize);
		 JSONArray zbmxjg= new JSONArray();
		 QueryWrapper queryWrapper =new QueryWrapper();
		 queryWrapper.eq("ZBID",khjg.getZbid());
		 ZbmxSet zbmxSet = zbmxSetService.getOne(queryWrapper);
		 if(zbmxSet!=null){
			 if("2".equals(zbmxSet.getMxlx())){
				 String sqlStr= replaceStringVal(zbmxSet.getJssql(), khjg);
				 StringBuffer sqlBufCount= new StringBuffer();
				 sqlBufCount.append("SELECT count(1) FROM (" );
				 sqlBufCount.append(sqlStr);
				 sqlBufCount.append(") ");
				 Long total = zbmxSetService.execCount(sqlBufCount.toString());
				 page.setTotal(total);
				 StringBuffer sqlBuf= new StringBuffer();
				 sqlBuf.append("SELECT * FROM (  SELECT A.*, ROWNUM RN  FROM (" );
				 sqlBuf.append(sqlStr);
				 sqlBuf.append(") A  WHERE ROWNUM <= ").append(pageNo*pageSize).append(")");
				 sqlBuf.append("WHERE RN >").append((pageNo-1)*pageSize);
				 System.out.println(sqlBuf.toString());
				 List<LinkedHashMap> maps = zbmxSetService.execZbmx(sqlBuf.toString());
				 zbmxjg= JSONArray.parseArray(JSON.toJSONString(maps));
				 page.setRecords(zbmxjg);
			 }
			 Result.ok(page);
		 }else {
			 page.setTotal(0);
			 page.setRecords(new ArrayList<>());
		 }
		 return Result.ok(page);

	 }

	 /**
	  * 指标明细导出
	  * @param id
	  * @param request
	  * @param response
	  * @throws Exception
	  */
	 @RequestMapping("/mxdc")
	 public void mxdc(String id, HttpServletRequest request , HttpServletResponse response) throws Exception {
		 Jgkhjg khjg = jgkhjgService.getById(id);
		 JSONArray jsonArray=new JSONArray();
		 QueryWrapper queryWrapper =new QueryWrapper();
		 queryWrapper.eq("ZBID",khjg.getZbid());
		 ZbmxSet zbmxSet = zbmxSetService.getOne(queryWrapper);
		 if(zbmxSet!=null){
			 if("2".equals(zbmxSet.getMxlx())){
				 String sqlStr= replaceStringVal(zbmxSet.getJssql(), khjg);
				 List<LinkedHashMap> maps = zbmxSetService.execZbmx(sqlStr);
				 for(LinkedHashMap map:maps){
					 JSONObject jsonObject=new JSONObject(new LinkedHashMap());
					 for (Object key : map.keySet()) {
						 System.out.println("key= "+ key + " and value= " + map.get(key));
						 jsonObject.put(String.valueOf(key),map.get(key));
					 }
					 jsonArray.add(jsonObject);
				 }
				 //jsonArray = JSONArray.parseArray(JSON.toJSONString(maps));
			 }
		 }
		 HSSFWorkbook workbook = new HSSFWorkbook();
		 HSSFSheet sheet = workbook.createSheet("指标明细");
		 SimpleStandardTable simpleStandardTable = new SimpleStandardTable(workbook,sheet);
		 String zszd = zbmxSet.getZszd();
		 String[] split = zszd.split(",");
		 List<String> list=new ArrayList<>();
		 for(String zdxx:split){
			 list.add(zdxx.split(":")[1]);
		 }
		 simpleStandardTable.setTableName("指标明细",list.size());
		 simpleStandardTable.setTableHeader(list);
		 if (CollUtil.isNotEmpty(jsonArray)){
			 simpleStandardTable.setTableData(jsonArray);
		 }
		 workbookWrite(request, response, "指标明细_"+khjg.getZbid(), workbook);
	 }

	 /**
	  * 遍历替换成常量
	  * @param val
	  * @param khjg
	  * @return
	  */
	 public String replaceStringVal(String val, Jgkhjg khjg) {
		 String tmp = val;
		 if (val.contains("${p_month}")) {
			 tmp= LangUtil.replace(tmp, "${p_month}", DateUtil.formatDateTime("yyMM", khjg.getGzrq()));
			 //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
		 }
		 if (val.contains("${gzrq}")) {
			 tmp= LangUtil.replace(tmp, "${gzrq}", DateUtil.formatDateTime("yyyyMMdd", khjg.getGzrq()));
			 //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
		 }
		 if (val.contains("${lv_tjrq_yyyymm}")) {
			 tmp= LangUtil.replace(tmp, "${lv_tjrq_yyyymm}", DateUtil.formatDateTime("yyyyMM", khjg.getGzrq()));
		 }
		 if (val.contains("${lv_tjrq_yymmdd}")) {
			 tmp= LangUtil.replace(tmp, "${lv_tjrq_yymmdd}", DateUtil.formatDateTime("yyMMdd", khjg.getGzrq()));
		 }
		 if (val.contains("${lv_snmrq_yymmdd}")) {
			 tmp= LangUtil.replace(tmp, "${lv_snmrq_yymmdd}", DateUtil.formatDateTime("yyMMdd", cn.hutool.core.date.DateUtil.endOfYear(cn.hutool.core.date.DateUtil.offset(khjg.getGzrq(), DateField.YEAR, -1))));
		 }
		 if (val.contains("${lv_year}")) {
			 tmp= LangUtil.replace(tmp, "${lv_year}", DateUtil.formatDateTime("yyyy", khjg.getGzrq()));
		 }
		 if (val.contains("${zbid}")) {
			 try {
				 tmp= LangUtil.replace(tmp, "${zbid}",khjg.getZbid() );
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 if (val.contains("${yggh}")) {
			 try {
				 tmp= LangUtil.replace(tmp, "${yggh}",khjg.getYggh() );
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 if (val.contains("${ygxm}")) {
			 try {
				 tmp= LangUtil.replace(tmp, "${ygxm}", sysDictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", khjg.getYggh()));
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 if (val.contains("${gwbz}")) {
			 try {
				 tmp= LangUtil.replace(tmp, "${gwbz}",String.valueOf(khjg.getGwbz()));
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 if (val.contains("${zzbz}")) {
			 try {
				 tmp= LangUtil.replace(tmp, "${zzbz}",khjg.getZzbz());
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 if (val.contains("${zzmc}")) {
			 try {
				 tmp= LangUtil.replace(tmp, "${zzmc}",sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", khjg.getZzbz()));
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 if (val.contains("${ywjgdm}")) {
			 try {
				 tmp= LangUtil.replace(tmp, "${ywjgdm}", sysDictService.queryTableDictTextByKey("hr_bas_organization", "ywjgdm", "zzbz", khjg.getZzbz()));
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 return tmp;
	 }

	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "指标明细设置-通过id查询")
	 @ApiOperation(value="指标明细设置-通过id查询", notes="指标明细设置-通过id查询")
	 @GetMapping(value = "/queryById")
	 public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		 ZbmxSet zbmxSet = zbmxSetService.getById(id);
		 return Result.ok(zbmxSet);
	 }
  /**
   * 导出excel
   *
   * @param request
   * @param zbmxSet
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ZbmxSet zbmxSet) {
      return super.exportXls(request, zbmxSet, ZbmxSet.class, "指标明细设置");
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
      return super.importExcel(request, response, ZbmxSet.class);
  }

}
