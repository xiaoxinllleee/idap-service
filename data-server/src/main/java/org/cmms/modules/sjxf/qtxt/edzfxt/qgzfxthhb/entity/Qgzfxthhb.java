package org.cmms.modules.sjxf.qtxt.edzfxt.qgzfxthhb.entity;

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
 * @Description: 全国支付系统行号表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Tgps_cnapsbankinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgps_cnapsbankinfo对象", description="全国支付系统行号表")
public class Qgzfxthhb {
    
	/**参与者行号*/
	@Excel(name = "参与者行号", width = 15)
    @ApiModelProperty(value = "参与者行号")
	private String bankcode;
	/**参与者全称*/
	@Excel(name = "参与者全称", width = 15)
    @ApiModelProperty(value = "参与者全称")
	private String bankname;
	/**参与者简称*/
	@Excel(name = "参与者简称", width = 15)
    @ApiModelProperty(value = "参与者简称")
	private String bankaliasname;
	/**参与者类别*/
	@Excel(name = "参与者类别", width = 15)
    @ApiModelProperty(value = "参与者类别")
	private String bankcatalog;
	/**参与者行别*/
	@Excel(name = "参与者行别", width = 15)
    @ApiModelProperty(value = "参与者行别")
	private String banktype;
	/**管辖人行行号*/
	@Excel(name = "管辖人行行号", width = 15)
    @ApiModelProperty(value = "管辖人行行号")
	private String pbccode;
	/**参与者所属CCPC*/
	@Excel(name = "参与者所属CCPC", width = 15)
    @ApiModelProperty(value = "参与者所属CCPC")
	private String ccpc;
	/**所属直接参与机构*/
	@Excel(name = "所属直接参与机构", width = 15)
    @ApiModelProperty(value = "所属直接参与机构")
	private String dreccode;
	/**代理清算参与机构号*/
	@Excel(name = "代理清算参与机构号", width = 15)
    @ApiModelProperty(value = "代理清算参与机构号")
	private String agentsettbank;
	/**上级参与者*/
	@Excel(name = "上级参与者", width = 15)
    @ApiModelProperty(value = "上级参与者")
	private String suprlist;
	/**承接行行号*/
	@Excel(name = "承接行行号", width = 15)
    @ApiModelProperty(value = "承接行行号")
	private String sbstitnbk;
	/**所属城市*/
	@Excel(name = "所属城市", width = 15)
    @ApiModelProperty(value = "所属城市")
	private String debtorcity;
	/**加入业务系统标识*/
	@Excel(name = "加入业务系统标识", width = 15)
    @ApiModelProperty(value = "加入业务系统标识")
	private String syscode;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String addr;
	/**邮编*/
	@Excel(name = "邮编", width = 15)
    @ApiModelProperty(value = "邮编")
	private String postcode;
	/**电话/电挂*/
	@Excel(name = "电话/电挂", width = 15)
    @ApiModelProperty(value = "电话/电挂")
	private String tel;
	/**电子邮件地址*/
	@Excel(name = "电子邮件地址", width = 15)
    @ApiModelProperty(value = "电子邮件地址")
	private String email;
	/**生效日期*/
	@Excel(name = "生效日期", width = 15)
    @ApiModelProperty(value = "生效日期")
	private String effectdate;
	/**注销日期*/
	@Excel(name = "注销日期", width = 15)
    @ApiModelProperty(value = "注销日期")
	private String expdate;
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
