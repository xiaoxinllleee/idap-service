package org.cmms.modules.tjfx.zfsjtj.sxyxtj.zh.zhsxyxtj.entity;

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
 * @Description: 支行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2022-08-17
 * @Version: V1.0
 */
@Data
@TableName("tjfx_sxyxtj_zh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_sxyxtj_zh对象", description="支行授信用信统计")
public class Zhsxyxtj {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**统计维度*/
//	@Excel(name = "统计维度", width = 15)
    @ApiModelProperty(value = "统计维度")
	private String tjwd;
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**数据日期*/
//	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15,dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String zzbz;
	/**客户类型*/
//	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String khlx;
	/**授信户数*/
	@Excel(name = "授信户数", width = 15)
    @ApiModelProperty(value = "授信户数")
	private Integer sxhs;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
    @ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**授信户数排名*/
	@Excel(name = "授信户数排名", width = 15)
    @ApiModelProperty(value = "授信户数排名")
	private Integer sxhspm;
	/**授信金额排名*/
	@Excel(name = "授信金额排名", width = 15)
    @ApiModelProperty(value = "授信金额排名")
	private Integer sxjepm;
	/**授信户数比上期*/
	@Excel(name = "授信户数比上期", width = 15)
    @ApiModelProperty(value = "授信户数比上期")
	private Integer sxhsBsq;
	/**授信金额比上期*/
	@Excel(name = "授信金额比上期", width = 15)
    @ApiModelProperty(value = "授信金额比上期")
	private java.math.BigDecimal sxjeBsq;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
    @ApiModelProperty(value = "用信户数")
	private Integer yxhs;
	/**用信金额*/
	@Excel(name = "用信金额", width = 15)
    @ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;
	/**用信户数排名*/
	@Excel(name = "用信户数排名", width = 15)
    @ApiModelProperty(value = "用信户数排名")
	private Integer yxhspm;
	/**用信金额排名*/
	@Excel(name = "用信金额排名", width = 15)
    @ApiModelProperty(value = "用信金额排名")
	private Integer yxjepm;
	/**用信户数比上期*/
	@Excel(name = "用信户数比上期", width = 15)
    @ApiModelProperty(value = "用信户数比上期")
	private Integer yxhsBsq;
	/**用信金额比上期*/
	@Excel(name = "用信金额比上期", width = 15)
    @ApiModelProperty(value = "用信金额比上期")
	private java.math.BigDecimal yxjeBsq;
	/**存量客户授信户数*/
	@Excel(name = "存量客户授信户数", width = 15)
    @ApiModelProperty(value = "存量客户授信户数")
	private Integer clkhSxhs;
	/**存量客户授信金额*/
	@Excel(name = "存量客户授信金额", width = 15)
    @ApiModelProperty(value = "存量客户授信金额")
	private java.math.BigDecimal clkhSxje;
	/**存量客户授信户数排名*/
	@Excel(name = "存量客户授信户数排名", width = 15)
    @ApiModelProperty(value = "存量客户授信户数排名")
	private Integer clkhSxhspm;
	/**存量客户授信金额排名*/
	@Excel(name = "存量客户授信金额排名", width = 15)
    @ApiModelProperty(value = "存量客户授信金额排名")
	private Integer clkhSxjepm;
	/**存量客户授信户数比上期*/
	@Excel(name = "存量客户授信户数比上期", width = 15)
    @ApiModelProperty(value = "存量客户授信户数比上期")
	private Integer clkhSxhsBsq;
	/**存量客户授信金额比上期*/
	@Excel(name = "存量客户授信金额比上期", width = 15)
    @ApiModelProperty(value = "存量客户授信金额比上期")
	private java.math.BigDecimal clkhSxjeBsq;
	/**存量客户用信户数*/
	@Excel(name = "存量客户用信户数", width = 15)
    @ApiModelProperty(value = "存量客户用信户数")
	private Integer clkhYxhs;
	/**存量客户用信金额*/
	@Excel(name = "存量客户用信金额", width = 15)
    @ApiModelProperty(value = "存量客户用信金额")
	private java.math.BigDecimal clkhYxje;
	/**存量客户用信户数排名*/
	@Excel(name = "存量客户用信户数排名", width = 15)
    @ApiModelProperty(value = "存量客户用信户数排名")
	private Integer clkhYxhspm;
	/**存量客户用信金额排名*/
	@Excel(name = "存量客户用信金额排名", width = 15)
    @ApiModelProperty(value = "存量客户用信金额排名")
	private Integer clkhYxjepm;
	/**存量客户用信户数比上期*/
	@Excel(name = "存量客户用信户数比上期", width = 15)
    @ApiModelProperty(value = "存量客户用信户数比上期")
	private Integer clkhYxhsBsq;
	/**存量客户用信金额比上期*/
	@Excel(name = "存量客户用信金额比上期", width = 15)
    @ApiModelProperty(value = "存量客户用信金额比上期")
	private java.math.BigDecimal clkhYxjeBsq;
	/**新客户授信户数*/
	@Excel(name = "新客户授信户数", width = 15)
    @ApiModelProperty(value = "新客户授信户数")
	private Integer xkhSxhs;
	/**新客户授信金额*/
	@Excel(name = "新客户授信金额", width = 15)
    @ApiModelProperty(value = "新客户授信金额")
	private java.math.BigDecimal xkhSxje;
	/**新客户授信户数排名*/
	@Excel(name = "新客户授信户数排名", width = 15)
    @ApiModelProperty(value = "新客户授信户数排名")
	private Integer xkhSxhspm;
	/**新客户授信金额排名*/
	@Excel(name = "新客户授信金额排名", width = 15)
    @ApiModelProperty(value = "新客户授信金额排名")
	private Integer xkhSxjepm;
	/**新客户授信户数比上期*/
	@Excel(name = "新客户授信户数比上期", width = 15)
    @ApiModelProperty(value = "新客户授信户数比上期")
	private Integer xkhSxhsBsq;
	/**新客户授信金额比上期*/
	@Excel(name = "新客户授信金额比上期", width = 15)
    @ApiModelProperty(value = "新客户授信金额比上期")
	private java.math.BigDecimal xkhSxjeBsq;
	/**新客户用信户数*/
	@Excel(name = "新客户用信户数", width = 15)
    @ApiModelProperty(value = "新客户用信户数")
	private Integer xkhYxhs;
	/**新客户用信金额*/
	@Excel(name = "新客户用信金额", width = 15)
    @ApiModelProperty(value = "新客户用信金额")
	private java.math.BigDecimal xkhYxje;
	/**新客户用信户数排名*/
	@Excel(name = "新客户用信户数排名", width = 15)
    @ApiModelProperty(value = "新客户用信户数排名")
	private Integer xkhYxhspm;
	/**新客户用信金额排名*/
	@Excel(name = "新客户用信金额排名", width = 15)
    @ApiModelProperty(value = "新客户用信金额排名")
	private Integer xkhYxjepm;
	/**新客户用信户数比上期*/
	@Excel(name = "新客户用信户数比上期", width = 15)
    @ApiModelProperty(value = "新客户用信户数比上期")
	private Integer xkhYxhsBsq;
	/**新客户用信金额比上期*/
	@Excel(name = "新客户用信金额比上期", width = 15)
    @ApiModelProperty(value = "新客户用信金额比上期")
	private java.math.BigDecimal xkhYxjeBsq;
	/**惠农快贷授信户数*/
	@Excel(name = "惠农快贷授信户数", width = 15)
    @ApiModelProperty(value = "惠农快贷授信户数")
	private Integer hnkdSxhs;
	/**惠农快贷授信金额*/
	@Excel(name = "惠农快贷授信金额", width = 15)
    @ApiModelProperty(value = "惠农快贷授信金额")
	private java.math.BigDecimal hnkdSxje;
	/**惠农快贷授信户数排名*/
	@Excel(name = "惠农快贷授信户数排名", width = 15)
    @ApiModelProperty(value = "惠农快贷授信户数排名")
	private Integer hnkdSxhspm;
	/**惠农快贷授信金额排名*/
	@Excel(name = "惠农快贷授信金额排名", width = 15)
    @ApiModelProperty(value = "惠农快贷授信金额排名")
	private Integer hnkdSxjepm;
	/**惠农快贷授信户数比上期*/
	@Excel(name = "惠农快贷授信户数比上期", width = 15)
    @ApiModelProperty(value = "惠农快贷授信户数比上期")
	private Integer hnkdSxhsBsq;
	/**惠农快贷授信金额比上期*/
	@Excel(name = "惠农快贷授信金额比上期", width = 15)
    @ApiModelProperty(value = "惠农快贷授信金额比上期")
	private java.math.BigDecimal hnkdSxjeBsq;
	/**惠农快贷用信户数*/
	@Excel(name = "惠农快贷用信户数", width = 15)
    @ApiModelProperty(value = "惠农快贷用信户数")
	private Integer hnkdYxhs;
	/**惠农快贷用信金额*/
	@Excel(name = "惠农快贷用信金额", width = 15)
    @ApiModelProperty(value = "惠农快贷用信金额")
	private java.math.BigDecimal hnkdYxje;
	/**惠农快贷用信户数排名*/
	@Excel(name = "惠农快贷用信户数排名", width = 15)
    @ApiModelProperty(value = "惠农快贷用信户数排名")
	private Integer hnkdYxhspm;
	/**惠农快贷用信金额排名*/
	@Excel(name = "惠农快贷用信金额排名", width = 15)
    @ApiModelProperty(value = "惠农快贷用信金额排名")
	private Integer hnkdYxjepm;
	/**惠农快贷用信户数比上期*/
	@Excel(name = "惠农快贷用信户数比上期", width = 15)
    @ApiModelProperty(value = "惠农快贷用信户数比上期")
	private Integer hnkdYxhsBsq;
	/**惠农快贷用信金额比上期*/
	@Excel(name = "惠农快贷用信金额比上期", width = 15)
    @ApiModelProperty(value = "惠农快贷用信金额比上期")
	private java.math.BigDecimal hnkdYxjeBsq;
	/**createBy*/
//	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
//	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
//	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
//	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**客户经理授信额度*/
	@Excel(name = "客户经理授信额度", width = 15)
	@ApiModelProperty(value = "客户经理授信额度")
	private java.math.BigDecimal khjlsxed;
}
