package org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.entity;

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
 * @Description: 客户经理等级评定汇总表
 * @Author: jeecg-boot
 * @Date:   2023-02-10
 * @Version: V1.0
 */
@Data
@TableName("Grade_cust_hz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Grade_cust_hz对象", description="客户经理等级评定汇总表")
public class KhjldjpdHz {

	/**评定周期*/
	@Excel(name = "评定周期", width = 15)
    @ApiModelProperty(value = "评定周期")
	private String pdzq;
	/**评定日期*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
	private Date pdrq;
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
	/**评定得分*/
	@Excel(name = "评定得分", width = 15)
    @ApiModelProperty(value = "评定得分")
	private java.math.BigDecimal pjdf;
	/**所属等级*/
	@Excel(name = "所属等级", width = 15)
    @ApiModelProperty(value = "所属等级")
	private Integer ssdj;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private Integer lrbz;
	/**lrr*/
	@Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
	private String lrr;
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**业务机构性质*/
	@Excel(name = "业务机构性质", width = 15)
    @ApiModelProperty(value = "业务机构性质")
	private Integer ywjgxz;
	/**所在区域*/
	@Excel(name = "所在区域", width = 15)
    @ApiModelProperty(value = "所在区域")
	private Integer szqy;
}
