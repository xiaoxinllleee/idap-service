package org.cmms.modules.khgl.khglgx.entity;

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
 * @Description: 关联关系明细
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
@Data
@TableName("V_KHGL_GLGX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_KHGL_GLGX对象", description="关联关系明细")
public class Vglgxmx {
    
	@ApiModelProperty(value = "序号")
	private Long xh;

	@Excel(name = "所属支行", width = 15, dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**镇*/
	@Excel(name = "镇", width = 15, dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "镇")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_YJYXDYGL", dicText="DYMC")
	private String yjyxdy;
	/**村*/
	@Excel(name = "村", width = 15, dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "村")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_EJYXDYGL", dicText="DYMC")
	private String ejyxdy;
	/**组*/
	@Excel(name = "组", width = 15, dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	@ApiModelProperty(value = "组")
	@Dict(dicCode="DYBH", dictTable="YXDYGL_SJYXDYGL", dicText="DYMC")
	private String sjyxdy;
	/**hhbm*/
	@Excel(name = "hhbm", width = 15)
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**yhzgx*/
	@Excel(name = "yhzgx", width = 15)
    @ApiModelProperty(value = "yhzgx")
	private String yhzgx;
	/**khmc*/
	@Excel(name = "khmc", width = 15)
    @ApiModelProperty(value = "khmc")
	private String khmc;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**sskhjl*/
	@Excel(name = "客户经理", width = 15, dicCode="YGGH",dictTable="HR_BAS_STAFF",dicText="YGXM")
	@ApiModelProperty(value = "客户经理")
	@Dict(dicCode="YGGH", dictTable="HR_BAS_STAFF", dicText="YGXM")
	private String sskhjl;
	/**khxz*/
	@ApiModelProperty(value = "客户性质（1：小额农贷，2：个人，3：企业，4：按揭）")
	@Excel(name = "客户性质", width = 15, dicCode = "khgl_khxz")
	@Dict(dicCode = "khgl_khxz")
	private Integer khxz;
}
