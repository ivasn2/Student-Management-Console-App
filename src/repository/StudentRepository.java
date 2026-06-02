package repository;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();
    private int nextId = 1;


    // Создаёт Student с nextId, добавляет в список
    public void add(String name, int age, int course) {

        Student student = new Student(nextId, name, age, course);
        students.add(student);
        nextId++;
    }

    // Возвращает копию всего списка студентов
    public List<Student> getAll() {

        ArrayList<Student> copyStudents = new ArrayList<>();
        copyStudents.addAll(students);
        return copyStudents;
    }

    // Ищет студента по ID, возвращает null если не найден
    public Student getById(int id) {

        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Обновляет данные. Возвращает false если ID не найден
    public boolean update(int id, String name, int age, int course) {

        Student student = getById(id);

        if (student == null) {
            return false;
        }
        student.setName(name);
        student.setAge(age);
        student.setCourse(course);

        return true;

    }

    // Удаляет студента. Возвращает false если ID не найден
    public boolean delete(int id) {

        Student student = getById(id);

        if (student == null) {
            return false;
        }

        students.remove(student);

        return true;
    }

    // Возвращает количество студентов в списке
    public int count() {
        return students.size();
    }

}
