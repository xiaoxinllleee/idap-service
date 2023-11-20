package org.cmms.modules.pad.dagl.bwdksjmx.entity;

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
 * @Description: 表外贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2023-07-20
 * @Version: V1.0
 */
@Data
@TableName("loan_bwdk_sjmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="loan_bwdk_sjmx对象", description="表外贷款数据明细")
public class BwdksjmxPad {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
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
	/**金额（包含本金+利息）*/
	@Excel(name = "金额（包含本金+利息）", width = 15)
    @ApiModelProperty(value = "金额（包含本金+利息）")
	private java.math.BigDecimal je;
	/**已收回金额（包含本金+利息）*/
	@Excel(name = "已收回金额（包含本金+利息）", width = 15)
    @ApiModelProperty(value = "已收回金额（包含本金+利息）")
	private java.math.BigDecimal yshje;
	/**核心余额*/
	@Excel(name = "核心余额", width = 15)
    @ApiModelProperty(value = "核心余额")
	private java.math.BigDecimal hxye;
	/**转入表外类型*/
	@Excel(name = "转入表外类型", width = 15)
    @ApiModelProperty(value = "转入表外类型")
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
	/**状态（1：已结清、2：未结清）*/
	@Excel(name = "状态（1：已结清、2：未结清）", width = 15)
    @ApiModelProperty(value = "状态（1：已结清、2：未结清）")
	private Integer zt;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识（0：导入 1：录入 2：修改）", width = 15)
    @ApiModelProperty(value = "录入标识（0：导入 1：录入 2：修改）")
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
	private java.math.BigDecimal shbj;
	/**收回利息*/
	@Excel(name = "收回利息", width = 15)
    @ApiModelProperty(value = "收回利息")
	private java.math.BigDecimal shlx;
	/**利息*/
	@Excel(name = "利息", width = 15)
    @ApiModelProperty(value = "利息")
	private java.math.BigDecimal lx;
	/**本金*/
	@Excel(name = "本金", width = 15)
    @ApiModelProperty(value = "本金")
	private java.math.BigDecimal bj;
	/**ywid*/
	@Excel(name = "ywid", width = 15)
    @ApiModelProperty(value = "ywid")
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
	/**借款方式（信用、抵押、保证、质押）*/
	@Excel(name = "借款方式（信用、抵押、保证、质押）", width = 15)
    @ApiModelProperty(value = "借款方式（信用、抵押、保证、质押）")
	private Integer jkfs;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**审批状态(0 未审批 1已审批)*/
	@Excel(name = "审批状态(0 未审批 1已审批)", width = 15)
    @ApiModelProperty(value = "审批状态(0 未审批 1已审批)")
	private Integer spzt;
	/**管理责任人工号*/
	@Excel(name = "管理责任人工号", width = 15)
    @ApiModelProperty(value = "管理责任人工号")
	private String glzrrgh;
	/**溢出利息*/
	@Excel(name = "溢出利息", width = 15)
    @ApiModelProperty(value = "溢出利息")
	private java.math.BigDecimal yclx;
	/**借款主体*/
	@Excel(name = "借款主体", width = 15)
    @ApiModelProperty(value = "借款主体")
	private Integer jkzt;
	/**是否进入诉讼（1：是 2：否）*/
	@Excel(name = "是否进入诉讼（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否进入诉讼（1：是 2：否）")
	private Integer sfjrss;
	/**是否进入执行（1：是 2：否）*/
	@Excel(name = "是否进入执行（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否进入执行（1：是 2：否）")
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
	/**贷款分类（1：员工自借及担保 2：假借冒名 3：抵质押物未处置 4：未追责 5：其他）*/
	@Excel(name = "贷款分类（1：员工自借及担保 2：假借冒名 3：抵质押物未处置 4：未追责 5：其他）", width = 15)
    @ApiModelProperty(value = "贷款分类（1：员工自借及担保 2：假借冒名 3：抵质押物未处置 4：未追责 5：其他）")
	private Integer dkfl;
	/**被追责责任人工号*/
	@Excel(name = "被追责责任人工号", width = 15)
    @ApiModelProperty(value = "被追责责任人工号")
	private String bzzzrrgh;
	/**手工算息*/
	@Excel(name = "手工算息", width = 15)
    @ApiModelProperty(value = "手工算息")
	private java.math.BigDecimal sgsx;
	/**起息日期*/
	@Excel(name = "起息日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "起息日期")
	private Date qxrq;
	/**重组（转据）次数*/
	@Excel(name = "重组（转据）次数", width = 15)
    @ApiModelProperty(value = "重组（转据）次数")
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
	/**管理时间起*/
	@Excel(name = "管理时间起", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "管理时间起")
	private Date glsjq;
	/**管理时间止*/
	@Excel(name = "管理时间止", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "管理时间止")
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
	/**不良贷款认定情况*/
	@Excel(name = "不良贷款认定情况", width = 15)
    @ApiModelProperty(value = "不良贷款认定情况")
	private String bldkrdqk;
	/**收回表外挂息*/
	@Excel(name = "收回表外挂息", width = 15)
    @ApiModelProperty(value = "收回表外挂息")
	private java.math.BigDecimal shbwgx;
	/**审批出表责任人姓名*/
	@Excel(name = "审批出表责任人姓名", width = 15)
    @ApiModelProperty(value = "审批出表责任人姓名")
	private String spcbzrrxm;
	/**形成原因 1:宏观产业政策 2:企业经营管理 3:其客户涉及民间融资  4:其它客观性因素  5:制度缺失  6:决策失误 7:信贷人员违规 8:风险意识淡薄 9:管理交接不规范 10:其它主观原因)*/
	@Excel(name = "形成原因 1:宏观产业政策 2:企业经营管理 3:其客户涉及民间融资  4:其它客观性因素  5:制度缺失  6:决策失误 7:信贷人员违规 8:风险意识淡薄 9:管理交接不规范 10:其它主观原因)", width = 15)
    @ApiModelProperty(value = "形成原因 1:宏观产业政策 2:企业经营管理 3:其客户涉及民间融资  4:其它客观性因素  5:制度缺失  6:决策失误 7:信贷人员违规 8:风险意识淡薄 9:管理交接不规范 10:其它主观原因)")
	private Integer xcyy;
	/**清收公司*/
	@Excel(name = "清收公司", width = 15)
    @ApiModelProperty(value = "清收公司")
	private String qsgs;
	/**原机构名称*/
	@Excel(name = "原机构名称", width = 15)
    @ApiModelProperty(value = "原机构名称")
	private String yjgmc;
	/**欠息*/
	@Excel(name = "欠息", width = 15)
    @ApiModelProperty(value = "欠息")
	private java.math.BigDecimal qx;
	/**处置类型*/
	@Excel(name = "处置类型", width = 15)
    @ApiModelProperty(value = "处置类型")
	private String czlx;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private Long xh;
	/**信贷系统客户名称*/
	@Excel(name = "信贷系统客户名称", width = 15)
    @ApiModelProperty(value = "信贷系统客户名称")
	private String xdxtkhmc;
	/**清收公司2*/
	@Excel(name = "清收公司2", width = 15)
    @ApiModelProperty(value = "清收公司2")
	private String qsgsSecond;
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
	/**异常数据（是/否）*/
	@Excel(name = "异常数据（是/否）", width = 15)
    @ApiModelProperty(value = "异常数据（是/否）")
	private String ycsj;
	/**特殊金额*/
	@Excel(name = "特殊金额", width = 15)
    @ApiModelProperty(value = "特殊金额")
	private java.math.BigDecimal tsje;
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15)
    @ApiModelProperty(value = "所属乡镇")
	private String ssxz;
	/**原借据名*/
	@Excel(name = "原借据名", width = 15)
    @ApiModelProperty(value = "原借据名")
	private String yjjm;
	/**收回日期*/
	@Excel(name = "收回日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "收回日期")
	private Date shrq;
	/**处置日期*/
	@Excel(name = "处置日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "处置日期")
	private Date czrq;
	/**原处置类型*/
	@Excel(name = "原处置类型", width = 15)
    @ApiModelProperty(value = "原处置类型")
	private String yczlx;
	/**现处置类型*/
	@Excel(name = "现处置类型", width = 15)
    @ApiModelProperty(value = "现处置类型")
	private String xczlx;
	/**是否交清收公司*/
	@Excel(name = "是否交清收公司", width = 15)
    @ApiModelProperty(value = "是否交清收公司")
	private String sfjqsgs;
	/**实际借款人*/
	@Excel(name = "实际借款人", width = 15)
    @ApiModelProperty(value = "实际借款人")
	private String sjjkr;
	/**实际借款人地址*/
	@Excel(name = "实际借款人地址", width = 15)
    @ApiModelProperty(value = "实际借款人地址")
	private String sjjkrdz;
	/**有无抵押物*/
	@Excel(name = "有无抵押物", width = 15)
    @ApiModelProperty(value = "有无抵押物")
	private String ywdyw;
	/**抵押物是否处置*/
	@Excel(name = "抵押物是否处置", width = 15)
    @ApiModelProperty(value = "抵押物是否处置")
	private String dywsfcz;
	/**利于政府，法院等部门清收的信息*/
	@Excel(name = "利于政府，法院等部门清收的信息", width = 15)
    @ApiModelProperty(value = "利于政府，法院等部门清收的信息")
	private String lyzf;
	/**能否找到借款人或担保人*/
	@Excel(name = "能否找到借款人或担保人", width = 15)
    @ApiModelProperty(value = "能否找到借款人或担保人")
	private String dbr;
	/**借款人现状*/
	@Excel(name = "借款人现状", width = 15)
    @ApiModelProperty(value = "借款人现状")
	private String jkrxz;
	/**是否认账认还*/
	@Excel(name = "是否认账认还", width = 15)
    @ApiModelProperty(value = "是否认账认还")
	private String sfrzrh;
	/**贷款真实情况*/
	@Excel(name = "贷款真实情况", width = 15)
    @ApiModelProperty(value = "贷款真实情况")
	private String dkzsqk;
	/**司法处置情况*/
	@Excel(name = "司法处置情况", width = 15)
    @ApiModelProperty(value = "司法处置情况")
	private String sfczqk;
}
