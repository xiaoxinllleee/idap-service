package org.cmms.modules.pad.nhxxgl.entity;

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
 * @Description: 与我行业务往来信息_户汇总
 * @Author: jeecg-boot
 * @Date:   2022-03-20
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_ywhywwlxx_h")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_ywhywwlxx_h对象", description="与我行业务往来信息_户汇总")
public class KhxxglYwhywwlxxH {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**是否符合年龄*/
	@Excel(name = "是否符合年龄", width = 15)
    @ApiModelProperty(value = "是否符合年龄")
	private String sffhnl;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**存款日平余额*/
	@Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**存款年日平余额*/
	@Excel(name = "存款年日平余额", width = 15)
    @ApiModelProperty(value = "存款年日平余额")
	private java.math.BigDecimal cknrpye;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**正常贷款余额*/
	@Excel(name = "正常贷款余额", width = 15)
    @ApiModelProperty(value = "正常贷款余额")
	private java.math.BigDecimal zcdkye;
	/**表内不良贷款余额*/
	@Excel(name = "表内不良贷款余额", width = 15)
    @ApiModelProperty(value = "表内不良贷款余额")
	private java.math.BigDecimal bnbldkye;
	/**表外不良贷款余额*/
	@Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
	private java.math.BigDecimal bwbldkye;
	/**是否诉讼*/
	@Excel(name = "是否诉讼", width = 15)
    @ApiModelProperty(value = "是否诉讼")
	private String sfss;
	/**是否服刑*/
	@Excel(name = "是否服刑", width = 15)
    @ApiModelProperty(value = "是否服刑")
	private String sffx;
	/**是否涉毒*/
	@Excel(name = "是否涉毒", width = 15)
    @ApiModelProperty(value = "是否涉毒")
	private String sfsd;
	/**是否五保低保户*/
	@Excel(name = "是否五保低保户", width = 15)
    @ApiModelProperty(value = "是否五保低保户")
	private String sfwbdbh;
	/**是否授信*/
	@Excel(name = "是否授信", width = 15)
	@ApiModelProperty(value = "是否授信")
	private String sfsx;
	/**是否不予授信*/
	@Excel(name = "是否不予授信", width = 15)
	@ApiModelProperty(value = "是否不予授信")
	private String sfbysx;

	@Excel(name = "是否非法集资", width = 15)
	@ApiModelProperty(value = "是否非法集资")
	private String sfffjz;

	@Excel(name = "是否重大疾病", width = 15)
	@ApiModelProperty(value = "是否重大疾病")
	private String sfzdjb;
}
