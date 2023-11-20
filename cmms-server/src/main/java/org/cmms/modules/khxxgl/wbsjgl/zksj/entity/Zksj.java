package org.cmms.modules.khxxgl.wbsjgl.zksj.entity;

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
 * @Description: 制卡数据
 * @Author: jeecg-boot
 * @Date:   2022-02-22
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_wbsjgl_zksj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_wbsjgl_zksj对象", description="制卡数据")
public class Zksj {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**证件号码*/
	@Excel(name = "身份证", width = 15)
	@ApiModelProperty(value = "身份证")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String khmc;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**联系地址*/
	@Excel(name = "联系地址", width = 15)
    @ApiModelProperty(value = "联系地址")
	private String lxdz;
	/**单位*/
	@Excel(name = "单位", width = 15)
    @ApiModelProperty(value = "单位")
	private String dw;
	/**网点名称*/
	@Excel(name = "网点名称", width = 15)
    @ApiModelProperty(value = "网点名称")
	private String wdmc;
	/**银行名称*/
	@Excel(name = "银行名称", width = 15)
    @ApiModelProperty(value = "银行名称")
	private String yhmc;
	/**银行卡号*/
	@Excel(name = "银行卡号", width = 15)
    @ApiModelProperty(value = "银行卡号")
	private String yhkh;
	/**卡状态*/
	@Excel(name = "卡状态", width = 15)
    @ApiModelProperty(value = "卡状态")
	private String kzt;
	/**医保用卡记录*/
	@Excel(name = "医保用卡记录", width = 15)
    @ApiModelProperty(value = "医保用卡记录")
	private String ybykjl;
	/**前期排查的疑似风险卡*/
	@Excel(name = "前期排查的疑似风险卡", width = 15)
    @ApiModelProperty(value = "前期排查的疑似风险卡")
	private String ysfxk;
	/**登记序号*/
	@Excel(name = "登记序号", width = 15)
	@ApiModelProperty(value = "登记序号")
	private String djxh;
	/**社保号*/
	@Excel(name = "社保号", width = 15)
	@ApiModelProperty(value = "社保号")
	private String sbh;
	/**计税依据*/
	@Excel(name = "计税依据", width = 15)
	@ApiModelProperty(value = "计税依据")
	private String jsyj;
	/**实缴金额*/
	@Excel(name = "实缴金额", width = 15)
	@ApiModelProperty(value = "实缴金额")
	private String sjje;
	/**应征发生日期*/
	@Excel(name = "应征发生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "应征发生日期")
	private Date yzfsrq;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}
