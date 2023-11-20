package org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdsjxgl.entity;

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
 * @Description: 评定数据项管理
 * @Author: jeecg-boot
 * @Date:   2021-09-02
 * @Version: V1.0
 */
@Data
@TableName("GRADE_SJX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_SJX对象", description="评定数据项管理")
public class Pdsjxgl {

	/**数据项ID*/
	@Excel(name = "数据项ID", width = 15)
    @ApiModelProperty(value = "数据项ID")
	private String sjxid;
	/**数据项名称*/
	@Excel(name = "数据项名称", width = 15)
    @ApiModelProperty(value = "数据项名称")
	private String sjxmc;
	/**Q-季、YY-半年、YYYY年*/
	@Excel(name = "数据项维度", width = 15,dicCode = "rqwd")
    @ApiModelProperty(value = "数据项维度")
	@Dict(dicCode = "rqwd")
	private String sjxwd;
	/**数据项取值SQL*/
	@Excel(name = "数据项取值SQL", width = 15)
    @ApiModelProperty(value = "数据项取值SQL")
	private String sjxsql;
	/**数据来源(0 系统取数 1 人工录入)*/
	@Excel(name = "数据来源", width = 15,dicCode = "sjxsjly")
    @ApiModelProperty(value = "数据来源")
	@Dict(dicCode = "sjxsjly")
	private Integer sjly;
	/**执行顺序*/
	@Excel(name = "执行顺序", width = 15)
    @ApiModelProperty(value = "执行顺序")
	private Integer zxsx;
	/**lrsj*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**lrbz*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**lrr*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}
