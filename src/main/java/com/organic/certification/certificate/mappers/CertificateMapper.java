package com.organic.certification.certificate.mappers;

import com.organic.certification.certificate.dtos.CertificateRequest;
import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.certificate.entity.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CertificateMapper {
    @Mapping(target = "id", ignore = true)
    Certificate toEntity(CertificateRequest certificateRequest);

    @Mapping(target = "farmResponse", ignore = true)
    @Mapping(source = "certificateNumber", target ="certificateNumber")
    CertificateResponse toResponse(Certificate certificate);

    /*TODO: map farm entity */
    /*TODO: map farmResponse */
}
