package org.cmms.modules.sjxf.hxxt.jjk.kcpxx.entity;

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
 * @Description: 卡产品信息
 * @Author: jeecg-boot
 * @Date:   2021-12-06
 * @Version: V1.0
 */
@Data
@TableName("CBSC_PRODUCT")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CBSC_PRODUCT对象", description="卡产品信息")
public class Kcpxx {

	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private Integer institution;
	/**BIN号*/
	@Excel(name = "卡BIN号", width = 15)
    @ApiModelProperty(value = "卡BIN号")
	private Integer bin;
	/**产口号*/
	@Excel(name = "产口号", width = 15)
    @ApiModelProperty(value = "产口号")
	@TableField(value = "no")
	private Integer no;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String name;
	/**卡的默认有效时限*/
	@Excel(name = "卡的默认有效时限", width = 15)
    @ApiModelProperty(value = "卡的默认有效时限")
	private Integer defaultExpiry;
	/**服务码*/
	@Excel(name = "服务码", width = 15)
    @ApiModelProperty(value = "服务码")
	private String serviceRestrict;
	/**交易约束标识位*/
	@Excel(name = "交易约束标识位", width = 15)
    @ApiModelProperty(value = "交易约束标识位")
	private String txnRestrict;
	/**商户约束标识位*/
	@Excel(name = "商户约束标识位", width = 15)
    @ApiModelProperty(value = "商户约束标识位")
	private String merchantRestrict;
	/**商户类型限制*/
	@Excel(name = "商户类型限制", width = 15)
    @ApiModelProperty(value = "商户类型限制")
	private String merchanttypeRestrict;
	/**区域约束*/
	@Excel(name = "区域约束", width = 15)
    @ApiModelProperty(value = "区域约束")
	private Integer regionRestrict;
	/**附属卡数量*/
	@Excel(name = "附属卡数量", width = 15)
    @ApiModelProperty(value = "附属卡数量")
	private Integer subsidiaryRestrict;
	/**交易币种*/
	@Excel(name = "交易币种", width = 15)
    @ApiModelProperty(value = "交易币种")
	private String currencyRestrict;
	/**卡段索引*/
	@Excel(name = "卡段索引", width = 15)
    @ApiModelProperty(value = "卡段索引")
	private Integer cardRange;
	/**卡段格式*/
	@Excel(name = "卡段格式", width = 15)
    @ApiModelProperty(value = "卡段格式")
	private String cardRangeFormat;
	/**未用*/
	@Excel(name = "未用", width = 15)
    @ApiModelProperty(value = "未用")
	private Integer pvki;
	/**客户关联卡数量*/
	@Excel(name = "客户关联卡数量", width = 15)
    @ApiModelProperty(value = "客户关联卡数量")
	private Integer replica;
	/**电子渠道交易开关*/
	@Excel(name = "电子渠道交易开关", width = 15)
    @ApiModelProperty(value = "电子渠道交易开关")
	private String secondaryAccess;
	/**密码重输次数*/
	@Excel(name = "密码重输次数", width = 15)
    @ApiModelProperty(value = "密码重输次数")
	private Integer pinRetries;
	/**CVV重试次数*/
	@Excel(name = "CVV重试次数", width = 15)
    @ApiModelProperty(value = "CVV重试次数")
	private Integer cvvRetries;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String custTypeRestrict;
	/**客户性别约束*/
	@Excel(name = "客户性别约束", width = 15)
    @ApiModelProperty(value = "客户性别约束")
	private String genderRestrict;
	/**卡介质类型*/
	@Excel(name = "卡介质类型", width = 15)
    @ApiModelProperty(value = "卡介质类型")
	private String mediaType;
	/**重要空白凭证号*/
	@Excel(name = "重要空白凭证号", width = 15)
    @ApiModelProperty(value = "重要空白凭证号")
	private String ibdType;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
	/**换卡关联信息*/
	@Excel(name = "换卡关联信息", width = 15)
    @ApiModelProperty(value = "换卡关联信息")
	@TableField(value = "control_info_01")
	private String controlInfo01;
	/**换卡关联信息*/
	@Excel(name = "换卡关联信息", width = 15)
    @ApiModelProperty(value = "换卡关联信息")
	@TableField(value = "control_info_02")
	private String controlInfo02;
	/**换卡关联信息*/
	@Excel(name = "换卡关联信息", width = 15)
    @ApiModelProperty(value = "换卡关联信息")
	@TableField(value = "control_info_03")
	private String controlInfo03;
	/**换卡关联信息*/
	@Excel(name = "换卡关联信息", width = 15)
    @ApiModelProperty(value = "换卡关联信息")
	@TableField(value = "control_info_04")
	private String controlInfo04;
	/**换卡关联信息*/
	@Excel(name = "换卡关联信息", width = 15)
    @ApiModelProperty(value = "换卡关联信息")
	@TableField(value = "control_info_05")
	private String controlInfo05;
	/**换卡关联信息*/
	@Excel(name = "换卡关联信息", width = 15)
    @ApiModelProperty(value = "换卡关联信息")
	@TableField(value = "control_info_06")
	private String controlInfo06;
	/**换卡关联信息*/
	@Excel(name = "换卡关联信息", width = 15)
    @ApiModelProperty(value = "换卡关联信息")
	@TableField(value = "control_info_07")
	private String controlInfo07;
	/**换卡关联信息*/
	@Excel(name = "换卡关联信息", width = 15)
    @ApiModelProperty(value = "换卡关联信息")
	@TableField(value = "control_info_08")
	private String controlInfo08;
	/**换卡关联信息*/
	@Excel(name = "换卡关联信息", width = 15)
    @ApiModelProperty(value = "换卡关联信息")
	@TableField(value = "control_info_09")
	private String controlInfo09;
	/**换卡关联信息*/
	@Excel(name = "换卡关联信息", width = 15)
    @ApiModelProperty(value = "换卡关联信息")
	@TableField(value = "control_info_10")
	private String controlInfo10;
	/**账户关联信息*/
	@Excel(name = "账户关联信息", width = 15)
    @ApiModelProperty(value = "账户关联信息")
	@TableField(value = "link_info_01")
	private String linkInfo01;
	/**账户关联信息*/
	@Excel(name = "账户关联信息", width = 15)
    @ApiModelProperty(value = "账户关联信息")
	@TableField(value = "link_info_02")
	private String linkInfo02;
	/**账户关联信息*/
	@Excel(name = "账户关联信息", width = 15)
    @ApiModelProperty(value = "账户关联信息")
	@TableField(value = "link_info_03")
	private String linkInfo03;
	/**账户关联信息*/
	@Excel(name = "账户关联信息", width = 15)
    @ApiModelProperty(value = "账户关联信息")
	@TableField(value = "link_info_04")
	private String linkInfo04;
	/**账户关联信息*/
	@Excel(name = "账户关联信息", width = 15)
    @ApiModelProperty(value = "账户关联信息")
	@TableField(value = "link_info_05")
	private String linkInfo05;
	/**账户关联信息*/
	@Excel(name = "账户关联信息", width = 15)
    @ApiModelProperty(value = "账户关联信息")
	@TableField(value = "link_info_06")
	private String linkInfo06;
	/**账户关联信息*/
	@Excel(name = "账户关联信息", width = 15)
    @ApiModelProperty(value = "账户关联信息")
	@TableField(value = "link_info_07")
	private String linkInfo07;
	/**账户关联信息*/
	@Excel(name = "账户关联信息", width = 15)
    @ApiModelProperty(value = "账户关联信息")
	@TableField(value = "link_info_08")
	private String linkInfo08;
	/**账户关联信息*/
	@Excel(name = "账户关联信息", width = 15)
    @ApiModelProperty(value = "账户关联信息")
	@TableField(value = "link_info_09")
	private String linkInfo09;
	/**账户关联信息*/
	@Excel(name = "账户关联信息", width = 15)
    @ApiModelProperty(value = "账户关联信息")
	@TableField(value = "link_info_10")
	private String linkInfo10;
	/**开卡方式*/
	@Excel(name = "开卡方式", width = 15)
    @ApiModelProperty(value = "开卡方式")
	private String issueWayFlag;
	/**银联交易开通标识*/
	@Excel(name = "银联交易开通标识", width = 15)
    @ApiModelProperty(value = "银联交易开通标识")
	private String cuppayDefaultFlag;
	/**最近维护日期*/
    @ApiModelProperty(value = "最近维护日期")
	private Date lastMaintainDate;
	/**最近维护标志*/
	@Excel(name = "最近维护标志", width = 15)
    @ApiModelProperty(value = "最近维护标志")
	private String lastMaintainFlag;
	/**产品码*/
	@Excel(name = "产品码", width = 15)
    @ApiModelProperty(value = "产品码")
	private String productCode;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;

	/**天入库表编号-对不同的表名唯一*/
    /*@ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
