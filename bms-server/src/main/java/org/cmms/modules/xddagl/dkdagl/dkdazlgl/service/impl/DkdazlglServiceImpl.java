package org.cmms.modules.xddagl.dkdagl.dkdazlgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.dkdagl.dkdazlgl.entity.Dkdazlgl;
import org.cmms.modules.xddagl.dkdagl.dkdazlgl.mapper.DkdazlglMapper;
import org.cmms.modules.xddagl.dkdagl.dkdazlgl.service.IDkdazlglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款档案资料管理
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class DkdazlglServiceImpl extends ServiceImpl<DkdazlglMapper, Dkdazlgl> implements IDkdazlglService {

}
