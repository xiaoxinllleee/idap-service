package org.cmms.modules.sjxf.qtxt.cwglxt.jydsgxhxxb.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 交易对手个性化信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_cpty_info_iddzt")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_cpty_info_iddzt对象", description="交易对手个性化信息表")
public class Jydsgxhxxb {
    
	/**交易对手编号*/
	@Excel(name = "交易对手编号", width = 15)
    @ApiModelProperty(value = "交易对手编号")
	private String ctpyNo;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
	private String version;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String businessType;
	/**资金账户户名*/
	@Excel(name = "资金账户户名", width = 15)
    @ApiModelProperty(value = "资金账户户名")
	private String accountName;
	/**资金开户行名*/
	@Excel(name = "资金开户行名", width = 15)
    @ApiModelProperty(value = "资金开户行名")
	private String bankName;
	/**资金账号*/
	@Excel(name = "资金账号", width = 15)
    @ApiModelProperty(value = "资金账号")
	private String bankAcno;
	/**支付系统行号*/
	@Excel(name = "支付系统行号", width = 15)
    @ApiModelProperty(value = "支付系统行号")
	private String bankNo;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
	private String telecd;
	/**传真*/
	@Excel(name = "传真", width = 15)
    @ApiModelProperty(value = "传真")
	private String faxitl;
	/**投资限额(元)*/
	@Excel(name = "投资限额(元)", width = 15)
    @ApiModelProperty(value = "投资限额(元)")
	private java.math.BigDecimal cptyAmt;
	/**投资比例(%)*/
	@Excel(name = "投资比例(%)", width = 15)
    @ApiModelProperty(value = "投资比例(%)")
	private java.math.BigDecimal cptyRatio;
	/**内部风险评级*/
	@Excel(name = "内部风险评级", width = 15)
    @ApiModelProperty(value = "内部风险评级")
	private String cptyLevel;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String sts;
	/**资金账户类型*/
	@Excel(name = "资金账户类型", width = 15)
    @ApiModelProperty(value = "资金账户类型")
	private String acnoType;
	/**维护方账务机构*/
	@Excel(name = "维护方账务机构", width = 15)
    @ApiModelProperty(value = "维护方账务机构")
	private String dcBrNo;
	/**交易对手个性信息编号*/
	@Excel(name = "交易对手个性信息编号", width = 15)
    @ApiModelProperty(value = "交易对手个性信息编号")
	private String subCtpyNo;
	/**对手方账务机构*/
	@Excel(name = "对手方账务机构", width = 15)
    @ApiModelProperty(value = "对手方账务机构")
	private String dsdcBrNo;
	/**对手部门号*/
	@Excel(name = "对手部门号", width = 15)
    @ApiModelProperty(value = "对手部门号")
	private String dsDepNo;
	/**部门号*/
	@Excel(name = "部门号", width = 15)
    @ApiModelProperty(value = "部门号")
	private String depNo;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
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
