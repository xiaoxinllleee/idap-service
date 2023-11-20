package org.cmms.modules.khgl.nh.entity;

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
 * @Date:   2020-02-11
 * @Version: V1.0
 */
@Data
@TableName("CMMS_SHSPJYXX")
public class Cmms_shjyx implements Serializable {
    private static final long serialVersionUID = 1L;

	/**证件号码*/
	private String zjhm;
	/**营业执照名称*/
	private String yyzzmc;
	/**营业执照号码*/
	private String yyzzhm;
	/**职工人数*/
	private Integer zgrs;
	/**年营业收入(万元)*/
	private java.math.BigDecimal nyysr;
	/**年进货成本(万元)*/
	private java.math.BigDecimal njhcb;
	/**成本率*/
	private java.math.BigDecimal cbl;
	/**年固定费用(万元)*/
	private java.math.BigDecimal ngdfy;
	/**年经营利润*/
	private java.math.BigDecimal njylr;
	/**年净收入(万元)*/
	private java.math.BigDecimal njsr;
	/**其他收入(万元)*/
	private java.math.BigDecimal qtsr;
	/**其他支出(万元)*/
	private java.math.BigDecimal qtzc;
	/**总收入(万元)*/
	private java.math.BigDecimal zrs;
	/**总支出(万元)*/
	private java.math.BigDecimal zzc;
	/**经营商铺是否租赁*/
	private String jyspsfzl;
	/**经营商铺是否抵押*/
	private String jyspsfdy;
}
