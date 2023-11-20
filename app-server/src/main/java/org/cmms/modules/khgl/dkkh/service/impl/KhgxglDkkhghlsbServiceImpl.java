package org.cmms.modules.khgl.dkkh.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khgl.dkkh.entity.KhgxglDkkhghlsb;
import org.cmms.modules.khgl.dkkh.mapper.KhgxglDkkhghlsbMapper;
import org.cmms.modules.khgl.dkkh.service.IKhgxglDkkhghlsbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 贷款客户管户历史表
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class KhgxglDkkhghlsbServiceImpl extends ServiceImpl<KhgxglDkkhghlsbMapper, KhgxglDkkhghlsb> implements IKhgxglDkkhghlsbService {

    @Override
    public List<String> getGhBsByHth(String hth,Integer ghlx) {
        return baseMapper.getYgghByHthAndGhlx(hth,ghlx);
    }

    @Override
    public String getBsrByHth(String hth) {
        return baseMapper.getBsrByHth(hth);
    }

    @Override
    public String getGhlxByHth(String hth, int ghlx) {
        return baseMapper.getGhlxByHth(hth, ghlx);
    }
}
