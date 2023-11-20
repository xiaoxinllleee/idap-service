package org.cmms.modules.report.bbgl.bbmbgl.entity;

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
public class Bbmbgl {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**报表编号*/
	@Excel(name = "报表编号", width = 15)
    @ApiModelProperty(value = "报表编号")
	private String bbbh;
	/**报表名称*/
	@Excel(name = "报表名称", width = 15)
    @ApiModelProperty(value = "报表名称")
	private String bbmc;
	/**报表类型(1: 1104监管月报 2: 1104监管季报)*/
	@Excel(name = "报表类型", width = 15, dicCode = "rep_bblx")
    @ApiModelProperty(value = "报表类型(1: 1104监管月报 2: 1104监管季报)")
	@Dict(dicCode = "rep_bblx")
	private String bblx;
	/**时间类型*/
	@Excel(name = "时间类型", width = 15, dicCode = "rep_sjlx")
	@ApiModelProperty(value = "时间类型")
	@Dict(dicCode = "rep_sjlx")
	private String sjlx;
	/**文件名称*/
	@Excel(name = "文件名称", width = 15)
	@ApiModelProperty(value = "文件名称")
	private String wjmc;
	/**报表路径*/
	@Excel(name = "报表路径", width = 15)
    @ApiModelProperty(value = "报表路径")
	private String bblj;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否启用")
	@Dict(dicCode = "sfbz")
	private String sfqy;
	/**报表顺序*/
	@Excel(name = "报表顺序", width = 15)
    @ApiModelProperty(value = "报表顺序")
	private Integer bbsx;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
	private String bbh;
	/**行数*/
	@Excel(name = "行数", width = 15)
	@ApiModelProperty(value = "行数")
	private Integer rnum;
	/**列数*/
	@Excel(name = "列数", width = 15)
	@ApiModelProperty(value = "列数")
	private Integer cnum;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;
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
	/**报表填充方式(1: 固定模型报表 2: 列表式报表)*/
	@Excel(name = "报表填充方式", width = 15, dicCode = "bbtcfs")
	@ApiModelProperty(value = "报表填充方式(1: 固定模型报表 2: 列表式报表)")
	@Dict(dicCode = "bbtcfs")
	private String bbtcfs;
	/**报表数据表名*/
	private String bbsjbm;
}
