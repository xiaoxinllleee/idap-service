package org.cmms.modules.sjxf.qtsjcx.lssjcx.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.dynamic.datasource.annotation.DS;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 历史数据查询
 * @Author: jeecg-boot
 * @Date:   2022-09-02
 * @Version: V1.0
 */
@Data
@TableName("V_IMZH_LSLSB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IMZH_LSLSB对象", description="历史数据查询")
public class LmzhLslsb {
    /**货币号*/
    @Excel(name = "货币号", width = 15)
    @ApiModelProperty(value = "货币号")
    private java.lang.Integer hbh;
    /**帐号*/
    @Excel(name = "帐号", width = 15)
    @ApiModelProperty(value = "帐号")
    private java.lang.String zh;
    /**帐户序号*/
    @Excel(name = "帐户序号", width = 15)
    @ApiModelProperty(value = "帐户序号")
    private java.lang.Integer zhxh;
    /**zl*/
    @Excel(name = "zl", width = 15)
    @ApiModelProperty(value = "zl")
    private java.lang.Integer zl;
    /**机构码*/
    @Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
    private java.lang.String jgdm;
    /**代理机构码*/
    @Excel(name = "代理机构码", width = 15)
    @ApiModelProperty(value = "代理机构码")
    private java.lang.String dljg;
    /**交易日期*/
    @Excel(name = "交易日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "交易日期")
    private java.util.Date jyrq;
    /**交易时间*/
    @Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
    private java.lang.String jysj;
    /**交易码*/
    @Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
    private String jym;
    /**交易金额*/
    @Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
    private java.math.BigDecimal jyje;
    /**zmye*/
    @Excel(name = "zmye", width = 15)
    @ApiModelProperty(value = "zmye")
    private java.math.BigDecimal zmye;
    /**积数*/
    @Excel(name = "积数", width = 15)
    @ApiModelProperty(value = "积数")
    private java.math.BigDecimal zhjs;
    /**摘要*/
    @Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
    private java.lang.Integer zy;
    /**打印次数*/
    @Excel(name = "打印次数", width = 15)
    @ApiModelProperty(value = "打印次数")
    private java.lang.Integer dycs;
    /**凭证种类*/
    @Excel(name = "凭证种类", width = 15)
    @ApiModelProperty(value = "凭证种类")
    private java.lang.Integer pzzl;
    /**凭证号码*/
    @Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
    private java.lang.String pzhm;
    /**借贷标志*/
    @Excel(name = "借贷标志", width = 15)
    @ApiModelProperty(value = "借贷标志")
    private java.lang.Integer jdbz;
    /**平账标志*/
    @Excel(name = "平账标志", width = 15)
    @ApiModelProperty(value = "平账标志")
    private java.lang.Integer pzbz;
    /**通兑标志*/
    @Excel(name = "通兑标志", width = 15)
    @ApiModelProperty(value = "通兑标志")
    private java.lang.Integer tdbz;
    /**记账柜员*/
    @Excel(name = "记账柜员", width = 15)
    @ApiModelProperty(value = "记账柜员")
    private java.lang.Integer jzgy;
    /**复核柜员*/
    @Excel(name = "复核柜员", width = 15)
    @ApiModelProperty(value = "复核柜员")
    private java.lang.Integer fhgy;
    /**柜员流水号*/
    @Excel(name = "柜员流水号", width = 15)
    @ApiModelProperty(value = "柜员流水号")
    private java.lang.Integer gylsh;
    /**对应帐号*/
    @Excel(name = "对应帐号", width = 15)
    @ApiModelProperty(value = "对应帐号")
    private java.lang.String dyzh;
    /**信息代码*/
    @Excel(name = "信息代码", width = 15)
    @ApiModelProperty(value = "信息代码")
    private java.lang.String dac;
    /**系统日期*/
    @Excel(name = "系统日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "系统日期")
    private java.util.Date xtrq;
    /**zxlsh*/
    @Excel(name = "zxlsh", width = 15)
    @ApiModelProperty(value = "zxlsh")
    private java.lang.Integer zxlsh;
    /**hashzh*/
    @Excel(name = "hashzh", width = 15)
    @ApiModelProperty(value = "hashzh")
    private java.lang.Long hashzh;
    /**impfileday*/
    @Excel(name = "impfileday", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "impfileday")
    private java.util.Date impfileday;
    /**impfileline*/
    @Excel(name = "impfileline", width = 15)
    @ApiModelProperty(value = "impfileline")
    private java.lang.Long impfileline;
    /**impfileid*/
    @Excel(name = "impfileid", width = 15)
    @ApiModelProperty(value = "impfileid")
    private java.lang.Long impfileid;
    /**imptime*/
    @Excel(name = "imptime", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "imptime")
    private java.util.Date imptime;
    /**impuser*/
    @Excel(name = "impuser", width = 15)
    @ApiModelProperty(value = "impuser")
    private java.lang.String impuser;
    /**kh*/
    @Excel(name = "kh", width = 15)
    @ApiModelProperty(value = "kh")
    private java.lang.String kh;
    /**hm*/
    @Excel(name = "hm", width = 15)
    @ApiModelProperty(value = "hm")
    private java.lang.String hm;

    @Excel(name = "dyzkh", width = 15)
    @ApiModelProperty(value = "dyzkh")
    private String dyzkh;

    /**hm*/
    @Excel(name = "khm", width = 15)
    @ApiModelProperty(value = "kmh")
    private java.lang.String kmh;


    /**dfkmh*/
    @Excel(name = "dfkmh", width = 15)
    @ApiModelProperty(value = "dfkmh")
    private java.lang.String   dfkmh;




}
