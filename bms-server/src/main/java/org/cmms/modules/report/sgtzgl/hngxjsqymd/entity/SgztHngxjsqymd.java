package org.cmms.modules.report.sgtzgl.hngxjsqymd.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 湖南高新技术企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_gxjsqy")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_gxjsqy对象", description="湖南高新技术企业名单")
public class SgztHngxjsqymd {

	/**主键ID*/
//	@TableId(type = IdType.ASSIGN_ID)
//    @ApiModelProperty(value = "主键ID")
//	private String id;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
	private String qymc;
	/**技术领域一级*/
	@Excel(name = "技术领域一级", width = 15)
    @ApiModelProperty(value = "技术领域一级")
	private String jslyyj;
	/**行政区域*/
	@Excel(name = "行政区域", width = 15)
    @ApiModelProperty(value = "行政区域")
	private java.lang.String xzqy;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	private java.lang.String tyshxydm;
	/**证书编号*/
	@Excel(name = "证书编号", width = 15)
    @ApiModelProperty(value = "证书编号")
	private java.lang.String zsbh;
	/**年度*/
	@Excel(name = "年度", width = 15)
    @ApiModelProperty(value = "年度")
	private java.lang.String nd;
	/**异动情况*/
	@Excel(name = "异动情况", width = 15)
    @ApiModelProperty(value = "异动情况")
	//@ExcelVerify(interHandler = true)
	private java.lang.String ydqk;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;

}
