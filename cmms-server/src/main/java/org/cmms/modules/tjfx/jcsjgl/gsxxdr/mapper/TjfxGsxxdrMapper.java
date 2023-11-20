package org.cmms.modules.tjfx.jcsjgl.gsxxdr.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.jcsjgl.gsxxdr.entity.TjfxGsxxdr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-12
 * @Version: V1.0
 */
public interface TjfxGsxxdrMapper extends BaseMapper<TjfxGsxxdr> {
    //根据证件号码和公式日期删除记录
    public int delete2(@Param("zjhm")String zjhm,@Param("sszh") String sszh,@Param("gsrq") Date gsrq);

    public TjfxGsxxdr queryByMsg(@Param("sszh") String sszh, @Param("zjhm")String zjhm, @Param("gsrq")Date gsrq);

    public Long querygscs(@Param("sszh") String sszh,@Param("zjhm") String zjhm );
}
