package com.example.rayan;

import com.example.rayan.repository.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RayanApplicationTests {

	//
	public final String REST_SERVICE_URI = "http://localhost:8080";
	// add the all repository variable
	private DoctorRepository doctorRepository;
	private DoctorTypeRepository doctorTypeRepository;
	private NoteRepository noteRepository;
 	private PatientRepository patientRepository;
	private RoleRepository roleRepository;


	@Test
	public void contextLoads() {
		// start of the test-ter
	}

	// get restTemplate
	public RestTemplate getRestTemplate(){ return  new RestTemplate(); }

	@Autowired
	public void setDoctorRepository(DoctorRepository doctorRepository) { this.doctorRepository = doctorRepository; }

	public DoctorRepository getDoctorRepository() { return doctorRepository; }

	@Autowired
	public void setDoctorTypeRepository(DoctorTypeRepository doctorTypeRepository) { this.doctorTypeRepository = doctorTypeRepository; }

	public DoctorTypeRepository getDoctorTypeRepository() { return doctorTypeRepository; }

	@Autowired
	public void setNoteRepository(NoteRepository noteRepository) { this.noteRepository = noteRepository; }

	public NoteRepository getNoteRepository() { return noteRepository; }

	@Autowired
	public void setPatientRepository(PatientRepository patientRepository) { this.patientRepository = patientRepository; }

	public PatientRepository getPatientRepository() { return patientRepository; }

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) { this.roleRepository = roleRepository; }

	public RoleRepository getRoleRepository() { return roleRepository; }

	// getting the url
	public final String getRestServiceUri() { return REST_SERVICE_URI; }

	/*
     * Add HTTP Authorization header, using Basic-Authentication to send user-credentials.
     */
    public HttpHeaders getHeaders(){
        // setting the passWord into the header
        String plainCredentials = "nabeel:ballistic";
        System.out.print("plainCredentials-------------->"+plainCredentials);
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
        System.out.print("base64Credentials-------------->"+base64Credentials);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        System.out.print("Headers----------->"+headers.toString());
        return headers;
    }

    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

}
