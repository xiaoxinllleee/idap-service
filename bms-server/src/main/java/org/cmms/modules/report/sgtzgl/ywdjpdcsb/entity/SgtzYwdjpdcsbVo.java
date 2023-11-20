package org.cmms.modules.report.sgtzgl.ywdjpdcsb.entity;

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
 * @Description: 业务等级评定参数表
 * @Author: jeecg-boot
 * @Date:   2022-12-30
 * @Version: V1.0
 */
@Data
@TableName("CWB_SGTZ_YWDJPDCSB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CWB_SGTZ_YWDJPDCSB对象", description="业务等级评定参数表")
public class SgtzYwdjpdcsbVo {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String sjrq;
	/**项目代号*/
	@Excel(name = "项目代号", width = 15)
    @ApiModelProperty(value = "项目代号")
	private String xmdh;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String xmmc;
	/**参数值*/
	@Excel(name = "参数值", width = 15)
    @ApiModelProperty(value = "参数值")
	private String csz;

}
