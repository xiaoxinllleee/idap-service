package org.cmms.modules.khgl.etckh.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class DjkdkjlbVO {

    private String hm;
    private String dkje;
    private String hsrq;
    private String dkrq;
    private String dksj;
    private String dklsh;
    private String operno;
    private String lxfs;
    private String zh;
    private String xtkh;
    private String sfhq;

    @TableField(exist = false)
    private String sfcs = "2";
}
