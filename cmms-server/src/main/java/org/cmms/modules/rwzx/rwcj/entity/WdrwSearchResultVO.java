package org.cmms.modules.rwzx.rwcj.entity;

import lombok.Data;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;

@Data
public class WdrwSearchResultVO {
    @Dict( dicCode="id", dictTable="TASK_CREATE", dicText="title")
    private String yxid;
    private String id;
    @Dict(dicCode = "fxd_status")
    private String status;
    private String khmc;
    private String zjhm;
    private String xtpdjg;
    @Dict( dicCode="zzbz", dictTable="hr_bas_organization", dicText="zzjc")
    private String sszh;
    @Dict(dicCode = "yhzgx")
    private String yhzgx;
    private String sjhm;
    private String hjdz;

    private Ywhywwlxx ywhywwlxx;
}
