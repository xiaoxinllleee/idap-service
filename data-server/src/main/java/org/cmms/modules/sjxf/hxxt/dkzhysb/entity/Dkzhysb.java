package org.cmms.modules.sjxf.hxxt.dkzhysb.entity;

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
 * @Description: 贷款置换映射表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_repl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_repl对象", description="贷款置换映射表")
public class Dkzhysb {
    
	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private String socNo;
	/**置换前账号*/
	@Excel(name = "置换前账号", width = 15)
    @ApiModelProperty(value = "置换前账号")
	private String reOldAcctNo;
	/**置换后账号*/
	@Excel(name = "置换后账号", width = 15)
    @ApiModelProperty(value = "置换后账号")
	private String reNewAcctNo;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String brchNo;
	/**置换产品大类*/
	@Excel(name = "置换产品大类", width = 15)
    @ApiModelProperty(value = "置换产品大类")
	private String newAcctType;
	/**置换产品子类*/
	@Excel(name = "置换产品子类", width = 15)
    @ApiModelProperty(value = "置换产品子类")
	private String newIntCat;
	/**本金置换日期*/
	@Excel(name = "本金置换日期", width = 15)
    @ApiModelProperty(value = "本金置换日期")
	private Integer prnReDate;
	/**本金置换资金协议号*/
	@Excel(name = "本金置换资金协议号", width = 15)
    @ApiModelProperty(value = "本金置换资金协议号")
	private String prnReFundNo;
	/**利息置换日期*/
	@Excel(name = "利息置换日期", width = 15)
    @ApiModelProperty(value = "利息置换日期")
	private Integer intReDate;
	/**利息置换资金协议号*/
	@Excel(name = "利息置换资金协议号", width = 15)
    @ApiModelProperty(value = "利息置换资金协议号")
	private String intReFundNo;
	/**本金置换金额*/
	@Excel(name = "本金置换金额", width = 15)
    @ApiModelProperty(value = "本金置换金额")
	private java.math.BigDecimal prnReAmount;
	/**利息置换金额*/
	@Excel(name = "利息置换金额", width = 15)
    @ApiModelProperty(value = "利息置换金额")
	private java.math.BigDecimal intReAmount;
	/**填充字段*/
	@Excel(name = "填充字段", width = 15)
    @ApiModelProperty(value = "填充字段")
	private String varArea;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
