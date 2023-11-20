package org.cmms.modules.tjfx.khnlfctj.entity;

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
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Data
@TableName("TJFX_QHKHNLFCTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_QHKHNLFCTJ对象", description="1")
public class Khnlfctj_qh {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**年龄不详客户数*/
	@Excel(name = "年龄不详客户数", width = 15)
    @ApiModelProperty(value = "年龄不详客户数")
	private String nlbxkhs;
	/**18岁以下客户数*/
	@Excel(name = "18岁以下客户数", width = 15)
    @ApiModelProperty(value = "18岁以下客户数")
	private String sbsyxkhs;
	/**19-26岁客户数*/
	@Excel(name = "19-26岁客户数", width = 15)
    @ApiModelProperty(value = "19-26岁客户数")
	private String sjzel;
	/**27-30岁客户数*/
	@Excel(name = "27-30岁客户数 ", width = 15)
    @ApiModelProperty(value = "27-30岁客户数 ")
	private java.lang.String eqzsh;
	/**31-40岁客户数*/
	@Excel(name = "31-40岁客户数 ", width = 15)
    @ApiModelProperty(value = "31-40岁客户数 ")
	private java.lang.String syzsh;
	/**41-55岁客户数*/
	@Excel(name = "41-55岁客户数 ", width = 15)
    @ApiModelProperty(value = "41-55岁客户数 ")
	private java.lang.String syzww;
	/**56-65岁客户数*/
	@Excel(name = "56-65岁客户数 ", width = 15)
    @ApiModelProperty(value = "56-65岁客户数 ")
	private java.lang.String wlzlw;
	/**66岁以上客户数*/
	@Excel(name = "66岁以上客户数", width = 15)
    @ApiModelProperty(value = "66岁以上客户数")
	private java.lang.String llsys;
}
