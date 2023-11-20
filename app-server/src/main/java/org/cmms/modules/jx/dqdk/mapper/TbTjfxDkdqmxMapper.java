package org.cmms.modules.jx.dqdk.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.jx.dqdk.entity.TbTjfxDkdqmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.jx.dqdk.entity.TbTjfxDkdqmxVO;
import org.springframework.stereotype.Component;

/**
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Component
public interface TbTjfxDkdqmxMapper extends BaseMapper<TbTjfxDkdqmx> {
    List<TbTjfxDkdqmxVO> getListByYggh(String yggh);
    IPage<TbTjfxDkdqmxVO> getPageListByYggh(Page page, String yggh);
}
