package org.cmms.modules.report.sgtzgl.yhktzwjflqk.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 银行卡透支五级分类情况表（三）导入实体类
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
public class YhktzWjflqkVO {

	/**数据日期*/
	//@Excel(name = "数据日期", width = 15)
	private String fiscalDate;
	/**机构编码*/
	//@Excel(name = "机构编码", width = 15)
	private String branchNo;
	/**机构名称*/
	@Excel(name = "项目数据名称", width = 15)
	@ExcelVerify(notNull = true)/*,interHandler = true*/
	private String branchName;

	/**卡透支年初余额*/
	@Excel(name = "卡透支年初余额", width = 15)
	private java.math.BigDecimal ktzNcye;
	/**卡透支本月余额*/
	@Excel(name = "卡透支本月余额", width = 15)
	private java.math.BigDecimal ktzByye;

	/**正常类贷款:正常:年初余额*/
	@Excel(name = "正常类贷款:正常:年初余额", width = 15)
	private java.math.BigDecimal zcNcye;
	/**正常类贷款:正常:月末余额*/
	@Excel(name = "正常类贷款:正常:月末余额", width = 15)
	private java.math.BigDecimal zcYmye;
	/**正常类贷款:正常:比年初+-*/
	@Excel(name = "正常类贷款:正常:比年初+-", width = 15)
	private java.math.BigDecimal zcBnc;

	/**正常类贷款:关注:年初余额*/
	@Excel(name = "正常类贷款:关注:年初余额", width = 15)
	private java.math.BigDecimal gzNcye;
	/**正常类贷款:关注:月末余额*/
	@Excel(name = "正常类贷款:关注:月末余额", width = 15)
	private java.math.BigDecimal gzYmye;
	/**正常类贷款:关注:比年初+-*/
	@Excel(name = "正常类贷款:关注:比年初+-", width = 15)
	private java.math.BigDecimal gzBnc;

	/**不良类贷款:次级:年初余额*/
	@Excel(name = "不良类贷款:次级:年初余额", width = 15)
	private java.math.BigDecimal cjNcye;
	/**不良类贷款:次级:月末余额*/
	@Excel(name = "不良类贷款:次级:月末余额", width = 15)
	private java.math.BigDecimal cjYmye;
	/**不良类贷款:次级:比年初+-*/
	@Excel(name = "不良类贷款:次级:比年初+-", width = 15)
	private java.math.BigDecimal cjBnc;

	/**不良类贷款:可疑:年初余额*/
	@Excel(name = "不良类贷款:可疑:年初余额", width = 15)
	private java.math.BigDecimal kyNcye;
	/**不良类贷款:可疑:月末余额*/
	@Excel(name = "不良类贷款:可疑:月末余额", width = 15)
	private java.math.BigDecimal kyYmye;
	/**不良类贷款:可疑:比年初+-*/
	@Excel(name = "不良类贷款:可疑:比年初+-", width = 15)
	private java.math.BigDecimal kyBnc;

	/**不良类贷款:损失:年初余额*/
	@Excel(name = "不良类贷款:损失:年初余额", width = 15)
	private java.math.BigDecimal ssNcye;
	/**不良类贷款:损失:月末余额*/
	@Excel(name = "不良类贷款:损失:月末余额", width = 15)
	private java.math.BigDecimal ssYmye;
	/**不良类贷款:损失:比年初+-*/
	@Excel(name = "不良类贷款:损失:比年初+-", width = 15)
	private java.math.BigDecimal ssBnc;

	/**不良贷款:不良年初余额*/
	@Excel(name = "不良贷款:不良年初余额", width = 15)
	private java.math.BigDecimal blNcye;
	/**不良贷款:不良上月余额*/
	@Excel(name = "不良贷款:不良上月余额", width = 15)
	private java.math.BigDecimal blSyye;
	/**不良贷款:不良本月余额*/
	@Excel(name = "不良贷款:不良本月余额", width = 15)
	private java.math.BigDecimal blByye;
	/**不良贷款:比年初+-*/
	@Excel(name = "不良贷款:比年初+-", width = 15)
	private java.math.BigDecimal blBnc;
	/**不良贷款:比上月+-*/
	@Excel(name = "不良贷款:比上月+-", width = 15)
	private java.math.BigDecimal blBsy;
	/**不良贷款:不良占比%*/
	@Excel(name = "不良贷款:不良占比%", width = 15)
	private java.math.BigDecimal blzb;
	/**不良贷款:压缩任务*/
	@Excel(name = "不良贷款:压缩任务", width = 15)
	private java.math.BigDecimal ysrw;
	/**不良贷款:任务完成率*/
	@Excel(name = "不良贷款:任务完成率", width = 15)
	private java.math.BigDecimal rwwcl;

	/**创建人*/
	private String createBy;
	/**创建时间*/
	private Date createTime;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	private Date updateTime;
}
