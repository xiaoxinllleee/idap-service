package org.cmms.modules.khlc.csgl.csxx.entity;

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
 * @Description: 参数信息
 * @Author: jeecg-boot
 * @Date:   2021-03-23
 * @Version: V1.0
 */
@Data
@TableName("PMA_F_PARAM_INFO")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_F_PARAM_INFO对象", description="参数信息")
public class PmaFParamInfo {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
	private String paramName;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
	private String remark;
	/**参数所属机构编号*/
	@Excel(name = "参数所属机构编号", width = 15)
    @ApiModelProperty(value = "参数所属机构编号")
	private String orgId;
	/**数据删除标志*/
	@Excel(name = "数据删除标志", width = 15)
    @ApiModelProperty(value = "数据删除标志")
	private String statFlag;
	/**参数类型编号*/
	@Excel(name = "参数类型编号", width = 15)
    @ApiModelProperty(value = "参数类型编号")
	private String dirId;
	/**参数值*/
	@Excel(name = "参数值", width = 15)
    @ApiModelProperty(value = "参数值")
	private Double paramValue;
	/**参数编号*/
	@Excel(name = "参数编号", width = 15)
    @ApiModelProperty(value = "参数编号")
	private String paramId;
	/**参数值下限*/
	@Excel(name = "参数值下限", width = 15)
    @ApiModelProperty(value = "参数值下限")
	private Double minLimit;
	/**参数值上限*/
	@Excel(name = "参数值上限", width = 15)
    @ApiModelProperty(value = "参数值上限")
	private Double maxLimit;
	/**生效范围(0-本机构1-辖内机构)*/
	@Excel(name = "生效范围(0-本机构 1-辖内机构)", width = 15)
    @ApiModelProperty(value = "生效范围(0-本机构 1-辖内机构)")
	private String area;
	/**业务条线*/
	@Excel(name = "业务条线", width = 15)
    @ApiModelProperty(value = "业务条线")
	private String bussSysNo;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**所属机构*/
	@Excel(name = "所属机构", width = 15)
	@ApiModelProperty(value = "所属机构")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**所属员工*/
	@Excel(name = "所属员工", width = 15)
	@ApiModelProperty(value = "所属员工")
	@Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
	private String yggh;
}
