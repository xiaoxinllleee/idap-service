package org.cmms.modules.ywgl.ckyw.jxlcyw.yglcjxmx.entity;

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
 * @Description: 员工揽储绩效明细
 * @Author: jeecg-boot
 * @Date:   2021-10-27
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_YGLCJXMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_YGLCJXMX对象", description="员工揽储绩效明细")
public class Yglcjxmx {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
	@DateTimeFormat(pattern="yyyy-MM")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15,dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	@ApiModelProperty(value = "岗位标识")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	@ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String khjg;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String khrq;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**存款账号*/
	@Excel(name = "存款账号", width = 15)
	@ApiModelProperty(value = "存款账号")
	private String ckzh;
	/**账号名称*/
	@Excel(name = "账号名称", width = 15)
	@ApiModelProperty(value = "账号名称")
	private String zhmc;
	/**账号性质*/
	@Excel(name = "账号性质", width = 15,dicCode = "zhxz")
	@ApiModelProperty(value = "账号性质")
	@Dict(dicCode = "zhxz")
	private Integer zhxz;
	/**关联比率*/
	@Excel(name = "关联比率(%)", width = 15)
	@ApiModelProperty(value = "关联比率(%)")
	private java.math.BigDecimal glbl;
	/**关联标识（0：自动关联 1：手工关联）*/
	@Excel(name = "关联标识", width = 15,dicCode = "glbz")
	@ApiModelProperty(value = "关联标识")
	@Dict(dicCode = "glbz")
	private Integer glbz;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
	@ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款日平余额*/
	@Excel(name = "月存款日平余额", width = 15)
	@ApiModelProperty(value = "月存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**年存款日平余额*/
	@Excel(name = "年存款日平余额", width = 15)
	@ApiModelProperty(value = "年存款日平余额")
	private java.math.BigDecimal nckrpye;
	/**折算后存款余额*/
	@Excel(name = "折算后存款余额", width = 15)
	@ApiModelProperty(value = "折算后存款余额")
	private java.math.BigDecimal zshckye;
	/**折算后存款日平余额*/
	@Excel(name = "折算后月存款日平余额", width = 15)
	@ApiModelProperty(value = "折算后月存款日平余额")
	private java.math.BigDecimal zshckrpye;
	/**折算后年存款日平余额*/
	@Excel(name = "折算后年存款日平余额", width = 15)
	@ApiModelProperty(value = "折算后年存款日平余额")
	private java.math.BigDecimal zshnckrpye;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入操作员*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**关联id*/
    @ApiModelProperty(value = "关联id")
	private Long glid;
	/**有效标识（0；无效 1：有效）*/
    @ApiModelProperty(value = "有效标识")
	private Integer yxbz;




}
