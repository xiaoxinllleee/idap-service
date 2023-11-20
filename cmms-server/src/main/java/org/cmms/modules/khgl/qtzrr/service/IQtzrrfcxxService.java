package org.cmms.modules.khgl.qtzrr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.qtzrr.entity.Qtzrrfcxx;

import java.util.List;

/**
 * @Description: 其他自然人房产信息
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
public interface IQtzrrfcxxService extends IService<Qtzrrfcxx> {

    public List<Qtzrrfcxx> selectByMainId(String zjhm);

    public Qtzrrfcxx selectFcjz(String hhbm);


}
