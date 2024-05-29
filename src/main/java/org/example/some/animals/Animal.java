package org.example.some.animals;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public interface Animal extends Serializable {



// Для анімації руху
    void movement();

// Для налаштування випадкового напрямку руху
    void setRandomDirection();

// Зміна позиції об'єкта при перетягуванні мишкою
    void handleMouseDragged(MouseEvent event);

// Для запобігання відтягування тварини за межі визначеної зони
    void handleMouseReleased(MouseEvent event);

// Створення об'єкта AnimalMenu при натисканні на тварину
    void handleMouseClicked(MouseEvent event);

    // Для додавання меню
    void addMenu(double x, double y);

// Для прибирання меню
    void removeMenu();

// Для початку або відновлення руху тварини
    void play();

// Для зміни рівня ситості в тварини
    void hunger();

// Для зміни рівня води у тварини
    void thirst();

// Для годування (рівень ситості + n в залежності від тварини, віднімаються гроші з гаманця)
    void feed();

// Для того, щоб напоїти (рівень води + n в залежності від тварини)
    void drink();

// Для надання твариною продукту
    void giveProduct();

//    Для стану тварини
    void emotions();

// Для продажу тварини
    void sell();

// Поверненн рівня ситості
    int getHungerLvl();

// Поверненн рівня води
    int getThirstLvl();

// Поверненн вартості тварини
    int getCost();

 //Звук тварини
 void playSound();

 //Смерть
 void  death();

 //Дізнатись, чи тваринка на екрані
    boolean whetherIsOnScreen();
// Поверненн картинки тварини
ImageView getAnimalView();
}
