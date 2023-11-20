package org.cmms.modules.kmtj.ydcx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.kmtj.ydcx.entity.KmtjYdcx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 月度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
public interface KmtjYdcxMapper extends BaseMapper<KmtjYdcx> {
    void pYgmrscs(@Param("tjyf")String tjyf);
}
