package org.cmms.common.excel;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.cmms.common.excel.command.ForEachCommand;
import org.cmms.common.excel.command.MergeCommand;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
//import org.jxls.transform.poi.WritableCellValue;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/29.
 */
public class JxlsUtil {
    private static final String TEMPLATE_PATH="jxls-template";
    static {
        //注册 jx 命令
        XlsCommentAreaBuilder.addCommandMapping("merge", MergeCommand.class);
        XlsCommentAreaBuilder.addCommandMapping("forEach", ForEachCommand.class);
    }

    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> model) throws IOException {
        Context context = new Context();
        if (model != null) {
            for (String key : model.keySet()) {
                context.putVar(key, model.get(key));
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();

        Transformer transformer  = jxlsHelper.createTransformer(is, os);

        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
        Map<String, Object> funcs = new HashMap<String, Object>();
          funcs.put("utils", new JxlsUtil());    //添加自定义功能
        JexlBuilder jb = new JexlBuilder();
        jb.namespaces(funcs);

        JexlEngine je = jb.create();
        evaluator.setJexlEngine(je);
//          evaluator.getJexlEngine().setFunctions(funcs);
        jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
    }

    public static void exportExcel(File xls, File out, Map<String, Object> model) throws FileNotFoundException, IOException {
        exportExcel(new FileInputStream(xls), new FileOutputStream(out), model);
    }

    public static void exportExcel(String templateName, OutputStream os, Map<String, Object> model) throws FileNotFoundException, IOException {
        File template = getTemplate(templateName);
        if(template!=null){
            exportExcel(new FileInputStream(template), os, model);
        }
    }

    public static void exportExcel(String templateName, String outPath, String outName, Map<String, Object> model) throws FileNotFoundException, IOException {
        File template = getTemplate(templateName);
        if (!(outPath.endsWith("/") || outPath.endsWith("\\"))) {
            outPath = outPath + "/";
        }
        if (!new File(outPath).exists()) {
            new File(outPath).mkdirs();
        }
        String file = outPath + outName;
        if(template != null){
            exportExcel(new FileInputStream(template), new FileOutputStream(file), model);
        }
    }


    //获取jxls模版文件

    public static File getTemplate(String name){
        File template = new File(name);
        if(template.exists()){
            return template;
        }
        return null;
    }

    // 日期格式化
    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
            return dateFmt.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // if判断
    public Object ifelse(boolean b, Object o1, Object o2) {
        return b ? o1 : o2;
    }

    //单元格合并
//    public WritableCellValue mergeCell(String value , Integer mergerRows) {
//        return new MergeCellValue(value , mergerRows);
//    }
}
