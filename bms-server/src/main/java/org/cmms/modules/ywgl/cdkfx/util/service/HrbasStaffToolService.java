package org.cmms.modules.ywgl.cdkfx.util.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.system.vo.TableComments;

import java.util.List;

/**
 * @Description: 
 * @Author: jeecg-boot
 * @Date:   2021-06-21
 * @Version: V1.0
 */
@DS("cdkyw")
public interface HrbasStaffToolService extends IService<TableComments> {
    /**
     * 根据名字获取客户经理标识
     * @param ygxm
     * @return
     */
    String getCustidByName(String ygxm);
    /**
     * 根据名字获取员工工号
     * @param ygxm
     * @return
     */
    String getYgghByName(String ygxm);
}
