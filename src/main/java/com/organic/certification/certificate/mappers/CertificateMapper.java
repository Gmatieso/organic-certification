package com.organic.certification.certificate.mappers;

import com.organic.certification.certificate.dtos.CertificateRequest;
import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.certificate.entity.Certificate;
import org.mapstruct.Mapping;

public interface CertificateMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "farm", ignore = true)
    Certificate toEntity(CertificateRequest certificateRequest);

    @Mapping(target = "farmResponse", ignore = true)
    CertificateResponse toResponse(Certificate certificate);
}
