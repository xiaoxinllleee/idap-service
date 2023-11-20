package org.cmms.modules.sjxf.xdxt.khzjxx.entity;

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
 * @Description: 客户证件信息
 * @Author: jeecg-boot
 * @Date:   2021-12-12
 * @Version: V1.0
 */
@Data
@TableName("Cms_ctfc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_ctfc对象", description="客户证件信息")
public class Khzjxx {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**成立日期*/
	@Excel(name = "成立日期", width = 15)
    @ApiModelProperty(value = "成立日期")
	private String createDate;
	/**标注地址,证件标注地,证件标注地址,注册地址*/
	@Excel(name = "标注地址,证件标注地,证件标注地址,注册地址", width = 15)
    @ApiModelProperty(value = "标注地址,证件标注地,证件标注地址,注册地址")
	private String ctfcAddr;
	/**颁发单位,登记机关,发证机构,发证机关,发证税务机关,认证机构*/
	@Excel(name = "颁发单位,登记机关,发证机构,发证机关,发证税务机关,认证机构", width = 15)
    @ApiModelProperty(value = "颁发单位,登记机关,发证机构,发证机关,发证税务机关,认证机构")
	private String ctfcAwardDept;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String ctfcCd;
	/**年检日期*/
	@Excel(name = "年检日期", width = 15)
    @ApiModelProperty(value = "年检日期")
	private String ctfcCheckDate;
	/**年检结果(是否有效),是否年检,证件类型*/
	@Excel(name = "年检结果(是否有效),是否年检,证件类型", width = 15)
    @ApiModelProperty(value = "年检结果(是否有效),是否年检,证件类型")
	private String ctfcCheckState;
	/**登记号,经营范围(66*3),实际住址,证件描述(66*3),主营范围(66*3)*/
	@Excel(name = "登记号,经营范围(66*3),实际住址,证件描述(66*3),主营范围(66*3)", width = 15)
    @ApiModelProperty(value = "登记号,经营范围(66*3),实际住址,证件描述(66*3),主营范围(66*3)")
	private String ctfcDesc;
	/**兼营范围(66*3),经营方式,凭证号*/
	@Excel(name = "兼营范围(66*3),经营方式,凭证号", width = 15)
    @ApiModelProperty(value = "兼营范围(66*3),经营方式,凭证号")
	private String ctfcDesc1;
	/**经营到期日期,失效日期,证件年检日期*/
	@Excel(name = "经营到期日期,失效日期,证件年检日期", width = 15)
    @ApiModelProperty(value = "经营到期日期,失效日期,证件年检日期")
	private String ctfcEndDate;
	/**流水编号*/
	@Excel(name = "流水编号", width = 15)
    @ApiModelProperty(value = "流水编号")
	private String ctfcId;
	/**注册类型*/
	@Excel(name = "注册类型", width = 15)
    @ApiModelProperty(value = "注册类型")
	private String ctfcLoginType;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String ctfcName;
	/**证书名称*/
	@Excel(name = "证书名称", width = 15)
    @ApiModelProperty(value = "证书名称")
	private String ctfcOthCd;
	/**实收资本*/
	@Excel(name = "实收资本", width = 15)
    @ApiModelProperty(value = "实收资本")
	private java.math.BigDecimal ctfcPaiupSum;
	/**贷款卡密码*/
	@Excel(name = "贷款卡密码", width = 15)
    @ApiModelProperty(value = "贷款卡密码")
	private String ctfcPassword;
	/**注册资本*/
	@Excel(name = "注册资本", width = 15)
    @ApiModelProperty(value = "注册资本")
	private java.math.BigDecimal ctfcRegSum;
	/**登记日期,发证日期,经营起始日期,生效日期*/
	@Excel(name = "登记日期,发证日期,经营起始日期,生效日期", width = 15)
    @ApiModelProperty(value = "登记日期,发证日期,经营起始日期,生效日期")
	private String ctfcStartDate;
	/**贷款卡状态,证件级别,资质等级*/
	@Excel(name = "贷款卡状态,证件级别,资质等级", width = 15)
    @ApiModelProperty(value = "贷款卡状态,证件级别,资质等级")
	private String ctfcState;
	/**经营期限,有效年限,证件有效期*/
	@Excel(name = "经营期限,有效年限,证件有效期", width = 15)
    @ApiModelProperty(value = "经营期限,有效年限,证件有效期")
	private String ctfcTerm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**有/无期限*/
	@Excel(name = "有/无期限", width = 15)
    @ApiModelProperty(value = "有/无期限")
	private String hasCtfcTerm;
	/**是否为注册证件*/
	@Excel(name = "是否为注册证件", width = 15)
    @ApiModelProperty(value = "是否为注册证件")
	private String register;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**调查人*/
	@Excel(name = "调查人", width = 15)
    @ApiModelProperty(value = "调查人")
	private String checkId;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String inputDate;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**暂末用*/
	@Excel(name = "暂末用", width = 15)
    @ApiModelProperty(value = "暂末用")
	private String principal;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String gender;
	/**照片路径*/
	@Excel(name = "照片路径", width = 15)
    @ApiModelProperty(value = "照片路径")
	private String photo;
	/**年检时间*/
	@Excel(name = "年检时间", width = 15)
    @ApiModelProperty(value = "年检时间")
	private String ctfccdate;
	/**是否有效*/
	@Excel(name = "是否有效", width = 15)
    @ApiModelProperty(value = "是否有效")
	private String isEnabled;
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
