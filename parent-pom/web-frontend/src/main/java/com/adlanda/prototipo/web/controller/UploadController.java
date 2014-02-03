package com.adlanda.prototipo.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {
	@Autowired
	private EmpleadoService empleadoService;
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getUploadForm(@PathVariable("id") String id, Model model) {
		UploadItem upload = new UploadItem();
		upload.setName(id);
		model.addAttribute(upload);
		return "upload/uploadForm";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String create(@PathVariable("id") String id,UploadItem uploadItem, BindingResult result,  HttpServletRequest request) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				logger.info("Error: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
			return "upload/uploadForm";
		}
		Empleado empleado = empleadoService.findById(id);
		// Some type of file processing...
		logger.info("-------------------------------------------");
		logger.info("Test upload: " + uploadItem.getName());
		logger.info("Test upload: "
				+ uploadItem.getFileData().getOriginalFilename());
		logger.info("-------------------------------------------");
        try {
            MultipartFile file = uploadItem.getFileData();
            String fileName = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                    inputStream = file.getInputStream();
                    if (file.getSize() > 50000) {
                            System.out.println("File Size:::" + file.getSize());
                            return "/uploadfile";
                    }
                    System.out.println("size::" + file.getSize());
                    fileName = request.getSession().getServletContext().getRealPath("/")+"/images/"+file.getOriginalFilename();
                    //outputStream = new FileOutputStream(fileName);
                    System.out.println("fileName:" + file.getOriginalFilename());

                    int readBytes = 0;
                    int posicion = 0;
                    byte[] buffer = new byte[50000];
                    byte[] buffer2 = new byte[50000];
                    while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                            //outputStream.write(buffer, 0, readBytes);
                    		for(int i=0; i< readBytes; i++){
                    			buffer2[posicion+i]=buffer[i];
                    		}
                    		posicion+=readBytes;
                    }
                    byte[] buffer3 = new byte[posicion];
                    for(int i = 0; i<posicion; i++)
                    	buffer3[i] = buffer2[i];
                    empleado.setImagen(buffer3);
                    empleadoService.update(empleado);
                    //outputStream.close();
                    inputStream.close();
            }

    } catch (Exception e) {
            e.printStackTrace();
    }

		return "upload/ok";
	}
}