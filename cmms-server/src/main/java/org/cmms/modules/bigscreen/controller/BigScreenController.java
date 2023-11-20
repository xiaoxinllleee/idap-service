package org.cmms.modules.bigscreen.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.api.client.json.Json;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.base.entity.LyNewNsbTableTemp;
import org.cmms.common.system.base.entity.SimpleStandardTable;
import org.cmms.common.util.encryption.RSAEncryptUtil;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.bigscreen.entity.*;
import org.cmms.modules.bigscreen.service.*;
import org.cmms.modules.bigscreen.service.impl.VDpYwhzServiceImpl;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.VNhPjsx;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.entity.ZfcjxxVo;
import org.cmms.modules.tjfx.wgtjfx.wgxxtj.service.IWgxxtjService;
import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.entity.Zcsxxzcjdb;
import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.service.IZcsxxzcjdbService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bigscreen/index")
@Slf4j
public class BigScreenController extends JeecgController<VDpYwhz, IVDpYwhzService> {
    @Autowired
    IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    IYxdyglMainService yxdyglMainService;
    @Autowired
    IDpWgsjtjService dpWgsjtjService;

    @Autowired
    ListToDictUtil listToDictUtil;

    @Autowired
    IWgxxtjService wgxxtjService;


    @Autowired
    IDpIndexYwjbService dpIndexYwjbService;
    @Autowired
    IDpIndexZhxxService dpIndexZhxxService;
    @Autowired
    IDpIndexCkdkwhService dpIndexCkdkwhService;

    @RequestMapping("/info")
    public Result<?> index(){
        JSONObject result = new JSONObject();
        List<DpIndexYwjb> list = dpIndexYwjbService.list();
        if (CollUtil.isNotEmpty(list)){
            result.set("ywjb",list);
        }

        List<DpIndexZhxx> zhxxes = dpIndexZhxxService.list();
        if (CollUtil.isNotEmpty(zhxxes)){
            List<DpIndexZhxx> sjyhList = zhxxes.stream().filter(item -> "1".equals(item.getType())).collect(Collectors.toList());
            List<DpIndexZhxx> ckList = zhxxes.stream().filter(item -> "2".equals(item.getType())).collect(Collectors.toList());
            List<DpIndexZhxx> dkList = zhxxes.stream().filter(item -> "3".equals(item.getType())).collect(Collectors.toList());

            result.set("sjyhList",sjyhList);
            result.set("ckList",ckList);
            result.set("dkList",dkList);
        }

        List<DpIndexCkdkwh> ckdkwhs = dpIndexCkdkwhService.list();
        if (CollUtil.isNotEmpty(ckdkwhs)){
            DpIndexCkdkwh dpIndexCkdkwh = ckdkwhs.get(0);
            //处理下时间  贷款金额  贷款客户
            if (dpIndexCkdkwh.getUpdateTime() != null){
                DateUtil.format(dpIndexCkdkwh.getUpdateTime(),"yyyy年MM月dd日");
            }
            if (dpIndexCkdkwh.getDkje() != null){
                 String s = dpIndexCkdkwh.getDkje().toString();
                 char[] chars = s.toCharArray();
                 dpIndexCkdkwh.setDkjeArr(chars);
            }
            if (dpIndexCkdkwh.getDkkh() != null){
                String s = dpIndexCkdkwh.getDkkh().toString();
                char[] chars = s.toCharArray();
                dpIndexCkdkwh.setDkkhArr(chars);
            }

            result.set("ckdkwh",ckdkwhs.get(0));
        }
        return Result.ok(result);
    }

    @RequestMapping("/info2")
    public void index2(){


        List<DpIndexZhxx> zhxxes = dpIndexZhxxService.list();
        if (CollUtil.isNotEmpty(zhxxes)){
            for (int i = 0; i < zhxxes.size(); i++) {
                DpIndexZhxx dpIndexZhxx = zhxxes.get(i);

                dpIndexZhxx.setSjyhYw(RandomUtil.randomNumbers(5));
                dpIndexZhxx.setSjyhCk(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));
                dpIndexZhxx.setSjyhSl(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));
                dpIndexZhxx.setSjyhYjyl(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));


                dpIndexZhxx.setCkCkye(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));
                dpIndexZhxx.setCkDyye(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));
                dpIndexZhxx.setCkLndq(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));
                dpIndexZhxx.setCkSndq(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));
                dpIndexZhxx.setCkWnjys(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));

                dpIndexZhxx.setDkDk(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));
                dpIndexZhxx.setDkPt(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));
                dpIndexZhxx.setDkYb(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));
                dpIndexZhxx.setDkFp(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));
                dpIndexZhxx.setDkAj(RandomUtil.randomBigDecimal().multiply(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_UP));

                dpIndexZhxxService.updateById(dpIndexZhxx);
            }
        }
    }


    @RequestMapping("/xzinfo")
    public Result<?> xzinfo(String pid){
        JSONObject xzinfo = new JSONObject();
        if (!getRedisQydm().equals(QydmEnums.TIANYI)){
            String redisQydm = "095";//getRedisQydm();
            RequestDataHelper.setRequestData("YXDYGL_MAIN","YXDYGL_MAIN"+redisQydm);
        }
        LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(YxdyglMain::getWgxz,"2","5","1");
        if (StringUtils.isNotBlank(pid)){
            lambdaQueryWrapper.eq(YxdyglMain::getParentId,pid);
        }
        List<YxdyglMain> list = yxdyglMainService.list(lambdaQueryWrapper);
        xzinfo.set("list",list);
        return Result.ok(xzinfo);
    }


    /*
     一些比较少变化的行内数据
     乡镇数量
     行政村数量
     社区居委数量
     */
    @RequestMapping("/noChange")
    public Result<?> noChange(){
        //乡镇数量  行政村数据 社区居委数量
        LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<YxdyglMain> list = yxdyglMainService.list();
        Map<String, Long> counts = list.stream()
                .filter(yxdyglMain -> Arrays.asList("1","2","5").contains(yxdyglMain.getWgxz()))
                .collect(Collectors.groupingBy(YxdyglMain::getWgxz, Collectors.counting()));


        return Result.ok();
    }


    /*
    /wgtjfx/wgxxtj/jbxxlist  cs= 4 外部社保卡 5 脱贫及监测户 13
    /wgtjfx/wgxxtj/queryByWgbh  khxxgl_tjfx_wgxxtj
    /wgtjfx/wgywtj/getWgxxOtherInfo  TGACS_TPS_MCHNT_INFO
    /wgtjfx/wgywtj/getDzyhByjz
    /wgtjfx/wgxxtj/getPyxxPh

    PKG_TJFX
    tjfx_zcsxjdb_cun
    * */
    @Autowired
    IZcsxxzcjdbService zcsxxzcjdbService;
    @RequestMapping("/wgsj")
    public Result<?> wgsj(){
        //乡镇数量  行政村数据 社区居委数量
        LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        List<Zcsxxzcjdb> cunList = zcsxxzcjdbService.getCunList();
        return Result.ok();
    }


    @Autowired
    private INhxqService nhxqService;
    @RequestMapping("/zczpp")
    public void zczpp(){
        String redisQydm = "020";//getRedisQydm();
        RequestDataHelper.setRequestData("YXDYGL_MAIN","YXDYGL_MAIN"+redisQydm);
        List<YxdyglMain> list = yxdyglMainService.list();

        List<YxdyglMain> wgxz2List = list.stream()
                .filter(item -> Arrays.asList("2", "4").contains(item.getWgxz()))
                .collect(Collectors.toList());

        List<YxdyglMain> wgxz1List = list.stream()
                .filter(item -> "1".equals(item.getWgxz()))
                .collect(Collectors.toList());

        List<YxdyglMain> wgxz3List = list.stream()
                .filter(item -> "3".equals(item.getWgxz()))
                .collect(Collectors.toList());
        log.info("===nhxqs加载开始===");
        List<Nhxq> nhxqs = nhxqService.sj10000();
        log.info("===nhxqs加载完成===");
        for (int i = 0; i < nhxqs.size(); i++) {
            Nhxq nhxq = nhxqs.get(i);
            String hjdz = nhxq.getHjdz();
            String wgbh1 = null;
            String wgbh2 = null;
            List<String> wgbh2s = new ArrayList<>();
            String wgbh3 = null;
            //暴力去匹配
            /*for (int j = 0; j < wgxz1List.size(); j++) {
                YxdyglMain yxdyglMain = wgxz1List.get(j);
                String wgmc = yxdyglMain.getWgmc();
                           if (hjdz.contains(wgmc)){
                    wgbh1 = yxdyglMain.getWgbh();
                }
            }
            if (StringUtils.isNotBlank(wgbh1)){
                for (int j = 0; j < wgxz2List.size(); j++) {
                    //匹配村  可能有多个
                    YxdyglMain yxdyglMain = wgxz2List.get(j);
                    String wgmc = yxdyglMain.getWgmc();
                    String wgbh = yxdyglMain.getWgbh();
                    if (hjdz.contains(wgmc) && yxdyglMain.getParentId().equals(wgbh1)){
                        wgbh2 = wgbh;
                        continue;
                    }
                }
            }

            if (StringUtils.isNotBlank(wgbh2)){
                for (int j = 0; j < wgxz3List.size(); j++) {
                    //匹配村  可能有多个
                    YxdyglMain yxdyglMain = wgxz3List.get(j);
                    String wgmc = yxdyglMain.getWgmc();
                    String wgbh = yxdyglMain.getWgbh();
                    if (hjdz.contains(wgmc) && yxdyglMain.getParentId().equals(wgbh2)){
                        wgbh3 = wgbh;
                        continue;
                    }
                }
            }

            if (StringUtils.isNotBlank(wgbh1) && StringUtils.isNotBlank(wgbh2) && StringUtils.isNotBlank(wgbh3)){
                nhxq.setWgbh1(wgbh1);
                nhxq.setWgbh2(wgbh2);
                nhxq.setWgbh3(wgbh3);
                nhxq.setWgbh(wgbh3);
                nhxqService.update10000(nhxq);
            }*/

            //去匹配村 需要去处理同名问题
            for (int j = 0; j <wgxz2List.size() ; j++) {
                YxdyglMain yxdyglMain = wgxz2List.get(j);
                String wgmc = yxdyglMain.getWgmc();
                if (hjdz.contains(wgmc)){
                    wgbh2s.add(yxdyglMain.getWgbh());
                }
            }

            if (CollUtil.isNotEmpty(wgbh2s)){
                for (int j = 0; j < wgxz3List.size(); j++) {
                    YxdyglMain yxdyglMain = wgxz3List.get(j);
                    String wgmc = yxdyglMain.getWgmc();
                    String wgbh = yxdyglMain.getWgbh();
                    String pid = yxdyglMain.getParentId();
                    if (hjdz.contains(wgmc)){
                        for (String s:wgbh2s) {
                            if (s.equals(pid)){
                                wgbh3 = wgbh;
                                wgbh2 = s;
                                continue;
                            }
                        }
                    }

                    if (StringUtils.isNotBlank(wgbh2))
                        continue;
                }

                if (StringUtils.isBlank(wgbh2)){
                    if (wgbh2s.size() == 1)
                        wgbh2 = wgbh2s.get(0);
                }
            }


            if (StringUtils.isNotBlank(wgbh2)){
                //nhxq.setWgbh2(wgbh2);
                //nhxq.setWgbh3(wgbh3);
                nhxqService.update10000(nhxq);
            }



//            for (int j = 0; j < wgxz2List.size(); j++) {
//                //匹配村  可能有多个
//                YxdyglMain yxdyglMain = wgxz2List.get(j);
//                String wgmc = yxdyglMain.getWgmc();
//                if (hjdz.contains(wgmc)){
//                    wgbh2 = zfc(wgbh2,yxdyglMain.getWgbh());
//                }
//            }
//
//            for (int j = 0; j < wgxz3List.size(); j++) {
//                //匹配村  可能有多个
//                YxdyglMain yxdyglMain = wgxz3List.get(j);
//                String wgmc = yxdyglMain.getWgmc();
//                if (hjdz.contains(wgmc)){
//                    wgbh3 = zfc(wgbh3,yxdyglMain.getWgbh());
//                }
//            }

            //匹配之后  会出现  中文重复匹配的情况  要进一步处理

            if (i % 100 == 0){
                log.info("当前数据有{}条，已经处理到{}条",nhxqs.size(),i);
            }

        }


    }

    public String zfc(String s,String s2){
        if (StringUtils.isBlank(s)){
            return s2;
        }else {
            return s+","+s2;
        }
    }


    @RequestMapping("/zczpp1")
    public void zczpp1(){
        LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.isNotNull(Nhxq::getWgbh);
        //lambdaQueryWrapper.eq(Nhxq::getWgbh,"430181117242");

        RequestDataHelper.setRequestData("KHXXGL_KHXQ_NH","KHXXGL_KHXQ_NH020");
        List<Nhxq> list = nhxqService.list(lambdaQueryWrapper);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Nhxq nhxq = list.get(i);
            String id = nhxq.getId();
            String wgbh = nhxq.getWgbh();
            String s = "update KHXXGL_KHXQ_NH set wgbh ='"+wgbh+"' where id ='"+id+"';";
            strings.add(s);
        }
        File touch = FileUtil.touch("E:\\wgbh"+System.currentTimeMillis()+".sql");
        FileUtil.appendUtf8Lines(strings,touch);

    }


    @RequestMapping("/gxzzcsxgzjd")
    public Result<?>gxzzcsxgzjd(String wgbh,String wgxz){
        JSONObject jsonObject = new JSONObject();


        if (StringUtils.isNotBlank(wgbh)){

            if (StringUtils.isNotBlank(wgxz) || "2".equals(wgxz)){
                LambdaQueryWrapper<YxdyglMain> yxdyglMainLambdaQueryWrapper = new LambdaQueryWrapper<>();
                yxdyglMainLambdaQueryWrapper.eq(YxdyglMain::getParentId,wgbh);
                long count = yxdyglMainService.count(yxdyglMainLambdaQueryWrapper);
                jsonObject.set("childCount",count);


                List<ZfcjxxVo> pyxxPh = wgxxtjService.getPyxxPh(Arrays.asList(wgbh.split(",")), "1");

                Map<String, List<ZfcjxxVo>> groupedByWgxz = pyxxPh.stream()
                        .collect(Collectors.groupingBy(ZfcjxxVo::getXmlx));

                List<ZfcjxxVo> zfcjxxVos1 = groupedByWgxz.get("1");
                List<ZfcjxxVo> zfcjxxVos2 = groupedByWgxz.get("2");
                List<ZfcjxxVo> zfcjxxVos3 = groupedByWgxz.get("3");
                List<ZfcjxxVo> zfcjxxVos4 = groupedByWgxz.get("4");

                jsonObject.set("zyhyfl",zfcjxxVos1);
                jsonObject.set("zyjydd",zfcjxxVos2);
                jsonObject.set("cqmf",zfcjxxVos3);
                jsonObject.set("jyfl",zfcjxxVos4);

            }else {
                List<Xzzcsxgzjdcun> cunList = service.getCunList(wgbh);
                List list1 = listToDictUtil.parseDictText(cunList);
                jsonObject.set("gzjd",list1);
            }

            LambdaQueryWrapper<DpWgsjtj> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DpWgsjtj::getWgbh,wgbh);
            List<DpWgsjtj> list = dpWgsjtjService.list(lambdaQueryWrapper);
            jsonObject.set("xqgk",list);


            LambdaQueryWrapper<YxdyglMain> yxdyglMainLambdaQueryWrapper = new LambdaQueryWrapper<>();
            yxdyglMainLambdaQueryWrapper.in(YxdyglMain::getWgxz,"2");
            yxdyglMainLambdaQueryWrapper.eq(YxdyglMain::getParentId,wgbh);
            List<YxdyglMain> yxdyglMains = yxdyglMainService.list(yxdyglMainLambdaQueryWrapper);
            jsonObject.set("mapList",yxdyglMains);

        }else {
            List<Xzzcsxgzjd> maxList = service.getMaxList();
            List list1 = listToDictUtil.parseDictText(maxList);
            jsonObject.set("gzjd",list1);

            if (StringUtils.isNotBlank(wgxz)){
                LambdaQueryWrapper<DpWgsjtj> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(DpWgsjtj::getWgxz,wgxz);
                List<DpWgsjtj> list = dpWgsjtjService.list(lambdaQueryWrapper);
                jsonObject.set("xqgk",list);
            }
        }

        return Result.ok(jsonObject);
    }


    @RequestMapping("/lyfrj")
    public void method1(){
        String path = "E:\\nsb.xlsx";
        File file = FileUtil.file(path);

        List<Nhxq> list = new ArrayList<>();
        List<String> key = new ArrayList<>();

        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(new FileInputStream(file));
            //wb = new HSSFWorkbook(new FileInputStream(file));

            Sheet sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);

            if (lastRowNum >= 3) {

                for (int i = 3; i < lastRowNum; i++) {
                    Nhxq nhxq = new Nhxq();

                    String wgbh = null;
                    Row row = sheet.getRow(i);
                    if (row.getCell(1) != null) {
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        wgbh = row.getCell(1).getStringCellValue().trim();

                        nhxq.setWgbh(wgbh);

                        if (!key.contains(wgbh)){
                            key.add(wgbh);
                        }
                    }

                    String xm = null;
                    if (row.getCell(2) != null) {
                        row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        xm = row.getCell(2).getStringCellValue().trim();

                        nhxq.setKhmc(xm);
                    }

                    String zjhm = null;
                    if (row.getCell(3) != null) {
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        zjhm = row.getCell(3).getStringCellValue().trim();

                        nhxq.setZjhm(zjhm);
                    }

                    String hhbm = null;
                    if (row.getCell(4) != null) {
                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                        hhbm = row.getCell(4).getStringCellValue().trim();

                        nhxq.setHhbm(hhbm);
                    }

                    String yhzgx = null;
                    if (row.getCell(5) != null) {
                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                        yhzgx = row.getCell(5).getStringCellValue().trim();

                        nhxq.setYhzgx(yhzgx);
                    }

                    String sjhm = null;
                    if (row.getCell(6) != null) {
                        row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                        sjhm = row.getCell(6).getStringCellValue().trim();

                        nhxq.setSjhm(sjhm);
                    }

                    String dja = null;
                    if (row.getCell(7) != null) {
                        row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                        dja = row.getCell(7).getStringCellValue().trim();

                        nhxq.setHjdz(dja);
                    }

                    String djb = null;
                    if (row.getCell(8) != null) {
                        row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                        djb = row.getCell(8).getStringCellValue().trim();

                        nhxq.setZz(djb);
                    }




                    list.add(nhxq);
                }



            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("=== 开始输出表格 {}===",key.size());

        Map<String, List<Nhxq>> groupedByWgxz = list.stream()
                .collect(Collectors.groupingBy(Nhxq::getWgbh));

        HSSFWorkbook workbook = new HSSFWorkbook();



        for (int i = 0; i < key.size(); i++) {
            String s = key.get(i);
            log.info("=== {} 开始生成sheet ===",s);
            List<Nhxq> nhxqs = groupedByWgxz.get(s);
            List<List<String>> nsbList = getNsbList(nhxqs);

            HSSFSheet sheet = workbook.createSheet(s);
            LyNewNsbTableTemp lyNewNsbTableTemp = new LyNewNsbTableTemp(workbook,sheet);
            lyNewNsbTableTemp.setTableName();
            lyNewNsbTableTemp.setRowTwoNam();
            lyNewNsbTableTemp.setTableHeader();
            lyNewNsbTableTemp.setTableData(nsbList);
            lyNewNsbTableTemp.setTableFooter();
            lyNewNsbTableTemp.setTableFooter2();


        }

        log.info("=== 开始导出最后的表格 ===");
        try {
            String fileName = "2023年审表" + System.currentTimeMillis() + ".xls";
            String upfile = "E:\\"  + fileName;
            OutputStream out = new FileOutputStream(new File(upfile));
            workbook.write(out);
            workbook.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<List<String>> getNsbList(List<Nhxq> list) {
        List<List<String>> listArrayList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            Nhxq nhxq = list.get(i);
            List<String> colList = Lists.newArrayList();

            colList.add("沙市支行");

            if (StringUtils.isNotBlank(nhxq.getWgbh())){
                colList.add(nhxq.getWgbh());
            }else {
                colList.add("");
            }

            if (StringUtils.isNotBlank(nhxq.getKhmc())){
                colList.add(nhxq.getKhmc());
            }else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(nhxq.getZjhm())){
                colList.add(nhxq.getZjhm());
            }else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(nhxq.getHhbm())){
                colList.add(nhxq.getHhbm());
            }else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(nhxq.getYhzgx())){
                colList.add(nhxq.getYhzgx());
            }else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(nhxq.getSjhm())){
                colList.add(nhxq.getSjhm());
            }else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(nhxq.getHjdz())){
                colList.add(nhxq.getHjdz());
            }else {
                colList.add("");
            }
            if (StringUtils.isNotBlank(nhxq.getZz())){
                colList.add(nhxq.getZz());
            }else {
                colList.add("");
            }
            colList.add("");
            colList.add("");
            colList.add("");
            colList.add("");

            listArrayList.add(colList);
        }
        return listArrayList;
    }

    public static void main(String[] args) {
        BigScreenController bigScreenController = new BigScreenController();
        bigScreenController.method1();
    }
}
