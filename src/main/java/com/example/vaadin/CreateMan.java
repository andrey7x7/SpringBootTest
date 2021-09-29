//package com.example;
//
//import com.vaadin.annotations.Theme;
//import com.vaadin.data.Binder;
//import com.vaadin.server.VaadinRequest;
//import com.vaadin.server.VaadinServlet;
//import com.vaadin.ui.*;
//import javax.servlet.annotation.WebServlet;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Theme("mytheme")
//public class CreateMan extends UI {
//
//    //private Binder<Man> binder = new Binder(Man.class);
//    List<Man> db;
//
//    public CreateMan(){
//        db = new ArrayList<>();
//        System.out.println("recreate CLASS");
//    }
//
//
//    @Override
//    protected void init(VaadinRequest vaadinRequest) {
//
//
//        final VerticalLayout layoutV = new VerticalLayout();
//        final HorizontalLayout layoutH = new HorizontalLayout();
//
//        final TextField fieldName = new TextField();
//        fieldName.setCaption("Name");
//        fieldName.setValue("Name");
//        layoutH.addComponent(new VerticalLayout(fieldName));
//
//        final ComboBox<String> boxProf = new ComboBox<>();
//        boxProf.setItems("Manager", "Worker");
//        boxProf.setCaption("Profession");
//        boxProf.setEmptySelectionAllowed(false);
//        boxProf.setValue("Worker");
//        layoutH.addComponent(new VerticalLayout(boxProf));
//
//        final DateField birthDay = new DateField("Birth Day");
//        birthDay.setValue(LocalDate.now());
//        layoutH.addComponent(new VerticalLayout(birthDay));
//
//        final Grid<Man> grid = new Grid<>(Man.class);
//        grid.setItems(db);
//        grid.addItemClickListener(
//                event -> {
////                    fieldName.setValue(event.getItem().getName());
////                    boxProf.setValue(event.getItem().getProfession());
////                    birthDay.setValue(event.getItem().getBirthDay());
//                    System.out.println(event.getItem().toString());
//                    printMan(fieldName,boxProf,birthDay,event.getItem());
//                }
//        );
//        layoutH.addComponent(new VerticalLayout(grid));
//
//        final Button createMan = new Button("Create Man");
//        createMan.addClickListener(e -> {
//            db.add(new Man(fieldName.getValue(),boxProf.getValue(),birthDay.getValue()));
//            //printMan();
//            grid.setItems(db);
//        });
//        layoutH.addComponent(new VerticalLayout(createMan));
//
//        layoutV.addComponent(layoutH);
//        layoutV.addComponent(grid);
//
//        setContent(layoutV);
//    }
//
//    public void printMan(TextField fieldName,ComboBox boxProf, DateField birthDay, Man event )
//    {
//        fieldName.setValue(event.getName());
//        boxProf.setValue(event.getProfession());
//        birthDay.setValue(event.getBirthDay());
//    }
//
//
//    @WebServlet(urlPatterns = "/man/*", name = "Man", asyncSupported = true)
//    public static class MyCalcServlet extends VaadinServlet {
//    }
//}
