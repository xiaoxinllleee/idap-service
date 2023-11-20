package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 支行表内不良贷款结构情况表2
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Data
@TableName("REP_YWBB_ZHBNBLDKJGQKB2")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_YWBB_ZHBNBLDKJGQKB2对象", description="支行表内不良贷款结构情况表2")
public class ZhBnbldkJgqkb2Impor {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private String tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**宏观产业政策户数*/
	@Excel(name = "宏观产业政策户数", width = 15)
    @ApiModelProperty(value = "宏观产业政策户数")
	private String hgcyzchs;
	/**宏观产业政策金额*/
	@Excel(name = "宏观产业政策金额", width = 15)
    @ApiModelProperty(value = "宏观产业政策金额")
	private java.math.BigDecimal hgcyzcje;
	/**企业经营管理户数*/
	@Excel(name = "企业经营管理户数", width = 15)
    @ApiModelProperty(value = "企业经营管理户数")
	private String qyjyglhs;
	/**企业经营管理金额*/
	@Excel(name = "企业经营管理金额", width = 15)
    @ApiModelProperty(value = "企业经营管理金额")
	private java.math.BigDecimal qyjyglje;
	/**其客户涉及民间融资户数*/
	@Excel(name = "其客户涉及民间融资户数", width = 15)
    @ApiModelProperty(value = "其客户涉及民间融资户数")
	private String khsjmjrzhs;
	/**其客户涉及民间融资金额*/
	@Excel(name = "其客户涉及民间融资金额", width = 15)
    @ApiModelProperty(value = "其客户涉及民间融资金额")
	private java.math.BigDecimal khsjmjrzje;
	/**其它客观性因素户数*/
	@Excel(name = "其它客观性因素户数", width = 15)
    @ApiModelProperty(value = "其它客观性因素户数")
	private String qtkgyshs;
	/**其它客观性因素金额*/
	@Excel(name = "其它客观性因素金额", width = 15)
    @ApiModelProperty(value = "其它客观性因素金额")
	private java.math.BigDecimal qtkgysje;
	/**制度缺失户数*/
	@Excel(name = "制度缺失户数", width = 15)
    @ApiModelProperty(value = "制度缺失户数")
	private String zdqshshs;
	/**制度缺失金额*/
	@Excel(name = "制度缺失金额", width = 15)
    @ApiModelProperty(value = "制度缺失金额")
	private java.math.BigDecimal zdqshsje;
	/**决策失误户数*/
	@Excel(name = "决策失误户数", width = 15)
    @ApiModelProperty(value = "决策失误户数")
	private String jcswhs;
	/**决策失误金额*/
	@Excel(name = "决策失误金额", width = 15)
    @ApiModelProperty(value = "决策失误金额")
	private java.math.BigDecimal jcswje;
	/**信贷人员违规户数*/
	@Excel(name = "信贷人员违规户数", width = 15)
    @ApiModelProperty(value = "信贷人员违规户数")
	private String xdrywghs;
	/**信贷人员违规金额*/
	@Excel(name = "信贷人员违规金额", width = 15)
    @ApiModelProperty(value = "信贷人员违规金额")
	private java.math.BigDecimal xdrywgje;
	/**风险意识淡薄户数*/
	@Excel(name = "风险意识淡薄户数", width = 15)
    @ApiModelProperty(value = "风险意识淡薄户数")
	private String fxysdbhs;
	/**风险意识淡薄金额*/
	@Excel(name = "风险意识淡薄金额", width = 15)
    @ApiModelProperty(value = "风险意识淡薄金额")
	private java.math.BigDecimal fxysdbje;
	/**管理交接不规范户数*/
	@Excel(name = "管理交接不规范户数", width = 15)
    @ApiModelProperty(value = "管理交接不规范户数")
	private String gljjbgfhs;
	/**管理交接不规范金额*/
	@Excel(name = "管理交接不规范金额", width = 15)
    @ApiModelProperty(value = "管理交接不规范金额")
	private java.math.BigDecimal gljjbgfje;
	/**其它主观原因户数*/
	@Excel(name = "其它主观原因户数", width = 15)
    @ApiModelProperty(value = "其它主观原因户数")
	private String qtzgyyhs;
	/**其它主观原因金额*/
	@Excel(name = "其它主观原因金额", width = 15)
    @ApiModelProperty(value = "其它主观原因金额")
	private java.math.BigDecimal qtzgyyje;
	/**企业户数*/
	@Excel(name = "企业户数", width = 15)
    @ApiModelProperty(value = "企业户数")
	private String qyhs;
	/**企业金额*/
	@Excel(name = "企业金额", width = 15)
    @ApiModelProperty(value = "企业金额")
	private java.math.BigDecimal qyje;
	/**农户户数*/
	@Excel(name = "农户户数", width = 15)
    @ApiModelProperty(value = "农户户数")
	private String nhhs;
	/**农户金额*/
	@Excel(name = "农户金额", width = 15)
    @ApiModelProperty(value = "农户金额")
	private java.math.BigDecimal nhje;
	/**自然人户数*/
	@Excel(name = "自然人户数", width = 15)
    @ApiModelProperty(value = "自然人户数")
	private String zrrhs;
	/**自然人金额*/
	@Excel(name = "自然人金额", width = 15)
    @ApiModelProperty(value = "自然人金额")
	private java.math.BigDecimal zrrje;
	/**其他户数*/
	@Excel(name = "其他户数", width = 15)
    @ApiModelProperty(value = "其他户数")
	private String qths;
	/**其他金额*/
	@Excel(name = "其他金额", width = 15)
    @ApiModelProperty(value = "其他金额")
	private java.math.BigDecimal qtje;
	/**公司类客户户数*/
	@Excel(name = "公司类客户户数", width = 15)
    @ApiModelProperty(value = "公司类客户户数")
	private String gslhs;
	/**公司类客户金额*/
	@Excel(name = "公司类客户金额", width = 15)
    @ApiModelProperty(value = "公司类客户金额")
	private java.math.BigDecimal gslje;
	/**新型农业经营主体户数*/
	@Excel(name = "新型农业经营主体户数", width = 15)
    @ApiModelProperty(value = "新型农业经营主体户数")
	private String xxnyjyzths;
	/**新型农业经营主体金额*/
	@Excel(name = "新型农业经营主体金额", width = 15)
    @ApiModelProperty(value = "新型农业经营主体金额")
	private java.math.BigDecimal xxnyjyztje;
}
