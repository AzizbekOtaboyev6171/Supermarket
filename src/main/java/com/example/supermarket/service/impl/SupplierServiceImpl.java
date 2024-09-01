package com.example.supermarket.service.impl;

import com.example.supermarket.dto.supplier.SupplierCreateDTO;
import com.example.supermarket.dto.supplier.SupplierDTO;
import com.example.supermarket.dto.supplier.SupplierUpdateDTO;
import com.example.supermarket.entity.Attachment;
import com.example.supermarket.entity.Supplier;
import com.example.supermarket.entity.SupplierBalance;
import com.example.supermarket.exceptions.ResourceAlreadyExistsException;
import com.example.supermarket.exceptions.ResourceConflictException;
import com.example.supermarket.exceptions.ResourceNotFoundException;
import com.example.supermarket.mapper.SupplierMapper;
import com.example.supermarket.repository.AttachmentRepository;
import com.example.supermarket.repository.SupplierBalanceRepository;
import com.example.supermarket.repository.SupplierRepository;
import com.example.supermarket.service.SupplierService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierBalanceRepository supplierBalanceRepository;
    private final AttachmentRepository attachmentRepository;
    private final SupplierMapper supplierMapper;

    @Override
    @Transactional
    public SupplierDTO create(SupplierCreateDTO supplierCreateDTO) {
        if (supplierRepository.existsByNameIgnoreCase(supplierCreateDTO.getName())) {
            throw new ResourceAlreadyExistsException("Supplier with this name already exists");
        }
        if (supplierRepository.existsByTinIgnoreCase(supplierCreateDTO.getTin())) {
            throw new ResourceAlreadyExistsException("Supplier with this TIN already exists");
        }
        List<Attachment> attachmentList = new ArrayList<>();
        for (Long attachmentId : supplierCreateDTO.getAttachment()) {
            if (attachmentId == null || attachmentId == 0) {
                continue;
            }
            Attachment attachment = attachmentRepository.findById(attachmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Attachment not found with ID: " + attachmentId));
            attachmentList.add(attachment);
        }
        Supplier supplier = new Supplier();
        supplier.setName(supplierCreateDTO.getName());
        supplier.setPhoneNumber(supplierCreateDTO.getPhoneNumber());
        supplier.setContactPerson(supplierCreateDTO.getContactPerson());
        supplier.setTin(supplierCreateDTO.getTin());
        supplier.setAddress(supplierCreateDTO.getAddress());
        supplier.setBankName(supplierCreateDTO.getBankName());
        supplier.setBankAccount(supplierCreateDTO.getBankAccount());
        supplier.setBankCode(supplierCreateDTO.getBankCode());
        supplier.setNote(supplierCreateDTO.getNote());
        supplier.setAttachmentList(attachmentList);
        Supplier savedSupplier = supplierRepository.save(supplier);
        for (Attachment attachment : attachmentList) {
            attachment.setSupplier(savedSupplier);
            attachmentRepository.save(attachment);
        }
        SupplierBalance supplierBalance = new SupplierBalance();
        supplierBalance.setSupplier(savedSupplier);
        SupplierBalance savedSupplierBalance = supplierBalanceRepository.save(supplierBalance);
        SupplierDTO supplierDTO = supplierMapper.toDTO(savedSupplier);
        supplierDTO.setSupplierBalanceDTO(supplierMapper.toDTO(savedSupplierBalance));
        return supplierDTO;
    }


    @Override
    @Transactional
    public SupplierDTO update(Long id, SupplierUpdateDTO supplierUpdateDTO) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        if (supplierRepository.existsByNameIgnoreCaseAndIdNot(supplierUpdateDTO.getName(), id)) {
            throw new ResourceAlreadyExistsException("Supplier with this name already exists");
        }
        if (supplierRepository.existsByTinIgnoreCaseAndIdNot(supplierUpdateDTO.getTin(), id)) {
            throw new ResourceAlreadyExistsException("Supplier with this TIN already exists");
        }
        List<Attachment> attachmentList = new ArrayList<>();
        for (Long attachmentId : supplierUpdateDTO.getAttachment()) {
            if (attachmentId == null || attachmentId == 0) {
                continue;
            }
            Attachment attachment = attachmentRepository.findById(attachmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Attachment not found with ID: " + attachmentId));
            attachmentList.add(attachment);
        }
        supplier.setName(supplierUpdateDTO.getName());
        supplier.setPhoneNumber(supplierUpdateDTO.getPhoneNumber());
        supplier.setContactPerson(supplierUpdateDTO.getContactPerson());
        supplier.setTin(supplierUpdateDTO.getTin());
        supplier.setAddress(supplierUpdateDTO.getAddress());
        supplier.setBankName(supplierUpdateDTO.getBankName());
        supplier.setBankAccount(supplierUpdateDTO.getBankAccount());
        supplier.setBankCode(supplierUpdateDTO.getBankCode());
        supplier.setNote(supplierUpdateDTO.getNote());
        supplier.setAttachmentList(attachmentList);
        Supplier updatedSupplier = supplierRepository.save(supplier);
        for (Attachment attachment : attachmentList) {
            attachment.setSupplier(updatedSupplier);
            attachmentRepository.save(attachment);
        }
        return supplierMapper.toDTO(updatedSupplier);
    }


    @Override
    public Optional<SupplierDTO> findById(Long id) {
        return Optional.ofNullable(supplierRepository.findById(id).map(supplierMapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("Supplier not found")));
    }

    @Override
    public List<SupplierDTO> searchActiveSuppliers(int page, int size, String keyword) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (keyword == null || keyword.isBlank()) {
            return supplierRepository.findAllByDeletedAtIsNull(pageRequest).map(supplierMapper::toDTO).toList();
        }
        return supplierRepository.searchByKeywordAndDeletedAtIsNull(keyword, pageRequest).map(supplierMapper::toDTO).toList();
    }

    @Override
    public List<SupplierDTO> searchInactiveSuppliers(int page, int size, String keyword) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (keyword == null || keyword.isBlank()) {
            return supplierRepository.findAllByDeletedAtIsNotNull(pageRequest).map(supplierMapper::toDTO).toList();
        }
        return supplierRepository.searchByKeywordAndDeletedAtIsNotNull(keyword, pageRequest).map(supplierMapper::toDTO).toList();
    }

    @Override
    public void deleteById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        if (supplier.getDeletedAt() != null) {
            throw new ResourceConflictException("Supplier already deleted");
        }
        supplier.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        supplierRepository.save(supplier);
    }

    @Override
    public void restoreById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
        if (supplier.getDeletedAt() == null) {
            throw new ResourceConflictException("Supplier already restored");
        }
        supplier.setDeletedAt(null);
        supplierRepository.save(supplier);
    }

    @Override
    public Boolean existsByName(String name) {
        return supplierRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public Boolean existsByNameAndId(String name, Long id) {
        return supplierRepository.existsByNameIgnoreCaseAndIdNot(name, id);
    }

    @Override
    public Boolean existsByTin(String tin) {
        return supplierRepository.existsByTinIgnoreCase(tin);
    }

    @Override
    public Boolean existsByTinAndId(String tin, Long id) {
        return supplierRepository.existsByTinIgnoreCaseAndIdNot(tin, id);
    }
}