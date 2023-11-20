package org.cmms.modules.word.entity;

import lombok.Data;
import org.cmms.modules.xdgl.grdkgl.entity.Dydb;

import java.util.List;

@Data
public class GrdkdyDTO {
    private String id;
    private String zjhm;
    private String khmc;
    List<Dydb> dydbs;
}
