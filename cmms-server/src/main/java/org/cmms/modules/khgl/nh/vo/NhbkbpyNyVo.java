package org.cmms.modules.khgl.nh.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2023/9/14 14:09 周四
 */
@Data
public class NhbkbpyNyVo {

    /**户号编码*/
    @Excel(name = "户号", width = 15)
    private String hhbm;
    /**与户主关系*/
    @Excel(name = "与户主关系", width = 15,dicCode = "yhzgx",groupName = "户籍基础信息")
    @Dict(dicCode = "yhzgx")
    private String yhzgx;
    /**姓名*/
    @Excel(name = "姓名", width = 15,groupName = "户籍基础信息")
    private String khmc;
    /**年龄*/
    @Excel(name = "年龄", width = 15,groupName = "户籍基础信息")
    private String nl;
    /**身份证号码*/
    @Excel(name = "身份证号码", width = 15)
    private String zjhm;
    /**手机号*/
    @Excel(name = "手机号", width = 15)
    private String sjhm;
    /**居住地址*/
    @Excel(name = "居住地址", width = 15)
    private String jzdz;
    /**是否了解情况*/
    @Excel(name = "是否了解情况", width = 15,dicCode = "sfljqk")
    @Dict(dicCode = "sfljqk")
    private String sfljqk;
    /**不符合授信原因*/
    @Excel(name = "不符合授信原因", width = 15,dicCode = "py_bysxqx_ny")
    @Dict(dicCode = "py_bysxqx_ny")
    private String bysxqx;
    /**备注*/
    @Excel(name = "备注", width = 15)
    private String bz;
    /**农村房产情况*/
    @Excel(name = "农村房产情况", width = 15,dicCode = "ncfcqk")
    @Dict(dicCode = "ncfcqk")
    private String ncfcqk;
    /**城区有无房产*/
    @Excel(name = "城区有无房产", width = 15,dicCode = "ywbz")
    @Dict(dicCode = "ywbz")
    private String cqywfc;
    /**有无车辆*/
    @Excel(name = "有无车辆", width = 15,dicCode = "ywbz")
    @Dict(dicCode = "ywbz")
    private String ywcl;
    /**是否在本地*/
    @Excel(name = "是否在本地", width = 15,dicCode = "sfzbd")
    @Dict(dicCode = "sfzbd")
    private String sfzbd;
    /**长期居住地*/
    @Excel(name = "长期居住地", width = 15,dicCode = "sfzbd")
    @Dict(dicCode = "sfzbd")
    private String cqjzd;
    /**主营项目*/
    @Excel(name = "主营项目", width = 15)
    private String zyxm;
    /**工作类型*/
    @Excel(name = "工作类型", width = 15,dicCode = "gzlx")
    @Dict(dicCode = "gzlx")
    private String gzlx;
    /**收入*/
    @Excel(name = "收入", width = 15,dicCode = "bkbpy_sr")
    @Dict(dicCode = "bkbpy_sr")
    private java.math.BigDecimal sr;
    /**基础测算模型*/
    @Excel(name = "基础测算模型", width = 15)
    private java.math.BigDecimal jcmxcs;
    /**评议轮数*/
    @Excel(name = "评议轮数", width = 15)
    private Integer pyls;
    /**评议员姓名*/
    @Excel(name = "评议员姓名", width = 15)
    private String pyyxm;
    /**评议员证件号码*/
    @Excel(name = "评议员证件号码", width = 15)
    private String pyyzjhm;
    /**农村房产备注*/
    private String ncfcbz;
    /**城区房产备注*/
    private String cqfcbz;
    /**车辆备注*/
    private String ywclbz;
    /**长期居住地备注*/
    private String cqjzdbz;
    /**评议员建议额度*/
    private java.math.BigDecimal pyyjyed;
    /**评议类型*/
    @Dict(dicCode = "bkbpy_pylx")
    private String pylx;
    /**评议类型*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pysj;
    /**村组*/
    @Dict(dicCode ="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
    private String wgbh;
}
