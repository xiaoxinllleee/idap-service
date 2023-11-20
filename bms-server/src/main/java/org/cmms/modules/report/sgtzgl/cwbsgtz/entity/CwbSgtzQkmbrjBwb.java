package org.cmms.modules.report.sgtzgl.cwbsgtz.entity;

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
 * @Description: 财务部-全科目日均表
 * @Author: jeecg-boot
 * @Date:   2023-06-13
 * @Version: V1.0
 */
@Data
@TableName("cwb_sgtz_qkmbrj_bwb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cwb_sgtz_qkmbrj_bwb对象", description="财务部-全科目日均表")
public class CwbSgtzQkmbrjBwb {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private java.lang.String sjrq;
	/**项目代号*/
	@Excel(name = "项目代号", width = 15)
    @ApiModelProperty(value = "项目代号")
	private java.lang.String xmdh;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private java.lang.String xmmc;
	/**期末借方年日均余额*/
	@Excel(name = "期末借方年日均余额", width = 15)
    @ApiModelProperty(value = "期末借方年日均余额")
	private java.math.BigDecimal qmjfnrjye;
	/**期末贷方年日均余额*/
	@Excel(name = "期末贷方年日均余额", width = 15)
    @ApiModelProperty(value = "期末贷方年日均余额")
	private java.math.BigDecimal qmdfnrjye;
	/**项目代号1*/
	@Excel(name = "项目代号1", width = 15)
    @ApiModelProperty(value = "项目代号1")
	private java.lang.String xmdh1;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
}
