package org.cmms.modules.khlc.bmgpgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HrPostBmxx {

    /**岗位标识*/

    @ApiModelProperty(value = "组织标识")
    private String zzbz;
    @ApiModelProperty(value = "组织简称")
    private String zzjc;
}
