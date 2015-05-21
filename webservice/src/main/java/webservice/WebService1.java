/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Deivie
 */
@WebService(serviceName = "WebService1")
public class WebService1 {

    /**
     * Operação de Web service
     * @param nome
     * @param imei
     * @return 
     */
    @WebMethod(operationName = "saudacao")
    public String saudacao(@WebParam(name = "nome") String nome, @WebParam(name = "imei") String imei) {
        //TODO write your implementation code here:
        return "Ola,"+nome+",seu imei e:"+imei;
    }
}
