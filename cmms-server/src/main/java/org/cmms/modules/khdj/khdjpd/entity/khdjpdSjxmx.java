package org.cmms.modules.khdj.khdjpd.entity;

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
 * @Description: 等级评定数据项明细
 * @Author: cmms
 * @Date:   2019-11-14
 * @Version: V1.0
 */
@Data
@TableName("TMP_KHDJ_DJPDZBSJMX_C")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TMP_KHDJ_DJPDZBSJMX_C对象", description="等级评定数据项明细")
public class khdjpdSjxmx {

	/**评定日期*/
	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
	private Date pdrq;
	/**评定周期*/
	@Excel(name = "评定周期", width = 15, dicCode = "rqwd")
    @ApiModelProperty(value = "评定周期")
    @Dict(dicCode = "rqwd")
	private String pdzq;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "组织标识")
    @Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode="ywjgdm",dictTable="HR_BAS_ORGANIZATION",dicText="zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode="ywjgdm",dictTable="HR_BAS_ORGANIZATION",dicText="zzjc")
	private String jgdm;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15, dicCode = "dybh", dictTable = "YXDYGL_EJYXDYGL", dicText = "dymc")
    @ApiModelProperty(value = "所属营销单元")
    @Dict(dicCode = "dybh", dictTable = "YXDYGL_EJYXDYGL", dicText = "dymc")
	private String ssyxdy;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
    /**联系方式*/
    @Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
    private String lxfs;
	/**客户类型(1.个人/2.对公)*/
	@Excel(name = "客户类型", width = 15, dicCode = "khdjpd_khlx")
    @ApiModelProperty(value = "客户类型(01.个人/02.对公)")
    @Dict(dicCode = "khdjpd_khlx")
	private String khlx;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15, dicCode = "khzycd")
    @ApiModelProperty(value = "客户重要程度")
    @Dict(dicCode = "khzycd")
	private String khzycd;
	/**数据项维度(DD.天/MM.月/Q.季/YYYY.年)*/
	@Excel(name = "数据项维度", width = 15, dicCode = "rqwd")
    @ApiModelProperty(value = "数据项维度(DD.天/MM.月/Q.季/YYYY.年)")
    @Dict(dicCode = "rqwd")
	private String sjxwd;
	/**数据项ID*/
	@Excel(name = "数据项ID", width = 15)
    @ApiModelProperty(value = "数据项ID")
	private String sjxid;
	/**数据项名称*/
	@Excel(name = "数据项名称", width = 15)
    @ApiModelProperty(value = "数据项名称")
	private String sjxmc;
	/**数据项SQL*/
	@Excel(name = "数据项SQL", width = 15)
    @ApiModelProperty(value = "数据项SQL")
	private String sjxsql;
	/**数据项结果*/
	@Excel(name = "数据项结果", width = 15)
    @ApiModelProperty(value = "数据项结果")
	private java.math.BigDecimal sjxjg;
    /**折算系数*/
    @Excel(name = "折算系数", width = 15)
    @ApiModelProperty(value = "折算系数")
    private java.math.BigDecimal zsxs;
	/**数据来源(0.系统取数/1.人工录入)*/
	@Excel(name = "数据来源", width = 15)
    @ApiModelProperty(value = "数据来源(0.系统取数/1.人工录入)")
	private String sjly;
	/**执行顺序*/
	@Excel(name = "执行顺序", width = 15)
    @ApiModelProperty(value = "执行顺序")
	private Integer zxsx;
	/**执行时间*/
	@Excel(name = "执行时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "执行时间")
	private Date etime;
	/**执行信息*/
	@Excel(name = "执行信息", width = 15)
    @ApiModelProperty(value = "执行信息")
	private String einfo;
	/**执行状态(0.未执行/1.执行成功/2.执行失败/3.存在人行数据跳过)*/
	@Excel(name = "执行状态", width = 15)
    @ApiModelProperty(value = "执行状态(0.未执行/1.执行成功/2.执行失败/3.存在人行数据跳过)")
	private String estat;
	/**usetime*/
	@Excel(name = "usetime", width = 15)
    @ApiModelProperty(value = "usetime")
	private Integer usetime;
	/**itime*/
	@Excel(name = "itime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "itime")
	private Date itime;
	/**主键序列*/
	@Excel(name = "主键序列", width = 15)
    @ApiModelProperty(value = "主键序列")
	private String srid;
}
