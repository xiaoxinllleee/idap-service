package org.cmms.modules.khlc.khfagl.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 考核对象
 * @Author: jeecg-boot
 * @Date:   2021-03-04
 * @Version: V1.0
 */
@Data
@TableName("V_PMA_A_SCHEME_EVLOBJ_REL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_PMA_A_SCHEME_EVLOBJ_REL对象", description="考核对象")
public class VPmaASchemeEvlobjRel {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**schemeId*/
	@Excel(name = "schemeId", width = 15)
    @ApiModelProperty(value = "schemeId")
	private String schemeId;
	/**evlObjType*/
	@Excel(name = "evlObjType", width = 15)
    @ApiModelProperty(value = "evlObjType")
	private String evlObjType;
	/**yggh*/
	@Excel(name = "yggh", width = 15)
    @ApiModelProperty(value = "yggh")
	private String yggh;
	/**ygxm*/
	@Excel(name = "ygxm", width = 15)
    @ApiModelProperty(value = "ygxm")
	private String ygxm;
	/**是否仅生效*/
	@Excel(name = "speRuleType", width = 15)
	@ApiModelProperty(value = "speRuleType")
	private String speRuleType;

}
