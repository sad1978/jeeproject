package com.techiekernel.ws.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;



import es.microforum.serviceapi.SueldoService;
import es.microforum.serviceimpl.SueldoServiceImpl;





@WebService
public class VariarSueldoWebService implements IVariarSueldoWebService{
	private SueldoService variarSueldo;
	  
	@WebMethod(exclude=true)
	public void setVariarSueldo(SueldoService variarSueldo){
		this.variarSueldo = variarSueldo;
	}
	@WebMethod(operationName="callVariarSueldo")
	public boolean callVariarSueldo(double porcentaje){
		return variarSueldo.variarSueldo(porcentaje);
	}
	

}
