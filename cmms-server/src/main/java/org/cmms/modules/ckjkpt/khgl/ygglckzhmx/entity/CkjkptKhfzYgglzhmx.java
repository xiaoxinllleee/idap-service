package org.cmms.modules.ckjkpt.khgl.ygglckzhmx.entity;

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
 * @Description: 员工关联存款账户明细
 * @Author: jeecg-boot
 * @Date:   2021-07-09
 * @Version: V1.0
 */
@Data
@TableName("ckjkpt_khfz_ygglzhmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ckjkpt_khfz_ygglzhmx对象", description="员工关联存款账户明细")
public class CkjkptKhfzYgglzhmx {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**组织标识*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "所属岗位", width = 15,dicCode = "gwbz", dictTable = "Hr_bas_post",dicText = "gwmc" )
    @ApiModelProperty(value = "岗位标识")
	@Dict(dicCode = "gwbz",dictTable = "Hr_bas_post",dicText = "gwmc")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15,dicCode = "ywjgdm",dictTable = "Hr_bas_organization",dicText = "zzjc")
	@ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm",dictTable = "Hr_bas_organization",dicText = "zzjc")
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
	/**卡号*/
	@Excel(name = "卡号", width = 15)
	@ApiModelProperty(value = "卡号")
	private String kh;
	/**账号名称*/
	@Excel(name = "户名", width = 15)
	@ApiModelProperty(value = "户名")
	private String zhmc;
	/**电环号码*/
	@Excel(name = "电话号码", width = 15)
	@ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**存期*/
	@Excel(name = "存期", width = 15)
	@ApiModelProperty(value = "存期")
	private String cq;
	/**本期起息日*/
	@Excel(name = "本期起息日", width = 15)
	@ApiModelProperty(value = "本期起息日")
	private String bqqxr;
	/**本期截至日*/
	@Excel(name = "本期截至日", width = 15)
	@ApiModelProperty(value = "本期截至日")
	private String bqjzr;
	/**账户性质*/
	@Excel(name = "账号性质", width = 15,dicCode = "zhxz")
	@ApiModelProperty(value = "账户性质")
	@Dict(dicCode = "zhxz")
	private Integer zhxz;
	/**关联比率*/
	@Excel(name = "关联比率(%)", width = 15)
	@ApiModelProperty(value = "关联比率")
	private java.math.BigDecimal glbl;
	/**关联标识（0：自动关联 1：手工关联）*/
	@Excel(name = "关联标识", width = 15,dicCode = "glbz")
	@ApiModelProperty(value = "关联标识")
	@Dict(dicCode = "glbz")
	private Integer glbz;
	/**有效标识（0；无效 1：有效）*/
    @ApiModelProperty(value = "有效标识")
	private Integer yxbz;
	/**存量余额*/
	@Excel(name = "(年初)存款余额", width = 15)
	@ApiModelProperty(value = "存量余额")
	private java.math.BigDecimal clye;
	/**存量月日平*/
	@Excel(name = "(年初)存款月日平", width = 15)
	@ApiModelProperty(value = "存量月日平")
	private java.math.BigDecimal clyrp;
	/**存量年日平*/
	@Excel(name = "(年初)存款年日平", width = 15)
	@ApiModelProperty(value = "存量年日平")
	private java.math.BigDecimal clnrp;
	/**存款余额*/
	@Excel(name = "(本月)存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款日平余额*/
	@Excel(name = "(本月)存款月日平", width = 15)
    @ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**年存款日平余额*/
	@Excel(name = "(本月)存款年日平", width = 15)
    @ApiModelProperty(value = "年存款日平余额")
	private java.math.BigDecimal nckrpye;
	/**折算后存款余额*/
	@Excel(name = "(关联考核数据)存款余额", width = 15)
	@ApiModelProperty(value = "折算后存款余额")
	private java.math.BigDecimal zshckye;
	/**折算后存款日平余额*/
	@Excel(name = "(关联考核数据)存款月日平", width = 15)
	@ApiModelProperty(value = "折算后存款日平余额")
	private java.math.BigDecimal zshckrpye;
	/**折算后年存款日平余额*/
	@Excel(name = "(关联考核数据)存款年日平", width = 15)
	@ApiModelProperty(value = "折算后年存款日平余额")
	private java.math.BigDecimal zshnckrpye;

	/**本月交易笔数*/
	@ApiModelProperty(value = "本月交易笔数")
	private Long byjybs;
	/**是否首次开户*/
	@ApiModelProperty(value = "是否首次开户")
	private Integer sfsckh;
	/**是否贷款户*/
	@ApiModelProperty(value = "是否贷款户")
	private Integer sfdkh;
	/**关联ID*/
	@ApiModelProperty(value = "关联ID")
	private Long glid;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@ApiModelProperty(value = "录入标识")
	private Integer lrbz;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入操作员*/
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;
}
