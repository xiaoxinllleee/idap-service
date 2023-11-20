package org.cmms.modules.gr.grjxsj.entity;

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
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 员工绩效工资表
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_ygjxgz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_ygjxgz对象", description="员工绩效工资表")
public class TbTjfxYgjxgz {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
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
	private String yggh;
	/**工资合计*/
	@Excel(name = "工资合计", width = 15)
    @ApiModelProperty(value = "工资合计")
	private java.math.BigDecimal gzhj;
	/**全行同岗人均*/
	@Excel(name = "全行同岗人均", width = 15)
    @ApiModelProperty(value = "全行同岗人均")
	private java.math.BigDecimal qhtgrj;
	/**支行同岗人均*/
	@Excel(name = "支行同岗人均", width = 15)
    @ApiModelProperty(value = "支行同岗人均")
	private java.math.BigDecimal zhtgrj;
	/**全行人均绩效*/
	@Excel(name = "全行人均绩效", width = 15)
    @ApiModelProperty(value = "全行人均绩效")
	private java.math.BigDecimal qhrjjx;
	/**支行人均绩效*/
	@Excel(name = "支行人均绩效", width = 15)
    @ApiModelProperty(value = "支行人均绩效")
	private java.math.BigDecimal zhrjjx;
	/**当年月均绩效*/
	@Excel(name = "当年月均绩效", width = 15)
    @ApiModelProperty(value = "当年月均绩效")
	private java.math.BigDecimal dnyjjx;
	/**支行排名*/
	@Excel(name = "支行排名", width = 15)
    @ApiModelProperty(value = "支行排名")
	private Integer zhpm;
	/**全行排名*/
	@Excel(name = "全行排名", width = 15)
    @ApiModelProperty(value = "全行排名")
	private Integer qhpm;
	/**支行同岗排名*/
	@Excel(name = "支行同岗排名", width = 15)
    @ApiModelProperty(value = "支行同岗排名")
	private Integer zhtgpm;
	/**全行同岗排名*/
	@Excel(name = "全行同岗排名", width = 15)
    @ApiModelProperty(value = "全行同岗排名")
	private Integer qhtgpm;
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
