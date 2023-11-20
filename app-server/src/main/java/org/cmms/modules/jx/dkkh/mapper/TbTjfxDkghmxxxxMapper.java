package org.cmms.modules.jx.dkkh.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.jx.dkkh.entity.TbTjfxDkghmxxxx;
import org.cmms.modules.jx.dkkh.entity.TbTjfxDkghmxxxxBnblmxVO;
import org.springframework.stereotype.Component;

/**
 * @Description: 贷款管户明细信息
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
@Component
public interface TbTjfxDkghmxxxxMapper extends BaseMapper<TbTjfxDkghmxxxx> {

    IPage<TbTjfxDkghmxxxxBnblmxVO> getPageByYggh(Page page,String yggh);
}
