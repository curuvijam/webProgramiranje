package services;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PorudzbinaService {

	private ObjectMapper mapper;
	private File file;
	
	public PorudzbinaService() {
		this.mapper = new ObjectMapper();
		this.file = new File("C:\\Users\\Marko\\workspace\\WebProject\\src\\data\\porudzbine.json");
	}
}
