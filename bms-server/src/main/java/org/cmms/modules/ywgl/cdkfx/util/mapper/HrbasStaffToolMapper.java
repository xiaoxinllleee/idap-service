package org.cmms.modules.ywgl.cdkfx.util.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.system.vo.TableComments;

import java.util.List;

public interface HrbasStaffToolMapper extends BaseMapper<TableComments> {
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
