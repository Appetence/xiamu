package demo.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: xiaomu-oauth
 * @description:
 * @author: xiamu
 * @create: 2021-03-11 19:20
 */
public class Test {
    public static void main(String[] args) {
        CertificateQueryResponse certificateQueryResponse = new CertificateQueryResponse();

        System.out.println(JSONObject.toJSONString(certificateQueryResponse));
        System.out.println(JSONObject.toJSONString(CertificateQueryResponse.class));
        CertificateQueryResponse y = CertificateQueryResponse.builder().result("y").failMsg("").loanContractMongoId("").loanContractServiceMongoId("").
                certificateGuaranteeNameFile(new CertificateGuaranteeNameFileResponse()).debtTransferFiles(new LinkedList<CertificateDateFileResponse>())
                .certificateFiles(new HashMap<Integer, List<CertificateDateFileResponse>>()).build();
        System.out.println(JSONObject.toJSONString(y));
    }
}
