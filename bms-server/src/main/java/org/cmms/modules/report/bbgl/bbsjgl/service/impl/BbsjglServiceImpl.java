package org.cmms.modules.report.bbgl.bbsjgl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.cmms.common.util.ExcelUtils;
import org.cmms.modules.report.bbgl.bbmbgl.entity.Bbmbgl;
import org.cmms.modules.report.bbgl.bbmbgl.mapper.BbmbglMapper;
import org.cmms.modules.report.bbgl.bbsjgl.entity.Bbsjgl;
import org.cmms.modules.report.bbgl.bbsjgl.mapper.BbsjglMapper;
import org.cmms.modules.report.bbgl.bbsjgl.service.IBbsjglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Description: 报表数据管理
 * @Author: jeecg-boot
 * @Date:   2022-03-31
 * @Version: V1.0
 */
@Service
public class BbsjglServiceImpl extends ServiceImpl<BbsjglMapper, Bbsjgl> implements IBbsjglService {
    @Autowired
    private BbmbglMapper bbmbglMapper;
    @Value(value = "${common.path.upload}")
    private String uploadpath;

    public void fillData(String bbbh) {
        QueryWrapper<Bbmbgl> bbmbglQueryWrapper = new QueryWrapper<>();
        bbmbglQueryWrapper.eq("bbbh", bbbh);
        Bbmbgl bbmbgl = bbmbglMapper.selectOne(bbmbglQueryWrapper);
        int rnum = bbmbgl.getRnum();
        int cnum = bbmbgl.getCnum();
        String filePath = uploadpath + File.separator + bbmbgl.getBblj();
        try {
            File file = new File(filePath);
            InputStream inputStream = new FileInputStream(file);
            Sheet sheet = ExcelUtils.getExcelSheet(inputStream, file.getName());
            //循环模板文件所有的cell
            int lastRowNum = sheet.getLastRowNum();
            if (rnum > 0) {
                lastRowNum = rnum - 1;
            }
            for (int i = 0; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                if (cnum > 0) {
                    lastCellNum = cnum - 1;
                }
                for (int j = 0; j <= lastCellNum; j++) {
                    Cell cell = row.getCell(j);

                }
            }
            //判断是否是取数规则
            //解析取数规则
            //将文件保存到路径
        } catch (Throwable tx) {

        }

    }

}
