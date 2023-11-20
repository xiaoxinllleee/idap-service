package org.cmms.modules.ckjkpt.util.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Component;

@DS("ckjkpt")//ckjkpt
public interface CkjkptCallMapper {

    /**
     * 考核辅助-员工管理账户明细
     */
    void  pYgglzhmx(String tjyf);

    /**
     * ckjkpt_csgl 存款监控平台参数值拿取
     * @param csz
     * @return
     */
    String getCSZ(String csz);
}
