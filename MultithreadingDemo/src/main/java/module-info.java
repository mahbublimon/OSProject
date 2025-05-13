module com.example.multithreadingdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.multithreadingdemo to javafx.fxml;
    exports com.example.multithreadingdemo;
}