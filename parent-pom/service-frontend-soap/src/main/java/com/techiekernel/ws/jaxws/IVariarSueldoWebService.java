package com.techiekernel.ws.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;



@WebService
public interface IVariarSueldoWebService {

	@WebMethod(operationName="callVariarSueldo")
	public boolean callVariarSueldo(double porcentaje);

}
