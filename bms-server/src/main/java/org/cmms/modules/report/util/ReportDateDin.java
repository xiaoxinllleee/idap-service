package org.cmms.modules.report.util;

import org.cmms.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by mkeasyx220 on 14-8-8.
 */

public class ReportDateDin {



    //定义时间
    private Date dateBq = null; //本期时间
    private Date dateBq_MM = null;//本期月初时间
    private Date dateBq_QM = null;//期末

    private Date dateSq_MM = null;//上期时间
    private Date dateNc_MM = null;//年初时间--年初时间自动计算为当年第一期
    private Date dateSnm_MM = null;//上年末时间--自动计算为去年最后一期时间

    private Date dateBq_Q = null;//本期时间
    private Date dateBq_Q_QM = null;//期末
    private Date dateSq_Q = null;//上期时间
    private Date dateNc_Q = null;//年初时间--年初时间自动计算为当年第一期


    private Date dateNc_YY = null;//年初时间--年初时间自动计算为当年半年
    private Date dateNc_YYYY = null;//年初时间--年初时间自动计算为当年年末
    private Date dateSnm_Q = null;//上年末时间--自动计算为去年最后一期时间
    private String sfdsjptStr = "false";

    public ReportDateDin(Date sjrq,String sfdsjpt) {
        long nowTime = sjrq.getTime();
        String nowM = DateUtil.format(sjrq, "MM");
        String nowY = DateUtil.format(sjrq, "yyyy");
        sfdsjptStr=sfdsjpt;
        dateBq = sjrq;
        dateBq_MM = DateUtil.parseDateFormat(nowY + nowM + "01", "yyyyMMdd");
        try {
            dateBq_QM= DateUtil.getLastday_Month(dateBq_MM,0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateSq_MM = new Date(DateUtil.getFrontMonthTime(dateBq_MM.getTime(), 1));
        dateNc_MM = DateUtil.parseDateFormat(nowY + "0101", "yyyyMMdd");
        dateSnm_MM = DateUtil.parseDateFormat((Integer.parseInt(nowY) - 1) + "1231", "yyyyMMdd");

        String nowQ = nowM;
        String sqQ = nowQ;
        String sqY = nowY;

        int nowMi = Integer.parseInt(nowM);
        if (nowMi <= 3) {
            nowQ = "03";
            sqQ = "12";
            sqY = (Integer.parseInt(nowY) - 1) + "";
        } else if (nowMi <= 6) {
            nowQ = "06";
            sqQ = "03";
        } else if (nowMi <= 9) {
            nowQ = "09";
            sqQ = "06";
        } else {
            nowQ = "12";
            sqQ = "09";
        }

        dateBq_Q = DateUtil.parseDateFormat(nowY + nowQ + "01", "yyyyMMdd");
        try {
            dateBq_Q_QM= DateUtil.getLastday_Month(dateBq_Q,0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateSq_Q = DateUtil.parseDateFormat(sqY + sqQ + "01", "yyyyMMdd");
        dateNc_Q = DateUtil.parseDateFormat(nowY + "03" + "01", "yyyyMMdd");
        dateNc_YY = DateUtil.parseDateFormat(nowY + "06" + "01", "yyyyMMdd");
        dateNc_YYYY = DateUtil.parseDateFormat(nowY + "12" + "01", "yyyyMMdd");
        dateSnm_Q = DateUtil.parseDateFormat((Integer.parseInt(nowY) - 1) + "12" + "01", "yyyyMMdd");

    }

    /**
     * 替换字符串
     * ${BQ}
     * ${SQ}
     * ${NC}
     * ${SNM}
     *
     * @param val
     * @return
     */
    public String replaceStringVal(String val, String zbwd) {
        String tmp = val;
        if ("MM".equalsIgnoreCase(zbwd)) {
            if (val.contains("${BQ}")) {
                tmp= LangUtil.replace(tmp, "${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
                //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
            }

            if (val.contains("${BQ_YYMM}")) {
                tmp= LangUtil.replace(tmp, "${BQ_YYMM}", DateUtil.formatDateTime("yyMM", getDateBq_MM()));
            }

            if (val.contains("${BQ_YYMMDD}")) {
                tmp= LangUtil.replace(tmp, "${BQ_YYMMDD}", DateUtil.formatDateTime("yyMMdd", getDateBq_MM()));
            }

            if (val.contains("${QC}")) {
                try {
                    tmp= LangUtil.replace(tmp, "${QC}", DateUtil.formatDateTime("yyyyMMdd", DateUtil.getFirstday_Month(getDateBq_MM(),0 )));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
            }

            if (val.contains("${QM}")) {
                tmp= LangUtil.replace(tmp, "${QM}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_QM()));
                //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
            }

            if (val.contains("${QM_YYMM}")) {
                tmp= LangUtil.replace(tmp, "${QM_YYMM}", DateUtil.formatDateTime("yyMM", getDateBq_QM()));
            }

            if (val.contains("${QM_YYMMDD}")) {
                tmp= LangUtil.replace(tmp, "${QM_YYMMDD}", DateUtil.formatDateTime("yyMMdd", getDateBq_QM()));
            }

            if (val.contains("${SQ}")) {
                //tmp = tmp.replaceAll("${SQ}", DateUtil.formatDateTime("yyyyMMdd", getDateSq_MM()));
                tmp= LangUtil.replace(tmp, "${SQ}", DateUtil.formatDateTime("yyyyMMdd", getDateSq_MM()));
            }

            if (val.contains("${SQ_YYMM}")) {
                tmp= LangUtil.replace(tmp, "${SQ_YYMM}", DateUtil.formatDateTime("yyMM", getDateSq_MM()));
            }

            if (val.contains("${SQ_YYMMDD}")) {
                tmp= LangUtil.replace(tmp, "${SQ_YYMMDD}", DateUtil.formatDateTime("yyMMdd", getDateSq_MM()));
            }

            if (val.contains("${SYM}")) {
                //tmp = tmp.replaceAll("${SQ}", DateUtil.formatDateTime("yyyyMMdd", getDateSq_MM()));
                try {
                    tmp= LangUtil.replace(tmp, "${SYM}",DateUtil.getLastDayString(DateUtil.formatDateTime("yyyyMMdd", getDateSq_MM())) );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (val.contains("${NC}")) {
                //tmp = tmp.replaceAll("${NC}", DateUtil.formatDateTime("yyyyMMdd", getDateNc_MM()));
                tmp= LangUtil.replace(tmp, "${NC}", DateUtil.formatDateTime("yyyyMMdd", getDateNc_MM()));
            }
            if (val.contains("${SNM}")) {
                //tmp = tmp.replaceAll("${SNM}", DateUtil.formatDateTime("yyyyMMdd", getDateSnm_MM()));
                tmp= LangUtil.replace(tmp, "${SNM}", DateUtil.formatDateTime("yyyyMMdd", getDateSnm_MM()));
            }

            if (val.contains("${SNM_YYMM}")) {
                tmp= LangUtil.replace(tmp, "${SNM_YYMM}", DateUtil.formatDateTime("yyMM", getDateSnm_MM()));
            }

            if (val.contains("${SNM_YYMMDD}")) {
                tmp= LangUtil.replace(tmp, "${SNM_YYMMDD}", DateUtil.formatDateTime("yyMMdd", getDateSnm_MM()));
            }

            if ("true".equalsIgnoreCase(sfdsjptStr)) {
                tmp= LangUtil.replace(tmp, "${BQCKZB}", "CBS_INVM_BASE_DAY");
                tmp= LangUtil.replace(tmp, "${BQDKZB}", "CBS_BORM_BASE_DAY");
            }else{
                tmp= LangUtil.replace(tmp, "${BQCKZB}", "ZMCBSINVMBASE"+DateUtil.formatDateTime("yyMM", getDateBq_MM()));
                tmp= LangUtil.replace(tmp, "${BQDKZB}", "ZMCBSBORMBASE"+DateUtil.formatDateTime("yyMM", getDateBq_MM()));

            }

            /*if (val.contains("$JGDM")) {
                //tmp = tmp.replaceAll("${SNM}", DateUtil.formatDateTime("yyyyMMdd", getDateSnm_MM()));
                tmp= LangUtil.replace(tmp, "$JGDM", getAuthI().getYwjgdm());
            }*/

        } else if("Q".equalsIgnoreCase(zbwd)) {
            if (val.contains("${BQ}")) {
                //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_Q()));
                tmp= LangUtil.replace(tmp, "${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_Q()));
            }
            if (val.contains("${BQ_YYMM}")) {
                tmp= LangUtil.replace(tmp, "${BQ_YYMM}", DateUtil.formatDateTime("yyMM", getDateBq_Q()));
            }

            if (val.contains("${BQ_YYMMDD}")) {
                tmp= LangUtil.replace(tmp, "${BQ_YYMMDD}", DateUtil.formatDateTime("yyMMdd", getDateBq_Q()));
            }
            if (val.contains("${QC}")) {
                try {
                    tmp= LangUtil.replace(tmp, "${QC}", DateUtil.formatDateTime("yyyyMMdd", DateUtil.getFirstday_Month(getDateBq_Q(),0 )));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
            }

            if (val.contains("${QM}")) {
                tmp= LangUtil.replace(tmp, "${QM}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_Q_QM()));
                //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
            }
            if (val.contains("${QM_YYMM}")) {
                tmp= LangUtil.replace(tmp, "${QM_YYMM}", DateUtil.formatDateTime("yyMM", getDateBq_Q_QM()));
            }

            if (val.contains("${QM_YYMMDD}")) {
                tmp= LangUtil.replace(tmp, "${QM_YYMMDD}", DateUtil.formatDateTime("yyMMdd", getDateBq_Q_QM()));
            }

            if (val.contains("${SQ}")) {
                //tmp = tmp.replaceAll("${SQ}", DateUtil.formatDateTime("yyyyMMdd", getDateSq_Q()));
                tmp= LangUtil.replace(tmp, "${SQ}", DateUtil.formatDateTime("yyyyMMdd", getDateSq_Q()));
            }
            if (val.contains("${SQ_YYMM}")) {
                tmp= LangUtil.replace(tmp, "${SQ_YYMM}", DateUtil.formatDateTime("yyMM", getDateSq_Q()));
            }

            if (val.contains("${SQ_YYMMDD}")) {
                tmp= LangUtil.replace(tmp, "${SQ_YYMMDD}", DateUtil.formatDateTime("yyMMdd", getDateSq_Q()));
            }
            if (val.contains("${SYM}")) {
                //tmp = tmp.replaceAll("${SQ}", DateUtil.formatDateTime("yyyyMMdd", getDateSq_MM()));
                try {
                    tmp= LangUtil.replace(tmp, "${SYM}",DateUtil.getLastDayString(DateUtil.formatDateTime("yyyyMMdd", getDateSq_Q())) );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (val.contains("${NC}")) {
                //tmp = tmp.replaceAll("${NC}", DateUtil.formatDateTime("yyyyMMdd", getDateNc_Q()));
                tmp= LangUtil.replace(tmp, "${NC}", DateUtil.formatDateTime("yyyyMMdd", getDateNc_Q()));
            }
            if (val.contains("${SNM}")) {
                //tmp = tmp.replaceAll("${SNM}", DateUtil.formatDateTime("yyyyMMdd", getDateSnm_Q()));
                tmp= LangUtil.replace(tmp, "${SNM}", DateUtil.formatDateTime("yyyyMMdd", getDateSnm_Q()));
            }

            if (val.contains("${SNM_YYMM}")) {
                tmp= LangUtil.replace(tmp, "${SNM_YYMM}", DateUtil.formatDateTime("yyMM", getDateSnm_Q()));
            }

            if (val.contains("${SNM_YYMMDD}")) {
                tmp= LangUtil.replace(tmp, "${SNM_YYMMDD}", DateUtil.formatDateTime("yyMMdd", getDateSnm_Q()));
            }

            if ("true".equalsIgnoreCase(sfdsjptStr)) {
                tmp= LangUtil.replace(tmp, "${BQ}CKZB", "CBS_INVM_BASE_DAY");
                tmp= LangUtil.replace(tmp, "${BQ}DKZB", "CBS_BORM_BASE_DAY");
            }else{
                tmp= LangUtil.replace(tmp, "${BQ}CKZB", "ZMCBSINVMBASE"+DateUtil.formatDateTime("yyMM", getDateBq_Q()));
                tmp= LangUtil.replace(tmp, "${BQ}DKZB", "ZMCBSBORMBASE"+DateUtil.formatDateTime("yyMM", getDateBq_Q()));

            }

           /* if (val.contains("$JGDM")) {
                //tmp = tmp.replaceAll("${SNM}", DateUtil.formatDateTime("yyyyMMdd", getDateSnm_MM()));
                tmp= LangUtil.replace(tmp, "$JGDM", getAuthI().getYwjgdm());
            }*/

        } else if("W".equalsIgnoreCase(zbwd)||"DD".equalsIgnoreCase(zbwd)) {
            //周
            if (val.contains("${BQ}")) {
                tmp= LangUtil.replace(tmp, "${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq()));
                //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
            }

            if (val.contains("${QC}")) {
                try {
                    tmp= LangUtil.replace(tmp, "${QC}", DateUtil.formatDateTime("yyyyMMdd", DateUtil.getFirstday_Month(getDateBq_MM(),0 )));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
            }

            if (val.contains("${QM}")) {
                tmp= LangUtil.replace(tmp, "${QM}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_QM()));
                //tmp = tmp.replaceAll("${BQ}", DateUtil.formatDateTime("yyyyMMdd", getDateBq_MM()));
            }

            if (val.contains("${SQ}")) {
                //tmp = tmp.replaceAll("${SQ}", DateUtil.formatDateTime("yyyyMMdd", getDateSq_MM()));
                tmp= LangUtil.replace(tmp, "${SQ}", DateUtil.formatDateTime("yyyyMMdd", getDateSq_MM()));
            }

            if (val.contains("${SYM}")) {
                //tmp = tmp.replaceAll("${SQ}", DateUtil.formatDateTime("yyyyMMdd", getDateSq_MM()));
                try {
                    tmp= LangUtil.replace(tmp, "${SYM}",DateUtil.getLastDayString(DateUtil.formatDateTime("yyyyMMdd", getDateSq_MM())) );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (val.contains("${NC}")) {
                //tmp = tmp.replaceAll("${NC}", DateUtil.formatDateTime("yyyyMMdd", getDateNc_MM()));
                tmp= LangUtil.replace(tmp, "${NC}", DateUtil.formatDateTime("yyyyMMdd", getDateNc_MM()));
            }
            if (val.contains("${SNM}")) {
                //tmp = tmp.replaceAll("${SNM}", DateUtil.formatDateTime("yyyyMMdd", getDateSnm_MM()));
                tmp= LangUtil.replace(tmp, "${SNM}", DateUtil.formatDateTime("yyyyMMdd", getDateSnm_MM()));
            }
        }
        return tmp;
    }

    public Date getDateBq() {
        return dateBq;
    }

    public void setDateBq(Date dateBq) {
        this.dateBq = dateBq;
    }

    public Date getDateBq_MM() {
        return dateBq_MM;
    }

    public Date getDateBq_QM() {
        return dateBq_QM;
    }
    public void setDateBq_QM(Date dateBq_QM) {
        this.dateBq_QM = dateBq_QM;
    }

    public Date getDateBq_Q_QM() {
        return dateBq_Q_QM;
    }
    public void setDateBq_Q_QM(Date dateBq_Q_QM) {
        this.dateBq_Q_QM = dateBq_Q_QM;
    }

    public void setDateBq_MM(Date dateBq_MM) {
        this.dateBq_MM = dateBq_MM;
    }

    public Date getDateSq_MM() {
        return dateSq_MM;
    }

    public void setDateSq_MM(Date dateSq_MM) {
        this.dateSq_MM = dateSq_MM;
    }

    public Date getDateNc_MM() {
        return dateNc_MM;
    }

    public void setDateNc_MM(Date dateNc_MM) {
        this.dateNc_MM = dateNc_MM;
    }

    public Date getDateSnm_MM() {
        return dateSnm_MM;
    }

    public void setDateSnm_MM(Date dateSnm_MM) {
        this.dateSnm_MM = dateSnm_MM;
    }

    public Date getDateBq_Q() {
        return dateBq_Q;
    }

    public void setDateBq_Q(Date dateBq_Q) {
        this.dateBq_Q = dateBq_Q;
    }

    public Date getDateSq_Q() {
        return dateSq_Q;
    }

    public void setDateSq_Q(Date dateSq_Q) {
        this.dateSq_Q = dateSq_Q;
    }

    public Date getDateNc_Q() {
        return dateNc_Q;
    }

    public void setDateNc_Q(Date dateNc_Q) {
        this.dateNc_Q = dateNc_Q;
    }

    public Date getDateSnm_Q() {
        return dateSnm_Q;
    }

    public void setDateSnm_Q(Date dateSnm_Q) {
        this.dateSnm_Q = dateSnm_Q;
    }

    public Date getDateNc_YY() {
        return dateNc_YY;
    }

    public void setDateNc_YY(Date dateNc_YY) {
        this.dateNc_YY = dateNc_YY;
    }

    public Date getDateNc_YYYY() {
        return dateNc_YYYY;
    }

    public void setDateNc_YYYY(Date dateNc_YYYY) {
        this.dateNc_YYYY = dateNc_YYYY;
    }
}
