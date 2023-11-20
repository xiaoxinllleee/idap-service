package org.cmms.modules.message.service.impl;

import org.cmms.common.system.base.service.impl.JeecgServiceImpl;
import org.cmms.modules.message.entity.SysMessage;
import org.cmms.modules.message.mapper.SysMessageMapper;
import org.cmms.modules.message.service.ISysMessageService;
import org.springframework.stereotype.Service;

/**
 * @Description: 消息
 * @Author: cmms
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Service
public class SysMessageServiceImpl extends JeecgServiceImpl<SysMessageMapper, SysMessage> implements ISysMessageService {

}
