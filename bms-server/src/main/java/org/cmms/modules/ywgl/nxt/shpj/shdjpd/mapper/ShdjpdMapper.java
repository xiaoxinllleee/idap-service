package org.cmms.modules.ywgl.nxt.shpj.shdjpd.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.entity.Shdjpd;

/**
 * @Description: 商户等级评定
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
public interface ShdjpdMapper extends BaseMapper<Shdjpd> {
      //    void pShdjpd(@Param("pdlx")String pdlx, @Param("pdzq")String pdzq, @Param("username")String username);
      public void pShdjpd(Map<String, String> sql,String username);
}
