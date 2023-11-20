package org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.entity;

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
 * @Description: 贷款客户关联人管理
 * @Author: cmms
 * @Date:   2019-10-11
 * @Version: V1.0
 */
@Data
@TableName("Dkjkpt_dkkhglrgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Dkjkpt_dkkhglrgl对象", description="贷款客户关联人管理")
public class DkjkptDkkhglrgl {
    
	/**借款人证件号码*/
	@Excel(name = "借款人证件号码", width = 15)
    @ApiModelProperty(value = "借款人证件号码")
	private String jkrzjhm;
	/**借款人名称*/
	@Excel(name = "借款人名称", width = 15)
    @ApiModelProperty(value = "借款人名称")
	private String jkrmc;
    /**关联人证件号码*/
    @Excel(name = "关联人证件号码", width = 15)
    @ApiModelProperty(value = "关联人证件号码")
    private String glrzjhm;
	/**关联人证件类型（1：身份证 0：其他）*/
	@Excel(name = "关联人证件类型", width = 15, dicCode = "dkjkpt_zjlx")
    @ApiModelProperty(value = "关联人证件类型")
	@Dict(dicCode = "dkjkpt_zjlx")
	private Integer glrzjlx;
    /**关联人姓名*/
    @Excel(name = "关联人姓名", width = 15)
    @ApiModelProperty(value = "关联人姓名")
    private String glrxm;
	/**关联关系（1：父母 2：配偶 3：兄弟姐妹 4：子女）*/
	@Excel(name = "关联关系", width = 15, dicCode = "dkjkpt_glgx")
    @ApiModelProperty(value = "关联关系")
	@Dict(dicCode = "dkjkpt_glgx")
	private Integer glgx;
    /**关联人存款余额*/
    @Excel(name = "关联人存款余额", width = 15)
    @ApiModelProperty(value = "关联人存款余额")
    private java.math.BigDecimal glrckye;
	/**关联人工作单位*/
	@Excel(name = "关联人工作单位", width = 15)
    @ApiModelProperty(value = "关联人工作单位")
	private String glrgzdw;
	/**关联人联系电话*/
	@Excel(name = "关联人联系电话", width = 15)
    @ApiModelProperty(value = "关联人联系电话")
	private String glrlxdh;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识（0：导入 1：录入 2：修改）")
    @Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
