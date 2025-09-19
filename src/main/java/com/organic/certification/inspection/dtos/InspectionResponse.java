package com.organic.certification.inspection.dtos;

import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.common.enums.InspectionEnum;
import com.organic.certification.farm.dtos.FarmResponse;
import com.organic.certification.farm.dtos.FarmResponseBasic;

import java.time.LocalDate;
import java.util.UUID;

public record InspectionResponse(
        UUID id,
        LocalDate date,
        InspectionEnum status,
        String inspectorName,
        FarmResponseBasic farmResponse
//        CertificateResponse certificateResponse
        ) {
}
