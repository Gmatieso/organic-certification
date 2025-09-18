package com.organic.certification.certificate.controller;

import com.organic.certification.certificate.dtos.CertificateRequest;
import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.certificate.service.CertificateService;
import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.common.response.ApiResponseEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(CertificateController.PATH)
@AllArgsConstructor
public class CertificateController {
    public static  final String PATH = ApiConfig.BASE_API_PATH + "certificate";
    private final CertificateService certificateService;




    @GetMapping
    public ResponseEntity<?> getCertificate(Pageable pageable){
        Page<CertificateResponse> response = certificateService.getCertificates(pageable);
        return ApiResponseEntity.success("Certificates retrieved successfully", response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCertificateById(@PathVariable UUID id){
        CertificateResponse response = certificateService.getCertificate(id);
        return ApiResponseEntity.success("Certificate retrieved successfully", response);
    }

}
