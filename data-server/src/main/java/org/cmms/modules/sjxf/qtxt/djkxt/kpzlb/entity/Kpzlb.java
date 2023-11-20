package org.cmms.modules.sjxf.qtxt.djkxt.kpzlb.entity;

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
 * @Description: 卡片资料表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ccd_card")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ccd_card对象", description="卡片资料表")
public class Kpzlb {
    
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String cardNbr;
	/**产品编号*/
	@Excel(name = "产品编号", width = 15)
    @ApiModelProperty(value = "产品编号")
	private String product;
	/**卡片状态代码*/
	@Excel(name = "卡片状态代码", width = 15)
    @ApiModelProperty(value = "卡片状态代码")
	private String canclCode;
	/**卡片状态日期*/
	@Excel(name = "卡片状态日期", width = 15)
    @ApiModelProperty(value = "卡片状态日期")
	private String canclDay;
	/**卡片挂失员工*/
	@Excel(name = "卡片挂失员工", width = 15)
    @ApiModelProperty(value = "卡片挂失员工")
	private String canclEmpl;
	/**卡片挂失时间*/
	@Excel(name = "卡片挂失时间", width = 15)
    @ApiModelProperty(value = "卡片挂失时间")
	private String canclTime;
	/**外卡黑名单动作代码*/
	@Excel(name = "外卡黑名单动作代码", width = 15)
    @ApiModelProperty(value = "外卡黑名单动作代码")
	private String actioncode;
	/**产生日期*/
	@Excel(name = "产生日期", width = 15)
    @ApiModelProperty(value = "产生日期")
	private String createDay;
	/**每日外币预借现金笔数*/
	@Excel(name = "每日外币预借现金笔数", width = 15)
    @ApiModelProperty(value = "每日外币预借现金笔数")
	private String cashadNox;
	/**每日本币预借现金笔数*/
	@Excel(name = "每日本币预借现金笔数", width = 15)
    @ApiModelProperty(value = "每日本币预借现金笔数")
	private String cashadvNo;
	/**递送方式*/
	@Excel(name = "递送方式", width = 15)
    @ApiModelProperty(value = "递送方式")
	private String despatch;
	/**第二道凸字(企业卡的公司名称)*/
	@Excel(name = "第二道凸字(企业卡的公司名称)", width = 15)
    @ApiModelProperty(value = "第二道凸字(企业卡的公司名称)")
	private String embossLn2;
	/**凸字名称*/
	@Excel(name = "凸字名称", width = 15)
    @ApiModelProperty(value = "凸字名称")
	private String embossNme;
	/**到期日(YYMM)*/
	@Excel(name = "到期日(YYMM)", width = 15)
    @ApiModelProperty(value = "到期日(YYMM)")
	private String expiryDte;
	/**重发卡的新到期日(YYMM)*/
	@Excel(name = "重发卡的新到期日(YYMM)", width = 15)
    @ApiModelProperty(value = "重发卡的新到期日(YYMM)")
	private String expiryNew;
	/**卡片费用代码*/
	@Excel(name = "卡片费用代码", width = 15)
    @ApiModelProperty(value = "卡片费用代码")
	private String feeCode;
	/**发卡日期*/
	@Excel(name = "发卡日期", width = 15)
    @ApiModelProperty(value = "发卡日期")
	private String issueDay;
	/**发卡原因代码*/
	@Excel(name = "发卡原因代码", width = 15)
    @ApiModelProperty(value = "发卡原因代码")
	private String issueReas;
	/**卡片遗失日期*/
	@Excel(name = "卡片遗失日期", width = 15)
    @ApiModelProperty(value = "卡片遗失日期")
	private String lossDay;
	/**挂失方式*/
	@Excel(name = "挂失方式", width = 15)
    @ApiModelProperty(value = "挂失方式")
	private String lossReprt;
	/**卡片遗失时间*/
	@Excel(name = "卡片遗失时间", width = 15)
    @ApiModelProperty(value = "卡片遗失时间")
	private String lossTime;
	/**允许每日外币存款最大金额*/
	@Excel(name = "允许每日外币存款最大金额", width = 15)
    @ApiModelProperty(value = "允许每日外币存款最大金额")
	private java.math.BigDecimal depamTdx;
	/**允许每日本币存款最大金额*/
	@Excel(name = "允许每日本币存款最大金额", width = 15)
    @ApiModelProperty(value = "允许每日本币存款最大金额")
	private java.math.BigDecimal depamTdy;
	/**允许每日外币存款最多次数(作废)*/
	@Excel(name = "允许每日外币存款最多次数(作废)", width = 15)
    @ApiModelProperty(value = "允许每日外币存款最多次数(作废)")
	private String depnoTdx;
	/**允许每日本币存款最多次数(作废)*/
	@Excel(name = "允许每日本币存款最多次数(作废)", width = 15)
    @ApiModelProperty(value = "允许每日本币存款最多次数(作废)")
	private String depnoTdy;
	/**允许每日存款最多次数(作废)*/
	@Excel(name = "允许每日存款最多次数(作废)", width = 15)
    @ApiModelProperty(value = "允许每日存款最多次数(作废)")
	private String depositNo;
	/**允许本币预借现金最多次数*/
	@Excel(name = "允许本币预借现金最多次数", width = 15)
    @ApiModelProperty(value = "允许本币预借现金最多次数")
	private String hrcashNo;
	/**允许外币预借现金最多次数*/
	@Excel(name = "允许外币预借现金最多次数", width = 15)
    @ApiModelProperty(value = "允许外币预借现金最多次数")
	private String hrcashNox;
	/**允许每日外币缴款最大金额*/
	@Excel(name = "允许每日外币缴款最大金额", width = 15)
    @ApiModelProperty(value = "允许每日外币缴款最大金额")
	private String maxPamtx;
	/**允许使用PIN错误最多次数*/
	@Excel(name = "允许使用PIN错误最多次数", width = 15)
    @ApiModelProperty(value = "允许使用PIN错误最多次数")
	private String maxPintry;
	/**允许每日PIN错误次数*/
	@Excel(name = "允许每日PIN错误次数", width = 15)
    @ApiModelProperty(value = "允许每日PIN错误次数")
	private String pinFaildl;
	/**允许PIN错误次数*/
	@Excel(name = "允许PIN错误次数", width = 15)
    @ApiModelProperty(value = "允许PIN错误次数")
	private String pinFails;
	/**允许每日外币购货总额*/
	@Excel(name = "允许每日外币购货总额", width = 15)
    @ApiModelProperty(value = "允许每日外币购货总额")
	private java.math.BigDecimal purcamTdx;
	/**允许每日本币购货总额*/
	@Excel(name = "允许每日本币购货总额", width = 15)
    @ApiModelProperty(value = "允许每日本币购货总额")
	private java.math.BigDecimal purcamTdy;
	/**允许今日外币购货次数*/
	@Excel(name = "允许今日外币购货次数", width = 15)
    @ApiModelProperty(value = "允许今日外币购货次数")
	private String purchsNox;
	/**允许今日本币购货次数*/
	@Excel(name = "允许今日本币购货次数", width = 15)
    @ApiModelProperty(value = "允许今日本币购货次数")
	private String purchseNo;
	/**重发卡日期*/
	@Excel(name = "重发卡日期", width = 15)
    @ApiModelProperty(value = "重发卡日期")
	private String reissDte;
	/**紧急替代卡标志*/
	@Excel(name = "紧急替代卡标志", width = 15)
    @ApiModelProperty(value = "紧急替代卡标志")
	private String urgentfee;
	/**有效期的起始日期*/
	@Excel(name = "有效期的起始日期", width = 15)
    @ApiModelProperty(value = "有效期的起始日期")
	private String validFrom;
	/**重发卡有效期的起始日期*/
	@Excel(name = "重发卡有效期的起始日期", width = 15)
    @ApiModelProperty(value = "重发卡有效期的起始日期")
	private String validNew;
	/**短信通知标志*/
	@Excel(name = "短信通知标志", width = 15)
    @ApiModelProperty(value = "短信通知标志")
	private String smsYn;
	/**消费使用密码标志*/
	@Excel(name = "消费使用密码标志", width = 15)
    @ApiModelProperty(value = "消费使用密码标志")
	private String pinChk;
	/**卡片版面*/
	@Excel(name = "卡片版面", width = 15)
    @ApiModelProperty(value = "卡片版面")
	private String cdfrm;
	/**附卡单独设置额度比例*/
	@Excel(name = "附卡单独设置额度比例", width = 15)
    @ApiModelProperty(value = "附卡单独设置额度比例")
	private String limitX;
	/**附卡额度比例*/
	@Excel(name = "附卡额度比例", width = 15)
    @ApiModelProperty(value = "附卡额度比例")
	private String credLmt;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String custrNbr;
	/**入网机构代码*/
	@Excel(name = "入网机构代码", width = 15)
    @ApiModelProperty(value = "入网机构代码")
	private String areaCode;
	/**下次收年费月份*/
	@Excel(name = "下次收年费月份", width = 15)
    @ApiModelProperty(value = "下次收年费月份")
	private String feeMonth;
	/**外币网上交易开通标志*/
	@Excel(name = "外币网上交易开通标志", width = 15)
    @ApiModelProperty(value = "外币网上交易开通标志")
	private String ecYn;
	/**外币网上交易开通期限*/
	@Excel(name = "外币网上交易开通期限", width = 15)
    @ApiModelProperty(value = "外币网上交易开通期限")
	private String ecEnddt;
	/**境外交易开关*/
	@Excel(name = "境外交易开关", width = 15)
    @ApiModelProperty(value = "境外交易开关")
	private String ltnonlyYn;
	/**本币无磁交易开关*/
	@Excel(name = "本币无磁交易开关", width = 15)
    @ApiModelProperty(value = "本币无磁交易开关")
	private String track2N;
	/**卡片激活日期*/
	@Excel(name = "卡片激活日期", width = 15)
    @ApiModelProperty(value = "卡片激活日期")
	private String activeday;
	/**卡片激活渠道*/
	@Excel(name = "卡片激活渠道", width = 15)
    @ApiModelProperty(value = "卡片激活渠道")
	private String activech;
	/**卡片注销原因代码*/
	@Excel(name = "卡片注销原因代码", width = 15)
    @ApiModelProperty(value = "卡片注销原因代码")
	private String canclReas;
	/**是否允许无磁无密商户交易*/
	@Excel(name = "是否允许无磁无密商户交易", width = 15)
    @ApiModelProperty(value = "是否允许无磁无密商户交易")
	private String ntnpYn;
	/**消费密码生效金额*/
	@Excel(name = "消费密码生效金额", width = 15)
    @ApiModelProperty(value = "消费密码生效金额")
	private String pinLmt;
	/**卡片单独积分兑换年费*/
	@Excel(name = "卡片单独积分兑换年费", width = 15)
    @ApiModelProperty(value = "卡片单独积分兑换年费")
	private String cdptfeeYn;
	/**可取现功能*/
	@Excel(name = "可取现功能", width = 15)
    @ApiModelProperty(value = "可取现功能")
	private String withdrwYn;
	/**可转出功能*/
	@Excel(name = "可转出功能", width = 15)
    @ApiModelProperty(value = "可转出功能")
	private String xfrfromYn;
	/**可存款功能*/
	@Excel(name = "可存款功能", width = 15)
    @ApiModelProperty(value = "可存款功能")
	private String depositYn;
	/**可余额查询功能*/
	@Excel(name = "可余额查询功能", width = 15)
    @ApiModelProperty(value = "可余额查询功能")
	private String balinqYn;
	/**可消费功能*/
	@Excel(name = "可消费功能", width = 15)
    @ApiModelProperty(value = "可消费功能")
	private String purchYn;
	/**可找现功能*/
	@Excel(name = "可找现功能", width = 15)
    @ApiModelProperty(value = "可找现功能")
	private String cashbckYn;
	/**ATM转账、电话转账、网银转账、手机转账功能字段*/
	@Excel(name = "ATM转账、电话转账、网银转账、手机转账功能字段", width = 15)
    @ApiModelProperty(value = "ATM转账、电话转账、网银转账、手机转账功能字段")
	private String xfrchnl;
	/**续卡类型*/
	@Excel(name = "续卡类型", width = 15)
    @ApiModelProperty(value = "续卡类型")
	private String reissType;
	/**取消PRMMT账期控制*/
	@Excel(name = "取消PRMMT账期控制", width = 15)
    @ApiModelProperty(value = "取消PRMMT账期控制")
	private String clmlimYn;
	/**是否续卡标志位*/
	@Excel(name = "是否续卡标志位", width = 15)
    @ApiModelProperty(value = "是否续卡标志位")
	private String reissFlg;
	/**换卡收费标志位*/
	@Excel(name = "换卡收费标志位", width = 15)
    @ApiModelProperty(value = "换卡收费标志位")
	private String replacefee;
	/**IC卡标志位*/
	@Excel(name = "IC卡标志位", width = 15)
    @ApiModelProperty(value = "IC卡标志位")
	private String pbocYn;
	/**最后一次交易发生日期*/
	@Excel(name = "最后一次交易发生日期", width = 15)
    @ApiModelProperty(value = "最后一次交易发生日期")
	private String authPday;
	/**最后一次交易发生时间*/
	@Excel(name = "最后一次交易发生时间", width = 15)
    @ApiModelProperty(value = "最后一次交易发生时间")
	private String authPtime;
	/**硬件序列号*/
	@Excel(name = "硬件序列号", width = 15)
    @ApiModelProperty(value = "硬件序列号")
	private String hdwrSn;
	/**卡片序号*/
	@Excel(name = "卡片序号", width = 15)
    @ApiModelProperty(value = "卡片序号")
	private String issueNbr;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
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
