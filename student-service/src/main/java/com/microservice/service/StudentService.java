package com.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.microservice.app.StudentServiceApplication;
import com.microservice.controller.StudentController;
import com.microservice.entity.Student;
import com.microservice.repository.StudentRepository;
import com.microservice.request.CreateStudentRequest;
import com.microservice.response.AddressResponse;
import com.microservice.response.StudentResponse;

import reactor.core.publisher.Mono;

@Service
public class StudentService {


	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	WebClient webClient;
	
	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);

		//returning the student details with address.
		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(getAddressById(student.getAddressId()));;
		return studentResponse;
	}
	
	public StudentResponse getById (long id) {
		Student student = studentRepository.findById(id).get();
		//adding the address with student details
		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
		return studentResponse;
		
	}
	
	//get address by using Id
	public AddressResponse getAddressById(long addressId) {
		Mono<AddressResponse> addressResponse =	webClient.get()
														 .uri("/getById/{addressId}" , addressId)
													     .retrieve()
													     .bodyToMono(AddressResponse.class);
		return addressResponse.block();
	}
}
