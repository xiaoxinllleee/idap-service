package org.cmms.modules.khgl.jzyx.yxzfqd.entity;

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
 * @Description: 优先走访清单
 * @Author: jeecg-boot
 * @Date:   2023-03-25
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_JZYX_YXZFQD对象", description="优先走访清单")
public class YxzfqdVo {
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private String dhhm;
	/**一卡通账号*/
	@Excel(name = "一卡通账号", width = 15)
	@ApiModelProperty(value = "一卡通账号")
	private String yktzh;
	/**种植地址*/
	@Excel(name = "种植地址", width = 15)
	@ApiModelProperty(value = "种植地址")
	private String zzdd;
	/**租赁稻田面积*/
	@Excel(name = "租赁稻田面积", width = 15)
	@ApiModelProperty(value = "租赁稻田面积")
	private String zldtmj;
	/**补贴金额*/
	@Excel(name = "补贴金额", width = 15)
	@ApiModelProperty(value = "补贴金额")
	private String btje;
	/**镇办核实双季稻种植*/
	@Excel(name = "镇办核实双季稻种植", width = 15)
	@ApiModelProperty(value = "镇办核实双季稻种植")
	private String zbhssjdzz;
	/**优先类型*/
	@Excel(name = "优先类型", width = 15, dicCode = "jzyx_yxlx")
	@ApiModelProperty(value = "优先类型")
	@Dict(dicCode = "jzyx_yxlx")
	private String yxlx;
	/**其他*/
	@Excel(name = "其他", width = 15)
	@ApiModelProperty(value = "其他")
	private String qt;

}
