package com.organic.certification.certificate.controller;

import com.organic.certification.certificate.dtos.CertificateRequest;
import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.certificate.service.CertificateService;
import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.common.response.ApiResponseEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(CertificateController.PATH)
@AllArgsConstructor
public class CertificateController {
    public static  final String PATH = ApiConfig.BASE_API_PATH + "certificate";
    private final CertificateService certificateService;

    @PostMapping
    public ResponseEntity<?> createCertificate( @Valid @RequestBody CertificateRequest certificateRequest){
        CertificateResponse response = certificateService.createCertificate(certificateRequest);
        return ApiResponseEntity.success("Certificate Created successfully", response);
    }

    @PostMapping("{id}")
    public ResponseEntity<?> updateCertificate(@PathVariable UUID id, @Valid @RequestBody CertificateRequest certificateRequest){
        CertificateResponse response = certificateService.updateCertificate(id,certificateRequest);
        return ApiResponseEntity.success("Certificate Updated successfully", response);
    }
}
