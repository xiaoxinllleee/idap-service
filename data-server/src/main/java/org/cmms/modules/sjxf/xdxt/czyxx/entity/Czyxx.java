package org.cmms.modules.sjxf.xdxt.czyxx.entity;

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
 * @Description: 操作员信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_user")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_user对象", description="操作员信息")
public class Czyxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**五级分类权限*/
	@Excel(name = "五级分类权限", width = 15)
    @ApiModelProperty(value = "五级分类权限")
	private java.math.BigDecimal fiveValue;
	/**评级权限*/
	@Excel(name = "评级权限", width = 15)
    @ApiModelProperty(value = "评级权限")
	private java.math.BigDecimal evalValue;
	/**呆账核销权限*/
	@Excel(name = "呆账核销权限", width = 15)
    @ApiModelProperty(value = "呆账核销权限")
	private java.math.BigDecimal badValue;
	/**对应的客户编号*/
	@Excel(name = "对应的客户编号", width = 15)
    @ApiModelProperty(value = "对应的客户编号")
	private String custId;
	/**前台柜员号*/
	@Excel(name = "前台柜员号", width = 15)
    @ApiModelProperty(value = "前台柜员号")
	private String frontCd;
	/**是否可用*/
	@Excel(name = "是否可用", width = 15)
    @ApiModelProperty(value = "是否可用")
	private String isEnabled;
	/**该操作员的权限*/
	@Excel(name = "该操作员的权限", width = 15)
    @ApiModelProperty(value = "该操作员的权限")
	private String loginIp;
	/**暂时无作用*/
	@Excel(name = "暂时无作用", width = 15)
    @ApiModelProperty(value = "暂时无作用")
	private String loginTime;
	/**列表显示行数*/
	@Excel(name = "列表显示行数", width = 15)
    @ApiModelProperty(value = "列表显示行数")
	private String pageSize;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**登陆号*/
	@Excel(name = "登陆号", width = 15)
    @ApiModelProperty(value = "登陆号")
	private String userCd;
	/**流水编号*/
	@Excel(name = "流水编号", width = 15)
    @ApiModelProperty(value = "流水编号")
	private String userId;
	/**登陆密码*/
	@Excel(name = "登陆密码", width = 15)
    @ApiModelProperty(value = "登陆密码")
	private String userPassword;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15)
    @ApiModelProperty(value = "录入时间")
	private String inputDate;
	/**修改日期*/
	@Excel(name = "修改日期", width = 15)
    @ApiModelProperty(value = "修改日期")
	private String updateDate;
	/**放款日期(起*/
	@Excel(name = "放款日期(起", width = 15)
    @ApiModelProperty(value = "放款日期(起")
	private String startDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String endDate;
	/**审批金额*/
	@Excel(name = "审批金额", width = 15)
    @ApiModelProperty(value = "审批金额")
	private java.math.BigDecimal checkValue;
	/**是否注销*/
	@Excel(name = "是否注销", width = 15)
    @ApiModelProperty(value = "是否注销")
	private String islogoff;
	/**密码更新日期*/
	@Excel(name = "密码更新日期", width = 15)
    @ApiModelProperty(value = "密码更新日期")
	private String passwordUpdate;
	/**操作员状态*/
	@Excel(name = "操作员状态", width = 15)
    @ApiModelProperty(value = "操作员状态")
	private String userStatus;
	/**登录IP*/
	@Excel(name = "登录IP", width = 15)
    @ApiModelProperty(value = "登录IP")
	private String loginIpAddress;
	/**是否固定贷审会成员*/
	@Excel(name = "是否固定贷审会成员", width = 15)
    @ApiModelProperty(value = "是否固定贷审会成员")
	private String isFixMember;
	/**是否固定风管会成员*/
	@Excel(name = "是否固定风管会成员", width = 15)
    @ApiModelProperty(value = "是否固定风管会成员")
	private String isRiskFixMember;
	/**企业损失类权限*/
	@Excel(name = "企业损失类权限", width = 15)
    @ApiModelProperty(value = "企业损失类权限")
	private java.math.BigDecimal fiveEmpLoseValue;
	/**企业非损失类权限*/
	@Excel(name = "企业非损失类权限", width = 15)
    @ApiModelProperty(value = "企业非损失类权限")
	private java.math.BigDecimal noFiveEmpLoseValue;
	/**个人损失类权限*/
	@Excel(name = "个人损失类权限", width = 15)
    @ApiModelProperty(value = "个人损失类权限")
	private java.math.BigDecimal fivePerLoseValue;
	/**个人非损失类权限*/
	@Excel(name = "个人非损失类权限", width = 15)
    @ApiModelProperty(value = "个人非损失类权限")
	private java.math.BigDecimal noFivePerLoseValue;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String telePhone;
	/**公开授信权限(授信)*/
	@Excel(name = "公开授信权限(授信)", width = 15)
    @ApiModelProperty(value = "公开授信权限(授信)")
	private java.math.BigDecimal creditValue;
	/**操作员类型*/
	@Excel(name = "操作员类型", width = 15)
    @ApiModelProperty(value = "操作员类型")
	private String userType;
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
