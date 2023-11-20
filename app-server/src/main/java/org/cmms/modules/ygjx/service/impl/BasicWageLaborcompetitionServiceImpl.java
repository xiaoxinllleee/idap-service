package org.cmms.modules.ygjx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ygjx.entity.BasicWageLaborcompetition;
import org.cmms.modules.ygjx.mapper.BasicWageLaborcompetitionMapper;
import org.cmms.modules.ygjx.service.IBasicWageLaborcompetitionService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 劳动竞赛
 * @Author: jeecg-boot
 * @Date:   2022-03-02
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class BasicWageLaborcompetitionServiceImpl extends ServiceImpl<BasicWageLaborcompetitionMapper, BasicWageLaborcompetition> implements IBasicWageLaborcompetitionService {

}
