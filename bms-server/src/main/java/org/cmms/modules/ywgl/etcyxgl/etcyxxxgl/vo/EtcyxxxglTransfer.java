package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.vo;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EtcyxxxglTransfer {
    /**统计月份*/
    @Excel(name = "数据月份", width = 15)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date tjyf;
    /**营销机构代码*/
    @Excel(name = "营销机构代码", width = 15)
    private String yxjgdm;
    private String khrq_begin;
    private String khrq_end;
    /**办理员工编号*/
    @Excel(name = "办理员工编号", width = 15)
    private String blygbh;
    /**营销人工号*/
    @Excel(name = "营销人工号", width = 15)
    private String yxrgh;
    /**客户姓名*/
    @Excel(name = "客户姓名", width = 15)
    private String khxm;
    /**客户身份证号*/
    @Excel(name = "客户身份证号", width = 15)
    @ExcelVerify(notNull = true)
    private String khsfzh;
    /**车牌号码*/
    @Excel(name = "车牌号码", width = 15)
    private String cphm;
    /**ETC绑定状态*/
    @Excel(name = "ETC绑定状态", width = 15)
    private String etcbdzt;
    /**ETC绑定状态*/
    @Excel(name = "申请说明", width = 15)
    private String sqsm;
    /**ETC绑定状态*/
    @Excel(name = "组织标志", width = 15)
    private String zzbz;
    /**ETC绑定状态*/
    @Excel(name = "员工工号", width = 15)
    private String yggh;
    /**ETC绑定状态*/
    @Excel(name = "移交日期", width = 15)
    private String yjrq;
    /**ETC绑定状态*/
    @Excel(name = "移交类型", width = 15)
    private String yjlx;
    /**选择行*/
    @Excel(name = "选择行", width = 15)
    private JSONArray selectionRows;
}
