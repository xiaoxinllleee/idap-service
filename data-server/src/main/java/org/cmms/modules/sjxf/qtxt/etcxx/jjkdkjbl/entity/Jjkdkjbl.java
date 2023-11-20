package org.cmms.modules.sjxf.qtxt.etcxx.jjkdkjbl.entity;

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
 * @Description: 借记卡垫款记录表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ibus_etc_jjkdkjl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_etc_jjkdkjl对象", description="借记卡垫款记录表")
public class Jjkdkjbl {
    
	/**借记卡卡号*/
	@Excel(name = "借记卡卡号", width = 15)
    @ApiModelProperty(value = "借记卡卡号")
	private String kh;
	/**垫款日期*/
	@Excel(name = "垫款日期", width = 15)
    @ApiModelProperty(value = "垫款日期")
	private String dkrq;
	/**垫款时间*/
	@Excel(name = "垫款时间", width = 15)
    @ApiModelProperty(value = "垫款时间")
	private String dksj;
	/**垫款流水号*/
	@Excel(name = "垫款流水号", width = 15)
    @ApiModelProperty(value = "垫款流水号")
	private String dklsh;
	/**垫款金额*/
	@Excel(name = "垫款金额", width = 15)
    @ApiModelProperty(value = "垫款金额")
	private String dkje;
	/**垫款原因*/
	@Excel(name = "垫款原因", width = 15)
    @ApiModelProperty(value = "垫款原因")
	private String dkyy;
	/**是否还清*/
	@Excel(name = "是否还清", width = 15)
    @ApiModelProperty(value = "是否还清")
	private String sfhq;
	/**上核心流水号*/
	@Excel(name = "上核心流水号", width = 15)
    @ApiModelProperty(value = "上核心流水号")
	private String hostserialno;
	/**回收日期*/
	@Excel(name = "回收日期", width = 15)
    @ApiModelProperty(value = "回收日期")
	private String hsrq;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	private String khjg;
	/**湘通卡号*/
	@Excel(name = "湘通卡号", width = 15)
    @ApiModelProperty(value = "湘通卡号")
	private String xtkh;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**账户类型*/
	@Excel(name = "账户类型", width = 15)
    @ApiModelProperty(value = "账户类型")
	private String zhlx;
	/**核心返回流水号*/
	@Excel(name = "核心返回流水号", width = 15)
    @ApiModelProperty(value = "核心返回流水号")
	private String hostseqno;
	/**老账号*/
	@Excel(name = "老账号", width = 15)
    @ApiModelProperty(value = "老账号")
	private String oldZh;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
