package org.cmms.modules.report.bbgl.bbmbgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 报表模板管理
 * @Author: jeecg-boot
 * @Date:   2022-03-22
 * @Version: V1.0
 */
@Data
@TableName("rep_bbmbgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rep_bbmbgl对象", description="报表模板管理")
public class BbmbglVo {

	/**id*/
	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value = "id")
	private String id;
	/**bbyf*/
	@Excel(name = "bbyf", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "bbyf")
	private Date bbyf;
	/**bbbh*/
	@Excel(name = "bbbh", width = 15)
	@ApiModelProperty(value = "bbbh")
	private String bbbh;
	/**bblj*/
	@Excel(name = "bblj", width = 15)
	@ApiModelProperty(value = "bblj")
	private String bblj;
	/**bbmc*/
	@Excel(name = "bbmc", width = 15)
	@ApiModelProperty(value = "bbmc")
	private String bbmc;
	/**bblx*/
	@Excel(name = "bblx", width = 15, dicCode = "rep_bblx")
	@ApiModelProperty(value = "bblx")
	@Dict(dicCode = "rep_bblx")
	private String bblx;
	/**sjlx*/
	@Excel(name = "sjlx", width = 15, dicCode = "rep_sjlx")
	@ApiModelProperty(value = "sjlx")
	@Dict(dicCode = "rep_sjlx")
	private String sjlx;
	/**sfqy*/
	@Excel(name = "sfqy", width = 15)
	@ApiModelProperty(value = "sfqy")
	private String sfqy;
	/**bbsx*/
	@Excel(name = "bbsx", width = 15)
	@ApiModelProperty(value = "bbsx")
	private Integer bbsx;
	/**bbh*/
	@Excel(name = "bbh", width = 15)
	@ApiModelProperty(value = "bbh")
	private String bbh;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
	@ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
	@ApiModelProperty(value = "updateBy")
	private String updateBy;

	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "updateTime")
	private Date updateTime;

	@ApiModelProperty(value = "是否创建")
	@Dict(dicCode = "rep_sfcj")
	private String sfcj;
}
