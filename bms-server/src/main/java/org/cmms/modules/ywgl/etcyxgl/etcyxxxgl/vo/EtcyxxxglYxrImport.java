package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: ETC营销信息管理
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@ApiModel(value="Erp_bas_etcyxxxgl对象", description="ETC营销信息管理")
public class EtcyxxxglYxrImport {
	/**客户身份证号*/
	@Excel(name = "客户身份证号", width = 15)
    @ApiModelProperty(value = "客户身份证号")
	@ExcelVerify(notNull = true)
	private String khsfzh;
	/**开户时间(时分秒)*/
	@Excel(name = "开户时间(时分秒)", width = 15)
	@ApiModelProperty(value = "开户时间(时分秒)")
	@ExcelVerify(notNull = true)
	private String khsj;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	@ExcelVerify(notNull = true)
	private Date khrq;
	/**营销人工号*/
	@Excel(name = "营销人工号", width = 15)
	@ApiModelProperty(value = "营销人工号")
	private String yxrgh;
	/**营销机构代码*/
	@Excel(name = "营销机构代码", width = 15)
	@ApiModelProperty(value = "营销机构代码")
	@ExcelVerify(interHandler = true)
	private String yxjgdm;

}
