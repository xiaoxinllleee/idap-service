package org.cmms.modules.word.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.K;
import org.cmms.common.constant.WordConstant;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.cmms.modules.khgl.grkhgl.entity.VKhglGrkhgl;
import org.cmms.modules.khgl.grkhgl.mapper.KhhmcxxMapper;
import org.cmms.modules.khgl.grkhgl.mapper.VKhglGrkhglMapper;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.mapper.FjglMapper;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDictItem;
import org.cmms.modules.system.mapper.HrBasOrganizationMapper;
import org.cmms.modules.system.mapper.SysDictItemMapper;
import org.cmms.modules.util.DocxUtil;
import org.cmms.modules.util.DocxUtil2;
import org.cmms.modules.word.entity.*;
import org.cmms.modules.word.mapper.CamsZcsxFxpjscMapper;
import org.cmms.modules.word.mapper.CamsZcsxWordinfoMapper;
import org.cmms.modules.word.service.IWordService;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import org.cmms.modules.xdgl.grdkgl.mapper.*;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grpjsxspjl;
import org.cmms.modules.xdgl.jtspcy.entity.Jtspcy;
import org.cmms.modules.xdgl.jtspcy.mapper.JtspcyMapper;
import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import org.cmms.modules.yxdygl.ejyxdygl.mapper.EjyxdyglMapper;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.mapper.SjyxdyglMapper;
import org.cmms.modules.yxdygl.yjyxdygl.entity.Yjyxdygl;
import org.cmms.modules.yxdygl.yjyxdygl.mapper.YjyxdyglMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class IWordServiceImpl implements IWordService {

    private final String prePath = "static" + File.separator + "files" + File.separator;
    String upload = "/grdk/";
    String grdksx = "/grdksx/";
    String xendupload = "/xend/";
    String moban = "/moban/";
    String grdkfj = "/grdkfj/";
    String grdkmt = "/grdkmt/";
    String grdkgt = "/grdkgt/";
    String grdkdydb = "/grdkdydb/";
    String grdkdyzx = "/grdkzx/";
    String jtsp = "/jtsp/";
    @Autowired
    GrdkJtspMapper grdkJtspMapper;

    @Autowired
    KhhmcxxMapper khhmcxxMapper;

    @Autowired
    VKhglGrkhglMapper vKhglGrkhglMapper;
    @Autowired
    HrBasOrganizationMapper hrBasOrganizationMapper;
    @Autowired
    YjyxdyglMapper yjyxdyglMapper;
    @Autowired
    EjyxdyglMapper ejyxdyglMapper;
    @Autowired
    SjyxdyglMapper sjyxdyglMapper;
    @Autowired
    CamsZcsxWordinfoMapper camsZcsxWordinfoMapper;
    @Autowired
    SysDictItemMapper sysDictItemMapper;
    @Autowired
    GrdkglMapper grdkglMapper;
    @Autowired
    CamsZcsxDydbMapper camsZcsxDydbMapper;
    @Autowired
    KhglKhhmcxxGrxdMapper khglKhhmcxxGrxdMapper;
    @Autowired
    IDictValueQuery iDictValueQuery;
    @Autowired
    CamsZcsxYhdkMapper camsZcsxYhdkMapper;
    @Autowired
    FwxxMapper fwxxMapper;
    @Autowired
    ClxxMapper clxxMapper;

    @Autowired
    QtglzcMapper qtglzcMapper;
    @Autowired
    CamsZcsxGrxdcjxxMapper camsZcsxGrxdcjxxMapper;

    @Value(value = "${common.path.upload}")
    private String uploadpath;
    @Autowired
    GrxdzllbMapper grxdzllbMapper;
    @Autowired
    DydbMapper dydbMapper;
    @Autowired
    ZydbMapper zydbMapper;
    @Autowired
    XydbMapper xydbMapper;
    @Autowired
    CamsZcsxFxpjscMapper camsZcsxFxpjscMapper;
    @Autowired
    FjglMapper fjglMapper;
    @Autowired
    JtspcyMapper jtspcyMapper;

    List<SysDictItem> cszy = new ArrayList<>();

    @Override
    public String smallLoanApplicaiton(Grpjsxspjl grkhpjsx) {
        //去在word表中去查
        QueryWrapper nhxe = new QueryWrapper();
        nhxe.eq("ZJHM", grkhpjsx.getZjhm());
        nhxe.eq("HHBM", grkhpjsx.getHhbm());
        nhxe.orderByDesc("UPDATE_TIME");
        CamsZcsxWordinfo camsZcsxWordinfo = new CamsZcsxWordinfo();
        camsZcsxWordinfo.setZjhm(grkhpjsx.getZjhm());
        camsZcsxWordinfo.setHhbm(grkhpjsx.getHhbm());
        List<CamsZcsxWordinfo> list = camsZcsxWordinfoMapper.selectList(nhxe);
        if (list != null && list.size() > 0) {
            camsZcsxWordinfo = list.get(0);
        }
        if (camsZcsxWordinfo != null && StringUtils.isNoneBlank(camsZcsxWordinfo.getWordType()) && camsZcsxWordinfo.getWordType().equals("pmk")) {
            return pmk(camsZcsxWordinfo);
        } else {
            return xend(camsZcsxWordinfo);
        }

    }

    public String pmk(CamsZcsxWordinfo camsZcsxWordinfo) {
        String zjhm = camsZcsxWordinfo.getZjhm();
        String hhbm = camsZcsxWordinfo.getHhbm();
        PmkDTO pmkDTO = new PmkDTO();
        if (camsZcsxWordinfo != null) {
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getWordColumnOne()))
                pmkDTO.setULoan(camsZcsxWordinfo.getWordColumnOne());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getWordColumnTwo()))
                pmkDTO.setLCredit(camsZcsxWordinfo.getWordColumnTwo());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getWordColumnThree()))
                pmkDTO.setLoanTime(camsZcsxWordinfo.getWordColumnThree());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getWordColumnFour()))
                pmkDTO.setPayWay(camsZcsxWordinfo.getWordColumnFour());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getWordColumnFive()))
                pmkDTO.setJxfs(camsZcsxWordinfo.getWordColumnFive());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getZwr()))
                pmkDTO.setSName(camsZcsxWordinfo.getZwr());

            if (StringUtils.isNotBlank(camsZcsxWordinfo.getLxr()))
                pmkDTO.setLxr(camsZcsxWordinfo.getLxr());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getSzjhm()))
                pmkDTO.setSIdn(camsZcsxWordinfo.getSzjhm());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getSphone()))
                pmkDTO.setSPhone(camsZcsxWordinfo.getSphone());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getSzz()))
                pmkDTO.setSZz(camsZcsxWordinfo.getSzz());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getHkbz()))
                pmkDTO.setHkbz(camsZcsxWordinfo.getHkbz());
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ZJHM", zjhm);
        queryWrapper.eq("HHBM", hhbm);
        VKhglGrkhgl vKhglGrkhgl = null;
        List<VKhglGrkhgl> vKhglGrkhglList = vKhglGrkhglMapper.selectList(queryWrapper);
        if (vKhglGrkhglList != null && vKhglGrkhglList.size() > 0)
            vKhglGrkhgl = vKhglGrkhglList.get(0);

        if (vKhglGrkhgl == null)
            return "2";

        if (vKhglGrkhgl != null) {
            //支行名称
            if (StringUtils.isNotBlank(vKhglGrkhgl.getSszh())) {
                HrBasOrganization hrBasOrganization = hrBasOrganizationMapper.queryByZzbz(vKhglGrkhgl.getSszh());
                if (hrBasOrganization != null && StringUtils.isNotBlank(hrBasOrganization.getZzjc()))
                    pmkDTO.setBank(hrBasOrganization.getZzjc());
            }


            if (StringUtils.isNotBlank(vKhglGrkhgl.getKhmc()))
                pmkDTO.setName(vKhglGrkhgl.getKhmc());
            pmkDTO.setIdn(vKhglGrkhgl.getZjhm());
            //家庭电话默认户主的电话 todo
            if (StringUtils.isNotBlank(vKhglGrkhgl.getLxfs())) {
                pmkDTO.setPhone(vKhglGrkhgl.getLxfs());
            }

           /* if (vKhglGrkhgl.getNl() != null)
                pmkDTO.setNl(vKhglGrkhgl.getNl().toString());*/

            if (IdcardUtil.isValidCard(vKhglGrkhgl.getZjhm())){
                pmkDTO.setNl(IdcardUtil.getAgeByIdCard(vKhglGrkhgl.getZjhm())+"");
            }

            if (StringUtils.isNotBlank(vKhglGrkhgl.getZz()))
                pmkDTO.setZz(vKhglGrkhgl.getZz());

            QueryWrapper jtrsq = new QueryWrapper();
            jtrsq.eq("HHBM",jtrsq);
            pmkDTO.setJtrs(khhmcxxMapper.selectCount(jtrsq)+"");

           /* if (vKhglGrkhgl.getJtrs() != null)
                pmkDTO.setJtrs(vKhglGrkhgl.getJtrs().toString());*/
        }

        if (StringUtils.isNotBlank(vKhglGrkhgl.getCszy())) {
            pmkDTO.setZy(iDictValueQuery.getCszyValue(vKhglGrkhgl.getCszy()));
        }


        /*QueryWrapper lxr = new QueryWrapper();
        lxr.eq("HHBM",camsZcsxWordinfo.getHhbm());
        lxr.eq("YHZGX",camsZcsxWordinfo.getYhzgx());
        lxr.eq("ZJHM",camsZcsxWordinfo.getSzjhm());
        Khhmcxx khhmcxx = khhmcxxMapper.selectOne(lxr);
        if (khhmcxx != null){
            pmkDTO.setLxr(khhmcxx.getKhmc());
            pmkDTO.setSIdn(khhmcxx.getZjhm());
            pmkDTO.setSPhone(khhmcxx.getLxfs());
            pmkDTO.setSZz(khhmcxx.getDz());
        }*/
        ClassPathResource classPathResource = new ClassPathResource(prePath + "pmk.doc");
        DocxUtil.mkdirCatalog(uploadpath + "/dkzl/");
        String upload = uploadpath + "/dkzl/" + vKhglGrkhgl.getKhmc()+vKhglGrkhgl.getId()+ "pmk.doc";
        log.info("便民卡模板文件存放位置|{}|", classPathResource.getPath());

        String result = DocxUtil.exportDocToAddr(classPathResource, pmkDTO, upload);
        if (result.equals("fail"))
            return "2";
        return vKhglGrkhgl.getKhmc()+vKhglGrkhgl.getId()+"pmk.doc";
    }

    public String xend(CamsZcsxWordinfo camsZcsxWordinfo) {
        String zjhm = camsZcsxWordinfo.getZjhm();
        String hhbm = camsZcsxWordinfo.getHhbm();
        SmallLoanDTO smallLoanDTO = new SmallLoanDTO();
        if (camsZcsxWordinfo != null) {
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getWordColumnOne()))
                smallLoanDTO.setULoan(camsZcsxWordinfo.getWordColumnOne());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getWordColumnTwo())){
                smallLoanDTO.setLCredit(Convert.digitToChinese(new Double(camsZcsxWordinfo.getWordColumnTwo())).replace("元整",""));
            }
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getWordColumnThree()))
                smallLoanDTO.setLoanTime(camsZcsxWordinfo.getWordColumnThree());
            if (StringUtils.isNotBlank(camsZcsxWordinfo.getWordColumnFour()))
                smallLoanDTO.setPayWay(camsZcsxWordinfo.getWordColumnFour());
        }
        log.info("===开始生成农户小额申请书==");
        //用身份证去查询相关信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ZJHM", zjhm);
        queryWrapper.eq("HHBM", hhbm);
        VKhglGrkhgl vKhglGrkhgl = null;
        List<VKhglGrkhgl> vKhglGrkhglList = vKhglGrkhglMapper.selectList(queryWrapper);
        if (vKhglGrkhglList != null && vKhglGrkhglList.size() > 0)
            vKhglGrkhgl = vKhglGrkhglList.get(0);

        if (vKhglGrkhgl == null)
            return "2";

        if (vKhglGrkhgl != null) {

            //支行名称
            if (StringUtils.isNotBlank(vKhglGrkhgl.getSszh())) {
                HrBasOrganization hrBasOrganization = hrBasOrganizationMapper.queryByZzbz(vKhglGrkhgl.getSszh());
                if (hrBasOrganization != null && StringUtils.isNotBlank(hrBasOrganization.getZzjc()))
                    smallLoanDTO.setBank(hrBasOrganization.getZzjc());
            }

            if (StringUtils.isNotBlank(vKhglGrkhgl.getKhmc()))
                smallLoanDTO.setName(vKhglGrkhgl.getKhmc());
            smallLoanDTO.setIdn(vKhglGrkhgl.getZjhm());
            //家庭电话默认户主的电话 todo
            if (StringUtils.isNotBlank(vKhglGrkhgl.getLxfs())) {
                smallLoanDTO.setFPhone(vKhglGrkhgl.getLxfs());
                smallLoanDTO.setPhone(vKhglGrkhgl.getLxfs());
            }

            //配偶 根据zjhm 去找 与户主关系
            QueryWrapper peiou = new QueryWrapper();
            peiou.eq("HHBM", vKhglGrkhgl.getHhbm());
            peiou.eq("YHZGX", "2");
            List<Khhmcxx> list = khhmcxxMapper.selectList(peiou);
            if (list != null && list.size() > 0) {
                Khhmcxx grkhgl = list.get(0);
                if (grkhgl != null) {
                    if (StringUtils.isNotBlank(grkhgl.getKhmc()))
                        smallLoanDTO.setSName(grkhgl.getKhmc());

                    if (StringUtils.isNotBlank(grkhgl.getZjhm()))
                        smallLoanDTO.setSIdn(grkhgl.getZjhm());

                    if (StringUtils.isNotBlank(grkhgl.getLxfs()))
                        smallLoanDTO.setSPhone(grkhgl.getLxfs());
                }
            }

            //地址
            if (StringUtils.isNotBlank(vKhglGrkhgl.getYjyxdybh())) {
                QueryWrapper yxdy = new QueryWrapper();
                yxdy.eq("DYBH", vKhglGrkhgl.getYjyxdybh());
                Yjyxdygl yjyxdygl = yjyxdyglMapper.selectOne(yxdy);
                if (yjyxdygl != null)
                    smallLoanDTO.setTown(yjyxdygl.getDymc());
            }

            if (StringUtils.isNotBlank(vKhglGrkhgl.getEjyxdybh())) {
                QueryWrapper exdy = new QueryWrapper();
                exdy.eq("DYBH", vKhglGrkhgl.getEjyxdybh());
                Ejyxdygl ejyxdygl = ejyxdyglMapper.selectOne(exdy);
                if (ejyxdygl != null)
                    smallLoanDTO.setVi(ejyxdygl.getDymc());
            }

            if (StringUtils.isNotBlank(vKhglGrkhgl.getSjyxdybh())) {
                QueryWrapper sxdy = new QueryWrapper();
                sxdy.eq("DYBH", vKhglGrkhgl.getSjyxdybh());
                Sjyxdygl sjyxdygl = sjyxdyglMapper.selectOne(sxdy);
                if (sjyxdygl != null)
                    smallLoanDTO.setGp(sjyxdygl.getDymc());
            }


            smallLoanDTO.setLoanMan(vKhglGrkhgl.getKhmc());
        }
        ClassPathResource classPathResource = new ClassPathResource(prePath + "农户小额申请书.doc");
        DocxUtil.mkdirCatalog(uploadpath + "/dkzl/");
        String upload = uploadpath + "/dkzl/" + vKhglGrkhgl.getKhmc()+vKhglGrkhgl.getId() + "nhxe.doc";
        log.info("农户小额申请书模板文件存放位置|{}|", classPathResource.getPath());

        String result = DocxUtil.exportDocToAddr(classPathResource, smallLoanDTO, upload);
        if (result.equals("fail"))
            return "2";
        return vKhglGrkhgl.getKhmc()+vKhglGrkhgl.getId()+"nhxe.doc";
    }

    @Autowired
    GrdkspjlMapper grdkspjlMapper;

    static final String DAFAULT_WORD="      ";

    @Override
    public String grdkWord(String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        Grdkspjl grdkspjl = grdkspjlMapper.selectById(id);
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        GrsxsqbDTO grsxsqbDTO = new GrsxsqbDTO();
        int issc = 0;


        if (grdkspjl != null) {

            //调查报告的拼接值
            String zhen =DAFAULT_WORD;
            String cun =DAFAULT_WORD;
            String yuanyin =DAFAULT_WORD;
            String dk =DAFAULT_WORD;
            String sqje =DAFAULT_WORD;
            String qx =DAFAULT_WORD;
            String age =DAFAULT_WORD;
            String dz =DAFAULT_WORD;
            String mzjhm =DAFAULT_WORD;
            String msjhm =DAFAULT_WORD;
            String mpo ="";
            String mpoage =DAFAULT_WORD;
            String mpozjhm =DAFAULT_WORD;
            String zzc =DAFAULT_WORD;
            String fcdz =DAFAULT_WORD;
            String fcjz =DAFAULT_WORD;
            String qtzc =DAFAULT_WORD;
            String jtgj =DAFAULT_WORD;
            String ldzj =DAFAULT_WORD;
            String zfz =DAFAULT_WORD;
            String jysr =DAFAULT_WORD;
            String sxlr =DAFAULT_WORD;
            String mdyjz =DAFAULT_WORD;
            String mdywz =DAFAULT_WORD;

            //客户经理名称和所属支行
            String khjl = sysUser.getRealname();
            HrBasOrganization hrBasOrganization = hrBasOrganizationMapper.queryByZzbz(sysUser.getOrgCode());

            String zjhm = grdkspjl.getZjhm();
            age = IdcardUtil.getAgeByIdCard(zjhm)+"";
            grsxsqbDTO.setZdcr_(khjl);
            grsxsqbDTO.setBhjbry_(khjl);
            grsxsqbDTO.setKhmc_(grdkspjl.getKhmc());
            grsxsqbDTO.setKhmc(grdkspjl.getKhmc());
            grsxsqbDTO.setCxxm_(grdkspjl.getKhmc());
            grsxsqbDTO.setCxzjhm_(grdkspjl.getZjhm());
            grsxsqbDTO.setSszh_(hrBasOrganization.getZzjc().replace("支行", ""));
            grsxsqbDTO.setSszh(hrBasOrganization.getZzjc());
            grsxsqbDTO.setGtsszh_(hrBasOrganization.getZzjc());
            grsxsqbDTO.setZjhm(zjhm);
            grsxsqbDTO.setMzdcr("调查人："+khjl);

            //todo 开户银行
            BigDecimal xydk = new BigDecimal(0);
            QueryWrapper khyh = new QueryWrapper();
            khyh.eq("ZJHM", zjhm);
            khyh.orderByAsc(WordConstant.UPDATE_TIME);
            List<CamsZcsxYhdk> yhdks = camsZcsxYhdkMapper.selectList(khyh);
            if (yhdks != null && yhdks.size() > 0) {
                int yhdkMax = yhdks.size() > 1 ? 2 : yhdks.size();
                for (int i = 0; i < yhdkMax; i++) {
                    CamsZcsxYhdk camsZcsxYhdk = yhdks.get(i);
                    if (camsZcsxYhdk.getDkje() != null)
                        xydk.add(camsZcsxYhdk.getDkje());
                    if (i == 0) {
                        if (StringUtils.isNotBlank(camsZcsxYhdk.getDkyh()))
                            grsxsqbDTO.setKhyh1(camsZcsxYhdk.getDkyh());
                        if (StringUtils.isNotBlank(camsZcsxYhdk.getDkzh()))
                            grsxsqbDTO.setZh1(camsZcsxYhdk.getDkzh());
                        if (camsZcsxYhdk.getDkje() != null)
                            grsxsqbDTO.setDrye1(camsZcsxYhdk.getDkje().toString());
                    }
                    if (i == 1) {
                        if (StringUtils.isNotBlank(camsZcsxYhdk.getDkyh()))
                            grsxsqbDTO.setKhyh2(camsZcsxYhdk.getDkyh());
                        if (StringUtils.isNotBlank(camsZcsxYhdk.getDkzh()))
                            grsxsqbDTO.setZh2(camsZcsxYhdk.getDkzh());
                        if (camsZcsxYhdk.getDkje() != null)
                            grsxsqbDTO.setDrye2(camsZcsxYhdk.getDkje().toString());
                    }
                }
            }
            grsxsqbDTO.setXydk(xydk.toString());

            //todo 现有贷款
            if (StringUtils.isNotBlank(grdkspjl.getDwdb()))
                grsxsqbDTO.setDwdb(grdkspjl.getDwdb());
            if (StringUtils.isNotBlank(grdkspjl.getDbdx()))
                grsxsqbDTO.setDbdx(grdkspjl.getDbdx());
            if (StringUtils.isNotBlank(grdkspjl.getQtyx()))
                grsxsqbDTO.setQtyx1(grdkspjl.getQtyx());

            //todo 家庭成员
            QueryWrapper jt = new QueryWrapper();
            jt.eq("HHBM", grdkspjl.getHhbm());
            jt.orderByAsc("YHZGX");
            String gtjkrpo = "";
            String sjcode="";
            List<KhglKhhmcxxGrxd> hmcs = khglKhhmcxxGrxdMapper.selectList(jt);
            if (hmcs != null && hmcs.size() > 0) {
                for (int i = 0; i < hmcs.size(); i++) {
                    KhglKhhmcxxGrxd khglKhhmcxxGrxd = hmcs.get(i);
                    if (khglKhhmcxxGrxd.getYhzgx() != null && khglKhhmcxxGrxd.getYhzgx().equals("2")) {
                        grsxsqbDTO.setCxpoValue(khglKhhmcxxGrxd.getKhmc());
                        grsxsqbDTO.setCxpozjhm_(khglKhhmcxxGrxd.getZjhm());
                        mpozjhm = khglKhhmcxxGrxd.getZjhm();
                        if (khglKhhmcxxGrxd.getZjhm()!=null && IdcardUtil.isValidCard(khglKhhmcxxGrxd.getZjhm()))
                         mpoage = IdcardUtil.getAgeByIdCard(khglKhhmcxxGrxd.getZjhm())+"";

                        mpo="配偶 "+khglKhhmcxxGrxd.getKhmc()+"，现年 "+mpoage+"岁 ，身份证号 "+mpozjhm+"。";
                    }

                    if (zjhm.equals(khglKhhmcxxGrxd.getZjhm())) {
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getDz())) {
                            grsxsqbDTO.setDz(khglKhhmcxxGrxd.getDz());
                            grsxsqbDTO.setDbrlxdz_(khglKhhmcxxGrxd.getDz());
                            dz = khglKhhmcxxGrxd.getDz();
                        }
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getSjhm())){
                            grsxsqbDTO.setSjhm(khglKhhmcxxGrxd.getSjhm());
                            msjhm = khglKhhmcxxGrxd.getSjhm();
                        }
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getXb())) {
                            if ("1".equals(khglKhhmcxxGrxd.getXb()))
                                grsxsqbDTO.setXb("男");
                            if ("2".equals(khglKhhmcxxGrxd.getXb()))
                                grsxsqbDTO.setXb("女");
                        }
                        //获得镇 村
                        if (StringUtils.isNoneBlank(khglKhhmcxxGrxd.getSsyxdy())){
                            sjcode =  khglKhhmcxxGrxd.getSsyxdy();
                            QueryWrapper sjj = new QueryWrapper();
                            sjj.eq("DYBH",sjcode);
                            Sjyxdygl sjyxdygl = sjyxdyglMapper.selectOne(sjj);

                            if (sjyxdygl != null){
                                String sj = iDictValueQuery.getyjValue(sjyxdygl.getYjyxdybh());
                                String ej = iDictValueQuery.getejValue(sjyxdygl.getEjyxdybh());
                                if (StringUtils.isNoneBlank(sj))
                                    zhen = sj;
                                if (StringUtils.isNoneBlank(ej))
                                    cun = ej;
                            }

                        }

                    }
                    if ("2".equals(khglKhhmcxxGrxd.getYhzgx())){
                        grsxsqbDTO.setSsjhm(khglKhhmcxxGrxd.getSjhm());
                        gtjkrpo = "."+khglKhhmcxxGrxd.getKhmc();
                    }

                    if (i == 1) {
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getKhmc()))
                            grsxsqbDTO.setFkhmc1(khglKhhmcxxGrxd.getKhmc());
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getYhzgx())) {
                            grsxsqbDTO.setYjkrgx1(iDictValueQuery.getYzhgxValue(khglKhhmcxxGrxd.getYhzgx()));
                        }
                        grsxsqbDTO.setFzjhm1(khglKhhmcxxGrxd.getZjhm());
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getDz()))
                            grsxsqbDTO.setFdz1(khglKhhmcxxGrxd.getDz());
                    }
                    if (i == 2) {
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getKhmc()))
                            grsxsqbDTO.setFkhmc2(khglKhhmcxxGrxd.getKhmc());
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getYhzgx())) {
                            grsxsqbDTO.setYjkrgx2(iDictValueQuery.getYzhgxValue(khglKhhmcxxGrxd.getYhzgx()));
                        }
                        grsxsqbDTO.setFzjhm2(khglKhhmcxxGrxd.getZjhm());
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getDz()))
                            grsxsqbDTO.setFdz2(khglKhhmcxxGrxd.getDz());
                    }
                    if (i == 3) {
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getKhmc()))
                            grsxsqbDTO.setFkhmc3(khglKhhmcxxGrxd.getKhmc());
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getYhzgx())) {
                            grsxsqbDTO.setYjkrgx3(iDictValueQuery.getYzhgxValue(khglKhhmcxxGrxd.getYhzgx()));
                        }
                        grsxsqbDTO.setFzjhm3(khglKhhmcxxGrxd.getZjhm());
                        if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getDz()))
                            grsxsqbDTO.setFdz3(khglKhhmcxxGrxd.getDz());
                    }
                }
            }


            if (grdkspjl.getSxje() != null) {
                grsxsqbDTO.setSqsxze(grdkspjl.getSxje().toString() + " 万元");
                grsxsqbDTO.setMtjk_(grdkspjl.getSxje().toString());
                grsxsqbDTO.setJk_(grdkspjl.getSxje().toString());
                dk = grdkspjl.getSxje().toString();
            }
            if (grdkspjl.getSqje() != null){
                sqje =grdkspjl.getSqje();
            }
            if (StringUtils.isNotBlank(grdkspjl.getJkqx())) {
                grsxsqbDTO.setQx_(grdkspjl.getJkqx());

                if (isInteger(grdkspjl.getJkqx())) {
                    Integer num = Integer.valueOf(grdkspjl.getJkqx()) / 12;
                    grsxsqbDTO.setMtqx_(num.toString());
                    grsxsqbDTO.setQx(num.toString());
                    qx = num.toString();
                }
            }

            //福农卡 便民卡
            if (grdkspjl.getFnkjkje() != null) {
                //grsxsqbDTO.setFnkAdnFnkk();
                grsxsqbDTO.setFnkAdnFnkk(grdkspjl.getFnkjkje().toString());
            }
            if (grdkspjl.getBmkjkje() != null) {
                grsxsqbDTO.setBmkAdnBmkk(grdkspjl.getBmkjkje().toString());
            }
            if (grdkspjl.getDbjkje() != null) {
                grsxsqbDTO.setDbjeAndDbjek(grdkspjl.getDbjkje().toString());
            }


            //借款用途
            if (StringUtils.isNotBlank(grdkspjl.getJkyt())) {
                grsxsqbDTO.setGtyt_(grdkspjl.getJkyt());
                grsxsqbDTO.setMtyt_(grdkspjl.getJkyt());
                grsxsqbDTO.setYt_(grdkspjl.getJkyt());
                yuanyin = grdkspjl.getJkyt();
            }
            //还款来源及计划
            StringBuffer stringBuffer = new StringBuffer();
            /*if (StringUtils.isNotBlank(grdkspjl.getDyhkly()))
                stringBuffer.append(grdkspjl.getDyhkly()).append("。");
            if (StringUtils.isNotBlank(grdkspjl.getDehkly()))
                stringBuffer.append(grdkspjl.getDehkly()).append("。");*/
            if (StringUtils.isNotBlank(grdkspjl.getHkfs()))
                stringBuffer.append(grdkspjl.getHkfs()).append("。");
            if (StringUtils.isNotBlank(grdkspjl.getHkjh()))
                stringBuffer.append(grdkspjl.getHkjh()).append("。");
            if (stringBuffer.toString().length() > 0)
                grsxsqbDTO.setHklyjjh(stringBuffer.toString());


            if (grdkspjl.getZczeHj() != null)
                grsxsqbDTO.setMtsr_(grdkspjl.getZczeHj().multiply(new BigDecimal(10000)).toString());

            //还款计划
            String hklyjjh = "";
            if (StringUtils.isNotBlank(grdkspjl.getDyhkly()))
                hklyjjh += grdkspjl.getDyhkly() + ",";
            if (StringUtils.isNotBlank(grdkspjl.getDehkly()))
                hklyjjh += grdkspjl.getDehkly() + ",";
            if (StringUtils.isNotBlank(grdkspjl.getHkjh()))
                hklyjjh += grdkspjl.getHkjh();

            //todo 信用信息查询 使用授权书

            //湖南浏阳农村商业银行抵押房地产评估报告单
            List<CamsZcsxDydb> dydbs = camsZcsxDydbMapper.selectList(khyh);
            if (dydbs != null && dydbs.size() > 0) {
                grsxsqbDTO.setDyk(WordConstant.GOUXUAN);
                int maxValue = dydbs.size() > 4 ? 4 : dydbs.size();
                for (int i = 0; i < maxValue; i++) {
                    CamsZcsxDydb camsZcsxDydb = dydbs.get(i);
                    if (i == 0) {
                        if (camsZcsxDydb.getPgz() != null){

                            mdyjz = camsZcsxDydb.getPgz().toString();
                        }
                        if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                            grsxsqbDTO.setFwssqr1(camsZcsxDydb.getSyqr());
                        if (camsZcsxDydb.getSyqrdh() != null)
                            grsxsqbDTO.setLxdh1(camsZcsxDydb.getSyqrdh().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                            grsxsqbDTO.setFcyjkrgx1(camsZcsxDydb.getYbdcrgx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz())){
                            grsxsqbDTO.setFwzlwz1(camsZcsxDydb.getJtwz());
                            mdywz = camsZcsxDydb.getJtwz();
                        }

                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                            grsxsqbDTO.setTdzh1(camsZcsxDydb.getTdzh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                            grsxsqbDTO.setTdlx1(camsZcsxDydb.getTdlx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                            grsxsqbDTO.setTdyt1(camsZcsxDydb.getTdyt());
                        if (camsZcsxDydb.getTdsymj() != null)
                            grsxsqbDTO.setSyqmj1(camsZcsxDydb.getTdsymj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                            grsxsqbDTO.setZzrq1(camsZcsxDydb.getTdzzrq());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                            grsxsqbDTO.setFczh1(camsZcsxDydb.getFczh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                            grsxsqbDTO.setZcs1(camsZcsxDydb.getZcs());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                            grsxsqbDTO.setFcyt1(camsZcsxDydb.getZdyt());
                        if (camsZcsxDydb.getZdljzmj() != null)
                            grsxsqbDTO.setJzmj1(camsZcsxDydb.getZdljzmj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                            grsxsqbDTO.setJcsj1(camsZcsxDydb.getFcjcsj());
                        if (camsZcsxDydb.getFclc() != null)
                            grsxsqbDTO.setCc1(camsZcsxDydb.getFclc().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                            grsxsqbDTO.setCcyt1(camsZcsxDydb.getFcyt());
                        if (camsZcsxDydb.getFcjzmj() != null)
                            grsxsqbDTO.setCcjzmj1(camsZcsxDydb.getFcjzmj().toString());

                        if (camsZcsxDydb.getBdcmpfdj() != null)
                            grsxsqbDTO.setMpfdj1(camsZcsxDydb.getBdcmpfdj().toString());
                        if (camsZcsxDydb.getPgz() != null)
                            grsxsqbDTO.setGjz1(camsZcsxDydb.getPgz().toString());
                    }

                    if (i == 1) {
                        if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                            grsxsqbDTO.setFwssqr2(camsZcsxDydb.getSyqr());
                        if (camsZcsxDydb.getSyqrdh() != null)
                            grsxsqbDTO.setLxdh2(camsZcsxDydb.getSyqrdh().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                            grsxsqbDTO.setFcyjkrgx2(camsZcsxDydb.getYbdcrgx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                            grsxsqbDTO.setFwzlwz2(camsZcsxDydb.getJtwz());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                            grsxsqbDTO.setTdzh2(camsZcsxDydb.getTdzh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                            grsxsqbDTO.setTdlx2(camsZcsxDydb.getTdlx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                            grsxsqbDTO.setTdyt2(camsZcsxDydb.getTdyt());
                        if (camsZcsxDydb.getTdsymj() != null)
                            grsxsqbDTO.setSyqmj2(camsZcsxDydb.getTdsymj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                            grsxsqbDTO.setZzrq2(camsZcsxDydb.getTdzzrq());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                            grsxsqbDTO.setFczh2(camsZcsxDydb.getFczh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                            grsxsqbDTO.setZcs2(camsZcsxDydb.getZcs());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                            grsxsqbDTO.setFcyt2(camsZcsxDydb.getZdyt());
                        if (camsZcsxDydb.getZdljzmj() != null)
                            grsxsqbDTO.setJzmj2(camsZcsxDydb.getZdljzmj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                            grsxsqbDTO.setJcsj2(camsZcsxDydb.getFcjcsj());
                        if (camsZcsxDydb.getFclc() != null)
                            grsxsqbDTO.setCc2(camsZcsxDydb.getFclc().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                            grsxsqbDTO.setCcyt2(camsZcsxDydb.getFcyt());
                        if (camsZcsxDydb.getFcjzmj() != null)
                            grsxsqbDTO.setCcjzmj2(camsZcsxDydb.getFcjzmj().toString());

                        if (camsZcsxDydb.getBdcmpfdj() != null)
                            grsxsqbDTO.setMpfdj2(camsZcsxDydb.getBdcmpfdj().toString());
                        if (camsZcsxDydb.getPgz() != null)
                            grsxsqbDTO.setGjz2(camsZcsxDydb.getPgz().toString());
                    }

                    if (i == 2) {
                        if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                            grsxsqbDTO.setFwssqr3(camsZcsxDydb.getSyqr());
                        if (camsZcsxDydb.getSyqrdh() != null)
                            grsxsqbDTO.setLxdh3(camsZcsxDydb.getSyqrdh().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                            grsxsqbDTO.setFcyjkrgx3(camsZcsxDydb.getYbdcrgx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                            grsxsqbDTO.setFwzlwz3(camsZcsxDydb.getJtwz());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                            grsxsqbDTO.setTdzh3(camsZcsxDydb.getTdzh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                            grsxsqbDTO.setTdlx3(camsZcsxDydb.getTdlx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                            grsxsqbDTO.setTdyt3(camsZcsxDydb.getTdyt());
                        if (camsZcsxDydb.getTdsymj() != null)
                            grsxsqbDTO.setSyqmj3(camsZcsxDydb.getTdsymj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                            grsxsqbDTO.setZzrq3(camsZcsxDydb.getTdzzrq());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                            grsxsqbDTO.setFczh3(camsZcsxDydb.getFczh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                            grsxsqbDTO.setZcs3(camsZcsxDydb.getZcs());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                            grsxsqbDTO.setFcyt3(camsZcsxDydb.getZdyt());
                        if (camsZcsxDydb.getZdljzmj() != null)
                            grsxsqbDTO.setJzmj3(camsZcsxDydb.getZdljzmj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                            grsxsqbDTO.setJcsj3(camsZcsxDydb.getFcjcsj());
                        if (camsZcsxDydb.getFclc() != null)
                            grsxsqbDTO.setCc3(camsZcsxDydb.getFclc().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                            grsxsqbDTO.setCcyt3(camsZcsxDydb.getFcyt());
                        if (camsZcsxDydb.getFcjzmj() != null)
                            grsxsqbDTO.setCcjzmj3(camsZcsxDydb.getFcjzmj().toString());

                        if (camsZcsxDydb.getBdcmpfdj() != null)
                            grsxsqbDTO.setMpfdj3(camsZcsxDydb.getBdcmpfdj().toString());
                        if (camsZcsxDydb.getPgz() != null)
                            grsxsqbDTO.setGjz3(camsZcsxDydb.getPgz().toString());
                    }

                    if (i == 3) {
                        if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                            grsxsqbDTO.setFwssqr4(camsZcsxDydb.getSyqr());
                        if (camsZcsxDydb.getSyqrdh() != null)
                            grsxsqbDTO.setLxdh4(camsZcsxDydb.getSyqrdh().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                            grsxsqbDTO.setFcyjkrgx4(camsZcsxDydb.getYbdcrgx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                            grsxsqbDTO.setFwzlwz4(camsZcsxDydb.getJtwz());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                            grsxsqbDTO.setTdzh4(camsZcsxDydb.getTdzh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                            grsxsqbDTO.setTdlx4(camsZcsxDydb.getTdlx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                            grsxsqbDTO.setTdyt4(camsZcsxDydb.getTdyt());
                        if (camsZcsxDydb.getTdsymj() != null)
                            grsxsqbDTO.setSyqmj4(camsZcsxDydb.getTdsymj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                            grsxsqbDTO.setZzrq4(camsZcsxDydb.getTdzzrq());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                            grsxsqbDTO.setFczh4(camsZcsxDydb.getFczh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                            grsxsqbDTO.setZcs4(camsZcsxDydb.getZcs());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                            grsxsqbDTO.setFcyt4(camsZcsxDydb.getZdyt());
                        if (camsZcsxDydb.getZdljzmj() != null)
                            grsxsqbDTO.setJzmj4(camsZcsxDydb.getZdljzmj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                            grsxsqbDTO.setJcsj4(camsZcsxDydb.getFcjcsj());
                        if (camsZcsxDydb.getFclc() != null)
                            grsxsqbDTO.setCc4(camsZcsxDydb.getFclc().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                            grsxsqbDTO.setCcyt4(camsZcsxDydb.getFcyt());
                        if (camsZcsxDydb.getFcjzmj() != null)
                            grsxsqbDTO.setCcjzmj4(camsZcsxDydb.getFcjzmj().toString());

                        if (camsZcsxDydb.getBdcmpfdj() != null)
                            grsxsqbDTO.setMpfdj4(camsZcsxDydb.getBdcmpfdj().toString());
                        if (camsZcsxDydb.getPgz() != null)
                            grsxsqbDTO.setGjz4(camsZcsxDydb.getPgz().toString());
                    }
                }
            }
            //保证 质押 信息
            int bz = bzdbMapper.selectCount(khyh).intValue();
            if (bz > 0)
                grsxsqbDTO.setBzk(WordConstant.GOUXUAN);
            int zy = zydbMapper.selectCount(khyh).intValue();
            if (zy > 0)
                grsxsqbDTO.setZyk(WordConstant.GOUXUAN);
            int xy = xydbMapper.selectCount(khyh).intValue();
            if (xy > 0)
                grsxsqbDTO.setXyk(WordConstant.GOUXUAN);


            //
            if (StringUtils.isNotBlank(grdkspjl.getGtjkr())){
                grsxsqbDTO.setGtjkr_(grdkspjl.getGtjkr());
                if (gtjkrpo.length() > 0)
                    grsxsqbDTO.setGtjkr_(grdkspjl.getGtjkr()+gtjkrpo);
            }

            if (StringUtils.isNotBlank(grdkspjl.getZdqsr()))
                grsxsqbDTO.setZdqsr_(grdkspjl.getZdqsr());
            if (StringUtils.isNotBlank(grdkspjl.getQsrsj()))
                grsxsqbDTO.setSjhm_(grdkspjl.getQsrsj());
            if (StringUtils.isNotBlank(grdkspjl.getQsrcz()))
                grsxsqbDTO.setCzhm_(grdkspjl.getQsrcz());
            if (StringUtils.isNotBlank(grdkspjl.getQsryx()))
                grsxsqbDTO.setDzyx_(grdkspjl.getQsryx());
            if (StringUtils.isNotBlank(grdkspjl.getQsrwx()))
                grsxsqbDTO.setWxh_(grdkspjl.getQsrwx());
            if (StringUtils.isNotBlank(grdkspjl.getQtqsfs()))
                grsxsqbDTO.setQtfs_(grdkspjl.getQtqsfs());

            //todo 调查报告
            if (grdkspjl.getZczeHj() != null){
                zzc = grdkspjl.getZczeHj().toString();
            }
            if (grdkspjl.getLdzcHj() != null){
                ldzj = grdkspjl.getLdzcHj().toString();
            }
            if (grdkspjl.getFzzeHj() != null){
                zfz = grdkspjl.getFzzeHj().toString();
            }
            if (grdkspjl.getXse() != null){
                jysr = grdkspjl.getXse().toString();
            }
            if (grdkspjl.getJlr() != null){
                sxlr = grdkspjl.getJlr().toString();
            }
            //查询房产价值
            QueryWrapper fcqw = new QueryWrapper();
            fcqw.eq("ZJHM",zjhm);
            List<Fwxx> fwxxList = fwxxMapper.selectList(fcqw);
            if (CollUtil.isNotEmpty(fwxxList)){
                fcdz = "";
                int intjz = 0;
                for (int i = 0; i < fwxxList.size(); i++) {

                    Fwxx fwxx = fwxxList.get(i);
                    intjz += fwxx.getPjgz().intValue();
                    if (i == fwxxList.size() - 1){
                        fcdz+=fwxx.getJtwz();
                    }else {
                        fcdz += fwxx.getJtwz()+",";
                    }
                }
                fcjz = intjz+"";
            }

            String pljzByZjhm = clxxMapper.getPljzByZjhm(zjhm);
            if (StringUtils.isNoneBlank(pljzByZjhm)){
                jtgj = pljzByZjhm;
            }

            String pjgByZjhm = qtglzcMapper.getPjgByZjhm(zjhm);
            if (StringUtils.isNoneBlank(pjgByZjhm)){
                qtzc = pjgByZjhm;
            }

            grsxsqbDTO.setMName("关于"+grdkspjl.getKhmc()+"申请贷款在贷前调查报告");
            grsxsqbDTO.setMHeader("  "+zhen+ " "+cun+"居民 "+grdkspjl.getKhmc()+" 因 "+yuanyin+" 缺少流动资金，向我行申请贷款 "+sqje+" 万元，期限 "+qx+" 年。我行对其调查如下：  " );
            grsxsqbDTO.setMYi("  借款申请人 "+grdkspjl.getKhmc()+",现年 "+age+"岁，住 "+dz+" ,身份证号码 "+zjhm+" ,手机号码 "+msjhm+" 。"+mpo+"该户主要以 "+grdkspjl.getKhmc()+" 为主。");
            grsxsqbDTO.setMSan("  该户总资产经调查估值 "+ zzc+" 万元，其中 "+fcdz+ " 房产价值 "+fcjz+" 万元"+
                    "，家用电器      万元，交通工具"+jtgj+"万元，流动资金 "+ldzj+" 万元,其他资产 "+qtzc+" 万元，负债 "+zfz+" 万元。");
            grsxsqbDTO.setMSi("  该户去年经营收入 "+jysr+" 万元，实现利润 "+sxlr+" 万元，创造了较好的效益。");
            grsxsqbDTO.setMWu("  该户因 "+yuanyin+" 缺少资金，向我行申请借款 "+dk+" 万元，计划用 "+hklyjjh+" 作为还款来源。");
            grsxsqbDTO.setMLiu("  该户贷款自愿以位于 "+mdywz+",  价值 "+mdyjz+ " 万元的商住房作抵押，能办理合法有效的抵押手续。");
            grsxsqbDTO.setMQi("  经调查，该户经营状况良好，信誉优良。抵押物真实，合法，有效，符合授信条件同意授信 "+ dk+" 万元，期限 "+qx+" 年。");
            //附件
            QueryWrapper fj = new QueryWrapper();
            fj.eq("HHBM", grdkspjl.getHhbm());
            fj.eq("ZJHM", zjhm);
            fj.orderByAsc("ZLLX");
            List<Grxdzllb> zls = grxdzllbMapper.selectList(fj);
            if (CollUtil.isNotEmpty(zls)) {
                StringBuffer zl1 = new StringBuffer();
                StringBuffer zl2 = new StringBuffer();
                StringBuffer zl3 = new StringBuffer();
                StringBuffer zl4 = new StringBuffer();
                StringBuffer zl5 = new StringBuffer();
                StringBuffer zl6 = new StringBuffer();
                StringBuffer zl7 = new StringBuffer();
                StringBuffer zl8 = new StringBuffer();
                StringBuffer zl9 = new StringBuffer();
                StringBuffer zl10 = new StringBuffer();
                StringBuffer zl11 = new StringBuffer();
                StringBuffer zl12 = new StringBuffer();
                StringBuffer zl13 = new StringBuffer();
                StringBuffer zl14 = new StringBuffer();
                StringBuffer zl15 = new StringBuffer();
                StringBuffer zl16 = new StringBuffer();
                StringBuffer zl17 = new StringBuffer();
                StringBuffer zl18 = new StringBuffer();
                for (int i = 0; i < zls.size(); i++) {
                    Grxdzllb grxdzllb = zls.get(i);
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("1")) {
                        grsxsqbDTO.setPic1(zl1.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("2")) {
                        grsxsqbDTO.setPic2(zl2.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("3")) {
                        grsxsqbDTO.setPic3(zl3.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("4")) {
                        grsxsqbDTO.setPic4(zl4.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("5")) {
                        grsxsqbDTO.setPic5(zl5.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("6")) {
                        grsxsqbDTO.setPic6(zl6.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("7")) {
                        grsxsqbDTO.setPic7(zl7.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("8")) {
                        grsxsqbDTO.setPic8(zl8.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("9")) {
                        grsxsqbDTO.setPic9(zl9.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("10")) {
                        grsxsqbDTO.setPic10(zl10.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("11")) {
                        grsxsqbDTO.setPic11(zl11.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("12")) {
                        grsxsqbDTO.setPic12(zl12.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("13")) {
                        grsxsqbDTO.setPic13(zl13.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("14")) {
                        grsxsqbDTO.setPic14(zl14.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("15")) {
                        grsxsqbDTO.setPic15(zl15.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("16")) {
                        grsxsqbDTO.setPic16(zl16.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("17")) {
                        grsxsqbDTO.setPic17(zl17.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                    if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("18")) {
                        grsxsqbDTO.setPic18(zl18.append(grxdzllb.getZllj()).append(",").toString());
                        continue;
                    }
                }
            }

            //支行风险评价审查表
            QueryWrapper fx = new QueryWrapper();
            /*fx.eq("HHBM", grdkspjl.getHhbm());
            fx.eq("ZJHM", zjhm);*/
            fx.eq("SPID",grdkspjl.getSpid());
            CamsZcsxFxpjsc camsZcsxFxpjsc = camsZcsxFxpjscMapper.selectOne(fx);
            if (camsZcsxFxpjsc != null) {
                if (StringUtils.isNotBlank(camsZcsxFxpjsc.getXdzljbys())) {
                    if (camsZcsxFxpjsc.getXdzljbys().contains("1"))
                        grsxsqbDTO.setSc11(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getXdzljbys().contains("2"))
                        grsxsqbDTO.setSc12(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getXdzljbys().contains("3"))
                        grsxsqbDTO.setSc13(WordConstant.GOUXUAN);
                }

                if (StringUtils.isNotBlank(camsZcsxFxpjsc.getZtzg())) {
                    if (camsZcsxFxpjsc.getZtzg().contains("1"))
                        grsxsqbDTO.setSc21(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getZtzg().contains("2"))
                        grsxsqbDTO.setSc22(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getZtzg().contains("3"))
                        grsxsqbDTO.setSc23(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getZtzg().contains("4"))
                        grsxsqbDTO.setSc24(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getZtzg().contains("5"))
                        grsxsqbDTO.setSc25(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getZtzg().contains("6"))
                        grsxsqbDTO.setSc26(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getZtzg().contains("7"))
                        grsxsqbDTO.setSc27(WordConstant.GOUXUAN);
                }

                if (StringUtils.isNotBlank(camsZcsxFxpjsc.getZcgd())) {
                    if (camsZcsxFxpjsc.getZcgd().contains("1"))
                        grsxsqbDTO.setSc31(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getZcgd().contains("2"))
                        grsxsqbDTO.setSc32(WordConstant.GOUXUAN);

                }

                if (StringUtils.isNotBlank(camsZcsxFxpjsc.getXdfx())) {
                    if (camsZcsxFxpjsc.getXdfx().contains("1"))
                        grsxsqbDTO.setSc41(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getXdfx().contains("2"))
                        grsxsqbDTO.setSc42(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getXdfx().contains("3"))
                        grsxsqbDTO.setSc43(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getXdfx().contains("4"))
                        grsxsqbDTO.setSc44(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getXdfx().contains("5"))
                        grsxsqbDTO.setSc45(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getXdfx().contains("6"))
                        grsxsqbDTO.setSc46(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getXdfx().contains("7"))
                        grsxsqbDTO.setSc47(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getXdfx().contains("8"))
                        grsxsqbDTO.setSc48(WordConstant.GOUXUAN);

                }

                if (StringUtils.isNotBlank(camsZcsxFxpjsc.getDb())) {
                    if (camsZcsxFxpjsc.getDb().contains("1"))
                        grsxsqbDTO.setSc51(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getDb().contains("2"))
                        grsxsqbDTO.setSc52(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getDb().contains("3"))
                        grsxsqbDTO.setSc53(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getDb().contains("4"))
                        grsxsqbDTO.setSc54(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getDb().contains("5"))
                        grsxsqbDTO.setSc55(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getDb().contains("6"))
                        grsxsqbDTO.setSc56(WordConstant.GOUXUAN);

                }

                if (StringUtils.isNotBlank(camsZcsxFxpjsc.getQtzl()))
                    grsxsqbDTO.setSc6(camsZcsxFxpjsc.getQtzl());
                if (StringUtils.isNotBlank(camsZcsxFxpjsc.getFxdwzsm()))
                    grsxsqbDTO.setSc7(camsZcsxFxpjsc.getFxdwzsm());

                if (StringUtils.isNotBlank(camsZcsxFxpjsc.getScjl())) {
                    if (camsZcsxFxpjsc.getScjl().contains("1"))
                        grsxsqbDTO.setSc81(WordConstant.GOUXUAN);

                    if (camsZcsxFxpjsc.getScjl().contains("2"))
                        grsxsqbDTO.setSc82(WordConstant.GOUXUAN);
                    if (camsZcsxFxpjsc.getScjl().contains("3"))
                        grsxsqbDTO.setSc83(WordConstant.GOUXUAN);
                }
                //集体审批讨论记录及审批责任书
                GrdkJtsp grdkJtsp = grdkJtspMapper.selectById(id);
                if (grdkJtsp != null) {
                    if (StringUtils.isNotBlank(grdkJtsp.getJtspsj()))
                        grsxsqbDTO.setSpsj(grdkJtsp.getJtspsj());
                    if (StringUtils.isNotBlank(grdkJtsp.getJtspdd()))
                        grsxsqbDTO.setSpdd(grdkJtsp.getJtspdd());
                    if (StringUtils.isNotBlank(grdkJtsp.getJtspjlr()))
                        grsxsqbDTO.setSpjlr(grdkJtsp.getJtspjlr());
                    if (grdkJtsp.getSnsxze() != null)
                        grsxsqbDTO.setSpsx(grdkJtsp.getSnsxze().toString());

                    if (grdkJtsp.getSnsxzezdk() != null)
                        grsxsqbDTO.setSpdk(grdkJtsp.getSnsxzezdk().toString());
                    if (grdkJtsp.getSnsxzezbmk() != null)
                        grsxsqbDTO.setSpbm(grdkJtsp.getSnsxzezbmk().toString());
                    if (grdkJtsp.getSnsxzezfnk() != null)
                        grsxsqbDTO.setPfn(grdkJtsp.getSnsxzezfnk().toString());
                    if (grdkJtsp.getSnsxzezqt() != null)
                        grsxsqbDTO.setPqt(grdkJtsp.getSnsxzezqt().toString());

                    if (grdkJtsp.getXyyxye() != null)
                        grsxsqbDTO.setPyx(grdkJtsp.getXyyxye().toString());
                    if (grdkJtsp.getXydkye() != null)
                        grsxsqbDTO.setPdk(grdkJtsp.getXydkye().toString());
                    if (StringUtils.isNotBlank(grdkJtsp.getXydkdqrq()))
                        grsxsqbDTO.setPrq(grdkJtsp.getXydkdqrq());
                    if (grdkJtsp.getXydkfnkye() != null)
                        grsxsqbDTO.setKnf(grdkJtsp.getXydkfnkye().toString());
                    if (grdkJtsp.getXydkqt() != null)
                        grsxsqbDTO.setQtp(grdkJtsp.getXydkqt().toString());

                    if (grdkJtsp.getXsqsxje() != null)
                        grsxsqbDTO.setXsx(grdkJtsp.getXsqsxje().toString());
                    if (StringUtils.isNotBlank(grdkJtsp.getXdbfs()))
                        grsxsqbDTO.setPdb(grdkJtsp.getXdbfs());
                    if (grdkJtsp.getXdywjz() != null)
                        grsxsqbDTO.setPdywjz(grdkJtsp.getXdywjz().toString());


                    if (StringUtils.isNotBlank(grdkJtsp.getCsry()))
                        grsxsqbDTO.setCsry(grdkJtsp.getCsry());
                    if (StringUtils.isNotBlank(grdkJtsp.getSdjl()))
                        grsxsqbDTO.setSdjl(grdkJtsp.getSdjl());


                    if (grdkJtsp.getSxze() != null){
                        //grsxsqbDTO.setSxze(grdkJtsp.getSxze().toString());
                        grsxsqbDTO.setSxze(Convert.digitToChinese(grdkJtsp.getSxze().multiply(new BigDecimal("10000"))));
                    }
                    if (grdkJtsp.getSxqx() != null)
                        grsxsqbDTO.setPqx(grdkJtsp.getSxqx().toString());
                    if (grdkJtsp.getLlnq() != null)
                        grsxsqbDTO.setN(grdkJtsp.getLlnq().toString());
                    if (grdkJtsp.getLljd() != null)
                        grsxsqbDTO.setJd(grdkJtsp.getLljd().toString());
                    if (grdkJtsp.getDksxed() != null)
                        grsxsqbDTO.setDksx(grdkJtsp.getDksxed().toString());
                    if (grdkJtsp.getBmksxed() != null)
                        grsxsqbDTO.setPbmksx(grdkJtsp.getBmksxed().toString());
                    if (grdkJtsp.getDbsxed() != null)
                        grsxsqbDTO.setPdbsx(grdkJtsp.getDbsxed().toString());
                    if (grdkJtsp.getFnksxed() != null)
                        grsxsqbDTO.setPfnksx(grdkJtsp.getFnksxed().toString());
                    if (StringUtils.isNotBlank(grdkJtsp.getFjtj()))
                        grsxsqbDTO.setPfjtj(grdkJtsp.getFjtj());

                    if (StringUtils.isNotBlank(grdkJtsp.getJtspzzzt()) && "1".equals(grdkJtsp.getJtspzzzt()))
                        issc = 1;

                    QueryWrapper jcy = new QueryWrapper();
                    jcy.eq("ID",id);
                    List<Jtspcy> list = jtspcyMapper.selectList(jcy);
                    StringBuffer spryj = new StringBuffer();
                    StringBuffer dcryj = new StringBuffer();
                    StringBuffer picsqz = new StringBuffer();
                    if (CollUtil.isNotEmpty(list)){
                        for (int i = 0; i < list.size(); i++) {
                            //1主调查人 2是协调查人 3是审批组组长 4是其他


                            Jtspcy jtspcy = list.get(i);
                            String lrrlx = jtspcy.getZrrlx();
                            if (StringUtils.isNotBlank(lrrlx)){
                                //客户经理
                                if (lrrlx.equals("1")){
                                    if (StringUtils.isNotBlank(jtspcy.getBjyj())){
                                        //grsxsqbDTO.setDcryj(getbjyj(j));
                                        dcryj.append("主调查人 ").append(jtspcy.getZrrmc()).append(" 表决情况：").append(getbjyj(jtspcy.getBjyj())).append("  ，");
                                    }

                                    if (StringUtils.isNotBlank(jtspcy.getQtyj())){
                                        dcryj.append("其他意见：").append(jtspcy.getQtyj()).append("  。").append("\n");
                                    }

                                    if (StringUtils.isNotBlank(jtspcy.getQmtp())){
                                        grsxsqbDTO.setPiczqz(uploadpath+File.separator+jtspcy.getQmtp());
                                    }

                                }

                                //行长
                                if (lrrlx.equals("3")){
                                    if (StringUtils.isNotBlank(jtspcy.getQmtp())){
                                        grsxsqbDTO.setPiccqz(uploadpath+File.separator+jtspcy.getQmtp());
                                    }

                                    if (StringUtils.isNotBlank(jtspcy.getBjyj())){
                                        spryj.append("审批责任人 ").append(jtspcy.getZrrmc()).append(" 表决情况：").append(getbjyj(jtspcy.getBjyj())).append("  ，");
                                    }

                                    if (StringUtils.isNotBlank(jtspcy.getQtyj())){
                                        spryj.append("其他意见：").append(jtspcy.getQtyj()).append("  。").append("\n");
                                    }

                                }


                                //协调查人
                                if (lrrlx.equals("2")){
                                    if (StringUtils.isNotBlank(jtspcy.getBjyj())){
                                        dcryj.append("协调查人 ").append(jtspcy.getZrrmc()).append(" 表决情况：").append(getbjyj(jtspcy.getBjyj())).append("  ，");
                                    }

                                    if (StringUtils.isNotBlank(jtspcy.getQtyj())){
                                        dcryj.append("其他意见：").append(jtspcy.getQtyj()).append("  。").append("\n");
                                    }


                                    if (StringUtils.isNotBlank(jtspcy.getQmtp())){
                                        grsxsqbDTO.setPicxqz((uploadpath+File.separator+jtspcy.getQmtp()));
                                    }
                                }

                                if (lrrlx.equals("4")){
                                    if (StringUtils.isNotBlank(jtspcy.getBjyj())){
                                        spryj.append("审批责任人 ").append(jtspcy.getZrrmc()).append(" 表决情况：").append(getbjyj(jtspcy.getBjyj())).append("  ，");
                                    }

                                    if (StringUtils.isNotBlank(jtspcy.getQtyj())){
                                        spryj.append("其他意见：").append(jtspcy.getQtyj()).append("  。").append("\n");
                                    }

                                    //审批责任人
                                    if (StringUtils.isNotBlank(jtspcy.getQmtp())){
                                        //grsxsqbDTO.setPicsqz(uploadpath+File.separator+jtspcy.getQmtp());
                                        picsqz.append(uploadpath+File.separator+jtspcy.getQmtp()).append(",");
                                    }
                                }

                            /*if (StringUtils.isNotBlank(jtspcy.getBjyj())){
                                //grsxsqbDTO.setSpryj(jtspcy.getBjyj());
                                spryj.append(jtspcy.getBjyj()).append(",");
                            }*/




                            }

                        }

                        if (dcryj.length()>0)
                            grsxsqbDTO.setDcryj(dcryj.toString());
                        if (spryj.length()>0)
                            grsxsqbDTO.setSpryj(spryj.toString());
                        if (picsqz.length()>0)
                            grsxsqbDTO.setPicsqz(picsqz.toString());

                    }

                }
            }


            //String pro = uploadpath.substring(0, 1);
            DocxUtil.mkdirCatalog(uploadpath + upload);
            String fileName = id + "grdk.doc";
            String upload2 = uploadpath + upload + id + "grdk.doc";

            File file = new File(upload2);
            if (file.exists() && issc == 1) {
                log.info("已经完成终审  不在生成文档");
                return fileName;
            }

            log.info("本次个人信贷申请书生成位置|{}|", upload2);
            DocxUtil2.exportX2Doc(grsxsqbDTO, uploadpath+moban+"grdk.docx", upload2);

            return fileName;
        }
        return "fail";
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    public String getdcbg(List<KhglKhhmcxxGrxd> khglKhhmcxxGrxds) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("  ");
        if (khglKhhmcxxGrxds.size() > 1) {
            for (int i = 0; i < khglKhhmcxxGrxds.size(); i++) {
                KhglKhhmcxxGrxd khglKhhmcxxGrxd = khglKhhmcxxGrxds.get(i);
                if ("1".equals(khglKhhmcxxGrxd.getYhzgx())) {
                    stringBuffer.append(khglKhhmcxxGrxd.getKhmc()).append(WordConstant.FUHAO_DIAN);
                    if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getXb())) {
                        if ("1".equals(khglKhhmcxxGrxd.getXb())) {
                            stringBuffer.append("男").append(WordConstant.FUHAO_DIAN);
                        }
                        if ("2".equals(khglKhhmcxxGrxd.getXb())) {
                            stringBuffer.append("女").append(WordConstant.FUHAO_DIAN);
                        }
                    }

                    stringBuffer.append(IdcardUtil.getAgeByIdCard(khglKhhmcxxGrxd.getZjhm())).append(WordConstant.FUHAO_DIAN);
                    stringBuffer.append(khglKhhmcxxGrxd.getZjhm()).append(WordConstant.FUHAO_DIAN);
                    if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getDz()))
                        stringBuffer.append(khglKhhmcxxGrxd.getDz()).append(WordConstant.FUHAO_JUHAO);

                    if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getCszy())) {
                        stringBuffer.append(iDictValueQuery.getCszyValue(khglKhhmcxxGrxd.getCszy())).append(WordConstant.FUHAO_DIAN);
                    }

                }
                stringBuffer.append(iDictValueQuery.getYzhgxValue(khglKhhmcxxGrxd.getYhzgx()))
                        .append(WordConstant.FUHAO_MAOHAO).append(khglKhhmcxxGrxd.getKhmc())
                        .append(WordConstant.FUHAO_DIAN).append(khglKhhmcxxGrxd.getZjhm());
                if (i == khglKhhmcxxGrxds.size() - 1) {
                    stringBuffer.append(WordConstant.FUHAO_JUHAO);
                } else {
                    stringBuffer.append(WordConstant.FUHAO_DIAN);
                }
            }
        }


        return stringBuffer.toString();
    }

    @Autowired
    BzdbMapper bzdbMapper;

    public String getdbje(String zjhm) {

        return "0";
    }

    @Override
    public String xendFjWord(String zjhm, String hhbm) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ZJHM", zjhm);
        queryWrapper.eq("HHBM", hhbm);
        queryWrapper.orderByAsc("ZLLX");
        List<Fjgl> list = fjglMapper.selectList(queryWrapper);
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        StringBuffer stringBuffer3 = new StringBuffer();
        StringBuffer stringBuffer4 = new StringBuffer();
        StringBuffer stringBuffer5 = new StringBuffer();
        StringBuffer stringBuffer6 = new StringBuffer();
        StringBuffer stringBuffer7 = new StringBuffer();
        StringBuffer stringBuffer8 = new StringBuffer();
        StringBuffer stringBuffer9 = new StringBuffer();
        StringBuffer stringBuffer10 = new StringBuffer();
        XendFuJianDTO xendFuJianDTO = new XendFuJianDTO();
        if (CollUtil.isNotEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Fjgl fjgl = list.get(i);
                if (StringUtils.isNotBlank(fjgl.getZllx())) {
                    if (fjgl.getZllx().equals("1")) {
                        stringBuffer1.append(fjgl.getZllj()).append(",");
                    } else if (fjgl.getZllx().equals("2")) {
                        stringBuffer2.append(fjgl.getZllj()).append(",");
                    } else if (fjgl.getZllx().equals("3")) {
                        stringBuffer3.append(fjgl.getZllj()).append(",");
                    } else if (fjgl.getZllx().equals("4")) {
                        stringBuffer4.append(fjgl.getZllj()).append(",");
                    } else if (fjgl.getZllx().equals("5")) {
                        stringBuffer5.append(fjgl.getZllj()).append(",");
                    } else if (fjgl.getZllx().equals("6")) {
                        stringBuffer6.append(fjgl.getZllj()).append(",");
                    } else if (fjgl.getZllx().equals("7")) {
                        stringBuffer7.append(fjgl.getZllj()).append(",");
                    } else if (fjgl.getZllx().equals("8")) {
                        stringBuffer8.append(fjgl.getZllj()).append(",");
                    } else if (fjgl.getZllx().equals("9")) {
                        stringBuffer9.append(fjgl.getZllj()).append(",");
                    } else if (fjgl.getZllx().equals("10")) {
                        stringBuffer10.append(fjgl.getZllj()).append(",");
                    }
                }

            }
        } else {
            //附件列表为空 请先上传列表
            return "2";
        }

        if (stringBuffer1.length() > 0)
            xendFuJianDTO.setPic1(stringBuffer1.toString());
        if (stringBuffer2.length() > 0)
            xendFuJianDTO.setPic2(stringBuffer2.toString());
        if (stringBuffer3.length() > 0)
            xendFuJianDTO.setPic3(stringBuffer3.toString());
        if (stringBuffer4.length() > 0)
            xendFuJianDTO.setPic4(stringBuffer5.toString());
        if (stringBuffer5.length() > 0)
            xendFuJianDTO.setPic5(stringBuffer5.toString());
        if (stringBuffer6.length() > 0)
            xendFuJianDTO.setPic6(stringBuffer6.toString());
        if (stringBuffer7.length() > 0)
            xendFuJianDTO.setPic7(stringBuffer7.toString());
        if (stringBuffer8.length() > 0)
            xendFuJianDTO.setPic8(stringBuffer8.toString());
        if (stringBuffer9.length() > 0)
            xendFuJianDTO.setPic9(stringBuffer9.toString());
        if (stringBuffer10.length() > 0)
            xendFuJianDTO.setPic10(stringBuffer10.toString());

        DocxUtil.mkdirCatalog(uploadpath + xendupload);
        String fileName = zjhm.substring(6) + "xendfujian.docx";
        String upload2 = uploadpath + xendupload + zjhm.substring(6) + "xendfujian.docx";
        log.info("本次个人信贷申请书生成位置|{}|", upload2);
        DocxUtil2.exportX2Doc(xendFuJianDTO, uploadpath + moban+ "xendfj.docx", upload2);
        return fileName;
    }

    @Override
    public String grdkFjWord(String zjhm, String hhbm) {
        //附件
        QueryWrapper fj = new QueryWrapper();
        fj.eq("HHBM", hhbm);
        fj.eq("ZJHM", zjhm);
        fj.orderByAsc("ZLLX");
        List<Grxdzllb> zls = grxdzllbMapper.selectList(fj);
        GrdkDTO grdkDTO = new GrdkDTO();
        if (CollUtil.isNotEmpty(zls)) {
            StringBuffer zl1 = new StringBuffer();
            StringBuffer zl2 = new StringBuffer();
            StringBuffer zl3 = new StringBuffer();
            StringBuffer zl4 = new StringBuffer();
            StringBuffer zl5 = new StringBuffer();
            StringBuffer zl6 = new StringBuffer();
            StringBuffer zl7 = new StringBuffer();
            StringBuffer zl8 = new StringBuffer();
            StringBuffer zl9 = new StringBuffer();
            StringBuffer zl10 = new StringBuffer();
            StringBuffer zl11 = new StringBuffer();
            StringBuffer zl12 = new StringBuffer();
            StringBuffer zl13 = new StringBuffer();
            StringBuffer zl14 = new StringBuffer();
            StringBuffer zl15 = new StringBuffer();
            StringBuffer zl16 = new StringBuffer();
            StringBuffer zl17 = new StringBuffer();
            StringBuffer zl18 = new StringBuffer();
            for (int i = 0; i < zls.size(); i++) {
                Grxdzllb grxdzllb = zls.get(i);
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("1")) {
                    grdkDTO.setPic1(zl1.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("2")) {
                    grdkDTO.setPic2(zl2.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("3")) {
                    grdkDTO.setPic3(zl3.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("4")) {
                    grdkDTO.setPic4(zl4.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("5")) {
                    grdkDTO.setPic5(zl5.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("6")) {
                    grdkDTO.setPic6(zl6.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("7")) {
                    grdkDTO.setPic7(zl7.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("8")) {
                    grdkDTO.setPic8(zl8.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("9")) {
                    grdkDTO.setPic9(zl9.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("10")) {
                    grdkDTO.setPic10(zl10.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("11")) {
                    grdkDTO.setPic11(zl11.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("12")) {
                    grdkDTO.setPic12(zl12.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("13")) {
                    grdkDTO.setPic13(zl13.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("14")) {
                    grdkDTO.setPic14(zl14.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("15")) {
                    grdkDTO.setPic15(zl15.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("16")) {
                    grdkDTO.setPic16(zl16.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("17")) {
                    grdkDTO.setPic17(zl17.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
                if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("18")) {
                    grdkDTO.setPic18(zl18.append(grxdzllb.getZllj()).append(",").toString());
                    continue;
                }
            }
        }else {
            return "fail";
        }

        DocxUtil.mkdirCatalog(uploadpath + grdkfj);
        String fileName = zjhm.substring(6) + "grdkfj.docx";
        String upload2 = uploadpath + grdkfj + zjhm.substring(6) + "grdkfj.docx";
        log.info("本次个人信贷 附件 生成位置|{}|", upload2);
        DocxUtil2.exportX2Doc(grdkDTO, uploadpath + moban+ "grdkfj.docx", upload2);
        return fileName;
    }


    @Override
    public String grdkWordSx(String id) {

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id", id);
            CamsZcsxGrxdcjxx grdkspjl = camsZcsxGrxdcjxxMapper.selectById(id);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            GrsxsqbDTO grsxsqbDTO = new GrsxsqbDTO();
            int issc = 0;


            if (grdkspjl != null) {

                //调查报告的拼接值
                //调查报告的拼接值
                String zhen =DAFAULT_WORD;
                String cun =DAFAULT_WORD;
                String yuanyin =DAFAULT_WORD;
                String dk =DAFAULT_WORD;
                String sqje =DAFAULT_WORD;
                String qx =DAFAULT_WORD;
                String age =DAFAULT_WORD;
                String dz =DAFAULT_WORD;
                String mzjhm =DAFAULT_WORD;
                String msjhm =DAFAULT_WORD;
                String mpo ="";
                String mpoage =DAFAULT_WORD;
                String mpozjhm =DAFAULT_WORD;
                String zzc =DAFAULT_WORD;
                String fcdz =DAFAULT_WORD;
                String fcjz =DAFAULT_WORD;
                String qtzc =DAFAULT_WORD;
                String jtgj =DAFAULT_WORD;
                String ldzj =DAFAULT_WORD;
                String zfz =DAFAULT_WORD;
                String jysr =DAFAULT_WORD;
                String sxlr =DAFAULT_WORD;
                String mdyjz =DAFAULT_WORD;
                String mdywz =DAFAULT_WORD;

                //客户经理名称和所属支行
                String khjl = sysUser.getRealname();
                HrBasOrganization hrBasOrganization = hrBasOrganizationMapper.queryByZzbz(sysUser.getOrgCode());

                String zjhm = grdkspjl.getZjhm();
                mzjhm = zjhm;
                age =IdcardUtil.getAgeByIdCard(zjhm)+"";
                grsxsqbDTO.setZdcr_(khjl);
                grsxsqbDTO.setBhjbry_(khjl);
                grsxsqbDTO.setKhmc_(grdkspjl.getKhmc());
                grsxsqbDTO.setKhmc(grdkspjl.getKhmc());
                grsxsqbDTO.setCxxm_(grdkspjl.getKhmc());
                grsxsqbDTO.setCxzjhm_(grdkspjl.getZjhm());
                grsxsqbDTO.setSszh_(hrBasOrganization.getZzjc().replace("支行", ""));
                grsxsqbDTO.setSszh(hrBasOrganization.getZzjc());
                grsxsqbDTO.setGtsszh_(hrBasOrganization.getZzjc());
                grsxsqbDTO.setZjhm(zjhm);
                grsxsqbDTO.setMzdcr("调查人："+khjl);
                //todo 开户银行
                BigDecimal xydk = new BigDecimal(0);
                QueryWrapper khyh = new QueryWrapper();
                khyh.eq("ZJHM", zjhm);
                khyh.orderByAsc(WordConstant.UPDATE_TIME);
                List<CamsZcsxYhdk> yhdks = camsZcsxYhdkMapper.selectList(khyh);
                if (yhdks != null && yhdks.size() > 0) {
                    int yhdkMax = yhdks.size() > 1 ? 2 : yhdks.size();
                    for (int i = 0; i < yhdkMax; i++) {
                        CamsZcsxYhdk camsZcsxYhdk = yhdks.get(i);
                        if (camsZcsxYhdk.getDkje() != null)
                            xydk.add(camsZcsxYhdk.getDkje());
                        if (i == 0) {
                            if (StringUtils.isNotBlank(camsZcsxYhdk.getDkyh()))
                                grsxsqbDTO.setKhyh1(camsZcsxYhdk.getDkyh());
                            if (StringUtils.isNotBlank(camsZcsxYhdk.getDkzh()))
                                grsxsqbDTO.setZh1(camsZcsxYhdk.getDkzh());
                            if (camsZcsxYhdk.getDkje() != null)
                                grsxsqbDTO.setDrye1(camsZcsxYhdk.getDkje().toString());
                        }
                        if (i == 1) {
                            if (StringUtils.isNotBlank(camsZcsxYhdk.getDkyh()))
                                grsxsqbDTO.setKhyh2(camsZcsxYhdk.getDkyh());
                            if (StringUtils.isNotBlank(camsZcsxYhdk.getDkzh()))
                                grsxsqbDTO.setZh2(camsZcsxYhdk.getDkzh());
                            if (camsZcsxYhdk.getDkje() != null)
                                grsxsqbDTO.setDrye2(camsZcsxYhdk.getDkje().toString());
                        }
                    }
                }
                grsxsqbDTO.setXydk(xydk.toString());

                //todo 现有贷款
                if (StringUtils.isNotBlank(grdkspjl.getDwdb()))
                    grsxsqbDTO.setDwdb(grdkspjl.getDwdb());
                if (StringUtils.isNotBlank(grdkspjl.getDbdx()))
                    grsxsqbDTO.setDbdx(grdkspjl.getDbdx());
                if (StringUtils.isNotBlank(grdkspjl.getQtyx()))
                    grsxsqbDTO.setQtyx1(grdkspjl.getQtyx());

                //todo 家庭成员
                QueryWrapper jt = new QueryWrapper();
                jt.eq("HHBM", grdkspjl.getHhbm());
                jt.orderByAsc("YHZGX");
                String gtjkrpo = "";
                List<KhglKhhmcxxGrxd> hmcs = khglKhhmcxxGrxdMapper.selectList(jt);
                if (hmcs != null && hmcs.size() > 0) {
                    for (int i = 0; i < hmcs.size(); i++) {
                        KhglKhhmcxxGrxd khglKhhmcxxGrxd = hmcs.get(i);
                        if (khglKhhmcxxGrxd.getYhzgx() != null && khglKhhmcxxGrxd.getYhzgx().equals("2")) {
                            grsxsqbDTO.setCxpoValue(khglKhhmcxxGrxd.getKhmc());
                            grsxsqbDTO.setCxpozjhm_(khglKhhmcxxGrxd.getZjhm());

                            mpozjhm = khglKhhmcxxGrxd.getZjhm();
                            if (khglKhhmcxxGrxd.getZjhm()!=null && IdcardUtil.isValidCard(khglKhhmcxxGrxd.getZjhm()))
                                mpoage = IdcardUtil.getAgeByIdCard(khglKhhmcxxGrxd.getZjhm())+"";

                            mpo="配偶 "+khglKhhmcxxGrxd.getKhmc()+"，现年 "+mpoage+"岁 ，身份证号 "+mpozjhm+"。";
                        }

                        if (zjhm.equals(khglKhhmcxxGrxd.getZjhm())) {
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getDz())) {
                                grsxsqbDTO.setDz(khglKhhmcxxGrxd.getDz());
                                grsxsqbDTO.setDbrlxdz_(khglKhhmcxxGrxd.getDz());
                                dz = khglKhhmcxxGrxd.getDz();
                            }
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getSjhm())){
                                grsxsqbDTO.setSjhm(khglKhhmcxxGrxd.getSjhm());
                                msjhm = khglKhhmcxxGrxd.getSjhm();
                            }
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getXb())) {
                                if ("1".equals(khglKhhmcxxGrxd.getXb()))
                                    grsxsqbDTO.setXb("男");
                                if ("2".equals(khglKhhmcxxGrxd.getXb()))
                                    grsxsqbDTO.setXb("女");
                            }

                            //获得镇 村
                            if (StringUtils.isNoneBlank(khglKhhmcxxGrxd.getSsyxdy())){
                                QueryWrapper sjj = new QueryWrapper();
                                sjj.eq("DYBH",khglKhhmcxxGrxd.getSsyxdy());
                                Sjyxdygl sjyxdygl = sjyxdyglMapper.selectOne(sjj);

                                if (sjyxdygl != null){
                                    String sj = iDictValueQuery.getyjValue(sjyxdygl.getYjyxdybh());
                                    String ej = iDictValueQuery.getejValue(sjyxdygl.getEjyxdybh());
                                    if (StringUtils.isNoneBlank(sj))
                                        zhen = sj;
                                    if (StringUtils.isNoneBlank(ej))
                                        cun = ej;
                                }

                            }

                        }
                        if ("2".equals(khglKhhmcxxGrxd.getYhzgx())){
                            grsxsqbDTO.setSsjhm(khglKhhmcxxGrxd.getSjhm());
                            gtjkrpo = "."+khglKhhmcxxGrxd.getKhmc();
                        }

                        if (i == 1) {
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getKhmc()))
                                grsxsqbDTO.setFkhmc1(khglKhhmcxxGrxd.getKhmc());
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getYhzgx())) {
                                grsxsqbDTO.setYjkrgx1(iDictValueQuery.getYzhgxValue(khglKhhmcxxGrxd.getYhzgx()));
                            }
                            grsxsqbDTO.setFzjhm1(khglKhhmcxxGrxd.getZjhm());
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getDz()))
                                grsxsqbDTO.setFdz1(khglKhhmcxxGrxd.getDz());
                        }
                        if (i == 2) {
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getKhmc()))
                                grsxsqbDTO.setFkhmc2(khglKhhmcxxGrxd.getKhmc());
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getYhzgx())) {
                                grsxsqbDTO.setYjkrgx2(iDictValueQuery.getYzhgxValue(khglKhhmcxxGrxd.getYhzgx()));
                            }
                            grsxsqbDTO.setFzjhm2(khglKhhmcxxGrxd.getZjhm());
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getDz()))
                                grsxsqbDTO.setFdz2(khglKhhmcxxGrxd.getDz());
                        }
                        if (i == 3) {
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getKhmc()))
                                grsxsqbDTO.setFkhmc3(khglKhhmcxxGrxd.getKhmc());
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getYhzgx())) {
                                grsxsqbDTO.setYjkrgx3(iDictValueQuery.getYzhgxValue(khglKhhmcxxGrxd.getYhzgx()));
                            }
                            grsxsqbDTO.setFzjhm3(khglKhhmcxxGrxd.getZjhm());
                            if (StringUtils.isNotBlank(khglKhhmcxxGrxd.getDz()))
                                grsxsqbDTO.setFdz3(khglKhhmcxxGrxd.getDz());
                        }
                    }
                }


                if (grdkspjl.getSxje() != null) {
                    grsxsqbDTO.setSqsxze(grdkspjl.getSxje() + " 万元");
                    grsxsqbDTO.setMtjk_(grdkspjl.getSxje().toString());
                    grsxsqbDTO.setJk_(grdkspjl.getSxje().toString());
                    dk = grdkspjl.getSxje()+"";

                }
                if (grdkspjl.getSqje() != null){
                    sqje = grdkspjl.getSqje()+"";
                }
                if (StringUtils.isNotBlank(grdkspjl.getJkqx())) {
                    grsxsqbDTO.setQx_(grdkspjl.getJkqx());

                    if (isInteger(grdkspjl.getJkqx())) {
                        Integer num = Integer.valueOf(grdkspjl.getJkqx()) / 12;
                        grsxsqbDTO.setMtqx_(num.toString());
                        grsxsqbDTO.setQx(num.toString());
                        qx = num.toString();
                    }
                }

                //福农卡 便民卡
                if (grdkspjl.getFnkjkje() != null) {
                    //grsxsqbDTO.setFnkAdnFnkk();
                    grsxsqbDTO.setFnkAdnFnkk(grdkspjl.getFnkjkje().toString());
                }
                if (grdkspjl.getBmkjkje() != null) {
                    grsxsqbDTO.setBmkAdnBmkk(grdkspjl.getBmkjkje().toString());
                }
                if (grdkspjl.getDbjkje() != null) {
                    grsxsqbDTO.setDbjeAndDbjek(grdkspjl.getDbjkje().toString());
                }


                //借款用途
                if (StringUtils.isNotBlank(grdkspjl.getJkyt())) {
                    grsxsqbDTO.setGtyt_(grdkspjl.getJkyt());
                    grsxsqbDTO.setMtyt_(grdkspjl.getJkyt());
                    grsxsqbDTO.setYt_(grdkspjl.getJkyt());
                    yuanyin = grdkspjl.getJkyt();
                }
                //还款来源及计划
                StringBuffer stringBuffer = new StringBuffer();
                if (StringUtils.isNotBlank(grdkspjl.getHkfs()))
                    stringBuffer.append(grdkspjl.getHkfs()).append("。");
                if (StringUtils.isNotBlank(grdkspjl.getHkjh()))
                    stringBuffer.append(grdkspjl.getHkjh()).append("。");
                if (stringBuffer.toString().length() > 0){
                    grsxsqbDTO.setHklyjjh(stringBuffer.toString());
                }


                if (grdkspjl.getZczeHj() != null){
                    grsxsqbDTO.setMtsr_(grdkspjl.getZczeHj().multiply(new BigDecimal(10000)).toString());
                }

                //todo 信用信息查询 使用授权书

                //湖南浏阳农村商业银行抵押房地产评估报告单
                List<CamsZcsxDydb> dydbs = camsZcsxDydbMapper.selectList(khyh);
                if (dydbs != null && dydbs.size() > 0) {
                    grsxsqbDTO.setDyk(WordConstant.GOUXUAN);
                    int maxValue = dydbs.size() > 4 ? 4 : dydbs.size();
                    for (int i = 0; i < maxValue; i++) {
                        CamsZcsxDydb camsZcsxDydb = dydbs.get(i);
                        if (i == 0) {
                            if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                                grsxsqbDTO.setFwssqr1(camsZcsxDydb.getSyqr());
                            if (camsZcsxDydb.getSyqrdh() != null)
                                grsxsqbDTO.setLxdh1(camsZcsxDydb.getSyqrdh().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                                grsxsqbDTO.setFcyjkrgx1(camsZcsxDydb.getYbdcrgx());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                                grsxsqbDTO.setFwzlwz1(camsZcsxDydb.getJtwz());

                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                                grsxsqbDTO.setTdzh1(camsZcsxDydb.getTdzh());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                                grsxsqbDTO.setTdlx1(camsZcsxDydb.getTdlx());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                                grsxsqbDTO.setTdyt1(camsZcsxDydb.getTdyt());
                            if (camsZcsxDydb.getTdsymj() != null)
                                grsxsqbDTO.setSyqmj1(camsZcsxDydb.getTdsymj().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                                grsxsqbDTO.setZzrq1(camsZcsxDydb.getTdzzrq());

                            if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                                grsxsqbDTO.setFczh1(camsZcsxDydb.getFczh());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                                grsxsqbDTO.setZcs1(camsZcsxDydb.getZcs());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                                grsxsqbDTO.setFcyt1(camsZcsxDydb.getZdyt());
                            if (camsZcsxDydb.getZdljzmj() != null)
                                grsxsqbDTO.setJzmj1(camsZcsxDydb.getZdljzmj().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                                grsxsqbDTO.setJcsj1(camsZcsxDydb.getFcjcsj());
                            if (camsZcsxDydb.getFclc() != null)
                                grsxsqbDTO.setCc1(camsZcsxDydb.getFclc().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                                grsxsqbDTO.setCcyt1(camsZcsxDydb.getFcyt());
                            if (camsZcsxDydb.getFcjzmj() != null)
                                grsxsqbDTO.setCcjzmj1(camsZcsxDydb.getFcjzmj().toString());

                            if (camsZcsxDydb.getBdcmpfdj() != null)
                                grsxsqbDTO.setMpfdj1(camsZcsxDydb.getBdcmpfdj().toString());
                            if (camsZcsxDydb.getPgz() != null)
                                grsxsqbDTO.setGjz1(camsZcsxDydb.getPgz().toString());
                        }

                        if (i == 1) {
                            if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                                grsxsqbDTO.setFwssqr2(camsZcsxDydb.getSyqr());
                            if (camsZcsxDydb.getSyqrdh() != null)
                                grsxsqbDTO.setLxdh2(camsZcsxDydb.getSyqrdh().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                                grsxsqbDTO.setFcyjkrgx2(camsZcsxDydb.getYbdcrgx());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                                grsxsqbDTO.setFwzlwz2(camsZcsxDydb.getJtwz());

                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                                grsxsqbDTO.setTdzh2(camsZcsxDydb.getTdzh());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                                grsxsqbDTO.setTdlx2(camsZcsxDydb.getTdlx());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                                grsxsqbDTO.setTdyt2(camsZcsxDydb.getTdyt());
                            if (camsZcsxDydb.getTdsymj() != null)
                                grsxsqbDTO.setSyqmj2(camsZcsxDydb.getTdsymj().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                                grsxsqbDTO.setZzrq2(camsZcsxDydb.getTdzzrq());

                            if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                                grsxsqbDTO.setFczh2(camsZcsxDydb.getFczh());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                                grsxsqbDTO.setZcs2(camsZcsxDydb.getZcs());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                                grsxsqbDTO.setFcyt2(camsZcsxDydb.getZdyt());
                            if (camsZcsxDydb.getZdljzmj() != null)
                                grsxsqbDTO.setJzmj2(camsZcsxDydb.getZdljzmj().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                                grsxsqbDTO.setJcsj2(camsZcsxDydb.getFcjcsj());
                            if (camsZcsxDydb.getFclc() != null)
                                grsxsqbDTO.setCc2(camsZcsxDydb.getFclc().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                                grsxsqbDTO.setCcyt2(camsZcsxDydb.getFcyt());
                            if (camsZcsxDydb.getFcjzmj() != null)
                                grsxsqbDTO.setCcjzmj2(camsZcsxDydb.getFcjzmj().toString());

                            if (camsZcsxDydb.getBdcmpfdj() != null)
                                grsxsqbDTO.setMpfdj2(camsZcsxDydb.getBdcmpfdj().toString());
                            if (camsZcsxDydb.getPgz() != null)
                                grsxsqbDTO.setGjz2(camsZcsxDydb.getPgz().toString());
                        }

                        if (i == 2) {
                            if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                                grsxsqbDTO.setFwssqr3(camsZcsxDydb.getSyqr());
                            if (camsZcsxDydb.getSyqrdh() != null)
                                grsxsqbDTO.setLxdh3(camsZcsxDydb.getSyqrdh().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                                grsxsqbDTO.setFcyjkrgx3(camsZcsxDydb.getYbdcrgx());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                                grsxsqbDTO.setFwzlwz3(camsZcsxDydb.getJtwz());

                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                                grsxsqbDTO.setTdzh3(camsZcsxDydb.getTdzh());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                                grsxsqbDTO.setTdlx3(camsZcsxDydb.getTdlx());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                                grsxsqbDTO.setTdyt3(camsZcsxDydb.getTdyt());
                            if (camsZcsxDydb.getTdsymj() != null)
                                grsxsqbDTO.setSyqmj3(camsZcsxDydb.getTdsymj().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                                grsxsqbDTO.setZzrq3(camsZcsxDydb.getTdzzrq());

                            if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                                grsxsqbDTO.setFczh3(camsZcsxDydb.getFczh());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                                grsxsqbDTO.setZcs3(camsZcsxDydb.getZcs());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                                grsxsqbDTO.setFcyt3(camsZcsxDydb.getZdyt());
                            if (camsZcsxDydb.getZdljzmj() != null)
                                grsxsqbDTO.setJzmj3(camsZcsxDydb.getZdljzmj().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                                grsxsqbDTO.setJcsj3(camsZcsxDydb.getFcjcsj());
                            if (camsZcsxDydb.getFclc() != null)
                                grsxsqbDTO.setCc3(camsZcsxDydb.getFclc().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                                grsxsqbDTO.setCcyt3(camsZcsxDydb.getFcyt());
                            if (camsZcsxDydb.getFcjzmj() != null)
                                grsxsqbDTO.setCcjzmj3(camsZcsxDydb.getFcjzmj().toString());

                            if (camsZcsxDydb.getBdcmpfdj() != null)
                                grsxsqbDTO.setMpfdj3(camsZcsxDydb.getBdcmpfdj().toString());
                            if (camsZcsxDydb.getPgz() != null)
                                grsxsqbDTO.setGjz3(camsZcsxDydb.getPgz().toString());
                        }

                        if (i == 3) {
                            if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                                grsxsqbDTO.setFwssqr4(camsZcsxDydb.getSyqr());
                            if (camsZcsxDydb.getSyqrdh() != null)
                                grsxsqbDTO.setLxdh4(camsZcsxDydb.getSyqrdh().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                                grsxsqbDTO.setFcyjkrgx4(camsZcsxDydb.getYbdcrgx());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                                grsxsqbDTO.setFwzlwz4(camsZcsxDydb.getJtwz());

                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                                grsxsqbDTO.setTdzh4(camsZcsxDydb.getTdzh());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                                grsxsqbDTO.setTdlx4(camsZcsxDydb.getTdlx());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                                grsxsqbDTO.setTdyt4(camsZcsxDydb.getTdyt());
                            if (camsZcsxDydb.getTdsymj() != null)
                                grsxsqbDTO.setSyqmj4(camsZcsxDydb.getTdsymj().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                                grsxsqbDTO.setZzrq4(camsZcsxDydb.getTdzzrq());

                            if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                                grsxsqbDTO.setFczh4(camsZcsxDydb.getFczh());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                                grsxsqbDTO.setZcs4(camsZcsxDydb.getZcs());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                                grsxsqbDTO.setFcyt4(camsZcsxDydb.getZdyt());
                            if (camsZcsxDydb.getZdljzmj() != null)
                                grsxsqbDTO.setJzmj4(camsZcsxDydb.getZdljzmj().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                                grsxsqbDTO.setJcsj4(camsZcsxDydb.getFcjcsj());
                            if (camsZcsxDydb.getFclc() != null)
                                grsxsqbDTO.setCc4(camsZcsxDydb.getFclc().toString());
                            if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                                grsxsqbDTO.setCcyt4(camsZcsxDydb.getFcyt());
                            if (camsZcsxDydb.getFcjzmj() != null)
                                grsxsqbDTO.setCcjzmj4(camsZcsxDydb.getFcjzmj().toString());

                            if (camsZcsxDydb.getBdcmpfdj() != null)
                                grsxsqbDTO.setMpfdj4(camsZcsxDydb.getBdcmpfdj().toString());
                            if (camsZcsxDydb.getPgz() != null)
                                grsxsqbDTO.setGjz4(camsZcsxDydb.getPgz().toString());
                        }
                    }
                }
                //保证 质押 信息
                int bz = bzdbMapper.selectCount(khyh).intValue();
                if (bz > 0)
                    grsxsqbDTO.setBzk(WordConstant.GOUXUAN);
                int zy = zydbMapper.selectCount(khyh).intValue();
                if (zy > 0)
                    grsxsqbDTO.setZyk(WordConstant.GOUXUAN);
                int xy = xydbMapper.selectCount(khyh).intValue();
                if (xy > 0)
                    grsxsqbDTO.setXyk(WordConstant.GOUXUAN);


                //
                if (StringUtils.isNotBlank(grdkspjl.getGtjkr())){
                    grsxsqbDTO.setGtjkr_(grdkspjl.getGtjkr());
                    if (gtjkrpo.length() > 0)
                        grsxsqbDTO.setGtjkr_(grdkspjl.getGtjkr()+gtjkrpo);
                }

                if (StringUtils.isNotBlank(grdkspjl.getZdqsr()))
                    grsxsqbDTO.setZdqsr_(grdkspjl.getZdqsr());
                if (StringUtils.isNotBlank(grdkspjl.getQsrsj()))
                    grsxsqbDTO.setSjhm_(grdkspjl.getQsrsj());
                if (StringUtils.isNotBlank(grdkspjl.getQsrcz()))
                    grsxsqbDTO.setCzhm_(grdkspjl.getQsrcz());
                if (StringUtils.isNotBlank(grdkspjl.getQsryx()))
                    grsxsqbDTO.setDzyx_(grdkspjl.getQsryx());
                if (StringUtils.isNotBlank(grdkspjl.getQsrwx()))
                    grsxsqbDTO.setWxh_(grdkspjl.getQsrwx());
                if (StringUtils.isNotBlank(grdkspjl.getQtqsfs()))
                    grsxsqbDTO.setQtfs_(grdkspjl.getQtqsfs());

                //todo 调查报告
                //grsxsqbDTO.setDcbg(getdcbg(hmcs));
                if (grdkspjl.getZczeHj() != null){
                    zzc = grdkspjl.getZczeHj().toString();
                }
                if (grdkspjl.getLdzcHj() != null){
                    ldzj = grdkspjl.getLdzcHj().toString();
                }
                if (grdkspjl.getFzzeHj() != null){
                    zfz = grdkspjl.getFzzeHj().toString();
                }
                if (grdkspjl.getXse() != null){
                    jysr = grdkspjl.getXse().toString();
                }
                if (grdkspjl.getJlr() != null){
                    sxlr = grdkspjl.getJlr().toString();
                }

                //查询房产价值
                QueryWrapper fcqw = new QueryWrapper();
                fcqw.eq("ZJHM",zjhm);
                List<Fwxx> fwxxList = fwxxMapper.selectList(fcqw);
                if (CollUtil.isNotEmpty(fwxxList)){
                    fcdz = "";
                    int intjz = 0;
                    for (int i = 0; i < fwxxList.size(); i++) {

                        Fwxx fwxx = fwxxList.get(i);
                        intjz += fwxx.getPjgz().intValue();
                        if (i == fwxxList.size() - 1){
                            fcdz+=fwxx.getJtwz();
                        }else {
                            fcdz += fwxx.getJtwz()+",";
                        }
                    }
                    fcjz = intjz+"";
                }

                String pljzByZjhm = clxxMapper.getPljzByZjhm(zjhm);
                if (StringUtils.isNoneBlank(pljzByZjhm)){
                    jtgj = pljzByZjhm;
                }

                String pjgByZjhm = qtglzcMapper.getPjgByZjhm(zjhm);
                if (StringUtils.isNoneBlank(pjgByZjhm)){
                    qtzc = pjgByZjhm;
                }

                grsxsqbDTO.setMName("关于"+grdkspjl.getKhmc()+"申请贷款在贷前调查报告");
                grsxsqbDTO.setMHeader("  "+zhen+ " "+cun+"居民 "+grdkspjl.getKhmc()+" 因 "+yuanyin+" 缺少流动资金，向我行申请贷款 "+sqje+" 万元，期限 "+qx+" 年。我行对其调查如下：  " );
                grsxsqbDTO.setMYi("  借款申请人 "+grdkspjl.getKhmc()+",现年 "+age+"岁，住 "+dz+" ,身份证号码 "+zjhm+" ,手机号码 "+msjhm+" 。"+mpo+"该户主要以 "+grdkspjl.getKhmc()+" 为主。");
                grsxsqbDTO.setMSan("  该户总资产经调查估值 "+ zzc+" 万元，其中 "+fcdz+ " 房产价值 "+fcjz+" 万元"+
                        "，家用电器      万元，交通工具"+jtgj+"万元，流动资金 "+ldzj+" 万元,其他资产 "+qtzc+" 万元，负债 "+zfz+" 万元。");
                grsxsqbDTO.setMSi("  该户去年经营收入 "+jysr+" 万元，实现利润 "+sxlr+" 万元，创造了较好的效益。");
                grsxsqbDTO.setMWu("  该户因 "+yuanyin+" 缺少资金，向我行申请借款 "+dk+" 万元，计划用 "+stringBuffer.toString()+" 作为还款来源。");
                grsxsqbDTO.setMLiu("  该户贷款自愿以位于 "+mdywz+",  价值 "+mdyjz+ " 万元的商住房作抵押，能办理合法有效的抵押手续。");
                grsxsqbDTO.setMQi("  经调查，该户经营状况良好，信誉优良。抵押物真实，合法，有效，符合授信条件同意授信 "+ dk+" 万元，期限 "+qx+" 年。");

                //附件
                QueryWrapper fj = new QueryWrapper();
                fj.eq("HHBM", grdkspjl.getHhbm());
                fj.orderByAsc("ZLLX");
                List<Grxdzllb> zls = grxdzllbMapper.selectList(fj);
                if (CollUtil.isNotEmpty(zls)) {
                    StringBuffer zl1 = new StringBuffer();
                    StringBuffer zl2 = new StringBuffer();
                    StringBuffer zl3 = new StringBuffer();
                    StringBuffer zl4 = new StringBuffer();
                    StringBuffer zl5 = new StringBuffer();
                    StringBuffer zl6 = new StringBuffer();
                    StringBuffer zl7 = new StringBuffer();
                    StringBuffer zl8 = new StringBuffer();
                    StringBuffer zl9 = new StringBuffer();
                    StringBuffer zl10 = new StringBuffer();
                    StringBuffer zl11 = new StringBuffer();
                    StringBuffer zl12 = new StringBuffer();
                    StringBuffer zl13 = new StringBuffer();
                    StringBuffer zl14 = new StringBuffer();
                    StringBuffer zl15 = new StringBuffer();
                    StringBuffer zl16 = new StringBuffer();
                    StringBuffer zl17 = new StringBuffer();
                    StringBuffer zl18 = new StringBuffer();
                    for (int i = 0; i < zls.size(); i++) {
                        Grxdzllb grxdzllb = zls.get(i);
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("1")) {
                            grsxsqbDTO.setPic1(zl1.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("2")) {
                            grsxsqbDTO.setPic2(zl2.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("3")) {
                            grsxsqbDTO.setPic3(zl3.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("4")) {
                            grsxsqbDTO.setPic4(zl4.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("5")) {
                            grsxsqbDTO.setPic5(zl5.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("6")) {
                            grsxsqbDTO.setPic6(zl6.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("7")) {
                            grsxsqbDTO.setPic7(zl7.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("8")) {
                            grsxsqbDTO.setPic8(zl8.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("9")) {
                            grsxsqbDTO.setPic9(zl9.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("10")) {
                            grsxsqbDTO.setPic10(zl10.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("11")) {
                            grsxsqbDTO.setPic11(zl11.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("12")) {
                            grsxsqbDTO.setPic12(zl12.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("13")) {
                            grsxsqbDTO.setPic13(zl13.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("14")) {
                            grsxsqbDTO.setPic14(zl14.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("15")) {
                            grsxsqbDTO.setPic15(zl15.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("16")) {
                            grsxsqbDTO.setPic16(zl16.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("17")) {
                            grsxsqbDTO.setPic17(zl17.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                        if (grxdzllb != null && grxdzllb.getZllx() != null && grxdzllb.getZllx().equals("18")) {
                            grsxsqbDTO.setPic18(zl18.append(grxdzllb.getZllj()).append(",").toString());
                            continue;
                        }
                    }
                }

                //支行风险评价审查表
                QueryWrapper fx = new QueryWrapper();
                fx.eq("HHBM", grdkspjl.getHhbm());
                fx.eq("ZJHM", zjhm);
                //fx.eq("SPID",grdkspjl.getSpid());
                CamsZcsxFxpjsc camsZcsxFxpjsc = camsZcsxFxpjscMapper.selectOne(fx);
                if (camsZcsxFxpjsc != null) {
                    if (StringUtils.isNotBlank(camsZcsxFxpjsc.getXdzljbys())) {
                        if (camsZcsxFxpjsc.getXdzljbys().contains("1"))
                            grsxsqbDTO.setSc11(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getXdzljbys().contains("2"))
                            grsxsqbDTO.setSc12(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getXdzljbys().contains("3"))
                            grsxsqbDTO.setSc13(WordConstant.GOUXUAN);
                    }

                    if (StringUtils.isNotBlank(camsZcsxFxpjsc.getZtzg())) {
                        if (camsZcsxFxpjsc.getZtzg().contains("1"))
                            grsxsqbDTO.setSc21(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getZtzg().contains("2"))
                            grsxsqbDTO.setSc22(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getZtzg().contains("3"))
                            grsxsqbDTO.setSc23(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getZtzg().contains("4"))
                            grsxsqbDTO.setSc24(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getZtzg().contains("5"))
                            grsxsqbDTO.setSc25(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getZtzg().contains("6"))
                            grsxsqbDTO.setSc26(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getZtzg().contains("7"))
                            grsxsqbDTO.setSc27(WordConstant.GOUXUAN);
                    }

                    if (StringUtils.isNotBlank(camsZcsxFxpjsc.getZcgd())) {
                        if (camsZcsxFxpjsc.getZcgd().contains("1"))
                            grsxsqbDTO.setSc31(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getZcgd().contains("2"))
                            grsxsqbDTO.setSc32(WordConstant.GOUXUAN);

                    }

                    if (StringUtils.isNotBlank(camsZcsxFxpjsc.getXdfx())) {
                        if (camsZcsxFxpjsc.getXdfx().contains("1"))
                            grsxsqbDTO.setSc41(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getXdfx().contains("2"))
                            grsxsqbDTO.setSc42(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getXdfx().contains("3"))
                            grsxsqbDTO.setSc43(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getXdfx().contains("4"))
                            grsxsqbDTO.setSc44(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getXdfx().contains("5"))
                            grsxsqbDTO.setSc45(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getXdfx().contains("6"))
                            grsxsqbDTO.setSc46(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getXdfx().contains("7"))
                            grsxsqbDTO.setSc47(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getXdfx().contains("8"))
                            grsxsqbDTO.setSc48(WordConstant.GOUXUAN);

                    }

                    if (StringUtils.isNotBlank(camsZcsxFxpjsc.getDb())) {
                        if (camsZcsxFxpjsc.getDb().contains("1"))
                            grsxsqbDTO.setSc51(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getDb().contains("2"))
                            grsxsqbDTO.setSc52(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getDb().contains("3"))
                            grsxsqbDTO.setSc53(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getDb().contains("4"))
                            grsxsqbDTO.setSc54(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getDb().contains("5"))
                            grsxsqbDTO.setSc55(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getDb().contains("6"))
                            grsxsqbDTO.setSc56(WordConstant.GOUXUAN);

                    }

                    if (StringUtils.isNotBlank(camsZcsxFxpjsc.getQtzl()))
                        grsxsqbDTO.setSc6(camsZcsxFxpjsc.getQtzl());
                    if (StringUtils.isNotBlank(camsZcsxFxpjsc.getFxdwzsm()))
                        grsxsqbDTO.setSc7(camsZcsxFxpjsc.getFxdwzsm());

                    if (StringUtils.isNotBlank(camsZcsxFxpjsc.getScjl())) {
                        if (camsZcsxFxpjsc.getScjl().contains("1"))
                            grsxsqbDTO.setSc81(WordConstant.GOUXUAN);

                        if (camsZcsxFxpjsc.getScjl().contains("2"))
                            grsxsqbDTO.setSc82(WordConstant.GOUXUAN);
                        if (camsZcsxFxpjsc.getScjl().contains("3"))
                            grsxsqbDTO.setSc83(WordConstant.GOUXUAN);
                    }
                    //集体审批讨论记录及审批责任书
                    GrdkJtsp grdkJtsp = grdkJtspMapper.selectById(id);
                    if (grdkJtsp != null) {
                        if (StringUtils.isNotBlank(grdkJtsp.getJtspsj()))
                            grsxsqbDTO.setSpsj(grdkJtsp.getJtspsj());
                        if (StringUtils.isNotBlank(grdkJtsp.getJtspdd()))
                            grsxsqbDTO.setSpdd(grdkJtsp.getJtspdd());
                        if (StringUtils.isNotBlank(grdkJtsp.getJtspjlr()))
                            grsxsqbDTO.setSpjlr(grdkJtsp.getJtspjlr());
                        if (grdkJtsp.getSnsxze() != null)
                            grsxsqbDTO.setSpsx(grdkJtsp.getSnsxze().toString());

                        if (grdkJtsp.getSnsxzezdk() != null)
                            grsxsqbDTO.setSpdk(grdkJtsp.getSnsxzezdk().toString());
                        if (grdkJtsp.getSnsxzezbmk() != null)
                            grsxsqbDTO.setSpbm(grdkJtsp.getSnsxzezbmk().toString());
                        if (grdkJtsp.getSnsxzezfnk() != null)
                            grsxsqbDTO.setPfn(grdkJtsp.getSnsxzezfnk().toString());
                        if (grdkJtsp.getSnsxzezqt() != null)
                            grsxsqbDTO.setPqt(grdkJtsp.getSnsxzezqt().toString());

                        if (grdkJtsp.getXyyxye() != null)
                            grsxsqbDTO.setPyx(grdkJtsp.getXyyxye().toString());
                        if (grdkJtsp.getXydkye() != null)
                            grsxsqbDTO.setPdk(grdkJtsp.getXydkye().toString());
                        if (StringUtils.isNotBlank(grdkJtsp.getXydkdqrq()))
                            grsxsqbDTO.setPrq(grdkJtsp.getXydkdqrq());
                        if (grdkJtsp.getXydkfnkye() != null)
                            grsxsqbDTO.setKnf(grdkJtsp.getXydkfnkye().toString());
                        if (grdkJtsp.getXydkqt() != null)
                            grsxsqbDTO.setQtp(grdkJtsp.getXydkqt().toString());

                        if (grdkJtsp.getXsqsxje() != null)
                            grsxsqbDTO.setXsx(grdkJtsp.getXsqsxje().toString());
                        if (StringUtils.isNotBlank(grdkJtsp.getXdbfs()))
                            grsxsqbDTO.setPdb(grdkJtsp.getXdbfs());
                        if (grdkJtsp.getXdywjz() != null)
                            grsxsqbDTO.setPdywjz(grdkJtsp.getXdywjz().toString());


                        if (StringUtils.isNotBlank(grdkJtsp.getCsry()))
                            grsxsqbDTO.setCsry(grdkJtsp.getCsry());
                        if (StringUtils.isNotBlank(grdkJtsp.getSdjl()))
                            grsxsqbDTO.setSdjl(grdkJtsp.getSdjl());


                        if (grdkJtsp.getSxze() != null)
                            grsxsqbDTO.setSxze(grdkJtsp.getSxze().toString());
                        if (grdkJtsp.getSxqx() != null)
                            grsxsqbDTO.setPqx(grdkJtsp.getSxqx().toString());
                        if (grdkJtsp.getLlnq() != null)
                            grsxsqbDTO.setN(grdkJtsp.getLlnq().toString());
                        if (grdkJtsp.getLljd() != null)
                            grsxsqbDTO.setJd(grdkJtsp.getLljd().toString());
                        if (grdkJtsp.getDksxed() != null)
                            grsxsqbDTO.setDksx(grdkJtsp.getDksxed().toString());
                        if (grdkJtsp.getBmksxed() != null)
                            grsxsqbDTO.setPbmksx(grdkJtsp.getBmksxed().toString());
                        if (grdkJtsp.getDbsxed() != null)
                            grsxsqbDTO.setPdbsx(grdkJtsp.getDbsxed().toString());
                        if (grdkJtsp.getFnksxed() != null)
                            grsxsqbDTO.setPfnksx(grdkJtsp.getFnksxed().toString());
                        if (StringUtils.isNotBlank(grdkJtsp.getFjtj()))
                            grsxsqbDTO.setPfjtj(grdkJtsp.getFjtj());

                        if (StringUtils.isNotBlank(grdkJtsp.getJtspzzzt()) && "1".equals(grdkJtsp.getJtspzzzt()))
                            issc = 1;

                    }
                }


                //String pro = uploadpath.substring(0, 1);
                DocxUtil.mkdirCatalog(uploadpath + grdksx);
                String fileName = grdkspjl.getId() + "grdk.doc";
                String upload2 = uploadpath + grdksx + grdkspjl.getId() + "grdk.doc";

                /*File file = new File(upload2);
                if (file.exists() && issc == 1) {
                    log.info("已经完成终审  不在生成文档");
                    return fileName;
                }*/

                log.info("本次个人信贷申请书生成位置|{}|", upload2);
                DocxUtil2.exportX2Doc(grsxsqbDTO, uploadpath+moban+"grdk.docx", upload2);

                return fileName;
            }
            return "fail";
    }

    @Override
    public String grdkMtWord(GrdkMtDTO grdkMtDTO) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        grdkMtDTO.setUpdateBy(loginUser.getWorkNo());
        grdkMtDTO.setUpdateTime(new Date());
        int result = camsZcsxGrxdcjxxMapper.updateMt(grdkMtDTO);
        if (StringUtils.isNotBlank(grdkMtDTO.getZczeHj()))
            grdkMtDTO.setZczeHjBig(new BigDecimal(grdkMtDTO.getZczeHj()).multiply(new BigDecimal(10000)));
        String name = createMtWord(grdkMtDTO);
        return name;
    }

    @Override
    public String grdkGtWord(GrdkGtDTO grdkGtDTO) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        grdkGtDTO.setUpdateTime(new Date());
        grdkGtDTO.setUpdateBy(loginUser.getWorkNo());

        camsZcsxGrxdcjxxMapper.updateGt(grdkGtDTO);

        if (StringUtils.isNotBlank(grdkGtDTO.getDz())){
            khglKhhmcxxGrxdMapper.updateDzByZjhm(grdkGtDTO.getZjhm(),grdkGtDTO.getDz());
        }

        grdkGtDTO.setSskhjl(loginUser.getRealname());
        HrBasOrganization hrBasOrganization = hrBasOrganizationMapper.queryByZzbz(loginUser.getOrgCode());
        if (hrBasOrganization != null && StringUtils.isNotBlank(hrBasOrganization.getZzjc())){
            grdkGtDTO.setSszh(hrBasOrganization.getZzjc());
        }
        CamsZcsxGrxdcjxx camsZcsxGrxdcjxx = camsZcsxGrxdcjxxMapper.selectById(grdkGtDTO.getId());
        if (StringUtils.isNotBlank(camsZcsxGrxdcjxx.getSqje()))
            grdkGtDTO.setSqje(camsZcsxGrxdcjxx.getSqje());
        if (StringUtils.isNotBlank(camsZcsxGrxdcjxx.getJkyt()))
            grdkGtDTO.setJkyt(camsZcsxGrxdcjxx.getJkyt());
        if (StringUtils.isNotBlank(camsZcsxGrxdcjxx.getJkqx()))
            grdkGtDTO.setJkqx(camsZcsxGrxdcjxx.getJkqx());

        return createGtWord(grdkGtDTO);
    }

    public String createMtWord(GrdkMtDTO grdkMtDTO){
        ClassPathResource classPathResource = new ClassPathResource(prePath + "grdkmt.doc");
        DocxUtil.mkdirCatalog(uploadpath + grdkmt);
        String upload = uploadpath + grdkmt + grdkMtDTO.getId()+ "grdkmt.doc";
        log.info("个人贷款面谈资料文件存放位置|{}|", classPathResource.getPath());
        DocxUtil.exportDocToAddr(classPathResource, grdkMtDTO, upload);
        return "grdkmt/"+grdkMtDTO.getId()+"grdkmt.doc";
    }

    public String createGtWord(GrdkGtDTO grdkGtDTO){
        ClassPathResource classPathResource = new ClassPathResource(prePath + "grdkgt.doc");
        DocxUtil.mkdirCatalog(uploadpath + grdkgt);
        String upload = uploadpath + grdkgt + grdkGtDTO.getId()+ "grdkgt.doc";
        log.info("个人贷款共同借款人文件存放位置|{}|", classPathResource.getPath());
        DocxUtil.exportDocToAddr(classPathResource, grdkGtDTO, upload);
        return "grdkgt/"+grdkGtDTO.getId()+"grdkgt.doc";
    }

    @Override
    public String grdkDyWord(GrdkdyDTO grdkdyDTO) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        if (grdkdyDTO.getDydbs() != null && grdkdyDTO.getDydbs().size() > 0){
            for (int i = 0; i < grdkdyDTO.getDydbs().size(); i++) {
                Dydb dydb = grdkdyDTO.getDydbs().get(i);
                dydb.setKhmc(grdkdyDTO.getKhmc());
                if (StringUtils.isNotBlank(dydb.getId())){
                    int result = dydbMapper.updateById(dydb);
                    if (result == 0){
                        dydb.setZjhm(grdkdyDTO.getZjhm());
                        dydbMapper.insert(dydb);
                    }
                }else {
                    dydb.setZjhm(grdkdyDTO.getZjhm());
                    dydb.setId(IdUtil.simpleUUID());
                    dydbMapper.insert(dydb);
                }
            }

            GrdkDydbDTO grsxsqbDTO = new GrdkDydbDTO();
            if (grdkdyDTO.getDydbs() != null && grdkdyDTO.getDydbs().size() > 0) {
                List<Dydb> dydbs = grdkdyDTO.getDydbs();
                int maxValue = dydbs.size() > 4 ? 4 : dydbs.size();
                for (int i = 0; i < maxValue; i++) {
                    Dydb camsZcsxDydb = dydbs.get(i);
                    if (i == 0) {
                        if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                            grsxsqbDTO.setFwssqr1(camsZcsxDydb.getSyqr());
                        if (camsZcsxDydb.getSyqrdh() != null)
                            grsxsqbDTO.setLxdh1(camsZcsxDydb.getSyqrdh().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                            grsxsqbDTO.setFcyjkrgx1(camsZcsxDydb.getYbdcrgx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                            grsxsqbDTO.setFwzlwz1(camsZcsxDydb.getJtwz());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                            grsxsqbDTO.setTdzh1(camsZcsxDydb.getTdzh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                            grsxsqbDTO.setTdlx1(camsZcsxDydb.getTdlx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                            grsxsqbDTO.setTdyt1(camsZcsxDydb.getTdyt());
                        if (camsZcsxDydb.getTdsymj() != null)
                            grsxsqbDTO.setSyqmj1(camsZcsxDydb.getTdsymj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                            grsxsqbDTO.setZzrq1(camsZcsxDydb.getTdzzrq());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                            grsxsqbDTO.setFczh1(camsZcsxDydb.getFczh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                            grsxsqbDTO.setZcs1(camsZcsxDydb.getZcs());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                            grsxsqbDTO.setFcyt1(camsZcsxDydb.getZdyt());
                        if (camsZcsxDydb.getZdljzmj() != null)
                            grsxsqbDTO.setJzmj1(camsZcsxDydb.getZdljzmj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                            grsxsqbDTO.setJcsj1(camsZcsxDydb.getFcjcsj());
                        if (camsZcsxDydb.getFclc() != null)
                            grsxsqbDTO.setCc1(camsZcsxDydb.getFclc().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                            grsxsqbDTO.setCcyt1(camsZcsxDydb.getFcyt());
                        if (camsZcsxDydb.getFcjzmj() != null)
                            grsxsqbDTO.setCcjzmj1(camsZcsxDydb.getFcjzmj().toString());

                        if (camsZcsxDydb.getBdcmpfdj() != null)
                            grsxsqbDTO.setMpfdj1(camsZcsxDydb.getBdcmpfdj().toString());
                        if (camsZcsxDydb.getPgz() != null)
                            grsxsqbDTO.setGjz1(camsZcsxDydb.getPgz().toString());
                    }

                    if (i == 1) {
                        if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                            grsxsqbDTO.setFwssqr2(camsZcsxDydb.getSyqr());
                        if (camsZcsxDydb.getSyqrdh() != null)
                            grsxsqbDTO.setLxdh2(camsZcsxDydb.getSyqrdh().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                            grsxsqbDTO.setFcyjkrgx2(camsZcsxDydb.getYbdcrgx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                            grsxsqbDTO.setFwzlwz2(camsZcsxDydb.getJtwz());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                            grsxsqbDTO.setTdzh2(camsZcsxDydb.getTdzh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                            grsxsqbDTO.setTdlx2(camsZcsxDydb.getTdlx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                            grsxsqbDTO.setTdyt2(camsZcsxDydb.getTdyt());
                        if (camsZcsxDydb.getTdsymj() != null)
                            grsxsqbDTO.setSyqmj2(camsZcsxDydb.getTdsymj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                            grsxsqbDTO.setZzrq2(camsZcsxDydb.getTdzzrq());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                            grsxsqbDTO.setFczh2(camsZcsxDydb.getFczh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                            grsxsqbDTO.setZcs2(camsZcsxDydb.getZcs());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                            grsxsqbDTO.setFcyt2(camsZcsxDydb.getZdyt());
                        if (camsZcsxDydb.getZdljzmj() != null)
                            grsxsqbDTO.setJzmj2(camsZcsxDydb.getZdljzmj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                            grsxsqbDTO.setJcsj2(camsZcsxDydb.getFcjcsj());
                        if (camsZcsxDydb.getFclc() != null)
                            grsxsqbDTO.setCc2(camsZcsxDydb.getFclc().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                            grsxsqbDTO.setCcyt2(camsZcsxDydb.getFcyt());
                        if (camsZcsxDydb.getFcjzmj() != null)
                            grsxsqbDTO.setCcjzmj2(camsZcsxDydb.getFcjzmj().toString());

                        if (camsZcsxDydb.getBdcmpfdj() != null)
                            grsxsqbDTO.setMpfdj2(camsZcsxDydb.getBdcmpfdj().toString());
                        if (camsZcsxDydb.getPgz() != null)
                            grsxsqbDTO.setGjz2(camsZcsxDydb.getPgz().toString());
                    }

                    if (i == 2) {
                        if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                            grsxsqbDTO.setFwssqr3(camsZcsxDydb.getSyqr());
                        if (camsZcsxDydb.getSyqrdh() != null)
                            grsxsqbDTO.setLxdh3(camsZcsxDydb.getSyqrdh().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                            grsxsqbDTO.setFcyjkrgx3(camsZcsxDydb.getYbdcrgx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                            grsxsqbDTO.setFwzlwz3(camsZcsxDydb.getJtwz());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                            grsxsqbDTO.setTdzh3(camsZcsxDydb.getTdzh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                            grsxsqbDTO.setTdlx3(camsZcsxDydb.getTdlx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                            grsxsqbDTO.setTdyt3(camsZcsxDydb.getTdyt());
                        if (camsZcsxDydb.getTdsymj() != null)
                            grsxsqbDTO.setSyqmj3(camsZcsxDydb.getTdsymj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                            grsxsqbDTO.setZzrq3(camsZcsxDydb.getTdzzrq());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                            grsxsqbDTO.setFczh3(camsZcsxDydb.getFczh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                            grsxsqbDTO.setZcs3(camsZcsxDydb.getZcs());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                            grsxsqbDTO.setFcyt3(camsZcsxDydb.getZdyt());
                        if (camsZcsxDydb.getZdljzmj() != null)
                            grsxsqbDTO.setJzmj3(camsZcsxDydb.getZdljzmj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                            grsxsqbDTO.setJcsj3(camsZcsxDydb.getFcjcsj());
                        if (camsZcsxDydb.getFclc() != null)
                            grsxsqbDTO.setCc3(camsZcsxDydb.getFclc().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                            grsxsqbDTO.setCcyt3(camsZcsxDydb.getFcyt());
                        if (camsZcsxDydb.getFcjzmj() != null)
                            grsxsqbDTO.setCcjzmj3(camsZcsxDydb.getFcjzmj().toString());

                        if (camsZcsxDydb.getBdcmpfdj() != null)
                            grsxsqbDTO.setMpfdj3(camsZcsxDydb.getBdcmpfdj().toString());
                        if (camsZcsxDydb.getPgz() != null)
                            grsxsqbDTO.setGjz3(camsZcsxDydb.getPgz().toString());
                    }

                    if (i == 3) {
                        if (StringUtils.isNotBlank(camsZcsxDydb.getSyqr()))
                            grsxsqbDTO.setFwssqr4(camsZcsxDydb.getSyqr());
                        if (camsZcsxDydb.getSyqrdh() != null)
                            grsxsqbDTO.setLxdh4(camsZcsxDydb.getSyqrdh().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getYbdcrgx()))
                            grsxsqbDTO.setFcyjkrgx4(camsZcsxDydb.getYbdcrgx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getJtwz()))
                            grsxsqbDTO.setFwzlwz4(camsZcsxDydb.getJtwz());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzh()))
                            grsxsqbDTO.setTdzh4(camsZcsxDydb.getTdzh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdlx()))
                            grsxsqbDTO.setTdlx4(camsZcsxDydb.getTdlx());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdyt()))
                            grsxsqbDTO.setTdyt4(camsZcsxDydb.getTdyt());
                        if (camsZcsxDydb.getTdsymj() != null)
                            grsxsqbDTO.setSyqmj4(camsZcsxDydb.getTdsymj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getTdzzrq()))
                            grsxsqbDTO.setZzrq4(camsZcsxDydb.getTdzzrq());

                        if (StringUtils.isNotBlank(camsZcsxDydb.getFczh()))
                            grsxsqbDTO.setFczh4(camsZcsxDydb.getFczh());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZcs()))
                            grsxsqbDTO.setZcs4(camsZcsxDydb.getZcs());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getZdyt()))
                            grsxsqbDTO.setFcyt4(camsZcsxDydb.getZdyt());
                        if (camsZcsxDydb.getZdljzmj() != null)
                            grsxsqbDTO.setJzmj4(camsZcsxDydb.getZdljzmj().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcjcsj()))
                            grsxsqbDTO.setJcsj4(camsZcsxDydb.getFcjcsj());
                        if (camsZcsxDydb.getFclc() != null)
                            grsxsqbDTO.setCc4(camsZcsxDydb.getFclc().toString());
                        if (StringUtils.isNotBlank(camsZcsxDydb.getFcyt()))
                            grsxsqbDTO.setCcyt4(camsZcsxDydb.getFcyt());
                        if (camsZcsxDydb.getFcjzmj() != null)
                            grsxsqbDTO.setCcjzmj4(camsZcsxDydb.getFcjzmj().toString());

                        if (camsZcsxDydb.getBdcmpfdj() != null)
                            grsxsqbDTO.setMpfdj4(camsZcsxDydb.getBdcmpfdj().toString());
                        if (camsZcsxDydb.getPgz() != null)
                            grsxsqbDTO.setGjz4(camsZcsxDydb.getPgz().toString());
                    }
                }
            }
            HrBasOrganization hrBasOrganization = hrBasOrganizationMapper.queryByZzbz(loginUser.getOrgCode());
            if (hrBasOrganization != null && StringUtils.isNotBlank(hrBasOrganization.getZzjc())){
                grsxsqbDTO.setSszh(hrBasOrganization.getZzjc());
            }

            DocxUtil.mkdirCatalog(uploadpath + grdkdydb);
            String fileName = grdkdyDTO.getId() + "grdkdydb.docx";
            String upload2 = uploadpath + grdkdydb + grdkdyDTO.getId() + "grdkdydb.docx";

                /*File file = new File(upload2);
                if (file.exists() && issc == 1) {
                    log.info("已经完成终审  不在生成文档");
                    return fileName;
                }*/

            log.info("抵押物评估报告单生成位置|{}|", upload2);
            DocxUtil2.exportX2Doc(grsxsqbDTO, uploadpath+moban+"grdkdydb.docx", upload2);

            return "grdkdydb/"+fileName;

        }

        return null;
    }

    @Override
    public String ZxbgWord(ZxbgDTO zxbgDTO) {

        if (StringUtils.isNoneBlank(zxbgDTO.getHhbm())){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("HHBM",zxbgDTO.getHhbm());
            queryWrapper.eq("YHZGX","2");
            List<KhglKhhmcxxGrxd> list = khglKhhmcxxGrxdMapper.selectList(queryWrapper);
            if (CollUtil.isNotEmpty(list)){
                 KhglKhhmcxxGrxd khglKhhmcxxGrxd = list.get(0);
                if (StringUtils.isNotBlank(zxbgDTO.getPname()))
                    khglKhhmcxxGrxd.setKhmc(zxbgDTO.getPname());
                if (StringUtils.isNotBlank(zxbgDTO.getPzjhm()))
                    khglKhhmcxxGrxd.setZjhm(zxbgDTO.getPzjhm());
                khglKhhmcxxGrxdMapper.updateById(khglKhhmcxxGrxd);
            }else {
                zxbgDTO.setKuang(WordConstant.GOUXUAN);
                KhglKhhmcxxGrxd khglKhhmcxxGrxd = new KhglKhhmcxxGrxd();
                khglKhhmcxxGrxd.setId(IdUtil.simpleUUID());
                if (StringUtils.isNotBlank(zxbgDTO.getPname()))
                    khglKhhmcxxGrxd.setKhmc(zxbgDTO.getPname());
                if (StringUtils.isNotBlank(zxbgDTO.getPzjhm()))
                    khglKhhmcxxGrxd.setZjhm(zxbgDTO.getPzjhm());
                if (StringUtils.isNotBlank(zxbgDTO.getHhbm()))
                    khglKhhmcxxGrxd.setHhbm(zxbgDTO.getHhbm());
                khglKhhmcxxGrxd.setYhzgx("2");
                khglKhhmcxxGrxdMapper.insert(khglKhhmcxxGrxd);

            }
        }
/*
        if (StringUtils.isNotBlank(zxbgDTO.getPid())){
            if (StringUtils.isNotBlank(zxbgDTO.getPname()) ||StringUtils.isNotBlank(zxbgDTO.getPzjhm())){
                zxbgDTO.setKuang(WordConstant.GOUXUAN);
                KhglKhhmcxxGrxd khglKhhmcxxGrxd = new KhglKhhmcxxGrxd();
                khglKhhmcxxGrxd.setId(zxbgDTO.getPid());
                if (StringUtils.isNotBlank(zxbgDTO.getPname()))
                    khglKhhmcxxGrxd.setKhmc(zxbgDTO.getPname());
                if (StringUtils.isNotBlank(zxbgDTO.getPzjhm()))
                    khglKhhmcxxGrxd.setZjhm(zxbgDTO.getPzjhm());
                int result = khglKhhmcxxGrxdMapper.updateById(khglKhhmcxxGrxd);
                if (result == 0 ){
                    khglKhhmcxxGrxdMapper.insert(khglKhhmcxxGrxd);
                }
            }
        }else {
            if (StringUtils.isNotBlank(zxbgDTO.getPname()) ||StringUtils.isNotBlank(zxbgDTO.getPzjhm())){
                zxbgDTO.setKuang(WordConstant.GOUXUAN);
                KhglKhhmcxxGrxd khglKhhmcxxGrxd = new KhglKhhmcxxGrxd();
                khglKhhmcxxGrxd.setId(IdUtil.simpleUUID());
                if (StringUtils.isNotBlank(zxbgDTO.getPname()))
                    khglKhhmcxxGrxd.setKhmc(zxbgDTO.getPname());
                if (StringUtils.isNotBlank(zxbgDTO.getPzjhm()))
                    khglKhhmcxxGrxd.setZjhm(zxbgDTO.getPzjhm());
                if (StringUtils.isNotBlank(zxbgDTO.getHhbm()))
                    khglKhhmcxxGrxd.setHhbm(zxbgDTO.getHhbm());
                khglKhhmcxxGrxd.setYhzgx("2");

                QueryWrapper queryWrapper = new QueryWrapper();

                khglKhhmcxxGrxdMapper.insert(khglKhhmcxxGrxd);
            }*/
        return   createZxWord(zxbgDTO);
    }

    public String createZxWord(ZxbgDTO zxbgDTO){
        ClassPathResource classPathResource = new ClassPathResource(prePath + "zxbg.doc");
        DocxUtil.mkdirCatalog(uploadpath + grdkdyzx);
        String upload = uploadpath + grdkdyzx + zxbgDTO.getId()+ "zxbg.doc";
        log.info("个人授信资料补录征信报告文件存放位置|{}|", classPathResource.getPath());
        DocxUtil.exportDocToAddr(classPathResource, zxbgDTO, upload);
        return "grdkzx/"+zxbgDTO.getId()+"zxbg.doc";
    }

    @Override
    public String jtspWord(String id) {
        GrdkJtsp grdkJtsp = grdkJtspMapper.selectById(id);
        JtspDTO grsxsqbDTO = new JtspDTO();
        if (grdkJtsp != null) {
            if (StringUtils.isNotBlank(grdkJtsp.getJtspsj()))
                grsxsqbDTO.setSpsj(grdkJtsp.getJtspsj());
            if (StringUtils.isNotBlank(grdkJtsp.getJtspdd()))
                grsxsqbDTO.setSpdd(grdkJtsp.getJtspdd());
            if (StringUtils.isNotBlank(grdkJtsp.getJtspjlr()))
                grsxsqbDTO.setSpjlr(grdkJtsp.getJtspjlr());
            if (grdkJtsp.getSnsxze() != null)
                grsxsqbDTO.setSpsx(grdkJtsp.getSnsxze().toString());

            if (grdkJtsp.getSnsxzezdk() != null)
                grsxsqbDTO.setSpdk(grdkJtsp.getSnsxzezdk().toString());
            if (grdkJtsp.getSnsxzezbmk() != null)
                grsxsqbDTO.setSpbm(grdkJtsp.getSnsxzezbmk().toString());
            if (grdkJtsp.getSnsxzezfnk() != null)
                grsxsqbDTO.setPfn(grdkJtsp.getSnsxzezfnk().toString());
            if (grdkJtsp.getSnsxzezqt() != null)
                grsxsqbDTO.setPqt(grdkJtsp.getSnsxzezqt().toString());

            if (grdkJtsp.getXyyxye() != null)
                grsxsqbDTO.setPyx(grdkJtsp.getXyyxye().toString());
            if (grdkJtsp.getXydkye() != null)
                grsxsqbDTO.setPdk(grdkJtsp.getXydkye().toString());
            if (StringUtils.isNotBlank(grdkJtsp.getXydkdqrq()))
                grsxsqbDTO.setPrq(grdkJtsp.getXydkdqrq());
            if (grdkJtsp.getXydkfnkye() != null)
                grsxsqbDTO.setKnf(grdkJtsp.getXydkfnkye().toString());
            if (grdkJtsp.getXydkqt() != null)
                grsxsqbDTO.setQtp(grdkJtsp.getXydkqt().toString());

            if (grdkJtsp.getXsqsxje() != null)
                grsxsqbDTO.setXsx(grdkJtsp.getXsqsxje().toString());
            if (StringUtils.isNotBlank(grdkJtsp.getXdbfs()))
                grsxsqbDTO.setPdb(grdkJtsp.getXdbfs());
            if (grdkJtsp.getXdywjz() != null)
                grsxsqbDTO.setPdywjz(grdkJtsp.getXdywjz().toString());


            if (StringUtils.isNotBlank(grdkJtsp.getCsry()))
                grsxsqbDTO.setCsry(grdkJtsp.getCsry());
            if (StringUtils.isNotBlank(grdkJtsp.getSdjl()))
                grsxsqbDTO.setSdjl(grdkJtsp.getSdjl());


            if (grdkJtsp.getSxze() != null){
                grsxsqbDTO.setSxze(Convert.digitToChinese(grdkJtsp.getSxze().multiply(new BigDecimal("10000"))));
            }
            if (grdkJtsp.getSxqx() != null)
                grsxsqbDTO.setPqx(grdkJtsp.getSxqx().toString());
            if (grdkJtsp.getLlnq() != null)
                grsxsqbDTO.setN(grdkJtsp.getLlnq().toString());
            if (grdkJtsp.getLljd() != null)
                grsxsqbDTO.setJd(grdkJtsp.getLljd().toString());
            if (grdkJtsp.getDksxed() != null)
                grsxsqbDTO.setDksx(grdkJtsp.getDksxed().toString());
            if (grdkJtsp.getBmksxed() != null)
                grsxsqbDTO.setPbmksx(grdkJtsp.getBmksxed().toString());
            if (grdkJtsp.getDbsxed() != null)
                grsxsqbDTO.setPdbsx(grdkJtsp.getDbsxed().toString());
            if (grdkJtsp.getFnksxed() != null)
                grsxsqbDTO.setPfnksx(grdkJtsp.getFnksxed().toString());
            if (StringUtils.isNotBlank(grdkJtsp.getFjtj()))
                grsxsqbDTO.setPfjtj(grdkJtsp.getFjtj());

            QueryWrapper jcy = new QueryWrapper();
            jcy.eq("ID",id);
            List<Jtspcy> list = jtspcyMapper.selectList(jcy);
            StringBuffer spryj = new StringBuffer();
            StringBuffer dcryj = new StringBuffer();
            StringBuffer picsqz = new StringBuffer();
            if (CollUtil.isNotEmpty(list)){
                for (int i = 0; i < list.size(); i++) {
                    //1主调查人 2是协调查人 3是审批组组长 4是其他


                    Jtspcy jtspcy = list.get(i);
                        String lrrlx = jtspcy.getZrrlx();
                        if (StringUtils.isNotBlank(lrrlx)){
                            //客户经理
                            if (lrrlx.equals("1")){
                                if (StringUtils.isNotBlank(jtspcy.getBjyj())){
                                    //grsxsqbDTO.setDcryj(getbjyj(j));
                                    dcryj.append("主调查人 ").append(jtspcy.getZrrmc()).append(" 表决情况：").append(getbjyj(jtspcy.getBjyj())).append("  ，");
                                }

                                if (StringUtils.isNotBlank(jtspcy.getQtyj())){
                                    dcryj.append("其他意见：").append(jtspcy.getQtyj()).append("  。").append("\n");
                                }

                                if (StringUtils.isNotBlank(jtspcy.getQmtp())){
                                    grsxsqbDTO.setPiczqz(uploadpath+File.separator+jtspcy.getQmtp());
                                }

                            }

                            //行长
                            if (lrrlx.equals("3")){
                                if (StringUtils.isNotBlank(jtspcy.getQmtp())){
                                    grsxsqbDTO.setPiccqz(uploadpath+File.separator+jtspcy.getQmtp());
                                }

                                if (StringUtils.isNotBlank(jtspcy.getBjyj())){
                                    spryj.append("审批责任人 ").append(jtspcy.getZrrmc()).append(" 表决情况：").append(getbjyj(jtspcy.getBjyj())).append("  ，");
                                }

                                if (StringUtils.isNotBlank(jtspcy.getQtyj())){
                                    spryj.append("其他意见：").append(jtspcy.getQtyj()).append("  。").append("\n");
                                }

                            }


                            //协调查人
                            if (lrrlx.equals("2")){
                                if (StringUtils.isNotBlank(jtspcy.getBjyj())){
                                    dcryj.append("协调查人 ").append(jtspcy.getZrrmc()).append(" 表决情况：").append(getbjyj(jtspcy.getBjyj())).append("  ，");
                                }

                                if (StringUtils.isNotBlank(jtspcy.getQtyj())){
                                    dcryj.append("其他意见：").append(jtspcy.getQtyj()).append("  。").append("\n");
                                }


                                if (StringUtils.isNotBlank(jtspcy.getQmtp())){
                                    grsxsqbDTO.setPicxqz((uploadpath+File.separator+jtspcy.getQmtp()));
                                }
                            }

                            if (lrrlx.equals("4")){
                                if (StringUtils.isNotBlank(jtspcy.getBjyj())){
                                    spryj.append("审批责任人 ").append(jtspcy.getZrrmc()).append(" 表决情况：").append(getbjyj(jtspcy.getBjyj())).append("  ，");
                                }

                                if (StringUtils.isNotBlank(jtspcy.getQtyj())){
                                    spryj.append("其他意见：").append(jtspcy.getQtyj()).append("  。").append("\n");
                                }

                                //审批责任人
                                if (StringUtils.isNotBlank(jtspcy.getQmtp())){
                                    //grsxsqbDTO.setPicsqz(uploadpath+File.separator+jtspcy.getQmtp());
                                    picsqz.append(uploadpath+File.separator+jtspcy.getQmtp()).append(",");
                                }
                            }

                            /*if (StringUtils.isNotBlank(jtspcy.getBjyj())){
                                //grsxsqbDTO.setSpryj(jtspcy.getBjyj());
                                spryj.append(jtspcy.getBjyj()).append(",");
                            }*/




                        }

                }

                if (dcryj.length()>0)
                    grsxsqbDTO.setDcryj(dcryj.toString());
                if (spryj.length()>0)
                    grsxsqbDTO.setSpryj(spryj.toString());
                if (picsqz.length()>0)
                    grsxsqbDTO.setPicsqz(picsqz.toString());

            }

            System.out.println(grsxsqbDTO.toString());
            DocxUtil.mkdirCatalog(uploadpath + jtsp);
            String fileName = id + "jtsp.doc";
            String upload2 = uploadpath + jtsp + id + "jtsp.doc";

            log.info("本次个人信贷申请书生成位置|{}|", upload2);
            DocxUtil2.exportX2Doc(grsxsqbDTO, uploadpath+moban+"jtsp.docx", upload2);

            return "jtsp/"+fileName;
        }
        return "null";
    }


    public String getbjyj(String value){
        if (value.equals("1"))
            return "同意";
        if (value.equals("2"))
            return "否决";
        if (value.equals("3"))
            return "弃权";
        return "同意";
    }
}
