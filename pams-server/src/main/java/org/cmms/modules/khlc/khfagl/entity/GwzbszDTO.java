package org.cmms.modules.khlc.khfagl.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GwzbszDTO {
    //var data={schemeId:that.schemeId,indexType:that.zblx, indexId:formData.id,balTypeId:formData.yelx,curTypeId:formData.bz
    // ,applyTypeId:formData.yylx,evlObjType:formData.pjdx}

    private String bz;
    private String schemeId;
    private String indexType;
    private String id;
    private String yelx;
    private String yylx;
    private String pjdx;
    private BigDecimal zbdj;
    private String zbdw;
    private BigDecimal tjxs;
    private BigDecimal zbqz;
    private BigDecimal rwwdj;
}
