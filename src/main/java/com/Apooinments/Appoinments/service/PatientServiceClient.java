package com.Apooinments.Appoinments.service;



import com.Apooinments.Appoinments.model.PatientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "user-service", url = "${USER_SERVICE_URL:user-service-url}")
public interface PatientServiceClient {

    @GetMapping("/validate/{patientId}")
    PatientResponse validatePatient(@PathVariable int patientId);

    @GetMapping("/get/{id}")
    PatientResponse getPatientData(@PathVariable int id);

}
