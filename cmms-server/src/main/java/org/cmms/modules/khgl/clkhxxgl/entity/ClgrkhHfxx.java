package org.cmms.modules.khgl.clkhxxgl.entity;

import java.io.Serializable;
import java.util.Date;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户回访信息表
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
@Data
@TableName("YXGL_KHHFXXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YXGL_KHHFXXB对象", description="客户回访信息表")
public class ClgrkhHfxx {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	private String yggh;
	/**客户经理编号*/
	@Excel(name = "客户经理编号", width = 15)
    @ApiModelProperty(value = "客户经理编号")
	private String khjlbh;
	/**营销单元*/
	@Excel(name = "营销单元", width = 15)
    @ApiModelProperty(value = "营销单元")
	private String yxdy;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户性质*/
	@Excel(name = "客户性质", width = 15)
    @ApiModelProperty(value = "客户性质")
	private String khxz;
	/**回访日期*/
	@Excel(name = "回访日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "回访日期")
	private Date hfrq;
	/**回访方式(1.上门回访;2.电话联系;3.约谈;4.其他)*/
	@Excel(name = "回访方式(1.上门回访;2.电话联系;3.约谈;4.其他)", width = 15)
    @ApiModelProperty(value = "回访方式(1.上门回访;2.电话联系;3.约谈;4.其他)")
	@Dict(dicCode = "hffs")
	private String hffs;
	/**回访目的(1.初次拜访;2.完善客户资料;3.营销产品;4.解决客户疑问;5.维系客户关系;6.其他)*/
	@Excel(name = "回访目的(1.初次拜访;2.完善客户资料;3.营销产品;4.解决客户疑问;5.维系客户关系;6.其他)", width = 15)
    @ApiModelProperty(value = "回访目的(1.初次拜访;2.完善客户资料;3.营销产品;4.解决客户疑问;5.维系客户关系;6.其他)")
	@Dict(dicCode = "hfmd")
	private String hfmd;
	/**本次营销业务(1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)*/
	@Excel(name = "本次营销业务(1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)", width = 15)
    @ApiModelProperty(value = "本次营销业务(1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)")
	private String bcyxyw;
	/**营销业务成果(1.现场办理;2.预约办理;3.继续跟进;4.不感兴趣;5.其他)*/
	@Excel(name = "营销业务成果(1.现场办理;2.预约办理;3.继续跟进;4.不感兴趣;5.其他)", width = 15)
    @ApiModelProperty(value = "营销业务成果(1.现场办理;2.预约办理;3.继续跟进;4.不感兴趣;5.其他)")
	private String yxywcg;
	/**业务办理日期*/
	@Excel(name = "业务办理日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "业务办理日期")
	private Date ywblrq;
	/**潜在业务需求(可多选:1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)*/
	@Excel(name = "潜在业务需求(可多选:1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)", width = 15)
    @ApiModelProperty(value = "潜在业务需求(可多选:1.存款;2.贷款;3.便民卡;4.开户;5.手机银行;6.网上银行;7.理财产品;8.ETC;9.其他)")
	private String qzywxq;
	/**礼品名称*/
	@Excel(name = "礼品名称", width = 15)
    @ApiModelProperty(value = "礼品名称")
	private String lpmc;
	/**礼品价值*/
	@Excel(name = "礼品价值", width = 15)
    @ApiModelProperty(value = "礼品价值")
	private java.math.BigDecimal lpjz;
	/**回访详情说明*/
	@Excel(name = "回访详情说明", width = 15)
    @ApiModelProperty(value = "回访详情说明")
	private String hfxqsm;
	/**下次回访日期*/
	@Excel(name = "下次回访日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "下次回访日期")
	private Date xchfrq;
	/**下次回访注意事项*/
	@Excel(name = "下次回访注意事项", width = 15)
    @ApiModelProperty(value = "下次回访注意事项")
	private String xchfzysx;
	/**数据来源(1.PC端录入;2.平板端录入)*/
	@Excel(name = "数据来源(1.PC端录入;2.平板端录入)", width = 15)
    @ApiModelProperty(value = "数据来源(1.PC端录入;2.平板端录入)")
	private String sjly;
	/**提交状态(1.未提交;2.已提交)*/
	@Excel(name = "提交状态(1.未提交;2.已提交)", width = 15)
    @ApiModelProperty(value = "提交状态(1.未提交;2.已提交)")
	private String tjzt;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private String lrbz;
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
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
}
