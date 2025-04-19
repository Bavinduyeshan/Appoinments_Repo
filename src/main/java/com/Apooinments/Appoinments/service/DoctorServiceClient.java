package com.Apooinments.Appoinments.service;



import com.Apooinments.Appoinments.model.DoctorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service", url = "${DOCTOR_SERVICE_URL:doctor-service-url}")
public interface DoctorServiceClient {

    @GetMapping("validate/{doctorId}")
    DoctorResponse validateDoctor(@PathVariable Integer doctorId);
}
