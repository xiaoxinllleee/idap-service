package org.cmms.common.constant;

import org.jeecgframework.poi.excel.def.NormalExcelConstants;

import java.util.ArrayList;
import java.util.List;

public interface IdentConstant extends NormalExcelConstants {
    /**
     * 证件号码字段
     */
    public final static List<String> Field_Name = new ArrayList<String>(){
        {
            this.add("zjhm");
            add("hzzjhm");
            add("pyyzjhm");
            add("frzjhm");
            add("sxdxzjh");
        }
    } ;
    /**
     * get证件号码字段
     */
    public final static List<String> Get_Field_Name = new ArrayList<String>(){
        {
            this.add("getZjhm");
            add("getZzjhm");
            add("getPyyzjhm");
            add("getFrzjhm");
            add("getSxdxzjh");
        }
    } ;
    /**
     * set证件号码字段
     */
    public final static List<String> Set_Field_Name = new ArrayList<String>(){
        {
            this.add("setZjhm");
            add("setZzjhm");
            add("setPyyzjhm");
            add("setFrzjhm");
            add("setSxdxzjh");
        }
    } ;

}
