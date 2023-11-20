package org.cmms.modules.tjfx.wgtjfx.bkbpymx.entity;

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
 * @Description: 背靠背评议明细
 * @Author: jeecg-boot
 * @Date:   2022-04-18
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_TJFX_BKBPYMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_TJFX_BKBPYMX对象", description="背靠背评议明细")
public class Bkbpymx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**sszh*/
	@Excel(name = "sszh", width = 15)
    @ApiModelProperty(value = "sszh")
	private String sszh;
	/**wgbh*/
	@Excel(name = "wgbh", width = 15)
    @ApiModelProperty(value = "wgbh")
	@Dict(dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
	/**hhbm*/
	@Excel(name = "hhbm", width = 15)
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**hzxm*/
	@Excel(name = "hzxm", width = 15)
    @ApiModelProperty(value = "hzxm")
	private String hzxm;
	/**hzzjhm*/
	@Excel(name = "hzzjhm", width = 15)
    @ApiModelProperty(value = "hzzjhm")
	private String hzzjhm;
	/**sxdx*/
	@Excel(name = "sxdx", width = 15)
    @ApiModelProperty(value = "sxdx")
	private String sxdx;
	/**sxdxzjh*/
	@Excel(name = "sxdxzjh", width = 15)
    @ApiModelProperty(value = "sxdxzjh")
	private String sxdxzjh;
	/**lx*/
	@Excel(name = "lx", width = 15)
    @ApiModelProperty(value = "lx")
	private String lx;
	@Dict(dicCode = "qxfkdj")
	private String fkdj;
	/**pyed*/
	@Excel(name = "pyed", width = 15)
    @ApiModelProperty(value = "pyed")
	private java.math.BigDecimal pyed;
	/**pyyjyed*/
	@Excel(name = "pyyjyed", width = 15)
    @ApiModelProperty(value = "pyyjyed")
	private Integer pyyjyed;
	/**pycs*/
	@Excel(name = "pycs", width = 15)
    @ApiModelProperty(value = "pycs")
	private Integer pycs;
	/**bysxqx*/
	@Excel(name = "bysxqx", width = 15)
    @ApiModelProperty(value = "bysxqx")
	private String bysxqx;
	/**sxed*/
	@Excel(name = "sxed", width = 15)
    @ApiModelProperty(value = "sxed")
	private java.math.BigDecimal sxed;
	/**dkje*/
	@Excel(name = "dkje", width = 15)
    @ApiModelProperty(value = "dkje")
	private java.math.BigDecimal dkje;
	/**dkye*/
	@Excel(name = "dkye", width = 15)
    @ApiModelProperty(value = "dkye")
	private java.math.BigDecimal dkye;
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
