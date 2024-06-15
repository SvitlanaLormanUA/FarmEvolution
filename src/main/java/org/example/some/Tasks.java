package org.example.some;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.example.some.otherGameObjects.Wallet;


public class Tasks {
    private Pane root;
    private AnchorPane main;
    private ImageView menuView;
    private Wallet wallet;
    private Storage storage;
    private double x;
    private double y;
    private  Label taskLabel;
    private  Label taskLabel2;
    private  Label taskLabel3;
    private  Label taskLabel4;
    private Button close;

    private Button nextLvlButton;
    private boolean nextLvL;


    public boolean task1 ;
    public boolean task2 ;
    public boolean task3 ;
    public boolean task4 ;

    private int nWool;
    private int nEggs;
    private int soldMilk;
    private int soldPig;
    private int soldRabbit;
    private int driedDragonfly;
    private int soldMango;
    private int gatheredNuts;
    private int gatheredBananas;
    private int soldFeathers;

    int nCoins;
    public Text text1, text2, text3, text4;
    private boolean firstLevel = false;
    private boolean secondLevel = false;
    private boolean thirdLevel = false;

    AnchorPane anchorPane;
    Scene scene;
    FinishLevel finishLevel;


    public Tasks(AnchorPane main, int level) {
        this.main = main;
        this.wallet = FirstLevel.wallet;
        this.storage = FirstLevel.storage;
        this.nCoins = 0;
        task1 = false;
        task2 = false;
        task3 = false;
        task4 = false;
        nextLvL = false;

        switch (level) {
            case 1 -> {
                firstLevel = true;
                secondLevel = false;
                thirdLevel = false;

                if (storage!=null) {
                    nWool = storage.getnWoolP();
                    nEggs = storage.getnEggsP();
                    soldMilk = storage.getSoldMilkP();
                    soldPig = storage.getSoldPigP();
                    soldRabbit = storage.getSoldRabbitP();
                }
            }
            case 2 -> {
                firstLevel = false;
                secondLevel = true;
                thirdLevel = false;
                driedDragonfly = storage.getDriedDragonflyP();
                soldMango = storage.getSoldMango();
                gatheredNuts = storage.getGatheredNuts();
                gatheredBananas = storage.getGatheredBananas();
                soldFeathers = storage.getSoldFeather();

            }
            case 3 -> {
                firstLevel = false;
                secondLevel = false;
                thirdLevel = true;
            }

        }        //createMenu();





        anchorPane = new AnchorPane();
        finishLevel = new FinishLevel();
      //  nextLevel();
        strikeThoughTasks();
    }

    private void addCloseButton(){
        close = new Button("×");
        close.setLayoutX(240);
        close.setLayoutY(10);
        close.setStyle("-fx-background-color: #ff5757; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-background-radius: 10px; ");
        close.setOnAction(event -> {
            main.getChildren().remove(root);
        });
    }
    private void addTaskFour(String task) {
        if (task1 && task2 && task3){
            nCoins = wallet.getCoins();
        }
        text4 = new Text(task);
        taskLabel4 = new Label(text4.getText());

        if(firstLevel){
            taskLabel4.setText("Завдання 4: «Туди сюди і мільйонер»\n" + "Заробити монети: "+nCoins +"/200");
        } else if (secondLevel) {
            taskLabel4.setText("Завдання 4: «Тоні Старк відпочиває» \n" + "Заробити:  " + nCoins + "/500 монет");
        } else if (thirdLevel) {
            taskLabel4.setText("Завдання 4: «Геній, мільярдер, плейбой, \nфілантроп» \n" + "Заробити:  " + nCoins + "/1000 монет");
        }
        taskLabel4.setLayoutX(20);
        if(firstLevel) {
            taskLabel4.setLayoutY(190);
        } else if (secondLevel) {
            taskLabel4.setLayoutY(230);
        } else if (thirdLevel) {
            taskLabel4.setLayoutY(180);
        }

        if (firstLevel)
            taskFourFirstLevel();
        if (secondLevel)
            taskFourSecondLevel();
        if (thirdLevel)
            taskFourThirdLevel();

    }

    private void strikeThoughTasks() {
        if (task1) {
            taskLabel.setText("");
            text1.setStyle(" -fx-strikethrough: true;-fx-color: blue;");
            taskLabel.setGraphic(text1);
        }
        if (task2) {
            taskLabel2.setText("");
            text2.setStyle(" -fx-strikethrough: true;-fx-color: blue;");
            taskLabel2.setGraphic(text2);
        }
        if (task3) {
            taskLabel3.setText("");
            text3.setStyle(" -fx-strikethrough: true;-fx-color: blue;");
            taskLabel3.setGraphic(text3);
        }
        if (task4) {
            taskLabel4.setText("");
            text4.setStyle(" -fx-strikethrough: true;-fx-color: blue;");
            taskLabel4.setGraphic(text4);
        }
    }
    private void taskFourFirstLevel() {
        if (nCoins >=200) {
            task4 = true;
        }
    }
    private void taskFourSecondLevel() {
        if (nCoins >= 500) {
            task4 = true;
        }
    }

    private void taskFourThirdLevel() {
        if (nCoins >= 1000) {
            task4 = true;
        }
    }
    private void addTaskThree(String task) {
        text3 = new Text(task);
        taskLabel3 = new Label(text3.getText());
        taskLabel3.setLayoutX(20);
        taskLabel3.setLayoutY(140);
        if(thirdLevel){
            taskLabel3.setLayoutY(120);
        }

        if (firstLevel)
            taskThreeFirstLevel();
        else if (secondLevel)
            taskThreeSecondLevel();
        else if (thirdLevel) {
            taskThreeThirdLevel();
        }
    }

    private void taskThreeThirdLevel() {
        if ((storage.getGatheredHorns()>=storage.getGatheredHornsP()) && (storage.getSoldMushrooms()>=storage.getSoldMushroomsP())) {
            task3 = true;
        }
    }


    private void taskThreeSecondLevel() {
        if( (storage.getSoldMango() >= storage.getSoldManagoP()) && (storage.getGatheredNuts() >= storage.getGatheredNutsP()) && (storage.getGatheredBananas() >= storage.getGatheredBananas()) ) {
            task3 = true;
        }
    }

    private void taskThreeFirstLevel() {
        if (storage!=null) {
            if (storage.getnWool() >= storage.getnWoolP()) {
                task3 = true;
            }
        }

    }
    private void addTaskTwo(String task) {
        text2 = new Text(task);
        taskLabel2 = new Label(text2.getText());
        taskLabel2.setLayoutX(20);
        taskLabel2.setLayoutY(80);
        if(thirdLevel){
            taskLabel2.setLayoutY(60);
        }

        if (firstLevel)
            taskTwoFirstLevel();
        else if (secondLevel)
            taskTwoSecondLevel();
        else if (thirdLevel) {
            taskTwoThirdLevel();
        }
    }

    private void taskTwoThirdLevel() {
        if ((storage.getSoldDust() >= storage.getSoldDustP()) && (storage.getSoldPouch()>=storage.getSoldPouchP())) {
            task2 = true;
        }
    }

    private void taskTwoSecondLevel() {
        if (storage.getSoldFeather() >= storage.getSoldFeatherP()) {
            task2 = true;
        }
    }

    private void taskTwoFirstLevel() {

        if(storage!=null) {
            if (storage.getnEggs() >= storage.getnEggsP() && storage.getSoldMilk() >= storage.getSoldMilkP()) {
                task2 = true;
            }
        }
    }
    private void addTaskOne(String task) {
        text1 = new Text(task);
        taskLabel = new Label(text1.getText());
        taskLabel.setLayoutX(20);
        taskLabel.setLayoutY(20);

        if (firstLevel)
            taskOneFirstLevel();
        else if (secondLevel)
            taskOneSecondLevel();
        else if (thirdLevel) {
            taskOneThirdLevel();
        }

    }

    private void taskOneThirdLevel() {
        if (storage.getGatheredUnicornBlood() >= storage.getGatheredUnicornBloodP()) {
            task1 = true;
        }
    }

    private void taskOneSecondLevel() {
        if (storage.getDriedDragonfly() >= storage.getDriedDragonflyP()) {
            task1 = true;
        }
    }

    private void taskOneFirstLevel() {
        if (storage!=null) {
            if (storage.getSoldRabbit() >= storage.getSoldRabbitP() && storage.getSoldPig() >= storage.getSoldPigP()){
                task1 = true;
            }
        }
    }




    public void createMenu(String task1, String task2, String task3, String task4) {
        Image image = new Image("file:src/main/resources/images/animalMenu/animalMenu.JPG");
        this.menuView = new ImageView(image);
        menuView.setFitWidth(280);
        if(firstLevel) {
            menuView.setFitHeight(250);
        } else if (secondLevel) {
            menuView.setFitHeight(300);
        } else if (thirdLevel) {
            menuView.setFitHeight(260);
        }

        Rectangle clip = new Rectangle(menuView.getFitWidth(), menuView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        menuView.setClip(clip);

        addTaskOne(task1);
        addTaskTwo(task2);
        addTaskThree(task3);
        addTaskFour(task4);
        addCloseButton();
        strikeThoughTasks();



        if(this.task4){
            nextLvlButton = new Button("Завершити рівень");
            nextLvlButton.setLayoutX(70);
            if(firstLevel) {
                nextLvlButton.setLayoutY(240);
            } else if (secondLevel) {
                nextLvlButton.setLayoutY(290);
            } else if (thirdLevel) {
                nextLvlButton.setLayoutY(250);
            }
            nextLvlButton.setCursor(Cursor.HAND);
            nextLvlButton.setStyle("-fx-background-color: #ff5757; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-background-radius: 10px; ");
            nextLvlButton.setOnAction(event -> {
                nextLvL = true;
            });
        }


        root = new Pane();
        root.setTranslateX(this.x + 880);
        root.setTranslateY(this.y + 100);
        if(!this.task4) {
            root.getChildren().addAll(menuView, taskLabel, taskLabel2, taskLabel3, taskLabel4, close);
        } else {
            root.getChildren().addAll(menuView, taskLabel, taskLabel2, taskLabel3, taskLabel4, close, nextLvlButton);
        }
    }

    public boolean isNextLvL() {
        return nextLvL;
    }

    public Pane getRoot() {
        return root;
    }
}