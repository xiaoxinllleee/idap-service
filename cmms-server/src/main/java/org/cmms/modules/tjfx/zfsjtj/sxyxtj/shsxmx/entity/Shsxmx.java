package org.cmms.modules.tjfx.zfsjtj.sxyxtj.shsxmx.entity;

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
 * @Description: 商户授信明细
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
@Data
@TableName("tjfx_sxmx_sh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_sxmx_sh对象", description="商户授信明细")
public class Shsxmx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**tjrq*/
	@Excel(name = "tjrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "tjrq")
	private Date tjrq;
	/**shid*/
	@Excel(name = "shid", width = 15)
    @ApiModelProperty(value = "shid")
	private String shid;
	/**shmc*/
	@Excel(name = "shmc", width = 15)
    @ApiModelProperty(value = "shmc")
	private String shmc;
	/**tyshxydm*/
	@Excel(name = "tyshxydm", width = 15)
    @ApiModelProperty(value = "tyshxydm")
	private String tyshxydm;
	/**fddbr*/
	@Excel(name = "fddbr", width = 15)
    @ApiModelProperty(value = "fddbr")
	private String fddbr;
	/**frzjhm*/
	@Excel(name = "frzjhm", width = 15)
    @ApiModelProperty(value = "frzjhm")
	private String frzjhm;
	/**wgbh*/
	@Excel(name = "网格名称", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "wgbh")
	@Dict(dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
	/**jgdm*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "jgdm")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**sxksrq*/
	@Excel(name = "sxksrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "sxksrq")
	private Date sxksrq;
	/**sxdqrq*/
	@Excel(name = "sxdqrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "sxdqrq")
	private Date sxdqrq;
	/**sxje*/
	@Excel(name = "sxje", width = 15)
    @ApiModelProperty(value = "sxje")
	private java.math.BigDecimal sxje;
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
	/**贷款种类*/
	@Excel(name = "贷款种类", width = 15)
    @ApiModelProperty(value = "贷款种类")
	private String dkpz;
	/**新老客户标志*/
	@Excel(name = "新老客户标志", width = 15)
    @ApiModelProperty(value = "新老客户标志")
	private String xlkhbz;
	/**是否惠农快贷*/
	@Excel(name = "是否惠农快贷", width = 15)
    @ApiModelProperty(value = "是否惠农快贷")
	private String sfhnkd;
}
