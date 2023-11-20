package org.cmms.modules.khgxgl.entity;

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
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.enums.DesensitizeRuleEnums;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 个人客户
 * @Author: jeecg-boot
 * @Date:   2022-03-07
 * @Version: V1.0
 */
@Data
@TableName("KHGXGL_KHZLGL_GRKH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGXGL_KHZLGL_GRKH对象", description="个人客户")
public class KhgxglKhzlglGrkh {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String khbh;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	@Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
	private String khmc;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String zjlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String custType;
	/**客户类型1*/
	@Excel(name = "客户类型1", width = 15)
    @ApiModelProperty(value = "客户类型1")
	private String custType1;
	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15)
    @ApiModelProperty(value = "客户类型2")
	private String custType2;
	/**客户类型3*/
	@Excel(name = "客户类型3", width = 15)
    @ApiModelProperty(value = "客户类型3")
	private String custType3;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private String xb;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
	private String mz;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
	private Date csrq;
	/**工作单位名称*/
	@Excel(name = "工作单位名称", width = 15)
    @ApiModelProperty(value = "工作单位名称")
	private String gzdwmc;
	/**单位地址*/
	@Excel(name = "单位地址", width = 15)
    @ApiModelProperty(value = "单位地址")
	private String dwdz;
	/**单位电话*/
	@Excel(name = "单位电话", width = 15)
    @ApiModelProperty(value = "单位电话")
	private String dwdh;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
	private String hyzk;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	@Desensitize(rule = DesensitizeRuleEnums.MOBILE_PHONE)
	private String sjhm;
	/**固定电话*/
	@Excel(name = "固定电话", width = 15)
    @ApiModelProperty(value = "固定电话")
	private String gddh;
	/**电子邮箱*/
	@Excel(name = "电子邮箱", width = 15)
    @ApiModelProperty(value = "电子邮箱")
	@Desensitize(rule = DesensitizeRuleEnums.EMAIL)
	private String dzyx;
	/**住址*/
	@Excel(name = "住址", width = 15)
    @ApiModelProperty(value = "住址")
	@Desensitize(rule = DesensitizeRuleEnums.ADDRESS)
	private String zz;
	/**最高学历*/
	@Excel(name = "最高学历", width = 15)
    @ApiModelProperty(value = "最高学历")
	private String zgxl;
	/**最高学位*/
	@Excel(name = "最高学位", width = 15)
    @ApiModelProperty(value = "最高学位")
	private String zgxw;
	/**行业分类*/
	@Excel(name = "行业分类", width = 15)
    @ApiModelProperty(value = "行业分类")
	private String hyfl;
	/**职业*/
	@Excel(name = "职业", width = 15)
    @ApiModelProperty(value = "职业")
	private String zy;
	/**邮编*/
	@Excel(name = "邮编", width = 15)
    @ApiModelProperty(value = "邮编")
	private String yb;
	/**个人性格*/
	@Excel(name = "个人性格", width = 15)
    @ApiModelProperty(value = "个人性格")
	private String grxg;
	/**兴趣*/
	@Excel(name = "兴趣", width = 15)
    @ApiModelProperty(value = "兴趣")
	private String xq;
	/**爱好*/
	@Excel(name = "爱好", width = 15)
    @ApiModelProperty(value = "爱好")
	private String ah;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**账户类型*/
	@Excel(name = "账户类型", width = 15)
    @ApiModelProperty(value = "账户类型")
	private String zhlx;
	/**营销类型（1：主动营销 2：自然增长）*/
	@Excel(name = "营销类型（1：主动营销 2：自然增长）", width = 15)
    @ApiModelProperty(value = "营销类型（1：主动营销 2：自然增长）")
	private Integer yxlx;
	/**产品信息*/
	@Excel(name = "产品信息", width = 15)
    @ApiModelProperty(value = "产品信息")
	private String cpxx;
	/**最早开户日期*/
	@Excel(name = "最早开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早开户日期")
	private Date zzkhrq;
	/**所属等级*/
	@Excel(name = "所属等级", width = 15)
    @ApiModelProperty(value = "所属等级")
	private String ssdj;
	/**ECIF 客户编号*/
	@Excel(name = "ECIF 客户编号", width = 15)
    @ApiModelProperty(value = "ECIF 客户编号")
	private String custId;
}
