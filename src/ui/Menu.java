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

            try {

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

                    default:
                        System.out.println(
                                "Такого пункта меню не существует");
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Номер пункта меню должен быть числом!");
                scanner.nextLine();
            }
        }
    }

    // Выводит список всех студентов. Если список пуст, отображает сообщение «Список пуст».
    public void showAll () {

        List<Student> students = repository.getAll();

        if (students.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        System.out.println("Всего студентов: " + repository.count());
        System.out.println(students);

    }

    // Запрашивает у пользователя ID студента, выполняет поиск и выводит найденного студента или сообщение «Не найден».
    public void findById () {

        try {

            System.out.print("Введите ID студента: ");
            int id = scanner.nextInt();

            Student student = repository.getById(id);

            if (student == null) {
                System.out.println("Студент не найден");
                return;
            }

            System.out.println(student);

        } catch (InputMismatchException e) {

            System.out.println("ID должен быть числом!");
            scanner.nextLine();

        }
    }

    // Запрашивает данные нового студента и передает их в метод repository.add() для добавления в хранилище.
    public void addStudent () {

        try {

            System.out.print("Введите имя студента: ");
            String nameStudent = scanner.nextLine();

            // Проверка имени
            if (!nameStudent.matches("[а-яА-ЯёЁa-zA-Z ]+")) {
                throw new IllegalArgumentException(
                        "Имя должно содержать только буквы");
            }

            System.out.print("Введите возраст: ");
            int ageStudent = scanner.nextInt();

            if (ageStudent <= 0) {
                throw new IllegalArgumentException(
                        "Возраст должен быть больше 0");
            }

            if (ageStudent > 122) {
                throw new IllegalArgumentException(
                        "Возраст не должен быть больше 122");
            }

            System.out.print("Введите курс (1-6): ");
            int studentCourse = scanner.nextInt();

            if (studentCourse < 1 || studentCourse > 6) {
                throw new IllegalArgumentException(
                        "Курс должен быть от 1 до 6");
            }

            repository.add(nameStudent, ageStudent, studentCourse);
            System.out.println("Студент успешно добавлен!");

        } catch (InputMismatchException e) {

            System.out.println("Возраст и курс должны быть числами!");
            scanner.nextLine();

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

        }
    }

    // Запрашивает ID студента и новые данные, затем вызывает repository.update() для обновления информации.
    public void updateStudent () {

        try {

            System.out.print("Введите ID студента: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();

            if (repository.getById(studentId) == null) {
                System.out.println("Студент не найден");
                return;
            }

            System.out.print("Введите новое имя: ");
            String newName = scanner.nextLine();

            if (!newName.matches("[а-яА-ЯёЁa-zA-Z ]+")) {
                throw new IllegalArgumentException(
                        "Имя должно содержать только буквы");
            }

            System.out.print("Введите новый возраст: ");
            int newAge = scanner.nextInt();

            if (newAge <= 0) {
                throw new IllegalArgumentException(
                        "Возраст должен быть больше 0");
            }

            if (newAge > 122) {
                throw new IllegalArgumentException(
                        "Возраст не должен быть больше 122");
            }

            System.out.print("Введите новый курс (1-6): ");
            int newCourse = scanner.nextInt();

            if (newCourse < 1 || newCourse > 6) {
                throw new IllegalArgumentException(
                        "Курс должен быть от 1 до 6");
            }

            repository.update(
                    studentId,
                    newName,
                    newAge,
                    newCourse);

            System.out.println("Данные успешно обновлены");

        } catch (InputMismatchException e) {

            System.out.println("Возраст, курс и ID должны быть числами!");
            scanner.nextLine();

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

        }
    }

    // Запрашивает ID студента и вызывает repository.delete() для удаления записи из хранилища.
    public void deleteStudent () {

        try {

            System.out.print("Введите ID студента: ");
            int studentId = scanner.nextInt();

            if (repository.getById(studentId) == null) {
                System.out.println("Студент не найден");
                return;
            }

            repository.delete(studentId);
            System.out.println("Студент удален");

        } catch (InputMismatchException e) {

            System.out.println("ID должен быть числом!");
            scanner.nextLine();

        }
    }

}
