package org.cmms.modules.report.sgtzgl.wjplsc.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.report.sgtzgl.wjplsc.entity.bulkimport;
import org.cmms.modules.report.sgtzgl.wjplsc.mapper.bulkimportMapper;
import org.cmms.modules.report.sgtzgl.wjplsc.service.IbulkimportService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: ddw
 * @Author: jeecg-boot
 * @Date:   2022-10-26
 * @Version: V1.0
 */
@Service
public class bulkimportServiceImpl extends ServiceImpl<bulkimportMapper, bulkimport> implements IbulkimportService {

    @Override
    @DS("#ds")
    public void deleteTableDataByTable(String tableName ,String ds) {

      this.baseMapper.deleteTableDateByTable(tableName);
    }

    @Override
    @DS("#ds")
    public void deleteTableDataByDate(String daysName, String tableName, Date date,String ds) {
           this.baseMapper.deleteTableDataByDate(daysName,tableName,date);
    }
    @Override
    @DS("#ds")
    public void deleteTableDataByString(String daysName, String tableName, String date,String ds) {
        this.baseMapper.deleteTableDataByString(daysName,tableName,date);
    }
    @Override
    public void insertBulKDate(bulkimport bulkimpor) {
        this.baseMapper.insert(bulkimpor);
    }
}
