package org.cmms.modules.sjxf.xdxt.ywmsb.entity;

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
 * @Description: 业务描述表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_business_classify")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_business_classify对象", description="业务描述表")
public class Ywmsb {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**个人征信业务种类*/
	@Excel(name = "个人征信业务种类", width = 15)
    @ApiModelProperty(value = "个人征信业务种类")
	private String pcKind1;
	/**企业征信业务种类*/
	@Excel(name = "企业征信业务种类", width = 15)
    @ApiModelProperty(value = "企业征信业务种类")
	private String ecKind1;
	/**是否需要评级*/
	@Excel(name = "是否需要评级", width = 15)
    @ApiModelProperty(value = "是否需要评级")
	private String isEvaluate;
	/**每年放贷限额*/
	@Excel(name = "每年放贷限额", width = 15)
    @ApiModelProperty(value = "每年放贷限额")
	private java.math.BigDecimal temp1;
	/**偿还本金比率（借新还旧）/首付比例（按揭贷款）*/
	@Excel(name = "偿还本金比率（借新还旧）/首付比例（按揭贷款）", width = 15)
    @ApiModelProperty(value = "偿还本金比率（借新还旧）/首付比例（按揭贷款）")
	private java.math.BigDecimal temp2;
	/**五级分类业务种类*/
	@Excel(name = "五级分类业务种类", width = 15)
    @ApiModelProperty(value = "五级分类业务种类")
	private String fiveClassKind;
	/**业务序号*/
	@Excel(name = "业务序号", width = 15)
    @ApiModelProperty(value = "业务序号")
	private String bicfCode;
	/**父交易代码*/
	@Excel(name = "父交易代码", width = 15)
    @ApiModelProperty(value = "父交易代码")
	private String bicfParentCode;
	/**业务代码*/
	@Excel(name = "业务代码", width = 15)
    @ApiModelProperty(value = "业务代码")
	private String businessCode;
	/**业务品种描述*/
	@Excel(name = "业务品种描述", width = 15)
    @ApiModelProperty(value = "业务品种描述")
	private String businessDesc;
	/**业务对应的工作流程*/
	@Excel(name = "业务对应的工作流程", width = 15)
    @ApiModelProperty(value = "业务对应的工作流程")
	private String businessFlow;
	/**业务品种名称*/
	@Excel(name = "业务品种名称", width = 15)
    @ApiModelProperty(value = "业务品种名称")
	private String businessName;
	/**排序顺序号*/
	@Excel(name = "排序顺序号", width = 15)
    @ApiModelProperty(value = "排序顺序号")
	private String businessOrderIndex;
	/**业务页面编号*/
	@Excel(name = "业务页面编号", width = 15)
    @ApiModelProperty(value = "业务页面编号")
	private String businessTempId;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String businessType;
	/**业务类型新增、展期、借新还旧、还旧借新、贷款重组、合同变更*/
	@Excel(name = "业务类型新增、展期、借新还旧、还旧借新、贷款重组、合同变更", width = 15)
    @ApiModelProperty(value = "业务类型新增、展期、借新还旧、还旧借新、贷款重组、合同变更")
	private String businessType1;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String businessType2;
	/**备用字段*/
	@Excel(name = "备用字段", width = 15)
    @ApiModelProperty(value = "备用字段")
	private String businessType3;
	/**质押方式*/
	@Excel(name = "质押方式", width = 15)
    @ApiModelProperty(value = "质押方式")
	private String impawnType;
	/**是否授信*/
	@Excel(name = "是否授信", width = 15)
    @ApiModelProperty(value = "是否授信")
	private String isCredit;
	/**暂不用*/
	@Excel(name = "暂不用", width = 15)
    @ApiModelProperty(value = "暂不用")
	private String isLen;
	/**抵押方式*/
	@Excel(name = "抵押方式", width = 15)
    @ApiModelProperty(value = "抵押方式")
	private String pledgeType;
	/**风险类型*/
	@Excel(name = "风险类型", width = 15)
    @ApiModelProperty(value = "风险类型")
	private String riskType;
	/**业务适用的角色对象*/
	@Excel(name = "业务适用的角色对象", width = 15)
    @ApiModelProperty(value = "业务适用的角色对象")
	private String roleId;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String inputDate;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**业务种类*/
	@Excel(name = "业务种类", width = 15)
    @ApiModelProperty(value = "业务种类")
	private String businessKind;
	/**利率浮动比例(下限）*/
	@Excel(name = "利率浮动比例(下限）", width = 15)
    @ApiModelProperty(value = "利率浮动比例(下限）")
	private java.math.BigDecimal temp3;
	/**检查产品代码标志(1:检查 2:不检查)*/
	@Excel(name = "检查产品代码标志(1:检查 2:不检查)", width = 15)
    @ApiModelProperty(value = "检查产品代码标志(1:检查 2:不检查)")
	private String checkProductFlag;
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
}
