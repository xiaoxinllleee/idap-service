package org.cmms.modules.sjxf.qtxt.gmqdxt.jylsdjxx.entity;

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
 * @Description: 交易流水登记信息
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Tler_logmaster")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tler_logmaster对象", description="交易流水登记信息")
public class Jylsdjxx {
    
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String trandate;
	/**柜面交易流水号*/
	@Excel(name = "柜面交易流水号", width = 15)
    @ApiModelProperty(value = "柜面交易流水号")
	private String taskno;
	/**交易时间,精确到毫秒*/
	@Excel(name = "交易时间,精确到毫秒", width = 15)
    @ApiModelProperty(value = "交易时间,精确到毫秒")
	private String transtime;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private String tradecode;
	/**交易名称*/
	@Excel(name = "交易名称", width = 15)
    @ApiModelProperty(value = "交易名称")
	private String tranname;
	/**机构号*/
	@Excel(name = "机构号", width = 15)
    @ApiModelProperty(value = "机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String orgno;
	/**经办柜员号*/
	@Excel(name = "经办柜员号", width = 15)
    @ApiModelProperty(value = "经办柜员号")
	private String tlid;
	/**同机授权柜员号*/
	@Excel(name = "同机授权柜员号", width = 15)
    @ApiModelProperty(value = "同机授权柜员号")
	private String authtlid;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String transstatus;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String trantype;
	/**系统码*/
	@Excel(name = "系统码", width = 15)
    @ApiModelProperty(value = "系统码")
	private String syscode;
	/**会计日期*/
	@Excel(name = "会计日期", width = 15)
    @ApiModelProperty(value = "会计日期")
	private String accdate;
	/**会计时间*/
	@Excel(name = "会计时间", width = 15)
    @ApiModelProperty(value = "会计时间")
	private String acctime;
	/**后台流水号*/
	@Excel(name = "后台流水号", width = 15)
    @ApiModelProperty(value = "后台流水号")
	private String serialno;
	/**后台返回码*/
	@Excel(name = "后台返回码", width = 15)
    @ApiModelProperty(value = "后台返回码")
	private String code;
	/**后台返回信息*/
	@Excel(name = "后台返回信息", width = 15)
    @ApiModelProperty(value = "后台返回信息")
	private String message;
	/**客户账号*/
	@Excel(name = "客户账号", width = 15)
    @ApiModelProperty(value = "客户账号")
	private String accountno;
	/**账户姓名*/
	@Excel(name = "账户姓名", width = 15)
    @ApiModelProperty(value = "账户姓名")
	private String accountname;
	/**分账号*/
	@Excel(name = "分账号", width = 15)
    @ApiModelProperty(value = "分账号")
	private String faccountno;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String money;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String currtype;
	/**钞汇标志*/
	@Excel(name = "钞汇标志", width = 15)
    @ApiModelProperty(value = "钞汇标志")
	private String commcode;
	/**交易数据路径*/
	@Excel(name = "交易数据路径", width = 15)
    @ApiModelProperty(value = "交易数据路径")
	private String trandatapath;
	/**上送报文路径*/
	@Excel(name = "上送报文路径", width = 15)
    @ApiModelProperty(value = "上送报文路径")
	private String upmespath;
	/**下送报文路径*/
	@Excel(name = "下送报文路径", width = 15)
    @ApiModelProperty(value = "下送报文路径")
	private String downmespath;
	/**uuid*/
	@Excel(name = "uuid", width = 15)
    @ApiModelProperty(value = "uuid")
	private String uuid;
	/**sendmap*/
	@Excel(name = "sendmap", width = 15)
    @ApiModelProperty(value = "sendmap")
	private String sendmap;
	/**原流水号*/
	@Excel(name = "原流水号", width = 15)
    @ApiModelProperty(value = "原流水号")
	private String tracenum;
	/**借方账号序号*/
	@Excel(name = "借方账号序号", width = 15)
    @ApiModelProperty(value = "借方账号序号")
	private String childxuno;
	/**贷方账号*/
	@Excel(name = "贷方账号", width = 15)
    @ApiModelProperty(value = "贷方账号")
	private String debitno;
	/**贷方户名*/
	@Excel(name = "贷方户名", width = 15)
    @ApiModelProperty(value = "贷方户名")
	private String debitname;
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
/*	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
