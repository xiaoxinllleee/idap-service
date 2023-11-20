package org.cmms.modules.performance.depositcustomer.ckkhghyj.entity;

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
 * @Description: 存款客户管户移交
 * @Author: Penghr
 * @Date:   2021-03-25
 * @Version: V1.0
 */
@Data
@TableName("V_KHGXGL_CKKHGHYJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_KHGXGL_CKKHGHYJXX对象", description="存款客户管户移交")
public class Ckkhghyjxx {
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private String id;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件类型(0 其他 1 身份证)*/
	@Excel(name = "证件类型", width = 15, dicCode = "jx_zjlx")
    @ApiModelProperty(value = "证件类型(0 其他 1 身份证)")
	@Dict(dicCode = "jx_zjlx")
	private String zjlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型(1 对公客户 2 个人客户 3 金融机构客户 4 其他企业客户 5 其他个人客户)*/
	@Excel(name = "客户类型", width = 15, dicCode = "jx_khlx")
    @ApiModelProperty(value = "客户类型(1 对公客户 2 个人客户 3 金融机构客户 4 其他企业客户 5 其他个人客户)")
	@Dict(dicCode = "jx_khlx")
	private String khlx;
	/**营销类型(1 主动营销 2 自然增长)*/
	@Excel(name = "营销类型", width = 15, dicCode = "yxlx")
    @ApiModelProperty(value = "营销类型(1 主动营销 2 自然增长)")
	@Dict(dicCode = "yxlx")
	private String yxlx;
	/**产品信息*/
	@Excel(name = "产品信息", width = 15)
    @ApiModelProperty(value = "产品信息")
	private String cpxx;
	/**最早开户日期*/
	@Excel(name = "最早开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早开户日期")
	private Date zzkhrq;
	/**管户类型(1 拓展 2 管户 3 包收 4 审批 5 调查 6 安装)*/
	@Excel(name = "管户类型", width = 15, dicCode = "manager_acc_types")
    @ApiModelProperty(value = "管户类型(1 拓展 2 管户 3 包收 4 审批 5 调查 6 安装)")
	@Dict(dicCode = "manager_acc_types")
	private String ghlx;
	/**管户人*/
	@Excel(name = "管户人", width = 15, dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    @ApiModelProperty(value = "管户人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String ghr;
	/**管户比例(%)*/
	@Excel(name = "管户比例(%)", width = 15)
    @ApiModelProperty(value = "管户比例(%)")
	private java.math.BigDecimal ghbl;
}
