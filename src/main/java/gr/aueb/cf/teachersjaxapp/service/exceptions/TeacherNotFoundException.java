package gr.aueb.cf.teachersjaxapp.service.exceptions;


import gr.aueb.cf.teachersjaxapp.model.Teacher;

public class TeacherNotFoundException extends Exception {
    private final static long serialVersionUID = 1L;

    public TeacherNotFoundException(Teacher teacher) {
        super("Teacher with id " + teacher.getId() + " does not exist");
    }

    public TeacherNotFoundException(String s) {
        super(s);
    }
}
