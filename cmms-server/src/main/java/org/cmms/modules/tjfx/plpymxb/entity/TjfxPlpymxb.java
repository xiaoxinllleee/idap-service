package org.cmms.modules.tjfx.plpymxb.entity;

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
 * @Description: 批量评议明细表
 * @Author: jeecg-boot
 * @Date:   2023-06-28
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_TJFX_PLPYMXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_TJFX_PLPYMXB对象", description="批量评议明细表")
public class TjfxPlpymxb {

	/**评议时间*/
	@Excel(name = "评议时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评议时间")
	private Date pysj;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**村组编码*/
	@Excel(name = "村组名称", width = 15,dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "村组名称")
	@Dict(dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String czbm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**评议得分*/
	@Excel(name = "评议得分", width = 15)
    @ApiModelProperty(value = "评议得分")
	private String pddf;
	/**评定等级*/
	@Excel(name = "评定等级", width = 15)
    @ApiModelProperty(value = "评定等级")
	@Dict(dicCode = "plpypddj")
	private String pddj;
	/**建议授信额度*/
	@Excel(name = "建议授信额度", width = 15)
    @ApiModelProperty(value = "建议授信额度")
	private java.math.BigDecimal jysxed;
	/**评议轮数*/
	@Excel(name = "评议轮数", width = 15)
    @ApiModelProperty(value = "评议轮数")
	private Integer pyls;
	/**评议员姓名*/
	@Excel(name = "评议员姓名", width = 15)
    @ApiModelProperty(value = "评议员姓名")
	private String pyyxm;
	/**评议员证件号码*/
	@Excel(name = "评议员证件号码", width = 15)
    @ApiModelProperty(value = "评议员证件号码")
	private String pyyzjhm;
	/**录入员工*/
	@Excel(name = "录入员工", width = 15)
    @ApiModelProperty(value = "录入员工")
	private String lryg;
}
