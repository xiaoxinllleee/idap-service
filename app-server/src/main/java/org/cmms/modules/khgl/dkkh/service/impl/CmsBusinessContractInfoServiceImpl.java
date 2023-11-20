package org.cmms.modules.khgl.dkkh.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khgl.dkkh.entity.CmsBusinessContractInfo;
import org.cmms.modules.khgl.dkkh.mapper.CmsBusinessContractInfoMapper;
import org.cmms.modules.khgl.dkkh.service.ICmsBusinessContractInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款借据表
 * @Author: jeecg-boot
 * @Date:   2022-04-11
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class CmsBusinessContractInfoServiceImpl extends ServiceImpl<CmsBusinessContractInfoMapper, CmsBusinessContractInfo> implements ICmsBusinessContractInfoService {

}
