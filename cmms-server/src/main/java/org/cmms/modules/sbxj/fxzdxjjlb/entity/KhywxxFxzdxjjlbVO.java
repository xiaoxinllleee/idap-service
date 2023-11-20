package org.cmms.modules.sbxj.fxzdxjjlb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 巡检记录表
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
@ToString
public class KhywxxFxzdxjjlbVO {

	private String zdbh;

	private String xjrgh;

	private String xjry;

	private String xjcs;


	private Date xjsj;

	private String sfsbgz;

	private String sfsbzc;

	private String sfpx;

	private String jydbgsffhgd;

	private String djbsfzc;

	private String jylc;

	private String cwhd;

	private String fxff;

	private String bcz;

	private String qgdbg;

	private String gzcl;

	private String shwt;

	private String xcjg;

	private String xcjgsm;

	private String jd;

	private String wd;

	private List<SbxxFjVO> yxdyfjxxList;

}
