package org.cmms.modules.jx.ckgl.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.jx.ckgl.entity.TbTjfxCkzrzzmxxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.jx.ckgl.entity.TbTjfxCkzrzzmxxxVo;

/**
 * @Description: 存款拓展明细信息
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
public interface TbTjfxCkzrzzmxxxMapper extends BaseMapper<TbTjfxCkzrzzmxxx> {
    IPage<TbTjfxCkzrzzmxxxVo> getListByYggh(Page<TbTjfxCkzrzzmxxxVo> page,@Param("yggh") String yggh);
}
