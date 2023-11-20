package org.cmms.modules.khdj.khdjpd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khdj.khdjpd.entity.Khdjpd;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
public interface KhdjpdMapper extends BaseMapper<Khdjpd> {

    public void initData(Map<String, String> sql);

    public void callAutoMission();

}
