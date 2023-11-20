package org.cmms.modules.jx.ckyj.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.jx.ckyj.entity.TbTjfxCkyjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.jx.ckyj.entity.TbTjfxCkyjxxVo;

/**
 * @Description: 存款预警信息表
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
public interface TbTjfxCkyjxxMapper extends BaseMapper<TbTjfxCkyjxx> {
    IPage<TbTjfxCkyjxxVo> queryListByYggh(Page<TbTjfxCkyjxxVo> page, @Param("yggh") String yggh);

    IPage<TbTjfxCkyjxxVo> getWatchList(Page<TbTjfxCkyjxxVo> page, String yggh, String khbh);
}
