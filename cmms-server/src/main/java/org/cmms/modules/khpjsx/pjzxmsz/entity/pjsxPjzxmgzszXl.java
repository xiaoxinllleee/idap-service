package org.cmms.modules.khpjsx.pjzxmsz.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 与我行往来情况
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Data
@TableName("pjsx_pjzxmgzsz_xl")
public class pjsxPjzxmgzszXl implements Serializable {
    private static final long serialVersionUID = 1L;

	/**区域代码*/
    @Excel(name = "区域代码", width = 15)
	private String qydm;
	/**客户类型*/
	private String khlx;
	/**父项目编号*/
	private String xmbhId;
	/**项目编号*/
    @Excel(name = "项目编号", width = 15)
	private String xmbh;
	/**键*/
    @Excel(name = "键", width = 15)
	private String key;
	/**值*/
    @Excel(name = "值", width = 15)
	private Integer value;
	/**分值*/
    @Excel(name = "分值", width = 15)
	private Double fz;
}
