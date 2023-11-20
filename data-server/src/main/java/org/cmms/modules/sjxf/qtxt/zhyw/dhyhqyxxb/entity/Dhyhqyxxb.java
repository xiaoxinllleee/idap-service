package org.cmms.modules.sjxf.qtxt.zhyw.dhyhqyxxb.entity;

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
 * @Description: 电话银行签约信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Fbuss_tel_signinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Fbuss_tel_signinfo对象", description="电话银行签约信息表")
public class Dhyhqyxxb {
    
	/**账号/卡号*/
	@Excel(name = "账号/卡号", width = 15)
    @ApiModelProperty(value = "账号/卡号")
	private String oldacctno;
	/**新账号*/
	@Excel(name = "新账号", width = 15)
    @ApiModelProperty(value = "新账号")
	private String newacctno;
	/**旧查询密码*/
	@Excel(name = "旧查询密码", width = 15)
    @ApiModelProperty(value = "旧查询密码")
	private String oldquerypwd;
	/**新查询密码*/
	@Excel(name = "新查询密码", width = 15)
    @ApiModelProperty(value = "新查询密码")
	private String newquerypwd;
	/**密码标志*/
	@Excel(name = "密码标志", width = 15)
    @ApiModelProperty(value = "密码标志")
	private String pwdflag;
	/**卡折标志*/
	@Excel(name = "卡折标志", width = 15)
    @ApiModelProperty(value = "卡折标志")
	private String cardflag;
	/**签约状态*/
	@Excel(name = "签约状态", width = 15)
    @ApiModelProperty(value = "签约状态")
	private String signstate;
	/**签约功能*/
	@Excel(name = "签约功能", width = 15)
    @ApiModelProperty(value = "签约功能")
	private String signfunction;
	/**签约渠道*/
	@Excel(name = "签约渠道", width = 15)
    @ApiModelProperty(value = "签约渠道")
	private String signchannel;
	/**账户类别*/
	@Excel(name = "账户类别", width = 15)
    @ApiModelProperty(value = "账户类别")
	private String accttype;
	/**经办人证件类型*/
	@Excel(name = "经办人证件类型", width = 15)
    @ApiModelProperty(value = "经办人证件类型")
	private String operidenttype;
	/**经办人证件号码*/
	@Excel(name = "经办人证件号码", width = 15)
    @ApiModelProperty(value = "经办人证件号码")
	private String operidentno;
	/**单笔限额*/
	@Excel(name = "单笔限额", width = 15)
    @ApiModelProperty(value = "单笔限额")
	private Long singlelimit;
	/**单日限额*/
	@Excel(name = "单日限额", width = 15)
    @ApiModelProperty(value = "单日限额")
	private Long daylimit;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15)
    @ApiModelProperty(value = "签约日期")
	private String signdate;
	/**签约时间*/
	@Excel(name = "签约时间", width = 15)
    @ApiModelProperty(value = "签约时间")
	private String signtime;
	/**签约机构*/
	@Excel(name = "签约机构", width = 15)
    @ApiModelProperty(value = "签约机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String signbranchid;
	/**签约柜员*/
	@Excel(name = "签约柜员", width = 15)
    @ApiModelProperty(value = "签约柜员")
	private String signtellerno;
	/**解约日期*/
	@Excel(name = "解约日期", width = 15)
    @ApiModelProperty(value = "解约日期")
	private String canceldate;
	/**解约时间*/
	@Excel(name = "解约时间", width = 15)
    @ApiModelProperty(value = "解约时间")
	private String canceltime;
	/**解约机构*/
	@Excel(name = "解约机构", width = 15)
    @ApiModelProperty(value = "解约机构")
	private String cancelbranchid;
	/**解约柜员*/
	@Excel(name = "解约柜员", width = 15)
    @ApiModelProperty(value = "解约柜员")
	private String canceltellerno;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**预留字段1*/
	@Excel(name = "预留字段1", width = 15)
    @ApiModelProperty(value = "预留字段1")
	private String reserved1;
	/**预留字段2*/
	@Excel(name = "预留字段2", width = 15)
    @ApiModelProperty(value = "预留字段2")
	private String reserved2;
	/**预留字段3*/
	@Excel(name = "预留字段3", width = 15)
    @ApiModelProperty(value = "预留字段3")
	private String reserved3;
	/**账务处理日期*/
	@Excel(name = "账务处理日期", width = 15)
    @ApiModelProperty(value = "账务处理日期")
	private String acctdate;
	/**单日累计金额*/
	@Excel(name = "单日累计金额", width = 15)
    @ApiModelProperty(value = "单日累计金额")
	private Long dayaccumulamt;
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
