package org.cmms.modules.sjxf.qtxt.dssf.sfdkqyxx.entity;

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
 * @Description: 水费代扣签约信息
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_dssf_dealinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_dssf_dealinfo对象", description="水费代扣签约信息")
public class Sfdkqyxx {
    
	/**协议登记日期*/
	@Excel(name = "协议登记日期", width = 15)
    @ApiModelProperty(value = "协议登记日期")
	private String startdate;
	/**协议终止日期*/
	@Excel(name = "协议终止日期", width = 15)
    @ApiModelProperty(value = "协议终止日期")
	private String endDate;
	/**上供水公司流水号*/
	@Excel(name = "上供水公司流水号", width = 15)
    @ApiModelProperty(value = "上供水公司流水号")
	private String serialno;
	/**用户编号*/
	@Excel(name = "用户编号", width = 15)
    @ApiModelProperty(value = "用户编号")
	private String userNo;
	/**用户名称*/
	@Excel(name = "用户名称", width = 15)
    @ApiModelProperty(value = "用户名称")
	private String username;
	/**用户身份证号码*/
	@Excel(name = "用户身份证号码", width = 15)
    @ApiModelProperty(value = "用户身份证号码")
	private String userSfzno;
	/**操作机构代码*/
	@Excel(name = "操作机构代码", width = 15)
    @ApiModelProperty(value = "操作机构代码")
	private String operbankno;
	/**操作柜员号*/
	@Excel(name = "操作柜员号", width = 15)
    @ApiModelProperty(value = "操作柜员号")
	private String operno;
	/**授权柜员号*/
	@Excel(name = "授权柜员号", width = 15)
    @ApiModelProperty(value = "授权柜员号")
	private String powerteller;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String bustype;
	/**协议状态*/
	@Excel(name = "协议状态", width = 15)
    @ApiModelProperty(value = "协议状态")
	private String status;
	/**银行账号*/
	@Excel(name = "银行账号", width = 15)
    @ApiModelProperty(value = "银行账号")
	private String accountno;
	/**卡折标志*/
	@Excel(name = "卡折标志", width = 15)
    @ApiModelProperty(value = "卡折标志")
	private String kzflag;
	/**账户名称*/
	@Excel(name = "账户名称", width = 15)
    @ApiModelProperty(value = "账户名称")
	private String acctName;
	/**开户网点*/
	@Excel(name = "开户网点", width = 15)
    @ApiModelProperty(value = "开户网点")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String bankCode;
	/**接水点编号*/
	@Excel(name = "接水点编号", width = 15)
    @ApiModelProperty(value = "接水点编号")
	private String jsdNo;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**用户地址*/
	@Excel(name = "用户地址", width = 15)
    @ApiModelProperty(value = "用户地址")
	private String remark1;
	/**联系方式1*/
	@Excel(name = "联系方式1", width = 15)
    @ApiModelProperty(value = "联系方式1")
	private String remark2;
	/**联系方式2*/
	@Excel(name = "联系方式2", width = 15)
    @ApiModelProperty(value = "联系方式2")
	private String remark3;
	/**新账号*/
	@Excel(name = "新账号", width = 15)
    @ApiModelProperty(value = "新账号")
	private String newAccountno;
	/**起始日期*/
	@Excel(name = "起始日期", width = 15)
    @ApiModelProperty(value = "起始日期")
	private String sDate;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
	private String eDate;
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
