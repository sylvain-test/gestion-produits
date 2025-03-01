package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Location;
import com.example.inventorymanagement.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.inventorymanagement.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location) {
        // Si vous avez besoin de vérifier l'unicité, vous pourriez :
        // locationRepository.findByName(location.getName()).ifPresent(loc -> {
        //     throw new DuplicateException("Nom d'emplacement déjà utilisé : " + location.getName());
        // });
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emplacement introuvable (ID : " + id + ")"));
    }

    public Location updateLocation(Long id, Location updatedLoc) {
        Location existing = getLocationById(id);
        existing.setName(updatedLoc.getName());
        existing.setDescription(updatedLoc.getDescription());
        return locationRepository.save(existing);
    }

    public void deleteLocation(Long id) {
        Location loc = getLocationById(id);
        locationRepository.delete(loc);
    }
}