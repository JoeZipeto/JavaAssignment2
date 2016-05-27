//********************************************************************************************************
//*	Project: COMP2080_ASSIGN2_Coder_Folks
//*	Assignment: assignment#2
//*	Authors: Bahman Yaghoubi Vije, Joseph Zipeto
//*	Student numbers: 100968843, 100963441
//*	Date: April 10, 2016
//*	Description: This file contains a main class to test AutoComplete class. 
//* JavaFX is used to show the result as a form
//********************************************************************************************************
package drivers;

import java.io.BufferedReader;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import java.io.InputStreamReader;
import java.util.Arrays;

import timing.Stopwatch;
import autoComplete.Autocomplete;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import term.Term;

public class TestAutocomplete extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	// Defining all of the needed controls and variables
	Label lblTitle = new Label("Test \"Autocomplete\" class");
	Label lblPalin = new Label("Enter any word. As you type, the program predicts the complete query");
	Label lblResult = new Label("Selected items:\n\n");
	Label lblTime = new Label("Search Time:\n\n");
	Button btnClear = new Button("Clear");
	TextField txtEnter = new TextField("");
	TextField txtTime = new TextField("");
	final ToggleGroup group = new ToggleGroup();
	RadioButton cities = new RadioButton("cities");
	RadioButton wiktionary = new RadioButton("wiktionary");
	GridPane grid = new GridPane();
	FileChooser fileChooser = new FileChooser();
	StringBuilder content = new StringBuilder();
	String path;
	String everything;
	Term[] terms;
	String query;
	Double weight;
	Stopwatch timer = new Stopwatch();

	@Override
	public void start(Stage stage) {
		// start the stage
		stage.setTitle("Assignment2 Test Environment");
		Scene scene = new Scene(new Group(), 800, 580, Color.BEIGE);
		Group root = (Group) scene.getRoot();
		ListView<String> list = new ListView<String>();
		ListView<String> listResult = new ListView<String>();
		// create radio buttons to choose the target file
		cities.setToggleGroup(group);
		wiktionary.setToggleGroup(group);
		
		// setting x and y coordinates and other properties
		// like font and color for controls
		lblTitle.setTranslateY(20);
		lblTitle.setMinWidth(700);
		lblTitle.setTextFill(Color.web("#0076a3"));
		lblTitle.setFont(Font.font(null, FontWeight.BOLD, 28));
		lblPalin.setTranslateY(20);
		lblPalin.setMinWidth(700);
		txtEnter.setTranslateY(20);
		txtEnter.setMinWidth(700);
		lblResult.setTranslateY(50);
		lblResult.setMinWidth(700);
		lblResult.setWrapText(true);
		lblResult.setTextFill(Color.web("#0076a3"));
		lblResult.setFont(Font.font(null, FontWeight.BOLD, 12));
		cities.setTranslateY(20);
		wiktionary.setTranslateY(20);
		wiktionary.setTranslateX(-600);
		lblTime.setMinWidth(200);
		lblTime.setTranslateY(20);
		lblTime.setTranslateX(-470);
		txtTime.setMinWidth(295);
		txtTime.setTranslateX(-600);
		txtTime.setTranslateY(20);
		btnClear.setTranslateY(40);
		btnClear.setMinWidth(150);
		btnClear.setFont(Font.font(null, FontWeight.BOLD, 12));
		list.setMaxWidth(720);
		list.setMaxHeight(180);
		list.setTranslateY(30);
		listResult.setMaxWidth(720);
		listResult.setMaxHeight(100);
		listResult.setTranslateY(40);

		btnClear.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			// Adding the shadow when the mouse cursor is on
			DropShadow shadow = new DropShadow();
			btnClear.setEffect(shadow);
		});

		btnClear.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			// Removing the shadow when the mouse cursor is off
			btnClear.setEffect(null);
		});

		btnClear.setOnAction(new EventHandler<ActionEvent>() {
			// clearing the form for new search
			public void handle(ActionEvent e) {
				txtEnter.setText("");
				list.getItems().clear();
				listResult.getItems().clear();
				txtTime.setText("");
			}
		});
	
		cities.setSelected(true);
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (group.getSelectedToggle() != null) {
					
					path = (cities.isSelected() ? cities.getText() : wiktionary.getText()) + ".txt";

								}
			}
		});
		try {
			// read from the file using path and make them uni-code
			BufferedReader input = new BufferedReader(
					new InputStreamReader(TestAutocomplete.class.getResourceAsStream(path), "UTF-8"));

			;
			// read the first element which contains the number of
			// elements
			int numElements = Integer.parseInt(input.readLine());
			// create an array of Term objects with the same number
			// of elements
			// read in the previous step
			terms = new Term[numElements];
			for (int i = 0; i < terms.length; i++) {
				query = input.readLine();
				weight = Double.parseDouble(query.split("\t")[0]);
				query = query.split("\t")[1];
				terms[i] = new Term(query, weight);
			}
			input.close();
			// sort the array so binary search could be done
			Arrays.sort(terms);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		txtEnter.textProperty().addListener(new ChangeListener<String>() {
			// text changed event handler for TextField
			@SuppressWarnings("rawtypes")
			@Override
			public void changed(ObservableValue observableValue, String oldValue, String newValue) {
				timer.start();
				// set the path to the text of the selected radio button
				everything = "";
				// handle the situation when the search TextField gets empty
				if (newValue.length() == 0)
					newValue = " ";
				{
					try {

						// create an object of Autocomplete class
						Autocomplete autocomp = new Autocomplete(terms);
						// create another array of Term object to hold matching
						// elements
						// numberOfMatches method of the Autocomplete class is
						// called to
						// define the number of array elements.
						Term[] matches = new Term[autocomp.numberOfMatches(newValue)];

						// fill matches array with matching elements.
						// allMatches method of the Autocomplete class is being
						// used.
						matches = autocomp.allMatches(newValue);
						timer.stop();
						for (int i = 0; i < matches.length; i++) {
							// append all matching elements to an string
							// variable separated by \n.
							everything += (matches[i].getQuery() + "\n");
						}
					} catch (Exception e) {
					}

					// splitting the result lie by line and save them in an
					// arrayList
					ObservableList<String> olist = FXCollections.observableArrayList(everything.split("\n"));
					// populating listView by term queries of matching elements
					list.setItems(olist);

					txtTime.setText(timer.time() + "");

				}
			}
		});

		list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// populating the listResult ListView by selected elements
			if (newValue != null)
				listResult.getItems().add(newValue);
		});

		// adding all of the controls to the grid
		grid.setVgap(4);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 55));
		grid.add(lblTitle, 0, 0);
		grid.add(lblPalin, 0, 1);
		grid.add(cities, 0, 2);
		grid.add(wiktionary, 1, 2);
		grid.add(lblTime, 2, 2);
		grid.add(txtTime, 3, 2);
		grid.add(txtEnter, 0, 6);
		grid.add(lblResult, 0, 9);
		grid.add(listResult, 0, 10);
		grid.add(btnClear, 0, 8);
		grid.add(list, 0, 7);
		root.getChildren().add(grid);
		stage.setScene(scene);
		stage.show();
	}
}