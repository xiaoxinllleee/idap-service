package org.cmms.modules.khgl.khglgx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.khglgx.entity.Khhmc;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2019-09-26
 * @Version: V1.0
 */
public interface QKhhmcMapper extends BaseMapper<Khhmc> {
    public Khhmc queryByZjhm(String zjhm);

    public Khhmc queryByZjhmAndHhbm(String zjhm, String hhbm);

    public void initKhhmcxx();

}
