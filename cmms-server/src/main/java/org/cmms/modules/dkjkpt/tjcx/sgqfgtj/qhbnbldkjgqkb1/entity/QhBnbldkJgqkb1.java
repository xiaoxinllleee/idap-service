package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.entity;

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
 * @Description: 全行表内不良贷款结构情况表1
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Data
@TableName("REP_YWBB_QHBNBLDKJGQKB1")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_YWBB_QHBNBLDKJGQKB1对象", description="全行表内不良贷款结构情况表1")
public class QhBnbldkJgqkb1 {
    
	/**单户50万元以下户数*/
	@Excel(name = "单户50万元以下户数", width = 15)
    @ApiModelProperty(value = "单户50万元以下户数")
	private String dhqj1hs;
	/**单户50万元以下金额*/
	@Excel(name = "单户50万元以下金额", width = 15)
    @ApiModelProperty(value = "单户50万元以下金额")
	private java.math.BigDecimal dhqj1je;
	/**单户50万元至500万元户数*/
	@Excel(name = "单户50万元至500万元户数", width = 15)
    @ApiModelProperty(value = "单户50万元至500万元户数")
	private String dhqj2hs;
	/**单户50万元至500万元金额*/
	@Excel(name = "单户50万元至500万元金额", width = 15)
    @ApiModelProperty(value = "单户50万元至500万元金额")
	private java.math.BigDecimal dhqj2je;
	/**单户500万元至1000万元户数*/
	@Excel(name = "单户500万元至1000万元户数", width = 15)
    @ApiModelProperty(value = "单户500万元至1000万元户数")
	private String dhqj3hs;
	/**单户500万元至1000万元金额*/
	@Excel(name = "单户500万元至1000万元金额", width = 15)
    @ApiModelProperty(value = "单户500万元至1000万元金额")
	private java.math.BigDecimal dhqj3je;
	/**单户1000万元以上户数*/
	@Excel(name = "单户1000万元以上户数", width = 15)
    @ApiModelProperty(value = "单户1000万元以上户数")
	private String dhqj4hs;
	/**单户1000万元以上金额*/
	@Excel(name = "单户1000万元以上金额", width = 15)
    @ApiModelProperty(value = "单户1000万元以上金额")
	private java.math.BigDecimal dhqj4je;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**表外贷款总户数*/
	@Excel(name = "表外贷款总户数", width = 15)
    @ApiModelProperty(value = "表外贷款总户数")
	private String bwdkzhs;
	/**表外贷款总额*/
	@Excel(name = "表外贷款总额", width = 15)
    @ApiModelProperty(value = "表外贷款总额")
	private java.math.BigDecimal bwdkzje;
	/**农商银行改制前户数*/
	@Excel(name = "农商银行改制前户数", width = 15)
    @ApiModelProperty(value = "农商银行改制前户数")
	private String nshgzqhs;
	/**农商银行改制前金额*/
	@Excel(name = "农商银行改制前金额", width = 15)
    @ApiModelProperty(value = "农商银行改制前金额")
	private java.math.BigDecimal nshgzqje;
	/**农商银行改制后户数*/
	@Excel(name = "农商银行改制后户数", width = 15)
    @ApiModelProperty(value = "农商银行改制后户数")
	private String nshgzhhs;
	/**农商银行改制后金额*/
	@Excel(name = "农商银行改制后金额", width = 15)
    @ApiModelProperty(value = "农商银行改制后金额")
	private java.math.BigDecimal nshgzhje;
	/**信用户数*/
	@Excel(name = "信用户数", width = 15)
    @ApiModelProperty(value = "信用户数")
	private String xyhs;
	/**信用金额*/
	@Excel(name = "信用金额", width = 15)
    @ApiModelProperty(value = "信用金额")
	private java.math.BigDecimal xyje;
	/**抵押户数*/
	@Excel(name = "抵押户数", width = 15)
    @ApiModelProperty(value = "抵押户数")
	private String dyhs;
	/**抵押金额*/
	@Excel(name = "抵押金额", width = 15)
    @ApiModelProperty(value = "抵押金额")
	private java.math.BigDecimal dyje;
	/**保证户数*/
	@Excel(name = "保证户数", width = 15)
    @ApiModelProperty(value = "保证户数")
	private String bzhs;
	/**保证金额*/
	@Excel(name = "保证金额", width = 15)
    @ApiModelProperty(value = "保证金额")
	private java.math.BigDecimal bzje;
	/**质押户数*/
	@Excel(name = "质押户数", width = 15)
    @ApiModelProperty(value = "质押户数")
	private String zyhs;
	/**质押金额*/
	@Excel(name = "质押金额", width = 15)
    @ApiModelProperty(value = "质押金额")
	private java.math.BigDecimal zyje;
	/**组合户数*/
	@Excel(name = "组合户数", width = 15)
    @ApiModelProperty(value = "组合户数")
	private String zhhs;
	/**组合金额*/
	@Excel(name = "组合金额", width = 15)
    @ApiModelProperty(value = "组合金额")
	private java.math.BigDecimal zhje;
	/**农业类贷款户数*/
	@Excel(name = "农业类贷款户数", width = 15)
    @ApiModelProperty(value = "农业类贷款户数")
	private String nyldkhs;
	/**农业类贷款金额*/
	@Excel(name = "农业类贷款金额", width = 15)
    @ApiModelProperty(value = "农业类贷款金额")
	private java.math.BigDecimal nyldkdkje;
	/**制造业贷款户数*/
	@Excel(name = "制造业贷款户数", width = 15)
    @ApiModelProperty(value = "制造业贷款户数")
	private String zzykhs;
	/**制造业贷款金额*/
	@Excel(name = "制造业贷款金额", width = 15)
    @ApiModelProperty(value = "制造业贷款金额")
	private java.math.BigDecimal zzydkje;
	/**服务业贷款户数*/
	@Excel(name = "服务业贷款户数", width = 15)
    @ApiModelProperty(value = "服务业贷款户数")
	private String fwydkhs;
	/**服务业贷款金额*/
	@Excel(name = "服务业贷款金额", width = 15)
    @ApiModelProperty(value = "服务业贷款金额")
	private java.math.BigDecimal fwydkje;
	/**房地产开发贷款户数*/
	@Excel(name = "房地产开发贷款户数", width = 15)
    @ApiModelProperty(value = "房地产开发贷款户数")
	private String fdckfdkhs;
	/**房地产开发贷款金额*/
	@Excel(name = "房地产开发贷款金额", width = 15)
    @ApiModelProperty(value = "房地产开发贷款金额")
	private java.math.BigDecimal fdckfdkje;
	/**政府公共服务类贷款户数*/
	@Excel(name = "政府公共服务类贷款户数", width = 15)
    @ApiModelProperty(value = "政府公共服务类贷款户数")
	private String zfggfwldkhs;
	/**政府公共服务类贷款金额*/
	@Excel(name = "政府公共服务类贷款金额", width = 15)
    @ApiModelProperty(value = "政府公共服务类贷款金额")
	private java.math.BigDecimal zfggfwldkje;
	/**基础设施建设贷款户数*/
	@Excel(name = "基础设施建设贷款户数", width = 15)
    @ApiModelProperty(value = "基础设施建设贷款户数")
	private String jcssjsdkhs;
	/**基础设施建设贷款金额*/
	@Excel(name = "基础设施建设贷款金额", width = 15)
    @ApiModelProperty(value = "基础设施建设贷款金额")
	private java.math.BigDecimal jcssjsdkje;
	/**政府重点项目贷款户数*/
	@Excel(name = "政府重点项目贷款户数", width = 15)
    @ApiModelProperty(value = "政府重点项目贷款户数")
	private String zfzdxmdkhs;
	/**政府重点项目贷款金额*/
	@Excel(name = "政府重点项目贷款金额", width = 15)
    @ApiModelProperty(value = "政府重点项目贷款金额")
	private java.math.BigDecimal zfzdxmdkje;
	/**其他贷款户数*/
	@Excel(name = "其他贷款户数", width = 15)
    @ApiModelProperty(value = "其他贷款户数")
	private String qtdkhs;
	/**其他贷款金额*/
	@Excel(name = "其他贷款金额", width = 15)
    @ApiModelProperty(value = "其他贷款金额")
	private java.math.BigDecimal qtdkje;
}
