package org.cmms.modules.report.sgtzgl.wjplsc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.http.entity.ContentType;
import org.apache.poi.ss.formula.functions.T;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.api.ISysBaseAPI;
import org.cmms.common.system.controller.CommonController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.dynamic.db.SqlUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.jtkhtz.entity.SgtzglJtkhtz;
import org.cmms.modules.report.sgtzgl.txdjb.service.ISgtzglTxdjbService;
import org.cmms.modules.report.sgtzgl.wjplsc.entity.ConfigurationTableSheet;
import org.cmms.modules.report.sgtzgl.wjplsc.entity.bulkimport;
import org.cmms.modules.report.sgtzgl.wjplsc.entity.configurationTable;
import org.cmms.modules.report.sgtzgl.wjplsc.service.IConfigurationTableSheetService;
import org.cmms.modules.report.sgtzgl.wjplsc.service.IbulkimportService;
import org.cmms.modules.report.sgtzgl.wjplsc.service.IconfigurationTableService;
import org.cmms.modules.system.service.ToolService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: ddw
 * @Author: jeecg-boot
 * @Date: 2022-10-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "ddw")
@RestController
@RequestMapping("/wjplsc/bulkimport")
public class bulkimportController extends JeecgController<bulkimport, IbulkimportService> {
    @Autowired
    private IbulkimportService bulkimportService;

    @Autowired
    private ToolService toolService;
    @Autowired
    private ISysBaseAPI sysBaseAPI;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IconfigurationTableService iconfigurationTableService;
    @Autowired
    private IConfigurationTableSheetService configurationTableSheetService;

    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;



    /**
     * 分页列表查询
     *
     * @param bulkimport
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "ddw-分页列表查询")
    @ApiOperation(value = "ddw-分页列表查询", notes = "ddw-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(bulkimport bulkimport,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<bulkimport> queryWrapper = QueryGenerator.initQueryWrapper(bulkimport, req.getParameterMap());
        Page<bulkimport> page = new Page<bulkimport>(pageNo, pageSize);
        IPage pageList= PageUtil.toPage(IbulkimportService.class,bulkimportService,pageNo,pageSize,queryWrapper,"FILE_NAME");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param bulkimport
     * @return
     */
    @AutoLog(value = "ddw-添加")
    @ApiOperation(value = "ddw-添加", notes = "ddw-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody bulkimport bulkimport) {
        bulkimportService.save(bulkimport);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param bulkimport
     * @return
     */
    @AutoLog(value = "ddw-编辑")
    @ApiOperation(value = "ddw-编辑", notes = "ddw-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody bulkimport bulkimport) {
        bulkimportService.updateById(bulkimport);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "ddw-通过id删除")
    @ApiOperation(value = "ddw-通过id删除", notes = "ddw-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        bulkimportService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "ddw-批量删除")
    @ApiOperation(value = "ddw-批量删除", notes = "ddw-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.bulkimportService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "ddw-通过id查询")
    @ApiOperation(value = "ddw-通过id查询", notes = "ddw-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        bulkimport bulkimport = bulkimportService.getById(id);
        return Result.ok(bulkimport);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param bulkimport
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, bulkimport bulkimport) {
        return super.exportXls(request, bulkimport, bulkimport.class, "ddw");
    }

    public List<File> getFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        List<File> filelist = new ArrayList<>();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else {
                    String strFileName = files[i].getAbsolutePath();
                    filelist.add(files[i]);
                }
            }


        }
        return filelist;
    }


    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String filePaths = jsonObject.getString("filePaths");
        String days = jsonObject.getString("days").substring(0,10);
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getUsername());
        String  Ds = null;
        if ("020".equals(qydm)){
            Ds= "ads";
        }else {
            Ds="idap";
        }

        System.out.println(days);
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        for (String filePath : filePathList) {
            bulkimport bulkimpor=new bulkimport();
            String baseFilePath = uploadpath + File.separator + filePath;
            bulkimpor.setFileDate(days);
            bulkimpor.setFileName(filePath);
            bulkimpor.setFilePath(baseFilePath);
            bulkimportService.insertBulKDate(bulkimpor);
            File file = new File(baseFilePath);
            try {
                String unzipPath = uploadpath + File.separator + "report/unzip";
                //先清除文件
                FileUtil.del(unzipPath);
                //解压文件
                ZipUtil.unzip(baseFilePath, unzipPath, CharsetUtil.CHARSET_GBK);
                //遍历解压之后的文件清单
                List<File> fileList = getFileList(unzipPath);
                for (File file1 : fileList) {
                    System.out.println(file1.getName());
                    configurationTable configurationTable = iconfigurationTableService.selectTableCodeByTableName(file1.getName());
                    if (configurationTable != null) {
                        if("1".equals(configurationTable.getSingleSheet())){
                            //单sheet导入
                            String daysName = configurationTable.getDaysName();

                            String daynameField="";
                            if(daysName.indexOf("_")>0){
                                String[] strs = daysName.split("_");
                                for(int y=1;y<strs.length;y++){
                                    daynameField+=getToSzmdx(strs[y].toLowerCase());
                                }
                                daynameField=strs[0]+daynameField;

                            }else{
                                daynameField=daysName.toLowerCase();
                            }

                            Class entity = Class.forName(configurationTable.getEntityPath());
                            Class ser = Class.forName(configurationTable.getServicePath());
                            String  dsName = "master";
                            DS ds = (DS)ser.getAnnotation(DS.class);
                            if (ds != null) {
                                dsName = ds.value();
                            }


                            if (StringUtils.isEmpty(daysName)) {
                                bulkimportService.deleteTableDataByTable(configurationTable.getTableCode(),Ds);
                            } else {
                                Field field = entity.getDeclaredField(daynameField);//通过反射获取字段
                                if(field.getType().equals(String.class)){
                                    bulkimportService.deleteTableDataByString(daysName, configurationTable.getTableCode(), days.replace("-",""),Ds);
                                }else if(field.getType().equals(Date.class)){
                                    if("true".equals(sfdsjpt)){
                                        bulkimportService.deleteTableDataByString(daysName, configurationTable.getTableCode(),days,Ds);
                                    }else{
                                        bulkimportService.deleteTableDataByDate(daysName, configurationTable.getTableCode(), DateUtil.parse(days),Ds);
                                    }
                                }

                            }

                            //IService service = (IService) SpringContextUtils.getBean(ser);
                            InputStream inputStream = new FileInputStream(file1);
                            ImportParams params = new ImportParams();
                            params.setTitleRows(configurationTable.getTitleRow());
                            params.setHeadRows(configurationTable.getHeadRow());
                            params.setNeedSave(false);
                            log.info("开始读取excel--{}的数据", file1.getName());
                            List<T> list = ExcelImportUtil.importExcel(inputStream, entity, params);

                            log.info("完成读取excel--{}的数据，共{}条", file1.getName(), list.size());
//                        List<Object> newList =new ArrayList<>();
//                        log.info("开始遍历增加主键字段--{}", file1.getName());
//                        for (int i = 0; i < list.size(); i++) {
//                            Object t = list.get(i);
//                            Class clazz = t.getClass();
//                            Field stuNo = clazz.getDeclaredField(daynameField);//通过反射获取stuNo字段
//                            stuNo.setAccessible(true);//private属性必须设置
//                            stuNo.set(t,days);
//                            newList.add(t);
//                        }
//                        log.info("完成遍历增加主键字段--{}", file1.getName());
                            //update-begin-author:taoyan date:20190528 for:批量插入数据
                            //开始拼接sql
                            StringBuffer insertSql = SqlUtils.getInsertSql(entity,sfdsjpt,dsName);

                            System.out.println("@@@@@@@@@@==insertSql===="+insertSql);
                            if (insertSql == null) {
                                log.info("未找到文件对应的表名信息--{}", file1.getName());
                                continue;
                            }

                            log.info("开始进行批量insert--{}", file1.getName());
                            StringBuffer finalInsertSql = new StringBuffer();
                            if("true".equals(sfdsjpt)){
                                finalInsertSql.append(insertSql);
                            }

                            for (int i = 0; i < list.size(); i++) {
                                if(!"true".equals(sfdsjpt)){
                                    finalInsertSql.append(insertSql);
                                }
                                Object t = list.get(i);
                                Class clazz = t.getClass();
                                Field field = clazz.getDeclaredField(daynameField);//通过反射获取字段
                                field.setAccessible(true);//private属性必须设置
                                if(field.getType().equals(String.class)){
                                    field.set(t,days.replace("-",""));
                                }else if(field.getType().equals(Date.class)){
                                    field.set(t,DateUtil.parse(days));
                                }else{
                                    field.set(t,days);
                                }


                                StringBuffer insertValueSql = SqlUtils.getInsertValueSql(t, entity,sfdsjpt);
                                finalInsertSql.append(insertValueSql);


                                //1000条写一次
                                if ((i > 0 && i % 1000 == 0) || i == list.size() - 1 ) {
                                    finalInsertSql = new StringBuffer(StrUtil.removeSuffix(finalInsertSql.toString(), ","));
                                    log.info("finalInsertSql.toString()---"+finalInsertSql.toString());
                                    String result = null;
                                    if ("true".equals(sfdsjpt)){
                                        result =finalInsertSql.toString();
                                    }else{
                                        result = "insert all "+finalInsertSql.toString().replaceAll(",into"," into")+" select 1 from dual";
                                    }
                                    log.info("result===@@==" +result);
//                                insertValueSql.append(";");
                                    toolService.execInsertSql(result, dsName);
                                    log.info("已提交{}条--{}", i+1, file1.getName());
                                    finalInsertSql.setLength(0);
                                    finalInsertSql.append(insertSql);
                                }

                            }
//                        service.saveBatch(newList);

                            log.info("完成数据库保存--{}", file1.getName());
                            //400条 saveBatch消耗时间1592毫秒  循环插入消耗时间1947毫秒
                            //1200条  saveBatch消耗时间3687毫秒 循环插入消耗时间5212毫秒

                        }else if("2".equals(configurationTable.getSingleSheet())){
                            //多个sheet导入到不同的表
                            QueryWrapper<ConfigurationTableSheet> queryWrapper=new QueryWrapper();
                            queryWrapper.eq("table_name",file1.getName());
                            List<ConfigurationTableSheet> configurationTableSheets = configurationTableSheetService.list(queryWrapper);


                            for(ConfigurationTableSheet sheet:configurationTableSheets){
                                String daysName = sheet.getDaysName();

                                String daynameField="";
                                if(daysName.indexOf("_")>0){
                                    String[] strs = daysName.split("_");
                                    for(int y=1;y<strs.length;y++){
                                        daynameField+=getToSzmdx(strs[y].toLowerCase());
                                    }
                                    daynameField=strs[0]+daynameField;

                                }else{
                                    daynameField=daysName.toLowerCase();
                                }




                                Class entity = Class.forName(sheet.getEntityPath());
                                Class ser = Class.forName(sheet.getServicePath());
                                String  dsName = "master";
                                DS ds = (DS)ser.getAnnotation(DS.class);
                                if (ds != null) {
                                    dsName = ds.value();
                                }

                                if (StringUtils.isEmpty(daysName)) {
                                    bulkimportService.deleteTableDataByTable(configurationTable.getTableCode(),Ds);
                                } else {
                                    Field field = entity.getDeclaredField(daynameField);//通过反射获取字段
                                    if(field.getType().equals(String.class)){
                                        bulkimportService.deleteTableDataByString(daysName, sheet.getTableCode(), days.replace("-",""),Ds);
                                    }else if(field.getType().equals(Date.class)){
                                        if("true".equals(sfdsjpt)){
                                            bulkimportService.deleteTableDataByString(daysName, sheet.getTableCode(),days,Ds);
                                        }else{
                                            bulkimportService.deleteTableDataByDate(daysName, sheet.getTableCode(), DateUtil.parse(days),Ds);
                                        }
                                    }

                                }


                                InputStream inputStream = new FileInputStream(file1);
                                ImportParams params = new ImportParams();
                                params.setTitleRows(sheet.getTitleRow());
                                params.setHeadRows(sheet.getHeadRow());
                                params.setNeedSave(false);
                                log.info("开始读取excel--{}的数据", file1.getName());
                                List<T> list = ExcelImportUtil.importExcelBysheet(inputStream, entity, params,sheet.getSheetName());

                                log.info("完成读取excel--{}的数据，共{}条", file1.getName()+"-"+sheet.getSheetName(), list.size());

                                StringBuffer insertSql = SqlUtils.getInsertSql(entity,sfdsjpt,dsName);

                                System.out.println("@@@@@@@@@@==insertSql===="+insertSql);
                                if (insertSql == null) {
                                    log.info("未找到文件对应的表名信息--{}", file1.getName());
                                    continue;
                                }

                                log.info("开始进行批量insert--{}", file1.getName());
                                StringBuffer finalInsertSql = new StringBuffer();
                                if("true".equals(sfdsjpt)){
                                    finalInsertSql.append(insertSql);
                                }

                                for (int i = 0; i < list.size(); i++) {
                                    if(!"true".equals(sfdsjpt)){
                                        finalInsertSql.append(insertSql);
                                    }
                                    Object t = list.get(i);
                                    Class clazz = t.getClass();
                                    Field field = clazz.getDeclaredField(daynameField);//通过反射获取字段
                                    field.setAccessible(true);//private属性必须设置
                                    if(field.getType().equals(String.class)){
                                        field.set(t,days.replace("-",""));
                                    }else if(field.getType().equals(Date.class)){
                                        field.set(t,DateUtil.parse(days));
                                    }else{
                                        field.set(t,days);
                                    }


                                    StringBuffer insertValueSql = SqlUtils.getInsertValueSql(t, entity,sfdsjpt);
                                    finalInsertSql.append(insertValueSql);


                                    //1000条写一次
                                    if ((i > 0 && i % 1000 == 0) || i == list.size() - 1 ) {
                                        finalInsertSql = new StringBuffer(StrUtil.removeSuffix(finalInsertSql.toString(), ","));
                                        log.info("finalInsertSql.toString()---"+finalInsertSql.toString());
                                        String result = null;
                                        if ("true".equals(sfdsjpt)){
                                            result =finalInsertSql.toString();
                                        }else{
                                            result = "insert all "+finalInsertSql.toString().replaceAll(",into"," into")+" select 1 from dual";
                                        }
                                        log.info("result===@@==" +result);
//                                insertValueSql.append(";");
                                        toolService.execInsertSql(result, dsName);
                                        log.info("已提交{}条--{}", i+1, file1.getName());
                                        finalInsertSql.setLength(0);
                                        finalInsertSql.append(insertSql);
                                    }

                                }
//                        service.saveBatch(newList);

                                log.info("完成数据库保存--{}", file1.getName());

                            }


                        }


                    } else {
                        log.info("未找到文件对应的配置信息--{}", file1.getName());
                    }


                }


            } catch (Throwable throwable) {
                throwable.printStackTrace();
                log.error("系统错误，请联系管理员处理！", throwable.getMessage());
                return Result.error(throwable.getMessage()+"系统错误，请联系管理员处理！");
            }
        }
        return Result.ok("文件导入完成！");
    }

    private static String getToSzmdx(String str) {
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

}
