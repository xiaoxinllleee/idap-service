package org.cmms.modules.zhgl.wdckls.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.zhgl.wdckls.entity.Wdckls;
import org.cmms.modules.zhgl.wdckls.mapper.WdcklsMapper;
import org.cmms.modules.zhgl.wdckls.service.IWdcklsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 网点存款流失
 * @Author: jeecg-boot
 * @Date:   2022-03-12
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class WdcklsServiceImpl extends ServiceImpl<WdcklsMapper, Wdckls> implements IWdcklsService {

}
