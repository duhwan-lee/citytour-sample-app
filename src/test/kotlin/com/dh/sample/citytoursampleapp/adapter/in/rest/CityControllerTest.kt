package com.dh.sample.citytoursampleapp.adapter.`in`.rest

import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityRegisterRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityUpdateRequest
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
class CityControllerTest(
    private val mockMvc: MockMvc,
) {

    @Test
    fun testRegisterCity() {
        val request = CityRegisterRequest("Pangyo", "Korea")
        val jsonBody = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.perform(
            RestDocumentationRequestBuilders.post("/api/v1/city").content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "city_register",
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    PayloadDocumentation.requestFields(
                        PayloadDocumentation.fieldWithPath("cityName").description("도시명"),
                        PayloadDocumentation.fieldWithPath("country").description("국가명"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description("공통 메세지"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.OBJECT)
                            .description("공통 data object"),
                        PayloadDocumentation.fieldWithPath("data.cityId")
                            .type(JsonFieldType.NUMBER).description("도시 ID"),
                        PayloadDocumentation.fieldWithPath("data.cityName")
                            .type(JsonFieldType.STRING).description("도시명"),
                        PayloadDocumentation.fieldWithPath("data.country")
                            .type(JsonFieldType.STRING).description("국가명"),
                        PayloadDocumentation.fieldWithPath("data.createdAt")
                            .type(JsonFieldType.STRING).description("생성일"),
                        PayloadDocumentation.fieldWithPath("data.searchAt")
                            .type(JsonFieldType.NULL).description("조회일-없음"),
                    )
                )
            )
    }

    @Test
    fun testFindCityByCityName() {
        mockMvc.perform(
            RestDocumentationRequestBuilders.get("/api/v1/city")
                .param("cityName", "서울")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "city_findBy_cityName",
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    RequestDocumentation.queryParameters(
                        RequestDocumentation.parameterWithName("cityName").description("도시명"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description("공통 메세지"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.OBJECT)
                            .description("공통 data object"),
                        PayloadDocumentation.fieldWithPath("data.cityId")
                            .type(JsonFieldType.NUMBER).description("도시 ID"),
                        PayloadDocumentation.fieldWithPath("data.cityName")
                            .type(JsonFieldType.STRING).description("도시명"),
                        PayloadDocumentation.fieldWithPath("data.country")
                            .type(JsonFieldType.STRING).description("국가명"),
                        PayloadDocumentation.fieldWithPath("data.createdAt")
                            .type(JsonFieldType.STRING).description("생성일"),
                        PayloadDocumentation.fieldWithPath("data.searchAt")
                            .type(JsonFieldType.STRING).description("최근 조회일"),
                    )
                )
            )
    }

    @Test
    fun testFindCityByCityId() {
        mockMvc.perform(
            RestDocumentationRequestBuilders.get("/api/v1/city/{cityId}", 1)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "city_findBy_cityId",
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("cityId").description("도시 ID"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description("공통 메세지"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.OBJECT)
                            .description("공통 data object"),
                        PayloadDocumentation.fieldWithPath("data.cityId")
                            .type(JsonFieldType.NUMBER).description("도시 ID"),
                        PayloadDocumentation.fieldWithPath("data.cityName")
                            .type(JsonFieldType.STRING).description("도시명"),
                        PayloadDocumentation.fieldWithPath("data.country")
                            .type(JsonFieldType.STRING).description("국가명"),
                        PayloadDocumentation.fieldWithPath("data.createdAt")
                            .type(JsonFieldType.STRING).description("생성일"),
                        PayloadDocumentation.fieldWithPath("data.searchAt")
                            .type(JsonFieldType.STRING).description("최근 조회일"),
                    )
                )
            )
    }

    @Test
    fun testUpdateCity() {
        val request = CityUpdateRequest("Seoul-test")
        val jsonBody = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.perform(
            RestDocumentationRequestBuilders.put("/api/v1/city/{cityId}", 1).content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "city_update",
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("cityId").description("도시 ID"),
                    ),
                    PayloadDocumentation.requestFields(
                        PayloadDocumentation.fieldWithPath("cityName").description("도시명"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description("공통 메세지"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.OBJECT)
                            .description("공통 data object"),
                        PayloadDocumentation.fieldWithPath("data.cityId")
                            .type(JsonFieldType.NUMBER).description("도시 ID"),
                        PayloadDocumentation.fieldWithPath("data.cityName")
                            .type(JsonFieldType.STRING).description("도시명"),
                        PayloadDocumentation.fieldWithPath("data.country")
                            .type(JsonFieldType.STRING).description("국가명"),
                        PayloadDocumentation.fieldWithPath("data.createdAt")
                            .type(JsonFieldType.STRING).description("생성일"),
                        PayloadDocumentation.fieldWithPath("data.searchAt")
                            .type(JsonFieldType.STRING).description("최근 조회일"),
                    )
                )
            )
    }

    @Test
    fun testDeleteCity() {
        mockMvc.perform(
            RestDocumentationRequestBuilders.delete("/api/v1/city/{cityId}", 12)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "city_delete",
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("cityId").description("도시 ID"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description("공통 메세지"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.NULL)
                            .description("공통 data object"),
                    )
                )
            )
    }

    @Test
    fun testFindCityList() {
        mockMvc.perform(
            RestDocumentationRequestBuilders.get("/api/v1/city/list/{userId}", 1)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(
                MockMvcRestDocumentation.document(
                    "city_get_list",
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("userId").description("유저 ID"),
                    ),
                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                            .description("공통 메세지"),
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.OBJECT)
                            .description("공통 data object"),
                        PayloadDocumentation.fieldWithPath("data.onTourCityList")
                            .type(JsonFieldType.ARRAY)
                            .description("현재 여행 중 도시 목록"),
                        PayloadDocumentation.fieldWithPath("data.onTourCityList[].cityId")
                            .type(JsonFieldType.NUMBER)
                            .description("도시 ID"),
                        PayloadDocumentation.fieldWithPath("data.onTourCityList[].endDate")
                            .type(JsonFieldType.STRING)
                            .description("여행 종료 날짜"),
                        PayloadDocumentation.fieldWithPath("data.onTourCityList[].startDate")
                            .type(JsonFieldType.STRING)
                            .description("여행 시작 날짜"),
                        PayloadDocumentation.fieldWithPath("data.onTourCityList[].cityName")
                            .type(JsonFieldType.STRING)
                            .description("도시명"),
                        PayloadDocumentation.fieldWithPath("data.plannedCityList")
                            .type(JsonFieldType.ARRAY)
                            .description("여행 예정 도시 목록"),
                        PayloadDocumentation.fieldWithPath("data.plannedCityList[].cityId")
                            .type(JsonFieldType.NUMBER)
                            .description("도시 ID"),
                        PayloadDocumentation.fieldWithPath("data.plannedCityList[].startDate")
                            .type(JsonFieldType.STRING)
                            .description("여행 시작 날짜"),
                        PayloadDocumentation.fieldWithPath("data.plannedCityList[].cityName")
                            .type(JsonFieldType.STRING)
                            .description("도시명"),
                        PayloadDocumentation.fieldWithPath("data.defaultCityList")
                            .type(JsonFieldType.ARRAY)
                            .description("기본 도시 목록"),
                        PayloadDocumentation.fieldWithPath("data.defaultCityList[].cityId")
                            .type(JsonFieldType.NUMBER)
                            .description("도시 ID"),
                        PayloadDocumentation.fieldWithPath("data.defaultCityList[].country")
                            .type(JsonFieldType.STRING)
                            .description("국가명"),
                        PayloadDocumentation.fieldWithPath("data.defaultCityList[].cityName")
                            .type(JsonFieldType.STRING)
                            .description("도시명"),
                        PayloadDocumentation.fieldWithPath("data.defaultCityList[].createdAt")
                            .type(JsonFieldType.STRING)
                            .description("도시 등록일"),
                        PayloadDocumentation.fieldWithPath("data.defaultCityList[].searchAt")
                            .description("도시 조회일").optional(),
                    )
                )
            )
    }
}