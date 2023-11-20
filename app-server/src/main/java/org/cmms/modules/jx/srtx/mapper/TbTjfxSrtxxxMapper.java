package org.cmms.modules.jx.srtx.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.jx.srtx.entity.TbTjfxSrtxxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.jx.srtx.entity.TbTjfxSrtxxxVo;

/**
 * @Description: 生日提醒信息表
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
public interface TbTjfxSrtxxxMapper extends BaseMapper<TbTjfxSrtxxx> {
    IPage<TbTjfxSrtxxxVo> getPageByYggh(Page<TbTjfxSrtxxxVo> page, @Param("yggh") String yggh);
}
