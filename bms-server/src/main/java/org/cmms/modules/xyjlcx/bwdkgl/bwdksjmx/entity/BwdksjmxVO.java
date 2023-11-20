package org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity;

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
 * @Description: 表外贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
@Data
@TableName("Credit_bwdksjmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Credit_bwdksjmx对象", description="表外贷款数据明细")
public class BwdksjmxVO {

    /**证件类型*/
    @Excel(name = "证件类型", width = 15,dicCode = "dkjkpt_zjlx")
    @ApiModelProperty(value = "证件类型")
    @Dict(dicCode = "dkjkpt_zjlx")
    private String zjlx;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**联系方式*/
    @Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
    private String lxfs;
    /**客户地址*/
    @Excel(name = "客户地址", width = 15)
    @ApiModelProperty(value = "客户地址")
    private String khdz;
    /**客户状况*/
    @Excel(name = "客户状况", width = 15)
    @ApiModelProperty(value = "客户状况")
    private String khzk;

    /**贷款日期*/
    @Excel(name = "贷款日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "贷款日期")
    private Date dkrq;
    /**到期日期*/
    @Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
    private Date dqrq;
    /**借款金额*/
    @Excel(name = "借款金额", width = 15)
    @ApiModelProperty(value = "借款金额")
    private java.math.BigDecimal jkje;
    /**贷款投向*/
    @Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
    private String dktx;
    /**包收责任人*/
    @Excel(name = "包收责任人", width = 15)
    @ApiModelProperty(value = "包收责任人")
    private String bszrr;
    /**管理责任人*/
    @Excel(name = "管理责任人", width = 15)
    @ApiModelProperty(value = "管理责任人")
    private String glzrr;
    /**最近催收日期*/
    @Excel(name = "最近催收日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最近催收日期")
    private Date zjcsrq;
    /**情况说明*/
    @Excel(name = "情况说明", width = 15)
    @ApiModelProperty(value = "情况说明")
    private String qksm;
    /**贷款账号*/
    @Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
    private String dkzh;

}
