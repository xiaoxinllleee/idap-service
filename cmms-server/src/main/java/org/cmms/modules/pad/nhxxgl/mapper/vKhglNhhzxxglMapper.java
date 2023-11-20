package org.cmms.modules.pad.nhxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.nhxxgl.entity.vKhglNhhzxxgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-29
 * @Version: V1.0
 */
public interface vKhglNhhzxxglMapper extends BaseMapper<vKhglNhhzxxgl> {

    public void init(String hhbm, String zjhm, String yggh, String username, String zfrq);
    public void init1(String hhbm);
    public void init2(String hhbm);
    public Integer init3(String hhbm);

    List<String> getLrryList();

    List<String> getPfrList();
}
