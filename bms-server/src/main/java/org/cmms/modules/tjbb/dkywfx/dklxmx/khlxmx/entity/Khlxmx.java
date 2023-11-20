package org.cmms.modules.tjbb.dkywfx.dklxmx.khlxmx.entity;

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
 * @Description: 客户利息明细
 * @Author: jeecg-boot
 * @Date:   2021-08-21
 * @Version: V1.0
 */
@Data
@TableName("TJBB_DKYW_KHLXMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_DKYW_KHLXMX对象", description="客户利息明细")
public class Khlxmx {
    

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
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户类型(01-个体工商户/02-农户/03-微型企业/04-小型企业)*/
	@Excel(name = "客户类型", width = 15,dicCode = "dklxmx_khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dklxmx_khlx")
	private String khlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**收息日*/
	@Excel(name = "收息日", width = 15)
    @ApiModelProperty(value = "收息日")
	private String sxr;
	/**利息*/
	@Excel(name = "利息", width = 15)
    @ApiModelProperty(value = "利息")
	private java.math.BigDecimal lx;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
    @ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15)
    @ApiModelProperty(value = "借款日期")
	private String jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**贷款期限(天)*/
	@Excel(name = "贷款期限(天)", width = 15)
    @ApiModelProperty(value = "贷款期限(天)")
	private Integer dkqx;
	/**贷款总期数*/
	@Excel(name = "贷款总期数", width = 15)
    @ApiModelProperty(value = "贷款总期数")
	private Integer dkzqs;
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private java.math.BigDecimal lv;
	/**批准利率*/
	@Excel(name = "批准利率", width = 15)
    @ApiModelProperty(value = "批准利率")
	private java.math.BigDecimal pzlv;
	/**基准利率*/
	@Excel(name = "基准利率", width = 15)
    @ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal jzll;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String cpmc;
	/**科目号*/
	@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private String kmh;
	/**科目名称*/
	@Excel(name = "科目名称", width = 15)
	@ApiModelProperty(value = "科目名称")
	private String kmmc;
	/**客户地址*/
	@Excel(name = "客户地址", width = 15)
    @ApiModelProperty(value = "客户地址")
	private String khdz;
	/**客户电话*/
	@Excel(name = "客户电话", width = 15)
    @ApiModelProperty(value = "客户电话")
	private String khdh;
	/**客户类型1*/
	@Excel(name = "客户类型1", width = 15,dicCode = "dkjkpt_khlx1")
    @ApiModelProperty(value = "客户类型1")
	@Dict(dicCode = "dkjkpt_khlx1")
	@TableField(value = "KHLX_1")
	private String khlx1;
	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15,dicCode = "dkjkpt_khlx2")
    @ApiModelProperty(value = "客户类型2")
	@Dict(dicCode = "dkjkpt_khlx2")
	@TableField(value = "KHLX_2")
	private String khlx2;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15,dicCode = "dbfs1")
    @ApiModelProperty(value = "担保方式")
	@Dict(dicCode = "dbfs1")
	private String dbfs;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15,dicCode = "qygm")
    @ApiModelProperty(value = "企业规模")
	@Dict(dicCode = "qygm")
	private String qygm;
	/**实际用途*/
	@Excel(name = "实际用途", width = 15)
    @ApiModelProperty(value = "实际用途")
	private String sjyt;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15,dicCode = "dmid", dictTable = "SYS_BAS_DMXX", dicText = "dmmc",ds = "eweb")
    @ApiModelProperty(value = "贷款投向")
	@Dict(dicCode = "dmid", dictTable = "SYS_BAS_DMXX", dicText = "dmmc",ds = "eweb")
	private String dktx;
	/**贷款投向1*/
	@Excel(name = "贷款投向1", width = 15,dicCode = "dmid", dictTable = "SYS_BAS_DMXX", dicText = "dmmc",ds = "eweb")
    @ApiModelProperty(value = "贷款投向1")
	@Dict(dicCode = "dmid", dictTable = "SYS_BAS_DMXX", dicText = "dmmc",ds = "eweb")
	@TableField(value = "DKTX_1")
	private String dktx1;
	/**贷款投向2*/
	@Excel(name = "贷款投向2", width = 15,dicCode = "dmid", dictTable = "SYS_BAS_DMXX", dicText = "dmmc",ds = "eweb")
    @ApiModelProperty(value = "贷款投向2")
	@Dict(dicCode = "dmid", dictTable = "SYS_BAS_DMXX", dicText = "dmmc",ds = "eweb")
	@TableField(value = "DKTX_2")
	private String dktx2;
	/**贷款投向3*/
	@Excel(name = "贷款投向3", width = 15,dicCode = "dmid", dictTable = "SYS_BAS_DMXX", dicText = "dmmc",ds = "eweb")
    @ApiModelProperty(value = "贷款投向3")
	@Dict(dicCode = "dmid", dictTable = "SYS_BAS_DMXX", dicText = "dmmc",ds = "eweb")
	@TableField(value = "DKTX_3")
	private String dktx3;
	/**贷款投向4*/
	@Excel(name = "贷款投向4", width = 15,dicCode = "id", dictTable = "FPDK_DKTXDZB_4", dicText = "name",ds = "eweb")
    @ApiModelProperty(value = "贷款投向4")
	@Dict(dicCode = "id", dictTable = "FPDK_DKTXDZB_4", dicText = "name",ds = "eweb")
	@TableField(value = "DKTX_4")
	private String dktx4;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
