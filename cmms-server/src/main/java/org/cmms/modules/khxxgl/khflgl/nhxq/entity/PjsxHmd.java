package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

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
 * @Description: 评级授信黑名单
 * @Author: jeecg-boot
 * @Date:   2023-11-15
 * @Version: V1.0
 */
@Data
@TableName("pjsx_hmd")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="pjsx_hmd对象", description="评级授信黑名单")
public class PjsxHmd {
    
	/**编号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
	private String id;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "评定人", width = 15,dictTable = "hr_bas_staff",dicText = "ygxm",dicCode = "yggh")
    @ApiModelProperty(value = "updator")
	@Dict(dictTable = "hr_bas_staff",dicText = "ygxm",dicCode = "yggh")
	private String updator;
	/**pdsj*/
	@Excel(name = "评定时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "pdsj")
	private Date pdsj;
	/**khmc*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**zjhm*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**rdly*/
	@Excel(name = "认定理由", width = 15,dicCode = "pjsx_hmd_rdly")
    @ApiModelProperty(value = "rdly")
	@Dict(dicCode = "pjsx_hmd_rdly")
	private String rdly;
	/**rdzh*/
	@Excel(name = "认定支行", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "rdzh")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String rdzh;
	@Excel(name = "认定支行", width = 15,dicCode = "pjsx_hmd_status")
	@Dict(dicCode = "pjsx_hmd_status")
	private String status;
	@TableField(exist = false)
	private String yj;
	@TableField(exist = false)
	private String ty;
}
