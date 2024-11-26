package org.example.springjpa.service;

import org.example.springjpa.model.Patient;
import org.example.springjpa.model.Room;
import org.example.springjpa.model.RoomPatientAssociation;
import org.example.springjpa.repository.PatientRepository;
import org.example.springjpa.repository.RoomPatientAssociationRepository;
import org.example.springjpa.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoomPatientAssociationRepository roomPatientAssociationRepository;

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room updatedRoom) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setName(updatedRoom.getName());
        room.setCharges(updatedRoom.getCharges());
        room.setDescription(updatedRoom.getDescription());
        return roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    // Method to assign a new room to a patient and track the move date
    public RoomPatientAssociation movePatientToNewRoom(Long roomId, Long patientId) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        Optional<Patient> patientOpt = patientRepository.findById(patientId);

        if (roomOpt.isPresent() && patientOpt.isPresent()) {
            Room room = roomOpt.get();
            Patient patient = patientOpt.get();

            // Check if the patient is already in a room
            Optional<RoomPatientAssociation> existingAssociation = roomPatientAssociationRepository
                    .findByPatientIdAndMoveDateIsNull(patientId); // Assume moveDate is null for current room

            if (existingAssociation.isPresent()) {
                // If patient is already assigned to a room, mark that association as completed (with move date)
                RoomPatientAssociation association = existingAssociation.get();
                association.setMoveDate(LocalDate.now()); // Set the move date for the old room
                roomPatientAssociationRepository.save(association); // Save the updated association
            }

            // Now create a new room-patient association for the new room
            RoomPatientAssociation newAssociation = new RoomPatientAssociation();
            newAssociation.setRoom(room);
            newAssociation.setPatient(patient);
            newAssociation.setMoveDate(LocalDate.now()); // Set the current date as move date for the new room
            return roomPatientAssociationRepository.save(newAssociation);
        } else {
            throw new RuntimeException("Room or Patient not found");
        }
    }
}
