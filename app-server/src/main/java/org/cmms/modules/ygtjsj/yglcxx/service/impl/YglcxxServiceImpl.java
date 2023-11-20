package org.cmms.modules.ygtjsj.yglcxx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ygtjsj.yglcxx.mapper.YglcxxMapper;
import org.cmms.modules.ygtjsj.yglcxx.service.IYglcxxService;
import org.cmms.modules.ygtjsj.yglcxx.entity.TbTjfxYglcxx;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工揽储信息
 * @Author: jeecg-boot
 * @Date:   2021-05-15
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class YglcxxServiceImpl extends ServiceImpl<YglcxxMapper, TbTjfxYglcxx> implements IYglcxxService {

}
