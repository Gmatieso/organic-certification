package com.organic.certification.certificate.controller;

import com.organic.certification.certificate.dtos.CertificateRequest;
import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.certificate.entity.Certificate;
import com.organic.certification.certificate.service.CertificateService;
import com.organic.certification.common.config.ApiConfig;
import com.organic.certification.common.response.ApiResponseEntity;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
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

    @GetMapping("{id}/download")
    public ResponseEntity<FileSystemResource> downloadCertificate(@PathVariable UUID id) {
        CertificateResponse certResponse = certificateService.getCertificate(id);

        if (certResponse.pdfUrl() == null) {
            return ResponseEntity.notFound().build();
        }

        File file = new File(certResponse.pdfUrl());
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        FileSystemResource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getName() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }


}
