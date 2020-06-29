package ar.com.ada.atenea.service;
import ar.com.ada.atenea.component.BusinessLogicExceptionComponent;
import ar.com.ada.atenea.model.dto.CourseHasFinishedDTO;
import ar.com.ada.atenea.model.dto.CourseHasParticipantDTO;
import ar.com.ada.atenea.model.dto.ParticipantCourseApplicationDTO;
import ar.com.ada.atenea.model.dto.ScholarshipApprovalDTO;
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

    public CourseHasParticipantDTO saveCourseApplication(ParticipantCourseApplicationDTO dto, Long courseId, Long participantId) {
        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Course", courseId));

        Participant participant = participantRepository
                .findById(participantId)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("Participant", participantId));

        CourseParticipantId id = new CourseParticipantId()
                .setCourseId(course.getId())
                .setParticipantId(participant.getId());

        courseHasParticipantRepository
                .findById(id)
                .ifPresent(courseHasParticipant -> {
                    throw logicExceptionComponent.getExceptionApplicationAlreadyExists(id);
                });

        CourseHasParticipant courseHasParticipantToSave = new CourseHasParticipant()
                .setId(id)
                .setCourse(course)
                .setParticipant(participant);

        CourseHasParticipantDTO courseApplication;
        if (dto.getIsScholarship()) {
            courseApplication = this.saveCourseApplicationByScholarship(courseHasParticipantToSave);
        } else {
            courseApplication = this.saveCourseApplicationByPurchase(courseHasParticipantToSave);
        }
        return courseApplication;
    }
    public CourseHasParticipantDTO saveCourseApplicationByPurchase(CourseHasParticipant courseHasParticipantToSave) {
        Integer participantsCounter = courseHasParticipantToSave.getCourse().getParticipantsCounter();

        if (participantsCounter == 0)
            logicExceptionComponent.throwExceptionSoldOut(courseHasParticipantToSave.getCourse().getName());

        courseHasParticipantToSave.setHasApproved(true);
        courseHasParticipantToSave.setIsBuy(true);
        courseHasParticipantToSave.setPercentage(0);
        courseHasParticipantToSave.setHasFinish(false);

        CourseHasParticipant courseHasParticipantSaved = courseHasParticipantRepository.save(courseHasParticipantToSave);

        CourseHasParticipantDTO courseApplicationByPurchase = courseHasParticipantMapper.toDto(courseHasParticipantSaved, context);

        return courseApplicationByPurchase;
    }

    public CourseHasParticipantDTO saveCourseApplicationByScholarship(CourseHasParticipant courseHasParticipantToSave) {

        courseHasParticipantToSave.setIsBuy(false);
        courseHasParticipantToSave.setHasFinish(false);
        CourseHasParticipant courseHasParticipantSaved = courseHasParticipantRepository.save(courseHasParticipantToSave);

        CourseHasParticipantDTO courseApplicationByScholarship = courseHasParticipantMapper.toDto(courseHasParticipantSaved, context);
        return courseApplicationByScholarship;
    }

    public CourseHasParticipantDTO scholarshipApproval(ScholarshipApprovalDTO dto, Long courseId, Long participantId) {
        CourseParticipantId id = new CourseParticipantId()
                .setCourseId(courseId)
                .setParticipantId(participantId);

        CourseHasParticipant courseHasParticipant = courseHasParticipantRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("CourseHasParticipant", id));

        Integer scholarshipCounter = courseHasParticipant.getCourse().getScholarshipCounter();

        //Verifica si se puede aprobar el curso
        if (scholarshipCounter == 0)
            throw logicExceptionComponent.throwExceptionSoldOut(courseHasParticipant.getCourse().getName());

        //Si se puede aprobar, se settea los datos y se actualiza el contador de becas (" - 1")
        if (dto.getIsApproved()) {
            courseHasParticipant.setHasApproved(dto.getIsApproved());
            courseHasParticipant.setPercentage(dto.getScholarshipPercentage());
            courseHasParticipant.getCourse().setScholarshipCounter(scholarshipCounter - 1);

        } else {
            //Si no, solo se actualiza el "false" en el estatus de la aprobacion y el porcentaje a 0
            courseHasParticipant.setHasApproved(dto.getIsApproved());
            courseHasParticipant.setPercentage(0);
        }
        CourseHasParticipantDTO courseHasParticipantDTOUpdated = courseHasParticipantMapper.toDto(courseHasParticipant, context);

        return courseHasParticipantDTOUpdated;
    }

    public CourseHasParticipantDTO courseHasFinished(CourseHasFinishedDTO dto, Long courseId, Long participantId) {
        CourseParticipantId id = new CourseParticipantId()
                .setCourseId(courseId)
                .setParticipantId(participantId);

        CourseHasParticipant courseHasParticipant = courseHasParticipantRepository
                .findById(id)
                .orElseThrow(() -> logicExceptionComponent.throwExceptionEntityNotFound("CourseHasParticipant", id));

        if (!courseHasParticipant.getHasFinish() && dto.getCourseHasFinished()) {
            courseHasParticipant.setHasFinish(dto.getCourseHasFinished());
        }

        CourseHasParticipantDTO courseHasParticipantDTOUpdated = courseHasParticipantMapper.toDto(courseHasParticipant, context);

        return courseHasParticipantDTOUpdated;
    }

}