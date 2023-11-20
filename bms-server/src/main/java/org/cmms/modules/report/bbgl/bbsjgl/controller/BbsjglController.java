package org.cmms.modules.report.bbgl.bbsjgl.controller;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.OperatorOfNumber;
import net.sf.saxon.trans.SymbolicName;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.cmms.common.api.vo.Result;
import org.cmms.common.cal.CalculationFormula;
import org.cmms.common.cal.CalculationNode;
import org.cmms.common.excel.ExlsReport;
import org.cmms.common.excel.JxlsUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.bbgl.bbmbgl.entity.Bbmbgl;
import org.cmms.modules.report.bbgl.bbmbgl.service.IBbmbglService;
import org.cmms.modules.report.bbgl.bbsjgl.entity.Bbsjgl;
import org.cmms.modules.report.bbgl.bbsjgl.entity.VBbsjgl;
import org.cmms.modules.report.bbgl.bbsjgl.service.IBbsjglService;
import org.cmms.modules.report.bbgl.bbsjgl.service.IVBbsjglService;
import org.cmms.modules.report.util.ReportDateDin;
import org.cmms.modules.report.zbgl.zbjg.entity.Zbjg;
import org.cmms.modules.report.zbgl.zbjg.mapper.ZbjgMapper;
import org.cmms.modules.report.zbgl.zbjg.service.IZbjgService;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.ToolService;

import org.cmms.modules.util.WaterMarkUtil;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.util.ZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 报表数据管理
 * @Author: jeecg-boot
 * @Date:   2022-03-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="报表数据管理")
@RestController
@RequestMapping("/bbgl/bbsjgl")
public class BbsjglController extends JeecgController<Bbsjgl, IBbsjglService> {
	@Autowired
	private IBbsjglService bbsjglService;
    @Autowired
	private IBbmbglService bbmbglService;
    @Autowired
	private IZbjgService zbjgService;
    @Autowired
	private ToolService toolService;
    @Value(value = "${common.path.upload}")
	private String uploadpath;
    @Value(value = "${common.report.dataPath}")
	private String dataPath;
	 @Autowired
	 private ExlsReport exlsReport;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;
	 @Autowired
	 private IVBbsjglService ivBbsjglService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bbsjgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "报表数据管理-分页列表查询")
	@ApiOperation(value="报表数据管理-分页列表查询", notes="报表数据管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Bbsjgl bbsjgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Bbsjgl> queryWrapper = QueryGenerator.initQueryWrapper(bbsjgl, req.getParameterMap());
		Page<Bbsjgl> page = new Page<Bbsjgl>(pageNo, pageSize);
		IPage<Bbsjgl> pageList = bbsjglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 //根据选择的报表与时间提取数据
	 @PostMapping(value = "/create")
	 public Result<?> init(@RequestBody JSONObject jsonObject) {
		 Date bbyf = jsonObject.getDate("bbyf");
		 String bbbhStr = jsonObject.getString("bbbhStr");
		 List<String> bbbhList = Arrays.asList(bbbhStr.split(","));
		 String bbyfStr = DateUtil.format(bbyf, "yyyyMM");
		 InputStream inputStream = null;

		 try {
			 ReportDateDin reportDate = new ReportDateDin(bbyf,sfdsjpt);
			 //将所有指标结果查询出来保存到内存中
			 QueryWrapper<Zbjg> zbjgsQueryWrapper = new QueryWrapper<>();
			 zbjgsQueryWrapper.select("zbid","zbjg");
			 zbjgsQueryWrapper.eq("sjrq", bbyf);
			 List<Map<String, Object>> maps = zbjgService.listMaps(zbjgsQueryWrapper);
			 Map<String, Double> mapZbjg = new HashMap<>();
			 for(Map map:maps){
				 String key= (String) map.get("zbid");
				 if (key == null) {
					 key= (String) map.get("ZBID");
				 }
				 BigDecimal value=(BigDecimal) map.get("zbjg");
				 if (value == null) {
					 value=(BigDecimal) map.get("ZBJG");
				 }
				 log.info("#########################key:"+key+",value:"+value);
				 mapZbjg.put(key,value.doubleValue());
			 }

			 String savePath = dataPath + File.separator + bbyfStr;
			 File savePathFile = new File(savePath);
			 if (!savePathFile.exists()) {
				 savePathFile.mkdirs();
			 }
			 //遍历报表
			 for (int k = 0; k < bbbhList.size(); k++) {
			 	 String bbbh = bbbhList.get(k);
				 //获取报表对应的模板文件
				 QueryWrapper<Bbmbgl> bbmbglQueryWrapper = new QueryWrapper<>();
				 bbmbglQueryWrapper.eq("bbbh", bbbh);
				 Bbmbgl bbmbgl = bbmbglService.getOne(bbmbglQueryWrapper);
				 String bblj = bbmbgl.getBblj();
				 String bblx = bbmbgl.getBblx();
				 String sjlx = bbmbgl.getSjlx();
				 String zbwd = "";
				 if ("1".equals(sjlx)) {
					 zbwd = "MM";
				 } else if ("2".equals(sjlx)) {
					 zbwd = "Q";
				 } else if ("3".equals(sjlx)) {
					 zbwd = "YYYY";
				 } else if ("4".equals(sjlx)) {
					 zbwd = "W";
				 } else {
					 if("1".equals(bblx) || "3".equals(bblx)){
						 //1104月报 信贷计划报表
						 zbwd = "MM";
					 } else if ("2".equals(bblx)) {
						 //1104季报
						 zbwd = "Q";
					 } else if ("4".equals(bblx)) {
						 //人行周报
						 zbwd = "W";
					 }
				 }
				 List<Map<String, Object>> resultMap = new ArrayList<>();
			     if("1104Q029".equalsIgnoreCase(bbmbgl.getBbbh())) {
					 Map<String, Object> conditionMap = new HashMap<>();
					 conditionMap.put("fiscal_date", DateUtil.format(reportDate.getDateBq_Q_QM(), "yyyyMMdd"));
					 resultMap = toolService.getTableData(bbmbgl.getBbsjbm(), conditionMap, null, "ads");
				 }
				 int rnum = bbmbgl.getRnum() == null ? 0 : bbmbgl.getRnum();
				 int cnum = bbmbgl.getCnum() == null ? 0 : bbmbgl.getCnum();
				 String filePath = uploadpath + File.separator + bbmbgl.getBblj(); //模板文件实际存放路径
				 String dataPath = savePath + File.separator + bblj.substring(bblj.lastIndexOf("/")); //生成的数据文件存放路径
				 //报表填充方式(1: 固定模型报表 2: 列表式报表)
				 String bbtcfs = bbmbgl.getBbtcfs();
				 if(StringUtils.isEmpty(bbtcfs) || "1".equals(bbtcfs)) {
				 	 //将模板文件拷贝到指定路径
					 FileUtils.copyToDirectory(new File(filePath), savePathFile);
					 inputStream = new FileInputStream(dataPath);
					 Workbook workbook = ExcelUtils.getExcelWorkbook(inputStream, bbmbgl.getWjmc());
					 Sheet sheet = workbook.getSheetAt(0);
					 int lastRowNum = sheet.getLastRowNum();
					 if (rnum > 0) {
						 lastRowNum = rnum - 1;
					 }
					 if ("6".equals(bblx)) {
					 	 //校验报表
					     this.reportCheck(sheet, lastRowNum, cnum, mapZbjg, reportDate, zbwd, bbmbgl);
					 } else {
						 for (int i = 0; i <= lastRowNum; i++) {
							 Row row = sheet.getRow(i);
							 if (row == null) {
								 row = sheet.createRow(i);
							 }
							 int lastCellNum = row.getLastCellNum();
							 if (cnum > 0) {
								 lastCellNum = cnum - 1;
							 }
							 for (int j = 0; j <= lastCellNum; j++) {
								 Cell cell = row.getCell(j);
								 if (cell == null) {
									 cell = row.createCell(j);
								 }
								 String cellValue = ExcelUtils.getCellValue(cell);
								 if (StringUtils.isNotEmpty(cellValue) && cellValue.startsWith("$[")) {
									 Double res = this.expressionCal(cellValue, mapZbjg, reportDate, zbwd, bbmbgl, resultMap);
									 if (res == null) {
										 cell.setCellValue("");
									 } else {
										 cell.setCellValue(res);
									 }
								 }


							 }
						 }
					 }
					 //解析模板文件，遍历每个单元格，获取每个单元格的数据，判断单元格是否需要进行解析
					 //解析规则，将结果存入单元格中，保存文件到指定路径
					 //
					 FileOutputStream fos = new FileOutputStream(dataPath);
					 workbook.setForceFormulaRecalculation(true);
					 workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
					 workbook.write(fos);
					 fos.close();
				 } else if ("2".equals(bbtcfs)) {
				 	//列表式报表 通过jxls的方式生成报表
					 Map<String, Object> dataMap = new HashMap<>();
					 Map<String, Object> conditionMap = new HashMap<>();
					 conditionMap.put("sjrq", DateUtil.formatDateTime("yyyyMMdd",reportDate.getDateBq_QM()));
					 Map<String, Object> sortMap = new HashMap<>();
					 sortMap.put("pxxh", "asc");
					 List<Map<String, Object>> list = toolService.getTableData(bbmbgl.getBbsjbm(), conditionMap, sortMap, "ads");
					 dataMap.put("list", list);
					 JxlsUtil.exportExcel(filePath, savePath, bblj.substring(bblj.lastIndexOf("/")), dataMap);
				 }
				 //删除当前编号的数据
				 QueryWrapper<Bbsjgl> queryWrapper = new QueryWrapper<>();
				 queryWrapper.eq("bbyf", bbyf);
				 queryWrapper.eq("bbbh", bbbh);
				 bbsjglService.remove(queryWrapper);
				 //保存数据到rep_bbsjgl中
				 Bbsjgl bbsjgl = new Bbsjgl();
				 bbsjgl.setBbyf(bbyf);
				 bbsjgl.setBbbh(bbbh);
				 bbsjgl.setBblj(bbyfStr + File.separator + bblj.substring(bblj.lastIndexOf("/")));
				 bbsjglService.save(bbsjgl);
			 }
		 } catch (Throwable tx) {
		     tx.printStackTrace();
		     log.error("系统错误", tx);
		     return Result.error("系统错误！");
		 } finally {
			 IoUtil.close(inputStream);
		 }

		 return Result.ok("操作成功！");
	 }

	 //报表校验
	 public void reportCheck(Sheet sheet, int lastRowNum, int cnum, Map<String, Double> mapZbjg,
							 ReportDateDin reportDate, String zbwd, Bbmbgl bbmbgl) throws Exception{
		 List<Map<String, Object>> resultMap = new ArrayList<>();
		 //从第二行开始
		 for (int i = 1; i <= lastRowNum; i++) {
			 Row row = sheet.getRow(i);
			 if (row == null) {
				 row = sheet.createRow(i);
			 }
			 int lastCellNum = row.getLastCellNum();
			 if (cnum > 0) {
				 lastCellNum = cnum - 1;
			 }
			 Double leftValue = 0.00;
			 Double rightValue = 0.00;
			 String rule = "=";
			 for (int j = 0; j <= lastCellNum; j++) {
				 Cell cell = row.getCell(j);
				 if (cell == null) {
					 cell = row.createCell(j);
				 }
				 String cellValue = ExcelUtils.getCellValue(cell);
				 if (j == 1) {
				 	//左值
					 CalculationFormula left = new CalculationFormula(cellValue);
					 CalculationNode node = left.getNode().top();

					 while (node != null) {
						 if (node.isPix()) {
							 node = node.next();
							 continue;
						 }
						 String nodeStr = node.getNodeStr();
						 //计算单个表达式的结果
						 Double nodeValue = calculateCell(nodeStr, mapZbjg, reportDate, zbwd, bbmbgl, resultMap);
						 node.setNodeValue(nodeValue);
						 if (node.next() == null) {
							 break;
						 }
						 node = node.next();
					 }
					 node = node.top();

					 String cellStr = node.toString();

					 ExpressRunner runner = new ExpressRunner();
					 leftValue = (Double)runner.execute(cellStr, null, null, true, false);
					 cell.setCellValue(leftValue);
				 } else if (j == 2) {
				     //规则
					 rule = cellValue;
					 if ("=".equals(rule)) {
					 	rule = "==";
					 }
				 } else if (j == 3) {
				 	 //右值
					 CalculationFormula right = new CalculationFormula(cellValue);
					 CalculationNode node = right.getNode().top();

					 while (node != null) {
						 if (node.isPix()) {
							 node = node.next();
							 continue;
						 }
						 String nodeStr = node.getNodeStr();
						 //计算单个表达式的结果
						 Double nodeValue = calculateCell(nodeStr, mapZbjg, reportDate, zbwd, bbmbgl, resultMap);
						 node.setNodeValue(nodeValue);
						 if (node.next() == null) {
							 break;
						 }
						 node = node.next();
					 }
					 node = node.top();

					 String cellStr = node.toString();

					 ExpressRunner runner = new ExpressRunner();
					 rightValue = (Double)runner.execute(cellStr, null, null, true, false);
					 cell.setCellValue(rightValue);
				 } else if (j == 4) {
				 	 //校验结果  左值 规则 右值
					 ExpressRunner runner = new ExpressRunner();
					 DefaultContext<String, Object> context = new DefaultContext<String, Object>();
					 context.put("left", leftValue);
					 context.put("rule", rule);
					 context.put("right", rightValue);
					 String expressString = leftValue + rule + rightValue;
					 boolean checkResult = (Boolean)runner.execute(expressString, null, null, true, false);
					 if (checkResult) {
					 	cell.setCellValue("通过");
					 } else {
						 Double result = OperatorOfNumber.subtract(leftValue, rightValue, true).doubleValue();
						 cell.setCellValue("不通过。差值：" + result);
					 }
				 }
			 }
		 }
	 }

	 private Double expressionCal(String cellValue,  Map<String, Double> mapZbjg, ReportDateDin reportDate, String zbwd,
								 	Bbmbgl bbmbgl, List<Map<String, Object>> resultMap) throws Exception {
		 Double value = null;
		 CalculationFormula left = new CalculationFormula(cellValue);
		 CalculationNode node = left.getNode().top();
		 boolean hasValue = false;
		 while (node != null) {
			 if (node.isPix()) {
				 node = node.next();
				 continue;
			 }
			 String nodeStr = node.getNodeStr();
			 //计算单个表达式的结果
			 Double nodeValue = calculateCell(nodeStr, mapZbjg, reportDate, zbwd, bbmbgl, resultMap);
			 if (nodeValue != null) {
				 node.setNodeValue(nodeValue);
				 hasValue = true;
			 }
			 if (node.next() == null) {
				 break;
			 }
			 node = node.next();
		 }
		 node = node.top();

		 //处理公式计算中的部分null值
		 while (node != null) {
			 if (node.isPix()) {
				 node = node.next();
				 continue;
			 }
			 Double nodeValue = node.getNodeValue();
			 if (hasValue && nodeValue == null) {
				 node.setNodeValue(0);
			 }
			 if (node.next() == null) {
				 break;
			 }
			 node = node.next();
		 }

		 node = node.top();

		 String cellStr = node.toString();
		 if (StringUtils.isNotEmpty(cellStr)) {
			 ExpressRunner runner = new ExpressRunner();
			 value = (Double)runner.execute(cellStr, null, null, true, false);
		 }
		 return value;
	 }

	 private Double calculateCell(String cellStr, Map<String, Double> mapZbjg, ReportDateDin reportDate, String zbwd,
								  Bbmbgl bbmbgl, List<Map<String, Object>> resultMap) {
		 Double nodeValue = null;
		 if (StringUtils.isNotEmpty(cellStr) && cellStr.startsWith("$[")) {
			 String rule = cellStr.substring(2, cellStr.length() - 1);
			 List<String> ruleList = Arrays.asList(rule.split("@"));
			 String ruleType = ruleList.get(0);
			 if ("1".equals(ruleType)) {
				 //指标
				 String zblx = ruleList.get(1);
				 String zbid = ruleList.get(2);

				 if ("1".equals(zblx)) {
					 Double zbjg=mapZbjg.get(zbid);
					 if (zbjg != null) {
						 //cell.setCellValue(zbjg.getZbjg().doubleValue());
						 nodeValue = zbjg;
					 } else {
						 nodeValue = 0.00;
					 }
				 } else if ("2".equals(zblx)) {
					 //明细指标
					 String tableName = ruleList.get(3);
					 tableName = reportDate.replaceStringVal(tableName, zbwd);
					 String condition = ruleList.get(4);
					 String result = ruleList.get(5);
					 //将条件转换成map
					 List<String> list = new ArrayList<>();
					 Map<String, Object> map = new HashMap<>();
					 List<String> keyList = new ArrayList<>();
					 List<String> valueList = new ArrayList<>();

					 List<String> conditionList = Arrays.asList(condition.split("&"));
					 for (int m = 0; m < conditionList.size(); m++) {
						 String con = conditionList.get(m);
						 if (StringUtils.isNotEmpty(con)) {
							 con = reportDate.replaceStringVal(con, zbwd);
							 list.add(con);

							 List<String> c = Arrays.asList(con.split("="));
							 if (!c.isEmpty() && c.size() >= 2) {
								 String tjzd = c.get(0);
								 String tjz = c.get(1);
								 keyList.add(tjzd);
								 valueList.add(tjz.replaceAll("'",""));
								 map.put(tjzd, tjz);
							 }
						 }
					 }

					 Object res = null;
					 if ("1104Q029".equalsIgnoreCase(bbmbgl.getBbbh())) {
						 res = resultMap.stream()
								 .filter(m ->
										 valueList.get(0).equals(m.get(keyList.get(0).toLowerCase())) &&
												 valueList.get(1).equals(m.get(keyList.get(1).toLowerCase())) &&
												 valueList.get(2).equals(m.get(keyList.get(2).toLowerCase()))
								 )
								 .mapToDouble(m -> Double.parseDouble(m.get("dkye").toString()))
								 .sum();
						 res = new BigDecimal((Double)res).divide(new BigDecimal(10000d));
					 } else {
						 res = toolService.getResultDynamic(tableName, result, list, "ads");
					 }
					 if(res instanceof String) {
						 try {
							 nodeValue = Double.parseDouble((String)res);
						 } catch (NumberFormatException e) {
						 	 e.printStackTrace();
						 }
					 } else if (res instanceof BigDecimal) {
						 nodeValue = ((BigDecimal) res).doubleValue();
					 } else if (res instanceof Double) {
						 nodeValue = (Double) res;
					 }else if (res instanceof Long) {
						 nodeValue = ((Long) res).doubleValue();
					 }
				 }
			 } else if ("2".equals(ruleType)) {
				 //常量
				 try {
					 nodeValue = Double.parseDouble((String)ruleList.get(1));
				 } catch (NumberFormatException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return nodeValue;
	 }

	 //根据选择的报表与时间提取数据
	 @PutMapping(value = "/initOneReport")
	 public Result<?> initOneReport(@RequestBody Bbsjgl bbsjglObj) {
		 Date bbyf = bbsjglObj.getBbyf();
		 String bbbh = bbsjglObj.getBbbh();
		 String bbyfStr = DateUtil.format(bbyf, "yyyyMM");
		 InputStream inputStream = null;

		 try {
			 ReportDateDin reportDate = new ReportDateDin(bbyf,sfdsjpt);
			 QueryWrapper<Zbjg> zbjgsQueryWrapper = new QueryWrapper<>();
			 zbjgsQueryWrapper.select("zbid","zbjg");
			 zbjgsQueryWrapper.eq("sjrq", bbyf);
			 List<Map<String, Object>> maps = zbjgService.listMaps(zbjgsQueryWrapper);
			 Map<String, Double> mapZbjg=new HashMap<>();
			 for(Map map:maps){
				 String key= (String) map.get("zbid");
				 BigDecimal value=(BigDecimal) map.get("zbjg");
				 log.info("#########################key:"+key+",value:"+value);
				 mapZbjg.put(key,value.doubleValue());
			 }

			 String savePath = dataPath + File.separator + bbyfStr;
			 File savePathFile = new File(savePath);
			 if (!savePathFile.exists()) {
				 savePathFile.mkdirs();
			 }

			 //遍历报表
			 //获取报表对应的模板文件
			 QueryWrapper<Bbmbgl> bbmbglQueryWrapper = new QueryWrapper<>();
			 bbmbglQueryWrapper.eq("bbbh", bbbh);
			 Bbmbgl bbmbgl = bbmbglService.getOne(bbmbglQueryWrapper);
			 String bblj = bbmbgl.getBblj();
			 String bblx = bbmbgl.getBblx();
			 String zbwd = "";
			 if("1".equals(bblx) || "3".equals(bblx)){
				 zbwd = "MM";
			 } else if ("2".equals(bblx)) {
				 zbwd = "Q";
			 } else if ("4".equals(bblx)) {
				 zbwd = "W";
			 }
			 List<Map<String, Object>> resultMap = new ArrayList<>();
			 if("1104Q029".equalsIgnoreCase(bbmbgl.getBbbh())) {
				 Map<String, Object> conditionMap = new HashMap<>();
				 conditionMap.put("fiscal_date", DateUtil.format(reportDate.getDateBq_Q_QM(), "yyyyMMdd"));
				 resultMap = toolService.getTableData(bbmbgl.getBbsjbm(), conditionMap, null, "ads");
			 }
			 int rnum = bbmbgl.getRnum() == null ? 0 : bbmbgl.getRnum();
			 int cnum = bbmbgl.getCnum() == null ? 0 : bbmbgl.getCnum();
			 String filePath = uploadpath + File.separator + bbmbgl.getBblj();
			 String dataPath = savePath + File.separator + bblj.substring(bblj.lastIndexOf("/"));
			 //报表填充方式(1: 固定模型报表 2: 列表式报表)
			 String bbtcfs = bbmbgl.getBbtcfs();
			 if(StringUtils.isEmpty(bbtcfs) || "1".equals(bbtcfs)) {
				 //将模板文件拷贝到指定路径
				 FileUtils.copyToDirectory(new File(filePath), savePathFile);
				 inputStream = new FileInputStream(dataPath);
				 Workbook workbook = ExcelUtils.getExcelWorkbook(inputStream, bbmbgl.getWjmc());
				 Sheet sheet = workbook.getSheetAt(0);
				 int lastRowNum = sheet.getLastRowNum();
				 if (rnum > 0) {
					 lastRowNum = rnum - 1;
				 }
				 for (int i = 0; i <= lastRowNum; i++) {
					 Row row = sheet.getRow(i);
					 if (row == null) {
						 row = sheet.createRow(i);
					 }
					 int lastCellNum = row.getLastCellNum();
					 if (cnum > 0) {
						 lastCellNum = cnum - 1;
					 }
					 for (int j = 0; j <= lastCellNum; j++) {
						 Cell cell = row.getCell(j);
						 if (cell == null) {
							 cell = row.createCell(j);
						 }
						 String cellValue = ExcelUtils.getCellValue(cell);
						 if (StringUtils.isNotEmpty(cellValue) && cellValue.startsWith("$[")) {
							 double res = this.expressionCal(cellValue, mapZbjg, reportDate, zbwd, bbmbgl, resultMap);
							 cell.setCellValue(res);
						 }

					 }
				 }
				 //解析模板文件，遍历每个单元格，获取每个单元格的数据，判断单元格是否需要进行解析
				 //解析规则，将结果存入单元格中，保存文件到指定路径
				 //
				 FileOutputStream fos = new FileOutputStream(dataPath);
				 workbook.setForceFormulaRecalculation(true);
				 workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
				 workbook.write(fos);
				 fos.close();
			 } else if ("2".equals(bbtcfs)) {
				 //列表式报表 通过jxls的方式生成报表
				 Map<String, Object> dataMap = new HashMap<>();
				 Map<String, Object> conditionMap = new HashMap<>();
				 conditionMap.put("sjrq", DateUtil.format(reportDate.getDateBq_QM(), "yyyyMMdd"));
				 Map<String, Object> sortMap = new HashMap<>();
				 sortMap.put("pxxh", "asc");
				 List<Map<String, Object>> list = toolService.getTableData(bbmbgl.getBbsjbm(), conditionMap, sortMap, "ads");
				 dataMap.put("list", list);
				 JxlsUtil.exportExcel(filePath, savePath, bblj.substring(bblj.lastIndexOf("/")), dataMap);
			 }
			 //删除当前编号的数据
			 QueryWrapper<Bbsjgl> queryWrapper = new QueryWrapper<>();
			 queryWrapper.eq("bbyf", bbyf);
			 queryWrapper.eq("bbbh", bbbh);
			 bbsjglService.remove(queryWrapper);
			 //保存数据到rep_bbsjgl中
			 Bbsjgl bbsjgl = new Bbsjgl();
			 bbsjgl.setBbyf(bbyf);
			 bbsjgl.setBbbh(bbbh);
			 bbsjgl.setBblj(bbyfStr + File.separator + bblj.substring(bblj.lastIndexOf("/")));
			 bbsjglService.save(bbsjgl);
		 } catch (Throwable tx) {
			 tx.printStackTrace();
			 log.error("系统错误", tx);
			 return Result.error("系统错误！");
		 } finally {
			 IoUtil.close(inputStream);
		 }

		 return Result.ok("操作成功！");

	 }

	 /**
	  * 根据报表ID获取模板文件HTML
	  * @return
	  */
	 @GetMapping("/getReportHtml")
	 public Result<JSONObject> getReportHtml(@RequestParam(name="id") String id) {
	 	 Bbsjgl bbsjgl = bbsjglService.getById(id);
	 	 QueryWrapper<Bbmbgl> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("bbbh", bbsjgl.getBbbh());
		 Bbmbgl bbmbgl = bbmbglService.getOne(queryWrapper);
		 String filePath = dataPath + File.separator + bbsjgl.getBblj();
		 Result<JSONObject> result = new Result<>();
		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("reportHtml", exlsReport.loadReport(filePath, bbmbgl.getRnum() == null ? 0 : bbmbgl.getRnum(), bbmbgl.getCnum() == null ? 0 : bbmbgl.getCnum()));
		 result.setSuccess(true);
		 result.setResult(jsonObject);
		 return result;
	 }


	 /**
	  * 下载全部
	  * @return
	  */
	 @AutoLog(value = "报表数据管理视图-分页列表查询")
	 @ApiOperation(value="报表数据管理视图-分页列表查询", notes="报表数据管理视图-分页列表查询")
	 @GetMapping(value = "/downloadAll")
	 public Result<?> downloadAll(VBbsjgl vBbsjgl) {
		 String saveFileName="";
		 try {
		 	QueryWrapper<VBbsjgl> queryWrapper=new QueryWrapper<>();
		 	if (vBbsjgl.getBbyf()!=null){
		 		queryWrapper.eq("bbyf",vBbsjgl.getBbyf());
			}
		 	if (StringUtils.isNotBlank(vBbsjgl.getBblx())){
		 		queryWrapper.eq("bblx",vBbsjgl.getBblx());
			}
		 	if(StringUtils.isNotBlank(vBbsjgl.getBbbh())){
		 		queryWrapper.eq("bbbh",vBbsjgl.getBbbh());
			}
		 	List<String> urlList=ivBbsjglService.list(queryWrapper).stream().map(VBbsjgl::getBblj).collect(Collectors.toList());
		 	List<File> fileList=new ArrayList<>();
		 	urlList.forEach(item->{
		 		fileList.add(new File(dataPath+File.separator+item));
			});

			 DateFormat dateFormat = new SimpleDateFormat("yyyyMM");
			 String bbyfstr=DateUtil.getDateString(vBbsjgl.getBbyf(),dateFormat);
//			 String srcDir = dataPath + File.separator +bbyfstr;
			 String zipSavePath = dataPath + File.separator +bbyfstr+".zip";
			 saveFileName=bbyfstr+".zip";
			 FileOutputStream fos1 = new FileOutputStream(new File(zipSavePath));
			 ZipUtils.toZip(fileList,fos1);
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
			 return  Result.error("压缩文件失败！");
		 }
		 return  Result.ok("操作成功",saveFileName);
	 }


	 /**
	 * 添加
	 *
	 * @param bbsjgl
	 * @return
	 */
	@AutoLog(value = "报表数据管理-添加")
	@ApiOperation(value="报表数据管理-添加", notes="报表数据管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Bbsjgl bbsjgl) {
		bbsjglService.save(bbsjgl);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param bbsjgl
	 * @return
	 */
	@AutoLog(value = "报表数据管理-编辑")
	@ApiOperation(value="报表数据管理-编辑", notes="报表数据管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Bbsjgl bbsjgl) {
		bbsjglService.updateById(bbsjgl);
		return Result.ok("编辑成功!");
	}


	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报表数据管理-通过id删除")
	@ApiOperation(value="报表数据管理-通过id删除", notes="报表数据管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bbsjglService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "报表数据管理-批量删除")
	@ApiOperation(value="报表数据管理-批量删除", notes="报表数据管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bbsjglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报表数据管理-通过id查询")
	@ApiOperation(value="报表数据管理-通过id查询", notes="报表数据管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Bbsjgl bbsjgl = bbsjglService.getById(id);
		return Result.ok(bbsjgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param bbsjgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Bbsjgl bbsjgl) {
      return super.exportXls(request, bbsjgl, Bbsjgl.class, "报表数据管理");
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
      return super.importExcel(request, response, Bbsjgl.class);
  }

}
