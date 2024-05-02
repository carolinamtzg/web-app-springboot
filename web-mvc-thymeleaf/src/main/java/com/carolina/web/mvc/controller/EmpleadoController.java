package com.carolina.web.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.carolina.web.mvc.entity.Empleado;
import com.carolina.web.mvc.servicio.EmpleadoService;

@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	
	@GetMapping({"/empleados", "/"})
	public String listarEmpleados(Model modelo) {
		modelo.addAttribute("lista_empleados", empleadoService.listarEmpleados());
		return "empleado"; // nombre del archivo html
	}
	
	@GetMapping("/empleados/new")
	public String formularioEmpleado(Model modelo) {
		Empleado empleado = new Empleado();
		modelo.addAttribute("empleado", empleado);
		return "crear_empleado";
	}
	
	@PostMapping("/empleados/new")
	public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
		empleadoService.guardarEmpleado(empleado);
		return "redirect:/empleados";
	}
	
	@GetMapping("/empleado/edit/{id}")
	public String formularioEditarEmpleado(@PathVariable Long id,Model modelo) {
		modelo.addAttribute("empleado",empleadoService.obtenerEmpleadoPorId(id) );
		
		return "editar_empleado";
	}
	
	@PostMapping("/empleado/edit/{id}")
	public String actualizarEmpleado(@PathVariable Long id, @ModelAttribute("empleado") Empleado empleado) {
		Empleado empleadoUpdate = empleadoService.obtenerEmpleadoPorId(id);
		
		empleadoUpdate.setNombre(empleado.getNombre());
		empleadoUpdate.setApellido(empleado.getApellido());
		empleadoUpdate.setEmail(empleado.getEmail());
		empleadoUpdate.setTelefono(empleado.getTelefono());
		empleadoUpdate.setDni(empleado.getDni());
		
		empleadoService.guardarEmpleado(empleadoUpdate);
		
		return "redirect:/empleados";
		
	}
	
	@GetMapping("/empleado/eliminar/{id}")
	public String eliminarEmpleado(@PathVariable Long id) {
		empleadoService.eliminarEmpleado(id);
		
		return "redirect:/empleados";
	}
	
	
}
