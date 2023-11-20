package org.cmms.modules.jgywsj.dktjsj.entity;

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
 * @Description: 贷款统计数据
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_dktjsj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_dktjsj对象", description="贷款统计数据")
public class TbTjfxDktjsj {
    
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识（0：导入 1：录入 2：修改）", width = 15)
    @ApiModelProperty(value = "录入标识（0：导入 1：录入 2：修改）")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	private String zzbz;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String brNo;
	/**贷款注册客户数*/
	@Excel(name = "贷款注册客户数", width = 15)
    @ApiModelProperty(value = "贷款注册客户数")
	private Long dkzckhs;
	/**贷款有效客户数*/
	@Excel(name = "贷款有效客户数", width = 15)
    @ApiModelProperty(value = "贷款有效客户数")
	private Long dkyxkhs;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**表内不良贷款余额*/
	@Excel(name = "表内不良贷款余额", width = 15)
    @ApiModelProperty(value = "表内不良贷款余额")
	private java.math.BigDecimal bldkye;
	/**表内不良贷款占比*/
	@Excel(name = "表内不良贷款占比", width = 15)
    @ApiModelProperty(value = "表内不良贷款占比")
	private java.math.BigDecimal bldkzb;
	/**正常贷款余额比例*/
	@Excel(name = "正常贷款余额比例", width = 15)
    @ApiModelProperty(value = "正常贷款余额比例")
	private java.math.BigDecimal zcdkyebl;
	/**关注贷款余额比例*/
	@Excel(name = "关注贷款余额比例", width = 15)
    @ApiModelProperty(value = "关注贷款余额比例")
	private java.math.BigDecimal gzdkyebl;
	/**次级贷款余额比例*/
	@Excel(name = "次级贷款余额比例", width = 15)
    @ApiModelProperty(value = "次级贷款余额比例")
	private java.math.BigDecimal cjdkyebl;
	/**可疑贷款余额比例*/
	@Excel(name = "可疑贷款余额比例", width = 15)
    @ApiModelProperty(value = "可疑贷款余额比例")
	private java.math.BigDecimal kydkyebl;
	/**损失贷款余额比例*/
	@Excel(name = "损失贷款余额比例", width = 15)
    @ApiModelProperty(value = "损失贷款余额比例")
	private java.math.BigDecimal ssdkyebl;
	/**"YYYYMMDD 按天存储数据"*/
	@Excel(name = "YYYYMMDD 按天存储数据", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "YYYYMMDD 按天存储数据")
	private Date tjrq;
}
