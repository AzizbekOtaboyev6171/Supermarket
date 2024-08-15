package com.example.supermarket.service;

import com.example.supermarket.dto.attachment.AttachmentDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    AttachmentDTO upload(MultipartFile file);

    Resource download(Long id);

    AttachmentDTO findById(Long id);

    void delete(Long id);
}