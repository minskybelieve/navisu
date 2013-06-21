/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.view.instruments.shelf;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Control;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.effect.ReflectionBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.util.Duration;
import org.navisu.instrument.controller.InstrumentsGlassPaneController;
import org.navisu.instrument.model.Display;
import org.navisu.instrument.view.gps.GPS;
import org.navisu.instrument.view.sounder.Sounder;

import org.navisu.nmea.devices.view.instruments.gps.controller.GPSController;
import org.navisu.nmea.devices.view.instruments.sounder.controller.SounderController;
import org.navisu.nmea.devices.view.layers.controller.GPSLayerController;
import org.navisu.nmea.devices.view.widget.ButtonWidget;
import org.navisu.widget.view.cube3D.Cube3D;


/**
 *
 * @author Serge
 */
public class DisplayShelf
        extends Display {

    private static String ID = "displayShelf";
    private Image backgroundImage;
    private static String BACKGROUND_IMAGE = "displayL.png";
    //  protected ImageView background;
    private double initX;
    private double initY;
    private Point2D dragAnchor;
    //  protected String rootDir = null;
    protected final String IMAGES = "/resources/images/";
    protected Button quit;
    protected final int LAYOUT_XX = 813;
    protected InstrumentsGlassPaneController instrumentsGlassPaneController;

    public DisplayShelf(final String backgroundFileName, InstrumentsGlassPaneController igpc) {
        super(ID, BACKGROUND_IMAGE);
        instrumentsGlassPaneController = igpc;

        backgroundImage = new Image(rootDir + IMAGES + backgroundFileName);
        background = ImageViewBuilder.create()
                .id("background")
                .pickOnBounds(true)
                .image(backgroundImage)
                .build();
        getChildren().add(background);
        Rectangle interiorBackgroundClip = RectangleBuilder.create()
                .id("r1")
                .fill(new Color(0.078125, 0.078125, 0.078125, 1.0))
                .layoutX(30)
                .layoutY(30)
                .width(780)
                .height(314)
                .arcHeight(20)
                .arcWidth(20)
                .build();
        getChildren().add(interiorBackgroundClip);
        createScene();

    }

    @Override
    protected void createScene() {
        // load images
        Image[] images = new Image[7];
        images[0] = new Image(DisplayShelf.class.getResource("resources/images/instrument_0.png").toExternalForm(), false);
        images[1] = new Image(DisplayShelf.class.getResource("resources/images/instrument_1.png").toExternalForm(), false);
        images[2] = new Image(DisplayShelf.class.getResource("resources/images/instrument_2.png").toExternalForm(), false);
        images[3] = new Image(DisplayShelf.class.getResource("resources/images/instrument_3.png").toExternalForm(), false);
        images[4] = new Image(DisplayShelf.class.getResource("resources/images/sounder.png").toExternalForm(), false);
        // images[4] = new Image(DisplayShelf.class.getResource("resources/images/sounder.gif").toExternalForm(), false);
        images[5] = new Image(DisplayShelf.class.getResource("resources/images/instrument_4.png").toExternalForm(), false);
        images[6] = new Image(DisplayShelf.class.getResource("resources/images/gps.png").toExternalForm(), false);
        //images[6] = new Image(DisplayShelf.class.getResource("resources/images/gps.gif").toExternalForm(), false);
        // create display shelf
        Shelf displayShelf = new Shelf(images, instrumentsGlassPaneController);
        getChildren().add(displayShelf);
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                initX = getTranslateX();
                initY = getTranslateY();
                dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me != null && dragAnchor != null) {
                    setTranslateX((int) (initX + me.getSceneX() - dragAnchor.getX()));
                    setTranslateY((int) (initY + me.getSceneY() - dragAnchor.getY()));
                }
            }
        });

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(10);
        dropShadow.setOffsetY(10);
        dropShadow.setColor(Color.rgb(50, 50, 50, 0.7));
        setEffect(dropShadow);

        quit = ButtonBuilder.create()
                .id("quit")
                .minWidth(Control.USE_PREF_SIZE)
                .prefWidth(1.0)
                .minHeight(Control.USE_PREF_SIZE)
                .prefHeight(1.0)
                .rotate(40)
                .graphic(new ImageView(new Image(rootDir + IMAGES + "quit.png")))
                .layoutX(LAYOUT_XX)
                .layoutY(24)
                .build();
        getChildren().add(quit);
        quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                setVisible(false);
            }
        });

    }

    /**
     * A ui control which displays a browseble display shlef of images
     */
    public class Shelf
            extends Region {

        private final Duration DURATION = Duration.millis(500);
        private final Interpolator INTERPOLATOR = Interpolator.EASE_BOTH;
        private static final double SPACING = 50;
        private static final double LEFT_OFFSET = -110;
        private static final double RIGHT_OFFSET = 110;
        private static final double SCALE_SMALL = 0.7;
        private PerspectiveImage[] items;
        private Group centered = new Group();
        private Group left = new Group();
        private Group center = new Group();
        private Group right = new Group();
        private int centerIndex = 0;
        private Timeline timeline;
        private Rectangle clip;
        private GPS gps;
        private Sounder sounder;
        private ButtonWidget buttonWidget;
        private Cube3D cube3D;
        protected InstrumentsGlassPaneController instrumentsGlassPaneController;
        protected final String id = "Shelf";

        public Shelf(Image[] images, InstrumentsGlassPaneController igpc) {
            instrumentsGlassPaneController = igpc;
            setId(id);
            clip = RectangleBuilder.create()
                    .layoutX(30)
                    .layoutY(30)
                    .width(780)
                    .height(314)
                    .arcHeight(20)
                    .arcWidth(20)
                    .build();
            setClip(clip);
            // set background gradient using css
            setStyle("-fx-background-color: #131313;");
            // create items
            items = new PerspectiveImage[images.length];
            for (int i = 0; i < images.length; i++) {
                final PerspectiveImage item = items[i] = new PerspectiveImage(images[i]);
                item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent me) {
                        shiftToCenter(item);
                        if (me.isMetaDown() == true && center.getChildren().get(0) == items[6]) {
                            Platform.setImplicitExit(false);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    gps = new GPSController().getDisplay();
                                    instrumentsGlassPaneController.addInstrument(gps);
                                    instrumentsGlassPaneController.startParallelTransition(gps, 0f, 0f, 300f, 300f, 0.0f, 0.0f, 1.f, 1.f);
                                    new GPSLayerController();
                                }
                            });
                        }
                        if (me.isMetaDown() == true && center.getChildren().get(0) == items[4]) {
                            Platform.setImplicitExit(false);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    sounder = new SounderController().getDisplay();
                                    instrumentsGlassPaneController.addInstrument(sounder);
                                    instrumentsGlassPaneController.startParallelTransition(sounder, 0f, 0f, 300f, 300f, 0.0f, 0.0f, 1.f, 1.f);
                                }
                            });
                        }
                        /*
                        if (me.isMetaDown() == true && center.getChildren().get(0) == items[0]) {
                            Platform.setImplicitExit(false);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    buttonWidget = new ButtonWidget();
                                    instrumentsGlassPaneController.add(buttonWidget);
                                    instrumentsGlassPaneController.startParallelTransition(buttonWidget, 0f, 0f, 300f, 300f, 0.0f, 0.0f, 1.f, 1.f);
                                }
                            });
                        }
                        if (me.isMetaDown() == true && center.getChildren().get(0) == items[1]) {
                            Platform.setImplicitExit(false);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    cube3D = new Cube3D();
                                    instrumentsGlassPaneController.add(cube3D);
                                    instrumentsGlassPaneController.getScene().setCamera(new PerspectiveCamera());
                                    instrumentsGlassPaneController.startParallelTransition(cube3D, 0f, 0f, 300f, 300f, 0.0f, 0.0f, 1.f, 1.f);
                                }
                            });
                        }
                        * */
                    }
                });
            }
            // create content
            centered.getChildren().addAll(left, right, center);
            getChildren().addAll(centered);
            // listen for keyboard events
            setFocusTraversable(true);
            setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode() == KeyCode.LEFT) {
                        shift(1);
                    } else if (ke.getCode() == KeyCode.RIGHT) {
                        shift(-1);
                    }
                }
            });

            update();

        }

        @Override
        protected void layoutChildren() {

            // keep centered centered
            centered.setLayoutY(90);
            centered.setLayoutX(300);
        }

        private void update() {
            // move items to new homes in groups
            left.getChildren().clear();
            center.getChildren().clear();
            right.getChildren().clear();
            for (int i = 0; i < centerIndex; i++) {
                left.getChildren().add(items[i]);
            }
            center.getChildren().add(items[centerIndex]);
            for (int i = items.length - 1; i > centerIndex; i--) {
                right.getChildren().add(items[i]);
            }
            // stop old timeline if there is one running
            if (timeline != null) {
                timeline.stop();
            }
            // create timeline to animate to new positions
            timeline = new Timeline();
            // add keyframes for left items
            final ObservableList<KeyFrame> keyFrames = timeline.getKeyFrames();
            for (int i = 0; i < left.getChildren().size(); i++) {
                final PerspectiveImage it = items[i];
                double newX = -left.getChildren().size()
                        * SPACING + SPACING * i + LEFT_OFFSET;
                keyFrames.add(new KeyFrame(DURATION,
                        new KeyValue(it.translateXProperty(), newX, INTERPOLATOR),
                        new KeyValue(it.scaleXProperty(), SCALE_SMALL, INTERPOLATOR),
                        new KeyValue(it.scaleYProperty(), SCALE_SMALL, INTERPOLATOR),
                        new KeyValue(it.angle, 45.0, INTERPOLATOR)));
            }
            // add keyframe for center item
            final PerspectiveImage centerItem = items[centerIndex];
            keyFrames.add(new KeyFrame(DURATION,
                    new KeyValue(centerItem.translateXProperty(), 0, INTERPOLATOR),
                    new KeyValue(centerItem.scaleXProperty(), 1.0, INTERPOLATOR),
                    new KeyValue(centerItem.scaleYProperty(), 1.0, INTERPOLATOR),
                    new KeyValue(centerItem.angle, 90.0, INTERPOLATOR)));
            // add keyframes for right items
            for (int i = 0; i < right.getChildren().size(); i++) {
                final PerspectiveImage it = items[items.length - i - 1];
                final double newX = right.getChildren().size()
                        * SPACING - SPACING * i + RIGHT_OFFSET;
                keyFrames.add(new KeyFrame(DURATION,
                        new KeyValue(it.translateXProperty(), newX, INTERPOLATOR),
                        new KeyValue(it.scaleXProperty(), SCALE_SMALL, INTERPOLATOR),
                        new KeyValue(it.scaleYProperty(), SCALE_SMALL, INTERPOLATOR),
                        new KeyValue(it.angle, 135.0, INTERPOLATOR)));
            }
            // play animation
            timeline.play();
        }

        private void shiftToCenter(PerspectiveImage item) {
            for (int i = 0; i < left.getChildren().size(); i++) {
                if (left.getChildren().get(i) == item) {
                    int shiftAmount = left.getChildren().size() - i;
                    shift(shiftAmount);
                    return;
                }
            }
            if (center.getChildren().get(0) == item) {
                return;
            }
            for (int i = 0; i < right.getChildren().size(); i++) {
                if (right.getChildren().get(i) == item) {
                    int shiftAmount = -(right.getChildren().size() - i);
                    shift(shiftAmount);
                    return;
                }
            }
        }

        public void shift(int shiftAmount) {
            if (centerIndex <= 0 && shiftAmount > 0) {
                return;
            }
            if (centerIndex >= items.length - 1 && shiftAmount < 0) {
                return;
            }
            centerIndex -= shiftAmount;
            update();
        }
    }

    /**
     * A Node that displays a image with some 2.5D perspective rotation around
     * the Y axis.
     */
    public static class PerspectiveImage extends Parent {

        private static final double REFLECTION_SIZE = 0.25;
        private static final double WIDTH = 200;
        private static final double HEIGHT = WIDTH + (WIDTH * REFLECTION_SIZE)/* + 40*/;
        private static final double RADIUS_H = WIDTH / 2;
        private static final double BACK = WIDTH / 10;//10
        private PerspectiveTransform transform = new PerspectiveTransform();
        /**
         * Angle Property
         */
        private final DoubleProperty angle = new SimpleDoubleProperty(45) {
            @Override
            protected void invalidated() {
                // when angle changes calculate new transform
                double lx = (RADIUS_H - Math.sin(Math.toRadians(angle.get())) * RADIUS_H - 1);
                double rx = (RADIUS_H + Math.sin(Math.toRadians(angle.get())) * RADIUS_H + 1);
                double uly = (-Math.cos(Math.toRadians(angle.get())) * BACK);
                double ury = -uly;
                transform.setUlx(lx);
                transform.setUly(uly);
                transform.setUrx(rx);
                transform.setUry(ury);
                transform.setLrx(rx);
                transform.setLry(HEIGHT + uly);
                transform.setLlx(lx);
                transform.setLly(HEIGHT + ury);
            }
        };

        public final double getAngle() {
            return angle.getValue();
        }

        public final void setAngle(double value) {
            angle.setValue(value);
        }

        public final DoubleProperty angleModel() {
            return angle;
        }

        public PerspectiveImage(Image image) {
            ImageView imageView = new ImageView(image);
            imageView.setEffect(ReflectionBuilder.create().fraction(REFLECTION_SIZE).build());
            setEffect(transform);
            getChildren().addAll(imageView);
        }
    }
}