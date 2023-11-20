package org.cmms.modules.sjxf.qtxt.zzxt.kmwdxx.entity;

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
 * @Description: 科目维度信息
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Tgls_glsitem_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgls_glsitem_info对象", description="科目维度信息")
public class Kmwdxx {
    
	/**科目编码*/
	@Excel(name = "科目编码", width = 15)
    @ApiModelProperty(value = "科目编码")
	private String itemNo;
	/**科目名称*/
	@Excel(name = "科目名称", width = 15)
    @ApiModelProperty(value = "科目名称")
	private String itemName;
	/**科目级别*/
	@Excel(name = "科目级别", width = 15)
    @ApiModelProperty(value = "科目级别")
	private String itemLv;
	/**科目类别编码*/
	@Excel(name = "科目类别编码", width = 15)
    @ApiModelProperty(value = "科目类别编码")
	private String itemCategoryCode;
	/**科目类别名称*/
	@Excel(name = "科目类别名称", width = 15)
    @ApiModelProperty(value = "科目类别名称")
	private String itemCategoryName;
	/**余额方向*/
	@Excel(name = "余额方向", width = 15)
    @ApiModelProperty(value = "余额方向")
	private String balanceDirection;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
