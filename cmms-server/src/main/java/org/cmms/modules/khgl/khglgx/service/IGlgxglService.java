package org.cmms.modules.khgl.khglgx.service;

import org.cmms.modules.khgl.khglgx.entity.Glgxgl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 关联关系管理
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
public interface IGlgxglService extends IService<Glgxgl> {
    public void updateGlgx(String ykhjl ,String zyhkhjl,String name);
    public void updateKhGlgx(String zjhm ,String zyhkhjl,String name,String khxz);
}
