package org.cmms.modules.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.cmms.common.constant.IdentConstant;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.export.ExcelExportServer;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class IdentEncryptionController extends JeecgEntityExcelView {

    private static String sfbz;

    @Value(value = "${common.certificateEncryption.sfbz}")
    public void setsfbz(String sfbz) {
        IdentEncryptionController.sfbz = sfbz;
    }

    public static Object createInstance(String className){

        try {
            Class clz = Class.forName(className);
            Object obj = clz.newInstance();
            return obj;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (sfbz.equals("1")){
            List<Object> objectList = (List<Object>) model.get(NormalExcelConstants.DATA_LIST);
            for (Object  obj : objectList) {
                Class<?> clazz = obj.getClass();
                Field[] fields = getAllFields(obj);
                System.out.println(fields);
                for (Field field : fields) {
                    for (int i = 0; i < IdentConstant.Field_Name.size(); i++) {
                        if (field.getName().equalsIgnoreCase(IdentConstant.Field_Name.get(i))) {
                            Method method = clazz.getMethod(IdentConstant.Set_Field_Name.get(i), String.class);
                            Method getMethod = clazz.getMethod(IdentConstant.Get_Field_Name.get(i));
                            String zjhm = (String)getMethod.invoke(obj);
                            System.out.println(zjhm.substring(zjhm.length()-4,zjhm.length()));
                            if (zjhm.length() > 10){
                                zjhm = zjhm.replaceAll("(\\w{6})\\w*(\\w{4})", "$1********$2");
                                method.invoke(obj,zjhm);
                            }
                            else {
                                zjhm = zjhm + "******";
                                method.invoke(obj, zjhm);
                            }
                        }
                    }
                }
            }
            model.put(NormalExcelConstants.DATA_LIST,objectList);
        }
        String codedFileName = "临时文件";
        Workbook workbook = null;
        //---update-end-----autor:scott------date:20191016-------for:导出字段支持自定义--------
        String[] exportFields = null;

        Object exportFieldStr =  model.get(NormalExcelConstants.EXPORT_FIELDS);
        if(exportFieldStr!=null && exportFieldStr!=""){
            exportFields = exportFieldStr.toString().split(",");
        }

        //---update-end-----autor:scott------date:20191016-------for:导出字段支持自定义--------
        if (model.containsKey(NormalExcelConstants.MAP_LIST)) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) model.get(NormalExcelConstants.MAP_LIST);
            if (list.size() == 0) {
                throw new RuntimeException("MAP_LIST IS NULL");
            }
            workbook = ExcelExportUtil.exportExcel((ExportParams) list.get(0).get(NormalExcelConstants.PARAMS), (Class<?>) list.get(0).get(NormalExcelConstants.CLASS), (Collection<?>) list.get(0).get(NormalExcelConstants.DATA_LIST),exportFields);
            for (int i = 1; i < list.size(); i++) {
                new ExcelExportServer().createSheet(workbook, (ExportParams) list.get(i).get(NormalExcelConstants.PARAMS), (Class<?>) list.get(i).get(NormalExcelConstants.CLASS), (Collection<?>) list.get(i).get(NormalExcelConstants.DATA_LIST),exportFields);
            }
        } else {
            workbook = ExcelExportUtil.exportExcel((ExportParams) model.get(NormalExcelConstants.PARAMS), (Class<?>) model.get(NormalExcelConstants.CLASS), (Collection<?>) model.get(NormalExcelConstants.DATA_LIST),exportFields);
        }
        if (model.containsKey(NormalExcelConstants.FILE_NAME)) {
            codedFileName = (String) model.get(NormalExcelConstants.FILE_NAME);
        }
        if (workbook instanceof HSSFWorkbook) {
            codedFileName += HSSF;
        } else {
            codedFileName += XSSF;
        }
        if (isIE(request)) {
            codedFileName = java.net.URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
    /**
     * 获取类的所有属性，包括父类
     *
     * @param object
     * @return
     */
    public static Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }
}
