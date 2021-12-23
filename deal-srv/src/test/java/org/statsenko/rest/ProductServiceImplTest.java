package org.statsenko.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.MessageDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
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
import org.statsenko.entity.Currency;
import org.statsenko.entity.Product;
import org.statsenko.entity.ProductType;
import org.statsenko.mapper.ProductMapper;
import org.statsenko.service.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {ProductControllerImpl.class, ProductService.class})
@WebMvcTest
class ProductServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    ProductMapper REST_MAPPER = Mappers.getMapper(ProductMapper.class);

    @MockBean
    ProductService productService;

    ProductType type1 = new ProductType(1,"DEBIT","debit cards",null,null,null);
    ProductType type2 = new ProductType(2,"CREDIT","credit cards",null,null,null);
    Currency currency = new Currency(1,"Rubles","RUB",null,null,null);


    Product product1 = new Product(1,"CREDIT CARD","15%",null,null,null,null,type2,currency);
    Product product2 = new Product(2,"DEBIT CARD","cashback",null,null,null,null,type1,currency);

    List<Product> products = new ArrayList<>(List.of(product1,product2));

    @Test
    void getAllProduct() throws Exception {
        Mockito.when(productService.getAllProduct()).thenReturn(REST_MAPPER.toDtoList(products));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("CREDIT CARD")))
                .andExpect(jsonPath("$[1].desc", is("cashback")));
    }

    @Test
    void getProductById() throws Exception{
        MessageDto messageDto = new MessageDto();
        messageDto.setResponse(product1);
        Mockito.when(productService.getProductById(1)).thenReturn(messageDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.name", is("CREDIT CARD")))
                .andExpect(jsonPath("$.response.desc", is("15%")));
    }

    @Test
    void createProduct() throws Exception{

        Product newProduct = new Product(3,"IPOTEKA","ipoteka 6%",null,null,null,null,null,null);
        Mockito.when(productService.createProduct(REST_MAPPER.toDto(newProduct))).thenReturn(REST_MAPPER.toDto(newProduct));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(newProduct));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name", is("IPOTEKA")))
                .andExpect(jsonPath("$.desc", is("ipoteka 6%")));
    }

    @Test
    void editProduct() throws Exception {
        Product newProduct = new Product(1,"DEB CARD","cashback 3$",null,null,null,null,null,null);
        MessageDto messageDto = new MessageDto();
        messageDto.setResponse(newProduct);
        Mockito.when(productService.editProduct(REST_MAPPER.toDto(newProduct),1 )).thenReturn(messageDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(newProduct));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response.name", is("DEB CARD")))
                .andExpect(jsonPath("$.response.desc", is("cashback 3$")));
    }

    @Test
    void deleteProduct() throws Exception{
        MessageDto messageDto = new MessageDto();
        messageDto.setResponse("Product deleted");
        Mockito.when(productService.deleteProduct(1)).thenReturn(messageDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/product/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}