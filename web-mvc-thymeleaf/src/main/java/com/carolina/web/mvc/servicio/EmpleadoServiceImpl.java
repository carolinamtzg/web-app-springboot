package com.carolina.web.mvc.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolina.web.mvc.dao.EmpleadoRepository;
import com.carolina.web.mvc.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
	
	@Autowired
	private EmpleadoRepository empleadorepository;

	@Override
	public List<Empleado> listarEmpleados() {
		return empleadorepository.findAll();
	}

	@Override
	public Empleado guardarEmpleado(Empleado empleado) {
		return empleadorepository.save(empleado);
	}

	@Override
	public Empleado obtenerEmpleadoPorId(Long id) {
		return empleadorepository.findById(id).get();
	}

	@Override
	public void eliminarEmpleado(Long id) {
		empleadorepository.deleteById(id);
		
	}

}
