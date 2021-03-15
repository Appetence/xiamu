package demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.tea.utils.StringUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import demo.annotation.AccessLimit;
import demo.annotation.RateLimit;
import demo.entity.TableData;
import demo.request.TableDataRequest;
import demo.request.TableDataRequestAdd;
import demo.response.TableDataResponse;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static List<TableData> list = new LinkedList<>();

    @PostMapping("/")
    public TableDataResponse getTableData(@RequestBody TableDataRequest tableDataRequest) {
        logger.info("request ：" + tableDataRequest.getUsername());
        TableDataResponse response = new TableDataResponse();
        if (!StringUtils.isEmpty(tableDataRequest.getUsername())) {
            List<TableData> listResult = new LinkedList<>();
            //获取list中的指定value返回
            for (TableData tableData : list) {
                if (tableData.getName().equals(tableDataRequest.getUsername())) {
                    listResult.add(tableData);
                }
            }
            response.setData(listResult);
            return response;
        }
        initData();
        response.setData(list);
        //手动模拟异常情况是
        if (tableDataRequest.getUsername().equals("123")) {
            throw new RuntimeException();
        }
        return response;
    }
    @RateLimit(seconds =1,maxCount = 10,type = "test")
    @PostMapping("/demoAdd")
    public TableDataResponse addTableData(@RequestBody TableDataRequestAdd tableDataRequest) {
        logger.info("add info" + tableDataRequest.toString());
        list.add(createTableData(tableDataRequest));
        System.out.println(">>>>>");
        return new TableDataResponse();
    }

    /**
     * 授信申请
     *
     * @param tableDataRequest
     * @return
     */
    @AccessLimit(seconds = 1, maxCount = 10)
    @PostMapping("/creditApply")
    public CreditResult creditApply(@RequestBody Map tableDataRequest) {
        logger.info("reques info is :" + JSONObject.toJSONString(tableDataRequest));
/*        tableDataRequest.put("responseCode", "02");
        tableDataRequest.put("responseMessage", "success");
        Map message = new HashMap();
        message.put("reqNo","123");
        message.put("customerId","customerId");
        tableDataRequest.put("message", "success");*/
        CreditResult result = new CreditResult();
        result.setResponseCode("1");
        result.setResponseMessage("success");
        MessageCredite message = new MessageCredite();
        message.setReqNo((String) tableDataRequest.get("reqNo"));
        message.setCustomerId("99999");
       /* message.setBrwManNm("刘颢");
        message.setBrwManIdentTp("1");
        message.setBrwManIdentNum("142232111111111111");
        message.setQuantum("222.22");
        message.setCreetsEfftDt("2020-12-11 16:59:04");
        message.setDcsonRslt("10031")
        HitRuleGrp hitRuleGrp = new HitRuleGrp();
        hitRuleGrp.setRuleNm("");
        message.setHitRuleGrp(hitRuleGrp);*/
        result.setMessage(message);
        ;
        logger.info("response info is :" + JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 授信查询
     *
     * @param tableDataRequest
     * @return
     */
    @PostMapping("/creditQuery")
    public ResultQuery creditQuery(@RequestBody Map tableDataRequest) {
        logger.info("reques info is :" + JSONObject.toJSONString(tableDataRequest));
/*        tableDataRequest.put("responseCode", "02");
        tableDataRequest.put("responseMessage", "success");
        Map message = new HashMap();
        message.put("reqNo","123");
        message.put("customerId","customerId");
        tableDataRequest.put("message", "success");*/
        ResultQuery result = new ResultQuery();
        result.setResponseCode("1");
        result.setResponseMessage("success");
        MessageQuery message = new MessageQuery();
        message.setReqNo((String) tableDataRequest.get("reqNo"));
        message.setCustomerId("99999");
        message.setBrwManNm("刘颢");
        message.setBrwManIdentTp("1");
        message.setBrwManIdentNum("142232111111111111");
        message.setQuantum("222.22");
        message.setCreetsEfftDt("2020-12-11 16:59:04");
        message.setDcsonRslt("10031");
        HitRuleGrp hitRuleGrp = new HitRuleGrp();
        hitRuleGrp.setRuleNm("");
        message.setHitRuleGrp(hitRuleGrp);
        result.setMessage(message);
        ;
        logger.info("response info is :" + JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 借款申请
     *
     * @param tableDataRequest
     * @return
     */
    @PostMapping("/loanApply")
    public ResultLoanApply loanApply(@RequestBody Map tableDataRequest) {
        logger.info("reques info is :" + JSONObject.toJSONString(tableDataRequest));
/*        tableDataRequest.put("responseCode", "02");
        tableDataRequest.put("responseMessage", "success");
        Map message = new HashMap();
        message.put("reqNo","123");
        message.put("customerId","customerId");
        tableDataRequest.put("message", "success");*/
        ResultLoanApply result = new ResultLoanApply();
        result.setResponseCode("1");
        result.setResponseMessage("success");
        MessageLoanApply message = new MessageLoanApply();
        message.setTransSerialNo((String) tableDataRequest.get("transSerialNo"));
        message.setLoanSerialNo(String.valueOf(new Random().nextInt(10)));
       /* message.setBrwManNm("刘颢");
        message.setBrwManIdentTp("1");
        message.setBrwManIdentNum("142232111111111111");
        message.setQuantum("222.22");
        message.setCreetsEfftDt("2020-12-11 16:59:04");
        message.setDcsonRslt("10031")
        HitRuleGrp hitRuleGrp = new HitRuleGrp();
        hitRuleGrp.setRuleNm("");
        message.setHitRuleGrp(hitRuleGrp);*/
        result.setMessage(message);
        ;
        logger.info("response info is :" + JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 借款查询
     *
     * @param tableDataRequest
     * @return
     */
    @PostMapping("/loanApplyQuery")
    public ResultLoanApplyQuery loanApplyQuery(@RequestBody Map tableDataRequest) {
        logger.info("reques info is :" + JSONObject.toJSONString(tableDataRequest));
/*        tableDataRequest.put("responseCode", "02");
        tableDataRequest.put("responseMessage", "success");
        Map message = new HashMap();
        message.put("reqNo","123");
        message.put("customerId","customerId");
        tableDataRequest.put("message", "success");*/
        ResultLoanApplyQuery result = new ResultLoanApplyQuery();
        result.setResponseCode("1");
        result.setResponseMessage("success");
        MessageLoanApplyQuery message = new MessageLoanApplyQuery();
        message.setLoanSerialNo("99999999");
        message.setPutoutDate("2020-11-16 11:31:47");
       /* message.setBrwManNm("刘颢");
        message.setBrwManIdentTp("1");
        message.setBrwManIdentNum("142232111111111111");
        message.setQuantum("222.22");
        message.setCreetsEfftDt("2020-12-11 16:59:04");
        message.setDcsonRslt("10031")
        HitRuleGrp hitRuleGrp = new HitRuleGrp();
        hitRuleGrp.setRuleNm("");
        message.setHitRuleGrp(hitRuleGrp);*/
        result.setMessage(message);
        ;
        logger.info("response info is :" + JSONObject.toJSONString(result));
        return result;
    }

    @PostMapping("/creditBefore")
    public  BaseResult<CreditBefore> creditBefore(@RequestBody Map tableDataRequest) {
        logger.info("reques info is :" + JSONObject.toJSONString(tableDataRequest));
/*        tableDataRequest.put("responseCode", "02");
        tableDataRequest.put("responseMessage", "success");
        Map message = new HashMap();
        message.put("reqNo","123");
        message.put("customerId","customerId");
        tableDataRequest.put("message", "success");*/
        BaseResult<CreditBefore> result = new  BaseResult<CreditBefore>();
        result.setResponseCode("1");
        result.setResponseMessage("success");
        CreditBefore message = new CreditBefore();
        message.setCustomerId("99999");
        result.setMessage(message);
        ;
        logger.info("response info is :" + JSONObject.toJSONString(result));
        return result;
    }

    @Data
    @ToString
    class Result {
        private String responseCode;
        private String responseMessage;
        private Message message;

    }

    @Data
    @ToString
    class Message {
        private String reqNo;
        private String customerId;
        //授信查询专属
        private String brwManNm;
        private String brwManIdentTp;
        private String brwManIdentNum;
        private String quantum;
        private String creetsEfftDt;
        private String dcsonRslt;
        private HitRuleGrp hitRuleGrp;

    }

    @Data
    @ToString
    class ResultLoanApplyQuery {
        private String responseCode;
        private String responseMessage;
        private MessageLoanApplyQuery message;

    }

    @Data
    @ToString
    class BaseResult<T> {
        private String responseCode;
        private String responseMessage;
        private T message;
    }

    @Data
    @ToString
    class CreditBefore {
        private String customerId;
    }

    @Data
    @ToString
    class MessageLoanApplyQuery {
        /**
         * 交易流水号
         */
        private String transSerialNo;
        /**
         * 借据编号
         */
        private String loanSerialNo;
        /**
         * 交易状态
         */
        private String transStatus;
        /**
         * 客户编号
         */
        private String customerId;
        /**
         * 客户名称
         */
        private String customerName;
        /**
         * 证件类型
         */
        private String certType;
        /**
         * 证件号码
         */
        private String certId;
        /**
         * 贷款币种
         */
        private String currency;
        /**
         * 贷款金
         */
        private String businessSum;
        /**
         * 贷款发放日期
         */
        private String putoutDate;
        /**
         * 贷款到期日
         */
        private String maturityDate;
        /**
         * 基准利率类型
         */
        private String baseRateType;
        /**
         * 基准利率档次
         */
        private String baseRateGrade;
        /**
         * 基准利率
         */
        private String baseRate;
        /**
         * 浮动利率类型
         */
        private String rateFloatType;
        /**
         * 浮动幅度
         */
        private String rateFloat;
        /**
         * 利率单位
         */
        private String rateUnit;
        /**
         * 执行利率
         */
        private String businessRate;

        /**
         * 还款计划组
         */
        private XianPaymentSchedules paymentSchedules;

    }

    @Data
    @ToString
    class XianPaymentSchedules {
        /**
         * 流水号
         */
        private String psSerialNo;
        /**
         * 期次
         */
        private String periodNo;
        /**
         * 应还日期
         */
        private String payDate;
        /**
         * 币种
         */
        private String currency;
        /**
         * 应还本金
         */
        private String payPrincipalAmt;
        /**
         * 实还本金
         */
        private String actualPayPrincipalAmt;
        /**
         * 应还利息
         */
        private String payInterestAmt;
        /**
         * 实还利息
         */
        private String actualPayInterestAmt;
        /**
         * 应还本金罚息
         */
        private String payPrincipalPenaltyAmt;
        /**
         * 实还本金罚息
         */
        private String actualPayPrincipalPenaltyAmt;
        /**
         * 应还利息罚息
         */
        private String payInterestPenaltyAmt;
        /**
         * 实还利息罚息
         */
        private String actualPayInterestPenaltyAmt;
        /**
         * 应还管理费
         */
        private String payFeeAmt;
        /**
         * 实还管理费
         */
        private String actualPayFeeAmt;
        /**
         * 应还服务费
         */
        private String payServiceFeeAmt;
        /**
         * 实还服务费
         */
        private String actualPayServiceFeeAmt;
        /**
         * 应还渠道费
         */
        private String payChannelFeeAmt;
        /**
         * 实还渠道费
         */
        private String actualPayChannelFeeAmt;
        /**
         * 应还滞纳金
         */
        private String payOverdueFeeAmt;
        /**
         * 实还滞纳金
         */
        private String actualPayOverdueFeeAmt;
        /**
         * 应还提前还款违约金金额
         */
        private String prePayPenaltyAmt;
        /**
         * 实收提前还款违约金金额
         */
        private String actualPrePayPenaltyAmt;
        /**
         * 剩余本金
         */
        private String principalBalance;
        /**
         * 结清日期
         */
        private String finishDate;
    }

    @Data
    @ToString
    class ResultLoanApply {
        private String responseCode;
        private String responseMessage;
        private MessageLoanApply message;

    }

    @Data
    @ToString
    class MessageLoanApply {
        private String loanSerialNo;
        private String transSerialNo;

    }

    @Data
    @ToString
    class ResultQuery {
        private String responseCode;
        private String responseMessage;
        private MessageQuery message;

    }

    @Data
    @ToString
    class MessageQuery {
        private String reqNo;
        private String customerId;
        //授信查询专属
        private String brwManNm;
        private String brwManIdentTp;
        private String brwManIdentNum;
        private String quantum;
        private String creetsEfftDt;
        private String dcsonRslt;
        private HitRuleGrp hitRuleGrp;

    }

    @Data
    @ToString
    class CreditResult {
        private String responseCode;
        private String responseMessage;
        private MessageCredite message;

    }

    @Data
    @ToString
    class MessageCredite {
        private String reqNo;
        private String customerId;
    }


    @Data
    @ToString
    class HitRuleGrp {
        private String ruleNm;
    }

    private TableData createTableData(TableDataRequestAdd tableDataRequestAdd) {

        TableData tableData = new TableData();
        tableData.setAddress(tableDataRequestAdd.getAddress());
        tableData.setAge(tableDataRequestAdd.getAge());
        tableData.setName(tableDataRequestAdd.getName());
        return tableData;
    }

    private void initData() {
        if (list.size() <= 0) {
            for (int i = 0; i < 3; i++) {
                TableData tableData = new TableData();
                tableData.setAddress("address:" + i);
                tableData.setAge("age:" + i);
                tableData.setName("name:" + i);
                list.add(tableData);
            }
        }

    }

}
