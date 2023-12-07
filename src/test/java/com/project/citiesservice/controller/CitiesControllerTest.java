package com.project.citiesservice.controller;

import com.project.citiesservice.mapper.CityMapper;
import com.project.citiesservice.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CitiesController.class)
@ContextConfiguration
class CitiesControllerTest {

    @MockBean
    private CityServiceImpl cityService;
    @MockBean
    private CityMapper cityMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "john", roles = "USER")
    void testUpdateCityMethodByUser_returnStatus200() throws Exception {
        mvc.perform(get("/cities/unique")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void testGetUniqueCitiesMethodByUser_returnStatus401() throws Exception {
        mvc.perform(get("/cities/unique")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "john1", roles = "USER")
    void testUpdateCityMethodByUser_returnStatus403() throws Exception {
        mvc.perform(patch("/cities/1")
                        .param("newName", "minsk")
                        .param("newLogo", "newlogo2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "editor", roles = "EDITOR")
    void testUpdateCityMethodByEditor_returnStatus200() throws Exception {
        mvc.perform(patch("/cities/1")
                        .param("newName", "minsk")
                        .param("newLogo", "newlogo2")
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void testSearchByCityAndCountryName_returnStatus401() throws Exception {
        mvc.perform(get("/cities/search")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "john", roles = "USER")
    void testSearchByCityAndCountryNameByUser_returnStatus200() throws Exception {
        Mockito.when(cityService.searchByCityAndCountryName("", "", 10, 0))
                .thenReturn(Page.empty());
        mvc.perform(get("/cities/search")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}