package org.cmms.modules.pad.qtzrrxxgl.entity;

import lombok.Data;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FjxxRecive {
    String name;
    String url;
    BigDecimal size;
    String zlbh;
    String hhbm;
    String zllx;
    String zjhm;
    String qydm;
    String zlxh;

    List<KhglNhhzzllb>  khglNhhzzllbs;
}
