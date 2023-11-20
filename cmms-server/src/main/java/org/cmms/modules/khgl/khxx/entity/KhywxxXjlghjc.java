package org.cmms.modules.khgl.khxx.entity;

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
 * @Description: 现金流归行检测
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
@Data
@TableName("KHYWXX_XJLGHJC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHYWXX_XJLGHJC对象", description="现金流归行检测")
public class KhywxxXjlghjc {

	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String identNo;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**现金流入*/
	@Excel(name = "现金流入", width = 15)
    @ApiModelProperty(value = "现金流入")
	private java.math.BigDecimal xjlr;
	/**现金流出*/
	@Excel(name = "现金流出", width = 15)
    @ApiModelProperty(value = "现金流出")
	private java.math.BigDecimal xjlc;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date ksrq;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date jsrq;
	/**总现金流*/
	@Excel(name = "总现金流", width = 15)
    @ApiModelProperty(value = "总现金流")
	private java.math.BigDecimal zxjl;
	/**净现金流*/
	@Excel(name = "净现金流", width = 15)
    @ApiModelProperty(value = "净现金流")
	private java.math.BigDecimal jxjl;
}
