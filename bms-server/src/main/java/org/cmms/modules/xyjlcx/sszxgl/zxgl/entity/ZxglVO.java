package org.cmms.modules.xyjlcx.sszxgl.zxgl.entity;

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
 * @Description: 执行管理
 * @Author: jeecg-boot
 * @Date:   2021-08-16
 * @Version: V1.0
 */
@Data
@TableName("Credit_zxgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Credit_zxgl对象", description="执行管理")
public class ZxglVO {

    /**账号/卡号*/
    @Excel(name = "账号/卡号", width = 15)
    @ApiModelProperty(value = "账号/卡号")
    @ExcelVerify(notNull = true)
    private String zh;
    /**申请执行日期*/
    @Excel(name = "申请执行日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "申请执行日期")
    private Date sqzxrq;
    /**执行本金*/
    @Excel(name = "执行本金", width = 15)
    @ApiModelProperty(value = "执行本金")
    private java.math.BigDecimal zxbj;
    /**执行利息*/
    @Excel(name = "执行利息", width = 15)
    @ApiModelProperty(value = "执行利息")
    private java.math.BigDecimal zxlx;
    /**待执行金额*/
    @Excel(name = "待执行金额", width = 15)
    @ApiModelProperty(value = "待执行金额")
    private java.math.BigDecimal dzxje;
    /**执行案号*/
    @Excel(name = "执行案号", width = 15)
    @ApiModelProperty(value = "执行案号")
    private String zxah;
    /**当前执行法院*/
    @Excel(name = "当前执行法院", width = 15)
    @ApiModelProperty(value = "当前执行法院")
    private String dqzxfy;
    /**可供执行财产额*/
    @Excel(name = "可供执行财产额", width = 15)
    @ApiModelProperty(value = "可供执行财产额")
    private String kgzxcce;
    /**抵押担保人*/
    @Excel(name = "抵押担保人", width = 15)
    @ApiModelProperty(value = "抵押担保人")
    private String dydbr;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    @ExcelVerify(interHandler = true)
    private String bz;
}
