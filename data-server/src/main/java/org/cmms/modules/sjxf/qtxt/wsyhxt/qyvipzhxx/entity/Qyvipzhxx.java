package org.cmms.modules.sjxf.qtxt.wsyhxt.qyvipzhxx.entity;

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
 * @Description: 企业VIP账号信息
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_cb_accinf_vip")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_cb_accinf_vip对象", description="企业VIP账号信息")
public class Qyvipzhxx {
    
	/**流水号(自动生成)*/
	@Excel(name = "流水号(自动生成)", width = 15)
    @ApiModelProperty(value = "流水号(自动生成)")
	private String livFlowno;
	/**网银客户号*/
	@Excel(name = "网银客户号", width = 15)
    @ApiModelProperty(value = "网银客户号")
	private String aifCstno;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String aifAccno;
	/**操作标志(0:增加;1:修改;2:删除)默认:0*/
	@Excel(name = "操作标志", width = 15)
    @ApiModelProperty(value = "操作标志")
	private String livOptflag;
	/**启用标志(0:未启用;1:启用)*/
	@Excel(name = "启用标志(0:未启用;1:启用)", width = 15)
    @ApiModelProperty(value = "启用标志(0:未启用;1:启用)")
	private String livFlag;
	/**网银状态(0:正常,1:挂失,4:冻结,5:注销)*/
	@Excel(name = "网银状态", width = 15)
    @ApiModelProperty(value = "网银状态")
	private String aifStt;
	/**账号核心开户网点*/
	@Excel(name = "账号核心开户网点", width = 15)
    @ApiModelProperty(value = "账号核心开户网点")
	private String aifOpennode;
	/**账号核心开户网点名称*/
	@Excel(name = "账号核心开户网点名称", width = 15)
    @ApiModelProperty(value = "账号核心开户网点名称")
	private String aifBranchname;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String aifCrytype;
	/**单笔限额*/
	@Excel(name = "单笔限额", width = 15)
    @ApiModelProperty(value = "单笔限额")
	private java.math.BigDecimal livSinglemax;
	/**日累计限额*/
	@Excel(name = "日累计限额", width = 15)
    @ApiModelProperty(value = "日累计限额")
	private java.math.BigDecimal livDaymax;
	/**核心客户号*/
	@Excel(name = "核心客户号", width = 15)
    @ApiModelProperty(value = "核心客户号")
	private String cifHostno;
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
