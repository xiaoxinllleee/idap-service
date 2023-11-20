package org.cmms.modules.report.sgtzgl.yhktzwjflqk.entity;

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
 * @Description: 银行卡透支五级分类情况表（三）
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_yhktzwjflqk_three")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_yhktzwjflqk_three对象", description="银行卡透支五级分类情况表（三）")
public class SgtzYhktzWjflqk {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**机构编码*/
	@Excel(name = "机构编码", width = 15)
    @ApiModelProperty(value = "机构编码")
	private String branchNo;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String branchName;
	/**卡透支年初余额*/
	@Excel(name = "卡透支年初余额", width = 15)
    @ApiModelProperty(value = "卡透支年初余额")
	private java.math.BigDecimal ktzNcye;
	/**卡透支本月余额*/
	@Excel(name = "卡透支本月余额", width = 15)
    @ApiModelProperty(value = "卡透支本月余额")
	private java.math.BigDecimal ktzByye;
	/**正常类贷款_正常_年初余额*/
	@Excel(name = "正常类贷款_正常_年初余额", width = 15)
    @ApiModelProperty(value = "正常类贷款_正常_年初余额")
	private java.math.BigDecimal zcNcye;
	/**正常类贷款_正常_月末余额*/
	@Excel(name = "正常类贷款_正常_月末余额", width = 15)
    @ApiModelProperty(value = "正常类贷款_正常_月末余额")
	private java.math.BigDecimal zcYmye;
	/**正常类贷款_正常_比年初+-*/
	@Excel(name = "正常类贷款_正常_比年初+-", width = 15)
    @ApiModelProperty(value = "正常类贷款_正常_比年初+-")
	private java.math.BigDecimal zcBnc;
	/**正常类贷款_关注_年初余额*/
	@Excel(name = "正常类贷款_关注_年初余额", width = 15)
    @ApiModelProperty(value = "正常类贷款_关注_年初余额")
	private java.math.BigDecimal gzNcye;
	/**正常类贷款_关注_月末余额*/
	@Excel(name = "正常类贷款_关注_月末余额", width = 15)
    @ApiModelProperty(value = "正常类贷款_关注_月末余额")
	private java.math.BigDecimal gzYmye;
	/**正常类贷款_关注_比年初+-*/
	@Excel(name = "正常类贷款_关注_比年初+-", width = 15)
    @ApiModelProperty(value = "正常类贷款_关注_比年初+-")
	private java.math.BigDecimal gzBnc;
	/**不良类贷款_次级_年初余额*/
	@Excel(name = "不良类贷款_次级_年初余额", width = 15)
    @ApiModelProperty(value = "不良类贷款_次级_年初余额")
	private java.math.BigDecimal cjNcye;
	/**不良类贷款_次级_月末余额*/
	@Excel(name = "不良类贷款_次级_月末余额", width = 15)
    @ApiModelProperty(value = "不良类贷款_次级_月末余额")
	private java.math.BigDecimal cjYmye;
	/**不良类贷款_次级_比年初+-*/
	@Excel(name = "不良类贷款_次级_比年初+-", width = 15)
    @ApiModelProperty(value = "不良类贷款_次级_比年初+-")
	private java.math.BigDecimal cjBnc;
	/**不良类贷款_可疑_年初余额*/
	@Excel(name = "不良类贷款_可疑_年初余额", width = 15)
    @ApiModelProperty(value = "不良类贷款_可疑_年初余额")
	private java.math.BigDecimal kyNcye;
	/**不良类贷款_可疑_月末余额*/
	@Excel(name = "不良类贷款_可疑_月末余额", width = 15)
    @ApiModelProperty(value = "不良类贷款_可疑_月末余额")
	private java.math.BigDecimal kyYmye;
	/**不良类贷款_可疑_比年初+-*/
	@Excel(name = "不良类贷款_可疑_比年初+-", width = 15)
    @ApiModelProperty(value = "不良类贷款_可疑_比年初+-")
	private java.math.BigDecimal kyBnc;
	/**不良类贷款_损失_年初余额*/
	@Excel(name = "不良类贷款_损失_年初余额", width = 15)
    @ApiModelProperty(value = "不良类贷款_损失_年初余额")
	private java.math.BigDecimal ssNcye;
	/**不良类贷款_损失_月末余额*/
	@Excel(name = "不良类贷款_损失_月末余额", width = 15)
    @ApiModelProperty(value = "不良类贷款_损失_月末余额")
	private java.math.BigDecimal ssYmye;
	/**不良类贷款_损失_比年初+-*/
	@Excel(name = "不良类贷款_损失_比年初+-", width = 15)
    @ApiModelProperty(value = "不良类贷款_损失_比年初+-")
	private java.math.BigDecimal ssBnc;
	/**不良贷款_不良年初余额*/
	@Excel(name = "不良贷款_不良年初余额", width = 15)
    @ApiModelProperty(value = "不良贷款_不良年初余额")
	private java.math.BigDecimal blNcye;
	/**不良贷款_不良上月余额*/
	@Excel(name = "不良贷款_不良上月余额", width = 15)
    @ApiModelProperty(value = "不良贷款_不良上月余额")
	private java.math.BigDecimal blSyye;
	/**不良贷款_不良本月余额*/
	@Excel(name = "不良贷款_不良本月余额", width = 15)
    @ApiModelProperty(value = "不良贷款_不良本月余额")
	private java.math.BigDecimal blByye;
	/**不良贷款_比年初+-*/
	@Excel(name = "不良贷款_比年初+-", width = 15)
    @ApiModelProperty(value = "不良贷款_比年初+-")
	private java.math.BigDecimal blBnc;
	/**不良贷款_比上月+-*/
	@Excel(name = "不良贷款_比上月+-", width = 15)
    @ApiModelProperty(value = "不良贷款_比上月+-")
	private java.math.BigDecimal blBsy;
	/**不良贷款_不良占比%*/
	@Excel(name = "不良贷款_不良占比%", width = 15)
    @ApiModelProperty(value = "不良贷款_不良占比%")
	private java.math.BigDecimal blzb;
	/**不良贷款_压缩任务*/
	@Excel(name = "不良贷款_压缩任务", width = 15)
    @ApiModelProperty(value = "不良贷款_压缩任务")
	private java.math.BigDecimal ysrw;
	/**不良贷款_任务完成率*/
	@Excel(name = "不良贷款_任务完成率", width = 15)
    @ApiModelProperty(value = "不良贷款_任务完成率")
	private java.math.BigDecimal rwwcl;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
