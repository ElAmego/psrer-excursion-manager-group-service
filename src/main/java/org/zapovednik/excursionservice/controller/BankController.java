package org.zapovednik.excursionservice.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zapovednik.excursionservice.dto.request.BankRequestDto;
import org.zapovednik.excursionservice.dto.response.BankResponseDto;
import org.zapovednik.excursionservice.service.BankService;

@RestController
@RequestMapping("/api/excursion-service/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody final BankRequestDto requestDto) {
        final Long bankId = bankService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(bankId);
    }

    @GetMapping("/{bankId}")
    public ResponseEntity<BankResponseDto> findById(@PathVariable final Long bankId) {
        final BankResponseDto responseDto = bankService.findById(bankId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<BankResponseDto>> findAll() {
        final List<BankResponseDto> responseDtoList = bankService.findAll();

        return ResponseEntity.ok(responseDtoList);
    }

    @PutMapping("/{bankId}")
    public ResponseEntity<BankResponseDto> updateById(
            @PathVariable final Long bankId,
            @Valid @RequestBody final BankRequestDto requestDto
    ) {
        final BankResponseDto responseDto = bankService.updateById(bankId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{bankId}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long bankId) {
        bankService.deleteById(bankId);

        return ResponseEntity.noContent().build();
    }
}