package org.cmms.modules.ywgl.cdkfx.xzblkkhz.entity;

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
 * @Description: 新增不良扣款汇总
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
@Data
@TableName("V_XZBLKKHZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_XZBLKKHZ对象", description="新增不良扣款汇总")
public class Vxzblkkhz {

	/**考核月份*/
	@Excel(name = "考核月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "考核月份")
	private Date khyf;
	/**支行代码*/
	@Excel(name = "支行名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "支行代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户经理人数*/
	@Excel(name = "客户经理人数", width = 15)
    @ApiModelProperty(value = "客户经理人数")
	private Integer custrs;
	/**新增不良贷款余额*/
	@Excel(name = "新增不良贷款余额", width = 15)
    @ApiModelProperty(value = "新增不良贷款余额")
	private java.math.BigDecimal bqbldkye;
	/**本金不良*/
	@Excel(name = "本金不良", width = 15)
    @ApiModelProperty(value = "本金不良")
	private java.math.BigDecimal bjbl;
	/**利息不良*/
	@Excel(name = "利息不良", width = 15)
    @ApiModelProperty(value = "利息不良")
	private java.math.BigDecimal lxbl;
	/**利息及本金不良*/
	@Excel(name = "利息及本金不良", width = 15)
    @ApiModelProperty(value = "利息及本金不良")
	private java.math.BigDecimal blbl;
	/**不良扣款金额*/
	@Excel(name = "不良扣款金额", width = 15)
    @ApiModelProperty(value = "不良扣款金额")
	private java.math.BigDecimal bqkkjedc;
}
