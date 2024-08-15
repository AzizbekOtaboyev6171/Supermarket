package com.example.supermarket.controller;

import com.example.supermarket.dto.attachment.AttachmentDTO;
import com.example.supermarket.dto.supplier.SupplierCreateDTO;
import com.example.supermarket.dto.supplier.SupplierDTO;
import com.example.supermarket.dto.supplier.SupplierUpdateDTO;
import com.example.supermarket.service.AttachmentService;
import com.example.supermarket.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supermarket")
public class MainController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping(value = "/create_supplier")
    public ResponseEntity<SupplierDTO> createSupplier(@Valid @RequestBody SupplierCreateDTO supplierCreateDTO) {
        return ResponseEntity.ok(supplierService.create(supplierCreateDTO));
    }

    @PostMapping(value = "/upload_file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AttachmentDTO> uploadFile(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(attachmentService.upload(file));
    }

    @GetMapping("/download_file/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        Resource resource = attachmentService.download(id);
        AttachmentDTO attachmentDTO = attachmentService.findById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, attachmentDTO.getContentType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete_file/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        attachmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check_supplier_by_name")
    public ResponseEntity<Boolean> getSupplierByName(@RequestParam String name) {
        return ResponseEntity.ok(supplierService.existsByName(name));
    }

    @GetMapping("/check_supplier_by_tin")
    public ResponseEntity<Boolean> getSupplierByTin(@RequestParam String tin) {
        return ResponseEntity.ok(supplierService.existsByTin(tin));
    }

    @PutMapping("/update_supplier/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierUpdateDTO supplierUpdateDTO) {
        return ResponseEntity.ok(supplierService.update(id, supplierUpdateDTO));
    }

    @DeleteMapping("/delete_supplier/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/restore_supplier/{id}")
    public ResponseEntity<Void> restoreSupplier(@PathVariable Long id) {
        supplierService.restoreById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get_supplier/{id}")
    public ResponseEntity<SupplierDTO> getSupplier(@PathVariable Long id) {
        Optional<SupplierDTO> supplierDTO = supplierService.findById(id);
        return supplierDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get_active_suppliers")
    public ResponseEntity<List<SupplierDTO>> getActiveSuppliers(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(supplierService.searchActiveSuppliers(page, size, keyword));
    }

    @GetMapping("/get_inactive_suppliers")
    public ResponseEntity<List<SupplierDTO>> getInactiveSuppliers(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(supplierService.searchInactiveSuppliers(page, size, keyword));
    }

    @GetMapping("/check_supplier_by_name_and_id/{name}/{id}")
    public ResponseEntity<Boolean> getSupplierByNameAndId(@PathVariable String name, @PathVariable Long id) {
        return ResponseEntity.ok(supplierService.existsByNameAndId(name, id));
    }

    @GetMapping("/check_supplier_by_tin_and_id/{tin}/{id}")
    public ResponseEntity<Boolean> getSupplierByTinAndId(@PathVariable String tin, @PathVariable Long id) {
        return ResponseEntity.ok(supplierService.existsByTinAndId(tin, id));
    }
}