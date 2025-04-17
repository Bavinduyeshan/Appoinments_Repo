package com.Apooinments.Appoinments.service;


import com.Apooinments.Appoinments.model.Appoinments;
import com.Apooinments.Appoinments.model.PatientResponse;
import com.Apooinments.Appoinments.repositery.AppoinmentRepositery;
import feign.FeignException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppoinmentService {



    @Autowired
    private AppoinmentRepositery appoinmentRepositery;

    @Autowired
    private PatientServiceClient patientServiceClient;


//    public String createAppoinment(Appoinments appoinments){
//
//        try {
//            if (appoinments.getPatientID()==null){
//                throw  new RuntimeException("Patient Id is missing in request");
//            }
//
//            try {
//                PatientResponse patientResponse=patientServiceClient.validatePatient(appoinments.getPatientID());
//
//            }
//            catch (FeignException.NotFound e){
//                throw  new RuntimeException("Requested Patietn  ID" +appoinments.getPatientID() +"Does not exist");
//            }
//
//            appoinmentRepositery.save(appoinments);
//
//            return "Appoinmetn added succesfully";
//        }
//     catch (Exception ex) {
//        // Log the exception for more insight
//        System.err.println("Error occurred: " + ex.getMessage());
//        ex.printStackTrace();
//        throw new RuntimeException("An error occurred while adding the medical record: " + ex.getMessage());
//    }
//
//
//
//    }

    @Autowired
    private JavaMailSender mailSender; // For sending emails

    public String createAppoinment(Appoinments appoinments) {
        try {
            // Validate patient ID
            if (appoinments.getPatientID() == null) {
                throw new RuntimeException("Patient ID is missing in request");
            }

            // Fetch patient data and validate
            PatientResponse patientResponse;
            try {
                patientResponse = patientServiceClient.validatePatient(appoinments.getPatientID());
            } catch (FeignException.NotFound e) {
                throw new RuntimeException("Requested Patient ID " + appoinments.getPatientID() + " does not exist");
            }

            // Save the appointment
            appoinmentRepositery.save(appoinments);

            // Send email to patient
            sendAppointmentConfirmationEmail(patientResponse, appoinments);

            return "Appointment added successfully";
        } catch (Exception ex) {
            // Log the exception for debugging
            System.err.println("Error occurred: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("An error occurred while adding the appointment: " + ex.getMessage());
        }
    }

    private void sendAppointmentConfirmationEmail(PatientResponse patientResponse, Appoinments appoinments) {
        try {
            String patientEmail = patientResponse.getEmail();
            if (patientEmail == null || patientEmail.isEmpty()) {
                System.err.println("Patient email not found for patient ID: " + appoinments.getPatientID());
                return;
            }

            // Create a MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(patientEmail);
            helper.setSubject("Your Appointment Confirmation - HealthCare Hub");
            helper.setFrom("your-email@gmail.com"); // Replace with your sender email

            // HTML email content
            String htmlContent = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<meta charset='UTF-8'>" +
                    "</head>" +
                    "<body style='font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4;'>" +
                    "<div style='max-width: 600px; margin: 20px auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); overflow: hidden;'>" +
                    "<div style='background-color: #007bff; padding: 20px; text-align: center; color: #ffffff;'>" +
                    "<h1 style='margin: 0; font-size: 28px;'>Appointment Confirmed!</h1>" +
                    "</div>" +
                    "<div style='padding: 20px;'>" +
                    "<p style='font-size: 16px; color: #333333;'>Dear " +
                    (patientResponse.getFirstname() != null ? patientResponse.getFirstname() : "Patient") + ",</p>" +
                    "<p style='font-size: 16px; color: #333333;'>We’re pleased to inform you that your appointment has been successfully scheduled with HealthCare Hub.</p>" +
                    "<table style='width: 100%; margin: 20px 0; border-collapse: collapse;'>" +
                    "<tr style='background-color: #f8f9fa;'>" +
                    "<td style='padding: 10px; font-weight: bold; color: #555555;'>Doctor:</td>" +
                    "<td style='padding: 10px; color: #333333;'>" + appoinments.getDocname() + "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td style='padding: 10px; font-weight: bold; color: #555555;'>Date:</td>" +
                    "<td style='padding: 10px; color: #333333;'>" + appoinments.getAppoinment_Date() + "</td>" +
                    "</tr>" +
                    "<tr style='background-color: #f8f9fa;'>" +
                    "<td style='padding: 10px; font-weight: bold; color: #555555;'>Time:</td>" +
                    "<td style='padding: 10px; color: #333333;'>" + appoinments.getAppoinment_Time()+ "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td style='padding: 10px; font-weight: bold; color: #555555;'>Reason:</td>" +
                    "<td style='padding: 10px; color: #333333;'>" +
                    (appoinments.getReason() != null ? appoinments.getReason() : "Not specified") + "</td>" +
                    "</tr>" +
                    "</table>" +
                    "<p style='font-size: 16px; color: #333333;'>If you need to reschedule or cancel, please contact us at <a href='mailto:support@healthcarehub.com' style='color: #007bff; text-decoration: none;'>support@healthcarehub.com</a>.</p>" +
                    "</div>" +
                    "<div style='background-color: #007bff; padding: 15px; text-align: center; color: #ffffff;'>" +
                    "<p style='margin: 0; font-size: 14px;'>HealthCare Hub - Compassionate Care, Expert Solutions</p>" +
                    "<p style='margin: 5px 0 0; font-size: 12px;'>© 2025 HealthCare Hub. All rights reserved.</p>" +
                    "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true); // true indicates HTML content

            // Send the email
            mailSender.send(message);
            System.out.println("HTML appointment confirmation email sent to: " + patientEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email to " + patientResponse.getEmail() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<Appoinments> getAllAppoinments(){
        return  appoinmentRepositery.findAll();
    }


    public  String updateAppoinment(Integer appoinmentId,Appoinments appoinments){
        Optional<Appoinments>exisitnAppoinment=appoinmentRepositery.findById(appoinmentId);
        if (exisitnAppoinment.isPresent()){
            Appoinments updateAppoinemnt=exisitnAppoinment.get();

            updateAppoinemnt.setDocname(appoinments.getDocname());
            updateAppoinemnt.setAppoinment_Date(appoinments.getAppoinment_Date());
            updateAppoinemnt.setAppoinment_Time(appoinments.getAppoinment_Time());
            updateAppoinemnt.setReason(appoinments.getReason());

            appoinmentRepositery.save(updateAppoinemnt);
            return "Appoinment updated Succesfully";
        }
        else {
            throw  new RuntimeException("Appoinemn not found");
        }
    }
}
