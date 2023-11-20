package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.Date;

@Data
@ToString
public class CmsLxhsdjbVO {

    @ApiModelProperty(value = "删除标志")
    private String dataFlag;
    @ApiModelProperty(value = "货币号")
    private String hbh;
    @ApiModelProperty(value = "机构码")
    private String jgm;
    @ApiModelProperty(value = "科目号")
    private String kmh;
    @ApiModelProperty(value = "正常形态贷款帐号")
    private String zh;
    @ApiModelProperty(value = "对应账号")
    private String zh1;
    @ApiModelProperty(value = "户名")
    private String hm;
    @ApiModelProperty(value = "借贷标志")
    private String jdbz;
    @ApiModelProperty(value = "对应科目")
    private String dykm;
    @ApiModelProperty(value = "对应账号")
    private String dyzh;
    @ApiModelProperty(value = "起息日")
    private String qxr;
    @ApiModelProperty(value = "结息日")
    private String jxr;
    @ApiModelProperty(value = "利率")
    private java.math.BigDecimal ll;
    @ApiModelProperty(value = "积数")
    private java.math.BigDecimal js;
    @ApiModelProperty(value = "日数")
    private String rs;
    @ApiModelProperty(value = "收息日")
    private String sxr;
    @ApiModelProperty(value = "入帐标志")
    private String rzbz;
    @ApiModelProperty(value = "备注1")
    private String re1;
    @ApiModelProperty(value = "备注2")
    private String re2;
    @ApiModelProperty(value = "四级分类")
    private String sjfl;
    @ApiModelProperty(value = "五级分类")
    private String wjfl;
    @ApiModelProperty(value = "核心借据号")
    private String jjh;
    @ApiModelProperty(value = "信贷借据号")
    private String voucherNo;
    @ApiModelProperty(value = "利息")
    private java.math.BigDecimal lx;
    @ApiModelProperty(value = "合计利息")
    private java.math.BigDecimal hjlx;
    @ApiModelProperty(value = "贷款本金")
    private java.math.BigDecimal dkbj;
    @ApiModelProperty(value = "入账标志")
    private String shouldPayTerm;
    @ApiModelProperty(value = "数据日期")
    private String dataDate;
    @ApiModelProperty(value = "加载时间")
    private Date loadDate;
    @ApiModelProperty(value = "法人标识")
    private String legalNo;

    @ApiModelProperty(value = "当前记录下标数")
    private int rownum;
    @ApiModelProperty(value = "逾期持续月数")
    private int yqcxys;
    @ApiModelProperty(value = "逾期余额")
    private java.math.BigDecimal yqye;
    @ApiModelProperty(value = "起息日Date")
    private Date qxrDate;

}
