package org.cmms.modules.xdgl.dksp.dkspkhzc.entity;

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
 * @Description: 贷款审批客户注册
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Data
@TableName("v_cams_dksp_khzc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_cams_dksp_khzc对象", description="贷款审批客户注册")
public class VDkspKhzc {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15, dicCode="ZZBZ",dictTable="V_HR_BAS_ORGANIZATION_CMMS",dicText="ZZJC")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode="ZZBZ",dictTable="V_HR_BAS_ORGANIZATION_CMMS",dicText="ZZJC")
	private String zzbz;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "lldj_khlx")
	private String khlx;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
	@ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**地址*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;
	/**信用等级*/
	@Excel(name = "信用等级", width = 15)
	@ApiModelProperty(value = "信用等级")
	@Dict(dicCode = "rate_xydj")
	private String xydj;
	/**借款用途*/
	@Excel(name = "借款用途", width = 15)
	@ApiModelProperty(value = "借款用途")
	private String jkyt;
	/**借款方式*/
	@Excel(name = "借款方式", width = 15)
	@ApiModelProperty(value = "借款方式")
	@Dict(dicCode = "dbfs")
	private String jkfs;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15)
	@ApiModelProperty(value = "贷款期限")
	private String dkqx;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款种类*/
	@Excel(name = "贷款种类", width = 15)
    @ApiModelProperty(value = "贷款种类")
	@Dict(dicCode = "dkzl")
	private String dkzl;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
	@ApiModelProperty(value = "授信额度")
	private String sxed;
	/**利率*/
	@Excel(name = "利率", width = 15)
	@ApiModelProperty(value = "利率")
	private String ll;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh", dicText = "ygxm", dictTable = "hr_bas_staff")
	private String yggh;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNumber;
	/**流程编号*/
	@Excel(name = "流程编号", width = 15)
    @ApiModelProperty(value = "流程编号")
	private String processId;
	/**结果状态*/
	@Excel(name = "结果状态", width = 15)
    @ApiModelProperty(value = "结果状态")
	@Dict(dicCode = "lczt")
	private String result;
	/**结果状态*/
	@Excel(name = "表单ID", width = 15)
	@ApiModelProperty(value = "表单ID")
	private String procInstId;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}
