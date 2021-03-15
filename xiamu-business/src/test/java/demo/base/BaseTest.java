package demo.base;

import demo.BusiApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * @program: vue2x
 * @description:
 * @Author: xiamu
 * @create: 2021-02-20 10:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {
    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);

    private MockMvc mockMvc;
    @Resource
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MockitoAnnotations.initMocks(this);
    }

//    @Deprecated
    protected String post(String api, String jsonBody) {
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                    .post(api)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .characterEncoding("utf-8")
                    .content(jsonBody)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            return mvcResult.getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error((Marker) e, "invoke api error, {}", e.getMessage());
            return null;
        }
    }

    @Deprecated
    protected <T> String invoke(String api, T request) {
        return post(api, (String) request);
    }


}
