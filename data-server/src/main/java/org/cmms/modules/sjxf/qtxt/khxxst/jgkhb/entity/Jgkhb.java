package org.cmms.modules.sjxf.qtxt.khxxst.jgkhb.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 机构客户表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("ECIF_ORG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ECIF_ORG对象", description="机构客户表")
public class Jgkhb {
    
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String dataFlag;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**机构客户类型*/
	@Excel(name = "机构客户类型", width = 15)
    @ApiModelProperty(value = "机构客户类型")
	private String orgCustType;
	/**国家或地区代码*/
	@Excel(name = "国家或地区代码", width = 15)
    @ApiModelProperty(value = "国家或地区代码")
	private String nationCode;
	/**行政区划代码*/
	@Excel(name = "行政区划代码", width = 15)
    @ApiModelProperty(value = "行政区划代码")
	private String areaCode;
	/**组织机构类型*/
	@Excel(name = "组织机构类型", width = 15)
    @ApiModelProperty(value = "组织机构类型")
	private String orgType;
	/**组织机构代码*/
	@Excel(name = "组织机构代码", width = 15)
    @ApiModelProperty(value = "组织机构代码")
	private String orgCode;
	/**营业执照号码*/
	@Excel(name = "营业执照号码", width = 15)
    @ApiModelProperty(value = "营业执照号码")
	private String busiLicNo;
	/**行业分类（主营）*/
	@Excel(name = "行业分类（主营）", width = 15)
    @ApiModelProperty(value = "行业分类（主营）")
	private String mainIndustry;
	/**行业分类（副营）*/
	@Excel(name = "行业分类（副营）", width = 15)
    @ApiModelProperty(value = "行业分类（副营）")
	private String minorIndustry;
	/**产业划分*/
	@Excel(name = "产业划分", width = 15)
    @ApiModelProperty(value = "产业划分")
	private String industryDivision;
	/**企业性质*/
	@Excel(name = "企业性质", width = 15)
    @ApiModelProperty(value = "企业性质")
	private String entProperty;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15)
    @ApiModelProperty(value = "企业规模")
	private String entScale;
	/**资产规模*/
	@Excel(name = "资产规模", width = 15)
    @ApiModelProperty(value = "资产规模")
	private String assetsScale;
	/**员工规模*/
	@Excel(name = "员工规模", width = 15)
    @ApiModelProperty(value = "员工规模")
	private String employeeScale;
	/**经济类型*/
	@Excel(name = "经济类型", width = 15)
    @ApiModelProperty(value = "经济类型")
	private String economicType;
	/**控股类型*/
	@Excel(name = "控股类型", width = 15)
    @ApiModelProperty(value = "控股类型")
	private String comHoldType;
	/**组织形式*/
	@Excel(name = "组织形式", width = 15)
    @ApiModelProperty(value = "组织形式")
	private String orgForm;
	/**投资主体*/
	@Excel(name = "投资主体", width = 15)
    @ApiModelProperty(value = "投资主体")
	private String investType;
	/**企业隶属*/
	@Excel(name = "企业隶属", width = 15)
    @ApiModelProperty(value = "企业隶属")
	private String entBelong;
	/**成立日期*/
	@Excel(name = "成立日期", width = 15)
    @ApiModelProperty(value = "成立日期")
	private String buildDate;
	/**主管部门*/
	@Excel(name = "主管部门", width = 15)
    @ApiModelProperty(value = "主管部门")
	private String superDept;
	/**主营业务*/
	@Excel(name = "主营业务", width = 15)
    @ApiModelProperty(value = "主营业务")
	private String mainBusiness;
	/**兼营业务*/
	@Excel(name = "兼营业务", width = 15)
    @ApiModelProperty(value = "兼营业务")
	private String minorBusiness;
	/**经营方式*/
	@Excel(name = "经营方式", width = 15)
    @ApiModelProperty(value = "经营方式")
	private String businessMode;
	/**开始营业时间*/
	@Excel(name = "开始营业时间", width = 15)
    @ApiModelProperty(value = "开始营业时间")
	private String busiStartDate;
	/**经费来源*/
	@Excel(name = "经费来源", width = 15)
    @ApiModelProperty(value = "经费来源")
	private String fundSource;
	/**经济区编码*/
	@Excel(name = "经济区编码", width = 15)
    @ApiModelProperty(value = "经济区编码")
	private String zoneCode;
	/**外汇许可证号码*/
	@Excel(name = "外汇许可证号码", width = 15)
    @ApiModelProperty(value = "外汇许可证号码")
	private String fexcPrmCode;
	/**产业化龙头企业级别*/
	@Excel(name = "产业化龙头企业级别", width = 15)
    @ApiModelProperty(value = "产业化龙头企业级别")
	private String topCorpLevel;
	/**特种经营标志*/
	@Excel(name = "特种经营标志", width = 15)
    @ApiModelProperty(value = "特种经营标志")
	private String comSpBusiness;
	/**特种经营许可证编号*/
	@Excel(name = "特种经营许可证编号", width = 15)
    @ApiModelProperty(value = "特种经营许可证编号")
	private String comSpLicNo;
	/**特种经营情况*/
	@Excel(name = "特种经营情况", width = 15)
    @ApiModelProperty(value = "特种经营情况")
	private String comSpDetail;
	/**特种许可证颁发机关*/
	@Excel(name = "特种许可证颁发机关", width = 15)
    @ApiModelProperty(value = "特种许可证颁发机关")
	private String comSpLicOrg;
	/**特种经营起始日期*/
	@Excel(name = "特种经营起始日期", width = 15)
    @ApiModelProperty(value = "特种经营起始日期")
	private String comSpStrDate;
	/**特种经营到期日期*/
	@Excel(name = "特种经营到期日期", width = 15)
    @ApiModelProperty(value = "特种经营到期日期")
	private String comSpEndDate;
	/**法定代表人名称*/
	@Excel(name = "法定代表人名称", width = 15)
    @ApiModelProperty(value = "法定代表人名称")
	private String legalReprName;
	/**法定代表人性别*/
	@Excel(name = "法定代表人性别", width = 15)
    @ApiModelProperty(value = "法定代表人性别")
	private String legalReprGender;
	/**法定代表人证件类型*/
	@Excel(name = "法定代表人证件类型", width = 15)
    @ApiModelProperty(value = "法定代表人证件类型")
	private String legalReprIdentType;
	/**法定代表人证件号码*/
	@Excel(name = "法定代表人证件号码", width = 15)
    @ApiModelProperty(value = "法定代表人证件号码")
	private String legalReprIdentNo;
	/**法定代表人联系电话*/
	@Excel(name = "法定代表人联系电话", width = 15)
    @ApiModelProperty(value = "法定代表人联系电话")
	private String legalReprTel;
	/**法定代表人户籍地址*/
	@Excel(name = "法定代表人户籍地址", width = 15)
    @ApiModelProperty(value = "法定代表人户籍地址")
	private String legalReprAddr;
	/**法定代表人所在国家（地区）*/
	@Excel(name = "法定代表人所在国家（地区）", width = 15)
    @ApiModelProperty(value = "法定代表人所在国家（地区）")
	private String legalReprNationCode;
	/**财务报表类型*/
	@Excel(name = "财务报表类型", width = 15)
    @ApiModelProperty(value = "财务报表类型")
	private String finRepType;
	/**总资产*/
	@Excel(name = "总资产", width = 15)
    @ApiModelProperty(value = "总资产")
	private java.math.BigDecimal totalAssets;
	/**总负债*/
	@Excel(name = "总负债", width = 15)
    @ApiModelProperty(value = "总负债")
	private java.math.BigDecimal totalDebt;
	/**年收入*/
	@Excel(name = "年收入", width = 15)
    @ApiModelProperty(value = "年收入")
	private java.math.BigDecimal annualIncome;
	/**年利润*/
	@Excel(name = "年利润", width = 15)
    @ApiModelProperty(value = "年利润")
	private java.math.BigDecimal annualProfit;
	/**通讯地址*/
	@Excel(name = "通讯地址", width = 15)
    @ApiModelProperty(value = "通讯地址")
	private String orgAddr;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
    @ApiModelProperty(value = "邮政编码")
	private String orgZipcode;
	/**联系人*/
	@Excel(name = "联系人", width = 15)
    @ApiModelProperty(value = "联系人")
	private String orgCus;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String orgTel;
	/**传真号码*/
	@Excel(name = "传真号码", width = 15)
    @ApiModelProperty(value = "传真号码")
	private String orgFex;
	/**电子邮件地址*/
	@Excel(name = "电子邮件地址", width = 15)
    @ApiModelProperty(value = "电子邮件地址")
	private String orgEmail;
	/**主页*/
	@Excel(name = "主页", width = 15)
    @ApiModelProperty(value = "主页")
	private String orgHomepage;
	/**微博*/
	@Excel(name = "微博", width = 15)
    @ApiModelProperty(value = "微博")
	private String orgWeibo;
	/**微信*/
	@Excel(name = "微信", width = 15)
    @ApiModelProperty(value = "微信")
	private String orgWeixin;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remarks;
	/**最后更新系统*/
	@Excel(name = "最后更新系统", width = 15)
    @ApiModelProperty(value = "最后更新系统")
	private String lastUpdateSys;
	/**最后更新人*/
	@Excel(name = "最后更新人", width = 15)
    @ApiModelProperty(value = "最后更新人")
	private String lastUpdateUser;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 15)
    @ApiModelProperty(value = "最后更新时间")
	private String lastUpdateTm;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private String txSeqNo;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
//	/**天入库表编号-对不同的表名唯一*/
//	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
//    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
//	private Integer dtnum;
//	/**dttime*/
//	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "dttime")
//	private Date dttime;
//	/**legalReprIdentExpDate*/
//	@Excel(name = "legalReprIdentExpDate", width = 15)
//    @ApiModelProperty(value = "legalReprIdentExpDate")
//	private String legalReprIdentExpDate;
}
