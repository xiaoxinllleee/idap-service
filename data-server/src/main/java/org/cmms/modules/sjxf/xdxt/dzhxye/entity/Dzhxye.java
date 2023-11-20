package org.cmms.modules.sjxf.xdxt.dzhxye.entity;

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
 * @Description: 呆帐核销余额
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cms_bad_debt_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_bad_debt_info对象", description="呆帐核销余额")
public class Dzhxye {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**贷款账号(当前形态)*/
	@Excel(name = "贷款账号(当前形态)", width = 15)
    @ApiModelProperty(value = "贷款账号(当前形态)")
	private String acctNo;
	/**贷款账号(正常形态)*/
	@Excel(name = "贷款账号(正常形态)", width = 15)
    @ApiModelProperty(value = "贷款账号(正常形态)")
	private String accNo;
	/**表外账号*/
	@Excel(name = "表外账号", width = 15)
    @ApiModelProperty(value = "表外账号")
	private String bwzh;
	/**核销金额*/
	@Excel(name = "核销金额", width = 15)
    @ApiModelProperty(value = "核销金额")
	private java.math.BigDecimal offprincipal;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
	private java.math.BigDecimal balance;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private String badUserId;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15)
    @ApiModelProperty(value = "录入时间")
	private String insertDate;
	/**核销标志*/
	@Excel(name = "核销标志", width = 15)
    @ApiModelProperty(value = "核销标志")
	private String hxbz;
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
