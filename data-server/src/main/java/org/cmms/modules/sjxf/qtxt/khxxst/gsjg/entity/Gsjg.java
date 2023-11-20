package org.cmms.modules.sjxf.qtxt.khxxst.gsjg.entity;

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
 * @Description: 归属机构
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("ECIF_BELONG_BRANCH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ECIF_BELONG_BRANCH对象", description="归属机构")
public class Gsjg {
    
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String dataFlag;
	/**归属机构ID*/
	@Excel(name = "归属机构ID", width = 15)
    @ApiModelProperty(value = "归属机构ID")
	private String belongBranchId;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**归属机构类型（存款机构、贷款机构）*/
	@Excel(name = "归属机构类型（存款机构、贷款机构）", width = 15)
    @ApiModelProperty(value = "归属机构类型（存款机构、贷款机构）")
	private String belongBranchType;
	/**归属机构编号*/
	@Excel(name = "归属机构编号", width = 15)
    @ApiModelProperty(value = "归属机构编号")
	private String belongBranchNo;
	/**有效标志*/
	@Excel(name = "有效标志", width = 15)
    @ApiModelProperty(value = "有效标志")
	private String validFlag;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15)
    @ApiModelProperty(value = "开始日期")
	private String startDate;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
	private String endDate;
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
