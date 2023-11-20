package org.cmms.modules.ywgl.nxt.shpj.shdjpd.entity;

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
 * @Description: 每月末临时表
 * @Author: jeecg-boot
 * @Date:   2022-01-21
 * @Version: V1.0
 */
@Data
@TableName("ERP_NXT_MYM_TMP")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_NXT_MYM_TMP对象", description="每月末临时表")
public class ErpNxtMymTmp {


	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键id")
	private String id;

	/**月末日期*/
	@Excel(name = "月末日期", width = 15)
    @ApiModelProperty(value = "月末日期")
	private java.lang.String ymrq;
}
