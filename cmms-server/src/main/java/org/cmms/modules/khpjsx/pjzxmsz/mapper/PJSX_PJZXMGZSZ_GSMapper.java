package org.cmms.modules.khpjsx.pjzxmsz.mapper;

import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.cmms.modules.khpjsx.pjzxmsz.entity.PJSX_PJZXMGZSZ_GS;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-02-26
 * @Version: V1.0
 */
public interface PJSX_PJZXMGZSZ_GSMapper extends BaseMapper<PJSX_PJZXMGZSZ_GS> {
    public boolean deleteByMainId(@Param("mainId")String mainId,@Param("khlx")String khlx);

    public List<PJSX_PJZXMGZSZ_GS> selectByMainId(@Param("mainId")String mainId, @Param("khlx")String khlx);
}
