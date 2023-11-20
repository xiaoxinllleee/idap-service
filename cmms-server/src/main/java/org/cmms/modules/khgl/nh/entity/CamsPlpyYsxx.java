package org.cmms.modules.khgl.nh.entity;

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
 * @Description: 批量评议验收信息
 * @Author: jeecg-boot
 * @Date:   2022-04-28
 * @Version: V1.0
 */
@Data
@TableName("CAMS_PLPY_YSXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_PLPY_YSXX对象", description="批量评议验收信息")
public class CamsPlpyYsxx {
    
	/**资料编号*/
	@TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "资料编号")
	private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**评议轮数*/
	@Excel(name = "评议轮数", width = 15)
    @ApiModelProperty(value = "评议轮数")
	private Integer pyls;
	/**抽取比例*/
	@Excel(name = "抽取比例", width = 15)
    @ApiModelProperty(value = "抽取比例")
	private Integer cqbl;
	/**验收人*/
	@Excel(name = "验收人", width = 15)
    @ApiModelProperty(value = "验收人")
	private String ysr;
	/**验收人合影*/
	@Excel(name = "验收人合影", width = 15)
    @ApiModelProperty(value = "验收人合影")
	private String ysrhy;
	/**验收人签字*/
	@Excel(name = "验收人签字", width = 15)
    @ApiModelProperty(value = "验收人签字")
	private String ysrqz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
}
