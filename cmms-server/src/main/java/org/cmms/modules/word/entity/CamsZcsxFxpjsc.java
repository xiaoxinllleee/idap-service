package org.cmms.modules.word.entity;

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
 * @Description: 支行风险评价审查
 * @Author: jeecg-boot
 * @Date:   2020-08-27
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_FXPJSC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_FXPJSC对象", description="支行风险评价审查")
public class CamsZcsxFxpjsc {
    
	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**信贷资料基本要素的审查。1=借款人资料，2=担保人（物）资料，3=调查资料*/
	@Excel(name = "信贷资料基本要素的审查。1=借款人资料，2=担保人（物）资料，3=调查资料", width = 15)
    @ApiModelProperty(value = "信贷资料基本要素的审查。1=借款人资料，2=担保人（物）资料，3=调查资料")
	private String xdzljbys;
	/**主体资格的审查：
①借款人及担保人主体资格□；②法定代表人有关证明材料是否符合规定□；③借款人申请贷款是否履行了法律法规或公司章程规定的授权程序□；④借款人股东的实力及注册资金到位情况□；⑤借款人及担保人组织机构是否合理□；⑥产权关系是否明晰□；⑦借款人及担保人的法定代表人、股东、高管、主要部门负责人有无不良记录□。*/
	@Excel(name = "主体资格的审查：①借款人及担保人主体资格□；②法定代表人有关证明材料是否符合规定□；③借款人申请贷款是否履行了法律法规或公司章程规定的授权程序□；④借款人股东的实力及注册资金到位情况□；⑤借款人及担保人组织机构是否合理□；⑥产权关系是否明晰□；⑦借款人及担保人的法定代表人、股东、高管、主要部门负责人有无不良记录□。", width = 15)
    @ApiModelProperty(value = "主体资格的审查：①借款人及担保人主体资格□；②法定代表人有关证明材料是否符合规定□；③借款人申请贷款是否履行了法律法规或公司章程规定的授权程序□；④借款人股东的实力及注册资金到位情况□；⑤借款人及担保人组织机构是否合理□；⑥产权关系是否明晰□；⑦借款人及担保人的法定代表人、股东、高管、主要部门负责人有无不良记录□。")
	private String ztzg;
	/**政策规定的审查：①贷款用途、期限、方式、利率等是否符合国家宏观经济政策、产业行业政策、土地、环保和节能政策以及国家货币信贷政策等规定□；②是否符合监管限制性比例规定□。*/
	@Excel(name = "政策规定的审查：①贷款用途、期限、方式、利率等是否符合国家宏观经济政策、产业行业政策、土地、环保和节能政策以及国家货币信贷政策等规定□；②是否符合监管限制性比例规定□。", width = 15)
    @ApiModelProperty(value = "政策规定的审查：①贷款用途、期限、方式、利率等是否符合国家宏观经济政策、产业行业政策、土地、环保和节能政策以及国家货币信贷政策等规定□；②是否符合监管限制性比例规定□。")
	private String zcgd;
	/**信贷风险的审查：①审查测定的借款人信用等级□；②授信额度与营运资金需求□；③分析揭示借款人的生产风险□、④交易风险□、⑤财务风险□、⑥经营管理风险□、⑦市场风险□、⑧其他风险□；⑨提出风险防范措施□。*/
	@Excel(name = "信贷风险的审查：①审查测定的借款人信用等级□；②授信额度与营运资金需求□；③分析揭示借款人的生产风险□、④交易风险□、⑤财务风险□、⑥经营管理风险□、⑦市场风险□、⑧其他风险□；⑨提出风险防范措施□。", width = 15)
    @ApiModelProperty(value = "信贷风险的审查：①审查测定的借款人信用等级□；②授信额度与营运资金需求□；③分析揭示借款人的生产风险□、④交易风险□、⑤财务风险□、⑥经营管理风险□、⑦市场风险□、⑧其他风险□；⑨提出风险防范措施□。")
	private String xdfx;
	/**担保审查：①保证人的保证资格□；②保证能力□；③保证意愿□；④抵（质）押担保的合法有效性□；⑤抵（质）押物价值□；⑥变现能力□。*/
	@Excel(name = "担保审查：①保证人的保证资格□；②保证能力□；③保证意愿□；④抵（质）押担保的合法有效性□；⑤抵（质）押物价值□；⑥变现能力□。", width = 15)
    @ApiModelProperty(value = "担保审查：①保证人的保证资格□；②保证能力□；③保证意愿□；④抵（质）押担保的合法有效性□；⑤抵（质）押物价值□；⑥变现能力□。")
	private String db;
	/**审核人员认为需要补充的其他资料：*/
	@Excel(name = "审核人员认为需要补充的其他资料：", width = 15)
    @ApiModelProperty(value = "审核人员认为需要补充的其他资料：")
	private String qtzl;
	/**风险点文字说明：*/
	@Excel(name = "风险点文字说明：", width = 15)
    @ApiModelProperty(value = "风险点文字说明：")
	private String fxdwzsm;
	/**审查结论：①风险审查无瑕疵，同意提交审批□；②风险审查有瑕疵，同意提交审批□；③风险审查有瑕疵，不同意提交审批□。 */
	@Excel(name = "审查结论：①风险审查无瑕疵，同意提交审批□；②风险审查有瑕疵，同意提交审批□；③风险审查有瑕疵，不同意提交审批□。 ", width = 15)
    @ApiModelProperty(value = "审查结论：①风险审查无瑕疵，同意提交审批□；②风险审查有瑕疵，同意提交审批□；③风险审查有瑕疵，不同意提交审批□。 ")
	private String scjl;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**户名编码*/
	@Excel(name = "户名编码", width = 15)
	@ApiModelProperty(value = "户名编码")
	private String hhbm;

	/**户名编码*/
	@Excel(name = "提交审批状态", width = 15)
	@ApiModelProperty(value = "提交审批状态")
	private String tjspzt;



	/**审批id*/
	@Excel(name = "审批id", width = 15)
	@ApiModelProperty(value = "审批id")
	private String spid;

}
