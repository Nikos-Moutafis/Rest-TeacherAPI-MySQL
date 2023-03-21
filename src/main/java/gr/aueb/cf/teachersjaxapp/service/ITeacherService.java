package gr.aueb.cf.teachersjaxapp.service;


import gr.aueb.cf.teachersjaxapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersjaxapp.dto.TeacherDTO;
import gr.aueb.cf.teachersjaxapp.model.Teacher;
import gr.aueb.cf.teachersjaxapp.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {

    /**
     * Inserts a {@link Teacher} based on the data carried by the
     * {@link TeacherDTO}.
     *
     * @param teacherToInsert
     * 			DTO object that contains the data.
     * @return
     *          The inserted teacher instance.
     * @throws TeacherDAOException
     * 			if any DAO exception happens.
     */
    Teacher insertTeacher(TeacherDTO teacherToInsert) throws TeacherDAOException;

    /**
     * Updates a {@link Teacher} based on the data carried by the
     * {@link TeacherDTO}.
     *
     * @param teacherToUpdate
     * 			DTO object that contains the data
     * 			of the new {@link Teacher}.
     * @return
     *          the update instance of the {@link Teacher}.
     * @throws TeacherNotFoundException
     * 			if any Teacher identified by their id
     * 			was not found.
     * @throws TeacherDAOException
     * 			if any DAO exception happens.
     */
    Teacher updateTeacher(TeacherDTO teacherToUpdate)
            throws TeacherDAOException, TeacherNotFoundException;

    /**
     * Deletes a {@link Teacher} based on the data carried by the
     * {@link TeacherDTO}.
     *
     * @param id
     * 			the id of the teacher to be deleted
     * @throws TeacherNotFoundException
     * 			if any Teacher needed to be deleted
     * 			has not found.
     * @throws TeacherDAOException
     * 			if any error happens between the driver
     * 			and the server at the DAO Level.
     */
    void deleteTeacher(int id) throws TeacherDAOException, TeacherNotFoundException;

    /**
     * Searches and gets back to the caller a list
     * of the {@link Teacher} objects identified
     * by their lastname or lastname's initial letters.
     *
     * @param lastname
     * 			a String object that contains the
     * 			surname or the letters that the
     * 			surname starts with, of the {@link Teacher}
     * 			objects we are looking for.
     * @return
     * 			a List that contains the results of
     * 			the search, that is a List of {@link Teacher}
     * 			objects.
     * @throws TeacherDAOException
     * 			if any error happens between the driver
     * 			and the server.
     */
    List<Teacher> getTeachersByLastname(String lastname)
            throws TeacherDAOException;

    Teacher getTeacherById(int id)throws TeacherDAOException;
}
