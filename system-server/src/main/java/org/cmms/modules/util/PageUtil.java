package org.cmms.modules.util;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


public class PageUtil {

    public static IPage getPages(List list, int total, int pageSize, int pageNo) {
        List newList = null;
        IPage page = new Page(pageNo, pageSize);
        page.setTotal(total);
        if(list!=null&&list.size()>0){
            newList = (List) list.subList(pageSize * (pageNo - 1), ((pageSize * pageNo) > total ? total : (pageSize * pageNo)));
        }
        page.setRecords(newList);
        return page;
    }

}
