package org.cmms.modules.khdj.khdjcsgl.entity;

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
 * @Description: 客户等级评定参数管理
 * @Author: cmms
 * @Date:   2019-10-28
 * @Version: V1.0
 */
@Data
@TableName("KHDJ_CSGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHDJ_CSGL对象", description="客户等级评定参数管理")
public class KhdjCsgl {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
    /**参数ID*/
    @Excel(name = "参数ID", width = 15)
    @ApiModelProperty(value = "参数ID")
    private String pid;
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
	private String pname;
	/**参数值*/
	@Excel(name = "参数值", width = 15)
    @ApiModelProperty(value = "参数值")
	private java.math.BigDecimal pvalue;
    /**参数描述*/
    @Excel(name = "参数描述", width = 15)
    @ApiModelProperty(value = "参数描述")
    private String poutline;
}
