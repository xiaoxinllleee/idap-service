package org.cmms.modules.khgl.khhmc.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.khhmc.entity.Khhmc;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.khhmc.entity.KhhmcImport;

import java.util.List;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2019-09-26
 * @Version: V1.0
 */
public interface IKhhmcService extends IService<Khhmc> {
    public Khhmc queryByZjhm(String zjhm);

    public Khhmc queryByZjhmAndHhbm(String zjhm, String hhbm);

    public void initKhhmcxx();

    public List<Khhmc>selectByFrzjhm(String frzjhm);

}
