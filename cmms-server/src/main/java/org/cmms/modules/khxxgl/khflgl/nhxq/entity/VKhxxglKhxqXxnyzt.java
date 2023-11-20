package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

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
 * @Description: 新型农业主体
 * @Author: jeecg-boot
 * @Date:   2023-10-13
 * @Version: V1.0
 */
@Data
@TableName("v_khxxgl_khxq_xxnyzt")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khxxgl_khxq_xxnyzt对象", description="新型农业主体")
public class VKhxxglKhxqXxnyzt {
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15,dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "网格编号")
	@Dict(dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String wgbh;
	/**主体分类*/
	@Excel(name = "主体分类", width = 15)
    @ApiModelProperty(value = "主体分类")
	@Dict(dicCode = "xxnyzt-ztfl")
	private String ztfl;
	/**新型主体名称*/
	@Excel(name = "新型主体名称", width = 15)
    @ApiModelProperty(value = "新型主体名称")
	private String xxztmc;
	/**新型主体证件号码*/
	@Excel(name = "新型主体证件号码", width = 15)
    @ApiModelProperty(value = "新型主体证件号码")
	private String xxztzjhm;
	/**经营者*/
	@Excel(name = "经营者", width = 15)
    @ApiModelProperty(value = "经营者")
	private String jyz;
	/**经营者证件号码*/
	@Excel(name = "经营者证件号码", width = 15)
    @ApiModelProperty(value = "经营者证件号码")
	private String jyzzjhm;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
	private Integer ysxed;
	/**是否已授信*/
	@Excel(name = "是否已授信", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否已授信")
	@Dict(dicCode = "sfbz")
	private String sfysx;
	/**评定分类*/
	@Excel(name = "村组评定", width = 15,dicCode = "xxnyzt-cxpj")
    @ApiModelProperty(value = "评定分类")
	@Dict(dicCode = "xxnyzt-cxpj")
	private String pdfl;
	/**是否有信贷需求*/
	@Excel(name = "是否有信贷需求", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否有信贷需求")
	@Dict(dicCode = "sfbz")
	private String sfyxdxq;
	/**陪访人*/
	@Excel(name = "评议员", width = 15)
    @ApiModelProperty(value = "陪访人")
	private String pfr;
	/**是否正常经营*/
	@Excel(name = "是否正常经营", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否正常经营")
	@Dict(dicCode = "sfbz")
	private String sfzcjy;
	/**注册地址*/
	@Excel(name = "注册地址", width = 15)
    @ApiModelProperty(value = "注册地址")
	private String zcdz;
	/**管户人*/
	@Excel(name = "管户人", width = 15,dicCode = "yggh",dictTable = "hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "管户人")
	@Dict(dicCode = "yggh",dictTable = "hr_bas_staff",dicText = "ygxm")
	private String ghzrr;
	/**经营项目*/
	@Excel(name = "经营项目", width = 15)
    @ApiModelProperty(value = "经营项目")
	private String jyxm;
	/**经营规模*/
	@Excel(name = "经营规模", width = 15)
    @ApiModelProperty(value = "经营规模")
	private String jygm;
	/**年产值*/
	@Excel(name = "年产值", width = 15)
    @ApiModelProperty(value = "年产值")
	private Integer nsr;
	/**系统测算授信额度*/
	@Excel(name = "系统测算授信额度", width = 15)
    @ApiModelProperty(value = "系统测算授信额度")
	private Integer ysxedcj;
	/**白名单授信额度*/
	@Excel(name = "白名单授信额度", width = 15)
    @ApiModelProperty(value = "白名单授信额度")
	private Integer nsxedck;
	/**数据日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "sjrq")
	private Date sjrq;
	/**户号编码*/
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**有效签约对象*/
	@Excel(name = "有效签约对象", width = 15)
    @ApiModelProperty(value = "有效签约对象")
	private String yxqydx;
	/**有效签约对象证件号码 */
	@Excel(name = "有效签约对象证件号码", width = 15)
	@ApiModelProperty(value = "有效签约对象证件号码")
	private String yxqydxZjhm;
	/**有效签约额度*/
	@Excel(name = "有效签约额度", width = 15)
    @ApiModelProperty(value = "有效签约额度")
	private java.math.BigDecimal yxqyed;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**当年新增签约额度*/
	@Excel(name = "当年新增签约额度", width = 15)
    @ApiModelProperty(value = "当年新增签约额度")
	private java.math.BigDecimal dnxzqyed;
	/**当年贷款余额净增*/
	@Excel(name = "当年贷款余额净增", width = 15)
    @ApiModelProperty(value = "dndkyejz")
	private java.math.BigDecimal dndkyejz;

	/** 白名单授信（做筛选条件） */
	@TableField(exist = false)
	private String bmdysx;
}
