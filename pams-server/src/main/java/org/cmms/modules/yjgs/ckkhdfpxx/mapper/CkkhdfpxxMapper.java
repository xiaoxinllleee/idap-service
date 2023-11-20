package org.cmms.modules.yjgs.ckkhdfpxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgs.ckkhdfpxx.entity.Ckkhdfpxx;

/**
 * @Description: 待认领存款客户信息
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
public interface CkkhdfpxxMapper extends BaseMapper<Ckkhdfpxx> {
    public void init();

    List<Ckkhdfpxx> getListByIds(@Param("ids") List<String> ids);

    public void deleteSptgsj(String tableId);
}
