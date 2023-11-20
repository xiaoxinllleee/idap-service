package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Date 2022/9/21
 * @Created by eran
 */
@XmlRootElement(name = "Service")
public class FXED0205 {
    private FXED0205Header header;
    private FXED0205Request body;
    @XmlElement(name = "Header")
    public FXED0205Header getHeader() {
        return header;
    }

    public void setHeader(FXED0205Header header) {
        this.header = header;
    }
    @XmlElement(name = "Body")
    public FXED0205Request getBody() {
        return body;
    }

    public void setBody(FXED0205Request body) {
        this.body = body;
    }
}
