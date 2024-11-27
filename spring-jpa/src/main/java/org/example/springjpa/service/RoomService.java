package org.example.springjpa.service;

import org.example.springjpa.model.PatientRoom;
import org.example.springjpa.model.Room;
import org.example.springjpa.repository.PatientRoomRepository;
import org.example.springjpa.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PatientRoomRepository patientRoomRepository;

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

    public PatientRoom movePatientToNewRoom(Long roomId, Long patientId, LocalDate startDate) {
        // Find the patient room with no start date (i.e., not yet moved)
        PatientRoom patientRoom = patientRoomRepository.findByPatientIdAndStartDateIsNull(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found or already moved"));

        // Update the room and start date
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        patientRoom.setStartDate(startDate);
        patientRoom.setRoom(room);  // Set the Room object, not the roomId

        // Save the updated patient room
        return patientRoomRepository.save(patientRoom);
    }
}
