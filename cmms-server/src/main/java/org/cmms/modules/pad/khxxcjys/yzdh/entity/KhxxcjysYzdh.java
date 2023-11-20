package org.cmms.modules.pad.khxxcjys.yzdh.entity;

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
 * @Description: 客户信息采集要素_养殖大户
 * @Author: jeecg-boot
 * @Date:   2022-06-30
 * @Version: V1.0
 */
@Data
@TableName("KHXXCJYS_YZDH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXCJYS_YZDH对象", description="客户信息采集要素_养殖大户")
public class KhxxcjysYzdh {
    
	/**养殖场名称*/
	@Excel(name = "养殖场名称", width = 15)
    @ApiModelProperty(value = "养殖场名称")
	private String yzcmc;
	/**养殖场地址*/
	@Excel(name = "养殖场地址", width = 15)
    @ApiModelProperty(value = "养殖场地址")
	private String yzcdz;
	/**对接支行*/
	@Excel(name = "对接支行", width = 15)
    @ApiModelProperty(value = "对接支行")
	private String djzh;
	/**对接人员*/
	@Excel(name = "对接人员", width = 15)
    @ApiModelProperty(value = "对接人员")
	private String djry;
	/**我行存量贷款客户（1是，2否）*/
	@Excel(name = "我行存量贷款客户（1是，2否）", width = 15)
    @ApiModelProperty(value = "我行存量贷款客户（1是，2否）")
	@Dict(dicCode = "sfbz")
	private String whcldkkh;
	/**借款主体名字*/
	@Excel(name = "借款主体名字", width = 15)
    @ApiModelProperty(value = "借款主体名字")
	private String jkztmz;
	/**借款主体证件号*/
	@Excel(name = "借款主体证件号", width = 15)
    @ApiModelProperty(value = "借款主体证件号")
	private String jkztzjh;
	/**授信金额（存量客户）*/
	@Excel(name = "授信金额（存量客户）", width = 15)
    @ApiModelProperty(value = "授信金额（存量客户）")
	private Integer clkhsxje;
	/**用心金额*/
	@Excel(name = "用心金额", width = 15)
    @ApiModelProperty(value = "用心金额")
	private Integer yxje;
	/**表外贷款余额*/
	@Excel(name = "表外贷款余额", width = 15)
    @ApiModelProperty(value = "表外贷款余额")
	private Integer bwdkye;
	/**增量贷款需求*/
	@Excel(name = "增量贷款需求", width = 15)
    @ApiModelProperty(value = "增量贷款需求")
	private String zldkxq;
	/**需求金额*/
	@Excel(name = "需求金额", width = 15)
    @ApiModelProperty(value = "需求金额")
	private Integer xqje;
	/**被走访交流对象姓名*/
	@Excel(name = "被走访交流对象姓名", width = 15)
    @ApiModelProperty(value = "被走访交流对象姓名")
	private String bzfjldxxm;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**养殖规模*/
	@Excel(name = "养殖规模", width = 15)
    @ApiModelProperty(value = "养殖规模")
	private Integer yzgm;
	/**养殖种类（1生猪，2牛，3羊，4鸡，5其他）*/
	@Excel(name = "养殖种类（1生猪，2牛，3羊，4鸡，5其他）", width = 15)
    @ApiModelProperty(value = "养殖种类（1生猪，2牛，3羊，4鸡，5其他）")
	private String yzzl;
	/**预计年收入*/
	@Excel(name = "预计年收入", width = 15)
    @ApiModelProperty(value = "预计年收入")
	private Integer yjnsr;
	/**本次授信对象名称*/
	@Excel(name = "本次授信对象名称", width = 15)
    @ApiModelProperty(value = "本次授信对象名称")
	private String bcsxdxmc;
	/**授信对象证件号码*/
	@Excel(name = "授信对象证件号码", width = 15)
    @ApiModelProperty(value = "授信对象证件号码")
	private String sxdxzjhm;
	/**授信金额（非存量客户）*/
	@Excel(name = "授信金额（非存量客户）", width = 15)
    @ApiModelProperty(value = "授信金额（非存量客户）")
	private Integer fclkhsxje;
	/**授信为0说明（1超年龄，2有不良嗜好，3社会诚信评价差，4无还款来源，5有不良贷款）*/
	@Excel(name = "授信为0说明（1超年龄，2有不良嗜好，3社会诚信评价差，4无还款来源，5有不良贷款）", width = 15)
    @ApiModelProperty(value = "授信为0说明（1超年龄，2有不良嗜好，3社会诚信评价差，4无还款来源，5有不良贷款）")
	private String sxwlsm;
	/**本次营销签约金额*/
	@Excel(name = "本次营销签约金额", width = 15)
    @ApiModelProperty(value = "本次营销签约金额")
	private Integer bcyxqyje;
	/**客户分类（1无效客户，2白名单客户，3继续跟进）*/
	@Excel(name = "客户分类（1无效客户，2白名单客户，3继续跟进）", width = 15)
    @ApiModelProperty(value = "客户分类（1无效客户，2白名单客户，3继续跟进）")
	private String khfl;
	/**客户基本情况补充描述*/
	@Excel(name = "客户基本情况补充描述", width = 15)
    @ApiModelProperty(value = "客户基本情况补充描述")
	private String khjbqkbcms;
	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
}
