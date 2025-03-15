package com.rodrigo.magaluAgendamentoNotificacao_API.controllel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rodrigo.magaluAgendamentoNotificacao_API.business.AgendamentoService;
import com.rodrigo.magaluAgendamentoNotificacao_API.controller.AgendamentoController;
import com.rodrigo.magaluAgendamentoNotificacao_API.controller.dto.in.AgendamentoRecord;
import com.rodrigo.magaluAgendamentoNotificacao_API.controller.dto.out.AgendamentoRecordOut;
import com.rodrigo.magaluAgendamentoNotificacao_API.infrastructure.entities.Agendamento;
import com.rodrigo.magaluAgendamentoNotificacao_API.infrastructure.entities.enums.StatusNotificacaoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AgendamentoControllerTest {

    @Mock
    AgendamentoService agendamentoService;

    @InjectMocks
    AgendamentoController agendamentoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private AgendamentoRecord agendamentoRecord;
    private AgendamentoRecordOut agendamentoRecordOut;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build();
        objectMapper.registerModule(new JavaTimeModule());
        agendamentoRecord = new AgendamentoRecord("email@email.com", "54999307840",
                "TESTE URGENTE", LocalDateTime.of(2025, 01, 02, 11, 02, 01));

        agendamentoRecordOut = new AgendamentoRecordOut(4L, "email@email.com", "54999307840",
                "TESTE URGENTE", LocalDateTime.of(2025, 01, 02, 11, 02, 01),
        StatusNotificacaoEnum.AGENDADO);
    }

    @Test
    void deveCriarAgendamentoComSucesso() throws Exception {
        when(agendamentoService.gravarAgendamento(agendamentoRecord)).thenReturn(agendamentoRecordOut);
        mockMvc.perform(MockMvcRequestBuilders.post("/agendamento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(agendamentoRecord)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4L))
                .andExpect(jsonPath("$.emailDestinatario").value("email@email.com"))
                .andExpect(jsonPath("$.telefoneDestinatario").value(agendamentoRecordOut.telefoneDestinatario()))
                .andExpect(jsonPath("$.mensagem").value(agendamentoRecord.mensagem()))
                .andExpect(jsonPath("$.dataHoraEnvio").value("02-01-2025 11:02:01"))
                .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"));
        verify(agendamentoService, times(1)).gravarAgendamento(agendamentoRecord);
    }
}
