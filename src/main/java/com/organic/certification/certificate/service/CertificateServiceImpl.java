package com.organic.certification.certificate.service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.organic.certification.certificate.dtos.CertificateResponse;
import com.organic.certification.certificate.entity.Certificate;
import com.organic.certification.certificate.mappers.CertificateMapper;
import com.organic.certification.certificate.repository.CertificateRepository;
import com.organic.certification.common.exception.ResourceNotFoundException;
import com.organic.certification.farm.mappers.FarmMapper;
import com.organic.certification.inspection.entity.Inspection;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final CertificateMapper certificateMapper;
    private final CertificateRepository certificateRepository;
    private  final FarmMapper farmMapper;

    @Override
    public Page<CertificateResponse> getCertificates(Pageable pageable) {
        Page<Certificate> certificatesPage = certificateRepository.findAll(pageable);
        return certificatesPage.map(certificateMapper::toResponse);
    }

    @Override
    public CertificateResponse getCertificate(UUID id) {
        Certificate certificate = getCertificateByIdOrThrow(id);
        return certificateMapper.toResponse(certificate);
    }

    @Override
    public Certificate getCertificateByIdOrThrow(UUID id) {
        return certificateRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Certificate with id" + " " + id + " " + " not found "));
    }

    @Override
    public CertificateResponse generateCertificate(Inspection inspection) {
        Certificate certificate = new Certificate();
        certificate.setCertificateNumber(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        certificate.setInspection(inspection);
        certificate.setIssueDate(LocalDate.now());
        certificate.setExpiryDate(LocalDate.now().plusYears(1));
        certificate.setComplianceScore(inspection.getComplianceScore());

        // persist
       Certificate  savedCert = certificateRepository.save(certificate);

        // generate PDF and store
        String pdfpath =  generateCertificatePdf(savedCert);
        savedCert.setPdfUrl(pdfpath);
        savedCert = certificateRepository.save(savedCert); // update with URL

        return CertificateResponse.builder()
                .id(savedCert.getId())
                .certificateNumber(savedCert.getCertificateNumber())
                .issueDate(savedCert.getIssueDate())
                .expiryDate(savedCert.getExpiryDate())
                .pdfUrl(savedCert.getPdfUrl())
                .farmResponse(farmMapper.toResponse(inspection.getFarm()))
                .build();

    }

    public String generateCertificatePdf(Certificate cert) {
        try {
            String directory = "certificates";
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdirs(); // create directory if missing
            }

            String filePath =  directory + File.separator  + cert.getCertificateNumber() + ".pdf";


        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            Document document = new Document();
            PdfWriter.getInstance(document, fos);
            document.open();

            // Simple formatting
            Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
            Font textFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

            document.add(new Paragraph("Organic Certification Certificate", titleFont));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Certificate Number: " + cert.getCertificateNumber(), textFont));
            document.add(new Paragraph("Farmer: " + cert.getInspection().getFarm().getFarmer().getName(), textFont));
            document.add(new Paragraph("Farm: " + cert.getInspection().getFarm().getFarmName(), textFont));
            document.add(new Paragraph("Issue Date: " + cert.getIssueDate(), textFont));
            document.add(new Paragraph("Expiry Date: " + cert.getExpiryDate(), textFont));
            document.add(new Paragraph("Compliance Score: " + cert.getComplianceScore() + "%", textFont));

            document.close();
        }
        return filePath;  // return absolute/relative path
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate certificate PDF", e);
        }
    }
    }


