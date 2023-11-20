package org.cmms.modules.rwzx.rwcj.entity;

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
 * @Description: 基础拜访任务
 * @Author: jeecg-boot
 * @Date:   2023-04-01
 * @Version: V1.0
 */
@Data
@TableName("TASK_BFRW_BASE")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TASK_BFRW_BASE对象", description="基础拜访任务")
public class TaskBfrwBase {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**khmc*/
	@Excel(name = "khmc", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
	@Dict(dictTable = "khxxgl_khxq_nh",dicCode = "zjhm",dicText = "sjhm")
	private String zjhm;
	/**wt1*/
	@Excel(name = "wt1", width = 15)
    @ApiModelProperty(value = "wt1")
	@Dict(dicCode = "fxd_yxcp")
	private String wt1;
	/**wt2*/
	@Excel(name = "wt2", width = 15)
    @ApiModelProperty(value = "wt2")
	@Dict(dicCode = "fxd_yxcp")
	private String wt2;
	/**wt3*/
	@Excel(name = "wt3", width = 15)
    @ApiModelProperty(value = "wt3")
	private String wt3;
	/**wt4*/
	@Excel(name = "wt4", width = 15)
    @ApiModelProperty(value = "wt4")
	@Dict(dicCode = "fxd_sbyy")
	private String wt4;
	/**wt5*/
	@Excel(name = "wt5", width = 15)
    @ApiModelProperty(value = "wt5")
	private String wt5;
	/**wt6*/
	@Excel(name = "wt6", width = 15)
    @ApiModelProperty(value = "wt6")
	private String wt6;
	/**wt7*/
	@Excel(name = "wt7", width = 15)
    @ApiModelProperty(value = "wt7")
	private String wt7;
	/**wt8*/
	@Excel(name = "wt8", width = 15)
    @ApiModelProperty(value = "wt8")
	private String wt8;
	/**yxid*/
	@Excel(name = "yxid", width = 15)
    @ApiModelProperty(value = "yxid")
	@Dict( dicCode="id", dictTable="TASK_CREATE", dicText="title")
	private String yxid;
	/**wgbh*/
	@Excel(name = "wgbh", width = 15)
    @ApiModelProperty(value = "wgbh")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
	/**sskhjl*/
	@Excel(name = "sskhjl", width = 15)
    @ApiModelProperty(value = "sskhjl")
	private String sskhjl;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
	@Dict(dicCode = "fxd_yxjg")
	private String status;
	@Dict(dicCode = "fxd_zffs")
	private String zffs;

	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
	@ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
	@ApiModelProperty(value = "updator")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String updator;
}
