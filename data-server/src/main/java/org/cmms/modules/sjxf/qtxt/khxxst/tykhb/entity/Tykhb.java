package org.cmms.modules.sjxf.qtxt.khxxst.tykhb.entity;

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
 * @Description: 同业客户表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("ECIF_INTERBANK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ECIF_INTERBANK对象", description="同业客户表")
public class Tykhb {
    
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String dataFlag;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**同业客户类型*/
	@Excel(name = "同业客户类型", width = 15)
    @ApiModelProperty(value = "同业客户类型")
	private String finaCustType;
	/**行号*/
	@Excel(name = "行号", width = 15)
    @ApiModelProperty(value = "行号")
	private String bankNo;
	/**行名*/
	@Excel(name = "行名", width = 15)
    @ApiModelProperty(value = "行名")
	private String bankName;
	/**行别*/
	@Excel(name = "行别", width = 15)
    @ApiModelProperty(value = "行别")
	private String bankType;
	/**金融机构类型*/
	@Excel(name = "金融机构类型", width = 15)
    @ApiModelProperty(value = "金融机构类型")
	private String finaOrgType;
	/**金融许可证*/
	@Excel(name = "金融许可证", width = 15)
    @ApiModelProperty(value = "金融许可证")
	private String finaLicNo;
	/**金融机构代码*/
	@Excel(name = "金融机构代码", width = 15)
    @ApiModelProperty(value = "金融机构代码")
	private String finaOrgCode;
	/**法定代表人*/
	@Excel(name = "法定代表人", width = 15)
    @ApiModelProperty(value = "法定代表人")
	private String legalRepr;
	/**地区代码*/
	@Excel(name = "地区代码", width = 15)
    @ApiModelProperty(value = "地区代码")
	private String zoneCode;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
    @ApiModelProperty(value = "邮政编码")
	private String zipCode;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String address;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String tel;
	/**信用等级*/
	@Excel(name = "信用等级", width = 15)
    @ApiModelProperty(value = "信用等级")
	private String creditLevel;
	/**信用等级有效期*/
	@Excel(name = "信用等级有效期", width = 15)
    @ApiModelProperty(value = "信用等级有效期")
	private String creditLevelPeriod;
	/**现代支付系统行号*/
	@Excel(name = "现代支付系统行号", width = 15)
    @ApiModelProperty(value = "现代支付系统行号")
	private String modernPaySysNo;
	/**同城交换号*/
	@Excel(name = "同城交换号", width = 15)
    @ApiModelProperty(value = "同城交换号")
	private String exchangeNo;
	/**SWIFT*/
	@Excel(name = "SWIFT", width = 15)
    @ApiModelProperty(value = "SWIFT")
	private String swift;
	/**最后更新系统*/
	@Excel(name = "最后更新系统", width = 15)
    @ApiModelProperty(value = "最后更新系统")
	private String lastUpdateSys;
	/**最后更新人*/
	@Excel(name = "最后更新人", width = 15)
    @ApiModelProperty(value = "最后更新人")
	private String lastUpdateUser;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 15)
    @ApiModelProperty(value = "最后更新时间")
	private String lastUpdateTm;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private String txSeqNo;
	/**核心系统保存的银行证件*/
	@Excel(name = "核心系统保存的银行证件", width = 15)
    @ApiModelProperty(value = "核心系统保存的银行证件")
	private String coreBankIdentno;
	/**核心系统保存的字母编码*/
	@Excel(name = "核心系统保存的字母编码", width = 15)
    @ApiModelProperty(value = "核心系统保存的字母编码")
	private String coreAlaphaCode;
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
//	/**天入库表编号-对不同的表名唯一*/
//	@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
//    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
//	private Integer dtnum;
//	/**dttime*/
//	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @ApiModelProperty(value = "dttime")
//	private Date dttime;
}
