package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.PropertyDTO;
import AoristHomes.AoristHomes.service.property.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyDTO> addProperty(@RequestBody PropertyDTO propertyDTO) {
        try {
            PropertyDTO createdProperty = propertyService.addProperty(propertyDTO);
            return ResponseEntity.ok(createdProperty);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable String id) {
        try {
            PropertyDTO propertyDTO = propertyService.getPropertyById(id);
            return ResponseEntity.ok(propertyDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable String id, @RequestBody PropertyDTO propertyDTO) {
        try {
            PropertyDTO updatedProperty = propertyService.updateProperty(id, propertyDTO);
            return ResponseEntity.ok(updatedProperty);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable String id) {
        try {
            propertyService.deleteProperty(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}