package org.cmms.modules.khgl.khhmc.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 客户附加信息
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Data
public class KhfjxxglExp {
	@Excel(name = "所属网格", width = 15)
	private String wgmc;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	private String wgbh;
	/**证件号码*/
	@TableId(type = IdType.ASSIGN_ID)
	@Excel(name = "证件号码", width = 30)
    @ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = true)
	private String zjhm;




	/**是否诉讼*/
	@ApiModelProperty(value = "是否诉讼")
	@Excel(name = "是否诉讼", width = 15)
	@Dict(dicCode = "sfbz")
	private String sfss;
	/**是否党员*/
	@ApiModelProperty(value = "是否党员")
	@Excel(name = "是否党员", width = 15)
	@Dict(dicCode = "sfbz")
	private String sfdy;

	/**是否公职人员*/
	@Excel(name = "是否公职人员", width = 15)
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否公职人员")
	private String sfgzry;

	/**是否退岗教师*/
	@ApiModelProperty(value = "是否退岗教师")
	@Excel(name = "是否退岗教师", width = 15)
	@Dict(dicCode = "sfbz")
	private String sftgjs;

	/**是否贫困户*/
	@Excel(name = "是否脱贫及监测户", width = 20)
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否贫困户")
	private String sfpkh;

	/**是否五保低保户*/
	@Excel(name = "是否五保低保户", width = 15)
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否五保低保户")
	private String sfdb;


	/**是否重大疾病*/
	@Excel(name = "是否重大疾病", width = 15)
	@Dict(dicCode = "sfbz")
	@ExcelVerify(interHandler = true)
	private String sfzdjb;

	/**是否诈骗人员*/
	@ApiModelProperty(value = "是否诈骗人员")
	@Excel(name = "是否诈骗人员", width = 15)
	@Dict(dicCode = "sfbz")
	private String sfzpry;

	/**是否非法集资*/
	@Excel(name = "是否非法集资", width = 15)
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否非法集资")
	private String sfffjz;

	/**是否吸毒*/
	@ApiModelProperty(value = "是否吸毒")
	@Excel(name = "是否吸毒", width = 15)
	@Dict(dicCode = "sfbz")
	private String sfxdry;

	/**是否服刑*/
	@ApiModelProperty(value = "是否服刑人员")
	@Excel(name = "是否服刑人员", width = 15)
	@Dict(dicCode = "sfbz")
	private String sffx;
}
