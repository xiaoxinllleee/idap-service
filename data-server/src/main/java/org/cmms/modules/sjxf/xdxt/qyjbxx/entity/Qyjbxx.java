package org.cmms.modules.sjxf.xdxt.qyjbxx.entity;

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
 * @Description: 企业基本信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_ent_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_ent_info对象", description="企业基本信息")
public class Qyjbxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**是否与政府合作*/
	@Excel(name = "是否与政府合作", width = 15)
    @ApiModelProperty(value = "是否与政府合作")
	private String budgetType;
	/**主管部门*/
	@Excel(name = "主管部门", width = 15)
    @ApiModelProperty(value = "主管部门")
	private String changeDepartment;
	/**法人ID*/
	@Excel(name = "法人ID", width = 15)
    @ApiModelProperty(value = "法人ID")
	private String corpId;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**担保金额销售额*/
	@Excel(name = "担保金额销售额", width = 15)
    @ApiModelProperty(value = "担保金额销售额")
	private java.math.BigDecimal dbValue;
	/**描述1*/
	@Excel(name = "描述1", width = 15)
    @ApiModelProperty(value = "描述1")
	private String describe1;
	/**描述2*/
	@Excel(name = "描述2", width = 15)
    @ApiModelProperty(value = "描述2")
	private String describe2;
	/**描述3(担保机构经营许可证号)*/
	@Excel(name = "描述3(担保机构经营许可证号)", width = 15)
    @ApiModelProperty(value = "描述3(担保机构经营许可证号)")
	private String describe3;
	/**财务部联系方式*/
	@Excel(name = "财务部联系方式", width = 15)
    @ApiModelProperty(value = "财务部联系方式")
	private String describe4;
	/**企业所在地*/
	@Excel(name = "企业所在地", width = 15)
    @ApiModelProperty(value = "企业所在地")
	private String districtBelong;
	/**经济性质*/
	@Excel(name = "经济性质", width = 15)
    @ApiModelProperty(value = "经济性质")
	private String economyType;
	/**公司邮箱*/
	@Excel(name = "公司邮箱", width = 15)
    @ApiModelProperty(value = "公司邮箱")
	private String emailAddr;
	/**职工人数*/
	@Excel(name = "职工人数", width = 15)
    @ApiModelProperty(value = "职工人数")
	private String employeeNumber;
	/**主键流水号*/
	@Excel(name = "主键流水号", width = 15)
    @ApiModelProperty(value = "主键流水号")
	private String entInfoId;
	/**是否集团标志*/
	@Excel(name = "是否集团标志", width = 15)
    @ApiModelProperty(value = "是否集团标志")
	private String enterpriseBelong;
	/**财务主管*/
	@Excel(name = "财务主管", width = 15)
    @ApiModelProperty(value = "财务主管")
	private String financManger;
	/**报表类型*/
	@Excel(name = "报表类型", width = 15)
    @ApiModelProperty(value = "报表类型")
	private String financeBelong;
	/**报表时间间隔*/
	@Excel(name = "报表时间间隔", width = 15)
    @ApiModelProperty(value = "报表时间间隔")
	private String financeUpType;
	/**进出口资格*/
	@Excel(name = "进出口资格", width = 15)
    @ApiModelProperty(value = "进出口资格")
	private String hasIeRight;
	/**所属行业*/
	@Excel(name = "所属行业", width = 15)
    @ApiModelProperty(value = "所属行业")
	private String industryType;
	/**国标行业1*/
	@Excel(name = "国标行业1", width = 15)
    @ApiModelProperty(value = "国标行业1")
	private String industryType1;
	/**国标行业2*/
	@Excel(name = "国标行业2", width = 15)
    @ApiModelProperty(value = "国标行业2")
	private String industryType2;
	/**国标行业3*/
	@Excel(name = "国标行业3", width = 15)
    @ApiModelProperty(value = "国标行业3")
	private String industryType3;
	/**是否授权*/
	@Excel(name = "是否授权", width = 15)
    @ApiModelProperty(value = "是否授权")
	private String isAccredit;
	/**基本帐户开户行*/
	@Excel(name = "基本帐户开户行", width = 15)
    @ApiModelProperty(value = "基本帐户开户行")
	private String isBaseAccount;
	/**是否法人资格*/
	@Excel(name = "是否法人资格", width = 15)
    @ApiModelProperty(value = "是否法人资格")
	private String isCorp;
	/**是否担保企业*/
	@Excel(name = "是否担保企业", width = 15)
    @ApiModelProperty(value = "是否担保企业")
	private String isDb;
	/**董事会*/
	@Excel(name = "董事会", width = 15)
    @ApiModelProperty(value = "董事会")
	private String isDirectorate;
	/**是否我行股东*/
	@Excel(name = "是否我行股东", width = 15)
    @ApiModelProperty(value = "是否我行股东")
	private String isSh;
	/**法人担任职务*/
	@Excel(name = "法人担任职务", width = 15)
    @ApiModelProperty(value = "法人担任职务")
	private String juriDuty;
	/**法人是否我行高管*/
	@Excel(name = "法人是否我行高管", width = 15)
    @ApiModelProperty(value = "法人是否我行高管")
	private String juriIsHighManager;
	/**法人联系电话*/
	@Excel(name = "法人联系电话", width = 15)
    @ApiModelProperty(value = "法人联系电话")
	private String juriPhone;
	/**法律责任形式*/
	@Excel(name = "法律责任形式", width = 15)
    @ApiModelProperty(value = "法律责任形式")
	private String legalDutyType;
	/**是否上市公司*/
	@Excel(name = "是否上市公司", width = 15)
    @ApiModelProperty(value = "是否上市公司")
	private String listingCorpOrNot;
	/**经营范围及主要产品*/
	@Excel(name = "经营范围及主要产品", width = 15)
    @ApiModelProperty(value = "经营范围及主要产品")
	private String mainProduction;
	/**市场前景*/
	@Excel(name = "市场前景", width = 15)
    @ApiModelProperty(value = "市场前景")
	private String marketForeground;
	/**高新技术企业*/
	@Excel(name = "高新技术企业", width = 15)
    @ApiModelProperty(value = "高新技术企业")
	private String newTechCorpOrNot;
	/**办公地址*/
	@Excel(name = "办公地址", width = 15)
    @ApiModelProperty(value = "办公地址")
	private String officeAddr;
	/**传真号码*/
	@Excel(name = "传真号码", width = 15)
    @ApiModelProperty(value = "传真号码")
	private String officeTax;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String officeTel;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
    @ApiModelProperty(value = "邮政编码")
	private String officeZip;
	/**开户许可证号*/
	@Excel(name = "开户许可证号", width = 15)
    @ApiModelProperty(value = "开户许可证号")
	private String openLicense;
	/**组织形式*/
	@Excel(name = "组织形式", width = 15)
    @ApiModelProperty(value = "组织形式")
	private String orgType;
	/**实收资本*/
	@Excel(name = "实收资本", width = 15)
    @ApiModelProperty(value = "实收资本")
	private java.math.BigDecimal paicalUpCaptial;
	/**实收资本币种*/
	@Excel(name = "实收资本币种", width = 15)
    @ApiModelProperty(value = "实收资本币种")
	private String pcCurrency;
	/**注册资本币种*/
	@Excel(name = "注册资本币种", width = 15)
    @ApiModelProperty(value = "注册资本币种")
	private String rcCurrency;
	/**所在的行政区域代码*/
	@Excel(name = "所在的行政区域代码", width = 15)
    @ApiModelProperty(value = "所在的行政区域代码")
	private String regionCode;
	/**注册地址*/
	@Excel(name = "注册地址", width = 15)
    @ApiModelProperty(value = "注册地址")
	private String registerAddr;
	/**注册资本*/
	@Excel(name = "注册资本", width = 15)
    @ApiModelProperty(value = "注册资本")
	private java.math.BigDecimal registerCaptial;
	/**离退休人数*/
	@Excel(name = "离退休人数", width = 15)
    @ApiModelProperty(value = "离退休人数")
	private String retireeNumber;
	/**主导产品*/
	@Excel(name = "主导产品", width = 15)
    @ApiModelProperty(value = "主导产品")
	private String saleMainProduct;
	/**销售情况*/
	@Excel(name = "销售情况", width = 15)
    @ApiModelProperty(value = "销售情况")
	private String saleThing;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15)
    @ApiModelProperty(value = "企业规模")
	private String scopy;
	/**省联社所有制/商行性质分类*/
	@Excel(name = "省联社所有制/商行性质分类", width = 15)
    @ApiModelProperty(value = "省联社所有制/商行性质分类")
	private String slxHavSystem;
	/**省联社标准/商行行业分类*/
	@Excel(name = "省联社标准/商行行业分类", width = 15)
    @ApiModelProperty(value = "省联社标准/商行行业分类")
	private String slxStandard;
	/**技术人员人数*/
	@Excel(name = "技术人员人数", width = 15)
    @ApiModelProperty(value = "技术人员人数")
	private String technicianNumber;
	/**公司网站*/
	@Excel(name = "公司网站", width = 15)
    @ApiModelProperty(value = "公司网站")
	private String webAddr;
	/**资产总额*/
	@Excel(name = "资产总额", width = 15)
    @ApiModelProperty(value = "资产总额")
	private java.math.BigDecimal zcValue;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String inputDate;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**财务管理人员ID*/
	@Excel(name = "财务管理人员ID", width = 15)
    @ApiModelProperty(value = "财务管理人员ID")
	private String financMangerId;
	/**基本帐户开户行*/
	@Excel(name = "基本帐户开户行", width = 15)
    @ApiModelProperty(value = "基本帐户开户行")
	private String baseAccountBank;
	/**是否市辖区*/
	@Excel(name = "是否市辖区", width = 15)
    @ApiModelProperty(value = "是否市辖区")
	private String isCantonal;
	/**是否龙头企业*/
	@Excel(name = "是否龙头企业", width = 15)
    @ApiModelProperty(value = "是否龙头企业")
	private String isLongtou;
	/**龙头企业级别*/
	@Excel(name = "龙头企业级别", width = 15)
    @ApiModelProperty(value = "龙头企业级别")
	private String longtouJibie;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	private String deptId;
	/**操作员号*/
	@Excel(name = "操作员号", width = 15)
    @ApiModelProperty(value = "操作员号")
	private String userId;
	/**行业规模1*/
	@Excel(name = "行业规模1", width = 15)
    @ApiModelProperty(value = "行业规模1")
	private String scopy1;
	/**是否发送短信*/
	@Excel(name = "是否发送短信", width = 15)
    @ApiModelProperty(value = "是否发送短信")
	private String isSendMess;
	/**是否劳动密集性*/
	@Excel(name = "是否劳动密集性", width = 15)
    @ApiModelProperty(value = "是否劳动密集性")
	private String isWorkDense;
	/**公司类客户级别*/
	@Excel(name = "公司类客户级别", width = 15)
    @ApiModelProperty(value = "公司类客户级别")
	private String entCustClass;
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
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
