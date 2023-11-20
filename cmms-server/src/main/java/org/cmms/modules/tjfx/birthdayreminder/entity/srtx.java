package org.cmms.modules.tjfx.birthdayreminder.entity;

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
 * @Description: sd
 * @Author: jeecg-boot
 * @Date:   2022-07-14
 * @Version: V1.0
 */
@Data
@TableName("TJFX_SRTX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_SRTX对象", description="sd")
public class srtx {

	@TableId(value = "id ",type = IdType.ASSIGN_ID)
	private  String id;

	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String name;
	/**日期*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "日期")
	private Date days;
	/**性别*/
	@Excel(name = "性别", width = 15,dicCode = "sex")
    @ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String sex;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String doornumbercode;
	/**年龄*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String age;
	/**身份证*/
	@Excel(name = "身份证", width = 15)
    @ApiModelProperty(value = "身份证")
	private String idnumber;
	/**家庭地址*/
	@Excel(name = "家庭地址", width = 15)
    @ApiModelProperty(value = "家庭地址")
	private String homeaddress;
	/**是否达标*/
	@Excel(name = "是否达标", width = 15,dicCode = "gzrwSfdb")
	@Dict(dicCode = "gzrw_sfdb")
    @ApiModelProperty(value = "是否达标")
	private String 	gzrwSfdb;
	/**达标原因*/
	@Excel(name = "达标原因", width = 15)
    @ApiModelProperty(value = "达标原因")
	private String standardcause;
	/**操作*/

	@ApiModelProperty(value = "操作")
/*	@Dict(dicCode = "operate")*/
	private String operate;


	/**操作*/
/*	@Excel(name = "操作人", width = 15)*/
	@ApiModelProperty(value = "操作人")
	private String operatePeople;


	/**所属支行 id**
	 *
	 */
	private String sszh;

	/**原所属乡镇**
	 *
	 */
	private String yssxz;
	/**
	 * 是否重要
	 */
	@Dict(dicCode = "if_master")
	private String ifMaster;

	private String   csrqstr;

}
