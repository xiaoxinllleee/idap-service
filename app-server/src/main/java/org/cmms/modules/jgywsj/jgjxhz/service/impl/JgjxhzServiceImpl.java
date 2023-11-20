package org.cmms.modules.jgywsj.jgjxhz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.jgywsj.jgjxhz.entity.TbTjfxJgjxhz;
import org.cmms.modules.jgywsj.jgjxhz.mapper.JgjxhzMapper;
import org.cmms.modules.jgywsj.jgjxhz.service.IJgjxhzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 机构绩效汇总
 * @Author: jeecg-boot
 * @Date:   2021-05-19
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class JgjxhzServiceImpl extends ServiceImpl<JgjxhzMapper, TbTjfxJgjxhz> implements IJgjxhzService {

}
