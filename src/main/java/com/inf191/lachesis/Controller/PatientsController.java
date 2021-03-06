package com.inf191.lachesis.Controller;

import com.inf191.lachesis.Server.PatientsService;
import com.inf191.lachesis.Server.PatientsServiceImpl;
import com.inf191.lachesis.generate.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value="/api")
@CrossOrigin

public class PatientsController {

    @Autowired
    private PatientsServiceImpl patientsServiceImpl;

    /**
     * @api {get} /getbyid={id}         request patients informaiton
     * @apiName getPatients
     * @apiGroup patients
     *
     * @apiParam {id} id patients unique ID.
     *
     * @apiSuccess {String} JSON Type information
     * @apiSuccess {String} error 500
     */
    @RequestMapping(method=RequestMethod.GET ,value ="/getbyid={id}")
    public Patients getPatients(@PathVariable ("id") int id){
        return patientsServiceImpl.selectByPrimaryKey(id);
    }

    /**
     * @api {Post}  /uploadPatientsByJSON        upload patients info
     * @apiName uploadPatients
     * @apiGroup patients
     *
     * Required body Json type
     *
     * @apiSuccess {String} return toString type
     * @apiSuccess {String} error 500
     */
    @RequestMapping(method = RequestMethod.POST ,value="/uploadPatientsByJSON")
    public int uploadPatients(@RequestBody @Valid Patients patients){
        Patients localPatient = new Patients();
        localPatient.setDobday(patients.getDobday());
        localPatient.setDobmonth(patients.getDobmonth());
        localPatient.setDobyear(patients.getDobyear());
        localPatient.setFirstName(patients.getFirstName());
        localPatient.setLastName(patients.getLastName());
        localPatient.setMid(patients.getMid());
        localPatient.setWeight(patients.getWeight());
        localPatient.setHeight(patients.getHeight());
        patientsServiceImpl.insert(localPatient);
        return localPatient.getPid();
    }

    /**
     * @api {post} /updatePatientsByJSON        update by patients id
     * @apiName updatePatietns
     * @apiGroup patients
     *
     *
     * @apiSuccess {int} integer
     * @apiSuccess {String} error 500
     */
    @RequestMapping(method = RequestMethod.POST, value="/updatePatientsByJSON")
    public int updatePatietns(@RequestBody @Valid Patients patietns){
        return patientsServiceImpl.updateByPrimaryKey(patietns);
    }

    /**
     * @api {get} /delateById={id}         delate patients informaiton
     * @apiName delatePatientById
     * @apiGroup patients
     *
     * @apiParam {id} id patients unique ID.
     *
     * @apiSuccess {int} integer
     * @apiSuccess {String} error 500
     */
    @RequestMapping(method = RequestMethod.GET, value = "/delateById={id}")
    public int delatePatientById(@PathVariable("id") int id){
        return patientsServiceImpl.deleteByPrimaryKey(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllPatients")
    public List<Patients> getAllPatients(){return patientsServiceImpl.selectAllPatients();}

    @RequestMapping(method = RequestMethod.GET, value = "/getActivePatients")
    public List<Patients> getActivepatients(){return patientsServiceImpl.getActivePatietns();}

}
