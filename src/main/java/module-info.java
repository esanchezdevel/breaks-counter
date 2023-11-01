module breaksCounter {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.media;

  opens com.esanchez.devel.breakscounter.window to javafx.fxml;
  exports com.esanchez.devel.breakscounter.window;
}
