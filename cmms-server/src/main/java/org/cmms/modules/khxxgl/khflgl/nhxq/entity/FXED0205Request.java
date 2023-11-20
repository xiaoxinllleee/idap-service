package org.cmms.modules.khxxgl.khflgl.nhxq.entity;


import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

/**
 * @Date 2022/9/21
 * @Created by eran
 */
public class FXED0205Request {
    private SlSHnkdVO request;

    @XmlElement(name = "Request")
    public SlSHnkdVO getRequest() {
        return request;
    }

    public void setRequest(SlSHnkdVO request) {
        this.request = request;
    }
}
