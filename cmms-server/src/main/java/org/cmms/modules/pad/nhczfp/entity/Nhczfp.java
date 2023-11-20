package org.cmms.modules.pad.nhczfp.entity;

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
 * @Description: 农户村组复评
 * @Author: jeecg-boot
 * @Date:   2023-03-27
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_NHCZFP")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_NHCZFP对象", description="农户村组复评")
public class Nhczfp {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**复评网格*/
	@Excel(name = "复评网格", width = 15)
    @ApiModelProperty(value = "复评网格")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String fpwg;
	/**复评子网格*/
	@Excel(name = "复评子网格", width = 15)
    @ApiModelProperty(value = "复评子网格")
	@Dict( dicCode="wgbh", dictTable="yxdygl_main", dicText="wgmc")
	private String fpwgChild;
	/**复评人证件号码1*/
	@Excel(name = "复评人证件号码1", width = 15)
    @ApiModelProperty(value = "复评人证件号码1")
	private String fprzjhm1;
	/**复评人姓名1*/
	@Excel(name = "复评人姓名1", width = 15)
    @ApiModelProperty(value = "复评人姓名1")
	private String fprxm1;
	/**复评人电子签名1*/
	@Excel(name = "复评人电子签名1", width = 15)
    @ApiModelProperty(value = "复评人电子签名1")
	private String fprdzqm1;
	/**复评人证件号码2*/
	@Excel(name = "复评人证件号码2", width = 15)
    @ApiModelProperty(value = "复评人证件号码2")
	private String fprzjhm2;
	/**复评人姓名2*/
	@Excel(name = "复评人姓名2", width = 15)
    @ApiModelProperty(value = "复评人姓名2")
	private String fprxm2;
	/**复评人电子签名2*/
	@Excel(name = "复评人电子签名2", width = 15)
    @ApiModelProperty(value = "复评人电子签名2")
	private String fprdzqm2;
	/**复评人证件号码3*/
	@Excel(name = "复评人证件号码3", width = 15)
    @ApiModelProperty(value = "复评人证件号码3")
	private String fprzjhm3;
	/**复评人姓名3*/
	@Excel(name = "复评人姓名3", width = 15)
    @ApiModelProperty(value = "复评人姓名3")
	private String fprxm3;
	/**复评人电子签名3*/
	@Excel(name = "复评人电子签名3", width = 15)
    @ApiModelProperty(value = "复评人电子签名3")
	private String fprdzqm3;
	/**复评人证件号码4*/
	@Excel(name = "复评人证件号码4", width = 15)
    @ApiModelProperty(value = "复评人证件号码4")
	private String fprzjhm4;
	/**复评人姓名4*/
	@Excel(name = "复评人姓名4", width = 15)
    @ApiModelProperty(value = "复评人姓名4")
	private String fprxm4;
	/**复评人电子签名4*/
	@Excel(name = "复评人电子签名4", width = 15)
    @ApiModelProperty(value = "复评人电子签名4")
	private String fprdzqm4;
	/**复评客户经理*/
	@Excel(name = "复评客户经理", width = 15)
    @ApiModelProperty(value = "复评客户经理")
	@Dict(dicCode = "yggh",dictTable = "hr_bas_staff",dicText = "ygxm")
	private String fpkhjl;
	/**复评客户经理电子签名*/
	@Excel(name = "复评客户经理电子签名", width = 15)
    @ApiModelProperty(value = "复评客户经理电子签名")
	private String fpkhjldzqm;
	/**是否完成复评*/
	@Excel(name = "是否完成复评", width = 15)
	@ApiModelProperty(value = "是否完成复评")
	@Dict(dicCode = "sfbz")
	private String sfwcfp;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
