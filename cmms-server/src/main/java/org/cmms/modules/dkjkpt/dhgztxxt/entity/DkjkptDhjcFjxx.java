package org.cmms.modules.dkjkpt.dhgztxxt.entity;

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
 * @Description: 贷后检查附件信息_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-09-07
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_DHJCFJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_DHJCFJXX对象", description="贷后检查附件信息_湘潭")
public class DkjkptDhjcFjxx {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**附件类型*/
	@Excel(name = "附件类型", width = 15)
    @ApiModelProperty(value = "附件类型")
	private String fjlx;
	/**文件路径*/
	@Excel(name = "文件路径", width = 15)
    @ApiModelProperty(value = "文件路径")
	private String wjlj;
	/**文件映射路径*/
	@Excel(name = "文件映射路径", width = 15)
    @ApiModelProperty(value = "文件映射路径")
	private String fwlj;
	/**文件大小*/
	@Excel(name = "文件大小", width = 15)
    @ApiModelProperty(value = "文件大小")
	private java.math.BigDecimal wjdx;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**附件年份*/
	@Excel(name = "附件年份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "附件年份")
	private Date fjnf;
	/**1：第一季度报告，2：第二季度报表，3：第三季度报告，4：第四季度报表，5：上半年报表，6下半年报告 7:年度报告，8首发跟踪*/
	@Excel(name = "1：第一季度报告，2：第二季度报表，3：第三季度报告，4：第四季度报表，5：上半年报表，6下半年报告 7:年度报告，8首发跟踪", width = 15)
    @ApiModelProperty(value = "1：第一季度报告，2：第二季度报表，3：第三季度报告，4：第四季度报表，5：上半年报表，6下半年报告 7:年度报告，8首发跟踪")
	@Dict(dicCode="id",dictTable = "DKJKPT_DHJCBGJDCS",dicText = "name",ds = "eweb")
	private String fjsjjd;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
}
