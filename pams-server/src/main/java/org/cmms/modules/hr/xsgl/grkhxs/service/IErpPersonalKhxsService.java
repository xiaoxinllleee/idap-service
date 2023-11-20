package org.cmms.modules.hr.xsgl.grkhxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.hr.xsgl.grkhxs.entity.ErpPersonalKhxs;

import java.util.Date;

/**
 * @Description: 个人考核系数
 * @Author: jeecg-boot
 * @Date:   2021-10-26
 * @Version: V1.0
 */
public interface IErpPersonalKhxsService extends IService<ErpPersonalKhxs> {

    /**
     * 计算员工在一段时间内 是否已经设置了参数  返回true就是可以新增 返回false就是时间段有冲突
     * */
    Boolean isHaveByDate(String yggh, Date kssj,Date jssj);
}
