package org.cmms.modules.khlc.khfagl.entity;

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
 * @Description: 考核方案评价对象表
 * @Author: jeecg-boot
 * @Date:   2021-02-19
 * @Version: V1.0
 */
@Data
@TableName("PMA_A_SCHEME_EVLOBJ_REL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_A_SCHEME_EVLOBJ_REL对象", description="考核方案评价对象表")
public class PmaASchemeEvlobjRel {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**方案id*/
	@Excel(name = "方案id", width = 15)
    @ApiModelProperty(value = "方案id")
	private String schemeId;
	/**评价对象类型*/
	@Excel(name = "评价对象类型", width = 15)
    @ApiModelProperty(value = "评价对象类型")
	private String evlObjType;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String zzbz;
	/**岗位标志*/
	@Excel(name = "岗位标志", dictTable = "hr_bas_post", dicText = "gwmc")
	@ApiModelProperty(value = "岗位标志")
	@Dict(dicCode = "gwbz", dictTable = "hr_bas_post", dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
	@ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private String yggh;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
	@ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "createTime")
	private Date createTime;
}
