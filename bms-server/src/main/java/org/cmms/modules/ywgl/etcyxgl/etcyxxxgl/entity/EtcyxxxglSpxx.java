package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity;

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
 * @Description: ETC营销管理审批信息
 * @Author: jeecg-boot
 * @Date:   2021-12-22
 * @Version: V1.0
 */
@Data
@TableName("Erp_bas_etcyxspxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_bas_etcyxspxx对象", description="ETC营销管理审批信息")
public class EtcyxxxglSpxx {
    
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**移交日期*/
	@Excel(name = "移交日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "移交日期")
	private Date yjrq;
	/**流程编号*/
	@Excel(name = "流程编号", width = 15)
    @ApiModelProperty(value = "流程编号")
	private String processid;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessnumber;
	/**绑定帐卡号*/
	@Excel(name = "绑定帐卡号", width = 15)
    @ApiModelProperty(value = "绑定帐卡号")
	private String bdzkh;
	/**原营销人工号*/
	@Excel(name = "原营销人工号", width = 15)
    @ApiModelProperty(value = "原营销人工号")
	private String yyxrgh;
	/**新营销人工号*/
	@Excel(name = "新营销人工号", width = 15)
    @ApiModelProperty(value = "新营销人工号")
	private String xyxrgh;
	/**原营销机构代码*/
	@Excel(name = "原营销机构代码", width = 15)
    @ApiModelProperty(value = "原营销机构代码")
	private String yyxjgdm;
	/**新营销机构代码*/
	@Excel(name = "新营销机构代码", width = 15)
    @ApiModelProperty(value = "新营销机构代码")
	private String xyxjgdm;
	/**流程状态*/
	@Excel(name = "流程状态", width = 15)
    @ApiModelProperty(value = "流程状态")
	private String lczt;
	/**申请说明*/
	@Excel(name = "申请说明", width = 15)
    @ApiModelProperty(value = "申请说明")
	private String sqsm;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
	/**网点机构码*/
	@Excel(name = "网点机构码", width = 15)
    @ApiModelProperty(value = "网点机构码")
	private String yywdjgm;
	/**办理渠道*/
	@Excel(name = "办理渠道", width = 15)
    @ApiModelProperty(value = "办理渠道")
	private String blqd;
	/**办理员工编号*/
	@Excel(name = "办理员工编号", width = 15)
    @ApiModelProperty(value = "办理员工编号")
	private String blygbh;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**客户身份证号*/
	@Excel(name = "客户身份证号", width = 15)
    @ApiModelProperty(value = "客户身份证号")
	private String khsfzh;
	/**车牌号码*/
	@Excel(name = "车牌号码", width = 15)
    @ApiModelProperty(value = "车牌号码")
	private String cphm;
	/**车牌颜色*/
	@Excel(name = "车牌颜色", width = 15)
    @ApiModelProperty(value = "车牌颜色")
	private String cpys;
	/**获客方式*/
	@Excel(name = "获客方式", width = 15)
    @ApiModelProperty(value = "获客方式")
	private String hkfs;
}
