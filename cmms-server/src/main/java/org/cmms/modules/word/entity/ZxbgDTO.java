package org.cmms.modules.word.entity;

import lombok.Data;
import lombok.ToString;
import org.cmms.common.constant.WordConstant;

@Data
@ToString
public class ZxbgDTO {
    private String id;
    private String pid;
    private String zjhm;
    private String khmc;
    private String hhbm;
    private String pname;
    private String pzjhm;
    private String kuang = WordConstant.FANGKUANG;
}
