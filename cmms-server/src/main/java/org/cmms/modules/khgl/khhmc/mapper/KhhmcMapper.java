package org.cmms.modules.khgl.khhmc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.khhmc.entity.Khhmc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2019-09-26
 * @Version: V1.0
 */
public interface KhhmcMapper extends BaseMapper<Khhmc> {
    public Khhmc queryByZjhm(String zjhm);

    public Khhmc queryByZjhmAndHhbm(String zjhm, String hhbm);

    public void initKhhmcxx();

    public List<Khhmc>selectByFrzjhm(@Param("zjhm")String zjhm);
}
