package org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description: 贷记卡贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2021-08-10
 * @Version: V1.0
 */
@Data
@TableName("CREDIT_DJKDKSJMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CREDIT_DJKDKSJMX对象", description="贷记卡贷款数据明细")
public class DjkdksjmxVO {

    /**卡号*/
    @Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
    private String kh;
    /**卡种类*/
    @Excel(name = "卡种类", width = 15,dicCode = "kzl")
    @ApiModelProperty(value = "卡种类")
    @Dict(dicCode = "kzl")
    private String kzl;
    /**证件类型*/
    @Excel(name = "证件类型", width = 15,dicCode = "dkjkpt_zjlx")
    @ApiModelProperty(value = "证件类型")
    @Dict(dicCode = "dkjkpt_zjlx")
    private String zjlx;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**客户名称*/
    @Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String khmc;
    /**性别*/
    @Excel(name = "性别", width = 15,dicCode = "sex_djk")
    @ApiModelProperty(value = "性别")
    @Dict(dicCode = "sex_djk")
    private String xb;
    /**婚姻状况*/
    @Excel(name = "婚姻状况", width = 15,dicCode = "hyzk_ly")
    @ApiModelProperty(value = "婚姻状况")
    @Dict(dicCode = "hyzk_ly")
    private Integer hyzk;
    /**家庭住址*/
    @Excel(name = "家庭住址", width = 15)
    @ApiModelProperty(value = "家庭住址")
    private String jtzz;
    /**联系电话*/
    @Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
    private String sjhm;
    /**发卡日期*/
    @Excel(name = "发放日期", width = 15, format = "yyyyMMdd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发放日期")
    private Date fkrq;
    /**授信金额*/
    @Excel(name = "授信金额", width = 15)
    @ApiModelProperty(value = "授信金额")
    private java.math.BigDecimal sxje;
    /**透支本金*/
    @Excel(name = "透支本金", width = 15)
    @ApiModelProperty(value = "透支本金")
    private java.math.BigDecimal tzbj;
    /**透支余额*/
    @Excel(name = "透支余额", width = 15)
    @ApiModelProperty(value = "透支余额")
    private java.math.BigDecimal tzye;
    /**推广人工号*/
    @Excel(name = "推广人工号", width = 15)
    @ApiModelProperty(value = "推广人工号")
    private String tgrgh;
    /**卡状态标志*/
    @Excel(name = "卡状态标志", width = 15,dicCode = "djkzl")
    @ApiModelProperty(value = "卡状态标志")
    @Dict(dicCode = "djkzl")
    private String kztbz;

}
