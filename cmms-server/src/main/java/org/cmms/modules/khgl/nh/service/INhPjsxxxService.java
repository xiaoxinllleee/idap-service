package org.cmms.modules.khgl.nh.service;

import org.cmms.modules.khgl.nh.entity.NhPjsxxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 农户评级授信信息表
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
public interface INhPjsxxxService extends IService<NhPjsxxx> {

    public List<NhPjsxxx> selectByMainId(String zjhm);


}
