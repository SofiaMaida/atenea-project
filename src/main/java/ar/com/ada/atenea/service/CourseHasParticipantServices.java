package ar.com.ada.atenea.service;
import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.CourseHasParticipantDTO;
import ar.com.ada.atenea.model.entity.Course;
import ar.com.ada.atenea.model.entity.CourseHasParticipant;
import ar.com.ada.atenea.model.entity.CourseParticipantId;
import ar.com.ada.atenea.model.entity.Participant;
import ar.com.ada.atenea.model.mapper.CourseHasParticipantCycleMapper;
import ar.com.ada.atenea.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.atenea.model.repository.CourseHasParticipantRepository;
import ar.com.ada.atenea.model.repository.CourseRepository;
import ar.com.ada.atenea.model.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("courseHasParticipantServices")
public class CourseHasParticipantServices {

    CourseHasParticipantCycleMapper courseHasParticipantMapper = CourseHasParticipantCycleMapper.MAPPER;

    @Autowired @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    @Autowired @Qualifier("businessLogicExceptionComponent")
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired @Qualifier("courseRepository")
    private CourseRepository courseRepository;

    @Autowired @Qualifier("participantRepository")
    private ParticipantRepository participantRepository;

    @Autowired @Qualifier("courseHasParticipantRepository")
    private CourseHasParticipantRepository courseHasParticipantRepository;

    public CourseHasParticipantDTO courseApplication(CourseHasParticipantDTO dto, Long courseId, Long participantId) {
        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Course", courseId));

        Participant participant = participantRepository
                .findById(participantId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Participant", participantId));

        CourseHasParticipantDTO courseApplication;
        if (dto.getIsBuy()) {
            courseApplication = this.saveCourseApplicationByPurchase(participant, course);
        } else {
            courseApplication = this.saveCourseApplicationByScholarship(participant, course);
        }
        return courseApplication;
    }
    public CourseHasParticipantDTO saveCourseApplicationByPurchase(Participant participant, Course course) {
        if (course.getParticipantsCounter() == 0) {
            logicExceptionComponent.throwExceptionSoldOut(course.getName());
        }
        CourseParticipantId id = new CourseParticipantId();
        id.setCourseId(course.getId());
        id.setParticipantId(participant.getId());

        CourseHasParticipant courseHasParticipantToSave = new CourseHasParticipant();
        courseHasParticipantToSave.setId(id);
        courseHasParticipantToSave.setHasApproved(true);
        courseHasParticipantToSave.setIsBuy(true);
        courseHasParticipantToSave.setPercentage(0);
        courseHasParticipantToSave.setHasFinish(false);
        CourseHasParticipant courseHasParticipantSaved = courseHasParticipantRepository.save(courseHasParticipantToSave);

        int participantsCounter = course.getParticipantsCounter() - 1;
        course.setParticipantsCounter(participantsCounter);
        courseRepository.save(course);

        CourseHasParticipantDTO courseApplicationByPurchase = courseHasParticipantMapper.toDto(courseHasParticipantSaved, context);
        return courseApplicationByPurchase;
    }
    public CourseHasParticipantDTO saveCourseApplicationByScholarship(Participant participant, Course course) {
        CourseParticipantId id = new CourseParticipantId();
        id.setCourseId(course.getId());
        id.setParticipantId(participant.getId());

        CourseHasParticipant courseHasParticipantToSave = new CourseHasParticipant();
        courseHasParticipantToSave.setId(id);
        courseHasParticipantToSave.setHasApproved(null);
        courseHasParticipantToSave.setIsBuy(false);
        courseHasParticipantToSave.setPercentage(null);
        courseHasParticipantToSave.setHasFinish(false);
        CourseHasParticipant courseHasParticipantSaved = courseHasParticipantRepository.save(courseHasParticipantToSave);

        CourseHasParticipantDTO courseApplicationByScholarship = courseHasParticipantMapper.toDto(courseHasParticipantSaved, context);
        return courseApplicationByScholarship;
    }
}