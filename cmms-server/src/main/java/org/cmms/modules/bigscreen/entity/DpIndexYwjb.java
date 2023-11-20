package org.cmms.modules.bigscreen.entity;

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
 * @Description: 大屏首页业务简报
 * @Author: jeecg-boot
 * @Date:   2023-11-02
 * @Version: V1.0
 */
@Data
@TableName("DP_INDEX_YWJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DP_INDEX_YWJB对象", description="大屏首页业务简报")
public class DpIndexYwjb {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**贷款亿元*/
	@Excel(name = "贷款亿元", width = 15)
    @ApiModelProperty(value = "贷款亿元")
	private Integer dk;
	/**贷款较年初*/
	@Excel(name = "贷款较年初", width = 15)
    @ApiModelProperty(value = "贷款较年初")
	private Integer dkjnc;
	/**存款亿元*/
	@Excel(name = "存款亿元", width = 15)
    @ApiModelProperty(value = "存款亿元")
	private Integer ck;
	/**存款较年初*/
	@Excel(name = "存款较年初", width = 15)
    @ApiModelProperty(value = "存款较年初")
	private Integer ckjnc;
	/**不良率*/
	@Excel(name = "不良率", width = 15)
    @ApiModelProperty(value = "不良率")
	private Integer bll;
	/**不良率较检测*/
	@Excel(name = "不良率较检测", width = 15)
    @ApiModelProperty(value = "不良率较检测")
	private Integer blljnc;
	/**评级*/
	@Excel(name = "评级", width = 15)
    @ApiModelProperty(value = "评级")
	private String pj;
	/**评级排名*/
	@Excel(name = "评级排名", width = 15)
    @ApiModelProperty(value = "评级排名")
	private String pjpm;
	/**收入*/
	@Excel(name = "收入", width = 15)
    @ApiModelProperty(value = "收入")
	private Integer sr;
	/**收入净利润*/
	@Excel(name = "收入净利润", width = 15)
    @ApiModelProperty(value = "收入净利润")
	private Integer srjlr;
	/**纳税*/
	@Excel(name = "纳税", width = 15)
    @ApiModelProperty(value = "纳税")
	private Integer ns;
	/**纳税累计*/
	@Excel(name = "纳税累计", width = 15)
    @ApiModelProperty(value = "纳税累计")
	private Integer nslj;
	/**资本充足率*/
	@Excel(name = "资本充足率", width = 15)
    @ApiModelProperty(value = "资本充足率")
	private Integer zbczl;
	/**资本充足率拨备率*/
	@Excel(name = "资本充足率拨备率", width = 15)
    @ApiModelProperty(value = "资本充足率拨备率")
	private Integer zbczlbbl;
	/**资产亿元*/
	@Excel(name = "资产亿元", width = 15)
    @ApiModelProperty(value = "资产亿元")
	private Integer zc;
	/**负债*/
	@Excel(name = "负债", width = 15)
    @ApiModelProperty(value = "负债")
	private Integer zcfz;
}
