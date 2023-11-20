package org.cmms.modules.pad.pyxx.entity;

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
 * @Description: 背靠背附属信息
 * @Author: jeecg-boot
 * @Date:   2020-10-20
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_NHBKBPYFSXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_NHBKBPYFSXX对象", description="背靠背附属信息")
public class Nhbkbpyfsxx {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**hmcId*/
	@Excel(name = "hmcId", width = 15)
    @ApiModelProperty(value = "hmcId")
	private String hmcId;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**评议员姓名*/
	@Excel(name = "评议员姓名", width = 15)
    @ApiModelProperty(value = "评议员姓名")
	private String pyyxm;
	/**评议员证件号码*/
	@Excel(name = "评议员证件号码", width = 15)
    @ApiModelProperty(value = "评议员证件号码")
	private String pyyzjhm;
	/**评议时间*/
	@Excel(name = "评议时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评议时间")
	private Date pysj;
	/**房屋价值情况*/
	@Excel(name = "房屋价值情况", width = 15)
    @ApiModelProperty(value = "房屋价值情况")
	private java.math.BigDecimal fcjz;
	/**大宗耐用消费品情况*/
	@Excel(name = "大宗耐用消费品情况", width = 15)
    @ApiModelProperty(value = "大宗耐用消费品情况")
	private java.math.BigDecimal dznyxfpqk;
	/**农机具情况*/
	@Excel(name = "农机具情况", width = 15)
    @ApiModelProperty(value = "农机具情况")
	private java.math.BigDecimal njjqk;
	/**交通运输工具情况*/
	@Excel(name = "交通运输工具情况", width = 15)
    @ApiModelProperty(value = "交通运输工具情况")
	private java.math.BigDecimal jtysgjqk;
	/**家庭纯收入情况*/
	@Excel(name = "家庭纯收入情况", width = 15)
    @ApiModelProperty(value = "家庭纯收入情况")
	private java.math.BigDecimal jtcsrqk;
}
