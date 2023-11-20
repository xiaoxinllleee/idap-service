package org.cmms.modules.xdgl.dksp.dkzqywsp.entity;

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
 * @Description: 贷款展期业务审批信息表
 * @Author: jeecg-boot
 * @Date:   2023-10-14
 * @Version: V1.0
 */
@Data
@TableName("v_cams_zqywsp_ywzc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_cams_zqywsp_ywzc对象", description="贷款展期业务审批信息表")
public class VCamsZqywspYwzc {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	/**sszh*/
	@Excel(name = "sszh", width = 15)
	@ApiModelProperty(value = "sszh")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private java.lang.String sszh;
	/**khmc*/
	@Excel(name = "khmc", width = 15)
	@ApiModelProperty(value = "khmc")
	private java.lang.String khmc;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
	@ApiModelProperty(value = "khmc")
	private java.lang.String zjhm;
	/**dkzh*/
	@Excel(name = "dkzh", width = 15)
	@ApiModelProperty(value = "dkzh")
	private java.lang.String dkzh;
	/**dkzl*/
	@Excel(name = "dkzl", width = 15)
	@ApiModelProperty(value = "dkzl")
	@Dict(dicCode = "dkzl")
	private java.lang.String dkzl;
	/**yhtbh*/
	@Excel(name = "yhtbh", width = 15)
	@ApiModelProperty(value = "yhtbh")
	private java.lang.String yhtbh;
	/**ydkrq*/
	@Excel(name = "ydkrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "ydkrq")
	private java.util.Date ydkrq;
	/**ydkje*/
	@Excel(name = "ydkje", width = 15)
	@ApiModelProperty(value = "ydkje")
	private java.math.BigDecimal ydkje;
	/**ydqrq*/
	@Excel(name = "ydqrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "ydqrq")
	private java.util.Date ydqrq;
	/**bz*/
	@Excel(name = "bz", width = 15)
	@ApiModelProperty(value = "bz")
	private java.lang.String bz;
	/**businessNumber*/
	@Excel(name = "businessNumber", width = 15)
	@ApiModelProperty(value = "businessNumber")
	private java.lang.String businessNumber;
	/**processId*/
	@Excel(name = "processId", width = 15)
	@ApiModelProperty(value = "processId")
	private java.lang.String processId;
	/**sqzqje*/
	@Excel(name = "sqzqje", width = 15)
	@ApiModelProperty(value = "sqzqje")
	private java.math.BigDecimal sqzqje;
	/**ll*/
	@Excel(name = "ll", width = 15)
	@ApiModelProperty(value = "ll")
	private java.lang.String ll;
	/**bszrr*/
	@Excel(name = "bszrr", width = 15)
	@ApiModelProperty(value = "bszrr")
	private java.lang.String bszrr;
	/**glzrr*/
	@Excel(name = "glzrr", width = 15)
	@ApiModelProperty(value = "glzrr")
	private java.lang.String glzrr;
	/**sqzqrq*/
	@Excel(name = "sqzqrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "sqzqrq")
	private java.util.Date sqzqrq;
	/**sqzqqx*/
	@Excel(name = "sqzqqx", width = 15)
	@ApiModelProperty(value = "sqzqqx")
	private java.lang.String sqzqqx;
	/**sqzqhkly*/
	@Excel(name = "sqzqhkly", width = 15)
	@ApiModelProperty(value = "sqzqhkly")
	private java.lang.String sqzqhkly;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
	@ApiModelProperty(value = "createBy")
	@Dict(dicCode="yggh",dictTable="hr_bas_staff",dicText="ygxm")
	private java.lang.String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
	@ApiModelProperty(value = "updateBy")
	private java.lang.String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	/**result*/
	@Excel(name = "result", width = 15)
	@ApiModelProperty(value = "result")
	@Dict(dicCode = "lczt")
	private java.lang.Integer result;
	/**procInstId*/
	@Excel(name = "procInstId", width = 15)
	@ApiModelProperty(value = "procInstId")
	private java.lang.String procInstId;
}
