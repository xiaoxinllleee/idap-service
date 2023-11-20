package org.cmms.modules.report.sgtzgl.hnkjxzxqymd.entity;

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
 * @Description: 湖南科技型中小企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_kjxzxqy对象", description="湖南科技型中小企业名单")
public class SgztHnkjxzxqymdVO {


	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
	private String qymc;
	/**统一社会信用代码*/
	@Excel(name = "统一社会信用代码", width = 15)
    @ApiModelProperty(value = "统一社会信用代码")
	private String tyshxydm;
	/**企业注册地*/
	@Excel(name = "企业注册地", width = 15)
    @ApiModelProperty(value = "企业注册地")
	private String qyzcd;
	/**评价状态*/
	@Excel(name = "评价状态", width = 15)
    @ApiModelProperty(value = "评价状态")
	private String pjzt;
	/**是否高企*/
	@Excel(name = "是否高企", width = 15)
    @ApiModelProperty(value = "是否高企")
	//@ExcelVerify(interHandler = true)
	private String sfgq;

}
