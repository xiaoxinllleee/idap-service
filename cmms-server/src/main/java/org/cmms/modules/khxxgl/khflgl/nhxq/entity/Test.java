package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import net.sf.saxon.trans.SymbolicName;
import org.cmms.common.system.util.JavaToXmlUtil;

/**
 * @Date 2022/9/21
 * @Created by eran
 */
public class Test {
    public static void main(String[] args) throws Exception{
        FXED0205 fxed0205 = new FXED0205();
        FXED0205Header fxed0205Header = new FXED0205Header();
        FXED0205Request fxed0205Request = new FXED0205Request();
        SlSHnkdVO slSHnkdVO = new SlSHnkdVO();
        slSHnkdVO.setCustName("刘鑫");
        slSHnkdVO.setCertNo("43070319941009685X");
        fxed0205Request.setRequest(slSHnkdVO);
        fxed0205.setHeader(fxed0205Header);
        fxed0205.setBody(fxed0205Request);
        String s = JavaToXmlUtil.javaToxml(fxed0205);
        System.out.println(s);
    }
}
