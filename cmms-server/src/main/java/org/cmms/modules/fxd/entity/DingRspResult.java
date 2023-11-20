package org.cmms.modules.fxd.entity;

import lombok.Data;

@Data
public class DingRspResult {
    private String device_id;
    private String name;
    private String unionid;
    private String userid;
    private Boolean sys;
    private int sys_level;
}
