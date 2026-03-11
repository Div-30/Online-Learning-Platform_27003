package com.example.online_learning_platform.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_learning_platform.domain.ELocationType;
import com.example.online_learning_platform.domain.Location;
import com.example.online_learning_platform.repository.LocationRepository;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public String saveLocation(Location location, String parentId) {

        Location locationParent = null;

        if (parentId != null) {
            locationParent = locationRepository.findById(UUID.fromString(parentId)).orElse(null);

            if (locationParent == null) {
                return "Parent location not found.";
            } else {
                location.setParent(locationParent);
            }
        }

        if (!location.getType().equals(ELocationType.PROVINCE) && parentId == null) {
            return "Parent location is required for non-province locations.";
        }

        if (locationParent != null) {
            ELocationType parentType = locationParent.getType();
            ELocationType currentType = location.getType();

            if (currentType == ELocationType.DISTRICT && parentType != ELocationType.PROVINCE) {
                return "Invalid hierarchy: A District must belong to a Province.";
            } else if (currentType == ELocationType.SECTOR && parentType != ELocationType.DISTRICT) {
                return "Invalid hierarchy: A Sector must belong to a District.";
            } else if (currentType == ELocationType.CELL && parentType != ELocationType.SECTOR) {
                return "Invalid hierarchy: A Cell must belong to a Sector.";
            } else if (currentType == ELocationType.VILLAGE && parentType != ELocationType.CELL) {
                return "Invalid hierarchy: A Village must belong to a Cell.";
            }
        }

        Boolean checkLocation = locationRepository.existsByCode(location.getCode());
        if (checkLocation) {
            return "Location with this code already exists.";
        }

        locationRepository.save(location);
        return "Location saved successfully.";
    }

    public List<Location> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        if (!locations.isEmpty()) {
            return locations;
        } else {
            return null;
        }
    }
}
