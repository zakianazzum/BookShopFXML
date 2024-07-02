package com.example.bookshop;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleFloatProperty;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookShopController
{
    @javafx.fxml.FXML
    private RadioButton bookLendRadioButton;
    @javafx.fxml.FXML
    private ComboBox<String> bookNameComboBox;
    @javafx.fxml.FXML
    private Button checkOutButton;
    @javafx.fxml.FXML
    private TableColumn<BillSummary, Float> withVateTableColumn;
    @javafx.fxml.FXML
    private DatePicker deliveryOrReturnDatePicker;
    @javafx.fxml.FXML
    private Label vatrateLabel;
    @javafx.fxml.FXML
    private Label bookPriceLabel;
    @javafx.fxml.FXML
    private ComboBox<Integer> quantityComboBox;
    @javafx.fxml.FXML
    private Label cartItemShowListLabel;
    @javafx.fxml.FXML
    private RadioButton bookBuyRadioButton;
    @javafx.fxml.FXML
    private Label showWarningLabel;
    @javafx.fxml.FXML
    private Button addItemToTheCartButton;
    @javafx.fxml.FXML
    private TableColumn<BillSummary, Float> priceTableColumn;
    @javafx.fxml.FXML
    private TableColumn<BillSummary, String> bookNameTableColumn;
    @javafx.fxml.FXML
    private TableColumn<BillSummary, Integer> quantityTableColumn;
    @javafx.fxml.FXML
    private TableView<BillSummary> showingDetailsTableView;
    @javafx.fxml.FXML
    private TableColumn<BillSummary, LocalDate> dateShowingTableColumn;
    @javafx.fxml.FXML
    private Label grossToalLabel;

    private ArrayList<BillSummary> summaryItems = new ArrayList<>();

    private String[] bookNames = {"Joule Verne's Eighty Days Around The World", " Sherlok Holmes", "Adolf Hitler", " Wings of Fire"};
    private float[] prices = {350.0f, 600.0f, 400.0f, 300.0f, 450.0f};
    private int[] vatRates = {1, 1, 2, 4, 2};
    private int totalQuantity = 0;


    @javafx.fxml.FXML
    public void initialize() {
        quantityComboBox.getItems().addAll(1,2,3,4,5);

        bookNameComboBox.getItems().addAll(bookNames);
        bookNameComboBox.setOnAction(this::onBookNameSelected);

        bookNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        quantityTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        withVateTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        dateShowingTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        /*





        bookLendRadioButton.setOnAction(event -> {
            if (bookLendRadioButton.isSelected()) {
                bookBuyRadioButton.setSelected(false);
            }
        });

        bookBuyRadioButton.setOnAction(event -> {
            if (bookBuyRadioButton.isSelected()) {
                bookLendRadioButton.setSelected(false);
            }
        }); */

    }

    @javafx.fxml.FXML
    public void bookBuyRadioButtonOnAction(ActionEvent actionEvent) {
        if (bookBuyRadioButton.isSelected()) {
            grossToalLabel.setText(null);
            cartItemShowListLabel.setText(null);
            showingDetailsTableView.getItems().clear();
            quantityComboBox.setValue(null);
            totalQuantity = 0;
            showWarningLabel.setText("You have selected a buy button");
        }
    }

    private int findIndex (String booKName){
        for (int i = 0; i < bookNames.length; i++){
            if (bookNames[i].equals(booKName)){
                return i;
            }

        }
        return -1;
    }

    @javafx.fxml.FXML
    public void addItemToTheCartButtonOnAction(ActionEvent actionEvent) {
        showWarningLabel.setText("");
        String selectedItem = bookNameComboBox.getValue();
        int selectedIndex = findIndex(selectedItem);
        Integer quantity = quantityComboBox.getValue();
        LocalDate selectedDate = deliveryOrReturnDatePicker.getValue();

        if (selectedItem == null || quantity == null || selectedDate == null){
            showWarningLabel.setText("Please fill out all the fields");
            return;

        }
        if (bookLendRadioButton.isSelected()){
            if (totalQuantity + quantity > 3) {
                bookNameComboBox.setValue(null);
                quantityComboBox.setValue(null);
                deliveryOrReturnDatePicker.setValue(null);
                bookPriceLabel.setText("");
                vatrateLabel.setText("");
                showWarningLabel.setText("You cannot Borrow more than 3 Books. Please Choose again.");
                return;
            }

        }


        BillSummary existingItem = null;
        for (BillSummary summary : summaryItems){
            if(summary.getBookName().equals(selectedItem)){
                existingItem = summary;
                break;
            }
        }
        if (existingItem != null){
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        }
        else {
            BillSummary summary = new BillSummary(selectedItem, prices[selectedIndex], quantity, vatRates[selectedIndex],selectedDate);
            summaryItems.add(summary);
        }

        totalQuantity += quantity;
        bookNameComboBox.setValue(null);
        quantityComboBox.setValue(null);
        deliveryOrReturnDatePicker.setValue(null);
        bookPriceLabel.setText("");
        vatrateLabel.setText("");

        updateCartItemShowLabel();

        }
    private void updateCartItemShowLabel() {
        StringBuilder itemsText = new StringBuilder();
        for (BillSummary summary : summaryItems){
            itemsText.append(summary.toString()).append ("\n");
        }
        cartItemShowListLabel.setText(itemsText.toString());
    }


    @javafx.fxml.FXML
    public void onBookNameSelected(ActionEvent actionEvent) {
        String selectedItem = bookNameComboBox.getValue();
        int selectedIndex = findIndex(selectedItem);
        if (selectedIndex != -1){
            bookPriceLabel.setText(prices[selectedIndex] + "");
            vatrateLabel.setText(vatRates[selectedIndex] + "");
        }
    }

    @javafx.fxml.FXML
    public void datePickerOnMouseClick(Event event) {
    }

    @javafx.fxml.FXML
    public void bookLendRadioButtonOnAction(ActionEvent actionEvent) {
       if (bookLendRadioButton.isSelected()) {
           grossToalLabel.setText(null);
           cartItemShowListLabel.setText(null);
           showingDetailsTableView.getItems().clear();
           quantityComboBox.getItems().clear();
           quantityComboBox.getItems().addAll(1,2,3,4,5);
           quantityComboBox.setValue(null);
           totalQuantity = 0;
           showWarningLabel.setText("You have selected a lend button");

       }
    }

    @javafx.fxml.FXML
    public void checkOutButtonOnAction(ActionEvent actionEvent) {
        showingDetailsTableView.getItems().clear();
        showingDetailsTableView.getItems().addAll(summaryItems);
        float grossTotal = 0;
        for (BillSummary summary : summaryItems){
            grossTotal += summary.getTotalAmount();
        }
        grossToalLabel.setText(String.valueOf(grossTotal));

        if (bookLendRadioButton.isSelected()){
            grossToalLabel.setText("On Loosing books, you need to pay this amount : \n" + (grossTotal));
        }
    }
}