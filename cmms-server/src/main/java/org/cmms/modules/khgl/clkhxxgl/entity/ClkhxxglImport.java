package org.cmms.modules.khgl.clkhxxgl.entity;

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
 * @Description: 存量客户信息管理
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Data
@TableName("v_khgl_khjbxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgl_khjbxx对象", description="存量客户信息管理")
public class ClkhxxglImport {
	/**zzbz*/
	@Excel(name = "机构名称", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String zzbz;
	/**jgdm*/
	@Excel(name = "机构代码", width = 15)
	private String jgdm;
	/**khbh*/
	@Excel(name = "客户编号", width = 15)
	private String khbh;
	/**ssyxdy*/
	@Excel(name = "所属营销单元", width = 15,dicCode="dybh",dictTable="YXDYGL_EJYXDYGL",dicText="dymc")
	private String ssyxdy;
	/**khmc*/
	@Excel(name = "客户名称", width = 15)
	private String khmc;
	/**zjlx*/
	private String zjlx;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
	private String zjhm;
	/**khxz*/
	private String khxz;
	/**dz*/
	@Excel(name = "地址", width = 15)
	private String dz;
	/**lxfs*/
	@Excel(name = "联系方式", width = 15)
	private String lxfs;
	/**khzycd*/
	@Excel(name = "客户重要程度", width = 15,dicCode = "khzycd")
	private String khzycd;
	/**khqzyw*/
	@Excel(name = "客户潜在业务", width = 15,dicCode = "khqzyw")
	private String khqzyw;
}
