package org.cmms.modules.tjbb.ywltj.sbkcx.entity;

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
 * @Description: 社保卡查询
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@Data
@TableName("TJBB_YWLTJ_SBKCX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_YWLTJ_SBKCX对象", description="社保卡查询")
public class Sbkcx {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private String bankNo;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String cardNo;
	/**卡产品号*/
	@Excel(name = "卡产品号", width = 15)
    @ApiModelProperty(value = "卡产品号")
	private String cardProduct;
	/**卡BIN代码*/
	@Excel(name = "卡BIN代码", width = 15)
    @ApiModelProperty(value = "卡BIN代码")
	private String cardBinCode;
	/**客户号*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String customerNo;
	/**卡名1*/
	@Excel(name = "卡名1", width = 15)
    @ApiModelProperty(value = "卡名1")
	@TableField(value ="CARD_NAME_1")
	private String cardName1;
	/**卡名2*/
	@Excel(name = "卡名2", width = 15)
    @ApiModelProperty(value = "卡名2")
	@TableField(value ="CARD_NAME_2")
	private String cardName2;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String telphone;
	/**卡创建日期*/
	@Excel(name = "卡创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "卡创建日期")
	private Date cardCreateDate;
	/**卡状态*/
	@Excel(name = "卡状态", width = 15)
    @ApiModelProperty(value = "卡状态")
	private String cardStatus;
	/**发卡机构*/
	@Excel(name = "发卡机构", width = 15)
    @ApiModelProperty(value = "发卡机构")
	private String issueBranch;
	/**发卡所在分行*/
	@Excel(name = "发卡所在分行", width = 15)
    @ApiModelProperty(value = "发卡所在分行")
	private String issueRegion;
	/**PIN初始化标志*/
	@Excel(name = "PIN初始化标志", width = 15)
    @ApiModelProperty(value = "PIN初始化标志")
	private String pinInitFlag;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
