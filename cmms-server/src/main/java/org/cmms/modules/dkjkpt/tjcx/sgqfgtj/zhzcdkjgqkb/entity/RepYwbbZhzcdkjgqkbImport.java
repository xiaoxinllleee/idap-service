package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.entity;

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
 * @Description: 支行正常贷款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
@Data
@TableName("REP_YWBB_ZHZCDKJGQKB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_YWBB_ZHZCDKJGQKB对象", description="支行正常贷款结构情况表")
public class RepYwbbZhzcdkjgqkbImport {
    
	/**组合户数*/
	@Excel(name = "组合户数", width = 15)
    @ApiModelProperty(value = "组合户数")
	private String zhhs;
	/**组合余额*/
	@Excel(name = "组合余额", width = 15)
    @ApiModelProperty(value = "组合余额")
	private java.math.BigDecimal zhye;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private String tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**表内贷款总户数*/
	@Excel(name = "表内贷款总户数", width = 15)
    @ApiModelProperty(value = "表内贷款总户数")
	private String bndkzhs;
	/**表内贷款总额*/
	@Excel(name = "表内贷款总额", width = 15)
    @ApiModelProperty(value = "表内贷款总额")
	private java.math.BigDecimal bndkzje;
	/**个人客户贷款总户数*/
	@Excel(name = "个人客户贷款总户数", width = 15)
    @ApiModelProperty(value = "个人客户贷款总户数")
	private String grkhdkzhs;
	/**个人客户贷款总余额*/
	@Excel(name = "个人客户贷款总余额", width = 15)
    @ApiModelProperty(value = "个人客户贷款总余额")
	private java.math.BigDecimal grkhdkzye;
	/**个人客户贷款其中农户贷款户数*/
	@Excel(name = "个人客户贷款其中农户贷款户数", width = 15)
    @ApiModelProperty(value = "个人客户贷款其中农户贷款户数")
	private String grkhdkqznhdkhs;
	/**个人客户贷款其中农户贷款余额*/
	@Excel(name = "个人客户贷款其中农户贷款余额", width = 15)
    @ApiModelProperty(value = "个人客户贷款其中农户贷款余额")
	private java.math.BigDecimal grkhdkqznhdkye;
	/**法人客户贷款总户数*/
	@Excel(name = "法人客户贷款总户数", width = 15)
    @ApiModelProperty(value = "法人客户贷款总户数")
	private String frkhdkzhs;
	/**法人客户贷款总余额*/
	@Excel(name = "法人客户贷款总余额", width = 15)
    @ApiModelProperty(value = "法人客户贷款总余额")
	private java.math.BigDecimal frkhdkzye;
	/**法人客户贷款其中公司类贷款户数*/
	@Excel(name = "法人客户贷款其中公司类贷款户数", width = 15)
    @ApiModelProperty(value = "法人客户贷款其中公司类贷款户数")
	private String frkhdkqzgsldkhs;
	/**法人客户贷款其中公司类贷款余额*/
	@Excel(name = "法人客户贷款其中公司类贷款余额", width = 15)
    @ApiModelProperty(value = "法人客户贷款其中公司类贷款余额")
	private java.math.BigDecimal frkhdkqzgsldkye;
	/**农业类贷款户数*/
	@Excel(name = "农业类贷款户数", width = 15)
    @ApiModelProperty(value = "农业类贷款户数")
	private String nyldkhs;
	/**农业类贷款余额*/
	@Excel(name = "农业类贷款余额", width = 15)
    @ApiModelProperty(value = "农业类贷款余额")
	private java.math.BigDecimal nyldkye;
	/**制造业贷款户数*/
	@Excel(name = "制造业贷款户数", width = 15)
    @ApiModelProperty(value = "制造业贷款户数")
	private String zzydkhs;
	/**制造业贷款余额*/
	@Excel(name = "制造业贷款余额", width = 15)
    @ApiModelProperty(value = "制造业贷款余额")
	private java.math.BigDecimal zzydkye;
	/**服务业贷款户数*/
	@Excel(name = "服务业贷款户数", width = 15)
    @ApiModelProperty(value = "服务业贷款户数")
	private String fwydkhs;
	/**服务业贷款余额*/
	@Excel(name = "服务业贷款余额", width = 15)
    @ApiModelProperty(value = "服务业贷款余额")
	private java.math.BigDecimal fwydkye;
	/**房地产开发贷户数*/
	@Excel(name = "房地产开发贷户数", width = 15)
    @ApiModelProperty(value = "房地产开发贷户数")
	private String fdckfdhs;
	/**房地产开发贷余额*/
	@Excel(name = "房地产开发贷余额", width = 15)
    @ApiModelProperty(value = "房地产开发贷余额")
	private java.math.BigDecimal fdckfdye;
	/**政府公共服务类户数*/
	@Excel(name = "政府公共服务类户数", width = 15)
    @ApiModelProperty(value = "政府公共服务类户数")
	private String zfggfwlhs;
	/**政府公共服务类余额*/
	@Excel(name = "政府公共服务类余额", width = 15)
    @ApiModelProperty(value = "政府公共服务类余额")
	private java.math.BigDecimal zfggfwlye;
	/**基础设施建设贷款户数*/
	@Excel(name = "基础设施建设贷款户数", width = 15)
    @ApiModelProperty(value = "基础设施建设贷款户数")
	private String jcssjsdkhs;
	/**基础设施建设贷款余额*/
	@Excel(name = "基础设施建设贷款余额", width = 15)
    @ApiModelProperty(value = "基础设施建设贷款余额")
	private java.math.BigDecimal jcssjsdkye;
	/**县市及以上政府重点项目贷款户数*/
	@Excel(name = "县市及以上政府重点项目贷款户数", width = 15)
    @ApiModelProperty(value = "县市及以上政府重点项目贷款户数")
	private String xsjyszfzdxmdkhs;
	/**县市及以上政府重点项目贷款余额*/
	@Excel(name = "县市及以上政府重点项目贷款余额", width = 15)
    @ApiModelProperty(value = "县市及以上政府重点项目贷款余额")
	private java.math.BigDecimal xsjyszfzdxmdkye;
	/**其他户数*/
	@Excel(name = "其他户数", width = 15)
    @ApiModelProperty(value = "其他户数")
	private String qths;
	/**其他余额*/
	@Excel(name = "其他余额", width = 15)
    @ApiModelProperty(value = "其他余额")
	private java.math.BigDecimal qtye;
	/**单户50万以下户数*/
	@Excel(name = "单户50万以下户数", width = 15)
    @ApiModelProperty(value = "单户50万以下户数")
	private String dhwswyxhs;
	/**单户50万以下余额*/
	@Excel(name = "单户50万以下余额", width = 15)
    @ApiModelProperty(value = "单户50万以下余额")
	private java.math.BigDecimal dhwswyxye;
	/**单户50万元至500万户数*/
	@Excel(name = "单户50万元至500万户数", width = 15)
    @ApiModelProperty(value = "单户50万元至500万户数")
	private String dhwswzwbwhs;
	/**单户50万元至500万余额*/
	@Excel(name = "单户50万元至500万余额", width = 15)
    @ApiModelProperty(value = "单户50万元至500万余额")
	private java.math.BigDecimal dhwswzwbwye;
	/**单户500万元至1000万户数*/
	@Excel(name = "单户500万元至1000万户数", width = 15)
    @ApiModelProperty(value = "单户500万元至1000万户数")
	private String dhwbwzyqwhs;
	/**单户500万元至1000万余额*/
	@Excel(name = "单户500万元至1000万余额", width = 15)
    @ApiModelProperty(value = "单户500万元至1000万余额")
	private java.math.BigDecimal dhwbwzyqwye;
	/**单户1000万元以上户数*/
	@Excel(name = "单户1000万元以上户数", width = 15)
    @ApiModelProperty(value = "单户1000万元以上户数")
	private String dhyqwyxhs;
	/**单户1000万元以上余额*/
	@Excel(name = "单户1000万元以上余额", width = 15)
    @ApiModelProperty(value = "单户1000万元以上余额")
	private java.math.BigDecimal dhyqwyxye;
	/**信用户数*/
	@Excel(name = "信用户数", width = 15)
    @ApiModelProperty(value = "信用户数")
	private String xyhs;
	/**信用余额*/
	@Excel(name = "信用余额", width = 15)
    @ApiModelProperty(value = "信用余额")
	private java.math.BigDecimal xyye;
	/**保证户数*/
	@Excel(name = "保证户数", width = 15)
    @ApiModelProperty(value = "保证户数")
	private String bzhs;
	/**保证余额*/
	@Excel(name = "保证余额", width = 15)
    @ApiModelProperty(value = "保证余额")
	private java.math.BigDecimal bzye;
	/**抵押户数*/
	@Excel(name = "抵押户数", width = 15)
    @ApiModelProperty(value = "抵押户数")
	private String dyhs;
	/**抵押余额*/
	@Excel(name = "抵押余额", width = 15)
    @ApiModelProperty(value = "抵押余额")
	private java.math.BigDecimal dyye;
	/**质押户数*/
	@Excel(name = "质押户数", width = 15)
    @ApiModelProperty(value = "质押户数")
	private String zyhs;
	/**质押余额*/
	@Excel(name = "质押余额", width = 15)
    @ApiModelProperty(value = "质押余额")
	private java.math.BigDecimal zyye;
}
