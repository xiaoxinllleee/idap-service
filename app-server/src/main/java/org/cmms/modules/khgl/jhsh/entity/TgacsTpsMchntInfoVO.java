package org.cmms.modules.khgl.jhsh.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TgacsTpsMchntInfoVO {
    /**商户简称*/
    @Excel(name = "商户简称", width = 15)
    @ApiModelProperty(value = "商户简称")
    private String simpMchntName;
    /**机构名称*/
    @Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
    private String orgNm;
    /**客户经理姓名*/
    @Excel(name = "客户经理姓名", width = 15)
    @ApiModelProperty(value = "客户经理姓名")
    private String mcc;
    /**法人姓名*/
    @Excel(name = "法人姓名", width = 15)
    @ApiModelProperty(value = "法人姓名")
    private String legalPerson;
    /**商户联系电话*/
    @Excel(name = "商户联系电话", width = 15)
    @ApiModelProperty(value = "商户联系电话")
    private String contPhone;
    /**开始日期*/
    @Excel(name = "开始日期", width = 15)
    @ApiModelProperty(value = "开始日期")
    private String sDate;
    /**结束日期*/
    @Excel(name = "结束日期", width = 15)
    @ApiModelProperty(value = "结束日期")
    private String eDate;
    /**营业执照编号*/
    @Excel(name = "营业执照编号", width = 15)
    @ApiModelProperty(value = "营业执照编号")
    private String license;
    /**农信通商户编号*/
    @Excel(name = "农信通商户编号", width = 15)
    @ApiModelProperty(value = "农信通商户编号")
    private String merId;
    /**聚合商户编号*/
    @Excel(name = "聚合商户编号", width = 15)
    @ApiModelProperty(value = "聚合商户编号")
    private String mchntId;
    @Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
    private String mchntName;
    /**商户联系地址*/
    @Excel(name = "商户联系地址", width = 15)
    @ApiModelProperty(value = "商户联系地址")
    private String contaddr;

}
