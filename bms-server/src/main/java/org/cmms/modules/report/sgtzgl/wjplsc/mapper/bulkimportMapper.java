package org.cmms.modules.report.sgtzgl.wjplsc.mapper;

import java.util.Date;
import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.report.sgtzgl.wjplsc.entity.bulkimport;

/**
 * @Description: ddw
 * @Author: jeecg-boot
 * @Date:   2022-10-26
 * @Version: V1.0
 */
public interface bulkimportMapper extends BaseMapper<bulkimport> {

    void deleteTableDateByTable(@Param("tableCode") String tableCode);

    void deleteTableDataByDate(@Param("daysName")String daysName, @Param("tableCode")String tableCode, @Param("days") Date date);

    void deleteTableDataByString(@Param("daysName")String daysName, @Param("tableCode")String tableCode, @Param("days") String date);

}
