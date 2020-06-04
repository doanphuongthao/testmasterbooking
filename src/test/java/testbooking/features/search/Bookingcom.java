package testbooking.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import testbooking.action.Chonngay;
import testbooking.action.Chonphong;
import testbooking.question.BookingResultQuestion;
import testbooking.tasks.SearchDestination;

import java.time.LocalDate;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@RunWith(SerenityRunner.class)
public class Bookingcom {

    Actor anna = Actor.named("Anna");

    @Managed(uniqueSession = true)
    public WebDriver herBrowser;

    @Before
    public void annaCanBrowseTheWeb() {
        anna.can(BrowseTheWeb.with(herBrowser));
    }

    @Test
    public void search_results_should_show_the_search_term_in_the_title() {

        LocalDate now = LocalDate.now();

        givenThat(anna).wasAbleTo(Open.url("https://www.booking.com/"));

        when(anna).attemptsTo(
                SearchDestination.withKeyword("Phú Quốc"),
                Chonngay.checkin(now.plusDays(7)).checkout(now.plusDays(10)),
                Chonphong.adults(4).child(3).room(2)
        );

        then(anna).should(seeThat("The number of matched rooms ",BookingResultQuestion.getResult(), equalTo("272")));

    }
}