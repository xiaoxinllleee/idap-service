package org.cmms.modules.sjxf.qtxt.khxxst.khdjxx.entity;

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
 * @Description: 客户等级信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("ECIF_GRADE")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ECIF_GRADE对象", description="客户等级信息")
public class Khdjxx {
    
	/**删除标识*/
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
	private String dataFlag;
	/**客户等级ID*/
	@Excel(name = "客户等级ID", width = 15)
    @ApiModelProperty(value = "客户等级ID")
	private String custGradeId;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**评级机构编码存储法人编码*/
	@Excel(name = "评级机构编码存储法人编码", width = 15)
    @ApiModelProperty(value = "评级机构编码存储法人编码")
	private String orgCode;
	/**评级机构名称*/
	@Excel(name = "评级机构名称", width = 15)
    @ApiModelProperty(value = "评级机构名称")
	private String orgName;
	/**等级类型码值:VIP普通*/
	@Excel(name = "等级类型码值:VIP普通", width = 15)
    @ApiModelProperty(value = "等级类型码值:VIP普通")
	private String custGradeType;
	/**客户等级等级类型=VIP时生效0普通客户*/
	@Excel(name = "客户等级等级类型=VIP时生效0普通客户", width = 15)
    @ApiModelProperty(value = "客户等级等级类型=VIP时生效0普通客户")
	private String custGrade;
	/**评定日期*/
	@Excel(name = "评定日期", width = 15)
    @ApiModelProperty(value = "评定日期")
	private String evaluateDate;
	/**生效日期*/
	@Excel(name = "生效日期", width = 15)
    @ApiModelProperty(value = "生效日期")
	private String effectiveDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String expiredDate;
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
	/**有效期*/
	@Excel(name = "有效期", width = 15)
    @ApiModelProperty(value = "有效期")
	private String validPeriod;
	/**原ECIF法人编号*/
	@Excel(name = "原ECIF法人编号", width = 15)
    @ApiModelProperty(value = "原ECIF法人编号")
	private String legalNoOri;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String sDate;
	/**加载时间*/
	@Excel(name = "加载时间", width = 15)
    @ApiModelProperty(value = "加载时间")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人编号*/
	@Excel(name = "法人编号", width = 15)
    @ApiModelProperty(value = "法人编号")
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
