package org.cmms.modules.khgl.dkkh.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 员工管户贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
@Data
public class TbDkYgghdksjmxVO {
    
	private TbDkYgghdksjmx tbDkYgghdksjmx;
	private String ghr;
	private String dklx;
	private String fiveClassTypeVal;

	public void appendGhr(String ygxm){
		if (this.ghr == null){
			this.ghr = ygxm;
		}else {
			this.ghr = this.ghr+"丶"+ygxm;
		}
	}

	public void appendDklx(String dklx){
		if (this.dklx == null){
			this.dklx = dklx;
		}else {
			this.dklx = this.dklx+"丶"+dklx;
		}
	}

}
