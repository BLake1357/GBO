module GBO
{
    requires javafx.controls;

    requires javafx.fxml;

    requires transitive javafx.graphics;

    requires java.scripting;

    requires javafx.base;

    requires java.desktop;

    requires org.junit.jupiter.api;

    requires java.xml;

    exports gui.mvp.vocabtrainer;

    exports gui.animation;

    exports gui.calculator;

    exports gui.charts;

    exports gui.collections;

    exports gui.container;

    exports gui.controls;

    exports gui.controls2;

    exports gui.counter;

    exports gui.dragndrop;

    exports gui.intro;

    exports gui.mvp.contact;

    exports gui.mvp.contact.detail;

    exports gui.mvp.contact.main;

    exports gui.mvp.contact.model;

    exports gui.mvp.contact.overview;

    exports gui.mvp.login;

    exports gui.mvp.login.fxml;

    exports gui.mvp.login.fxml.controller;

    exports gui.mvp.login.fxml.di;

    exports gui.pizza;

    exports gui.plusminus;

    exports gui.properties;

    exports gui.shapes;

    exports gui.stage;

    exports gui.undoredo;

    exports gui.country.combo;

    exports gui.mvp.basicquiz;

    exports gui.mvp.basicquiz.game;

    exports gui.mvp.basicquiz.main;

    exports gui.mvp.basicquiz.model;

    exports gui.mvp.basicquiztable;

    exports gui.mvp.basicquiztable.game;

    exports gui.mvp.basicquiztable.main;

    exports gui.mvp.basicquiztable.model;

    exports gui.mvp.basicquiztable.overview;

    exports gui.mvp.editquiz;

    exports gui.mvp.editquiz.game;

    exports gui.mvp.editquiz.main;

    exports gui.mvp.editquiz.model;

    exports gui.mvp.editquiz.overview;

    exports gui.mvp.editquiz.editor;

    exports gui.graphics.sinus;

    exports gui.tutorial.ws20192020;

    exports gui.tutorial.ws20192020.mvp.counter;

    exports gui.drawing;

    exports gui.drawingmenu;

    exports gui.exam;

    exports gui.exam2;

    opens gui.country.combo;

    opens gui.pizza;

    opens gui.animation;

    opens gui.charts;

    opens gui.collections;

    opens gui.container;

    opens gui.controls;

    opens gui.controls2;

    opens gui.dragndrop;

    opens gui.intro;

    opens gui.mvp.contact;

    opens gui.mvp.contact.detail;

    opens gui.mvp.contact.main;

    opens gui.mvp.contact.model;

    opens gui.mvp.contact.overview;

    opens gui.mvp.login;

    opens gui.mvp.login.fxml;

    opens gui.mvp.login.fxml.controller;

    opens gui.mvp.login.fxml.di;

    opens gui.properties;

    opens gui.shapes;

    opens gui.stage;

    opens gui.undoredo;

    opens gui.plusminus;

    opens gui.calculator;

    opens gui.mvp.vocabtrainer;
}