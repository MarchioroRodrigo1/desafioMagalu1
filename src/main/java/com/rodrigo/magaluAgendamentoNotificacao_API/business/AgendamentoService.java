package com.rodrigo.magaluAgendamentoNotificacao_API.business;



import com.rodrigo.magaluAgendamentoNotificacao_API.business.mapper.IAgendamentoMapper;
import com.rodrigo.magaluAgendamentoNotificacao_API.controller.dto.in.AgendamentoRecord;
import com.rodrigo.magaluAgendamentoNotificacao_API.controller.dto.out.AgendamentoRecordOut;
import com.rodrigo.magaluAgendamentoNotificacao_API.infrastructure.repositoris.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final IAgendamentoMapper agendamentoMapper;

    public AgendamentoRecordOut gravarAgendamento(AgendamentoRecord agendamento){
        return agendamentoMapper.paraOut(
                repository.save(
                        agendamentoMapper.paraEntity(agendamento)));
    }

}
