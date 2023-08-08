package com.springboot.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
// 웹에서 사용되는 요청과 응답에대한 테스트를 수행할 수 있다.
// 대상 클래스만 로드해 테스트를 수행하며, 만약 추가하지않으면 컨트롤러 관련 빈 객체가 모두 로드된다.
class ProductControllerTest {
    @Autowired
    // MockMvc는 컨트롤러의 API를 테스트하기 위해 사용된 객체
    private MockMvc mockMvc;

    @MockBean
    // @MockBean을 통해 ProductController가 의존성을 가지고 있던 ProductService객체에 mock객체 주입
    ProductServiceImpl productService;

    @Autowired
    private ObjectMapper objectMapper; // json을 쉽게 바꿔줌

    @Test
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception {
        // given 메서드를 통해 이 객체에서 어떤 메서드가 호출되고 어떤 파라미터르 주입받는지 가정한 후
        // willReturn 메서드를 통해 어떤 결과를 리턴할 것인지 정의하는 구조로 코드 작성
        given(productService.getProduct(123L))
                .willReturn(new ProductResponseDto(123L, "pen", 5000, 2000));

        // perform 메서드를 이용하면 서버로 url 요청을 보내는 것처럼 통신테스트 코드를 작성해서 컨트롤러 테스트 가능
        mockMvc.perform(get("/product?number=123"))
//                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("pen"))
//                .andExpect(jsonPath("$.name").exists())
//                .andExpect(jsonPath("$.price").exists())
//                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        // 지정된 메서드가 실행됐는지 검증하는 역할
        verify(productService).getProduct(123L);
    }

    @Test
    @DisplayName("product 데이터 생성 테스트")
    void createProductTest() throws Exception {
        // given
        ProductDto pen = new ProductDto("pen", 5000, 2000);

        given(productService.saveProduct(any()))
                .willReturn(ProductResponseDto.builder()
                        .number(12315L)
                        .price(5000)
                        .name("pen")
                        .stock(2000)
                        .build());
        // when
        // then
        mockMvc.perform(post("/product")
//                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                pen
                        )))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.number").value(1))
                .andExpect(jsonPath("$.name").value("pen"))
//                .andExpect(jsonPath("$.price").exists())
//                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).saveProduct(refEq(new ProductDto("pen", 5000, 2000)));
    }
}