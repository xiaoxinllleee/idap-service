package org.cmms.modules.ywgl.yydj.ckkh.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @Date 2022/3/2
 * @Created by eran
 */
@Data
@ToString
public class CkkhDTO {
    private String khlx;
    private String khmc;
    private String zjhm;
    private String dqrq;
    private String khsj;
    private List<String> yxry;
    private String bz;
    private String zb;
}
