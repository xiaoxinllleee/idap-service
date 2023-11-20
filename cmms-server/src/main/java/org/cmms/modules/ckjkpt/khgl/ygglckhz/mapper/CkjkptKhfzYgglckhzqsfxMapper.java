package org.cmms.modules.ckjkpt.khgl.ygglckhz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity.CkjkptKhfxYgglkhckqsfx;
import org.cmms.modules.ckjkpt.khgl.ygglckhz.entity.CkjkptKhfzYgglckhzqsfx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 员工揽储趋势分析
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
public interface CkjkptKhfzYgglckhzqsfxMapper extends BaseMapper<CkjkptKhfzYgglckhzqsfx> {
    public List<CkjkptKhfzYgglckhzqsfx> queryjsryeqs(@Param("zzbz") String zzbz, @Param("gwbz") String gwbz, @Param("yggh") String yggh);
    public List<CkjkptKhfzYgglckhzqsfx> queryjsryeqsByOracle(@Param("zzbz") String zzbz, @Param("gwbz") String gwbz, @Param("yggh") String yggh);

}
