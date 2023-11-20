package org.cmms.modules.khxxgl.khflgl.nhhzxzsp.entity;

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
 * @Description: 农户户主信息（新增待审批）
 * @Author: jeecg-boot
 * @Date:   2023-07-10
 * @Version: V1.0
 */
@Data
@TableName("khgl_nhhzxx_xz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgl_nhhzxx_xz对象", description="农户户主信息（新增待审批）")
public class NhhzxxXzSP {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**归属网格*/
	@Excel(name = "归属网格", width = 15, dicCode = "id", dictTable = "yxdygl_main", dicText = "wgmc")
    @ApiModelProperty(value = "归属网格")
	@Dict(dicCode = "id", dictTable = "yxdygl_main", dicText = "wgmc")
	private String sswg;
	/**归属支行*/
	@Excel(name = "归属支行", width = 15, dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "归属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
	@ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15, dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "主客户经理")
	@Dict(dicCode = "yggh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private String zkhjl;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "xtpdjg")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xtpdjg")
	private String khlx;
	/**户主姓名*/
	@Excel(name = "户主姓名", width = 15)
    @ApiModelProperty(value = "户主姓名")
	private String hzxm;
	/**户主证件号码*/
	@Excel(name = "户主证件号码", width = 15)
    @ApiModelProperty(value = "户主证件号码")
	private String hzzjhm;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
    @ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**审核状态(0 未审核 1 审核通过 2 驳回)*/
	@Excel(name = "审核状态", width = 15, dicCode = "approval_status")
    @ApiModelProperty(value = "审核状态(0 未审核 1 审核通过 2 驳回)")
	@Dict(dicCode = "approval_status")
	private String shzt;
	/**审核/驳回批注*/
	@Excel(name = "审核/驳回批注", width = 15)
    @ApiModelProperty(value = "审核/驳回批注")
	private String shpz;
	/**录入标识*/
	@Excel(name = "操作标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private String lrbz;
	/**录入人*/
	@Excel(name = "操作人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入操作员", width = 15)
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "录入操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "录入操作时间")
	private Date lrczsj;
}
