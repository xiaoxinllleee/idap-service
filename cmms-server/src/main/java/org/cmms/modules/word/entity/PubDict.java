package org.cmms.modules.word.entity;

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
 * @Description: pad字典表
 * @Author: jeecg-boot
 * @Date:   2020-08-26
 * @Version: V1.0
 */
@Data
@TableName("PUB_DICT")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PUB_DICT对象", description="pad字典表")
public class PubDict {
    
	/**dictNm*/
	@Excel(name = "dictNm", width = 15)
    @ApiModelProperty(value = "dictNm")
	private String dictNm;
	/**dictKey*/
	@Excel(name = "dictKey", width = 15)
    @ApiModelProperty(value = "dictKey")
	private String dictKey;
	/**dictValue*/
	@Excel(name = "dictValue", width = 15)
    @ApiModelProperty(value = "dictValue")
	private String dictValue;
	/**tmSmp*/
	@Excel(name = "tmSmp", width = 15)
    @ApiModelProperty(value = "tmSmp")
	private String tmSmp;
}
