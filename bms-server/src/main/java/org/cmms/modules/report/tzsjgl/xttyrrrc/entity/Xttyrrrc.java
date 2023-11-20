package org.cmms.modules.report.tzsjgl.xttyrrrc.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 湘潭同业融入融出
 * @Author: jeecg-boot
 * @Date:   2022-05-07
 * @Version: V1.0
 */
@Data
@TableName("rep_tzgl_xt_tyrrrc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rep_tzgl_xt_tyrrrc对象", description="湘潭同业融入融出")
public class Xttyrrrc {
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**业务类别*/
	@Excel(name = "业务类别", width = 15)
    @ApiModelProperty(value = "业务类别")
	private String ywlx;
	/**交易日*/
	@Excel(name = "交易日", width = 15)
    @ApiModelProperty(value = "交易日")
	private String jyrq;
	/**质押券账面价值（元）*/
	@Excel(name = "质押券账面价值（元）", width = 15)
    @ApiModelProperty(value = "质押券账面价值（元）")
	private java.math.BigDecimal zmjz;
	/**交易金额（元）*/
	@Excel(name = "交易金额（元）", width = 15)
    @ApiModelProperty(value = "交易金额（元）")
	private java.math.BigDecimal jyje;
	/**到期日*/
	@Excel(name = "到期日", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "到期日")
	private java.util.Date dqr;
	/**期限*/
	@Excel(name = "期限（天）", width = 15)
    @ApiModelProperty(value = "期限（天）")
	private String qx;
	/**交易对手方*/
	@Excel(name = "交易对手方（必须按主机构报全称、分类）", width = 15)
    @ApiModelProperty(value = "交易对手方（必须按主机构报全称、分类）")
	private String jyds;
	/**交易对手类别*/
	@Excel(name = "交易对手类别", width = 15)
    @ApiModelProperty(value = "交易对手类别")
	private String jydslb;
	/**交易对手方金融机构代码*/
	@Excel(name = "交易对手方金融机构代码（非银类填写组织机构代码证）", width = 15)
    @ApiModelProperty(value = "交易对手方金融机构代码（非银类填写组织机构代码证）")
	private String jydsjrjgdm;
	/**回购利率*/
	@Excel(name = "回购利率（%）", width = 15)
    @ApiModelProperty(value = "回购利率（%）")
	private String hgll;
	/**质押国债/地方政府债 */
	@Excel(name = "质押国债/地方政府债（券面，万元）", width = 15)
    @ApiModelProperty(value = "质押国债/地方政府债（券面，万元）")
	private java.math.BigDecimal zygz;
	/**质押政策性金融债*/
	@Excel(name = "质押政策性金融债（券面，万元）", width = 15)
    @ApiModelProperty(value = "质押政策性金融债（券面，万元）")
	private java.math.BigDecimal zyzcxjrz;
	/**质押存单金融*/
	@Excel(name = "质押存单金融", width = 15)
    @ApiModelProperty(value = "质押存单金融")
	private java.math.BigDecimal zycdjr;
	/**质押铁道债金额*/
	@Excel(name = "质押铁道债金额", width = 15)
    @ApiModelProperty(value = "质押铁道债金额")
	private java.math.BigDecimal zytdz;
	/**质押信用债金额*/
	@Excel(name = "质押信用债金额（券面，万元）", width = 15)
    @ApiModelProperty(value = "质押信用债金额（券面，万元）")
	private java.math.BigDecimal zyxyz;
	/**质押券名称*/
	@Excel(name = "质押券名称", width = 15)
    @ApiModelProperty(value = "质押券名称")
	private String zyqmc;
	/**质押券面*/
	@Excel(name = "质押券面（万元）", width = 15)
    @ApiModelProperty(value = "质押券面（万元）")
	private java.math.BigDecimal zyqmje;

}
