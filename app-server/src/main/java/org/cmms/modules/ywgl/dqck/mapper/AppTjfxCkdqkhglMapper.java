package org.cmms.modules.ywgl.dqck.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.dqck.entity.AppDqckVO;
import org.cmms.modules.ywgl.dqck.entity.AppTjfxCkdqkh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 到期存款
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
public interface AppTjfxCkdqkhglMapper extends BaseMapper<AppTjfxCkdqkh> {

    public IPage<AppDqckVO> getAppList(Page page, @Param("type") String type
            , @Param("yggh") String yggh);
}
