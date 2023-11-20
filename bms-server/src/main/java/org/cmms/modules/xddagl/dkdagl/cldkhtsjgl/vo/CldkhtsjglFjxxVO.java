package org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.CldkhtsjglFjxx;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 存量贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2022-01-06
 * @Version: V1.0
 */
@Data
@TableName("XDDAGL_DKHTSJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XDDAGL_DKHTSJXX对象", description="存量贷款合同数据管理")
public class CldkhtsjglFjxxVO {
    /**提取日期*/
    @Excel(name = "提取日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "提取日期")
    private Date tqrq;
    /**档案编号*/
    @Excel(name = "档案编号", width = 15)
    @ApiModelProperty(value = "档案编号")
    private String dabh;
    /**机构代码*/
    @Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    private String jgdm;
    /**客户名称*/
    @Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String khmc;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**客户类型*/
    @Excel(name = "客户类型", width = 15,dicCode = "xdda_khlx")
    @ApiModelProperty(value = "客户类型")
    @Dict(dicCode = "xdda_khlx")
    private String khlx;
    /**联系电话*/
    @Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
    private String lxdh;
    /**联系地址*/
    @Excel(name = "联系地址", width = 15)
    @ApiModelProperty(value = "联系地址")
    private String lxdz;
    /**签约日期*/
    @Excel(name = "签约日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "签约日期")
    private Date qyrq;

    /**业务编号*/
    @Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
    private String ywbh;
    /**合同开始日期*/
    @Excel(name = "合同开始日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同开始日期")
    private Date fkrq;
    /**合同到期日期*/
    @Excel(name = "合同到期日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同到期日期")
    private Date dqrq;
    /**签约期限*/
    @Excel(name = "签约期限", width = 15)
    @ApiModelProperty(value = "签约期限")
    private String qyqx;
    /**合同金额*/
    @Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
    private java.math.BigDecimal qyje;
    /**贷款余额*/
    @Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
    private java.math.BigDecimal dkye;
    /**最早贷款日期*/
    @Excel(name = "最早贷款日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早贷款日期")
    private Date zzdkrq;
    /**最早到期日期*/
    @Excel(name = "最早到期日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早到期日期")
    private Date zzdqrq;
    /**贷款品种*/
    @Excel(name = "贷款品种", width = 15,dicCode = "dkzl")
    @ApiModelProperty(value = "贷款品种")
    @Dict(dicCode = "dkzl")
    private String dkpz;
    /**贷款品种(补充)*/
    @Excel(name = "贷款品种(补充)", width = 15,dicCode = "dkzlbc")
    @ApiModelProperty(value = "贷款品种(补充)")
    @Dict(dicCode = "dkzlbc")
    private String dkpzbc;
    /**担保方式*/
    @Excel(name = "担保方式", width = 15,dicCode = "gljydbfs")
    @ApiModelProperty(value = "担保方式")
    @Dict(dicCode = "gljydbfs")
    private String dbfs;
    /**贷款责任人*/
    @Excel(name = "贷款责任人", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "贷款责任人")
    @Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    private String dkzrr;
    /**合同号*/
    @Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
    @ExcelVerify(notNull = true,interHandler = true)
    private String hth;
    /**是否用信*/
    @Excel(name = "是否用信", width = 15,dicCode = "yxzt")
    @ApiModelProperty(value = "是否用信")
    @Dict(dicCode = "yxzt")
    private String sfsx;
    /**是否上传档案*/
    @Excel(name = "是否上传档案", width = 15,dicCode = "sfbs")
    @ApiModelProperty(value = "是否上传档案")
    @Dict(dicCode = "sfbs")
    private String sfscda;
    /**数据日期*/
    @Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
    private Date sjrq;
    /**贷款类型*/
    @Excel(name = "贷款类型", width = 15,dicCode = "xddagl_dklx")
    @ApiModelProperty(value = "贷款类型")
    @Dict(dicCode = "xddagl_dklx")
    private String lx;
    /**录入人*/
    @Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
    private String lrr;
    /**录入标志*/
    @Excel(name = "录入标志", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
    @Dict(dicCode = "lrbz")
    private Integer lrbz;
    /**录入时间*/
    @Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
    private Date lrsj;



    /**贷款金额*/
    @ApiModelProperty(value = "贷款金额")
    private java.math.BigDecimal dkje;
    /**贷款形态*/
    @ApiModelProperty(value = "贷款形态")
    private String dkxt;
    /**businessStatus*/
    @ApiModelProperty(value = "businessStatus")
    private String businessStatus;
    /**是否启用授信*/
    @ApiModelProperty(value = "是否启用授信")
    private String sfqysx;
    /**审核状态*/
    @ApiModelProperty(value = "审核状态")
    private Integer shzt;
    /**月结审核标识*/
    @ApiModelProperty(value = "月结审核标识")
    private Integer htzt;

    @ExcelCollection(name="附件信息")
    private List<CldkhtsjglFjxx> cldkhtsjglFjxxList;
}
