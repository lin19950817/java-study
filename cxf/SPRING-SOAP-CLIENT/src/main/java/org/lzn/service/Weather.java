package org.lzn.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.4.0
 * 2020-09-10T21:29:52.721+08:00
 * Generated source version: 3.4.0
 *
 */
@WebService(targetNamespace = "http://service.lzn.org/", name = "Weather")
@XmlSeeAlso({ObjectFactory.class})
public interface Weather {

    @WebMethod
    @RequestWrapper(localName = "queryWeather", targetNamespace = "http://service.lzn.org/", className = "org.lzn.service.QueryWeather")
    @ResponseWrapper(localName = "queryWeatherResponse", targetNamespace = "http://service.lzn.org/", className = "org.lzn.service.QueryWeatherResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String queryWeather(

        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
