package org.cmms.modules.rwzx.rwcj.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.rwzx.rwcj.entity.*;

/**
 * @Description: 任务创建
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
public interface TaskCreateMapper extends BaseMapper<TaskCreate> {

    List<Nhxq> getTaskList(TaskCreateQuery taskCreateQuery);
    Page<Nhxq> getPageTaskList(Page page,@Param("taskCreateQuery") TaskCreateQuery taskCreateQuery);
    int insertNhxxInfo(@Param("taskCreateQuery")TaskCreateQuery taskCreateQuery);
    int insertZzrwInfo(@Param("taskCreateQuery")TaskCreateQuery taskCreateQuery);
    int insertDklshInfo(@Param("dklshjTaskCreateQuery") DklshjTaskCreateQuery dklshjTaskCreateQuery);
    List<UniDataPicker> getYxPicker();


    void initRwsh(@Param("rwid") String rwid);

    void initRwpf(@Param("rwid") String rwid,@Param("yggh") String yggh);

    void initRwpfLs(@Param("rwid") String rwid);
}
