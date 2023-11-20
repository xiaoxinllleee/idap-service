package org.cmms.modules.report.sgtzgl.wjplsc.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.report.sgtzgl.wjplsc.entity.bulkimport;

import java.util.Date;

/**
 * @Description: ddw
 * @Author: jeecg-boot
 * @Date:   2022-10-26
 * @Version: V1.0
 */
public interface IbulkimportService extends IService<bulkimport> {
    void deleteTableDataByTable(String tableCode,String ds);
    void deleteTableDataByDate(String daysName, String tableCode, Date date,String ds);
    void deleteTableDataByString(String daysName, String tableCode, String date,String ds);

    void insertBulKDate(bulkimport bulkimpor);
}
