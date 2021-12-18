package com.sipsewanaInstitue.bo.custom.impl;

import com.sipsewanaInstitue.bo.custom.StudentBO;
import com.sipsewanaInstitue.dao.DAOFactory;
import com.sipsewanaInstitue.dao.custom.StudentDAO;
import com.sipsewanaInstitue.dto.StudentDTO;
import com.sipsewanaInstitue.entity.Program;
import com.sipsewanaInstitue.entity.Student;
import com.sipsewanaInstitue.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public void add(StudentDTO studentDTO) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        session.getTransaction().begin();

        try {
            studentDAO.add(new Student(studentDTO.getSid(), studentDTO.getName(), studentDTO.getAddress(),
                    studentDTO.getContact(), studentDTO.getDob(), studentDTO.getGender()));
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        session.getTransaction().begin();

        String lastId = "";
        try {
            lastId = studentDAO.getLastId();
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
        return lastId;
    }

    @Override
    public List<StudentDTO> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        session.getTransaction().begin();

        List<StudentDTO> list = new ArrayList<>();
        try {
            List<Student> all = studentDAO.getAll();
            for (Student s : all) {
                list.add(new StudentDTO(s.getSid(), s.getName(), s.getAddress(), s.getContact(), s.getDob(), s.getGender()));
            }
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public StudentDTO search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        session.getTransaction().begin();

        StudentDTO studentDTO = null;
        try {
            Student s = studentDAO.search(id);
            studentDTO = new StudentDTO(s.getSid(), s.getName(), s.getAddress(), s.getContact(), s.getDob(), s.getGender());
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
        return studentDTO;
    }

    @Override
    public void update(StudentDTO student) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        session.getTransaction().begin();

        try {
            studentDAO.update(new Student(student.getSid(), student.getName(), student.getAddress(),
                    student.getContact(), student.getDob(), student.getGender()));
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        session.getTransaction().begin();

        try {
            studentDAO.delete(id);
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public List<StudentDTO> findStudents(String value) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        session.getTransaction().begin();

        List<StudentDTO> list = new ArrayList<>();
        try {
            List<Student> studentList = studentDAO.findStudent(value);
            for (Student s : studentList) {
                list.add(new StudentDTO(s.getSid(), s.getName(), s.getAddress(), s.getContact(), s.getDob(), s.getGender()));
            }
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
        return list;
    }
}
