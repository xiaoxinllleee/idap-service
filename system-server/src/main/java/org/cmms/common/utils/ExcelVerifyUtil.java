package org.cmms.common.utils;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.system.vo.DictModel;

import java.util.List;

/**
 * @Date 2022/1/14
 * @Created by eran
 */
public class ExcelVerifyUtil {

    /**
     * 用来校验导入时字典值存在
     * */
    public static boolean isDictItemValue(String value, List<DictModel> dictModels){
        if (CollUtil.isEmpty(dictModels))
            return false;
        if (StringUtils.isBlank(value))
            return false;

        for (int i = 0; i < dictModels.size(); i++) {
            DictModel dictModel = dictModels.get(i);
            if (value.trim().equals(dictModel.getValue()))
                return true;
        }

        return false;
    }

    /**
     * 用来校验key
     */
    public static boolean isDictItemText(String text, List<DictModel> dictModels){
        if (CollUtil.isEmpty(dictModels))
            return false;
        if (StringUtils.isBlank(text))
            return false;

        for (int i = 0; i < dictModels.size(); i++) {
            DictModel dictModel = dictModels.get(i);
            if (text.trim().equals(dictModel.getText()))
                return true;
        }

        return false;
    }

    /**
     * 根据导入的value找到key
     * */
    public static String getValueByKey(String value,List<DictModel> dictModels){
        if (CollUtil.isEmpty(dictModels))
            return null;
        if (StringUtils.isBlank(value))
            return null;
        for (int i = 0; i < dictModels.size(); i++) {
            DictModel dictModel = dictModels.get(i);
            if (value.trim().equals(dictModel.getValue()))
                return dictModel.getText();
        }
        return null;
    }

    /**
     * 判断导入的值是否重复出现
     * */
    public static boolean isRepeat(String value,List<String> list){
        if (CollUtil.isEmpty(list))
            return false;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            //如果已经重复了 就不用继续去找了
            if (count > 1)
                break;;
             String s = list.get(i);
             if (s.equals(value))
                 count++;
        }
        if (count > 1)
            return true;
        return false;
    }

}
