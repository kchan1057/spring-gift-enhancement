package com.example.demo.product;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProductPagingTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void 첫번째_페이지_조회시_10개가_반환되고_가장_최신_상품이_첫번째에_위치한다() throws Exception{
    mockMvc.perform(get("/product-page")
        .param("page", "0"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("paging"))
        .andExpect(model().attribute("paging", Matchers.hasProperty("content", Matchers.hasSize(10))))
        .andExpect(model().attribute("paging", Matchers.hasProperty("number", Matchers.equalTo(0))))
        .andExpect(model().attribute("paging", Matchers.hasProperty("totalPages", Matchers.equalTo(11))));
  }
}
