package org.cmms.modules.qtsjdrjk.fxezf.entity;

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
 * @Description: 福祥E支付
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Data
@TableName("SHYWXX_FXEZH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SHYWXX_FXEZH对象", description="福祥E支付")
public class ShywxxFxezh {

	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "唯一标识")
	private String id;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	@ExcelVerify(notNull = true)
	private String shmc;
	/**商户类型*/
	@Excel(name = "商户类型", width = 15)
    @ApiModelProperty(value = "商户类型")
	private String shlx;
	/**法人代表姓名*/
	@Excel(name = "法人代表姓名", width = 15)
    @ApiModelProperty(value = "法人代表姓名")
	@ExcelVerify(notNull = true)
	private String frdbxx;
	/**法人代表证件号*/
	@Excel(name = "法人代表证件号", width = 15)
    @ApiModelProperty(value = "法人代表证件号")
	@ExcelVerify(notNull = true)
	private String drzjhm;
	/**商户联系电话*/
	@Excel(name = "商户联系电话", width = 15)
    @ApiModelProperty(value = "商户联系电话")
	private String shlxdh;
	/**营业地区*/
	@Excel(name = "营业地区", width = 15)
    @ApiModelProperty(value = "营业地区")
	private String yydq;
	/**商户入网状态*/
	@Excel(name = "商户入网状态", width = 15)
    @ApiModelProperty(value = "商户入网状态")
	private String ssrwzt;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String jyzt;
	/**渠道*/
	@Excel(name = "渠道", width = 15)
    @ApiModelProperty(value = "渠道")
	private String qd;
	/**商户所属支行*/
	@Excel(name = "商户所属支行", width = 15)
    @ApiModelProperty(value = "商户所属支行")
	private String shsszh;
	/**客户经理姓名*/
	@Excel(name = "客户经理姓名", width = 15)
	@ApiModelProperty(value = "客户经理姓名")
	private String khjlxm;
	/**录入人*/
	@ExcelVerify(interHandler = true)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@ApiModelProperty(value = "录入标志")
	private String lrbz;
}
