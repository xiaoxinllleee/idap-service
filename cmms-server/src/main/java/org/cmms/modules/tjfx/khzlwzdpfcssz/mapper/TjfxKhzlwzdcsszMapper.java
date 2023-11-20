package org.cmms.modules.tjfx.khzlwzdpfcssz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.khzlwzdpfcssz.entity.TjfxKhzlwzdcssz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户建档完整度评分参数设置
 * @Author: cmms
 * @Date:   2019-12-05
 * @Version: V1.0
 */
public interface TjfxKhzlwzdcsszMapper extends BaseMapper<TjfxKhzlwzdcssz> {

    public List<TjfxKhzlwzdcssz> selectByMainId(String csbm);


    public  List<Map<String,String>> selectTable(@Param("tablename") String tablename);


}
