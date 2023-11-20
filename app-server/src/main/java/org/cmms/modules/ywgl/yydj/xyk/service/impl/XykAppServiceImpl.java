package org.cmms.modules.ywgl.yydj.xyk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.yydj.xyk.entity.XykApp;
import org.cmms.modules.ywgl.yydj.xyk.mapper.XykAppMapper;
import org.cmms.modules.ywgl.yydj.xyk.service.IXykAppService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 信用卡App
 * @Author: jeecg-boot
 * @Date:   2022-03-22
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class XykAppServiceImpl extends ServiceImpl<XykAppMapper, XykApp> implements IXykAppService {

}
