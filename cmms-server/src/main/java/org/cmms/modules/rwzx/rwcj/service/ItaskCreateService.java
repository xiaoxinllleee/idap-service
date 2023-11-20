package org.cmms.modules.rwzx.rwcj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.rwzx.rwcj.entity.*;

import java.util.List;

/**
 * @Description: 任务创建
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
public interface ItaskCreateService extends IService<TaskCreate> {

    Page<Nhxq> getPageTaskList(Page page, TaskCreateQuery taskCreateQuery);
    int insertNhxxInfo(TaskCreateQuery taskCreateQuery);
    int insertZzrwInfo(TaskCreateQuery taskCreateQuery);
    int insertDklshInfo(DklshjTaskCreateQuery dklshjTaskCreateQuery);

    List<UniDataPicker> getYxPicker();

    void initRwsh( String rwid);

    void initRwpf(String rwid, String yggh);

    void initRwpfLs(String rwid);
}
