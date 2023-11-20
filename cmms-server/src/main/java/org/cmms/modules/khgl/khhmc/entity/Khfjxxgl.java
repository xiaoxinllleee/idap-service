package org.cmms.modules.khgl.khhmc.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户附加信息
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Data
@TableName("KHGL_KHHMCXX_FJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_KHHMCXX_FJXX对象", description="客户附加信息管理")
public class Khfjxxgl {

	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@TableId(type = IdType.ASSIGN_ID)
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@ExcelVerify(notNull = true)
	private String zjhm;

	/**是否吸毒*/
	@ApiModelProperty(value = "是否吸毒")
	@Excel(name = "是否吸毒", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	private String sfxdry;


	/**是否诉讼*/
	@ApiModelProperty(value = "是否诉讼")
	@Excel(name = "是否诉讼", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	private String sfss;


	/**是否五保低保户*/
	@Excel(name = "是否五保低保户", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否五保低保户")
	private String sfdb;


	/**是否退岗教师*/
	@ApiModelProperty(value = "是否退岗教师")
	@Excel(name = "是否退岗教师", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	private String sftgjs;


	/**是否党员*/
	@ApiModelProperty(value = "是否党员")
	@Excel(name = "是否党员", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	private String sfdy;

	/**是否公职人员*/
	@Excel(name = "是否公职人员", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否公职人员")
	private String sfgzry;

	/**是否服刑*/
	@ApiModelProperty(value = "是否服刑人员")
	@Excel(name = "是否服刑人员", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	private String sffx;



	/**是否诈骗人员*/
	@ApiModelProperty(value = "是否诈骗人员")
	@Excel(name = "是否诈骗人员", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	private String sfzpry;


	/**是否重大疾病*/
	@ApiModelProperty(value = "是否重大疾病")
	@Excel(name = "是否重大疾病", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ExcelVerify(interHandler = true)
	private String sfzdjb;




	/**是否贫困户*/
	//@Excel(name = "是否贫困户", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否贫困户")
	private String sfpkh;

	/**是否非法集资*/
	//@Excel(name = "是否非法集资", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否非法集资")
	private String sfffjz;
	/**是否开通信用卡*/
	//@Excel(name = "是否开通信用卡", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通信用卡")
	private String sfktxyk;
	/**是否开通福民卡*/
	//@Excel(name = "是否开通福民卡", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通福民卡")
	private String sfktfmk;
	/**是否开扫码付*/
	//@Excel(name = "是否开扫码付", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否开扫码付")
	private String sfktsmf;
	/**是否开通POS机*/
	//@Excel(name = "是否开通POS机", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通POS机")
	private String sfktpos;
	/**是否开通聚合支付*/
	//@Excel(name = "是否开通聚合支付", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否开通聚合支付")
	private String sfktjhzf;
	/**是否办理E支付*/
	//@Excel(name = "是否办理E支付", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理E支付")
	private String sfblezf;
	/**是否办理E缴费*/
	//@Excel(name = "是否办理E缴费", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理E缴费")
	private String sfblejf;
	/**是否办理助农终端*/
	//@Excel(name = "是否办理助农终端", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理助农终端")
	private String sfblznzd;
	/**是否办理理财业务*/
	//@Excel(name = "是否办理理财业务", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理理财业务")
	private String sfbllcyw;
	/**是否办理代理保险业务*/
	//@Excel(name = "是否办理代理保险业务", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否办理代理保险业务")
	private String sfbldlbx;
	/**是否关注我行公众号*/
	//@Excel(name = "是否关注我行公众号", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否关注我行公众号")
	private String sfgzgzh;

	@Excel(name = "是否涉案", width = 15, dicCode = "sfbz")
	@Dict(dicCode = "sfbz")
	@ApiModelProperty(value = "是否涉案")
	private String sfsa;
	/**患病记录*/
	//@Excel(name = "患病记录", width = 15)
    @ApiModelProperty(value = "患病记录")
	private String hbjl;
	/**创建人*/
	//@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	//@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	//@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	//@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
