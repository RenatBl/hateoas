package ru.itis.hateoas;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.dto.ClientDto;
import ru.itis.hateoas.mappers.ClientsMapper;
import ru.itis.hateoas.services.ClientsService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ClientsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientsMapper clientsMapper;

    @MockBean
    private ClientsService clientsService;

    private ObjectId id = ObjectId.get();

    private ClientDto client = ClientDto.builder()
            .id(id)
            .firstName("Anton")
            .lastName("Pirogov")
            .worksQuantity(12)
            .total(12000.00)
            .score(1200)
            .build();

    @BeforeEach
    public void setUp() {
        when(clientsService.getClientById(id)).thenReturn(client);
    }

    @Test
    public void clientGetByIdTest() throws Exception {
        mockMvc.perform(put("/api/clients/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(client.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(client.getLastName()))
                .andExpect(jsonPath("$.worksQuantity").value(client.getWorksQuantity()))
                .andExpect(jsonPath("$.total").value(client.getTotal()))
                .andExpect(jsonPath("$.score").value(client.getScore()))
                .andDo(document("clients", responseFields(
                        fieldWithPath("firstName").description("Имя клиента"),
                        fieldWithPath("lastName").description("Фамилия клиента"),
                        fieldWithPath("worksQuantity").description("Количество заказанных работ"),
                        fieldWithPath("total").description("Сумма заказов клиента"),
                        fieldWithPath("score").description("Баллы клиента")
                )));
    }
}
