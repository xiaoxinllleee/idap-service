package org.cmms.modules.sjxf.qtxt.khxxst.khjgxb.entity;

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
 * @Description: 客户间关系表
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("ECIF_CUSTREL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ECIF_CUSTREL对象", description="客户间关系表")
public class Khjgxb {
    
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String dataFlag;
	/**客户间关系ID*/
	@Excel(name = "客户间关系ID", width = 15)
    @ApiModelProperty(value = "客户间关系ID")
	private String custRelId;
	/**客户间关系类型*/
	@Excel(name = "客户间关系类型", width = 15)
    @ApiModelProperty(value = "客户间关系类型")
	private String custRelType;
	/**客户间关系描述*/
	@Excel(name = "客户间关系描述", width = 15)
    @ApiModelProperty(value = "客户间关系描述")
	private String custRelDesc;
	/**客户间关系状态*/
	@Excel(name = "客户间关系状态", width = 15)
    @ApiModelProperty(value = "客户间关系状态")
	private String custRelStat;
	/**源客户编号*/
	@Excel(name = "源客户编号", width = 15)
    @ApiModelProperty(value = "源客户编号")
	private String srcCustId;
	/**目标客户编号*/
	@Excel(name = "目标客户编号", width = 15)
    @ApiModelProperty(value = "目标客户编号")
	private String destCustId;
	/**关系开始日期*/
	@Excel(name = "关系开始日期", width = 15)
    @ApiModelProperty(value = "关系开始日期")
	private String relStartDate;
	/**关系结束日期*/
	@Excel(name = "关系结束日期", width = 15)
    @ApiModelProperty(value = "关系结束日期")
	private String relEndDate;
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
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String sDate;
	/**加载时间*/
	@Excel(name = "加载时间", width = 15)
    @ApiModelProperty(value = "加载时间")
	private String eDate;
	/**加载方式*/
    @ApiModelProperty(value = "加载方式")
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
