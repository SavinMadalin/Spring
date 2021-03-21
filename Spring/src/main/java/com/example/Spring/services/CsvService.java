package com.example.Spring.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Spring.models.Car;
import com.example.Spring.models.Person;
import com.example.Spring.repository.CarRepository;

import com.example.Spring.repository.PersonRepository;

@Service
@Transactional
public class CsvService {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	private String line = "";
	
	public String getCsvContent() {
		Person p = new Person();
		try {
			BufferedReader brp = new BufferedReader(new FileReader("src/main/resources/Person.csv"));
			    line=brp.readLine() ; 
				String [] datap = line.split(";");
				p.setName(datap[0]);
				p.setAge(Integer.parseInt(datap[1]));
				p.setEmail(datap[2]);
				personRepository.save(p);
				
			BufferedReader brc  = new BufferedReader(new FileReader("src/main/resources/Cars.csv"));
				while((line = brc.readLine())!=null) {
					String[] datac = line.split(";");
					Car c = new Car();
					c.setPerson(p);
					c.setBrand(datac[0]);
					c.setCapacity(Integer.parseInt(datac[1]));
					carRepository.save(c);
		         }
		
		}
			
		catch (IOException e) {
			
			e.printStackTrace();
		}
		return "Data from CSV files was saved";
	}

}
