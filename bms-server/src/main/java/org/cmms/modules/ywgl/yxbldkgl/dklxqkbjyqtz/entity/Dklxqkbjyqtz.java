package org.cmms.modules.ywgl.yxbldkgl.dklxqkbjyqtz.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 贷款五级分类人工复核
 * @Author: Penghr
 * @Date:   2022-08-22
 * @Version: V1.0
 */
@Data
@TableName("yxbldk_dklxqkbjyqtz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="yxbldk_dklxqkbjyqtz对象", description="贷款五级分类人工复核")
public class Dklxqkbjyqtz {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**所属支行*/
	@Excel(name = "所属机构名称", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String sszh;
	/**开户机构号*/
	@Excel(name = "开户机构名称", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "开户机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String branchNo;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String identNo;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String acctNo;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
	private Date qxDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date endDate;
	/**贷款期限(1 短期贷款 2 长期贷款)*/
	@Excel(name = "贷款期限", width = 15, dicCode = "yxbldkqx")
    @ApiModelProperty(value = "贷款期限(1 短期贷款 2 长期贷款)")
	@Dict(dicCode = "yxbldkqx")
	private Integer dkqx;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private String syts;
	/**贷款余额(元)*/
	@Excel(name = "贷款余额(元)", width = 15, numFormat = ",##0.000")
    @ApiModelProperty(value = "贷款余额(元)")
	private java.math.BigDecimal currBal;
	/**贷款形态(1 正常 2 关注 3 次级 4 可疑 5 损失)*/
	@Excel(name = "贷款形态", width = 15, dicCode = "wjflbz")
    @ApiModelProperty(value = "贷款形态")
	@Dict(dicCode = "wjflbz")
	private Integer fiveClassType;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15, dicCode = "khjlbh",dictTable = "hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "主客户经理")
	@Dict(dicCode = "khjlbh",dictTable = "hr_bas_staff",dicText = "ygxm")
	private String zkhjl;

	/**利息欠款日期1*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd", groupName = "利息欠款(1)")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "利息欠款日期1")
	private Date lxqkrqOne;
	/**利息欠款期数1*/
	@Excel(name = "期数", width = 15, groupName = "利息欠款(1)")
    @ApiModelProperty(value = "利息欠款期数1")
	private Integer lxqkqsOne;

	/**利息欠款日期2*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd", groupName = "利息欠款(2)")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "利息欠款日期2")
	private Date lxqkrqTwo;
	/**利息欠款期数2*/
	@Excel(name = "期数", width = 15, groupName = "利息欠款(2)")
    @ApiModelProperty(value = "利息欠款期数2")
	private Integer lxqkqsTwo;

	/**利息欠款日期3*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd", groupName = "利息欠款(3)")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "利息欠款日期3")
	private Date lxqkrqThree;
	/**利息欠款期数3*/
	@Excel(name = "期数", width = 15, groupName = "利息欠款(3)")
    @ApiModelProperty(value = "利息欠款期数3")
	private Integer lxqkqsThree;

	/**利息欠款日期4*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd", groupName = "利息欠款(4)")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "利息欠款日期4")
	private Date lxqkrqFour;
	/**利息欠款期数4*/
	@Excel(name = "期数", width = 15, groupName = "利息欠款(4)")
    @ApiModelProperty(value = "利息欠款期数4")
	private Integer lxqkqsFour;

	/**利息欠款日期5*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd", groupName = "利息欠款(5)")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "利息欠款日期5")
	private Date lxqkrqFive;
	/**利息欠款期数5*/
	@Excel(name = "期数", width = 15, groupName = "利息欠款(5)")
    @ApiModelProperty(value = "利息欠款期数5")
	private Integer lxqkqsFive;

	/**利息欠款日期6*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd", groupName = "利息欠款(6)")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "利息欠款日期6")
	private Date lxqkrqSix;
	/**利息欠款期数6*/
	@Excel(name = "期数", width = 15, groupName = "利息欠款(6)")
    @ApiModelProperty(value = "利息欠款期数6")
	private Integer lxqkqsSix;

	/**贷款逾期日期至*/
	@Excel(name = "日期至", width = 15, format = "yyyy-MM-dd", groupName = "贷款逾期")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "贷款逾期日期至")
	private Date dkyqrqz;
	/**贷款逾期天数*/
	@Excel(name = "天数", width = 15, groupName = "贷款逾期")
    @ApiModelProperty(value = "贷款逾期天数")
	private Integer dkyqts;

	/**最近利息逾期四期及以上日期*/
	@Excel(name = "最近利息逾期四期及以上日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最近利息逾期四期及以上日期")
	private Date zjlxyqrq;
	/**建议解除手工标记*/
	@Excel(name = "建议解除手工标记", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "建议解除手工标记")
	@Dict(dicCode = "sfbz")
	private Integer sfjcsgbj;
	/**解除手工标记日期（弃用）*/
//	@Excel(name = "解除手工标记日期", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "解除手工标记日期")
//	private Date jcsgbjrq;
	/**建议手工下调（弃用）*/
//	@Excel(name = "建议手工下调", width = 15, dicCode = "sfbz")
//    @ApiModelProperty(value = "建议手工下调")
//	@Dict(dicCode = "sfbz")
//	private Integer sfsgxt;
	/**是否一户多形态（弃用）*/
//	@Excel(name = "一户多形态", width = 15, dicCode = "yxbldk_yhdxt")
//    @ApiModelProperty(value = "是否一户多形态")
//	@Dict(dicCode = "yxbldk_yhdxt")
//	private Integer sfyhdxt;
	/**是否隐性不良（弃用）*/
//	@Excel(name = "隐性不良", width = 15, dicCode = "sfbz")
//	@ApiModelProperty(value = "是否隐性不良")
//	@Dict(dicCode = "sfbz")
//	private Integer sfyxbl;
	/**五级分类人工复核*/
	@Excel(name = "五级分类人工复核", width = 15, dicCode = "dkxt")
	@ApiModelProperty(value = "五级分类人工复核")
	@Dict(dicCode = "dkxt")/*yxbldk_wjflrgfh*/
	private Integer wjflrgfh;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;

	/**录入标识(0 导入 1 录入 2 修改)*/
//	@Excel(name = "操作标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
//	@Excel(name = "操作人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
//	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
