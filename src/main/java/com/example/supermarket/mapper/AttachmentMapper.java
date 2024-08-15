package com.example.supermarket.mapper;

import com.example.supermarket.dto.attachment.AttachmentDTO;
import com.example.supermarket.entity.Attachment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment> {
}