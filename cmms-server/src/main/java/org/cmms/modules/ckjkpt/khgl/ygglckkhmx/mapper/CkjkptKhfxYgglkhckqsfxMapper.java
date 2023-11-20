package org.cmms.modules.ckjkpt.khgl.ygglckkhmx.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity.CkjkptKhfxYgglkhckqsfx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * @Description: 客户近10日存款余额
 * @Author: jeecg-boot
 * @Date:   2021-11-01
 * @Version: V1.0
 */
public interface CkjkptKhfxYgglkhckqsfxMapper extends BaseMapper<CkjkptKhfxYgglkhckqsfx> {
    public List<CkjkptKhfxYgglkhckqsfx> queryjsryeqs( @Param("zzbz") String zzbz,  @Param("gwbz") String gwbz, @Param("yggh") String yggh, @Param("zjhm") String zjhm);

    public List<CkjkptKhfxYgglkhckqsfx> queryjsryeqsDsj( @Param("zzbz") String zzbz,  @Param("gwbz") String gwbz, @Param("yggh") String yggh, @Param("zjhm") String zjhm);

    public List<CkjkptKhfxYgglkhckqsfx> queryjsryeqsByOracle( @Param("zzbz") String zzbz,  @Param("gwbz") String gwbz, @Param("yggh") String yggh, @Param("zjhm") String zjhm);

}
