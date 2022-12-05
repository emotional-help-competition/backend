package com.epam.emotionalhelp.controller;

import com.epam.emotionalhelp.controller.config.CORSConfig;
import com.epam.emotionalhelp.model.Category;
import com.epam.emotionalhelp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.epam.emotionalhelp.controller.util.EndpointName.CATEGORY;

@RestController
@RequestMapping(path = CATEGORY)
@CrossOrigin(origins = CORSConfig.LOCALHOST)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;


    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryRepository.findCategoryByEmotionId(id);
        return categoryOptional.get();
    }
}
