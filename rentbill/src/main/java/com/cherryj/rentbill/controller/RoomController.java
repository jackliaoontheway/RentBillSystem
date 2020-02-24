package com.cherryj.rentbill.controller;

import com.cherryj.base.controller.BaseController;
import com.cherryj.rentbill.domain.Room;
import com.cherryj.rentbill.service.RoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController extends BaseController<Room, RoomService> {


}
