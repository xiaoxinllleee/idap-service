package org.cmms.modules.dkjkpt.dkjkptfxtsh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class XzbldkmxVo {

    private Integer xh;
    private String khmc;
    private java.math.BigDecimal dkye;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dqrq;
    private String zkhjl;
}
