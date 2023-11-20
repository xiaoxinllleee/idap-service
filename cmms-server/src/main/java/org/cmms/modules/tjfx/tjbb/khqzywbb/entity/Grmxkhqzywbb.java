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
 * @Description: 客户潜在业务报表(明细)
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHQZYW_GRMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHQZYW_GRMX对象", description="客户潜在业务报表(明细)")
public class Grmxkhqzywbb {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date tjrq;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
	private String ssyxdy;
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
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否户主")
	@Dict(dicCode = "sfbz")
	private String sfhz;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String zkhjl;
	/**客户潜在业务*/
	@Excel(name = "客户潜在业务", width = 15 , dicCode = "sfbz")
    @ApiModelProperty(value = "客户潜在业务")
	@Dict(dicCode = "sfbz")
	private String kcqzyw;
	/**是否潜在办理贷款*/
	@Excel(name = "是否潜在办理贷款", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否潜在办理贷款")
	@Dict(dicCode = "sfbz")
	private Integer sfqzdk;
	/**是否潜在办理存款*/
	@Excel(name = "是否潜在办理存款", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否潜在办理存款")
	@Dict(dicCode = "sfbz")
	private Integer sfqzck;
	/**是否潜在办理网上银行*/
	@Excel(name = "是否潜在办理网上银行", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否潜在办理网上银行")
	@Dict(dicCode = "sfbz")
	private Integer sfqzwsyh;
	/**是否潜在办理手机银行*/
	@Excel(name = "是否潜在办理手机银行", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否潜在办理手机银行")
	@Dict(dicCode = "sfbz")
	private Integer sfqzsjyh;
	/**是否潜在办理ETC*/
	@Excel(name = "是否潜在办理ETC", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否潜在办理ETC")
	@Dict(dicCode = "sfbz")
	private Integer sfqzetc;
	/**是否潜在办理E支付*/
	@Excel(name = "是否潜在办理E支付", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否潜在办理E支付")
	@Dict(dicCode = "sfbz")
	private Integer sfqzezf;
	/**是否潜在办理E缴费*/
	@Excel(name = "是否潜在办理E缴费", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否潜在办理E缴费")
	@Dict(dicCode = "sfbz")
	private Integer sfqzejf;
	/**是否潜在办理POS机*/
	@Excel(name = "是否潜在办理POS机", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否潜在办理POS机")
	@Dict(dicCode = "sfbz")
	private Integer sfqzpos;
	/**是否潜在办理助农终端*/
	@Excel(name = "是否潜在办理助农终端", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否潜在办理助农终端")
	@Dict(dicCode = "sfbz")
	private Integer sfqzznzd;
	/**是否潜在办理理财业务*/
	@Excel(name = "是否潜在办理理财业务", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否潜在办理理财业务")
	@Dict(dicCode = "sfbz")
	private Integer sfqzlc;
	/**是否潜在办理保险业务*/
	@Excel(name = "是否潜在办理保险业务", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否潜在办理保险业务")
	@Dict(dicCode = "sfbz")
	private Integer sfqzbx;

}
