package org.cmms.modules.ywgl.yydj.etcyw.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ywgl.yydj.etcyw.entity.Etcyw;
import org.cmms.modules.ywgl.yydj.etcyw.mapper.EtcywMapper;
import org.cmms.modules.ywgl.yydj.etcyw.service.IEtcywService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: ETC业务
 * @Author: jeecg-boot
 * @Date:   2022-03-04
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class EtcywServiceImpl extends ServiceImpl<EtcywMapper, Etcyw> implements IEtcywService {

}
