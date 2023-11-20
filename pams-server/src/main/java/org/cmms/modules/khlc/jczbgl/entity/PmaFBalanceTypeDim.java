package org.cmms.modules.khlc.jczbgl.entity;

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
 * @Description: 余额类型维表
 * @Author: jeecg-boot
 * @Date:   2021-01-22
 * @Version: V1.0
 */
@Data
@TableName("PMA_F_BALANCE_TYPE_DIM")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_F_BALANCE_TYPE_DIM对象", description="余额类型维表")
public class PmaFBalanceTypeDim {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**指标编号*/
	@Excel(name = "指标编号", width = 15)
    @ApiModelProperty(value = "指标编号")
	private java.lang.String indexId;
	/**余额类型代码*/
	@Excel(name = "余额类型代码", width = 15)
    @ApiModelProperty(value = "余额类型代码")
	private java.lang.String balTypeId;
}
