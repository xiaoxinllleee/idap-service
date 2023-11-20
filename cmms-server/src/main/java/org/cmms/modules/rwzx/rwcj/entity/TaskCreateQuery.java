package org.cmms.modules.rwzx.rwcj.entity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;

import java.math.BigDecimal;

/**
 * @Date 2023/3/31
 * @Created by eran
 */
@Data
public class TaskCreateQuery {
    private String wgbh;
    private String khlx;
    //是否存款客戶
    private String sfckkh;

    //存款区间
    private BigDecimal ckje_begin;
    private BigDecimal ckje_end;

    //是否贷款客户
    private String sfdkkh;

    //贷款区间
    private BigDecimal dkje_begin;
    private BigDecimal dkje_end;
    //年龄区间
    private BigDecimal nl_begin;
    private BigDecimal nl_end;

    private BigDecimal xb;
    //private String sflqsbk;
    private String sfblsbk;
    private String sfktsjyhyw;
    private String id;
    //营销类型 ： 1 贷款流失户 2 二次走访
    private Integer yxlx;

    private String rwlx;


    private String rwmc;
    private String sszh;
    private String sswg;
    private String ywsszh;
    private String khmc;
    private String zjhm;

}
