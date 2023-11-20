package org.cmms.modules.khgl.clkhxxgl.entity;

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
 * @Description: 存量客户信息管理
 * @Author: jeecg-boot
 * @Date:   2021-08-03
 * @Version: V1.0
 */
@Data
@TableName("V_khgl_clkhxxgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_khgl_clkhxxgl对象", description="存量客户信息管理")
public class VKhglClkhxxgl {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**jgdm*/
	@Excel(name = "机构名称", width = 15, dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**khmc*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**khlx*/
	@Excel(name = "客户类型", width = 15, dicCode = "lldjkhlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "lldjkhlx")
	private String khlx;
	/**xb*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
    @ApiModelProperty(value = "性别")
	@Dict(dicCode = "sex")
	private String xb;
	/**nl*/
	@Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
	private String nl;
	/**lxfs*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String lxfs;
	/**dz*/
	@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;
	/**sfktckyw*/
	@Excel(name = "是否开通存款业务", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通存款业务")
	@Dict(dicCode = "sfbz")
	private String sfktckyw;
	/**ckye*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**ckrpye*/
	@Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**sfktdkyw*/
	@Excel(name = "是否开通贷款业务", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通贷款业务")
	@Dict(dicCode = "sfbz")
	private String sfktdkyw;
	/**dkje*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**dkye*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**bldkye*/
	@Excel(name = "不良贷款余额", width = 15)
    @ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;
	/**bwbldkye*/
	@Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
	private java.math.BigDecimal bwbldkye;
	/**sfktsjyhyw*/
	@Excel(name = "是否开通手机银行", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通手机银行")
	@Dict(dicCode = "sfbz")
	private String sfktsjyhyw;
	/**sfbletcyw*/
	@Excel(name = "是否办理ETC", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理ETC")
	@Dict(dicCode = "sfbz")
	private String sfbletcyw;
	/**sfktsbk*/
	@Excel(name = "是否开通社保卡", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通社保卡")
	@Dict(dicCode = "sfbz")
	private String sfktsbk;
}
