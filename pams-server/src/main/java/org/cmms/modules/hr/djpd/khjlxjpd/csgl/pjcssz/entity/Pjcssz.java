package org.cmms.modules.hr.djpd.khjlxjpd.csgl.pjcssz.entity;

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
 * @Description: 评级参数设置
 * @Author: jeecg-boot
 * @Date:   2021-09-02
 * @Version: V1.0
 */
@Data
@TableName("GRADE_CUST_CSSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GRADE_CUST_CSSZ对象", description="评级参数设置")
public class Pjcssz {
    
	/**参数编号*/
	@Excel(name = "参数编号", width = 15)
    @ApiModelProperty(value = "参数编号")
	private String csbh;
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
	private String csmc;
	/**参数值起*/
	@Excel(name = "参数值起", width = 15)
    @ApiModelProperty(value = "参数值起")
	private java.math.BigDecimal cszq;
	/**参数值止*/
	@Excel(name = "参数值止", width = 15)
    @ApiModelProperty(value = "参数值止")
	private java.math.BigDecimal cszz;
	/**排名名次*/
	@Excel(name = "等级人数", width = 15)
    @ApiModelProperty(value = "等级人数")
	private java.math.BigDecimal pmmc;
	/**享受评级系数*/
	@Excel(name = "享受评级系数", width = 15)
    @ApiModelProperty(value = "享受评级系数")
	private java.math.BigDecimal sspjxs;
	/**贷款权限*/
	@Excel(name = "贷款权限", width = 15)
    @ApiModelProperty(value = "贷款权限")
	private String dkqx;
}
