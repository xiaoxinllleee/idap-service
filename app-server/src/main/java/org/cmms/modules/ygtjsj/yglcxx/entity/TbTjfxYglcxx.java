package org.cmms.modules.ygtjsj.yglcxx.entity;

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
 * @Description: 员工揽储信息
 * @Author: jeecg-boot
 * @Date:   2021-05-15
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_yglcxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_yglcxx对象", description="员工揽储信息")
public class TbTjfxYglcxx {
    
	/**揽储日均*/
	@Excel(name = "揽储日均", width = 15)
    @ApiModelProperty(value = "揽储日均")
	private java.math.BigDecimal lcrj;
	/**年初揽储日均*/
	@Excel(name = "年初揽储日均", width = 15)
    @ApiModelProperty(value = "年初揽储日均")
	private java.math.BigDecimal nclcrj;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**揽储余额*/
	@Excel(name = "揽储余额", width = 15)
    @ApiModelProperty(value = "揽储余额")
	private java.math.BigDecimal lcye;
	/**年初揽储余额*/
	@Excel(name = "年初揽储余额", width = 15)
    @ApiModelProperty(value = "年初揽储余额")
	private java.math.BigDecimal nclcye;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}
