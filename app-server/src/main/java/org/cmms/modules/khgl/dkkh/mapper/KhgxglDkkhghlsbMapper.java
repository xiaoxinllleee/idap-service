package org.cmms.modules.khgl.dkkh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.dkkh.entity.KhgxglDkkhghlsb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款客户管户历史表
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
public interface KhgxglDkkhghlsbMapper extends BaseMapper<KhgxglDkkhghlsb> {

    List<String> getGhBsByHth(String hth);
    List<String> getYgghByHthAndGhlx(@Param("hth") String hth,@Param("ghlx") int ghlx);

    String getBsrByHth(String hth);
    String getGhlxByHth(@Param("hth") String hth,@Param("ghlx") int ghlx);
}
