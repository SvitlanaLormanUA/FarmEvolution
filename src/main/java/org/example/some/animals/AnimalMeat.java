package org.example.some.animals;

public interface AnimalMeat {

    //Отримання вартості
    int getProductCost();

    //Метод для отримання продукту
    void giveProduct();

    //Метод для додавання додатково меню
    void addMeatMenu(double x, double y);

    //Метод для видалення додаткового меню
    void removeMeatMenu();
}