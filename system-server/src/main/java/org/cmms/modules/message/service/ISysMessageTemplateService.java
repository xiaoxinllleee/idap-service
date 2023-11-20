package org.cmms.modules.message.service;

import java.util.List;

import org.cmms.common.system.base.service.JeecgService;
import org.cmms.modules.message.entity.SysMessageTemplate;

/**
 * @Description: 消息模板
 * @Author: cmms
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends JeecgService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}
