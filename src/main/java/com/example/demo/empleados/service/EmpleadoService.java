package com.example.demo.empleados.service;

import com.example.demo.empleados.dto.EmpleadoDTO;
import com.example.demo.empleados.entity.EmpleadoEntity;
import com.example.demo.empleados.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private EmpleadoRepository repo;

    public EmpleadoDTO actualizar(EmpleadoDTO dto,Long id){
        Optional<EmpleadoEntity> opt = repo.findById(id);

        if(opt.isEmpty()){
            return null;
        }
        EmpleadoEntity entity = opt.get();
        return convertirADTO(repo.save(entity));
    }

    public boolean eliminar(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    public EmpleadoDTO obtenerPorId(Long id){
        Optional<EmpleadoEntity> opt = repo.findById(id);
        return opt.isEmpty()?convertirADTO(opt.get()):null;
    }

    public List<EmpleadoDTO> obtenerTodo(){
        List<EmpleadoDTO> list = new ArrayList<>();
        for(EmpleadoEntity e : repo.findAll()){
            list.add(convertirADTO(e));
        }
        return list;
    }

    public EmpleadoDTO nuevoEmpleado(EmpleadoDTO dto){
        EmpleadoEntity entity = convertirAEntity(dto);
        EmpleadoEntity guardar = repo.save(entity);
        return convertirADTO(guardar);
    }

    private EmpleadoEntity convertirAEntity(EmpleadoDTO dto){
        EmpleadoEntity e = new EmpleadoEntity();

        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setEmail(dto.getEmail());
        e.setFechaNacimiento(dto.getFechaNacimiento());
        e.setEstadoCivil(dto.getEstadoCivil());
        e.setSueldo(dto.getSueldo());

        return e;
    }

    private EmpleadoDTO convertirADTO(EmpleadoEntity e){

        EmpleadoDTO dto = new EmpleadoDTO();

        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setApellido(e.getApellido());
        dto.setEmail(e.getEmail());
        dto.setFechaNacimiento(e.getFechaNacimiento());
        dto.setEstadoCivil(e.getEstadoCivil());
        dto.setSueldo(e.getSueldo());

        return dto;
    }
}
