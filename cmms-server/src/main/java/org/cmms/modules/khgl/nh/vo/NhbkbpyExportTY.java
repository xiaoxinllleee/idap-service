package org.cmms.modules.khgl.nh.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 农户背靠背评议
 * @Author: cmms
 * @Version: V1.0 * @Date:   2019-12-02
 */
@Data
@TableName("CAMS_ZCSX_NHBKBPY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_NHBKBPY对象", description="农户背靠背评议")
public class NhbkbpyExportTY {
    /**主键ID*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private String id;
    /**所属网格*/
    @Excel(name = "所属网格", width = 15,dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    @ApiModelProperty(value = "所属网格")
    @Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
    private String qydm;
    /**户号编码*/
    @Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
    private String hhbm;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**客户名称*/
    @Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String khmc;
    /**评议次数*/
    @Excel(name = "评议次数", width = 15)
    @ApiModelProperty(value = "评议次数")
    private Integer pyls;
    /**是否了解情况*/
    @Excel(name = "是否了解情况", width = 15,dicCode = "sfljqk")
    @ApiModelProperty(value = "是否了解情况")
    @Dict(dicCode = "sfljqk")
    private String sfljqk;
    /**评议类型*/
    @Excel(name = "评议类型", width = 15, dicCode = "bkbpy_pylx")
    @ApiModelProperty(value = "评议类型")
    @Dict(dicCode = "bkbpy_pylx")
    private String pylx;
    /**不予授信情形*/
    @Excel(name = "不予授信情形", width = 15, dicCode = "py_bysxqx_ty")
    @ApiModelProperty(value = "不予授信情形")
    @Dict(dicCode = "py_bysxqx_ty")
    private String bysxqx;
    /**客户分类*/
    @Excel(name = "客户分类", width = 15,dicCode = "ty_khfl")
    @ApiModelProperty(value = "客户分类")
    @Dict(dicCode = "ty_khfl")
    private String khfl;
    /**就业分类*/
    @Excel(name = "就业分类", width = 15,dicCode = "ty_jyfl")
    @ApiModelProperty(value = "就业分类")
    @Dict(dicCode = "ty_jyfl")
    private String jyfl;
    /**行业分类*/
    @Excel(name = "行业分类", width = 15,dicCode = "ty_hyfl")
    @ApiModelProperty(value = "行业分类")
    @Dict(dicCode = "ty_hyfl")
    private String hyfl;
    /**就业地点*/
    @Excel(name = "就业地点", width = 15,dicCode = "ty_jydd")
    @ApiModelProperty(value = "就业地点")
    @Dict(dicCode = "ty_jydd")
    private String jydd;
    /**收入*/
    @Excel(name = "收入", width = 15,dicCode = "bkbpy_sr")
    @ApiModelProperty(value = "收入")
    @Dict(dicCode = "bkbpy_sr")
    private Integer sr;
    /**婚姻状况*/
    @Excel(name = "婚姻状况", width = 15,dicCode = "hyzk")
    @ApiModelProperty(value = "婚姻状况")
    @Dict(dicCode = "hyzk")
    private String hyzk;
    /**最新手机号码*/
    @Excel(name = "最新手机号码", width = 15)
    @ApiModelProperty(value = "最新手机号码")
    private String sjhm;

    /**长期居住地*/
    @Excel(name = "长期居住地", width = 15)
    @ApiModelProperty(value = "长期居住地")
    private String cqjzdxq;
    /**长期居住地备注*/
    @Excel(name = "长期居住地备注", width = 15)
    @ApiModelProperty(value = "长期居住地备注")
    private String cqjzdbz;

    /**配偶姓名*/
    @Excel(name = "配偶姓名", width = 15)
    @ApiModelProperty(value = "配偶姓名")
    private String poxm;
    /**配偶证件号码*/
    @Excel(name = "配偶证件号码", width = 15)
    @ApiModelProperty(value = "配偶证件号码")
    private String pozjhm;
    /**农村房产情况*/
    @Excel(name = "农村房产情况", width = 15,dicCode = "ncfcqk")
    @ApiModelProperty(value = "农村房产情况")
    @Dict(dicCode = "ncfcqk")
    private String ncfcqk;
    /**农村房产情况备注*/
    @Excel(name = "农村房产情况备注", width = 15)
    @ApiModelProperty(value = "农村房产情况备注")
    private String ncfcqkBz;
    /**城区有无房产*/
    @Excel(name = "城区有无房产", width = 15,dicCode = "ywbz")
    @ApiModelProperty(value = "城区有无房产")
    @Dict(dicCode = "ywbz")
    private String cqywfc;
    /**城区房产位置*/
    @Excel(name = "城区房产位置", width = 15,dicCode = "ty_cqfcwz")
    @ApiModelProperty(value = "城区房产位置")
    @Dict(dicCode = "ty_cqfcwz")
    private String cqfcwz;
    /**城区有无房产备注*/
    @Excel(name = "城区有无房产备注", width = 15)
    @ApiModelProperty(value = "城区有无房产备注")
    private String cqywfcBz;
    /**有无车辆*/
    @Excel(name = "有无车辆", width = 15,dicCode = "ywbz")
    @Dict(dicCode = "ywbz")
    @ApiModelProperty(value = "有无车辆")
    private String ywcl;
    /**有无车辆备注*/
    @Excel(name = "有无车辆备注", width = 15)
    @ApiModelProperty(value = "有无车辆备注")
    private String ywclBz;
    @Excel(name = "基础模型测算", width = 15)
    @ApiModelProperty(value = "基础模型测算")
    private java.math.BigDecimal jcmxcs;
    /**评议员建议额度*/
    @Excel(name = "评议员建议额度", width = 15)
    @ApiModelProperty(value = "评议员建议额度")
    private java.math.BigDecimal pyyjyed;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String bz;
    /**支行审定备注*/
    @Excel(name = "支行审定备注", width = 15)
    @ApiModelProperty(value = "支行审定备注")
    private String zhsdbz;
    /**评议员姓名*/
    @Excel(name = "评议员姓名", width = 15)
    @ApiModelProperty(value = "评议员姓名")
    private String pyyxm;
    /**评议员证件号码*/
    @Excel(name = "评议员证件号码", width = 15)
    @ApiModelProperty(value = "评议员证件号码")
    private String pyyzjhm;
    /**评议时间*/
    @Excel(name = "评议时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评议时间")
    private Date pysj;


}
