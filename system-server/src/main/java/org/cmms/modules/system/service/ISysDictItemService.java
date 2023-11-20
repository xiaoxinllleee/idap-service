package org.cmms.modules.system.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.entity.SysDictItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @Author zhangweijian
 * @since 2018-12-28
 */
public interface ISysDictItemService extends IService<SysDictItem> {
    public List<SysDictItem> selectItemsByMainId(String mainId);

    /**
     * 根据担保信息设定条件(rate_dbxxgl.dbwms)获取对应分数（sys_dict_item.description）
     * @param dictcode 数据字典code
     * @param itemvalue 数据字典值
     * @return
     */
    String getCollateralScore(@Param("dictcode") String dictcode, @Param("itemvalue") String itemvalue);
}
