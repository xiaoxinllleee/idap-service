package org.cmms.modules.jx.dqdk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.dqdk.entity.TbTjfxDkdqmx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.jx.dqdk.entity.TbTjfxDkdqmxVO;

import java.util.List;

/**
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@DS("eweb")
public interface ITbTjfxDkdqmxService extends IService<TbTjfxDkdqmx> {
    List<TbTjfxDkdqmxVO> getListByYggh(String yggh);
    IPage<TbTjfxDkdqmxVO> getListByYggh(Page page,String yggh);
}
