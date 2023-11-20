package org.cmms.modules.khgl.jgsydw.entity;

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
 * @Description: 机关事业单位
 * @Author: jeecg-boot
 * @Date:   2020-02-17
 * @Version: V1.0
 */
@Data
@TableName("KHGL_JGSYDWJBXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGL_JGSYDWJBXX对象", description="机关事业单位")
public class JgsydwJbxx {
    
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "所属支行")
    @Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String sszh;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15, dicCode="qybm",dictTable="yxdygl_czxxgl",dicText="village || organize")
    @ApiModelProperty(value = "所属营销单元")
    @Dict(dicCode="qybm",dictTable="yxdygl_czxxgl",dicText="village || organize")
	private String ssyxdy;
	/**归属客户经理*/
	@Excel(name = "归属客户经理", width = 15, dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
    @ApiModelProperty(value = "归属客户经理")
    @Dict(dicCode = "YGGH", dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private String gskhjl;
	/**档案编号*/
	@Excel(name = "档案编号", width = 15)
    @ApiModelProperty(value = "档案编号")
	private String dabh;
	/**单位名称*/
	@Excel(name = "单位名称", width = 15)
    @ApiModelProperty(value = "单位名称")
	private String dwmc;
	/**主要负责人*/
	@Excel(name = "主要负责人", width = 15)
    @ApiModelProperty(value = "主要负责人")
	private String zyfzr;
	/**负责人证件号码*/
	@Excel(name = "负责人证件号码", width = 15)
    @ApiModelProperty(value = "负责人证件号码")
	private String fzrzjhm;
	/**单位电话*/
	@Excel(name = "单位电话", width = 15)
    @ApiModelProperty(value = "单位电话")
	private String dwdh;
	/**传真号*/
	@Excel(name = "传真号", width = 15)
    @ApiModelProperty(value = "传真号")
	private String czh;
	/**邮政编码*/
	@Excel(name = "邮政编码", width = 15)
    @ApiModelProperty(value = "邮政编码")
	private String yzbm;
	/**单位地址*/
	@Excel(name = "单位地址", width = 15)
    @ApiModelProperty(value = "单位地址")
	private String dwdz;
	/**行业分类*/
	@Excel(name = "行业分类", width = 15)
    @ApiModelProperty(value = "行业分类")
	private String hyfl;
	/**职工人数*/
	@Excel(name = "职工人数", width = 15)
    @ApiModelProperty(value = "职工人数")
	private Integer zgrs;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15, dicCode = "khgl_khzycd")
    @ApiModelProperty(value = "客户重要程度")
    @Dict(dicCode = "khgl_khzycd")
	private String khzycd;
	/**潜在业务*/
	@Excel(name = "潜在业务", width = 15)
    @ApiModelProperty(value = "潜在业务")
	private String qzyw;
	/**主要收入项目*/
	@Excel(name = "主要收入项目", width = 15)
    @ApiModelProperty(value = "主要收入项目")
	private String zysrxm;
	/**资产负债(万元)*/
	@Excel(name = "资产负债(万元)", width = 15)
    @ApiModelProperty(value = "资产负债(万元)")
	private java.math.BigDecimal zcfz;
	/**年收入(万元)*/
	@Excel(name = "年收入(万元)", width = 15)
    @ApiModelProperty(value = "年收入(万元)")
	private java.math.BigDecimal nsr;
	/**年支出(万元)*/
	@Excel(name = "年支出(万元)", width = 15)
    @ApiModelProperty(value = "年支出(万元)")
	private java.math.BigDecimal nzc;
	/**年利润(万元)*/
	@Excel(name = "年利润(万元)", width = 15)
    @ApiModelProperty(value = "年利润(万元)")
	private java.math.BigDecimal nlr;
	/**其它收入(万元)*/
	@Excel(name = "其它收入(万元)", width = 15)
    @ApiModelProperty(value = "其它收入(万元)")
	private java.math.BigDecimal qtsr;
	/**其他支出(万元)*/
	@Excel(name = "其他支出(万元)", width = 15)
    @ApiModelProperty(value = "其他支出(万元)")
	private java.math.BigDecimal qtzc;
	/**总收入(万元)*/
	@Excel(name = "总收入(万元)", width = 15)
    @ApiModelProperty(value = "总收入(万元)")
	private java.math.BigDecimal zsr;
	/**总支出(万元)*/
	@Excel(name = "总支出(万元)", width = 15)
    @ApiModelProperty(value = "总支出(万元)")
	private java.math.BigDecimal zzc;
	/**净利润(万元)*/
	@Excel(name = "净利润(万元)", width = 15)
    @ApiModelProperty(value = "净利润(万元)")
	private java.math.BigDecimal jlr;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String createBy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}
