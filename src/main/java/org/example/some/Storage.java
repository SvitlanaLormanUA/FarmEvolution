package org.example.some;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.some.animals.AbstractAnimal;
import org.example.some.otherGameObjects.Wallet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Storage {

    public int soldHorns;
    private int product1Cost;
    private int product2Cost;
    private int product3Cost;
    private int product4Cost;
    private int product5Cost;

    private int bananaCost = 30;
    private int dragonflyPrCost = 200;
    private int featherCost = 100;
    private int mangoCost = 110;
    private int nutCost = 120;
    private ImageView storageView;
    private Wallet wallet;
    private static Pane root;

    private static int product1;
    private static int product2;
    private static int product3;
    private static int product4;
    private static int product5;

    private Image product1Image;
    private Image product2Image;
    private Image product3Image;
    private Image product4Image;
    private Image product5Image;

    private List<ImageView> product1Views;
    private List<ImageView> product2Views;
    private List<ImageView> product3Views;
    private List<ImageView> product4Views;
    private List<ImageView> product5Views;

    private Random random = new Random();


    //FIrstLevelProducts
    private static int nWool;
    private static int nEggs;
    private static int soldMilk;
    private static int soldPig;
    private static int soldRabbit;
    //FIrstLevelTasks
    private int nWoolP = 3;
    private int nEggsP = 3;
    private int soldMilkP = 3;
    private int soldPigP = 1;
    private int soldRabbitP = 1;

    //SecondLeveLProducts
    public static int driedDragonfly;
    static int soldFeather;
    static int soldMango;
    static int gatheredNuts;
    static int gatheredBananas;

    //SecondLeveLTasks
    private  int driedDragonflyP = 1;
    private  int soldFeatherP = 3;
    private  int soldMangoP = 3;
    private  int gatheredNutsP = 3;
    private  int getGatheredBananasP = 3;

    int gatheredUnicornBlood;
    int soldDust;
    int soldPouch;
    int soldMushrooms;
    private int gatheredHorns;
    private int gatheredUnicornBloodP = 1;
    private int soldDustP = 3;
    private int soldPouchP = 3;
    private int soldMushroomsP = 4;
    private int gatheredHornsP = 3;


    private boolean firstLvl;
    private boolean secondLvl;
    private boolean thirdLvl;

    /**
     * Конструктор для складу
     * @param storageView
     * @param wallet
     * @param product1View
     * @param product2View
     * @param product3View
     * @param product4View
     * @param product5View
     */
    public Storage(ImageView storageView, Wallet wallet, Image product1View, Image product2View, Image product3View,
                   Image product4View, Image product5View) {
        this.storageView = storageView;
        this.storageView.setCursor(Cursor.HAND);
        this.wallet = wallet;
        root = new Pane();
        root.getChildren().add(this.storageView);

        this.product1Image = product1View;
        this.product2Image = product2View;
        this.product3Image = product3View;
        this.product4Image = product4View;
        this.product5Image = product5View;

        product1Views = new ArrayList<>();
        product2Views = new ArrayList<>();
        product3Views = new ArrayList<>();
        product4Views = new ArrayList<>();
        product5Views = new ArrayList<>();

//        loadState();
    }

    public static void setSoldFeather(int soldFeather) {
        Storage.soldFeather = soldFeather;
    }

    /**
     * Ініціалізація змінних для 1 рівня
     */
    public void lvl1() {
        product1Cost = 5;
        product2Cost = 10;
        product3Cost = 8;
        product4Cost = 80;
        product5Cost = 40;
        firstLvl = true;
        secondLvl = false;
        thirdLvl = false;
        loadState();
    }

    /**
     * Ініціалізація змінних для 2 рівня
     */
    public void lvl2() {
        product1Cost = 100;
        product2Cost = 500;
        product3Cost = 100;
        product4Cost = 110;
        product5Cost = 120;
        firstLvl = false;
        secondLvl = true;
        thirdLvl = false;
        loadState();
    }

    /**
     * Ініціалізація змінних для 3 рівня
     */
    public void lvl3() {
        product1Cost = 580;
        product2Cost = 320;
        product3Cost = 200;
        product4Cost = 1600;
        product5Cost = 180;
        firstLvl = false;
        secondLvl = false;
        thirdLvl = true;
        loadState();
    }

    /**
     * Додавання картинки продукту при відновленні прогресу
     * @param product
     * @param quantity
     * @param viewsList
     * @param productImage
     * @param width
     * @param height
     */
    private void addProductsToList(int product, int quantity, List<ImageView> viewsList, Image productImage, int width,  int height) {
        double startX = storageView.getLayoutX();
        double startY = storageView.getLayoutY();
        double x = 0, y = 0;
        if(quantity>5){
            quantity = 5;
        }

        boolean under;

        for (int i = 0; i < quantity; i++) {
            under = true;
            switch(product) {
                case 1 -> {
                    if(firstLvl) {
                        x = startX + i * 13;
                        y = startY + 52; // Висота, на якій розташовані продукти
                        if (i == product1) {
                            if (product1 != 1) {
                                for (int j = 2; j <= product1; j++) {
                                    x += 13;
                                }
                            }
                        }
                    } else if (secondLvl) {
                        width = 55;
                        height = 55;
                        x = random.nextInt(880, 1000);
                        y = random.nextInt(540, 560);
                    } else if (thirdLvl) {
                        width = 35;
                        height = 35;
                        x = storageView.getLayoutX()-100;
                        y = storageView.getLayoutY() + 150;
                        if (i < 3) {
                            for (int j = 1; j <= i; j++) {
                                x += 25;
                            }
                            if(i!=1){
                                y-=15;
                            }
                        } else {
                            if(i==3) {
                                x+=25;
                            }
                            if(i==4){
                                x+=30;
                                y-=15;
                            }
                            y-=25;
                        }
                    }
                }

                case 2->{
                    if(firstLvl) {
                        x = storageView.getLayoutX() + 1;
                        y = storageView.getLayoutY() + 22;
                        if (i == 1 || i == 2) {
                            for (int j = 1; j <= i; j++) {
                                x += 24;
                            }
                        } else if (i == 3) {
                            x = storageView.getLayoutX() + 34;
                            y = storageView.getLayoutY() + 10;
                        } else if (i == 4) {
                            x = storageView.getLayoutX() + 15;
                            y = storageView.getLayoutY() + 10;
                        }
                    } else if (secondLvl) {
                        x = random.nextInt(880, 1000);
                        y = random.nextInt(540, 560);
                    } else if (thirdLvl) {
                        under = false;
                        width = 40;
                        height = 40;
                        x = storageView.getLayoutX() + 20;
                        y = storageView.getLayoutY() + 55;
                        if (i > 0 && i < 3) {
                            for (int j = 1; j <= i; j++) {
                                x += 25;
                                y += 5;
                            }
                        } else if(i>=3){
                            x = storageView.getLayoutX() + 20;
                            y = storageView.getLayoutY() + 70;
                            for (int j = 3; j <= i; j++) {
                                x += 25;
                                y += 5;
                            }
                        }
                    }
                }

                case 3 -> {
                    if (firstLvl) {
                        x = storageView.getLayoutX() + i * 16;
                        y = storageView.getLayoutY() - 39;

                        for (int j = 1; j <= 5; j++) {
                            if (i == product3) {
                                if (product3 != 1) {
                                    for (int l = 2; l <= product3; l++) {
                                        x += 16;
                                    }
                                }
                            }
                        }
                    } else if (secondLvl) {
                        width = 20;
                        height = 60;
                        x = random.nextInt(880, 1000);
                        y = random.nextInt(540, 560);
                    } else if (thirdLvl) {
                        under = false;
                        x = storageView.getLayoutX() + 60;
                        y = storageView.getLayoutY() - 10;
                        width = 40;
                        height = 25;
                        if (i>0) {
                            for (int j = 1; j <= i; j++) {
                                x += 20;
                                y += 5;
                            }
                        }
                    }
                }

                case 4 -> {
                    if (firstLvl) {
                        x = storageView.getLayoutX() + 2 + i * 22;
                        y = storageView.getLayoutY() + 97;
                        if (i == 3) {
                            x = storageView.getLayoutX() + 12;
                            y = storageView.getLayoutY() + 87;
                        } else if (i == 4) {
                            x = storageView.getLayoutX() + 32;
                            y = storageView.getLayoutY() + 87;
                        }
                    } else if (secondLvl) {
                        x = random.nextInt(880, 1000);
                        y = random.nextInt(550, 570);
                    } else if (thirdLvl) {
                        x = storageView.getLayoutX()+35;
                        y = storageView.getLayoutY()+63;
                        width = 30;
                        height = 40;
                        if (i > 0 && i<4) {
                            for (int j = 1; j <= i; j++) {
                                x += 20;
                                y += 6;
                            }
                        }
                    }
                }

                case 5 -> {
                    if (firstLvl) {
                        x = storageView.getLayoutX() + 2 + i * 13;
                        y = storageView.getLayoutY() + 127;
                        if (i == product5) {
                            if (product5 != 1) {
                                for (int j = 2; j <= product5; j++) {
                                    x += 13;
                                }
                            }
                        }
                    } else if (secondLvl) {
                        width = 20;
                        height = 15;
                        x = random.nextInt(880, 1000);
                        y = random.nextInt(550, 570);
                    } else if (thirdLvl) {
                        x = storageView.getLayoutX() + 20;
                        y = storageView.getLayoutY() + 72;
                        width = 25;
                        height = 25;
                        if (i != 0) {
                            for (int j = 1; j <= i; j++) {
                                x += 25;
                                if(j%2!=0){
                                    y-=10;
                                }else {
                                    y+=20;
                                    x-=10;
                                }
                            }
                        }

                    }

                }
                default -> {
                    return;
                }
            }
            if (i < 5) {
                ImageView productView = createProduct(productImage, x, y, width, height);
                viewsList.add(productView);
                if (under){
                    Platform.runLater(() -> root.getChildren().add(1, productView));
                } else {
                    Platform.runLater(() -> root.getChildren().add(productView));
                }
            }

        }
    }

    /**
     * Додавання яйця до складу
     */
    public void addEgg() {

        product1++;
        nEggs++;

        if (product1 <= 5) {
            double x = storageView.getLayoutX();
            double y = storageView.getLayoutY() + 52;
            int width = 25;
            for (int i = 1; i <= 5; i++) {
                if (i == product1) {
                    if (product1 != 1) {
                        for (int j = 2; j <= product1; j++) {
                            x += 13;
                        }
                    }
                    ImageView productView = createProduct(product1Image, x, y, width, width);
                    product1Views.add(productView);
                    int num = product1 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product1Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання хутра до складу
     */
    public void addWool() {
        product2++;
        nWool++;
        if (product2 <= 5) {
            double x = storageView.getLayoutX() + 1;
            double y = storageView.getLayoutY() + 22;
            int width = 30;
            for (int i = 1; i <= 5; i++) {
                if (i == product2) {
                    if (product2 == 2 || product2 == 3) {
                        for (int j = 2; j <= product2; j++) {
                            x += 24;
                        }
                    } else if (product2 == 4) {
                        x = storageView.getLayoutX() + 15;
                        y = storageView.getLayoutY() + 10;
                    } else if (product2 == 5) {
                        x = storageView.getLayoutX() + 34;
                        y = storageView.getLayoutY() + 10;
                    }
                    ImageView productView = createProduct(product2Image, x, y, width, width);
                    product2Views.add(productView);
                    int num = product2 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product2Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання молока до складу
     */
    public void addMilk() {

        product3++;
        if (product3 <= 5) {
            double x = storageView.getLayoutX() + 1;
            double y = storageView.getLayoutY() - 39;
            int width = 15;
            int height = 40;
            for (int i = 1; i <= 5; i++) {
                if (i == product3) {
                    if (product3 != 1) {
                        for (int j = 2; j <= product3; j++) {
                            x += 16;
                        }
                    }
                    ImageView productView = createProduct(product3Image, x, y, width, height);
                    product3Views.add(productView);
                    int num = product3 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product3Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання м'яса свинки до складу
     */
    public void addPigMeat() {
        product4++;

        if (product4 <= 5) {
            double x = storageView.getLayoutX() + 2;
            double y = storageView.getLayoutY() + 97;
            int width = 30;
            for (int i = 1; i <= 5; i++) {
                if (i == product4) {
                    if (product4 == 2 || product4 == 3) {
                        for (int j = 2; j <= product4; j++) {
                            x += 22;
                        }
                    } else if (product4 == 4) {
                        x += 10;
                        y = storageView.getLayoutY() + 87;
                    } else if (product4 == 5) {
                        x += 30;
                        y = storageView.getLayoutY() + 87;
                    }
                    ImageView productView = createProduct(product4Image, x, y, width, width);
                    product4Views.add(productView);
                    int num = product4 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product4Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання м'яса кролика до складу
     */
    public void addRabbitMeat() {

        product5++;

        if (product5 <= 5) {
            double x = storageView.getLayoutX() + 2;
            double y = storageView.getLayoutY() + 127;
            int width = 20;
            for (int i = 1; i <= 5; i++) {
                if (i == product5) {
                    if (product5 != 1) {
                        for (int j = 2; j <= product5; j++) {
                            x += 13;
                        }
                    }
                    ImageView productView = createProduct(product5Image, x, y, width, width);
                    product5Views.add(productView);
                    int num = product5 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product5Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання банану до складу
     */
    public void addBanana() {
        product1++;
        gatheredBananas++;
        saveState();
    }

    /**
     * Додавання засушеної бабки до складу
     */
    public void addDragonflyPr() {
        product2++;
        if (product2 <= 5) {
            double x;
            double y;
            int width = 50;
            int height = 50;
            for (int i = 1; i <= 5; i++) {
                if (i == product2) {
                    x = random.nextInt(880, 1000);
                    y = random.nextInt(540, 560);
                    ImageView productView = createProduct(product2Image, x, y, width, height);
                    product2Views.add(productView);
                    int num = product2 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product2Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання пера павича до складу
     */
    public void addFeather(){
        product3++;

        if (product3 <= 7) {
            double x;
            double y;
            int width = 20;
            int height = 60;
            for (int i = 1; i <= 7; i++) {
                if (i == product3) {
                    x = random.nextInt(880, 1000);
                    y = random.nextInt(540, 560);
                    ImageView productView = createProduct(product3Image, x, y, width, height);
                    product3Views.add(productView);
                    int num = product3 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product3Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання манго до складу
     */
    public void addMango() {
        product4++;

        if (product4 <= 7) {
            double x;
            double y;
            int width = 30;
            int height = 30;
            for (int i = 1; i <= 7; i++) {
                if (i == product4) {
                    x = random.nextInt(880, 1000);
                    y = random.nextInt(550, 570);
                    ImageView productView = createProduct(product4Image, x, y, width, height);
                    product4Views.add(productView);
                    int num = product4 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product4Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання горіхів до складу
     */
    public void addNut() {
        product5++;
        gatheredNuts++;
        if (product5 <= 7) {
            double x;
            double y;
            int width = 20;
            int height = 15;
            for (int i = 1; i <= 7; i++) {
                if (i == product5) {
                    x = random.nextInt(880, 1000);
                    y = random.nextInt(550, 570);
                    ImageView productView = createProduct(product5Image, x, y, width, height);
                    product5Views.add(productView);
                    int num = product5 - 1;
                    if( product5Views.get(num)!=null) {
                        Platform.runLater(() -> root.getChildren().add(1, product5Views.get(num)));
                    }
                    //  Platform.runLater(() -> root.getChildren().add(1, product5Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання мішечку до складу
     */
    public void addPouch() {

        product1++;

        if (product1 <= 5) {
            double x = storageView.getLayoutX()-100;
            double y = storageView.getLayoutY() + 150;
            int width = 35;
            int height = 35;
            for (int i = 1; i <= 5; i++) {
                if (i == product1) {
                    if (product1 < 4) {
                        for (int j = 2; j <= product1; j++) {
                            x += 25;
                        }
                        if(product1!=2){
                            y-=15;
                        }
                    } else {
                        if(product1==4) {
                            x+=25;
                        }
                        if(product1==5){
                            x+=30;
                            y-=15;
                        }
                        y-=25;
                    }
                    ImageView productView = createProduct(product1Image, x, y, width, height);
                    product1Views.add(productView);
                    int num = product1 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product1Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання пилу феї до складу
     */
    public void addFairyDust() {
        product2++;
        if (product2 <= 5) {
            double x = storageView.getLayoutX() + 20;
            double y = storageView.getLayoutY() + 55;
            int width = 40;
            for (int i = 1; i <= 5; i++) {
                if (i == product2) {
                    if (product2 > 1 && product2 < 4) {
                        for (int j = 2; j <= product2; j++) {
                            x += 25;
                            y += 5;
                        }
                    } else if(product2>=4){
                        x = storageView.getLayoutX() + 20;
                        y = storageView.getLayoutY() + 70;
                        for (int j = 4; j <= product2; j++) {
                            x += 25;
                            y += 5;
                        }
                    }
                    ImageView productView = createProduct(product2Image, x, y, width, width);
                    product2Views.add(productView);
                    int num = product2 - 1;
                    Platform.runLater(() -> root.getChildren().add(product2Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання рогу до складу
     */
    public void addHorn() {
        product3++;
        gatheredHorns++;
        if (product3 <= 5) {
            double x = storageView.getLayoutX() + 60;
            double y = storageView.getLayoutY() - 10;
            int width = 40;
            int height = 25;
            for (int i = 1; i <= 5; i++) {
                if (i == product3) {
                    if (product3>1) {
                        for (int j = 2; j <= product3; j++) {
                            x += 20;
                            y += 5;
                        }
                    }
                    ImageView productView = createProduct(product3Image, x, y, width, height);
                    product3Views.add(productView);
                    int num = product3 - 1;
                    Platform.runLater(() -> root.getChildren().add(product3Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання крові єдинорогу до складу
     */
    public void addUnicornBlood() {
        product4++;
        gatheredUnicornBlood++;
        if (product4 <= 4) {
            double x = storageView.getLayoutX()+35;
            double y = storageView.getLayoutY()+63;
            int width = 30;
            int height = 40;
            for (int i = 1; i <= 4; i++) {
                if (i == product4) {
                    if (product4 > 1) {
                        for (int j = 2; j <= product4; j++) {
                            x += 20;
                            y += 6;
                        }
                    }

                    ImageView productView = createProduct(product4Image, x, y, width, height);
                    product4Views.add(productView);
                    int num = product4 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product4Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Додавання гриба до складу
     */
    public void addMushroom() {
        product5++;

        if (product5 <= 5) {
            double x = storageView.getLayoutX() + 20;
            double y = storageView.getLayoutY() + 72;
            int width = 25;
            for (int i = 1; i <= 5; i++) {
                if (i == product5) {
                    if (product5 != 1) {
                        for (int j = 2; j <= product5; j++) {
                            x += 25;
                            if(j%2==0){
                                y-=10;
                            }else {
                                y+=20;
                                x-=10;
                            }
                        }
                    }
                    ImageView productView = createProduct(product5Image, x, y, width, width);
                    product5Views.add(productView);
                    int num = product5 - 1;
                    Platform.runLater(() -> root.getChildren().add(1, product5Views.get(num)));
                }
            }
        }
        saveState();
    }

    /**
     * Створення зображення продукту
     * @param productView
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    private ImageView createProduct(Image productView, double x, double y, int width, int height) {
        ImageView product = new ImageView(productView);
        product.setFitWidth(width);
        product.setFitHeight(height);
        product.setX(x);
        product.setY(y);
        return product;
    }

    /**
     * Збереження стану для відновлення прогресу
     */
    public void saveState() {
        try (FileOutputStream fos = new FileOutputStream("stateStorage.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeInt(product1);
            oos.writeInt(product2);
            oos.writeInt(product3);
            oos.writeInt(product4);
            oos.writeInt(product5);
            oos.writeInt(soldMilk);
            oos.writeInt(soldPig);
            oos.writeInt(soldRabbit);
            oos.writeInt(nWool);
            oos.writeInt(nEggs);

            oos.writeInt(driedDragonfly);
            oos.writeInt(soldFeather);
            oos.writeInt(soldMango);
            oos.writeInt(gatheredNuts);
            oos.writeInt(gatheredBananas);

            oos.writeInt(gatheredUnicornBlood);
            oos.writeInt(soldDust);
            oos.writeInt(soldPouch);
            oos.writeInt(soldMushrooms);
            oos.writeInt(gatheredHorns);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для завантаження збереженого прогресу
     */
    public void loadState() {
        try (FileInputStream fis = new FileInputStream("stateStorage.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            product1 = ois.readInt();
            product2 = ois.readInt();
            product3 = ois.readInt();
            product4 = ois.readInt();
            product5 = ois.readInt();
            soldMilk = ois.readInt();
            soldPig = ois.readInt();
            soldRabbit = ois.readInt();
            nWool = ois.readInt();
            nEggs = ois.readInt();

            driedDragonfly = ois.readInt();
            soldFeather = ois.readInt();
            soldMango = ois.readInt();
            gatheredNuts = ois.readInt();
            gatheredBananas = ois.readInt();

            gatheredUnicornBlood = ois.readInt();
            soldDust = ois.readInt();
            soldPouch = ois.readInt();
            soldMushrooms = ois.readInt();
            gatheredHorns = ois.readInt();


            // Додавання продуктів до списків
            addProductsToList(1, product1, product1Views, product1Image, 25, 25); // Приклад для першого продукту
            addProductsToList(2, product2, product2Views, product2Image, 30, 30); // Приклад для другого продукту
            addProductsToList(3, product3, product3Views, product3Image, 15, 40); // Приклад для третього продукту
            addProductsToList(4, product4, product4Views, product4Image, 30,  30);
            addProductsToList(5, product5, product5Views, product5Image, 20, 20);

        } catch (IOException e) {
            product1 = 0;
            product2 = 0;
            product3 = 0;
            product4 = 0;
            product5 = 0;
            soldMilk = 0;
            soldPig = 0;
            soldRabbit = 0;
            nWool = 0;
            nEggs = 0;

        }
    }

    /**
     * Метод для продажу 1 продукту
     * @param toSell
     */
    public void sellProduct1(int toSell) {
        int t = product1;
        product1 -= toSell;
        wallet.income(product1Cost * toSell);
        removeProductView(product1Views, t, toSell);
        soldPouch+=toSell;
        saveState();
    }

    /**
     * Метод для продажу 2 продукту
     * @param toSell
     */
    public void sellProduct2(int toSell) {
        int t = product2;
        product2 -= toSell;
        wallet.income(product2Cost * toSell);
        removeProductView(product2Views, t, toSell);
        soldDust+=toSell;
        saveState();
    }

    /**
     * Метод для продажу 3 продукту
     * @param toSell
     */
    public void sellProduct3(int toSell) {
        int t = product3;
        product3 -= toSell;
        wallet.income(product3Cost * toSell);
        removeProductView(product3Views, t, toSell);
        soldMilk+=toSell;
        soldFeather+=toSell;
        saveState();
    }

    /**
     * Метод для продажу 4 продукту
     * @param toSell
     */
    public void sellProduct4(int toSell) {
        int t = product4;
        product4 -= toSell;
        wallet.income(product4Cost * toSell);
        removeProductView(product4Views, t, toSell);
        soldPig+=toSell;
        soldMango+=toSell;
        saveState();
    }

    /**
     * Метод для продажу 5 продукту
     * @param toSell
     */
    public void sellProduct5(int toSell) {
        int t = product5;
        product5 -= toSell;
        wallet.income(product5Cost * toSell);
        removeProductView(product5Views, t, toSell);
        soldMushrooms+=toSell;
        soldRabbit+=toSell;
        saveState();
    }

    /**
     * Метод для видалення зображення продукту
     * @param productViews
     * @param product
     * @param toRemove
     */
    private void removeProductView(List<ImageView> productViews, int product, int toRemove) {
        int start = product - 1;
        int end = product - toRemove;
        if (end < 0) end = 0;

        for (int i = start; i >= end; i--) {
            if (i < 5) {
                if (!productViews.isEmpty()) {
                    ImageView view = productViews.get(i);
                    Platform.runLater(() -> root.getChildren().remove(view));
                    productViews.remove(i);
                    saveState();
                }
            }
        }
        saveState();
    }

    /**
     * Метод для продажу бананів
     * @param toSell
     */
    public void sellBananas(int toSell){
        int t = product1;
        product1 -= toSell;
        wallet.income(product1Cost *toSell);

        int start = t - 1;
        int end = t - toSell;
        if (end < 0) end = 0;

        for (int i = start; i >= end; i--) {
            ImageView view = SecondLevel.bananas.get(i).getProductView();
            SecondLevel.bananas.remove(i);
            Platform.runLater(() -> AbstractAnimal.root.getChildren().remove(view));
        }
    }

    /**
     * Отримання ціни 4 продукту
     * @return
     */
    public int getProduct4Cost() {
        return product4Cost;
    }

    /**
     * Отримання ціни 5 продукту
     * @return
     */
    public int getProduct5Cost() {
        return product5Cost;
    }

    public int getBananaCost() {
        return bananaCost;
    }

    public int getDragonflyPrCost() {
        return dragonflyPrCost;
    }

    public int getFeatherCost() {
        return featherCost;
    }

    public int getMangoCost() {
        return mangoCost;
    }

    public int getNutCost() {
        return nutCost;
    }

    /**
     * Отримання кількості 1 продукту
     * @return
     */
    public int getProduct1() {
        return product1;
    }

    /**
     * Отримання кількості 2 продукту
     * @return
     */
    public int getProduct2() {
        return product2;
    }

    /**
     * Отримання кількості 3 продукту
     * @return
     */
    public int getProduct3() {
        return product3;
    }

    /**
     * Отримання кількості 4 продукту
     * @return
     */
    public int getProduct4() {
        return product4;
    }

    /**
     * Отримання кількості 5 продукту
     * @return
     */
    public int getProduct5() {
        return product5;
    }

    /**
     * Отримання ціни 2 продукту
     * @return
     */
    public int getProduct2Cost() {
        return product2Cost;
    }

    /**
     * Отримання ціни 1 продукту
     * @return
     */
    public int getProduct1Cost() {
        return product1Cost;
    }


    /**
     * Отримання node
     * @return
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Отримання кількості зібраного хутра
     * @return
     */
    public int getnWool() {
        if(nWool>nWoolP){
            nWool = nWoolP;
        }
        return nWool;
    }

    /**
     * Отримання кількості зібраних яєць
     * @return
     */
    public int getnEggs() {
        if(nEggs>nEggsP){
            nEggs = nEggsP;
        }
        return nEggs;
    }

    /**
     * Отримання кількості проданого молока
     * @return
     */
    public int getSoldMilk() {
        if(soldMilk>soldMilkP){
            soldMilk = soldMilkP;
        }
        return soldMilk;
    }

    /**
     * Отримання кількості проданої свинини
     * @return
     */
    public int getSoldPig() {
        if(soldPig>soldPigP){
            soldPig = soldPigP;
        }
        return soldPig;
    }

    /**
     * Отримання кількості проданої кролятини
     * @return
     */
    public int getSoldRabbit() {
        if (soldRabbit>soldRabbitP){
            soldRabbit = soldRabbitP;
        }
        return soldRabbit;
    }

    /**
     * Отримання кількості яєць, що треба зібрати
     * @return
     */
    public int getnEggsP() {
        return nEggsP;
    }

    /**
     * Отримання кількості хутра, що треба зібрати
     * @return
     */
    public int getnWoolP() {
        return nWoolP;
    }

    /**
     * Отримання кількості молока, що треба продати
     * @return
     */
    public int getSoldMilkP() {
        return soldMilkP;
    }

    /**
     * Отримання кількості свинини, що треба продати
     * @return
     */
    public int getSoldPigP() {
        return soldPigP;
    }

    /**
     * Отримання кількості кролятини, що треба продати
     * @return
     */
    public int getSoldRabbitP() {
        return soldRabbitP;
    }

    /**
     * Отримання вартості 3 продукту
     * @return
     */
    public int getProduct3Cost() {
        return product3Cost;
    }

    /**
     * Отримання кількості засушеної бабки
     * @return
     */
    public  int getDriedDragonfly() {
        if (driedDragonfly >= driedDragonflyP){
            driedDragonfly = driedDragonflyP;
        }
        return driedDragonfly;
    }

    /**
     * Отримання кількості засушеної бабки, що треба зібрати
     * @return
     */
    public int getDriedDragonflyP() {
        return driedDragonflyP;
    }

    /**
     * Отримання кількості пір'я павича, що треба продати
     * @return
     */
    public int getSoldFeatherP() {
        return soldFeatherP;
    }

    /**
     * Отримання кількості проданих пір'їв
     * @return
     */
    public int getSoldFeather() {
        if (soldFeather >= soldFeatherP){
            soldFeather= soldFeatherP;
        }
        return soldFeather;

    }

    /**
     * Отримання кількості проданого манго
     * @return
     */
    public  int getSoldMango() {
        if (soldMango >= soldMangoP){
            soldMango= soldMangoP;
        }
        return soldMango;
    }

    /**
     * Отримання кількості зібраних горіхів
     * @return
     */
    public  int getGatheredNuts() {
        if (gatheredNuts >= gatheredNutsP){
            gatheredNuts= gatheredNutsP;
        }
        return gatheredNuts;
    }

    /**
     * Отримання кількості зібраних горіхів
     * @return
     */
    public  int getGatheredBananas() {
        if (gatheredBananas >= getGatheredBananasP){
            gatheredBananas = getGatheredBananasP;
        }

        return gatheredBananas;
    }

    /**
     * Отримання кількості манго, що треба продати
     * @return
     */
    public int getSoldManagoP() {
        return soldMangoP;
    }

    /**
     * Отримання кількості горіхів, що треба зібрати
     * @return
     */
    public   int getGatheredNutsP() {
        return gatheredNutsP;
    }

    /**
     * Отримання кількості бананів, що треба зібрати
     * @return
     */
    public   int getGatheredBananasP() {
        return getGatheredBananasP;
    }

    /**
     * Отримання кількості проданих мішечків
     * @return
     */
    public int getSoldPouch() {
        if(soldPouch>=soldPouchP){
            soldPouch = soldPouchP;
        }
        return soldPouch;
    }

    /**
     * Отримання кількості проданого пилу феї
     * @return
     */
    public int getSoldDust() {
        if(soldDust>=soldDustP){
            soldDust=soldDustP;
        }
        return soldDust;
    }

    /**
     * Отримання кількості зібраних рогів
     * @return
     */
    public int getGatheredHorns() {
        if(gatheredHorns>=gatheredHornsP){
            gatheredHorns=gatheredHornsP;
        }
        return gatheredHorns;
    }

    /**
     * Отримання кількості зібраної крові єдинорога
     * @return
     */
    public int getGatheredUnicornBlood() {
        if(gatheredUnicornBlood>=gatheredUnicornBloodP){
            gatheredUnicornBlood = gatheredUnicornBloodP;
        }
        return gatheredUnicornBlood;
    }

    /**
     * Отримання кількості проданих грибів
     * @return
     */
    public int getSoldMushrooms() {
        if(soldMushrooms>=soldMushroomsP){
            soldMushrooms = soldMushroomsP;
        }
        return soldMushrooms;
    }

    /**
     * Отримання кількості мішечків, що треба продати
     * @return
     */
    public int getSoldPouchP() {
        return soldPouchP;
    }

    /**
     * Отримання кількості пилу феї, що треба продати
     * @return
     */
    public int getSoldDustP() {
        return soldDustP;
    }

    /**
     * Отримання кількості рогів, що треба зібрати
     * @return
     */
    public int getGatheredHornsP() {
        return gatheredHornsP;
    }

    /**
     * Отримання кількості крові єдинорога, що треба зібрати
     * @return
     */
    public int getGatheredUnicornBloodP() {
        return gatheredUnicornBloodP;
    }

    /**
     * Отримання кількості грбів, що треба продати
     * @return
     */
    public int getSoldMushroomsP() {
        return soldMushroomsP;
    }

    /**
     * Оновлення змінних
     */
    public  void reset() {
        removeProductView(product1Views, product1, product1);
        removeProductView(product2Views, product2, product2);
        removeProductView(product3Views, product3, product3);
        removeProductView(product4Views, product4, product4);
        removeProductView(product5Views, product5, product5);

        product1 = 0;
        product2 = 0;
        product3 = 0;
        product4 = 0;
        product5 = 0;
        soldMilk = 0;
        soldPig = 0;
        soldRabbit = 0;
        nWool = 0;
        nEggs = 0;
        gatheredBananas = 0;
        soldMango = 0;
        gatheredNuts = 0;
        soldFeather = 0;
        driedDragonfly = 0;
        gatheredUnicornBlood = 0;
        soldPouch = 0;
        soldMushrooms = 0;
        soldDust = 0;
        gatheredHorns = 0;


        saveState();
    }


   
}