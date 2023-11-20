package org.cmms.modules.jtxx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 家庭信息
 * @Author: jeecg-boot
 * @Date:   2020-10-16
 * @Version: V1.0
 */
@Data
@TableName("Jtxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Jtxx对象", description="家庭信息")
public class JtxxList {

	private List<String> ids;

	@ExcelCollection(name="基本信息")
	private Jtxx jtxx;

	@ExcelCollection(name="家庭成员信息")
	private List<Jtxx> jtxxList;

	@ExcelCollection(name="家庭成员信息")
	private List<Jtxx> jtcyxx;
}
