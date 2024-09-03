package com.example.supermarket.controller;

import com.example.supermarket.dto.attachment.AttachmentDTO;
import com.example.supermarket.dto.category.CategoryCreateDTO;
import com.example.supermarket.dto.category.CategoryDTO;
import com.example.supermarket.dto.category.CategoryUpdateDTO;
import com.example.supermarket.dto.supplier.SupplierCreateDTO;
import com.example.supermarket.dto.supplier.SupplierDTO;
import com.example.supermarket.dto.supplier.SupplierUpdateDTO;
import com.example.supermarket.dto.unit.UnitCreateDTO;
import com.example.supermarket.dto.unit.UnitDTO;
import com.example.supermarket.dto.unit.UnitUpdateDTO;
import com.example.supermarket.dto.unitTemplate.UnitTemplateCreateDTO;
import com.example.supermarket.dto.unitTemplate.UnitTemplateDTO;
import com.example.supermarket.dto.unitTemplate.UnitTemplateUpdateDTO;
import com.example.supermarket.service.*;
import io.swagger.v3.oas.annotations.Operation;
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

    @Autowired
    private UnitTemplateService unitTemplateService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/create_supplier")
    @Operation(summary = "Create a new supplier", description = "Create a new supplier with the given details", tags = {"Supplier"})
    public ResponseEntity<SupplierDTO> createSupplier(@Valid @RequestBody SupplierCreateDTO supplierCreateDTO) {
        return ResponseEntity.ok(supplierService.create(supplierCreateDTO));
    }

    @PostMapping(value = "/upload_file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Upload a file", description = "Upload a file to the server", tags = {"Attachment"})
    public ResponseEntity<List<Long>> uploadFile(@RequestPart("file") List<MultipartFile> file) {
        return ResponseEntity.ok(attachmentService.upload(file));
    }

    @GetMapping("/download_file/{id}")
    @Operation(summary = "Download a file", description = "Download a file from the server", tags = {"Attachment"})
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        Resource resource = attachmentService.download(id);
        AttachmentDTO attachmentDTO = attachmentService.findById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, attachmentDTO.getContentType())
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete_file/{id}")
    @Operation(summary = "Delete a file", description = "Delete a file from the server", tags = {"Attachment"})
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        attachmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check_supplier_by_name")
    @Operation(summary = "Check if a supplier exists by name", description = "Check if a supplier exists by name", tags = {"Supplier"})
    public ResponseEntity<Boolean> getSupplierByName(@RequestParam String name) {
        return ResponseEntity.ok(supplierService.existsByName(name));
    }

    @GetMapping("/check_supplier_by_tin")
    @Operation(summary = "Check if a supplier exists by tin", description = "Check if a supplier exists by tin", tags = {"Supplier"})
    public ResponseEntity<Boolean> getSupplierByTin(@RequestParam String tin) {
        return ResponseEntity.ok(supplierService.existsByTin(tin));
    }

    @PutMapping("/update_supplier/{id}")
    @Operation(summary = "Update a supplier", description = "Update a supplier with the given details", tags = {"Supplier"})
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierUpdateDTO supplierUpdateDTO) {
        return ResponseEntity.ok(supplierService.update(id, supplierUpdateDTO));
    }

    @DeleteMapping("/delete_supplier/{id}")
    @Operation(summary = "Delete a supplier", description = "Delete a supplier by id", tags = {"Supplier"})
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/restore_supplier/{id}")
    @Operation(summary = "Restore a supplier", description = "Restore a supplier by id", tags = {"Supplier"})
    public ResponseEntity<Void> restoreSupplier(@PathVariable Long id) {
        supplierService.restoreById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get_supplier/{id}")
    @Operation(summary = "Get a supplier", description = "Get a supplier by id", tags = {"Supplier"})
    public ResponseEntity<SupplierDTO> getSupplier(@PathVariable Long id) {
        Optional<SupplierDTO> supplierDTO = supplierService.findById(id);
        return supplierDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get_active_suppliers")
    @Operation(summary = "Get active suppliers", description = "Get active suppliers", tags = {"Supplier"})
    public ResponseEntity<List<SupplierDTO>> getActiveSuppliers(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(supplierService.searchActiveSuppliers(page, size, keyword));
    }

    @GetMapping("/get_inactive_suppliers")
    @Operation(summary = "Get inactive suppliers", description = "Get inactive suppliers", tags = {"Supplier"})
    public ResponseEntity<List<SupplierDTO>> getInactiveSuppliers(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(supplierService.searchInactiveSuppliers(page, size, keyword));
    }

    @GetMapping("/check_supplier_by_name_and_id/{name}/{id}")
    @Operation(summary = "Check if a supplier exists by name and id", description = "Check if a supplier exists by name and id", tags = {"Supplier"})
    public ResponseEntity<Boolean> getSupplierByNameAndId(@PathVariable String name, @PathVariable Long id) {
        return ResponseEntity.ok(supplierService.existsByNameAndId(name, id));
    }

    @GetMapping("/check_supplier_by_tin_and_id/{tin}/{id}")
    @Operation(summary = "Check if a supplier exists by tin and id", description = "Check if a supplier exists by tin and id", tags = {"Supplier"})
    public ResponseEntity<Boolean> getSupplierByTinAndId(@PathVariable String tin, @PathVariable Long id) {
        return ResponseEntity.ok(supplierService.existsByTinAndId(tin, id));
    }

    @PostMapping("/create_unit_template")
    @Operation(summary = "Create a new unit template", description = "Create a new unit template with the given details", tags = {"Unit Template"})
    public ResponseEntity<UnitTemplateDTO> createUnitTemplate(@Valid @RequestBody UnitTemplateCreateDTO unitTemplateCreateDTO) {
        return ResponseEntity.ok(unitTemplateService.createUnitTemplate(unitTemplateCreateDTO));
    }

    @PutMapping("/update_unit_template/{id}")
    @Operation(summary = "Update a unit template", description = "Update a unit template with the given details", tags = {"Unit Template"})
    public ResponseEntity<UnitTemplateDTO> updateUnitTemplate(@PathVariable Long id, @Valid @RequestBody UnitTemplateUpdateDTO unitTemplateUpdateDTO) {
        return ResponseEntity.ok(unitTemplateService.updateUnitTemplate(id, unitTemplateUpdateDTO));
    }

    @GetMapping("/get_unit_template/{id}")
    @Operation(summary = "Get a unit template", description = "Get a unit template by id", tags = {"Unit Template"})
    public ResponseEntity<UnitTemplateDTO> getUnitTemplate(@PathVariable Long id) {
        Optional<UnitTemplateDTO> unitTemplateDTO = unitTemplateService.findUnitTemplateById(id);
        return unitTemplateDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get_unit_templates")
    @Operation(summary = "Get all unit templates", description = "Get all unit templates", tags = {"Unit Template"})
    public ResponseEntity<List<UnitTemplateDTO>> getUnitTemplates() {
        return ResponseEntity.ok(unitTemplateService.getUnitTemplateDtoList());
    }

    @DeleteMapping("/delete_unit_template/{id}")
    @Operation(summary = "Delete a unit template", description = "Delete a unit template by id", tags = {"Unit Template"})
    public ResponseEntity<Void> deleteUnitTemplate(@PathVariable Long id) {
        unitTemplateService.deleteUnitTemplateById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create_unit")
    @Operation(summary = "Create a new unit", description = "Create a new unit with the given details", tags = {"Unit"})
    public ResponseEntity<UnitDTO> createUnit(@Valid @RequestBody UnitCreateDTO unitCreateDTO) {
        return ResponseEntity.ok(unitService.createUnit(unitCreateDTO));
    }

    @PutMapping("/update_unit/{id}")
    @Operation(summary = "Update a unit", description = "Update a unit with the given details", tags = {"Unit"})
    public ResponseEntity<UnitDTO> updateUnit(@PathVariable Long id, @Valid @RequestBody UnitUpdateDTO unitUpdateDTO) {
        return ResponseEntity.ok(unitService.updateUnit(id, unitUpdateDTO));
    }

    @GetMapping("/get_unit/{id}")
    @Operation(summary = "Get a unit", description = "Get a unit by id", tags = {"Unit"})
    public ResponseEntity<UnitDTO> getUnit(@PathVariable Long id) {
        Optional<UnitDTO> unitDTO = unitService.findUnitById(id);
        return unitDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get_units")
    @Operation(summary = "Get all units", description = "Get all units", tags = {"Unit"})
    public ResponseEntity<List<UnitDTO>> getUnits(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(unitService.searchActiveUnits(page, size, keyword));
    }

    @DeleteMapping("/delete_unit/{id}")
    @Operation(summary = "Delete a unit", description = "Delete a unit by id", tags = {"Unit"})
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        unitService.deleteUnitById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create_category")
    @Operation(summary = "Create a new category", description = "Create a new category with the given details", tags = {"Category"})
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryCreateDTO));
    }

    @PutMapping("/update_category/{id}")
    @Operation(summary = "Update a category", description = "Update a category with the given details", tags = {"Category"})
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryUpdateDTO));
    }

    @GetMapping("/get_category/{id}")
    @Operation(summary = "Get a category", description = "Get a category by id", tags = {"Category"})
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        Optional<CategoryDTO> categoryDTO = categoryService.findCategoryById(id);
        return categoryDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get_categories")
    @Operation(summary = "Get all categories", description = "Get all categories", tags = {"Category"})
    public ResponseEntity<List<CategoryDTO>> getCategories(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(categoryService.searchActiveCategories(page, size, keyword));
    }

    @DeleteMapping("/delete_category/{id}")
    @Operation(summary = "Delete a category", description = "Delete a category by id", tags = {"Category"})
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check_category_by_name")
    @Operation(summary = "Check if a category exists by name", description = "Check if a category exists by name", tags = {"Category"})
    public ResponseEntity<Boolean> getCategoryByName(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.existsByName(name));
    }

    @GetMapping("/check_category_by_name_and_id")
    @Operation(summary = "Check if a category exists by name and id", description = "Check if a category exists by name and id", tags = {"Category"})
    public ResponseEntity<Boolean> getCategoryByNameAndId(@RequestParam String name, @RequestParam Long id) {
        return ResponseEntity.ok(categoryService.existsByNameAndId(name, id));
    }

    @GetMapping("/count_active_categories")
    @Operation(summary = "Count active categories", description = "Count active categories", tags = {"Category"})
    public ResponseEntity<Integer> countActiveCategories() {
        return ResponseEntity.ok(categoryService.countActiveCategories());
    }
}