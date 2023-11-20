package org.cmms.modules.yjgl.yjyy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 客户预约登记簿
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
@Data
@TableName("KHGXGL_YJYY_KHYYDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGXGL_YJYY_KHYYDJB对象", description="客户预约登记簿")
public class KhxxglYjyyKhyydjbVo {

	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private java.lang.String jgdm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
	@ApiModelProperty(value = "手机号码")
	private java.lang.String sjhm;

	/**预约编号*/
	@Excel(name = "预约编号", width = 15)
	@ApiModelProperty(value = "预约编号")
	private java.lang.String yybh;

	/**预约类型*/
	@Excel(name = "预约类型", width = 15,dicCode = "yylx")
	@ApiModelProperty(value = "预约类型")
	@Dict(dicCode = "yylx")
	private java.lang.String yylx;

	/**预约日期*/
	@Excel(name = "预约日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "预约日期")
	private java.util.Date yyrq;
	/**营销人工号*/
	@Excel(name = "营销人工号", width = 15)
	@ApiModelProperty(value = "营销人工号")
	private java.lang.String yxrgh;
	/**营销比例*/
	@Excel(name = "营销比例", width = 15)
	@ApiModelProperty(value = "营销比例")
	private java.math.BigDecimal yxbl;
	/**申报状态*/
	@Excel(name = "申报状态", width = 15,dicCode = "sbzt")
	@ApiModelProperty(value = "申报状态")
	@Dict(dicCode = "sbzt")
	private java.lang.String sbzt;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private java.lang.String bz;

}
