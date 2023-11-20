package org.cmms.modules.hr.djpd.khjlxjpd.tjbb.khjlpdmxtj.entity;

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
 * @Description: 客户经理评定明细统计
 * @Author: jeecg-boot
 * @Date:   2021-09-16
 * @Version: V1.0
 */
@Data
@TableName("V_GRADE_CUSTSTAR_MX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_GRADE_CUSTSTAR_MX对象", description="客户经理评定明细统计")
public class Khjlpdmxtj {

	/**pdzq*/
	@Excel(name = "评定周期", width = 15,dicCode = "rqwd")
    @ApiModelProperty(value = "评定周期")
	@Dict(dicCode = "rqwd")
	private String pdzq;
	/**pdrq*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
	private Date pdrq;
	/**djbh*/
	@Excel(name = "支行级别", width = 15,dicCode = "djbh", dictTable = "v_grade_custstar_zhdj", dicText = "djms",ds = "eweb")
	@Dict(dicCode = "djbh", dictTable = "v_grade_custstar_zhdj", dicText = "djms",ds = "eweb")
    @ApiModelProperty(value = "支行级别")
	private String djbh;
	/**djms*/
	//@Excel(name = "支行级别", width = 15)
    @ApiModelProperty(value = "支行级别")
	//@Dict(dicCode = "zhjb")
	private String djms;
	/**zzbz*/
	@Excel(name = "支行名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "支行名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sjzzbz;
	/**sjzzbz*/
	@Excel(name = "机构名称", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**gwbz*/
	//@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	private Integer gwbz;
	/**yggh*/
	//@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**khjlbz*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**ygxm*/
	@Excel(name = "客户经理名称", width = 15)
    @ApiModelProperty(value = "客户经理名称")
	private String ygxm;

	/**kjz*/
	@Excel(name = "会计证", width = 15,dicCode = "ywbz",groupName = "岗位资质")
    @ApiModelProperty(value = "会计证")
	@Dict(dicCode = "ywbz")
	private Integer kjz;
	/**yhycyzgz*/
	@Excel(name = "银行业从业资格证", width = 15,dicCode = "ywbz",groupName = "岗位资质")
    @ApiModelProperty(value = "银行业从业资格证")
	@Dict(dicCode = "ywbz")
	private Integer yhycyzgz;
	/**jsjczz*/
	@Excel(name = "计算机操作证", width = 15,dicCode = "ywbz",groupName = "岗位资质")
    @ApiModelProperty(value = "计算机操作证")
	@Dict(dicCode = "ywbz")
	private Integer jsjczz;
	/**gwzzDf*/
	@Excel(name = "计分", width = 15,groupName = "岗位资质")
	@ApiModelProperty(value = "计分")
	private Integer gwzzDf;

	/**pxksJg*/
	@Excel(name = "测试成绩", width = 15,groupName = "考试培训")
    @ApiModelProperty(value = "测试成绩")
	private Integer pxksJg;

	/**pxksRw*/
	@Excel(name = "平均分", width = 15,groupName = "考试培训")
    @ApiModelProperty(value = "平均分")
	private Integer pxksRw;
	/**pxksDf*/
	@Excel(name = "计分", width = 15,groupName = "考试培训")
	@ApiModelProperty(value = "计分")
	private Integer pxksDf;
	/**cknrpJg*/
	@Excel(name = "存款日平", width = 15,groupName = "存款管理")
	@ApiModelProperty(value = "存款日平")
	private Integer cknrpJg;
	/**cknrpRw*/
	@Excel(name = "平均存款日平", width = 15,groupName = "存款管理")
    @ApiModelProperty(value = "平均存款日平")
	private Integer cknrpRw;
	/**cknrpDf*/
	@Excel(name = "计分", width = 15,groupName = "存款管理")
	@ApiModelProperty(value = "计分")
	private Integer cknrpDf;
	/**dknrpJg*/
	@Excel(name = "贷款日平", width = 15,groupName = "贷款管理")
    @ApiModelProperty(value = "贷款日平")
	private Integer dknrpJg;
	/**dknrpRw*/
	@Excel(name = "平均贷款日平", width = 15,groupName = "贷款管理")
    @ApiModelProperty(value = "平均贷款日平")
	private Integer dknrpRw;
	/**dknrpDf*/
	@Excel(name = "计分", width = 15,groupName = "贷款管理")
	@ApiModelProperty(value = "计分")
	private Integer dknrpDf;
	/**dkhsJg*/
	@Excel(name = "贷款户数", width = 15,groupName = "贷款户数")
    @ApiModelProperty(value = "贷款户数")
	private Integer dkhsJg;
	/**dkhsRw*/
	@Excel(name = "平均贷款户数", width = 15,groupName = "贷款户数")
    @ApiModelProperty(value = "平均贷款户数")
	private Integer dkhsRw;
	/**dkhsDf*/
	@Excel(name = "计分", width = 15,groupName = "贷款户数")
	@ApiModelProperty(value = "计分")
	private Integer dkhsDf;
	/**bndkzbJg*/
	@Excel(name = "表内不良占比(%)", width = 15,groupName = "不良贷款管理")
    @ApiModelProperty(value = "表内不良占比(%)")
	private Integer bndkzbJg;
	/**bndkzbRw*/
	@Excel(name = "平均不良占比(%)", width = 15,groupName = "不良贷款管理")
	@ApiModelProperty(value = "平均不良占比(%)")
	private Integer bndkzbRw;
	/**bndkzbDf*/
	@Excel(name = "计分", width = 15,groupName = "不良贷款管理")
    @ApiModelProperty(value = "计分")
	private Integer bndkzbDf;
	/**bwbldkqsJg*/
	@Excel(name = "表外不良回收", width = 15,groupName = "表外不良收回")
	@ApiModelProperty(value = "表外不良回收")
	private java.math.BigDecimal bwbldkqsJg;
	/**bwbldkqszbJg*/
	@Excel(name = "不良收回占比（%）", width = 15,groupName = "表外不良收回")
	@ApiModelProperty(value = "不良收回占比（%）")
	private Integer bwbldkqszbJg;
	/**bwbldkqszbRw*/
	@Excel(name = "支行年度任务（%）", width = 15,groupName = "表外不良收回")
	@ApiModelProperty(value = "支行年度任务（%）")
	private Integer bwbldkqszbRw;
	/**bwbldkqszbDf*/
	@Excel(name = "计分", width = 15,groupName = "表外不良收回")
	@ApiModelProperty(value = "计分")
	private Integer bwbldkqszbDf;
	/**yqndqshlJg*/
	@Excel(name = "以前年度发放当年到期（%）", width = 15,groupName = "贷款到期收回率")
    @ApiModelProperty(value = "以前年度发放当年到期（%）")
	private Integer yqndqshlJg;
	/**yqndqshlDf*/
	@Excel(name = "计分", width = 15,groupName = "贷款到期收回率")
    @ApiModelProperty(value = "计分")
	private Integer yqndqshlDf;
	/**dndqshlJg*/
	@Excel(name = "当年发放当年到期（%）", width = 15,groupName = "贷款到期收回率")
    @ApiModelProperty(value = "当年发放当年到期（%）")
	private Integer dndqshlJg;
	/**dndqshlDf*/
	@Excel(name = "计分", width = 15,groupName = "贷款到期收回率")
    @ApiModelProperty(value = "计分")
	private Integer dndqshlDf;
	/**djkhysJg*/
	@Excel(name = "贷记卡管理数", width = 15,groupName = "贷记卡管理数")
	@ApiModelProperty(value = "贷记卡管理数")
	private Integer djkhysJg;
	/**djkhysRw*/
	@Excel(name = "平均管理数（%）", width = 15,groupName = "贷记卡管理数")
	@ApiModelProperty(value = "平均管理数（%）")
	private Integer djkhysRw;
	/**djkhysDf*/
	@Excel(name = "计分", width = 15,groupName = "贷记卡管理数")
	@ApiModelProperty(value = "计分")
	private Integer djkhysDf;
	/**djkxzblJg*/
	@Excel(name = "贷记卡不良", width = 15,groupName = "贷记卡管理数")
	@ApiModelProperty(value = "贷记卡不良")
	private Integer djkxzblJg;
	/**djkxzblDf*/
	@Excel(name = "计分", width = 15,groupName = "贷记卡管理数")
	@ApiModelProperty(value = "计分")
	private Integer djkxzblDf;

	/**sjyhJg*/
	@Excel(name = "手机银行", width = 15,groupName = "新业务管理")
    @ApiModelProperty(value = "手机银行")
	private Integer sjyhJg;
	/**sjyhRw*/
	@Excel(name = "满分考核数", width = 15,groupName = "新业务管理")
	@ApiModelProperty(value = "满分考核数")
	private Integer sjyhRw;
	/**sjyhDf*/
	@Excel(name = "计分", width = 15,groupName = "新业务管理")
    @ApiModelProperty(value = "计分")
	private Integer sjyhDf;
	/**etcJg*/
	@Excel(name = "ETC", width = 15,groupName = "新业务管理")
	@ApiModelProperty(value = "ETC")
	private Integer etcJg;
	/**etcRw*/
	@Excel(name = "满分考核数", width = 15,groupName = "新业务管理")
	@ApiModelProperty(value = "满分考核数")
	private Integer etcRw;
	/**etcDf*/
	@Excel(name = "计分", width = 15,groupName = "新业务管理")
	@ApiModelProperty(value = "计分")
	private Integer etcDf;
	/**djkJg*/
	@Excel(name = "贷记卡", width = 15,groupName = "新业务管理")
	@ApiModelProperty(value = "贷记卡")
	private Integer djkJg;
	/**djkRw*/
	@Excel(name = "满分考核数", width = 15,groupName = "新业务管理")
	@ApiModelProperty(value = "满分考核数")
	private Integer djkRw;
	/**djkDf*/
	@Excel(name = "计分", width = 15,groupName = "新业务管理")
	@ApiModelProperty(value = "计分")
	private Integer djkDf;
	/**收单商户*/
	@Excel(name = "收单商户", width = 15,groupName = "新业务管理")
	@ApiModelProperty(value = "收单商户")
	private Integer sdshJg;
	/**满分考核数*/
	@Excel(name = "满分考核数", width = 15,groupName = "新业务管理")
	@ApiModelProperty(value = "满分考核数")
	private Integer sdshRw;
	/**计分*/
	@Excel(name = "计分", width = 15,groupName = "新业务管理")
	@ApiModelProperty(value = "计分")
	private Integer sdshDf;
	/**zhzhpjJg*/
	@Excel(name = "评价结果", width = 15,dicCode = "pjjg",groupName = "支行评价")
	@ApiModelProperty(value = "评价结果")
	@Dict(dicCode = "pjjg")
	private Integer zhzhpjJg;
	/**zhzhpjDf*/
	@Excel(name = "计分", width = 15,groupName = "支行评价")
	@ApiModelProperty(value = "计分")
	private Integer zhzhpjDf;
	/**zbzhpjJg*/
	@Excel(name = "优秀得票率", width = 15,groupName = "总部评价")
	@ApiModelProperty(value = "优秀得票率")
	private Integer zbzhpjJg;
	/**zbzhpjkfJg*/
	@Excel(name = "扣分", width = 15,groupName = "总部评价")
	@ApiModelProperty(value = "扣分")
	private Integer zbzhpjkfJg;
	/**zbzhpjDf*/
	@Excel(name = "计分", width = 15,groupName = "总部评价")
	@ApiModelProperty(value = "计分")
	private Integer zbzhpjDf;
	/**jfxDf*/
	@Excel(name = "加分项", width = 15,groupName = "总部评价")
	@ApiModelProperty(value = "加分项")
	private Integer jfxDf;
	/**kfxDf*/
	@Excel(name = "扣分项", width = 15,groupName = "总部评价")
	@ApiModelProperty(value = "扣分项")
	private Integer kfxDf;
	/**pjdf*/
	@Excel(name = "总得分", width = 15,groupName = "总部评价")
	@ApiModelProperty(value = "总得分")
	private java.math.BigDecimal pjdf;
	/**ssdj*/
	@Excel(name = "评定等级", width = 15,dicCode = "csbh", dictTable = "Grade_cust_cssz", dicText = "csmc",ds = "eweb",groupName = "总部评价")
	@ApiModelProperty(value = "评定等级")
	@Dict(dicCode = "csbh", dictTable = "Grade_cust_cssz", dicText = "csmc",ds = "eweb")
//	@Dict(dicCode = "pjdj")
	private Integer ssdj;





	/**kdlqJg*/
	//@Excel(name = "口袋零钱", width = 15)
    @ApiModelProperty(value = "kdlqJg")
	private Integer kdlqJg;
	/**kdlqDf*/
	//@Excel(name = "计分", width = 15)
    @ApiModelProperty(value = "计分")
	private Integer kdlqDf;
	/**kdlqRw*/
	//@Excel(name = "满分考核数", width = 15)
    @ApiModelProperty(value = "满分考核数")
	private Integer kdlqRw;
	/**posJg*/
	//@Excel(name = "posJg", width = 15)
    @ApiModelProperty(value = "posJg")
	private Integer posJg;
	/**posDf*/
	//@Excel(name = "posDf", width = 15)
    @ApiModelProperty(value = "posDf")
	private Integer posDf;
	/**posRw*/
	//@Excel(name = "posRw", width = 15)
    @ApiModelProperty(value = "posRw")
	private Integer posRw;








}
