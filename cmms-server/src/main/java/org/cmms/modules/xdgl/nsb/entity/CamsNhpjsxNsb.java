package org.cmms.modules.xdgl.nsb.entity;

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
 * @Description: 浏阳农户评级授信农户年审表
 * @Author: jeecg-boot
 * @Date:   2022-10-12
 * @Version: V1.0
 */
@Data
@TableName("CAMS_NHPJSX_NSB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_NHPJSX_NSB对象", description="浏阳农户评级授信农户年审表")
public class CamsNhpjsxNsb {
    
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String creator;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**授信年份*/
	@Excel(name = "授信年份", width = 15)
    @ApiModelProperty(value = "授信年份")
	private String synf;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updator;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date updateTime;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**导入时网格名称*/
	@Excel(name = "所属网格", width = 15)
    @ApiModelProperty(value = "导入时网格名称")
	private String drwgmc;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	/**客户名称*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String khmc;
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15)
    @ApiModelProperty(value = "所属客户经理")
	private String sskhjl;
	/**导入时客户经理名称*/
	@Excel(name = "导入时客户经理名称", width = 15)
    @ApiModelProperty(value = "导入时客户经理名称")
	private String drsskhjl;
	/**证件号码*/
	@Excel(name = "身份证号码", width = 15)
    @ApiModelProperty(value = "身份证号码")
	private String zjhm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**与户主关系*/
	@Excel(name = "与户主关系", width = 15,dicCode = "yhzgx")
    @ApiModelProperty(value = "与户主关系")
	@Dict(dicCode = "yhzgx")
	private String yhzgx;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**初评等级*/
	@Excel(name = "新初评等级", width = 15)
    @ApiModelProperty(value = "初评等级")
	private String cpdj;
	/**初评金额万元*/
	@Excel(name = "新初评授信", width = 15)
    @ApiModelProperty(value = "初评金额万元")
	private BigDecimal cpje;
	/**复评等级*/
	@Excel(name = "新复评等级", width = 15)
    @ApiModelProperty(value = "复评等级")
	private String fpdj;
	/**复评金额*/
	@Excel(name = "新复评金额", width = 15)
    @ApiModelProperty(value = "复评金额")
	private BigDecimal fpje;
	/**产品类型*/
	@Excel(name = "产品类型", width = 15)
    @ApiModelProperty(value = "产品类型")
	private String cplx;
	/**导入产品类型*/
	@Excel(name = "导入产品类型", width = 15)
    @ApiModelProperty(value = "导入产品类型")
	private String drcplx;
	/**bz*/
	@Excel(name = "bz", width = 15)
    @ApiModelProperty(value = "bz")
	private String bz;
	private String errortype;
}
