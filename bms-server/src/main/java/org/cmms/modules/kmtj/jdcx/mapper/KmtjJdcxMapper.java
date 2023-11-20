package org.cmms.modules.kmtj.jdcx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.kmtj.jdcx.entity.KmtjJdcx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 季度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
public interface KmtjJdcxMapper extends BaseMapper<KmtjJdcx> {
    void pYgmrscs(@Param("tjyf")String tjyf);
}
