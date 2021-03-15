package demo.controller;

import com.aliyun.tea.utils.StringUtils;
import demo.entity.TableData;
import demo.request.TableDataRequest;
import demo.request.TableDataRequestAdd;
import demo.response.TableDataResponse;
import demo.util.validate.ValidateResult;
import demo.util.validate.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "demo")
public class DemoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static List<TableData> list = new LinkedList<>();

    @PostMapping("/")
    public TableDataResponse getTableData(@RequestBody TableDataRequest tableDataRequest) {
        ValidateResult validateResult = ValidatorUtils.validate(tableDataRequest);
        if (!validateResult.isValidate()) {
            logger.error("【提前结清还款】接口异常, 参数错误 {}", validateResult.getResultMap().toString());
            return TableDataResponse.builder().build();
        }
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

    @PostMapping("/demoAdd")
    public TableDataResponse addTableData(@RequestBody TableDataRequestAdd tableDataRequest) {
        logger.info("add info" + tableDataRequest.toString());
        list.add(createTableData(tableDataRequest));
        return new TableDataResponse();
    }

    @PostMapping("/demoDel")
    public TableDataResponse demoDel(@RequestBody TableDataRequest tableDataRequest) {
        for (TableData tableData : list) {
            if (tableData.getName().equals(tableDataRequest.getUsername())) {
                list.remove(tableData);
            }
        }
        TableDataResponse tableDataResponse = new TableDataResponse();
        tableDataResponse.setData(logger);
        return tableDataResponse;
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
