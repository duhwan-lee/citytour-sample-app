package com.dh.sample.citytoursampleapp.adapter.`in`.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TourControllerTest(private val mockMvc: MockMvc) {

    @Test
    fun testRegisterTour() {
        val map = mapOf(
            "userId" to 1,
            "cityId" to 1,
            "startDate" to "2023-01-01",
            "endDate" to "2023-02-01"
        )
        val jsonBody = jacksonObjectMapper().writeValueAsString(map)
        mockMvc.perform(
            RestDocumentationRequestBuilders.post("/api/v1/tour").content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "tour_register",
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    PayloadDocumentation.requestFields(
                        PayloadDocumentation.fieldWithPath("userId").description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("cityId").description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("startDate")
                            .description("??????????????? (YYYY-MM-DD)"),
                        PayloadDocumentation.fieldWithPath("endDate")
                            .description("??????????????? (YYYY-MM-DD)"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.OBJECT)
                            .description("?????? data object"),
                        PayloadDocumentation.fieldWithPath("data.tourId").type(JsonFieldType.NUMBER)
                            .description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("data.userId").type(JsonFieldType.NUMBER)
                            .description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("data.city").type(JsonFieldType.OBJECT)
                            .description("????????????"),
                        PayloadDocumentation.fieldWithPath("data.city.cityId")
                            .type(JsonFieldType.NUMBER)
                            .description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("data.city.cityName")
                            .type(JsonFieldType.STRING)
                            .description("?????????"),
                        PayloadDocumentation.fieldWithPath("data.city.country")
                            .type(JsonFieldType.STRING)
                            .description("?????????"),
                        PayloadDocumentation.fieldWithPath("data.city.createdAt")
                            .type(JsonFieldType.STRING)
                            .description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data.city.searchAt")
                            .type(JsonFieldType.STRING)
                            .description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data.startDate")
                            .type(JsonFieldType.STRING).description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data.endDate")
                            .type(JsonFieldType.STRING).description("?????? ?????????"),
                    )
                )
            )
    }

    @Test
    fun testUpdateTour() {
        val map = mapOf(
            "cityId" to 1,
            "startDate" to "2023-01-01",
            "endDate" to "2023-02-01"
        )
        val jsonBody = jacksonObjectMapper().writeValueAsString(map)
        mockMvc.perform(
            RestDocumentationRequestBuilders.put("/api/v1/tour/{userId}/{tourId}", 1, 1)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "tour_update",
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("userId").description("?????? ID"),
                        RequestDocumentation.parameterWithName("tourId").description("?????? ID"),
                    ),
                    PayloadDocumentation.requestFields(
                        PayloadDocumentation.fieldWithPath("cityId").description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("startDate")
                            .description("??????????????? (YYYY-MM-DD)"),
                        PayloadDocumentation.fieldWithPath("endDate")
                            .description("??????????????? (YYYY-MM-DD)"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.OBJECT)
                            .description("?????? data object"),
                        PayloadDocumentation.fieldWithPath("data.tourId").type(JsonFieldType.NUMBER)
                            .description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("data.userId").type(JsonFieldType.NUMBER)
                            .description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("data.city").type(JsonFieldType.OBJECT)
                            .description("????????????"),
                        PayloadDocumentation.fieldWithPath("data.city.cityId")
                            .type(JsonFieldType.NUMBER)
                            .description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("data.city.cityName")
                            .type(JsonFieldType.STRING)
                            .description("?????????"),
                        PayloadDocumentation.fieldWithPath("data.city.country")
                            .type(JsonFieldType.STRING)
                            .description("?????????"),
                        PayloadDocumentation.fieldWithPath("data.city.createdAt")
                            .type(JsonFieldType.STRING)
                            .description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data.city.searchAt")
                            .type(JsonFieldType.STRING)
                            .description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data.startDate")
                            .type(JsonFieldType.STRING).description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data.endDate")
                            .type(JsonFieldType.STRING).description("?????? ?????????"),
                    )
                )
            )
    }

    @Test
    fun testDeleteTour() {
        mockMvc.perform(
            RestDocumentationRequestBuilders.delete("/api/v1/tour/{userId}/{tourId}", 1, 1)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "tour_delete",
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("userId").description("?????? ID"),
                        RequestDocumentation.parameterWithName("tourId").description("?????? ID"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.NULL)
                            .description("?????? data object"),
                    )
                )
            )
    }

    @Test
    fun testGetTour() {
        mockMvc.perform(
            RestDocumentationRequestBuilders.get("/api/v1/tour/{userId}/{tourId}", 1, 1)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "tour_get",
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("userId").description("?????? ID"),
                        RequestDocumentation.parameterWithName("tourId").description("?????? ID"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.OBJECT)
                            .description("?????? data object"),
                        PayloadDocumentation.fieldWithPath("data.tourId").type(JsonFieldType.NUMBER)
                            .description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("data.userId").type(JsonFieldType.NUMBER)
                            .description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("data.city").type(JsonFieldType.OBJECT)
                            .description("????????????"),
                        PayloadDocumentation.fieldWithPath("data.city.cityId")
                            .type(JsonFieldType.NUMBER)
                            .description("?????? ID"),
                        PayloadDocumentation.fieldWithPath("data.city.cityName")
                            .type(JsonFieldType.STRING)
                            .description("?????????"),
                        PayloadDocumentation.fieldWithPath("data.city.country")
                            .type(JsonFieldType.STRING)
                            .description("?????????"),
                        PayloadDocumentation.fieldWithPath("data.city.createdAt")
                            .type(JsonFieldType.STRING)
                            .description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data.city.searchAt")
                            .type(JsonFieldType.STRING)
                            .description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data.startDate")
                            .type(JsonFieldType.STRING).description("?????? ?????????"),
                        PayloadDocumentation.fieldWithPath("data.endDate")
                            .type(JsonFieldType.STRING).description("?????? ?????????"),
                    )
                )
            )
    }
}