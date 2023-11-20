package org.cmms.modules.khgl.ckdkkh.entity;

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
 * @Description: 存量贷款客户
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
@Data
@TableName("cl_dkkh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cl_dkkh对象", description="存量贷款客户")
public class ClDkkh {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**creator*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**createTime*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updator*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**updateTime*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String dz;
	/**采集人*/
	@Excel(name = "采集人", width = 15)
    @ApiModelProperty(value = "采集人")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String cjr;
	/**所属机构代码*/
	@Excel(name = "所属机构代码", width = 15)
    @ApiModelProperty(value = "所属机构代码")
	private String ssjgdm;
	/**归属情况*/
	@Excel(name = "归属情况", width = 15)
    @ApiModelProperty(value = "归属情况")
	@Dict(dicCode = "cl_dkkh_gsqk")
	private String gsqk;
	/**custId*/
	@Excel(name = "custId", width = 15)
    @ApiModelProperty(value = "custId")
	private String custId;

	@Dict(dicCode = "sfbz")
	private String sfcj;
}
