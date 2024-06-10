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

    private static int nWool;
    private static int nEggs;
    private static int soldMilk;
    private static int soldPig;
    private static int soldRabbit;

    private int nWoolP = 20;
    private int nEggsP = 30;
    private int soldMilkP = 15;
    private int soldPigP = 10;
    private int soldRabbitP = 15;

    private boolean firstLvl;
    private boolean secondLvl;
    private boolean thirdLvl;

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

    public void lvl3() {
        product1Cost = 0;
        product2Cost = 0;
        product3Cost = 0;
        product4Cost = 0;
        product5Cost = 0;
        firstLvl = false;
        secondLvl = false;
        thirdLvl = true;
        loadState();
    }
    private void addProductsToList(int product, int quantity, List<ImageView> viewsList, Image productImage, int width,  int height) {
        double startX = storageView.getLayoutX();
        double startY = storageView.getLayoutY();
        double x = 0, y = 0;

        for (int i = 0; i < quantity; i++) {
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

                    }
                }

                case 2->{
                    if(firstLvl) {
                        x = storageView.getLayoutX() + i * 12;
                        y = storageView.getLayoutY() + 22;
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
                        }
                    } else if (secondLvl) {
                        x = random.nextInt(880, 1000);
                        y = random.nextInt(540, 560);
                    } else if (thirdLvl) {

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

                    }
                }

                case 4 -> {
                    if (firstLvl) {
                        x = storageView.getLayoutX() + 2 + i * 22;
                        y = storageView.getLayoutY() + 97;
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
                        }
                    } else if (secondLvl) {
                        x = random.nextInt(880, 1000);
                        y = random.nextInt(550, 570);
                    } else if (thirdLvl) {

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

                    } else if (thirdLvl) {

                    }
                }
                default -> {
                    return;
                }
            }
            if (i < 5) {
                ImageView productView = createProduct(productImage, x, y, width, height);
                viewsList.add(productView);
                Platform.runLater(() -> root.getChildren().add(1, productView));
            }

        }
    }

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

    public void addBanana() {
        product1++;
        saveState();
    }

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

    public void addNut() {
        product5++;
        saveState();
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
    }

    private ImageView createProduct(Image productView, double x, double y, int width, int height) {
        ImageView product = new ImageView(productView);
        product.setFitWidth(width);
        product.setFitHeight(height);
        product.setX(x);
        product.setY(y);
        return product;
    }

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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



    public void sellProduct1(int toSell) {
        int t = product1;
        product1 -= toSell;
        wallet.income(product1Cost * toSell);
        removeProductView(product1Views, t, toSell);
        saveState();
    }

    public void sellProduct2(int toSell) {
        int t = product2;
        product2 -= toSell;
        wallet.income(product2Cost * toSell);
        removeProductView(product2Views, t, toSell);
        saveState();
    }

    public void sellProduct3(int toSell) {
        int t = product3;
        product3 -= toSell;
        wallet.income(product3Cost * toSell);
        removeProductView(product3Views, t, toSell);
        soldMilk+=toSell;
        saveState();
    }

    public void sellProduct4(int toSell) {
        int t = product4;
        product4 -= toSell;
        wallet.income(product4Cost * toSell);
        removeProductView(product4Views, t, toSell);
        soldPig+=toSell;
        saveState();
    }

    public void sellProduct5(int toSell) {
        int t = product5;
        product5 -= toSell;
        wallet.income(product5Cost * toSell);
        removeProductView(product5Views, t, toSell);
        soldRabbit+=toSell;
        saveState();
    }

    private void removeProductView(List<ImageView> productViews, int product, int toRemove) {
        int start = product - 1;
        int end = product - toRemove;
        if (end < 0) end = 0;

        for (int i = start; i >= end; i--) {
            if (i < 5) {
                ImageView view = productViews.get(i);
                Platform.runLater(() -> root.getChildren().remove(view));
                productViews.remove(i);
                saveState();
            }
        }
        saveState();
    }

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

    public int getProduct4Cost() {
        return product4Cost;
    }

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
    public int getProduct1() {
        return product1;
    }
    public int getProduct2() {
        return product2;
    }
    public int getProduct3() {
        return product3;
    }
    public int getProduct4() {
        return product4;
    }

    public int getProduct5() {
        return product5;
    }


    public int getProduct2Cost() {
        return product2Cost;
    }

    public int getProduct1Cost() {
        return product1Cost;
    }


    public Node getRoot() {
        return root;
    }

    public int getnWool() {
        if(nWool>nWoolP){
            nWool = nWoolP;
        }
        return nWool;
    }

    public int getnEggs() {
        if(nEggs>nEggsP){
            nEggs = nEggsP;
        }
        return nEggs;
    }

    public int getSoldMilk() {
        if(soldMilk>soldMilkP){
            soldMilk = soldMilkP;
        }
        return soldMilk;
    }

    public int getSoldPig() {
        if(soldPig>soldPigP){
            soldPig = soldPigP;
        }
        return soldPig;
    }

    public int getSoldRabbit() {
        if (soldRabbit>soldRabbitP){
            soldRabbit = soldRabbitP;
        }
        return soldRabbit;
    }

    public int getnEggsP() {
        return nEggsP;
    }

    public int getnWoolP() {
        return nWoolP;
    }

    public int getSoldMilkP() {
        return soldMilkP;
    }

    public int getSoldPigP() {
        return soldPigP;
    }

    public int getSoldRabbitP() {
        return soldRabbitP;
    }

    public int getProduct3Cost() {
        return product3Cost;
    }


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
        saveState();
    }
}
