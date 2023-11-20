package org.cmms.modules.report.sgtzgl.whckye.entity;

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
 * @Description: 浏阳农商行_外汇存款余额表
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
@TableName("gjb_sgtz_whckye")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="gjb_sgtz_whckye对象", description="浏阳农商行_外汇存款余额表")
public class SgtzWhckye {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**机构编码*/
	@Excel(name = "机构编码", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构编码")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String sjywjgdm;
	/**年初余额*/
	@Excel(name = "年初余额", width = 15)
    @ApiModelProperty(value = "年初余额")
	private java.math.BigDecimal ncye;
	/**本月末余额*/
	@Excel(name = "本月末余额", width = 15)
    @ApiModelProperty(value = "本月末余额")
	private java.math.BigDecimal bymye;
	/**年初低息存款余额（2001、2003、2005、2006、2011、2014科目余额）*/
	@Excel(name = "年初低息存款余额（2001、2003、2005、2006、2011、2014科目余额）", width = 15)
    @ApiModelProperty(value = "年初低息存款余额（2001、2003、2005、2006、2011、2014科目余额）")
	private java.math.BigDecimal ncdxckye;
	/**本月低息存款余额（2001、2003、2005、2006、2011、2014科目余额）*/
	@Excel(name = "本月低息存款余额（2001、2003、2005、2006、2011、2014科目余额）", width = 15)
    @ApiModelProperty(value = "本月低息存款余额（2001、2003、2005、2006、2011、2014科目余额）")
	private java.math.BigDecimal bydxckye;
	/**本月末余额(1)*/
	@Excel(name = "本月末余额(1)", width = 15)
    @ApiModelProperty(value = "本月末余额(1)")
	private java.math.BigDecimal bymye1;

	/**机构名称*/
	@TableField(exist = false)
	private String branchName;
}
