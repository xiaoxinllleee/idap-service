package org.cmms.modules.kmtj.ndcx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.kmtj.ndcx.entity.KmtjNdcx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 年度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
public interface KmtjNdcxMapper extends BaseMapper<KmtjNdcx> {
    void pYgmrscs(@Param("tjyf")String tjyf);
}
