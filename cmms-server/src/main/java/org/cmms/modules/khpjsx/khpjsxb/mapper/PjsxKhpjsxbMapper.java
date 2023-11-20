package org.cmms.modules.khpjsx.khpjsxb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khpjsx.khpjsxb.entity.PjsxKhpjsxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户评级授信表
 * @Author: jeecg-boot
 * @Date:   2020-01-13
 * @Version: V1.0
 */
public interface PjsxKhpjsxbMapper extends BaseMapper<PjsxKhpjsxb> {
    public void extractPjsx(@Param("khlx") String khlx,@Param("tjyf") String tjyf);
}
