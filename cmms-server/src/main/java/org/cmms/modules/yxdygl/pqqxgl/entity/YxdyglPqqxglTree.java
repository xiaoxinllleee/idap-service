package org.cmms.modules.yxdygl.pqqxgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
import java.util.List;

/**
 * @Description: 片区权限管理
 * @Author: jeecg-boot
 * @Date:   2021-11-17
 * @Version: V1.0
 */
@Data
public class YxdyglPqqxglTree {
    
	/**主键id*/
	private String id;
	/**所属支行*/
	private String sszh;
	/**是否主客户经理*/
	private String sfzkhjl;
	private String sfzkhjlVal;
	/**创建人*/
	private String createBy;
	/**创建时间*/
	private Date createTime;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	private Date updateTime;
	/**所属单元编号*/
	private String menuId;
	private String menuIdVal;
	/**客户经理*/
	private String khjl;
	private String khjlVal;
	/**区分标识(1:农户，2：商户)*/
	private String sjqx;
	
	@TableField(exist=false)
	List<YxdyglPqqxglTree> children;
}
