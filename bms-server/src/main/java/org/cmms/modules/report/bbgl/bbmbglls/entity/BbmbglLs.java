package org.cmms.modules.report.bbgl.bbmbglls.entity;

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
 * @Description: 历史报表模板管理
 * @Author: jeecg-boot
 * @Date:   2022-07-15
 * @Version: V1.0
 */
@Data
@TableName("rep_bbmbgl_ls")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rep_bbmbgl_ls对象", description="历史报表模板管理")
public class BbmbglLs {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
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
	@Excel(name = "报表类型(1: 1104监管月报 2: 1104监管季报)", width = 15)
    @ApiModelProperty(value = "报表类型(1: 1104监管月报 2: 1104监管季报)")
	private String bblx;
	/**时间类型*/
	@Excel(name = "时间类型", width = 15)
    @ApiModelProperty(value = "时间类型")
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
	@Excel(name = "是否启用", width = 15)
    @ApiModelProperty(value = "是否启用")
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
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime HH:mm:ss")
	private Date updateTime;
}
