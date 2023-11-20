package org.cmms.modules.report.sgtzgl.qszlxxxcyqymd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

/**
 * @Description: 全省战略性新兴产业企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_SGTZ_QSZLXXXCYQYMD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_QSZLXXXCYQYMD对象", description="全省战略性新兴产业企业名单")
public class SgtzglQszlxxxcyqymdVO {

	/**法人码*/
	@Excel(name = "法人码", width = 15)
    @ApiModelProperty(value = "法人码")
	private String frm;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
	private String mc;
	/**节能环保产业*/
	@Excel(name = "节能环保产业", width = 15)
    @ApiModelProperty(value = "节能环保产业")
	private String cy;

}
