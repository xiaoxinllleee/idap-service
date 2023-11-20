package org.cmms.modules.ywgl.zzsfpgl.zzsfpxx.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 增值税发票信息
 * @Author: jeecg-boot
 * @Date:   2021-10-08
 * @Version: V1.0
 */
@Data
@TableName("VAT_BILL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VAT_BILL对象", description="增值税发票信息")
public class ZzsfpxxVO {


    /**开票日期*/
    @Excel(name = "开票日期", width = 15, format = "yyyyMMdd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开票日期")
    private Date kprq;
    /**发票号码*/
    @Excel(name = "发票号码", width = 15)
    @ApiModelProperty(value = "发票号码")
    private String fphm;
    /**发票代码*/
    @Excel(name = "发票代码", width = 15)
    @ApiModelProperty(value = "发票代码")
    private String hydm;
    /**货物或应税劳务、服务名称*/
    @Excel(name = "货物或应税劳务、服务名称", width = 15)
    @ApiModelProperty(value = "货物或应税劳务、服务名称")
    private String fwmc;
    /**价税合计金额*/
    @Excel(name = "价税合计金额", width = 15)
    @ApiModelProperty(value = "价税合计金额")
    private java.math.BigDecimal jshj;
    /**开票地区*/
    @Excel(name = "开票地区", width = 15)
    @ApiModelProperty(value = "开票地区")
    private String kpdq;
    /**销售方单位名称*/
    @Excel(name = "销售方单位名称", width = 15)
    @ApiModelProperty(value = "销售方单位名称")
    private String xsfdwmc;
    /**列账项目名称*/
    @Excel(name = "列账项目名称", width = 15)
    @ApiModelProperty(value = "列账项目名称")
    private String lzxmmc;
    /**是否电子发票*/
    @Excel(name = "是否电子发票", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否电子发票")
    @Dict(dicCode = "sfbz")
    private String sfdzfp;
    /**经办人*/
    @Excel(name = "经办人", width = 15)
    @ApiModelProperty(value = "经办人")
    private String jbr;
    /**经办人*/
    @Excel(name = "记账人", width = 15)
    @ApiModelProperty(value = "记账人")
    private String jzr;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**机构代码*/
    @Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    private String jgdm;

}
