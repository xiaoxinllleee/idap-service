package org.cmms.modules.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DocxUtil2 {
	/**
	 * @Description: 将t中的字段转换成替换模板需要的数据${字段}-->字段值
	 *               在word模板中变量为${valuename},为每个值建一个以‘${valuename}’为键，‘value’为值的Map集合，
	 *               利用键去和Word模板中寻找相等的变量
	 */
	public static <T> Map<String, String> copyParamFromBean(T t, Map<String, String> params) {
		Field[] fields = t.getClass().getDeclaredFields();
		String key;
		String value;
		for (Field field : fields) {
			String fieldName = field.getName();
			key = "@" + fieldName + "@";
			String name = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				if (name.equals("getPic1"))
					System.out.println(String.valueOf(t.getClass().getMethod(name).invoke(t)));
				value = String.valueOf(t.getClass().getMethod(name).invoke(t));
				params.put(key, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return params;
	}


	public static <T> Map<String, Object> copyParamFromBean2(T t, Map<String, Object> params) {
		Field[] fields = t.getClass().getDeclaredFields();
		String key;
		String value;
		for (Field field : fields) {
			String fieldName = field.getName();
			key = "@" + fieldName + "@";
			String name = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				value = String.valueOf(t.getClass().getMethod(name).invoke(t));
				if (value.equals("null"))
					value =  "";
				params.put(key, value);
				//System.out.println("key="+key+"|value="+value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return params;
	}

	/***
	 * 替换Word模板中的对应变量。 两种情况：1-段落中的变量。 2-表格中的变量
	 */
	public static void searchAndReplace(HWPFDocument document, Map<String, String> map) {
		// 替换段落中的指定文字
		Range range = document.getRange();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getValue().equals("null")) {
				range.replaceText(entry.getKey(), "_______");
			} else {
				range.replaceText(entry.getKey(), entry.getValue());
			}
		}
	}

	/***
	 * 替换Word模板中的对应变量。 两种情况：1-段落中的变量。 2-表格中的变量
	 */
	public static void searchXAndReplace(XWPFDocument document, Map<String, String> map) {
		Iterator<XWPFTable> itTable = document.getTablesIterator();
		Iterator<XWPFParagraph> iterator = document.getParagraphsIterator();
		XWPFParagraph para;
		while (iterator.hasNext()) {
			para = iterator.next();
			replaceInPara(para, map);
		}

		/* 替换表格中的指定文字 */
		while (itTable.hasNext()) {
			XWPFTable table = (XWPFTable) itTable.next();
			int count = table.getNumberOfRows();
			for (int i = 0; i < count; i++) {
				XWPFTableRow row = table.getRow(i);
				List<XWPFTableCell> cells = row.getTableCells();
				for (XWPFTableCell cell : cells) {
					for (Map.Entry<String, String> e : map.entrySet()) {
						if (cell.getText().equals(e.getKey())) {
							cell.removeParagraph(0);
							if (e.getValue() == null || "".equals(e.getKey())) {
								cell.setText("");
							} else {

								cell.setText(e.getValue());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 替换段落里面的变量
	 * 
	 * @param para   要替换的段落
	 * @param params 参数
	 */
	private static void replaceInPara(XWPFParagraph para, Map<String, String> params) {
		List<XWPFRun> runs;
		Matcher matcher;
		if (matcher(para.getParagraphText()).find()) {
			runs = para.getRuns();
			for (int i = 0; i < runs.size(); i++) {
				XWPFRun run = runs.get(i);
				String runText = run.toString();
				matcher = matcher(runText);
				if (matcher.find()) {
					while ((matcher = matcher(runText)).find()) {
						String param = params.get("@" + matcher.group(1) + "@");
						if (param == null || "null".equals(param)) {
							param = "_____";
						}
						runText = matcher.replaceFirst(param);
					}
					// 直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
					// 所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
					para.removeRun(i);
					para.insertNewRun(i).setText(runText);
				}
			}
		}
	}

	/**
	 * 正则匹配字符串
	 * 
	 * @param str
	 * @return
	 */
	private static Matcher matcher(String str) {
		Pattern pattern = Pattern.compile("@(.+?)@", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher;
	}

	public static <T> String exportDoc(ClassPathResource classPathResource, T t, String path) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			HWPFDocument document = new HWPFDocument(classPathResource.getInputStream());// 读取Word模板
			DocxUtil2.copyParamFromBean(t, params);// 调用DocxUtil中的copyParamFromBean方法，为VO的每个值建立“${valuename}”键
			DocxUtil2.searchAndReplace(document, params);// 替换模板中的对应变量。
			// 清空response
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			document.write(fileOutputStream);
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("resource")
	public static <T> String exportXDoc(ClassPathResource classPathResource, T t,String path) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			XWPFDocument document = new XWPFDocument(classPathResource.getInputStream());// 读取Word模板
			DocxUtil2.copyParamFromBean(t, params);// 调用DocxUtil中的copyParamFromBean方法，为VO的每个值建立“${valuename}”键
			/**
			 * 对段落中的标记进行替换
			 */
			List<XWPFParagraph> parasList = document.getParagraphs();
			DocxUtil3.replaceInAllParagraphs(parasList, params);

			/**
			 * 对表格中的标记进行替换
			 */
			List<XWPFTable> tables = document.getTables();
			DocxUtil3.replaceInTables(tables, params);
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			document.write(fileOutputStream);
			fileOutputStream.close();

			log.info("个人授信文本生成结束,文件位置在|{}|",path);
		} catch (IOException e) {
			e.printStackTrace();
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
	}

	/**
	 * 关闭输出流
	 *
	 * @param os
	 */
	public void close(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static <T> void exportX2Doc(T t,String path,String name) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			DocxUtil2.copyParamFromBean2(t, params);// 调用DocxUtil中的copyParamFromBean方法，为VO的每个值建立“${valuename}”键
			XWPFDocument document = WordUtils2.generateWord(params,path);
			FileOutputStream outputStream = new FileOutputStream(name);
			document.write(outputStream);
			outputStream.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}


}
