package com.organic.certification.farm.controller;

import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.farm.dtos.FarmRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(FarmController.PATH)
@AllArgsConstructor
public class FarmController {

    public static final String PATH = ApiConfig.BASE_API_PATH + "/farm";

    @PostMapping
    public ResponseEntity<?> createFarm(@Valid  @RequestBody FarmRequest farmRequest) {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getAllFarms(Pageable pageable) {
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getFarm(@PathVariable UUID id) {
        return null;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateFarm(@PathVariable UUID id, @RequestBody FarmRequest farmRequest){
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFarm(@PathVariable UUID id){
            return null;
    }
}
