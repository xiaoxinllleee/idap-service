package org.cmms.modules.khdj.khdjpd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khdj.khdjpd.entity.Khdjpd;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
public interface IKhdjpdService extends IService<Khdjpd> {

    public void initData(Map<String, String> sql);

    void callAutoMission();
}
