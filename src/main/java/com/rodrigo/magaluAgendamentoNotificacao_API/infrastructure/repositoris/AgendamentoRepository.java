package com.rodrigo.magaluAgendamentoNotificacao_API.infrastructure.repositoris;


import com.rodrigo.magaluAgendamentoNotificacao_API.infrastructure.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}