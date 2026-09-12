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
import org.zapovednik.excursionservice.dto.request.CountryRequestDto;
import org.zapovednik.excursionservice.dto.response.CountryResponseDto;
import org.zapovednik.excursionservice.service.CountryService;

@RestController
@RequestMapping("/api/excursion-service/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @PostMapping
    public ResponseEntity<Long> save (@Valid @RequestBody final CountryRequestDto requestDto) {
        final Long countryId = countryService.save(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(countryId);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<CountryResponseDto> findById(@PathVariable final Long countryId) {
        final CountryResponseDto responseDto = countryService.findById(countryId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<CountryResponseDto>> findAll() {
        final List<CountryResponseDto> responseDtoList = countryService.findAll();

        return ResponseEntity.ok(responseDtoList);
    }

    @PutMapping("/{countryId}")
    public ResponseEntity<CountryResponseDto> updateById(
            @PathVariable final Long countryId,
            @Valid @RequestBody CountryRequestDto requestDto
    ) {
        final CountryResponseDto responseDto = countryService.updateById(countryId, requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long countryId) {
        countryService.deleteById(countryId);

        return ResponseEntity.noContent().build();
    }
}