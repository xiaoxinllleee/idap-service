package org.cmms.modules.khpjsx.pjzxmsz.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-02-26
 * @Version: V1.0
 */
@Data
@TableName("PJSX_PJZXMGZSZ_GS")
public class PJSX_PJZXMGZSZ_GS implements Serializable {
    private static final long serialVersionUID = 1L;

	/**满分方向（1：以上，2：以下）*/
	private String mffx;
	/**客户类型*/
	private String khlx;
	/**标准值*/
	private Double bzz;
	/**升降（1:每上升，2：每下降）*/
	private String sj;
	/**系数*/
	private Double xs;
	/**区域代码*/
	private String qydm;
	/**父项目编号*/
	private String xmbhId;
	/**项目编号*/
	private String xmbh;
	/**加减（1：加，2：扣）*/
	private String jj;
	/**单位分值*/
	private Double dwfz;
	/**最低分*/
	private Double zdf;
	/**最高分*/
	private Double zgf;
}
