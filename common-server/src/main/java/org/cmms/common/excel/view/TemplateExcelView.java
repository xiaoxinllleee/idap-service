/**
 * Copyright 2013-2015 JEECG (jeecgos@163.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cmms.common.excel.view;

import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.JxlsUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.view.MiniAbstractExcelView;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Excel 模板导出
 *
 * @author JEECG
 * @date 2014年6月30日 下午9:15:49
 */
@SuppressWarnings("unchecked")
@Controller("templateExcelView")
public class TemplateExcelView extends MiniAbstractExcelView {

	public TemplateExcelView() {
		super();
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String codedFileName = "临时文件.xls";

		String templateFileName = (String)model.get(JxlsConstants.TEMPLATE_FILE_NAME);
		File file = new File(templateFileName);
		FileInputStream in = new FileInputStream(file);
		String saveFileName = (String)model.get(JxlsConstants.SAVE_FILE_NAME);
		File outFile = new File(saveFileName);
		OutputStream os = new FileOutputStream(outFile);

		JxlsUtil.exportExcel(in, os, (Map<String, Object>) model.get(JxlsConstants.MAP_DATA));


		if (model.containsKey(NormalExcelConstants.FILE_NAME)) {
			codedFileName = (String) model.get(NormalExcelConstants.FILE_NAME);
		}
		if (isIE(request)) {
			codedFileName = java.net.URLEncoder.encode(codedFileName, "UTF8");
		} else {
			codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
		}

		//下面步骤为浏览器下载部分
		//指定数据生成后的文件输入流（将上述out的路径作为文件的输入流）
		FileInputStream fileInputStream = new FileInputStream(saveFileName);
		//导出excel文件，设置文件名
		response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
		ServletOutputStream out = response.getOutputStream();
		//将文件写入浏览器
		byte[] bys = new byte[fileInputStream.available()];
		fileInputStream.read(bys);
		out.write(bys);
		out.flush();
		out.close();
	}
}
