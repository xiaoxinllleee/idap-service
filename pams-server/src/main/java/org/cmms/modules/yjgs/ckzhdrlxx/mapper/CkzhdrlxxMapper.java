package org.cmms.modules.yjgs.ckzhdrlxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgs.ckzhdrlxx.entity.Ckzhdrlxx;

/**
 * @Description: 存款账号待认领
 * @Author: jeecg-boot
 * @Date:   2023-04-10
 * @Version: V1.0
 */
public interface CkzhdrlxxMapper extends BaseMapper<Ckzhdrlxx> {
    public void init();

    List<Ckzhdrlxx> getListByIds(@Param("ids") List<String> ids);

    public void deleteSptgsj(String tableId);
}
