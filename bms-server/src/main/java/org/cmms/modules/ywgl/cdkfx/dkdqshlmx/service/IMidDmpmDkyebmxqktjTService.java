package org.cmms.modules.ywgl.cdkfx.dkdqshlmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.dkdqshlmx.entity.MidDmpmDkyebmxqktjT;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款到期收回率明细
 * @Author: jeecg-boot
 * @Date:   2021-07-02
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IMidDmpmDkyebmxqktjTService extends IService<MidDmpmDkyebmxqktjT> {
    IPage<MidDmpmDkyebmxqktjT> getList(Page<MidDmpmDkyebmxqktjT> page, Wrapper wrapper,
                                       String hxrq );
}
