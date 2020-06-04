package testbooking.action;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import testbooking.ui.BookingPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Chonphong implements Interaction {
    private int adults;
    private int childrens;
    private int rooms;

    public Chonphong(int adult, int children, int room) {
        this.adults = adult;
        this.childrens = children;
        this.rooms = room;

    }

    @Override
    @Step("{0} books #rooms rooms for #adults adults and #childrens childrents")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BookingPage.VISIT_FIELD)
        );

        for(int i=0;i< adults -2;i++){
            actor.attemptsTo(
                    Click.on(BookingPage.ADULTS)
            );
        }

        for(int i=0;i< childrens;i++){
            actor.attemptsTo(
                    Click.on(BookingPage.CHILDRENS)
            );
        }

        for(int i=0;i< rooms-1;i++){
            actor.attemptsTo(
                    Click.on(BookingPage.ROOM)
            );
        }

        actor.attemptsTo(
            Click.on(BookingPage.BUTTON_SEARCH)
        );

    }

    public static ChooseVisitBuilder adults(int adults) {

        return new ChooseVisitBuilder(adults);
    }

    public static class ChooseVisitBuilder{
        private int adult;
        private int children;

        public ChooseVisitBuilder(int adult){
            this.adult = adult;
        }

        public ChooseVisitBuilder child(int children){
            this.children =children;
            return this;
        }

        public Interaction room(int room){
            return instrumented(Chonphong.class, this.adult, this.children, room);
        }
    }

}
