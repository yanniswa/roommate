package com.example.Controller.domain.applicationservice;

import com.example.Controller.domain.model.Room;

import java.util.List;

public interface RoomRepository {
    List<Room> findAll();
    void save(Room room);

}
