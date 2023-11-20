package org.cmms.modules.activiti.controller;

import org.cmms.common.util.StringUtils;

public class ActTaskUtil {
    private enum ActTask {
        etcyxxxProcess("etcyxxxProcess", "org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.process.EtcyxxxglYjProcess"),
        sxsplc("sxsplc", "org.cmms.modules.xdgl.grkhpjsx.process.SxsplcProcess"),
        grdksplc("grdksplc", "org.cmms.modules.xdgl.grdkgl.process.GrdksplcProcess"),
        ckkhrl("ckkhrl", "org.cmms.modules.yjgs.ckkhdfpxx.process.CkkhdfpxxProcess"),
        ckzhrl("ckzhrl", "org.cmms.modules.yjgs.ckzhdrlxx.process.CkzhdfpxxProcess"),
        ckkhghyj("ckkhghyj", "org.cmms.modules.performance.depositcustomer.ckkhghyj.process.CkkhghyjProcess"),
        ckzhtzyj("ckzhtzyj", "org.cmms.modules.performance.depositcustomer.ckzhtzyj.process.CkzhtzyjProcess"),
        dkkhyj("dkkhyj", "org.cmms.modules.performance.loancustomer.dkkhyj.process.DkkhyjProcess");
        private String className;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        private String key;

        private ActTask(String key, String className) {
            this.key = key;
            this.className = className;
        }

        public static String getClassName(String key){
            if (StringUtils.isEmpty(key)) {
                return null;
            }
            for (ActTask val : values()) {
                if (val.getKey().equals(key)) {
                    return val.getClassName();
                }
            }
            return null;
        }
    }

    public static ActTaskHandler getHandler(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        try {
            String className = ActTask.getClassName(key);

            if (StringUtils.isEmpty(className)) {
                return null;
            }
            Class cls = Class.forName(className);
            Object obj = cls.newInstance();
            if (!(obj instanceof ActTaskHandler)) {
                return null;
            }
            return (ActTaskHandler)obj;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
