package org.cmms.modules.khgl.ckkh.service.impl;

import org.cmms.modules.khgl.ckkh.entity.KhgxglCkzhghxx;
import org.cmms.modules.khgl.ckkh.entity.ZhlbVO;
import org.cmms.modules.khgl.ckkh.mapper.KhgxglCkzhghxxMapper;
import org.cmms.modules.khgl.ckkh.service.IKhgxglCkzhghxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 存款账号管户信息
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
@Service
public class KhgxglCkzhghxxServiceImpl extends ServiceImpl<KhgxglCkzhghxxMapper, KhgxglCkzhghxx> implements IKhgxglCkzhghxxService {

    @Override
    public List<ZhlbVO> getCkzhList(String tzr) {
        return baseMapper.getCkzhList(tzr);
    }
}
