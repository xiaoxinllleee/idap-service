package org.cmms.modules.report.tzsjgl.sgtzxyk.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 信用卡_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-03-06
 * @Version: V1.0
 */
@Data
@TableName("rep_tzgl_xt_xyk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rep_tzgl_xt_xyk对象", description="信用卡_湘潭")
public class SgtzXyk {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private java.util.Date sjrq;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String kh;
	/**是否为附卡*/
	@Excel(name = "是否为附卡", width = 15)
	@ApiModelProperty(value = "是否为附卡")
	private String sfwfk;
	/**是否为附卡*/
	@Excel(name = "是否核销", width = 15)
	@ApiModelProperty(value = "是否核销")
	private String sfhx;
	/**卡种*/
	@Excel(name = "卡种", width = 15)
    @ApiModelProperty(value = "卡种")
	private String kz;
	/**推广人员*/
	@Excel(name = "推广人员", width = 15)
    @ApiModelProperty(value = "推广人员")
	private String tgry;

	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
	@ApiModelProperty(value = "机构名称")
	private String jgdm;
	/**推广人员*/
	@Excel(name = "推广员姓名", width = 15)
	@ApiModelProperty(value = "推广员姓名")
	private String tgryxm;
	/**推广专案*/
	@Excel(name = "推广专案", width = 15)
    @ApiModelProperty(value = "推广专案")
	private String tgza;
	/**卡片状态*/
	@Excel(name = "卡片状态", width = 15)
    @ApiModelProperty(value = "卡片状态")
	private String kpzt;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**卡片到期日*/
	@Excel(name = "卡片到期日", width = 15)
    @ApiModelProperty(value = "卡片到期日")
	private String kpdqr;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String xm;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**逾期月份*/
	@Excel(name = "逾期月份", width = 15)
	private Long yqyf;
	/**循环额度*/
	@Excel(name = "循环额度", width = 15)
    @ApiModelProperty(value = "循环额度")
	private java.math.BigDecimal xhed;
	/**账单最小还款*/
	@Excel(name = "账单最小还款", width = 15)
    @ApiModelProperty(value = "账单最小还款")
	private java.math.BigDecimal zdzxhk;
	/**账单逾期金额*/
	@Excel(name = "账单逾期金额", width = 15)
    @ApiModelProperty(value = "账单逾期金额")
	private java.math.BigDecimal zdyqje;
	/**透支全额*/
	@Excel(name = "透支全额", width = 15)
    @ApiModelProperty(value = "透支全额")
	private java.math.BigDecimal tzje;
	/**贷款金额*/
	@Excel(name = "贷款金额（剔除核销）", width = 15)
    @ApiModelProperty(value = "贷款金额（剔除核销）")
	private java.math.BigDecimal dkje;
	/**制卡日期*/
	@Excel(name = "制卡日期", width = 15)
    @ApiModelProperty(value = "制卡日期")
	private String zkrq;
	/**激活日期*/
	@Excel(name = "激活日期", width = 15)
    @ApiModelProperty(value = "激活日期")
	private String jhrq;
	/**发卡日期*/
	@Excel(name = "发卡日期", width = 15)
    @ApiModelProperty(value = "发卡日期")
	private String fkrq;
	/**逾期期数*/
	@Excel(name = "逾期期数", width = 15)
    @ApiModelProperty(value = "逾期期数")
	private Long yqqs;
	/**五级分类*/
	@Excel(name = "五级分类", width = 15)
    @ApiModelProperty(value = "五级分类")
	private String wjfl;
	/**逾期天数*/
	@Excel(name = "逾期天数", width = 15)
    @ApiModelProperty(value = "逾期天数")
	private Long yqts;

}
