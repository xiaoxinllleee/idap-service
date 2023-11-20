package org.cmms.modules.sjxf.qtxt.wsyhxt.grzckhqdxxb.entity;

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
 * @Description: 个人注册客户渠道信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_pb_channelinf")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_pb_channelinf对象", description="个人注册客户渠道信息表")
public class Grzckhqdxxb {
    
	/**网银客户号*/
	@Excel(name = "网银客户号", width = 15)
    @ApiModelProperty(value = "网银客户号")
	private String cstno;
	/**0网银，1手机银行*/
	@Excel(name = "类型(0网银，1手机银行)", width = 15)
    @ApiModelProperty(value = "类型(0网银，1手机银行)")
	private String channelid;
	/**开户柜员号*/
	@Excel(name = "开户柜员号", width = 15)
    @ApiModelProperty(value = "开户柜员号")
	private String openteller;
	/**开户网点机构号*/
	@Excel(name = "开户网点机构号", width = 15)
    @ApiModelProperty(value = "开户网点机构号")
	private String opennode;
	/**开户时间*/
	@Excel(name = "开户时间", width = 15)
    @ApiModelProperty(value = "开户时间")
	private String opentime;
	/**客户经理编号*/
	@Excel(name = "客户经理编号", width = 15)
    @ApiModelProperty(value = "客户经理编号")
	private String manamcode;
	/**客户经理姓名*/
	@Excel(name = "客户经理姓名", width = 15)
    @ApiModelProperty(value = "客户经理姓名")
	private String manamname;
	/**最后更新柜员*/
	@Excel(name = "最后更新柜员", width = 15)
    @ApiModelProperty(value = "最后更新柜员")
	private String modifyteller;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 15)
    @ApiModelProperty(value = "最后更新时间")
	private String modifytime;
	/**冻结或注销原因*/
	@Excel(name = "冻结或注销原因", width = 15)
    @ApiModelProperty(value = "冻结或注销原因")
	private String reason;
	/**开户授权柜员号*/
	@Excel(name = "开户授权柜员号", width = 15)
    @ApiModelProperty(value = "开户授权柜员号")
	private String openauthteller;
	/**销户柜员号*/
	@Excel(name = "销户柜员号", width = 15)
    @ApiModelProperty(value = "销户柜员号")
	private String cancelteller;
	/**销户授权柜员号*/
	@Excel(name = "销户授权柜员号", width = 15)
    @ApiModelProperty(value = "销户授权柜员号")
	private String cancelauthteller;
	/**销户网点机构号*/
	@Excel(name = "销户网点机构号", width = 15)
    @ApiModelProperty(value = "销户网点机构号")
	private String cancelnode;
	/**销户时间*/
	@Excel(name = "销户时间", width = 15)
    @ApiModelProperty(value = "销户时间")
	private String canceltime;
	/**认证方式*/
	@Excel(name = "认证方式", width = 15)
    @ApiModelProperty(value = "认证方式")
	private String security;
	/**索引*/
	@Excel(name = "索引", width = 15)
    @ApiModelProperty(value = "索引")
	private String equipno;
	/**渠道状态*/
	@Excel(name = "渠道状态", width = 15)
    @ApiModelProperty(value = "渠道状态")
	private String stt;
	/**起始日期*/
	@Excel(name = "起始日期", width = 15)
    @ApiModelProperty(value = "起始日期")
	private String sDate;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
	private String eDate;
	/**导入日期*/
    @ApiModelProperty(value = "导入日期")
	private Date loadDate;
	/**法人编号*/
	/*@Excel(name = "法人编号", width = 15)
    @ApiModelProperty(value = "法人编号")
	private String legalNo;*/
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
}
