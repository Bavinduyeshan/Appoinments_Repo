package com.Apooinments.Appoinments.controller;

import com.Apooinments.Appoinments.model.Appoinments;
import com.Apooinments.Appoinments.repositery.AppoinmentRepositery;
import com.Apooinments.Appoinments.service.AppoinmentService;
import com.Apooinments.Appoinments.service.PatientServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
// Adjust based on your frontend origin
@RestController
@RequestMapping("/appoinments")
public class AppoinmentController {



    @Autowired
    private AppoinmentService appoinmentService;

    @Autowired
    private PatientServiceClient patientServiceClient;

    private final AppoinmentRepositery appoinmentRepositery;

    public AppoinmentController(AppoinmentRepositery appoinmentRepositery) {
        this.appoinmentRepositery = appoinmentRepositery;
    }


    @PostMapping("/add")
    public ResponseEntity<String> addAppoinment(@RequestBody Appoinments appoinments){
        try {

            String message=appoinmentService.createAppoinment(appoinments);
            return  ResponseEntity.ok(message);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error" +ex.getMessage());
        }

    }


    @GetMapping("/getAll")
    public List<Appoinments>getAllAppoinmetns(){
        return  appoinmentService.getAllAppoinments();
    }


    @PutMapping("/update/{appoinmentId}")
    public  ResponseEntity<String>updateAppoinemnt(@PathVariable Integer appoinmentId,@RequestBody Appoinments appoinments){
        try {
            String result=appoinmentService.updateAppoinment(appoinmentId,appoinments);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
