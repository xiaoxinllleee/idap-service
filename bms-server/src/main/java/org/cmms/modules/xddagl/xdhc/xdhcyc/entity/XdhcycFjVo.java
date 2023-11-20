package org.cmms.modules.xddagl.xdhc.xdhcyc.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@TableName("Xddagl_xdhc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Xddagl_xdhc对象", description="信贷T+1核查先隐藏")
public class XdhcycFjVo {

    /**序号*/
    @Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
    private String xh;
    /**机构代码*/
    @Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    private String jgdm;
    /**客户姓名*/
    @Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
    private String khxm;
    /**电话号码*/
    @Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
    private String sjhm;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**合同号*/
    @Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
    @ExcelVerify(notNull = true)
    private String hth;
    /**最新借款日期*/
    @Excel(name = "最新借款日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最新借款日期")
    private Date zxjkrq;
    /**贷款金额*/
    @Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
    private java.math.BigDecimal dkje;
    /**担保方式*/
    @Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
    private String dbfs;
    /**第一责任人*/
    @Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
    private String zrr;
    /**利率*/
    @Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
    private String ll;
    /**主客户经理*/
    @Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
    private String khjl;
    /**合同批准日*/
    @Excel(name = "合同批准日", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同批准日")
    private Date htzpr;
    /**合同金额*/
    @Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
    private java.math.BigDecimal htje;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String bz;
    /**是否核查*/
    @Excel(name = "是否核查", width = 15)
    @ApiModelProperty(value = "是否核查")
    @Dict(dicCode = "sfhc")
    @ExcelVerify(interHandler = true)
    private String sfhc;
    /**录入人*/
    @Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
    private String lrr;
    /**录入时间*/
    @Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
    private Date lrsj;

    @ApiModelProperty(value = "资料类型")
    @Dict(dicCode = "xdhc_fjlx")
    private String zllx;

    /**附件数据*/
    @ApiModelProperty(value = "附件数据")
    private JSONArray imgdate;

}
