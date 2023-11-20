package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 评级授信支行数据
 * @Author: jeecg-boot
 * @Date:   2023-11-10
 * @Version: V1.0
 */
@Data
@TableName("PJSX_ZHSJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PJSX_ZHSJ对象", description="评级授信支行数据")
public class PjsxZhsj {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "创建人", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "creator")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String creator;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
    @ApiModelProperty(value = "updator")
	private String updator;
	/**组织标志*/
	@Excel(name = "所属支行", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**镇*/
	@Excel(name = "镇", width = 15)
    @ApiModelProperty(value = "镇")
	private String zhen;
	/**村*/
	@Excel(name = "村", width = 15)
    @ApiModelProperty(value = "村")
	private String cun;
	/**组*/
	@Excel(name = "组", width = 15)
    @ApiModelProperty(value = "组")
	private String zu;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**户主姓名*/
	@Excel(name = "户主姓名", width = 15)
    @ApiModelProperty(value = "户主姓名")
	private String hzxm;
	/**户主证件号码*/
	@Excel(name = "户主证件号码", width = 15)
    @ApiModelProperty(value = "户主证件号码")
	private String hzzjhm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15,dicCode = "yhzgx")
    @ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx"
	)
	private String yhzgx;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**原信用等级*/
	@Excel(name = "原信用等级", width = 15)
    @ApiModelProperty(value = "原信用等级")
	private String yxydj;
	/**原信用金额*/
	@Excel(name = "原信用金额", width = 15)
    @ApiModelProperty(value = "原信用金额")
	private BigDecimal ysxje;
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15)
    @ApiModelProperty(value = "所属客户经理")
	private String sskhjl;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private BigDecimal dkje;
	/**存款金额*/
	@Excel(name = "存款金额", width = 15)
    @ApiModelProperty(value = "存款金额")
	private BigDecimal ckje;
	/**存款日平*/
	@Excel(name = "存款日平", width = 15)
    @ApiModelProperty(value = "存款日平")
	private BigDecimal ckrp;
	/**表内不良*/
	@Excel(name = "表内不良", width = 15)
    @ApiModelProperty(value = "表内不良")
	private BigDecimal blbl;
	/**表外不良*/
	@Excel(name = "表外不良", width = 15)
    @ApiModelProperty(value = "表外不良")
	private BigDecimal bwbl;
	/**年审分类*/
	@Excel(name = "年审分类", width = 15)
    @ApiModelProperty(value = "年审分类")
	@Dict(dicCode = "ly_xend_nsfl")
	private String nsfl;
	/**年审分类原因*/
	@Excel(name = "年审分类原因", width = 15)
    @ApiModelProperty(value = "年审分类原因")
	private String nsflyy;
	/**信贷金额*/
	@Excel(name = "信贷金额", width = 15)
    @ApiModelProperty(value = "信贷金额")
	private BigDecimal xdje;
	/**信贷冻结金额*/
	@Excel(name = "信贷冻结金额", width = 15)
    @ApiModelProperty(value = "信贷冻结金额")
	private BigDecimal xddjje;
	/**近三年日平*/
	@Excel(name = "近三年日平", width = 15)
    @ApiModelProperty(value = "近三年日平")
	private BigDecimal sanckrp;
	/**逾期次数*/
	@Excel(name = "逾期次数", width = 15)
    @ApiModelProperty(value = "逾期次数")
	private BigDecimal yqcs;
	/**户籍地址*/
	@Excel(name = "户籍地址", width = 15)
    @ApiModelProperty(value = "户籍地址")
	private String hjdz;
	/**信贷机构代码*/
	@Excel(name = "信贷机构代码", width = 15)
    @ApiModelProperty(value = "信贷机构代码")
	private String xdjgdm;
	private String sszh;
	private String sswg;
	private String xcpdj;
	private String xfpdj;
	private String xycp;
	private BigDecimal xcpsx;
	private BigDecimal xfpsx;
}
