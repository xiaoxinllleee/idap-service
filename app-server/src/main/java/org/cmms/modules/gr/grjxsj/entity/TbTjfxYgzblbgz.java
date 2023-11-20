package org.cmms.modules.gr.grjxsj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 员工指标类别工资
 * @Author: jeecg-boot
 * @Date:   2021-05-27
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_ygzblbgz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_ygzblbgz对象", description="员工指标类别工资")
public class TbTjfxYgzblbgz {
    
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
	/**指标类别*/
	@Excel(name = "指标类别", width = 15)
    @ApiModelProperty(value = "指标类别")
	private Integer zblb;
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


	/**指标类别名称*/
	@Excel(name = "指标类别名称", width = 15)
	@ApiModelProperty(value = "指标类别名称")
	@Transient
	private transient String zblbmc;
}
