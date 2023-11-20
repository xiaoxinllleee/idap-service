package org.cmms.modules.report.sgtzgl.hngxjsqymd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Description: 湖南高新技术企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_gxjsqy对象", description="湖南高新技术企业名单")
public class SgztHngxjsqymdVO {


	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
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
	private String xzqy;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	private String tyshxydm;
	/**证书编号*/
	@Excel(name = "证书编号", width = 15)
    @ApiModelProperty(value = "证书编号")
	private String zsbh;
	/**年度*/
	@Excel(name = "年度", width = 15)
    @ApiModelProperty(value = "年度")
	private String nd;
	/**异动情况*/
	@Excel(name = "异动情况", width = 15)
    @ApiModelProperty(value = "异动情况")
	//@ExcelVerify(interHandler = true)
	private String ydqk;

}
