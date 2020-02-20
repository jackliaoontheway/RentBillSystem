package com.cherryj.rentbill.controller;

import com.cherryj.rentbill.domain.Building;
import com.cherryj.rentbill.service.BuildingService;

import com.cherryj.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/building")
public class BuildingController extends BaseController<Building,BuildingService> {


}
