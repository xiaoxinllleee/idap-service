package org.cmms.modules.report.sgtzgl.jtkhtz.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 集团客户台账
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_jtkhtz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_jtkhtz对象", description="集团客户台账")
public class SgtzglJtkhtz {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**支行*/
	@Excel(name = "支行", width = 15)
    @ApiModelProperty(value = "支行")
	private String zh;
	/**是否法人股东*/
	@Excel(name = "是否法人股东", width = 15)
    @ApiModelProperty(value = "是否法人股东")
	private String sffrgd;
	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
	private String qymc;
	/**合计数*/
	@Excel(name = "合计数", width = 15)
    @ApiModelProperty(value = "合计数")
	private java.math.BigDecimal hjs;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**用信合计数*/
	@Excel(name = "用信合计数", width = 15)
    @ApiModelProperty(value = "用信合计数")
	//@ExcelVerify(interHandler = true)
	private java.math.BigDecimal yxhjs;

}
