package org.cmms.modules.khxxgl.yjzrbg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("cams_zcsx_yjzrbgbh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cams_zcsx_yjzrbgbh表对象", description="一键查报告编号表")
public class CamsZcsxYjzrbgbh {

    /**查询机构代码*/
    @Excel(name = "查询机构代码", width = 15)
    @ApiModelProperty(value = "查询机构代码")
    private String jgdm;
    /**查询日期*/
    @Excel(name = "查询日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "查询日期")
    private Date cxrq;
    /**报告编号*/
    @Excel(name = "报告编号", width = 15)
    @ApiModelProperty(value = "报告编号")
    private Integer xh;

}
