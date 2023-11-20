package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 龚辉
 * @date 2023/7/3 14:06 周一
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class XxnyztYxzfVo {

    private String tjrq;

    @Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    private String yggh;

    private String zbmc;

    private String sfdb;

    private String bz;


}
