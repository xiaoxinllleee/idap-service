package org.cmms.modules.khgl.xjjl.entity;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @Description: 聚合商户巡检记录
 * @Author: jeecg-boot
 * @Date:   2022-03-14
 * @Version: V1.0
 */
@Data
@TableName("APP_JHSH_XJJL")
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
@ApiModel(value="APP_JHSH_XJJL对象", description="聚合商户巡检记录")
public class AppJhshXjjl {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id")
	private String id;
	/**巡检结果（1正常，2异常）*/
	@Excel(name = "巡检结果（1正常，2异常）", width = 15)
    @ApiModelProperty(value = "巡检结果（1正常，2异常）")
	@Dict(dicCode = "xjjg")
	private int xjjg;
	/**巡检人*/
	@Excel(name = "巡检人", width = 15)
    @ApiModelProperty(value = "巡检人")
	private String xjr;
	/**聚合商户号*/
	@Excel(name = "聚合商户号", width = 15)
	@ApiModelProperty(value = "聚合商户号")
	private String mchntId;
	/**巡检时间*/
	@Excel(name = "巡检时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "巡检时间")
	private Date xjsj;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;

	/**巡检要素1状态（1正常，2异常）*/
	@Excel(name = "巡检要素1状态", width = 15)
	@ApiModelProperty(value = "巡检要素1状态")
	private Integer type1;
	/** 巡检要素1说明*/
	@Excel(name = "巡检要素1说明", width = 15)
	@ApiModelProperty(value = "巡检要素1说明")
	private String ztsm1;

	/**巡检要素2状态（1正常，2异常）*/
	@Excel(name = "巡检要素2状态", width = 15)
	@ApiModelProperty(value = "巡检要素2状态")
	private Integer type2;
	/** 巡检要素2说明*/
	@Excel(name = "巡检要素2说明", width = 15)
	@ApiModelProperty(value = "巡检要素2说明")
	private String ztsm2;

	/**巡检要素3状态（1正常，2异常）*/
	@Excel(name = "巡检要素3状态", width = 15)
	@ApiModelProperty(value = "巡检要素3状态")
	private Integer type3;
	/** 巡检要素3说明*/
	@Excel(name = "巡检要素3说明", width = 15)
	@ApiModelProperty(value = "巡检要素3说明")
	private String ztsm3;

	/**巡检要素4状态（1正常，2异常）*/
	@Excel(name = "巡检要素4状态", width = 15)
	@ApiModelProperty(value = "巡检要素4状态")
	private Integer type4;
	/** 巡检要素4说明*/
	@Excel(name = "巡检要素4说明", width = 15)
	@ApiModelProperty(value = "巡检要素4说明")
	private String ztsm4;

	/**巡检要素5状态（1正常，2异常）*/
	@Excel(name = "巡检要素5状态", width = 15)
	@ApiModelProperty(value = "巡检要素5状态")
	private Integer type5;
	/** 巡检要素5说明*/
	@Excel(name = "巡检要素5说明", width = 15)
	@ApiModelProperty(value = "巡检要素5说明")
	private String ztsm5;

	/**巡检要素6状态（1正常，2异常）*/
	@Excel(name = "巡检要素6状态", width = 15)
	@ApiModelProperty(value = "巡检要素6状态")
	private Integer type6;
	/** 巡检要素1说明*/
	@Excel(name = "巡检要素6说明", width = 15)
	@ApiModelProperty(value = "巡检要素6说明")
	private String ztsm6;

	/**巡检要素7状态（1正常，2异常）*/
	@Excel(name = "巡检要素7状态", width = 15)
	@ApiModelProperty(value = "巡检要素7状态")
	private Integer type7;
	/** 巡检要素7说明*/
	@Excel(name = "巡检要素7说明", width = 15)
	@ApiModelProperty(value = "巡检要素7说明")
	private String ztsm7;

	/**巡检要素8状态（1正常，2异常）*/
	@Excel(name = "巡检要素8状态", width = 15)
	@ApiModelProperty(value = "巡检要素8状态")
	private Integer type8;
	/** 巡检要素8说明*/
	@Excel(name = "巡检要素8说明", width = 15)
	@ApiModelProperty(value = "巡检要素8说明")
	private String ztsm8;

	/**商户签名*/
	@Excel(name = "商户签名", width = 15)
	@ApiModelProperty(value = "商户签名")
	@Dict(dictTable = "APP_FJXX",dicText = "FWLJ",dicCode = "id")
	private String shqm;

	/**巡检员签名*/
	@Excel(name = "巡检员签名", width = 15)
	@ApiModelProperty(value = "巡检员签名")
	@Dict(dictTable = "APP_FJXX",dicText = "FWLJ",dicCode = "id")
	private String xjyqm;

	/**附件信息*/
	@Excel(name = "商户签名",width = 15)
    @ApiModelProperty(value = "商户签名")
    @Dict(dictTable = "APP_FJXX",dicText = "FWLJ",dicCode = "id")
    private String fjxx;

}
