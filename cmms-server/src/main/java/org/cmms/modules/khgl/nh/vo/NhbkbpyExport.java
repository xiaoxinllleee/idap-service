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
public class NhbkbpyExport {
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
    /**评议轮数*/
    @Excel(name = "评议轮数", width = 15)
    @ApiModelProperty(value = "评议轮数")
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
    @Excel(name = "不予授信情形", width = 15, dicCode = "py_bysxqx")
    @ApiModelProperty(value = "不予授信情形")
    @Dict(dicCode = "py_bysxqx_ny")
    private String bysxqx;
    /**基础模型测算*/
    @Excel(name = "基础模型测算", width = 15)
    @ApiModelProperty(value = "基础模型测算")
    private java.math.BigDecimal jcmxcs;
    /**评议员建议额度*/
    @Excel(name = "评议员建议额度", width = 15)
    @ApiModelProperty(value = "评议员建议额度")
    private java.math.BigDecimal pyyjyed;
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
    /**长期居住地*/
    @Excel(name = "长期居住地", width = 15,dicCode = "sfzbd")
    @ApiModelProperty(value = "长期居住地")
    @Dict(dicCode = "sfzbd")
    private String cqjzd;
    /**长期居住地备注*/
    @Excel(name = "长期居住地备注", width = 15)
    @ApiModelProperty(value = "长期居住地备注")
    private String cqjzdbz;
    /**是否在本地*/
    @Excel(name = "是否在本地", width = 15,dicCode = "sfzbd")
    @ApiModelProperty(value = "是否在本地")
    @Dict(dicCode = "sfzbd")
    private String sfzbd;
    /**工作类型*/
    @Excel(name = "工作类型", width = 15,dicCode = "gzlx")
    @ApiModelProperty(value = "工作类型")
    @Dict(dicCode = "gzlx")
    private String gzlx;
    /**收入*/
    @Excel(name = "收入", width = 15,dicCode = "bkbpy_sr")
    @ApiModelProperty(value = "收入")
    @Dict(dicCode = "bkbpy_sr")
    private Integer sr;
    /**主营项目*/
    @Excel(name = "主营项目", width = 15)
    @ApiModelProperty(value = "主营项目")
    private String zyxm;
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
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
//    /**家庭存款日平合计*/
//    @Excel(name = "家庭存款日平合计", width = 15)
//    @ApiModelProperty(value = "家庭存款日平合计")
//    private java.math.BigDecimal jtckrphj;
    /**健康状态情况(1.较差；2.一般；3.良好)*/

}
