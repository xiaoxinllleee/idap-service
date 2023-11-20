package org.cmms.modules.ckjkpt.jcyj.khckrpjc.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ckjkpt.jcyj.khckrpjc.entity.CkjkptKhckrpjc;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptKhckrpjcService extends IService<CkjkptKhckrpjc> {
    public void extract(String tjyf);

    public  String getlvBytsM(String date);

    public  String getlvBytsY(String date);

    public  String getlvSytsM(String date);

    public  String getlvSytsY(String date);
}
