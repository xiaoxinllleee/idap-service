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
public class NhbkbpyExportQD {
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
    /**不予授信情形*/
    @Excel(name = "不予授信情形", width = 15, dicCode = "py_bysxqx")
    @ApiModelProperty(value = "不予授信情形")
    @Dict(dicCode = "py_bysxqx_ny")
    private String bysxqx;
    /**建议授信额度*/
    @Excel(name = "建议授信额度", width = 15)
    @ApiModelProperty(value = "建议授信额度")
    private java.math.BigDecimal jysxed;
    /**婚姻状况(1.未婚；2.已婚无子女；3.已婚有子女)*/
    @Excel(name = "婚姻情况", width = 15, dicCode = "bkbpy_hyzk")
    @ApiModelProperty(value = "婚姻情况")
    @Dict(dicCode = "bkbpy_hyzk")
    private String hyzk;
    /**年龄情况(1.46-60岁；2.18-30岁；3.31-45岁)*/
    @Excel(name = "年龄情况", width = 15, dicCode = "bkbpy_nlqk")
    @ApiModelProperty(value = "年龄情况(1.46-60岁；2.18-30岁；3.31-45岁)")
    @Dict(dicCode = "bkbpy_nlqk")
    private String nnqk;
    /**健康状态情况(1.较差；2.一般；3.良好)*/
    @Excel(name = "健康状态情况", width = 15, dicCode = "bkbpy_qkms")
    @ApiModelProperty(value = "健康状态情况(1.较差；2.一般；3.良好)")
    @Dict(dicCode = "bkbpy_qkms")
    private String jkztqk;
    /**家庭劳动力人数情况(1.2人及以下；2.3人；3.4人及以上)*/
    @Excel(name = "家庭劳动力人数情况", width = 15, dicCode = "bkbpy_jtldlrsqk")
    @ApiModelProperty(value = "家庭劳动力人数情况(1.2人及以下；2.3人；3.4人及以上)")
    @Dict(dicCode = "bkbpy_jtldlrsqk")
    private String jtndlrsqk;
    /**房屋价值情况*/
    @Excel(name = "房屋价值情况", width = 15, dicCode = "bkbpy_fwjzqk")
    @ApiModelProperty(value = "房屋价值情况")
    @Dict(dicCode = "bkbpy_fwjzqk")
    private String fwjzqk;
    /**家庭纯收入情况*/
    @Excel(name = "家庭纯收入情况", width = 15, dicCode = "bkbpy_jtcsrqk")
    @ApiModelProperty(value = "家庭纯收入情况")
    @Dict(dicCode = "bkbpy_jtcsrqk")
    private String jtcsrqk;
    /**存款业务往来*/
    @Excel(name = "存款业务往来", width = 15, dicCode = "bkbpy_ckywwlqk")
    @ApiModelProperty(value = "存款业务往来")
    @Dict(dicCode = "bkbpy_ckywwlqk")
    private String ckywwl;
    /**职业情况(1.普通村民；2.多种经营者；3.村干部及有固定工作者)*/
    @Excel(name = "职业情况", width = 15, dicCode = "bkbpy_zyqk")
    @ApiModelProperty(value = "职业情况(1.普通村民；2.多种经营者；3.村干部及有固定工作者)")
    @Dict(dicCode = "bkbpy_zyqk")
    private String zwqk;
    /**家庭成员关系(1.不和睦，矛盾冲突；2.基本团结，无突出矛盾；3。团结和睦)*/
    @Excel(name = "家庭成员关系", width = 15, dicCode = "bkbpy_jtcygx")
    @ApiModelProperty(value = "家庭成员关系(1.不和睦，矛盾冲突；2.基本团结，无突出矛盾；3。团结和睦)")
    @Dict(dicCode = "bkbpy_jtcygx")
    private String jtcygx;
    /**信誉状况(1.较差；2.一般；3.良好)*/
    @Excel(name = "信誉状况", width = 15, dicCode = "bkbpy_qkms")
    @ApiModelProperty(value = "信誉状况(1.较差；2.一般；3.良好)")
    @Dict(dicCode = "bkbpy_qkms")
    private String xyzk;
    /**社会关系状况*/
    @Excel(name = "社会关系状况", width = 15, dicCode = "bkbpy_shgxzk")
    @ApiModelProperty(value = "社会关系状况")
    @Dict(dicCode = "bkbpy_shgxzk")
    private String shgxzk;
    /**经营能力(1.较差；2.一般；3.良好)*/
    @Excel(name = "经营能力", width = 15,dicCode = "bkbpy_qkms")
    @ApiModelProperty(value = "经营能力(1.较差；2.一般；3.良好)")
    @Dict(dicCode = "bkbpy_qkms")
    private String jynl;
    /**评议得分*/
    @Excel(name = "评议得分", width = 15)
    @ApiModelProperty(value = "评议得分")
    private String pydf;
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
}
