package org.cmms.modules.hr.yggl.ygxxgl.entity;

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
 * @Description: 岗位表
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
@Data
@TableName("v_hr_bas_staff_post")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_hr_bas_staff_post对象", description="岗位表")
public class Vhrbasstaffpost {


	@Excel(name = "所属支行", width = 15, dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String zzbz;
	/**zzjc*/
	@Excel(name = "zzjc", width = 15)
	@ApiModelProperty(value = "zzjc")
	private String zzjc;
	/**gwbz*/
	@Excel(name = "gwbz", dictTable = "hr_bas_post", dicText = "gwmc")
    @ApiModelProperty(value = "gwbz")
	@Dict(dicCode = "gwbz", dictTable = "hr_bas_post", dicText = "gwmc")
	private Integer gwbz;
	/**gwmc*/
	@Excel(name = "gwmc", width = 15)
	@ApiModelProperty(value = "gwmc")
	private String gwmc;
	/**yggh*/
	@Excel(name = "yggh", width = 15)
    @ApiModelProperty(value = "yggh")
	private String yggh;
	/**ygxm*/
	@Excel(name = "ygxm", width = 15)
    @ApiModelProperty(value = "ygxm")
	private String ygxm;
	/**khjlbz*/
	@Excel(name = "khjlbz", width = 15)
    @ApiModelProperty(value = "khjlbz")
	private String khjlbz;

	/**gyh*/
	@Excel(name = "gyh", width = 15)
	@ApiModelProperty(value = "gyh")
	private String gyh;



}
