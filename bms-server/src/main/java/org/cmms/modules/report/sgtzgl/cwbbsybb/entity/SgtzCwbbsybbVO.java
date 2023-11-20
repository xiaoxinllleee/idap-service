package org.cmms.modules.report.sgtzgl.cwbbsybb.entity;

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
 * @Description: 财务报表损益报表
 * @Author: jeecg-boot
 * @Date:   2022-10-11
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_cwbbsybb对象", description="财务报表损益报表-本外币")
public class SgtzCwbbsybbVO {

	/**行次*/
	@Excel(name = "行次", width = 15)
    @ApiModelProperty(value = "行次")
	private String hc;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String xmmc;
	/**上年同期数*/
	@Excel(name = "上年同期数", width = 15)
    @ApiModelProperty(value = "上年同期数")
	private String sntqs;
	/**本年累计数*/
	@Excel(name = "本年累计数", width = 15)
    @ApiModelProperty(value = "本年累计数")
	private String bnljs;
}
