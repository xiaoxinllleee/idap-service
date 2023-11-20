package org.cmms.modules.khgl.jhsh.entity;

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
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.enums.DesensitizeRuleEnums;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 聚合商户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
@Data
@TableName("TGACS_TPS_MCHNT_INFO")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TGACS_TPS_MCHNT_INFO对象", description="聚合商户信息")
public class TgacsTpsMchntInfo {
    
	/**聚合机构号*/
	@Excel(name = "聚合机构号", width = 15)
    @ApiModelProperty(value = "聚合机构号")
	private String tpsOrgId;
	/**所属成员行行号*/
	@Excel(name = "所属成员行行号", width = 15)
    @ApiModelProperty(value = "所属成员行行号")
	private String bnkCd;
	/**机构编码*/
	@Excel(name = "机构编码", width = 15)
    @ApiModelProperty(value = "机构编码")
	private String orgNb;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String orgNm;
	/**聚合商户编号*/
	@Excel(name = "聚合商户编号", width = 15)
    @ApiModelProperty(value = "聚合商户编号")
	@Desensitize(rule = DesensitizeRuleEnums.USER_ID)
	private String mchntId;
	/**农信通商户编号*/
	@Excel(name = "农信通商户编号", width = 15)
    @ApiModelProperty(value = "农信通商户编号")
	@Desensitize(rule = DesensitizeRuleEnums.USER_ID)
	private String merId;
	/**商户类型*/
	@Excel(name = "商户类型", width = 15)
    @ApiModelProperty(value = "商户类型")
	private Integer mchntType;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private String mchntName;
	/**商户简称*/
	@Excel(name = "商户简称", width = 15)
    @ApiModelProperty(value = "商户简称")
	//@Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
	private String simpMchntName;
	/**法人姓名*/
	@Excel(name = "法人姓名", width = 15)
    @ApiModelProperty(value = "法人姓名")
	@Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
	private String legalPerson;
	/**法人身份证号*/
	@Excel(name = "法人身份证号", width = 15)
    @ApiModelProperty(value = "法人身份证号")
	@Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
	private String legalPersonId;
	/**营业执照编号*/
	@Excel(name = "营业执照编号", width = 15)
    @ApiModelProperty(value = "营业执照编号")
	@Desensitize(rule = DesensitizeRuleEnums.BANK_CARD)
	private String license;
	/**商户注册资金*/
	@Excel(name = "商户注册资金", width = 15)
    @ApiModelProperty(value = "商户注册资金")
	private String registeredCapital;
	/**营业期限起始日期*/
	@Excel(name = "营业期限起始日期", width = 15)
    @ApiModelProperty(value = "营业期限起始日期")
	private String registDt;
	/**营业期限截至日期*/
	@Excel(name = "营业期限截至日期", width = 15)
    @ApiModelProperty(value = "营业期限截至日期")
	private String expireDt;
	/**经营类目*/
	@Excel(name = "经营类目", width = 15)
    @ApiModelProperty(value = "经营类目")
	private String wechatId;
	/**商户注册地址*/
	@Excel(name = "商户注册地址", width = 15)
    @ApiModelProperty(value = "商户注册地址")
	private String registAddress;
	/**营业地址省代码*/
	@Excel(name = "营业地址省代码", width = 15)
    @ApiModelProperty(value = "营业地址省代码")
	private String provCd;
	/**营业地址市代码*/
	@Excel(name = "营业地址市代码", width = 15)
    @ApiModelProperty(value = "营业地址市代码")
	private String cityCd;
	/**营业地址区县代码*/
	@Excel(name = "营业地址区县代码", width = 15)
    @ApiModelProperty(value = "营业地址区县代码")
	private String countyCd;
	/**营业街道详细地址*/
	@Excel(name = "营业街道详细地址", width = 15)
    @ApiModelProperty(value = "营业街道详细地址")
	private String busiAddress;
	/**身份证正面图片*/
	@Excel(name = "身份证正面图片", width = 15)
    @ApiModelProperty(value = "身份证正面图片")
	private String certCorrect;
	/**身份证背面图片*/
	@Excel(name = "身份证背面图片", width = 15)
    @ApiModelProperty(value = "身份证背面图片")
	private String certOpposite;
	/**手持身份证图片或营业执照*/
	@Excel(name = "手持身份证图片或营业执照", width = 15)
    @ApiModelProperty(value = "手持身份证图片或营业执照")
	private String certMeet;
	/**银行卡正面图片或开户许可证*/
	@Excel(name = "银行卡正面图片或开户许可证", width = 15)
    @ApiModelProperty(value = "银行卡正面图片或开户许可证")
	private String cardCorrect;
	/**银行卡背面图片*/
	@Excel(name = "银行卡背面图片", width = 15)
    @ApiModelProperty(value = "银行卡背面图片")
	private String cardOpposite;
	/**商户状态*/
	@Excel(name = "商户状态", width = 15)
    @ApiModelProperty(value = "商户状态")
	private Integer mchntSt;
	/**账户所属者ID*/
	@Excel(name = "账户所属者ID", width = 15)
    @ApiModelProperty(value = "账户所属者ID")
	private String ownerId;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15)
    @ApiModelProperty(value = "创建时间")
	private String rowCrtTs;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String rowCrtUsr;
	/**最后修改时间*/
	@Excel(name = "最后修改时间", width = 15)
    @ApiModelProperty(value = "最后修改时间")
	private String rowUpdTs;
	/**最后更新人*/
	@Excel(name = "最后更新人", width = 15)
    @ApiModelProperty(value = "最后更新人")
	private String rowUpdUsr;
	/**注销日期*/
	@Excel(name = "注销日期", width = 15)
    @ApiModelProperty(value = "注销日期")
	private String creDate;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private Integer transSt;
	/**客户经理姓名*/
	@Excel(name = "客户经理姓名", width = 15)
    @ApiModelProperty(value = "客户经理姓名")
	private String mcc;
	/**商户性质*/
	@Excel(name = "商户性质", width = 15)
    @ApiModelProperty(value = "商户性质")
	private String nature;
	/**商户联系人*/
	@Excel(name = "商户联系人", width = 15)
    @ApiModelProperty(value = "商户联系人")
	private String contacts;
	/**商户联系电话*/
	@Excel(name = "商户联系电话", width = 15)
    @ApiModelProperty(value = "商户联系电话")
	@TableField("CONTPHONE")
	@Desensitize(rule = DesensitizeRuleEnums.MOBILE_PHONE)
	private String contPhone;
	/**商户联系电话*/
	@Excel(name = "商户联系电话", width = 15)
    @ApiModelProperty(value = "商户联系电话")
	@TableField("CONT_PHONE")
	private String contPhone2;
	/**商户联系地址*/
	@Excel(name = "商户联系地址", width = 15)
    @ApiModelProperty(value = "商户联系地址")
	private String contaddr;
	/**商户同步文件名*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "商户同步文件名")
	private String id;
	/**农信银审核结果*/
	@Excel(name = "农信银审核结果", width = 15)
    @ApiModelProperty(value = "农信银审核结果")
	private String authRes;
	/**商户同步状态*/
	@Excel(name = "商户同步状态", width = 15)
    @ApiModelProperty(value = "商户同步状态")
	private Integer syncState;
	/**同步次数*/
	@Excel(name = "同步次数", width = 15)
    @ApiModelProperty(value = "同步次数")
	private Integer syncCount;
	/**同步时间*/
	@Excel(name = "同步时间", width = 15)
    @ApiModelProperty(value = "同步时间")
	private String syncTime;
	/**记录同步文件名*/
	@Excel(name = "记录同步文件名", width = 15)
    @ApiModelProperty(value = "记录同步文件名")
	private String fileName;
	/**审核结果*/
	@Excel(name = "审核结果", width = 15)
    @ApiModelProperty(value = "审核结果")
	private String refuseRes;
	/**机构路径*/
	@Excel(name = "机构路径", width = 15)
    @ApiModelProperty(value = "机构路径")
	private String orgPath;
	/**渠道标识*/
	@Excel(name = "渠道标识", width = 15)
    @ApiModelProperty(value = "渠道标识")
	private Integer channelFlag;
	/**外部商户号*/
	@Excel(name = "外部商户号", width = 15)
    @ApiModelProperty(value = "外部商户号")
	private String outMchntId;
	/**推送渠道*/
	@Excel(name = "推送渠道", width = 15)
    @ApiModelProperty(value = "推送渠道")
	private Integer notifyChannel;
	/**结算卡类型*/
	@Excel(name = "结算卡类型", width = 15)
    @ApiModelProperty(value = "结算卡类型")
	private Integer cardType;
	/**账户户名*/
	@Excel(name = "账户户名", width = 15)
    @ApiModelProperty(value = "账户户名")
	private String realName;
	/**开户证件类型*/
	@Excel(name = "开户证件类型", width = 15)
    @ApiModelProperty(value = "开户证件类型")
	private String certType;
	/**开户证件号*/
	@Excel(name = "开户证件号", width = 15)
    @ApiModelProperty(value = "开户证件号")
	private String certNo;
	/**结算卡号*/
	@Excel(name = "结算卡号", width = 15)
    @ApiModelProperty(value = "结算卡号")
	private String cardNo;
	/**开户省代码*/
	@Excel(name = "开户省代码", width = 15)
    @ApiModelProperty(value = "开户省代码")
	private String provCd1;
	/**开户城市代码*/
	@Excel(name = "开户城市代码", width = 15)
    @ApiModelProperty(value = "开户城市代码")
	private String cityCd1;
	/**行别代码*/
	@Excel(name = "行别代码", width = 15)
    @ApiModelProperty(value = "行别代码")
	private String bankCd;
	/**支行号*/
	@Excel(name = "支行号", width = 15)
    @ApiModelProperty(value = "支行号")
	private String pmsBankNo;
	/**预留手机号*/
	@Excel(name = "预留手机号", width = 15)
    @ApiModelProperty(value = "预留手机号")
	private String mobile;
	/**卡折标志*/
	@Excel(name = "卡折标志", width = 15)
    @ApiModelProperty(value = "卡折标志")
	private String cardNature;
	/**微信结算周期*/
	@Excel(name = "微信结算周期", width = 15)
    @ApiModelProperty(value = "微信结算周期")
	private Integer wxStlCycle;
	/**支付宝结算周期*/
	@Excel(name = "支付宝结算周期", width = 15)
    @ApiModelProperty(value = "支付宝结算周期")
	private Integer alStlCycle;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15)
    @ApiModelProperty(value = "开始日期")
	private String sDate;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人编号*/
	@Excel(name = "法人编号", width = 15)
    @ApiModelProperty(value = "法人编号")
	private String legalNo;

	@TableField(exist = false)
	private String sfxj = "2";
}
