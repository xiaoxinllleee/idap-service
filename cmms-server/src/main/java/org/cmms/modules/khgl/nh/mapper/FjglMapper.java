package org.cmms.modules.khgl.nh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-11-30
 * @Version: V1.0
 */
@Component
public interface FjglMapper extends BaseMapper<Fjgl> {

    public boolean deleteByMainId(String zjhm);

    public List<Fjgl> selectByMainId(String zjhm);

    public boolean deleteImg(String zjhm,String zllx);
}
