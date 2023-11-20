package org.cmms.modules.khgl.nh.service;

import org.cmms.modules.khgl.nh.entity.Nhfcxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 农户房产信息
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
public interface INhfcxxService extends IService<Nhfcxx> {

    public List<Nhfcxx> selectByMainId( String zjhm);

    public Nhfcxx selectFcjz(String hhbm);


}
