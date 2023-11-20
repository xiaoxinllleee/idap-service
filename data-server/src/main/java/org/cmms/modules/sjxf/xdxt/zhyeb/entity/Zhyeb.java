package org.cmms.modules.sjxf.xdxt.zhyeb.entity;

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
 * @Description: 置换余额表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_replace_loan_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_replace_loan_info对象", description="置换余额表")
public class Zhyeb {
    
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String dataFlag;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String acctNo;
	/**正常账号*/
	@Excel(name = "正常账号", width = 15)
    @ApiModelProperty(value = "正常账号")
	private String accNo;
	/**表外账号*/
	@Excel(name = "表外账号", width = 15)
    @ApiModelProperty(value = "表外账号")
	private String bwzh;
	/**置换本金金额*/
	@Excel(name = "置换本金金额", width = 15)
    @ApiModelProperty(value = "置换本金金额")
	private java.math.BigDecimal offprincipal;
	/**本金余额*/
	@Excel(name = "本金余额", width = 15)
    @ApiModelProperty(value = "本金余额")
	private java.math.BigDecimal balance;
	/**原贷账号*/
	@Excel(name = "原贷账号", width = 15)
    @ApiModelProperty(value = "原贷账号")
	private String ydzh;
	/**操作员号*/
	@Excel(name = "操作员号", width = 15)
    @ApiModelProperty(value = "操作员号")
	private Integer badUserId;
	/**更新日期*/
    @ApiModelProperty(value = "更新日期")
	private Date insertDate;
	/**置换标志*/
	@Excel(name = "置换标志", width = 15)
    @ApiModelProperty(value = "置换标志")
	private String hxbz;
	/**置换方式*/
	@Excel(name = "置换方式", width = 15)
    @ApiModelProperty(value = "置换方式")
	private String replaceType;
	/**置换利息*/
	@Excel(name = "置换利息", width = 15)
    @ApiModelProperty(value = "置换利息")
	private java.math.BigDecimal offinterest;
	/**利息余额*/
	@Excel(name = "利息余额", width = 15)
    @ApiModelProperty(value = "利息余额")
	private java.math.BigDecimal intBalance;
	/**新贷款账号*/
	@Excel(name = "新贷款账号", width = 15)
    @ApiModelProperty(value = "新贷款账号")
	private String acctNew;
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
