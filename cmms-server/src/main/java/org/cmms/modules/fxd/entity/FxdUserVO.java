package org.cmms.modules.fxd.entity;

import lombok.Data;
import org.cmms.modules.system.entity.SysBasUser;

@Data
public class FxdUserVO {
    private DingRspResult dingRspResult;
    private SysBasUser sysBasUser;
}
