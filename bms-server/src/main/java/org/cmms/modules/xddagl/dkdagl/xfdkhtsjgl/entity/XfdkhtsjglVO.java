package org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.entity;

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
 * @Description: 新放贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
@Data
@TableName("XDDAGL_DKHTSJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XDDAGL_DKHTSJXX对象", description="新放贷款合同数据管理")
public class XfdkhtsjglVO {

    /**联系电话*/
    @Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
    private String lxdh;
    /**联系地址*/
    @Excel(name = "联系地址", width = 15)
    @ApiModelProperty(value = "联系地址")
    private String lxdz;
    /**贷款品种(补充)*/
    @Excel(name = "贷款品种(补充)", width = 15,dicCode = "dkzlbc")
    @ApiModelProperty(value = "贷款品种(补充)")
    @Dict(dicCode = "dkzlbc")
    private String dkpzbc;
    /**贷款责任人工号*/
    @Excel(name = "贷款责任人工号", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "贷款责任人工号")
    @Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    private String dkzrr;
    /**合同号*/
    @Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
    private String hth;
}
