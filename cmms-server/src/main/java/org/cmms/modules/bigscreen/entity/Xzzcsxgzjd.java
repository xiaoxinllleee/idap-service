package org.cmms.modules.bigscreen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Xzzcsxgzjd {
    @Dict(dicCode ="wgbh",dictTable="YXDYGL_MAIN",dicText="WGMC")
    private String wgbh;
    private BigDecimal zhs;
    private BigDecimal bmdhs;
    private BigDecimal zhsdbmdhs;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy年MM月dd日")
    private Date tjrq;
}
