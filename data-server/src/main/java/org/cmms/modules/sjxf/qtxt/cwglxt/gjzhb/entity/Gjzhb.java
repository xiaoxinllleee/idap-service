package org.cmms.modules.sjxf.qtxt.cwglxt.gjzhb.entity;

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
 * @Description: 股金账户表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_stockmr")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_stockmr对象", description="股金账户表")
public class Gjzhb {
    
	/**机构代号*/
	@Excel(name = "机构代号", width = 15)
    @ApiModelProperty(value = "机构代号")
	private String opnBrNo;
	/**股金账号*/
	@Excel(name = "股金账号", width = 15)
    @ApiModelProperty(value = "股金账号")
	private String stockno;
	/**股东编号*/
	@Excel(name = "股东编号", width = 15)
    @ApiModelProperty(value = "股东编号")
	private String clientNo;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String opnDate;
	/**销户日期*/
	@Excel(name = "销户日期", width = 15)
    @ApiModelProperty(value = "销户日期")
	private String clsDate;
	/**股金性质*/
	@Excel(name = "股金性质", width = 15)
    @ApiModelProperty(value = "股金性质")
	private String stocktype;
	/**账户状态*/
	@Excel(name = "账户状态", width = 15)
    @ApiModelProperty(value = "账户状态")
	private String state;
	/**挂失标志*/
	@Excel(name = "挂失标志", width = 15)
    @ApiModelProperty(value = "挂失标志")
	private String stppb;
	/**冻结标志*/
	@Excel(name = "冻结标志", width = 15)
    @ApiModelProperty(value = "冻结标志")
	private String actfzn;
	/**冻结股数*/
	@Excel(name = "冻结股数", width = 15)
    @ApiModelProperty(value = "冻结股数")
	private java.math.BigDecimal ctlamt;
	/**总股数*/
	@Excel(name = "总股数", width = 15)
    @ApiModelProperty(value = "总股数")
	private java.math.BigDecimal stacknum;
	/**股价*/
	@Excel(name = "股价", width = 15)
    @ApiModelProperty(value = "股价")
	private java.math.BigDecimal price;
	/**总金额*/
	@Excel(name = "总金额", width = 15)
    @ApiModelProperty(value = "总金额")
	private java.math.BigDecimal txamt;
	/**股权证号*/
	@Excel(name = "股权证号", width = 15)
    @ApiModelProperty(value = "股权证号")
	private String noteNo;
	/**密印标志*/
	@Excel(name = "密印标志", width = 15)
    @ApiModelProperty(value = "密印标志")
	private String pswdcd;
	/**密码*/
	@Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
	private String passwd;
	/**备注摘要信息*/
	@Excel(name = "备注摘要信息", width = 15)
    @ApiModelProperty(value = "备注摘要信息")
	private String remark;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String acId;
	/**入股方式*/
	@Excel(name = "入股方式", width = 15)
    @ApiModelProperty(value = "入股方式")
	private String stockMode;
	/**资产类型*/
	@Excel(name = "资产类型", width = 15)
    @ApiModelProperty(value = "资产类型")
	private String assetsType;
	/**入股资产编号*/
	@Excel(name = "入股资产编号", width = 15)
    @ApiModelProperty(value = "入股资产编号")
	private String assetsNo;
	/**可用股数*/
	@Excel(name = "可用股数", width = 15)
    @ApiModelProperty(value = "可用股数")
	private java.math.BigDecimal usramt;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**代持账号*/
	@Excel(name = "代持账号", width = 15)
    @ApiModelProperty(value = "代持账号")
	private String agentno;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
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
