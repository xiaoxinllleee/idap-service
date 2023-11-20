package org.cmms.modules.sjxf.hxxt.zydmb.entity;

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
 * @Description: 摘要代码表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_mnmp")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_mnmp对象", description="摘要代码表")
public class Zydmb {
    
	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private String instNo;
	/**语言代码*/
	@Excel(name = "语言代码", width = 15)
    @ApiModelProperty(value = "语言代码")
	private String languageCode;
	/**主代码*/
	@Excel(name = "主代码", width = 15)
    @ApiModelProperty(value = "主代码")
	private String promoNo;
	/**助记*/
	@Excel(name = "助记", width = 15)
    @ApiModelProperty(value = "助记")
	private String mnemonic;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
	private String description;
	/**对账单备注*/
	@Excel(name = "对账单备注", width = 15)
    @ApiModelProperty(value = "对账单备注")
	private String statement;
	/**社会保险号标志*/
	@Excel(name = "社会保险号标志", width = 15)
    @ApiModelProperty(value = "社会保险号标志")
	private String socialSecInd;
	/**免收费标志*/
	@Excel(name = "免收费标志", width = 15)
    @ApiModelProperty(value = "免收费标志")
	private String exemptInd;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**总账代码覆盖指示*/
	@Excel(name = "总账代码覆盖指示", width = 15)
    @ApiModelProperty(value = "总账代码覆盖指示")
	private String glcOvrdInd;
	/**填充*/
	@Excel(name = "填充", width = 15)
    @ApiModelProperty(value = "填充")
	private String fil01;
	/**费用类型*/
	@Excel(name = "费用类型", width = 15)
    @ApiModelProperty(value = "费用类型")
	private String feeMask;
	/**长记符*/
	@Excel(name = "长记符", width = 15)
    @ApiModelProperty(value = "长记符")
	private String longMnemonic;
	/**费用金额*/
	@Excel(name = "费用金额", width = 15)
    @ApiModelProperty(value = "费用金额")
	private String feeAmount;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15)
    @ApiModelProperty(value = "开始日期")
	private String sDate;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
