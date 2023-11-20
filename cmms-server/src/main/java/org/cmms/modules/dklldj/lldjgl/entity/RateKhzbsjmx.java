package org.cmms.modules.dklldj.lldjgl.entity;

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
 * @Description: 客户指标数据明细
 * @Author: Penghr
 * @Date:   2022-09-21
 * @Version: V1.0
 */
@Data
@TableName("rate_lldj_khzbsjmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_lldj_khzbsjmx对象", description="客户指标数据明细")
public class RateKhzbsjmx {

    /**定价年份*/
    @Excel(name = "定价年份", width = 15, format = "yyyy")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "定价年份")
    private Date djnf;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**前一年存款日平余额*/
    @Excel(name = "前一年存款日平余额", width = 15)
    @ApiModelProperty(value = "前一年存款日平余额")
    private java.math.BigDecimal qynckrpye;
    /**前一年贷款日平余额*/
    @Excel(name = "前一年贷款日平余额", width = 15)
    @ApiModelProperty(value = "前一年贷款日平余额")
    private java.math.BigDecimal qyndkrpye;
    /**前半年存款日平余额*/
    @Excel(name = "前半年存款日平余额", width = 15)
    @ApiModelProperty(value = "前半年存款日平余额")
    private java.math.BigDecimal qbnckrpye;
    /**前半年贷款日平余额*/
    @Excel(name = "前半年贷款日平余额", width = 15)
    @ApiModelProperty(value = "前半年贷款日平余额")
    private java.math.BigDecimal qbndkrpye;
    /**是否建立信贷业务关系(1-是;2-否)*/
    @Excel(name = "建立信贷业务关系", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否建立信贷业务关系(1-是;2-否)")
    @Dict(dicCode = "sfbz")
    private String sfjlxdywgx;
    /**是否开立结算账户(1-是;2-否)*/
    @Excel(name = "开立结算账户", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开立结算账户(1-是;2-否)")
    @Dict(dicCode = "sfbz")
    private String sfkljszh;
    /**是否开通手机网银业务(1-是;2-否)*/
    @Excel(name = "开通手机网银业务", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通手机网银业务(1-是;2-否)")
    @Dict(dicCode = "sfbz")
    private String sfktsjwy;
    /**是否开通ETC业务*/
    @Excel(name = "开通ETC业务", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通ETC业务")
    @Dict(dicCode = "sfbz")
    private String sfktetcyw;
    /**是否开通信用卡业务*/
    @Excel(name = "开通信用卡业务", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通信用卡业务")
    @Dict(dicCode = "sfbz")
    private String sfktxykyw;
    /**信贷业务往来时间(月)*/
    @Excel(name = "信贷业务往来时间(月)", width = 15)
    @ApiModelProperty(value = "信贷业务往来时间(月)")
    private String xdywwlsj;
    /**存贷业务往来年限(月)*/
    @Excel(name = "存贷业务往来年限(月)", width = 15)
    @ApiModelProperty(value = "存贷业务往来年限(月)")
    private String cdywwlnx;
    /**开立基本账户往来年限(月)*/
    @Excel(name = "开立基本账户往来年限(月)", width = 15)
    @ApiModelProperty(value = "开立基本账户往来年限(月)")
    private String kljbzhwlnx;
    /**录入标识(0-导入/1-录入/2-修改)*/
    @Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
    @Dict(dicCode = "lrbz")
    private Integer lrbz;
    /**录入人*/
    @Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
    private String lrr;
    /**录入时间*/
    @Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
    private Date lrsj;


}
