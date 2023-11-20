package org.cmms.modules.tjbb.tjfz.czbxzhgl.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 财政保险账户管理
 * @Author: jeecg-boot
 * @Date:   2021-08-26
 * @Version: V1.0
 */
@Data
@TableName("TJBB_TJFZ_CZBXZHGL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_TJFZ_CZBXZHGL对象", description="财政保险账户管理")
public class CzbxzhglVO {

    /**存款账号*/
    @Excel(name = "存款账号", width = 15)
    @ApiModelProperty(value = "存款账号")
    @ExcelVerify(notNull = true,interHandler = true)
    private String ckzh;
}
