module breaksCounter {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.media;
  requires org.apache.logging.log4j;

  opens com.esanchez.devel.breakscounter.window to javafx.fxml;
  exports com.esanchez.devel.breakscounter.window;
}
