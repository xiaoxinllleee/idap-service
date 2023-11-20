package org.cmms.modules.khgl.csjl.entity;

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
 * @Description: etc客户催收记录
 * @Author: jeecg-boot
 * @Date:   2022-03-19
 * @Version: V1.0
 */
@Data
@TableName("APP_ETCKH_CSJL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="APP_ETCKH_CSJL对象", description="etc客户催收记录")
public class AppEtckhCsjl {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private Integer id;
	/**khxx*/
	@Excel(name = "khxx", width = 15)
    @ApiModelProperty(value = "khxx")
	private String khxx;
	/**csr*/
	@Excel(name = "csr", width = 15)
    @ApiModelProperty(value = "csr")
	private String csr;
	/**csrq*/
	@Excel(name = "csrq", width = 15)
    @ApiModelProperty(value = "csrq")
	private Date csrq;
	/**dkrq*/
	@Excel(name = "dkrq", width = 15)
    @ApiModelProperty(value = "dkrq")
	private String dkrq;
	/**dkje*/
	@Excel(name = "dkje", width = 15)
    @ApiModelProperty(value = "dkje")
	private String dkje;
	/**hsrq*/
	@Excel(name = "hsrq", width = 15)
    @ApiModelProperty(value = "hsrq")
	private String hsrq;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "提交日期")
	private Date tjrq;
	/**csxx*/
	@Excel(name = "csxx", width = 15)
	@ApiModelProperty(value = "csxx")
	private String csxx;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
	@ApiModelProperty(value = "zjhm")
	private String zjhm;
}
