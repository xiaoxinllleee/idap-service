package org.cmms.modules.dklldj.lldjgl.glzhgl.entity;

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
 * @Description: 客户账号关联信息
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Data
@TableName("rate_khzhglxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_khzhglxxb对象", description="客户账号关联信息表")
public class rateKhzhglxxb {
	/**组织标识*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String zzbz;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**存款账号*/
	@Excel(name = "存款账号", width = 15)
    @ApiModelProperty(value = "存款账号")
	private String ckzh;
	/**账号名称*/
	@Excel(name = "账号名称", width = 15)
    @ApiModelProperty(value = "账号名称")
	private String zhmc;
	/**账号关系（1、本企业(人)，2、直系亲属，3、其它关系人，4、以他人名义存入本行）*/
	@Excel(name = "账号关系", width = 15,dicCode = "zhgx")
    @ApiModelProperty(value = "账号关系")
	@Dict(dicCode = "zhgx")
	private String zhgx;
	/**删除标志*/
    @ApiModelProperty(value = "删除标志")
	private Integer scbz;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入操作员*/
    @ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入标志*/
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**关联账号详细关系*/
	@Excel(name = "关联账号详细关系", width = 15)
    @ApiModelProperty(value = "关联账号详细关系")
	private String glzhxxgx;
	/**对应主卡号*/
	@Excel(name = "对应主卡号", width = 15)
    @ApiModelProperty(value = "对应主卡号")
	private String dyzkh;
}
