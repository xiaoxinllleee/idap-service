package org.cmms.modules.khxxgl.yjzrbg.entity;

import lombok.Data;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;

/**
 * @Date 2023/4/19
 * @Created by eran
 */
@Data
public class YjzrbgScore {
    private boolean zzjg = true;
    private String zzjgval = "未核查";
    private String zzjgvalDict = "3";
    private int fs = 0;
    private String fsval = "";
    private String fxdj = "";
    private String fxdjDict = "3";
    private String zjhm;

    private int jcf=500;
    private int dkkh=0;
    private int yqkh=0;
    private boolean blkh = true;
    private String blkhval;
    private int ckrj=0;
    private int dkyewlsj=100;
    private int dzyhktqk=0;
    private boolean hmdkh=true;
    private boolean wbsj = true;

    private int qxsj;
    private Object ywhywwlxx;

    //核查内容有三种   通过1 失败2 未核查到数据3
    //内部征信
    private int nbzx = 1;
    private String nbzxval;

    //黑名单
    private int hmd = 3;
    private String hmdval;

    //背靠背
    private int bkbpy=3;
    private String bkbpyval;

    //征信报告
    private int zxbg=3;
    private String zxbgval;

    //五保户 低保户
    private int wbhdbh=1;
    private String wbhdbhval;

    private int tpjch = 1;
    private String tpjchval;


    public void setNbzxval(String nbzxval) {
        if (this.nbzxval != null && this.nbzxval.length() > 0){
            this.nbzxval += nbzxval;
        }else {
            this.nbzxval = nbzxval;
        }
    }

    public void setHmdval(String hmdval) {
        if (this.hmdval != null && this.hmdval.length() > 0){
            this.hmdval += hmdval;
        }else {
            this.hmdval = hmdval;
        }
    }

    public void jszf(){
        this.fs = this.jcf+this.dkkh+this.yqkh+this.ckrj+this.dkyewlsj+this.dzyhktqk+this.bkbpy;
        if (this.tpjch == 2 || this.nbzx == 2 || this.hmd == 2 || this.bkbpy == 2 || this.wbhdbh == 2 || this.zxbg == 2){
            this.zzjg = false;
            zzjgval ="建议拒绝";
            zzjgvalDict ="2";
        }else {
            zzjgval = "核查通过";
            zzjgvalDict = "1";
        }
        if (zzjg){
            fxdj="高";
            fxdjDict = "1";
            fsval="信用差";

            if (fs > 500 ){
                fxdj="中";
                fxdjDict = "2";
                fsval="信用一般";
            }
           if (fs > 1000){
               fxdj="低";
               fxdjDict = "3";
               fsval="信用极好";
           }

        }else {
            fxdj="高";
            fxdjDict = "1";
            fs=0;
            fsval="信用差";
        }
    }
}
