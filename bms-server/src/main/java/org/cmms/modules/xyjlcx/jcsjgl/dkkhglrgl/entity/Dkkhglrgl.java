package org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.entity;

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
 * @Description: 贷款客户关联人管理
 * @Author: jeecg-boot
 * @Date:   2021-08-10
 * @Version: V1.0
 */
@Data
@TableName("Credit_dkkhglrgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Credit_dkkhglrgl对象", description="贷款客户关联人管理")
public class Dkkhglrgl {

	/**借款人证件号码*/
	@Excel(name = "借款人证件号码", width = 15)
    @ApiModelProperty(value = "借款人证件号码")
	@ExcelVerify(notNull = true)
	private String jkrzjhm;
	/**借款人名称*/
	@Excel(name = "借款人名称", width = 15)
    @ApiModelProperty(value = "借款人名称")
	@ExcelVerify(notNull = true)
	private String jkrmc;

	/**关联人证件号码*/
	@Excel(name = "关联人证件号码", width = 15)
	@ApiModelProperty(value = "关联人证件号码")
	@ExcelVerify(notNull = true)
	private String glrzjhm;


	/**关联人证件类型*/
	@Excel(name = "关联人证件类型", width = 15,dicCode = "dkjkpt_zjlx")
    @ApiModelProperty(value = "关联人证件类型")
	@Dict(dicCode = "dkjkpt_zjlx")
	@ExcelVerify(notNull = true)
	private Integer glrzjlx;

	/**关联人姓名*/
	@Excel(name = "关联人姓名", width = 15)
    @ApiModelProperty(value = "关联人姓名")
	@ExcelVerify(notNull = true)
	private String glrxm;
	/**关联关系*/
	@Excel(name = "关联关系", width = 15,dicCode = "dkjkpt_glgx")
    @ApiModelProperty(value = "关联关系")
	@ExcelVerify(notNull = true)
	@Dict(dicCode = "dkjkpt_glgx")
	private Integer glgx;
	/**关联人工作单位*/
	@Excel(name = "关联人工作单位", width = 15)
    @ApiModelProperty(value = "关联人工作单位")

	private String glrgzdw;



	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;

	/**关联人联系电话*/
	@Excel(name = "关联人联系电话", width = 15)
	@ApiModelProperty(value = "关联人联系电话")
	@ExcelVerify(interHandler = true)
	private String glrlxdh;

	//关联人报告编号
	@TableField(exist = false)
	private String glrbgbh;
	//关联人证件类型
	@TableField(exist = false)
	private String zjlxDictText;
	//关联人关联类型
	@TableField(exist = false)
	private String gllxDictText;
}
