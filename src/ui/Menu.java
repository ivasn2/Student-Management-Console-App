package ui;

import model.Student;
import repository.StudentRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private StudentRepository repository = new StudentRepository();
    private Scanner scanner = new Scanner(System.in);

    // Главный цикл приложения: отображает меню, считывает выбор пользователя и вызывает соответствующий метод.
    public void start() {

        // Вызов меню
        while (true) {

            System.out.println("=== УПРАВЛЕНИЕ СТУДЕНТАМИ ===");
            System.out.println("1. Добавить студента");
            System.out.println("2. Показать всех студентов");
            System.out.println("3. Найти студента по ID");
            System.out.println("4. Изменить данные студента");
            System.out.println("5. Удалить студента");
            System.out.println("0. Выход");

            // Обработка исключения
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    showAll();
                    break;

                case 3:
                    findById();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 0:
                    System.out.println("Выход из программы...");
                    return;
            }
        }







    }

    // Выводит список всех студентов. Если список пуст, отображает сообщение «Список пуст».
    public void showAll() {

        List<Student> students = repository.getAll();

        if (students.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        System.out.println("Всего студентов: " + repository.count());
        System.out.println(students);

    }

    // Запрашивает у пользователя ID студента, выполняет поиск и выводит найденного студента или сообщение «Не найден».
    public void findById() {

        System.out.print("Введите ID студента, которого хотите найти: ");
        int choiceId = scanner.nextInt();

        if (repository.getById(choiceId) == null) {
            System.out.println("Не найден");
            return;
        }

        System.out.println(repository.getById(choiceId));

    }

    // Запрашивает данные нового студента и передает их в метод repository.add() для добавления в хранилище.
    public void addStudent() {

        try {
            System.out.println("Введите имя студента: ");
            String nameStudent = scanner.nextLine();

            System.out.println("Введите возраст: ");
            int ageStudent = scanner.nextInt();

            System.out.println("Введите курс (1-6): ");
            int studentCourse = scanner.nextInt();

            if (studentCourse < 1 || studentCourse > 6) {
                throw new IllegalArgumentException("Курс должен быть от 1 до 6");
            }

            repository.add(nameStudent, ageStudent, studentCourse);
            System.out.println("Студент успешно добавлен!");

        } catch (InputMismatchException e) {
            System.out.println("Ошибка");
        }

    }

    // Запрашивает ID студента и новые данные, затем вызывает repository.update() для обновления информации.
    public void updateStudent() {

        try {
            System.out.print("Введите ID студента: ");
            int studentId = scanner.nextInt();

            if (repository.getById(studentId) == null) {
                System.out.println("Студент не найден");
                return;
            }

            System.out.print("Введите новое имя: ");
            String newStudentName = scanner.nextLine();

            System.out.print("Введите новый возраст: ");
            int newStudentAge = scanner.nextInt();

            System.out.print("Введите новый курс: ");
            int newStudentCourse = scanner.nextInt();

            repository.update(studentId, newStudentName, newStudentAge, newStudentCourse);
            System.out.println("Данные успешно обновлены");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // Запрашивает ID студента и вызывает repository.delete() для удаления записи из хранилища.
    public void deleteStudent() {

        System.out.print("Введите ID студента для удаления: ");
        int studentId = scanner.nextInt();

        if (repository.getById(studentId) == null) {
            System.out.println("Студент не найден");
            return;
        }

        repository.delete(studentId);
        System.out.println("Студент удален");

    }





}
