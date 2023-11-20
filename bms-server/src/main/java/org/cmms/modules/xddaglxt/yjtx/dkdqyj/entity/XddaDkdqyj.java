package org.cmms.modules.xddaglxt.yjtx.dkdqyj.entity;

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
 * @Description: 贷款到期预警
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Data
@TableName("V_XDDAGL_DKDQTX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_XDDAGL_DKDQTX对象", description="贷款到期预警")
public class XddaDkdqyj {
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
	@ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dkjkpt_khlx")
	private String khlx;
	/**住址*/
	@Excel(name = "住址", width = 15)
	@ApiModelProperty(value = "住址")
	private String dz;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
	@ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "借款日期")
	private Date jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15)
	@ApiModelProperty(value = "贷款期限")
	@Dict(dicCode = "dkqx")
	private Integer dkqx;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
	@ApiModelProperty(value = "剩余天数")
	private Integer syts;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
	@ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
	@ApiModelProperty(value = "主客户经理")
	private String khjlbz;
	/**贷款形态*/
	@Excel(name = "贷款形态", width = 15)
	@ApiModelProperty(value = "贷款形态")
	@Dict(dicCode = "wjflbz")
	private String dkxt;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种", width = 15)
	@ApiModelProperty(value = "信贷贷款品种")
	@Dict(dicCode = "dkzl")
	private String xddkpz;
	/**主要责任人*/
	@Excel(name = "主要责任人", width = 15)
	@ApiModelProperty(value = "主要责任人")
	private String zyzrr;
	/**次要责任人*/
	@Excel(name = "次要责任人", width = 15)
	@ApiModelProperty(value = "次要责任人")
	private String cyzrr;
	/**清收责任人*/
	@Excel(name = "清收责任人", width = 15)
	@ApiModelProperty(value = "清收责任人")
	private String qszrr;



	/**khjlyggh*/
    @ApiModelProperty(value = "khjlyggh")
	private String khjlyggh;
	/**xwqy*/
    @ApiModelProperty(value = "xwqy")
	private String xwqy;
	/**dhhm*/
    @ApiModelProperty(value = "dhhm")
	private String dhhm;
	/**到期类型*/
    @ApiModelProperty(value = "到期类型")
	@Dict(dicCode = "dqlx")
	private Integer dqlx;
	/**jxr*/
    @ApiModelProperty(value = "jxr")
	private Integer jxr;
	/**cpmc*/
    @ApiModelProperty(value = "cpmc")
	private String cpmc;
	/**qxts*/
    @ApiModelProperty(value = "qxts")
	private Integer qxts;
	/**bnyjlx*/
    @ApiModelProperty(value = "bnyjlx")
	private java.math.BigDecimal bnyjlx;
	/**bnyslx*/
    @ApiModelProperty(value = "bnyslx")
	private java.math.BigDecimal bnyslx;
	/**bwyjlx*/
    @ApiModelProperty(value = "bwyjlx")
	private java.math.BigDecimal bwyjlx;
	/**bwyslx*/
    @ApiModelProperty(value = "bwyslx")
	private java.math.BigDecimal bwyslx;
	/**bnwqxh*/
    @ApiModelProperty(value = "bnwqxh")
	private java.math.BigDecimal bnwqxh;
	/**dkll*/
    @ApiModelProperty(value = "dkll")
	private java.math.BigDecimal dkll;
	/**dbfs*/
    @ApiModelProperty(value = "dbfs")
	private String dbfs;
	/**khsshylx*/
    @ApiModelProperty(value = "khsshylx")
	private String khsshylx;
	/**dktx*/
    @ApiModelProperty(value = "dktx")
	private String dktx;
	/**dqqkjc*/
    @ApiModelProperty(value = "dqqkjc")
	private String dqqkjc;
	/**blxcyy*/
    @ApiModelProperty(value = "blxcyy")
	private String blxcyy;
	/**zrjd*/
    @ApiModelProperty(value = "zrjd")
	private String zrjd;
	/**qsczcs*/
    @ApiModelProperty(value = "qsczcs")
	private String qsczcs;
	/**qsczsx*/
    @ApiModelProperty(value = "qsczsx")
	private String qsczsx;
	/**dkzrr*/
    @ApiModelProperty(value = "dkzrr")
	private String dkzrr;
	/**bz*/
    @ApiModelProperty(value = "bz")
	private String bz;
	/**lrsj*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrbz*/
    @ApiModelProperty(value = "lrbz")
	private Integer lrbz;
	/**lrr*/
    @ApiModelProperty(value = "lrr")
	private String lrr;
	/**tjyf*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "tjyf")
	private Date tjyf;
	/**bmkkh*/
    @ApiModelProperty(value = "bmkkh")
	private String bmkkh;
	/**qxr*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "qxr")
	private Date qxr;
}
