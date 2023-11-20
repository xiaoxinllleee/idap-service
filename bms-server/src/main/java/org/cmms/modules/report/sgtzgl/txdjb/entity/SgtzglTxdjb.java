package org.cmms.modules.report.sgtzgl.txdjb.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贴现登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_SGTZ_TXDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_TXDJB对象", description="贴现登记簿")
public class SgtzglTxdjb {

	/**主键ID*/
//	@TableId(type = IdType.ASSIGN_ID)
//    @ApiModelProperty(value = "主键ID")
//	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgmc;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String zh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**票据号码*/
	@Excel(name = "票据号码", width = 15)
    @ApiModelProperty(value = "票据号码")
	private String pjhm;
	/**票面金额*/
	@Excel(name = "票据金额", width = 15)
    @ApiModelProperty(value = "票据金额")
	private java.math.BigDecimal pjje;
	/**贴现金额*/
	@Excel(name = "贴现金额", width = 15)
    @ApiModelProperty(value = "贴现金额")
	private java.math.BigDecimal txje;
	/**贴现余额*/
	@Excel(name = "贴现余额", width = 15)
    @ApiModelProperty(value = "贴现余额")
	private java.math.BigDecimal txye;
	/**贴现日期*/
	@Excel(name = "贴现日期", width = 15)
    @ApiModelProperty(value = "贴现日期")
	private String txrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
	@ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**票据状态*/
	@Excel(name = "票据状态", width = 15)
    @ApiModelProperty(value = "票据状态")
	//@ExcelVerify(interHandler = true)
	private String pjzt;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15)
	@ApiModelProperty(value = "企业规模")
	private String qygm;
	/**投向*/
	@Excel(name = "投向", width = 15)
	@ApiModelProperty(value = "投向")
	private String tx;
	/**投向1*/
	@Excel(name = "投向1", width = 15)
	@ApiModelProperty(value = "投向1")
	private String tx1;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
	@ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**贴现利率*/
	@Excel(name = "贴现利率", width = 15)
	@ApiModelProperty(value = "贴现利率")
	private java.math.BigDecimal txll;
	/**实际金额*/
	@Excel(name = "实际金额", width = 15)
	@ApiModelProperty(value = "实际金额")
	private java.math.BigDecimal sjje;
	/**贴现利息*/
	@Excel(name = "贴现利息", width = 15)
	@ApiModelProperty(value = "贴现利息")
	private java.math.BigDecimal txlx;
	/**年化收益*/
	@Excel(name = "年化收益", width = 15)
	@ApiModelProperty(value = "年化收益")
	private java.math.BigDecimal nhsy;
	/**分摊后金额*/
	@Excel(name = "分摊后金额", width = 15)
	@ApiModelProperty(value = "分摊后金额")
	private java.math.BigDecimal fthje;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;

}
