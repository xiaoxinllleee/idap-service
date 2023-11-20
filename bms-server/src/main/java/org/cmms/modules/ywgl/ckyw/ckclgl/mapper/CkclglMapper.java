package org.cmms.modules.ywgl.ckyw.ckclgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ckyw.ckclgl.entity.Ckclgl;

/**
 * @Description: 存款存量管理
 * @Author: jeecg-boot
 * @Date:   2021-10-09
 * @Version: V1.0
 */
public interface CkclglMapper extends BaseMapper<Ckclgl> {
    void pCkclgl(@Param("clnf")String clnf);

}
