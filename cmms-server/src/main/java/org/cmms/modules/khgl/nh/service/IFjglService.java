package org.cmms.modules.khgl.nh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.nh.entity.Fjgl;

import java.util.List;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-11-30
 * @Version: V1.0
 */
public interface IFjglService extends IService<Fjgl> {

    public List<Fjgl> selectByMainId(String zjhm);

    public boolean deleteImg(String zjhm,String zllx);
}
