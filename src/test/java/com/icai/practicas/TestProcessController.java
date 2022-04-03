package com.icai.practicas;

import com.icai.practicas.controller.ResponseHTMLGenerator;
import com.icai.practicas.controller.ProcessController;
import com.icai.practicas.controller.ProcessController.DataRequest;
import com.icai.practicas.controller.ProcessController.DataResponse;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import org.junit.jupiter.api.Test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestProcessController {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void testing_api_credentials_for_login_then_ok(){
        //Given the link
        String address = "http://localhost:" + port + "/api/v1/process-step1";
		HttpHeaders httpHeaders = new HttpHeaders();
        DataRequest dataRequest = new DataRequest("Pedro", "71307593A", "+34633928281");
        //HTTP request
		HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataRequest, httpHeaders);

		//When
		ResponseEntity<ProcessController.DataResponse> result = this.restTemplate.postForEntity(address, request, DataResponse.class);

		//Then
		String resultExpected = "OK";
		DataResponse responseExpected = new DataResponse(resultExpected);

		then(result.getBody().result()).isEqualTo(resultExpected);
		then(result.getBody()).isEqualTo(responseExpected);

    }

    @Test
    public void testing_api_credentials_for_login_then_ko(){
        //Given the link
        String address = "http://localhost:" + port + "/api/v1/process-step1";
        HttpHeaders httpHeaders = new HttpHeaders();
		DataRequest dataRequest = new DataRequest("Pedro", "713075ZZZ", "+34633928281"); //DNI INCORRECTO
        //HTTP request
		HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataRequest, httpHeaders);

        //When
		ResponseEntity<ProcessController.DataResponse> result = this.restTemplate.postForEntity(address, request, DataResponse.class);


		//Then
		String resultExpected = "KO";
		DataResponse responseExpected = new DataResponse(resultExpected);

		then(result.getBody().result()).isEqualTo(resultExpected);
		then(result.getBody()).isEqualTo(responseExpected);



        dataRequest = new DataRequest("Pedro", "71307593A", "+3463SSa81"); //Telefono incorrecto
        //HTTP request
		request = new HttpEntity<>(dataRequest, httpHeaders);

        //When
		result = this.restTemplate.postForEntity(address, request, DataResponse.class);


		//Then
		responseExpected = new DataResponse(resultExpected);

		then(result.getBody().result()).isEqualTo(resultExpected);
		then(result.getBody()).isEqualTo(responseExpected);
    }

    @Test
    public void testing_api_credentials_legacy_for_login_then_ok(){
        //Given the url
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";
		MultiValueMap<String, String> data = new LinkedMultiValueMap<String, String>();
        
        data.add("fullName", "Pedro");
        data.add("dni", "71307593A");
        data.add("telefono", "+34633928281");
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, httpHeaders);

        //When
		ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

		//Then
		String resultExpected = ResponseHTMLGenerator.message1;
        then(result.getBody()).isEqualTo(resultExpected);
        
    }

    @Test
    public void testing_api_credentials_legacy_for_login_then_ko(){
        //Given the url
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";
		MultiValueMap<String, String> data = new LinkedMultiValueMap<String, String>();
        
        data.add("fullName", "Pedro");
        data.add("dni", "7Avad@");
        data.add("telefono", "+34633928281");
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, httpHeaders);

        //When
		ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

		//Then
		String resultExpected = ResponseHTMLGenerator.message2;
        then(result.getBody()).isEqualTo(resultExpected);
        
    }
    
}
