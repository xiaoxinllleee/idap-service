package org.cmms.modules.khgl.khglgx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.khglgx.entity.Glgxgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 关联关系管理
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
public interface GlgxglMapper extends BaseMapper<Glgxgl> {
    public void updateGlgx(String ykhjl,String zyhkhjl ,String name);

    public void updateKhGlgx(String zjhm ,String zyhkhjl,String name,String khxz);
}
