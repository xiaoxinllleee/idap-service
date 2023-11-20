package org.cmms.modules.sjxf.qtxt.khxxst.khxxb.entity;

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
 * @Description: 客户信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("ECIF_CUSTOMER")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ECIF_CUSTOMER对象", description="客户信息表")
public class Khxxb {
    
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String dataFlag;
	/**客户创建机构编号*/
	@Excel(name = "客户创建机构编号", width = 15)
	@ApiModelProperty(value = "客户创建机构编号")
	private String createBranchNo;
	/**客户创建柜员编号*/
	@Excel(name = "客户创建柜员编号", width = 15)
	@ApiModelProperty(value = "客户创建柜员编号")
	private String createTellerNo;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
	@ApiModelProperty(value = "客户编号")
	private String custId;
	/**核心客户号*/
	@Excel(name = "核心客户号", width = 15)
    @ApiModelProperty(value = "核心客户号")
	private String coreNo;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String custType;
	/**财务系统客户身份*/
	@Excel(name = "财务系统客户身份", width = 15)
    @ApiModelProperty(value = "财务系统客户身份")
	private String finCustRole;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String identType;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String identNo;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String custName;
	/**客户简称*/
	@Excel(name = "客户简称", width = 15)
    @ApiModelProperty(value = "客户简称")
	private String shortName;
	/**英文名称*/
	@Excel(name = "英文名称", width = 15)
    @ApiModelProperty(value = "英文名称")
	private String enName;
	/**英文简称*/
	@Excel(name = "英文简称", width = 15)
    @ApiModelProperty(value = "英文简称")
	private String enShortName;
	/**客户状态*/
	@Excel(name = "客户状态", width = 15)
    @ApiModelProperty(value = "客户状态")
	private String custStat;
	/**境内外标志*/
	@Excel(name = "境内外标志", width = 15)
    @ApiModelProperty(value = "境内外标志")
	private String inoutFlag;
	/**VIP等级*/
	@Excel(name = "VIP等级", width = 15)
    @ApiModelProperty(value = "VIP等级")
	private String vipLevel;
	/**客户合并标志*/
	@Excel(name = "客户合并标志", width = 15)
    @ApiModelProperty(value = "客户合并标志")
	private String mergeFlag;
	/**联系人姓名*/
	@Excel(name = "联系人姓名", width = 15)
    @ApiModelProperty(value = "联系人姓名")
	private String linkmanName;
	/**联系人电话*/
	@Excel(name = "联系人电话", width = 15)
    @ApiModelProperty(value = "联系人电话")
	private String linkmanTel;
	/**推荐人*/
	@Excel(name = "推荐人", width = 15)
    @ApiModelProperty(value = "推荐人")
	private String recommender;
	/**客户创建日期*/
	@Excel(name = "客户创建日期", width = 15)
    @ApiModelProperty(value = "客户创建日期")
	private String createDate;
	/**客户创建时间*/
	@Excel(name = "客户创建时间", width = 15)
    @ApiModelProperty(value = "客户创建时间")
	private String createTime;
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
