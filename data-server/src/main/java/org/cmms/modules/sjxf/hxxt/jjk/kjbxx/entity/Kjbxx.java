package org.cmms.modules.sjxf.hxxt.jjk.kjbxx.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 卡基本信息
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("Cbsc_card")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbsc_card对象", description="卡基本信息")
public class Kjbxx {

	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private Integer inst;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	@TableField(value = "no")
	private String no;
	/**卡产品号*/
	@Excel(name = "卡产品号", width = 15)
    @ApiModelProperty(value = "卡产品号")
	private Integer product;
	/**卡BIN代码*/
	@Excel(name = "卡BIN代码", width = 15)
    @ApiModelProperty(value = "卡BIN代码")
	private Integer bin;
	/**客户号*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String customer;
	/**卡名*/
	@Excel(name = "卡名", width = 15)
    @ApiModelProperty(value = "卡名")
	private String name1;
	/**卡名*/
	@Excel(name = "卡名", width = 15)
    @ApiModelProperty(value = "卡名")
	private String name2;
	/**地址行1*/
	@Excel(name = "地址行1", width = 15)
    @ApiModelProperty(value = "地址行1")
	private String address1;
	/**地址行2*/
	@Excel(name = "地址行2", width = 15)
    @ApiModelProperty(value = "地址行2")
	private String address2;
	/**地址行3*/
	@Excel(name = "地址行3", width = 15)
    @ApiModelProperty(value = "地址行3")
	private String address3;
	/**地址行4*/
	@Excel(name = "地址行4", width = 15)
    @ApiModelProperty(value = "地址行4")
	private String address4;
	/**邮编*/
	@Excel(name = "邮编", width = 15)
    @ApiModelProperty(value = "邮编")
	private String postcode;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String phone;
	/**专用地址标记*/
	@Excel(name = "专用地址标记", width = 15)
    @ApiModelProperty(value = "专用地址标记")
	private String hasAddress;
	/**专用地址到期日期*/
    @ApiModelProperty(value = "专用地址到期日期")
	private Date addressExpiry;
	/**卡创建日期*/
    @ApiModelProperty(value = "卡创建日期")
	private Date creationDate;
	/**卡签发日期*/
    @ApiModelProperty(value = "卡签发日期")
	private Date issueDate;
	/**卡压印浮字日期*/
    @ApiModelProperty(value = "卡压印浮字日期")
	private Date embossingDate;
	/**卡到期日*/
    @ApiModelProperty(value = "卡到期日")
	private Date expiryDate;
	/**卡重发日*/
    @ApiModelProperty(value = "卡重发日")
	private Date reissueDate;
	/**卡最后保留日期*/
    @ApiModelProperty(value = "卡最后保留日期")
	private Date lastMaintDate;
	/**卡最后支取日期*/
    @ApiModelProperty(value = "卡最后支取日期")
	private Date lastWithdrawalDate;
	/**卡最后使用日期*/
    @ApiModelProperty(value = "卡最后使用日期")
	private Date lastUseDate;
	/**最后一次卡费*/
    @ApiModelProperty(value = "最后一次卡费")
	private Date lastFeeDate;
	/**卡的使用柜台*/
	@Excel(name = "卡的使用柜台", width = 15)
    @ApiModelProperty(value = "卡的使用柜台")
	private Integer useCount;
	/**密码*/
	@Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
	private String pvv;
	/**无效的PIN的重试计数*/
	@Excel(name = "无效的PIN的重试计数", width = 15)
    @ApiModelProperty(value = "无效的PIN的重试计数")
	private Integer pinRetries;
	/**卡状况*/
	@Excel(name = "卡状况", width = 15)
    @ApiModelProperty(value = "卡状况")
	private Integer status;
	/**卡托收分行*/
	@Excel(name = "卡托收分行", width = 15)
    @ApiModelProperty(value = "卡托收分行")
	private Integer colBranch;
	/**设为hot卡的原因码*/
	@Excel(name = "设为hot卡的原因码", width = 15)
    @ApiModelProperty(value = "设为hot卡的原因码")
	private String hotReason;
	/**设为hot的机构*/
	@Excel(name = "设为hot的机构", width = 15)
    @ApiModelProperty(value = "设为hot的机构")
	private Integer hotBranch;
	/**设为warm卡的原因码*/
	@Excel(name = "设为warm卡的原因码", width = 15)
    @ApiModelProperty(value = "设为warm卡的原因码")
	private String warmReason;
	/**设为warm卡的机构*/
	@Excel(name = "设为warm卡的机构", width = 15)
    @ApiModelProperty(value = "设为warm卡的机构")
	private Integer warmBranch;
	/**标志*/
	@Excel(name = "标志", width = 15)
    @ApiModelProperty(value = "标志")
	private String remark;
	/**记名票据*/
	@Excel(name = "记名票据", width = 15)
    @ApiModelProperty(value = "记名票据")
	private String note;
	/**记名票据日期*/
    @ApiModelProperty(value = "记名票据日期")
	private Date noteDate;
	/**便民卡放款密码*/
	@Excel(name = "便民卡放款密码", width = 15)
    @ApiModelProperty(value = "便民卡放款密码")
	private String password;
	/**卡签发次数*/
	@Excel(name = "卡签发次数", width = 15)
    @ApiModelProperty(value = "卡签发次数")
	private Integer issueCount;
	/**发卡机构*/
	@Excel(name = "发卡机构", width = 15)
    @ApiModelProperty(value = "发卡机构")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private Integer issueBranch;
	/**发卡所在分行*/
	@Excel(name = "发卡所在分行", width = 15)
    @ApiModelProperty(value = "发卡所在分行")
	private Integer issueRegion;
	/**省份机构*/
	@Excel(name = "省份机构", width = 15)
    @ApiModelProperty(value = "省份机构")
	private Integer provincialBranch;
	/**warm卡的次数计数*/
	@Excel(name = "warm卡的次数计数", width = 15)
    @ApiModelProperty(value = "warm卡的次数计数")
	private Integer warmCount;
	/**卡是否已经自动warm*/
	@Excel(name = "卡是否已经自动warm", width = 15)
    @ApiModelProperty(value = "卡是否已经自动warm")
	private String autowarmed;
	/**pcv码*/
	@Excel(name = "pcv码", width = 15)
    @ApiModelProperty(value = "pcv码")
	private String pcv;
	/**总行*/
	@Excel(name = "总行", width = 15)
    @ApiModelProperty(value = "总行")
	private String parent;
	/**Pvk类型*/
	@Excel(name = "Pvk类型", width = 15)
    @ApiModelProperty(value = "Pvk类型")
	private String pvkType;
	/**口挂日期*/
    @ApiModelProperty(value = "口挂日期")
	private Date informalLostDate;
	/**书挂日期*/
    @ApiModelProperty(value = "书挂日期")
	private Date formalLostDate;
	/**密码挂失日期*/
    @ApiModelProperty(value = "密码挂失日期")
	private Date pinLostDate;
	/**密码挂失重置日期*/
    @ApiModelProperty(value = "密码挂失重置日期")
	private Date pinLostReissueDate;
	/**锁卡日期*/
    @ApiModelProperty(value = "锁卡日期")
	private Date lockedDate;
	/**卡解锁日期*/
    @ApiModelProperty(value = "卡解锁日期")
	private Date hotDate;
	/**Visa卡重试计数*/
	@Excel(name = "Visa卡重试计数", width = 15)
    @ApiModelProperty(value = "Visa卡重试计数")
	private Integer cvvRetries;
	/**短信签约标志*/
	@Excel(name = "短信签约标志", width = 15)
    @ApiModelProperty(value = "短信签约标志")
	private String sms;
	/**发卡索引*/
	@Excel(name = "发卡索引", width = 15)
    @ApiModelProperty(value = "发卡索引")
	private String issueIndex;
	/**下期年费日期*/
    @ApiModelProperty(value = "下期年费日期")
	private Date annualFeeNext;
	/**年费日期*/
    @ApiModelProperty(value = "年费日期")
	private Date annualFeeDate;
	/**年费百分值*/
	@Excel(name = "年费百分值", width = 15)
    @ApiModelProperty(value = "年费百分值")
	private Integer annualFeePercent;
	/**年费周期*/
	@Excel(name = "年费周期", width = 15)
    @ApiModelProperty(value = "年费周期")
	private Integer annualFreePeriod;
	/**剩余年费*/
	@Excel(name = "剩余年费", width = 15)
    @ApiModelProperty(value = "剩余年费")
	private java.math.BigDecimal annualFeeResidual;
	/**列入黑名单日期*/
    @ApiModelProperty(value = "列入黑名单日期")
	private Date blacklistDate;
	/**列入黑名单原因*/
	@Excel(name = "列入黑名单原因", width = 15)
    @ApiModelProperty(value = "列入黑名单原因")
	private String blacklistReason;
	/**列入黑名单机构*/
	@Excel(name = "列入黑名单机构", width = 15)
    @ApiModelProperty(value = "列入黑名单机构")
	private Integer blacklistBranch;
	/**主帐号更新*/
	@Excel(name = "主帐号更新", width = 15)
    @ApiModelProperty(value = "主帐号更新")
	private String panReplaced;
	/**有效值*/
	@Excel(name = "有效值", width = 15)
    @ApiModelProperty(value = "有效值")
	private java.math.BigDecimal availablePoints;
	/**历史可用积分*/
	@Excel(name = "历史可用积分", width = 15)
    @ApiModelProperty(value = "历史可用积分")
	private java.math.BigDecimal hisAvailablePoints;
	/**使用值*/
	@Excel(name = "使用值", width = 15)
    @ApiModelProperty(value = "使用值")
	private java.math.BigDecimal usedPoints;
	/**总值*/
	@Excel(name = "总值", width = 15)
    @ApiModelProperty(value = "总值")
	private java.math.BigDecimal totalPoints;
	/**Pin初始化标志*/
	@Excel(name = "Pin初始化标志", width = 15)
    @ApiModelProperty(value = "Pin初始化标志")
	private String pinForceInit;
	/**专用visa标志*/
	@Excel(name = "专用visa标志", width = 15)
    @ApiModelProperty(value = "专用visa标志")
	private String hasCvv;
	/**员工标识*/
	@Excel(name = "员工标识", width = 15)
    @ApiModelProperty(value = "员工标识")
	private String isStaff;
	/**城市银行代码*/
	@Excel(name = "城市银行代码", width = 15)
    @ApiModelProperty(value = "城市银行代码")
	private Integer pbocCity;
	/**省份隐含代码*/
	@Excel(name = "省份隐含代码", width = 15)
    @ApiModelProperty(value = "省份隐含代码")
	private Integer pbocProvince;
	/**个人标志*/
	@Excel(name = "个人标志", width = 15)
    @ApiModelProperty(value = "个人标志")
	private String personalised;
	/**CVN*/
	@Excel(name = "CVN", width = 15)
    @ApiModelProperty(value = "CVN")
	private String cvn;
	/**客户服务ID*/
	@Excel(name = "客户服务ID", width = 15)
    @ApiModelProperty(value = "客户服务ID")
	private String customer2;
	/**年费周期内卡消费次数累积*/
	@Excel(name = "年费周期内卡消费次数累积", width = 15)
    @ApiModelProperty(value = "年费周期内卡消费次数累积")
	private Integer poscount;
	/**最近重置POSCOUNT字段日期*/
    @ApiModelProperty(value = "最近重置POSCOUNT字段日期")
	private Date resetdate;
	/**ATM行内转账交易开通标识*/
	@Excel(name = "ATM行内转账交易开通标识", width = 15)
    @ApiModelProperty(value = "ATM行内转账交易开通标识")
	private String atmTfxIntFlag;
	/**ATM跨行转账交易开通标识*/
	@Excel(name = "ATM跨行转账交易开通标识", width = 15)
    @ApiModelProperty(value = "ATM跨行转账交易开通标识")
	private String atmTfxExtFlag;
	/**银联开通日期*/
    @ApiModelProperty(value = "银联开通日期")
	private Date cuppaySignDate;
	/**银联关闭日期*/
    @ApiModelProperty(value = "银联关闭日期")
	private Date cuppayCloseDate;
	/**银联开通标识*/
	@Excel(name = "银联开通标识", width = 15)
    @ApiModelProperty(value = "银联开通标识")
	private String cuppaySignFlag;
	/**银联重试次数*/
	@Excel(name = "银联重试次数", width = 15)
    @ApiModelProperty(value = "银联重试次数")
	private Integer cuppayRetries;
	/**最后银联交易日期*/
    @ApiModelProperty(value = "最后银联交易日期")
	private Date lastCuppayRetryDate;
	/**最后PIN重试日期*/
    @ApiModelProperty(value = "最后PIN重试日期")
	private Date lastPinRetryDate;
	/**最后更新时间*/
    @ApiModelProperty(value = "最后更新时间")
	private Date lastMaintainDate;
	/**最后更新操作*/
	@Excel(name = "最后更新操作", width = 15)
    @ApiModelProperty(value = "最后更新操作")
	private String lastMaintainFlag;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
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
