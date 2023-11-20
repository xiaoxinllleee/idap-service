package org.cmms.modules.sjxf.qtxt.cwglxt.gdxxb.entity;

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
 * @Description: 股东信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_stockhld_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_stockhld_info对象", description="股东信息表")
public class Gdxxb {
    
	/**机构代号*/
	@Excel(name = "机构代号", width = 15)
    @ApiModelProperty(value = "机构代号")
	private String opnBrNo;
	/**股东编号*/
	@Excel(name = "股东编号", width = 15)
    @ApiModelProperty(value = "股东编号")
	private String clientNo;
	/**股东名称*/
	@Excel(name = "股东名称", width = 15)
    @ApiModelProperty(value = "股东名称")
	private String clientName;
	/**股东类型*/
	@Excel(name = "股东类型", width = 15)
    @ApiModelProperty(value = "股东类型")
	private String clientType;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String certiftype;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String certifno;
	/**发证机关*/
	@Excel(name = "发证机关", width = 15)
    @ApiModelProperty(value = "发证机关")
	private String certifdep;
	/**股东地址*/
	@Excel(name = "股东地址", width = 15)
    @ApiModelProperty(value = "股东地址")
	private String address;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String telno;
	/**法定代表人姓名*/
	@Excel(name = "法定代表人姓名", width = 15)
    @ApiModelProperty(value = "法定代表人姓名")
	private String cprtName;
	/**法定代表人身份证号码*/
	@Excel(name = "法定代表人身份证号码", width = 15)
    @ApiModelProperty(value = "法定代表人身份证号码")
	private String cprtId;
	/**法定代表人联系电话*/
	@Excel(name = "法定代表人联系电话", width = 15)
    @ApiModelProperty(value = "法定代表人联系电话")
	private String cprtTelno;
	/**企业性质*/
	@Excel(name = "企业性质", width = 15)
    @ApiModelProperty(value = "企业性质")
	private String cmpType;
	/**营业执照号码*/
	@Excel(name = "营业执照号码", width = 15)
    @ApiModelProperty(value = "营业执照号码")
	private String buslceNo;
	/**员工所属单位编号*/
	@Excel(name = "员工所属单位编号", width = 15)
    @ApiModelProperty(value = "员工所属单位编号")
	private String branchNo;
	/**核心结算户账号*/
	@Excel(name = "核心结算户账号", width = 15)
    @ApiModelProperty(value = "核心结算户账号")
	private String acctNo;
	/**开户行号*/
	@Excel(name = "开户行号", width = 15)
    @ApiModelProperty(value = "开户行号")
	private String acctBranch;
	/**经办人姓名*/
	@Excel(name = "经办人姓名", width = 15)
    @ApiModelProperty(value = "经办人姓名")
	private String deputyName;
	/**经办人证件类型*/
	@Excel(name = "经办人证件类型", width = 15)
    @ApiModelProperty(value = "经办人证件类型")
	private String deputyType;
	/**经办人身份证号码*/
	@Excel(name = "经办人身份证号码", width = 15)
    @ApiModelProperty(value = "经办人身份证号码")
	private String deputyNo;
	/**代理人联系电话*/
	@Excel(name = "代理人联系电话", width = 15)
    @ApiModelProperty(value = "代理人联系电话")
	private String deputyTel;
	/**最后操作日期*/
	@Excel(name = "最后操作日期", width = 15)
    @ApiModelProperty(value = "最后操作日期")
	private String txDate;
	/**最后操作操作员*/
	@Excel(name = "最后操作操作员", width = 15)
    @ApiModelProperty(value = "最后操作操作员")
	private String operId;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**注册资本*/
	@Excel(name = "注册资本", width = 15)
    @ApiModelProperty(value = "注册资本")
	private java.math.BigDecimal regCapital;
	/**核心结算户名*/
	@Excel(name = "核心结算户名", width = 15)
    @ApiModelProperty(value = "核心结算户名")
	private String certifaddress;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String sex;
	/**行业*/
	@Excel(name = "行业", width = 15)
    @ApiModelProperty(value = "行业")
	private String industry;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**内部或外部股东区分标志*/
	@Excel(name = "内部或外部股东区分标志", width = 15)
    @ApiModelProperty(value = "内部或外部股东区分标志")
	private String ownership;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private String regDate;
	/**股东编号*/
	@Excel(name = "股东编号", width = 15)
    @ApiModelProperty(value = "股东编号")
	private String holdNo;
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
