package com.example.demo.empleados.controller;

import com.example.demo.ApiResponse.ApiResponse;
import com.example.demo.empleados.dto.EmpleadoDTO;
import com.example.demo.empleados.service.EmpleadoService;
import jakarta.validation.TraversableResolver;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService service;

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<ApiResponse<String>> eliminar(@PathVariable Long id){
        try {
            service.eliminar(id);
            ApiResponse<String> repuesta = new ApiResponse<>(true,"Los datos del empleado se eliminaron correctamente");
            return ResponseEntity.ok(repuesta);
        }catch (Exception e){
            log.error("Error al momento de borrar los datos", id , e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false,"No se pudo borrar los datos del empleado"));
        }
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<ApiResponse<EmpleadoDTO>> actualizar(@PathVariable Long id,@Valid @RequestBody EmpleadoDTO dto){
        try {
            EmpleadoDTO dto1 = service.actualizar(dto,id);
            ApiResponse<EmpleadoDTO> repuesta = new ApiResponse<>(true,"Los datos se actualizaron correctamente", dto1);
            return ResponseEntity.ok(repuesta);
        }catch (Exception e){
            log.error("Los datos no se pudieron actualizar" , id , e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false,"Error al momento de actualizar los daos"));
        }
    }

    @PostMapping("/empleados")
    public ResponseEntity<ApiResponse<EmpleadoDTO>> crearEmpleado(@Valid @RequestBody EmpleadoDTO json){
        try {
            EmpleadoDTO dto = service.nuevoEmpleado(json);
            ApiResponse<EmpleadoDTO> repuesta = new ApiResponse<>(true,"Se cre el empleado correctamente",json);
            return ResponseEntity.status(HttpStatus.CREATED).body(repuesta);
        }catch (Exception e){
            log.error("Erro al crear el empleado" , e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false,"Erro al momento de intentar crear al empleado"));
        }
    }

    @GetMapping("/empleados")
    public ResponseEntity<ApiResponse<List<EmpleadoDTO>>> listaTodo(){
        try {
            List<EmpleadoDTO> list = service.obtenerTodo();
            ApiResponse<List<EmpleadoDTO>> repuesta = new ApiResponse<>(true,"Datos encontrados correctamente" , list);
            return ResponseEntity.ok(repuesta);
        }catch (Exception e){
            log.error("Error al cargar los datos " , e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false,"No se pudo cargar los datos"));
        }
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<ApiResponse<EmpleadoDTO>> buscarPorId(@PathVariable Long id){
        try {
            EmpleadoDTO dto = service.obtenerPorId(id);
            ApiResponse<EmpleadoDTO> repuesta = new ApiResponse<>(true,"Datos encontrados correctamente" , dto);
            return ResponseEntity.ok(repuesta);
        }catch (Exception e){
            log.error("Error al cargar los datos " , e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false,"No se pudo cargar los datos del empleado que busca"));

        }
    }
}
