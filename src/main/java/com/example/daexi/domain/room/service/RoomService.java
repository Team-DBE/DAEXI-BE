package com.example.daexi.domain.room.service;

import com.example.daexi.domain.room.entity.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    public final RoomRepository roomRepository;



}
