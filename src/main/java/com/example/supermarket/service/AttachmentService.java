package com.example.supermarket.service;

import com.example.supermarket.dto.attachment.AttachmentDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {
    List<Long> upload(List<MultipartFile> file);
    Resource download(Long id);
    AttachmentDTO findById(Long id);
    void delete(Long id);
}