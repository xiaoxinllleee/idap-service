package org.cmms.modules.ywgl.ywl.ywlmxcx.entity;

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
 * @Description: 业务量明细查询
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
@Data
@TableName("ERP_ASSESS_YWLMX_YG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_ASSESS_YWLMX_YG对象", description="业务量明细查询")
public class Ywlmxcx {

	/**交易码名称*/
	@Excel(name = "交易码名称", width = 15)
    @ApiModelProperty(value = "交易码名称")
	@Dict(dicCode = "dmid", dictTable = "SYS_BAS_DMXX", dicText = "dmmc",ds="cdkyw")
	private String jymmc;
	/**ywjgdm*/
	@Excel(name = "ywjgdm", width = 15)
    @ApiModelProperty(value = "ywjgdm")
	private String ywjgdm;
	/**分配数据ID*/
	@Excel(name = "分配数据ID", width = 15)
    @ApiModelProperty(value = "分配数据ID")
	private Long fpid;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**岗位标志*/
	@Excel(name = "岗位标志", width = 15)
    @ApiModelProperty(value = "岗位标志")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String gyh;
	/**入岗类型*/
	@Excel(name = "入岗类型", width = 15)
    @ApiModelProperty(value = "入岗类型")
	private Integer rglx;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private String jym;
	/**交易笔数*/
	@Excel(name = "交易笔数", width = 15)
    @ApiModelProperty(value = "交易笔数")
	private java.math.BigDecimal jybs;
	/**现金交易金额*/
	@Excel(name = "现金交易金额", width = 15)
    @ApiModelProperty(value = "现金交易金额")
	private java.math.BigDecimal xjjyje;
	/**折算后交易笔数*/
	@Excel(name = "折算后交易笔数", width = 15)
    @ApiModelProperty(value = "折算后交易笔数")
	private java.math.BigDecimal zshjybs;
	/**0不需分配 1原始分配 2机构分配*/
	@Excel(name = "0不需分配 1原始分配 2机构分配", width = 15)
    @ApiModelProperty(value = "0不需分配 1原始分配 2机构分配")
	private Integer fpbz;
	/**需分配交易笔数*/
	@Excel(name = "需分配交易笔数", width = 15)
    @ApiModelProperty(value = "需分配交易笔数")
	private java.math.BigDecimal xfpjybs;
	/**需分配现金交易金额*/
	@Excel(name = "需分配现金交易金额", width = 15)
    @ApiModelProperty(value = "需分配现金交易金额")
	private java.math.BigDecimal xfpxjjyje;
	/**实际交易笔数*/
	@Excel(name = "实际交易笔数", width = 15)
    @ApiModelProperty(value = "实际交易笔数")
	private java.math.BigDecimal sjjybs;
	/**实际现金交易金额*/
	@Excel(name = "实际现金交易金额", width = 15)
    @ApiModelProperty(value = "实际现金交易金额")
	private java.math.BigDecimal sjxjjyje;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**0不需要分配 1待分配 2已分配*/
	@Excel(name = "0不需要分配 1待分配 2已分配", width = 15)
    @ApiModelProperty(value = "0不需要分配 1待分配 2已分配")
	private Integer fpzt;
	/**分配时间*/
	@Excel(name = "分配时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "分配时间")
	private Date fpsj;
	/**分配操作员*/
	@Excel(name = "分配操作员", width = 15)
    @ApiModelProperty(value = "分配操作员")
	private String fpczy;
}
