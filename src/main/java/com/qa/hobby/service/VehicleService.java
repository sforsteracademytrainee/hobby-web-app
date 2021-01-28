package com.qa.hobby.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.hobby.persistence.domain.VehicleDomain;
import com.qa.hobby.persistence.dto.VehicleAltDTO;
import com.qa.hobby.persistence.dto.VehicleDTO;
import com.qa.hobby.persistence.repos.VehicleRepo;
import com.qa.hobby.utils.MyBeanUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VehicleService {
	
	private VehicleRepo repo;
	private ModelMapper mapper;
	
	private VehicleDTO mapToDTO(VehicleDomain model) {
		return this.mapper.map(model, VehicleDTO.class);
	}
	
	private VehicleAltDTO mapToAltDTO(VehicleDomain model) {
		return this.mapper.map(model, VehicleAltDTO.class);
	}
	
	// Create
	public VehicleDTO create(VehicleDomain model) {
		return this.mapToDTO(this.repo.save(model));
	}
	
	// Read
	public VehicleDTO read(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	public VehicleAltDTO readKeeper(Long id) {
		return this.mapToAltDTO(this.repo.findById(id).orElseThrow());
	}
	
	public List<VehicleDTO> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	// Update
	public VehicleDTO update(Long id, VehicleDomain model) {
		VehicleDomain updatedModel = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(model, updatedModel);
		return this.mapToDTO(this.repo.save(updatedModel));
	}
	
	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
