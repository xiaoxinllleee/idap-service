package org.cmms.modules.tjfx.czxxtz.entity;

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
 * @Description: 村组信息台账
 * @Author: jeecg-boot
 * @Date:   2022-07-05
 * @Version: V1.0
 */
@Data
@TableName("v_tjfx_czxxtz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_tjfx_czxxtz对象", description="村组信息台账")
public class Czxxtz {
    
	/**wgbh*/
	@Excel(name = "所属网格", width = 15,dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
    @ApiModelProperty(value = "所属网格")
	@Dict(dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
	private String wgbh;
	/**sszh*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**hhbm*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**khmc*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**yhzgx*/
	@Excel(name = "与户主关系", width = 15, dicCode = "yhzgx")
    @ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**sjhm*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private String sjhm;
	/**dqckye*/
	@Excel(name = "定期存款余额", width = 15)
    @ApiModelProperty(value = "定期存款余额")
	private java.math.BigDecimal dqckye;
	/**hqckye*/
	@Excel(name = "活期存款余额", width = 15)
    @ApiModelProperty(value = "活期存款余额")
	private java.math.BigDecimal hqckye;
	/**dkye*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**bwbldkye*/
	@Excel(name = "表外贷款余额", width = 15)
    @ApiModelProperty(value = "表外贷款余额")
	private java.math.BigDecimal bwbldkye;
	/**bmkkh*/
	@Excel(name = "便民卡卡号", width = 15)
    @ApiModelProperty(value = "便民卡卡号")
	private String bmkkh;
	/**bmkye*/
	@Excel(name = "便民卡余额", width = 15)
    @ApiModelProperty(value = "便民卡余额")
	private String bmkye;
	/**ajdkye*/
	@Excel(name = "按揭贷款余额", width = 15)
    @ApiModelProperty(value = "按揭贷款余额")
	private String ajdkye;
	/**hnbmd*/
	@Excel(name = "是否符合行内白名单", width = 15,  dicCode = "sfbz")
    @ApiModelProperty(value = "是否符合行内白名单")
	@Dict(dicCode = "sfbz")
	private Integer hnbmd;
	/**czbmd*/
	@Excel(name = "是否符合村组白名单", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否符合村组白名单")
	@Dict(dicCode = "sfbz")
	private Integer czbmd;
	/**jysxed*/
	@Excel(name = "村组授信金额", width = 15)
    @ApiModelProperty(value = "村组授信金额")
	private Integer jysxed;
	/**bz*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
}
