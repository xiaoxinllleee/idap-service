package org.cmms.modules.sjxf.hxxt.zkjylsb.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 重空交易历史表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cbs_vpvv")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_vpvv对象", description="重空交易历史表")
public class Zkjylsb {
    
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer tranDate;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private Integer jrnlNo;
	/**序列号*/
	@Excel(name = "序列号", width = 15)
    @ApiModelProperty(value = "序列号")
	private Integer sequenceNo;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String tranTime;
	/**银行号*/
	@Excel(name = "银行号", width = 15)
    @ApiModelProperty(value = "银行号")
	private String instNo;
	/**省代码*/
	@Excel(name = "省代码", width = 15)
    @ApiModelProperty(value = "省代码")
	private String provinceCode;
	/**帐号*/
	@Excel(name = "帐号", width = 15)
    @ApiModelProperty(value = "帐号")
	private String acctNo;
	/**凭证类型*/
	@Excel(name = "凭证类型", width = 15)
    @ApiModelProperty(value = "凭证类型")
	private String ibdType;
	/**起始号码*/
	@Excel(name = "起始号码", width = 15)
    @ApiModelProperty(value = "起始号码")
	private String startSerialNo;
	/**终止号码*/
	@Excel(name = "终止号码", width = 15)
    @ApiModelProperty(value = "终止号码")
	private String endSerialNo;
	/**调用码*/
	@Excel(name = "调用码", width = 15)
    @ApiModelProperty(value = "调用码")
	private String callCode;
	/**交易代码*/
	@Excel(name = "交易代码", width = 15)
    @ApiModelProperty(value = "交易代码")
	private String tranNo;
	/**交易机构号*/
	@Excel(name = "交易机构号", width = 15)
    @ApiModelProperty(value = "交易机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String branchNo;
	/**交易柜员*/
	@Excel(name = "交易柜员", width = 15)
    @ApiModelProperty(value = "交易柜员")
	private String tellerNo;
	/**交易数量*/
	@Excel(name = "交易数量", width = 15)
    @ApiModelProperty(value = "交易数量")
	private Integer trnQty;
	/**旧状态      11-销毁      14-支取方式挂失      10-待销毁      03-在途      05-未用      07-未激活      30-遗失      31-内部作废      12-收回      13-外部作废      06-使用      32-圈存      33-止付      09-挂失      08-挂失确认      20-锁定*/
	@Excel(name = "旧状态      11-销毁      14-支取方式挂失      10-待销毁      03-在途      05-未用      07-未激活      30-遗失      31-内部作废      12-收回      13-外部作废      06-使用      32-圈存      33-止付      09-挂失      08-挂失确认      20-锁定", width = 15)
    @ApiModelProperty(value = "旧状态      11-销毁      14-支取方式挂失      10-待销毁      03-在途      05-未用      07-未激活      30-遗失      31-内部作废      12-收回      13-外部作废      06-使用      32-圈存      33-止付      09-挂失      08-挂失确认      20-锁定")
	private String oldStatus;
	/**新状态      11-销毁      14-支取方式挂失      10-待销毁      03-在途      05-未用      07-未激活      30-遗失      31-内部作废      12-收回      13-外部作废      06-使用      32-圈存      33-止付      09-挂失      08-挂失确认      20-锁定*/
	@Excel(name = "新状态      11-销毁      14-支取方式挂失      10-待销毁      03-在途      05-未用      07-未激活      30-遗失      31-内部作废      12-收回      13-外部作废      06-使用      32-圈存      33-止付      09-挂失      08-挂失确认      20-锁定", width = 15)
    @ApiModelProperty(value = "新状态      11-销毁      14-支取方式挂失      10-待销毁      03-在途      05-未用      07-未激活      30-遗失      31-内部作废      12-收回      13-外部作废      06-使用      32-圈存      33-止付      09-挂失      08-挂失确认      20-锁定")
	private String newStatus;
	/**授权柜员*/
	@Excel(name = "授权柜员", width = 15)
    @ApiModelProperty(value = "授权柜员")
	private String supTellerNo;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
	private String description;
	/**旧密码*/
	@Excel(name = "旧密码", width = 15)
    @ApiModelProperty(value = "旧密码")
	private String oldPassword;
	/**新密码*/
	@Excel(name = "新密码", width = 15)
    @ApiModelProperty(value = "新密码")
	private String newPassword;
	/**系统*/
	@Excel(name = "系统", width = 15)
    @ApiModelProperty(value = "系统")
	private String sys;
	/**过期日*/
	@Excel(name = "过期日", width = 15)
    @ApiModelProperty(value = "过期日")
	private Integer expiryDate;
	/**转出机构号*/
	@Excel(name = "转出机构号", width = 15)
    @ApiModelProperty(value = "转出机构号")
	private String frmBrNo;
	/**转入机构号*/
	@Excel(name = "转入机构号", width = 15)
    @ApiModelProperty(value = "转入机构号")
	private String toBrNo;
	/**转出柜员*/
	@Excel(name = "转出柜员", width = 15)
    @ApiModelProperty(value = "转出柜员")
	private String frmTelrNo;
	/**转入柜员*/
	@Excel(name = "转入柜员", width = 15)
    @ApiModelProperty(value = "转入柜员")
	private String toTelrNo;
	/**原交易流水号*/
	@Excel(name = "原交易流水号", width = 15)
    @ApiModelProperty(value = "原交易流水号")
	private Integer traceNo;
	/**冲正标识 Y:Yes冲正交易或该交易已被冲正 N:No非冲正交易或该交易未被冲正*/
	@Excel(name = "冲正标识 Y:Yes冲正交易或该交易已被冲正 N:No非冲正交易或该交易未被冲正", width = 15)
    @ApiModelProperty(value = "冲正标识 Y:Yes冲正交易或该交易已被冲正 N:No非冲正交易或该交易未被冲正")
	private String sysCorrectFlag;
	/**批处理标识*/
	@Excel(name = "批处理标识", width = 15)
    @ApiModelProperty(value = "批处理标识")
	private String sysBatchFlag;
	/**附加信息*/
	@Excel(name = "附加信息", width = 15)
    @ApiModelProperty(value = "附加信息")
	private String addiData;
	/**维护的日期*/
	@Excel(name = "维护的日期", width = 15)
    @ApiModelProperty(value = "维护的日期")
	private String lastMaintDate;
	/**维护标志*/
	@Excel(name = "维护标志", width = 15)
    @ApiModelProperty(value = "维护标志")
	private String lastMaintStat;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
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
