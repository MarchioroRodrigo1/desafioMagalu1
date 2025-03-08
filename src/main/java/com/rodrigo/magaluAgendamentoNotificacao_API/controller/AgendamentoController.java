package com.rodrigo.magaluAgendamentoNotificacao_API.controller;

import com.rodrigo.magaluAgendamentoNotificacao_API.business.AgendamentoService;
import com.rodrigo.magaluAgendamentoNotificacao_API.controller.dto.in.AgendamentoRecord;
import com.rodrigo.magaluAgendamentoNotificacao_API.controller.dto.out.AgendamentoRecordOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoRecordOut> gravarAgendamentos(@RequestBody AgendamentoRecord agendamento) {
        return ResponseEntity.ok(agendamentoService.gravarAgendamento(agendamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoRecordOut> buscarAgendamento(@PathVariable("id") Long id){
        return ResponseEntity.ok(agendamentoService.buscarAgendamentoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable("id") Long id){
        agendamentoService.canelarAgendamento(id);
        return ResponseEntity.accepted().build();
    }
}