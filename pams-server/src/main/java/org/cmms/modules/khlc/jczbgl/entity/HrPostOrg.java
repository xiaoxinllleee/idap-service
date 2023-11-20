package org.cmms.modules.khlc.jczbgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HrPostOrg {

    /**岗位标识*/
    @ApiModelProperty(value = "岗位标识")
    @TableId(type = IdType.ASSIGN_ID)
    private Integer gwbz;
    @ApiModelProperty(value = "岗位名称")
    private String gwmc;
    @ApiModelProperty(value = "组织标识")
    private String zzbz;
    @ApiModelProperty(value = "组织简称")
    private String zzjc;
}
