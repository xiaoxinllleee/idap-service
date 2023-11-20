package org.cmms.modules.dklldj.lldjgl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: 客户存款账号存款日平信息
 * @Author: Penghr
 * @Date:   2022-09-21
 * @Version: V1.0
 */
@Data
@TableName("rate_lldj_zhckrp")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_lldj_zhckrp对象", description="客户存款账号存款日平信息")
public class RateZhckrp {

    /**统计类型(YYYY、年 Q、季 MM、月)*/
    @Excel(name = "统计类型", width = 15)
    @ApiModelProperty(value = "统计类型")
    private String tjlx;
    /**定价年份*/
    @Excel(name = "定价年份", width = 15, format = "yyyy")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "定价年份")
    private Date tjyf;
    /**组织标识*/
    @Excel(name = "组织标识", width = 15, dicCode="zzbz", dictTable="hr_bas_organization", dicText="zzjc")
    @ApiModelProperty(value = "组织标识")
    @Dict(dicCode="zzbz", dictTable="hr_bas_organization", dicText="zzjc")
    private String zzbz;
    /**机构代码*/
    @Excel(name = "机构代码", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    private String jgdm;
    /**机构名称*/
    @TableField(exist = false)
    private String jgmc;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**户名*/
    @Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
    private String hm;
    /**与客户关系（1、本企业(人)，2、直系亲属，3、其它关系人，4、以他人名义存入本行）*/
    @Excel(name = "与客户关系（1、本企业(人)，2、直系亲属，3、其它关系人，4、以他人名义存入本行）", dicCode = "zhgx")
    @ApiModelProperty(value = "与客户关系（1、本企业(人)，2、直系亲属，3、其它关系人，4、以他人名义存入本行）")
    @Dict(dicCode = "zhgx")
    private Integer ykhgx;
    /**客户关系*/
    @TableField(exist = false)
    private String khgx;
    /**关联账号详细关系*/
    @Excel(name = "关联账号详细关系", width = 15)
    @ApiModelProperty(value = "关联账号详细关系")
    private String glzhxxgx;
    /**存款账号*/
    @Excel(name = "存款账号", width = 15)
    @ApiModelProperty(value = "存款账号")
    private String ckzh;
    /**账号类型(1、活期 2、定期*/
    @Excel(name = "账号类型(1、活期 2、定期", width = 15)
    @ApiModelProperty(value = "账号类型(1、活期 2、定期")
    private Integer zhlx;
    /**前三年第一年存款日平*/
    @Excel(name = "前三年第一年存款日平", width = 15)
    @ApiModelProperty(value = "前三年第一年存款日平")
    private java.math.BigDecimal qsndynckrp;
    /**前三年第二年存款日平*/
    @Excel(name = "前三年第二年存款日平", width = 15)
    @ApiModelProperty(value = "前三年第二年存款日平")
    private java.math.BigDecimal qsndenckrp;
    /**前三年第三年存款日平*/
    @Excel(name = "前三年第三年存款日平", width = 15)
    @ApiModelProperty(value = "前三年第三年存款日平")
    private java.math.BigDecimal qsndsnckrp;
    /**存入日*/
    @Excel(name = "存入日", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "存入日")
    private Date crr;
    /**支取日*/
    @Excel(name = "支取日", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "支取日")
    private Date zqr;
    /**金额*/
    @Excel(name = "金额", width = 15)
    @ApiModelProperty(value = "金额")
    private java.math.BigDecimal je;
    /**保证金存款日平*/
    @TableField(exist = false)
    private java.math.BigDecimal bzjckrp;

}
