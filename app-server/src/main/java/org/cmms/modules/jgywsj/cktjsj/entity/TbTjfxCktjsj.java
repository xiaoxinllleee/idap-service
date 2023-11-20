package org.cmms.modules.jgywsj.cktjsj.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 存款统计数据
 * @Author: jeecg-boot
 * @Date:   2021-05-28
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_cktjsj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_cktjsj对象", description="存款统计数据")
public class TbTjfxCktjsj {
    
	/**"YYYYMMDD
按天存储数据"*/
	@Excel(name = "YYYYMMDD按天存储数据", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "YYYYMMDD按天存储数据")
	private Date tjrq;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String jgmc;
	/**在档客户数*/
	@Excel(name = "在档客户数", width = 15)
    @ApiModelProperty(value = "在档客户数")
	private Long zdkhs;
	/**有效客户数*/
	@Excel(name = "有效客户数", width = 15)
    @ApiModelProperty(value = "有效客户数")
	private Long yxkhs;
	/**优质客户数*/
	@Excel(name = "优质客户数", width = 15)
    @ApiModelProperty(value = "优质客户数")
	private Long yzkhs;
	/**三万以内余额*/
	@Excel(name = "三万以内余额", width = 15)
    @ApiModelProperty(value = "三万以内余额")
	private java.math.BigDecimal swynye;
	/**三万以内余额户数*/
	@Excel(name = "三万以内余额户数", width = 15)
    @ApiModelProperty(value = "三万以内余额户数")
	private java.math.BigDecimal swynyehs;
	/**三万以内年日平*/
	@Excel(name = "三万以内年日平", width = 15)
    @ApiModelProperty(value = "三万以内年日平")
	private java.math.BigDecimal swynnrp;
	/**三万以内年日平户数*/
	@Excel(name = "三万以内年日平户数", width = 15)
    @ApiModelProperty(value = "三万以内年日平户数")
	private java.math.BigDecimal swynnrphs;
	/**三万（含）到五万余额*/
	@Excel(name = "三万（含）到五万余额", width = 15)
    @ApiModelProperty(value = "三万（含）到五万余额")
	private java.math.BigDecimal swzwwye;
	/**三万（含）到五万余额户数*/
	@Excel(name = "三万（含）到五万余额户数", width = 15)
    @ApiModelProperty(value = "三万（含）到五万余额户数")
	private java.math.BigDecimal swzwwyehs;
	/**三万（含）到五万年日平*/
	@Excel(name = "三万（含）到五万年日平", width = 15)
    @ApiModelProperty(value = "三万（含）到五万年日平")
	private java.math.BigDecimal swzwwnrp;
	/**三万（含）到五万年日平户数*/
	@Excel(name = "三万（含）到五万年日平户数", width = 15)
    @ApiModelProperty(value = "三万（含）到五万年日平户数")
	private java.math.BigDecimal swzwwnrphs;
	/**五万（含）到十万余额*/
	@Excel(name = "五万（含）到十万余额", width = 15)
    @ApiModelProperty(value = "五万（含）到十万余额")
	private java.math.BigDecimal wwzswye;
	/**五万（含）到十万余额户数*/
	@Excel(name = "五万（含）到十万余额户数", width = 15)
    @ApiModelProperty(value = "五万（含）到十万余额户数")
	private java.math.BigDecimal wwzswyehs;
	/**五万（含）到十万年日平*/
	@Excel(name = "五万（含）到十万年日平", width = 15)
    @ApiModelProperty(value = "五万（含）到十万年日平")
	private java.math.BigDecimal wwzswnrp;
	/**五万（含）到十万年日平户数*/
	@Excel(name = "五万（含）到十万年日平户数", width = 15)
    @ApiModelProperty(value = "五万（含）到十万年日平户数")
	private java.math.BigDecimal wwzswnrphs;
	/**十万（含）到二十万余额*/
	@Excel(name = "十万（含）到二十万余额", width = 15)
    @ApiModelProperty(value = "十万（含）到二十万余额")
	private java.math.BigDecimal swzesye;
	/**十万（含）到二十万余额户数*/
	@Excel(name = "十万（含）到二十万余额户数", width = 15)
    @ApiModelProperty(value = "十万（含）到二十万余额户数")
	private java.math.BigDecimal swzesyehs;
	/**十万（含）到二十万年日平*/
	@Excel(name = "十万（含）到二十万年日平", width = 15)
    @ApiModelProperty(value = "十万（含）到二十万年日平")
	private java.math.BigDecimal swzesnrp;
	/**十万（含）到二十万年日平户数*/
	@Excel(name = "十万（含）到二十万年日平户数", width = 15)
    @ApiModelProperty(value = "十万（含）到二十万年日平户数")
	private java.math.BigDecimal swzesnrphs;
	/**二十万（含）到五十万余额*/
	@Excel(name = "二十万（含）到五十万余额", width = 15)
    @ApiModelProperty(value = "二十万（含）到五十万余额")
	private java.math.BigDecimal eszwsye;
	/**二十万（含）到五十万余额户数*/
	@Excel(name = "二十万（含）到五十万余额户数", width = 15)
    @ApiModelProperty(value = "二十万（含）到五十万余额户数")
	private java.math.BigDecimal eszwsyehs;
	/**二十万（含）到五十万年日平*/
	@Excel(name = "二十万（含）到五十万年日平", width = 15)
    @ApiModelProperty(value = "二十万（含）到五十万年日平")
	private java.math.BigDecimal eszwsnrp;
	/**二十万（含）到五十万年日平户数*/
	@Excel(name = "二十万（含）到五十万年日平户数", width = 15)
    @ApiModelProperty(value = "二十万（含）到五十万年日平户数")
	private java.math.BigDecimal eszwsnrphs;
	/**五十万（含）到一百万余额*/
	@Excel(name = "五十万（含）到一百万余额", width = 15)
    @ApiModelProperty(value = "五十万（含）到一百万余额")
	private java.math.BigDecimal wszybye;
	/**五十万（含）到一百万余额户数*/
	@Excel(name = "五十万（含）到一百万余额户数", width = 15)
    @ApiModelProperty(value = "五十万（含）到一百万余额户数")
	private java.math.BigDecimal wszybyehs;
	/**五十万（含）到一百万年日平*/
	@Excel(name = "五十万（含）到一百万年日平", width = 15)
    @ApiModelProperty(value = "五十万（含）到一百万年日平")
	private java.math.BigDecimal wszybnrp;
	/**五十万（含）到一百万年日平户数*/
	@Excel(name = "五十万（含）到一百万年日平户数", width = 15)
    @ApiModelProperty(value = "五十万（含）到一百万年日平户数")
	private java.math.BigDecimal wszybnrphs;
	/**一百万（含）到两百万余额*/
	@Excel(name = "一百万（含）到两百万余额", width = 15)
    @ApiModelProperty(value = "一百万（含）到两百万余额")
	private java.math.BigDecimal ybzlbye;
	/**一百万（含）到两百万余额户数*/
	@Excel(name = "一百万（含）到两百万余额户数", width = 15)
    @ApiModelProperty(value = "一百万（含）到两百万余额户数")
	private java.math.BigDecimal ybzlbyehs;
	/**一百万（含）到两百万年日平*/
	@Excel(name = "一百万（含）到两百万年日平", width = 15)
    @ApiModelProperty(value = "一百万（含）到两百万年日平")
	private java.math.BigDecimal ybzlbnrp;
	/**一百万（含）到两百万年日平户数*/
	@Excel(name = "一百万（含）到两百万年日平户数", width = 15)
    @ApiModelProperty(value = "一百万（含）到两百万年日平户数")
	private java.math.BigDecimal ybzlbnrphs;
	/**两百万（含）到一千万余额*/
	@Excel(name = "两百万（含）到一千万余额", width = 15)
    @ApiModelProperty(value = "两百万（含）到一千万余额")
	private java.math.BigDecimal lbzyqye;
	/**两百万（含）到一千万余额户数*/
	@Excel(name = "两百万（含）到一千万余额户数", width = 15)
    @ApiModelProperty(value = "两百万（含）到一千万余额户数")
	private java.math.BigDecimal lbzyqhs;
	/**两百万（含）到一千万年日平*/
	@Excel(name = "两百万（含）到一千万年日平", width = 15)
    @ApiModelProperty(value = "两百万（含）到一千万年日平")
	private java.math.BigDecimal lbzyqnrp;
	/**两百万（含）到一千万年日平户数*/
	@Excel(name = "两百万（含）到一千万年日平户数", width = 15)
    @ApiModelProperty(value = "两百万（含）到一千万年日平户数")
	private java.math.BigDecimal lbzyqnrphs;
	/**一千万以上余额*/
	@Excel(name = "一千万以上余额", width = 15)
    @ApiModelProperty(value = "一千万以上余额")
	private java.math.BigDecimal yqysye;
	/**一千万以上余额户数*/
	@Excel(name = "一千万以上余额户数", width = 15)
    @ApiModelProperty(value = "一千万以上余额户数")
	private java.math.BigDecimal yqyshs;
	/**一千万以上年日平*/
	@Excel(name = "一千万以上年日平", width = 15)
    @ApiModelProperty(value = "一千万以上年日平")
	private java.math.BigDecimal yqysnrp;
	/**一千万以上年日平户数*/
	@Excel(name = "一千万以上年日平户数", width = 15)
    @ApiModelProperty(value = "一千万以上年日平户数")
	private java.math.BigDecimal yqysnrphs;
	/**活期存款余额*/
	@Excel(name = "活期存款余额", width = 15)
    @ApiModelProperty(value = "活期存款余额")
	private java.math.BigDecimal hqckye;
	/**三月至六月余额*/
	@Excel(name = "三月至六月余额", width = 15)
    @ApiModelProperty(value = "三月至六月余额")
	private java.math.BigDecimal syzlyye;
	/**一年定期余额*/
	@Excel(name = "一年定期余额", width = 15)
    @ApiModelProperty(value = "一年定期余额")
	private java.math.BigDecimal yndqye;
	/**三年定期余额*/
	@Excel(name = "三年定期余额", width = 15)
    @ApiModelProperty(value = "三年定期余额")
	private java.math.BigDecimal sndqye;
	/**五年定期余额*/
	@Excel(name = "五年定期余额", width = 15)
    @ApiModelProperty(value = "五年定期余额")
	private java.math.BigDecimal wndqye;
	/**其他存款余额*/
	@Excel(name = "其他存款余额", width = 15)
    @ApiModelProperty(value = "其他存款余额")
	private java.math.BigDecimal qtckye;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识（0：导入 1：录入 2：修改）", width = 15)
    @ApiModelProperty(value = "录入标识（0：导入 1：录入 2：修改）")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}
