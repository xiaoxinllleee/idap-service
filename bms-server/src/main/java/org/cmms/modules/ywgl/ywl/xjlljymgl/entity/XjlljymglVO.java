package org.cmms.modules.ywgl.ywl.xjlljymgl.entity;

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
 * @Description: 现金流量交易码管理
 * @Author: jeecg-boot
 * @Date:   2021-09-30
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_XJLLJYM")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_XJLLJYM对象", description="现金流量交易码管理")
public class XjlljymglVO {

    /**交易码*/
    @Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
    private String jym;
    /**交易名称*/
    @Excel(name = "交易名称", width = 15)
    @ApiModelProperty(value = "交易名称")
    private String jymc;
    /**是否启用*/
    @Excel(name = "是否启用", width = 15)
    @ApiModelProperty(value = "是否启用")
    @Dict(dicCode = "sfqy")
    private Integer sfqy;

}
