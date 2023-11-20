package org.cmms.modules.tjfx.zfsjtj.zfhztj.grhztj.entity;

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
 * @Description: 个人汇总统计
 * @Author: jeecg-boot
 * @Date:   2023-04-19
 * @Version: V1.0
 */
@Data
@TableName("v_tjfx_zfsjtj_khjl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_tjfx_zfsjtj_khjl对象", description="个人汇总统计")
public class Grzfhztj {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private java.util.Date tjrq;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "组织标志")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private java.lang.String zzbz;
	/**岗位标志*/
	@Excel(name = "岗位标志", width = 15,dicCode = "gwbz", dictTable = "HR_BAS_POST", dicText = "gwmc")
    @ApiModelProperty(value = "gwbz")
	@Dict(dicCode = "gwbz", dictTable = "HR_BAS_POST", dicText = "gwmc")
	private java.lang.String gwbz;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "yggh")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private java.lang.String yggh;
	/**农户本日走访数*/
	@Excel(name = "农户本日走访数", width = 15)
	@ApiModelProperty(value = "农户本日走访数")
	private Integer nhbrzfs;
	/**nhbzzfs*/
	@Excel(name = "农户本周走访数", width = 15)
	@ApiModelProperty(value = "农户本周走访数")
	private Integer nhbzzfs;
	/**nhbyzfs*/
	@Excel(name = "农户本月走访数", width = 15)
	@ApiModelProperty(value = "农户本月走访数")
	private Integer nhbyzfs;
	/**nhbjzfs*/
	@Excel(name = "农户本季走访数", width = 15)
	@ApiModelProperty(value = "农户本季走访数")
	private Integer nhbjzfs;
	/**nhbnzfs*/
	@Excel(name = "本年走访数", width = 15)
	@ApiModelProperty(value = "本年走访数")
	private Integer nhbnzfs;
	/**nhljzfs*/
	@Excel(name = "农户累计走访数", width = 15)
	@ApiModelProperty(value = "农户累计走访数")
	private Integer nhljzfs;
	/**nhbryxzfs*/
	@Excel(name = "农户本日有效走访数", width = 15)
	@ApiModelProperty(value = "农户本日有效走访数")
	private Integer nhbryxzfs;
	/**nhbzyxzfs*/
	@Excel(name = "农户本周有效走访数", width = 15)
	@ApiModelProperty(value = "农户本周有效走访数")
	private Integer nhbzyxzfs;
	/**nhbyyxzfs*/
	@Excel(name = "农户本月有效走访数", width = 15)
	@ApiModelProperty(value = "农户本月有效走访数")
	private Integer nhbyyxzfs;
	/**nhbjyxzfs*/
	@Excel(name = "农户本季有效走访数", width = 15)
	@ApiModelProperty(value = "农户本季有效走访数")
	private Integer nhbjyxzfs;
	/**nhbnyxzfs*/
	@Excel(name = "农户本年有效走访数", width = 15)
	@ApiModelProperty(value = "农户本年有效走访数")
	private Integer nhbnyxzfs;
	/**nhljyxzfs*/
	@Excel(name = "农户累计有效走访数", width = 15)
	@ApiModelProperty(value = "农户累计有效走访数")
	private Integer nhljyxzfs;
	/**shbrzfs*/
	@Excel(name = "商户本日走访数", width = 15)
	@ApiModelProperty(value = "商户本日走访数")
	private Integer shbrzfs;
	/**shbzzfs*/
	@Excel(name = "商户本周走访数", width = 15)
	@ApiModelProperty(value = "商户本周走访数")
	private Integer shbzzfs;
	/**shbyzfs*/
	@Excel(name = "商户本月走访数", width = 15)
	@ApiModelProperty(value = "商户本月走访数")
	private Integer shbyzfs;
	/**shbjzfs*/
	@Excel(name = "商户本季走访数", width = 15)
	@ApiModelProperty(value = "商户本季走访数")
	private Integer shbjzfs;
	/**shbnzfs*/
	@Excel(name = "商户本年走访数", width = 15)
	@ApiModelProperty(value = "商户本年走访数")
	private Integer shbnzfs;
	/**shljzfs*/
	@Excel(name = "商户累计走访数", width = 15)
	@ApiModelProperty(value = "商户累计走访数")
	private Integer shljzfs;
	/**shbryxzfs*/
	@Excel(name = "商户本日有效走访数", width = 15)
	@ApiModelProperty(value = "商户本日有效走访数")
	private Integer shbryxzfs;
	/**shbzyxzfs*/
	@Excel(name = "商户本周有效走访数", width = 15)
	@ApiModelProperty(value = "商户本周有效走访数")
	private Integer shbzyxzfs;
	/**shbyyxzfs*/
	@Excel(name = "商户本月有效走访数", width = 15)
	@ApiModelProperty(value = "商户本月有效走访数")
	private Integer shbyyxzfs;
	/**shbjyxzfs*/
	@Excel(name = "商户本季有效走访数", width = 15)
	@ApiModelProperty(value = "商户本季有效走访数")
	private Integer shbjyxzfs;
	/**shbnyxzfs*/
	@Excel(name = "商户本年有效走访数", width = 15)
	@ApiModelProperty(value = "商户本年有效走访数")
	private Integer shbnyxzfs;
	/**shljyxzfs*/
	@Excel(name = "商户累计有效走访数", width = 15)
	@ApiModelProperty(value = "商户累计有效走访数")
	private Integer shljyxzfs;

	/**农户本日二次走访数*/
	@Excel(name = "农户本日二次走访数", width = 15)
	@ApiModelProperty(value = "农户本日二次走访数")
	private Integer nhbreczfs;
	/**农户本周二次走访数*/
	@Excel(name = "农户本周二次走访数", width = 15)
	@ApiModelProperty(value = "农户本周二次走访数")
	private Integer nhbzeczfs;
	/**农户本月二次走访数*/
	@Excel(name = "农户本月二次走访数", width = 15)
	@ApiModelProperty(value = "农户本月二次走访数")
	private Integer nhbyeczfs;
	/**nhbjzfs*/
	@Excel(name = "农户本季二次走访数", width = 15)
	@ApiModelProperty(value = "农户本季二次走访数")
	private Integer nhbjeczfs;
	/**nhbnzfs*/
	@Excel(name = "本年二次走访数", width = 15)
	@ApiModelProperty(value = "本年二次走访数")
	private Integer nhbneczfs;
	/**nhljzfs*/
	@Excel(name = "农户累计二次走访数", width = 15)
	@ApiModelProperty(value = "农户累计二次走访数")
	private Integer nhljeczfs;
}
