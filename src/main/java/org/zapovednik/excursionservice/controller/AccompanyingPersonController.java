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
import org.zapovednik.excursionservice.dto.request.AccompanyingPersonRequestDto;
import org.zapovednik.excursionservice.dto.response.AccompanyingPersonResponseDto;
import org.zapovednik.excursionservice.service.AccompanyingPersonService;

@RestController
@RequestMapping("/api/excursion-service/accompanying-persons")
@RequiredArgsConstructor
public class AccompanyingPersonController {
    private final AccompanyingPersonService accompanyingPersonService;

    @PostMapping
    public ResponseEntity<Long> save (@Valid @RequestBody final AccompanyingPersonRequestDto requestDto) {
        final Long accompanyingPersonId = accompanyingPersonService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(accompanyingPersonId);
    }

    @GetMapping("/{accompanyingPersonId}")
    public ResponseEntity<AccompanyingPersonResponseDto> findById(@PathVariable final Long accompanyingPersonId) {
        final AccompanyingPersonResponseDto responseDto = accompanyingPersonService.findById(accompanyingPersonId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<AccompanyingPersonResponseDto>> findAll() {
        final List<AccompanyingPersonResponseDto> responseDtoList = accompanyingPersonService.findAll();

        return ResponseEntity.ok(responseDtoList);
    }

    @PutMapping("/{accompanyingPersonId}")
    public ResponseEntity<AccompanyingPersonResponseDto> updateById(
            @PathVariable final Long accompanyingPersonId,
            @Valid @RequestBody AccompanyingPersonRequestDto requestDto
    ) {
        final AccompanyingPersonResponseDto responseDto = accompanyingPersonService
                .updateById(accompanyingPersonId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{accompanyingPersonId}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long accompanyingPersonId) {
        accompanyingPersonService.deleteById(accompanyingPersonId);

        return ResponseEntity.noContent().build();
    }
}