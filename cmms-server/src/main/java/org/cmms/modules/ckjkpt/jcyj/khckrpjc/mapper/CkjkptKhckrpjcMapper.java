package org.cmms.modules.ckjkpt.jcyj.khckrpjc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ckjkpt.jcyj.khckrpjc.entity.CkjkptKhckrpjc;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-09
 * @Version: V1.0
 */
public interface CkjkptKhckrpjcMapper extends BaseMapper<CkjkptKhckrpjc> {
    public void extract(String tjyf);

    public  String getlvBytsM(String date);

    public  String getlvBytsY(String date);

    public  String getlvSytsM(String date);

    public  String getlvSytsY(String date);
}
