package org.cmms.modules.xdgl.nsb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xdgl.nsb.entity.CamsNhpjsxNsb;

/**
 * @Description: 浏阳农户评级授信农户年审表
 * @Author: jeecg-boot
 * @Date:   2022-10-12
 * @Version: V1.0
 */
public interface CamsNhpjsxNsbMapper extends BaseMapper<CamsNhpjsxNsb> {

    public List<String> getAllByErrorType(@Param("sszh") String sszh,@Param("errorType") String errorType);

    public void updateByDao(@Param("dao") CamsNhpjsxNsb camsNhpjsxNsb);

    void tq();
}
