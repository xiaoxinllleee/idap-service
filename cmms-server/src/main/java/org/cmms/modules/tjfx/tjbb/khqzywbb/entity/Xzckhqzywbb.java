package org.cmms.modules.tjfx.tjbb.khqzywbb.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户潜在业务报表(村社)
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHQZYW_XZC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHQZYW_XZC对象", description="客户潜在业务报表(村社)")
public class Xzckhqzywbb {
    
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date tjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**乡镇*/
	@Excel(name = "乡镇", width = 15)
    @ApiModelProperty(value = "乡镇")
	private String xz;
	/**村社*/
	@Excel(name = "村社", width = 15)
    @ApiModelProperty(value = "村社")
	private String xzc;
	/**总潜在客户数*/
	@Excel(name = "总潜在客户数", width = 15)
    @ApiModelProperty(value = "总潜在客户数")
	private Integer qzzhs;
	/**潜在办理贷款业务客户数*/
	@Excel(name = "潜在办理贷款业务客户数", width = 15)
    @ApiModelProperty(value = "潜在办理贷款业务客户数")
	private Integer qzdkhs;
	/**潜在办理存款业务客户数*/
	@Excel(name = "潜在办理存款业务客户数", width = 15)
    @ApiModelProperty(value = "潜在办理存款业务客户数")
	private Integer qzckhs;
	/**潜在办理网上银行客户数*/
	@Excel(name = "潜在办理网上银行客户数", width = 15)
    @ApiModelProperty(value = "潜在办理网上银行客户数")
	private Integer qzwsyhhs;
	/**潜在办理手机银行客户数*/
	@Excel(name = "潜在办理手机银行客户数", width = 15)
    @ApiModelProperty(value = "潜在办理手机银行客户数")
	private Integer qzsjyhhs;
	/**潜在办理ETC客户数*/
	@Excel(name = "潜在办理ETC客户数", width = 15)
    @ApiModelProperty(value = "潜在办理ETC客户数")
	private Integer qzetchs;
	/**潜在办理E支付客户数*/
	@Excel(name = "潜在办理E支付客户数", width = 15)
    @ApiModelProperty(value = "潜在办理E支付客户数")
	private Integer qzezfhs;
	/**潜在办理E缴费客户数*/
	@Excel(name = "潜在办理E缴费客户数", width = 15)
    @ApiModelProperty(value = "潜在办理E缴费客户数")
	private Integer qzejfhs;
	/**潜在办理POS机客户数*/
	@Excel(name = "潜在办理POS机客户数", width = 15)
    @ApiModelProperty(value = "潜在办理POS机客户数")
	private Integer qzposhs;
	/**潜在办理助农终端客户数*/
	@Excel(name = "潜在办理助农终端客户数", width = 15)
    @ApiModelProperty(value = "潜在办理助农终端客户数")
	private Integer qzznzdhs;
	/**潜在办理理财业务客户数*/
	@Excel(name = "潜在办理理财业务客户数", width = 15)
    @ApiModelProperty(value = "潜在办理理财业务客户数")
	private Integer qzlchs;
	/**潜在办理代理保险业务客户数*/
	@Excel(name = "潜在办理代理保险业务客户数", width = 15)
    @ApiModelProperty(value = "潜在办理代理保险业务客户数")
	private Integer qzbxhs;
}
