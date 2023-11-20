package org.cmms.modules.khpjsx.pjzxmsz.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 评级子项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Data
@TableName("pjsx_pjzxmsz")
public class pjsxPjzxmsz implements Serializable {
    private static final long serialVersionUID = 1L;

	/**区域代码*/
	private String qydm;
	/**父项目编号*/
	@Dict(dicCode = "sjxid", dictTable = "v_khpjsx_sjxmc", dicText = "sjxmc")
	private String fxmbh;
	/**项目编号*/
	private String xmbh;
	/**项目名称*/
	private String xmmc;
	/**计算方式(1.下拉（单选）  2.区间  3.公式)*/
	@Dict(dicCode = "pjsx_jsfs")
	private String jsfs;
	/**分值*/
	private Integer fz;
	/**客户类型*/
	@Dict(dicCode = "khlx")
	private String khlx;
	/**备注*/
	private String bz;
	/**排序序号*/
	private Integer pxxh;
	/**是否启用 0 启用 1 停用*/
	private String sfqy;
}
