package org.cmms.modules.yxdygl.czxxgl.entity;

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
 * @Description: 村信息管理
 * @Author: cmms
 * @Date:   2019-11-05
 * @Version: V1.0
 */
@Data
@TableName("YXDYGL_CZXXGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YXDYGL_CZXXGL对象", description="村组信息管理")
public class Yxdygl_czxxgl {

	/**区域编码*/
	@Excel(name = "区域编码", width = 15)
    @ApiModelProperty(value = "区域编码")
	private String qybm;
	/**乡镇*/
	@Excel(name = "乡镇", width = 15)
    @ApiModelProperty(value = "乡镇")
	private String town;
	/**村（街道/居委会）*/
	@Excel(name = "村（街道/居委会）", width = 15)
    @ApiModelProperty(value = "村（街道/居委会）")
	private String village;
	/**组*/
	@Excel(name = "组", width = 15)
    @ApiModelProperty(value = "组")
	private String organize;
	/**所属机构代码*/
	@Excel(name = "所属机构代码", width = 15, dicCode="ywjgdm", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "所属机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String ssjgdm;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "主客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String zkhjl;
	/**辅客户经理*/
	@Excel(name = "辅客户经理", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
	@ApiModelProperty(value = "辅客户经理")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String fkhjl;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
}
