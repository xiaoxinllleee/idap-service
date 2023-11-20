package org.cmms.modules.xdgl.grdkgl.entity;

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
 * @Description: 等级申请信息
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Data
@TableName("Rate_djsqxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Rate_djsqxx对象", description="等级申请信息")
public class RateDjsqxx {

	/**定价年份*/
	@Excel(name = "定价年份", width = 15, format = "yyyy")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "定价年份")
	private Date djnf;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**法人代表*/
	@Excel(name = "法人代表", width = 15)
    @ApiModelProperty(value = "法人代表")
	private String frdb;
	/**上年授信额度*/
	@Excel(name = "上年授信额度", width = 15)
    @ApiModelProperty(value = "上年授信额度")
	private java.math.BigDecimal snsxed;
	/**上年贷款利率上浮幅度*/
	@Excel(name = "上年贷款利率上浮幅度", width = 15)
    @ApiModelProperty(value = "上年贷款利率上浮幅度")
	private java.math.BigDecimal sndkllsffd;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15)
    @ApiModelProperty(value = "贷款期限")
	private Integer dkqx;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private Integer khlx;
	/**是否便民卡*/
	@Excel(name = "是否便民卡", width = 15)
    @ApiModelProperty(value = "是否便民卡")
	private Integer sfbmk;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**综合授信额度*/
	@Excel(name = "综合授信额度", width = 15)
    @ApiModelProperty(value = "综合授信额度")
	private java.math.BigDecimal zhsxed;
	/**其中：贷款授信+承兑敞口*/
	@Excel(name = "其中：贷款授信+承兑敞口", width = 15)
    @ApiModelProperty(value = "其中：贷款授信+承兑敞口")
	private java.math.BigDecimal cdck;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**是否保证保险贷款*/
	@Excel(name = "是否保证保险贷款", width = 15)
    @ApiModelProperty(value = "是否保证保险贷款")
	private Integer sfbzbxdk;
	/**还款方式(1.其他；2.等额本金；3.等额本息)*/
	@Excel(name = "还款方式(1.其他；2.等额本金；3.等额本息)", width = 15)
    @ApiModelProperty(value = "还款方式(1.其他；2.等额本金；3.等额本息)")
	private Integer hkfs;
	/**上年贷款基点(加/减)BP*/
	@Excel(name = "上年贷款基点(加/减)BP", width = 15)
    @ApiModelProperty(value = "上年贷款基点(加/减)BP")
	private java.math.BigDecimal sndkjdbp;
}
