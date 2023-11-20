package org.cmms.modules.khxxgl.wbsjgl.tjf.grtjf.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 个人碳积分
 * @Author: jeecg-boot
 * @Date:   2022-11-21
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_WBSJGL_GRTJF")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_WBSJGL_GRTJF对象", description="个人碳积分")
public class Grtjf {
    
	/**主键标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键标识")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**身份证*/
	@Excel(name = "身份证", width = 15)
    @ApiModelProperty(value = "身份证")
	private String sfz;
	/**类型*/
	@Excel(name = "类型", width = 15,dicCode = "grtjf_lx")
    @ApiModelProperty(value = "类型")
	@Dict(dicCode = "grtjf_lx")
	private String lx;
	/**输入方式*/
//	@Excel(name = "输入方式", width = 15 ,dicCode = "lrbz")
    @ApiModelProperty(value = "输入方式")
	@Dict(dicCode = "lrbz")
	private String srfs;
	/**数据来源*/
//	@Excel(name = "数据来源", width = 15)
    @ApiModelProperty(value = "数据来源")
	private String sjly;
	/**多少次*/
	@Excel(name = "多少次", width = 15)
    @ApiModelProperty(value = "多少次")
	private Integer dsc;
	/**创建时间*/
//	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**创建人*/
//	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**修改时间*/
//	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**修改人*/
//	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
}
