package ist.challenge.controller;


import id.co.asyst.commons.core.component.Translator;
import id.co.asyst.commons.core.controller.BaseController;
import id.co.asyst.commons.core.payload.BaseParameter;
import id.co.asyst.commons.core.payload.BaseRequest;
import id.co.asyst.commons.core.payload.BaseResponse;
import id.co.asyst.commons.core.payload.Status;
import id.co.asyst.commons.core.validator.ValidatorType;
import ist.challenge.model.DatabaseModel;
import ist.challenge.service.ApllicationService;
import ist.challenge.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class ApllicationController extends BaseController {

    Logger logger = LogManager.getLogger();

    @Autowired
    private ApllicationService apllicationService;

    @Autowired
    private Validator validator;

    public ApllicationController(ApllicationService apllicationService, Validator validator) {
        this.apllicationService = apllicationService;
        this.validator = validator;
    }

    @PostMapping("/registration")
    public BaseResponse registration(@RequestBody BaseRequest<BaseParameter<DatabaseModel>> request) {
        BaseResponse response = new BaseResponse<DatabaseModel>();
        DatabaseModel databaseModel = request.getParameter().getData();
        try {
//            validator.validate(databaseModel, "registration");
            response.setResult(apllicationService.registration(databaseModel));
            response.setStatus(new Status("HTTP Status Code 201", Status.SUCCESS_DESC, "SUCCESS"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody BaseRequest<BaseParameter<DatabaseModel>> request) {
        BaseResponse response = new BaseResponse<DatabaseModel>();
        DatabaseModel databaseModel = request.getParameter().getData();
        try {
//            validator.validate(databaseModel, "login");
            response.setResult(apllicationService.login(databaseModel));
            response.setStatus(new Status("HTTP Status Code 200", Status.SUCCESS_DESC, "Sukses Login"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

    @PutMapping("/edit")
    public BaseResponse edit(@RequestBody BaseRequest<BaseParameter<DatabaseModel>> request) {
        BaseResponse response = new BaseResponse<DatabaseModel>();
        DatabaseModel databaseModel = request.getParameter().getData();
        try {
//            validator.validate(databaseModel, "edit");
            response.setResult(apllicationService.edit(databaseModel));
            response.setStatus(new Status("HTTP Status Code 201", Status.SUCCESS_DESC, "Sukses Edit"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

    @GetMapping("/list")
    public BaseResponse list(@RequestBody BaseRequest<BaseParameter<DatabaseModel>> request) {
        BaseResponse response = new BaseResponse<DatabaseModel>();
        DatabaseModel databaseModel = request.getParameter().getData();
        try {
//            validator.validate(databaseModel, "edit");
            response.setResult(apllicationService.list(databaseModel));
            response.setStatus(new Status("HTTP Status Code 201", Status.SUCCESS_DESC, "Sukses List"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

}

