package org.cmms.modules.ygjx.entity;

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
 * @Description: 专项考核
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
@Data
@TableName("erp_zxkh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_zxkh对象", description="专项考核")
public class ErpZxkh {

	/**统计季度*/
	@Excel(name = "统计季度", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计季度")
	private Date tjjd;
	/**网点*/
	@Excel(name = "网点", width = 15)
    @ApiModelProperty(value = "网点")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String wd;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**手机银行*/
	@Excel(name = "手机银行", width = 15)
    @ApiModelProperty(value = "手机银行")
	private java.math.BigDecimal sjyh;
	/**ETC*/
	@Excel(name = "ETC", width = 15)
    @ApiModelProperty(value = "ETC")
	private java.math.BigDecimal etc;
	/**信用卡*/
	@Excel(name = "信用卡", width = 15)
    @ApiModelProperty(value = "信用卡")
	private java.math.BigDecimal xyk;
	/**庸易付*/
	@Excel(name = "庸易付", width = 15)
    @ApiModelProperty(value = "庸易付")
	private java.math.BigDecimal yyf;
	/**社会保障卡*/
	@Excel(name = "社会保障卡", width = 15)
    @ApiModelProperty(value = "社会保障卡")
	private java.math.BigDecimal shbzk;
	/**合计金额*/
	@Excel(name = "合计金额", width = 15)
    @ApiModelProperty(value = "合计金额")
	private java.math.BigDecimal hjje;
	/**奖励总计*/
	@Excel(name = "奖励总计", width = 15)
    @ApiModelProperty(value = "奖励总计")
	private java.math.BigDecimal jlzj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private String lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**专项考核合计*/
	@Excel(name = "专项考核合计", width = 15)
    @ApiModelProperty(value = "专项考核合计")
	private java.math.BigDecimal zxkhhj;
}
