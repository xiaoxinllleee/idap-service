package org.cmms.modules.tjfx.wgtjfx.wgywtj.entity;

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
 * @Description: 网格业务统计
 * @Author: jeecg-boot
 * @Date:   2022-03-25
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_tjfx_wgywtj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_tjfx_wgywtj对象", description="网格业务统计")
public class Wgywtj {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**总客户数*/
	@Excel(name = "总客户数", width = 15)
    @ApiModelProperty(value = "总客户数")
	private Integer zkhs;
	/**存量客户数*/
	@Excel(name = "存量客户数", width = 15)
    @ApiModelProperty(value = "存量客户数")
	private Integer clkhs;
	/**潜在客户数*/
	@Excel(name = "潜在客户数", width = 15)
    @ApiModelProperty(value = "潜在客户数")
	private Integer qzkhs;
	/**存款户数*/
	@Excel(name = "存款户数", width = 15)
    @ApiModelProperty(value = "存款户数")
	private Integer ckhs;
	/**存款客户数*/
	@Excel(name = "存款客户数", width = 15)
    @ApiModelProperty(value = "存款客户数")
	private Integer ckkhs;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款月日平*/
	@Excel(name = "存款月日平", width = 15)
    @ApiModelProperty(value = "存款月日平")
	private java.math.BigDecimal ckyrp;
	/**存款年日平*/
	@Excel(name = "存款年日平", width = 15)
    @ApiModelProperty(value = "存款年日平")
	private java.math.BigDecimal cknrp;
	/**贷款户数*/
	@Excel(name = "贷款户数", width = 15)
    @ApiModelProperty(value = "贷款户数")
	private Integer dkhs;
	/**贷款客户数*/
	@Excel(name = "贷款客户数", width = 15)
    @ApiModelProperty(value = "贷款客户数")
	private Integer dkkhs;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**不良贷款户数*/
	@Excel(name = "不良贷款户数", width = 15)
    @ApiModelProperty(value = "不良贷款户数")
	private Integer bldkhs;
	/**不良贷款客户数*/
	@Excel(name = "不良贷款客户数", width = 15)
    @ApiModelProperty(value = "不良贷款客户数")
	private Integer bldkkhs;
	/**不良贷款客户数_未结清*/
	@Excel(name = "不良贷款客户数_未结清", width = 15)
	@ApiModelProperty(value = "不良贷款客户数_未结清")
	private Integer bldkkhsWjq;
	/**不良贷款客户数_已结清*/
	@Excel(name = "不良贷款客户数_已结清", width = 15)
	@ApiModelProperty(value = "不良贷款客户数_已结清")
	private Integer bldkkhsYjq;
	/**不良贷款余额*/
	@Excel(name = "不良贷款余额", width = 15)
    @ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;
	/**表外不良贷款户数*/
	@Excel(name = "表外不良贷款户数", width = 15)
    @ApiModelProperty(value = "表外不良贷款户数")
	private Integer bwbldkhs;
	/**表外不良贷款客户数*/
	@Excel(name = "表外不良贷款客户数", width = 15)
    @ApiModelProperty(value = "表外不良贷款客户数")
	private Integer bwbldkkhs;
	/**表外不良贷款余额*/
	@Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
	private java.math.BigDecimal bwbldkye;
	/**手机银行客户数*/
	@Excel(name = "手机银行客户数", width = 15)
    @ApiModelProperty(value = "手机银行客户数")
	private Integer sjyhkhs;
	/**网上银行客户数*/
	@Excel(name = "网上银行客户数", width = 15)
    @ApiModelProperty(value = "网上银行客户数")
	private Integer wsyhkhs;
	/**etc客户数*/
	@Excel(name = "etc客户数", width = 15)
    @ApiModelProperty(value = "etc客户数")
	private Integer etckhs;
	/**信用卡客户数*/
	@Excel(name = "信用卡客户数", width = 15)
    @ApiModelProperty(value = "信用卡客户数")
	private Integer xykkhs;
	/**社保卡客户数*/
	@Excel(name = "社保卡客户数", width = 15)
    @ApiModelProperty(value = "社保卡客户数")
	private Integer sbkkhs;
	/**e支付客户数*/
	@Excel(name = "e支付客户数", width = 15)
    @ApiModelProperty(value = "e支付客户数")
	private Integer ezfkhs;
	/**福祥e站客户数*/
	@Excel(name = "福祥e站客户数", width = 15)
    @ApiModelProperty(value = "福祥e站客户数")
	private Integer fxezkhs;
	/**pos机客户数*/
	@Excel(name = "pos机客户数", width = 15)
    @ApiModelProperty(value = "pos机客户数")
	private Integer poskhs;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}
