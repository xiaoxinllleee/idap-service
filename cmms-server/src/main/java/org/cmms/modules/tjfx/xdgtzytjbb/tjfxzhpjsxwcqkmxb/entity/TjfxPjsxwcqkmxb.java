package org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity;

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
 * @Description: 评级授信完成情况明表
 * @Author: jeecg-boot
 * @Date:   2020-02-17
 * @Version: V1.0
 */
@Data
@TableName("TJFX_PJSXWCQKMXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_PJSXWCQKMXB对象", description="评级授信完成情况明表")
public class TjfxPjsxwcqkmxb {
    

	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date ksrq;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date jsrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
    @ApiModelProperty(value = "行政村")
	private String xzc;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")

	private String zkhjl;
	/**附件数量*/
	@Excel(name = "附件数量", width = 15)
    @ApiModelProperty(value = "附件数量")
	private Long fjsl;
	/**农户最终授信额度*/
	@Excel(name = "农户最终授信额度", width = 15)
    @ApiModelProperty(value = "农户最终授信额度")
	private String nhzzsxed;
	/**系统测算评定等级*/
	@Excel(name = "系统测算评定等级", width = 15)
    @ApiModelProperty(value = "系统测算评定等级")
	private String xtcspddj;
	/**用信金额*/
	@Excel(name = "用信金额", width = 15)
    @ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;
	/**班子成员*/
	@Excel(name = "班子成员", width = 15)
    @ApiModelProperty(value = "班子成员")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String bzcy;

	/**区分标识(1:支行评级授信   2: 示范点评级授信)*/
	@Excel(name = "区分标识", width = 15)
	@ApiModelProperty(value = "区分标识")
	private String qfbs;
}
