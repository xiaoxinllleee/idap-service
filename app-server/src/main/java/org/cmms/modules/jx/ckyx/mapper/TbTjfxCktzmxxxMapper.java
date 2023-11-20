package org.cmms.modules.jx.ckyx.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.jx.ckyx.entity.TbTjfxCktzmxxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.jx.ckyx.entity.TbTjfxCktzmxxxVO;

/**
 * @Description: 存款拓展明细
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
public interface TbTjfxCktzmxxxMapper extends BaseMapper<TbTjfxCktzmxxx> {
    IPage<TbTjfxCktzmxxxVO> getListByPage(Page page,String yggh);
}
