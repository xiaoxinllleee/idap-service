package org.cmms.modules.word.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class GrdkMtDTO {

    private String sqje;
    private String id;
    private String jkyt;
    private String jkqx;
    private String zczeHj;
    private BigDecimal zczeHjBig;
    private Date updateTime;
    private String updateBy;
}
