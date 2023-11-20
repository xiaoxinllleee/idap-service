package org.cmms.modules.ywgl.nxt.shpj.glzhxx.entity;

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
 * @Description: 关联账号信息
 * @Author: jeecg-boot
 * @Date:   2021-09-22
 * @Version: V1.0
 */
@Data
@TableName("ERP_NXT_GLZHXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_NXT_GLZHXX对象", description="关联账号信息")
public class GlzhxxImportVo {


	/**商户编码*/
	@Excel(name = "商户编码", width = 15)
    @ApiModelProperty(value = "商户编码")
	private String shbm;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private String shmc;
	/**账户户名*/
	@Excel(name = "账户户名", width = 15)
	@ApiModelProperty(value = "账户户名")
	private String zhmc;
	/**存款账号/卡号*/
	@Excel(name = "存款账号/卡号", width = 15)
    @ApiModelProperty(value = "存款账号/卡号")
	private String ckzh;

}
