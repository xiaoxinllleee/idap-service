package org.cmms.modules.pad.qtzrrxxgl.entity;

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
public class CamsZcsxQtzrrpjsxxxPad {

	/**资产负债率*/
	@Excel(name = "资产负债率", width = 15)
	@ApiModelProperty(value = "资产负债率")
	private String zcfzl;
	/**自有资金占比*/
	@Excel(name = "自有资金占比", width = 15)
	@ApiModelProperty(value = "自有资金占比")
	private String zyzjzb;
	/**年纯收入*/
	@Excel(name = "年纯收入", width = 15)
	@ApiModelProperty(value = "年纯收入")
	private String ncsr;
	/**与信用社往来时间*/
	@Excel(name = "与信用社往来时间", width = 15)
	@ApiModelProperty(value = "与信用社往来时间")
	private String yxyswlsj;
	/**与信用社合作情况*/
	@Excel(name = "与信用社合作情况", width = 15)
	@ApiModelProperty(value = "与信用社合作情况")
	private String yxyshzqk;
	/**人身保险*/
	@Excel(name = "人身保险", width = 15)
	@ApiModelProperty(value = "人身保险")
	private String rsbx;
	/**财产保险*/
	@Excel(name = "财产保险", width = 15)
	@ApiModelProperty(value = "财产保险")
	private String ccbx;
	/**总评得分小计*/
	@Excel(name = "总评得分小计", width = 15)
	@ApiModelProperty(value = "总评得分小计")
	private String zpdfxj;
	/**系统测算评定等级*/
	@Excel(name = "系统测算评定等级", width = 15)
	@ApiModelProperty(value = "系统测算评定等级")
	private String xtcspddj;
	/**系统测算授信额度*/
	@Excel(name = "系统测算授信额度", width = 15)
	@ApiModelProperty(value = "系统测算授信额度")
	private String xtcssxed;
	/**调整后等级*/
	@Excel(name = "调整后等级", width = 15)
	@ApiModelProperty(value = "调整后等级")
	private String tzhdj;
	/**等级调整事项*/
	@Excel(name = "等级调整事项", width = 15)
	@ApiModelProperty(value = "等级调整事项")
	private String djtzsx;
	/**客户经理评定等级*/
	@Excel(name = "客户经理评定等级", width = 15)
	@ApiModelProperty(value = "客户经理评定等级")
	private String khjlpddj;
	/**客户经理授信额度*/
	@Excel(name = "客户经理授信额度", width = 15)
	@ApiModelProperty(value = "客户经理授信额度")
	private String khjlsxed;
	/**最终评定等级*/
	@Excel(name = "最终评定等级", width = 15)
	@ApiModelProperty(value = "最终评定等级")
	private String zzpddj;
	/**最终授信额度*/
	@Excel(name = "最终授信额度", width = 15)
	@ApiModelProperty(value = "最终授信额度")
	private String zzsxed;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**upDt*/
	@Excel(name = "upDt", width = 15)
	@ApiModelProperty(value = "upDt")
	private String upDt;
	/**upTm*/
	@Excel(name = "upTm", width = 15)
	@ApiModelProperty(value = "upTm")
	private String upTm;
	/**hmcId*/
	@Excel(name = "hmcId", width = 15)
	@ApiModelProperty(value = "hmcId")
	private String hmcId;
	/**qydm*/
	@Excel(name = "qydm", width = 15)
	@ApiModelProperty(value = "qydm")
	private String qydm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**不予授信情形*/
	@Excel(name = "不予授信情形", width = 15)
	@ApiModelProperty(value = "不予授信情形")
	private String bysxqx;
	/**房地产资产合计*/
	@Excel(name = "房地产资产合计", width = 15)
	@ApiModelProperty(value = "房地产资产合计")
	private String fczchj;
	/**地产数量*/
	@Excel(name = "地产数量", width = 15)
	@ApiModelProperty(value = "地产数量")
	private String dcsl;
	/**地产价值*/
	@Excel(name = "地产价值", width = 15)
	@ApiModelProperty(value = "地产价值")
	private String dcjz;
	/**地产详情说明*/
	@Excel(name = "地产详情说明", width = 15)
	@ApiModelProperty(value = "地产详情说明")
	private String dcxqsm;
	/**交通工具数量*/
	@Excel(name = "交通工具数量", width = 15)
	@ApiModelProperty(value = "交通工具数量")
	private String jtgjsl;
	/**交通工具价值*/
	@Excel(name = "交通工具价值", width = 15)
	@ApiModelProperty(value = "交通工具价值")
	private String jtgjjz;
	/**交通工具详情说明*/
	@Excel(name = "交通工具详情说明", width = 15)
	@ApiModelProperty(value = "交通工具详情说明")
	private String jtgjxqsm;
	/**存款数量*/
	@Excel(name = "存款数量", width = 15)
	@ApiModelProperty(value = "存款数量")
	private String cksl;
	/**存款价值*/
	@Excel(name = "存款价值", width = 15)
	@ApiModelProperty(value = "存款价值")
	private String ckjz;
	/**存款详情说明*/
	@Excel(name = "存款详情说明", width = 15)
	@ApiModelProperty(value = "存款详情说明")
	private String ckxqsm;
	/**有价单证数量*/
	@Excel(name = "有价单证数量", width = 15)
	@ApiModelProperty(value = "有价单证数量")
	private String yjdzsl;
	/**有价单证价值*/
	@Excel(name = "有价单证价值", width = 15)
	@ApiModelProperty(value = "有价单证价值")
	private String yjdzjz;
	/**有价单证详情说明*/
	@Excel(name = "有价单证详情说明", width = 15)
	@ApiModelProperty(value = "有价单证详情说明")
	private String yjdzxqsm;
	/**股权数量*/
	@Excel(name = "股权数量", width = 15)
	@ApiModelProperty(value = "股权数量")
	private String gqsl;
	/**股权价值*/
	@Excel(name = "股权价值", width = 15)
	@ApiModelProperty(value = "股权价值")
	private String gqjz;
	/**股权详情说明*/
	@Excel(name = "股权详情说明", width = 15)
	@ApiModelProperty(value = "股权详情说明")
	private String gqxqsm;
	/**其他资产数量*/
	@Excel(name = "其他资产数量", width = 15)
	@ApiModelProperty(value = "其他资产数量")
	private String qtzcsl;
	/**其他资产价值*/
	@Excel(name = "其他资产价值", width = 15)
	@ApiModelProperty(value = "其他资产价值")
	private String qtzcjz;
	/**其他资产详情说明*/
	@Excel(name = "其他资产详情说明", width = 15)
	@ApiModelProperty(value = "其他资产详情说明")
	private String qtzcxqsm;
	/**所有资产合计*/
	@Excel(name = "所有资产合计", width = 15)
	@ApiModelProperty(value = "所有资产合计")
	private String sszchj;
	/**本系统借款债权人*/
	@Excel(name = "本系统借款债权人", width = 15)
	@ApiModelProperty(value = "本系统借款债权人")
	private String bxtjkzqr;
	/**本系统借款数量*/
	@Excel(name = "本系统借款数量", width = 15)
	@ApiModelProperty(value = "本系统借款数量")
	private String bxtjksl;
	/**本系统借款详情说明*/
	@Excel(name = "本系统借款详情说明", width = 15)
	@ApiModelProperty(value = "本系统借款详情说明")
	private String bxtjkxqsm;
	/**他行借款债权人*/
	@Excel(name = "他行借款债权人", width = 15)
	@ApiModelProperty(value = "他行借款债权人")
	private String thjkzqr;
	/**他行借款数量*/
	@Excel(name = "他行借款数量", width = 15)
	@ApiModelProperty(value = "他行借款数量")
	private String thjksl;
	/**他行借款详情说明*/
	@Excel(name = "他行借款详情说明", width = 15)
	@ApiModelProperty(value = "他行借款详情说明")
	private String thjkxqsm;
	/**信用卡债权人*/
	@Excel(name = "信用卡债权人", width = 15)
	@ApiModelProperty(value = "信用卡债权人")
	private String xykzqr;
	/**信用卡数量*/
	@Excel(name = "信用卡数量", width = 15)
	@ApiModelProperty(value = "信用卡数量")
	private String xyksl;
	/**信用卡详情说明*/
	@Excel(name = "信用卡详情说明", width = 15)
	@ApiModelProperty(value = "信用卡详情说明")
	private String xykxqsm;
	/**其他负债债权人*/
	@Excel(name = "其他负债债权人", width = 15)
	@ApiModelProperty(value = "其他负债债权人")
	private String qtfzzqr;
	/**其他负债数量*/
	@Excel(name = "其他负债数量", width = 15)
	@ApiModelProperty(value = "其他负债数量")
	private String qtfzsl;
	/**其他负债详情说明*/
	@Excel(name = "其他负债详情说明", width = 15)
	@ApiModelProperty(value = "其他负债详情说明")
	private String qtfzxqsm;
	/**家庭年开支债权人*/
	@Excel(name = "家庭年开支债权人", width = 15)
	@ApiModelProperty(value = "家庭年开支债权人")
	private String jtnkzzqr;
	/**家庭年开支数量*/
	@Excel(name = "家庭年开支数量", width = 15)
	@ApiModelProperty(value = "家庭年开支数量")
	private String jtnkzsl;
	/**家庭年开支详情说明*/
	@Excel(name = "家庭年开支详情说明", width = 15)
	@ApiModelProperty(value = "家庭年开支详情说明")
	private String jtnkzxqsm;
	/**所有负债合计*/
	@Excel(name = "所有负债合计", width = 15)
	@ApiModelProperty(value = "所有负债合计")
	private String ssfzhj;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
	@ApiModelProperty(value = "年龄")
	private String nl;
	/**文化程度*/
	@Excel(name = "文化程度", width = 15)
	@ApiModelProperty(value = "文化程度")
	private String whcd;
	/**健康状况*/
	@Excel(name = "健康状况", width = 15)
	@ApiModelProperty(value = "健康状况")
	private String jkzk;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
	@ApiModelProperty(value = "婚姻状况")
	private String hyzk;
	/**职业*/
	@Excel(name = "职业", width = 15)
	@ApiModelProperty(value = "职业")
	private String zy;
	/**职务*/
	@Excel(name = "职务", width = 15)
	@ApiModelProperty(value = "职务")
	private String zw;
	/**从业年限*/
	@Excel(name = "从业年限", width = 15)
	@ApiModelProperty(value = "从业年限")
	private String cynx;
	/**居住状况*/
	@Excel(name = "居住状况", width = 15)
	@ApiModelProperty(value = "居住状况")
	private String jzzk;
	/**居住时间*/
	@Excel(name = "居住时间", width = 15)
	@ApiModelProperty(value = "居住时间")
	private String jzsj;
	/**有无违法记录*/
	@Excel(name = "有无违法记录", width = 15)
	@ApiModelProperty(value = "有无违法记录")
	private String ywwfjl;
	/**社会评价*/
	@Excel(name = "社会评价", width = 15)
	@ApiModelProperty(value = "社会评价")
	private String shpj;
	/**贷款逾期情况*/
	@Excel(name = "贷款逾期情况", width = 15)
	@ApiModelProperty(value = "贷款逾期情况")
	private String dkyqqk;
	/**他行征信逾期记录*/
	@Excel(name = "他行征信逾期记录", width = 15)
	@ApiModelProperty(value = "他行征信逾期记录")
	private String thzxyqjl;
	/**经济能力*/
	@Excel(name = "经济能力", width = 15)
	@ApiModelProperty(value = "经济能力")
	private String jjnl;
}
