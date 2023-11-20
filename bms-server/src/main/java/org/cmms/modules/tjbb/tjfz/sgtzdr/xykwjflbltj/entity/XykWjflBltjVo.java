package org.cmms.modules.tjbb.tjfz.sgtzdr.xykwjflbltj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 手工台账：信用卡五级分类及不良统计
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
@Data
public class XykWjflBltjVo {

	/**数据日期*/
	//@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	//@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    //@ApiModelProperty(value = "数据日期")
	//private Date dataDate;
	/**机构代码*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构代码")
	@ExcelVerify(notNull = true)
	private String jgbm;
	/**机构名称*/
	@Excel(name = "机构名", width = 15)
    @ApiModelProperty(value = "机构名称")
	@ExcelVerify(notNull = false)
	private String jgmc;
	/**卡类型(1 标卡/2 非标卡)*/
	//@Excel(name = "卡类型(1 标卡/2 非标卡)", width = 15)
    @ApiModelProperty(value = "卡类型(1 标卡/2 非标卡)")
	private String cardType;
	/**透支本金合计*/
	@Excel(name = "透支本金合计", width = 15)
    @ApiModelProperty(value = "透支本金合计")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal tzbjhj;
	/**正常*/
	@Excel(name = "正常", width = 15)
    @ApiModelProperty(value = "正常")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal zc;
	/**关注*/
	@Excel(name = "关注", width = 15)
    @ApiModelProperty(value = "关注")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal gz;
	/**次级*/
	@Excel(name = "次级", width = 15)
    @ApiModelProperty(value = "次级")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal cj;
	/**可疑*/
	@Excel(name = "可疑", width = 15)
    @ApiModelProperty(value = "可疑")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal ky;
	/**损失*/
	@Excel(name = "损失", width = 15)
    @ApiModelProperty(value = "损失")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal ss;
	/**不良本金合计*/
	@Excel(name = "不良本金合计", width = 15)
    @ApiModelProperty(value = "不良本金合计")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal blbjhj;
	/**不良率*/
	@Excel(name = "不良率", width = 15)
    @ApiModelProperty(value = "不良率")
	@ExcelVerify(notNull = false)
	private java.math.BigDecimal bll;
	/**录入标识(0 导入 1 录入 2 修改)*/
//	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	private Integer lrbz;
	/**录入人*/
//	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
//	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
