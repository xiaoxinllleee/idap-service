package org.cmms.modules.bigscreen.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 大屏网格数据统计
 * @Author: jeecg-boot
 * @Date:   2023-10-30
 * @Version: V1.0
 */
@Data
@TableName("DP_WGSJTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DP_WGSJTJ对象", description="大屏网格数据统计")
public class DpWgsjtj {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**所属客户经理*/
	@Excel(name = "所属客户经理", width = 15)
    @ApiModelProperty(value = "所属客户经理")
	private String sskhjl;
	/**总人口*/
	@Excel(name = "总人口", width = 15)
    @ApiModelProperty(value = "总人口")
	private BigDecimal zrk;
	/**金融村官数量*/
	@Excel(name = "金融村官数量", width = 15)
    @ApiModelProperty(value = "金融村官数量")
	private BigDecimal jrcg;
	/**有效合同客户*/
	@Excel(name = "有效合同客户", width = 15)
    @ApiModelProperty(value = "有效合同客户")
	private BigDecimal yxhtkh;
	/**有效合同金额*/
	@Excel(name = "有效合同金额", width = 15)
    @ApiModelProperty(value = "有效合同金额")
	private BigDecimal yxhtje;
	/**贷款用信户数*/
	@Excel(name = "贷款用信户数", width = 15)
    @ApiModelProperty(value = "贷款用信户数")
	private BigDecimal dkyxhs;
	/**贷款用信金额*/
	@Excel(name = "贷款用信金额", width = 15)
    @ApiModelProperty(value = "贷款用信金额")
	private BigDecimal dkyxje;
	/**存款客户*/
	@Excel(name = "存款客户", width = 15)
    @ApiModelProperty(value = "存款客户")
	private BigDecimal ckkh;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private BigDecimal ckye;
	/**福祥e站*/
	@Excel(name = "福祥e站", width = 15)
    @ApiModelProperty(value = "福祥e站")
	private BigDecimal fxez;
	/**社保卡客户*/
	@Excel(name = "社保卡客户", width = 15)
    @ApiModelProperty(value = "社保卡客户")
	private BigDecimal sbkkh;
	/**手机银行*/
	@Excel(name = "手机银行", width = 15)
    @ApiModelProperty(value = "手机银行")
	private BigDecimal sjyh;
	/**信用卡客户*/
	@Excel(name = "信用卡客户", width = 15)
    @ApiModelProperty(value = "信用卡客户")
	private BigDecimal xykkh;
	/**福祥E支付*/
	@Excel(name = "福祥E支付", width = 15)
    @ApiModelProperty(value = "福祥E支付")
	private BigDecimal fxezf;
	/**系统初初筛白名单*/
	@Excel(name = "系统初初筛白名单", width = 15)
    @ApiModelProperty(value = "系统初初筛白名单")
	private BigDecimal xtcsbmd;
	/**支行审定白名单*/
	@Excel(name = "支行审定白名单", width = 15)
    @ApiModelProperty(value = "支行审定白名单")
	private BigDecimal zhsdbmd;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
    @ApiModelProperty(value = "授信金额")
	private BigDecimal sxje;
	/**惠农快贷导入户数*/
	@Excel(name = "惠农快贷导入户数", width = 15)
    @ApiModelProperty(value = "惠农快贷导入户数")
	private BigDecimal hnkddrhs;
	/**惠农快贷导入金额*/
	@Excel(name = "惠农快贷导入金额", width = 15)
    @ApiModelProperty(value = "惠农快贷导入金额")
	private BigDecimal hnkddrje;
	/**新增签约户数*/
	@Excel(name = "新增签约户数", width = 15)
    @ApiModelProperty(value = "新增签约户数")
	private BigDecimal xzqyhs;
	/**新增签约金额*/
	@Excel(name = "新增签约金额", width = 15)
    @ApiModelProperty(value = "新增签约金额")
	private BigDecimal xzqyje;
	/**新增用户户数*/
	@Excel(name = "新增用户户数", width = 15)
    @ApiModelProperty(value = "新增用户户数")
	private BigDecimal xzyxhs;
	/**新增用信金额*/
	@Excel(name = "新增用信金额", width = 15)
    @ApiModelProperty(value = "新增用信金额")
	private BigDecimal xzyxje;
	/**网格父编号*/
	@Excel(name = "网格父编号", width = 15)
    @ApiModelProperty(value = "网格父编号")
	private String pid;
	/**网格性质*/
	@Excel(name = "网格性质", width = 15)
    @ApiModelProperty(value = "网格性质")
	private String wgxz;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private BigDecimal zhs;
	/**乡镇数量*/
	@Excel(name = "乡镇数量", width = 15)
    @ApiModelProperty(value = "乡镇数量")
	private BigDecimal xzsl;
	/**行政村数量*/
	@Excel(name = "行政村数量", width = 15)
    @ApiModelProperty(value = "行政村数量")
	private BigDecimal xzcsl;
	/**社区居委数量*/
	@Excel(name = "社区居委数量", width = 15)
    @ApiModelProperty(value = "社区居委数量")
	private BigDecimal sqjwsl;
}
