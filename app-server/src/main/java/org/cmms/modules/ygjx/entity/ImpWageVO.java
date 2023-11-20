package org.cmms.modules.ygjx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Date 2022/7/6
 * @Created by eran
 */
@Data
public class ImpWageVO {
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date gzyf;
    //应发
    private BigDecimal yfgzhj;
    //应扣
    private BigDecimal ykgzhj;
    //实发
    private BigDecimal sfgz;
}
