package org.cmms.modules.hr.zzgl.gwxxgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

/**
 * @Description: 岗位信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
@Data
@TableName("HR_BAS_POST")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HR_BAS_POST对象", description="岗位信息管理")
public class HrBasPost {
    
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	@TableId(type = IdType.ASSIGN_ID)
	@ExcelVerify(notNull = true, interHandler = true)
	private Integer gwbz;
	/**岗位名称*/
	@Excel(name = "岗位名称", width = 15)
    @ApiModelProperty(value = "岗位名称")
	@ExcelVerify(notNull = true)
	private String gwmc;
	/**岗位性质（1：内勤 2：外勤）*/
	@Excel(name = "岗位性质", width = 15, dicCode = "gwxz")
	@ApiModelProperty(value = "岗位性质")
	@Dict(dicCode = "gwxz")
	private Integer gwxz;
	/**是否启用（1：是， 2：否）*/
	@Excel(name = "是否启用", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否启用")
	@Dict(dicCode = "sfbz")
	private Integer sfqy;
}
