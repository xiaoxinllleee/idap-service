package org.cmms.modules.performance.ckgspz.ckzhgs.entity;

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
 * @Description: 存款账号归属规则配置
 * @Author: jeecg-boot
 * @Date:   2023-03-23
 * @Version: V1.0
 */
@Data
@TableName("khgxgl_ckzhgs_gzpz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgxgl_ckzhgs_gzpz对象", description="存款账号归属规则配置")
public class Ckzhgs {

	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**规则名称*/
	@Excel(name = "规则名称", width = 15)
    @ApiModelProperty(value = "规则名称")
	private String gzmc;
	/**规则SQL*/
	@Excel(name = "规则SQL", width = 15)
    @ApiModelProperty(value = "规则SQL")
	private String gzsql;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15,dicCode = "qybz")
    @ApiModelProperty(value = "是否启用")
	@Dict(dicCode = "qybz")
	private String sfqy;
	/**执行顺序*/
	@Excel(name = "执行顺序", width = 15)
    @ApiModelProperty(value = "执行顺序")
	private Integer zxsx;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
}
