package com.rodrigo.magaluAgendamentoNotificacao_API.business;

import com.rodrigo.magaluAgendamentoNotificacao_API.business.mapper.IAgendamentoMapper;
import com.rodrigo.magaluAgendamentoNotificacao_API.controller.dto.in.AgendamentoRecord;
import com.rodrigo.magaluAgendamentoNotificacao_API.controller.dto.out.AgendamentoRecordOut;
import com.rodrigo.magaluAgendamentoNotificacao_API.infrastructure.entities.Agendamento;
import com.rodrigo.magaluAgendamentoNotificacao_API.infrastructure.entities.enums.StatusNotificacaoEnum;
import com.rodrigo.magaluAgendamentoNotificacao_API.infrastructure.repositoris.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private IAgendamentoMapper agendamentoMapper;

    private AgendamentoRecord agendamentoRecord;
    private AgendamentoRecordOut agendamentoRecordOut;
    private Agendamento agendamentoEntity;

    @BeforeEach
    void setUp(){

        agendamentoEntity = new Agendamento(4L, "email@email.com", "54999307840",
                 LocalDateTime.of(2025, 01, 02, 11, 02, 01),
                LocalDateTime.now(), null,
                "TESTE URGENTE",
                StatusNotificacaoEnum.AGENDADO);
        agendamentoRecord = new AgendamentoRecord("email@email.com", "54999307840",
                "TESTE URGENTE", LocalDateTime.of(2025, 01, 02, 11, 02, 01));

        agendamentoRecordOut = new AgendamentoRecordOut(4L, "email@email.com", "54999307840",
                "TESTE URGENTE", LocalDateTime.of(2025, 01, 02, 11, 02, 01),
                StatusNotificacaoEnum.AGENDADO);
    }

    @Test
    void deveGravarAgendamentoComSucesso(){
        when(agendamentoMapper.paraEntity(agendamentoRecord)).thenReturn(agendamentoEntity);
        when(agendamentoRepository.save(agendamentoEntity)).thenReturn(agendamentoEntity);
        when(agendamentoMapper.paraOut(agendamentoEntity)).thenReturn(agendamentoRecordOut);
        AgendamentoRecordOut out = agendamentoService.gravarAgendamento(agendamentoRecord);
        verify(agendamentoMapper, times(1)).paraEntity(agendamentoRecord);
        verify(agendamentoRepository, times(1)).save(agendamentoEntity);
        verify(agendamentoMapper, times(1)).paraOut(agendamentoEntity);
        assertThat(out).usingRecursiveComparison().isEqualTo(agendamentoRecordOut);
    }
}
