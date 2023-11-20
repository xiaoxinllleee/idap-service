package org.cmms.modules.khgl.qtzrr.entity;

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
 * @Description: 其他自然人评级授信信息
 * @Author: jeecg-boot
 * @Date:   2021-07-26
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_QTZRRPJSXXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_QTZRRPJSXXX对象", description="其他自然人评级授信信息")
public class QtzrrPjsxxx {

	/**资产负债率*/
	@Excel(name = "资产负债率", width = 15)
	@ApiModelProperty(value = "资产负债率")
	private java.lang.String zcfzl;
	/**自有资金占比*/
	@Excel(name = "自有资金占比", width = 15)
	@ApiModelProperty(value = "自有资金占比")
	private java.lang.String zyzjzb;
	/**年纯收入*/
	@Excel(name = "年纯收入", width = 15)
	@ApiModelProperty(value = "年纯收入")
	private java.lang.String ncsr;
	/**与信用社往来时间*/
	@Excel(name = "与信用社往来时间", width = 15)
	@ApiModelProperty(value = "与信用社往来时间")
	private java.lang.String yxyswlsj;
	/**与信用社合作情况*/
	@Excel(name = "与信用社合作情况", width = 15)
	@ApiModelProperty(value = "与信用社合作情况")
	private java.lang.String yxyshzqk;
	/**人身保险*/
	@Excel(name = "人身保险", width = 15)
	@ApiModelProperty(value = "人身保险")
	private java.lang.String rsbx;
	/**财产保险*/
	@Excel(name = "财产保险", width = 15)
	@ApiModelProperty(value = "财产保险")
	private java.lang.String ccbx;
	/**总评得分小计*/
	@Excel(name = "总评得分小计", width = 15)
	@ApiModelProperty(value = "总评得分小计")
	private java.lang.String zpdfxj;
	/**系统测算评定等级*/
	@Excel(name = "系统测算评定等级", width = 15)
	@ApiModelProperty(value = "系统测算评定等级")
	private java.lang.String xtcspddj;
	/**系统测算授信额度*/
	@Excel(name = "系统测算授信额度", width = 15)
	@ApiModelProperty(value = "系统测算授信额度")
	private java.lang.String xtcssxed;
	/**调整后等级*/
	@Excel(name = "调整后等级", width = 15)
	@ApiModelProperty(value = "调整后等级")
	private java.lang.String tzhdj;
	/**等级调整事项*/
	@Excel(name = "等级调整事项", width = 15)
	@ApiModelProperty(value = "等级调整事项")
	private java.lang.String djtzsx;
	/**客户经理评定等级*/
	@Excel(name = "客户经理评定等级", width = 15)
	@ApiModelProperty(value = "客户经理评定等级")
	private java.lang.String khjlpddj;
	/**客户经理授信额度*/
	@Excel(name = "客户经理授信额度", width = 15)
	@ApiModelProperty(value = "客户经理授信额度")
	private java.lang.String khjlsxed;
	/**最终评定等级*/
	@Excel(name = "最终评定等级", width = 15)
	@ApiModelProperty(value = "最终评定等级")
	private java.lang.String zzpddj;
	/**最终授信额度*/
	@Excel(name = "最终授信额度", width = 15)
	@ApiModelProperty(value = "最终授信额度")
	private java.lang.String zzsxed;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private java.lang.String bz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	private java.lang.String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private java.util.Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private java.lang.String lrr;
	/**upDt*/
	@Excel(name = "upDt", width = 15)
	@ApiModelProperty(value = "upDt")
	private java.lang.String upDt;
	/**upTm*/
	@Excel(name = "upTm", width = 15)
	@ApiModelProperty(value = "upTm")
	private java.lang.String upTm;
	/**hmcId*/
	@Excel(name = "hmcId", width = 15)
	@ApiModelProperty(value = "hmcId")
	private java.lang.String hmcId;
	/**qydm*/
	@Excel(name = "qydm", width = 15)
	@ApiModelProperty(value = "qydm")
	private java.lang.String qydm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private java.lang.String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**不予授信情形*/
	@Excel(name = "不予授信情形", width = 15)
	@ApiModelProperty(value = "不予授信情形")
	private java.lang.String bysxqx;
	/**房地产资产合计*/
	@Excel(name = "房地产资产合计", width = 15)
	@ApiModelProperty(value = "房地产资产合计")
	private java.lang.String fczchj;
	/**地产数量*/
	@Excel(name = "地产数量", width = 15)
	@ApiModelProperty(value = "地产数量")
	private java.lang.String dcsl;
	/**地产价值*/
	@Excel(name = "地产价值", width = 15)
	@ApiModelProperty(value = "地产价值")
	private java.lang.String dcjz;
	/**地产详情说明*/
	@Excel(name = "地产详情说明", width = 15)
	@ApiModelProperty(value = "地产详情说明")
	private java.lang.String dcxqsm;
	/**交通工具数量*/
	@Excel(name = "交通工具数量", width = 15)
	@ApiModelProperty(value = "交通工具数量")
	private java.lang.String jtgjsl;
	/**交通工具价值*/
	@Excel(name = "交通工具价值", width = 15)
	@ApiModelProperty(value = "交通工具价值")
	private java.lang.String jtgjjz;
	/**交通工具详情说明*/
	@Excel(name = "交通工具详情说明", width = 15)
	@ApiModelProperty(value = "交通工具详情说明")
	private java.lang.String jtgjxqsm;
	/**存款数量*/
	@Excel(name = "存款数量", width = 15)
	@ApiModelProperty(value = "存款数量")
	private java.lang.String cksl;
	/**存款价值*/
	@Excel(name = "存款价值", width = 15)
	@ApiModelProperty(value = "存款价值")
	private java.lang.String ckjz;
	/**存款详情说明*/
	@Excel(name = "存款详情说明", width = 15)
	@ApiModelProperty(value = "存款详情说明")
	private java.lang.String ckxqsm;
	/**有价单证数量*/
	@Excel(name = "有价单证数量", width = 15)
	@ApiModelProperty(value = "有价单证数量")
	private java.lang.String yjdzsl;
	/**有价单证价值*/
	@Excel(name = "有价单证价值", width = 15)
	@ApiModelProperty(value = "有价单证价值")
	private java.lang.String yjdzjz;
	/**有价单证详情说明*/
	@Excel(name = "有价单证详情说明", width = 15)
	@ApiModelProperty(value = "有价单证详情说明")
	private java.lang.String yjdzxqsm;
	/**股权数量*/
	@Excel(name = "股权数量", width = 15)
	@ApiModelProperty(value = "股权数量")
	private java.lang.String gqsl;
	/**股权价值*/
	@Excel(name = "股权价值", width = 15)
	@ApiModelProperty(value = "股权价值")
	private java.lang.String gqjz;
	/**股权详情说明*/
	@Excel(name = "股权详情说明", width = 15)
	@ApiModelProperty(value = "股权详情说明")
	private java.lang.String gqxqsm;
	/**其他资产数量*/
	@Excel(name = "其他资产数量", width = 15)
	@ApiModelProperty(value = "其他资产数量")
	private java.lang.String qtzcsl;
	/**其他资产价值*/
	@Excel(name = "其他资产价值", width = 15)
	@ApiModelProperty(value = "其他资产价值")
	private java.lang.String qtzcjz;
	/**其他资产详情说明*/
	@Excel(name = "其他资产详情说明", width = 15)
	@ApiModelProperty(value = "其他资产详情说明")
	private java.lang.String qtzcxqsm;
	/**所有资产合计*/
	@Excel(name = "所有资产合计", width = 15)
	@ApiModelProperty(value = "所有资产合计")
	private java.lang.String sszchj;
	/**本系统借款债权人*/
	@Excel(name = "本系统借款债权人", width = 15)
	@ApiModelProperty(value = "本系统借款债权人")
	private java.lang.String bxtjkzqr;
	/**本系统借款数量*/
	@Excel(name = "本系统借款数量", width = 15)
	@ApiModelProperty(value = "本系统借款数量")
	private java.lang.String bxtjksl;
	/**本系统借款详情说明*/
	@Excel(name = "本系统借款详情说明", width = 15)
	@ApiModelProperty(value = "本系统借款详情说明")
	private java.lang.String bxtjkxqsm;
	/**他行借款债权人*/
	@Excel(name = "他行借款债权人", width = 15)
	@ApiModelProperty(value = "他行借款债权人")
	private java.lang.String thjkzqr;
	/**他行借款数量*/
	@Excel(name = "他行借款数量", width = 15)
	@ApiModelProperty(value = "他行借款数量")
	private java.lang.String thjksl;
	/**他行借款详情说明*/
	@Excel(name = "他行借款详情说明", width = 15)
	@ApiModelProperty(value = "他行借款详情说明")
	private java.lang.String thjkxqsm;
	/**信用卡债权人*/
	@Excel(name = "信用卡债权人", width = 15)
	@ApiModelProperty(value = "信用卡债权人")
	private java.lang.String xykzqr;
	/**信用卡数量*/
	@Excel(name = "信用卡数量", width = 15)
	@ApiModelProperty(value = "信用卡数量")
	private java.lang.String xyksl;
	/**信用卡详情说明*/
	@Excel(name = "信用卡详情说明", width = 15)
	@ApiModelProperty(value = "信用卡详情说明")
	private java.lang.String xykxqsm;
	/**其他负债债权人*/
	@Excel(name = "其他负债债权人", width = 15)
	@ApiModelProperty(value = "其他负债债权人")
	private java.lang.String qtfzzqr;
	/**其他负债数量*/
	@Excel(name = "其他负债数量", width = 15)
	@ApiModelProperty(value = "其他负债数量")
	private java.lang.String qtfzsl;
	/**其他负债详情说明*/
	@Excel(name = "其他负债详情说明", width = 15)
	@ApiModelProperty(value = "其他负债详情说明")
	private java.lang.String qtfzxqsm;
	/**家庭年开支债权人*/
	@Excel(name = "家庭年开支债权人", width = 15)
	@ApiModelProperty(value = "家庭年开支债权人")
	private java.lang.String jtnkzzqr;
	/**家庭年开支数量*/
	@Excel(name = "家庭年开支数量", width = 15)
	@ApiModelProperty(value = "家庭年开支数量")
	private java.lang.String jtnkzsl;
	/**家庭年开支详情说明*/
	@Excel(name = "家庭年开支详情说明", width = 15)
	@ApiModelProperty(value = "家庭年开支详情说明")
	private java.lang.String jtnkzxqsm;
	/**所有负债合计*/
	@Excel(name = "所有负债合计", width = 15)
	@ApiModelProperty(value = "所有负债合计")
	private java.lang.String ssfzhj;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
	@ApiModelProperty(value = "年龄")
	private java.lang.String nl;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15)
	@ApiModelProperty(value = "文化程度")
	private java.lang.String whcd;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
	@ApiModelProperty(value = "健康状况")
	private java.lang.String jkzk;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
	@ApiModelProperty(value = "婚姻状况")
	private java.lang.String hyzk;
	/**职业*/
	@Excel(name = "职业", width = 15)
	@ApiModelProperty(value = "职业")
	private java.lang.String zy;
	/**职务*/
	@Excel(name = "职务", width = 15)
	@ApiModelProperty(value = "职务")
	private java.lang.String zw;
	/**从业年限*/
	@Excel(name = "从业年限", width = 15)
	@ApiModelProperty(value = "从业年限")
	private java.lang.String cynx;
	/**居住状况*/
	@Excel(name = "居住状况", width = 15)
	@ApiModelProperty(value = "居住状况")
	private java.lang.String jzzk;
	/**居住时间*/
	@Excel(name = "居住时间", width = 15)
	@ApiModelProperty(value = "居住时间")
	private java.lang.String jzsj;
	/**有无违法记录*/
	@Excel(name = "有无违法记录", width = 15)
	@ApiModelProperty(value = "有无违法记录")
	private java.lang.String ywwfjl;
	/**社会评价*/
	@Excel(name = "社会评价", width = 15)
	@ApiModelProperty(value = "社会评价")
	private java.lang.String shpj;
	/**贷款逾期情况*/
	@Excel(name = "贷款逾期情况", width = 15)
	@ApiModelProperty(value = "贷款逾期情况")
	private java.lang.String dkyqqk;
	/**他行征信逾期记录*/
	@Excel(name = "他行征信逾期记录", width = 15)
	@ApiModelProperty(value = "他行征信逾期记录")
	private java.lang.String thzxyqjl;
	/**经济能力*/
	@Excel(name = "经济能力", width = 15)
	@ApiModelProperty(value = "经济能力")
	private java.lang.String jjnl;
}
