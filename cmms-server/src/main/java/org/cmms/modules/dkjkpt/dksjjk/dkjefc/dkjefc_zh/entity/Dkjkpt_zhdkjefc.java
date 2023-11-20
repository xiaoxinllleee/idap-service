package org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.entity;

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
 * @Description: 贷款金额分层_支行
 * @Author: jeecg-boot
 * @Date: 2020-11-06
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_ZHDKJEFC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "DKJKPT_ZHDKJEFC对象", description = "贷款金额分层_支行")
public class Dkjkpt_zhdkjefc {
    /**
     * 统计月份
     */
    @Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
    private Date tjyf;
    /**
     * 所属支行
     */
    @Excel(name = "所属支行", width = 15, dicCode = "YWJGDM", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode="YWJGDM",dictTable="HR_BAS_ORGANIZATION",dicText="ZZJC")
    private String jgdm;
    /**
     * 50W-100W以下余额
     */
    @Excel(name = "50W-100W以下余额", width = 15)
    @ApiModelProperty(value = "50W-100W以下余额")
    private java.math.BigDecimal wwzybye;
    /**
     * 50W-100W以下户数
     */
    @Excel(name = "50W-100W以下户数", width = 15)
    @ApiModelProperty(value = "50W-100W以下户数")
    private Long wwzybhs;
    /**
     * 50W-100W以下比上月户数
     */
    @Excel(name = "50W-100W以下比上月户数", width = 15)
    @ApiModelProperty(value = "50W-100W以下比上月户数")
    private Long wwzybbsyhs;
    /**
     * 100W-300W含以下余额
     */
    @Excel(name = "100W-300W含以下余额", width = 15)
    @ApiModelProperty(value = "100W-300W含以下余额")
    private java.math.BigDecimal ybzsbye;
    /**
     * 100W-300W含以下户数
     */
    @Excel(name = "100W-300W含以下户数", width = 15)
    @ApiModelProperty(value = "100W-300W含以下户数")
    private Long ybzsbhs;
    /**
     * 100W-300W含下比上月户数
     */
    @Excel(name = "100W-300W含下比上月户数", width = 15)
    @ApiModelProperty(value = "100W-300W含下比上月户数")
    private Long ybzsbbsyhs;
    /**
     * 300W-500W含以下余额
     */
    @Excel(name = "300W-500W含以下余额", width = 15)
    @ApiModelProperty(value = "300W-500W含以下余额")
    private java.math.BigDecimal sbzwbye;
    /**
     * 300W-500W含以下户数
     */
    @Excel(name = "300W-500W含以下户数", width = 15)
    @ApiModelProperty(value = "300W-500W含以下户数")
    private Long sbzwbhs;
    /**
     * 300W-500W含下比上月户数
     */
    @Excel(name = "300W-500W含下比上月户数", width = 15)
    @ApiModelProperty(value = "300W-500W含下比上月户数")
    private Long sbzwbbsyhs;
    /**
     * 500W-1000W含以下余额
     */
    @Excel(name = "500W-1000W含以下余额", width = 15)
    @ApiModelProperty(value = "500W-1000W含以下余额")
    private java.math.BigDecimal wbzyqye;
    /**
     * 500W-1000W含以下户数
     */
    @Excel(name = "500W-1000W含以下户数", width = 15)
    @ApiModelProperty(value = "500W-1000W含以下户数")
    private Long wbzyqhs;
    /**
     * 500W-1000W含下比上月户数
     */
    @Excel(name = "500W-1000W含下比上月户数", width = 15)
    @ApiModelProperty(value = "500W-1000W含下比上月户数")
    private Long wbzyqbsyhs;
    /**
     * 1000W以上余额
     */
    @Excel(name = "1000W以上余额", width = 15)
    @ApiModelProperty(value = "1000W以上余额")
    private java.math.BigDecimal yqysye;
    /**
     * 1000W以上户数
     */
    @Excel(name = "1000W以上户数", width = 15)
    @ApiModelProperty(value = "1000W以上户数")
    private Long yqyshs;
    /**
     * 1000W以上比上月户数
     */
    @Excel(name = "1000W以上比上月户数", width = 15)
    @ApiModelProperty(value = "1000W以上比上月户数")
    private Long yqysbsyhs;
    /**
     * 贷款总余额
     */
    @Excel(name = "贷款总余额", width = 15)
    @ApiModelProperty(value = "贷款总余额")
    private java.math.BigDecimal dkzye;
    /**
     * 贷款总户数
     */
    @Excel(name = "贷款总户数", width = 15)
    @ApiModelProperty(value = "贷款总户数")
    private Long dkzhs;

}
