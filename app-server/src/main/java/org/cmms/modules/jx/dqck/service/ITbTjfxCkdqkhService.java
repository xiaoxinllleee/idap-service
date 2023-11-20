package org.cmms.modules.jx.dqck.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.dqck.entity.TbTjfxCkdqkh;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.jx.dqck.entity.TbTjfxCkdqkhVO;

import java.util.Map;

/**
 * @Description: 存款到期
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@DS("eweb")
public interface ITbTjfxCkdqkhService extends IService<TbTjfxCkdqkh> {

    IPage<TbTjfxCkdqkhVO> getListByPage(Page page,String yggh);
}
