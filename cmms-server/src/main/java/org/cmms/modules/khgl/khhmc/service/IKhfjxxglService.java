package org.cmms.modules.khgl.khhmc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.khhmc.entity.KhfjxxglExp;

import java.util.List;

/**
 * @Description: 客户附加信息管理
 * @Author: jeecg-boot
 * @Date:   2020-03-27
 * @Version: V1.0
 */
public interface IKhfjxxglService extends IService<Khfjxxgl> {
    public void updateKhhmc();
    public void updateywwl();
    public void updateHzxx();

    IPage<Khfjxxgl> getByWgbh(Page page,String wgbh, String type);

    List<KhfjxxglExp> getFjxxByWgbh(String wgbh,String yggh);
}
