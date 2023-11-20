package org.cmms.modules.pad.pyxx.service.impl;

import org.cmms.modules.pad.pyxx.entity.Nhbkbpyfsxx;
import org.cmms.modules.pad.pyxx.mapper.NhbkbpyfsxxMapper;
import org.cmms.modules.pad.pyxx.service.INhbkbpyfsxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 背靠背附属信息
 * @Author: jeecg-boot
 * @Date:   2020-10-20
 * @Version: V1.0
 */
@Service
public class NhbkbpyfsxxServiceImpl extends ServiceImpl<NhbkbpyfsxxMapper, Nhbkbpyfsxx> implements INhbkbpyfsxxService {

    @Override
    //房产价值
    public int fcjz(String fcjz){
        if (fcjz.equals("1") || fcjz.equals("2") ){
            return  100000;
        }else if (fcjz.equals("3")) {
            return 300000;
        }
        return 0;
    }

    @Override
    //大宗耐用消费品情况
    public int dznyxfpqk(String dznyxfpqk) {
        if (dznyxfpqk.equals("1") || dznyxfpqk.equals("2")) {
            return 3000;
        } else if (dznyxfpqk.equals("3")) {
            return 5000;
        } else if (dznyxfpqk.equals("4")) {
            return 8000;
        }
        return 0;
    }

    @Override
    //家庭纯收入情况
    public int jtcsrqk(String jtcsrqk){
        if (jtcsrqk.equals("1") || jtcsrqk.equals("4")) {
            return 10000;
        } else if (jtcsrqk.equals("6")) {
            return 20000;
        } else if (jtcsrqk.equals("8")) {
            return 40000;
        }
        return 0;
    }

    @Override
    //农机具情况
    public int njjqk(String njjqk){
        if (njjqk.equals("1") || njjqk.equals("2")) {
            return 10000;
        } else if (njjqk.equals("3")) {
            return 20000;
        }
        return 0;
    }
    @Override
    //交通运输工具情况
    public int jtysgjqk(String jtysgjqk){
        if (jtysgjqk.equals("1") || jtysgjqk.equals("2")) {
            return 10000;
        } else if (jtysgjqk.equals("3")) {
            return 20000;
        }
        return 0;
    }
}
