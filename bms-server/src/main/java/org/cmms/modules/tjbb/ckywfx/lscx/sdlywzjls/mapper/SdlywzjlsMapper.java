package org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.entity.Sdlywzjls;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 收单类业务资金流水
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
public interface SdlywzjlsMapper extends BaseMapper<Sdlywzjls> {
    void pSdlywzjls(@Param("ksrq")String ksrq,@Param("jsrq")String jsrq);
}
