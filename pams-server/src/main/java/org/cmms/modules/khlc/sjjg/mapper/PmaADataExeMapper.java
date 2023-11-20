package org.cmms.modules.khlc.sjjg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khlc.sjjg.entity.PmaADataExe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 数据加工功能
 * @Author: jeecg-boot
 * @Date:   2023-03-08
 * @Version: V1.0
 */
public interface PmaADataExeMapper extends BaseMapper<PmaADataExe> {
    public void extract(@Param("spname") String spname, @Param("sjrq")  String sjrq, @Param("rwid") String rwid);

}
