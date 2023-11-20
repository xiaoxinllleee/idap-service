package org.cmms.modules.xdgl.grdkgl.entity;

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
 * @Description: 个人贷款担保信息
 * @Author: jeecg-boot
 * @Date:   2020-09-11
 * @Version: V1.0
 */
@Data
@TableName("CAMS_JBXX_GRXD_DBXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_JBXX_GRXD_DBXX对象", description="个人贷款担保信息")
public class GrdkDbxx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	private String dbfs;
	/**所有权人证件号码*/
	@Excel(name = "担保物名称", width = 15)
	@ApiModelProperty(value = "担保方式")
	private String dbw;

	@Excel(name = "所有权人证件号码", width = 15)
    @ApiModelProperty(value = "所有权人证件号码")
	private String syqr;
	/**所有权人*/
	@Excel(name = "所有权人", width = 15)
    @ApiModelProperty(value = "所有权人")
	private String syqrzjhm;
	/**权证号码*/
	@Excel(name = "权证号码", width = 15)
    @ApiModelProperty(value = "权证号码")
	private String qzhm;
	/**房屋位置*/
	@Excel(name = "房屋位置", width = 15)
    @ApiModelProperty(value = "房屋位置")
	private String fwwz;
	/**权证面积*/
	@Excel(name = "权证面积", width = 15)
    @ApiModelProperty(value = "权证面积")
	private java.math.BigDecimal qzmj;
	/**评估价值*/
	@Excel(name = "评估价值", width = 15)
    @ApiModelProperty(value = "评估价值")
	private java.math.BigDecimal pgjz;
	/**抵押金额*/
	@Excel(name = "抵押金额", width = 15)
    @ApiModelProperty(value = "抵押金额")
	private java.math.BigDecimal dyje;
	/**抵押期限*/
	@Excel(name = "抵押期限", width = 15)
    @ApiModelProperty(value = "抵押期限")
	private Integer dyqx;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15)
    @ApiModelProperty(value = "所属客户经理")
	private String sskhjl;
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
}
