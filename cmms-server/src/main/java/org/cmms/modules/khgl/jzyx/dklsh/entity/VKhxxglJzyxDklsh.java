package org.cmms.modules.khgl.jzyx.dklsh.entity;

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
 * @Description: 贷款流失户
 * @Author: jeecg-boot
 * @Date:   2023-03-25
 * @Version: V1.0
 */
@Data
@TableName("V_KHXXGL_JZYX_DKLSH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_KHXXGL_JZYX_DKLSH对象", description="贷款流失户")
public class VKhxxglJzyxDklsh {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**原支行*/
	@Excel(name = "原支行", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "原支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String ysszh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String hth;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal htje;
	/**合同起始日期*/
	@Excel(name = "合同起始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同起始日期")
	private Date htqsrq;
	/**合同到期日期*/
	@Excel(name = "合同到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同到期日期")
	private Date htdqrq;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "签约日期")
	private Date qyrq;
	/**原担保方式*/
	@Excel(name = "原担保方式", width = 15)
    @ApiModelProperty(value = "原担保方式")
	private String ydbfs;
	/**借款原因*/
	@Excel(name = "借款原因", width = 15)
    @ApiModelProperty(value = "借款原因")
	private String jkyy;

	/**原客户经理*/
	@Excel(name = "原客户经理", width = 15,dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "原客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String ykhjl;


	/**系统评定结果(1:灰名单;2:白名单;3:黑名单)*/
	@Excel(name = "客户类型", width = 15, dicCode = "xtpdjg")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xtpdjg")
	private String khlx;

	/**是否走访*/
	@Excel(name = "是否走访", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否走访")
	@Dict(dicCode = "sfbz")
	private String sfzf;
	/**是否有效走访*/
	@Excel(name = "是否有效走访", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "是否有效走访")
	@Dict(dicCode = "sfbz")
	private String sfyxzf;
	/**走访人员*/
	@Excel(name = "走访人员", width = 15)
    @ApiModelProperty(value = "走访人员")
	private String zfry;

	/**户籍所在网格*/
	@Excel(name = "户籍所在网格", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	@ApiModelProperty(value = "户籍所在网格")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String hjszwg;


	/**户籍所属支行*/
	@Excel(name = "户籍所属支行", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "户籍所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String hjsszh;

	/**不予授信情形*/
	@Excel(name = "不予授信情形", width = 15, dicCode = "py_bysxqx_dx")
	@ApiModelProperty(value = "不予授信情形")
	@Dict(dicCode = "py_bysxqx_dx")
	private String bysxqx;
	/**建议授信额度*/
	@Excel(name = "建议授信额度", width = 15)
    @ApiModelProperty(value = "建议授信额度")
	private java.math.BigDecimal jysxed;

	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

}
