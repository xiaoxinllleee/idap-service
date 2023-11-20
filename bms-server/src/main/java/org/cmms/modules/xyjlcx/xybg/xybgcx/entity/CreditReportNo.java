package org.cmms.modules.xyjlcx.xybg.xybgcx.entity;

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

@Data
@TableName("credit_bgbh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="credit_bgbh表对象", description="信用报告编号")
public class CreditReportNo {
    /**区域代码*/
    @Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
    private String jgdm;
    /**查询日期*/
    @Excel(name = "查询日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "查询日期")
    private Date cxrq;
    /**序号*/
    @Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
    private Integer xh;
}
