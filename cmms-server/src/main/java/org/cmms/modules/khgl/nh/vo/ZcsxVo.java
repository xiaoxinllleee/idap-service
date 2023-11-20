package org.cmms.modules.khgl.nh.vo;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2023/7/18 14:20 周二
 */
@Data
public class ZcsxVo {
    @Excel(name = "序号",width = 15)
    private String xh;
    @Excel(name = "户主姓名",width = 15)
    private String hzxm;
    @Excel(name = "评议对象",width = 15)
    private String pydx;
    @Excel(name = "身份证号码",width = 15)
    private String zjhm;
    @Excel(name = "联系电话",width = 15)
    private String lxdh;
    @Excel(name = "不予授信情形", width = 15, dicCode = "py_bysxqx")
    private String bysxqx;
    @Excel(name = "婚姻状况", width = 15, dicCode = "bkbpy_hyzk")
    private String hyzk;
    @Excel(name = "社会声望及荣誉情况", width = 15, dicCode = "bkbpy_shswjry")
    private String shswjry;
    @Excel(name = "社会关系状况", width = 15, dicCode = "bkbpy_shgxzk")
    private String shgxzk;
    @Excel(name = "生活习惯情况", width = 15, dicCode = "bkbpy_shxgqk")
    private String shxgqk;
    @Excel(name = "在民间是否有高息贷款", width = 15, dicCode = "bkbpy_zmjgxjkqk")
    private String zmjgljk;
    @Excel(name = "建议授信额度(万元)", width = 15)
    private java.math.BigDecimal jysxed;
    @Excel(name = "评议员姓名", width = 15)
    private String pyyxm;
    @Excel(name = "评议员证件号", width = 15)
    private String pyyzjhm;
    @Excel(name = "评议时间", width = 15, format = "yyyy-MM-dd")
    @ExcelVerify(interHandler = true)
    private Date pysj;
}
