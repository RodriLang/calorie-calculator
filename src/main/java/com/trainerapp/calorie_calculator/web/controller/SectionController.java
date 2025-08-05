package com.trainerapp.calorie_calculator.web.controller;

import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.SectionResponseDto;
import com.trainerapp.calorie_calculator.application.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @GetMapping
    public List<SectionResponseDto> getAllSections() {
        return sectionService.getSections();
    }

    @GetMapping("/{id}")
    public SectionResponseDto getSectionById(
            @PathVariable Long id
    ) {
        return sectionService.findById(id);
    }

    @PostMapping
    public SectionResponseDto createSection(
            @RequestBody SectionRequestDto sectionRequestDto
    ) {
        return sectionService.createSection(sectionRequestDto);
    }

    @PutMapping("/{id}")
    public SectionResponseDto updateSection(
            @PathVariable Long id,
            @RequestBody SectionRequestDto sectionRequestDto
    ) {
        return sectionService.updateSection(id, sectionRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
    }
}
