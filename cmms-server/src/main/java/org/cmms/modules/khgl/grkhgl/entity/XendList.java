package org.cmms.modules.khgl.grkhgl.entity;

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
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="xendList", description="小额农贷")
public class XendList {



	@ExcelCollection(name="客户花名册信息")
	private Khhmcxx khhmcxx;

	@ExcelCollection(name="家庭成员信息")
	private Khhmcxx jtcyxx;

	@ExcelCollection(name="农户评级授信信息")
	private CamsZcsxGrpjsxxx camsZcsxGrpjsxxx;

	@ExcelCollection(name="农户采集信息")
	private ZcsxNhcjxx zcsxNhcjxx;
}
