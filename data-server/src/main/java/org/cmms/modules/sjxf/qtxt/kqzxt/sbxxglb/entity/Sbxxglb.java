package org.cmms.modules.sjxf.qtxt.kqzxt.sbxxglb.entity;

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
 * @Description: 设备信息管理表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Cpps_bcpbdevinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cpps_bcpbdevinfo对象", description="设备信息管理表")
public class Sbxxglb {
    
	/**设备标识号*/
	@Excel(name = "设备标识号", width = 15)
    @ApiModelProperty(value = "设备标识号")
	private String devid;
	/**设备类型:A-ATM;B-CDM;C-CRS;D-多媒体查询机;E-农民金融自助;P-间联POS;R-补登折机*/
	@Excel(name = "设备类型", width = 15)
    @ApiModelProperty(value = "设备类型")
	private String devtype;
	/**设备厂商编号*/
	@Excel(name = "设备厂商编号", width = 15)
    @ApiModelProperty(value = "设备厂商编号")
	private String devmanuid;
	/**设备编号*/
	@Excel(name = "设备编号", width = 15)
    @ApiModelProperty(value = "设备编号")
	private String devno;
	/**商户号*/
	@Excel(name = "商户号", width = 15)
    @ApiModelProperty(value = "商户号")
	private String merchantno;
	/**虚拟账户*/
	@Excel(name = "虚拟账户", width = 15)
    @ApiModelProperty(value = "虚拟账户")
	private String virtualaccno;
	/**设备IP地址*/
	@Excel(name = "设备IP地址", width = 15)
    @ApiModelProperty(value = "设备IP地址")
	private String devip;
	/**设备所属地区*/
	@Excel(name = "设备所属地区", width = 15)
    @ApiModelProperty(value = "设备所属地区")
	private String devcity;
	/**设备所属分行*/
	@Excel(name = "设备所属分行", width = 15)
    @ApiModelProperty(value = "设备所属分行")
	private String devzoneno;
	/**设备所属网点*/
	@Excel(name = "设备所属网点", width = 15)
    @ApiModelProperty(value = "设备所属网点")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String devbrno;
	/**设备安放地址类型0-城市；1-农村*/
	@Excel(name = "设备安放地址类型", width = 15)
    @ApiModelProperty(value = "设备安放地址类型")
	private String devaddrtype;
	/**设备安放地址*/
	@Excel(name = "设备安放地址", width = 15)
    @ApiModelProperty(value = "设备安放地址")
	private String devaddr;
	/**设备绑定电话*/
	@Excel(name = "设备绑定电话", width = 15)
    @ApiModelProperty(value = "设备绑定电话")
	private String devphone;
	/**设备连接方式0-间联;1-直联*/
	@Excel(name = "设备连接方式", width = 15)
    @ApiModelProperty(value = "设备连接方式")
	private String devmode;
	/**设备状态A-异常;0-启用;1-草稿;2-审批;3-停用;4-移机;5-报废;9-注销*/
	@Excel(name = "设备状态", width = 15)
    @ApiModelProperty(value = "设备状态")
	private String devstatus;
	/**设备签到状态0-签退;1-签到*/
	@Excel(name = "设备签到状态", width = 15)
    @ApiModelProperty(value = "设备签到状态")
	private String loginstatus;
	/**设备安放类型10-离行式；1-在行式*/
	@Excel(name = "设备安放类型", width = 15)
    @ApiModelProperty(value = "设备安放类型")
	private String fixtype1;
	/**设备安放类型20-穿墙式；1-大堂式*/
	@Excel(name = "设备安放类型", width = 15)
    @ApiModelProperty(value = "设备安放类型")
	private String fixtype2;
	/**设备单笔取款最大金额*/
	@Excel(name = "设备单笔取款最大金额", width = 15)
    @ApiModelProperty(value = "设备单笔取款最大金额")
	private String permaxamt;
	/**设备单天取款最大笔数*/
	@Excel(name = "设备单天取款最大笔数", width = 15)
    @ApiModelProperty(value = "设备单天取款最大笔数")
	private String daytotalcnt;
	/**设备单天取款最大金额*/
	@Excel(name = "设备单天取款最大金额", width = 15)
    @ApiModelProperty(value = "设备单天取款最大金额")
	private String daytotalamt;
	/**地区号*/
	@Excel(name = "地区号", width = 15)
    @ApiModelProperty(value = "地区号")
	private String cityno;
	/**设备柜员所属机构号*/
	@Excel(name = "设备柜员所属机构号", width = 15)
    @ApiModelProperty(value = "设备柜员所属机构号")
	private String brno;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String tellerno;
	/**授权柜员号*/
	@Excel(name = "授权柜员号", width = 15)
    @ApiModelProperty(value = "授权柜员号")
	private String authtellerno;
	/**出纳员*/
	@Excel(name = "出纳员", width = 15)
    @ApiModelProperty(value = "出纳员")
	private String cashier;
	/**A岗负责人*/
	@Excel(name = "A岗负责人", width = 15)
    @ApiModelProperty(value = "A岗负责人")
	private String person1;
	/**A岗负责人手机号*/
	@Excel(name = "A岗负责人手机号", width = 15)
    @ApiModelProperty(value = "A岗负责人手机号")
	private String person1phone;
	/**B岗负责人*/
	@Excel(name = "B岗负责人", width = 15)
    @ApiModelProperty(value = "B岗负责人")
	private String person2;
	/**B岗负责人手机号*/
	@Excel(name = "B岗负责人手机号", width = 15)
    @ApiModelProperty(value = "B岗负责人手机号")
	private String person2phone;
	/**负责人*/
	@Excel(name = "负责人", width = 15)
    @ApiModelProperty(value = "负责人")
	private String person3;
	/**负责人手机号*/
	@Excel(name = "负责人手机号", width = 15)
    @ApiModelProperty(value = "负责人手机号")
	private String person3phone;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15)
    @ApiModelProperty(value = "创建日期")
	private String crtdate;
	/**正式运营时间*/
	@Excel(name = "正式运营时间", width = 15)
    @ApiModelProperty(value = "正式运营时间")
	private String startdate;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15)
    @ApiModelProperty(value = "创建时间")
	private String crttime;
	/**创建地区号*/
	@Excel(name = "创建地区号", width = 15)
    @ApiModelProperty(value = "创建地区号")
	private String crtcity;
	/**创建网点号*/
	@Excel(name = "创建网点号", width = 15)
    @ApiModelProperty(value = "创建网点号")
	private String crtbrno;
	/**创建柜员号*/
	@Excel(name = "创建柜员号", width = 15)
    @ApiModelProperty(value = "创建柜员号")
	private String crttellerno;
	/**最近操作日期*/
	@Excel(name = "最近操作日期", width = 15)
    @ApiModelProperty(value = "最近操作日期")
	private String moddate;
	/**最近操作时间*/
	@Excel(name = "最近操作时间", width = 15)
    @ApiModelProperty(value = "最近操作时间")
	private String modtime;
	/**最近操作地区号*/
	@Excel(name = "最近操作地区号", width = 15)
    @ApiModelProperty(value = "最近操作地区号")
	private String modcity;
	/**最近操作网点号*/
	@Excel(name = "最近操作网点号", width = 15)
    @ApiModelProperty(value = "最近操作网点号")
	private String modbrno;
	/**最近操作柜员号*/
	@Excel(name = "最近操作柜员号", width = 15)
    @ApiModelProperty(value = "最近操作柜员号")
	private String modtellerno;
	/**代理行机构标识*/
	@Excel(name = "代理行机构标识", width = 15)
    @ApiModelProperty(value = "代理行机构标识")
	private String proxybrno;
	/**受理机构标识码*/
	@Excel(name = "受理机构标识码", width = 15)
    @ApiModelProperty(value = "受理机构标识码")
	private String sendbank;
	/**发送机构标识码*/
	@Excel(name = "发送机构标识码", width = 15)
    @ApiModelProperty(value = "发送机构标识码")
	private String sendclearbank;
	/**省联社报备时间*/
	@Excel(name = "省联社报备时间", width = 15)
    @ApiModelProperty(value = "省联社报备时间")
	private String supervisedate;
	/**省联社批复时间*/
	@Excel(name = "省联社批复时间", width = 15)
    @ApiModelProperty(value = "省联社批复时间")
	private String approvedate;
	/**安防验收时间*/
	@Excel(name = "安防验收时间", width = 15)
    @ApiModelProperty(value = "安防验收时间")
	private String securedate;
	/**监管部门报备时间*/
	@Excel(name = "监管部门报备时间", width = 15)
    @ApiModelProperty(value = "监管部门报备时间")
	private String admindate;
	/**加密方式*/
	@Excel(name = "加密方式", width = 15)
    @ApiModelProperty(value = "加密方式")
	private String encrypttype;
	/**主密钥更新标志0-未更新1-已更新*/
	@Excel(name = "主密钥更新标志", width = 15)
    @ApiModelProperty(value = "主密钥更新标志")
	private String firstflag;
	/**清算方式0-按T+0清算1-按T+1清算*/
	@Excel(name = "清算方式", width = 15)
    @ApiModelProperty(value = "清算方式")
	private String clearflag;
	/**清算行号*/
	@Excel(name = "清算行号", width = 15)
    @ApiModelProperty(value = "清算行号")
	private String clearbrno;
	/**账户开户机构*/
	@Excel(name = "账户开户机构", width = 15)
    @ApiModelProperty(value = "账户开户机构")
	private String accbrno;
	/**账户户名*/
	@Excel(name = "账户户名", width = 15)
    @ApiModelProperty(value = "账户户名")
	private String acctname;
	/**绑定账户号*/
	@Excel(name = "绑定账户号", width = 15)
    @ApiModelProperty(value = "绑定账户号")
	private String accno;
	/**设备型号*/
	@Excel(name = "设备型号", width = 15)
    @ApiModelProperty(value = "设备型号")
	private String devcode;
	/**管理员*/
	@Excel(name = "管理员", width = 15)
    @ApiModelProperty(value = "管理员")
	private String adminteller;
	/**交易批次号*/
	@Excel(name = "交易批次号", width = 15)
    @ApiModelProperty(value = "交易批次号")
	private String batchnum;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String devmacaddr;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**预留1（核心绑定标识;0,无绑定;1,绑定)*/
	@Excel(name = "预留1", width = 15)
    @ApiModelProperty(value = "预留1")
	private String reserved1;
	/**预留2*/
	@Excel(name = "预留2", width = 15)
    @ApiModelProperty(value = "预留2")
	private String reserved2;
	/**预留3*/
	@Excel(name = "预留3", width = 15)
    @ApiModelProperty(value = "预留3")
	private String reserved3;
	/**最后处理时间*/
	@Excel(name = "最后处理时间", width = 15)
    @ApiModelProperty(value = "最后处理时间")
	private String lasttime;
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
/*	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
