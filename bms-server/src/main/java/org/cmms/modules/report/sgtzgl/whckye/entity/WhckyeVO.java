package org.cmms.modules.report.sgtzgl.whckye.entity;

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
 * @Description: 浏阳农商行_外汇存款余额表
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
public class WhckyeVO {

	/**数据日期*/
	private String fiscalDate;
	/**机构编码*/
	private String sjywjgdm;
	/**支行名称*/
	@Excel(name = "支行名称", width = 15)
	@ExcelVerify(notNull = true)
	private String branchName;
	/**年初余额*/
	@Excel(name = "年初余额", width = 15)
	private java.math.BigDecimal ncye;
	/**本月末余额*/
	@Excel(name = "本月末余额", width = 15)
	private java.math.BigDecimal bymye;
	/**年初低息存款余额（2001、2003、2005、2006、2011、2014科目余额）*/
	@Excel(name = "年初低息存款余额（2001、2003、2005、2006、2011、2014科目余额）", width = 15)
	private java.math.BigDecimal ncdxckye;
	/**本月低息存款余额（2001、2003、2005、2006、2011、2014科目余额）*/
	@Excel(name = "本月低息存款余额（2001、2003、2005、2006、2011、2014科目余额）", width = 15)
	private java.math.BigDecimal bydxckye;
	/**本月末余额(1)*/
	@Excel(name = "本月末余额(1)", width = 15)
	private java.math.BigDecimal bymye1;
}
