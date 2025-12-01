package ru.practicum.ewmservice.compilation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmservice.compilation.dto.CompilationDto;
import ru.practicum.ewmservice.compilation.service.CompilationService;

import java.util.Collection;

@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Slf4j
public class PublicCompilationController {

    private final CompilationService compilationService;

    @GetMapping
    public ResponseEntity<Collection<CompilationDto>> getCompilation(@RequestParam(name = "pinned", required = false) Boolean pinned,
                                                                     @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                                     @RequestParam(name = "size", defaultValue = "10") Integer size) {

        log.info("Публичный запрос на получение подборок");
        Collection<CompilationDto> compilations = compilationService.getCompilations(pinned, from, size);
        log.info("Подборки получена успешно");
        return ResponseEntity.ok(compilations);
    }

    @GetMapping("/{compId}")
    public ResponseEntity<CompilationDto> getCompilationById(@PathVariable Long compId) {
        log.info("Публичный запрос на получение подборки");
        CompilationDto compilation = compilationService.getCompilationById(compId);
        log.info("Подборка получена успешно");
        return ResponseEntity.ok(compilation);
    }


}
