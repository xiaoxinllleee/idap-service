package org.cmms.modules.tjfx.khjdwzd.entity;

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
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-07
 * @Version: V1.0
 */
@Data
@TableName("TJFX_JDWZDTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_JDWZDTJ对象", description="1")
public class Tjfx_jdwzdtj {

	/**组织名称*/
	@Excel(name = "组织名称", width = 15)
    @ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String zzmc;
	/**营销单元名称*/
	@Excel(name = "营销单元名称", width = 15)
    @ApiModelProperty(value = "营销单元名称")
	@Dict(dicCode="qybm",dictTable="yxdygl_czxxgl",dicText="village || organize")
	private String yxdymc;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**建档得分*/
	@Excel(name = "建档得分", width = 15)
    @ApiModelProperty(value = "建档得分")
	private java.math.BigDecimal jddf;
	/**建档完成率*/
	@Excel(name = "建档完成率", width = 15)
    @ApiModelProperty(value = "建档完成率")
	private java.math.BigDecimal jdwcl;
}
