package org.cmms.modules.ckjkpt.jcyj.khckyejc.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ckjkpt.jcyj.khckyejc.entity.CkjkptKhyeckjc;

/**
 * @Description: 客户余额存款监测查询
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptKhyeckjcService extends IService<CkjkptKhyeckjc> {
    public void extract(String tjyf);

    public  String getlvBytsM(String date);

    public  String getlvBytsY(String date);

    public  String getlvSytsM(String date);

    public  String getlvSytsY(String date);
}
