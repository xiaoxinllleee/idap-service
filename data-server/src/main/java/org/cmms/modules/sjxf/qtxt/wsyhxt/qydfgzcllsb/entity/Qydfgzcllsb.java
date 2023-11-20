package org.cmms.modules.sjxf.qtxt.wsyhxt.qydfgzcllsb.entity;

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
 * @Description: 企业代发工资差旅流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_cb_wage_flow")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_cb_wage_flow对象", description="企业代发工资差旅流水表")
public class Qydfgzcllsb {
    
	/**开户机构号*/
	@Excel(name = "开户机构号", width = 15)
    @ApiModelProperty(value = "开户机构号")
	private String cifOpennode;
	/**批次号*/
	@Excel(name = "批次号", width = 15)
    @ApiModelProperty(value = "批次号")
	private String wflBatchno;
	/**企业客户号*/
	@Excel(name = "企业客户号", width = 15)
    @ApiModelProperty(value = "企业客户号")
	private String wflCstno;
	/**付款方账号*/
	@Excel(name = "付款方账号", width = 15)
    @ApiModelProperty(value = "付款方账号")
	private String wflPayacc;
	/**总笔数*/
	@Excel(name = "总笔数", width = 15)
    @ApiModelProperty(value = "总笔数")
	private Integer wflTotalnum;
	/**总金额*/
	@Excel(name = "总金额", width = 15)
    @ApiModelProperty(value = "总金额")
	private java.math.BigDecimal wflTotalamt;
	/**费用1手续费*/
	@Excel(name = "费用1手续费", width = 15)
    @ApiModelProperty(value = "费用1手续费")
	private java.math.BigDecimal wflFee1;
	/**费用2*/
	@Excel(name = "费用2", width = 15)
    @ApiModelProperty(value = "费用2")
	private java.math.BigDecimal wflFee2;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15)
    @ApiModelProperty(value = "录入时间")
	private String wflSubmittime;
	/**代发月份*/
	@Excel(name = "代发月份", width = 15)
    @ApiModelProperty(value = "代发月份")
	private String wflMonth;
	/**指令状态*/
	@Excel(name = "指令状态", width = 15)
    @ApiModelProperty(value = "指令状态")
	private String wflStt;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;
	/**dttime*/
	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;
}
