package com.example.Controller;

import java.util.List;

public interface RoomRepository {
    List<Room> findAll();
    void save(Room room);

}
