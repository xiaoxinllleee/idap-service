package org.cmms.modules.ywgl.cdkfx.dkdqshlmx.entity;

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
 * @Description: 贷款到期收回率明细
 * @Author: jeecg-boot
 * @Date:   2021-07-02
 * @Version: V1.0
 */
@Data
@TableName("MID_DMPM_DKYEBMXQKTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MID_DMPM_DKYEBMXQKTJ对象", description="贷款到期收回率明细")
public class MidDmpmDkyebmxqktjT {

	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String fiscalDate;

	@Excel(name = "支行名称",width = 15,dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	private	String zhjgdm;

	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgdm;

	/**客户经理标识*/
	@Excel(name = "客户经理姓名", width = 15,dicCode = "khjlbh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	@ApiModelProperty(value = "客户经理标识")
	@Dict(dicCode = "khjlbh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String custid;
	/**责任客户经理标识*/
	@Excel(name = "责任客户经理标识", width = 15)
    @ApiModelProperty(value = "责任客户经理标识")
	private String custidzr;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**科目号*/
	@Excel(name = "科目号", width = 15)
	@ApiModelProperty(value = "科目号")
	private String kmh;
	/**贷款金额(元)*/
	@Excel(name = "贷款金额(元)", width = 15)
	@ApiModelProperty(value = "贷款金额(元)")
	private java.math.BigDecimal dkje;
	/**贷款余额(元)*/
	@Excel(name = "贷款余额(元)", width = 15)
	@ApiModelProperty(value = "贷款余额(元)")
	private java.math.BigDecimal dkye;
	/**贷款发放日期*/
	@Excel(name = "贷款发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "贷款发放日期")
	private Date dkffrq;
	/**贷款到期日期*/
	@Excel(name = "贷款到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "贷款到期日期")
	private Date dkdqrq;
	/**发放标志*/
	@Excel(name = "发放标志", width = 15,dicCode = "ffbz")
	@ApiModelProperty(value = "发放标志")
	@Dict(dicCode = "ffbz")
	private Integer ffbz;
	/**到期标志*/
	@Excel(name = "到期标志", width = 15,dicCode = "dqbz")
	@ApiModelProperty(value = "到期标志")
	@Dict(dicCode = "dqbz")
	private Integer dqbz;
	/**收回标志*/
	@Excel(name = "收回标志", width = 15,dicCode = "shbz")
	@ApiModelProperty(value = "收回标志")
	@Dict(dicCode = "shbz")
	private Integer shbz;
	/**录入时间*/
	//@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	//@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入操作员*/
	//@Excel(name = "录入操作员", width = 15)
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;



//	/**包收责任人*/
//	@Excel(name = "包收责任人", width = 15)
//    @ApiModelProperty(value = "包收责任人")
//	private String bszrr;
//	/**包收责任人暂扣保证金*/
//	@Excel(name = "包收责任人暂扣保证金", width = 15)
//    @ApiModelProperty(value = "包收责任人暂扣保证金")
//	private java.math.BigDecimal bszrrzkbzj;
//	/**是否2019年后新增不良*/
//	@Excel(name = "是否2019年后新增不良", width = 15)
//    @ApiModelProperty(value = "是否2019年后新增不良")
//	private Integer sfxzbl;
//	/**管理责任人暂扣保证金*/
//	@Excel(name = "管理责任人暂扣保证金", width = 15)
//    @ApiModelProperty(value = "管理责任人暂扣保证金")
//	private java.math.BigDecimal glzrrzkbzj;
//	/**beginday*/
//	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "开始日期")
//	private Date beginday;
//	/**endday*/
//	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "结束日期")
//	private Date endday;
//	/**最早欠息日*/
//	@Excel(name = "最早欠息日", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "最早欠息日")
//	private Date zzqxrq;
//	/**bjyqys*/
//    @ApiModelProperty(value = "bjyqys")
//	private java.math.BigDecimal bjyqys;
//	/**lxyqys*/
//    @ApiModelProperty(value = "lxyqys")
//	private java.math.BigDecimal lxyqys;
//	/**yqys*/
//    @ApiModelProperty(value = "yqys")
//	private java.math.BigDecimal yqys;
//	/**不良类别*/
//	@Excel(name = "不良类别", width = 15)
//    @ApiModelProperty(value = "不良类别")
//	private Integer bllb;
//	/**不良标志*/
//	@Excel(name = "不良标志", width = 15)
//    @ApiModelProperty(value = "不良标志")
//	private Integer blbz;
//	/**不良月数*/
//	@Excel(name = "不良月数", width = 15)
//    @ApiModelProperty(value = "不良月数")
//	private java.math.BigDecimal blys;
//	/**本金不良标志*/
//	@Excel(name = "本金不良标志", width = 15)
//    @ApiModelProperty(value = "本金不良标志")
//	private Integer bjblbz;
//	/**本金不良月数*/
//	@Excel(name = "本金不良月数", width = 15)
//    @ApiModelProperty(value = "本金不良月数")
//	private java.math.BigDecimal bjblys;
//	/**利息不良标志*/
//	@Excel(name = "利息不良标志", width = 15)
//    @ApiModelProperty(value = "利息不良标志")
//	private Integer lxblbz;
//	/**利息不良月数*/
//	@Excel(name = "利息不良月数", width = 15)
//    @ApiModelProperty(value = "利息不良月数")
//	private java.math.BigDecimal lxblys;
//	/**追责标记*/
//	@Excel(name = "追责标记", width = 15)
//	@ApiModelProperty(value = "追责标记")
//	private Integer zzbz;
//	/**扣收标记*/
//	@Excel(name = "扣收标记", width = 15)
//	@ApiModelProperty(value = "扣收标记")
//	private Integer zbks;
}
