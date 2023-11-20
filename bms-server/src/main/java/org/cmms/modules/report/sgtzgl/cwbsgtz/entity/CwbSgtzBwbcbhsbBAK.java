package org.cmms.modules.report.sgtzgl.cwbsgtz.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 财务部_成本核算表
 * @Author: jeecg_boot
 * @Date:   2023_06_13
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cwb_sgtz_bwbcbhsb对象", description="财务部_成本核算表")
public class CwbSgtzBwbcbhsbBAK {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String sjrq;


	@ExcelCollection(name="资产收益情况")
	private List<CwbSgtzBwbcbhsbFZZCVO> cwbSgtzBwbcbhsbFZZCVOList;

	@ExcelCollection(name="负债支出及所有者权益情况")
	private List<CwbSgtzBwbcbhsbZCSYVO> cwbSgtzBwbcbhsbZCSYVOList;

	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy_MM_dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy_MM_dd")
    @DateTimeFormat(pattern="yyyy_MM_dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
}
