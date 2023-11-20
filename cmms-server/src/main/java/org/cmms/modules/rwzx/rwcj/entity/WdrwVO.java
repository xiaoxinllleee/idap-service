package org.cmms.modules.rwzx.rwcj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Date 2023/4/2
 * @Created by eran
 */
@Data
public class WdrwVO {
    private String title;
    private String yxid;
    private Integer rwzs;
    private Integer yzf;
    private Integer wzf;
    private Integer syts;
    private BigDecimal bfb;
    private Serier pie;
    @Dict(dicCode = "marketing_type")
    private String yxlx;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;
}
