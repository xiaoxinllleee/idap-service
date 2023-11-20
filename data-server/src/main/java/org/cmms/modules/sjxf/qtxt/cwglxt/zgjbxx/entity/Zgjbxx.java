package org.cmms.modules.sjxf.qtxt.cwglxt.zgjbxx.entity;

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
 * @Description: 职工基本信息
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_staff_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_staff_info对象", description="职工基本信息")
public class Zgjbxx {

	/**职工号*/
	@Excel(name = "职工号", width = 15)
    @ApiModelProperty(value = "职工号")
	private String staffNo;
	/**职工名字*/
	@Excel(name = "职工名字", width = 15)
    @ApiModelProperty(value = "职工名字")
	private String name;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String idType;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String idNo;
	/**职工职位描述*/
	@Excel(name = "职工职位描述", width = 15)
    @ApiModelProperty(value = "职工职位描述")
	private String telDesc;
	/**结算账号*/
	@Excel(name = "结算账号", width = 15)
    @ApiModelProperty(value = "结算账号")
	private String acNo;
	/**应付账号*/
	@Excel(name = "应付账号", width = 15)
    @ApiModelProperty(value = "应付账号")
	private String accNo;
	/**职工默认部门号*/
	@Excel(name = "职工默认部门号", width = 15)
    @ApiModelProperty(value = "职工默认部门号")
	private String depNo;
	/**所属法人联社*/
	@Excel(name = "所属法人联社", width = 15)
    @ApiModelProperty(value = "所属法人联社")
	private String upBrNo;
	/**结算户开户行名称*/
	@Excel(name = "结算户开户行名称", width = 15)
    @ApiModelProperty(value = "结算户开户行名称")
	private String acBrName;
	/**股金证号*/
	@Excel(name = "股金证号", width = 15)
    @ApiModelProperty(value = "股金证号")
	private String stockNo;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String sts;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String filler;
	/**状态2*/
	@Excel(name = "状态2", width = 15)
    @ApiModelProperty(value = "状态2")
	private String csts;
	/**是否入股*/
	@Excel(name = "是否入股", width = 15)
    @ApiModelProperty(value = "是否入股")
	private String isShareholder;
	/**住址*/
	@Excel(name = "住址", width = 15)
    @ApiModelProperty(value = "住址")
	private String addr;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
	private String phoneNo;
	/**紧急联系人*/
	@Excel(name = "紧急联系人", width = 15)
    @ApiModelProperty(value = "紧急联系人")
	private String emcContact;
	/**紧急联系人电话*/
	@Excel(name = "紧急联系人电话", width = 15)
    @ApiModelProperty(value = "紧急联系人电话")
	private String emcPhoneNo;
	/**结算户名*/
	@Excel(name = "结算户名", width = 15)
    @ApiModelProperty(value = "结算户名")
	private String acName;
	/**电子邮箱*/
	@Excel(name = "电子邮箱", width = 15)
    @ApiModelProperty(value = "电子邮箱")
	private String email;
	/**职务*/
	@Excel(name = "职务", width = 15)
    @ApiModelProperty(value = "职务")
	private String staffPost;
	/**职称*/
	@Excel(name = "职称", width = 15)
    @ApiModelProperty(value = "职称")
	private String techTitle;
	/**入职日期*/
	@Excel(name = "入职日期", width = 15)
    @ApiModelProperty(value = "入职日期")
	private String hireDate;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15)
    @ApiModelProperty(value = "政治面貌")
	private String politicalSts;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String sex;
	/**学历*/
	@Excel(name = "学历", width = 15)
    @ApiModelProperty(value = "学历")
	private String edu;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String nation;
	/**聘用形式*/
	@Excel(name = "聘用形式", width = 15)
    @ApiModelProperty(value = "聘用形式")
	private String hireType;
	/**职工目标机构*/
	@Excel(name = "职工目标机构", width = 15)
    @ApiModelProperty(value = "职工目标机构")
	private String desDepNo;
	/**借调日期*/
	@Excel(name = "借调日期", width = 15)
    @ApiModelProperty(value = "借调日期")
	private Integer seDate;
	/**是否借调*/
	@Excel(name = "是否借调", width = 15)
    @ApiModelProperty(value = "是否借调")
	private String isFlag;
	/**撤并时间*/
	@Excel(name = "撤并时间", width = 15)
    @ApiModelProperty(value = "撤并时间")
	private Integer cbDate;
	/**撤并前机构号*/
	@Excel(name = "撤并前机构号", width = 15)
    @ApiModelProperty(value = "撤并前机构号")
	private String cbqDepNo;
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
	private Integer dtnum;
	*//**dttime*//*
	@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}
