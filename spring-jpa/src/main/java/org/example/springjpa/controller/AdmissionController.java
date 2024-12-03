package org.example.springjpa.controller;

import org.example.springjpa.model.Admission;
import org.example.springjpa.model.AdmissionRoom;
import org.example.springjpa.repository.AdmissionRoomRepository;
import org.example.springjpa.service.AdmissionService;
import org.example.springjpa.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private AdmissionRoomRepository admissionRoomRepository;

    @Autowired
    private RoomService roomService;

    // Add a new admission
    @PostMapping
    public ResponseEntity<Admission> createAdmission(@RequestBody Admission admission) {
        Admission savedAdmission = admissionService.createAdmission(admission);
        return ResponseEntity.ok(savedAdmission);
    }

    // Get all admissions
    @GetMapping
    public ResponseEntity<List<Admission>> getAllAdmissions() {
        List<Admission> admissions = admissionService.getAllAdmissions();
        return ResponseEntity.ok(admissions);
    }

    // Get an admission by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable Long id) {
        Admission admission = admissionService.getAdmissionById(id);
        return ResponseEntity.ok(admission);
    }

    // Update admission (including status and other fields)
    @PutMapping("/{id}")
    public ResponseEntity<Admission> updateAdmission(
            @PathVariable Long id,
            @RequestBody Admission updatedAdmission) {
        Admission savedAdmission = admissionService.updateAdmission(id, updatedAdmission);
        return ResponseEntity.ok(savedAdmission);
    }

    // Move admission to a new room
    @PutMapping("/{roomId}/move/{admissionId}")
    public AdmissionRoom moveAdmissionToNewRoom(@PathVariable Long roomId, @PathVariable Long admissionId, @RequestBody AdmissionRoom admissionRoom) {
        return roomService.movePatientToNewRoom(roomId, admissionId, admissionRoom);
    }

    // Get rooms associated with an admission
    @GetMapping("/{admissionId}/rooms")
    public List<AdmissionRoom> getAdmissionRooms(@PathVariable Long admissionId) {
        return admissionRoomRepository.findByAdmissionId(admissionId);
    }
}


