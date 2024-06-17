package org.example.some.animals;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import org.example.some.FirstLevel;
import org.example.some.SecondLevel;
import org.example.some.Storage;
import org.example.some.ThirdLevel;
import org.example.some.otherGameObjects.Well;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractAnimal implements Animal {

    public static Pane root;



    public int cost;
    protected int hungerLvl;
    protected int thirstLvl;
    protected static Feeder feeder;
    protected Storage storage;
    protected TranslateTransition translateTransition;
    protected PathTransition pathTransition;
    protected boolean directionRight;
    protected String imagePath;
    protected String imagePathLeft;
    protected double deltaX;
    protected double deltaY;

    protected Bounds bounds;

    int worldStartX;
    int worldStartY;
    int worldEndX;
    int worldEndY;
    protected Random random = new Random();
    protected boolean openedMenu;
    private Well well;
    private MediaView mediaView;
    private File file;

    ImageView animalView;
    Media media;
    public static MediaPlayer mediaPlayer;
    Image product;

    AnimalMenu animalMenu;

    /**
     * @param worldStartX
     * @param worldStartY
     * @param worldEndX
     * @param worldEndY
     * @param root
     * @param well
     * @param feeder
     * @param storage
     * @param imagePath
     * @param soundFile
     * @param recourseFile
     * Конструктор, що ініціалізує тварину з певними параметрами
     */
    public AbstractAnimal(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage, String imagePath, String soundFile, String recourseFile) {
        file = new File(soundFile);
        product = new Image(recourseFile);
        this.imagePath = imagePath;


        this.animalView = new ImageView(new Image(this.imagePath));
        this.worldStartX = worldStartX;
        this.worldStartY = worldStartY;
        this.worldEndX = worldEndX;
        this.worldEndY = worldEndY;
        this.root = root;
        this.openedMenu = false;
        this.thirstLvl = 100;
        this.hungerLvl = 100;
        this.cost = 100;
        this.well = well;
        this.deltaX = 0;
        this.deltaY = 0;


        this.feeder = feeder;
        this.storage = storage;
        this.mediaView = new MediaView();
        animalView.setFitWidth(100);
        animalView.setFitHeight(100);
        animalView.setCursor(Cursor.HAND);


        int x = random.nextInt(worldStartX+50, worldEndX-50);
        int y = random.nextInt(worldStartY+50, worldEndY - 100);
        animalView.setLayoutX(x);
        animalView.setLayoutY(y);



        thirst();
        hunger();
        movement();
    }

    /**
     * @param worldStartX
     * @param worldStartY
     * @param worldEndX
     * @param worldEndY
     * @param root
     * @param well
     * @param feeder
     * @param storage
     * @param imagePath
     * @param imagePathLeft
     * @param soundFile
     * @param recourseFile
     * Перевантажені конструктори для різних наборів параметрів
     */
    public AbstractAnimal(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, Storage storage, String imagePath, String imagePathLeft, String soundFile, String recourseFile) {
        file = new File(soundFile);
        product = new Image(recourseFile);
        this.imagePath = imagePath;
        this.imagePathLeft = imagePathLeft;
        this.animalView = new ImageView(new Image(this.imagePath));
        this.worldStartX = worldStartX;
        this.worldStartY = worldStartY;
        this.worldEndX = worldEndX;
        this.worldEndY = worldEndY;
        this.root = root;
        this.openedMenu = false;
        this.thirstLvl = 100;
        this.hungerLvl = 100;
        this.cost = 100;
        this.well = well;
        this.deltaX = 0;
        this.deltaY = 0;


        this.feeder = feeder;
        this.storage = storage;
        this.mediaView = new MediaView();
        animalView.setFitWidth(100);
        animalView.setFitHeight(100);
        animalView.setCursor(Cursor.HAND);


        int x = random.nextInt(worldStartX+50, worldEndX-50);
        int y = random.nextInt(worldStartY+50, worldEndY - 100);
        animalView.setLayoutX(x);
        animalView.setLayoutY(y);



        thirst();
        hunger();
        movement();
    }

    /**
     * @param worldStartX
     * @param worldStartY
     * @param worldEndX
     * @param worldEndY
     * @param root
     * @param well
     * @param feeder
     * @param imagePath
     * @param imagePathLeft
     * @param soundFile
     */
    public AbstractAnimal(int worldStartX, int worldStartY, int worldEndX, int worldEndY, Pane root, Well well, Feeder feeder, String imagePath, String imagePathLeft, String soundFile) {
        file = new File(soundFile);

        this.imagePath = imagePath;
        this.imagePathLeft = imagePathLeft;
        this.animalView = new ImageView(new Image(this.imagePath));
        this.worldStartX = worldStartX;
        this.worldStartY = worldStartY;
        this.worldEndX = worldEndX;
        this.worldEndY = worldEndY;
        this.root = root;
        this.openedMenu = false;
        this.thirstLvl = 100;
        this.hungerLvl = 100;
        this.cost = 100;
        this.well = well;
        this.deltaX = 0;
        this.deltaY = 0;


        this.feeder = feeder;
        this.mediaView = new MediaView();
        animalView.setFitWidth(100);
        animalView.setFitHeight(100);
        animalView.setCursor(Cursor.HAND);


        int x = random.nextInt(worldStartX+50, worldEndX-50);
        int y = random.nextInt(worldStartY+50, worldEndY - 50);
        animalView.setLayoutX(x);
        animalView.setLayoutY(y);



        thirst();
        hunger();
        movement();

    }

    /**
     * Метод для обробки руху тварини
     */
    @Override
    public void movement() {
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(2000));
        translateTransition.setNode(animalView);
        directionRight = true;
        translateTransition.setOnFinished(event -> {
            animalView.setLayoutX(animalView.getLayoutX()+deltaX);
            animalView.setLayoutY(animalView.getLayoutY()+deltaY);

            // Скидання translateX і translateY
            animalView.setTranslateX(0);
            animalView.setTranslateY(0);

            setRandomDirection();
            translateTransition.play();
        });
        setRandomDirection();
        translateTransition.play();

        animalView.setOnMouseClicked(this::handleMouseClicked);
        animalView.setOnMouseDragged(this::handleMouseDragged);
        animalView.setOnMouseReleased(this::handleMouseReleased);
    }

    /**
     * Встановлення випадкового напрямку руху
     */
    @Override
    public void setRandomDirection() {
        double x = animalView.getLayoutX();
        double y = animalView.getLayoutY();


        if (directionRight) {
            deltaX = random.nextInt(10, 50);
        } else {
            deltaX = random.nextInt(-50, -10);
        }
        deltaY = random.nextInt(120) - 75;

        double newX = x + deltaX;
        double newY = y + deltaY;

        if (newX < worldStartX && !directionRight) {
            deltaX = -deltaX;
            animalView.setImage(new Image(imagePath));
            directionRight = !directionRight;
        } else if (newX > worldEndX && directionRight) {
            deltaX = -deltaX;
            if (imagePathLeft != null) {
                animalView.setImage(new Image(imagePathLeft));
            }
            directionRight = !directionRight;
        }
        if (newY < worldStartY || newY > worldEndY - animalView.getFitHeight()) {
            deltaY = -deltaY;
        }

        translateTransition.setByX(deltaX);
        translateTransition.setByY(deltaY);
    }


    /**
     * Обробка перетягування миші
     * @param event
     */
    @Override
    public void handleMouseDragged(MouseEvent event) {
        translateTransition.pause();
        double newX = event.getSceneX() - animalView.getFitWidth() / 2;
        double newY = event.getSceneY() - animalView.getFitHeight() / 2;

//        Перевірка, щоб тварина не виходила за межі світу
        if (newX < worldStartX) newX = worldStartX;
        if (newX > worldEndX - animalView.getFitWidth()) newX = worldEndX - animalView.getFitWidth();
        if (newY < worldStartY) newY = worldStartY;
        if (newY > worldEndY - animalView.getFitHeight()) newY = worldEndY - animalView.getFitHeight();

        animalView.setLayoutX(newX);
        animalView.setLayoutY(newY);
    }

    /**
     * Обробка відпускання миші
     * @param event
     */
    @Override
    public void handleMouseReleased(MouseEvent event) {
        double x = animalView.getLayoutX();
        double y = animalView.getLayoutY();
        boolean outOfBounds = false;

        if (x < worldStartX) {
            x = worldStartX;
            outOfBounds = true;
        } else if (x > worldEndX - animalView.getFitWidth()) {
            x = worldEndX - animalView.getFitWidth();
            outOfBounds = true;
        }

        if (y < worldStartY) {
            y = worldStartY;
            outOfBounds = true;
        } else if (y > worldEndY - animalView.getFitHeight()) {
            y = worldEndY - animalView.getFitHeight();
            outOfBounds = true;
        }
        translateTransition.play();

    }

    /**
     * Обробка кліку миші
     * @param event
     */
    @Override
    public void handleMouseClicked(MouseEvent event) {
        if (!openedMenu) {
            double x = event.getSceneX();
            double y = event.getSceneY();

            // Перевірка, щоб меню не виходило за межі вікна
            double menuWidth = 200;
            double menuHeight = 200;
            if (x + menuWidth > root.getWidth()) {
                x = root.getWidth() - menuWidth;
            }
            if (y + menuHeight > root.getHeight()) {
                y = root.getHeight() - menuHeight;
            }
            addMenu(x, y);

        } else {
            removeMenu();
        }
        playSound();

    }

    /**
     * Додавання меню
     * @param x
     * @param y
     */
    @Override
    public void addMenu(double x, double y) {
        animalMenu = new AnimalMenu(this, x, y);
        root.getChildren().add(animalMenu.getRoot());
        openedMenu = true;
        translateTransition.pause();
    }

    /**
     * Видалення меню
     */
    @Override
    public void removeMenu() {
        root.getChildren().remove(animalMenu.getRoot());
        openedMenu = false;
        translateTransition.play();
    }

    /**
     * Запуск анімації
     */
    @Override
    public void play() {
        translateTransition.play();
    }

    /**
     * Метод для поступової зміни голоду
     */
    @Override
    public void hunger() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (hungerLvl > 0) {
                    hungerLvl--;
                    cost = (int) (100 * ((double)hungerLvl / 100));
                } else {
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    /**
     * Метод для поступової зміни спраги
     */
    @Override
    public void thirst() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (thirstLvl > 0) {
                    thirstLvl--;
                } else {
                    timer.cancel();
                }
            }
        };

        // Запуск завдання з інтервалом 5 секунд (5000 мілісекунд)
        timer.scheduleAtFixedRate(task, 0, 5000);
    }

    /**
     * Метод для оновлення спраги
     */
    @Override
    public void drink() {
        System.out.println(FirstLevel.countCow);
        if (thirstLvl < 100) {
            thirstLvl += 50;
            if (thirstLvl > 100) {
                thirstLvl = 100;
            }
            well.getWater();
        }
    }

    @Override
    public void emotions(){

    }

    /**
     * Метод для продажу тварини
     */
    @Override
    public void sell() {
        FirstLevel.wallet.income(cost);
        removeMenu();
        delete();
        String animalType = this.getClass().getSimpleName();
        decreaseAnimalCount(animalType);

        mediaPlayer.stop();
    }

    /**
     * Метод для смерті тварини
     */
    @Override
    public void death() {
        if (this.hungerLvl == 0 || this.thirstLvl == 0) {
            FirstLevel.wallet.expense(63);
            removeMenu();
            delete();
//            FirstLevel.sellAnimal(this);
            String animalType = this.getClass().getSimpleName();
             decreaseAnimalCount(animalType);
            mediaPlayer.stop();
        }
    }

    /**
     * Мето для видалення
     */
    @Override
    public void delete(){
        releaseResources();
       Platform.runLater( () -> root.getChildren().remove(animalView));
    }

    /**
     * Метод для обнулення ресурсів
     */
    @Override
    public void releaseResources(){
        translateTransition.stop();
        hungerLvl = 0;
        thirstLvl = 0;
    }

    /**
     * Отримання рівня голоду
     * @return
     */
    @Override
    public int getHungerLvl() {
        return hungerLvl;
    }

    /**
     * Отримання рівня спраги
     * @return
     */
    @Override
    public int getThirstLvl() {
        return thirstLvl;
    }

    /**
     * Отримання ціни тварини
     * @return
     */
    @Override
    public int getCost() {
        return cost;
    }


    /**
     * Метод для програвання звуку тварини
     */
    public void playSound() {

        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.33);
        root.getChildren().add(mediaView);
        mediaPlayer.play();

        root.getChildren().remove(mediaView);
    }

    /**
     * Отримання зображення тварини
     * @return
     */
    @Override
    public ImageView getAnimalView() {
        return animalView;
    }
    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        AbstractAnimal.mediaPlayer = mediaPlayer;
    }
    public static void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    /**
     * Зменшення кількості певної тварини для сереалізації
     * @param animalType
     */
    public static void decreaseAnimalCount(String animalType) {
        switch (animalType) {
            case "Cow" ->
                FirstLevel.countCow--;

            case "Sheep" ->
                FirstLevel.countSheep--;

            case "Goose" ->
                FirstLevel.countGoose--;

            case "Pig" ->
                FirstLevel.countPig--;

            case "Rabbit" ->
                FirstLevel.countRabbit--;

            case "Monkey" ->
                SecondLevel.countMonkeys--;

            case "Lemur" ->
                SecondLevel.countLemurs--;

            case "Dragonfly" ->
                SecondLevel.countDragonflies--;
            case "Peacock" ->
                SecondLevel.countPeacocks--;
            case "Parrot" ->
                SecondLevel.countParrots--;
            case "Fairy" ->
                    ThirdLevel.countFairy--;
            case "Gnome" ->
                    ThirdLevel.countGnome--;
            case "Unicorn" ->
                    ThirdLevel.countUnicorn--;
            case "Mushroom" ->
                    ThirdLevel.countMushroom--;
            case "Minotaur" ->
                    ThirdLevel.countMinotaur--;


        }
    }

}


