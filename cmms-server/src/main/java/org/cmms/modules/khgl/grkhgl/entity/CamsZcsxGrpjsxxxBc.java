package org.cmms.modules.khgl.grkhgl.entity;

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
 * @Description: 个人客户评级授信补充
 * @Author: jeecg-boot
 * @Date:   2020-07-13
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_GRPJSXXX_BC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_GRPJSXXX_BC对象", description="个人客户评级授信补充")
public class CamsZcsxGrpjsxxxBc {
    
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**定活期余额*/
	@Excel(name = "定活期余额", width = 15)
    @ApiModelProperty(value = "定活期余额")
	private java.math.BigDecimal dhqye;
	/**存入交易额*/
	@Excel(name = "存入交易额", width = 15)
    @ApiModelProperty(value = "存入交易额")
	private java.math.BigDecimal crjye;
	/**支出交易额*/
	@Excel(name = "支出交易额", width = 15)
    @ApiModelProperty(value = "支出交易额")
	private java.math.BigDecimal zcjye;
	/**存款账户数*/
	@Excel(name = "存款账户数", width = 15)
    @ApiModelProperty(value = "存款账户数")
	private Integer ckzhs;
	/**口袋零钱记录数*/
	@Excel(name = "口袋零钱记录数", width = 15)
    @ApiModelProperty(value = "口袋零钱记录数")
	private Integer kdlqjls;
	/**网银记录数*/
	@Excel(name = "网银记录数", width = 15)
    @ApiModelProperty(value = "网银记录数")
	private Integer wyjls;
	/**手机银行记录数*/
	@Excel(name = "手机银行记录数", width = 15)
    @ApiModelProperty(value = "手机银行记录数")
	private Integer sjyhjls;
	/**etc记录数*/
	@Excel(name = "etc记录数", width = 15)
    @ApiModelProperty(value = "etc记录数")
	private Integer etcjls;
	/**农信银记录数*/
	@Excel(name = "农信银记录数", width = 15)
    @ApiModelProperty(value = "农信银记录数")
	private Integer nxyjls;
	/**五级分类*/
	@Excel(name = "五级分类", width = 15)
    @ApiModelProperty(value = "五级分类")
	private String wjfl;
	/**表外贷款*/
	@Excel(name = "表外贷款", width = 15)
    @ApiModelProperty(value = "表外贷款")
	private java.math.BigDecimal bwdk;
	/**预期次数*/
	@Excel(name = "预期次数", width = 15)
    @ApiModelProperty(value = "预期次数")
	private Integer yqcs;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal sxed;
	/**展期金额*/
	@Excel(name = "展期金额", width = 15)
    @ApiModelProperty(value = "展期金额")
	private java.math.BigDecimal zqje;
	/**是否信贷*/
	@Excel(name = "是否信贷", width = 15)
    @ApiModelProperty(value = "是否信贷")
	private String sfxd;
	/**开户时间*/
	@Excel(name = "开户时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户时间")
	private Date khsj;
	/**存款日平*/
	@Excel(name = "存款日平", width = 15)
    @ApiModelProperty(value = "存款日平")
	private java.math.BigDecimal ckrp;
	/**存入交易次数*/
	@Excel(name = "存入交易次数", width = 15)
	@ApiModelProperty(value = "存入交易次数")
	private java.math.BigDecimal crjycs;
	/**支出交易次数*/
	@Excel(name = "支出交易次数", width = 15)
	@ApiModelProperty(value = "支出交易次数")
	private java.math.BigDecimal zcjycs;
	/**收费代收次数*/
	@Excel(name = "水费代收次数", width = 15)
	@ApiModelProperty(value = "支水费代收次数")
	private Integer sfds;

	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String createBy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
