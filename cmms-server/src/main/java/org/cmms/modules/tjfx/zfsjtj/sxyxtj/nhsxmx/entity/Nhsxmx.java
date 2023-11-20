package org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhsxmx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 农户授信明细
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
@Data
@TableName("tjfx_sxmx_nh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_sxmx_nh对象", description="农户授信明细")
public class Nhsxmx {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
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
	/**网格编号*/
	@Excel(name = "网格名称", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "网格编号")
	@Dict(dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**授信开始日期*/
	@Excel(name = "授信开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "授信开始日期")
	private Date sxksrq;
	/**授信到期日期*/
	@Excel(name = "授信到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "授信到期日期")
	private Date sxdqrq;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
    @ApiModelProperty(value = "授信金额")
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
