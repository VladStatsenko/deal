package org.statsenko.service.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.statsenko.entity.Product;
import org.statsenko.entity.Promotion;
import org.statsenko.repository.PromotionRepository;
import org.statsenko.service.services.PromotionService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {PromotionServiceImpl.class, PromotionService.class})
@WebMvcTest
class PromotionServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PromotionRepository promotionRepository;
    Product product = new Product(1,"CREDIT CARD","15%",null,null,null,null,null,null);

    Promotion promotion1 = new Promotion(1,"SALE","15%",null,null,product,null);
    Promotion promotion2 = new Promotion(2,"CASHBACK","4%",null,null,product,null);

    Product product1 = new Product(1,"CREDIT CARD","15%",null,null,List.of(promotion1),null,null,null);

    List<Promotion> promotions = new ArrayList<>(List.of(promotion1,promotion2));

    @Test
    void getAllPromotion() throws Exception {
        Mockito.when(promotionRepository.findAll()).thenReturn(promotions);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/promotion")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("SALE")))
                .andExpect(jsonPath("$[1].desc", is("4%")));
    }

    @Test
    void getPromotionById() throws Exception{
        Mockito.when(promotionRepository.getById(promotion1.getId())).thenReturn(promotion1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/promotion/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name", is("SALE")));
    }

    @Test
    void createPromotion() throws Exception{
        Promotion promotion = new Promotion(3,"Free service","30 days",null,null,null,null);

        Mockito.when(promotionRepository.save(promotion)).thenReturn(promotion);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/promotion")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(promotion));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name", is("Free service")))
                .andExpect(jsonPath("$.desc", is("30 days")));
    }

    @Test
    void editPromotion() throws Exception{
        Promotion promotion1 = new Promotion(1,"Free service","30 days",null,null,null,null);

        Mockito.when(promotionRepository.save(promotion1)).thenReturn(promotion1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/promotion/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(promotion1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name", is("Free service")))
                .andExpect(jsonPath("$.desc", is("30 days")));
    }

    @Test
    void deletePromotion() throws Exception{
        Mockito.when(promotionRepository.getById(promotion1.getId())).thenReturn(promotion1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/promotion/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPromotionByProduct() throws Exception{
        Mockito.when(promotionRepository.getPromotionOfProduct(1)).thenReturn(List.of(promotion1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/promotion/product/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("SALE")));
    }
}