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
import org.statsenko.entity.Deal;
import org.statsenko.entity.Promotion;
import org.statsenko.repository.DealRepository;
import org.statsenko.service.services.DealService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {DealControllerImpl.class, DealService.class})
@WebMvcTest
class DealServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    DealRepository dealRepository;

    Deal deal1 = new Deal(1,null,1000,null,null,null,null);
    Deal deal2 = new Deal(2,null,2000,null,null,null,null);

    Promotion promotion1 = new Promotion(1,"sale","sale for all",null,null,null,List.of(deal1,deal2));

    List<Deal> deals = new ArrayList<>(List.of(deal1,deal2));

    @Test
    void getAllDeal() throws Exception{
        Mockito.when(dealRepository.findAll()).thenReturn(deals);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/deal")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].sum", is(1000)));
    }

    @Test
    void getDealById() throws Exception{
        Mockito.when(dealRepository.getById(deal1.getId())).thenReturn((deal1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/deal/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.sum", is(1000)));
    }

    @Test
    void createDeal() throws Exception {
        Deal newDeal = new Deal(3,null,3000,null,null,null,null);

        Mockito.when(dealRepository.save(newDeal)).thenReturn(newDeal);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/deal")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(newDeal));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.sum", is(3000)));
    }

    @Test
    void editDeal() throws Exception{
        Deal newDeal = new Deal(3,null,4000,null,null,null,null);

        Mockito.when(dealRepository.save(newDeal)).thenReturn(newDeal);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/deal/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(newDeal));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.sum", is(4000)));
    }

    @Test
    void deleteDeal() throws Exception {
        Mockito.when(dealRepository.getById(deal1.getId())).thenReturn(deal1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/deal/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllDealWithPromotion() throws Exception{
        Mockito.when(dealRepository.findDealByPromotion(1)).thenReturn(List.of(deal1,deal2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/deal/promotion/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].sum", is(1000)))
                .andExpect(jsonPath("$[1].sum", is(2000)));
    }
}