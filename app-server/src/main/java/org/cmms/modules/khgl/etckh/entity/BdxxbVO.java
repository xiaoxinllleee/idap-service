package org.cmms.modules.khgl.etckh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BdxxbVO {

    private String zh;
    private String khmc;
    private String khjg;
    private String operno;
    private String lxfs;
    private String zjhm;
    private String dkje;
    private int xb;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date csrq;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date frcsrq;

    private String zz;
    private String dzyx;

    private String frdb;
    private String frlxfs;
    private int frxb;
    private String frlxdz;
    private String frdzyx;

    private String custId;

}
