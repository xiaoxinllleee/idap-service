package org.cmms.modules.hr.zzgl.zzgwgl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 组织岗位管理
 * @Author: jeecg-boot
 * @Date:   2020-11-30
 * @Version: V1.0
 */
@Data
@TableName("HR_BAS_ORGAN_POST")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HR_BAS_ORGAN_POST对象", description="组织岗位管理")
public class HrBasOrganPost {
    
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	@Dict(dicCode = "gwbz", dictTable = "HR_BAS_POST", dicText = "gwmc")
	private Integer gwbz;

	@TableField(exist = false)
	@ApiModelProperty(value = "岗位名称")
	private String gwmc;

	/**是否启用（1：是， 2：否）*/
	@Excel(name = "是否启用（1：是， 2：否）", width = 15)
    @ApiModelProperty(value = "是否启用（1：是， 2：否）")
	private Integer sfqy;
}
