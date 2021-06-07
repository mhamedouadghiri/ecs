//package com.ecs.backend.services;
//
//import com.ecs.backend.model.Application;
//import com.ecs.backend.repositories.ApplicationRepository;
//import com.ecs.backend.repositories.StudentRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ApplicationService {
//
//    final private ApplicationRepository applicationRepository;
//    final private StudentRepository studentRepository;
//
//    public ApplicationService(ApplicationRepository applicationRepository, StudentRepository studentRepository) {
//        this.applicationRepository = applicationRepository;
//        this.studentRepository = studentRepository;
//    }
//
//    public ResponseEntity<?> getApplications(Long offerId) {
//        Application application = (Application) applicationRepository.findByInternshipOfferId(offerId);
//        // i wanna fetch all the application and then all the students
//        if (application == null ){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        //henaya jani khassna nakhdo men internship hya li fiha dak student_id donc mnin student taypostuli l chi offre khass tzad fhadik la table tahya
//        //et comme ça nkdro njbdo all students li df3o lhadik l3ibaa
////        Student student = null;
////        ObjectMapper mapper = new ObjectMapper();
////        ArrayNode allapplications = mapper.createArrayNode();
////        for (Application application : applications){
////            try {
////                JsonNode applicationNode = mapper.convertValue(application, JsonNode.class);
////                ObjectNode jsonNode = applicationNode.deepCopy();
////
////                Optional<Student> studentInfo = studentRepository.findById(application.getStudentId());
////                jsonNode.set("studentInfo", studentInfo);
////                allapplications.add(jsonNode);
////            }catch (Exception ignoerd){
////                return null;
////            }
////        }
//        return applicationRepository.findAllByInternship(offerId);
//    }
//}
