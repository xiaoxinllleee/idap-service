package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.PjsxZhsj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 评级授信支行数据
 * @Author: jeecg-boot
 * @Date:   2023-11-09
 * @Version: V1.0
 */
public interface PjsxZhsjMapper extends BaseMapper<PjsxZhsj> {

    void gxsj();
    void gxsjByZzbh(String zzbz);

}
