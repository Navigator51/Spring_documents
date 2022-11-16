package su.goodcat.spring_documents.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import su.goodcat.commonlib.domain.CounterpartyDocumentRequestDTO;
import su.goodcat.commonlib.domain.FrontStatus;
import su.goodcat.commonlib.domain.Status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DocumentControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
       void getDocumentsByCounterparty() throws Exception {
        //given
        CounterpartyDocumentRequestDTO counterpartyDocumentRequestDTO = new CounterpartyDocumentRequestDTO(FrontStatus.INCOMING, Status.DRAFT, 4L, 1L);

        mockMvc.perform(post("/api/v1/document/counterparty/read")
                .content(objectMapper.writeValueAsString(counterpartyDocumentRequestDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}