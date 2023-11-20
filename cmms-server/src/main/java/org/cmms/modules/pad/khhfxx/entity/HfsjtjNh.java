package org.cmms.modules.pad.khhfxx.entity;

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
 * @Description: 回访数据统计
 * @Author: jeecg-boot
 * @Date:   2023-04-17
 * @Version: V1.0
 */
@Data
@TableName("V_TJFX_HFSJTJ_NH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_TJFX_HFSJTJ_NH对象", description="回访数据统计")
public class HfsjtjNh {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;

	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**所属支行*/
	@Excel(name = "走访支行", width = 15)
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zfzh;

	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
	private java.lang.String ygxm;
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private java.lang.String yggh;
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "zjhm")
	private java.lang.String zjhm;
	@Excel(name = "回访日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "回访日期")
	private java.util.Date hfrq;
	@Excel(name = "走访次数", width = 15)
    @ApiModelProperty(value = "走访次数")
	private java.lang.Integer zfcs;
	@Excel(name = "是否有效走访", width = 15)
    @ApiModelProperty(value = "是否有效走访")
	@Dict(dicCode = "sfbz")
	private java.lang.String sfyxzf;

	@Excel(name = "走访原因", width = 15)
	@ApiModelProperty(value = "走访原因")
	@Dict(dicCode = "zfyy")
	private java.lang.String zfyy;

	@Excel(name = "归属情况", width = 15)
	@ApiModelProperty(value = "归属情况")
	@Dict(dicCode = "gsqk")
	private java.lang.String gsqk;
}
