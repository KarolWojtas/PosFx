module posfx.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires jackson.databind;
    requires io.reactivex.rxjava2;
    requires java.sql;
    requires java.instrument;
    requires java.xml;
    opens com.karol.posfx.controllers to javafx.fxml;
    exports com.karol.posfx.model.dto to jackson.databind;
    exports com.karol.posfx.mappers to jackson.databind;
    opens com.karol.posfx.ui.cells to javafx.fxml;
    exports com.karol.posfx;
}