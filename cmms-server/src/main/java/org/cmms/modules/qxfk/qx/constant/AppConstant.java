package org.cmms.modules.qxfk.qx.constant;

/**
 * 描述:<br/>
 * 平台全局常量定义
 *
 * @author lijibin@ssit-xm.com.cn 2017年10月13日
 */
public class AppConstant {
	/** 接口响应代码定义 : respCode **/
	public static final String RESP_CODE = "respCode";

	/** 接口响应信息定义 : respMsg **/
	public static final String RESP_MSG = "respMsg";

	/** APP 用户令牌的变量名称定义 **/
	public static final String USER_TOKEN = "token";

	/** 接口签名信息定义 : sign 签名摘要 **/
	public static final String SIGN = "sign";

	/** 常量定义 : CODE **/
	public static final String CODE = "code";

	/** 常量定义 : success 是否成功 **/
	public static final String SUCCESS = "success";

	/** 常量定义 : message 结果描述 **/
	public static final String MESSAGE = "message";

	/** 常量定义 : object 数据对象 **/
	public static final String OBJECT = "object";

	/** 常量定义 : UTF-8字符集 **/
	public static final String CHARSET = "UTF-8";

	/** 常量定义 : 分隔符 逗号 **/
	public static final String SPLIT_COMMA = ",";

	/** 常量定义 : 分隔符 竖线 **/
	public static final String SPLIT_VERTICAL = "|";

	/** 常量定义 : 分隔符 短横线 **/
	public static final String SPLIT_HORIZONTAL = "-";

	/** 常量定义 : 分隔符 下划线 **/
	public static final String SPLIT_UNDERLINE = "_";

	/** 常量定义 : Http Content Type Json格式 **/
	public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

	/** 常量定义 : 空 - NULL **/
	public static final String NULL = "null";

	/** 常量定义 : 布尔类型 是 **/
	public static final String YES_CN = "是";

	/** 常量定义 : 布尔类型 否 **/
	public static final String NO_CN = "否";

	/** 默认是属性配置文件名称 : application.properties **/
	public static final String DEFAULT_PROPERTY_FILE = "application.properties";

	/** 默认是属性配置文件相路径名称 : properties/application.properties **/
	public static final String DEFAULT_PROPERTY_PATH = "properties/application.properties";

	/** ftp配置文件 **/
	public static final String FTP_PROPERTY_PATH = "properties/ftp.properties";

	/** sftp配置文件 **/
	public static final String SFTP_PROPERTY_PATH = "properties/sftp.properties";

	/** 常量定义 : 树形控件默认父节点的ID - 0 **/
	public static final String DEFAULT_TREE_VIEW_ID = "0";

	/** 用户主键 **/
	public static final String USER_ID = "userId";

	/** 域名 **/
	public static final String YM_TEST= "http://uatapi.7starlab.com";
	//public static final String YM_PROD= "https://api.7starlab.com";
	public static final String YM_PROD= "http://11.1.64.155:8085";

	/** 签名盐 **/
	public static final String SIGN_SALT_TEST= "b41f9a3920df481d8362f11e100652d4";
	public static final String SIGN_SALT_PROD= "e4eff9c4c7004228a094e5b1935e6e74";

	/** 账号 **/
	public static final String ACCOUNT_TEST= "hnlynsh0080";
	public static final String ACCOUNT_PROD= "hnlynsh0110";

	/** 账号 **/
	public static final String API_KEY_TEST= "259beb9c6c8b4453";
	public static final String APU_KEY_PROD= "9334f01b03854b83";

	/** 模板ID **/
	public static final String TEMPLATE_ID_TEST= "M000110";
	public static final String TEMPLATE_ID_PROD= "M000337";
	public static final String PROD= "prod";
	//七星报告申请API
	public static final String API_BGSQ= "/api/product/proApply.do";
	public static final String API_PDF= "/api/product/reportDownload.do";
	public static final String API_JSON= "/api/product/reportDataDownload.do";


}
