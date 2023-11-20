package org.cmms.modules.word.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 文档补录信息
 * @Author: jeecg-boot
 * @Date:   2020-08-07
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_WORDINFO")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_WORDINFO对象", description="文档补录信息")
public class CamsZcsxWordinfo {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**文档类型*/
	@Excel(name = "文档类型", width = 15)
    @ApiModelProperty(value = "文档类型")
	private String wordType;
	/**文档字段-根据文档类型确定*/
	@Excel(name = "文档字段-根据文档类型确定", width = 15)
    @ApiModelProperty(value = "文档字段-根据文档类型确定")
	private String wordColumnOne;
	/**文档字段-根据文档类型确定*/
	@Excel(name = "文档字段-根据文档类型确定", width = 15)
    @ApiModelProperty(value = "文档字段-根据文档类型确定")
	private String wordColumnTwo;
	/**文档字段-根据文档类型确定*/
	@Excel(name = "文档字段-根据文档类型确定", width = 15)
    @ApiModelProperty(value = "文档字段-根据文档类型确定")
	private String wordColumnThree;
	/**文档字段-根据文档类型确定*/
	@Excel(name = "文档字段-根据文档类型确定", width = 15)
    @ApiModelProperty(value = "文档字段-根据文档类型确定")
	private String wordColumnFour;
	/**文档字段-根据文档类型确定*/
	@Excel(name = "文档字段-根据文档类型确定", width = 15)
    @ApiModelProperty(value = "文档字段-根据文档类型确定")
	private String wordColumnFive;

	@Excel(name = "文档字段-根据文档类型确定", width = 15)
	@ApiModelProperty(value = "文档字段-根据文档类型确定")
	private String hhbm;

	@Excel(name = "文档字段-根据文档类型确定", width = 15)
	@ApiModelProperty(value = "文档字段-根据文档类型确定")
	private String yhzgx;

	@Excel(name = "文档字段-根据文档类型确定", width = 15)
	@ApiModelProperty(value = "文档字段-根据文档类型确定")
	private String szjhm;
	private String lxr;
	private String zwr;
	private String sphone;
	private String szz;
	private String hkbz;
}
