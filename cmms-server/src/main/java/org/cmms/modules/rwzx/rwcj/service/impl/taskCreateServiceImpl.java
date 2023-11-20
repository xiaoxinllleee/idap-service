package org.cmms.modules.rwzx.rwcj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.rwzx.rwcj.entity.*;
import org.cmms.modules.rwzx.rwcj.mapper.TaskCreateMapper;
import org.cmms.modules.rwzx.rwcj.service.ItaskCreateService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 任务创建
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
@Service
public class taskCreateServiceImpl extends ServiceImpl<TaskCreateMapper, TaskCreate> implements ItaskCreateService {

    @Override
    public Page<Nhxq> getPageTaskList(Page page, TaskCreateQuery taskCreateQuery) {
        return baseMapper.getPageTaskList(page, taskCreateQuery);
    }

    @Override
    public int insertNhxxInfo(TaskCreateQuery taskCreateQuery) {
        return baseMapper.insertNhxxInfo(taskCreateQuery);
    }
    @Override
    public int insertZzrwInfo(TaskCreateQuery taskCreateQuery) {
        return baseMapper.insertZzrwInfo(taskCreateQuery);
    }
    @Override
    public int insertDklshInfo(DklshjTaskCreateQuery dklshjTaskCreateQuery) {
        return baseMapper.insertDklshInfo(dklshjTaskCreateQuery);
    }
    @Override
    public List<UniDataPicker> getYxPicker() {
        return baseMapper.getYxPicker();
    }

    @Override
    public void initRwsh(String rwid) {
        baseMapper.initRwsh(rwid);
    }

    @Override
    public void initRwpf(String rwid, String yggh) {
        baseMapper.initRwpf(rwid,yggh);
    }

    @Override
    public void initRwpfLs(String rwid) {
        baseMapper.initRwpfLs(rwid);
    }
}
