package ru.practicum.ewmservice.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmservice.category.dto.CategoryDto;
import ru.practicum.ewmservice.category.service.CategoryService;

import java.util.Collection;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class PublicCategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Collection<CategoryDto>> getCategories(@RequestParam(name = "from", defaultValue = "0") Integer from,
                                                                 @RequestParam(name = "size", defaultValue = "10") Integer size) {
        log.info("Публичный запрос на получение категорий from={}, size={}", from, size);
        Collection<CategoryDto> categories = categoryService.getCategories(from, size);
        log.info("Получены категорий: {}", categories.size());
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long catId) {
        log.info("Публичный запрос на получение категории с id={}", catId);
        CategoryDto category = categoryService.getCategoryById(catId);
        log.info("Получена категория: id={}, name={}", category.getId(), category.getName());
        return ResponseEntity.ok(category);
    }
}
