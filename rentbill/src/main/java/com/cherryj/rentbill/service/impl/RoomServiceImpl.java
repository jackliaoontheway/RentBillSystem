package com.cherryj.rentbill.service.impl;

import com.cherryj.base.service.BaseServiceImpl;
import com.cherryj.rentbill.domain.Room;
import com.cherryj.rentbill.service.RoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends BaseServiceImpl<Room> implements RoomService {

    @Override
    protected void initialBeforeUpdate(Room source, Room target) {

        target.setRoomNumber(source.getRoomNumber());
        target.setStatus(source.getStatus());

    }

}