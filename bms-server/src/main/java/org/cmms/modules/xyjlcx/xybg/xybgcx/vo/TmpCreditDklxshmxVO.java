package org.cmms.modules.xyjlcx.xybg.xybgcx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 贷款利息收回明细（临时）
 */

@Data
@ToString
public class TmpCreditDklxshmxVO {

    /**贷款账号*/
    private String zh;
    /**起息日*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy.MM.dd")
    @DateTimeFormat(pattern="yyyy.MM.dd")
    private Date qxr;
    /**结息日*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy.MM.dd")
    @DateTimeFormat(pattern="yyyy.MM.dd")
    private Date jxr;
    /**利息*/
    private java.math.BigDecimal lx;
    /**收回本金*/
    private java.math.BigDecimal shbz;

}
