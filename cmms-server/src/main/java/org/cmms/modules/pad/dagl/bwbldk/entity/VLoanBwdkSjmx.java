package org.cmms.modules.pad.dagl.bwbldk.entity;

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
 * @Description: 表外贷款_数据明细
 * @Author: jeecg-boot
 * @Date:   2022-11-10
 * @Version: V1.0
 */
@Data
@TableName("v_loan_bwdk_sjmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_loan_bwdk_sjmx对象", description="表外贷款_数据明细")
public class VLoanBwdkSjmx {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户地址*/
	@Excel(name = "客户地址", width = 15)
    @ApiModelProperty(value = "客户地址")
	private String khdz;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**转入表外日期*/
	@Excel(name = "转入表外日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "转入表外日期")
	private Date zrbwrq;
	/**金额(包含本金+利息)*/
	@Excel(name = "金额(包含本金+利息)", width = 15)
    @ApiModelProperty(value = "金额(包含本金+利息)")
	private java.math.BigDecimal je;
	/**已收回金额(包含本金+利息)*/
	@Excel(name = "已收回金额(包含本金+利息)", width = 15)
    @ApiModelProperty(value = "已收回金额(包含本金+利息)")
	private java.math.BigDecimal yshje;
	/**核心余额*/
	@Excel(name = "核心余额", width = 15)
    @ApiModelProperty(value = "核心余额")
	private java.math.BigDecimal hxye;
	/**转入表外类型(1 股金置换 2 政府置换 3 核销 4 票据置换 5 其他置换)*/
	@Excel(name = "转入表外类型(1 股金置换 2 政府置换 3 核销 4 票据置换 5 其他置换)", width = 15)
    @ApiModelProperty(value = "转入表外类型(1 股金置换 2 政府置换 3 核销 4 票据置换 5 其他置换)")
	@Dict(dicCode = "zrbwlx")
	private Integer zrbwlx;
	/**贷款日期*/
	@Excel(name = "贷款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "贷款日期")
	private Date dkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**原始利率*/
	@Excel(name = "原始利率", width = 15)
    @ApiModelProperty(value = "原始利率")
	private java.math.BigDecimal ysll;
	/**最近催收日期*/
	@Excel(name = "最近催收日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最近催收日期")
	private Date zjcsrq;
	/**诉讼时效*/
	@Excel(name = "诉讼时效", width = 15)
    @ApiModelProperty(value = "诉讼时效")
	private String sssx;
	/**诉讼时效到期日*/
	@Excel(name = "诉讼时效到期日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "诉讼时效到期日")
	private Date sssxdqr;
	/**状态(1 已结清 2 未结清)*/
	@Excel(name = "状态(1 已结清 2 未结清)", width = 15)
    @ApiModelProperty(value = "状态(1 已结清 2 未结清)")
	private Integer zt;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识(0 导入 1 录入 2 修改)*/
	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**收回本金*/
	@Excel(name = "收回本金", width = 15)
    @ApiModelProperty(value = "收回本金")
	private Integer shbj;
	/**收回利息*/
	@Excel(name = "收回利息", width = 15)
    @ApiModelProperty(value = "收回利息")
	private Integer shlx;
	/**利息*/
	@Excel(name = "利息", width = 15)
    @ApiModelProperty(value = "利息")
	private java.math.BigDecimal lx;
	/**本金*/
	@Excel(name = "本金", width = 15)
    @ApiModelProperty(value = "本金")
	private java.math.BigDecimal bj;
	/**手工算息*/
	@Excel(name = "手工算息", width = 15)
    @ApiModelProperty(value = "手工算息")
	private java.math.BigDecimal sgsx;
	/**业务ID*/
	@Excel(name = "业务ID", width = 15)
    @ApiModelProperty(value = "业务ID")
	private Integer ywid;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private Integer zjlx;
	/**客户状况*/
	@Excel(name = "客户状况", width = 15)
    @ApiModelProperty(value = "客户状况")
	private String khzk;
	/**借据名称*/
	@Excel(name = "借据名称", width = 15)
    @ApiModelProperty(value = "借据名称")
	private String jjmc;
	/**借款方式(1 信用 2 抵押 3 保证 4 质押 5 组合)*/
	@Excel(name = "借款方式(1 信用 2 抵押 3 保证 4 质押 5 组合)", width = 15)
    @ApiModelProperty(value = "借款方式(1 信用 2 抵押 3 保证 4 质押 5 组合)")
	private Integer jkfs;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**审批状态(0 未审批 1 已审核)*/
	@Excel(name = "审批状态(0 未审批 1 已审核)", width = 15)
    @ApiModelProperty(value = "审批状态(0 未审批 1 已审核)")
	private Integer spzt;
	/**管理责任人工号*/
	@Excel(name = "管理责任人工号", width = 15)
    @ApiModelProperty(value = "管理责任人工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String glzrrgh;
	/**溢出利息*/
	@Excel(name = "溢出利息", width = 15)
    @ApiModelProperty(value = "溢出利息")
	private java.math.BigDecimal yclx;
	/**借款主体*/
	@Excel(name = "借款主体", width = 15)
    @ApiModelProperty(value = "借款主体")
	private Integer jkzt;
	/**是否进入诉讼(1 是 2 否)*/
	@Excel(name = "是否进入诉讼(1 是 2 否)", width = 15)
    @ApiModelProperty(value = "是否进入诉讼(1 是 2 否)")
	private Integer sfjrss;
	/**是否进入执行(1 是 2 否)*/
	@Excel(name = "是否进入执行(1 是 2 否)", width = 15)
    @ApiModelProperty(value = "是否进入执行(1 是 2 否)")
	private Integer sfjrzx;
	/**审批人工号*/
	@Excel(name = "审批人工号", width = 15)
    @ApiModelProperty(value = "审批人工号")
	private String sprgh;
	/**行业分类*/
	@Excel(name = "行业分类", width = 15)
    @ApiModelProperty(value = "行业分类")
	private String hyfl;
	/**清收类型*/
	@Excel(name = "清收类型", width = 15)
    @ApiModelProperty(value = "清收类型")
	private Integer qslx;
	/**被追责责任人工号*/
	@Excel(name = "被追责责任人工号", width = 15)
    @ApiModelProperty(value = "被追责责任人工号")
	private String bzzzrrgh;
	/**贷款分类(1 员工自借及担保 2 假借冒名 3 抵质押物未处置 4 未追责 5 其他)*/
	@Excel(name = "贷款分类(1 员工自借及担保 2 假借冒名 3 抵质押物未处置 4 未追责 5 其他)", width = 15)
    @ApiModelProperty(value = "贷款分类(1 员工自借及担保 2 假借冒名 3 抵质押物未处置 4 未追责 5 其他)")
	private Integer dkfl;
	/**剩余本金*/
	@Excel(name = "剩余本金", width = 15)
    @ApiModelProperty(value = "剩余本金")
	private Integer sybj;
	/**剩余利息*/
	@Excel(name = "剩余利息", width = 15)
    @ApiModelProperty(value = "剩余利息")
	private Integer sylx;
	/**起息日期*/
	@Excel(name = "起息日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "起息日期")
	private Date qxrq;
	/**重组(转据)次数*/
	@Excel(name = "重组(转据)次数", width = 15)
    @ApiModelProperty(value = "重组(转据)次数")
	private Integer czzjcs;
	/**担保方名称*/
	@Excel(name = "担保方名称", width = 15)
    @ApiModelProperty(value = "担保方名称")
	private String dbfmc;
	/**担保方住址*/
	@Excel(name = "担保方住址", width = 15)
    @ApiModelProperty(value = "担保方住址")
	private String dbfzz;
	/**担保方联系方式*/
	@Excel(name = "担保方联系方式", width = 15)
    @ApiModelProperty(value = "担保方联系方式")
	private String dbflxfs;
	/**担保物名称*/
	@Excel(name = "担保物名称", width = 15)
    @ApiModelProperty(value = "担保物名称")
	private String dbwmc;
	/**担保物情况*/
	@Excel(name = "担保物情况", width = 15)
    @ApiModelProperty(value = "担保物情况")
	private String dbwqk;
	/**主调查人*/
	@Excel(name = "主调查人", width = 15)
    @ApiModelProperty(value = "主调查人")
	private String zdcr;
	/**次调查人*/
	@Excel(name = "次调查人", width = 15)
    @ApiModelProperty(value = "次调查人")
	private String cdcr;
	/**包收人*/
	@Excel(name = "包收人", width = 15)
    @ApiModelProperty(value = "包收人")
	private String bsr;
	/**审批人*/
	@Excel(name = "审批人", width = 15)
    @ApiModelProperty(value = "审批人")
	private String zhhzspr;
	/**最终审批人*/
	@Excel(name = "最终审批人", width = 15)
    @ApiModelProperty(value = "最终审批人")
	private String zzspr;
	/**关联时间起*/
	@Excel(name = "关联时间起", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "关联时间起")
	private Date glsjq;
	/**关联时间止*/
	@Excel(name = "关联时间止", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "关联时间止")
	private Date glsjz;
	/**形成不良原因*/
	@Excel(name = "形成不良原因", width = 15)
    @ApiModelProperty(value = "形成不良原因")
	private String xcblyy;
	/**问责情况*/
	@Excel(name = "问责情况", width = 15)
    @ApiModelProperty(value = "问责情况")
	private String wzqk;
	/**化解措施*/
	@Excel(name = "化解措施", width = 15)
    @ApiModelProperty(value = "化解措施")
	private String hjcs;
	/**表外挂息*/
	@Excel(name = "表外挂息", width = 15)
    @ApiModelProperty(value = "表外挂息")
	private java.math.BigDecimal bwgx;
	/**借款用途*/
	@Excel(name = "借款用途", width = 15)
    @ApiModelProperty(value = "借款用途")
	private String jkyt;
	/**审批出表责任人姓名*/
	@Excel(name = "审批出表责任人姓名", width = 15)
    @ApiModelProperty(value = "审批出表责任人姓名")
	private String spcbzrrxm;
	/**形成原因*/
	@Excel(name = "形成原因", width = 15)
    @ApiModelProperty(value = "形成原因")
	private Integer xcyy;
	/**不良贷款认定情况*/
	@Excel(name = "不良贷款认定情况", width = 15)
    @ApiModelProperty(value = "不良贷款认定情况")
	private String bldkrdqk;
	/**剩余表外挂息*/
	@Excel(name = "剩余表外挂息", width = 15)
    @ApiModelProperty(value = "剩余表外挂息")
	private Integer sybwgx;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private Integer dkye;
	/**清收公司*/
	@Excel(name = "清收公司", width = 15)
    @ApiModelProperty(value = "清收公司")
	private String qsgs;
	/**清收公司2*/
	@Excel(name = "清收公司2", width = 15)
    @ApiModelProperty(value = "清收公司2")
	private String qsgsSecond;
	/**原机构名称*/
	@Excel(name = "原机构名称", width = 15)
    @ApiModelProperty(value = "原机构名称")
	private String yjgmc;
	/**信贷系统客户名称*/
	@Excel(name = "信贷系统客户名称", width = 15)
    @ApiModelProperty(value = "信贷系统客户名称")
	private String xdxtkhmc;
	/**置换核销新账号*/
	@Excel(name = "置换核销新账号", width = 15)
    @ApiModelProperty(value = "置换核销新账号")
	private String zhhxxzh;
	/**诉讼情况描述*/
	@Excel(name = "诉讼情况描述", width = 15)
    @ApiModelProperty(value = "诉讼情况描述")
	private String ssqkms;
	/**第一责任人姓名*/
	@Excel(name = "第一责任人姓名", width = 15)
    @ApiModelProperty(value = "第一责任人姓名")
	private String dyzrrxm;
	/**利息账号*/
	@Excel(name = "利息账号", width = 15)
    @ApiModelProperty(value = "利息账号")
	private String lxzh;
	/**不良贷款处置措施*/
	@Excel(name = "不良贷款处置措施", width = 15)
    @ApiModelProperty(value = "不良贷款处置措施")
	private String bldkczcs;
	/**农商统计项*/
	@Excel(name = "农商统计项", width = 15)
    @ApiModelProperty(value = "农商统计项")
	private String nstjx;
	/**政府统计项*/
	@Excel(name = "政府统计项", width = 15)
    @ApiModelProperty(value = "政府统计项")
	private String zftjx;
	/**是否按协议还款*/
	@Excel(name = "是否按协议还款", width = 15)
    @ApiModelProperty(value = "是否按协议还款")
	private String sfaxyhk;
	/**协议还款内容*/
	@Excel(name = "协议还款内容", width = 15)
    @ApiModelProperty(value = "协议还款内容")
	private String xyhknr;
	/**催收类型*/
	@Excel(name = "催收类型", width = 15)
    @ApiModelProperty(value = "催收类型")
	private String cslx;
	/**是否上传附件*/
	@Excel(name = "是否上传附件", width = 15)
	@ApiModelProperty(value = "是否上传附件")
	@Dict(dicCode = "sfbz")
	private String sfscfj;
}
