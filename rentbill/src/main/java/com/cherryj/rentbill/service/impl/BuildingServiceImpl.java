package com.cherryj.rentbill.service.impl;

import com.cherryj.rentbill.domain.Building;
import com.cherryj.rentbill.service.BuildingService;
import org.springframework.stereotype.Service;
import com.cherryj.base.service.BaseServiceImpl;

@Service
public class BuildingServiceImpl extends BaseServiceImpl<Building> implements BuildingService {


    @Override
    protected void initialBeforeUpdate(Building source, Building target) {

        target.setBuildingNumber(source.getBuildingNumber());

    }


}