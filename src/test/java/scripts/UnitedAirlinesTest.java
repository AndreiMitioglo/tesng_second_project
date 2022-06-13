package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Driver;

    public class UnitedAirlinesTest extends Base {


        @Test(priority = 1, description = "Test Case 1: Validate \"Main menu\" navigation items")
        public void testNavigationItems() {
            driver.get("https://www.united.com/en/us");

            String[] expectedManuList = {"BOOK", "MY TRIPS", "TRAVEL INFO", "MILEAGEPLUS® PROGRAM", "DEALS", "HELP"};
            for (int i = 0; i < unitedMainPage.mainMenuList.size(); i++) {
                Assert.assertEquals(unitedMainPage.mainMenuList.get(i).getText(), expectedManuList[i]);
                Assert.assertTrue(unitedMainPage.mainMenuList.get(i).isDisplayed());
            }
        }

        @Test(priority = 2, description = "Test Case 2: Validate \"Book travel menu\" navigation items")
        public void validateBookTravelNavigationItems() {
            driver.get("https://www.united.com/en/us");

            String[] expectedBookTravelManu = {"Book", "Flight status", "Check-in", "My trips"};
            for (int i = 0; i < expectedBookTravelManu.length; i++) {
                Assert.assertTrue(unitedMainPage.bookTravelMenu.get(i).isDisplayed());
                Assert.assertEquals(unitedMainPage.bookTravelMenu.get(i).getText(), expectedBookTravelManu[i]);
            }
        }

        @Test(priority = 3, description = "Test Case 3: Validate \"Round-trip\" and \"One-way\" radio buttons")
        public void validateRoundTripOneWay() {
            driver.get("https://www.united.com/en/us");

            Assert.assertTrue(unitedMainPage.RoundTripRadioButton.isDisplayed());
            Assert.assertTrue(unitedMainPage.RoundTripRadioButton.isEnabled());
            Assert.assertTrue(!unitedMainPage.RoundTripRadioButton.isSelected());

            Assert.assertTrue(unitedMainPage.oneWayButton.isDisplayed());
            Assert.assertTrue(unitedMainPage.oneWayButton.isEnabled());
            Assert.assertTrue(!unitedMainPage.oneWayButton.isSelected());

            unitedMainPage.oneWayButton.click();
            Assert.assertTrue(unitedMainPage.oneWayButton.isDisplayed());
            Assert.assertTrue(!unitedMainPage.RoundTripRadioButton.isSelected());
        }

        @Test(priority = 4, description = "Test Case 4: Validate \"Book with miles\" and \"Flexible dates\" checkboxes")
        public void validatesBookWhitMilesFlexibleDates() {

            unitedMainPage.flexibleDates.click();

            Assert.assertTrue(unitedMainPage.bookWhitMiles.isSelected());
            Assert.assertTrue(unitedMainPage.flexibleDates.isSelected());
            unitedMainPage.bookWhitMiles.click();
            unitedMainPage.flexibleDates.click();
            Assert.assertTrue(unitedMainPage.bookWhitMiles.isSelected());
            Assert.assertTrue(unitedMainPage.flexibleDates.isSelected());
            unitedMainPage.bookWhitMiles.click();
            unitedMainPage.flexibleDates.click();
            Assert.assertTrue(!unitedMainPage.bookWhitMiles.isSelected());
            Assert.assertTrue(!unitedMainPage.flexibleDates.isSelected());

        }

        @Test(priority = 5, description = "Test Case 5: Validate One-way ticket search results \"from Chicago, IL, US (ORD) to Miami, FL, US (MIA)")
        public void validateDeparture() {
            driver.get("https://www.united.com/en/us");


            unitedMainPage.fromInputBox.clear();
            unitedMainPage.oneWayButton.click();
            unitedMainPage.fromInputBox.sendKeys("Chicago, IL, US (ORD)");
            unitedMainPage.toInputBox.clear();
            unitedMainPage.toInputBox.sendKeys("Miami, FL, US (MIA)");

            unitedMainPage.dateOfFly.clear();
            unitedMainPage.dateOfFly.sendKeys("Jan 30");

            unitedMainPage.numbersOfTravelersInput.sendKeys("2");
            unitedMainPage.numberOfTravelersButton.click();
            unitedMainPage.typeOfFly.click();
            unitedMainPage.businessClass.click();
            unitedMainPage.findFlightsButton.click();

            Assert.assertEquals(flightsPage.departureInfo.getText(), "Depart: Chicago, IL, US to Miami, FL, US");

        }
    }