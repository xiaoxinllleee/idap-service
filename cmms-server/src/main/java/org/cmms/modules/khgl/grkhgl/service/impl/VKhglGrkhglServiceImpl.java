package org.cmms.modules.khgl.grkhgl.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.constant.WordConstant;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.dklldj.lldjgl.glzhgl.mapper.CbsInvmBaseMapper;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxx;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxxBc;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxModelInfo;
import org.cmms.modules.khgl.grkhgl.entity.VKhglGrkhgl;
import org.cmms.modules.khgl.grkhgl.mapper.*;
import org.cmms.modules.khgl.grkhgl.service.IVKhglGrkhglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 视图
 * @Author: jeecg-boot
 * @Date: 2020-07-20
 * @Version: V1.0
 */
@Slf4j
@Service
public class VKhglGrkhglServiceImpl extends ServiceImpl<VKhglGrkhglMapper, VKhglGrkhgl> implements IVKhglGrkhglService {
    @Autowired
    CamsZcsxGrpjsxxxMapper camsZcsxGrpjsxxxMapper;
    @Autowired
    CbsInvmBaseMapper cbsInvmBaseMapper;
    @Autowired
    CamsZcsxGrpjsxxxBcMapper camsZcsxGrpjsxxxBcMapper;
    @Autowired
    VKhglGrkhglMapper vKhglGrkhglMapper;
    @Autowired
    IDictValueQuery iDictValueQuery;
    @Autowired
    CamsZcsxModelInfoMapper camsZcsxModelInfoMapper;
    @Autowired
    KhhmcxxMapper khhmcxxMapper;

    private final String PATTERN = "^[0-9]*$";
    /**
     * 存放所有需要用到的参数  给出一个默认值
     */
    CamsZcsxModelInfo camsZcsxModelInfo = null;

    //年龄 年龄得分
    int age = 0;

    public int getAge() {
        //stringBuffer.append(" 当前年龄为 " ).append(" ").append(age).append(" ");
        if (age >= 30 && age < 40){
            //stringBuffer.append(" 得分为 ").append(" ").append(10).append(" ");
            return 10;
        }
        if (age >= 40 && age < 50){
            //stringBuffer.append(" 得分为 ").append(" ").append(8).append(" ");
            return 8;
        }
        if (age >= 20 && age < 30){
            //stringBuffer.append(" 得分为 ").append(" ").append(7).append(" ");
            return 7;
        }
        if (age >= 50 && age < 60){
            //stringBuffer.append(" 得分为 ").append(" ").append(5).append(" ");
            return 5;
        }
        if (age >= 60){
            //stringBuffer.append(" 得分为 ").append(" ").append(4).append(" ");
            return 4;
        }
        if (age >= 10 && age < 20){
            //stringBuffer.append(" 得分为 ").append(" ").append(3).append(" ");
            return 3;
        }
        //stringBuffer.append(" 得分为 ").append(" ").append(0).append(" ");
        return 0;
    }

    //婚姻状况 10未婚 20已婚 30丧偶
    String hyzk = "10";

    public int getMarriage() {
        //stringBuffer.append(" 当前婚姻状况为 " ).append(" ").append(hyzk).append(" ");
        if (hyzk.equals("10")){
            //stringBuffer.append(" 得分为 ").append(" ").append(4).append(" ");
            return 4;
        }
        if (hyzk.equals("20")){
            //stringBuffer.append(" 得分为 ").append(" ").append(10).append(" ");
            return 10;
        }
        if (hyzk.equals("30")){
            //stringBuffer.append(" 得分为 ").append(" ").append(8).append(" ");
            return 8;
        }
        if (hyzk.equals("40")){
            //stringBuffer.append(" 得分为 ").append(" ").append(6).append(" ");
            return 6;
        }
        //stringBuffer.append(" 得分为 ").append(" ").append(0).append(" ");
        return 0;
    }

    //从事职业
    int cszy = 0;
    public int getProfession() {
        //stringBuffer.append(" 当前从事职业为 " ).append(" ").append(cszy).append(" ");
        if (cszy / 100 == 1){
            //stringBuffer.append(" 得分为 ").append(" ").append(10).append(" ");
            return 10;
        }
        if (cszy / 100 == 2){
            //stringBuffer.append(" 得分为 ").append(" ").append(8).append(" ");
            return 8;
        }
        if (cszy / 100 == 3){
            //stringBuffer.append(" 得分为 ").append(" ").append(7).append(" ");
            return 7;
        }
        if (cszy / 100 == 4){
            //stringBuffer.append(" 得分为 ").append(" ").append(6).append(" ");
            return 6;
        }
        if (cszy / 100 == 5){
            //stringBuffer.append(" 得分为 ").append(" ").append(5).append(" ");
            return 5;
        }
        if (cszy / 100 == 6){
            //stringBuffer.append(" 得分为 ").append(" ").append(4).append(" ");
            return 4;
        }
        if (cszy / 100 == 7){
            //stringBuffer.append(" 得分为 ").append(" ").append(9).append(" ");
            return 9;
        }
        if (cszy / 100 == 8){
            //stringBuffer.append(" 得分为 ").append(" ").append(3).append(" ");
            return 3;
        }
        //stringBuffer.append(" 得分为 ").append(" ").append(0).append(" ");
        return 0;
    }

    //职称情况
    int zc = 0;
    public int getCareerTitle() {
        //stringBuffer.append(" 当前职称为 " ).append(" ").append(zc).append(" ");
        if (zc == 4 || zc == 5){
            //stringBuffer.append(" 得分为 ").append(" ").append(10).append(" ");
            return 10;
        }
        if (zc == 3){
            //stringBuffer.append(" 得分为 ").append(" ").append(7).append(" ");
            return 7;
        }
        if (zc == 2 || zc == 1){
            //stringBuffer.append(" 得分为 ").append(" ").append(3).append(" ");
            return 3;
        }
        //stringBuffer.append(" 得分为 ").append(" ").append(0).append(" ");
        return 0;
    }
    StringBuffer stringBuffer = new StringBuffer();
    //家庭人数
    int jtrs = 0;
    public int getFamilyNum() {
        //stringBuffer.append(" 当前家庭人数为 " ).append(" ").append(jtrs).append(" ");
        if (jtrs >= 6){
            //stringBuffer.append(" 得分为 ").append(" ").append(6).append(" ");
            return 6;
        }
        if (jtrs == 5){
            //stringBuffer.append(" 得分为 ").append(" ").append(8).append(" ");
            return 8;
        }
        if (jtrs == 4){
            //stringBuffer.append(" 得分为 ").append(" ").append(10).append(" ");
            return 10;
        }
        if (jtrs == 3){
            //stringBuffer.append(" 得分为 ").append(" ").append(8).append(" ");
            return 8;
        }
        if (jtrs == 2){
            //stringBuffer.append(" 得分为 ").append(" ").append(6).append(" ");
            return 6;
        }
        if (jtrs == 1){
            //stringBuffer.append(" 得分为 ").append(" ").append(5).append(" ");
            return 5;
        }
        //stringBuffer.append(" 得分为 ").append(" ").append(0).append(" ");
        return 0;
    }

    //健康状况
    String jkzk = "0";
    public int getHealth(){
        //stringBuffer.append(" 当前健康情况为 " ).append(" ").append(jkzk).append(" ");
        if (jkzk.equals("0")){
            //stringBuffer.append(" 得分为 ").append(" ").append(10).append(" ");
            return 10;
        }
        if (jkzk.equals("1")){
            //stringBuffer.append(" 得分为 ").append(" ").append(8).append(" ");
            return 8;
        }
        if (jkzk.equals("2")){
            //stringBuffer.append(" 得分为 ").append(" ").append(6).append(" ");
            return 6;
        }
        if (jkzk.equals("3")){
            //stringBuffer.append(" 得分为 ").append(" ").append(4).append(" ");
            return 4;
        }
        if (jkzk.equals("4")){
            //stringBuffer.append(" 得分为 ").append(" ").append(4).append(" ");
            return 4;
        }
        if (jkzk.equals("5")){
            //stringBuffer.append(" 得分为 ").append(" ").append(2).append(" ");
            return 2;
        }
        if (jkzk.equals("6")){
            //stringBuffer.append(" 得分为 ").append(" ").append(2).append(" ");
            return 2;
        }
        //stringBuffer.append(" 得分为 ").append(" ").append(0).append(" ");
        return 0;
    }
    //定 活期余额
    int dhqye = 0;
    // 存入交易额  存入交易次数
    int crjycs = 0;
    BigDecimal crjye = new BigDecimal(0);

    //支出交易额 支出交易次数
    int zcjycs = 0;
    BigDecimal zcjye = new BigDecimal(0);

    //计算存款交易次数 和 支出交易次数 得分
    public int getDepositAmountNum(int num) {
        //stringBuffer.append(" 存款交易次数/支出交易次数 " ).append(" ").append(num).append(" ");
        if (num == 0){
            //stringBuffer.append(" 得分为 ").append(" ").append(0).append(" ");
            return 0;
        }
        if (num > 0 && num <= 10){
            //stringBuffer.append(" 得分为 ").append(" ").append(3).append(" ");
            return 3;
        }
        if (num > 10 && num <= 20){
            //stringBuffer.append(" 得分为 ").append(" ").append(5).append(" ");
            return 5;
        }
        if (num > 20 && num <= 30){
           // stringBuffer.append(" 得分为 ").append(" ").append(7).append(" ");
            return 7;
        }
        if (num > 30){
           // stringBuffer.append(" 得分为 ").append(" ").append(9).append(" ");
            return 9;
        }
        //stringBuffer.append(" 得分为 ").append(" ").append(0).append(" ");
        return 0;
    }

    //存款账户数量
    int ckzhsl = 0;
    //口袋零钱记录数
    int kdlqjls = 0;

    //网银记录数
    int wyjls = 0;

    //手机银行记录数
    int sjyhjls = 0;

    //etc记录数
    int etcjls = 0;

    //农信银记录数
    int nxyjls = 0;

    //水费代收记录数
    int sfds = 0;

    //计算账户数量得分 手机银行 口袋零钱 网银 电话银行 etc nxy sf
    public int getSaveAccountRecord(int num) {
        //stringBuffer.append(" 账户数量得分/手机银行/口袋零钱/网银/电话银行/etc/农信银/水费代收 " ).append(" ").append(num).append(" ");
        if (num > 2){
            //stringBuffer.append(" 得分为 ").append(" ").append(5).append(" ");
            return 5;
        }
        if (num == 2){
            //stringBuffer.append(" 得分为 ").append(" ").append(4).append(" ");
            return 4;
        }
        if (num == 1){
           // stringBuffer.append(" 得分为 ").append(" ").append(3).append(" ");
            return 3;
        }
        //stringBuffer.append(" 得分为 ").append(" ").append(0).append(" ");
        return 0;
    }

    //社会声誉情况
    //家庭成员是否有不良嗜好 默认否 是1 否2
    String shsyBlsh = "2";
    //家庭成员是否勤快 默认非常勤快  1非常勤快 2一般勤快 0否
    String sfqk = "1";
    //家庭成员是否有民间高息贷款 默认否=2   1是2否0不了解
    String shsySfygld = "2";
    //家庭成员是否有打架、闹事等不良行为  1偶尔 2经常 0否
    String shsySfdjns = "0";
    //家庭成员是否有刑事犯罪记录 默认否  1是 2否
    String shsySfxsfz = "2";
    //家庭成员是否涉诉 默认否=2    1是 2否 3不了解
    String shsySfss = "2";
    //家庭成员领取下列补助情况
    int dbbz = 0;
    int wbbz = 0;
    int ybbz = 0;
    int qtbz = 0;
    //对户主评价-品行评价 1良好 2较好 3一般 4差
    String dhzpjPxpj = "1";
    //对户主评价-信用评价 1良好 2较好 3一般 4差
    String dhzpjXypj = "1";

    //家庭净资产
    int jtjzc = 0;
    //合计(家庭收入)
    int jtjsr = 0;
    //开户时间是否满一年
    int khsj = 1;
    // 日平存款
    int rpck = 0;

    //家庭成员是否领取下列补助情况：低保、五保、幼保、其他（建档立卡贫困户）todo

    //判禁止准入、断谨慎准入、选择增长、积极发展 4,3,2,1  修改为5级分类
    int chekTypeShpj = chekTypeShpjResult();

    StringBuffer shpjly = null;
    public int chekTypeShpjResult() {
        shpjly = new StringBuffer();
        //stringBuffer.append(" 社会声誉类型判断 " ).append(WordConstant.FUHAO_HUANHANG);
        if (shsyBlsh.equals("1")){
            //stringBuffer.append(" 家庭成员有不良嗜好 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭成员有不良嗜好 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (sfqk.equals("0")){
            //stringBuffer.append(" 家庭成员不勤快 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭成员不勤快 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (shsySfygld.equals("1")){
            //.append(" 家庭成员有民间高息贷款 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭成员有民间高息贷款 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }

        if (shsySfdjns.equals("1")){
            //stringBuffer.append(" 家庭成员有打架、闹事等不良行为 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭成员有打架、闹事等不良行为 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }

        if (shsySfxsfz.equals("1")){
            //stringBuffer.append(" 家庭成员有刑事犯罪记录 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭成员有刑事犯罪记录 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }

        if (shsySfss.equals("1")){
            //stringBuffer.append(" 家庭成员有涉诉 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭成员有涉诉 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (dhzpjPxpj.equals("4")){
            //stringBuffer.append(" 户主品行评价为 差 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 户主品行评价为 差 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (dhzpjXypj.equals("4")){
            //stringBuffer.append(" 户主信用评价为 差 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 户主信用评价为 差 ").append(WordConstant.FUHAO_HUANHANG);
            //.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (jtjzc < 10){
            //.append(" 家庭净资产小于10万 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭净资产小于10万 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (getAge() == 4){
            //stringBuffer.append(" 户主年龄大于60岁 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 户主年龄大于60岁 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (jtjsr < 1){
            //stringBuffer.append(" 家庭净收入小于1万 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭净收入小于1万 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (khsj == 2){
            //stringBuffer.append(" 未在我行开户或者开户时间不满一年 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 未在我行开户或者开户时间不满一年 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }

        if (rpck < 1){
            //stringBuffer.append(" 日平存款小于1 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 日平存款小于1 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (wbbz == 1){
            //stringBuffer.append(" 家庭成员领取了补助 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭成员领取了补助 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为5,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        /*if (shsyBlsh.equals("1")
                || sfqk.equals("0")
                || shsySfygld.equals("1")
                || shsySfdjns.equals("1")
                || shsySfxsfz.equals("1")
                || shsySfss.equals("1")
                || dhzpjPxpj.equals("4")
                || dhzpjXypj.equals("4")
                || jtjzc < 10
                || getAge() == 4
                || jtjsr < 1
                || khsj == 2
                || rpck < 1
                || wbbz == 1
        ) {
            return 5;
        }*/
        if (dhzpjXypj.equals("3")){
            //stringBuffer.append(" 户主信用评价一般 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 户主信用评价一般 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为4,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 4;
        }
        if (dhzpjXypj.equals("3")){
            //stringBuffer.append(" 户主信用评价一般 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 户主信用评价一般 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为4,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 4;
        }
        if (shsySfdjns.equals("1")){
            //stringBuffer.append(" 家庭成员偶尔有打架、闹事等不良行为 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭成员偶尔有打架、闹事等不良行为 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为4,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 4;
        }
        if (jtjzc < 15){
            //stringBuffer.append(" 家庭净资产小于15万 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭净资产小于15万 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为4,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 4;
        }
        if (jtjsr < 3){
            //stringBuffer.append(" 家庭净收入小于3万 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭净收入小于3万 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为4,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 4;
        }
        /*if (
                dhzpjXypj.equals("3")
                        || shsySfdjns.equals("1")
                        || dhzpjPxpj.equals("3")
                        || jtjzc < 15
                        || jtjsr < 3
        ) {
            return 4;
        }*/

        if (jtjzc < 30){
            //stringBuffer.append(" 家庭净资产小于30万 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭净资产小于30万 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为3,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 3;
        }
        if (jtjsr < 3){
            //stringBuffer.append(" 家庭净收入小于5万 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭净收入小于5万 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为3,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 3;
        }
        /*if (
                jtjzc < 30
                        || jtjsr < 5
        ) {
            return 3;
        }*/
        if (dhzpjXypj.equals("1")){
            //stringBuffer.append(" 户主信用评价一般 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 户主信用评价一般 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为2,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 2;
        }
        if (shsySfss.equals("3")){
            //stringBuffer.append(" 家庭成员涉诉不了解 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭成员涉诉不了解 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为2,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 2;
        }
        if (jtjsr < 10){
            //stringBuffer.append(" 家庭净收入小于10万 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭净收入小于10万 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为2,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 2;
        }
        if (jtjzc < 50){
            //stringBuffer.append(" 家庭净资产小于50万 ").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭净资产小于50万 ").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为2,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 2;
        }

        if (dbbz == 1){
            //stringBuffer.append(" 家庭净领取了低保").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭净领取了低保").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为2,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 2;
        }
        if (ybbz == 1){
            //.append(" 家庭净领取了幼保").append(WordConstant.FUHAO_HUANHANG);
            shpjly.append(" 家庭净领取了幼保").append(WordConstant.FUHAO_HUANHANG);
            //stringBuffer.append(" 类型为2,原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 2;
        }
        /*if (
                dhzpjXypj.equals("1")
                        || shsySfss.equals("2")
                        || shsySfdjns.equals("2")
                        || shsySfygld.equals("2")
                        || sfqk.equals("1")
                        || jtjzc < 50
                        || jtjsr < 10
                        || dbbz == 1
                        || ybbz == 1

        ) {
            return 2;
        }*/
        shpjly.append(" 社会声誉都为优秀，未领取补助，家庭净收入大于10万，家庭净资产大于于50万 ");
        //stringBuffer.append(" 社会声誉评价类型为1类 " ).append(WordConstant.FUHAO_HUANHANG);
        return 1;
    }

    //计算社会评价得分
    int shpjScore = 0;

    public int getShpjScore() {
        if (chekTypeShpj == 5) {
            //stringBuffer.append(" 社会声誉评价类型为5类  起始分为59 " ).append(WordConstant.FUHAO_HUANHANG);
            shpjScore = 59;
            if (getAge() == 4){
                shpjScore -= 4;
                //stringBuffer.append(" 户主年龄大于60岁 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (jtjzc < 10){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭净资产小于10万 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (jtjsr < 1){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭净收入小于10万 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (khsj == 2){
                shpjScore -= 4;
                //stringBuffer.append(" 未开户或者开户时长小于一年 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (shsyBlsh.equals("1")){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭成员有不良嗜好 -4分 " ).append(WordConstant.FUHAO_HUANHANG);

            }
            if (sfqk.equals("3")){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭成员不勤快 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (shsySfygld.equals("1")){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭成员有民间高息贷款 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (shsySfdjns.equals("1")){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭成员有打架、闹事等不良行为 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (shsySfxsfz.equals("1")){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭成员有刑事犯罪记录 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (dhzpjPxpj.equals("4")){
                shpjScore -= 4;
                //stringBuffer.append(" 户主品行评价为差 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (shsySfss.equals("1")){
                shpjScore -= 4;
                //stringBuffer.append("家庭成员涉诉 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (dhzpjXypj.equals("4")){
                shpjScore -= 4;
                //stringBuffer.append(" 户主信用评价为差 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (wbbz == 1){
                shpjScore -= 4;
                //stringBuffer.append("家庭成员领取五保补助 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (ybbz == 1){
                shpjScore -= 4;
                //stringBuffer.append("家庭成员领取幼保补助 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (dbbz == 1){
                shpjScore -= 4;
                ///stringBuffer.append("家庭成员领取低保补助 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
        } else if (chekTypeShpj == 4) {
            stringBuffer.append(" 社会声誉评价类型为4类  起始分为69 " ).append(WordConstant.FUHAO_HUANHANG);
            shpjScore = 69;
            if (jtjzc < 15){
                shpjScore -= 2;
                //stringBuffer.append(" 家庭净资产小于10万 -2分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (jtjsr < 3){
                shpjScore -= 2;
                //stringBuffer.append(" 家庭净收入小于3万 -2分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (dhzpjXypj.equals("3")){
                shpjScore -= 2;
                //stringBuffer.append(" 户主信用评价为一般 -2分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (shsySfdjns.equals("1")){
                shpjScore -= 2;
                //stringBuffer.append(" 家庭成员偶尔打架、闹事等不良行为 -2分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (dhzpjPxpj.equals("3")){
                shpjScore -= 2;
                //stringBuffer.append(" 户主品行评价为一般 -2分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
        } else if (chekTypeShpj == 3) {
            shpjScore = 79;
            stringBuffer.append(" 社会声誉评价类型为3类  起始分为79 " ).append(WordConstant.FUHAO_HUANHANG);
            if (jtjzc < 30){
                shpjScore -= 3;
                //stringBuffer.append(" 家庭净资产小于30万 -3分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (jtjsr < 4){
                shpjScore -= 3;
                //stringBuffer.append(" 家庭净收入小于4万 -3分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (dhzpjXypj.equals("2")){
                shpjScore -= 4;
                //stringBuffer.append(" 户主信用评价为较好 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (shsySfss.equals("3")){
                shpjScore -= 3;
                //stringBuffer.append("家庭成员是否涉诉不了解 -3分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (dhzpjPxpj.equals("2")){
                shpjScore -= 4;
                //stringBuffer.append(" 户主品行评价为较好 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (sfqk.equals("2")){
                shpjScore -= 2;
                //stringBuffer.append(" 家庭一般勤快 -2分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (shsySfygld.equals("0")){
                shpjScore -= 2;
                //stringBuffer.append(" 家庭成员是否有民间高息贷款不了解 -2分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (dbbz == 1){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭成员领取低保 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (ybbz == 1){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭成员领取幼保 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
        } else if (chekTypeShpj == 2) {
            //stringBuffer.append(" 社会声誉评价类型为2类  起始分为85 " ).append(WordConstant.FUHAO_HUANHANG);
            shpjScore = 85;

            if (dhzpjXypj.equals("2")){
                shpjScore -= 4;
                //stringBuffer.append(" 户主信用评价为较好 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (shsySfss.equals("3")){
                shpjScore -= 3;
                //stringBuffer.append("家庭成员是否涉诉不了解 -3分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (dhzpjPxpj.equals("2")){
                shpjScore -= 4;
                //stringBuffer.append(" 户主品行评价为较好 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (sfqk.equals("2")){
                shpjScore -= 2;
                //stringBuffer.append(" 家庭一般勤快 -2分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (shsySfygld.equals("0")){
                shpjScore -= 2;
                //stringBuffer.append(" 家庭成员是否有民间高息贷款不了解 -2分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (dbbz == 1){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭成员领取低保 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }
            if (ybbz == 1){
                shpjScore -= 4;
                //stringBuffer.append(" 家庭成员领取幼保 -4分 " ).append(WordConstant.FUHAO_HUANHANG);
            }

        } else if (chekTypeShpj == 1) {
            shpjScore = 96;
            //stringBuffer.append(" 社会声誉评价类型为3类  起始分为96 " ).append(WordConstant.FUHAO_HUANHANG);
            shpjScore -= 2 * (getHealth() + (Integer.valueOf(dhzpjPxpj).intValue() - 1) + (Integer.valueOf(dhzpjXypj).intValue() - 1));
        }
        //stringBuffer.append(" 社会声誉得分为 " ).append(Max(shpjScore, 3)).append(WordConstant.FUHAO_HUANHANG);
        //stringBuffer.append(WordConstant.FUHAO_HUANHANG);
        return Max(shpjScore, 3);
    }

    //获取资产
    public int checkTop() {
        if (jtjzc < 50 || jtjsr < 10)
            return 0;
        return 1;
    }

    //判断资产
    public int checkTopAsset() {
        if (jtjzc < 50)
            return 0;
        return 1;
    }

    //判断收入
    public int checkTopIncome() {
        if (jtjsr < 10)
            return 0;
        return 1;
    }

    //五级分类
    String wjfl = "1";
    //初评等级 1特技 2优秀 3较好 4一般 5级外
    String cpdj = "1";
    //是否有表外贷款 是=1 否=0
    String bwdk = "0";
    //逾期次数
    int yqcs = 0;
    //# 依据逾期信息 判禁止准入、断谨慎准入、选择增长、积极发展 4,3,2,1  重新改为5级分类
    int checkIndex = checkTypeYqxx();

    StringBuffer yqdjly = null;

    public int checkTypeYqxx() {
        yqdjly = new StringBuffer();
        stringBuffer.append(" 预期信息等级判断开始 " ).append(WordConstant.FUHAO_HUANHANG);
        if (wjfl.equals("5")){
            stringBuffer.append(" 贷款五级分类为损失 " ).append(WordConstant.FUHAO_HUANHANG);
            stringBuffer.append(" 预期信息等级为5 原因如上 " ).append(WordConstant.FUHAO_HUANHANG);

            yqdjly.append(" 贷款五级分类为损失 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (wjfl.equals("4")){
            stringBuffer.append(" 贷款五级分类为次级 " ).append(WordConstant.FUHAO_HUANHANG);
            yqdjly.append(" 贷款五级分类为次级 " ).append(WordConstant.FUHAO_HUANHANG);
            stringBuffer.append(" 预期信息等级为5 原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (wjfl.equals("3")){
            stringBuffer.append(" 贷款五级分类为可疑 " ).append(WordConstant.FUHAO_HUANHANG);
            yqdjly.append(" 贷款五级分类为可疑 " ).append(WordConstant.FUHAO_HUANHANG);
            stringBuffer.append(" 预期信息等级为5 原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }
        if (wjfl.equals("3")){
            stringBuffer.append(" 初评等级为级外 " ).append(WordConstant.FUHAO_HUANHANG);
            yqdjly.append(" 初评等级为级外 " ).append(WordConstant.FUHAO_HUANHANG);
            stringBuffer.append(" 预期信息等级为5 原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 5;
        }

        /*if (
                wjfl.equals("5")
                        || wjfl.equals("4")
                        || wjfl.equals("3")
                        || cpdj.equals("5")
        ) {
            return 5;
        }*/

        if (wjfl.equals("2")){
            stringBuffer.append(" 贷款五级分类为关注 " ).append(WordConstant.FUHAO_HUANHANG);
            yqdjly.append(" 贷款五级分类为关注 " ).append(WordConstant.FUHAO_HUANHANG);
            stringBuffer.append(" 预期信息等级为4 原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 4;
        }
        if (bwdk.equals("1")){
            stringBuffer.append(" 存在表外贷款 " ).append(WordConstant.FUHAO_HUANHANG);
            yqdjly.append(" 存在表外贷款 " ).append(WordConstant.FUHAO_HUANHANG);
            stringBuffer.append(" 预期信息等级为4 原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 4;
        }

        if (yqcs > 0){
            stringBuffer.append(" 有过预期记录 " ).append(WordConstant.FUHAO_HUANHANG);
            yqdjly.append(" 有过预期记录 " ).append(WordConstant.FUHAO_HUANHANG);
            stringBuffer.append(" 预期信息等级为2 原因如上 " ).append(WordConstant.FUHAO_HUANHANG);
            return 2;
        }

        /*if (wjfl.equals("2")
                || bwdk.equals("1")
        ) {
            return 4;
        }*/
        /*if (yqcs > 0)
            return 2;*/
        yqdjly.append(" 无贷款，不存在表外贷款，没有逾期记录 ");
        stringBuffer.append(" 预期信息等级判断等级为 1 " ).append(WordConstant.FUHAO_HUANHANG);
        return 1;
    }

    //授信额度
    int creditLimit = 0;
    //展期金额
    int zqje = 0;
    //贷款余额
    int dkye = 0;
    //是否信贷 1是 0否
    int sfxd = 0;

    //计算逾期信息分数 禁止准入、断谨慎准入、选择增长、积极发展 4,3,2,1
    int yqcsScore = 0;

    public int getYqxxScore() {
        //stringBuffer.append(" 计算逾期信息分数开始 " ).append(WordConstant.FUHAO_HUANHANG);
        //stringBuffer.append(" 等级为 " ).append(checkIndex).append(WordConstant.FUHAO_HUANHANG);
        Double logCreditLimit = Math.log(creditLimit + 1);
        if (checkIndex == 1) {
            yqcsScore = sfxd + logCreditLimit.intValue() - 3 * (zqje);
            yqcsScore = Max(80, Min(99, 85 + yqcsScore));
        } else if (checkIndex == 2) {
            yqcsScore = yqcs * 2 + logCreditLimit.intValue() / 2;
            yqcsScore = Max(70, Min(79, yqcsScore));
        } else if (checkIndex == 4) {
            yqcsScore = -yqcs * 2 + logCreditLimit.intValue() / 2;
            yqcsScore = Max(60, Min(69, 60 + yqcsScore));
        } else if (checkIndex == 5) {
            yqcsScore = Max(0, 59 - 5 * Max(Integer.valueOf(wjfl) - 2, 0) - 5 * Integer.valueOf(bwdk)
                    - 5 * dkye - 3 * yqcs);
        }
        //stringBuffer.append(" 计算逾期信息分数为 " ).append(yqcsScore).append(WordConstant.FUHAO_HUANHANG);
        //stringBuffer.append(WordConstant.FUHAO_HUANHANG);
        return yqcsScore;
    }

    //# 依据年龄、婚姻、学历、人数打分
    public int getGrxxScore() {
        stringBuffer.append(" 依据年龄、婚姻、学历、人数打分 (最高分99)" ).append(WordConstant.FUHAO_HUANHANG);
        stringBuffer.append(" 基础分 40 " + " + 家庭人员得分 "+getFamilyNum()+" + 年龄得分 " + getAge()+" +健康得分 " + getHealth()+" +婚姻状况得分 " + getMarriage()+" +从事行业得分 " + getProfession()+" +职称得分 " + getCareerTitle()+" " ).append(WordConstant.FUHAO_HUANHANG);

        camsZcsxModelInfo.setJtrsdf(getFamilyNum()+StringUtils.EMPTY);
        camsZcsxModelInfo.setNldf(getAge()+StringUtils.EMPTY);
        camsZcsxModelInfo.setJkzkdf(getHealth()+StringUtils.EMPTY);
        camsZcsxModelInfo.setHyzkdf(getMarriage()+StringUtils.EMPTY);
        camsZcsxModelInfo.setCszydf(getProfession()+StringUtils.EMPTY);
        camsZcsxModelInfo.setZcdf(getCareerTitle()+StringUtils.EMPTY);

        int result = Min(99, 40 + getFamilyNum() + getAge() + getHealth() + getMarriage() + getProfession() + getCareerTitle());

        camsZcsxModelInfo.setScoreOne(result+StringUtils.EMPTY);

        stringBuffer.append(" 总得分为 ").append(result).append(WordConstant.FUHAO_HUANHANG);
        stringBuffer.append(WordConstant.FUHAO_HUANHANG);
        log.info("依据年龄、婚姻、学历、人数打分|{}|",result);
        return result;
    }

    //依据财产（活期、定期）直接打分
    public int getCkxxScore() {
        stringBuffer.append(" 依据财产（活期、定期）直接打分 (最高分99)" ).append(WordConstant.FUHAO_HUANHANG);
        double num = 3.2572 * Math.log(dhqye + 1);
        int score = new Double(num).intValue();

        camsZcsxModelInfo.setDqdf(score+StringUtils.EMPTY);

        stringBuffer.append(" 基础分 40 +"  ).append(" 财产得分 ").append(score).append(WordConstant.FUHAO_HUANHANG);
        int result =  Max(0, Min(99, 40 + score));

        camsZcsxModelInfo.setScoreTwo(result+StringUtils.EMPTY);

        stringBuffer.append(" 总得分为 ").append(result).append(WordConstant.FUHAO_HUANHANG);
        stringBuffer.append(WordConstant.FUHAO_HUANHANG);
        log.info("依据财产（活期、定期）直接打分|{}|",result);
        return result;
    }

    //依据交易信息获取与本行的关系分数
    public int getJyxxScore() {
        stringBuffer.append(" 依据交易信息获取与本行的关系直接打分 (最高分99)" ).append(WordConstant.FUHAO_HUANHANG);
        Double num = Math.abs(crjye.doubleValue()) + Math.abs(zcjye.doubleValue());
        Double num1 = 2*Math.log(num+1);
        camsZcsxModelInfo.setCrzcdf(num1.intValue()+StringUtils.EMPTY);
        camsZcsxModelInfo.setZcjycsdf(getDepositAmountNum(zcjycs)+StringUtils.EMPTY);
        camsZcsxModelInfo.setCrjycsdf(getDepositAmountNum(crjycs)+StringUtils.EMPTY);
        camsZcsxModelInfo.setCkzhsldf(getSaveAccountRecord(ckzhsl)+StringUtils.EMPTY);
        camsZcsxModelInfo.setSjyhjlsdf(getSaveAccountRecord(sjyhjls)+StringUtils.EMPTY);
        camsZcsxModelInfo.setKdlqjlsdf(getSaveAccountRecord(kdlqjls)+StringUtils.EMPTY);
        camsZcsxModelInfo.setWyjlsdf(getSaveAccountRecord(wyjls)+StringUtils.EMPTY);
        camsZcsxModelInfo.setEtcjlsdf(getSaveAccountRecord(etcjls)+StringUtils.EMPTY);
        camsZcsxModelInfo.setNxyjlsdf(getSaveAccountRecord(nxyjls)+StringUtils.EMPTY);
        camsZcsxModelInfo.setSfdsdf(getSaveAccountRecord(sfds)+StringUtils.EMPTY);

        double score = 2 * Math.log(num + 1) + getDepositAmountNum(zcjycs) + getDepositAmountNum(crjycs) + getSaveAccountRecord(ckzhsl)
                + getSaveAccountRecord(sjyhjls) + getSaveAccountRecord(kdlqjls) + getSaveAccountRecord(wyjls)
                + getSaveAccountRecord(etcjls) + getSaveAccountRecord(nxyjls) + getSaveAccountRecord(sfds);
        int scoreInt = new Double(score).intValue();
        stringBuffer.append(" 基础分 40 +"  ).append(" 我行关系 ").append(score).append(WordConstant.FUHAO_HUANHANG);
        int result =  Max(0, Min(99, 40 + scoreInt));

        camsZcsxModelInfo.setScoreThree(result+StringUtils.EMPTY);

        stringBuffer.append(" 总得分 ").append(result).append(WordConstant.FUHAO_HUANHANG);
        log.info("依据交易信息获取与本行的关系分数|{}|",result);
        stringBuffer.append(WordConstant.FUHAO_HUANHANG);
        return result;
    }

    String typeFlagIndex = "A";
    int creditAi = 10;
    int score = 60;
    public void productResult() {
        int a = getGrxxScore();
        int b = getCkxxScore();
        int c = getJyxxScore();
        int dIndex = checkTypeYqxx();
        log.info("预期信息等级|{}|",dIndex);
        camsZcsxModelInfo.setYqdj(dIndex+StringUtils.EMPTY);
        camsZcsxModelInfo.setYqdjly(yqdjly.toString());
        int d = getYqxxScore();
        log.info("计算逾期信息分数|{}|",d);
        camsZcsxModelInfo.setYqdjdf(d+StringUtils.EMPTY);

        int eIndex = chekTypeShpjResult();
        log.info("社会声誉等级|{}|",eIndex);
        camsZcsxModelInfo.setShpj(eIndex+StringUtils.EMPTY);
        camsZcsxModelInfo.setShpjly(shpjly.toString());

        int e = getShpjScore();
        log.info("社会声誉等级分数|{}|",e);
        camsZcsxModelInfo.setShpjdf(e+StringUtils.EMPTY);

        stringBuffer.append("计算模型得分开始 ").append(WordConstant.FUHAO_HUANHANG);

        int typeFlag = Max(dIndex, eIndex);

        if (typeFlag == 1) {
            int fiveScore = Max(0, (a + b + c + d + e) / 5 - 90);

            score = 90 + fiveScore;
            creditAi = 30;

            if (score < 91){
                creditAi = 27;
            }

            if (score < 80 || checkTop() == 0) {
                typeFlagIndex = "B";
                creditAi = 24;
            }

            if (score < 75) {
                typeFlagIndex = "B";
                creditAi = 21;
            }
        } else if (typeFlag == 2) {
            int fiveScore = Max(0, (a + b + c + d + e) / 5 - 90);
            score = 80 + fiveScore;
            typeFlagIndex = "B";
            creditAi = 24;

            if (score < 70)
                creditAi = 21;
            if (score < 60)
                creditAi = 18;
        } else if (typeFlag == 3) {
            int fiveScore = Max(0, (a + b + c + d + e) / 5 - 70);
            score = 70 + 10 * fiveScore / 30;
            typeFlagIndex = "C";
            creditAi = 15;

            if (score < 75)
                creditAi = 12;
            if (creditAi < 65)
                creditAi = 9;
        } else if (typeFlag == 4) {
            int fiveScore = Max(0, (a + b + c + d + e) / 5 - 60);
            score = 60 + 10 * fiveScore / 40;
            typeFlagIndex = "D";
            creditAi = 6;

            if (score < 70)
                creditAi = 3;
        } else if (typeFlag == 5) {
            double dscore;
            int three = a + b + c;
            if (d < 60 || e >= 60)
                dscore = 15 * (three + e) / 400 + 50 * d / 100 * 1.5;
            if (e < 60 || d >= 60)
                dscore = 15 * (three + d) / 400 + 50 * e / 100 * 1.5;
            dscore = 15 * three / 300 + 50 * (d + e) / 200 * 1.5;
            score = new Double(dscore).intValue();
            typeFlagIndex = "E";
            creditAi = 0;
            //log.info("总得分|{}|,等级模型|{}|,授信金额|{}|",dscore,typeFlagIndex,creditAi);
        }
        stringBuffer.append(" 总得分 ").append(score).append(WordConstant.FUHAO_HUANHANG);
        stringBuffer.append(" 模型等级 ").append(typeFlagIndex).append(WordConstant.FUHAO_HUANHANG);
        stringBuffer.append(" 授信金额 ").append(creditAi).append(WordConstant.FUHAO_HUANHANG);

        camsZcsxModelInfo.setXtpddj(typeFlagIndex);
        camsZcsxModelInfo.setXtsxje(creditAi+StringUtils.EMPTY);
        camsZcsxModelInfo.setXtpjdf(score+StringUtils.EMPTY);


        log.info("总得分|{}|,等级模型|{}|,授信金额|{}|",score,typeFlagIndex,creditAi);
    }

    public int Max(int... param) {
        int max = 0;
        int length = param.length;
        for (int i = 0; i < length; i++) {
            if (max < param[i])
                max = param[i];
        }
        return max;
    }

    public int Min(int... param) {
        int max = param[0];
        int length = param.length;
        for (int i = 0; i < length; i++) {
            if (max > param[i])
                max = param[i];
        }
        return max;
    }


    @Override
    public int calculateModel(String hhbm,String zjhm) {
        log.info("zjhm = = = "+zjhm);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ZJHM", zjhm);
        //queryWrapper.eq("HHBM", hhbm);

        VKhglGrkhgl vKhglGrkhgl = null;
        List<VKhglGrkhgl> vKhglGrkhglList = vKhglGrkhglMapper.selectList(queryWrapper);
        if (vKhglGrkhglList != null && vKhglGrkhglList.size() > 0)
            vKhglGrkhgl = vKhglGrkhglList.get(0);

        CamsZcsxGrpjsxxx camsZcsxGrpjsxxx = null;
        List<CamsZcsxGrpjsxxx> list = camsZcsxGrpjsxxxMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0)
            camsZcsxGrpjsxxx = list.get(0);

        CamsZcsxGrpjsxxxBc camsZcsxGrpjsxxxBc = null;
        List<CamsZcsxGrpjsxxxBc> list2 = camsZcsxGrpjsxxxBcMapper.selectList(queryWrapper);
        if (list2 != null && list2.size() > 0)
            camsZcsxGrpjsxxxBc = list2.get(0);

        if (vKhglGrkhgl == null && camsZcsxGrpjsxxx == null && camsZcsxGrpjsxxxBc == null){
            log.info("数据库中无此人信息,证件号码|{}|,本次不计算模型",zjhm);
            return 0;
        }



        resetParam();

        camsZcsxModelInfo.setZjhm(zjhm);
        camsZcsxModelInfo.setHhbm(hhbm);

        if (vKhglGrkhgl != null) {
            //赋值
            if (IdcardUtil.isValidCard(zjhm)){
                age = IdcardUtil.getAgeByIdCard(zjhm);
                camsZcsxModelInfo.setNl(IdcardUtil.getAgeByIdCard(zjhm)+"");
            }
            if (StringUtils.isNotBlank(vKhglGrkhgl.getHyzk())){
                hyzk = vKhglGrkhgl.getHyzk();
                camsZcsxModelInfo.setHyzk(iDictValueQuery.getHyzkValue(hyzk));
            }
            if (StringUtils.isNotBlank(vKhglGrkhgl.getCszy()) && vKhglGrkhgl.getCszy().matches(PATTERN)){
                cszy = Integer.valueOf(vKhglGrkhgl.getCszy());
                camsZcsxModelInfo.setCszy(iDictValueQuery.getCszyValue(vKhglGrkhgl.getCszy()));
            }

            if (StringUtils.isNotBlank(vKhglGrkhgl.getCszy()) && vKhglGrkhgl.getCszy().matches(PATTERN)){
                zc  = 10;
                //camsZcsxModelInfo.setZc(iDictValueQuery.getZcValue(vKhglGrkhgl.getZc()));
                camsZcsxModelInfo.setZc("暂无职称");
            }else {
                camsZcsxModelInfo.setZc("暂无职称");
            }
            /*if (StringUtils.isNotBlank(vKhglGrkhgl.getZc()) && vKhglGrkhgl.getZc().matches(PATTERN)){
                zc = Integer.valueOf(vKhglGrkhgl.getZc());
                camsZcsxModelInfo.setZc(iDictValueQuery.getZcValue(vKhglGrkhgl.getZc()));
            }else {
                camsZcsxModelInfo.setZc("暂无职称");
            }*/

            QueryWrapper jtrsq = new QueryWrapper();
            jtrsq.eq("HHBM",hhbm);
            Integer integer =  khhmcxxMapper.selectCount(jtrsq).intValue();

            if (integer >= 0){
                jtrs = integer;
                camsZcsxModelInfo.setJtrs(jtrs+StringUtils.EMPTY);
            }else {
                jtrs = 1;
                camsZcsxModelInfo.setJtrs(jtrs+StringUtils.EMPTY);
            }

          /*  if (vKhglGrkhgl.getJtrs() != null){
                jtrs = vKhglGrkhgl.getJtrs();
                camsZcsxModelInfo.setJtrs(jtrs+StringUtils.EMPTY);
            }*/
            /*if (StringUtils.isNotBlank(vKhglGrkhgl.getJkzk())){
                jkzk = vKhglGrkhgl.getJkzk();
                camsZcsxModelInfo.setJkzk("");
            }else {
                camsZcsxModelInfo.setJkzk("良好");
            }*/
            jkzk = "10";
            camsZcsxModelInfo.setJkzk("良好");
        }

        if (camsZcsxGrpjsxxxBc != null) {
            //定活期余额
            if (camsZcsxGrpjsxxxBc.getDhqye() != null){
                dhqye = camsZcsxGrpjsxxxBc.getDhqye().intValue();
                camsZcsxModelInfo.setDq(dhqye+StringUtils.EMPTY);
            }
            if (camsZcsxGrpjsxxxBc.getCrjycs() != null) {
                crjycs = camsZcsxGrpjsxxxBc.getCrjycs().intValue();
                crjye = camsZcsxGrpjsxxxBc.getCrjye();

                camsZcsxModelInfo.setCrjye(camsZcsxGrpjsxxxBc.getCrjye()+StringUtils.EMPTY);
                camsZcsxModelInfo.setCrjycs(crjycs+StringUtils.EMPTY);
            }
            if (camsZcsxGrpjsxxxBc.getZcjycs() != null) {
                zcjycs = camsZcsxGrpjsxxxBc.getZcjycs().intValue();
                zcjye = camsZcsxGrpjsxxxBc.getZcjye();

                camsZcsxModelInfo.setZcjye(zcjye+StringUtils.EMPTY);
                camsZcsxModelInfo.setZcjycs(zcjycs+StringUtils.EMPTY);
            }
            if (camsZcsxGrpjsxxxBc.getCkzhs() != null){
                ckzhsl = camsZcsxGrpjsxxxBc.getCkzhs();

                camsZcsxModelInfo.setCkzhsl(ckzhsl+StringUtils.EMPTY);
            }
            if (camsZcsxGrpjsxxxBc.getKdlqjls() != null){
                kdlqjls = camsZcsxGrpjsxxxBc.getKdlqjls();
                camsZcsxModelInfo.setKdlqjls(kdlqjls+"");
            }
            if (camsZcsxGrpjsxxxBc.getWyjls() != null){
                wyjls = camsZcsxGrpjsxxxBc.getWyjls();
                camsZcsxModelInfo.setWyjls(wyjls+"");
            }
            if (camsZcsxGrpjsxxxBc.getSjyhjls() != null){
                sjyhjls = camsZcsxGrpjsxxxBc.getSjyhjls();
                camsZcsxModelInfo.setSjyhjls(sjyhjls+StringUtils.EMPTY);
            }
            if (camsZcsxGrpjsxxxBc.getEtcjls() != null){
                etcjls = camsZcsxGrpjsxxxBc.getEtcjls();
                camsZcsxModelInfo.setEtcjls(etcjls+StringUtils.EMPTY);
            }
            if (camsZcsxGrpjsxxxBc.getNxyjls() != null){
                nxyjls = camsZcsxGrpjsxxxBc.getNxyjls();
                camsZcsxModelInfo.setNxyjls(nxyjls+StringUtils.EMPTY);
            }

            //开户时间是否满一年
            if (camsZcsxGrpjsxxxBc.getKhsj() != null) {
                long result = DateUtil.betweenYear(camsZcsxGrpjsxxxBc.getKhsj(), new Date(), false);
                if (result >= 1)
                    khsj = 1;
                khsj = 2;
            }
            //存款日平
            if (camsZcsxGrpjsxxxBc.getCkrp() != null)
                rpck = camsZcsxGrpjsxxxBc.getCkrp().intValue();

            //五级分类
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxxBc.getWjfl()))
                wjfl = camsZcsxGrpjsxxxBc.getWjfl();

            //初评等级
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getCpdj()))
                cpdj = camsZcsxGrpjsxxx.getCpdj();
            //是否有表外贷款
            if (camsZcsxGrpjsxxxBc.getBwdk() != null) {
                if (camsZcsxGrpjsxxxBc.getBwdk().intValue() > 0)
                    bwdk = "1";
                bwdk = "2";
            }
            //授信额度
            if (camsZcsxGrpjsxxxBc.getSxed() != null)
                creditLimit = camsZcsxGrpjsxxxBc.getSxed().intValue();
        }


        if (camsZcsxGrpjsxxx != null) {
            //社会声誉
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsyBlsh()))
                shsyBlsh = camsZcsxGrpjsxxx.getShsyBlsh();
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfqk()))
                sfqk = camsZcsxGrpjsxxx.getShsySfqk();
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfygld()))
                shsySfygld = camsZcsxGrpjsxxx.getShsySfygld();
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfdjns()))
                shsySfdjns = camsZcsxGrpjsxxx.getShsySfdjns();
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfxsfz()))
                shsySfxsfz = camsZcsxGrpjsxxx.getShsySfxsfz();
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySfss()))
                shsySfss = camsZcsxGrpjsxxx.getShsySfss();
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getDhzpjPxpj()))
                dhzpjPxpj = camsZcsxGrpjsxxx.getDhzpjPxpj();
            //家庭成员领取下列补助情况
            if (StringUtils.isNotBlank(camsZcsxGrpjsxxx.getShsySflqbz())) {
                String[] strings = camsZcsxGrpjsxxx.getShsySflqbz().split(",");
                List<String> strings1 = Arrays.asList(strings);
                if (strings1.contains("1"))
                    dbbz = 1;
                if (strings1.contains("2"))
                    wbbz = 1;
                if (strings1.contains("3"))
                    ybbz = 1;
                if (strings1.contains("4"))
                    qtbz = 1;
            }

            //家庭净资产
            if (camsZcsxGrpjsxxx.getJtjzc() != null)
                jtjzc = camsZcsxGrpjsxxx.getJtjzc().intValue();
            if (camsZcsxGrpjsxxx.getJtjsr() != null)
                jtjsr = camsZcsxGrpjsxxx.getJtjsr().intValue();

        }
        //计算方法
        productResult();

        //写入结果
        //log.info("评分过程|{}|",stringBuffer.toString());
        int a = camsZcsxGrpjsxxxBcMapper.updateAiResult(score,typeFlagIndex,creditAi,stringBuffer.toString(),zjhm);
        camsZcsxModelInfoMapper.delete(queryWrapper);
        camsZcsxModelInfo.setId(IdUtil.simpleUUID());
        camsZcsxModelInfoMapper.insert(camsZcsxModelInfo);

        return a;
    }

    @Override
    public int addJtcyxx(VKhglGrkhgl v) {
        return vKhglGrkhglMapper.addJtcyxx(v);
    }


    public void resetParam() {
        log.info("===开始重置模型计算参数===");
        age = 0;
        stringBuffer = new StringBuffer();
        hyzk = "10";
        cszy = 0;
        zc = 0;
        jtrs = 0;
        jkzk = "0";
        crjycs = 0;
        crjye = new BigDecimal(0);
        zcjycs = 0;
        zcjye = new BigDecimal(0);
        ckzhsl = 0;
        kdlqjls = 0;
        wyjls = 0;
        sjyhjls = 0;
        etcjls = 0;
        nxyjls = 0;
        sfds = 0;
        //社会声誉情况
        //家庭成员是否有不良嗜好 默认否 是1 否2
        shsyBlsh = "2";
        //家庭成员是否勤快 默认非常勤快  1非常勤快 2一般勤快 0否
        sfqk = "1";
        //家庭成员是否有民间高息贷款 默认否=2   1是2否0不了解
        shsySfygld = "2";
        //家庭成员是否有打架、闹事等不良行为  1偶尔 2经常 0否
        shsySfdjns = "0";
        //家庭成员是否有刑事犯罪记录 默认否  1是 2否
        shsySfxsfz = "2";
        //家庭成员是否涉诉 默认否=2    1是 2否 3不了解
        shsySfss = "2";
        //家庭成员领取下列补助情况
        dbbz = 0;
        wbbz = 0;
        ybbz = 0;
        qtbz = 0;

        //对户主评价-品行评价 1良好 2较好 3一般 4差
        dhzpjPxpj = "1";
        //对户主评价-信用评价 1良好 2较好 3一般 4差
        dhzpjXypj = "1";
        //家庭净资产
        jtjzc = 0;
        //合计(家庭收入)
        jtjsr = 0;
        //开户时间是否满一年
        khsj = 1;
        // 日平存款
        rpck = 0;

        //五级分类
        wjfl = "1";
        //初评等级 1特技 2优秀 3较好 4一般 5级外
        cpdj = "1";
        //是否有表外贷款 是=1 否=0
        bwdk = "0";
        //逾期次数
        yqcs = 0;

        //授信额度
        creditLimit = 0;
        //展期金额
        zqje = 0;
        //贷款余额
        dkye = 0;
        //是否信贷 1是 0否
        sfxd = 0;

        typeFlagIndex = "A";
        creditAi = 10;
        camsZcsxModelInfo = new CamsZcsxModelInfo();
        log.info("===重置模型计算参数结束===");
    }


}
