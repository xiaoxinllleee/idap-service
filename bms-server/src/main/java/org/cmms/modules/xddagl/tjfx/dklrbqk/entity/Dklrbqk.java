package org.cmms.modules.xddagl.tjfx.dklrbqk.entity;

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
 * @Description: 贷款录入情况表
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Data
@TableName("V_xddagl_dascqk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_xddagl_dascqk对象", description="贷款录入情况表")
public class Dklrbqk {
    
	/**jgdm*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "jgdm")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**zhts*/
	@Excel(name = "需上传合同数", width = 15)
    @ApiModelProperty(value = "需上传合同数")
	private Integer zhts;
	/**ywcs*/
	@Excel(name = "已完成合同数", width = 15)
    @ApiModelProperty(value = "已完成合同数")
	private Integer ywcs;
	/**wwcs*/
	@Excel(name = "未完成合同数", width = 15)
    @ApiModelProperty(value = "未完成合同数")
	private Integer wwcs;
}
