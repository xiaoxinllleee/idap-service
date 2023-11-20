package org.cmms.modules.report.sgtzgl.hnkjxzxqymd.entity;

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
 * @Description: 湖南科技型中小企业名单
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_kjxzxqy")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_kjxzxqy对象", description="湖南科技型中小企业名单")
public class SgztHnkjxzxqymd {

	/**主键ID*/
//	@TableId(type = IdType.ASSIGN_ID)
//    @ApiModelProperty(value = "主键ID")
//	private String id;
	/**数据日期*/
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
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
	private java.lang.String sfgq;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;

}
