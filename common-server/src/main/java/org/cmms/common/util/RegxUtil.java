package org.cmms.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class RegxUtil {
	private static Log log = LogFactory.getLog(RegxUtil.class);

	/**
	 * 根据某个特征量，获取一个完整的HTML Tag对象字符串
	 *
	 * @param buffer
	 *            页面字符串
	 * @param tagName
	 *            tag名称，例如table
	 * @param tagMarkRegx
	 *            tag的标示量，用于确定tag在页面中的位置
	 * @return tag的整个HTML
	 */
	public static String findTagByRegx(CharSequence buffer, String tagName, String tagMarkRegx) {
		String tagHtml = null;
		Matcher tableMatcher = Pattern.compile("(<" + tagName + ")[\\s|\\S]*?" + tagMarkRegx + "[\\s|\\S]*?/" + tagName + ">").matcher(buffer);
		int startPos = 0;
		while (tableMatcher.find(startPos)) {
			startPos = tableMatcher.end(1);
			// 开始处理table信息
			tagHtml = tableMatcher.group();
			// //注意，这里要解决一个严重隐患，就是循环嵌套的tag问题，我们要找最小的tag而不是大的tag，所以必须找到最小的开始位置
			// tagHtml = tagHtml.replaceAll("[\\s|\\S]*<" + tagName,
			// "<"+tagName);
			// tagHtml = tagHtml.replaceAll("/" + tagName + ">[\\s|\\S]*","/" +
			// tagName + ">");
		}
		return tagHtml;
	}


	/**
	 * 根据某个特征量，获取一个完整的HTML Tag对象字符串
	 *
	 * @param buffer
	 *            页面字符串
	 * @param tagName
	 *            tag名称，例如table
	 * @param tagMarkRegx
	 *            tag的标示量，用于确定tag在页面中的位置
	 * @return tag的整个HTML
	 */
	public static String findFirstTagByRegx(CharSequence buffer, String tagName, String tagMarkRegx) {
		String tagHtml = null;
		Matcher tableMatcher = Pattern.compile("(<" + tagName + ")[\\s|\\S]*?" + tagMarkRegx + "[\\s|\\S]*?/" + tagName + ">").matcher(buffer);
		int startPos = 0;
		if (tableMatcher.find(startPos)) {
			startPos = tableMatcher.end(1);
			// 开始处理table信息
			tagHtml = tableMatcher.group();
			// //注意，这里要解决一个严重隐患，就是循环嵌套的tag问题，我们要找最小的tag而不是大的tag，所以必须找到最小的开始位置
			// tagHtml = tagHtml.replaceAll("[\\s|\\S]*<" + tagName,
			// "<"+tagName);
			// tagHtml = tagHtml.replaceAll("/" + tagName + ">[\\s|\\S]*","/" +
			// tagName + ">");
		}
		return tagHtml;
	}


	/**
	 * 根据table的字串，行，列拿出需要的单元格内容
	 *
	 * @param tableBuffer
	 *            table的html
	 * @param row
	 *            第几行，从0开始
	 * @param col
	 *            第几列，从0开始
	 * @return cell单元格的内容
	 */
	public static String getCellDataByTable(CharSequence tableBuffer, int row, int col) {
		return getCellDataByTag(tableBuffer, row, col, "[t|T][r|R]", "[t|T][d|D]");
	}

	/**
	 * 根据table的字串，行，列拿出需要的单元格内容
	 *
	 * @param tagBuffer
	 *            某个tag的buffer的html
	 * @param row
	 *            第几行，从0开始
	 * @param col
	 *            第几列，从0开始
	 * @param rowTagName
	 *            行的tag name，例如<dt>
	 * @param cellTagName
	 *            cell的tagName，例如<dd>
	 * @return cell单元格的内容
	 */

	public static String getCellDataByTag(CharSequence tagBuffer, int row, int col, String rowTagName, String cellTagName) {
		if (tagBuffer != null && tagBuffer.length() > 0) {
			String result = null;
			boolean findTr = false;
			boolean findTd = false;
			boolean findCell = false;
			Matcher trMatcher = Pattern.compile("<" + rowTagName + "[\\s|\\S]+?/" + rowTagName + ">").matcher(tagBuffer);
			int trIndex = 0;
			int trStartPos = 0;
			while (trMatcher.find(trStartPos)) {
				findTr = true;
				trStartPos = trMatcher.end();
				// System.out.println("BBBBBBBBBB"+trIndex+","+this.row+"\n"+trMatcher.group());
				if (trIndex == row) {
					String trHtml = trMatcher.group();
					Matcher tdMatcher = Pattern.compile("<" + cellTagName + "[\\s|\\S]+?/" + cellTagName + ">").matcher(trHtml);
					int tdIndex = 0;
					int tdStartPos = 0;
					while (tdMatcher.find(tdStartPos)) {
						findTd = true;
						tdStartPos = tdMatcher.end();
						if (tdIndex == col) {
							String tdHtml = tdMatcher.group();
							// System.out.println("CCCCCCCCC:"+tdHtml);
							// Matcher cellMatcher = Pattern.compile("<" +
							// cellTagName + "[^>]*>([^<]*)<").matcher(tdHtml);
							Matcher cellMatcher = Pattern.compile("<" + cellTagName + "[^>]*>([\\s|\\S]+)</" + cellTagName).matcher(tdHtml);
							if (cellMatcher.find()) {
								result = cellMatcher.group(1);
							}
							break;
						}
						tdIndex++;
					}
					break;
				}
				trIndex++;
			}
			log.info("寻找table数据调试信息:tr=" + findTr + ",td=" + findTd + ",cell=" + findCell + "");
			return result;
		} else {
			log.info("buffer为空，无法获取数据");
			return null;
		}
	}

	/**
	 * 获取某个html tag元素的innerHTML
	 *
	 * @param tagHtml
	 * @return
	 */
	public static String getInnerHtml(CharSequence tagHtml) {
		Matcher matcher = Pattern.compile("^[^>]*?>([\\s|\\S]*)?<[^<]*?$").matcher(tagHtml);
		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return null;
		}
	}

	/**
	 * 获取某个html tag元素内部去掉html标签的纯文本
	 *
	 * @return
	 */
	public static String getInnerText(String tagHtml) {
		return removeHtmlTag(tagHtml);
	}

	/**
	 * 从buff获取指定regx的group内容
	 *
	 * @param buff
	 * @param regx
	 *            正则
	 * @param groupIndex
	 *            指定的groupIndex
	 * @return
	 */
	public static String getGroupString(CharSequence buff, String regx, int groupIndex) {
		if (buff != null) {
			Matcher matcher = Pattern.compile(regx).matcher(buff);
			if (matcher.find()) {
				return matcher.group(groupIndex);
			}
		} else {
			log.info("regx buff为空");
		}
		return null;
	}


	/**
	 * 从一个buff中获取html的tag元素，指定第几个
	 *
	 * @param buff
	 * @param tagName
	 * @param index
	 * @return
	 */
	public static String getChildTag(CharSequence buff, String tagName, int index) {
		List<String> list = getChildTagList(buff, tagName);
		if (index < list.size()) {
			return list.get(index);
		} else {
			return null;
		}
	}

	/**
	 * 获取某断buff中，某个tag的list
	 *
	 * @param buff
	 *            源buff
	 * @param tagName
	 *            tag的名称，例如dt tr等
	 * @return 一个包含tagHtml的list
	 */
	public static List<String> getChildTagList(CharSequence buff, String tagName) {
		List<String> list = new ArrayList<String>();
		Matcher matcher = Pattern.compile("<" + tagName + "[\\s|\\S]+?/" + tagName + ">").matcher(buff);
		int startPos = 0;
		while (matcher.find(startPos)) {
			startPos = matcher.end();
			list.add(matcher.group());
		}
		return list;
	}

	// 争对亿房网站的特殊处理
	public static List<String> fdcChildTagList(CharSequence buff, String beginName, String endName) {
		log.info(beginName + "[\\s|\\S]+?" + endName);
		List<String> list = new ArrayList<String>();
		Matcher matcher = Pattern.compile(beginName + "[\\s|\\S]+?" + endName).matcher(buff);
		int startPos = 0;
		while (matcher.find(startPos)) {
			startPos = matcher.end();
			list.add(matcher.group());
		}
		return list;
	}

	/**
	 * 简单移除html标签
	 *
	 * @param str
	 * @return
	 */
	public static String removeHtmlTag(String str) {
		if (str != null) {
			return str.replaceAll("<[^>]+>", " ").replaceAll("[\\t|\\n|\\t\\n]", " ").replaceAll("&nbsp;", " ").replaceAll("[\\s]{2,}", " ").trim();
		} else {
			return null;
		}
	}

	/**
	 * 去除js代码
	 *
	 * @param str
	 * @return
	 */
	public static String removeJs(String str) {
		if (str != null) {
			return str.replaceAll("(?i)<script[^>]+>[\\s|\\S]*</script>", "");
		} else {
			return null;
		}
	}

	/**
	 * 移除html标签，但保留某个标签。
	 *
	 * @param str
	 * @param excludeTag
	 * @return
	 */
	public static String removeHtmlTag(String str, String excludeTag) {
		String[] eArray = excludeTag.split(",");
		// 删除备注
		str = str.replaceAll("<!--[\\s|\\S]*?-->", "");
		HashMap<String, String> tempSrcMap = new HashMap<String, String>();
		for (String e : eArray) {
			if (e != null && !e.trim().equals("")) {
				if (!e.toLowerCase().equals("img")) {
					str = str.replaceAll("(?i)<\\s*" + e + "\\s+[^>]*>", "#EXT_S_" + e + "#");
					str = str.replaceAll("(?i)<\\s*" + e + ">", "#EXT_S_" + e + "#");
					str = str.replaceAll("(?i)</" + e + ">", "#EXT_E_" + e + "#");
					str = str.replaceAll("(?i)<" + e + "\\s*/\\s*>", "#EXT_A_" + e + "#");
				} else {
					str = str.replaceAll("(?i)<\\s*img\\s+[^>]*src=\"([^\"]+)\"[^>]*alt=\"([^\"]*)\">", "#IMG_$1#_$2#");
					str = str.replaceAll("(?i)<\\s*img\\s+[^>]*alt=\"([^\"]*)\"[^>]*src=\"([^\"]+)\"[^>]*>", "#IMG_$2#_$1#");
					str = str.replaceAll("(?i)<\\s*img\\s+[^>]*src=\"([^\"]+)\"[^>]*>", "#IMG_$1#");
				}
			}
		}
		str = removeHtmlTag(str);
		for (String e : eArray) {
			if (e != null && !e.trim().equals("")) {
				str = str.replaceAll("#EXT_S_" + e + "#", "<" + e + ">");
				str = str.replaceAll("#EXT_E_" + e + "#", "</" + e + ">");
				str = str.replaceAll("#EXT_A_" + e + "#", "<" + e + "/>");

				// 图片
				str = str.replaceAll("#IMG_(.*?)#_(.*?)#", "<img src=\"$1\" alt=\"$2\"/>");
				str = str.replaceAll("#IMG_(.*?)#", "<img src=\"$1\"/>");
			}
		}

		// 清除空的标签，例如<strong> </strong>
		// System.out.println("之前:"+str);
		for (String e : eArray) {
			if (e != null && !e.trim().equals("")) {
				str = str.replaceAll("(?i)<\\s*" + e + "\\s+[^>]*>\\s*<\\s*/" + e + "[^>]*>", "");
				str = str.replaceAll("(?i)<\\s*" + e + ">\\s*<\\s*/" + e + "\\s*>", "");
			}
		}
		// System.out.println("之后:"+str);
		return str;
	}

	/**
	 * 获取一个url的contextUrl，例如http://abc.com/dd/ee返回http://abc.com
	 *
	 * @return
	 */
	public static String getContextUrl(String url) {
		Matcher matcher = Pattern.compile("(http://[^/]+)/").matcher(url);
		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return null;
		}
	}

	/**
	 * 获取所有的匹配，组成一个list返回
	 *
	 * @param buff
	 * @param regx
	 * @param groupIndex
	 * @return
	 */
	public static List<String> getAllGroupString(CharSequence buff, String regx, int groupIndex) {
		List<String> list = new ArrayList<String>();
		if (buff != null) {
			Matcher matcher = Pattern.compile(regx).matcher(buff);
			int startPos = 0;
			while (matcher.find(startPos)) {
				startPos = matcher.end();
				list.add(matcher.group(groupIndex));
			}
		} else {
			log.info("regx buff为空");
		}
		return list;
	}

	/**
	 * 获取所有的匹配，包括所有group，并封装list，返回
	 *
	 * @param buff
	 * @param regx
	 * @return
	 */
	public static List<List<String>> getAllGroupString(CharSequence buff, String regx) {
		List<List<String>> list = new ArrayList<List<String>>();
		if (buff != null) {
			Matcher matcher = Pattern.compile(regx).matcher(buff);
			int startPos = 0;
			int groupCount = matcher.groupCount();
			while (matcher.find(startPos)) {
				startPos = matcher.end();
				List<String> result = new ArrayList<String>();
				for (int i = 1; i <= groupCount; i++) {
					result.add(matcher.group(i));
				}
				list.add(result);
			}
		} else {
			log.info("regx buff为空");
		}
		return list;
	}

	/**
	 * 返回唯一list，去掉同样的元素
	 *
	 * @param list
	 * @return
	 */
	public static List<String> removeDuplicate(List<String> list) {
		Set<String> set = new HashSet<String>();
		List<String> newList = new ArrayList<String>();
		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			String element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}


	/**
	 * 获取htmlTag中的propertyName值
	 *
	 * @param propertyName
	 * @param htmlTag
	 * @return
	 */
	public static String getPropertyByTag(String propertyName, CharSequence htmlTag) {
		if (htmlTag != null) {
			String value = RegxUtil.getGroupString(htmlTag, "(?i)\\s" + propertyName + "=\"([^\"]+)\"", 1);
			if (value == null) {
				value = RegxUtil.getGroupString(htmlTag, "(?i)\\s" + propertyName + "='([^']+)'", 1);
				if (value == null) {
					value = RegxUtil.getGroupString(htmlTag, "(?i)\\s" + propertyName + "=([^\\s>]+)", 1);
				}
			}
			return value;
		}
		return null;
	}

	/**
	 * 根据name获取value，一般用于提交
	 *
	 * @param name
	 * @param tags
	 * @return
	 */
	public static String getValueByElementName(String name, List<String> tags) {
		List<String> values = getValueListByElementName(name, tags);
		if (values.size() > 0) {
			if (values.size() > 1) {
				log.info("警告:name=" + name + "的元素共" + values.size() + "个，目前只返回第一个；返回全部的用法应该是getValueListByElementName(name,tags)");
			} else {
				return values.get(0);
			}
		}
		return null;
	}

	/**
	 * 获取当前tag的value，支持input textarea 和 select
	 * @param tag
	 * @return
	 */
	public static String getValueByElementName(String tag){
		String propertyName = RegxUtil.getPropertyByTag("name", tag);
		List<String> tagList = new ArrayList<String>();
		return getValueByElementName(propertyName,tagList);
	}

	/**
	 * 获取所有可提交的tag，包括input,textarea,select
	 * @return
	 */
	public static List<String> getAllSubmitableTag(CharSequence sourceHtml) {
		List<String> inputs = RegxUtil.getAllGroupString(sourceHtml, "(?i)<input\\s[^>]+>", 0);
		List<String> textareas = RegxUtil.getAllGroupString(sourceHtml, "(?i)<textarea\\s[^>]+>[\\s|\\S]+?</textarea>", 0);
		List<String> selects = RegxUtil.getAllGroupString(sourceHtml, "(?i)<select\\s[^>]+>[\\s|\\S]+?</select>", 0);
		List<String> all = new ArrayList<String>();
		all.addAll(inputs);
		all.addAll(textareas);
		all.addAll(selects);
		return all;
	}

	/**
	 * 根据name获取value的数组，一般用于提交
	 *
	 * @param name
	 * @param tags
	 * @return
	 */
	public static List<String> getValueListByElementName(String name, List<String> tags) {
		List<String> valueList = new ArrayList<String>();
		for (String element : tags) {
			String propertyName = RegxUtil.getPropertyByTag("name", element);
			if (name.equals(propertyName)) {
				if (element.toLowerCase().startsWith("<input ")) {
					String type = RegxUtil.getPropertyByTag("type", element);
					if (type != null && ("radio".equals(type.toLowerCase()) || "checkbox".equals(type.toLowerCase()))) {
						// radio和checkbox
						boolean checked = RegxUtil.getGroupString(element, "(?i)\\schecked[^a-zA-Z0-9-_]+", 0) != null;
						if (checked) {
							String value = RegxUtil.getPropertyByTag("value", element);
							if (value != null) {
								valueList.add(value);
							}
						}
					} else {
						// 其他
						String value = RegxUtil.getPropertyByTag("value", element);
						if (value != null) {
							valueList.add(value);
						}
					}

				} else if (element.toLowerCase().startsWith("<textarea ")) {
					String value = RegxUtil.getGroupString(element, "(?i)<textarea[^>]+>([\\s|\\S]*?)</textarea>", 1);
					if (value != null) {
						valueList.add(value);
					}
				} else if (element.toLowerCase().startsWith("<select ")) {
					List<String> options = RegxUtil.getAllGroupString(element, "(?i)<option[^>]+>", 0);
					for (String option : options) {
						String value = RegxUtil.getPropertyByTag("value", option);
						boolean selected = RegxUtil.getGroupString(element, "(?i)\\sselected[^a-zA-Z0-9-_]+", 0) != null;
						if (selected) {
							valueList.add(value);
						}
					}
				}
			}
		}
		return valueList;
	}

	public static void main(String[] args) {
	/*	String regx = "(p|strong|font|h2|h3|h4|h5|b|br)";
		String str = "<h2 id=\"26198\" class=\"subTitle\"><a title=\"受孕最佳时间\" href=\"26198.shtml\" target=\"_blank\">受孕最佳时间</a></h2>";
		System.out.println(RegxUtil.removeHtmlTag(str, "h2"));*/



		String html="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n" +
				"<html>\n" +
				" <head>\n" +
				"  <title>征信用户页面</title>\n" +
				"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
				"  <meta http-equiv=\"pragma\" content=\"no-cache\" />\n" +
				"  <meta http-equiv=\"Cache-Control\" content=\"no-store, must-revalidate\" />\n" +
				"  <meta http-equiv=\"expires\" content=\"0\" />\n" +
				"  <script type=\"text/javascript\"\n" +
				"   src=\"/QPCS/resources/js/jquery-1.4.2.min.js\">\n" +
				"</script>\n" +
				"  <script type=\"text/javascript\" src=\"/QPCS/resources/js/json.js\">\n" +
				"</script>\n" +
				"  <script language=\"javascript\"\n" +
				"   src=\"/QPCS/resources/tree/js/dhtmlXCommon.js\">\n" +
				"</script>\n" +
				"  <script language=\"javascript\"\n" +
				"   src=\"/QPCS/resources/tree/js/dhtmlXTree.js\">\n" +
				"</script>\n" +
				"  <script language=\"javascript\"\n" +
				"   src=\"/QPCS/resources/tree/js/dhtmlXTreeExtend.js\">\n" +
				"</script>\n" +
				"  <script language=\"javascript\"\n" +
				"   src=\"/QPCS/resources/plug/jwindow/jquery.jWindow.js\">\n" +
				"</script>\n" +
				"  <script language=\"javascript\"\n" +
				"   src=\"/QPCS/resources/plug/jwindow/jquery.pngFix.pack.js\">\n" +
				"</script>\n" +
				"  <link type=\"text/css\" rel=\"stylesheet\"\n" +
				"   href=\"/QPCS/resources/plug/jwindow/jwindow.css\">\n" +
				"<script type=\"text/javascript\" src=\"/QPCS/resources/My97DatePicker/WdatePicker.js\"></script>\n" +
				"  \n" +
				"  <link type=\"text/css\" rel=\"stylesheet\"\n" +
				"   href=\"/QPCS/resources/css/alliance.css\" />\n" +
				"  <link type=\"text/css\" rel=\"stylesheet\"\n" +
				"   href=\"/QPCS/resources/plug/jselect/jselect.css\" />\n" +
				"<script type=\"text/javascript\" src=\"/QPCS/resources/plug/keyword-zjg-query.js\"></script>\n" +
				"<script type=\"text/javascript\" src=\"/QPCS/resources/ext2.1/adapter/ext/ext-base.js\"></script>\n" +
				"        <script type=\"text/javascript\" src=\"/QPCS/resources/ext2.1/ext-all.js\"></script>\n" +
				"        <script type=\"text/javascript\" src=\"/QPCS/resources/ext2.1/locale/ext-lang-zh_CN.js\"></script>\n" +
				"  <link rel=\"stylesheet\" type=\"text/css\" href=\"/QPCS/resources/ext2.1/resources/css/ext-all.css\" />\n" +
				"  \n" +
				"\n" +
				"  <script type=\"text/javascript\">\n" +
				"\n" +
				"$(function() {\n" +
				" //选项卡\n" +
				" var url = \"/QPCS/resources/plug/qpcs/commonDicTree.jsp?hiddenId=workflowState&viewId=workflowStateDesc&code=creditApplyStatus\";\n" +
				" $(\"#workflowStateDesc\").showJWindow(\"选择信用报告查询申请状态\", url,\n" +
				"   \"keyword-input-select\");\n" +
				" \n" +
				" $(\"#uploadExcel\").showJWindow(\"批量导入申请\", \"toUpload.action\");\n" +
				"});\n" +
				"\n" +
				"function closeComonDicJWindow(returnValue) {\n" +
				" $('#fancy_wrap').hide();\n" +
				" $.each(returnValue, function(i, n) {\n" +
				"  $('#' + n.hiddenId).val(n.code);\n" +
				"  $('#' + n.viewId).val(n.name);\n" +
				" });\n" +
				"}\n" +
				"\n" +
				"function goSubmit() {\n" +
				"    document.forms[0].action=\"qryPersonCreditApply.action\";\n" +
				" document.forms[0].submit();\n" +
				"}\n" +
				"\n" +
				"function isDelete(id) {\n" +
				" if (confirm(\"您确定要删除该记录吗？\")) {\n" +
				"  location.href = \"deletePersonCreditApply.action?customerQuery.id=\" + id\n" +
				"    + \"&customerQuery.creditType=\" + $(\"#creditType\").val();\n" +
				" }\n" +
				"}\n" +
				"\n" +
				"function loactionAdd() {\n" +
				" var channelAndDesc ='';\n" +
				" if(channelAndDesc){\n" +
				"  var channelJson = JSON.parse(channelAndDesc);\n" +
				"  var channel = channelJson.channel;\n" +
				"  var desc = channelJson.desc;\n" +
				"  if(channel != '000000'){\n" +
				"   alert(\"您已绑定渠道【\"+desc+\"】，禁止在本地新建申请！\");\n" +
				"   return;\n" +
				"  }\n" +
				" }\n" +
				" location.href = \"addPersonCreditApply.action\";\n" +
				"}\n" +
				"\n" +
				"function view(id) {\n" +
				" location.href = \"viewPersonCreditApply.action?customerQuery.id=\" + id;\n" +
				"}\n" +
				"\n" +
				"function edit(id) {\n" +
				" location.href = \"editPersonCreditApply.action?customerQuery.id=\" + id;\n" +
				"}\n" +
				"\n" +
				"function doReset() {\n" +
				" var customName = document.getElementById(\"customName\");\n" +
				" customName.value = \"\";\n" +
				" var workflowStateDesc = document.getElementById(\"workflowStateDesc\");\n" +
				" workflowStateDesc.value = \"\";\n" +
				" var workflowState = document.getElementById(\"workflowState\");\n" +
				" workflowState.value = \"\";\n" +
				"}\n" +
				"\n" +
				"function viewCreditApplyImage(id,fileGroupTypeId,mainId,creditType,\n" +
				"  activetaskId,realId){\n" +
				"     var myMask = new Ext.LoadMask(Ext.getBody(), {msg: '数据加载中，请稍后...'});\n" +
				"        myMask.show();\n" +
				"        document.myForm.action=\"getValidCreditReportInfo.action\";\n" +
				"     document.getElementById(\"customerQuery.id\").value = realId;//原申请id\n" +
				"        Ext.Ajax.request({\n" +
				"                 form: 'myForm',\n" +
				"                 method: 'post',\n" +
				"                 success: function(response, option){\n" +
				"                  myMask.hide();\n" +
				"                   var results = Ext.util.JSON.decode(response.responseText);\n" +
				"          if(results.succ == 'true'){\n" +
				"         location.href = \"/QPCS/qpcs/creditApply/viewCreditApplyImage.jsp?customerQueryId=\"+id\n" +
				"                      +\"&fileGroupTypeId=\"+fileGroupTypeId\n" +
				"                      +\"&mainId=\"+mainId\n" +
				"                      +\"&creditType=\"+creditType\n" +
				"                      +\"&returnUrl=qryPersonCreditApply\"\n" +
				"                      +\"&activeId=\" + results.activeId\n" +
				"                      +\"&realMainId=\" + results.mainId\n" +
				"        +\"&isCreditQuery=\" + results.isCreditQuery;\n" +
				"\n" +
				"        }else{\n" +
				"            Ext.MessageBox.alert(\"信息提示\",results.msg); \n" +
				"        }\n" +
				"     },\n" +
				"     failure: function(response,option){\n" +
				"        myMask.hide();\n" +
				"        Ext.MessageBox.alert(\"信息提示\",\"【数据加载超时，请稍候再试...】\");\n" +
				"     }\n" +
				"       }); \n" +
				"  }\n" +
				"\n" +
				"function toUpload() {\n" +
				" location.href = \"toUpload.action\";\n" +
				"}\n" +
				"//批量提交\n" +
				"function submitDataForAll(){\n" +
				" var ids = \"\";\n" +
				" var msg = \"\";\n" +
				" $(\".ckx:checked\").each(function(){\n" +
				"    if(ids.length>0){\n" +
				"     ids =ids+\";\"+$(this).val();\n" +
				"    }else{\n" +
				"    ids =  $(this).val();\n" +
				"    }\n" +
				"    //验证信息是否 合法\n" +
				"    if(!$(this).attr(\"certTypeDesc\")){\n" +
				"     msg +=\"提交的数据中含有非法证件类型！\";\n" +
				"    return false;\n" +
				"     }\n" +
				"     if(!$(this).attr(\"queryReasonDesc\")){\n" +
				"      msg +=\"提交的数据中含有非法查询原因！\";\n" +
				"    return false;\n" +
				"   }\n" +
				"   if(!$(this).attr(\"queryTypeDesc\")){\n" +
				"     msg +=\"提交的数据中含有非法查询类型！\";\n" +
				"     return false;\n" +
				"   }\n" +
				" });\n" +
				" if(msg.length>0){\n" +
				"  alert(msg);\n" +
				"  return false;\n" +
				" }\n" +
				" //alert(ids);\n" +
				" var tmp = new Date();\n" +
				" if(ids.length>0){\n" +
				"  $.getJSON(\"submitCreditApplyForAll.action?tmp=\"+tmp,{id:ids},function(json){\n" +
				"   if(json.rst == 1){\n" +
				"    alert(\"提交成功！\");\n" +
				"    goSubmit();\n" +
				"   }else{\n" +
				"    alert(json.msg);\n" +
				"    goSubmit();\n" +
				"   }\n" +
				"  });\n" +
				" }else{\n" +
				"  alert(\"请选择要提交的数据！\");\n" +
				" }\n" +
				"}\n" +
				"//收回 任务\n" +
				"function redoWorkitem(queryId,caseId){\n" +
				" var tmp = new Date();\n" +
				" $.getJSON(\"redoWorkFlow.action?tmp=\"+tmp,{json:\"{id:\"+queryId+\",caseId:\"+caseId+\"}\"},function(json){\n" +
				"  if(json.rst == 1){\n" +
				"   alert(\"收回成功！\");\n" +
				"   goSubmit();\n" +
				"  }else{\n" +
				"   alert(json.msg);\n" +
				"   goSubmit();\n" +
				"  }\n" +
				" });\n" +
				"}\n" +
				"function viewAuditMind(aduitMain){//查看那审核意见\n" +
				"  alert(aduitMain);\n" +
				"}\n" +
				"//建立档案\n" +
				"function newScanAction(id){ \n" +
				" var dWidth = document.documentElement.clientWidth?document.documentElement.clientWidth:$(document).width();\n" +
				" var dHeight = document.documentElement.clientHeight?document.documentElement.clientHeight:$(document).height();\n" +
				" //跳转到扫描，信息录入页面 个人征信扫描队列ID  bcf0e280be5146568f6e0843e51a6de0\n" +
				" window.location.href=\"newDocActiveTaskApply.action?taskId=bcf0e280be5146568f6e0843e51a6de0&dWidth=\"+dWidth+\"&dHeight=\"+dHeight+\"&id=\"+id;\n" +
				"}\n" +
				"\n" +
				"</script>\n" +
				" </head>\n" +
				" <body>\n" +
				"\n" +
				"\n" +
				"     \n" +
				"<form id=\"myForm\" name=\"myForm\" onsubmit=\"customOnsubmit_myForm(); return true;\" action=\"qryPersonCreditApply.action\" method=\"post\">\n" +
				"\n" +
				"   <input type=\"hidden\" id=\"creditType\" name=\"customerQuery.creditType\"\n" +
				"    value=\"0\" />\n" +
				"   <input type=\"hidden\" id=\"workflowState\"\n" +
				"    name=\"customerQuery.workflowState\"\n" +
				"    value=\"\" />\n" +
				"   <input type=\"hidden\" id=\"customerQuery.id\"  name=\"customerQuery.id\"/>\n" +
				"   <div id=location>\n" +
				"    您的位置：查询申请>>个人信用报告查询\n" +
				"   </div>\n" +
				"   <div id=main>\n" +
				"    <!--操作条区域-->\n" +
				"    <div class=\"grid-button-panel\">\n" +
				"     <table>\n" +
				"      <tr width='100%'>\n" +
				"       <td width='20%'>\n" +
				"        客户姓名：\n" +
				"        <input type=\"text\" name=\"customerQuery.customName\"\n" +
				"         id=\"customName\" value=\"\"\n" +
				"         STYLE=\"width: 165px;\" />\n" +
				"       </td>\n" +
				"       <td width='20%'>\n" +
				"        &nbsp;&nbsp;&nbsp;状态：\n" +
				"        <input type=\"text\" name=\"customerQuery.workflowStateDesc\"\n" +
				"         id=\"workflowStateDesc\"\n" +
				"         value=\"\"\n" +
				"         STYLE=\"width: 165px;\" />\n" +
				"       </td>\n" +
				"       <td width=\"18%\" >开始时间：\n" +
				"        <input id='starttime' name='customerQuery.queryStartDate' class=\"Wdate\" onfocus=\"WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})\" value=''/>\n" +
				"          </td>\n" +
				"          <td width=\"18%\">&nbsp;结束时间：\n" +
				"        <input id='endtime' name='customerQuery.queryEndDate' class=\"Wdate\" onfocus=\"WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})\" value=''/>\n" +
				"          </td>\n" +
				"       <td width='25%' align=\"left\">\n" +
				"        <button class=\"s-button\" onclick=\"goSubmit();\">\n" +
				"         <span class=\"s-icon-search\">&nbsp;</span>查询\n" +
				"        </button>\n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"        <button class=\"s-button\" onclick=\"doReset();\">\n" +
				"         <span class=\"s-icon-reset\">&nbsp;</span>重置\n" +
				"        </button>\n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"        <button class=\"s-button\" onclick=\"document.location.reload();\">\n" +
				"         刷新页面\n" +
				"        </button>\n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"        <!--  <button class=\"s-button\" id=\"uploadExcel\" >\n" +
				"         批量上传\n" +
				"        </button>\n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"        <button class=\"s-button\" id=\"submitAll\" onclick=\"submitDataForAll();\" >\n" +
				"         批量提交\n" +
				"        </button>-->\n" +
				"       </td>\n" +
				"      </tr>\n" +
				"     </table>\n" +
				"    </div>\n" +
				"  </form>\n" +
				"\n" +
				"\n" +
				"<script type=\"text/javascript\">\n" +
				" function customOnsubmit_myForm() {\n" +
				" \n" +
				" }\n" +
				"</script>\n" +
				"\n" +
				"\n" +
				"\n" +
				"  <!--信息提示区域-->\n" +
				"  \n" +
				"  <button class=\"s-button\" onclick=\"loactionAdd();\">\n" +
				"   <span class=\"s-icon-add\">&nbsp;</span>添加\n" +
				"  </button>\n" +
				"\n" +
				"  <!--信息列表区域-->\n" +
				"  <table class=\"c-info-list\">\n" +
				"   <thead style=\"height: 20px;\">\n" +
				"    <tr>\n" +
				"     <th width=\"4%\">\n" +
				"      序号\n" +
				"     </th>\n" +
				"     <th width=\"8%\">\n" +
				"      客户姓名\n" +
				"     </th>\n" +
				"     <th width=\"8%\">\n" +
				"      证件类型\n" +
				"     </th>\n" +
				"     <th width=\"12%\">\n" +
				"      证件号码\n" +
				"     </th>\n" +
				"     <th width=\"10%\">\n" +
				"      查询原因\n" +
				"     </th>\n" +
				"     <!-- <th width=\"8%\">\n" +
				"      查询类型\n" +
				"     </th> -->\n" +
				"     <th width=\"7%\">\n" +
				"      授权日期\n" +
				"     </th>\n" +
				"     <th width=\"12%\">\n" +
				"      查询申请时间\n" +
				"     </th>\n" +
				"     <th width=\"12%\">\n" +
				"      审批时间\n" +
				"     </th>\n" +
				"     <th width=\"6%\">\n" +
				"      渠道\n" +
				"     </th>\n" +
				"     <th width=\"6%\">\n" +
				"      报告状态\n" +
				"     </th>\n" +
				"     <th width=\"10%\">\n" +
				"      操作\n" +
				"     </th>\n" +
				"    </tr>\n" +
				"   </thead>\n" +
				"   <tbody>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       1\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       刘鑫\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       43070319941009685X\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       资信审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-19\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-19 09:12:21\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-19 09:15:30\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e86801879711d56c2d71');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        <!-- 可以查看报告 -->\n" +
				"         <img src=\"/QPCS/resources/images/alliance/icon/yuanwen.gif\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:viewCreditApplyImage('8b8093948595e86801879711d56c2d71','8a8b800d3ee3bc9f013ee3c53b1a0002','8b8093948595e86801879710271f2cad','0','2f3ddf5107ed499e8c4f6ede1551b3a1','8b8093948595e86801879711d56c2d71');\"\n" +
				"         title=\"查看影像\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       2\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       陈建兰\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827197310261669\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       信用卡审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 11:16:39\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 11:17:43\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e86801878d36ead22a64');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       3\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       王石生\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827197206077634\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷款审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:38:42\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:39:43\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01878cdd3d217fff');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       4\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       王尤成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827195707120013\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       担保资格审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:38:16\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:40:08\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01878cdcd8b17fd5');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       5\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       王尤仁\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827196001177610\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       担保资格审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:37:47\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:40:19\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01878cdc69547f93');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       6\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       郭湘洪\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827196303296818\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       担保资格审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:37:27\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-17 09:40:32\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01878cdc18ab7f61');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       7\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       曹华春\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431025198705086815\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       担保资格审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-12\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-12 17:34:01\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-12 17:34:15\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e868018774d09d462177');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       8\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       马强\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       152104199603101916\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷款审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11 17:02:35\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11 17:03:00\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e86801876f8d794351d8');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       9\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       周继孝\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431022198308106094\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷后管理\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11 10:54:28\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-11 10:55:07\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e86801876e3c73ae5927');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       10\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       邝跃兵\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827197909021231\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷款审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 15:19:44\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 15:20:06\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01876a08f3c774b4');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       11\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       刘小美\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       432827197603011225\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       客户准入资格审查\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 14:55:29\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 15:00:04\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db018769f2c0ad634d');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       12\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       雷龙\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431025199108176032\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷款审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 11:11:58\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 11:12:25\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db018769261fc325f9');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       13\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       王莉媛\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431025199509026027\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷款审批\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 11:11:36\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-10 11:13:23\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093938595e6db01876925c9ba25b8');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       14\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       曾萍萍\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431025198408200220\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷后管理\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06 14:44:08\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06 14:44:48\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e8680187554eee714917');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"     <tr>\n" +
				"      <td align=\"left\">\n" +
				"       <!-- 只有存在授权 档案 的待提交  才能批量提交 -->\n" +
				"       \n" +
				"       \n" +
				"       \n" +
				"        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
				"       \n" +
				"       15\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       李俊锋\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       身份证\n" +
				"      </td>\n" +
				"      <td>\n" +
				"       431025198911070419\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       贷后管理\n" +
				"      </td>\n" +
				"      \n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06 14:43:46\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       2023-04-06 14:44:57\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       审批通过\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       已生成\n" +
				"      </td>\n" +
				"      <td align=\"left\">\n" +
				"       \n" +
				"       <!-- 审批通过 -->\n" +
				"       \n" +
				"        <img src=\"/QPCS/resources/images/alliance/icon/ckmx.png\"\n" +
				"         class=\"image-link\"\n" +
				"         onclick=\"javascript:view('8b8093948595e8680187554e95f348eb');\"\n" +
				"         title=\"查看信用报告查询申请详细信息\" />\n" +
				"        \n" +
				"       \n" +
				"       <!-- 打回 -->\n" +
				"       \n" +
				"       <!-- 待提交  没有授权档案 -->\n" +
				"       \n" +
				"       <!-- 待提交   存在授权档案-->\n" +
				"       \n" +
				"       <!-- 已收回 流程-->\n" +
				"       \n" +
				"       \n" +
				"      </td>\n" +
				"     </tr>\n" +
				"    \n" +
				"   </tbody>\n" +
				"  </table>\n" +
				"  <div style=\"text-align: right; width: 100%;\">\n" +
				"   <!--分页信息区域-->\n" +
				"   <script type=\"text/javascript\">\n" +
				"var curpage = +'1'.replace(',','');\n" +
				"var pagecount = 60;\n" +
				"//向指定页码翻页\n" +
				"function turnPage(pageNumber){\n" +
				" //document.forms[0].reset();\n" +
				" document.forms[0].action = document.forms[0].action + \"?paginationList.currentPage=\" + pageNumber;\n" +
				" document.forms[0].submit()\n" +
				"}\n" +
				"\n" +
				"function turnFirst(){\n" +
				" if(curpage>1) turnPage(1);\n" +
				"}\n" +
				"\n" +
				"function turnEnd(){\n" +
				" if(pagecount!=0 && pagecount!=curpage) turnPage(pagecount);\n" +
				"}\n" +
				"\n" +
				"function turnNext(){\n" +
				" if(curpage<pagecount) turnPage(curpage+1);\n" +
				"}\n" +
				"\n" +
				"function turnPre(){\n" +
				" if(curpage>1) turnPage(curpage-1);\n" +
				"}\n" +
				"\n" +
				"function jumpPage(obj){\n" +
				" if(event.keyCode==13){\n" +
				"  event.keyCode = 9;\n" +
				"  if(isDigital(obj.value)){\n" +
				"   var pagenum = obj.value;\n" +
				"   if(pagenum!=curpage){\n" +
				"    if(pagenum>pagecount) pagenum=pagecount;\n" +
				"    turnPage(pagenum);\n" +
				"   }\n" +
				"  }else{\n" +
				"   obj.value = curpage;\n" +
				"  }\n" +
				" }\n" +
				" obj.focus()\n" +
				"}\n" +
				"//判断是否为正整数\n" +
				"function isDigital(str){\n" +
				" var r = /^[0-9]*[1-9][0-9]*$/;\n" +
				" return r.test(str);\n" +
				"}\n" +
				"\n" +
				"</script>\n" +
				"<table class=\"page\">\n" +
				" <tr>\n" +
				"  <td style=\"text-align: left; font: verdana\">\n" +
				"   <strong>1-15</strong> 条 共<strong>897 </strong>条\n" +
				"  </td>\n" +
				"  <td style=\"text-align: right\">\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/back_b.gif\" class=\"image-link\" align=\"absmiddle\" onclick=\"turnFirst()\" />\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/back.gif\" class=\"image-link\" align=\"absmiddle\" onclick=\"turnPre()\" />\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/line.gif\" align=\"absmiddle\" />\n" +
				"   第 <label><input id=\"testinout\" type=\"text\" onfocus=\"this.select()\" size=\"2\" value=\"1\" onkeydown=\"jumpPage(this)\" /></label>\n" +
				"   页/共<strong>60 </strong>页\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/line.gif\" align=\"absmiddle\" />\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/front.gif\" class=\"image-link\" align=\"absmiddle\" onclick=\"turnNext()\" />\n" +
				"   <img src=\"/QPCS/resources/images/alliance/common/front_f.gif\" class=\"image-link\" align=\"absmiddle\" onclick=\"turnEnd()\" />\n" +
				"  </td>\n" +
				" </tr>\n" +
				"</table>\n" +
				"  </div>\n" +
				"  \n" +
				"\n" +
				" </body>\n" +
				"</html>";

		     Document document = Jsoup.parse(html);
		     Element body = document.body();
		     Elements elementsByClass = body.getElementsByClass("c-info-list");
			  for(Element tables :elementsByClass){
				  Elements trs = tables.getElementsByTag("tr");
				  for(Element tr: trs ){
				  	  System.out.println();
					  Elements tds = tr.getElementsByTag("td");
					  for(int i=0;i<tds.size();i++){
						  Element td=tds.get(i);
						  if(i==tds.size()-1){
							  Elements img = td.getElementsByTag("img");
							  if(img.size()>=2){
								  Element element = img.get(1);
								  String onclick = element.attr("onclick");
								  String substring = onclick.substring(onclick.indexOf("(")+1, onclick.indexOf(")")).replaceAll("'","");
								  System.out.println(substring);
							  }
						  }else{
							  System.out.println(td.text());
						  }
					  }


				  }

			  }
		System.out.println();
	}

}
