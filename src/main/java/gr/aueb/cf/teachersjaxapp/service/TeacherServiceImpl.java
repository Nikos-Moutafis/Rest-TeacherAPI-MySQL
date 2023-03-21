package gr.aueb.cf.teachersjaxapp.service;


import gr.aueb.cf.teachersjaxapp.dao.ITeacherDAO;
import gr.aueb.cf.teachersjaxapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersjaxapp.dto.TeacherDTO;
import gr.aueb.cf.teachersjaxapp.model.Teacher;
import gr.aueb.cf.teachersjaxapp.service.exceptions.TeacherNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class TeacherServiceImpl implements ITeacherService {

    @Inject
    private  ITeacherDAO teacherDAO;

    @Override
    public Teacher insertTeacher(TeacherDTO teacherToInsert) throws TeacherDAOException {
        if (teacherToInsert == null) return null;

        try {
            Teacher teacher = mapTeacher(teacherToInsert);
            return teacherDAO.insert(teacher);

        } catch (TeacherDAOException e) {
            // e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher updateTeacher(TeacherDTO teacherToUpdate)
            throws TeacherDAOException, TeacherNotFoundException {

        if (teacherToUpdate == null) return null;

        try {
            if (teacherDAO.getById(teacherToUpdate.getId()) == null) {
                throw new TeacherNotFoundException("Teacher with id " + teacherToUpdate.getId()
                        + " was not found");
            }

            Teacher teacher = mapTeacher(teacherToUpdate);
            return teacherDAO.update(teacher);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            // e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTeacher(int id) throws TeacherDAOException, TeacherNotFoundException {
        try {
            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " not found");
            }
            teacherDAO.delete(id);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            //e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname)
            throws TeacherDAOException {
        List<Teacher> teachers = new ArrayList<>();
        if (lastname == null) return teachers;

        try {
            teachers = teacherDAO.getByLastname(lastname);
            return teachers;
        } catch (TeacherDAOException e) {
            // e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher getTeacherById(int id) throws TeacherDAOException {
        try {
            return teacherDAO.getById(id);
        }catch (TeacherDAOException e){
            throw e;
        }
    }

    private Teacher mapTeacher(TeacherDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}
