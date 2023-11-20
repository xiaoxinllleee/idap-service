package org.cmms.modules.khgl.grkhgl.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.saxon.expr.Component;
import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxx;
import org.cmms.modules.khgl.grkhgl.entity.ModelCalculationVO;
import org.cmms.modules.khgl.grkhgl.mapper.CamsZcsxGrpjsxxxMapper;
import org.cmms.modules.khgl.grkhgl.service.ICamsZcsxGrpjsxxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.util.FileUtils;
import org.cmms.modules.word.entity.CamsZcsxWordinfo;
import org.cmms.modules.word.mapper.CamsZcsxWordinfoMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 个人客户评级授信信息
 * @Author: jeecg-boot
 * @Date:   2020-07-06
 * @Version: V1.0
 */
@Service
@Slf4j
public class CamsZcsxGrpjsxxxServiceImpl extends ServiceImpl<CamsZcsxGrpjsxxxMapper, CamsZcsxGrpjsxxx> implements ICamsZcsxGrpjsxxxService {
    @Value(value = "${common.path.upload}")
    private String uploadpath;
    @Autowired
    CamsZcsxGrpjsxxxMapper camsZcsxGrpjsxxxMapper;

    @Autowired
    CamsZcsxWordinfoMapper camsZcsxWordinfoMapper;
    @Autowired
    INhxqService nhxqService;

    @Override
    public CamsZcsxGrpjsxxx selectByMainId(String hhbm){
        return camsZcsxGrpjsxxxMapper.selectByMainId(hhbm);
    }

    @Override
    public void getYwgywxx(String hhbm) {
        baseMapper.getYwgywxx(hhbm);
    }

    @Override
    public String checkFile(String zjhm, String hhbm,String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ZJHM",zjhm);
        queryWrapper.eq("HHBM",hhbm);
        queryWrapper.orderByDesc("UPDATE_TIME");
        List<CamsZcsxWordinfo> list = camsZcsxWordinfoMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(list)){
            CamsZcsxWordinfo camsZcsxWordinfo = list.get(0);
            if ("pmk".equals(camsZcsxWordinfo.getWordType())){
                if (FileUtils.fileIsHave(uploadpath+"/bmkInfo/"+name+"-"+hhbm+"bmkInfo.docx"))
                    return "bmkInfo/"+name+"-"+hhbm+"bmkInfo.docx";
            }else {
                if (FileUtils.fileIsHave(uploadpath+"/nhxe/"+name+"-"+hhbm+"nhxe.docx"))
                    return "nhxe/"+name+"-"+hhbm+"nhxe.docx";
            }
        }else {
            return "未填写 小额农贷或者便民卡 信息,不能 预览或者下载 文件";
        }
        return "未填写 小额农贷或者便民卡 信息,不能预览或者下载 文件";
    }

    @Override
    public String checkHtFile(String zjhm, String hhbm, String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ZJHM",zjhm);
        queryWrapper.eq("HHBM",hhbm);
        queryWrapper.orderByDesc("UPDATE_TIME");
        List<CamsZcsxWordinfo> list = camsZcsxWordinfoMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(list)){
            CamsZcsxWordinfo camsZcsxWordinfo = list.get(0);
            if ("pmk".equals(camsZcsxWordinfo.getWordType())){
                if (FileUtils.fileIsHave(uploadpath+"/bmkht/"+name+"-"+hhbm+"bmkht.docx"))
                    return "bmkht/"+name+"-"+hhbm+"bmkht.docx";
            }else {
                if (FileUtils.fileIsHave(uploadpath+"/nhxeht/"+name+"-"+hhbm+"nhxeht.docx"))
                    return "nhxeht/"+name+"-"+hhbm+"nhxeht.docx";
            }
        }else {
            return "未填写 小额农贷或者便民卡 信息,不能 预览或者下载合同 文件";
        }
        return "未填写 小额农贷或者便民卡 信息,不能预览或者下载合同 文件";
    }


    public final static String PDDJ_A ="A";
    public final static String PDDJ_B ="B";
    public final static String PDDJ_C ="C";
    public final static String PDDJ_D ="D";
    public final static String PDDJ_E ="E";


    @Override
    public ModelCalculationVO calModel(@NotNull CamsZcsxGrpjsxxx camsZcsxGrpjsxxx) {
        StringBuffer stringBuffer = new StringBuffer();
        ModelCalculationVO modelCalculationVO = new ModelCalculationVO();
        String pddj = PDDJ_E;
        BigDecimal sxed = new BigDecimal(0);

        //判断逻辑从E级往A级走
        int hznl = 0;
        String zjhm = null;
        //家庭是否有低保户
        boolean sfdbh = false;
        boolean sfbldkkh = false;
        //家庭成员是否有18-60周岁的人
        //boolean ldl = true;
        //家庭成员社会声誉
        boolean shsy = false;
        boolean five = false;
//        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getZjhm())){
//            zjhm = camsZcsxGrpjsxxx.getZjhm();
//            if (IdcardUtil.isValidCard(zjhm)){
//                nl = IdcardUtil.getAgeByIdCard(zjhm);
//            }
//        }
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getHhbm())){
            LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Nhxq::getHhbm,camsZcsxGrpjsxxx.getHhbm());
            List<Nhxq> list = nhxqService.list(lambdaQueryWrapper);
            for (int i = 0; i < list.size(); i++) {
                Nhxq nhxq = list.get(i);
                String name = null;
                if (StringUtils.isNotBlank(nhxq.getKhmc())){
                    name = nhxq.getKhmc();
                }
                if (StringUtils.isNotBlank(nhxq.getSfdbh())){
                    if ("1".equals(nhxq.getSfdbh())){
                        sfdbh = true;
                        stringBuffer.append(" 家庭成员 "+name+" 是五保户。");
                    }
                }
                if (StringUtils.isNotBlank(nhxq.getSfbldkh())){
                    if ("1".equals(nhxq.getSfbldkh())){
                        sfbldkkh = true;
                        stringBuffer.append(" 家庭成员 "+name+" 是不良贷款客户。 ");
                    }
                }
                if (StringUtils.isNotBlank(nhxq.getZjhm()) && StringUtils.isNotBlank(nhxq.getYhzgx()) && "1".equals(nhxq.getYhzgx())){
                    if (IdcardUtil.isValidCard(nhxq.getZjhm())){
                        int ageByIdCard = IdcardUtil.getAgeByIdCard(nhxq.getZjhm());
//                        if (ageByIdCard >=18 && ageByIdCard <= 60){
//                        }
                        hznl = ageByIdCard;
                    }
                }
            }
        }

        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsyBlsh())){
            if ("1".equals(camsZcsxGrpjsxxx.getShsyBlsh())){
                shsy = true;
                stringBuffer.append("家庭成员是否有不良嗜好(黄赌毒、酗酒、偷盗、传销、沉迷于其他不良活动)；");
            }
        }
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfqk())){
            if ("3".equals(camsZcsxGrpjsxxx.getShsySfqk())){
                shsy = true;
                stringBuffer.append("家庭成员不勤快；");
            }
        }
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfygld())){
            if ("1".equals(camsZcsxGrpjsxxx.getShsySfygld())){
                shsy = true;
                stringBuffer.append("家庭成员有民间高息贷款;");
            }
        }
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfdjns())){
            if (!"3".equals(camsZcsxGrpjsxxx.getShsySfdjns())){
                shsy = true;
                stringBuffer.append("家庭成员有打架、闹事等不良行为;");
            }
        }
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfxsfz())){
            if ("1".equals(camsZcsxGrpjsxxx.getShsySfxsfz())){
                shsy = true;
                stringBuffer.append("家庭成员有刑事犯罪记录;");
            }
        }
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfss())){
            if (!"2".equals(camsZcsxGrpjsxxx.getShsySfss())){
                shsy = true;
                stringBuffer.append("家庭成员涉诉;");
            }
        }
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySflqbz()) || StringUtils.isNotBlank(camsZcsxGrpjsxxx.getQtbzqk())){
            shsy = true;
            stringBuffer.append(" 家庭成员领取补助; ");
        }
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getSfjdlkpkh())){
            if ("1".equals(camsZcsxGrpjsxxx.getSfjdlkpkh())){
                shsy = true;
                stringBuffer.append(" 家庭成员有贫困户; ");
            }
        }

        int pjInt =  1;
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getDhzpjPxpj())){
            Integer integer = Integer.valueOf(camsZcsxGrpjsxxx.getDhzpjPxpj());
            if (integer > pjInt)
                pjInt = integer;
        }
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getDhzpjXypj())){
            Integer integer = Integer.valueOf(camsZcsxGrpjsxxx.getDhzpjXypj());
            if (integer > pjInt)
                pjInt = integer;
        }

        //第5项
        if (hznl >=18 && hznl <= 60){
            five = true;
        }

        modelCalculationVO.setXtsxed(0);
        modelCalculationVO.setXtpddj(PDDJ_E);

        //先判断E
        if (sfdbh || sfbldkkh || shsy || !five){
            modelCalculationVO.setXtsxed(0);
            modelCalculationVO.setXtpddj(PDDJ_E);
            modelCalculationVO.setMxjsyy(stringBuffer.toString());
            return modelCalculationVO;
        }

        BigDecimal jtjzc = new BigDecimal(0);
        BigDecimal jtjsr = new BigDecimal(0);
        if (camsZcsxGrpjsxxx.getJtjzc() != null)
            jtjzc = camsZcsxGrpjsxxx.getJtjzc();
        if (camsZcsxGrpjsxxx.getJtjsr() != null)
            jtjsr = camsZcsxGrpjsxxx.getJtjsr();

        if (jtjzc.compareTo(new BigDecimal(100)) > 0 && jtjsr.compareTo(new BigDecimal(20))> 0){
            //todo 校验是否在本行开户
            modelCalculationVO.setXtsxed(30);
            modelCalculationVO.setXtpddj(PDDJ_A);
            modelCalculationVO.setMxjsyy(" 家庭净资产100万以上，家庭年纯收入在20万以上，符合本行评级授信条件；");
            if (pjInt > 1){
                String getmxdj = getmxdj(pjInt);
                int geted = geted(getmxdj);
                modelCalculationVO.setXtsxed(geted);
                modelCalculationVO.setXtpddj(getmxdj);
                modelCalculationVO.setMxjsyy(" 家庭净资产100万以上，家庭年纯收入在20万以上，但户主评价不符，进行了降级；");
            }
            return modelCalculationVO;
        }

        if (jtjzc.compareTo(new BigDecimal(60)) > 0 && jtjsr.compareTo(new BigDecimal(10))> 0 ){
            //todo 校验是否在本行开户
            modelCalculationVO.setXtsxed(20);
            modelCalculationVO.setXtpddj(PDDJ_B);
            modelCalculationVO.setMxjsyy(" 家庭净资产60万以上，家庭年纯收入在10万以上，符合本行评级授信条件； ");
            if (pjInt > 1){
                String getmxdj = getmxdj(pjInt);
                int geted = geted(getmxdj);
                modelCalculationVO.setXtsxed(geted);
                modelCalculationVO.setXtpddj(getmxdj);
                modelCalculationVO.setMxjsyy(" 家庭净资产60万以上，家庭年纯收入在10万以上，但户主评价不符，进行了降级；");
            }
            return modelCalculationVO;
        }


        if (jtjzc.compareTo(new BigDecimal(30)) > 0 && jtjsr.compareTo(new BigDecimal(5))> 0){
            //todo 校验是否在本行开户
            modelCalculationVO.setXtsxed(10);
            modelCalculationVO.setXtpddj(PDDJ_C);
            modelCalculationVO.setMxjsyy(" 家庭净资产30万以上，家庭年纯收入在5万以上，符合本行评级授信条件； ");
            if (pjInt > 2){
                String getmxdj = getmxdj(pjInt);
                int geted = geted(getmxdj);
                modelCalculationVO.setXtsxed(geted);
                modelCalculationVO.setXtpddj(getmxdj);
                modelCalculationVO.setMxjsyy(" 家庭净资产30万以上，家庭年纯收入在5万以上，但户主评价不符，进行了降级；");
            }
            return modelCalculationVO;
        }

        if (jtjzc.compareTo(new BigDecimal(10)) > 0 && jtjsr.compareTo(new BigDecimal(2))> 0 ){
            //todo 校验是否在本行开户
            modelCalculationVO.setXtsxed(5);
            modelCalculationVO.setXtpddj(PDDJ_D);
            modelCalculationVO.setMxjsyy(" 家庭净资产10万以上，家庭年纯收入在2万以上，符合本行评级授信条件； ");
            if (pjInt > 3){
                String getmxdj = getmxdj(pjInt);
                int geted = geted(getmxdj);
                modelCalculationVO.setXtsxed(geted);
                modelCalculationVO.setXtpddj(getmxdj);
                modelCalculationVO.setMxjsyy(" 家庭净资产10万以上，家庭年纯收入在2万以上，但户主评价不符，进行了降级；");
            }
            return modelCalculationVO;
        }

        modelCalculationVO.setMxjsyy(" 采集情况不满足授信条件； ");
        return modelCalculationVO;
    }

    public String getmxdj(int dj){
        if (dj == 2)
            return PDDJ_C;
        if (dj == 3)
            return PDDJ_D;
        if (dj == 4)
            return PDDJ_E;
        return PDDJ_E;
    }

    public int geted(String dj){
        if (PDDJ_A.equals(dj))
            return 30;
        if (PDDJ_B.equals(dj))
            return 20;
        if (PDDJ_C.equals(dj))
            return 10;
        if (PDDJ_D.equals(dj))
            return 5;
        return 0;
    }

    @Override
    public CamsZcsxGrpjsxxx getByZjhm(String zjhm) {
        LambdaQueryWrapper<CamsZcsxGrpjsxxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CamsZcsxGrpjsxxx::getZjhm,zjhm);
        List<CamsZcsxGrpjsxxx> camsZcsxGrpjsxxxes = baseMapper.selectList(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(camsZcsxGrpjsxxxes))
            return camsZcsxGrpjsxxxes.get(0);
        return null;
    }
}
