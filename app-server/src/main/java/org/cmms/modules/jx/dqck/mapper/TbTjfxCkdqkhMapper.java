package org.cmms.modules.jx.dqck.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.jx.dqck.entity.TbTjfxCkdqkh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.jx.dqck.entity.TbTjfxCkdqkhVO;

/**
 * @Description: 存款到期
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
public interface TbTjfxCkdqkhMapper extends BaseMapper<TbTjfxCkdqkh> {

    IPage<TbTjfxCkdqkhVO> getListByPage(Page page,String yggh);
}
