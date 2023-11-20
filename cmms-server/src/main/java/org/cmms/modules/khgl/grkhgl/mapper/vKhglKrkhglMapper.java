package org.cmms.modules.khgl.grkhgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.grkhgl.entity.vKhglKrkhgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 个人客户
 * @Author: jeecg-boot
 * @Date:   2020-07-03
 * @Version: V1.0
 */
public interface vKhglKrkhglMapper extends BaseMapper<vKhglKrkhgl> {

    public String queryTableDictTextByKey(@Param("table") String table,@Param("text") String text,@Param("code") String code,@Param("key") String key);

    //public boolean deleteByMainId(@Param("zjhm")String zjhm);

}
