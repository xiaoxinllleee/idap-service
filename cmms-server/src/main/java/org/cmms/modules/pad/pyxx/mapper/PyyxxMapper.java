package org.cmms.modules.pad.pyxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.nhxxgl.entity.KhglYwhywwlxxPad;
import org.cmms.modules.pad.pyxx.entity.Pyyxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 评议员信息
 * @Author: jeecg-boot
 * @Date:   2020-07-24
 * @Version: V1.0
 */
public interface PyyxxMapper extends BaseMapper<Pyyxx> {

    List<String> getByPyls(Integer pyls);

    void updateSxxx(@Param("id") String id);

    List<KhglYwhywwlxxPad> getbldkInfo(String hhbm);
}
