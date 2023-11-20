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
 * @Description: 关联关系管理
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
@Data
@TableName("KHGL_GLGXGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_GLGXGL对象", description="关联关系管理")
public class Glgxgl {
    
	/**转移类型(1:片区，2：客户)*/
    @ApiModelProperty(value = "转移类型(1:片区，2：客户)")
	@Excel(name = "转移类型", width = 15, dicCode = "zylx")
	@Dict(dicCode = "zylx")
	private String zylx;
	/**所属支行*/
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
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户性质（1：小额农贷，2：个人，3：企业，4：按揭）*/
    @ApiModelProperty(value = "客户性质（1：小额农贷，2：个人，3：企业，4：按揭）")
	@Excel(name = "客户性质", width = 15, dicCode = "khgl_khxz")
	@Dict(dicCode = "khgl_khxz")
	private String khxz;
	/**原客户经理*/
	@Excel(name = "原客户经理", width = 15, dicCode="YGGH",dictTable="HR_BAS_STAFF",dicText="YGXM")
	@ApiModelProperty(value = "原客户经理")
	@Dict(dicCode="YGGH", dictTable="HR_BAS_STAFF", dicText="YGXM")
	private String ykhjl;
	/**转移后客户经理*/
	@Excel(name = "转移后客户经理", width = 15, dicCode="YGGH",dictTable="HR_BAS_STAFF",dicText="YGXM")
	@ApiModelProperty(value = "转移后客户经理")
	@Dict(dicCode="YGGH", dictTable="HR_BAS_STAFF", dicText="YGXM")
	private String zyhkhjl;

	@ApiModelProperty(value = "操作员")
	private String czy;


	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date czsj;
}
