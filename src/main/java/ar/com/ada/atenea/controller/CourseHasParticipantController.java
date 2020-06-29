package ar.com.ada.atenea.controller;

import ar.com.ada.atenea.model.dto.CourseHasFinishedDTO;
import ar.com.ada.atenea.model.dto.CourseHasParticipantDTO;
import ar.com.ada.atenea.model.dto.ParticipantCourseApplicationDTO;
import ar.com.ada.atenea.model.dto.ScholarshipApprovalDTO;
import ar.com.ada.atenea.service.CourseHasParticipantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CourseHasParticipantController {

    @Autowired @Qualifier("courseHasParticipantServices")
    private CourseHasParticipantServices courseHasParticipantServices;

    //localhost:8080/courses/1/participants/1/
    @PreAuthorize("hasRole('PARTICIPANT')")
    @PostMapping({"/courses/{courseId}/participants/{participantId}", "/courses/{courseId}/participants/{participantId}/"})
    public ResponseEntity addNewParticipantCourseApplication(
            @Valid @RequestBody ParticipantCourseApplicationDTO participantCourseApplicationDTO,
            @PathVariable Long courseId, @PathVariable Long participantId) {

        CourseHasParticipantDTO courseHasParticipantDTOSaved = courseHasParticipantServices
                .saveCourseApplication(participantCourseApplicationDTO, courseId, participantId);

        return ResponseEntity.ok(courseHasParticipantDTOSaved);
    }

    //localhost:8080/courses/1/participants/1/approval
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"/courses/{courseId}/participants/{participantId}/approval", "/courses/{courseId}/participants/{participantId}/approval/"})
    public ResponseEntity scholarshipApproval(
            @Valid @RequestBody ScholarshipApprovalDTO scholarshipApprovalDTO,
            @PathVariable Long courseId, @PathVariable Long participantId) {

        CourseHasParticipantDTO courseHasParticipantDTOUpdated = courseHasParticipantServices
                .scholarshipApproval(scholarshipApprovalDTO, courseId, participantId);

        return ResponseEntity.ok(courseHasParticipantDTOUpdated);
    }

    //localhost:8080/courses/1/participants/1/finalize
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"/courses/{courseId}/participants/{participantId}/finalize", "/courses/{courseId}/participants/{participantId}/finalize/"})
    public ResponseEntity courseHasFinished(
            @Valid @RequestBody CourseHasFinishedDTO courseHasFinishedDTO,
            @PathVariable Long courseId, @PathVariable Long participantId) {

        CourseHasParticipantDTO courseHasParticipantDTOUpdated = courseHasParticipantServices
                .courseHasFinished(courseHasFinishedDTO, courseId, participantId);

        return ResponseEntity.ok(courseHasParticipantDTOUpdated);
    }

 }
