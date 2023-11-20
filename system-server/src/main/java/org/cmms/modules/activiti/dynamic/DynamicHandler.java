package org.cmms.modules.activiti.dynamic;

import org.cmms.modules.system.entity.SysUser;
import java.util.List;
import java.util.Map;

public abstract class DynamicHandler {
    public List<SysUser> getDynamicUser(Map<String, Object> paramMap) {
        return null;
    }
}
