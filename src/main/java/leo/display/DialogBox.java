package leo.display;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import leo.exceptions.InputException;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Instantiates a DialogBox object.
     *
     * @param text Text to be displayed.
     * @param img Image of the speaker.
     */
    private DialogBox(String text, Image img) throws InputException {
        assert text != null : "Text must not be null";
        assert img != null : "Image must not be null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new InputException("load");
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets the text of the User to be displayed on the GUI.
     *
     * @param text Text to be displayed.
     * @param img Image of the speaker.
     * @return The DialogBox object containing information to be displayed on the GUI.
     */
    public static DialogBox getUserDialog(String text, Image img) throws InputException {
        return new DialogBox(text, img);
    }

    /**
     * Gets the text of the Leo to be displayed on the GUI.
     *
     * @param text Text to be displayed.
     * @param img Image of the speaker.
     * @return The DialogBox object containing information to be displayed on the GUI.
     */
    public static DialogBox getLeoDialog(String text, Image img) throws InputException {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
